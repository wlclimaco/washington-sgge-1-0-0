<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("include/C000008_variables.php");
include('include/xtempl.php');
include('classes/viewpage.php');
include("classes/searchclause.php");

add_nocache_headers();

//	check if logged in
if(!isLogged() || CheckPermissionsEvent($strTableName, 'S') && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
{ 
	$_SESSION["MyURL"]=$_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
	header("Location: login.php?message=expired"); 
	return;
}

$layout = new TLayout("view3","BoldGreenTea","MobileGreenTea");
$layout->blocks["top"] = array();
$layout->skins["pdf"] = "empty";
$layout->blocks["top"][] = "pdf";
$layout->containers["view"] = array();

$layout->containers["view"][] = array("name"=>"viewheader","block"=>"","substyle"=>2);


$layout->containers["view"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"fields");


$layout->containers["fields"] = array();

$layout->containers["fields"][] = array("name"=>"viewfields","block"=>"","substyle"=>1);


$layout->containers["fields"][] = array("name"=>"viewbuttons","block"=>"","substyle"=>2);


$layout->skins["fields"] = "fields";

$layout->skins["view"] = "1";
$layout->blocks["top"][] = "view";
$layout->skins["details"] = "empty";
$layout->blocks["top"][] = "details";$page_layouts["C000008_view"] = $layout;




//$cipherer = new RunnerCipherer($strTableName);
	
$xt = new Xtempl();

$query = $gQuery->Copy();

$filename = "";	
$message = "";
$key = array();
$next = array();
$prev = array();
$all = postvalue("all");
$pdf = postvalue("pdf");
$mypage = 1;

//Show view page as popUp or not
$inlineview = (postvalue("onFly") ? true : false);

//If show view as popUp, get parent Id
if($inlineview)
	$parId = postvalue("parId");
else
	$parId = 0;

//Set page id	
if(postvalue("id"))
	$id = postvalue("id");
else
	$id = 1;

//$isNeedSettings = true;//($inlineview && postvalue("isNeedSettings") == 'true') || (!$inlineview);	
	
// assign an id
$xt->assign("id",$id);

//array of params for classes
$params = array("pageType" => PAGE_VIEW, "id" => $id, "tName" => $strTableName);
$params["xt"] = &$xt;
$params["all"] = $all;

//Get array of tabs for edit page
$params['useTabsOnView'] = $gSettings->useTabsOnView();
if($params['useTabsOnView'])
	$params['arrViewTabs'] = $gSettings->getViewTabs();
$pageObject = new ViewPage($params);

// SearchClause class stuff
$pageObject->searchClauseObj->parseRequest();
$_SESSION[$strTableName.'_advsearch'] = serialize($pageObject->searchClauseObj);

// proccess big google maps

// add button events if exist
$pageObject->addButtonHandlers();

//For show detail tables on master page view
$dpParams = array();
if($pageObject->isShowDetailTables && !isMobile())
{
	$ids = $id;
	$pageObject->jsSettings['tableSettings'][$strTableName]['dpParams'] = array();
}

//	Before Process event
if($eventObj->exists("BeforeProcessView"))
	$eventObj->BeforeProcessView($conn, $pageObject);
	
//	read current values from the database
$data = $pageObject->getCurrentRecordInternal();

if (!sizeof($data)) {
	header("Location: C000008_list.php?a=return");
	exit();
}

$out = "";
$first = true;
$fieldsArr = array();
$arr = array();
$arr['fName'] = "CODIGO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODIGO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NOME";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NOME");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "FUNCAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("FUNCAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "ENDERECO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ENDERECO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "BAIRRO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("BAIRRO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CIDADE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CIDADE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "UF";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("UF");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CEP";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CEP");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TELEFONE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TELEFONE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CELULAR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CELULAR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "EMAIL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("EMAIL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CPF";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CPF");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "RG";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("RG");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_ADMISSAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_ADMISSAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_DEMISSAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_DEMISSAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COMISSAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COMISSAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NASCIMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NASCIMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SALARIO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SALARIO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "OBS1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("OBS1");
$fieldsArr[] = $arr;

$mainTableOwnerID = $pageObject->pSet->getTableOwnerIdField();
$ownerIdValue="";

$pageObject->setGoogleMapsParams($fieldsArr);

