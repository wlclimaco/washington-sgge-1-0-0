<?php
require_once(getabspath("classes/cipherer.php"));
$tdataC000071 = array();
	$tdataC000071[".NumberOfChars"] = 80; 
	$tdataC000071[".ShortName"] = "C000071";
	$tdataC000071[".OwnerID"] = "";
	$tdataC000071[".OriginalTable"] = "C000071";

//	field labels
$fieldLabelsC000071 = array();
if(mlang_getcurrentlang()=="Portuguese(Brazil)")
{
	$fieldLabelsC000071["Portuguese(Brazil)"] = array();
	$fieldToolTipsC000071["Portuguese(Brazil)"] = array();
	$fieldLabelsC000071["Portuguese(Brazil)"]["CODIGO"] = "Código";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["CODIGO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["NOME"] = "Convênio";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["NOME"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["ENDERECO"] = "Endereço";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["ENDERECO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["BAIRRO"] = "Bairro";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["BAIRRO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["CIDADE"] = "Cidade";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["CIDADE"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["UF"] = "UF";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["UF"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["CEP"] = "CEP";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["CEP"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["TELEFONE1"] = "Telefone1";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["TELEFONE1"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["TELEFONE2"] = "Telefone2";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["TELEFONE2"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["CONTATO"] = "Contato";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["CONTATO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["CELULAR"] = "Celular";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["CELULAR"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["DIA_PGTO"] = "Dia de Pagamento";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["DIA_PGTO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["CNPJ"] = "CNPJ";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["CNPJ"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["IE"] = "IE";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["IE"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["FAX"] = "Fax";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["FAX"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["EMAIL"] = "E-Mail";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["EMAIL"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["DESCONTO"] = "Desconto";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["DESCONTO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["DIA_FECHAMENTO"] = "Dia de Fechamento";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["DIA_FECHAMENTO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["DIA_PAGAMENTO"] = "Dia de Pagamento";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["DIA_PAGAMENTO"] = "";
	$fieldLabelsC000071["Portuguese(Brazil)"]["LIMITE"] = "Limite";
	$fieldToolTipsC000071["Portuguese(Brazil)"]["LIMITE"] = "";
	if (count($fieldToolTipsC000071["Portuguese(Brazil)"]))
		$tdataC000071[".isUseToolTips"] = true;
}
	
	
	$tdataC000071[".NCSearch"] = true;



$tdataC000071[".shortTableName"] = "C000071";
$tdataC000071[".nSecOptions"] = 0;
$tdataC000071[".recsPerRowList"] = 1;
$tdataC000071[".mainTableOwnerID"] = "";
$tdataC000071[".moveNext"] = 1;
$tdataC000071[".nType"] = 0;

$tdataC000071[".strOriginalTableName"] = "C000071";




$tdataC000071[".showAddInPopup"] = false;

$tdataC000071[".showEditInPopup"] = false;

$tdataC000071[".showViewInPopup"] = false;

$tdataC000071[".fieldsForRegister"] = array();

$tdataC000071[".listAjax"] = false;

	$tdataC000071[".audit"] = false;

	$tdataC000071[".locking"] = false;

$tdataC000071[".listIcons"] = true;
$tdataC000071[".inlineEdit"] = true;
$tdataC000071[".inlineAdd"] = true;
$tdataC000071[".view"] = true;

$tdataC000071[".exportTo"] = true;

$tdataC000071[".printFriendly"] = true;


$tdataC000071[".showSimpleSearchOptions"] = false;

$tdataC000071[".showSearchPanel"] = true;

if (isMobile())
	$tdataC000071[".isUseAjaxSuggest"] = false;
else 
	$tdataC000071[".isUseAjaxSuggest"] = true;

$tdataC000071[".rowHighlite"] = true;

// button handlers file names

$tdataC000071[".addPageEvents"] = false;

// use timepicker for search panel
$tdataC000071[".isUseTimeForSearch"] = false;




$tdataC000071[".allSearchFields"] = array();

$tdataC000071[".allSearchFields"][] = "CODIGO";
$tdataC000071[".allSearchFields"][] = "NOME";
$tdataC000071[".allSearchFields"][] = "CNPJ";
$tdataC000071[".allSearchFields"][] = "IE";

$tdataC000071[".googleLikeFields"][] = "CODIGO";
$tdataC000071[".googleLikeFields"][] = "NOME";
$tdataC000071[".googleLikeFields"][] = "ENDERECO";
$tdataC000071[".googleLikeFields"][] = "BAIRRO";
$tdataC000071[".googleLikeFields"][] = "CIDADE";
$tdataC000071[".googleLikeFields"][] = "UF";
$tdataC000071[".googleLikeFields"][] = "CEP";
$tdataC000071[".googleLikeFields"][] = "TELEFONE1";
$tdataC000071[".googleLikeFields"][] = "TELEFONE2";
$tdataC000071[".googleLikeFields"][] = "CONTATO";
$tdataC000071[".googleLikeFields"][] = "CELULAR";
$tdataC000071[".googleLikeFields"][] = "DIA_PGTO";
$tdataC000071[".googleLikeFields"][] = "CNPJ";
$tdataC000071[".googleLikeFields"][] = "IE";
$tdataC000071[".googleLikeFields"][] = "FAX";
$tdataC000071[".googleLikeFields"][] = "EMAIL";
$tdataC000071[".googleLikeFields"][] = "DESCONTO";
$tdataC000071[".googleLikeFields"][] = "DIA_FECHAMENTO";
$tdataC000071[".googleLikeFields"][] = "DIA_PAGAMENTO";
$tdataC000071[".googleLikeFields"][] = "LIMITE";


$tdataC000071[".advSearchFields"][] = "CODIGO";
$tdataC000071[".advSearchFields"][] = "NOME";
$tdataC000071[".advSearchFields"][] = "CNPJ";
$tdataC000071[".advSearchFields"][] = "IE";

$tdataC000071[".isTableType"] = "list";

	



// Access doesn't support subqueries from the same table as main



$tdataC000071[".pageSize"] = 20;

$tstrOrderBy = "";
if(strlen($tstrOrderBy) && strtolower(substr($tstrOrderBy,0,8))!="order by")
	$tstrOrderBy = "order by ".$tstrOrderBy;
$tdataC000071[".strOrderBy"] = $tstrOrderBy;

$tdataC000071[".orderindexes"] = array();

$tdataC000071[".sqlHead"] = "SELECT CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  TELEFONE1,  TELEFONE2,  CONTATO,  CELULAR,  DIA_PGTO,  CNPJ,  IE,  FAX,  EMAIL,  DESCONTO,  DIA_FECHAMENTO,  DIA_PAGAMENTO,  LIMITE";
$tdataC000071[".sqlFrom"] = "FROM C000071";
$tdataC000071[".sqlWhereExpr"] = "";
$tdataC000071[".sqlTail"] = "";




//fill array of records per page for list and report without group fields
$arrRPP = array();
$arrRPP[] = 10;
$arrRPP[] = 20;
$arrRPP[] = 30;
$arrRPP[] = 50;
$arrRPP[] = 100;
$arrRPP[] = 500;
$arrRPP[] = -1;
$tdataC000071[".arrRecsPerPage"] = $arrRPP;

//fill array of groups per page for report with group fields
$arrGPP = array();
$arrGPP[] = 1;
$arrGPP[] = 3;
$arrGPP[] = 5;
$arrGPP[] = 10;
$arrGPP[] = 50;
$arrGPP[] = 100;
$arrGPP[] = -1;
$tdataC000071[".arrGroupsPerPage"] = $arrGPP;

$tableKeysC000071 = array();
$tableKeysC000071[] = "CODIGO";
$tdataC000071[".Keys"] = $tableKeysC000071;

$tdataC000071[".listFields"] = array();
$tdataC000071[".listFields"][] = "CODIGO";
$tdataC000071[".listFields"][] = "NOME";
$tdataC000071[".listFields"][] = "CIDADE";
$tdataC000071[".listFields"][] = "UF";
$tdataC000071[".listFields"][] = "TELEFONE1";
$tdataC000071[".listFields"][] = "CNPJ";
$tdataC000071[".listFields"][] = "IE";

$tdataC000071[".viewFields"] = array();
$tdataC000071[".viewFields"][] = "CODIGO";
$tdataC000071[".viewFields"][] = "NOME";
$tdataC000071[".viewFields"][] = "ENDERECO";
$tdataC000071[".viewFields"][] = "BAIRRO";
$tdataC000071[".viewFields"][] = "CIDADE";
$tdataC000071[".viewFields"][] = "UF";
$tdataC000071[".viewFields"][] = "CEP";
$tdataC000071[".viewFields"][] = "TELEFONE1";
$tdataC000071[".viewFields"][] = "TELEFONE2";
$tdataC000071[".viewFields"][] = "CONTATO";
$tdataC000071[".viewFields"][] = "CELULAR";
$tdataC000071[".viewFields"][] = "DIA_PGTO";
$tdataC000071[".viewFields"][] = "CNPJ";
$tdataC000071[".viewFields"][] = "IE";
$tdataC000071[".viewFields"][] = "FAX";
$tdataC000071[".viewFields"][] = "EMAIL";
$tdataC000071[".viewFields"][] = "DESCONTO";
$tdataC000071[".viewFields"][] = "DIA_FECHAMENTO";
$tdataC000071[".viewFields"][] = "DIA_PAGAMENTO";
$tdataC000071[".viewFields"][] = "LIMITE";

$tdataC000071[".addFields"] = array();

$tdataC000071[".inlineAddFields"] = array();

$tdataC000071[".editFields"] = array();

$tdataC000071[".inlineEditFields"] = array();

$tdataC000071[".exportFields"] = array();
$tdataC000071[".exportFields"][] = "CODIGO";
$tdataC000071[".exportFields"][] = "NOME";
$tdataC000071[".exportFields"][] = "ENDERECO";
$tdataC000071[".exportFields"][] = "BAIRRO";
$tdataC000071[".exportFields"][] = "CIDADE";
$tdataC000071[".exportFields"][] = "UF";
$tdataC000071[".exportFields"][] = "CEP";
$tdataC000071[".exportFields"][] = "TELEFONE1";
$tdataC000071[".exportFields"][] = "TELEFONE2";
$tdataC000071[".exportFields"][] = "CONTATO";
$tdataC000071[".exportFields"][] = "CELULAR";
$tdataC000071[".exportFields"][] = "DIA_PGTO";
$tdataC000071[".exportFields"][] = "CNPJ";
$tdataC000071[".exportFields"][] = "IE";
$tdataC000071[".exportFields"][] = "FAX";
$tdataC000071[".exportFields"][] = "EMAIL";
$tdataC000071[".exportFields"][] = "DESCONTO";
$tdataC000071[".exportFields"][] = "DIA_FECHAMENTO";
$tdataC000071[".exportFields"][] = "DIA_PAGAMENTO";
$tdataC000071[".exportFields"][] = "LIMITE";

$tdataC000071[".printFields"] = array();
$tdataC000071[".printFields"][] = "CODIGO";
$tdataC000071[".printFields"][] = "NOME";
$tdataC000071[".printFields"][] = "ENDERECO";
$tdataC000071[".printFields"][] = "BAIRRO";
$tdataC000071[".printFields"][] = "CIDADE";
$tdataC000071[".printFields"][] = "UF";
$tdataC000071[".printFields"][] = "CEP";
$tdataC000071[".printFields"][] = "TELEFONE1";
$tdataC000071[".printFields"][] = "TELEFONE2";
$tdataC000071[".printFields"][] = "CONTATO";
$tdataC000071[".printFields"][] = "CELULAR";
$tdataC000071[".printFields"][] = "DIA_PGTO";
$tdataC000071[".printFields"][] = "CNPJ";
$tdataC000071[".printFields"][] = "IE";
$tdataC000071[".printFields"][] = "FAX";
$tdataC000071[".printFields"][] = "EMAIL";
$tdataC000071[".printFields"][] = "DESCONTO";
$tdataC000071[".printFields"][] = "DIA_FECHAMENTO";
$tdataC000071[".printFields"][] = "DIA_PAGAMENTO";
$tdataC000071[".printFields"][] = "LIMITE";

//	CODIGO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 1;
	$fdata["strName"] = "CODIGO";
	$fdata["GoodName"] = "CODIGO";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["CODIGO"] = $fdata;
//	NOME
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 2;
	$fdata["strName"] = "NOME";
	$fdata["GoodName"] = "NOME";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Convênio"; 
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
			$edata["EditParams"].= " maxlength=80";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000071["NOME"] = $fdata;
//	ENDERECO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 3;
	$fdata["strName"] = "ENDERECO";
	$fdata["GoodName"] = "ENDERECO";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["ENDERECO"] = $fdata;
//	BAIRRO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 4;
	$fdata["strName"] = "BAIRRO";
	$fdata["GoodName"] = "BAIRRO";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["BAIRRO"] = $fdata;
//	CIDADE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 5;
	$fdata["strName"] = "CIDADE";
	$fdata["GoodName"] = "CIDADE";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["CIDADE"] = $fdata;
//	UF
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 6;
	$fdata["strName"] = "UF";
	$fdata["GoodName"] = "UF";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["UF"] = $fdata;
//	CEP
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 7;
	$fdata["strName"] = "CEP";
	$fdata["GoodName"] = "CEP";
	$fdata["ownerTable"] = "C000071";
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
			$edata["EditParams"].= " maxlength=15";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000071["CEP"] = $fdata;
//	TELEFONE1
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 8;
	$fdata["strName"] = "TELEFONE1";
	$fdata["GoodName"] = "TELEFONE1";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Telefone1"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
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
	
		
		
	$tdataC000071["TELEFONE1"] = $fdata;
//	TELEFONE2
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 9;
	$fdata["strName"] = "TELEFONE2";
	$fdata["GoodName"] = "TELEFONE2";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["TELEFONE2"] = $fdata;
//	CONTATO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 10;
	$fdata["strName"] = "CONTATO";
	$fdata["GoodName"] = "CONTATO";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Contato"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "CONTATO"; 
		$fdata["FullName"] = "CONTATO";
	
		
		
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
	
		
		
	$tdataC000071["CONTATO"] = $fdata;
//	CELULAR
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 11;
	$fdata["strName"] = "CELULAR";
	$fdata["GoodName"] = "CELULAR";
	$fdata["ownerTable"] = "C000071";
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
			$edata["EditParams"].= " maxlength=50";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000071["CELULAR"] = $fdata;
//	DIA_PGTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 12;
	$fdata["strName"] = "DIA_PGTO";
	$fdata["GoodName"] = "DIA_PGTO";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Dia de Pagamento"; 
	$fdata["FieldType"] = 200;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DIA_PGTO"; 
		$fdata["FullName"] = "DIA_PGTO";
	
		
		
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
	
		
		
	$tdataC000071["DIA_PGTO"] = $fdata;
//	CNPJ
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 13;
	$fdata["strName"] = "CNPJ";
	$fdata["GoodName"] = "CNPJ";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "CNPJ"; 
	$fdata["FieldType"] = 200;
	
		
		
		$fdata["bListPage"] = true; 
	
		
		
		
		
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
			$edata["EditParams"].= " maxlength=30";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000071["CNPJ"] = $fdata;
//	IE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 14;
	$fdata["strName"] = "IE";
	$fdata["GoodName"] = "IE";
	$fdata["ownerTable"] = "C000071";
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
			$edata["EditParams"].= " maxlength=30";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000071["IE"] = $fdata;
//	FAX
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 15;
	$fdata["strName"] = "FAX";
	$fdata["GoodName"] = "FAX";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Fax"; 
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
			$edata["EditParams"].= " maxlength=25";
	
		
//	Begin validation
	$edata["validateAs"] = array();
		
	//	End validation
	
		
				
		$fdata["EditFormats"]["edit"] = $edata;
//	End Edit Formats
	
		$fdata["isSeparate"] = false;
	
		
		
	$tdataC000071["FAX"] = $fdata;
//	EMAIL
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 16;
	$fdata["strName"] = "EMAIL";
	$fdata["GoodName"] = "EMAIL";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["EMAIL"] = $fdata;
//	DESCONTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 17;
	$fdata["strName"] = "DESCONTO";
	$fdata["GoodName"] = "DESCONTO";
	$fdata["ownerTable"] = "C000071";
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
	
		
		
	$tdataC000071["DESCONTO"] = $fdata;
//	DIA_FECHAMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 18;
	$fdata["strName"] = "DIA_FECHAMENTO";
	$fdata["GoodName"] = "DIA_FECHAMENTO";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Dia de Fechamento"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DIA_FECHAMENTO"; 
		$fdata["FullName"] = "DIA_FECHAMENTO";
	
		
		
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
	
		
		
	$tdataC000071["DIA_FECHAMENTO"] = $fdata;
//	DIA_PAGAMENTO
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 19;
	$fdata["strName"] = "DIA_PAGAMENTO";
	$fdata["GoodName"] = "DIA_PAGAMENTO";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Dia de Pagamento"; 
	$fdata["FieldType"] = 3;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "DIA_PAGAMENTO"; 
		$fdata["FullName"] = "DIA_PAGAMENTO";
	
		
		
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
	
		
		
	$tdataC000071["DIA_PAGAMENTO"] = $fdata;
//	LIMITE
//	Custom field settings
	$fdata = array();
	$fdata["Index"] = 20;
	$fdata["strName"] = "LIMITE";
	$fdata["GoodName"] = "LIMITE";
	$fdata["ownerTable"] = "C000071";
	$fdata["Label"] = "Limite"; 
	$fdata["FieldType"] = 131;
	
		
		
		
		
		
		
		
		$fdata["bViewPage"] = true; 
	
		
		$fdata["bPrinterPage"] = true; 
	
		$fdata["bExportPage"] = true; 
	
		$fdata["strField"] = "LIMITE"; 
		$fdata["FullName"] = "LIMITE";
	
		
		
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
	
		
		
	$tdataC000071["LIMITE"] = $fdata;

	
$tables_data["C000071"]=&$tdataC000071;
$field_labels["C000071"] = &$fieldLabelsC000071;
$fieldToolTips["C000071"] = &$fieldToolTipsC000071;

// -----------------start  prepare master-details data arrays ------------------------------//
// tables which are detail tables for current table (master)
$detailsTablesData["C000071"] = array();
	
// tables which are master tables for current table (detail)
$masterTablesData["C000071"] = array();

// -----------------end  prepare master-details data arrays ------------------------------//

require_once(getabspath("classes/sql.php"));










function createSqlQuery_C000071()
{
$proto0=array();
$proto0["m_strHead"] = "SELECT";
$proto0["m_strFieldList"] = "CODIGO,  NOME,  ENDERECO,  BAIRRO,  CIDADE,  UF,  CEP,  TELEFONE1,  TELEFONE2,  CONTATO,  CELULAR,  DIA_PGTO,  CNPJ,  IE,  FAX,  EMAIL,  DESCONTO,  DIA_FECHAMENTO,  DIA_PAGAMENTO,  LIMITE";
$proto0["m_strFrom"] = "FROM C000071";
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
	"m_strTable" => "C000071"
));

$proto5["m_expr"]=$obj;
$proto5["m_alias"] = "";
$obj = new SQLFieldListItem($proto5);

$proto0["m_fieldlist"][]=$obj;
						$proto7=array();
			$obj = new SQLField(array(
	"m_strName" => "NOME",
	"m_strTable" => "C000071"
));

$proto7["m_expr"]=$obj;
$proto7["m_alias"] = "";
$obj = new SQLFieldListItem($proto7);

$proto0["m_fieldlist"][]=$obj;
						$proto9=array();
			$obj = new SQLField(array(
	"m_strName" => "ENDERECO",
	"m_strTable" => "C000071"
));

$proto9["m_expr"]=$obj;
$proto9["m_alias"] = "";
$obj = new SQLFieldListItem($proto9);

$proto0["m_fieldlist"][]=$obj;
						$proto11=array();
			$obj = new SQLField(array(
	"m_strName" => "BAIRRO",
	"m_strTable" => "C000071"
));

$proto11["m_expr"]=$obj;
$proto11["m_alias"] = "";
$obj = new SQLFieldListItem($proto11);

$proto0["m_fieldlist"][]=$obj;
						$proto13=array();
			$obj = new SQLField(array(
	"m_strName" => "CIDADE",
	"m_strTable" => "C000071"
));

$proto13["m_expr"]=$obj;
$proto13["m_alias"] = "";
$obj = new SQLFieldListItem($proto13);

$proto0["m_fieldlist"][]=$obj;
						$proto15=array();
			$obj = new SQLField(array(
	"m_strName" => "UF",
	"m_strTable" => "C000071"
));

$proto15["m_expr"]=$obj;
$proto15["m_alias"] = "";
$obj = new SQLFieldListItem($proto15);

$proto0["m_fieldlist"][]=$obj;
						$proto17=array();
			$obj = new SQLField(array(
	"m_strName" => "CEP",
	"m_strTable" => "C000071"
));

$proto17["m_expr"]=$obj;
$proto17["m_alias"] = "";
$obj = new SQLFieldListItem($proto17);

$proto0["m_fieldlist"][]=$obj;
						$proto19=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE1",
	"m_strTable" => "C000071"
));

$proto19["m_expr"]=$obj;
$proto19["m_alias"] = "";
$obj = new SQLFieldListItem($proto19);

$proto0["m_fieldlist"][]=$obj;
						$proto21=array();
			$obj = new SQLField(array(
	"m_strName" => "TELEFONE2",
	"m_strTable" => "C000071"
));

$proto21["m_expr"]=$obj;
$proto21["m_alias"] = "";
$obj = new SQLFieldListItem($proto21);

$proto0["m_fieldlist"][]=$obj;
						$proto23=array();
			$obj = new SQLField(array(
	"m_strName" => "CONTATO",
	"m_strTable" => "C000071"
));

$proto23["m_expr"]=$obj;
$proto23["m_alias"] = "";
$obj = new SQLFieldListItem($proto23);

$proto0["m_fieldlist"][]=$obj;
						$proto25=array();
			$obj = new SQLField(array(
	"m_strName" => "CELULAR",
	"m_strTable" => "C000071"
));

$proto25["m_expr"]=$obj;
$proto25["m_alias"] = "";
$obj = new SQLFieldListItem($proto25);

$proto0["m_fieldlist"][]=$obj;
						$proto27=array();
			$obj = new SQLField(array(
	"m_strName" => "DIA_PGTO",
	"m_strTable" => "C000071"
));

$proto27["m_expr"]=$obj;
$proto27["m_alias"] = "";
$obj = new SQLFieldListItem($proto27);

$proto0["m_fieldlist"][]=$obj;
						$proto29=array();
			$obj = new SQLField(array(
	"m_strName" => "CNPJ",
	"m_strTable" => "C000071"
));

$proto29["m_expr"]=$obj;
$proto29["m_alias"] = "";
$obj = new SQLFieldListItem($proto29);

$proto0["m_fieldlist"][]=$obj;
						$proto31=array();
			$obj = new SQLField(array(
	"m_strName" => "IE",
	"m_strTable" => "C000071"
));

$proto31["m_expr"]=$obj;
$proto31["m_alias"] = "";
$obj = new SQLFieldListItem($proto31);

$proto0["m_fieldlist"][]=$obj;
						$proto33=array();
			$obj = new SQLField(array(
	"m_strName" => "FAX",
	"m_strTable" => "C000071"
));

$proto33["m_expr"]=$obj;
$proto33["m_alias"] = "";
$obj = new SQLFieldListItem($proto33);

$proto0["m_fieldlist"][]=$obj;
						$proto35=array();
			$obj = new SQLField(array(
	"m_strName" => "EMAIL",
	"m_strTable" => "C000071"
));

$proto35["m_expr"]=$obj;
$proto35["m_alias"] = "";
$obj = new SQLFieldListItem($proto35);

$proto0["m_fieldlist"][]=$obj;
						$proto37=array();
			$obj = new SQLField(array(
	"m_strName" => "DESCONTO",
	"m_strTable" => "C000071"
));

$proto37["m_expr"]=$obj;
$proto37["m_alias"] = "";
$obj = new SQLFieldListItem($proto37);

$proto0["m_fieldlist"][]=$obj;
						$proto39=array();
			$obj = new SQLField(array(
	"m_strName" => "DIA_FECHAMENTO",
	"m_strTable" => "C000071"
));

$proto39["m_expr"]=$obj;
$proto39["m_alias"] = "";
$obj = new SQLFieldListItem($proto39);

$proto0["m_fieldlist"][]=$obj;
						$proto41=array();
			$obj = new SQLField(array(
	"m_strName" => "DIA_PAGAMENTO",
	"m_strTable" => "C000071"
));

$proto41["m_expr"]=$obj;
$proto41["m_alias"] = "";
$obj = new SQLFieldListItem($proto41);

$proto0["m_fieldlist"][]=$obj;
						$proto43=array();
			$obj = new SQLField(array(
	"m_strName" => "LIMITE",
	"m_strTable" => "C000071"
));

$proto43["m_expr"]=$obj;
$proto43["m_alias"] = "";
$obj = new SQLFieldListItem($proto43);

$proto0["m_fieldlist"][]=$obj;
$proto0["m_fromlist"] = array();
												$proto45=array();
$proto45["m_link"] = "SQLL_MAIN";
			$proto46=array();
$proto46["m_strName"] = "C000071";
$proto46["m_columns"] = array();
$proto46["m_columns"][] = "CODIGO";
$proto46["m_columns"][] = "NOME";
$proto46["m_columns"][] = "ENDERECO";
$proto46["m_columns"][] = "BAIRRO";
$proto46["m_columns"][] = "CIDADE";
$proto46["m_columns"][] = "UF";
$proto46["m_columns"][] = "CEP";
$proto46["m_columns"][] = "TELEFONE1";
$proto46["m_columns"][] = "TELEFONE2";
$proto46["m_columns"][] = "CONTATO";
$proto46["m_columns"][] = "CELULAR";
$proto46["m_columns"][] = "DIA_PGTO";
$proto46["m_columns"][] = "CNPJ";
$proto46["m_columns"][] = "IE";
$proto46["m_columns"][] = "DATA_CADASTRO";
$proto46["m_columns"][] = "FAX";
$proto46["m_columns"][] = "EMAIL";
$proto46["m_columns"][] = "DESCONTO";
$proto46["m_columns"][] = "DIA_FECHAMENTO";
$proto46["m_columns"][] = "DIA_PAGAMENTO";
$proto46["m_columns"][] = "LIMITE";
$proto46["m_columns"][] = "NUMERO";
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
$queryData_C000071 = createSqlQuery_C000071();
																				$tdataC000071[".sqlquery"] = $queryData_C000071;
	
if(isset($tdataC000071["field2"])){
	$tdataC000071["field2"]["LookupTable"] = "carscars_view";
	$tdataC000071["field2"]["LookupOrderBy"] = "name";
	$tdataC000071["field2"]["LookupType"] = 4;
	$tdataC000071["field2"]["LinkField"] = "email";
	$tdataC000071["field2"]["DisplayField"] = "name";
	$tdataC000071[".hasCustomViewField"] = true;
}

$tableEvents["C000071"] = new eventsBase;
$tdataC000071[".hasEvents"] = false;

$cipherer = new RunnerCipherer("C000071");

?>