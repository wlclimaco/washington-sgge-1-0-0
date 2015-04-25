<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000009 = array();
	$tdataC000009[".NumberOfChars"] = 80; 
	$tdataC000009[".ShortName"] = "C000009";
	$tdataC000009[".OwnerID"] = "";
	$tdataC000009[".OriginalTable"] = "C000009";

//	field labels
$fieldLabelsC000009 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000009["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000009["Portuguese(Brazil)"] = array();
	$fieldLabelsC000009["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["NOME"] = "Empresa";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["FANTASIA"] = "Nome Fantasia";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["FANTASIA"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["ENDERECO"] = "Endereço";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["ENDERECO"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["BAIRRO"] = "Bairro";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["BAIRRO"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CIDADE"] = "Cidade";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CIDADE"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["UF"] = "UF";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["UF"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CEP"] = "CEP";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CEP"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["COMPLEMENTO"] = "Complemento";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["COMPLEMENTO"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["TELEFONE1"] = "Telefone1";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["TELEFONE1"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["TELEFONE2"] = "Telefone2";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["TELEFONE2"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["FAX"] = "FAX";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["FAX"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CONTATO1"] = "Contato1";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CONTATO1"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CONTATO2"] = "Contato2";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CONTATO2"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CELULAR1"] = "Celular1";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CELULAR1"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CELULAR2"] = "Celular2";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CELULAR2"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["EMAIL"] = "E-Mail";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["EMAIL"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["HOMEPAGE"] = "HOMEPAGE";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["HOMEPAGE"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["CNPJ"] = "CNPJ";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["CNPJ"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["IE"] = "IE";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["IE"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["OBS1"] = "OBS";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["OBS1"] = "";
	$fieldLabelsC000009["Portuguese(Brazil)"]["IM"] = "IM";
	$fieldToolTipsC000009["Portuguese(Brazil)"]["IM"] = "";
	if (count($fieldToolTipsC000009["Portuguese(Brazil)"]))
		$tdataC000009[".isUseToolTips"] = true;
}
	
	
	$tdataC000009[".NCSearch"] = true;



$tdataC000009[".shortTableName"] = "C000009";
$tdataC000009[".nSecOptions"] = 0;
$tdataC000009[".recsPerRowList"] = 1;
$tdataC000009[".mainTableOwnerID"] = "";
$tdataC000009[".moveNext"] = 1;
$tdataC000009[".nType"] = 0;

$tdataC000009[".strOriginalTableName"] = "C000009";




$tdataC000009[".showAddInPopup"] = false;

$tdataC000009[".showEditInPopup"] = false;

$tdataC000009[".showViewInPopup"] = false;

$tdataC000009[".fieldsForRegister"] = array();

$tdataC000009[".listAjax"] = false;

	$tdataC000009[".audit"] = false;

	$tdataC000009[".locking"] = false;

$tdataC000009[".listIcons"] = true;
$tdataC000009[".inlineEdit"] = true;
$tdataC000009[".inlineAdd"] = true;
$tdataC000009[".view"] = true;

$tdataC000009[".exportTo"] = true;

$tdataC000009[".printFriendly"] = true;


$tdataC000009[".showSimpleSearchOptions"] = false;

