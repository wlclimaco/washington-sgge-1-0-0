<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000007 = array();
	$tdataC000007[".NumberOfChars"] = 80; 
	$tdataC000007[".ShortName"] = "C000007";
	$tdataC000007[".OwnerID"] = "";
	$tdataC000007[".OriginalTable"] = "C000007";

//	field labels
$fieldLabelsC000007 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000007["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000007["Portuguese(Brazil)"] = array();
	$fieldLabelsC000007["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["NOME"] = "Nome";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["ENDERECO"] = "Endereço";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["ENDERECO"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["BAIRRO"] = "Bairro";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["BAIRRO"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["CIDADE"] = "Cidade";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["CIDADE"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["UF"] = "UF";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["UF"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["CEP"] = "CEP";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["CEP"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["COMPLEMENTO"] = "Complemento";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["COMPLEMENTO"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["TELEFONE1"] = "Telefone1";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["TELEFONE1"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["TELEFONE2"] = "Telefone2";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["TELEFONE2"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["TELEFONE3"] = "Telefone3";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["TELEFONE3"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["CELULAR"] = "Celular";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["CELULAR"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["EMAIL"] = "E-mail";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["EMAIL"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["RG"] = "RG";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["RG"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["CPF"] = "CPF";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["CPF"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["ESTADOCIVIL"] = "Estado Civil";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["ESTADOCIVIL"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["CONJUGE"] = "Conjugue";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["CONJUGE"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["NASCIMENTO"] = "Nascimento";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["NASCIMENTO"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["SEXO"] = "Sexo";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["SEXO"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["OBS1"] = "OBS";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["OBS1"] = "";
	$fieldLabelsC000007["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000007["Portuguese(Brazil)"]["SITUACAO"] = "";
	if (count($fieldToolTipsC000007["Portuguese(Brazil)"]))
		$tdataC000007[".isUseToolTips"] = true;
}
	
	
	$tdataC000007[".NCSearch"] = true;



$tdataC000007[".shortTableName"] = "C000007";
$tdataC000007[".nSecOptions"] = 0;
$tdataC000007[".recsPerRowList"] = 1;
$tdataC000007[".mainTableOwnerID"] = "";
$tdataC000007[".moveNext"] = 1;
$tdataC000007[".nType"] = 0;

$tdataC000007[".strOriginalTableName"] = "C000007";




$tdataC000007[".showAddInPopup"] = false;

$tdataC000007[".showEditInPopup"] = false;

$tdataC000007[".showViewInPopup"] = false;

$tdataC000007[".fieldsForRegister"] = array();

$tdataC000007[".listAjax"] = false;

	$tdataC000007[".audit"] = false;

	$tdataC000007[".locking"] = false;

$tdataC000007[".listIcons"] = true;
$tdataC000007[".inlineEdit"] = true;
$tdataC000007[".inlineAdd"] = true;
$tdataC000007[".view"] = true;

$tdataC000007[".exportTo"] = true;

$tdataC000007[".printFriendly"] = true;


$tdataC000007[".showSimpleSearchOptions"] = false;

$tdataC000007[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000007[".isUseAjaxSuggest"] = false;
else 
	$tdataC000007[".isUseAjaxSuggest"] = true;

$tdataC000007[".rowHighlite"] = true;

// button handlers file names

$tdataC000007[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000007[".isUseTimeForSearch"] = false;




$tdataC000007[".allSearchFields"] = array();

$tdataC000007[".allSearchFields"][] = "CODIGO";
$tdataC000007[".allSearchFields"][] = "NOME";
$tdataC000007[".allSearchFields"][] = "RG";
$tdataC000007[".allSearchFields"][] = "CPF";
$tdataC000007[".allSearchFields"][] = "SITUACAO";

$tdataC000007[".googleLikeFields"][] = "CODIGO";
$tdataC000007[".googleLikeFields"][] = "NOME";
$tdataC000007[".googleLikeFields"][] = "ENDERECO";
$tdataC000007[".googleLikeFields"][] = "BAIRRO";
$tdataC000007[".googleLikeFields"][] = "CIDADE";
$tdataC000007[".googleLikeFields"][] = "UF";
$tdataC000007[".googleLikeFields"][] = "CEP";
$tdataC000007[".googleLikeFields"][] = "COMPLEMENTO";
$tdataC000007[".googleLikeFields"][] = "TELEFONE1";
$tdataC000007[".googleLikeFields"][] = "TELEFONE2";
$tdataC000007[".googleLikeFields"][] = "TELEFONE3";
$tdataC000007[".googleLikeFields"][] = "CELULAR";
$tdataC000007[".googleLikeFields"][] = "EMAIL";
$tdataC000007[".googleLikeFields"][] = "RG";
$tdataC000007[".googleLikeFields"][] = "CPF";
$tdataC000007[".googleLikeFields"][] = "ESTADOCIVIL";
$tdataC000007[".googleLikeFields"][] = "CONJUGE";
$tdataC000007[".googleLikeFields"][] = "NASCIMENTO";
$tdataC000007[".googleLikeFields"][] = "SEXO";
$tdataC000007[".googleLikeFields"][] = "OBS1";
$tdataC000007[".googleLikeFields"][] = "SITUACAO";


$tdataC000007[".advSearchFields"][] = "CODIGO";
$tdataC000007[".advSearchFields"][] = "NOME";
$tdataC000007[".advSearchFields"][] = "RG";
$tdataC000007[".advSearchFields"][] = "CPF";
$tdataC000007[".advSearchFields"][] = "SITUACAO";

$tdataC000007[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000007[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000007[".strOrderBy"] = $tstrOrderBy;

$tdataC000007[".orderindexes"] = array();

$tdataC000007[".sqlHead"] = "SELECT CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  COMPLEMENTO,  TELEFONE1,  TELEFONE2,  TELEFONE3,  CELULAR,  EMAIL,  RG,  CPF,  ESTADOCIVIL,  CONJUGE,  NASCIMENTO,  SEXO,  OBS1,  SITUACAO";
$tdataC000007[".sqlFrom"] = "FROM C000007";
$tdataC000007[".sqlWhereExpr"] = "";
$tdataC000007[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000007[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000007[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000007 = array();
$tableKeysC000007[] = "CODIGO";
$tdataC000007[".Keys"] = $tableKeysC000007;

$tdataC000007[".listFields"] = array();
$tdataC000007[".listFields"][] = "CODIGO";
$tdataC000007[".listFields"][] = "SITUACAO";
$tdataC000007[".listFields"][] = "NOME";
$tdataC000007[".listFields"][] = "RG";
$tdataC000007[".listFields"][] = "CPF";
$tdataC000007[".listFields"][] = "ENDERECO";
$tdataC000007[".listFields"][] = "BAIRRO";
$tdataC000007[".listFields"][] = "CIDADE";
$tdataC000007[".listFields"][] = "UF";
$tdataC000007[".listFields"][] = "EMAIL";

$tdataC000007[".viewFields"] = array();
$tdataC000007[".viewFields"][] = "CODIGO";
$tdataC000007[".viewFields"][] = "SITUACAO";
$tdataC000007[".viewFields"][] = "NOME";
$tdataC000007[".viewFields"][] = "ENDERECO";
$tdataC000007[".viewFields"][] = "COMPLEMENTO";
$tdataC000007[".viewFields"][] = "BAIRRO";
$tdataC000007[".viewFields"][] = "CIDADE";
$tdataC000007[".viewFields"][] = "UF";
$tdataC000007[".viewFields"][] = "CEP";
$tdataC000007[".viewFields"][] = "TELEFONE1";
$tdataC000007[".viewFields"][] = "TELEFONE2";
$tdataC000007[".viewFields"][] = "TELEFONE3";
$tdataC000007[".viewFields"][] = "CELULAR";
$tdataC000007[".viewFields"][] = "EMAIL";
$tdataC000007[".viewFields"][] = "RG";
$tdataC000007[".viewFields"][] = "CPF";
$tdataC000007[".viewFields"][] = "ESTADOCIVIL";
$tdataC000007[".viewFields"][] = "CONJUGE";
$tdataC000007[".viewFields"][] = "NASCIMENTO";
$tdataC000007[".viewFields"][] = "SEXO";
$tdataC000007[".viewFields"][] = "OBS1";

$tdataC000007[".addFields"] = array();

$tdataC000007[".inlineAddFields"] = array();
$tdataC000007[".inlineAddFields"][] = "CODIGO";
$tdataC000007[".inlineAddFields"][] = "SITUACAO";
$tdataC000007[".inlineAddFields"][] = "NOME";
$tdataC000007[".inlineAddFields"][] = "RG";
$tdataC000007[".inlineAddFields"][] = "CPF";
$tdataC000007[".inlineAddFields"][] = "ENDERECO";
$tdataC000007[".inlineAddFields"][] = "BAIRRO";
$tdataC000007[".inlineAddFields"][] = "EMAIL";

$tdataC000007[".editFields"] = array();

$tdataC000007[".inlineEditFields"] = array();
$tdataC000007[".inlineEditFields"][] = "CODIGO";
$tdataC000007[".inlineEditFields"][] = "SITUACAO";
$tdataC000007[".inlineEditFields"][] = "NOME";
$tdataC000007[".inlineEditFields"][] = "RG";
$tdataC000007[".inlineEditFields"][] = "CPF";
$tdataC000007[".inlineEditFields"][] = "ENDERECO";
$tdataC000007[".inlineEditFields"][] = "BAIRRO";
$tdataC000007[".inlineEditFields"][] = "EMAIL";

$tdataC000007[".exportFields"] = array();
$tdataC000007[".exportFields"][] = "CODIGO";
$tdataC000007[".exportFields"][] = "SITUACAO";
$tdataC000007[".exportFields"][] = "NOME";
$tdataC000007[".exportFields"][] = "ENDERECO";
$tdataC000007[".exportFields"][] = "BAIRRO";
$tdataC000007[".exportFields"][] = "CIDADE";
$tdataC000007[".exportFields"][] = "UF";
$tdataC000007[".exportFields"][] = "CEP";
$tdataC000007[".exportFields"][] = "COMPLEMENTO";
$tdataC000007[".exportFields"][] = "TELEFONE1";
$tdataC000007[".exportFields"][] = "TELEFONE2";
$tdataC000007[".exportFields"][] = "TELEFONE3";
$tdataC000007[".exportFields"][] = "CELULAR";
$tdataC000007[".exportFields"][] = "EMAIL";
$tdataC000007[".exportFields"][] = "RG";
$tdataC000007[".exportFields"][] = "CPF";
$tdataC000007[".exportFields"][] = "ESTADOCIVIL";
$tdataC000007[".exportFields"][] = "CONJUGE";
$tdataC000007[".exportFields"][] = "NASCIMENTO";
$tdataC000007[".exportFields"][] = "SEXO";
$tdataC000007[".exportFields"][] = "OBS1";

$tdataC000007[".printFields"] = array();
$tdataC000007[".printFields"][] = "CODIGO";
$tdataC000007[".printFields"][] = "SITUACAO";
$tdataC000007[".printFields"][] = "NOME";
$tdataC000007[".printFields"][] = "ENDERECO";
$tdataC000007[".printFields"][] = "BAIRRO";
$tdataC000007[".printFields"][] = "CIDADE";
$tdataC000007[".printFields"][] = "UF";
$tdataC000007[".printFields"][] = "CEP";
$tdataC000007[".printFields"][] = "COMPLEMENTO";
$tdataC000007[".printFields"][] = "TELEFONE1";
$tdataC000007[".printFields"][] = "TELEFONE2";
$tdataC000007[".printFields"][] = "TELEFONE3";
$tdataC000007[".printFields"][] = "CELULAR";
$tdataC000007[".printFields"][] = "EMAIL";
$tdataC000007[".printFields"][] = "RG";
$tdataC000007[".printFields"][] = "CPF";
$tdataC000007[".printFields"][] = "ESTADOCIVIL";
$tdataC000007[".printFields"][] = "CONJUGE";
$tdataC000007[".printFields"][] = "NASCIMENTO";
$tdataC000007[".printFields"][] = "SEXO";
$tdataC000007[".printFields"][] = "OBS1";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["CODIGO"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["NOME"] = $fdata;
//	ENDERECO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "ENDERECO";
	$fdata["GoodName"] = "ENDERECO";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["ENDERECO"] = $fdata;
//	BAIRRO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "BAIRRO";
	$fdata["GoodName"] = "BAIRRO";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Bairro"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
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
	
		
		
	$tdataC000007["BAIRRO"] = $fdata;
//	CIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "CIDADE";
	$fdata["GoodName"] = "CIDADE";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["CIDADE"] = $fdata;
//	UF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "UF";
	$fdata["GoodName"] = "UF";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "UF"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
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
	
		
		
	$tdataC000007["UF"] = $fdata;
//	CEP
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "CEP";
	$fdata["GoodName"] = "CEP";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["CEP"] = $fdata;
//	COMPLEMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "COMPLEMENTO";
	$fdata["GoodName"] = "COMPLEMENTO";
	$fdata["ownerTable"] = "C000007";
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
			$edata["EditParams"].= " maxlength=80";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000007["COMPLEMENTO"] = $fdata;
//	TELEFONE1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "TELEFONE1";
	$fdata["GoodName"] = "TELEFONE1";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["TELEFONE1"] = $fdata;
//	TELEFONE2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "TELEFONE2";
	$fdata["GoodName"] = "TELEFONE2";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["TELEFONE2"] = $fdata;
//	TELEFONE3
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "TELEFONE3";
	$fdata["GoodName"] = "TELEFONE3";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Telefone3"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "TELEFONE3"; 
		$fdata["FullName"] = "TELEFONE3";
	
		
		
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
	
		
		
	$tdataC000007["TELEFONE3"] = $fdata;
//	CELULAR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "CELULAR";
	$fdata["GoodName"] = "CELULAR";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["CELULAR"] = $fdata;
//	EMAIL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "EMAIL";
	$fdata["GoodName"] = "EMAIL";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "E-mail"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
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
	
		
		
	$tdataC000007["EMAIL"] = $fdata;
//	RG
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "RG";
	$fdata["GoodName"] = "RG";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["RG"] = $fdata;
//	CPF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "CPF";
	$fdata["GoodName"] = "CPF";
	$fdata["ownerTable"] = "C000007";
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
			$edata["EditParams"].= " maxlength=18";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000007["CPF"] = $fdata;
//	ESTADOCIVIL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "ESTADOCIVIL";
	$fdata["GoodName"] = "ESTADOCIVIL";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Estado Civil"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ESTADOCIVIL"; 
		$fdata["FullName"] = "ESTADOCIVIL";
	
		
		
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
	
		
		
	$tdataC000007["ESTADOCIVIL"] = $fdata;
//	CONJUGE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "CONJUGE";
	$fdata["GoodName"] = "CONJUGE";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Conjugue"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CONJUGE"; 
		$fdata["FullName"] = "CONJUGE";
	
		
		
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
	
		
		
	$tdataC000007["CONJUGE"] = $fdata;
//	NASCIMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "NASCIMENTO";
	$fdata["GoodName"] = "NASCIMENTO";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Nascimento"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "NASCIMENTO"; 
		$fdata["FullName"] = "NASCIMENTO";
	
		
		
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
	
		
		
	$tdataC000007["NASCIMENTO"] = $fdata;
//	SEXO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "SEXO";
	$fdata["GoodName"] = "SEXO";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Sexo"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "SEXO"; 
		$fdata["FullName"] = "SEXO";
	
		
		
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
			$edata["EditParams"].= " maxlength=1";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000007["SEXO"] = $fdata;
//	OBS1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "OBS1";
	$fdata["GoodName"] = "OBS1";
	$fdata["ownerTable"] = "C000007";
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
	
		
		
	$tdataC000007["OBS1"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 21;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000007";
	$fdata["Label"] = "Situação"; 
	$fdata["FieldType"] = 3;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
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
	
		
		
	$tdataC000007["SITUACAO"] = $fdata;

	
$tables_data["C000007"]=&$tdataC000007;
$field_labels["C000007"] = &$fieldLabelsC000007;
$fieldToolTips["C000007"] = &$fieldToolTipsC000007;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000007"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000007"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000007()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  COMPLEMENTO,  TELEFONE1,  TELEFONE2,  TELEFONE3,  CELULAR,  EMAIL,  RG,  CPF,  ESTADOCIVIL,  CONJUGE,  NASCIMENTO,  SEXO,  OBS1,  SITUACAO";
$proto0["m_strFrom"] = "FROM C000007";
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
	"m_strTable" => "C000007"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "C000007"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "ENDERECO",
	"m_strTable" => "C000007"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "BAIRRO",
	"m_strTable" => "C000007"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "CIDADE",
	"m_strTable" => "C000007"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "UF",
	"m_strTable" => "C000007"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "CEP",
	"m_strTable" => "C000007"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "COMPLEMENTO",
	"m_strTable" => "C000007"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE1",
	"m_strTable" => "C000007"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE2",
	"m_strTable" => "C000007"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE3",
	"m_strTable" => "C000007"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "CELULAR",
	"m_strTable" => "C000007"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "EMAIL",
	"m_strTable" => "C000007"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "RG",
	"m_strTable" => "C000007"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "CPF",
	"m_strTable" => "C000007"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "ESTADOCIVIL",
	"m_strTable" => "C000007"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "CONJUGE",
	"m_strTable" => "C000007"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "NASCIMENTO",
	"m_strTable" => "C000007"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "SEXO",
	"m_strTable" => "C000007"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "OBS1",
	"m_strTable" => "C000007"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
						$proto45=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000007"
));

$proto45["m_expr"]=$obj;
$proto45["m_alias"] = "";
$obj = new SQLFieldListItem($proto45);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto47=array();
$proto47["m_link"] = "SQLL_MAIN";
			$proto48=array();
$proto48["m_strName"] = "C000007";
$proto48["m_columns"] = array();
$proto48["m_columns"][] = "CODIGO";
$proto48["m_columns"][] = "NOME";
$proto48["m_columns"][] = "APELIDO";
$proto48["m_columns"][] = "ENDERECO";
$proto48["m_columns"][] = "BAIRRO";
$proto48["m_columns"][] = "CIDADE";
$proto48["m_columns"][] = "UF";
$proto48["m_columns"][] = "CEP";
$proto48["m_columns"][] = "COMPLEMENTO";
$proto48["m_columns"][] = "MORADIA";
$proto48["m_columns"][] = "TIPO";
$proto48["m_columns"][] = "SITUACAO";
$proto48["m_columns"][] = "TELEFONE1";
$proto48["m_columns"][] = "TELEFONE2";
$proto48["m_columns"][] = "TELEFONE3";
$proto48["m_columns"][] = "CELULAR";
$proto48["m_columns"][] = "EMAIL";
$proto48["m_columns"][] = "RG";
$proto48["m_columns"][] = "CPF";
$proto48["m_columns"][] = "FILIACAO";
$proto48["m_columns"][] = "ESTADOCIVIL";
$proto48["m_columns"][] = "CONJUGE";
$proto48["m_columns"][] = "PROFISSAO";
$proto48["m_columns"][] = "EMPRESA";
$proto48["m_columns"][] = "RENDA";
$proto48["m_columns"][] = "LIMITE";
$proto48["m_columns"][] = "REF1";
$proto48["m_columns"][] = "REF2";
$proto48["m_columns"][] = "CODVENDEDOR";
$proto48["m_columns"][] = "DATA_CADASTRO";
$proto48["m_columns"][] = "DATA_ULTIMACOMPRA";
$proto48["m_columns"][] = "OBS1";
$proto48["m_columns"][] = "OBS2";
$proto48["m_columns"][] = "OBS3";
$proto48["m_columns"][] = "OBS4";
$proto48["m_columns"][] = "OBS5";
$proto48["m_columns"][] = "OBS6";
$proto48["m_columns"][] = "NASCIMENTO";
$proto48["m_columns"][] = "CODREGIAO";
$proto48["m_columns"][] = "CODCONVENIO";
$proto48["m_columns"][] = "CODUSUARIO";
$proto48["m_columns"][] = "NUMERO";
$proto48["m_columns"][] = "RG_ORGAO";
$proto48["m_columns"][] = "RG_ESTADO";
$proto48["m_columns"][] = "RG_EMISSAO";
$proto48["m_columns"][] = "SEXO";
$proto48["m_columns"][] = "HISTORICO";
$proto48["m_columns"][] = "PREVISAO";
$proto48["m_columns"][] = "CNAE";
$proto48["m_columns"][] = "COD_MUNICIPIO_IBGE";
$proto48["m_columns"][] = "IBGE";
$proto48["m_columns"][] = "TAMANHO_CALCA";
$proto48["m_columns"][] = "TAMANHO_BLUSA";
$proto48["m_columns"][] = "TAMANHO_SAPATO";
$proto48["m_columns"][] = "CORRESP_ENDERECO";
$proto48["m_columns"][] = "CORRESP_BAIRRO";
$proto48["m_columns"][] = "CORRESP_CIDADE";
$proto48["m_columns"][] = "CORRESP_UF";
$proto48["m_columns"][] = "CORRESP_CEP";
$proto48["m_columns"][] = "CORRESP_COMPLEMENTO";
$proto48["m_columns"][] = "RG_PRODUTOR";
$proto48["m_columns"][] = "RESP1_NOME";
$proto48["m_columns"][] = "RESP1_CPF";
$proto48["m_columns"][] = "RESP1_RG";
$proto48["m_columns"][] = "RESP1_PROFISSAO";
$proto48["m_columns"][] = "RESP1_ESTADO_CIVIL";
$proto48["m_columns"][] = "RESP1_ENDERECO";
$proto48["m_columns"][] = "RESP1_BAIRRO";
$proto48["m_columns"][] = "RESP1_CIDADE";
$proto48["m_columns"][] = "RESP1_UF";
$proto48["m_columns"][] = "RESP1_CEP";
$proto48["m_columns"][] = "RESP2_NOME";
$proto48["m_columns"][] = "RESP2_CPF";
$proto48["m_columns"][] = "RESP2_RG";
$proto48["m_columns"][] = "RESP2_PROFISSAO";
$proto48["m_columns"][] = "RESP2_ESTADO_CIVIL";
$proto48["m_columns"][] = "RESP2_ENDERECO";
$proto48["m_columns"][] = "RESP2_BAIRRO";
$proto48["m_columns"][] = "RESP2_CIDADE";
$proto48["m_columns"][] = "RESP2_UF";
$proto48["m_columns"][] = "RESP2_CEP";
$proto48["m_columns"][] = "FOTO";
$proto48["m_columns"][] = "CONDPGTO";
$obj = new SQLTable($proto48);

$proto47["m_table"] = $obj;
$proto47["m_alias"] = "";
$proto49=array();
$proto49["m_sql"] = "";
$proto49["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto49["m_column"]=$obj;
$proto49["m_contained"] = array();
$proto49["m_strCase"] = "";
$proto49["m_havingmode"] = "0";
$proto49["m_inBrackets"] = "0";
$proto49["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto49);

$proto47["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto47);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000007 = createSqlQuery_C000007();
																					$tdataC000007[".sqlquery"] = $queryData_C000007;
	
if(isset($tdataC000007["field2"])){
	$tdataC000007["field2"]["LookupTable"] = "carscars_view";
	$tdataC000007["field2"]["LookupOrderBy"] = "name";
	$tdataC000007["field2"]["LookupType"] = 4;
	$tdataC000007["field2"]["LinkField"] = "email";
	$tdataC000007["field2"]["DisplayField"] = "name";
	$tdataC000007[".hasCustomViewField"] = true;
}

$tableEvents["C000007"] = new eventsBase;
$tdataC000007[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000007");

?>