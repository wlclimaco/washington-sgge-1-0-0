<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000040 = array();
	$tdataC000040[".NumberOfChars"] = 80; 
	$tdataC000040[".ShortName"] = "C000040";
	$tdataC000040[".OwnerID"] = "";
	$tdataC000040[".OriginalTable"] = "C000040";

//	field labels
$fieldLabelsC000040 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000040["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000040["Portuguese(Brazil)"] = array();
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["EMISSAO"] = "Emissão";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["EMISSAO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["VENCIMENTO"] = "Vencimento";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["VENCIMENTO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DATA_DEPOSITO1"] = "Data Depósito nº1";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DATA_DEPOSITO1"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DATA_DEPOSITO2"] = "Data Depósito nº2";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DATA_DEPOSITO2"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DATA_DEVOLUCAO1"] = "Data Devolução nº1";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DATA_DEVOLUCAO1"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DATA_DEVOLUCAO2"] = "Data Devolução nº2";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DATA_DEVOLUCAO2"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["SITUACAO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODCLIENTE"] = "Código Cliente";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODCLIENTE"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["TITULAR"] = "Titular";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["TITULAR"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODBANCO"] = "Código Banco";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODBANCO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["AGENCIA"] = "Agência";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["AGENCIA"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CONTA"] = "Conta";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CONTA"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DATA_CONTA"] = "Data da Conta";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DATA_CONTA"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["NUMERO"] = "Número";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["NUMERO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["VALOR"] = "Valor";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["VALOR"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DESCONTO"] = "Desconto";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DESCONTO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["LIQUIDO"] = "Líquido";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["LIQUIDO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODVENDA"] = "Código da Venda";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODVENDA"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["OBS1"] = "OBS";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["OBS1"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODCONTAS_PAGAR"] = "Código Contas a Pagar";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODCONTAS_PAGAR"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DESTINO"] = "Destino";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DESTINO"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["DATA_BAIXA"] = "Data da Baixa";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["DATA_BAIXA"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODCONTA_CORRENTE"] = "Conta Corrente";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODCONTA_CORRENTE"] = "";
	$fieldLabelsC000040["Portuguese(Brazil)"]["CODCONTA"] = "Conta";
	$fieldToolTipsC000040["Portuguese(Brazil)"]["CODCONTA"] = "";
	if (count($fieldToolTipsC000040["Portuguese(Brazil)"]))
		$tdataC000040[".isUseToolTips"] = true;
}
	
	
	$tdataC000040[".NCSearch"] = true;



$tdataC000040[".shortTableName"] = "C000040";
$tdataC000040[".nSecOptions"] = 0;
$tdataC000040[".recsPerRowList"] = 1;
$tdataC000040[".mainTableOwnerID"] = "";
$tdataC000040[".moveNext"] = 1;
$tdataC000040[".nType"] = 0;

$tdataC000040[".strOriginalTableName"] = "C000040";




$tdataC000040[".showAddInPopup"] = false;

$tdataC000040[".showEditInPopup"] = false;

$tdataC000040[".showViewInPopup"] = false;

$tdataC000040[".fieldsForRegister"] = array();

$tdataC000040[".listAjax"] = false;

	$tdataC000040[".audit"] = false;

	$tdataC000040[".locking"] = false;

$tdataC000040[".listIcons"] = true;
$tdataC000040[".inlineEdit"] = true;
$tdataC000040[".inlineAdd"] = true;
$tdataC000040[".view"] = true;

$tdataC000040[".exportTo"] = true;

$tdataC000040[".printFriendly"] = true;


$tdataC000040[".showSimpleSearchOptions"] = false;

