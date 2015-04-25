<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
header("Expires: Thu, 01 Jan 1970 00:00:01 GMT"); 


$table = postvalue("table");
$pageType = postvalue("table");
$strTableName = GetTableByShort($table);

if (!checkTableName($table))
{
	exit(0);
}

include("include/".$table."_variables.php");

$gSettings = new ProjectSettings($strTableName, $pageType);

$field = postvalue('searchField');
$value = postvalue('searchFor');
$lookupValue = postvalue('lookupValue');
$LookupSQL = "";
$response = array();
$output = "";

$cipherer = new RunnerCipherer($strTableName);	

$strLoginTable = "WEB";
if ($strTableName != $strLoginTable)
{
	if(!isLogged()) { 
		return;	
	}
	if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Edit") && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Add") && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search")) { return;	}
}
else 
{
	$checkResult = true;
	if($field=="LOGIN")
		$checkResult=false;

	if($field=="SENHA")
		$checkResult=false;

	if($checkResult)
	{
		if(!isLogged()) { return;	}
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Edit") && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Add") && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search")) { return;	}
	}
}

$hasWhere = false;


$fieldsArr = $gSettings->getFieldsList();
$lwDisplayField = '';
$lwLinkField = '';
$lookupField = '';

foreach ($fieldsArr as $f)
{
	$fEditFormat = $gSettings->getEditFormat($f);
	if ($fEditFormat != EDIT_FORMAT_LOOKUP_WIZARD || GoodFieldName($f) != $field)
	{
		continue;
	}
	
	$lookupField = $f;
	$LookupType = $gSettings->getLookupType($f);
	if ($LookupType == LT_LOOKUPTABLE || $LookupType == LT_QUERY)
	{
		$lookupTable = $gSettings->getLookupTable($f);
		$linkFieldName = $gSettings->getLinkField($f);
		$displayFieldName = $gSettings->getDisplayField($f);
		$linkAndDisplaySame = $displayFieldName == $linkFieldName;
		
		$lwDisplayField = $gSettings->getLWDisplayField($f);
		if($LookupType == LT_QUERY){
			$lookupPSet = new ProjectSettings($lookupTable, $pageType);
			$lookupQueryObj = $lookupPSet->getSQLQuery();
			if($gSettings->getCustomDisplay($f))
				$lookupQueryObj->AddCustomExpression($displayFieldName, $lookupPSet, $strTableName, $f);
			$lookupQueryObj->ReplaceFieldsWithDummies($lookupPSet->getBinaryFieldsIndices());
			$cipherer->strTableName = $lookupTable;
		}else{
			$LookupSQLTable = "SELECT ";
			$lwLinkField = $gSettings->GetLWLinkField($f,true);
			if ($gSettings->isLookupUnique($f))
			{
				$LookupSQLTable .= "DISTINCT ";
			}
			$LookupSQLTable .= $cipherer->GetLookupFieldName($lwLinkField, $strTableName, $f, null, true);
			if(!$linkAndDisplaySame)
				$LookupSQLTable .= ",".($lwDisplayField == $lwLinkField ? $cipherer->GetFieldName($lwDisplayField, $f, true) : $lwDisplayField);
			$LookupSQLTable .= " FROM ".AddTableWrappers($lookupTable)." ";
		}
		
		$strLookupWhere = GetLWWhere($f, $pageType, $strTableName);
				if($LookupType == LT_QUERY)
		{	
			$secOpt = $lookupPSet->getAdvancedSecurityType();
			if($secOpt == ADVSECURITY_VIEW_OWN)
				$strLookupWhere = whereAdd($strLookupWhere, SecuritySQL("Search", $lookupTable));
		}
		if ($strLookupWhere)
		{
			$strLookupWhere = " (".$strLookupWhere.")  AND ";
		}
		if($LookupType == LT_QUERY){
			if($gSettings->getCustomDisplay($f))
				$strLookupWhere .= $displayFieldName;
			else
				$strLookupWhere .= GetFullFieldName($displayFieldName, $lookupTable, false);
		}else
			$strLookupWhere .= $cipherer->GetFieldName($lwDisplayField, $f);
			
		$strLookupWhere .= $cipherer->GetLikeClause($LookupType == LT_QUERY ? $displayFieldName : $f, $value);
		
		if ($gSettings->useCategory($f) && (postvalue("category") != '' || postvalue('editMode') != MODE_SEARCH))
		{
			$cvalue = make_db_value($gSettings->getCategoryControl($f), postvalue("category"));
			$strLookupWhere .= " AND ".AddFieldWrappers($gSettings->getCategoryFilter($f))."=".$cvalue;
		}
		
		$lookupOrderBy = $gSettings->getLookupOrderBy($f);
		if(strlen($lookupOrderBy))
		{
			$lookupOrderBy = GetFullFieldName($lookupOrderBy, $lookupTable);
			if($gSettings->isLookupDesc($f))
				$lookupOrderBy .= ' DESC';
		}
		if($LookupType == LT_QUERY){
			$LookupSQL = $lookupQueryObj->toSql(whereAdd($lookupQueryObj->m_where->toSql($lookupQueryObj), $strLookupWhere),
				strlen($lookupOrderBy) ? ' ORDER BY '.$lookupOrderBy : null);
		}else{
			$LookupSQL = $LookupSQLTable." where ".$strLookupWhere;
			if (!$gSettings->isLookupUnique($f) || nDATABASE_Access != 14)
			{
				if ($lookupOrderBy)
				{
					$LookupSQL.= " ORDER BY ".$lookupOrderBy;
				}
			}
		}
	}
	if (strlen(GetLWWhere($f, $pageType, $strTableName)))
	{
		$hasWhere = true;
	}
	break;
}

$lookupIndexes = GetLookupFieldsIndexes($gSettings, $lookupField);
$linkFieldIndex = $lookupIndexes["linkFieldIndex"];
$displayFieldIndex = $lookupIndexes["displayFieldIndex"];

$rs = db_query($LookupSQL,$conn);

while ($data = db_fetch_numarray($rs)) 
{
	if($LookupType == LT_QUERY && $gSettings->isLookupUnique($f)){
		if(!isset($uniqueArray))
			$uniqueArray = array();
		if(in_array($data[$displayFieldIndex], $uniqueArray))
			continue;
		$uniqueArray[] = $data[$displayFieldIndex];
	}
	$data[$linkFieldIndex] = $cipherer->DecryptField($f, $data[$linkFieldIndex]);
	if($LookupType == LT_QUERY)
		$data[$displayFieldIndex] = $cipherer->DecryptField($displayFieldName, $data[$displayFieldIndex]);
	
	$response[] = $data[$linkFieldIndex];
	$response[] = $data[$displayFieldIndex];
}

$respObj = array('success'=>true, 'data'=>array_slice($response, 0, 40));
echo "<textarea>".htmlspecialchars(my_json_encode($respObj))."</textarea>";
?>