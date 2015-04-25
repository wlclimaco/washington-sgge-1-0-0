<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000046 = array();
	$tdataC000046[".NumberOfChars"] = 80; 
	$tdataC000046[".ShortName"] = "C000046";
	$tdataC000046[".OwnerID"] = "";
	$tdataC000046[".OriginalTable"] = "C000046";

//	field labels
$fieldLabelsC000046 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000046["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000046["Portuguese(Brazil)"] = array();
	$fieldLabelsC000046["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["DATA_EMISSAO"] = "Data de Emissão";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["DATA_EMISSAO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["DATA_VENCIMENTO"] = "Data de Vencimento";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["DATA_VENCIMENTO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["DATA_PAGAMENTO"] = "Data de Pagamento";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["DATA_PAGAMENTO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["CODCONTA"] = "Código da Conta";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["CODCONTA"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["CODFORNECEDOR"] = "Código do Fornecedor";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["CODFORNECEDOR"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["VALOR"] = "Valor";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["VALOR"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["VALORPAGO"] = "Valor Pago";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["VALORPAGO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["LIQUIDO"] = "Valor Liquido";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["LIQUIDO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["DESCONTO"] = "Desconto";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["DESCONTO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["ACRESCIMO"] = "Acréscimo";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["ACRESCIMO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["DOCUMENTO"] = "Documento";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["DOCUMENTO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["NOTAFISCAL"] = "Nota Fiscal";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["NOTAFISCAL"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["HISTORICO"] = "Histórico";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["HISTORICO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["ESPECIE"] = "Espécie";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["ESPECIE"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["SITUACAO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["CODNOTA"] = "Código Nota";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["CODNOTA"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["MOVIMENTO"] = "Movimento";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["MOVIMENTO"] = "";
	$fieldLabelsC000046["Portuguese(Brazil)"]["CODCAIXA"] = "Código Caixa";
	$fieldToolTipsC000046["Portuguese(Brazil)"]["CODCAIXA"] = "";
	if (count($fieldToolTipsC000046["Portuguese(Brazil)"]))
		$tdataC000046[".isUseToolTips"] = true;
}
	
	
	$tdataC000046[".NCSearch"] = true;



$tdataC000046[".shortTableName"] = "C000046";
$tdataC000046[".nSecOptions"] = 0;
$tdataC000046[".recsPerRowList"] = 1;
$tdataC000046[".mainTableOwnerID"] = "";
$tdataC000046[".moveNext"] = 1;
$tdataC000046[".nType"] = 0;

$tdataC000046[".strOriginalTableName"] = "C000046";




$tdataC000046[".showAddInPopup"] = false;

$tdataC000046[".showEditInPopup"] = false;

$tdataC000046[".showViewInPopup"] = false;

$tdataC000046[".fieldsForRegister"] = array();

$tdataC000046[".listAjax"] = false;

	$tdataC000046[".audit"] = false;

	$tdataC000046[".locking"] = false;

$tdataC000046[".listIcons"] = true;
$tdataC000046[".inlineEdit"] = true;
$tdataC000046[".inlineAdd"] = true;
$tdataC000046[".view"] = true;

$tdataC000046[".exportTo"] = true;

$tdataC000046[".printFriendly"] = true;


$tdataC000046[".showSimpleSearchOptions"] = false;

$tdataC000046[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000046[".isUseAjaxSuggest"] = false;
else 
	$tdataC000046[".isUseAjaxSuggest"] = true;

$tdataC000046[".rowHighlite"] = true;

// button handlers file names

$tdataC000046[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000046[".isUseTimeForSearch"] = false;




$tdataC000046[".allSearchFields"] = array();

$tdataC000046[".allSearchFields"][] = "CODIGO";
$tdataC000046[".allSearchFields"][] = "CODFORNECEDOR";
$tdataC000046[".allSearchFields"][] = "CODNOTA";

$tdataC000046[".googleLikeFields"][] = "CODIGO";
$tdataC000046[".googleLikeFields"][] = "DATA_EMISSAO";
$tdataC000046[".googleLikeFields"][] = "DATA_VENCIMENTO";
$tdataC000046[".googleLikeFields"][] = "DATA_PAGAMENTO";
$tdataC000046[".googleLikeFields"][] = "CODCONTA";
$tdataC000046[".googleLikeFields"][] = "CODFORNECEDOR";
$tdataC000046[".googleLikeFields"][] = "VALOR";
$tdataC000046[".googleLikeFields"][] = "VALORPAGO";
$tdataC000046[".googleLikeFields"][] = "LIQUIDO";
$tdataC000046[".googleLikeFields"][] = "DESCONTO";
$tdataC000046[".googleLikeFields"][] = "ACRESCIMO";
$tdataC000046[".googleLikeFields"][] = "DOCUMENTO";
$tdataC000046[".googleLikeFields"][] = "NOTAFISCAL";
$tdataC000046[".googleLikeFields"][] = "HISTORICO";
$tdataC000046[".googleLikeFields"][] = "ESPECIE";
$tdataC000046[".googleLikeFields"][] = "SITUACAO";
$tdataC000046[".googleLikeFields"][] = "CODNOTA";
$tdataC000046[".googleLikeFields"][] = "MOVIMENTO";
$tdataC000046[".googleLikeFields"][] = "CODCAIXA";


$tdataC000046[".advSearchFields"][] = "CODIGO";
$tdataC000046[".advSearchFields"][] = "CODFORNECEDOR";
$tdataC000046[".advSearchFields"][] = "CODNOTA";

$tdataC000046[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000046[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000046[".strOrderBy"] = $tstrOrderBy;

$tdataC000046[".orderindexes"] = array();

$tdataC000046[".sqlHead"] = "SELECT CODIGO,  DATA_EMISSAO,  DATA_VENCIMENTO,  DATA_PAGAMENTO,  CODCONTA,  CODFORNECEDOR,  VALOR,  VALORPAGO,  LIQUIDO,  DESCONTO,  ACRESCIMO,  DOCUMENTO,  NOTAFISCAL,  HISTORICO,  ESPECIE,  SITUACAO,  CODNOTA,  MOVIMENTO,  CODCAIXA";
$tdataC000046[".sqlFrom"] = "FROM C000046";
$tdataC000046[".sqlWhereExpr"] = "";
$tdataC000046[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000046[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000046[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000046 = array();
$tableKeysC000046[] = "CODIGO";
$tdataC000046[".Keys"] = $tableKeysC000046;

$tdataC000046[".listFields"] = array();
$tdataC000046[".listFields"][] = "CODIGO";
$tdataC000046[".listFields"][] = "CODFORNECEDOR";
$tdataC000046[".listFields"][] = "DATA_EMISSAO";
$tdataC000046[".listFields"][] = "DATA_VENCIMENTO";
$tdataC000046[".listFields"][] = "DATA_PAGAMENTO";
$tdataC000046[".listFields"][] = "VALOR";
$tdataC000046[".listFields"][] = "DOCUMENTO";
$tdataC000046[".listFields"][] = "NOTAFISCAL";
$tdataC000046[".listFields"][] = "CODNOTA";

$tdataC000046[".viewFields"] = array();
$tdataC000046[".viewFields"][] = "CODIGO";
$tdataC000046[".viewFields"][] = "CODFORNECEDOR";
$tdataC000046[".viewFields"][] = "DATA_EMISSAO";
$tdataC000046[".viewFields"][] = "DATA_VENCIMENTO";
$tdataC000046[".viewFields"][] = "DATA_PAGAMENTO";
$tdataC000046[".viewFields"][] = "CODCONTA";
$tdataC000046[".viewFields"][] = "VALOR";
$tdataC000046[".viewFields"][] = "VALORPAGO";
$tdataC000046[".viewFields"][] = "LIQUIDO";
$tdataC000046[".viewFields"][] = "DESCONTO";
$tdataC000046[".viewFields"][] = "ACRESCIMO";
$tdataC000046[".viewFields"][] = "DOCUMENTO";
$tdataC000046[".viewFields"][] = "NOTAFISCAL";
$tdataC000046[".viewFields"][] = "HISTORICO";
$tdataC000046[".viewFields"][] = "ESPECIE";
$tdataC000046[".viewFields"][] = "SITUACAO";
$tdataC000046[".viewFields"][] = "CODNOTA";
$tdataC000046[".viewFields"][] = "MOVIMENTO";
$tdataC000046[".viewFields"][] = "CODCAIXA";

$tdataC000046[".addFields"] = array();

$tdataC000046[".inlineAddFields"] = array();

$tdataC000046[".editFields"] = array();

$tdataC000046[".inlineEditFields"] = array();

$tdataC000046[".exportFields"] = array();
$tdataC000046[".exportFields"][] = "CODIGO";
$tdataC000046[".exportFields"][] = "CODFORNECEDOR";
$tdataC000046[".exportFields"][] = "DATA_EMISSAO";
$tdataC000046[".exportFields"][] = "DATA_VENCIMENTO";
$tdataC000046[".exportFields"][] = "DATA_PAGAMENTO";
$tdataC000046[".exportFields"][] = "CODCONTA";
$tdataC000046[".exportFields"][] = "VALOR";
$tdataC000046[".exportFields"][] = "VALORPAGO";
$tdataC000046[".exportFields"][] = "LIQUIDO";
$tdataC000046[".exportFields"][] = "DESCONTO";
$tdataC000046[".exportFields"][] = "ACRESCIMO";
$tdataC000046[".exportFields"][] = "DOCUMENTO";
$tdataC000046[".exportFields"][] = "NOTAFISCAL";
$tdataC000046[".exportFields"][] = "HISTORICO";
$tdataC000046[".exportFields"][] = "ESPECIE";
$tdataC000046[".exportFields"][] = "SITUACAO";
$tdataC000046[".exportFields"][] = "CODNOTA";
$tdataC000046[".exportFields"][] = "MOVIMENTO";
$tdataC000046[".exportFields"][] = "CODCAIXA";

$tdataC000046[".printFields"] = array();
$tdataC000046[".printFields"][] = "CODIGO";
$tdataC000046[".printFields"][] = "CODFORNECEDOR";
$tdataC000046[".printFields"][] = "DATA_EMISSAO";
$tdataC000046[".printFields"][] = "DATA_VENCIMENTO";
$tdataC000046[".printFields"][] = "DATA_PAGAMENTO";
$tdataC000046[".printFields"][] = "CODCONTA";
$tdataC000046[".printFields"][] = "VALOR";
$tdataC000046[".printFields"][] = "VALORPAGO";
$tdataC000046[".printFields"][] = "LIQUIDO";
$tdataC000046[".printFields"][] = "DESCONTO";
$tdataC000046[".printFields"][] = "ACRESCIMO";
$tdataC000046[".printFields"][] = "DOCUMENTO";
$tdataC000046[".printFields"][] = "NOTAFISCAL";
$tdataC000046[".printFields"][] = "HISTORICO";
$tdataC000046[".printFields"][] = "ESPECIE";
$tdataC000046[".printFields"][] = "SITUACAO";
$tdataC000046[".printFields"][] = "CODNOTA";
$tdataC000046[".printFields"][] = "MOVIMENTO";
$tdataC000046[".printFields"][] = "CODCAIXA";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Código"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
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
			$edata["EditParams"].= " maxlength=6";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000046["CODIGO"] = $fdata;
//	DATA_EMISSAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "DATA_EMISSAO";
	$fdata["GoodName"] = "DATA_EMISSAO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Data de Emissão"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_EMISSAO"; 
		$fdata["FullName"] = "DATA_EMISSAO";
	
		
		
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
	
		
		
	$tdataC000046["DATA_EMISSAO"] = $fdata;
//	DATA_VENCIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "DATA_VENCIMENTO";
	$fdata["GoodName"] = "DATA_VENCIMENTO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Data de Vencimento"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_VENCIMENTO"; 
		$fdata["FullName"] = "DATA_VENCIMENTO";
	
		
		
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
	
		
		
	$tdataC000046["DATA_VENCIMENTO"] = $fdata;
//	DATA_PAGAMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "DATA_PAGAMENTO";
	$fdata["GoodName"] = "DATA_PAGAMENTO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Data de Pagamento"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_PAGAMENTO"; 
		$fdata["FullName"] = "DATA_PAGAMENTO";
	
		
		
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
	
		
		
	$tdataC000046["DATA_PAGAMENTO"] = $fdata;
//	CODCONTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "CODCONTA";
	$fdata["GoodName"] = "CODCONTA";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Código da Conta"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
	
		
		
	$tdataC000046["CODCONTA"] = $fdata;
//	CODFORNECEDOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "CODFORNECEDOR";
	$fdata["GoodName"] = "CODFORNECEDOR";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Código do Fornecedor"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODFORNECEDOR"; 
		$fdata["FullName"] = "CODFORNECEDOR";
	
		
		
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
	
		
		
	$tdataC000046["CODFORNECEDOR"] = $fdata;
//	VALOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "VALOR";
	$fdata["GoodName"] = "VALOR";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Valor"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
	
		
		
	$tdataC000046["VALOR"] = $fdata;
//	VALORPAGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "VALORPAGO";
	$fdata["GoodName"] = "VALORPAGO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Valor Pago"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALORPAGO"; 
		$fdata["FullName"] = "VALORPAGO";
	
		
		
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
	
		
		
	$tdataC000046["VALORPAGO"] = $fdata;
//	LIQUIDO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "LIQUIDO";
	$fdata["GoodName"] = "LIQUIDO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Valor Liquido"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "LIQUIDO"; 
		$fdata["FullName"] = "LIQUIDO";
	
		
		
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
	
		
		
	$tdataC000046["LIQUIDO"] = $fdata;
//	DESCONTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "DESCONTO";
	$fdata["GoodName"] = "DESCONTO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Desconto"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DESCONTO"; 
		$fdata["FullName"] = "DESCONTO";
	
		
		
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
	
		
		
	$tdataC000046["DESCONTO"] = $fdata;
//	ACRESCIMO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "ACRESCIMO";
	$fdata["GoodName"] = "ACRESCIMO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Acréscimo"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ACRESCIMO"; 
		$fdata["FullName"] = "ACRESCIMO";
	
		
		
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
	
		
		
	$tdataC000046["ACRESCIMO"] = $fdata;
//	DOCUMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "DOCUMENTO";
	$fdata["GoodName"] = "DOCUMENTO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Documento"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DOCUMENTO"; 
		$fdata["FullName"] = "DOCUMENTO";
	
		
		
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
	
		
		
	$tdataC000046["DOCUMENTO"] = $fdata;
//	NOTAFISCAL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "NOTAFISCAL";
	$fdata["GoodName"] = "NOTAFISCAL";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Nota Fiscal"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "NOTAFISCAL"; 
		$fdata["FullName"] = "NOTAFISCAL";
	
		
		
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
	
		
		
	$tdataC000046["NOTAFISCAL"] = $fdata;
//	HISTORICO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "HISTORICO";
	$fdata["GoodName"] = "HISTORICO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Histórico"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
	
		
		
	$tdataC000046["HISTORICO"] = $fdata;
//	ESPECIE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "ESPECIE";
	$fdata["GoodName"] = "ESPECIE";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Espécie"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ESPECIE"; 
		$fdata["FullName"] = "ESPECIE";
	
		
		
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
	
		
		
	$tdataC000046["ESPECIE"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Situação"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "SITUACAO"; 
		$fdata["FullName"] = "SITUACAO";
	
		
		
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
	
		
		
	$tdataC000046["SITUACAO"] = $fdata;
//	CODNOTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "CODNOTA";
	$fdata["GoodName"] = "CODNOTA";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Código Nota"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODNOTA"; 
		$fdata["FullName"] = "CODNOTA";
	
		
		
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
	
		
		
	$tdataC000046["CODNOTA"] = $fdata;
//	MOVIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "MOVIMENTO";
	$fdata["GoodName"] = "MOVIMENTO";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Movimento"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
	
		
		
	$tdataC000046["MOVIMENTO"] = $fdata;
//	CODCAIXA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "CODCAIXA";
	$fdata["GoodName"] = "CODCAIXA";
	$fdata["ownerTable"] = "C000046";
	$fdata["Label"] = "Código Caixa"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
	
		
		
	$tdataC000046["CODCAIXA"] = $fdata;

	
$tables_data["C000046"]=&$tdataC000046;
$field_labels["C000046"] = &$fieldLabelsC000046;
$fieldToolTips["C000046"] = &$fieldToolTipsC000046;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000046"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000046"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000046()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  DATA_EMISSAO,  DATA_VENCIMENTO,  DATA_PAGAMENTO,  CODCONTA,  CODFORNECEDOR,  VALOR,  VALORPAGO,  LIQUIDO,  DESCONTO,  ACRESCIMO,  DOCUMENTO,  NOTAFISCAL,  HISTORICO,  ESPECIE,  SITUACAO,  CODNOTA,  MOVIMENTO,  CODCAIXA";
$proto0["m_strFrom"] = "FROM C000046";
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
	"m_strTable" => "C000046"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_EMISSAO",
	"m_strTable" => "C000046"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_VENCIMENTO",
	"m_strTable" => "C000046"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_PAGAMENTO",
	"m_strTable" => "C000046"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCONTA",
	"m_strTable" => "C000046"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "CODFORNECEDOR",
	"m_strTable" => "C000046"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR",
	"m_strTable" => "C000046"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "VALORPAGO",
	"m_strTable" => "C000046"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "LIQUIDO",
	"m_strTable" => "C000046"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "DESCONTO",
	"m_strTable" => "C000046"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "ACRESCIMO",
	"m_strTable" => "C000046"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "DOCUMENTO",
	"m_strTable" => "C000046"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "NOTAFISCAL",
	"m_strTable" => "C000046"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "HISTORICO",
	"m_strTable" => "C000046"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "ESPECIE",
	"m_strTable" => "C000046"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000046"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "CODNOTA",
	"m_strTable" => "C000046"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "MOVIMENTO",
	"m_strTable" => "C000046"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCAIXA",
	"m_strTable" => "C000046"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto43=array();
$proto43["m_link"] = "SQLL_MAIN";
			$proto44=array();
$proto44["m_strName"] = "C000046";
$proto44["m_columns"] = array();
$proto44["m_columns"][] = "CODIGO";
$proto44["m_columns"][] = "DATA_EMISSAO";
$proto44["m_columns"][] = "DATA_VENCIMENTO";
$proto44["m_columns"][] = "DATA_PAGAMENTO";
$proto44["m_columns"][] = "CODCONTA";
$proto44["m_columns"][] = "CODFORNECEDOR";
$proto44["m_columns"][] = "VALOR";
$proto44["m_columns"][] = "VALORPAGO";
$proto44["m_columns"][] = "LIQUIDO";
$proto44["m_columns"][] = "DESCONTO";
$proto44["m_columns"][] = "ACRESCIMO";
$proto44["m_columns"][] = "DOCUMENTO";
$proto44["m_columns"][] = "NOTAFISCAL";
$proto44["m_columns"][] = "HISTORICO";
$proto44["m_columns"][] = "C";
$proto44["m_columns"][] = "E";
$proto44["m_columns"][] = "FILTRO";
$proto44["m_columns"][] = "ESPECIE";
$proto44["m_columns"][] = "SITUACAO";
$proto44["m_columns"][] = "CODNOTA";
$proto44["m_columns"][] = "MOVIMENTO";
$proto44["m_columns"][] = "CODCAIXA";
$obj = new SQLTable($proto44);

$proto43["m_table"] = $obj;
$proto43["m_alias"] = "";
$proto45=array();
$proto45["m_sql"] = "";
$proto45["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto45["m_column"]=$obj;
$proto45["m_contained"] = array();
$proto45["m_strCase"] = "";
$proto45["m_havingmode"] = "0";
$proto45["m_inBrackets"] = "0";
$proto45["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto45);

$proto43["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto43);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000046 = createSqlQuery_C000046();
																			$tdataC000046[".sqlquery"] = $queryData_C000046;
	
if(isset($tdataC000046["field2"])){
	$tdataC000046["field2"]["LookupTable"] = "carscars_view";
	$tdataC000046["field2"]["LookupOrderBy"] = "name";
	$tdataC000046["field2"]["LookupType"] = 4;
	$tdataC000046["field2"]["LinkField"] = "email";
	$tdataC000046["field2"]["DisplayField"] = "name";
	$tdataC000046[".hasCustomViewField"] = true;
}

$tableEvents["C000046"] = new eventsBase;
$tdataC000046[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000046");

?>