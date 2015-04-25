<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("include/C000009_variables.php");
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
$layout->blocks["top"][] = "details";$page_layouts["C000009_view"] = $layout;




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
	header("Location: C000009_list.php?a=return");
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
$arr['fName'] = "FANTASIA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("FANTASIA");
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
$arr['fName'] = "COMPLEMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COMPLEMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TELEFONE1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TELEFONE1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TELEFONE2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TELEFONE2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "FAX";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("FAX");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CONTATO1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CONTATO1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CONTATO2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CONTATO2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CELULAR1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CELULAR1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CELULAR2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CELULAR2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "EMAIL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("EMAIL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "HOMEPAGE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("HOMEPAGE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CNPJ";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CNPJ");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "IE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("IE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "OBS1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("OBS1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "IM";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("IM");
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
//FANTASIA - 
	
	$value = $pageObject->showDBValue("FANTASIA", $data, $keylink);
	if($mainTableOwnerID=="FANTASIA")
		$ownerIdValue=$value;
	$xt->assign("FANTASIA_value",$value);
	if(!$pageObject->isAppearOnTabs("FANTASIA"))
		$xt->assign("FANTASIA_fieldblock",true);
	else
		$xt->assign("FANTASIA_tabfieldblock",true);
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
//COMPLEMENTO - 
	
	$value = $pageObject->showDBValue("COMPLEMENTO", $data, $keylink);
	if($mainTableOwnerID=="COMPLEMENTO")
		$ownerIdValue=$value;
	$xt->assign("COMPLEMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("COMPLEMENTO"))
		$xt->assign("COMPLEMENTO_fieldblock",true);
	else
		$xt->assign("COMPLEMENTO_tabfieldblock",true);
////////////////////////////////////////////
//TELEFONE1 - 
	
	$value = $pageObject->showDBValue("TELEFONE1", $data, $keylink);
	if($mainTableOwnerID=="TELEFONE1")
		$ownerIdValue=$value;
	$xt->assign("TELEFONE1_value",$value);
	if(!$pageObject->isAppearOnTabs("TELEFONE1"))
		$xt->assign("TELEFONE1_fieldblock",true);
	else
		$xt->assign("TELEFONE1_tabfieldblock",true);
////////////////////////////////////////////
//TELEFONE2 - 
	
	$value = $pageObject->showDBValue("TELEFONE2", $data, $keylink);
	if($mainTableOwnerID=="TELEFONE2")
		$ownerIdValue=$value;
	$xt->assign("TELEFONE2_value",$value);
	if(!$pageObject->isAppearOnTabs("TELEFONE2"))
		$xt->assign("TELEFONE2_fieldblock",true);
	else
		$xt->assign("TELEFONE2_tabfieldblock",true);
////////////////////////////////////////////
//FAX - 
	
	$value = $pageObject->showDBValue("FAX", $data, $keylink);
	if($mainTableOwnerID=="FAX")
		$ownerIdValue=$value;
	$xt->assign("FAX_value",$value);
	if(!$pageObject->isAppearOnTabs("FAX"))
		$xt->assign("FAX_fieldblock",true);
	else
		$xt->assign("FAX_tabfieldblock",true);
////////////////////////////////////////////
//CONTATO1 - 
	
	$value = $pageObject->showDBValue("CONTATO1", $data, $keylink);
	if($mainTableOwnerID=="CONTATO1")
		$ownerIdValue=$value;
	$xt->assign("CONTATO1_value",$value);
	if(!$pageObject->isAppearOnTabs("CONTATO1"))
		$xt->assign("CONTATO1_fieldblock",true);
	else
		$xt->assign("CONTATO1_tabfieldblock",true);
////////////////////////////////////////////
//CONTATO2 - 
	
	$value = $pageObject->showDBValue("CONTATO2", $data, $keylink);
	if($mainTableOwnerID=="CONTATO2")
		$ownerIdValue=$value;
	$xt->assign("CONTATO2_value",$value);
	if(!$pageObject->isAppearOnTabs("CONTATO2"))
		$xt->assign("CONTATO2_fieldblock",true);
	else
		$xt->assign("CONTATO2_tabfieldblock",true);
////////////////////////////////////////////
//CELULAR1 - 
	
	$value = $pageObject->showDBValue("CELULAR1", $data, $keylink);
	if($mainTableOwnerID=="CELULAR1")
		$ownerIdValue=$value;
	$xt->assign("CELULAR1_value",$value);
	if(!$pageObject->isAppearOnTabs("CELULAR1"))
		$xt->assign("CELULAR1_fieldblock",true);
	else
		$xt->assign("CELULAR1_tabfieldblock",true);
////////////////////////////////////////////
//CELULAR2 - 
	
	$value = $pageObject->showDBValue("CELULAR2", $data, $keylink);
	if($mainTableOwnerID=="CELULAR2")
		$ownerIdValue=$value;
	$xt->assign("CELULAR2_value",$value);
	if(!$pageObject->isAppearOnTabs("CELULAR2"))
		$xt->assign("CELULAR2_fieldblock",true);
	else
		$xt->assign("CELULAR2_tabfieldblock",true);
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
//HOMEPAGE - 
	
	$value = $pageObject->showDBValue("HOMEPAGE", $data, $keylink);
	if($mainTableOwnerID=="HOMEPAGE")
		$ownerIdValue=$value;
	$xt->assign("HOMEPAGE_value",$value);
	if(!$pageObject->isAppearOnTabs("HOMEPAGE"))
		$xt->assign("HOMEPAGE_fieldblock",true);
	else
		$xt->assign("HOMEPAGE_tabfieldblock",true);
////////////////////////////////////////////
//CNPJ - 
	
	$value = $pageObject->showDBValue("CNPJ", $data, $keylink);
	if($mainTableOwnerID=="CNPJ")
		$ownerIdValue=$value;
	$xt->assign("CNPJ_value",$value);
	if(!$pageObject->isAppearOnTabs("CNPJ"))
		$xt->assign("CNPJ_fieldblock",true);
	else
		$xt->assign("CNPJ_tabfieldblock",true);
////////////////////////////////////////////
//IE - 
	
	$value = $pageObject->showDBValue("IE", $data, $keylink);
	if($mainTableOwnerID=="IE")
		$ownerIdValue=$value;
	$xt->assign("IE_value",$value);
	if(!$pageObject->isAppearOnTabs("IE"))
		$xt->assign("IE_fieldblock",true);
	else
		$xt->assign("IE_tabfieldblock",true);
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
////////////////////////////////////////////
//IM - 
	
	$value = $pageObject->showDBValue("IM", $data, $keylink);
	if($mainTableOwnerID=="IM")
		$ownerIdValue=$value;
	$xt->assign("IM_value",$value);
	if(!$pageObject->isAppearOnTabs("IM"))
		$xt->assign("IM_fieldblock",true);
	else
		$xt->assign("IM_tabfieldblock",true);

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
		$options['masterTable'] = "C000009";
		$options['firstTime'] = 1;
		
		$strTableName = $dpParams['strTableNames'][$d];
		include_once("include/".GetTableURL($strTableName)."_settings.php");
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{
			$strTableName = "C000009";
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
	$strTableName = "C000009";
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
$xt->assign("editlink_attrs","id=\"editLink".$id."\" name=\"editLink".$id."\" onclick=\"window.location.href='C000009_edit.php?".$editlink."'\"");

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
