<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("include/C000013_variables.php");
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
$layout->blocks["top"][] = "details";$page_layouts["C000013_view"] = $layout;




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
	header("Location: C000013_list.php?a=return");
	exit();
}

$out = "";
$first = true;
$fieldsArr = array();
$arr = array();
$arr['fName'] = "NUMERO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NUMERO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TITULAR_CONTA_PADRAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TITULAR_CONTA_PADRAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "BANCO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("BANCO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CARTAO_CREDITO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CARTAO_CREDITO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "FINANCEIRA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("FINANCEIRA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "RESSARCIMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("RESSARCIMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "PRAZO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("PRAZO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COMISSAO_CREDITO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COMISSAO_CREDITO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COMISSAO_DEBITO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COMISSAO_DEBITO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "REC_DEBITO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("REC_DEBITO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "REC_CREDITO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("REC_CREDITO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CONTA_PADRAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CONTA_PADRAO");
$fieldsArr[] = $arr;

$mainTableOwnerID = $pageObject->pSet->getTableOwnerIdField();
$ownerIdValue="";

$pageObject->setGoogleMapsParams($fieldsArr);

while($data)
{
	$xt->assign("show_key1", htmlspecialchars($pageObject->showDBValue("NUMERO", $data)));

	$keylink="";
	$keylink.="&key1=".htmlspecialchars(rawurlencode(@$data["NUMERO"]));

////////////////////////////////////////////
//NUMERO - 
	
	$value = $pageObject->showDBValue("NUMERO", $data, $keylink);
	if($mainTableOwnerID=="NUMERO")
		$ownerIdValue=$value;
	$xt->assign("NUMERO_value",$value);
	if(!$pageObject->isAppearOnTabs("NUMERO"))
		$xt->assign("NUMERO_fieldblock",true);
	else
		$xt->assign("NUMERO_tabfieldblock",true);
////////////////////////////////////////////
//TITULAR_CONTA_PADRAO - 
	
	$value = $pageObject->showDBValue("TITULAR_CONTA_PADRAO", $data, $keylink);
	if($mainTableOwnerID=="TITULAR_CONTA_PADRAO")
		$ownerIdValue=$value;
	$xt->assign("TITULAR_CONTA_PADRAO_value",$value);
	if(!$pageObject->isAppearOnTabs("TITULAR_CONTA_PADRAO"))
		$xt->assign("TITULAR_CONTA_PADRAO_fieldblock",true);
	else
		$xt->assign("TITULAR_CONTA_PADRAO_tabfieldblock",true);
////////////////////////////////////////////
//BANCO - 
	
	$value = $pageObject->showDBValue("BANCO", $data, $keylink);
	if($mainTableOwnerID=="BANCO")
		$ownerIdValue=$value;
	$xt->assign("BANCO_value",$value);
	if(!$pageObject->isAppearOnTabs("BANCO"))
		$xt->assign("BANCO_fieldblock",true);
	else
		$xt->assign("BANCO_tabfieldblock",true);
////////////////////////////////////////////
//CARTAO_CREDITO - 
	
	$value = $pageObject->showDBValue("CARTAO_CREDITO", $data, $keylink);
	if($mainTableOwnerID=="CARTAO_CREDITO")
		$ownerIdValue=$value;
	$xt->assign("CARTAO_CREDITO_value",$value);
	if(!$pageObject->isAppearOnTabs("CARTAO_CREDITO"))
		$xt->assign("CARTAO_CREDITO_fieldblock",true);
	else
		$xt->assign("CARTAO_CREDITO_tabfieldblock",true);
////////////////////////////////////////////
//FINANCEIRA - 
	
	$value = $pageObject->showDBValue("FINANCEIRA", $data, $keylink);
	if($mainTableOwnerID=="FINANCEIRA")
		$ownerIdValue=$value;
	$xt->assign("FINANCEIRA_value",$value);
	if(!$pageObject->isAppearOnTabs("FINANCEIRA"))
		$xt->assign("FINANCEIRA_fieldblock",true);
	else
		$xt->assign("FINANCEIRA_tabfieldblock",true);
////////////////////////////////////////////
//RESSARCIMENTO - 
	
	$value = $pageObject->showDBValue("RESSARCIMENTO", $data, $keylink);
	if($mainTableOwnerID=="RESSARCIMENTO")
		$ownerIdValue=$value;
	$xt->assign("RESSARCIMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("RESSARCIMENTO"))
		$xt->assign("RESSARCIMENTO_fieldblock",true);
	else
		$xt->assign("RESSARCIMENTO_tabfieldblock",true);
////////////////////////////////////////////
//PRAZO - 
	
	$value = $pageObject->showDBValue("PRAZO", $data, $keylink);
	if($mainTableOwnerID=="PRAZO")
		$ownerIdValue=$value;
	$xt->assign("PRAZO_value",$value);
	if(!$pageObject->isAppearOnTabs("PRAZO"))
		$xt->assign("PRAZO_fieldblock",true);
	else
		$xt->assign("PRAZO_tabfieldblock",true);
////////////////////////////////////////////
//COMISSAO_CREDITO - Number
	
	$value = $pageObject->showDBValue("COMISSAO_CREDITO", $data, $keylink);
	if($mainTableOwnerID=="COMISSAO_CREDITO")
		$ownerIdValue=$value;
	$xt->assign("COMISSAO_CREDITO_value",$value);
	if(!$pageObject->isAppearOnTabs("COMISSAO_CREDITO"))
		$xt->assign("COMISSAO_CREDITO_fieldblock",true);
	else
		$xt->assign("COMISSAO_CREDITO_tabfieldblock",true);
////////////////////////////////////////////
//COMISSAO_DEBITO - Number
	
	$value = $pageObject->showDBValue("COMISSAO_DEBITO", $data, $keylink);
	if($mainTableOwnerID=="COMISSAO_DEBITO")
		$ownerIdValue=$value;
	$xt->assign("COMISSAO_DEBITO_value",$value);
	if(!$pageObject->isAppearOnTabs("COMISSAO_DEBITO"))
		$xt->assign("COMISSAO_DEBITO_fieldblock",true);
	else
		$xt->assign("COMISSAO_DEBITO_tabfieldblock",true);
////////////////////////////////////////////
//REC_DEBITO - 
	
	$value = $pageObject->showDBValue("REC_DEBITO", $data, $keylink);
	if($mainTableOwnerID=="REC_DEBITO")
		$ownerIdValue=$value;
	$xt->assign("REC_DEBITO_value",$value);
	if(!$pageObject->isAppearOnTabs("REC_DEBITO"))
		$xt->assign("REC_DEBITO_fieldblock",true);
	else
		$xt->assign("REC_DEBITO_tabfieldblock",true);
////////////////////////////////////////////
//REC_CREDITO - 
	
	$value = $pageObject->showDBValue("REC_CREDITO", $data, $keylink);
	if($mainTableOwnerID=="REC_CREDITO")
		$ownerIdValue=$value;
	$xt->assign("REC_CREDITO_value",$value);
	if(!$pageObject->isAppearOnTabs("REC_CREDITO"))
		$xt->assign("REC_CREDITO_fieldblock",true);
	else
		$xt->assign("REC_CREDITO_tabfieldblock",true);
////////////////////////////////////////////
//CONTA_PADRAO - 
	
	$value = $pageObject->showDBValue("CONTA_PADRAO", $data, $keylink);
	if($mainTableOwnerID=="CONTA_PADRAO")
		$ownerIdValue=$value;
	$xt->assign("CONTA_PADRAO_value",$value);
	if(!$pageObject->isAppearOnTabs("CONTA_PADRAO"))
		$xt->assign("CONTA_PADRAO_fieldblock",true);
	else
		$xt->assign("CONTA_PADRAO_tabfieldblock",true);

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
		$options['masterTable'] = "C000013";
		$options['firstTime'] = 1;
		
		$strTableName = $dpParams['strTableNames'][$d];
		include_once("include/".GetTableURL($strTableName)."_settings.php");
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{
			$strTableName = "C000013";
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
	$strTableName = "C000013";
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
$xt->assign("editlink_attrs","id=\"editLink".$id."\" name=\"editLink".$id."\" onclick=\"window.location.href='C000013_edit.php?".$editlink."'\"");

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