$tdataC000040[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000040[".isUseAjaxSuggest"] = false;
else 
	$tdataC000040[".isUseAjaxSuggest"] = true;

$tdataC000040[".rowHighlite"] = true;

// button handlers file names

$tdataC000040[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000040[".isUseTimeForSearch"] = false;




$tdataC000040[".allSearchFields"] = array();

$tdataC000040[".allSearchFields"][] = "CODIGO";
$tdataC000040[".allSearchFields"][] = "TITULAR";

$tdataC000040[".googleLikeFields"][] = "CODIGO";
$tdataC000040[".googleLikeFields"][] = "EMISSAO";
$tdataC000040[".googleLikeFields"][] = "VENCIMENTO";
$tdataC000040[".googleLikeFields"][] = "DATA_DEPOSITO1";
$tdataC000040[".googleLikeFields"][] = "DATA_DEPOSITO2";
$tdataC000040[".googleLikeFields"][] = "DATA_DEVOLUCAO1";
$tdataC000040[".googleLikeFields"][] = "DATA_DEVOLUCAO2";
$tdataC000040[".googleLikeFields"][] = "SITUACAO";
$tdataC000040[".googleLikeFields"][] = "CODCLIENTE";
$tdataC000040[".googleLikeFields"][] = "TITULAR";
$tdataC000040[".googleLikeFields"][] = "CODBANCO";
$tdataC000040[".googleLikeFields"][] = "AGENCIA";
$tdataC000040[".googleLikeFields"][] = "CONTA";
$tdataC000040[".googleLikeFields"][] = "DATA_CONTA";
$tdataC000040[".googleLikeFields"][] = "NUMERO";
$tdataC000040[".googleLikeFields"][] = "VALOR";
$tdataC000040[".googleLikeFields"][] = "DESCONTO";
$tdataC000040[".googleLikeFields"][] = "LIQUIDO";
$tdataC000040[".googleLikeFields"][] = "CODVENDA";
$tdataC000040[".googleLikeFields"][] = "OBS1";
$tdataC000040[".googleLikeFields"][] = "CODCONTAS_PAGAR";
$tdataC000040[".googleLikeFields"][] = "DESTINO";
$tdataC000040[".googleLikeFields"][] = "DATA_BAIXA";
$tdataC000040[".googleLikeFields"][] = "CODCONTA_CORRENTE";
$tdataC000040[".googleLikeFields"][] = "CODCONTA";


$tdataC000040[".advSearchFields"][] = "CODIGO";
$tdataC000040[".advSearchFields"][] = "TITULAR";

$tdataC000040[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000040[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000040[".strOrderBy"] = $tstrOrderBy;

$tdataC000040[".orderindexes"] = array();

$tdataC000040[".sqlHead"] = "SELECT CODIGO,  EMISSAO,  VENCIMENTO,  DATA_DEPOSITO1,  DATA_DEPOSITO2,  DATA_DEVOLUCAO1,  DATA_DEVOLUCAO2,  SITUACAO,  CODCLIENTE,  TITULAR,  CODBANCO,  AGENCIA,  CONTA,  DATA_CONTA,  NUMERO,  VALOR,  DESCONTO,  LIQUIDO,  CODVENDA,  OBS1,  CODCONTAS_PAGAR,  DESTINO,  DATA_BAIXA,  CODCONTA_CORRENTE,  CODCONTA";
$tdataC000040[".sqlFrom"] = "FROM C000040";
$tdataC000040[".sqlWhereExpr"] = "";
$tdataC000040[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000040[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000040[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000040 = array();
$tableKeysC000040[] = "CODIGO";
$tdataC000040[".Keys"] = $tableKeysC000040;

$tdataC000040[".listFields"] = array();
$tdataC000040[".listFields"][] = "CODIGO";
$tdataC000040[".listFields"][] = "CODCLIENTE";
$tdataC000040[".listFields"][] = "TITULAR";
$tdataC000040[".listFields"][] = "EMISSAO";
$tdataC000040[".listFields"][] = "VENCIMENTO";
$tdataC000040[".listFields"][] = "VALOR";

$tdataC000040[".viewFields"] = array();
$tdataC000040[".viewFields"][] = "CODIGO";
$tdataC000040[".viewFields"][] = "TITULAR";
$tdataC000040[".viewFields"][] = "SITUACAO";
$tdataC000040[".viewFields"][] = "EMISSAO";
$tdataC000040[".viewFields"][] = "VENCIMENTO";
$tdataC000040[".viewFields"][] = "DATA_DEPOSITO1";
$tdataC000040[".viewFields"][] = "DATA_DEPOSITO2";
$tdataC000040[".viewFields"][] = "DATA_DEVOLUCAO1";
$tdataC000040[".viewFields"][] = "DATA_DEVOLUCAO2";
$tdataC000040[".viewFields"][] = "CODCLIENTE";
$tdataC000040[".viewFields"][] = "CODBANCO";
$tdataC000040[".viewFields"][] = "AGENCIA";
$tdataC000040[".viewFields"][] = "CONTA";
$tdataC000040[".viewFields"][] = "DATA_CONTA";
$tdataC000040[".viewFields"][] = "NUMERO";
$tdataC000040[".viewFields"][] = "VALOR";
$tdataC000040[".viewFields"][] = "DESCONTO";
$tdataC000040[".viewFields"][] = "LIQUIDO";
$tdataC000040[".viewFields"][] = "CODVENDA";
$tdataC000040[".viewFields"][] = "CODCONTAS_PAGAR";
$tdataC000040[".viewFields"][] = "DESTINO";
$tdataC000040[".viewFields"][] = "DATA_BAIXA";
$tdataC000040[".viewFields"][] = "CODCONTA_CORRENTE";
$tdataC000040[".viewFields"][] = "CODCONTA";
$tdataC000040[".viewFields"][] = "OBS1";

$tdataC000040[".addFields"] = array();

$tdataC000040[".inlineAddFields"] = array();

$tdataC000040[".editFields"] = array();

$tdataC000040[".inlineEditFields"] = array();

$tdataC000040[".exportFields"] = array();
$tdataC000040[".exportFields"][] = "CODIGO";
$tdataC000040[".exportFields"][] = "TITULAR";
$tdataC000040[".exportFields"][] = "EMISSAO";
$tdataC000040[".exportFields"][] = "VENCIMENTO";
$tdataC000040[".exportFields"][] = "DATA_DEPOSITO1";
$tdataC000040[".exportFields"][] = "DATA_DEPOSITO2";
$tdataC000040[".exportFields"][] = "DATA_DEVOLUCAO1";
$tdataC000040[".exportFields"][] = "DATA_DEVOLUCAO2";
$tdataC000040[".exportFields"][] = "SITUACAO";
$tdataC000040[".exportFields"][] = "CODCLIENTE";
$tdataC000040[".exportFields"][] = "CODBANCO";
$tdataC000040[".exportFields"][] = "AGENCIA";
$tdataC000040[".exportFields"][] = "CONTA";
$tdataC000040[".exportFields"][] = "DATA_CONTA";
$tdataC000040[".exportFields"][] = "NUMERO";
$tdataC000040[".exportFields"][] = "VALOR";
$tdataC000040[".exportFields"][] = "DESCONTO";
$tdataC000040[".exportFields"][] = "LIQUIDO";
$tdataC000040[".exportFields"][] = "CODVENDA";
$tdataC000040[".exportFields"][] = "CODCONTAS_PAGAR";
$tdataC000040[".exportFields"][] = "DESTINO";
$tdataC000040[".exportFields"][] = "DATA_BAIXA";
$tdataC000040[".exportFields"][] = "CODCONTA_CORRENTE";
$tdataC000040[".exportFields"][] = "CODCONTA";
$tdataC000040[".exportFields"][] = "OBS1";

$tdataC000040[".printFields"] = array();
$tdataC000040[".printFields"][] = "CODIGO";
$tdataC000040[".printFields"][] = "TITULAR";
$tdataC000040[".printFields"][] = "EMISSAO";
$tdataC000040[".printFields"][] = "VENCIMENTO";
$tdataC000040[".printFields"][] = "DATA_DEPOSITO1";
$tdataC000040[".printFields"][] = "DATA_DEPOSITO2";
$tdataC000040[".printFields"][] = "DATA_DEVOLUCAO1";
$tdataC000040[".printFields"][] = "DATA_DEVOLUCAO2";
$tdataC000040[".printFields"][] = "SITUACAO";
$tdataC000040[".printFields"][] = "CODCLIENTE";
$tdataC000040[".printFields"][] = "CODBANCO";
$tdataC000040[".printFields"][] = "AGENCIA";
$tdataC000040[".printFields"][] = "CONTA";
$tdataC000040[".printFields"][] = "DATA_CONTA";
$tdataC000040[".printFields"][] = "NUMERO";
$tdataC000040[".printFields"][] = "VALOR";
$tdataC000040[".printFields"][] = "DESCONTO";
$tdataC000040[".printFields"][] = "LIQUIDO";
$tdataC000040[".printFields"][] = "CODVENDA";
$tdataC000040[".printFields"][] = "CODCONTAS_PAGAR";
$tdataC000040[".printFields"][] = "DESTINO";
$tdataC000040[".printFields"][] = "DATA_BAIXA";
$tdataC000040[".printFields"][] = "CODCONTA_CORRENTE";
$tdataC000040[".printFields"][] = "CODCONTA";
$tdataC000040[".printFields"][] = "OBS1";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000040";
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
	
		
		
	$tdataC000040["CODIGO"] = $fdata;
//	EMISSAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "EMISSAO";
	$fdata["GoodName"] = "EMISSAO";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Emissão"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "EMISSAO"; 
		$fdata["FullName"] = "EMISSAO";
	
		
		
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
	
		
		
	$tdataC000040["EMISSAO"] = $fdata;
//	VENCIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "VENCIMENTO";
	$fdata["GoodName"] = "VENCIMENTO";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Vencimento"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VENCIMENTO"; 
		$fdata["FullName"] = "VENCIMENTO";
	
		
		
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
	
		
		
	$tdataC000040["VENCIMENTO"] = $fdata;
//	DATA_DEPOSITO1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "DATA_DEPOSITO1";
	$fdata["GoodName"] = "DATA_DEPOSITO1";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Data Depósito nº1"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_DEPOSITO1"; 
		$fdata["FullName"] = "DATA_DEPOSITO1";
	
		
		
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
	
		
		
	$tdataC000040["DATA_DEPOSITO1"] = $fdata;
//	DATA_DEPOSITO2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "DATA_DEPOSITO2";
	$fdata["GoodName"] = "DATA_DEPOSITO2";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Data Depósito nº2"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_DEPOSITO2"; 
		$fdata["FullName"] = "DATA_DEPOSITO2";
	
		
		
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
	
		
		
	$tdataC000040["DATA_DEPOSITO2"] = $fdata;
//	DATA_DEVOLUCAO1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "DATA_DEVOLUCAO1";
	$fdata["GoodName"] = "DATA_DEVOLUCAO1";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Data Devolução nº1"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_DEVOLUCAO1"; 
		$fdata["FullName"] = "DATA_DEVOLUCAO1";
	
		
		
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
	
		
		
	$tdataC000040["DATA_DEVOLUCAO1"] = $fdata;
//	DATA_DEVOLUCAO2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "DATA_DEVOLUCAO2";
	$fdata["GoodName"] = "DATA_DEVOLUCAO2";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Data Devolução nº2"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_DEVOLUCAO2"; 
		$fdata["FullName"] = "DATA_DEVOLUCAO2";
	
		
		
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
	
		
		
	$tdataC000040["DATA_DEVOLUCAO2"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000040";
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
	
		
		
	$tdataC000040["SITUACAO"] = $fdata;
//	CODCLIENTE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "CODCLIENTE";
	$fdata["GoodName"] = "CODCLIENTE";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Código Cliente"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODCLIENTE"; 
		$fdata["FullName"] = "CODCLIENTE";
	
		
		
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
	
		
		
	$tdataC000040["CODCLIENTE"] = $fdata;
//	TITULAR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "TITULAR";
	$fdata["GoodName"] = "TITULAR";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Titular"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TITULAR"; 
		$fdata["FullName"] = "TITULAR";
	
		
		
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
	
		
		
	$tdataC000040["TITULAR"] = $fdata;
//	CODBANCO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "CODBANCO";
	$fdata["GoodName"] = "CODBANCO";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Código Banco"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODBANCO"; 
		$fdata["FullName"] = "CODBANCO";
	
		
		
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
	
		
		
	$tdataC000040["CODBANCO"] = $fdata;
//	AGENCIA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "AGENCIA";
	$fdata["GoodName"] = "AGENCIA";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Agência"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "AGENCIA"; 
		$fdata["FullName"] = "AGENCIA";
	
		
		
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
			$edata["EditParams"].= " maxlength=8";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000040["AGENCIA"] = $fdata;
//	CONTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "CONTA";
	$fdata["GoodName"] = "CONTA";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Conta"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CONTA"; 
		$fdata["FullName"] = "CONTA";
	
		
		
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
	
		
		
	$tdataC000040["CONTA"] = $fdata;
//	DATA_CONTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "DATA_CONTA";
	$fdata["GoodName"] = "DATA_CONTA";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Data da Conta"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_CONTA"; 
		$fdata["FullName"] = "DATA_CONTA";
	
		
		
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
	
		
		
	$tdataC000040["DATA_CONTA"] = $fdata;
//	NUMERO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "NUMERO";
	$fdata["GoodName"] = "NUMERO";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Número"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
			$edata["EditParams"].= " maxlength=15";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000040["NUMERO"] = $fdata;
//	VALOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "VALOR";
	$fdata["GoodName"] = "VALOR";
	$fdata["ownerTable"] = "C000040";
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
	
		
		
	$tdataC000040["VALOR"] = $fdata;
//	DESCONTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "DESCONTO";
	$fdata["GoodName"] = "DESCONTO";
	$fdata["ownerTable"] = "C000040";
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
	
		
		
	$tdataC000040["DESCONTO"] = $fdata;
//	LIQUIDO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "LIQUIDO";
	$fdata["GoodName"] = "LIQUIDO";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Líquido"; 
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
	
		
		
	$tdataC000040["LIQUIDO"] = $fdata;
//	CODVENDA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "CODVENDA";
	$fdata["GoodName"] = "CODVENDA";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Código da Venda"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODVENDA"; 
		$fdata["FullName"] = "CODVENDA";
	
		
		
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
	
		
		
	$tdataC000040["CODVENDA"] = $fdata;
//	OBS1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "OBS1";
	$fdata["GoodName"] = "OBS1";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "OBS"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "OBS1"; 
		$fdata["FullName"] = "OBS1";
	
		
		
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
	
		
		
	$tdataC000040["OBS1"] = $fdata;
//	CODCONTAS_PAGAR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 21;
	$fdata["strName"] = "CODCONTAS_PAGAR";
	$fdata["GoodName"] = "CODCONTAS_PAGAR";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Código Contas a Pagar"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODCONTAS_PAGAR"; 
		$fdata["FullName"] = "CODCONTAS_PAGAR";
	
		
		
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
	
		
		
	$tdataC000040["CODCONTAS_PAGAR"] = $fdata;
//	DESTINO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 22;
	$fdata["strName"] = "DESTINO";
	$fdata["GoodName"] = "DESTINO";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Destino"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DESTINO"; 
		$fdata["FullName"] = "DESTINO";
	
		
		
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
	
		
		
	$tdataC000040["DESTINO"] = $fdata;
//	DATA_BAIXA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 23;
	$fdata["strName"] = "DATA_BAIXA";
	$fdata["GoodName"] = "DATA_BAIXA";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Data da Baixa"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_BAIXA"; 
		$fdata["FullName"] = "DATA_BAIXA";
	
		
		
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
	
		
		
	$tdataC000040["DATA_BAIXA"] = $fdata;
//	CODCONTA_CORRENTE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 24;
	$fdata["strName"] = "CODCONTA_CORRENTE";
	$fdata["GoodName"] = "CODCONTA_CORRENTE";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Conta Corrente"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODCONTA_CORRENTE"; 
		$fdata["FullName"] = "CODCONTA_CORRENTE";
	
		
		
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
	
		
		
	$tdataC000040["CODCONTA_CORRENTE"] = $fdata;
//	CODCONTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 25;
	$fdata["strName"] = "CODCONTA";
	$fdata["GoodName"] = "CODCONTA";
	$fdata["ownerTable"] = "C000040";
	$fdata["Label"] = "Conta"; 
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
			$edata["EditParams"].= " maxlength=20";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000040["CODCONTA"] = $fdata;

	
$tables_data["C000040"]=&$tdataC000040;
$field_labels["C000040"] = &$fieldLabelsC000040;
$fieldToolTips["C000040"] = &$fieldToolTipsC000040;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000040"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000040"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000040()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  EMISSAO,  VENCIMENTO,  DATA_DEPOSITO1,  DATA_DEPOSITO2,  DATA_DEVOLUCAO1,  DATA_DEVOLUCAO2,  SITUACAO,  CODCLIENTE,  TITULAR,  CODBANCO,  AGENCIA,  CONTA,  DATA_CONTA,  NUMERO,  VALOR,  DESCONTO,  LIQUIDO,  CODVENDA,  OBS1,  CODCONTAS_PAGAR,  DESTINO,  DATA_BAIXA,  CODCONTA_CORRENTE,  CODCONTA";
$proto0["m_strFrom"] = "FROM C000040";
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
	"m_strTable" => "C000040"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "EMISSAO",
	"m_strTable" => "C000040"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "VENCIMENTO",
	"m_strTable" => "C000040"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_DEPOSITO1",
	"m_strTable" => "C000040"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_DEPOSITO2",
	"m_strTable" => "C000040"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_DEVOLUCAO1",
	"m_strTable" => "C000040"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_DEVOLUCAO2",
	"m_strTable" => "C000040"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000040"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCLIENTE",
	"m_strTable" => "C000040"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "TITULAR",
	"m_strTable" => "C000040"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "CODBANCO",
	"m_strTable" => "C000040"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "AGENCIA",
	"m_strTable" => "C000040"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "CONTA",
	"m_strTable" => "C000040"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_CONTA",
	"m_strTable" => "C000040"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "NUMERO",
	"m_strTable" => "C000040"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR",
	"m_strTable" => "C000040"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "DESCONTO",
	"m_strTable" => "C000040"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "LIQUIDO",
	"m_strTable" => "C000040"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "CODVENDA",
	"m_strTable" => "C000040"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "OBS1",
	"m_strTable" => "C000040"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
						$proto45=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCONTAS_PAGAR",
	"m_strTable" => "C000040"
));

$proto45["m_expr"]=$obj;
$proto45["m_alias"] = "";
$obj = new SQLFieldListItem($proto45);

$proto0["m_fieldlist"][]=$obj;
						$proto47=array();
			$obj = new SQLField(array(
	"m_strName" => "DESTINO",
	"m_strTable" => "C000040"
));

$proto47["m_expr"]=$obj;
$proto47["m_alias"] = "";
$obj = new SQLFieldListItem($proto47);

$proto0["m_fieldlist"][]=$obj;
						$proto49=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_BAIXA",
	"m_strTable" => "C000040"
));

$proto49["m_expr"]=$obj;
$proto49["m_alias"] = "";
$obj = new SQLFieldListItem($proto49);

$proto0["m_fieldlist"][]=$obj;
						$proto51=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCONTA_CORRENTE",
	"m_strTable" => "C000040"
));

$proto51["m_expr"]=$obj;
$proto51["m_alias"] = "";
$obj = new SQLFieldListItem($proto51);

$proto0["m_fieldlist"][]=$obj;
						$proto53=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCONTA",
	"m_strTable" => "C000040"
));

