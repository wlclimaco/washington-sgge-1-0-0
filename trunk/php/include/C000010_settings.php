<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000010 = array();
	$tdataC000010[".NumberOfChars"] = 80; 
	$tdataC000010[".ShortName"] = "C000010";
	$tdataC000010[".OwnerID"] = "";
	$tdataC000010[".OriginalTable"] = "C000010";

//	field labels
$fieldLabelsC000010 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000010["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000010["Portuguese(Brazil)"] = array();
	$fieldLabelsC000010["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["NOME"] = "Nome";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["ENDERECO"] = "Endereço";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["ENDERECO"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["BAIRRO"] = "Bairro";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["BAIRRO"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["CIDADE"] = "Cidade";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["CIDADE"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["UF"] = "UF";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["UF"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["CEP"] = "CEP";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["CEP"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["CPF"] = "CPF";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["CPF"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["RG"] = "RG";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["RG"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["TELEFONE"] = "Telefone";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["TELEFONE"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["CELULAR"] = "Celular";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["CELULAR"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["PLACA"] = "Placa";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["PLACA"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["UFPLACA"] = "UF Placa";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["UFPLACA"] = "";
	$fieldLabelsC000010["Portuguese(Brazil)"]["OBS1"] = "OBS";
	$fieldToolTipsC000010["Portuguese(Brazil)"]["OBS1"] = "";
	if (count($fieldToolTipsC000010["Portuguese(Brazil)"]))
		$tdataC000010[".isUseToolTips"] = true;
}
	
	
	$tdataC000010[".NCSearch"] = true;



$tdataC000010[".shortTableName"] = "C000010";
$tdataC000010[".nSecOptions"] = 0;
$tdataC000010[".recsPerRowList"] = 1;
$tdataC000010[".mainTableOwnerID"] = "";
$tdataC000010[".moveNext"] = 1;
$tdataC000010[".nType"] = 0;

$tdataC000010[".strOriginalTableName"] = "C000010";




$tdataC000010[".showAddInPopup"] = false;

$tdataC000010[".showEditInPopup"] = false;

$tdataC000010[".showViewInPopup"] = false;

$tdataC000010[".fieldsForRegister"] = array();

$tdataC000010[".listAjax"] = false;

	$tdataC000010[".audit"] = false;

	$tdataC000010[".locking"] = false;

$tdataC000010[".listIcons"] = true;
$tdataC000010[".inlineEdit"] = true;
$tdataC000010[".inlineAdd"] = true;

$tdataC000010[".exportTo"] = true;

$tdataC000010[".printFriendly"] = true;


$tdataC000010[".showSimpleSearchOptions"] = false;

$tdataC000010[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000010[".isUseAjaxSuggest"] = false;
else 
	$tdataC000010[".isUseAjaxSuggest"] = true;

$tdataC000010[".rowHighlite"] = true;

// button handlers file names

$tdataC000010[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000010[".isUseTimeForSearch"] = false;




$tdataC000010[".allSearchFields"] = array();

$tdataC000010[".allSearchFields"][] = "CODIGO";
$tdataC000010[".allSearchFields"][] = "NOME";
$tdataC000010[".allSearchFields"][] = "CPF";
$tdataC000010[".allSearchFields"][] = "RG";

$tdataC000010[".googleLikeFields"][] = "CODIGO";
$tdataC000010[".googleLikeFields"][] = "NOME";
$tdataC000010[".googleLikeFields"][] = "ENDERECO";
$tdataC000010[".googleLikeFields"][] = "BAIRRO";
$tdataC000010[".googleLikeFields"][] = "CIDADE";
$tdataC000010[".googleLikeFields"][] = "UF";
$tdataC000010[".googleLikeFields"][] = "CEP";
$tdataC000010[".googleLikeFields"][] = "CPF";
$tdataC000010[".googleLikeFields"][] = "RG";
$tdataC000010[".googleLikeFields"][] = "TELEFONE";
$tdataC000010[".googleLikeFields"][] = "CELULAR";
$tdataC000010[".googleLikeFields"][] = "PLACA";
$tdataC000010[".googleLikeFields"][] = "UFPLACA";
$tdataC000010[".googleLikeFields"][] = "OBS1";


$tdataC000010[".advSearchFields"][] = "CODIGO";
$tdataC000010[".advSearchFields"][] = "NOME";
$tdataC000010[".advSearchFields"][] = "CPF";
$tdataC000010[".advSearchFields"][] = "RG";

$tdataC000010[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000010[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000010[".strOrderBy"] = $tstrOrderBy;

$tdataC000010[".orderindexes"] = array();

$tdataC000010[".sqlHead"] = "SELECT CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  CPF,  RG,  TELEFONE,  CELULAR,  PLACA,  UFPLACA,  OBS1";
$tdataC000010[".sqlFrom"] = "FROM C000010";
$tdataC000010[".sqlWhereExpr"] = "";
$tdataC000010[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000010[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000010[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000010 = array();
$tableKeysC000010[] = "CODIGO";
$tdataC000010[".Keys"] = $tableKeysC000010;

$tdataC000010[".listFields"] = array();
$tdataC000010[".listFields"][] = "CODIGO";
$tdataC000010[".listFields"][] = "NOME";
$tdataC000010[".listFields"][] = "ENDERECO";
$tdataC000010[".listFields"][] = "BAIRRO";
$tdataC000010[".listFields"][] = "CIDADE";
$tdataC000010[".listFields"][] = "UF";
$tdataC000010[".listFields"][] = "CPF";
$tdataC000010[".listFields"][] = "RG";

$tdataC000010[".viewFields"] = array();

$tdataC000010[".addFields"] = array();

$tdataC000010[".inlineAddFields"] = array();
$tdataC000010[".inlineAddFields"][] = "CODIGO";
$tdataC000010[".inlineAddFields"][] = "NOME";
$tdataC000010[".inlineAddFields"][] = "ENDERECO";
$tdataC000010[".inlineAddFields"][] = "BAIRRO";
$tdataC000010[".inlineAddFields"][] = "CIDADE";
$tdataC000010[".inlineAddFields"][] = "CPF";
$tdataC000010[".inlineAddFields"][] = "RG";

$tdataC000010[".editFields"] = array();

$tdataC000010[".inlineEditFields"] = array();
$tdataC000010[".inlineEditFields"][] = "CODIGO";
$tdataC000010[".inlineEditFields"][] = "NOME";
$tdataC000010[".inlineEditFields"][] = "ENDERECO";
$tdataC000010[".inlineEditFields"][] = "BAIRRO";
$tdataC000010[".inlineEditFields"][] = "CIDADE";
$tdataC000010[".inlineEditFields"][] = "CPF";
$tdataC000010[".inlineEditFields"][] = "RG";

$tdataC000010[".exportFields"] = array();
$tdataC000010[".exportFields"][] = "CODIGO";
$tdataC000010[".exportFields"][] = "NOME";
$tdataC000010[".exportFields"][] = "ENDERECO";
$tdataC000010[".exportFields"][] = "BAIRRO";
$tdataC000010[".exportFields"][] = "CIDADE";
$tdataC000010[".exportFields"][] = "UF";
$tdataC000010[".exportFields"][] = "CEP";
$tdataC000010[".exportFields"][] = "CPF";
$tdataC000010[".exportFields"][] = "RG";
$tdataC000010[".exportFields"][] = "TELEFONE";
$tdataC000010[".exportFields"][] = "CELULAR";
$tdataC000010[".exportFields"][] = "PLACA";
$tdataC000010[".exportFields"][] = "UFPLACA";
$tdataC000010[".exportFields"][] = "OBS1";

$tdataC000010[".printFields"] = array();
$tdataC000010[".printFields"][] = "CODIGO";
$tdataC000010[".printFields"][] = "NOME";
$tdataC000010[".printFields"][] = "ENDERECO";
$tdataC000010[".printFields"][] = "BAIRRO";
$tdataC000010[".printFields"][] = "CIDADE";
$tdataC000010[".printFields"][] = "UF";
$tdataC000010[".printFields"][] = "CEP";
$tdataC000010[".printFields"][] = "CPF";
$tdataC000010[".printFields"][] = "RG";
$tdataC000010[".printFields"][] = "TELEFONE";
$tdataC000010[".printFields"][] = "CELULAR";
$tdataC000010[".printFields"][] = "PLACA";
$tdataC000010[".printFields"][] = "UFPLACA";
$tdataC000010[".printFields"][] = "OBS1";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Código"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
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
	
		
		
	$tdataC000010["CODIGO"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Nome"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
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
	
		
		
	$tdataC000010["NOME"] = $fdata;
//	ENDERECO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "ENDERECO";
	$fdata["GoodName"] = "ENDERECO";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Endereço"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
		
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
	
		
		
	$tdataC000010["ENDERECO"] = $fdata;
//	BAIRRO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "BAIRRO";
	$fdata["GoodName"] = "BAIRRO";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Bairro"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
		
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
	
		
		
	$tdataC000010["BAIRRO"] = $fdata;
//	CIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "CIDADE";
	$fdata["GoodName"] = "CIDADE";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Cidade"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
		
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
	
		
		
	$tdataC000010["CIDADE"] = $fdata;
//	UF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "UF";
	$fdata["GoodName"] = "UF";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "UF"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		
		
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
	
		
		
	$tdataC000010["UF"] = $fdata;
//	CEP
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "CEP";
	$fdata["GoodName"] = "CEP";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "CEP"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000010["CEP"] = $fdata;
//	CPF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "CPF";
	$fdata["GoodName"] = "CPF";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "CPF"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
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
			$edata["EditParams"].= " maxlength=25";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000010["CPF"] = $fdata;
//	RG
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "RG";
	$fdata["GoodName"] = "RG";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "RG"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		$fdata["bInlineAdd"] = true; 
	
		
		$fdata["bInlineEdit"] = true; 
	
		
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
	
		
		
	$tdataC000010["RG"] = $fdata;
//	TELEFONE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "TELEFONE";
	$fdata["GoodName"] = "TELEFONE";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Telefone"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000010["TELEFONE"] = $fdata;
//	CELULAR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "CELULAR";
	$fdata["GoodName"] = "CELULAR";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Celular"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000010["CELULAR"] = $fdata;
//	PLACA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "PLACA";
	$fdata["GoodName"] = "PLACA";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "Placa"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "PLACA"; 
		$fdata["FullName"] = "PLACA";
	
		
		
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
	
		
		
	$tdataC000010["PLACA"] = $fdata;
//	UFPLACA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "UFPLACA";
	$fdata["GoodName"] = "UFPLACA";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "UF Placa"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "UFPLACA"; 
		$fdata["FullName"] = "UFPLACA";
	
		
		
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
	
		
		
	$tdataC000010["UFPLACA"] = $fdata;
//	OBS1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "OBS1";
	$fdata["GoodName"] = "OBS1";
	$fdata["ownerTable"] = "C000010";
	$fdata["Label"] = "OBS"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		
		
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
	
		
		
	$tdataC000010["OBS1"] = $fdata;

	
$tables_data["C000010"]=&$tdataC000010;
$field_labels["C000010"] = &$fieldLabelsC000010;
$fieldToolTips["C000010"] = &$fieldToolTipsC000010;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000010"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000010"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000010()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  CPF,  RG,  TELEFONE,  CELULAR,  PLACA,  UFPLACA,  OBS1";
$proto0["m_strFrom"] = "FROM C000010";
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
	"m_strTable" => "C000010"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "C000010"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "ENDERECO",
	"m_strTable" => "C000010"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "BAIRRO",
	"m_strTable" => "C000010"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "CIDADE",
	"m_strTable" => "C000010"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "UF",
	"m_strTable" => "C000010"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "CEP",
	"m_strTable" => "C000010"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "CPF",
	"m_strTable" => "C000010"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "RG",
	"m_strTable" => "C000010"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE",
	"m_strTable" => "C000010"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "CELULAR",
	"m_strTable" => "C000010"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "PLACA",
	"m_strTable" => "C000010"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "UFPLACA",
	"m_strTable" => "C000010"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "OBS1",
	"m_strTable" => "C000010"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto33=array();
$proto33["m_link"] = "SQLL_MAIN";
			$proto34=array();
$proto34["m_strName"] = "C000010";
$proto34["m_columns"] = array();
$proto34["m_columns"][] = "CODIGO";
$proto34["m_columns"][] = "NOME";
$proto34["m_columns"][] = "ENDERECO";
$proto34["m_columns"][] = "BAIRRO";
$proto34["m_columns"][] = "CIDADE";
$proto34["m_columns"][] = "UF";
$proto34["m_columns"][] = "CEP";
$proto34["m_columns"][] = "CPF";
$proto34["m_columns"][] = "RG";
$proto34["m_columns"][] = "TELEFONE";
$proto34["m_columns"][] = "CELULAR";
$proto34["m_columns"][] = "PLACA";
$proto34["m_columns"][] = "UFPLACA";
$proto34["m_columns"][] = "OBS1";
$proto34["m_columns"][] = "OBS2";
$proto34["m_columns"][] = "OBS3";
$proto34["m_columns"][] = "DATA";
$proto34["m_columns"][] = "TIPO";
$proto34["m_columns"][] = "NUMERO";
$proto34["m_columns"][] = "COD_MUNICIPIO_IBGE";
$proto34["m_columns"][] = "IBGE";
$proto34["m_columns"][] = "ANTT";
$obj = new SQLTable($proto34);

$proto33["m_table"] = $obj;
$proto33["m_alias"] = "";
$proto35=array();
$proto35["m_sql"] = "";
$proto35["m_uniontype"] = "SQLL_UNKNOWN";
	$obj = new SQLNonParsed(array(
	"m_sql" => ""
));

$proto35["m_column"]=$obj;
$proto35["m_contained"] = array();
$proto35["m_strCase"] = "";
$proto35["m_havingmode"] = "0";
$proto35["m_inBrackets"] = "0";
$proto35["m_useAlias"] = "0";
$obj = new SQLLogicalExpr($proto35);

$proto33["m_joinon"] = $obj;
$obj = new SQLFromListItem($proto33);

$proto0["m_fromlist"][]=$obj;
$proto0["m_groupby"] = array();
$proto0["m_orderby"] = array();
$obj = new SQLQuery($proto0);

	return $obj;
}
$queryData_C000010 = createSqlQuery_C000010();
														$tdataC000010[".sqlquery"] = $queryData_C000010;
	
if(isset($tdataC000010["field2"])){
	$tdataC000010["field2"]["LookupTable"] = "carscars_view";
	$tdataC000010["field2"]["LookupOrderBy"] = "name";
	$tdataC000010["field2"]["LookupType"] = 4;
	$tdataC000010["field2"]["LinkField"] = "email";
	$tdataC000010["field2"]["DisplayField"] = "name";
	$tdataC000010[".hasCustomViewField"] = true;
}

$tableEvents["C000010"] = new eventsBase;
$tdataC000010[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000010");

?>