while($data)
{
	$xt->assign("show_key1", htmlspecialchars($pageObject->showDBValue("CODIGO", $data)));

	$keylink="";
	$keylink.="&key1=".htmlspecialchars(rawurlencode(@$data["CODIGO"]));

////////////////////////////////////////////
//CODIGO - 
	
	$value = $pageObject->showDBValue("CODIGO", $data, $keylink);
	if($mainTableOwnerID=="CODIGO")
		$ownerIdValue=$value;
	$xt->assign("CODIGO_value",$value);
	if(!$pageObject->isAppearOnTabs("CODIGO"))
		$xt->assign("CODIGO_fieldblock",true);
	else
		$xt->assign("CODIGO_tabfieldblock",true);
////////////////////////////////////////////
//NOME - 
	
	$value = $pageObject->showDBValue("NOME", $data, $keylink);
	if($mainTableOwnerID=="NOME")
		$ownerIdValue=$value;
	$xt->assign("NOME_value",$value);
	if(!$pageObject->isAppearOnTabs("NOME"))
		$xt->assign("NOME_fieldblock",true);
	else
		$xt->assign("NOME_tabfieldblock",true);
////////////////////////////////////////////
//FUNCAO - 
	
	$value = $pageObject->showDBValue("FUNCAO", $data, $keylink);
	if($mainTableOwnerID=="FUNCAO")
		$ownerIdValue=$value;
	$xt->assign("FUNCAO_value",$value);
	if(!$pageObject->isAppearOnTabs("FUNCAO"))
		$xt->assign("FUNCAO_fieldblock",true);
	else
		$xt->assign("FUNCAO_tabfieldblock",true);
////////////////////////////////////////////
//SITUACAO - 
	
	$value = $pageObject->showDBValue("SITUACAO", $data, $keylink);
	if($mainTableOwnerID=="SITUACAO")
		$ownerIdValue=$value;
	$xt->assign("SITUACAO_value",$value);
	if(!$pageObject->isAppearOnTabs("SITUACAO"))
		$xt->assign("SITUACAO_fieldblock",true);
	else
		$xt->assign("SITUACAO_tabfieldblock",true);
////////////////////////////////////////////
//ENDERECO - 
	
	$value = $pageObject->showDBValue("ENDERECO", $data, $keylink);
	if($mainTableOwnerID=="ENDERECO")
		$ownerIdValue=$value;
	$xt->assign("ENDERECO_value",$value);
	if(!$pageObject->isAppearOnTabs("ENDERECO"))
		$xt->assign("ENDERECO_fieldblock",true);
	else
		$xt->assign("ENDERECO_tabfieldblock",true);
////////////////////////////////////////////
//BAIRRO - 
	
	$value = $pageObject->showDBValue("BAIRRO", $data, $keylink);
	if($mainTableOwnerID=="BAIRRO")
		$ownerIdValue=$value;
	$xt->assign("BAIRRO_value",$value);
	if(!$pageObject->isAppearOnTabs("BAIRRO"))
		$xt->assign("BAIRRO_fieldblock",true);
	else
		$xt->assign("BAIRRO_tabfieldblock",true);
////////////////////////////////////////////
//CIDADE - 
	
	$value = $pageObject->showDBValue("CIDADE", $data, $keylink);
	if($mainTableOwnerID=="CIDADE")
		$ownerIdValue=$value;
	$xt->assign("CIDADE_value",$value);
	if(!$pageObject->isAppearOnTabs("CIDADE"))
		$xt->assign("CIDADE_fieldblock",true);
	else
		$xt->assign("CIDADE_tabfieldblock",true);
////////////////////////////////////////////
//UF - 
	
	$value = $pageObject->showDBValue("UF", $data, $keylink);
	if($mainTableOwnerID=="UF")
		$ownerIdValue=$value;
	$xt->assign("UF_value",$value);
	if(!$pageObject->isAppearOnTabs("UF"))
		$xt->assign("UF_fieldblock",true);
	else
		$xt->assign("UF_tabfieldblock",true);
////////////////////////////////////////////
//CEP - 
	
	$value = $pageObject->showDBValue("CEP", $data, $keylink);
	if($mainTableOwnerID=="CEP")
		$ownerIdValue=$value;
	$xt->assign("CEP_value",$value);
	if(!$pageObject->isAppearOnTabs("CEP"))
		$xt->assign("CEP_fieldblock",true);
	else
		$xt->assign("CEP_tabfieldblock",true);
////////////////////////////////////////////
//TELEFONE - 
	
	$value = $pageObject->showDBValue("TELEFONE", $data, $keylink);
	if($mainTableOwnerID=="TELEFONE")
		$ownerIdValue=$value;
	$xt->assign("TELEFONE_value",$value);
	if(!$pageObject->isAppearOnTabs("TELEFONE"))
		$xt->assign("TELEFONE_fieldblock",true);
	else
		$xt->assign("TELEFONE_tabfieldblock",true);
////////////////////////////////////////////
//CELULAR - 
	
	$value = $pageObject->showDBValue("CELULAR", $data, $keylink);
	if($mainTableOwnerID=="CELULAR")
		$ownerIdValue=$value;
	$xt->assign("CELULAR_value",$value);
	if(!$pageObject->isAppearOnTabs("CELULAR"))
		$xt->assign("CELULAR_fieldblock",true);
	else
		$xt->assign("CELULAR_tabfieldblock",true);
////////////////////////////////////////////
//EMAIL - 
	
	$value = $pageObject->showDBValue("EMAIL", $data, $keylink);
	if($mainTableOwnerID=="EMAIL")
		$ownerIdValue=$value;
	$xt->assign("EMAIL_value",$value);
	if(!$pageObject->isAppearOnTabs("EMAIL"))
		$xt->assign("EMAIL_fieldblock",true);
	else
		$xt->assign("EMAIL_tabfieldblock",true);
////////////////////////////////////////////
//CPF - 
	
	$value = $pageObject->showDBValue("CPF", $data, $keylink);
	if($mainTableOwnerID=="CPF")
		$ownerIdValue=$value;
	$xt->assign("CPF_value",$value);
	if(!$pageObject->isAppearOnTabs("CPF"))
		$xt->assign("CPF_fieldblock",true);
	else
		$xt->assign("CPF_tabfieldblock",true);
////////////////////////////////////////////
//RG - 
	
	$value = $pageObject->showDBValue("RG", $data, $keylink);
	if($mainTableOwnerID=="RG")
		$ownerIdValue=$value;
	$xt->assign("RG_value",$value);
	if(!$pageObject->isAppearOnTabs("RG"))
		$xt->assign("RG_fieldblock",true);
	else
		$xt->assign("RG_tabfieldblock",true);
////////////////////////////////////////////
//DATA_ADMISSAO - Short Date
	
	$value = $pageObject->showDBValue("DATA_ADMISSAO", $data, $keylink);
	if($mainTableOwnerID=="DATA_ADMISSAO")
		$ownerIdValue=$value;
	$xt->assign("DATA_ADMISSAO_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_ADMISSAO"))
		$xt->assign("DATA_ADMISSAO_fieldblock",true);
	else
		$xt->assign("DATA_ADMISSAO_tabfieldblock",true);