$proto53["m_expr"]=$obj;
$proto53["m_alias"] = "";
$obj = new SQLFieldListItem($proto53);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto55=array();
$proto55["m_link"] = "SQLL_MAIN";
			$proto56=array();
$proto56["m_strName"] = "C000040";
$proto56["m_columns"] = array();
$proto56["m_columns"][] = "CODIGO";
$proto56["m_columns"][] = "EMISSAO";
$proto56["m_columns"][] = "VENCIMENTO";
$proto56["m_columns"][] = "DATA_DEPOSITO1";
$proto56["m_columns"][] = "DATA_DEPOSITO2";
$proto56["m_columns"][] = "DATA_DEVOLUCAO1";
$proto56["m_columns"][] = "DATA_DEVOLUCAO2";
$proto56["m_columns"][] = "SITUACAO";
$proto56["m_columns"][] = "CODCLIENTE";
$proto56["m_columns"][] = "TITULAR";
$proto56["m_columns"][] = "CODBANCO";
$proto56["m_columns"][] = "AGENCIA";
$proto56["m_columns"][] = "CONTA";
$proto56["m_columns"][] = "DATA_CONTA";
$proto56["m_columns"][] = "NUMERO";
$proto56["m_columns"][] = "VALOR";
$proto56["m_columns"][] = "DESCONTO";
$proto56["m_columns"][] = "LIQUIDO";
$proto56["m_columns"][] = "CODVENDA";
$proto56["m_columns"][] = "OBS1";
$proto56["m_columns"][] = "OBS2";
$proto56["m_columns"][] = "CODCONTAS_PAGAR";
$proto56["m_columns"][] = "DESTINO";
$proto56["m_columns"][] = "DATA_BAIXA";
$proto56["m_columns"][] = "CODCONTA_CORRENTE";
$proto56["m_columns"][] = "CODCONTA";
$obj = new SQLTable($proto56);

$proto55["m_table"] = $obj;
$proto55["m_alias"] = "";
$proto57=array();
$proto57["m_sql"] = "";
$proto57["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto57["m_column"]=$obj;
$proto57["m_contained"] = array();
$proto57["m_strCase"] = "";
$proto57["m_havingmode"] = "0";
$proto57["m_inBrackets"] = "0";
$proto57["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto57);

$proto55["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto55);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000040 = createSqlQuery_C000040();
																									$tdataC000040[".sqlquery"] = $queryData_C000040;
	
if(isset($tdataC000040["field2"])){
	$tdataC000040["field2"]["LookupTable"] = "carscars_view";
	$tdataC000040["field2"]["LookupOrderBy"] = "name";
	$tdataC000040["field2"]["LookupType"] = 4;
	$tdataC000040["field2"]["LinkField"] = "email";
	$tdataC000040["field2"]["DisplayField"] = "name";
	$tdataC000040[".hasCustomViewField"] = true;
}

$tableEvents["C000040"] = new eventsBase;
$tdataC000040[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000040");

?>