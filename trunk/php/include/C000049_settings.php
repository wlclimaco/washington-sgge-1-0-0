<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000049 = array();
	$tdataC000049[".NumberOfChars"] = 80; 
	$tdataC000049[".ShortName"] = "C000049";
	$tdataC000049[".OwnerID"] = "";
	$tdataC000049[".OriginalTable"] = "C000049";

//	field labels
$fieldLabelsC000049 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000049["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000049["Portuguese(Brazil)"] = array();
	$fieldLabelsC000049["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["CODVENDA"] = "Venda";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["CODVENDA"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["CODVENDEDOR"] = "Vendedor";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["CODVENDEDOR"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["CODCAIXA"] = "Caixa";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["CODCAIXA"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["CODCLIENTE"] = "Cliente";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["CODCLIENTE"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["DATA_EMISSAO"] = "Data de Emissão";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["DATA_EMISSAO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["DATA_VENCIMENTO"] = "Data de Vencimento";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["DATA_VENCIMENTO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["DATA_PAGAMENTO"] = "Data de Pagamento";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["DATA_PAGAMENTO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_ORIGINAL"] = "Valor Original";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_ORIGINAL"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_PAGO"] = "Valor Pago";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_PAGO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_JUROS"] = "Valor com Juros";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_JUROS"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_ATUAL"] = "Valor Atual";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_ATUAL"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_DESCONTO"] = "Valor com Desconto";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_DESCONTO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["DOCUMENTO"] = "Documento";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["DOCUMENTO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["TIPO"] = "Tipo";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["TIPO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["SITUACAO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["NOSSONUMERO"] = "Nosso Número";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["NOSSONUMERO"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["NUMERO_CUPOM"] = "Número Cupom";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["NUMERO_CUPOM"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_VENDA"] = "Valor da Venda";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_VENDA"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["COD_VENDA_ORIGINAL"] = "Código da Venda";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["COD_VENDA_ORIGINAL"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["VALOR_ATUAL_ANTERIOR"] = "VALOR ATUAL ANTERIOR";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["VALOR_ATUAL_ANTERIOR"] = "";
	$fieldLabelsC000049["Portuguese(Brazil)"]["HISTORICO"] = "Histórico";
	$fieldToolTipsC000049["Portuguese(Brazil)"]["HISTORICO"] = "";
	if (count($fieldToolTipsC000049["Portuguese(Brazil)"]))
		$tdataC000049[".isUseToolTips"] = true;
}
	
	
	$tdataC000049[".NCSearch"] = true;



$tdataC000049[".shortTableName"] = "C000049";
$tdataC000049[".nSecOptions"] = 0;
$tdataC000049[".recsPerRowList"] = 1;
$tdataC000049[".mainTableOwnerID"] = "";
$tdataC000049[".moveNext"] = 1;
$tdataC000049[".nType"] = 0;

$tdataC000049[".strOriginalTableName"] = "C000049";




$tdataC000049[".showAddInPopup"] = false;

$tdataC000049[".showEditInPopup"] = false;

$tdataC000049[".showViewInPopup"] = false;

$tdataC000049[".fieldsForRegister"] = array();

$tdataC000049[".listAjax"] = false;

	$tdataC000049[".audit"] = false;

	$tdataC000049[".locking"] = false;

$tdataC000049[".listIcons"] = true;

$tdataC000049[".exportTo"] = true;

$tdataC000049[".printFriendly"] = true;


$tdataC000049[".showSimpleSearchOptions"] = false;

