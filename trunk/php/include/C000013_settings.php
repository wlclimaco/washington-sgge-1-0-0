<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000013 = array();
	$tdataC000013[".NumberOfChars"] = 80; 
	$tdataC000013[".ShortName"] = "C000013";
	$tdataC000013[".OwnerID"] = "";
	$tdataC000013[".OriginalTable"] = "C000013";

//	field labels
$fieldLabelsC000013 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000013["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000013["Portuguese(Brazil)"] = array();
	$fieldLabelsC000013["Portuguese(Brazil)"]["NUMERO"] = "Número";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["NUMERO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["BANCO"] = "Bando";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["BANCO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["CARTAO_CREDITO"] = "Cartão de Crédito";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["CARTAO_CREDITO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["FINANCEIRA"] = "Financeira";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["FINANCEIRA"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["RESSARCIMENTO"] = "Ressarcimento";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["RESSARCIMENTO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["PRAZO"] = "Prazo";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["PRAZO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["COMISSAO_CREDITO"] = "Comissão Crédito";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["COMISSAO_CREDITO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["COMISSAO_DEBITO"] = "Comissão Débito";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["COMISSAO_DEBITO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["REC_DEBITO"] = "Rec. Débito";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["REC_DEBITO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["REC_CREDITO"] = "Rec. Crédito";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["REC_CREDITO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["CONTA_PADRAO"] = "Conta";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["CONTA_PADRAO"] = "";
	$fieldLabelsC000013["Portuguese(Brazil)"]["TITULAR_CONTA_PADRAO"] = "Titular";
	$fieldToolTipsC000013["Portuguese(Brazil)"]["TITULAR_CONTA_PADRAO"] = "";
	if (count($fieldToolTipsC000013["Portuguese(Brazil)"]))
		$tdataC000013[".isUseToolTips"] = true;
}
	
	
	$tdataC000013[".NCSearch"] = true;



$tdataC000013[".shortTableName"] = "C000013";
$tdataC000013[".nSecOptions"] = 0;
$tdataC000013[".recsPerRowList"] = 1;
$tdataC000013[".mainTableOwnerID"] = "";
$tdataC000013[".moveNext"] = 1;
$tdataC000013[".nType"] = 0;

$tdataC000013[".strOriginalTableName"] = "C000013";




$tdataC000013[".showAddInPopup"] = false;

$tdataC000013[".showEditInPopup"] = false;

$tdataC000013[".showViewInPopup"] = false;

$tdataC000013[".fieldsForRegister"] = array();

$tdataC000013[".listAjax"] = false;

	$tdataC000013[".audit"] = false;

	$tdataC000013[".locking"] = false;

$tdataC000013[".listIcons"] = true;
$tdataC000013[".inlineEdit"] = true;
$tdataC000013[".inlineAdd"] = true;
$tdataC000013[".view"] = true;

$tdataC000013[".exportTo"] = true;

$tdataC000013[".printFriendly"] = true;


$tdataC000013[".showSimpleSearchOptions"] = false;

$tdataC000013[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000013[".isUseAjaxSuggest"] = false;
else 
	$tdataC000013[".isUseAjaxSuggest"] = true;

$tdataC000013[".rowHighlite"] = true;

// button handlers file names

$tdataC000013[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000013[".isUseTimeForSearch"] = false;




$tdataC000013[".allSearchFields"] = array();

$tdataC000013[".allSearchFields"][] = "NUMERO";
$tdataC000013[".allSearchFields"][] = "BANCO";

$tdataC000013[".googleLikeFields"][] = "NUMERO";
$tdataC000013[".googleLikeFields"][] = "BANCO";
$tdataC000013[".googleLikeFields"][] = "CARTAO_CREDITO";
$tdataC000013[".googleLikeFields"][] = "FINANCEIRA";
$tdataC000013[".googleLikeFields"][] = "RESSARCIMENTO";
$tdataC000013[".googleLikeFields"][] = "PRAZO";
$tdataC000013[".googleLikeFields"][] = "COMISSAO_CREDITO";
$tdataC000013[".googleLikeFields"][] = "COMISSAO_DEBITO";
$tdataC000013[".googleLikeFields"][] = "REC_DEBITO";
$tdataC000013[".googleLikeFields"][] = "REC_CREDITO";
$tdataC000013[".googleLikeFields"][] = "CONTA_PADRAO";
$tdataC000013[".googleLikeFields"][] = "TITULAR_CONTA_PADRAO";


$tdataC000013[".advSearchFields"][] = "NUMERO";
$tdataC000013[".advSearchFields"][] = "BANCO";

$tdataC000013[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000013[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000013[".strOrderBy"] = $tstrOrderBy;

$tdataC000013[".orderindexes"] = array();

$tdataC000013[".sqlHead"] = "SELECT NUMERO,  BANCO,  CARTAO_CREDITO,  FINANCEIRA,  RESSARCIMENTO,  PRAZO,  COMISSAO_CREDITO,  COMISSAO_DEBITO,  REC_DEBITO,  REC_CREDITO,  CONTA_PADRAO,  TITULAR_CONTA_PADRAO";
$tdataC000013[".sqlFrom"] = "FROM C000013";
$tdataC000013[".sqlWhereExpr"] = "";
$tdataC000013[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000013[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000013[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000013 = array();
$tableKeysC000013[] = "NUMERO";
$tdataC000013[".Keys"] = $tableKeysC000013;

$tdataC000013[".listFields"] = array();
$tdataC000013[".listFields"][] = "NUMERO";
$tdataC000013[".listFields"][] = "TITULAR_CONTA_PADRAO";
$tdataC000013[".listFields"][] = "BANCO";
$tdataC000013[".listFields"][] = "CARTAO_CREDITO";
$tdataC000013[".listFields"][] = "FINANCEIRA";

$tdataC000013[".viewFields"] = array();
$tdataC000013[".viewFields"][] = "NUMERO";
$tdataC000013[".viewFields"][] = "TITULAR_CONTA_PADRAO";
$tdataC000013[".viewFields"][] = "BANCO";
$tdataC000013[".viewFields"][] = "CARTAO_CREDITO";
$tdataC000013[".viewFields"][] = "FINANCEIRA";
$tdataC000013[".viewFields"][] = "RESSARCIMENTO";
$tdataC000013[".viewFields"][] = "PRAZO";
$tdataC000013[".viewFields"][] = "COMISSAO_CREDITO";
$tdataC000013[".viewFields"][] = "COMISSAO_DEBITO";
$tdataC000013[".viewFields"][] = "REC_DEBITO";
$tdataC000013[".viewFields"][] = "REC_CREDITO";
$tdataC000013[".viewFields"][] = "CONTA_PADRAO";

$tdataC000013[".addFields"] = array();

$tdataC000013[".inlineAddFields"] = array();
$tdataC000013[".inlineAddFields"][] = "NUMERO";
$tdataC000013[".inlineAddFields"][] = "BANCO";
$tdataC000013[".inlineAddFields"][] = "CARTAO_CREDITO";
$tdataC000013[".inlineAddFields"][] = "FINANCEIRA";

$tdataC000013[".editFields"] = array();

$tdataC000013[".inlineEditFields"] = array();
$tdataC000013[".inlineEditFields"][] = "NUMERO";
$tdataC000013[".inlineEditFields"][] = "BANCO";
$tdataC000013[".inlineEditFields"][] = "CARTAO_CREDITO";
$tdataC000013[".inlineEditFields"][] = "FINANCEIRA";

$tdataC000013[".exportFields"] = array();
$tdataC000013[".exportFields"][] = "NUMERO";
$tdataC000013[".exportFields"][] = "BANCO";
$tdataC000013[".exportFields"][] = "CARTAO_CREDITO";
$tdataC000013[".exportFields"][] = "FINANCEIRA";
$tdataC000013[".exportFields"][] = "RESSARCIMENTO";
$tdataC000013[".exportFields"][] = "PRAZO";
$tdataC000013[".exportFields"][] = "COMISSAO_CREDITO";
$tdataC000013[".exportFields"][] = "COMISSAO_DEBITO";
$tdataC000013[".exportFields"][] = "REC_DEBITO";
$tdataC000013[".exportFields"][] = "REC_CREDITO";
$tdataC000013[".exportFields"][] = "CONTA_PADRAO";
$tdataC000013[".exportFields"][] = "TITULAR_CONTA_PADRAO";

$tdataC000013[".printFields"] = array();
$tdataC000013[".printFields"][] = "NUMERO";
$tdataC000013[".printFields"][] = "TITULAR_CONTA_PADRAO";
$tdataC000013[".printFields"][] = "BANCO";
$tdataC000013[".printFields"][] = "CARTAO_CREDITO";
$tdataC000013[".printFields"][] = "FINANCEIRA";
$tdataC000013[".printFields"][] = "RESSARCIMENTO";
$tdataC000013[".printFields"][] = "PRAZO";
$tdataC000013[".printFields"][] = "COMISSAO_CREDITO";
$tdataC000013[".printFields"][] = "COMISSAO_DEBITO";
$tdataC000013[".printFields"][] = "REC_DEBITO";
$tdataC000013[".printFields"][] = "REC_CREDITO";
$tdataC000013[".printFields"][] = "CONTA_PADRAO";

//	NUMERO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "NUMERO";
	$fdata["GoodName"] = "NUMERO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Número"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "NUMERO"; 
		$fdata["FullName"] = "NUMERO";
	
		
		
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
	
		
		
	$tdataC000013["NUMERO"] = $fdata;
//	BANCO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "BANCO";
	$fdata["GoodName"] = "BANCO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Bando"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "BANCO"; 
		$fdata["FullName"] = "BANCO";
	
		
		
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
			$edata["EditParams"].= " maxlength=50";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000013["BANCO"] = $fdata;
//	CARTAO_CREDITO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "CARTAO_CREDITO";
	$fdata["GoodName"] = "CARTAO_CREDITO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Cartão de Crédito"; 
	$fdata["FieldType"] = 3;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CARTAO_CREDITO"; 
		$fdata["FullName"] = "CARTAO_CREDITO";
	
		
		
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
	
		
		
	$tdataC000013["CARTAO_CREDITO"] = $fdata;
//	FINANCEIRA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "FINANCEIRA";
	$fdata["GoodName"] = "FINANCEIRA";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Financeira"; 
	$fdata["FieldType"] = 3;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "FINANCEIRA"; 
		$fdata["FullName"] = "FINANCEIRA";
	
		
		
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
	
		
		
	$tdataC000013["FINANCEIRA"] = $fdata;
//	RESSARCIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "RESSARCIMENTO";
	$fdata["GoodName"] = "RESSARCIMENTO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Ressarcimento"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "RESSARCIMENTO"; 
		$fdata["FullName"] = "RESSARCIMENTO";
	
		
		
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
	
		
		
	$tdataC000013["RESSARCIMENTO"] = $fdata;
//	PRAZO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "PRAZO";
	$fdata["GoodName"] = "PRAZO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Prazo"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "PRAZO"; 
		$fdata["FullName"] = "PRAZO";
	
		
		
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
	
		
		
	$tdataC000013["PRAZO"] = $fdata;
//	COMISSAO_CREDITO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "COMISSAO_CREDITO";
	$fdata["GoodName"] = "COMISSAO_CREDITO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Comissão Crédito"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COMISSAO_CREDITO"; 
		$fdata["FullName"] = "COMISSAO_CREDITO";
	
		
		
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
	
		
		
	$tdataC000013["COMISSAO_CREDITO"] = $fdata;
//	COMISSAO_DEBITO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "COMISSAO_DEBITO";
	$fdata["GoodName"] = "COMISSAO_DEBITO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Comissão Débito"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COMISSAO_DEBITO"; 
		$fdata["FullName"] = "COMISSAO_DEBITO";
	
		
		
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
	
		
		
	$tdataC000013["COMISSAO_DEBITO"] = $fdata;
//	REC_DEBITO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "REC_DEBITO";
	$fdata["GoodName"] = "REC_DEBITO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Rec. Débito"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "REC_DEBITO"; 
		$fdata["FullName"] = "REC_DEBITO";
	
		
		
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
	
		
		
	$tdataC000013["REC_DEBITO"] = $fdata;
//	REC_CREDITO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "REC_CREDITO";
	$fdata["GoodName"] = "REC_CREDITO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Rec. Crédito"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "REC_CREDITO"; 
		$fdata["FullName"] = "REC_CREDITO";
	
		
		
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
	
		
		
	$tdataC000013["REC_CREDITO"] = $fdata;
//	CONTA_PADRAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "CONTA_PADRAO";
	$fdata["GoodName"] = "CONTA_PADRAO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Conta"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CONTA_PADRAO"; 
		$fdata["FullName"] = "CONTA_PADRAO";
	
		
		
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
			$edata["EditParams"].= " maxlength=15";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000013["CONTA_PADRAO"] = $fdata;
//	TITULAR_CONTA_PADRAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "TITULAR_CONTA_PADRAO";
	$fdata["GoodName"] = "TITULAR_CONTA_PADRAO";
	$fdata["ownerTable"] = "C000013";
	$fdata["Label"] = "Titular"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TITULAR_CONTA_PADRAO"; 
		$fdata["FullName"] = "TITULAR_CONTA_PADRAO";
	
		
		
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
			$edata["EditParams"].= " maxlength=100";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000013["TITULAR_CONTA_PADRAO"] = $fdata;

	
$tables_data["C000013"]=&$tdataC000013;
$field_labels["C000013"] = &$fieldLabelsC000013;
$fieldToolTips["C000013"] = &$fieldToolTipsC000013;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000013"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000013"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000013()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "NUMERO,  BANCO,  CARTAO_CREDITO,  FINANCEIRA,  RESSARCIMENTO,  PRAZO,  COMISSAO_CREDITO,  COMISSAO_DEBITO,  REC_DEBITO,  REC_CREDITO,  CONTA_PADRAO,  TITULAR_CONTA_PADRAO";
$proto0["m_strFrom"] = "FROM C000013";
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
	"m_strName" => "NUMERO",
	"m_strTable" => "C000013"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "BANCO",
	"m_strTable" => "C000013"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "CARTAO_CREDITO",
	"m_strTable" => "C000013"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "FINANCEIRA",
	"m_strTable" => "C000013"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "RESSARCIMENTO",
	"m_strTable" => "C000013"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "PRAZO",
	"m_strTable" => "C000013"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "COMISSAO_CREDITO",
	"m_strTable" => "C000013"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "COMISSAO_DEBITO",
	"m_strTable" => "C000013"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "REC_DEBITO",
	"m_strTable" => "C000013"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "REC_CREDITO",
	"m_strTable" => "C000013"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "CONTA_PADRAO",
	"m_strTable" => "C000013"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "TITULAR_CONTA_PADRAO",
	"m_strTable" => "C000013"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto29=array();
$proto29["m_link"] = "SQLL_MAIN";
			$proto30=array();
$proto30["m_strName"] = "C000013";
$proto30["m_columns"] = array();
$proto30["m_columns"][] = "NUMERO";
$proto30["m_columns"][] = "BANCO";
$proto30["m_columns"][] = "LOGO";
$proto30["m_columns"][] = "CARTAO_CREDITO";
$proto30["m_columns"][] = "FINANCEIRA";
$proto30["m_columns"][] = "RESSARCIMENTO";
$proto30["m_columns"][] = "PRAZO";
$proto30["m_columns"][] = "COMISSAO_CREDITO";
$proto30["m_columns"][] = "COMISSAO_DEBITO";
$proto30["m_columns"][] = "REC_DEBITO";
$proto30["m_columns"][] = "REC_CREDITO";
$proto30["m_columns"][] = "CONTA_PADRAO";
$proto30["m_columns"][] = "TITULAR_CONTA_PADRAO";
$obj = new SQLTable($proto30);

$proto29["m_table"] = $obj;
$proto29["m_alias"] = "";
$proto31=array();
$proto31["m_sql"] = "";
$proto31["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto31["m_column"]=$obj;
$proto31["m_contained"] = array();
$proto31["m_strCase"] = "";
$proto31["m_havingmode"] = "0";
$proto31["m_inBrackets"] = "0";
$proto31["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto31);

$proto29["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto29);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000013 = createSqlQuery_C000013();
												$tdataC000013[".sqlquery"] = $queryData_C000013;
	
if(isset($tdataC000013["field2"])){
	$tdataC000013["field2"]["LookupTable"] = "carscars_view";
	$tdataC000013["field2"]["LookupOrderBy"] = "name";
	$tdataC000013["field2"]["LookupType"] = 4;
	$tdataC000013["field2"]["LinkField"] = "email";
	$tdataC000013["field2"]["DisplayField"] = "name";
	$tdataC000013[".hasCustomViewField"] = true;
}

$tableEvents["C000013"] = new eventsBase;
$tdataC000013[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000013");

?>