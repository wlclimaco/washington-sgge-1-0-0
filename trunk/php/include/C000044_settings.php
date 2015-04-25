<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000044 = array();
	$tdataC000044[".NumberOfChars"] = 80; 
	$tdataC000044[".ShortName"] = "C000044";
	$tdataC000044[".OwnerID"] = "";
	$tdataC000044[".OriginalTable"] = "C000044";

//	field labels
$fieldLabelsC000044 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000044["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000044["Portuguese(Brazil)"] = array();
	$fieldLabelsC000044["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["CODCAIXA"] = "Código do Caixa";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["CODCAIXA"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["CODOPERADOR"] = "Código do Operador";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["CODOPERADOR"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["DATA"] = "Data";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["DATA"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["SAIDA"] = "Saída";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["SAIDA"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["ENTRADA"] = "Entrada";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["ENTRADA"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["CODCONTA"] = "Código da Conta";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["CODCONTA"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["HISTORICO"] = "Histórico";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["HISTORICO"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["MOVIMENTO"] = "Movimento";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["MOVIMENTO"] = "";
	$fieldLabelsC000044["Portuguese(Brazil)"]["VALOR"] = "Valor";
	$fieldToolTipsC000044["Portuguese(Brazil)"]["VALOR"] = "";
	if (count($fieldToolTipsC000044["Portuguese(Brazil)"]))
		$tdataC000044[".isUseToolTips"] = true;
}
	
	
	$tdataC000044[".NCSearch"] = true;



$tdataC000044[".shortTableName"] = "C000044";
$tdataC000044[".nSecOptions"] = 0;
$tdataC000044[".recsPerRowList"] = 1;
$tdataC000044[".mainTableOwnerID"] = "";
$tdataC000044[".moveNext"] = 1;
$tdataC000044[".nType"] = 0;

$tdataC000044[".strOriginalTableName"] = "C000044";




$tdataC000044[".showAddInPopup"] = false;

$tdataC000044[".showEditInPopup"] = false;

$tdataC000044[".showViewInPopup"] = false;

$tdataC000044[".fieldsForRegister"] = array();

$tdataC000044[".listAjax"] = false;

	$tdataC000044[".audit"] = false;

	$tdataC000044[".locking"] = false;

$tdataC000044[".listIcons"] = true;

$tdataC000044[".exportTo"] = true;

$tdataC000044[".printFriendly"] = true;


$tdataC000044[".showSimpleSearchOptions"] = false;

$tdataC000044[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000044[".isUseAjaxSuggest"] = false;
else 
	$tdataC000044[".isUseAjaxSuggest"] = true;

$tdataC000044[".rowHighlite"] = true;

// button handlers file names

$tdataC000044[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000044[".isUseTimeForSearch"] = false;




$tdataC000044[".allSearchFields"] = array();

$tdataC000044[".allSearchFields"][] = "CODIGO";
$tdataC000044[".allSearchFields"][] = "CODCAIXA";
$tdataC000044[".allSearchFields"][] = "CODOPERADOR";

$tdataC000044[".googleLikeFields"][] = "CODIGO";
$tdataC000044[".googleLikeFields"][] = "CODCAIXA";
$tdataC000044[".googleLikeFields"][] = "CODOPERADOR";
$tdataC000044[".googleLikeFields"][] = "DATA";
$tdataC000044[".googleLikeFields"][] = "SAIDA";
$tdataC000044[".googleLikeFields"][] = "ENTRADA";
$tdataC000044[".googleLikeFields"][] = "CODCONTA";
$tdataC000044[".googleLikeFields"][] = "HISTORICO";
$tdataC000044[".googleLikeFields"][] = "MOVIMENTO";
$tdataC000044[".googleLikeFields"][] = "VALOR";


$tdataC000044[".advSearchFields"][] = "CODIGO";
$tdataC000044[".advSearchFields"][] = "CODCAIXA";
$tdataC000044[".advSearchFields"][] = "CODOPERADOR";

$tdataC000044[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000044[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000044[".strOrderBy"] = $tstrOrderBy;

$tdataC000044[".orderindexes"] = array();

$tdataC000044[".sqlHead"] = "SELECT CODIGO,  CODCAIXA,  CODOPERADOR,  DATA,  SAIDA,  ENTRADA,  CODCONTA,  HISTORICO,  MOVIMENTO,  VALOR";
$tdataC000044[".sqlFrom"] = "FROM C000044";
$tdataC000044[".sqlWhereExpr"] = "";
$tdataC000044[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000044[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000044[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000044 = array();
$tdataC000044[".Keys"] = $tableKeysC000044;

$tdataC000044[".listFields"] = array();
$tdataC000044[".listFields"][] = "CODIGO";
$tdataC000044[".listFields"][] = "CODCAIXA";
$tdataC000044[".listFields"][] = "CODOPERADOR";
$tdataC000044[".listFields"][] = "DATA";
$tdataC000044[".listFields"][] = "SAIDA";
$tdataC000044[".listFields"][] = "ENTRADA";
$tdataC000044[".listFields"][] = "VALOR";

$tdataC000044[".viewFields"] = array();

$tdataC000044[".addFields"] = array();

$tdataC000044[".inlineAddFields"] = array();

$tdataC000044[".editFields"] = array();

$tdataC000044[".inlineEditFields"] = array();

$tdataC000044[".exportFields"] = array();
$tdataC000044[".exportFields"][] = "CODIGO";
$tdataC000044[".exportFields"][] = "CODCAIXA";
$tdataC000044[".exportFields"][] = "CODOPERADOR";
$tdataC000044[".exportFields"][] = "DATA";
$tdataC000044[".exportFields"][] = "SAIDA";
$tdataC000044[".exportFields"][] = "ENTRADA";
$tdataC000044[".exportFields"][] = "CODCONTA";
$tdataC000044[".exportFields"][] = "MOVIMENTO";
$tdataC000044[".exportFields"][] = "VALOR";
$tdataC000044[".exportFields"][] = "HISTORICO";

$tdataC000044[".printFields"] = array();
$tdataC000044[".printFields"][] = "CODIGO";
$tdataC000044[".printFields"][] = "CODCAIXA";
$tdataC000044[".printFields"][] = "CODOPERADOR";
$tdataC000044[".printFields"][] = "DATA";
$tdataC000044[".printFields"][] = "SAIDA";
$tdataC000044[".printFields"][] = "ENTRADA";
$tdataC000044[".printFields"][] = "CODCONTA";
$tdataC000044[".printFields"][] = "HISTORICO";
$tdataC000044[".printFields"][] = "VALOR";
$tdataC000044[".printFields"][] = "MOVIMENTO";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Código"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODIGO"; 
		$fdata["FullName"] = "CODIGO";
	
		
		
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
			$edata["EditParams"].= " maxlength=10";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000044["CODIGO"] = $fdata;
//	CODCAIXA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "CODCAIXA";
	$fdata["GoodName"] = "CODCAIXA";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Código do Caixa"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODCAIXA"; 
		$fdata["FullName"] = "CODCAIXA";
	
		
		
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
			$edata["EditParams"].= " maxlength=6";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000044["CODCAIXA"] = $fdata;
//	CODOPERADOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "CODOPERADOR";
	$fdata["GoodName"] = "CODOPERADOR";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Código do Operador"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODOPERADOR"; 
		$fdata["FullName"] = "CODOPERADOR";
	
		
		
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
			$edata["EditParams"].= " maxlength=6";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000044["CODOPERADOR"] = $fdata;
//	DATA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "DATA";
	$fdata["GoodName"] = "DATA";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Data"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA"; 
		$fdata["FullName"] = "DATA";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "Short Date");
	
		
		
		
			
		
		
		
		
		
		
		$vdata["NeedEncode"] = true;
	
	$fdata["ViewFormats"]["view"] = $vdata;
//  End View Formats

//	Begin Edit Formats 	
	$fdata["EditFormats"] = array();
	
	$edata = array("EditFormat" => "Date");
	
		
		
	
//	Begin Lookup settings
	//	End Lookup Settings

		
		
		
		
			$edata["acceptFileTypes"] = ".+$";
	
		$edata["maxNumberOfFiles"] = 1;
	
		
		
		$edata["DateEditType"] = 13; 
	$edata["InitialYearFactor"] = 100; 
	$edata["LastYearFactor"] = 10; 
	
		
		
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000044["DATA"] = $fdata;
//	SAIDA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "SAIDA";
	$fdata["GoodName"] = "SAIDA";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Saída"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "SAIDA"; 
		$fdata["FullName"] = "SAIDA";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "Number");
	
		
		
		
			
		
		$vdata["DecimalDigits"] = 2;
	
		
		
		
		
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
	
		
		
	$tdataC000044["SAIDA"] = $fdata;
//	ENTRADA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "ENTRADA";
	$fdata["GoodName"] = "ENTRADA";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Entrada"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ENTRADA"; 
		$fdata["FullName"] = "ENTRADA";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "Number");
	
		
		
		
			
		
		$vdata["DecimalDigits"] = 2;
	
		
		
		
		
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
	
		
		
	$tdataC000044["ENTRADA"] = $fdata;
//	CODCONTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "CODCONTA";
	$fdata["GoodName"] = "CODCONTA";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Código da Conta"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODCONTA"; 
		$fdata["FullName"] = "CODCONTA";
	
		
		
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
			$edata["EditParams"].= " maxlength=6";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000044["CODCONTA"] = $fdata;
//	HISTORICO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "HISTORICO";
	$fdata["GoodName"] = "HISTORICO";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Histórico"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "HISTORICO"; 
		$fdata["FullName"] = "HISTORICO";
	
		
		
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
			$edata["EditParams"].= " maxlength=60";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000044["HISTORICO"] = $fdata;
//	MOVIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "MOVIMENTO";
	$fdata["GoodName"] = "MOVIMENTO";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Movimento"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "MOVIMENTO"; 
		$fdata["FullName"] = "MOVIMENTO";
	
		
		
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
	
		
		
	$tdataC000044["MOVIMENTO"] = $fdata;
//	VALOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "VALOR";
	$fdata["GoodName"] = "VALOR";
	$fdata["ownerTable"] = "C000044";
	$fdata["Label"] = "Valor"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR"; 
		$fdata["FullName"] = "VALOR";
	
		
		
				$fdata["FieldPermissions"] = true;
	
				$fdata["UploadFolder"] = "files";
		
//  Begin View Formats
	$fdata["ViewFormats"] = array();
	
	$vdata = array("ViewFormat" => "Number");
	
		
		
		
			
		
		$vdata["DecimalDigits"] = 2;
	
		
		
		
		
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
	
		
		
	$tdataC000044["VALOR"] = $fdata;

	
$tables_data["C000044"]=&$tdataC000044;
$field_labels["C000044"] = &$fieldLabelsC000044;
$fieldToolTips["C000044"] = &$fieldToolTipsC000044;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000044"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000044"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000044()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  CODCAIXA,  CODOPERADOR,  DATA,  SAIDA,  ENTRADA,  CODCONTA,  HISTORICO,  MOVIMENTO,  VALOR";
$proto0["m_strFrom"] = "FROM C000044";
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
	"m_strName" => "CODIGO",
	"m_strTable" => "C000044"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCAIXA",
	"m_strTable" => "C000044"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "CODOPERADOR",
	"m_strTable" => "C000044"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA",
	"m_strTable" => "C000044"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "SAIDA",
	"m_strTable" => "C000044"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "ENTRADA",
	"m_strTable" => "C000044"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCONTA",
	"m_strTable" => "C000044"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "HISTORICO",
	"m_strTable" => "C000044"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "MOVIMENTO",
	"m_strTable" => "C000044"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR",
	"m_strTable" => "C000044"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto25=array();
$proto25["m_link"] = "SQLL_MAIN";
			$proto26=array();
$proto26["m_strName"] = "C000044";
$proto26["m_columns"] = array();
$proto26["m_columns"][] = "CODIGO";
$proto26["m_columns"][] = "CODCAIXA";
$proto26["m_columns"][] = "CODOPERADOR";
$proto26["m_columns"][] = "DATA";
$proto26["m_columns"][] = "SAIDA";
$proto26["m_columns"][] = "ENTRADA";
$proto26["m_columns"][] = "CODCONTA";
$proto26["m_columns"][] = "HISTORICO";
$proto26["m_columns"][] = "MOVIMENTO";
$proto26["m_columns"][] = "VALOR";
$proto26["m_columns"][] = "CODNFSAIDA";
$obj = new SQLTable($proto26);

$proto25["m_table"] = $obj;
$proto25["m_alias"] = "";
$proto27=array();
$proto27["m_sql"] = "";
$proto27["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto27["m_column"]=$obj;
$proto27["m_contained"] = array();
$proto27["m_strCase"] = "";
$proto27["m_havingmode"] = "0";
$proto27["m_inBrackets"] = "0";
$proto27["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto27);

$proto25["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto25);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000044 = createSqlQuery_C000044();
										$tdataC000044[".sqlquery"] = $queryData_C000044;
	
if(isset($tdataC000044["field2"])){
	$tdataC000044["field2"]["LookupTable"] = "carscars_view";
	$tdataC000044["field2"]["LookupOrderBy"] = "name";
	$tdataC000044["field2"]["LookupType"] = 4;
	$tdataC000044["field2"]["LinkField"] = "email";
	$tdataC000044["field2"]["DisplayField"] = "name";
	$tdataC000044[".hasCustomViewField"] = true;
}

$tableEvents["C000044"] = new eventsBase;
$tdataC000044[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000044");

?>