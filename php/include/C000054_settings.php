<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000054 = array();
	$tdataC000054[".NumberOfChars"] = 80; 
	$tdataC000054[".ShortName"] = "C000054";
	$tdataC000054[".OwnerID"] = "";
	$tdataC000054[".OriginalTable"] = "C000054";

//	field labels
$fieldLabelsC000054 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000054["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000054["Portuguese(Brazil)"] = array();
	$fieldLabelsC000054["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["NOME"] = "Nome do Veículo";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["ANO"] = "Ano";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["ANO"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["COMBUSTIVEL"] = "Combustível";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["COMBUSTIVEL"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["PLACA"] = "Placa";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["PLACA"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["UFPLACA"] = "UF da Placa";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["UFPLACA"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["COR"] = "Cor";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["COR"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["OBS1"] = "OBS";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["OBS1"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["CIDADE"] = "Cidade";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["CIDADE"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["SEGURO_OBRIGATORIO"] = "Seguro Obrigatório";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["SEGURO_OBRIGATORIO"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["COD_MARCA"] = "Marca";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["COD_MARCA"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["COD_MODELO"] = "Modelo";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["COD_MODELO"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["SITUACAO"] = "Situação";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["SITUACAO"] = "";
	$fieldLabelsC000054["Portuguese(Brazil)"]["LICENCIAMENTO"] = "Licenciamento";
	$fieldToolTipsC000054["Portuguese(Brazil)"]["LICENCIAMENTO"] = "";
	if (count($fieldToolTipsC000054["Portuguese(Brazil)"]))
		$tdataC000054[".isUseToolTips"] = true;
}
	
	
	$tdataC000054[".NCSearch"] = true;



$tdataC000054[".shortTableName"] = "C000054";
$tdataC000054[".nSecOptions"] = 0;
$tdataC000054[".recsPerRowList"] = 1;
$tdataC000054[".mainTableOwnerID"] = "";
$tdataC000054[".moveNext"] = 1;
$tdataC000054[".nType"] = 0;

$tdataC000054[".strOriginalTableName"] = "C000054";




$tdataC000054[".showAddInPopup"] = false;

$tdataC000054[".showEditInPopup"] = false;

$tdataC000054[".showViewInPopup"] = false;

$tdataC000054[".fieldsForRegister"] = array();

$tdataC000054[".listAjax"] = false;

	$tdataC000054[".audit"] = false;

	$tdataC000054[".locking"] = false;

$tdataC000054[".listIcons"] = true;
$tdataC000054[".inlineEdit"] = true;
$tdataC000054[".inlineAdd"] = true;
$tdataC000054[".view"] = true;

$tdataC000054[".exportTo"] = true;

$tdataC000054[".printFriendly"] = true;


$tdataC000054[".showSimpleSearchOptions"] = false;

$tdataC000054[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000054[".isUseAjaxSuggest"] = false;
else 
	$tdataC000054[".isUseAjaxSuggest"] = true;

$tdataC000054[".rowHighlite"] = true;

// button handlers file names

$tdataC000054[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000054[".isUseTimeForSearch"] = false;




$tdataC000054[".allSearchFields"] = array();

$tdataC000054[".allSearchFields"][] = "CODIGO";
$tdataC000054[".allSearchFields"][] = "NOME";
$tdataC000054[".allSearchFields"][] = "PLACA";

$tdataC000054[".googleLikeFields"][] = "CODIGO";
$tdataC000054[".googleLikeFields"][] = "NOME";
$tdataC000054[".googleLikeFields"][] = "ANO";
$tdataC000054[".googleLikeFields"][] = "COMBUSTIVEL";
$tdataC000054[".googleLikeFields"][] = "PLACA";
$tdataC000054[".googleLikeFields"][] = "UFPLACA";
$tdataC000054[".googleLikeFields"][] = "COR";
$tdataC000054[".googleLikeFields"][] = "OBS1";
$tdataC000054[".googleLikeFields"][] = "CIDADE";
$tdataC000054[".googleLikeFields"][] = "SEGURO_OBRIGATORIO";
$tdataC000054[".googleLikeFields"][] = "COD_MARCA";
$tdataC000054[".googleLikeFields"][] = "COD_MODELO";
$tdataC000054[".googleLikeFields"][] = "SITUACAO";
$tdataC000054[".googleLikeFields"][] = "LICENCIAMENTO";


$tdataC000054[".advSearchFields"][] = "CODIGO";
$tdataC000054[".advSearchFields"][] = "NOME";
$tdataC000054[".advSearchFields"][] = "PLACA";

$tdataC000054[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000054[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000054[".strOrderBy"] = $tstrOrderBy;

$tdataC000054[".orderindexes"] = array();

$tdataC000054[".sqlHead"] = "SELECT CODIGO,  NOME,  ANO,  COMBUSTIVEL,  PLACA,  UFPLACA,  COR,  OBS1,  CIDADE,  SEGURO_OBRIGATORIO,  COD_MARCA,  COD_MODELO,  SITUACAO,  LICENCIAMENTO";
$tdataC000054[".sqlFrom"] = "FROM C000054";
$tdataC000054[".sqlWhereExpr"] = "";
$tdataC000054[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000054[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000054[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000054 = array();
$tableKeysC000054[] = "CODIGO";
$tdataC000054[".Keys"] = $tableKeysC000054;

$tdataC000054[".listFields"] = array();
$tdataC000054[".listFields"][] = "CODIGO";
$tdataC000054[".listFields"][] = "NOME";
$tdataC000054[".listFields"][] = "ANO";
$tdataC000054[".listFields"][] = "COMBUSTIVEL";
$tdataC000054[".listFields"][] = "PLACA";
$tdataC000054[".listFields"][] = "UFPLACA";
$tdataC000054[".listFields"][] = "COD_MARCA";
$tdataC000054[".listFields"][] = "COD_MODELO";

$tdataC000054[".viewFields"] = array();
$tdataC000054[".viewFields"][] = "CODIGO";
$tdataC000054[".viewFields"][] = "SITUACAO";
$tdataC000054[".viewFields"][] = "NOME";
$tdataC000054[".viewFields"][] = "ANO";
$tdataC000054[".viewFields"][] = "COMBUSTIVEL";
$tdataC000054[".viewFields"][] = "PLACA";
$tdataC000054[".viewFields"][] = "UFPLACA";
$tdataC000054[".viewFields"][] = "COR";
$tdataC000054[".viewFields"][] = "CIDADE";
$tdataC000054[".viewFields"][] = "SEGURO_OBRIGATORIO";
$tdataC000054[".viewFields"][] = "COD_MARCA";
$tdataC000054[".viewFields"][] = "COD_MODELO";
$tdataC000054[".viewFields"][] = "LICENCIAMENTO";
$tdataC000054[".viewFields"][] = "OBS1";

$tdataC000054[".addFields"] = array();

$tdataC000054[".inlineAddFields"] = array();

$tdataC000054[".editFields"] = array();

$tdataC000054[".inlineEditFields"] = array();

$tdataC000054[".exportFields"] = array();
$tdataC000054[".exportFields"][] = "CODIGO";
$tdataC000054[".exportFields"][] = "SITUACAO";
$tdataC000054[".exportFields"][] = "NOME";
$tdataC000054[".exportFields"][] = "ANO";
$tdataC000054[".exportFields"][] = "COMBUSTIVEL";
$tdataC000054[".exportFields"][] = "PLACA";
$tdataC000054[".exportFields"][] = "UFPLACA";
$tdataC000054[".exportFields"][] = "COR";
$tdataC000054[".exportFields"][] = "CIDADE";
$tdataC000054[".exportFields"][] = "SEGURO_OBRIGATORIO";
$tdataC000054[".exportFields"][] = "COD_MARCA";
$tdataC000054[".exportFields"][] = "COD_MODELO";
$tdataC000054[".exportFields"][] = "LICENCIAMENTO";
$tdataC000054[".exportFields"][] = "OBS1";

$tdataC000054[".printFields"] = array();
$tdataC000054[".printFields"][] = "CODIGO";
$tdataC000054[".printFields"][] = "SITUACAO";
$tdataC000054[".printFields"][] = "NOME";
$tdataC000054[".printFields"][] = "ANO";
$tdataC000054[".printFields"][] = "COMBUSTIVEL";
$tdataC000054[".printFields"][] = "PLACA";
$tdataC000054[".printFields"][] = "UFPLACA";
$tdataC000054[".printFields"][] = "COR";
$tdataC000054[".printFields"][] = "CIDADE";
$tdataC000054[".printFields"][] = "SEGURO_OBRIGATORIO";
$tdataC000054[".printFields"][] = "COD_MARCA";
$tdataC000054[".printFields"][] = "COD_MODELO";
$tdataC000054[".printFields"][] = "LICENCIAMENTO";
$tdataC000054[".printFields"][] = "OBS1";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000054";
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
	
		
		
	$tdataC000054["CODIGO"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Nome do Veículo"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
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
			$edata["EditParams"].= " maxlength=20";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000054["NOME"] = $fdata;
//	ANO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "ANO";
	$fdata["GoodName"] = "ANO";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Ano"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "ANO"; 
		$fdata["FullName"] = "ANO";
	
		
		
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
	
		
		
	$tdataC000054["ANO"] = $fdata;
//	COMBUSTIVEL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "COMBUSTIVEL";
	$fdata["GoodName"] = "COMBUSTIVEL";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Combustível"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COMBUSTIVEL"; 
		$fdata["FullName"] = "COMBUSTIVEL";
	
		
		
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
	
		
		
	$tdataC000054["COMBUSTIVEL"] = $fdata;
//	PLACA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "PLACA";
	$fdata["GoodName"] = "PLACA";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Placa"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		$fdata["bAdvancedSearch"] = true; 
	
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
	
		
		
	$tdataC000054["PLACA"] = $fdata;
//	UFPLACA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "UFPLACA";
	$fdata["GoodName"] = "UFPLACA";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "UF da Placa"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
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
	
		
		
	$tdataC000054["UFPLACA"] = $fdata;
//	COR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "COR";
	$fdata["GoodName"] = "COR";
	$fdata["ownerTable"] = "C000054";
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
			$edata["EditParams"].= " maxlength=20";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000054["COR"] = $fdata;
//	OBS1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "OBS1";
	$fdata["GoodName"] = "OBS1";
	$fdata["ownerTable"] = "C000054";
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
	
		
		
	$tdataC000054["OBS1"] = $fdata;
//	CIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "CIDADE";
	$fdata["GoodName"] = "CIDADE";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Cidade"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
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
			$edata["EditParams"].= " maxlength=50";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000054["CIDADE"] = $fdata;
//	SEGURO_OBRIGATORIO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "SEGURO_OBRIGATORIO";
	$fdata["GoodName"] = "SEGURO_OBRIGATORIO";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Seguro Obrigatório"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "SEGURO_OBRIGATORIO"; 
		$fdata["FullName"] = "SEGURO_OBRIGATORIO";
	
		
		
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
	
		
		
	$tdataC000054["SEGURO_OBRIGATORIO"] = $fdata;
//	COD_MARCA
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "COD_MARCA";
	$fdata["GoodName"] = "COD_MARCA";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Marca"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COD_MARCA"; 
		$fdata["FullName"] = "COD_MARCA";
	
		
		
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
	
		
		
	$tdataC000054["COD_MARCA"] = $fdata;
//	COD_MODELO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "COD_MODELO";
	$fdata["GoodName"] = "COD_MODELO";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Modelo"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "COD_MODELO"; 
		$fdata["FullName"] = "COD_MODELO";
	
		
		
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
	
		
		
	$tdataC000054["COD_MODELO"] = $fdata;
//	SITUACAO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "SITUACAO";
	$fdata["GoodName"] = "SITUACAO";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Situação"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
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
			$edata["EditParams"].= " maxlength=10";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000054["SITUACAO"] = $fdata;
//	LICENCIAMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "LICENCIAMENTO";
	$fdata["GoodName"] = "LICENCIAMENTO";
	$fdata["ownerTable"] = "C000054";
	$fdata["Label"] = "Licenciamento"; 
	$fdata["FieldType"] = 135;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "LICENCIAMENTO"; 
		$fdata["FullName"] = "LICENCIAMENTO";
	
		
		
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
	
		
		
	$tdataC000054["LICENCIAMENTO"] = $fdata;

	
$tables_data["C000054"]=&$tdataC000054;
$field_labels["C000054"] = &$fieldLabelsC000054;
$fieldToolTips["C000054"] = &$fieldToolTipsC000054;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000054"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000054"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000054()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  NOME,  ANO,  COMBUSTIVEL,  PLACA,  UFPLACA,  COR,  OBS1,  CIDADE,  SEGURO_OBRIGATORIO,  COD_MARCA,  COD_MODELO,  SITUACAO,  LICENCIAMENTO";
$proto0["m_strFrom"] = "FROM C000054";
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
	"m_strTable" => "C000054"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "C000054"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "ANO",
	"m_strTable" => "C000054"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "COMBUSTIVEL",
	"m_strTable" => "C000054"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "PLACA",
	"m_strTable" => "C000054"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "UFPLACA",
	"m_strTable" => "C000054"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "COR",
	"m_strTable" => "C000054"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "OBS1",
	"m_strTable" => "C000054"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "CIDADE",
	"m_strTable" => "C000054"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "SEGURO_OBRIGATORIO",
	"m_strTable" => "C000054"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "COD_MARCA",
	"m_strTable" => "C000054"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "COD_MODELO",
	"m_strTable" => "C000054"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "SITUACAO",
	"m_strTable" => "C000054"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "LICENCIAMENTO",
	"m_strTable" => "C000054"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto33=array();
$proto33["m_link"] = "SQLL_MAIN";
			$proto34=array();
$proto34["m_strName"] = "C000054";
$proto34["m_columns"] = array();
$proto34["m_columns"][] = "CODIGO";
$proto34["m_columns"][] = "NOME";
$proto34["m_columns"][] = "ANO";
$proto34["m_columns"][] = "COMBUSTIVEL";
$proto34["m_columns"][] = "PLACA";
$proto34["m_columns"][] = "UFPLACA";
$proto34["m_columns"][] = "COR";
$proto34["m_columns"][] = "OBS1";
$proto34["m_columns"][] = "OBS2";
$proto34["m_columns"][] = "OBS3";
$proto34["m_columns"][] = "CIDADE";
$proto34["m_columns"][] = "COTA_UNICA_IPVA";
$proto34["m_columns"][] = "COTA1_IPVA";
$proto34["m_columns"][] = "COTA2_IPVA";
$proto34["m_columns"][] = "COTA3_IPVA";
$proto34["m_columns"][] = "LICENCIAMENTO";
$proto34["m_columns"][] = "SEGURO_OBRIGATORIO";
$proto34["m_columns"][] = "COD_MARCA";
$proto34["m_columns"][] = "COD_MODELO";
$proto34["m_columns"][] = "SITUACAO";
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
$queryData_C000054 = createSqlQuery_C000054();
														$tdataC000054[".sqlquery"] = $queryData_C000054;
	
if(isset($tdataC000054["field2"])){
	$tdataC000054["field2"]["LookupTable"] = "carscars_view";
	$tdataC000054["field2"]["LookupOrderBy"] = "name";
	$tdataC000054["field2"]["LookupType"] = 4;
	$tdataC000054["field2"]["LinkField"] = "email";
	$tdataC000054["field2"]["DisplayField"] = "name";
	$tdataC000054[".hasCustomViewField"] = true;
}

$tableEvents["C000054"] = new eventsBase;
$tdataC000054[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000054");

?>