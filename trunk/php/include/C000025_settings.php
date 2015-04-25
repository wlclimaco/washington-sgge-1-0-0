<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000025 = array();
	$tdataC000025[".NumberOfChars"] = 80; 
	$tdataC000025[".ShortName"] = "C000025";
	$tdataC000025[".OwnerID"] = "";
	$tdataC000025[".OriginalTable"] = "C000025";

//	field labels
$fieldLabelsC000025 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000025["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000025["Portuguese(Brazil)"] = array();
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODBARRA"] = "Código de Barra";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODBARRA"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["PRODUTO"] = "Produto";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["PRODUTO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["UNIDADE"] = "Unidade";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["UNIDADE"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["DATA_CADASTRO"] = "Data de Cadastro";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["DATA_CADASTRO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["PRECOCUSTO"] = "Preço de Custo";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["PRECOCUSTO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["PRECOVENDA"] = "Preço de Venda";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["PRECOVENDA"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["ESTOQUE"] = "Estoque";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["ESTOQUE"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["ESTOQUEMINIMO"] = "Estoque Mínimo";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["ESTOQUEMINIMO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODALIQUOTA"] = "Alíquota";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODALIQUOTA"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["LOCALICAZAO"] = "Localização";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["LOCALICAZAO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["PESO"] = "Peso";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["PESO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CST"] = "CST";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CST"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CLASSIFICACAO_FISCAL"] = "Class. Fiscal";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CLASSIFICACAO_FISCAL"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["NCM"] = "NCM";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["NCM"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["SITUACAO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["TAMANHO"] = "Tamanho";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["TAMANHO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["COR"] = "Cor";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["COR"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CSOSN"] = "CSOSN";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CSOSN"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["NOTAFISCAL"] = "Nota Fiscal";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["NOTAFISCAL"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["VALIDADE"] = "Validade";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["VALIDADE"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODGRUPO"] = "Grupo";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODGRUPO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODSUBGRUPO"] = "Sub Grupo";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODSUBGRUPO"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODFORNECEDOR"] = "Fornecedor";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODFORNECEDOR"] = "";
	$fieldLabelsC000025["Portuguese(Brazil)"]["CODMARCA"] = "Marca";
	$fieldToolTipsC000025["Portuguese(Brazil)"]["CODMARCA"] = "";
	if (count($fieldToolTipsC000025["Portuguese(Brazil)"]))
		$tdataC000025[".isUseToolTips"] = true;
}
	
	
	$tdataC000025[".NCSearch"] = true;



$tdataC000025[".shortTableName"] = "C000025";
$tdataC000025[".nSecOptions"] = 0;
$tdataC000025[".recsPerRowList"] = 1;
$tdataC000025[".mainTableOwnerID"] = "";
$tdataC000025[".moveNext"] = 1;
$tdataC000025[".nType"] = 0;

$tdataC000025[".strOriginalTableName"] = "C000025";




$tdataC000025[".showAddInPopup"] = false;

$tdataC000025[".showEditInPopup"] = false;

$tdataC000025[".showViewInPopup"] = false;

$tdataC000025[".fieldsForRegister"] = array();

$tdataC000025[".listAjax"] = false;

	$tdataC000025[".audit"] = false;

	$tdataC000025[".locking"] = false;

$tdataC000025[".listIcons"] = true;
$tdataC000025[".inlineEdit"] = true;
$tdataC000025[".inlineAdd"] = true;
$tdataC000025[".view"] = true;

$tdataC000025[".exportTo"] = true;

$tdataC000025[".printFriendly"] = true;


$tdataC000025[".showSimpleSearchOptions"] = false;

$tdataC000025[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000025[".isUseAjaxSuggest"] = false;
else 
	$tdataC000025[".isUseAjaxSuggest"] = true;

$tdataC000025[".rowHighlite"] = true;

// button handlers file names

$tdataC000025[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000025[".isUseTimeForSearch"] = false;




$tdataC000025[".allSearchFields"] = array();

$tdataC000025[".allSearchFields"][] = "CODIGO";
$tdataC000025[".allSearchFields"][] = "CODBARRA";
$tdataC000025[".allSearchFields"][] = "PRODUTO";
$tdataC000025[".allSearchFields"][] = "NOTAFISCAL";
$tdataC000025[".allSearchFields"][] = "VALIDADE";
$tdataC000025[".allSearchFields"][] = "CODGRUPO";
$tdataC000025[".allSearchFields"][] = "CODSUBGRUPO";
$tdataC000025[".allSearchFields"][] = "CODFORNECEDOR";
$tdataC000025[".allSearchFields"][] = "CODMARCA";

$tdataC000025[".googleLikeFields"][] = "CODIGO";
$tdataC000025[".googleLikeFields"][] = "CODBARRA";
$tdataC000025[".googleLikeFields"][] = "PRODUTO";
$tdataC000025[".googleLikeFields"][] = "UNIDADE";
$tdataC000025[".googleLikeFields"][] = "DATA_CADASTRO";
$tdataC000025[".googleLikeFields"][] = "PRECOCUSTO";
$tdataC000025[".googleLikeFields"][] = "PRECOVENDA";
$tdataC000025[".googleLikeFields"][] = "ESTOQUE";
$tdataC000025[".googleLikeFields"][] = "ESTOQUEMINIMO";
$tdataC000025[".googleLikeFields"][] = "CODALIQUOTA";
$tdataC000025[".googleLikeFields"][] = "LOCALICAZAO";
$tdataC000025[".googleLikeFields"][] = "PESO";
$tdataC000025[".googleLikeFields"][] = "CST";
$tdataC000025[".googleLikeFields"][] = "TAMANHO";
$tdataC000025[".googleLikeFields"][] = "COR";
$tdataC000025[".googleLikeFields"][] = "CSOSN";
$tdataC000025[".googleLikeFields"][] = "SITUACAO";
$tdataC000025[".googleLikeFields"][] = "NOTAFISCAL";
$tdataC000025[".googleLikeFields"][] = "VALIDADE";
$tdataC000025[".googleLikeFields"][] = "CLASSIFICACAO_FISCAL";
$tdataC000025[".googleLikeFields"][] = "NCM";
$tdataC000025[".googleLikeFields"][] = "CODGRUPO";
$tdataC000025[".googleLikeFields"][] = "CODSUBGRUPO";
$tdataC000025[".googleLikeFields"][] = "CODFORNECEDOR";
$tdataC000025[".googleLikeFields"][] = "CODMARCA";


$tdataC000025[".advSearchFields"][] = "CODIGO";
$tdataC000025[".advSearchFields"][] = "CODBARRA";
$tdataC000025[".advSearchFields"][] = "PRODUTO";
$tdataC000025[".advSearchFields"][] = "NOTAFISCAL";
$tdataC000025[".advSearchFields"][] = "VALIDADE";
$tdataC000025[".advSearchFields"][] = "CODGRUPO";
$tdataC000025[".advSearchFields"][] = "CODSUBGRUPO";
$tdataC000025[".advSearchFields"][] = "CODFORNECEDOR";
$tdataC000025[".advSearchFields"][] = "CODMARCA";

$tdataC000025[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000025[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000025[".strOrderBy"] = $tstrOrderBy;

$tdataC000025[".orderindexes"] = array();

$tdataC000025[".sqlHead"] = "SELECT CODIGO,  CODBARRA,  PRODUTO,  UNIDADE,  DATA_CADASTRO,  PRECOCUSTO,  PRECOVENDA,  ESTOQUE,  ESTOQUEMINIMO,  CODALIQUOTA,  LOCALICAZAO,  PESO,  CST,  TAMANHO,  COR,  CSOSN,  SITUACAO,  NOTAFISCAL,  VALIDADE,  CLASSIFICACAO_FISCAL,  NCM,  CODGRUPO,  CODSUBGRUPO,  CODFORNECEDOR,  CODMARCA";
$tdataC000025[".sqlFrom"] = "FROM C000025";
$tdataC000025[".sqlWhereExpr"] = "";
$tdataC000025[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000025[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000025[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000025 = array();
$tableKeysC000025[] = "CODIGO";
$tdataC000025[".Keys"] = $tableKeysC000025;

$tdataC000025[".listFields"] = array();
$tdataC000025[".listFields"][] = "CODIGO";
$tdataC000025[".listFields"][] = "CODBARRA";
$tdataC000025[".listFields"][] = "PRODUTO";
$tdataC000025[".listFields"][] = "SITUACAO";
$tdataC000025[".listFields"][] = "NOTAFISCAL";
$tdataC000025[".listFields"][] = "CODGRUPO";
$tdataC000025[".listFields"][] = "CODSUBGRUPO";
$tdataC000025[".listFields"][] = "CODFORNECEDOR";
$tdataC000025[".listFields"][] = "CODMARCA";
$tdataC000025[".listFields"][] = "UNIDADE";
$tdataC000025[".listFields"][] = "VALIDADE";

$tdataC000025[".viewFields"] = array();
$tdataC000025[".viewFields"][] = "CODIGO";
$tdataC000025[".viewFields"][] = "SITUACAO";
$tdataC000025[".viewFields"][] = "CODBARRA";
$tdataC000025[".viewFields"][] = "PRODUTO";
$tdataC000025[".viewFields"][] = "UNIDADE";
$tdataC000025[".viewFields"][] = "CODGRUPO";
$tdataC000025[".viewFields"][] = "CODSUBGRUPO";
$tdataC000025[".viewFields"][] = "CODFORNECEDOR";
$tdataC000025[".viewFields"][] = "CODMARCA";
$tdataC000025[".viewFields"][] = "DATA_CADASTRO";
$tdataC000025[".viewFields"][] = "PRECOCUSTO";
$tdataC000025[".viewFields"][] = "PRECOVENDA";
$tdataC000025[".viewFields"][] = "ESTOQUE";
$tdataC000025[".viewFields"][] = "ESTOQUEMINIMO";
$tdataC000025[".viewFields"][] = "CODALIQUOTA";
$tdataC000025[".viewFields"][] = "LOCALICAZAO";
$tdataC000025[".viewFields"][] = "PESO";
$tdataC000025[".viewFields"][] = "CST";
$tdataC000025[".viewFields"][] = "TAMANHO";
$tdataC000025[".viewFields"][] = "COR";
$tdataC000025[".viewFields"][] = "CSOSN";
$tdataC000025[".viewFields"][] = "NOTAFISCAL";
$tdataC000025[".viewFields"][] = "VALIDADE";
$tdataC000025[".viewFields"][] = "CLASSIFICACAO_FISCAL";
$tdataC000025[".viewFields"][] = "NCM";

$tdataC000025[".addFields"] = array();

$tdataC000025[".inlineAddFields"] = array();
$tdataC000025[".inlineAddFields"][] = "CODIGO";
$tdataC000025[".inlineAddFields"][] = "CODBARRA";
$tdataC000025[".inlineAddFields"][] = "PRODUTO";
$tdataC000025[".inlineAddFields"][] = "NOTAFISCAL";
$tdataC000025[".inlineAddFields"][] = "CODGRUPO";
$tdataC000025[".inlineAddFields"][] = "CODSUBGRUPO";
$tdataC000025[".inlineAddFields"][] = "CODFORNECEDOR";
$tdataC000025[".inlineAddFields"][] = "CODMARCA";
$tdataC000025[".inlineAddFields"][] = "UNIDADE";
$tdataC000025[".inlineAddFields"][] = "VALIDADE";

$tdataC000025[".editFields"] = array();

$tdataC000025[".inlineEditFields"] = array();
$tdataC000025[".inlineEditFields"][] = "CODIGO";
$tdataC000025[".inlineEditFields"][] = "CODBARRA";
$tdataC000025[".inlineEditFields"][] = "PRODUTO";
$tdataC000025[".inlineEditFields"][] = "NOTAFISCAL";
$tdataC000025[".inlineEditFields"][] = "CODGRUPO";
$tdataC000025[".inlineEditFields"][] = "CODSUBGRUPO";
$tdataC000025[".inlineEditFields"][] = "CODFORNECEDOR";
$tdataC000025[".inlineEditFields"][] = "CODMARCA";
$tdataC000025[".inlineEditFields"][] = "UNIDADE";
$tdataC000025[".inlineEditFields"][] = "VALIDADE";

$tdataC000025[".exportFields"] = array();
$tdataC000025[".exportFields"][] = "CODIGO";
$tdataC000025[".exportFields"][] = "CODBARRA";
$tdataC000025[".exportFields"][] = "PRODUTO";
$tdataC000025[".exportFields"][] = "SITUACAO";
$tdataC000025[".exportFields"][] = "UNIDADE";
$tdataC000025[".exportFields"][] = "CODGRUPO";
$tdataC000025[".exportFields"][] = "DATA_CADASTRO";
$tdataC000025[".exportFields"][] = "CODSUBGRUPO";
$tdataC000025[".exportFields"][] = "CODFORNECEDOR";
$tdataC000025[".exportFields"][] = "PRECOCUSTO";
$tdataC000025[".exportFields"][] = "PRECOVENDA";
$tdataC000025[".exportFields"][] = "ESTOQUE";
$tdataC000025[".exportFields"][] = "ESTOQUEMINIMO";
$tdataC000025[".exportFields"][] = "CODALIQUOTA";
$tdataC000025[".exportFields"][] = "LOCALICAZAO";
$tdataC000025[".exportFields"][] = "PESO";
$tdataC000025[".exportFields"][] = "CST";
$tdataC000025[".exportFields"][] = "TAMANHO";
$tdataC000025[".exportFields"][] = "COR";
$tdataC000025[".exportFields"][] = "CSOSN";
$tdataC000025[".exportFields"][] = "NOTAFISCAL";
$tdataC000025[".exportFields"][] = "VALIDADE";
$tdataC000025[".exportFields"][] = "CLASSIFICACAO_FISCAL";
$tdataC000025[".exportFields"][] = "NCM";
$tdataC000025[".exportFields"][] = "CODMARCA";

$tdataC000025[".printFields"] = array();
$tdataC000025[".printFields"][] = "CODIGO";
$tdataC000025[".printFields"][] = "CODBARRA";
$tdataC000025[".printFields"][] = "PRODUTO";
$tdataC000025[".printFields"][] = "SITUACAO";
$tdataC000025[".printFields"][] = "CODGRUPO";
$tdataC000025[".printFields"][] = "CODSUBGRUPO";
$tdataC000025[".printFields"][] = "CODMARCA";
$tdataC000025[".printFields"][] = "UNIDADE";
$tdataC000025[".printFields"][] = "DATA_CADASTRO";
$tdataC000025[".printFields"][] = "PRECOCUSTO";
$tdataC000025[".printFields"][] = "PRECOVENDA";
$tdataC000025[".printFields"][] = "ESTOQUE";
$tdataC000025[".printFields"][] = "ESTOQUEMINIMO";
$tdataC000025[".printFields"][] = "CODALIQUOTA";
$tdataC000025[".printFields"][] = "LOCALICAZAO";
$tdataC000025[".printFields"][] = "PESO";
$tdataC000025[".printFields"][] = "CST";
$tdataC000025[".printFields"][] = "TAMANHO";
$tdataC000025[".printFields"][] = "COR";
$tdataC000025[".printFields"][] = "CSOSN";
$tdataC000025[".printFields"][] = "NOTAFISCAL";
$tdataC000025[".printFields"][] = "VALIDADE";
$tdataC000025[".printFields"][] = "CLASSIFICACAO_FISCAL";
$tdataC000025[".printFields"][] = "NCM";
$tdataC000025[".printFields"][] = "CODFORNECEDOR";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Código"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
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
	
		
		
	$tdataC000025["CODIGO"] = $fdata;
//	CODBARRA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "CODBARRA";
	$fdata["GoodName"] = "CODBARRA";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Código de Barra"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODBARRA"; 
		$fdata["FullName"] = "CODBARRA";
	
		
		
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
			$edata["EditParams"].= " maxlength=13";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["CODBARRA"] = $fdata;
//	PRODUTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "PRODUTO";
	$fdata["GoodName"] = "PRODUTO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Produto"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "PRODUTO"; 
		$fdata["FullName"] = "PRODUTO";
	
		
		
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
	
		
		
	$tdataC000025["PRODUTO"] = $fdata;
//	UNIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "UNIDADE";
	$fdata["GoodName"] = "UNIDADE";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Unidade"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "UNIDADE"; 
		$fdata["FullName"] = "UNIDADE";
	
		
		
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
			$edata["EditParams"].= " maxlength=2";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["UNIDADE"] = $fdata;
//	DATA_CADASTRO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "DATA_CADASTRO";
	$fdata["GoodName"] = "DATA_CADASTRO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Data de Cadastro"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_CADASTRO"; 
		$fdata["FullName"] = "DATA_CADASTRO";
	
		
		
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
	
		
		
	$tdataC000025["DATA_CADASTRO"] = $fdata;
//	PRECOCUSTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "PRECOCUSTO";
	$fdata["GoodName"] = "PRECOCUSTO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Preço de Custo"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "PRECOCUSTO"; 
		$fdata["FullName"] = "PRECOCUSTO";
	
		
		
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
	
		
		
	$tdataC000025["PRECOCUSTO"] = $fdata;
//	PRECOVENDA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "PRECOVENDA";
	$fdata["GoodName"] = "PRECOVENDA";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Preço de Venda"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "PRECOVENDA"; 
		$fdata["FullName"] = "PRECOVENDA";
	
		
		
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
	
		
		
	$tdataC000025["PRECOVENDA"] = $fdata;
//	ESTOQUE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "ESTOQUE";
	$fdata["GoodName"] = "ESTOQUE";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Estoque"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ESTOQUE"; 
		$fdata["FullName"] = "ESTOQUE";
	
		
		
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
	
		
		
	$tdataC000025["ESTOQUE"] = $fdata;
//	ESTOQUEMINIMO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "ESTOQUEMINIMO";
	$fdata["GoodName"] = "ESTOQUEMINIMO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Estoque Mínimo"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ESTOQUEMINIMO"; 
		$fdata["FullName"] = "ESTOQUEMINIMO";
	
		
		
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
	
		
		
	$tdataC000025["ESTOQUEMINIMO"] = $fdata;
//	CODALIQUOTA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "CODALIQUOTA";
	$fdata["GoodName"] = "CODALIQUOTA";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Alíquota"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODALIQUOTA"; 
		$fdata["FullName"] = "CODALIQUOTA";
	
		
		
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
			$edata["EditParams"].= " maxlength=5";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["CODALIQUOTA"] = $fdata;
//	LOCALICAZAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "LOCALICAZAO";
	$fdata["GoodName"] = "LOCALICAZAO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Localização"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "LOCALICAZAO"; 
		$fdata["FullName"] = "LOCALICAZAO";
	
		
		
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
	
		
		
	$tdataC000025["LOCALICAZAO"] = $fdata;
//	PESO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "PESO";
	$fdata["GoodName"] = "PESO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Peso"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "PESO"; 
		$fdata["FullName"] = "PESO";
	
		
		
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
	
		
		
	$tdataC000025["PESO"] = $fdata;
//	CST
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "CST";
	$fdata["GoodName"] = "CST";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "CST"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CST"; 
		$fdata["FullName"] = "CST";
	
		
		
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
	
		
		
	$tdataC000025["CST"] = $fdata;
//	TAMANHO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "TAMANHO";
	$fdata["GoodName"] = "TAMANHO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Tamanho"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TAMANHO"; 
		$fdata["FullName"] = "TAMANHO";
	
		
		
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
	
		
		
	$tdataC000025["TAMANHO"] = $fdata;
//	COR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "COR";
	$fdata["GoodName"] = "COR";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Cor"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COR"; 
		$fdata["FullName"] = "COR";
	
		
		
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
	
		
		
	$tdataC000025["COR"] = $fdata;
//	CSOSN
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "CSOSN";
	$fdata["GoodName"] = "CSOSN";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "CSOSN"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CSOSN"; 
		$fdata["FullName"] = "CSOSN";
	
		
		
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
	
		
		
	$tdataC000025["CSOSN"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Situação"; 
	$fdata["FieldType"] = 3;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
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
	
		
		
	$tdataC000025["SITUACAO"] = $fdata;
//	NOTAFISCAL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "NOTAFISCAL";
	$fdata["GoodName"] = "NOTAFISCAL";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Nota Fiscal"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
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
			
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["NOTAFISCAL"] = $fdata;
//	VALIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "VALIDADE";
	$fdata["GoodName"] = "VALIDADE";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Validade"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "VALIDADE"; 
		$fdata["FullName"] = "VALIDADE";
	
		
		
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
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["VALIDADE"] = $fdata;
//	CLASSIFICACAO_FISCAL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "CLASSIFICACAO_FISCAL";
	$fdata["GoodName"] = "CLASSIFICACAO_FISCAL";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Class. Fiscal"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CLASSIFICACAO_FISCAL"; 
		$fdata["FullName"] = "CLASSIFICACAO_FISCAL";
	
		
		
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
	
		
		
	$tdataC000025["CLASSIFICACAO_FISCAL"] = $fdata;
//	NCM
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 21;
	$fdata["strName"] = "NCM";
	$fdata["GoodName"] = "NCM";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "NCM"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "NCM"; 
		$fdata["FullName"] = "NCM";
	
		
		
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
	
		
		
	$tdataC000025["NCM"] = $fdata;
//	CODGRUPO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 22;
	$fdata["strName"] = "CODGRUPO";
	$fdata["GoodName"] = "CODGRUPO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Grupo"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODGRUPO"; 
		$fdata["FullName"] = "CODGRUPO";
	
		
		
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
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["CODGRUPO"] = $fdata;
//	CODSUBGRUPO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 23;
	$fdata["strName"] = "CODSUBGRUPO";
	$fdata["GoodName"] = "CODSUBGRUPO";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Sub Grupo"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODSUBGRUPO"; 
		$fdata["FullName"] = "CODSUBGRUPO";
	
		
		
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
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["CODSUBGRUPO"] = $fdata;
//	CODFORNECEDOR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 24;
	$fdata["strName"] = "CODFORNECEDOR";
	$fdata["GoodName"] = "CODFORNECEDOR";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Fornecedor"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
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
			
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["CODFORNECEDOR"] = $fdata;
//	CODMARCA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 25;
	$fdata["strName"] = "CODMARCA";
	$fdata["GoodName"] = "CODMARCA";
	$fdata["ownerTable"] = "C000025";
	$fdata["Label"] = "Marca"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CODMARCA"; 
		$fdata["FullName"] = "CODMARCA";
	
		
		
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
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000025["CODMARCA"] = $fdata;

	
$tables_data["C000025"]=&$tdataC000025;
$field_labels["C000025"] = &$fieldLabelsC000025;
$fieldToolTips["C000025"] = &$fieldToolTipsC000025;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000025"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000025"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000025()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  CODBARRA,  PRODUTO,  UNIDADE,  DATA_CADASTRO,  PRECOCUSTO,  PRECOVENDA,  ESTOQUE,  ESTOQUEMINIMO,  CODALIQUOTA,  LOCALICAZAO,  PESO,  CST,  TAMANHO,  COR,  CSOSN,  SITUACAO,  NOTAFISCAL,  VALIDADE,  CLASSIFICACAO_FISCAL,  NCM,  CODGRUPO,  CODSUBGRUPO,  CODFORNECEDOR,  CODMARCA";
$proto0["m_strFrom"] = "FROM C000025";
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
	"m_strTable" => "C000025"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "CODBARRA",
	"m_strTable" => "C000025"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "PRODUTO",
	"m_strTable" => "C000025"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "UNIDADE",
	"m_strTable" => "C000025"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_CADASTRO",
	"m_strTable" => "C000025"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "PRECOCUSTO",
	"m_strTable" => "C000025"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "PRECOVENDA",
	"m_strTable" => "C000025"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "ESTOQUE",
	"m_strTable" => "C000025"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "ESTOQUEMINIMO",
	"m_strTable" => "C000025"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "CODALIQUOTA",
	"m_strTable" => "C000025"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "LOCALICAZAO",
	"m_strTable" => "C000025"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "PESO",
	"m_strTable" => "C000025"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "CST",
	"m_strTable" => "C000025"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "TAMANHO",
	"m_strTable" => "C000025"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "COR",
	"m_strTable" => "C000025"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "CSOSN",
	"m_strTable" => "C000025"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000025"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "NOTAFISCAL",
	"m_strTable" => "C000025"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "VALIDADE",
	"m_strTable" => "C000025"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "CLASSIFICACAO_FISCAL",
	"m_strTable" => "C000025"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
						$proto45=array();
			$obj = new SQLField(array(
	"m_strName" => "NCM",
	"m_strTable" => "C000025"
));

$proto45["m_expr"]=$obj;
$proto45["m_alias"] = "";
$obj = new SQLFieldListItem($proto45);

$proto0["m_fieldlist"][]=$obj;
						$proto47=array();
			$obj = new SQLField(array(
	"m_strName" => "CODGRUPO",
	"m_strTable" => "C000025"
));

$proto47["m_expr"]=$obj;
$proto47["m_alias"] = "";
$obj = new SQLFieldListItem($proto47);

$proto0["m_fieldlist"][]=$obj;
						$proto49=array();
			$obj = new SQLField(array(
	"m_strName" => "CODSUBGRUPO",
	"m_strTable" => "C000025"
));

$proto49["m_expr"]=$obj;
$proto49["m_alias"] = "";
$obj = new SQLFieldListItem($proto49);

$proto0["m_fieldlist"][]=$obj;
						$proto51=array();
			$obj = new SQLField(array(
	"m_strName" => "CODFORNECEDOR",
	"m_strTable" => "C000025"
));

$proto51["m_expr"]=$obj;
$proto51["m_alias"] = "";
$obj = new SQLFieldListItem($proto51);

$proto0["m_fieldlist"][]=$obj;
						$proto53=array();
			$obj = new SQLField(array(
	"m_strName" => "CODMARCA",
	"m_strTable" => "C000025"
));

$proto53["m_expr"]=$obj;
$proto53["m_alias"] = "";
$obj = new SQLFieldListItem($proto53);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto55=array();
$proto55["m_link"] = "SQLL_MAIN";
			$proto56=array();
$proto56["m_strName"] = "C000025";
$proto56["m_columns"] = array();
$proto56["m_columns"][] = "CODIGO";
$proto56["m_columns"][] = "CODBARRA";
$proto56["m_columns"][] = "PRODUTO";
$proto56["m_columns"][] = "UNIDADE";
$proto56["m_columns"][] = "DATA_CADASTRO";
$proto56["m_columns"][] = "CODGRUPO";
$proto56["m_columns"][] = "CODSUBGRUPO";
$proto56["m_columns"][] = "CODFORNECEDOR";
$proto56["m_columns"][] = "CODMARCA";
$proto56["m_columns"][] = "DATA_ULTIMACOMPRA";
$proto56["m_columns"][] = "NOTAFISCAL";
$proto56["m_columns"][] = "PRECOCUSTO";
$proto56["m_columns"][] = "PRECOVENDA";
$proto56["m_columns"][] = "DATA_ULTIMAVENDA";
$proto56["m_columns"][] = "ESTOQUE";
$proto56["m_columns"][] = "ESTOQUEMINIMO";
$proto56["m_columns"][] = "CODALIQUOTA";
$proto56["m_columns"][] = "APLICACAO";
$proto56["m_columns"][] = "LOCALICAZAO";
$proto56["m_columns"][] = "PESO";
$proto56["m_columns"][] = "VALIDADE";
$proto56["m_columns"][] = "COMISSAO";
$proto56["m_columns"][] = "USA_BALANCA";
$proto56["m_columns"][] = "USA_SERIAL";
$proto56["m_columns"][] = "USA_GRADE";
$proto56["m_columns"][] = "CODRECEITA";
$proto56["m_columns"][] = "FOTO";
$proto56["m_columns"][] = "DATA_ULTIMACOMPRA_ANTERIOR";
$proto56["m_columns"][] = "NOTAFISCAL_ANTERIOR";
$proto56["m_columns"][] = "CODFORNECEDOR_ANTERIOR";
$proto56["m_columns"][] = "PRECOCUSTO_ANTERIOR";
$proto56["m_columns"][] = "PRECOVENDA_ANTERIOR";
$proto56["m_columns"][] = "CUSTOMEDIO";
$proto56["m_columns"][] = "AUTO_APLICACAO";
$proto56["m_columns"][] = "AUTO_COMPLEMENTO";
$proto56["m_columns"][] = "DATA_REMARCACAO_CUSTO";
$proto56["m_columns"][] = "DATA_REMARCACAO_VENDA";
$proto56["m_columns"][] = "PRECO_PROMOCAO";
$proto56["m_columns"][] = "DATA_PROMOCAO";
$proto56["m_columns"][] = "FIM_PROMOCAO";
$proto56["m_columns"][] = "CST";
$proto56["m_columns"][] = "CLASSIFICACAO_FISCAL";
$proto56["m_columns"][] = "NBM";
$proto56["m_columns"][] = "NCM";
$proto56["m_columns"][] = "ALIQUOTA";
$proto56["m_columns"][] = "IPI";
$proto56["m_columns"][] = "REDUCAO";
$proto56["m_columns"][] = "QTDE_EMBALAGEM";
$proto56["m_columns"][] = "TIPO";
$proto56["m_columns"][] = "PESO_LIQUIDO";
$proto56["m_columns"][] = "FARMACIA_CONTROLADO";
$proto56["m_columns"][] = "FARMACIA_APRESENTACAO";
$proto56["m_columns"][] = "FARMACIA_REGISTRO_MEDICAMENTO";
$proto56["m_columns"][] = "FARMACIA_PMC";
$proto56["m_columns"][] = "ULTIMA_ALTERACAO";
$proto56["m_columns"][] = "ULTIMA_CARGA";
$proto56["m_columns"][] = "DATA_INVENTARIO";
$proto56["m_columns"][] = "CUSTO_INVENTARIO";
$proto56["m_columns"][] = "ESTOQUE_INVENTARIO";
$proto56["m_columns"][] = "ESTOQUE_ANTERIOR";
$proto56["m_columns"][] = "PRECOVENDA_NOVO";
$proto56["m_columns"][] = "USA_RENTABILIDADE";
$proto56["m_columns"][] = "QUANTIDADE_MINIMA_FAB";
$proto56["m_columns"][] = "APRESENTACAO";
$proto56["m_columns"][] = "SITUACAO";
$proto56["m_columns"][] = "PRECOVENDA1";
$proto56["m_columns"][] = "PRECOVENDA2";
$proto56["m_columns"][] = "PRECOVENDA3";
$proto56["m_columns"][] = "PRECOVENDA4";
$proto56["m_columns"][] = "PRECOVENDA5";
$proto56["m_columns"][] = "DESCONTO_PRECOVENDA";
$proto56["m_columns"][] = "DATA_INVENTARIO_ATUAL";
$proto56["m_columns"][] = "CUSTO_INVENTARIO_ATUAL";
$proto56["m_columns"][] = "ESTOQUE_INVENTARIO_ATUAL";
$proto56["m_columns"][] = "MARGEM_MINIMA";
$proto56["m_columns"][] = "PISCOFINS";
$proto56["m_columns"][] = "REFERENCIA_FORNECEDOR";
$proto56["m_columns"][] = "COMISSAO1";
$proto56["m_columns"][] = "MARGEM_DESCONTO";
$proto56["m_columns"][] = "TAMANHO";
$proto56["m_columns"][] = "COR";
$proto56["m_columns"][] = "INCIDENCIA_PISCOFINS";
$proto56["m_columns"][] = "VEIC_CHASSI";
$proto56["m_columns"][] = "VEIC_SERIE";
$proto56["m_columns"][] = "VEIC_POTENCIA";
$proto56["m_columns"][] = "VEIC_PESO_LIQUIDO";
$proto56["m_columns"][] = "VEIC_PESO_BRUTO";
$proto56["m_columns"][] = "VEIC_TIPO_COMBUSTIVEL";
$proto56["m_columns"][] = "VEIC_RENAVAM";
$proto56["m_columns"][] = "VEIC_ANO_FABRICACAO";
$proto56["m_columns"][] = "VEIC_ANO_MODELO";
$proto56["m_columns"][] = "VEIC_TIPO";
$proto56["m_columns"][] = "VEIC_TIPO_PINTURA";
$proto56["m_columns"][] = "VEIC_COD_COR";
$proto56["m_columns"][] = "VEIC_COR";
$proto56["m_columns"][] = "VEIC_VIN";
$proto56["m_columns"][] = "VEIC_NUMERO_MOTOR";
$proto56["m_columns"][] = "VEIC_CMKG";
$proto56["m_columns"][] = "VEIC_CM3";
$proto56["m_columns"][] = "VEIC_DISTANCIA_EIXO";
$proto56["m_columns"][] = "VEIC_COD_MARCA";
$proto56["m_columns"][] = "VEIC_ESPECIE";
$proto56["m_columns"][] = "VEIC_CONDICAO";
$proto56["m_columns"][] = "LOTE_FABRICACAO";
$proto56["m_columns"][] = "LOTE_VALIDADE";
$proto56["m_columns"][] = "MARGEM_AGREGADA";
$proto56["m_columns"][] = "CODBARRA_NOVARTIS";
$proto56["m_columns"][] = "FARMACIA_DCB";
$proto56["m_columns"][] = "FARMACIA_ABCFARMA";
$proto56["m_columns"][] = "FARMACIA_APRESENTACAO_CAIXA";
$proto56["m_columns"][] = "FARMACIA_PRINCIPIOATIVO";
$proto56["m_columns"][] = "ULTIMA_COMPRA";
$proto56["m_columns"][] = "FARMACIA_DATAVIGENCIA";
$proto56["m_columns"][] = "FARMACIA_TIPO";
$proto56["m_columns"][] = "USA_COMBUSTIVEL";
$proto56["m_columns"][] = "REFERENCIA";
$proto56["m_columns"][] = "PERDA";
$proto56["m_columns"][] = "COMPOSICAO1";
$proto56["m_columns"][] = "COMPOSICAO2";
$proto56["m_columns"][] = "IAT";
$proto56["m_columns"][] = "IPPT";
$proto56["m_columns"][] = "SITUACAO_TRIBUTARIA";
$proto56["m_columns"][] = "FLAG_SIS";
$proto56["m_columns"][] = "FLAG_ACEITO";
$proto56["m_columns"][] = "FLAG_EST";
$proto56["m_columns"][] = "CSOSN";
$proto56["m_columns"][] = "CODORIGINAL";
$proto56["m_columns"][] = "CUSTO_ATACADO";
$proto56["m_columns"][] = "UNIDADE_ATACADO";
$proto56["m_columns"][] = "QTDE_EMBALAGEMATACADO";
$proto56["m_columns"][] = "PMARGEM1";
$proto56["m_columns"][] = "PMARGEM2";
$proto56["m_columns"][] = "PMARGEM3";
$proto56["m_columns"][] = "PMARGEM4";
$proto56["m_columns"][] = "PMARGEM5";
$proto56["m_columns"][] = "PMARGEMATACADO1";
$proto56["m_columns"][] = "PMARGEMATACADO2";
$proto56["m_columns"][] = "PMARGEMATACADO3";
$proto56["m_columns"][] = "PMARGEMATACADO4";
$proto56["m_columns"][] = "PMARGEMATACADO5";
$proto56["m_columns"][] = "PMARGEMATACADO6";
$proto56["m_columns"][] = "PRECOATACADO1";
$proto56["m_columns"][] = "PRECOATACADO2";
$proto56["m_columns"][] = "PRECOATACADO3";
$proto56["m_columns"][] = "PRECOATACADO4";
$proto56["m_columns"][] = "PRECOATACADO5";
$proto56["m_columns"][] = "IND_CFOP";
$proto56["m_columns"][] = "CFOP_DESC";
$proto56["m_columns"][] = "USA_LOTE";
$proto56["m_columns"][] = "IND_CFOP_VENDA_DENTRO";
$proto56["m_columns"][] = "CODCONTA";
$proto56["m_columns"][] = "IND_CFOP_VENDA_FORA";
$proto56["m_columns"][] = "IND_CFOP_DEVOLUCAO_DENTRO";
$proto56["m_columns"][] = "IND_CFOP_DEVOLUCAO_FORA";
$proto56["m_columns"][] = "IND_CFOP_GARANTIA_DENTRO";
$proto56["m_columns"][] = "IND_CFOP_GARANTIA_FORA";
$proto56["m_columns"][] = "USA_TB_PC";
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
$queryData_C000025 = createSqlQuery_C000025();
																									$tdataC000025[".sqlquery"] = $queryData_C000025;
	
if(isset($tdataC000025["field2"])){
	$tdataC000025["field2"]["LookupTable"] = "carscars_view";
	$tdataC000025["field2"]["LookupOrderBy"] = "name";
	$tdataC000025["field2"]["LookupType"] = 4;
	$tdataC000025["field2"]["LinkField"] = "email";
	$tdataC000025["field2"]["DisplayField"] = "name";
	$tdataC000025[".hasCustomViewField"] = true;
}

$tableEvents["C000025"] = new eventsBase;
$tdataC000025[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000025");

?>