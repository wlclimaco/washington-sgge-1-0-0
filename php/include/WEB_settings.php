<?php
require_once(getabspath("classes/cipherer.php"));
$tdataWEB = array();
	$tdataWEB[".NumberOfChars"] = 80; 
	$tdataWEB[".ShortName"] = "WEB";
	$tdataWEB[".OwnerID"] = "";
	$tdataWEB[".OriginalTable"] = "WEB";

//	field labels
$fieldLabelsWEB = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsWEB["Portuguese(Brazil)"] = array();
	$fieldToolTipsWEB["Portuguese(Brazil)"] = array();
	$fieldLabelsWEB["Portuguese(Brazil)"]["ID"] = "ID";
	$fieldToolTipsWEB["Portuguese(Brazil)"]["ID"] = "";
	$fieldLabelsWEB["Portuguese(Brazil)"]["NOME"] = "NOME";
	$fieldToolTipsWEB["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsWEB["Portuguese(Brazil)"]["LOGIN"] = "LOGIN";
	$fieldToolTipsWEB["Portuguese(Brazil)"]["LOGIN"] = "";
	$fieldLabelsWEB["Portuguese(Brazil)"]["SENHA"] = "SENHA";
	$fieldToolTipsWEB["Portuguese(Brazil)"]["SENHA"] = "";
	$fieldLabelsWEB["Portuguese(Brazil)"]["EMAIL"] = "EMAIL";
	$fieldToolTipsWEB["Portuguese(Brazil)"]["EMAIL"] = "";
	$fieldLabelsWEB["Portuguese(Brazil)"]["OBSERVACAO"] = "OBSERVACAO";
	$fieldToolTipsWEB["Portuguese(Brazil)"]["OBSERVACAO"] = "";
	if (count($fieldToolTipsWEB["Portuguese(Brazil)"]))
		$tdataWEB[".isUseToolTips"] = true;
}
	
	
	$tdataWEB[".NCSearch"] = true;



$tdataWEB[".shortTableName"] = "WEB";
$tdataWEB[".nSecOptions"] = 0;
$tdataWEB[".recsPerRowList"] = 1;
$tdataWEB[".mainTableOwnerID"] = "";
$tdataWEB[".moveNext"] = 1;
$tdataWEB[".nType"] = 0;

$tdataWEB[".strOriginalTableName"] = "WEB";




$tdataWEB[".showAddInPopup"] = false;

$tdataWEB[".showEditInPopup"] = false;

$tdataWEB[".showViewInPopup"] = false;

$tdataWEB[".fieldsForRegister"] = array();

$tdataWEB[".listAjax"] = false;

	$tdataWEB[".audit"] = false;

	$tdataWEB[".locking"] = false;

$tdataWEB[".listIcons"] = true;




$tdataWEB[".showSimpleSearchOptions"] = false;

$tdataWEB[".showSearchPanel"] = true;

if (isMobile())
	$tdataWEB[".isUseAjaxSuggest"] = false;
else 
	$tdataWEB[".isUseAjaxSuggest"] = true;

$tdataWEB[".rowHighlite"] = true;

// button handlers file names

$tdataWEB[".addPageEvents"] = false;

// use timepicker for search panel
$tdataWEB[".isUseTimeForSearch"] = false;




$tdataWEB[".allSearchFields"] = array();

$tdataWEB[".allSearchFields"][] = "ID";
$tdataWEB[".allSearchFields"][] = "NOME";
$tdataWEB[".allSearchFields"][] = "LOGIN";

$tdataWEB[".googleLikeFields"][] = "ID";
$tdataWEB[".googleLikeFields"][] = "NOME";
$tdataWEB[".googleLikeFields"][] = "LOGIN";
$tdataWEB[".googleLikeFields"][] = "SENHA";
$tdataWEB[".googleLikeFields"][] = "EMAIL";
$tdataWEB[".googleLikeFields"][] = "OBSERVACAO";


$tdataWEB[".advSearchFields"][] = "ID";
$tdataWEB[".advSearchFields"][] = "NOME";
$tdataWEB[".advSearchFields"][] = "LOGIN";

