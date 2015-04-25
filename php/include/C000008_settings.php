<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000008 = array();
	$tdataC000008[".NumberOfChars"] = 80; 
	$tdataC000008[".ShortName"] = "C000008";
	$tdataC000008[".OwnerID"] = "";
	$tdataC000008[".OriginalTable"] = "C000008";

//	field labels
$fieldLabelsC000008 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000008["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000008["Portuguese(Brazil)"] = array();
	$fieldLabelsC000008["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["NOME"] = "Nome";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["ENDERECO"] = "Endereço";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["ENDERECO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["BAIRRO"] = "Bairro";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["BAIRRO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["CIDADE"] = "Cidade";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["CIDADE"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["UF"] = "UF";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["UF"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["CEP"] = "CEP";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["CEP"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["TELEFONE"] = "Telefone";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["TELEFONE"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["CELULAR"] = "Celular";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["CELULAR"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["EMAIL"] = "E-mail";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["EMAIL"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["CPF"] = "CPF";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["CPF"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["RG"] = "RG";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["RG"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["FUNCAO"] = "Função";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["FUNCAO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["DATA_ADMISSAO"] = "Data de Admissão";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["DATA_ADMISSAO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["DATA_DEMISSAO"] = "Data de Demissão";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["DATA_DEMISSAO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["SITUACAO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["COMISSAO"] = "Comissão";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["COMISSAO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["OBS1"] = "OBS";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["OBS1"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["NASCIMENTO"] = "Data de Nascimento";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["NASCIMENTO"] = "";
	$fieldLabelsC000008["Portuguese(Brazil)"]["SALARIO"] = "Salário";
	$fieldToolTipsC000008["Portuguese(Brazil)"]["SALARIO"] = "";
	if (count($fieldToolTipsC000008["Portuguese(Brazil)"]))
		$tdataC000008[".isUseToolTips"] = true;
}
	
	
	$tdataC000008[".NCSearch"] = true;



$tdataC000008[".shortTableName"] = "C000008";
$tdataC000008[".nSecOptions"] = 0;
$tdataC000008[".recsPerRowList"] = 1;
$tdataC000008[".mainTableOwnerID"] = "";
$tdataC000008[".moveNext"] = 1;
$tdataC000008[".nType"] = 0;

$tdataC000008[".strOriginalTableName"] = "C000008";




$tdataC000008[".showAddInPopup"] = false;

$tdataC000008[".showEditInPopup"] = false;

$tdataC000008[".showViewInPopup"] = false;

$tdataC000008[".fieldsForRegister"] = array();

$tdataC000008[".listAjax"] = false;

	$tdataC000008[".audit"] = false;

	$tdataC000008[".locking"] = false;

$tdataC000008[".listIcons"] = true;
$tdataC000008[".inlineEdit"] = true;
$tdataC000008[".inlineAdd"] = true;
$tdataC000008[".view"] = true;

$tdataC000008[".exportTo"] = true;

$tdataC000008[".printFriendly"] = true;


$tdataC000008[".showSimpleSearchOptions"] = false;

$tdataC000008[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000008[".isUseAjaxSuggest"] = false;
else 
	$tdataC000008[".isUseAjaxSuggest"] = true;

$tdataC000008[".rowHighlite"] = true;

// button handlers file names

$tdataC000008[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000008[".isUseTimeForSearch"] = false;




$tdataC000008[".allSearchFields"] = array();

$tdataC000008[".allSearchFields"][] = "CODIGO";
$tdataC000008[".allSearchFields"][] = "NOME";
$tdataC000008[".allSearchFields"][] = "CPF";
$tdataC000008[".allSearchFields"][] = "RG";
$tdataC000008[".allSearchFields"][] = "SALARIO";

$tdataC000008[".googleLikeFields"][] = "CODIGO";
$tdataC000008[".googleLikeFields"][] = "NOME";
$tdataC000008[".googleLikeFields"][] = "ENDERECO";
$tdataC000008[".googleLikeFields"][] = "BAIRRO";
$tdataC000008[".googleLikeFields"][] = "CIDADE";
$tdataC000008[".googleLikeFields"][] = "UF";
$tdataC000008[".googleLikeFields"][] = "CEP";
$tdataC000008[".googleLikeFields"][] = "TELEFONE";
$tdataC000008[".googleLikeFields"][] = "CELULAR";
$tdataC000008[".googleLikeFields"][] = "EMAIL";
$tdataC000008[".googleLikeFields"][] = "CPF";
$tdataC000008[".googleLikeFields"][] = "RG";
$tdataC000008[".googleLikeFields"][] = "FUNCAO";
$tdataC000008[".googleLikeFields"][] = "DATA_ADMISSAO";
$tdataC000008[".googleLikeFields"][] = "DATA_DEMISSAO";
$tdataC000008[".googleLikeFields"][] = "SITUACAO";
$tdataC000008[".googleLikeFields"][] = "COMISSAO";
$tdataC000008[".googleLikeFields"][] = "OBS1";
$tdataC000008[".googleLikeFields"][] = "NASCIMENTO";
$tdataC000008[".googleLikeFields"][] = "SALARIO";


$tdataC000008[".advSearchFields"][] = "CODIGO";
$tdataC000008[".advSearchFields"][] = "NOME";
$tdataC000008[".advSearchFields"][] = "CPF";
$tdataC000008[".advSearchFields"][] = "RG";
$tdataC000008[".advSearchFields"][] = "SALARIO";

$tdataC000008[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000008[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000008[".strOrderBy"] = $tstrOrderBy;

$tdataC000008[".orderindexes"] = array();

$tdataC000008[".sqlHead"] = "SELECT CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  TELEFONE,  CELULAR,  EMAIL,  CPF,  RG,  FUNCAO,  DATA_ADMISSAO,  DATA_DEMISSAO,  SITUACAO,  COMISSAO,  OBS1,  NASCIMENTO,  SALARIO";
$tdataC000008[".sqlFrom"] = "FROM C000008";
$tdataC000008[".sqlWhereExpr"] = "";
$tdataC000008[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000008[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000008[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000008 = array();
$tableKeysC000008[] = "CODIGO";
$tdataC000008[".Keys"] = $tableKeysC000008;

$tdataC000008[".listFields"] = array();
$tdataC000008[".listFields"][] = "CODIGO";
$tdataC000008[".listFields"][] = "NOME";
$tdataC000008[".listFields"][] = "ENDERECO";
$tdataC000008[".listFields"][] = "BAIRRO";
$tdataC000008[".listFields"][] = "CIDADE";
$tdataC000008[".listFields"][] = "SALARIO";
$tdataC000008[".listFields"][] = "CPF";
$tdataC000008[".listFields"][] = "RG";

$tdataC000008[".viewFields"] = array();
$tdataC000008[".viewFields"][] = "CODIGO";
$tdataC000008[".viewFields"][] = "NOME";
$tdataC000008[".viewFields"][] = "FUNCAO";
$tdataC000008[".viewFields"][] = "SITUACAO";
$tdataC000008[".viewFields"][] = "ENDERECO";
$tdataC000008[".viewFields"][] = "BAIRRO";
$tdataC000008[".viewFields"][] = "CIDADE";
$tdataC000008[".viewFields"][] = "UF";
$tdataC000008[".viewFields"][] = "CEP";
$tdataC000008[".viewFields"][] = "TELEFONE";
$tdataC000008[".viewFields"][] = "CELULAR";
$tdataC000008[".viewFields"][] = "EMAIL";
$tdataC000008[".viewFields"][] = "CPF";
$tdataC000008[".viewFields"][] = "RG";
$tdataC000008[".viewFields"][] = "DATA_ADMISSAO";
$tdataC000008[".viewFields"][] = "DATA_DEMISSAO";
$tdataC000008[".viewFields"][] = "COMISSAO";
$tdataC000008[".viewFields"][] = "NASCIMENTO";
$tdataC000008[".viewFields"][] = "SALARIO";
$tdataC000008[".viewFields"][] = "OBS1";

$tdataC000008[".addFields"] = array();

$tdataC000008[".inlineAddFields"] = array();
$tdataC000008[".inlineAddFields"][] = "CODIGO";
$tdataC000008[".inlineAddFields"][] = "NOME";
$tdataC000008[".inlineAddFields"][] = "ENDERECO";
$tdataC000008[".inlineAddFields"][] = "SALARIO";
$tdataC000008[".inlineAddFields"][] = "CPF";
$tdataC000008[".inlineAddFields"][] = "RG";

$tdataC000008[".editFields"] = array();

$tdataC000008[".inlineEditFields"] = array();
$tdataC000008[".inlineEditFields"][] = "CODIGO";
$tdataC000008[".inlineEditFields"][] = "NOME";
$tdataC000008[".inlineEditFields"][] = "ENDERECO";
$tdataC000008[".inlineEditFields"][] = "SALARIO";
$tdataC000008[".inlineEditFields"][] = "CPF";
$tdataC000008[".inlineEditFields"][] = "RG";

$tdataC000008[".exportFields"] = array();
$tdataC000008[".exportFields"][] = "CODIGO";
$tdataC000008[".exportFields"][] = "NOME";
$tdataC000008[".exportFields"][] = "ENDERECO";
$tdataC000008[".exportFields"][] = "BAIRRO";
$tdataC000008[".exportFields"][] = "CIDADE";
$tdataC000008[".exportFields"][] = "UF";
$tdataC000008[".exportFields"][] = "CEP";
$tdataC000008[".exportFields"][] = "TELEFONE";
$tdataC000008[".exportFields"][] = "CELULAR";
$tdataC000008[".exportFields"][] = "EMAIL";
$tdataC000008[".exportFields"][] = "CPF";
$tdataC000008[".exportFields"][] = "RG";
$tdataC000008[".exportFields"][] = "FUNCAO";
$tdataC000008[".exportFields"][] = "DATA_ADMISSAO";
$tdataC000008[".exportFields"][] = "DATA_DEMISSAO";
$tdataC000008[".exportFields"][] = "SITUACAO";
$tdataC000008[".exportFields"][] = "COMISSAO";
$tdataC000008[".exportFields"][] = "NASCIMENTO";
$tdataC000008[".exportFields"][] = "SALARIO";
$tdataC000008[".exportFields"][] = "OBS1";

$tdataC000008[".printFields"] = array();
$tdataC000008[".printFields"][] = "CODIGO";
$tdataC000008[".printFields"][] = "SITUACAO";
$tdataC000008[".printFields"][] = "NOME";
$tdataC000008[".printFields"][] = "ENDERECO";
$tdataC000008[".printFields"][] = "BAIRRO";
$tdataC000008[".printFields"][] = "CIDADE";
$tdataC000008[".printFields"][] = "UF";
$tdataC000008[".printFields"][] = "CEP";
$tdataC000008[".printFields"][] = "TELEFONE";
$tdataC000008[".printFields"][] = "CELULAR";
$tdataC000008[".printFields"][] = "EMAIL";
$tdataC000008[".printFields"][] = "CPF";
$tdataC000008[".printFields"][] = "RG";
$tdataC000008[".printFields"][] = "FUNCAO";
$tdataC000008[".printFields"][] = "DATA_ADMISSAO";
$tdataC000008[".printFields"][] = "DATA_DEMISSAO";
$tdataC000008[".printFields"][] = "COMISSAO";
$tdataC000008[".printFields"][] = "OBS1";
$tdataC000008[".printFields"][] = "NASCIMENTO";
$tdataC000008[".printFields"][] = "SALARIO";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000008";
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
	
		
		
	$tdataC000008["CODIGO"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Nome"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
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
	
		
		
	$tdataC000008["NOME"] = $fdata;
//	ENDERECO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "ENDERECO";
	$fdata["GoodName"] = "ENDERECO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Endereço"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ENDERECO"; 
		$fdata["FullName"] = "ENDERECO";
	
		
		
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
	
		
		
	$tdataC000008["ENDERECO"] = $fdata;
//	BAIRRO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "BAIRRO";
	$fdata["GoodName"] = "BAIRRO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Bairro"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "BAIRRO"; 
		$fdata["FullName"] = "BAIRRO";
	
		
		
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
			$edata["EditParams"].= " maxlength=30";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000008["BAIRRO"] = $fdata;
//	CIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "CIDADE";
	$fdata["GoodName"] = "CIDADE";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Cidade"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CIDADE"; 
		$fdata["FullName"] = "CIDADE";
	
		
		
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
			$edata["EditParams"].= " maxlength=40";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000008["CIDADE"] = $fdata;
//	UF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "UF";
	$fdata["GoodName"] = "UF";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "UF"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "UF"; 
		$fdata["FullName"] = "UF";
	
		
		
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
	
		
		
	$tdataC000008["UF"] = $fdata;
//	CEP
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "CEP";
	$fdata["GoodName"] = "CEP";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "CEP"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CEP"; 
		$fdata["FullName"] = "CEP";
	
		
		
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
	
		
		
	$tdataC000008["CEP"] = $fdata;
//	TELEFONE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "TELEFONE";
	$fdata["GoodName"] = "TELEFONE";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Telefone"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TELEFONE"; 
		$fdata["FullName"] = "TELEFONE";
	
		
		
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
	
		
		
	$tdataC000008["TELEFONE"] = $fdata;
//	CELULAR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "CELULAR";
	$fdata["GoodName"] = "CELULAR";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Celular"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CELULAR"; 
		$fdata["FullName"] = "CELULAR";
	
		
		
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
	
		
		
	$tdataC000008["CELULAR"] = $fdata;
//	EMAIL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "EMAIL";
	$fdata["GoodName"] = "EMAIL";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "E-mail"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
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
			$edata["EditParams"].= " maxlength=50";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000008["EMAIL"] = $fdata;
//	CPF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "CPF";
	$fdata["GoodName"] = "CPF";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "CPF"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CPF"; 
		$fdata["FullName"] = "CPF";
	
		
		
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
	
		
		
	$tdataC000008["CPF"] = $fdata;
//	RG
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "RG";
	$fdata["GoodName"] = "RG";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "RG"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "RG"; 
		$fdata["FullName"] = "RG";
	
		
		
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
			$edata["EditParams"].= " maxlength=25";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000008["RG"] = $fdata;
//	FUNCAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "FUNCAO";
	$fdata["GoodName"] = "FUNCAO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Função"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "FUNCAO"; 
		$fdata["FullName"] = "FUNCAO";
	
		
		
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
			$edata["EditParams"].= " maxlength=30";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000008["FUNCAO"] = $fdata;
//	DATA_ADMISSAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "DATA_ADMISSAO";
	$fdata["GoodName"] = "DATA_ADMISSAO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Data de Admissão"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_ADMISSAO"; 
		$fdata["FullName"] = "DATA_ADMISSAO";
	
		
		
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
	
		
		
	$tdataC000008["DATA_ADMISSAO"] = $fdata;
//	DATA_DEMISSAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "DATA_DEMISSAO";
	$fdata["GoodName"] = "DATA_DEMISSAO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Data de Demissão"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DATA_DEMISSAO"; 
		$fdata["FullName"] = "DATA_DEMISSAO";
	
		
		
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
	
		
		
	$tdataC000008["DATA_DEMISSAO"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000008";
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
	
		
		
	$tdataC000008["SITUACAO"] = $fdata;
//	COMISSAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "COMISSAO";
	$fdata["GoodName"] = "COMISSAO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Comissão"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COMISSAO"; 
		$fdata["FullName"] = "COMISSAO";
	
		
		
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
	
		
		
	$tdataC000008["COMISSAO"] = $fdata;
//	OBS1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "OBS1";
	$fdata["GoodName"] = "OBS1";
	$fdata["ownerTable"] = "C000008";
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
	
		
		
	$tdataC000008["OBS1"] = $fdata;
//	NASCIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "NASCIMENTO";
	$fdata["GoodName"] = "NASCIMENTO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Data de Nascimento"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "NASCIMENTO"; 
		$fdata["FullName"] = "NASCIMENTO";
	
		
		
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
	
		
		
	$tdataC000008["NASCIMENTO"] = $fdata;
//	SALARIO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "SALARIO";
	$fdata["GoodName"] = "SALARIO";
	$fdata["ownerTable"] = "C000008";
	$fdata["Label"] = "Salário"; 
	$fdata["FieldType"] = 131;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "SALARIO"; 
		$fdata["FullName"] = "SALARIO";
	
		
		
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
	
		
		
	$tdataC000008["SALARIO"] = $fdata;

	
$tables_data["C000008"]=&$tdataC000008;
$field_labels["C000008"] = &$fieldLabelsC000008;
$fieldToolTips["C000008"] = &$fieldToolTipsC000008;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000008"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000008"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000008()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  TELEFONE,  CELULAR,  EMAIL,  CPF,  RG,  FUNCAO,  DATA_ADMISSAO,  DATA_DEMISSAO,  SITUACAO,  COMISSAO,  OBS1,  NASCIMENTO,  SALARIO";
$proto0["m_strFrom"] = "FROM C000008";
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
	"m_strTable" => "C000008"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "C000008"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "ENDERECO",
	"m_strTable" => "C000008"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "BAIRRO",
	"m_strTable" => "C000008"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "CIDADE",
	"m_strTable" => "C000008"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "UF",
	"m_strTable" => "C000008"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "CEP",
	"m_strTable" => "C000008"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE",
	"m_strTable" => "C000008"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "CELULAR",
	"m_strTable" => "C000008"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "EMAIL",
	"m_strTable" => "C000008"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "CPF",
	"m_strTable" => "C000008"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "RG",
	"m_strTable" => "C000008"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "FUNCAO",
	"m_strTable" => "C000008"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_ADMISSAO",
	"m_strTable" => "C000008"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "DATA_DEMISSAO",
	"m_strTable" => "C000008"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000008"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "COMISSAO",
	"m_strTable" => "C000008"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "OBS1",
	"m_strTable" => "C000008"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "NASCIMENTO",
	"m_strTable" => "C000008"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "SALARIO",
	"m_strTable" => "C000008"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto45=array();
$proto45["m_link"] = "SQLL_MAIN";
			$proto46=array();
$proto46["m_strName"] = "C000008";
$proto46["m_columns"] = array();
$proto46["m_columns"][] = "CODIGO";
$proto46["m_columns"][] = "NOME";
$proto46["m_columns"][] = "ENDERECO";
$proto46["m_columns"][] = "BAIRRO";
$proto46["m_columns"][] = "CIDADE";
$proto46["m_columns"][] = "UF";
$proto46["m_columns"][] = "CEP";
$proto46["m_columns"][] = "TELEFONE";
$proto46["m_columns"][] = "CELULAR";
$proto46["m_columns"][] = "EMAIL";
$proto46["m_columns"][] = "CPF";
$proto46["m_columns"][] = "RG";
$proto46["m_columns"][] = "CTPS";
$proto46["m_columns"][] = "FUNCAO";
$proto46["m_columns"][] = "DATA_ADMISSAO";
$proto46["m_columns"][] = "DATA_DEMISSAO";
$proto46["m_columns"][] = "SITUACAO";
$proto46["m_columns"][] = "SALARIO";
$proto46["m_columns"][] = "COMISSAO";
$proto46["m_columns"][] = "OBS1";
$proto46["m_columns"][] = "OBS2";
$proto46["m_columns"][] = "OBS3";
$proto46["m_columns"][] = "NASCIMENTO";
$proto46["m_columns"][] = "F_CAIXA";
$proto46["m_columns"][] = "F_VENDEDOR";
$proto46["m_columns"][] = "F_TECNICO";
$proto46["m_columns"][] = "NUMERO";
$proto46["m_columns"][] = "SENHA";
$obj = new SQLTable($proto46);

$proto45["m_table"] = $obj;
$proto45["m_alias"] = "";
$proto47=array();
$proto47["m_sql"] = "";
$proto47["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto47["m_column"]=$obj;
$proto47["m_contained"] = array();
$proto47["m_strCase"] = "";
$proto47["m_havingmode"] = "0";
$proto47["m_inBrackets"] = "0";
$proto47["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto47);

$proto45["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto45);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000008 = createSqlQuery_C000008();
																				$tdataC000008[".sqlquery"] = $queryData_C000008;
	
if(isset($tdataC000008["field2"])){
	$tdataC000008["field2"]["LookupTable"] = "carscars_view";
	$tdataC000008["field2"]["LookupOrderBy"] = "name";
	$tdataC000008["field2"]["LookupType"] = 4;
	$tdataC000008["field2"]["LinkField"] = "email";
	$tdataC000008["field2"]["DisplayField"] = "name";
	$tdataC000008[".hasCustomViewField"] = true;
}

$tableEvents["C000008"] = new eventsBase;
$tdataC000008[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000008");

?>