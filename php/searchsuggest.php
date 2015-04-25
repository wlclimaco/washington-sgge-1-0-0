<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
add_nocache_headers();

include('classes/searchclause.php');

$table = postvalue("table");
$strTableName = GetTableByShort($table);

if (!checkTableName($table))
	exit(0);

include("include/".$table."_variables.php");

if(!isLogged())
	return;
if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
	return;

// if nothing to search 
if (postvalue('searchFor') == ''){
	echo "<textarea>".htmlspecialchars(my_json_encode(array('success' => true, 'result' => '')))."</textarea>"; 
	return;
}
$sessionPrefix = $strTableName;

$cipherer = new RunnerCipherer($strTableName);
$pSet = new ProjectSettings($strTableName, PAGE_SEARCH);

// array of fields which were added in wizard for search
$allSearchFields = $pSet->getAllSearchFields();



// SearchClause class stuff
if (isset($_SESSION[$sessionPrefix.'_advsearch']))
	$searchClauseObj = unserialize($_SESSION[$sessionPrefix.'_advsearch']);
else{
	$params = array();
	$params['tName'] = $strTableName;
	$params['cipherer'] = $cipherer;
	$params['searchFieldsArr'] = $allSearchFields;
	$params['sessionPrefix'] = $sessionPrefix;
	$params['panelSearchFields'] = $pSet->getPanelSearchFields();
	$params['googleLikeFields'] = $pSet->getGoogleLikeFields();
	$searchClauseObj = new SearchClause($params);
}
// array of vals
$response = array();

if(postvalue("start"))
	$suggestAllContent=false;

$searchFor = postvalue('searchFor');
$searchField = GoodFieldName(postvalue('searchField'));
$strSecuritySql = SecuritySQL("Search", $strTableName);
$detailKeys = array();
$masterWhere = "";

if ($searchField == ""){
	$allSearchFields = $pSet->getGoogleLikeFields();
}	
require_once getabspath('classes/controls/EditControlsContainer.php');
if(@$_SESSION[$sessionPrefix."_mastertable"] != "")
{
	$masterTablesInfoArr = $pSet->getMasterTablesArr($strTableName);
	$masterKeys = array();
	for($i = 0; $i < count($masterTablesInfoArr); $i ++) 
	{
		if($_SESSION[$sessionPrefix."_mastertable"] == $masterTablesInfoArr[$i]['mDataSourceTable']) 
		{
			if($masterTablesInfoArr[$i]['dispInfo']) 
			{
				$masterPSet = new ProjectSettings($masterTablesInfoArr[$i]['mDataSourceTable'], PAGE_SEARCH);
				$masterControls = new EditControlsContainer(null, $masterPSet, PAGE_LIST, 
					new RunnerCipherer($masterTablesInfoArr[$i]['mDataSourceTable'], $masterPSet));
				$detailKeys = $masterTablesInfoArr[$i]['detailKeys'];
				for($j = 0; $j < count($detailKeys); $j ++){
					$masterWhere .= " and ".$masterControls->getControl($detailKeys[$j])->SQLWhere(@$_SESSION[$sessionPrefix."_masterkey".($j + 1)]
						, 'Equals', "", "", true);
				}
			}
			break;
		}
	}
}

$controls = new EditControlsContainer(null, $pSet, PAGE_LIST, $cipherer);
// proccess fields and create sql
foreach($allSearchFields as $f)
{
	$fType =  $pSet->getFieldType($f);
	// filter fields by type
	if (!IsCharType($fType) && !IsNumberType($fType) && !IsGuid($fType)	|| in_array($f, $detailKeys))
	{
		continue;
	}
	else
	{
			}
	// get suggest for field
	if(($searchField == '' || $searchField == GoodFieldName($f)) && $pSet->checkFieldPermissions($f))
	{
		$where = "";
		$having = "";
		if (!$gQuery->IsAggrFuncField($pSet->getFieldIndex($f)-1))
		{
			$where = $searchClauseObj->getSuggestWhere($controls->getControl($f), $suggestAllContent, $searchFor);
		}
		elseif ($gQuery->IsAggrFuncField($pSet->getFieldIndex($f)-1))
		{
			$having = $searchClauseObj->getSuggestWhere($controls->getControl($f), $suggestAllContent, $searchFor);
		}
		if(!strlen($where) && !strlen($having))
			continue;
		// prepare common vals
		$where = whereAdd($where.$masterWhere, $strSecuritySql);
		$distinct = "DISTINCT";
				$sqlHead = "SELECT ".$distinct." ".GetFullFieldName($f)." ";
		if($gQuery->HasGroupBy())
		{
			$strSQL = $gQuery->gSQLWhere_having_fromQuery("", $where, $having);
			$strSQL = "SELECT DISTINCT st.".AddFieldWrappers($f)." from (".$strSQL.") st";
		}else{
			$strSQL = SQLQuery::gSQLWhere_having($sqlHead, $gQuery->FromToSql(), $gQuery->WhereToSql(), $gQuery->GroupByToSql()
				, $gQuery->Having()->toSql($gQuery), $where, $having);
		}
		
			$strSQL = SQLQuery::gSQLWhere_having($sqlHead, $gQuery->FromToSql(), $gQuery->WhereToSql(), $gQuery->GroupByToSql()
			, $gQuery->Having()->toSql($gQuery), $where, $having);
		$rs=db_query($strSQL,$conn);
		$i=0;
		
		while (($row = db_fetch_numarray($rs)) && count($response) < 10) 
		{
			$val = $cipherer->DecryptField($f, $row[0]);
			if(IsGuid($fType))
				$val = substr($val,1,-1);
			$controls->getControl($f)->suggestValue($val, $searchFor, $response, $row);
		}
	}
}

db_close($conn);
ksort($response, SORT_STRING);
$suggestValues = array();
foreach ($response as $value => $realValue)
	$suggestValues[] = array("value" => $value, "realValue" => $realValue);
// all queries worked without errors, add success marker
$returnJSON['success'] = true;
$result = array();
for( $i=0;$i<10 && $i<count($suggestValues);$i++) 
{
	$value = htmlspecialchars($suggestValues[$i]['value']);
	$str = $value;
	$pos = my_stripos($value,$searchFor,0);
	if($pos===false)
		$result[] = array("value" => $value, "realValue" => $suggestValues[$i]['realValue']);
	else
		$result[] = array("value" => (substr($value,0,$pos))."<b>".(substr($value,$pos,strlen($searchFor)))
			."</b>".(substr($value,$pos+strlen($searchFor)))
			, "realValue" => $suggestValues[$i]['realValue']);
}
$returnJSON['result'] = $result;

echo "<textarea>".htmlspecialchars(my_json_encode($returnJSON))."</textarea>";
?>