$tdataWEB[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataWEB[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataWEB[".strOrderBy"] = $tstrOrderBy;

$tdataWEB[".orderindexes"] = array();

$tdataWEB[".sqlHead"] = "SELECT ID,  NOME,  LOGIN,  SENHA,  EMAIL,  OBSERVACAO";
$tdataWEB[".sqlFrom"] = "FROM WEB";
$tdataWEB[".sqlWhereExpr"] = "";
$tdataWEB[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataWEB[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataWEB[".arrGroupsPerPage"] = $arrGPP;

$tableKeysWEB = array();
$tdataWEB[".Keys"] = $tableKeysWEB;

$tdataWEB[".listFields"] = array();
$tdataWEB[".listFields"][] = "ID";
$tdataWEB[".listFields"][] = "NOME";
$tdataWEB[".listFields"][] = "LOGIN";
$tdataWEB[".listFields"][] = "EMAIL";

$tdataWEB[".viewFields"] = array();

$tdataWEB[".addFields"] = array();
$tdataWEB[".addFields"][] = "ID";
$tdataWEB[".addFields"][] = "NOME";
$tdataWEB[".addFields"][] = "LOGIN";
$tdataWEB[".addFields"][] = "SENHA";
$tdataWEB[".addFields"][] = "EMAIL";
$tdataWEB[".addFields"][] = "OBSERVACAO";

$tdataWEB[".inlineAddFields"] = array();

$tdataWEB[".editFields"] = array();

$tdataWEB[".inlineEditFields"] = array();

$tdataWEB[".exportFields"] = array();

$tdataWEB[".printFields"] = array();

//	ID
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "ID";
	$fdata["GoodName"] = "ID";
	$fdata["ownerTable"] = "WEB";
	$fdata["Label"] = "ID"; 
	$fdata["FieldType"] = 3;
	
		
		
		$fdata["bListPage"] = true; 
	
		$fdata["bAddPage"] = true; 
	
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		
		
		$fdata["strField"] = "ID"; 
		$fdata["FullName"] = "ID";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Text field");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		
		
		$edata["EditParams"] = "";
			
		
//	Begin validation
	$edata["validateAs"] = array();
				$edata["validateAs"]["basicValidate"][] = getJsValidatorName("Number");	
						
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataWEB["ID"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "WEB";
	$fdata["Label"] = "NOME"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		$fdata["bAddPage"] = true; 
	
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		
		
		$fdata["strField"] = "NOME"; 
		$fdata["FullName"] = "NOME";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Text field");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		
		
		$edata["EditParams"] = "";
			$edata["EditParams"].= " maxlength=80";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataWEB["NOME"] = $fdata;
//	LOGIN
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "LOGIN";
	$fdata["GoodName"] = "LOGIN";
	$fdata["ownerTable"] = "WEB";
	$fdata["Label"] = "LOGIN"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		$fdata["bAddPage"] = true; 
	
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		
		
		$fdata["strField"] = "LOGIN"; 
		$fdata["FullName"] = "LOGIN";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Text field");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		
		
		$edata["EditParams"] = "";
			$edata["EditParams"].= " maxlength=20";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataWEB["LOGIN"] = $fdata;
//	SENHA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "SENHA";
	$fdata["GoodName"] = "SENHA";
	$fdata["ownerTable"] = "WEB";
	$fdata["Label"] = "SENHA"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		$fdata["bAddPage"] = true; 
	
		
		
		
		
		
		
		
		$fdata["strField"] = "SENHA"; 
		$fdata["FullName"] = "SENHA";
	
		
		
				
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Password");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		
		
		$edata["EditParams"] = "";
			$edata["EditParams"].= " maxlength=20";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataWEB["SENHA"] = $fdata;
//	EMAIL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "EMAIL";
	$fdata["GoodName"] = "EMAIL";
	$fdata["ownerTable"] = "WEB";
	$fdata["Label"] = "EMAIL"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		$fdata["bAddPage"] = true; 
	
		
		
		
		
		
		
		
		$fdata["strField"] = "EMAIL"; 
		$fdata["FullName"] = "EMAIL";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Text field");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		
		
		$edata["EditParams"] = "";
			$edata["EditParams"].= " maxlength=80";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataWEB["EMAIL"] = $fdata;
//	OBSERVACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "OBSERVACAO";
	$fdata["GoodName"] = "OBSERVACAO";
	$fdata["ownerTable"] = "WEB";
	$fdata["Label"] = "OBSERVACAO"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		$fdata["bAddPage"] = true; 
	
		
		
		
		
		
		
		
		$fdata["strField"] = "OBSERVACAO"; 
		$fdata["FullName"] = "OBSERVACAO";
	
		
		
				
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Text area");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		
				$edata["nRows"] = 100;
			$edata["nCols"] = 200;
	
		
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataWEB["OBSERVACAO"] = $fdata;

	
$tables_data["WEB"]=&$tdataWEB;
$field_labels["WEB"] = &$fieldLabelsWEB;
$fieldToolTips["WEB"] = &$fieldToolTipsWEB;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["WEB"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["WEB"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_WEB()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "ID,  NOME,  LOGIN,  SENHA,  EMAIL,  OBSERVACAO";
$proto0["m_strFrom"] = "FROM WEB";
$proto0["m_strWhere"] = "";
$proto0["m_strOrderBy"] = "";
$proto0["m_strTail"] = "";
$proto0["cipherer"] = null;
$proto1=array();
$proto1["m_sql"] = "";
$proto1["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto1["m_column"]=$obj;
$proto1["m_contained"] = array();
$proto1["m_strCase"] = "";
$proto1["m_havingmode"] = "0";
$proto1["m_inBrackets"] = "0";
$proto1["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto1);

$proto0["m_where"] = $obj;
$proto3=array();
$proto3["m_sql"] = "";
$proto3["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto3["m_column"]=$obj;
$proto3["m_contained"] = array();
$proto3["m_strCase"] = "";
$proto3["m_havingmode"] = "0";
$proto3["m_inBrackets"] = "0";
$proto3["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto3);

$proto0["m_having"] = $obj;
$proto0["m_fieldlist"] = array();
						$proto5=array();
			$obj = new SQLField(array(
	"m_strName" => "ID",
	"m_strTable" => "WEB"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "WEB"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "LOGIN",
	"m_strTable" => "WEB"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "SENHA",
	"m_strTable" => "WEB"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "EMAIL",
	"m_strTable" => "WEB"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "OBSERVACAO",
	"m_strTable" => "WEB"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto17=array();
$proto17["m_link"] = "SQLL_MAIN";
			$proto18=array();
$proto18["m_strName"] = "WEB";
$proto18["m_columns"] = array();
$proto18["m_columns"][] = "ID";
$proto18["m_columns"][] = "NOME";
$proto18["m_columns"][] = "LOGIN";
$proto18["m_columns"][] = "SENHA";
$proto18["m_columns"][] = "EMAIL";
$proto18["m_columns"][] = "OBSERVACAO";
$obj = new SQLTable($proto18);

$proto17["m_table"] = $obj;
$proto17["m_alias"] = "";
$proto19=array();
$proto19["m_sql"] = "";
$proto19["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto19["m_column"]=$obj;
$proto19["m_contained"] = array();
$proto19["m_strCase"] = "";
$proto19["m_havingmode"] = "0";
$proto19["m_inBrackets"] = "0";
$proto19["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto19);

$proto17["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto17);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_WEB = createSqlQuery_WEB();
						$tdataWEB[".sqlquery"] = $queryData_WEB;
	
if(isset($tdataWEB["field2"])){
	$tdataWEB["field2"]["LookupTable"] = "carscars_view";
	$tdataWEB["field2"]["LookupOrderBy"] = "name";
	$tdataWEB["field2"]["LookupType"] = 4;
	$tdataWEB["field2"]["LinkField"] = "email";
	$tdataWEB["field2"]["DisplayField"] = "name";
	$tdataWEB[".hasCustomViewField"] = true;
}

$tableEvents["WEB"] = new eventsBase;
$tdataWEB[".hasEvents"] = false;

$cipherer = new RunnerCipherer("WEB");

?>