$tdataC000049[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000049[".isUseAjaxSuggest"] = false;
else 
	$tdataC000049[".isUseAjaxSuggest"] = true;

$tdataC000049[".rowHighlite"] = true;

// button handlers file names

$tdataC000049[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000049[".isUseTimeForSearch"] = false;




$tdataC000049[".allSearchFields"] = array();

$tdataC000049[".allSearchFields"][] = "CODIGO";
$tdataC000049[".allSearchFields"][] = "CODVENDA";
$tdataC000049[".allSearchFields"][] = "CODVENDEDOR";
$tdataC000049[".allSearchFields"][] = "CODCAIXA";

$tdataC000049[".googleLikeFields"][] = "CODIGO";
$tdataC000049[".googleLikeFields"][] = "CODVENDA";
$tdataC000049[".googleLikeFields"][] = "CODVENDEDOR";
$tdataC000049[".googleLikeFields"][] = "CODCAIXA";
$tdataC000049[".googleLikeFields"][] = "CODCLIENTE";
$tdataC000049[".googleLikeFields"][] = "DATA_EMISSAO";
$tdataC000049[".googleLikeFields"][] = "DATA_VENCIMENTO";
$tdataC000049[".googleLikeFields"][] = "DATA_PAGAMENTO";
$tdataC000049[".googleLikeFields"][] = "VALOR_ORIGINAL";
$tdataC000049[".googleLikeFields"][] = "VALOR_PAGO";
$tdataC000049[".googleLikeFields"][] = "VALOR_JUROS";
$tdataC000049[".googleLikeFields"][] = "VALOR_ATUAL";
$tdataC000049[".googleLikeFields"][] = "VALOR_DESCONTO";
$tdataC000049[".googleLikeFields"][] = "DOCUMENTO";
$tdataC000049[".googleLikeFields"][] = "TIPO";
$tdataC000049[".googleLikeFields"][] = "SITUACAO";
$tdataC000049[".googleLikeFields"][] = "NOSSONUMERO";
$tdataC000049[".googleLikeFields"][] = "NUMERO_CUPOM";
$tdataC000049[".googleLikeFields"][] = "VALOR_VENDA";
$tdataC000049[".googleLikeFields"][] = "COD_VENDA_ORIGINAL";
$tdataC000049[".googleLikeFields"][] = "VALOR_ATUAL_ANTERIOR";
$tdataC000049[".googleLikeFields"][] = "HISTORICO";


$tdataC000049[".advSearchFields"][] = "CODIGO";
$tdataC000049[".advSearchFields"][] = "CODVENDA";
$tdataC000049[".advSearchFields"][] = "CODVENDEDOR";
$tdataC000049[".advSearchFields"][] = "CODCAIXA";

$tdataC000049[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000049[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000049[".strOrderBy"] = $tstrOrderBy;

$tdataC000049[".orderindexes"] = array();

$tdataC000049[".sqlHead"] = "SELECT CODIGO,  CODVENDA,  CODVENDEDOR,  CODCAIXA,  CODCLIENTE,  DATA_EMISSAO,  DATA_VENCIMENTO,  DATA_PAGAMENTO,  VALOR_ORIGINAL,  VALOR_PAGO,  VALOR_JUROS,  VALOR_ATUAL,  VALOR_DESCONTO,  DOCUMENTO,  TIPO,  SITUACAO,  NOSSONUMERO,  NUMERO_CUPOM,  VALOR_VENDA,  COD_VENDA_ORIGINAL,  VALOR_ATUAL_ANTERIOR,  HISTORICO";
$tdataC000049[".sqlFrom"] = "FROM C000049";
$tdataC000049[".sqlWhereExpr"] = "";
$tdataC000049[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000049[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000049[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000049 = array();
$tdataC000049[".Keys"] = $tableKeysC000049;

$tdataC000049[".listFields"] = array();
$tdataC000049[".listFields"][] = "CODIGO";
$tdataC000049[".listFields"][] = "CODVENDA";
$tdataC000049[".listFields"][] = "CODVENDEDOR";
$tdataC000049[".listFields"][] = "CODCAIXA";
$tdataC000049[".listFields"][] = "CODCLIENTE";
$tdataC000049[".listFields"][] = "DATA_EMISSAO";
$tdataC000049[".listFields"][] = "DATA_VENCIMENTO";
$tdataC000049[".listFields"][] = "DATA_PAGAMENTO";
$tdataC000049[".listFields"][] = "VALOR_ORIGINAL";
$tdataC000049[".listFields"][] = "VALOR_PAGO";
$tdataC000049[".listFields"][] = "VALOR_ATUAL";

$tdataC000049[".viewFields"] = array();

$tdataC000049[".addFields"] = array();

$tdataC000049[".inlineAddFields"] = array();

$tdataC000049[".editFields"] = array();

$tdataC000049[".inlineEditFields"] = array();

$tdataC000049[".exportFields"] = array();
$tdataC000049[".exportFields"][] = "CODIGO";
$tdataC000049[".exportFields"][] = "CODVENDA";
$tdataC000049[".exportFields"][] = "CODVENDEDOR";
$tdataC000049[".exportFields"][] = "CODCAIXA";
$tdataC000049[".exportFields"][] = "CODCLIENTE";
$tdataC000049[".exportFields"][] = "DATA_EMISSAO";
$tdataC000049[".exportFields"][] = "DATA_VENCIMENTO";
$tdataC000049[".exportFields"][] = "DATA_PAGAMENTO";
$tdataC000049[".exportFields"][] = "VALOR_ORIGINAL";
$tdataC000049[".exportFields"][] = "VALOR_PAGO";
$tdataC000049[".exportFields"][] = "VALOR_JUROS";
$tdataC000049[".exportFields"][] = "VALOR_ATUAL";
$tdataC000049[".exportFields"][] = "VALOR_DESCONTO";
$tdataC000049[".exportFields"][] = "DOCUMENTO";
$tdataC000049[".exportFields"][] = "TIPO";
$tdataC000049[".exportFields"][] = "SITUACAO";
$tdataC000049[".exportFields"][] = "NUMERO_CUPOM";
$tdataC000049[".exportFields"][] = "VALOR_VENDA";
$tdataC000049[".exportFields"][] = "HISTORICO";

$tdataC000049[".printFields"] = array();
$tdataC000049[".printFields"][] = "CODIGO";
$tdataC000049[".printFields"][] = "CODVENDA";
$tdataC000049[".printFields"][] = "CODVENDEDOR";
$tdataC000049[".printFields"][] = "CODCAIXA";
$tdataC000049[".printFields"][] = "CODCLIENTE";
$tdataC000049[".printFields"][] = "DATA_EMISSAO";
$tdataC000049[".printFields"][] = "DATA_VENCIMENTO";
$tdataC000049[".printFields"][] = "DATA_PAGAMENTO";
$tdataC000049[".printFields"][] = "VALOR_ORIGINAL";
$tdataC000049[".printFields"][] = "VALOR_PAGO";
$tdataC000049[".printFields"][] = "VALOR_JUROS";
$tdataC000049[".printFields"][] = "VALOR_ATUAL";
$tdataC000049[".printFields"][] = "VALOR_DESCONTO";
$tdataC000049[".printFields"][] = "DOCUMENTO";
$tdataC000049[".printFields"][] = "TIPO";
$tdataC000049[".printFields"][] = "SITUACAO";
$tdataC000049[".printFields"][] = "NUMERO_CUPOM";
$tdataC000049[".printFields"][] = "VALOR_VENDA";
$tdataC000049[".printFields"][] = "HISTORICO";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000049";
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
			$edata["EditParams"].= " maxlength=12";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000049["CODIGO"] = $fdata;
//	CODVENDA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "CODVENDA";
	$fdata["GoodName"] = "CODVENDA";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Venda"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
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
			$edata["EditParams"].= " maxlength=10";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000049["CODVENDA"] = $fdata;
//	CODVENDEDOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "CODVENDEDOR";
	$fdata["GoodName"] = "CODVENDEDOR";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Vendedor"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODVENDEDOR"; 
		$fdata["FullName"] = "CODVENDEDOR";
	
		
		
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
	
		
		
	$tdataC000049["CODVENDEDOR"] = $fdata;
//	CODCAIXA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "CODCAIXA";
	$fdata["GoodName"] = "CODCAIXA";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Caixa"; 
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
	
		
		
	$tdataC000049["CODCAIXA"] = $fdata;
//	CODCLIENTE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "CODCLIENTE";
	$fdata["GoodName"] = "CODCLIENTE";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Cliente"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
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
	
		
		
	$tdataC000049["CODCLIENTE"] = $fdata;
//	DATA_EMISSAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "DATA_EMISSAO";
	$fdata["GoodName"] = "DATA_EMISSAO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Data de Emissão"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
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
	
		
		
	$tdataC000049["DATA_EMISSAO"] = $fdata;
//	DATA_VENCIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "DATA_VENCIMENTO";
	$fdata["GoodName"] = "DATA_VENCIMENTO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Data de Vencimento"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
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
	
		
		
	$tdataC000049["DATA_VENCIMENTO"] = $fdata;
//	DATA_PAGAMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "DATA_PAGAMENTO";
	$fdata["GoodName"] = "DATA_PAGAMENTO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Data de Pagamento"; 
	$fdata["FieldType"] = 135;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
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
	
		
		
	$tdataC000049["DATA_PAGAMENTO"] = $fdata;
//	VALOR_ORIGINAL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "VALOR_ORIGINAL";
	$fdata["GoodName"] = "VALOR_ORIGINAL";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Valor Original"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR_ORIGINAL"; 
		$fdata["FullName"] = "VALOR_ORIGINAL";
	
		
		
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
	
		
		
	$tdataC000049["VALOR_ORIGINAL"] = $fdata;
//	VALOR_PAGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "VALOR_PAGO";
	$fdata["GoodName"] = "VALOR_PAGO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Valor Pago"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR_PAGO"; 
		$fdata["FullName"] = "VALOR_PAGO";
	
		
		
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
	
		
		
	$tdataC000049["VALOR_PAGO"] = $fdata;
//	VALOR_JUROS
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "VALOR_JUROS";
	$fdata["GoodName"] = "VALOR_JUROS";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Valor com Juros"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR_JUROS"; 
		$fdata["FullName"] = "VALOR_JUROS";
	
		
		
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
	
		
		
	$tdataC000049["VALOR_JUROS"] = $fdata;
//	VALOR_ATUAL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "VALOR_ATUAL";
	$fdata["GoodName"] = "VALOR_ATUAL";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Valor Atual"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR_ATUAL"; 
		$fdata["FullName"] = "VALOR_ATUAL";
	
		
		
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
	
		
		
	$tdataC000049["VALOR_ATUAL"] = $fdata;
//	VALOR_DESCONTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "VALOR_DESCONTO";
	$fdata["GoodName"] = "VALOR_DESCONTO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Valor com Desconto"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR_DESCONTO"; 
		$fdata["FullName"] = "VALOR_DESCONTO";
	
		
		
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
	
		
		
	$tdataC000049["VALOR_DESCONTO"] = $fdata;
//	DOCUMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "DOCUMENTO";
	$fdata["GoodName"] = "DOCUMENTO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Documento"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000049["DOCUMENTO"] = $fdata;
//	TIPO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "TIPO";
	$fdata["GoodName"] = "TIPO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Tipo"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TIPO"; 
		$fdata["FullName"] = "TIPO";
	
		
		
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
	
		
		
	$tdataC000049["TIPO"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Situação"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000049["SITUACAO"] = $fdata;
//	NOSSONUMERO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "NOSSONUMERO";
	$fdata["GoodName"] = "NOSSONUMERO";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Nosso Número"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		
		
		$fdata["strField"] = "NOSSONUMERO"; 
		$fdata["FullName"] = "NOSSONUMERO";
	
		
		
				
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
			$edata["EditParams"].= " maxlength=30";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000049["NOSSONUMERO"] = $fdata;
//	NUMERO_CUPOM
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "NUMERO_CUPOM";
	$fdata["GoodName"] = "NUMERO_CUPOM";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Número Cupom"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "NUMERO_CUPOM"; 
		$fdata["FullName"] = "NUMERO_CUPOM";
	
		
		
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
	
		
		
	$tdataC000049["NUMERO_CUPOM"] = $fdata;
//	VALOR_VENDA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "VALOR_VENDA";
	$fdata["GoodName"] = "VALOR_VENDA";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Valor da Venda"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALOR_VENDA"; 
		$fdata["FullName"] = "VALOR_VENDA";
	
		
		
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
	
		
		
	$tdataC000049["VALOR_VENDA"] = $fdata;
//	COD_VENDA_ORIGINAL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "COD_VENDA_ORIGINAL";
	$fdata["GoodName"] = "COD_VENDA_ORIGINAL";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "Código da Venda"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		
		
		$fdata["strField"] = "COD_VENDA_ORIGINAL"; 
		$fdata["FullName"] = "COD_VENDA_ORIGINAL";
	
		
		
				
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
	
		
		
	$tdataC000049["COD_VENDA_ORIGINAL"] = $fdata;
//	VALOR_ATUAL_ANTERIOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 21;
	$fdata["strName"] = "VALOR_ATUAL_ANTERIOR";
	$fdata["GoodName"] = "VALOR_ATUAL_ANTERIOR";
	$fdata["ownerTable"] = "C000049";
	$fdata["Label"] = "VALOR ATUAL ANTERIOR"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		
		
		
		
		$fdata["strField"] = "VALOR_ATUAL_ANTERIOR"; 
		$fdata["FullName"] = "VALOR_ATUAL_ANTERIOR";
	
		
		
				
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
	
		
		
	$tdataC000049["VALOR_ATUAL_ANTERIOR"] = $fdata;
//	HISTORICO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 22;
	$fdata["strName"] = "HISTORICO";
	$fdata["GoodName"] = "HISTORICO";
	$fdata["ownerTable"] = "C000049";
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
			$edata["EditParams"].= " maxlength=100";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000049["HISTORICO"] = $fdata;

	
$tables_data["C000049"]=&$tdataC000049;
$field_labels["C000049"] = &$fieldLabelsC000049;
$fieldToolTips["C000049"] = &$fieldToolTipsC000049;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000049"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000049"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000049()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  CODVENDA,  CODVENDEDOR,  CODCAIXA,  CODCLIENTE,  DATA_EMISSAO,  DATA_VENCIMENTO,  DATA_PAGAMENTO,  VALOR_ORIGINAL,  VALOR_PAGO,  VALOR_JUROS,  VALOR_ATUAL,  VALOR_DESCONTO,  DOCUMENTO,  TIPO,  SITUACAO,  NOSSONUMERO,  NUMERO_CUPOM,  VALOR_VENDA,  COD_VENDA_ORIGINAL,  VALOR_ATUAL_ANTERIOR,  HISTORICO";
$proto0["m_strFrom"] = "FROM C000049";
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
	"m_strTable" => "C000049"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "CODVENDA",
	"m_strTable" => "C000049"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "CODVENDEDOR",
	"m_strTable" => "C000049"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCAIXA",
	"m_strTable" => "C000049"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "CODCLIENTE",
	"m_strTable" => "C000049"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_EMISSAO",
	"m_strTable" => "C000049"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_VENCIMENTO",
	"m_strTable" => "C000049"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_PAGAMENTO",
	"m_strTable" => "C000049"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_ORIGINAL",
	"m_strTable" => "C000049"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_PAGO",
	"m_strTable" => "C000049"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_JUROS",
	"m_strTable" => "C000049"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_ATUAL",
	"m_strTable" => "C000049"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_DESCONTO",
	"m_strTable" => "C000049"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "DOCUMENTO",
	"m_strTable" => "C000049"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "TIPO",
	"m_strTable" => "C000049"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000049"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "NOSSONUMERO",
	"m_strTable" => "C000049"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "NUMERO_CUPOM",
	"m_strTable" => "C000049"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_VENDA",
	"m_strTable" => "C000049"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "COD_VENDA_ORIGINAL",
	"m_strTable" => "C000049"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
						$proto45=array();
			$obj = new SQLField(array(
	"m_strName" => "VALOR_ATUAL_ANTERIOR",
	"m_strTable" => "C000049"
));

$proto45["m_expr"]=$obj;
$proto45["m_alias"] = "";
$obj = new SQLFieldListItem($proto45);

$proto0["m_fieldlist"][]=$obj;
						$proto47=array();
			$obj = new SQLField(array(
	"m_strName" => "HISTORICO",
	"m_strTable" => "C000049"
));

$proto47["m_expr"]=$obj;
$proto47["m_alias"] = "";
$obj = new SQLFieldListItem($proto47);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto49=array();
$proto49["m_link"] = "SQLL_MAIN";
			$proto50=array();
$proto50["m_strName"] = "C000049";
$proto50["m_columns"] = array();
$proto50["m_columns"][] = "CODIGO";
$proto50["m_columns"][] = "CODVENDA";
$proto50["m_columns"][] = "CODVENDEDOR";
$proto50["m_columns"][] = "CODCAIXA";
$proto50["m_columns"][] = "CODCLIENTE";
$proto50["m_columns"][] = "DATA_EMISSAO";
$proto50["m_columns"][] = "DATA_VENCIMENTO";
$proto50["m_columns"][] = "DATA_PAGAMENTO";
$proto50["m_columns"][] = "VALOR_ORIGINAL";
$proto50["m_columns"][] = "VALOR_PAGO";
$proto50["m_columns"][] = "VALOR_JUROS";
$proto50["m_columns"][] = "VALOR_ATUAL";
$proto50["m_columns"][] = "VALOR_DESCONTO";
$proto50["m_columns"][] = "DOCUMENTO";
$proto50["m_columns"][] = "TIPO";
$proto50["m_columns"][] = "SITUACAO";
$proto50["m_columns"][] = "FILTRO";
$proto50["m_columns"][] = "NOSSONUMERO";
$proto50["m_columns"][] = "CODREGIAO";
$proto50["m_columns"][] = "CODCEDENTE";
$proto50["m_columns"][] = "P5";
$proto50["m_columns"][] = "P3";
$proto50["m_columns"][] = "NUMERO_CUPOM";
$proto50["m_columns"][] = "VALOR_VENDA";
$proto50["m_columns"][] = "COD_VENDA_ORIGINAL";
$proto50["m_columns"][] = "VALOR_ATUAL_ANTERIOR";
$proto50["m_columns"][] = "EMAIL_ENVIADO";
$proto50["m_columns"][] = "HISTORICO";
$obj = new SQLTable($proto50);

$proto49["m_table"] = $obj;
$proto49["m_alias"] = "";
$proto51=array();
$proto51["m_sql"] = "";
$proto51["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto51["m_column"]=$obj;
$proto51["m_contained"] = array();
$proto51["m_strCase"] = "";
$proto51["m_havingmode"] = "0";
$proto51["m_inBrackets"] = "0";
$proto51["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto51);

$proto49["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto49);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000049 = createSqlQuery_C000049();
																						$tdataC000049[".sqlquery"] = $queryData_C000049;
	
if(isset($tdataC000049["field2"])){
	$tdataC000049["field2"]["LookupTable"] = "carscars_view";
	$tdataC000049["field2"]["LookupOrderBy"] = "name";
	$tdataC000049["field2"]["LookupType"] = 4;
	$tdataC000049["field2"]["LinkField"] = "email";
	$tdataC000049["field2"]["DisplayField"] = "name";
	$tdataC000049[".hasCustomViewField"] = true;
}

$tableEvents["C000049"] = new eventsBase;
$tdataC000049[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000049");

?>