$tdataC000009[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000009[".isUseAjaxSuggest"] = false;
else 
	$tdataC000009[".isUseAjaxSuggest"] = true;

$tdataC000009[".rowHighlite"] = true;

// button handlers file names

$tdataC000009[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000009[".isUseTimeForSearch"] = false;




$tdataC000009[".allSearchFields"] = array();

$tdataC000009[".allSearchFields"][] = "CODIGO";
$tdataC000009[".allSearchFields"][] = "NOME";
$tdataC000009[".allSearchFields"][] = "CNPJ";
$tdataC000009[".allSearchFields"][] = "IE";

$tdataC000009[".googleLikeFields"][] = "CODIGO";
$tdataC000009[".googleLikeFields"][] = "NOME";
$tdataC000009[".googleLikeFields"][] = "FANTASIA";
$tdataC000009[".googleLikeFields"][] = "ENDERECO";
$tdataC000009[".googleLikeFields"][] = "BAIRRO";
$tdataC000009[".googleLikeFields"][] = "CIDADE";
$tdataC000009[".googleLikeFields"][] = "UF";
$tdataC000009[".googleLikeFields"][] = "CEP";
$tdataC000009[".googleLikeFields"][] = "COMPLEMENTO";
$tdataC000009[".googleLikeFields"][] = "TELEFONE1";
$tdataC000009[".googleLikeFields"][] = "TELEFONE2";
$tdataC000009[".googleLikeFields"][] = "FAX";
$tdataC000009[".googleLikeFields"][] = "CONTATO1";
$tdataC000009[".googleLikeFields"][] = "CONTATO2";
$tdataC000009[".googleLikeFields"][] = "CELULAR1";
$tdataC000009[".googleLikeFields"][] = "CELULAR2";
$tdataC000009[".googleLikeFields"][] = "EMAIL";
$tdataC000009[".googleLikeFields"][] = "HOMEPAGE";
$tdataC000009[".googleLikeFields"][] = "CNPJ";
$tdataC000009[".googleLikeFields"][] = "IE";
$tdataC000009[".googleLikeFields"][] = "OBS1";
$tdataC000009[".googleLikeFields"][] = "IM";


$tdataC000009[".advSearchFields"][] = "CODIGO";
$tdataC000009[".advSearchFields"][] = "NOME";
$tdataC000009[".advSearchFields"][] = "CNPJ";
$tdataC000009[".advSearchFields"][] = "IE";

$tdataC000009[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000009[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000009[".strOrderBy"] = $tstrOrderBy;

$tdataC000009[".orderindexes"] = array();

$tdataC000009[".sqlHead"] = "SELECT CODIGO,  NOME,  FANTASIA,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  COMPLEMENTO,  TELEFONE1,  TELEFONE2,  FAX,  CONTATO1,  CONTATO2,  CELULAR1,  CELULAR2,  EMAIL,  HOMEPAGE,  CNPJ,  IE,  OBS1,  IM";
$tdataC000009[".sqlFrom"] = "FROM C000009";
$tdataC000009[".sqlWhereExpr"] = "";
$tdataC000009[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000009[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000009[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000009 = array();
$tableKeysC000009[] = "CODIGO";
$tdataC000009[".Keys"] = $tableKeysC000009;

$tdataC000009[".listFields"] = array();
$tdataC000009[".listFields"][] = "CODIGO";
$tdataC000009[".listFields"][] = "NOME";
$tdataC000009[".listFields"][] = "FANTASIA";
$tdataC000009[".listFields"][] = "CIDADE";
$tdataC000009[".listFields"][] = "UF";
$tdataC000009[".listFields"][] = "CNPJ";
$tdataC000009[".listFields"][] = "IE";

$tdataC000009[".viewFields"] = array();
$tdataC000009[".viewFields"][] = "CODIGO";
$tdataC000009[".viewFields"][] = "NOME";
$tdataC000009[".viewFields"][] = "FANTASIA";
$tdataC000009[".viewFields"][] = "ENDERECO";
$tdataC000009[".viewFields"][] = "BAIRRO";
$tdataC000009[".viewFields"][] = "CIDADE";
$tdataC000009[".viewFields"][] = "UF";
$tdataC000009[".viewFields"][] = "CEP";
$tdataC000009[".viewFields"][] = "COMPLEMENTO";
$tdataC000009[".viewFields"][] = "TELEFONE1";
$tdataC000009[".viewFields"][] = "TELEFONE2";
$tdataC000009[".viewFields"][] = "FAX";
$tdataC000009[".viewFields"][] = "CONTATO1";
$tdataC000009[".viewFields"][] = "CONTATO2";
$tdataC000009[".viewFields"][] = "CELULAR1";
$tdataC000009[".viewFields"][] = "CELULAR2";
$tdataC000009[".viewFields"][] = "EMAIL";
$tdataC000009[".viewFields"][] = "HOMEPAGE";
$tdataC000009[".viewFields"][] = "CNPJ";
$tdataC000009[".viewFields"][] = "IE";
$tdataC000009[".viewFields"][] = "OBS1";
$tdataC000009[".viewFields"][] = "IM";

$tdataC000009[".addFields"] = array();

$tdataC000009[".inlineAddFields"] = array();
$tdataC000009[".inlineAddFields"][] = "CODIGO";
$tdataC000009[".inlineAddFields"][] = "NOME";
$tdataC000009[".inlineAddFields"][] = "FANTASIA";
$tdataC000009[".inlineAddFields"][] = "UF";
$tdataC000009[".inlineAddFields"][] = "CNPJ";

$tdataC000009[".editFields"] = array();

$tdataC000009[".inlineEditFields"] = array();
$tdataC000009[".inlineEditFields"][] = "CODIGO";
$tdataC000009[".inlineEditFields"][] = "NOME";
$tdataC000009[".inlineEditFields"][] = "FANTASIA";
$tdataC000009[".inlineEditFields"][] = "UF";
$tdataC000009[".inlineEditFields"][] = "CNPJ";

$tdataC000009[".exportFields"] = array();
$tdataC000009[".exportFields"][] = "CODIGO";
$tdataC000009[".exportFields"][] = "NOME";
$tdataC000009[".exportFields"][] = "FANTASIA";
$tdataC000009[".exportFields"][] = "ENDERECO";
$tdataC000009[".exportFields"][] = "COMPLEMENTO";
$tdataC000009[".exportFields"][] = "BAIRRO";
$tdataC000009[".exportFields"][] = "CIDADE";
$tdataC000009[".exportFields"][] = "UF";
$tdataC000009[".exportFields"][] = "CEP";
$tdataC000009[".exportFields"][] = "TELEFONE1";
$tdataC000009[".exportFields"][] = "TELEFONE2";
$tdataC000009[".exportFields"][] = "FAX";
$tdataC000009[".exportFields"][] = "CONTATO1";
$tdataC000009[".exportFields"][] = "CONTATO2";
$tdataC000009[".exportFields"][] = "CELULAR1";
$tdataC000009[".exportFields"][] = "CELULAR2";
$tdataC000009[".exportFields"][] = "EMAIL";
$tdataC000009[".exportFields"][] = "HOMEPAGE";
$tdataC000009[".exportFields"][] = "CNPJ";
$tdataC000009[".exportFields"][] = "IE";
$tdataC000009[".exportFields"][] = "IM";
$tdataC000009[".exportFields"][] = "OBS1";

$tdataC000009[".printFields"] = array();
$tdataC000009[".printFields"][] = "CODIGO";
$tdataC000009[".printFields"][] = "NOME";
$tdataC000009[".printFields"][] = "FANTASIA";
$tdataC000009[".printFields"][] = "ENDERECO";
$tdataC000009[".printFields"][] = "BAIRRO";
$tdataC000009[".printFields"][] = "CIDADE";
$tdataC000009[".printFields"][] = "UF";
$tdataC000009[".printFields"][] = "CEP";
$tdataC000009[".printFields"][] = "COMPLEMENTO";
$tdataC000009[".printFields"][] = "TELEFONE1";
$tdataC000009[".printFields"][] = "TELEFONE2";
$tdataC000009[".printFields"][] = "FAX";
$tdataC000009[".printFields"][] = "CONTATO1";
$tdataC000009[".printFields"][] = "CONTATO2";
$tdataC000009[".printFields"][] = "CELULAR1";
$tdataC000009[".printFields"][] = "CELULAR2";
$tdataC000009[".printFields"][] = "EMAIL";
$tdataC000009[".printFields"][] = "HOMEPAGE";
$tdataC000009[".printFields"][] = "CNPJ";
$tdataC000009[".printFields"][] = "IE";
$tdataC000009[".printFields"][] = "IM";
$tdataC000009[".printFields"][] = "OBS1";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000009";
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
	
		
		
	$tdataC000009["CODIGO"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Empresa"; 
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
			$edata["EditParams"].= " maxlength=100";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000009["NOME"] = $fdata;
//	FANTASIA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "FANTASIA";
	$fdata["GoodName"] = "FANTASIA";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Nome Fantasia"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "FANTASIA"; 
		$fdata["FullName"] = "FANTASIA";
	
		
		
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
	
		
		
	$tdataC000009["FANTASIA"] = $fdata;
//	ENDERECO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "ENDERECO";
	$fdata["GoodName"] = "ENDERECO";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Endereço"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000009["ENDERECO"] = $fdata;
//	BAIRRO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "BAIRRO";
	$fdata["GoodName"] = "BAIRRO";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Bairro"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000009["BAIRRO"] = $fdata;
//	CIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "CIDADE";
	$fdata["GoodName"] = "CIDADE";
	$fdata["ownerTable"] = "C000009";
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
	
		
		
	$tdataC000009["CIDADE"] = $fdata;
//	UF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "UF";
	$fdata["GoodName"] = "UF";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "UF"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
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
	
		
		
	$tdataC000009["UF"] = $fdata;
//	CEP
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "CEP";
	$fdata["GoodName"] = "CEP";
	$fdata["ownerTable"] = "C000009";
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
	
		
		
	$tdataC000009["CEP"] = $fdata;
//	COMPLEMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "COMPLEMENTO";
	$fdata["GoodName"] = "COMPLEMENTO";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Complemento"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COMPLEMENTO"; 
		$fdata["FullName"] = "COMPLEMENTO";
	
		
		
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
	
		
		
	$tdataC000009["COMPLEMENTO"] = $fdata;
//	TELEFONE1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "TELEFONE1";
	$fdata["GoodName"] = "TELEFONE1";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Telefone1"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TELEFONE1"; 
		$fdata["FullName"] = "TELEFONE1";
	
		
		
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
	
		
		
	$tdataC000009["TELEFONE1"] = $fdata;
//	TELEFONE2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "TELEFONE2";
	$fdata["GoodName"] = "TELEFONE2";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Telefone2"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TELEFONE2"; 
		$fdata["FullName"] = "TELEFONE2";
	
		
		
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
	
		
		
	$tdataC000009["TELEFONE2"] = $fdata;
//	FAX
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "FAX";
	$fdata["GoodName"] = "FAX";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "FAX"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "FAX"; 
		$fdata["FullName"] = "FAX";
	
		
		
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
	
		
		
	$tdataC000009["FAX"] = $fdata;
//	CONTATO1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "CONTATO1";
	$fdata["GoodName"] = "CONTATO1";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Contato1"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CONTATO1"; 
		$fdata["FullName"] = "CONTATO1";
	
		
		
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
	
		
		
	$tdataC000009["CONTATO1"] = $fdata;
//	CONTATO2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "CONTATO2";
	$fdata["GoodName"] = "CONTATO2";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Contato2"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CONTATO2"; 
		$fdata["FullName"] = "CONTATO2";
	
		
		
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
	
		
		
	$tdataC000009["CONTATO2"] = $fdata;
//	CELULAR1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "CELULAR1";
	$fdata["GoodName"] = "CELULAR1";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Celular1"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CELULAR1"; 
		$fdata["FullName"] = "CELULAR1";
	
		
		
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
	
		
		
	$tdataC000009["CELULAR1"] = $fdata;
//	CELULAR2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "CELULAR2";
	$fdata["GoodName"] = "CELULAR2";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "Celular2"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CELULAR2"; 
		$fdata["FullName"] = "CELULAR2";
	
		
		
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
	
		
		
	$tdataC000009["CELULAR2"] = $fdata;
//	EMAIL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "EMAIL";
	$fdata["GoodName"] = "EMAIL";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "E-Mail"; 
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
			$edata["EditParams"].= " maxlength=80";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000009["EMAIL"] = $fdata;
//	HOMEPAGE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "HOMEPAGE";
	$fdata["GoodName"] = "HOMEPAGE";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "HOMEPAGE"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "HOMEPAGE"; 
		$fdata["FullName"] = "HOMEPAGE";
	
		
		
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
	
		
		
	$tdataC000009["HOMEPAGE"] = $fdata;
//	CNPJ
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "CNPJ";
	$fdata["GoodName"] = "CNPJ";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "CNPJ"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CNPJ"; 
		$fdata["FullName"] = "CNPJ";
	
		
		
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
	
		
		
	$tdataC000009["CNPJ"] = $fdata;
//	IE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "IE";
	$fdata["GoodName"] = "IE";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "IE"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "IE"; 
		$fdata["FullName"] = "IE";
	
		
		
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
	
		
		
	$tdataC000009["IE"] = $fdata;
//	OBS1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 21;
	$fdata["strName"] = "OBS1";
	$fdata["GoodName"] = "OBS1";
	$fdata["ownerTable"] = "C000009";
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
	
		
		
	$tdataC000009["OBS1"] = $fdata;
//	IM
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 22;
	$fdata["strName"] = "IM";
	$fdata["GoodName"] = "IM";
	$fdata["ownerTable"] = "C000009";
	$fdata["Label"] = "IM"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "IM"; 
		$fdata["FullName"] = "IM";
	
		
		
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
	
		
		
	$tdataC000009["IM"] = $fdata;

	
$tables_data["C000009"]=&$tdataC000009;
$field_labels["C000009"] = &$fieldLabelsC000009;
$fieldToolTips["C000009"] = &$fieldToolTipsC000009;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000009"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000009"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000009()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  NOME,  FANTASIA,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  COMPLEMENTO,  TELEFONE1,  TELEFONE2,  FAX,  CONTATO1,  CONTATO2,  CELULAR1,  CELULAR2,  EMAIL,  HOMEPAGE,  CNPJ,  IE,  OBS1,  IM";
$proto0["m_strFrom"] = "FROM C000009";
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
	"m_strTable" => "C000009"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "C000009"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "FANTASIA",
	"m_strTable" => "C000009"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "ENDERECO",
	"m_strTable" => "C000009"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "BAIRRO",
	"m_strTable" => "C000009"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "CIDADE",
	"m_strTable" => "C000009"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "UF",
	"m_strTable" => "C000009"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "CEP",
	"m_strTable" => "C000009"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "COMPLEMENTO",
	"m_strTable" => "C000009"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE1",
	"m_strTable" => "C000009"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE2",
	"m_strTable" => "C000009"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "FAX",
	"m_strTable" => "C000009"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "CONTATO1",
	"m_strTable" => "C000009"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "CONTATO2",
	"m_strTable" => "C000009"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "CELULAR1",
	"m_strTable" => "C000009"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "CELULAR2",
	"m_strTable" => "C000009"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "EMAIL",
	"m_strTable" => "C000009"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "HOMEPAGE",
	"m_strTable" => "C000009"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "CNPJ",
	"m_strTable" => "C000009"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "IE",
	"m_strTable" => "C000009"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
						$proto45=array();
			$obj = new SQLField(array(
	"m_strName" => "OBS1",
	"m_strTable" => "C000009"
));

$proto45["m_expr"]=$obj;
$proto45["m_alias"] = "";
$obj = new SQLFieldListItem($proto45);

$proto0["m_fieldlist"][]=$obj;
						$proto47=array();
			$obj = new SQLField(array(
	"m_strName" => "IM",
	"m_strTable" => "C000009"
));

$proto47["m_expr"]=$obj;
$proto47["m_alias"] = "";
$obj = new SQLFieldListItem($proto47);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto49=array();
$proto49["m_link"] = "SQLL_MAIN";
			$proto50=array();
$proto50["m_strName"] = "C000009";
$proto50["m_columns"] = array();
$proto50["m_columns"][] = "CODIGO";
$proto50["m_columns"][] = "NOME";
$proto50["m_columns"][] = "FANTASIA";
$proto50["m_columns"][] = "ENDERECO";
$proto50["m_columns"][] = "BAIRRO";
$proto50["m_columns"][] = "CIDADE";
$proto50["m_columns"][] = "UF";
$proto50["m_columns"][] = "CEP";
$proto50["m_columns"][] = "COMPLEMENTO";
$proto50["m_columns"][] = "TELEFONE1";
$proto50["m_columns"][] = "TELEFONE2";
$proto50["m_columns"][] = "FAX";
$proto50["m_columns"][] = "CONTATO1";
$proto50["m_columns"][] = "CONTATO2";
$proto50["m_columns"][] = "CELULAR1";
$proto50["m_columns"][] = "CELULAR2";
$proto50["m_columns"][] = "EMAIL";
$proto50["m_columns"][] = "HOMEPAGE";
$proto50["m_columns"][] = "CNPJ";
$proto50["m_columns"][] = "IE";
$proto50["m_columns"][] = "BANCO";
$proto50["m_columns"][] = "AGENCIA";
$proto50["m_columns"][] = "CONTA";
$proto50["m_columns"][] = "OBS1";
$proto50["m_columns"][] = "OBS2";
$proto50["m_columns"][] = "OBS3";
$proto50["m_columns"][] = "DATA";
$proto50["m_columns"][] = "TIPO";
$proto50["m_columns"][] = "ASSISTENCIA_TECNICA";
$proto50["m_columns"][] = "NUMERO";
$proto50["m_columns"][] = "IM";
$proto50["m_columns"][] = "REP_NOME";
$proto50["m_columns"][] = "REP_TELEFONE";
$proto50["m_columns"][] = "REP_ENDERECO";
$proto50["m_columns"][] = "REP_BAIRRO";
$proto50["m_columns"][] = "REP_COMPLEMENTO";
$proto50["m_columns"][] = "REP_CIDADE";
$proto50["m_columns"][] = "REP_UF";
$proto50["m_columns"][] = "REP_CEP";
$proto50["m_columns"][] = "REP_TELEFONE1";
$proto50["m_columns"][] = "REP_TELEFONE2";
$proto50["m_columns"][] = "REP_TELEFONE3";
$proto50["m_columns"][] = "REP_FAX";
$proto50["m_columns"][] = "REP_CNPJ";
$proto50["m_columns"][] = "REP_IE";
$proto50["m_columns"][] = "REP_HOME_PAGE";
$proto50["m_columns"][] = "REP_EMAIL";
$proto50["m_columns"][] = "CNAE";
$proto50["m_columns"][] = "COD_MUNICIPIO_IBGE";
$proto50["m_columns"][] = "IBGE";
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
$queryData_C000009 = createSqlQuery_C000009();
																						$tdataC000009[".sqlquery"] = $queryData_C000009;
	
if(isset($tdataC000009["field2"])){
	$tdataC000009["field2"]["LookupTable"] = "carscars_view";
	$tdataC000009["field2"]["LookupOrderBy"] = "name";
	$tdataC000009["field2"]["LookupType"] = 4;
	$tdataC000009["field2"]["LinkField"] = "email";
	$tdataC000009["field2"]["DisplayField"] = "name";
	$tdataC000009[".hasCustomViewField"] = true;
}

$tableEvents["C000009"] = new eventsBase;
$tdataC000009[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000009");

?>