////////////////////////////////////////////
//DATA_DEMISSAO - Short Date
	
	$value = $pageObject->showDBValue("DATA_DEMISSAO", $data, $keylink);
	if($mainTableOwnerID=="DATA_DEMISSAO")
		$ownerIdValue=$value;
	$xt->assign("DATA_DEMISSAO_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_DEMISSAO"))
		$xt->assign("DATA_DEMISSAO_fieldblock",true);
	else
		$xt->assign("DATA_DEMISSAO_tabfieldblock",true);
////////////////////////////////////////////
//COMISSAO - Number
	
	$value = $pageObject->showDBValue("COMISSAO", $data, $keylink);
	if($mainTableOwnerID=="COMISSAO")
		$ownerIdValue=$value;
	$xt->assign("COMISSAO_value",$value);
	if(!$pageObject->isAppearOnTabs("COMISSAO"))
		$xt->assign("COMISSAO_fieldblock",true);
	else
		$xt->assign("COMISSAO_tabfieldblock",true);
////////////////////////////////////////////
//NASCIMENTO - Short Date
	
	$value = $pageObject->showDBValue("NASCIMENTO", $data, $keylink);
	if($mainTableOwnerID=="NASCIMENTO")
		$ownerIdValue=$value;
	$xt->assign("NASCIMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("NASCIMENTO"))
		$xt->assign("NASCIMENTO_fieldblock",true);
	else
		$xt->assign("NASCIMENTO_tabfieldblock",true);
////////////////////////////////////////////
//SALARIO - Number
	
	$value = $pageObject->showDBValue("SALARIO", $data, $keylink);
	if($mainTableOwnerID=="SALARIO")
		$ownerIdValue=$value;
	$xt->assign("SALARIO_value",$value);
	if(!$pageObject->isAppearOnTabs("SALARIO"))
		$xt->assign("SALARIO_fieldblock",true);
	else
		$xt->assign("SALARIO_tabfieldblock",true);
////////////////////////////////////////////
//OBS1 - 
	
	$value = $pageObject->showDBValue("OBS1", $data, $keylink);
	if($mainTableOwnerID=="OBS1")
		$ownerIdValue=$value;
	$xt->assign("OBS1_value",$value);
	if(!$pageObject->isAppearOnTabs("OBS1"))
		$xt->assign("OBS1_fieldblock",true);
	else
		$xt->assign("OBS1_tabfieldblock",true);

/////////////////////////////////////////////////////////////
if($pageObject->isShowDetailTables && !isMobile())
{
	if(count($dpParams['ids']))
	{
		$xt->assign("detail_tables",true);
		include('classes/listpage.php');
		include('classes/listpage_embed.php');
		include('classes/listpage_dpinline.php');
	}
	
	$dControlsMap = array();
	$dViewControlsMap = array();
	
	for($d=0;$d<count($dpParams['ids']);$d++)
	{
		$options = array();
		//array of params for classes
		$options["mode"] = LIST_DETAILS;
		$options["pageType"] = PAGE_LIST;
		$options["masterPageType"] = PAGE_VIEW;
		$options["mainMasterPageType"] = PAGE_VIEW;
		$options['masterTable'] = "C000008";
		$options['firstTime'] = 1;
		
		$strTableName = $dpParams['strTableNames'][$d];
		include_once("include/".GetTableURL($strTableName)."_settings.php");
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{
			$strTableName = "C000008";
			continue;
		}
		
		$layout = GetPageLayout(GoodFieldName($strTableName), PAGE_LIST);
		if($layout)
		{
			$rtl = $xt->getReadingOrder() == 'RTL' ? 'RTL' : '';
			$xt->cssFiles[] = array("stylepath" => "styles/".$layout->style.'/style'.$rtl.".css"
				, "pagestylepath" => "pagestyles/".$layout->name.$rtl.".css");
			$xt->IEcssFiles[] = array("stylepathIE" => "styles/".$layout->style.'/styleIE'.".css");
		}
		
		$options['xt'] = new Xtempl();
		$options['id'] = $dpParams['ids'][$d];
		$options['flyId'] = $pageObject->genId()+1;
		$mkr = 1;
		foreach($mKeys[$strTableName] as $mk)
			$options['masterKeysReq'][$mkr++] = $data[$mk];

		$listPageObject = ListPage::createListPage($strTableName, $options);
		
		// prepare code
		$listPageObject->prepareForBuildPage();
		
		// show page
		if($listPageObject->permis[$strTableName]['search'] && $listPageObject->rowsFound)
		{
			//set page events
			foreach($listPageObject->eventsObject->events as $event => $name)
				$listPageObject->xt->assign_event($event, $listPageObject->eventsObject, $event, array());
			
			//add detail settings to master settings
			$listPageObject->addControlsJSAndCSS();
			$listPageObject->fillSetCntrlMaps();
			$pageObject->jsSettings['tableSettings'][$strTableName]	= $listPageObject->jsSettings['tableSettings'][$strTableName];
			$dControlsMap[$strTableName] = $listPageObject->controlsMap;
			$dViewControlsMap[$strTableName] = $listPageObject->viewControlsMap;
			foreach($listPageObject->jsSettings['global']['shortTNames'] as $keySet=>$val)
			{
				if(!array_key_exists($keySet,$pageObject->settingsMap["globalSettings"]['shortTNames']))
					$pageObject->settingsMap["globalSettings"]['shortTNames'][$keySet] = $val;
			}
			
			//Add detail's js files to master's files
			$pageObject->copyAllJSFiles($listPageObject->grabAllJSFiles());
			
			//Add detail's css files to master's files
			$pageObject->copyAllCSSFiles($listPageObject->grabAllCSSFiles());
		
			$xtParams = array("method"=>'showPage', "params"=> false);
			$xtParams['object'] = $listPageObject;
			$xt->assign("displayDetailTable_".GoodFieldName($listPageObject->tName), $xtParams);
		
			$pageObject->controlsMap['dpTablesParams'][] = array('tName'=>$strTableName, 'id'=>$options['id']);
		}
	}
	$pageObject->controlsMap['dControlsMap'] = $dControlsMap;
	$pageObject->viewControlsMap['dViewControlsMap'] = $dViewControlsMap; 
	$strTableName = "C000008";
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Begin prepare for Next Prev button
if(!@$_SESSION[$strTableName."_noNextPrev"] && !$inlineview && !$pdf)
{
	$pageObject->getNextPrevRecordKeys($data,"Search",$next,$prev);
}
//End prepare for Next Prev button
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


if ($pageObject->googleMapCfg['isUseGoogleMap'])
{
	$pageObject->initGmaps();
}

$pageObject->addCommonJs();

//fill tab groups name and sections name to controls
$pageObject->fillCntrlTabGroups();

if(!$inlineview)
{
	$pageObject->body["begin"].="<script type=\"text/javascript\" src=\"include/loadfirst.js\"></script>\r\n";
		$pageObject->body["begin"].= "<script type=\"text/javascript\" src=\"include/lang/".getLangFileName(mlang_getcurrentlang()).".js\"></script>";		
	
	$pageObject->jsSettings['tableSettings'][$strTableName]["keys"] = $pageObject->jsKeys;
	$pageObject->jsSettings['tableSettings'][$strTableName]['keyFields'] = $pageObject->keyFields;
	$pageObject->jsSettings['tableSettings'][$strTableName]["prevKeys"] = $prev;
	$pageObject->jsSettings['tableSettings'][$strTableName]["nextKeys"] = $next; 
	
	// assign body end
	$pageObject->body['end'] = array();
	$pageObject->body['end']["method"] = "assignBodyEnd";
	$pageObject->body['end']["object"] = &$pageObject;
	
	$xt->assign("body",$pageObject->body);
	$xt->assign("flybody",true);
}
else
{
	$xt->assign("footer",false);
	$xt->assign("header",false);
	$xt->assign("flybody",$pageObject->body);
	$xt->assign("body",true);
	$xt->assign("pdflink_block",false);
	
	$pageObject->fillSetCntrlMaps();
	
	$returnJSON['controlsMap'] = $pageObject->controlsHTMLMap;
	$returnJSON['viewControlsMap'] = $pageObject->viewControlsHTMLMap;
	$returnJSON['settings'] = $pageObject->jsSettings;
}
$xt->assign("style_block",true);
$xt->assign("stylefiles_block",true);

$editlink="";
$editkeys=array();
	$editkeys["editid1"]=postvalue("editid1");
foreach($editkeys as $key=>$val)
{
	if($editlink)
		$editlink.="&";
	$editlink.=$key."=".$val;
}
$xt->assign("editlink_attrs","id=\"editLink".$id."\" name=\"editLink".$id."\" onclick=\"window.location.href='C000008_edit.php?".$editlink."'\"");

$strPerm = GetUserPermissions($strTableName);
if(CheckSecurity($ownerIdValue,"Edit") && !$inlineview && strpos($strPerm, "E")!==false)
	$xt->assign("edit_button",true);
else
	$xt->assign("edit_button",false);

if(!$pdf && !$all && !$inlineview)
{
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Begin show Next Prev button
	$nextlink=$prevlink="";
	if(count($next))
	{
		$xt->assign("next_button",true);
	 		$nextlink .="editid1=".htmlspecialchars(rawurlencode($next[1-1]));
		$xt->assign("nextbutton_attrs","id=\"nextButton".$id."\"");
	}
	else 
		$xt->assign("next_button",false);
	if(count($prev))
	{
		$xt->assign("prev_button",true);
			$prevlink .="editid1=".htmlspecialchars(rawurlencode($prev[1-1]));
		$xt->assign("prevbutton_attrs","id=\"prevButton".$id."\"");
	}
	else 
		$xt->assign("prev_button",false);
//End show Next Prev button
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	$xt->assign("back_button",true);
	$xt->assign("backbutton_attrs","id=\"backButton".$id."\"");
}

$oldtemplatefile = $pageObject->templatefile;

if(!$all)
{
	if($eventObj->exists("BeforeShowView"))
	{
		$templatefile = $pageObject->templatefile;
		$eventObj->BeforeShowView($xt,$templatefile,$data, $pageObject);
		$pageObject->templatefile = $templatefile;
	}
	if(!$pdf)
	{
		if(!$inlineview)
			$xt->display($pageObject->templatefile);
		else{
				$xt->load_template($pageObject->templatefile);
				$returnJSON['html'] = $xt->fetch_loaded('style_block').$xt->fetch_loaded('body');
				if(count($pageObject->includes_css))
					$returnJSON['CSSFiles'] = array_unique($pageObject->includes_css);
				if(count($pageObject->includes_cssIE))
					$returnJSON['CSSFilesIE'] = array_unique($pageObject->includes_cssIE);				
				$returnJSON['idStartFrom'] = $id+1;
				$returnJSON["additionalJS"] = $pageObject->grabAllJsFiles();
				echo (my_json_encode($returnJSON)); 
			}
	}
	break;
}
}


?>
