<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("include/C000046_variables.php");
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
$layout->blocks["top"][] = "details";$page_layouts["C000046_view"] = $layout;




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
	header("Location: C000046_list.php?a=return");
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
$arr['fName'] = "CODFORNECEDOR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODFORNECEDOR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_EMISSAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_EMISSAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_VENCIMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_VENCIMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_PAGAMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_PAGAMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCONTA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCONTA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALORPAGO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALORPAGO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "LIQUIDO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("LIQUIDO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DESCONTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DESCONTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "ACRESCIMO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ACRESCIMO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DOCUMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DOCUMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NOTAFISCAL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NOTAFISCAL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "HISTORICO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("HISTORICO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "ESPECIE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ESPECIE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODNOTA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODNOTA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "MOVIMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("MOVIMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCAIXA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCAIXA");
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
//CODFORNECEDOR - 
	
	$value = $pageObject->showDBValue("CODFORNECEDOR", $data, $keylink);
	if($mainTableOwnerID=="CODFORNECEDOR")
		$ownerIdValue=$value;
	$xt->assign("CODFORNECEDOR_value",$value);
	if(!$pageObject->isAppearOnTabs("CODFORNECEDOR"))
		$xt->assign("CODFORNECEDOR_fieldblock",true);
	else
		$xt->assign("CODFORNECEDOR_tabfieldblock",true);
////////////////////////////////////////////
//DATA_EMISSAO - Short Date
	
	$value = $pageObject->showDBValue("DATA_EMISSAO", $data, $keylink);
	if($mainTableOwnerID=="DATA_EMISSAO")
		$ownerIdValue=$value;
	$xt->assign("DATA_EMISSAO_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_EMISSAO"))
		$xt->assign("DATA_EMISSAO_fieldblock",true);
	else
		$xt->assign("DATA_EMISSAO_tabfieldblock",true);
////////////////////////////////////////////
//DATA_VENCIMENTO - Short Date
	
	$value = $pageObject->showDBValue("DATA_VENCIMENTO", $data, $keylink);
	if($mainTableOwnerID=="DATA_VENCIMENTO")
		$ownerIdValue=$value;
	$xt->assign("DATA_VENCIMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_VENCIMENTO"))
		$xt->assign("DATA_VENCIMENTO_fieldblock",true);
	else
		$xt->assign("DATA_VENCIMENTO_tabfieldblock",true);
////////////////////////////////////////////
//DATA_PAGAMENTO - Short Date
	
	$value = $pageObject->showDBValue("DATA_PAGAMENTO", $data, $keylink);
	if($mainTableOwnerID=="DATA_PAGAMENTO")
		$ownerIdValue=$value;
	$xt->assign("DATA_PAGAMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_PAGAMENTO"))
		$xt->assign("DATA_PAGAMENTO_fieldblock",true);
	else
		$xt->assign("DATA_PAGAMENTO_tabfieldblock",true);
////////////////////////////////////////////
//CODCONTA - 
	
	$value = $pageObject->showDBValue("CODCONTA", $data, $keylink);
	if($mainTableOwnerID=="CODCONTA")
		$ownerIdValue=$value;
	$xt->assign("CODCONTA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODCONTA"))
		$xt->assign("CODCONTA_fieldblock",true);
	else
		$xt->assign("CODCONTA_tabfieldblock",true);
////////////////////////////////////////////
//VALOR - Number
	
	$value = $pageObject->showDBValue("VALOR", $data, $keylink);
	if($mainTableOwnerID=="VALOR")
		$ownerIdValue=$value;
	$xt->assign("VALOR_value",$value);
	if(!$pageObject->isAppearOnTabs("VALOR"))
		$xt->assign("VALOR_fieldblock",true);
	else
		$xt->assign("VALOR_tabfieldblock",true);
////////////////////////////////////////////
//VALORPAGO - Number
	
	$value = $pageObject->showDBValue("VALORPAGO", $data, $keylink);
	if($mainTableOwnerID=="VALORPAGO")
		$ownerIdValue=$value;
	$xt->assign("VALORPAGO_value",$value);
	if(!$pageObject->isAppearOnTabs("VALORPAGO"))
		$xt->assign("VALORPAGO_fieldblock",true);
	else
		$xt->assign("VALORPAGO_tabfieldblock",true);
////////////////////////////////////////////
//LIQUIDO - Number
	
	$value = $pageObject->showDBValue("LIQUIDO", $data, $keylink);
	if($mainTableOwnerID=="LIQUIDO")
		$ownerIdValue=$value;
	$xt->assign("LIQUIDO_value",$value);
	if(!$pageObject->isAppearOnTabs("LIQUIDO"))
		$xt->assign("LIQUIDO_fieldblock",true);
	else
		$xt->assign("LIQUIDO_tabfieldblock",true);
////////////////////////////////////////////
//DESCONTO - Number
	
	$value = $pageObject->showDBValue("DESCONTO", $data, $keylink);
	if($mainTableOwnerID=="DESCONTO")
		$ownerIdValue=$value;
	$xt->assign("DESCONTO_value",$value);
	if(!$pageObject->isAppearOnTabs("DESCONTO"))
		$xt->assign("DESCONTO_fieldblock",true);
	else
		$xt->assign("DESCONTO_tabfieldblock",true);
////////////////////////////////////////////
//ACRESCIMO - Number
	
	$value = $pageObject->showDBValue("ACRESCIMO", $data, $keylink);
	if($mainTableOwnerID=="ACRESCIMO")
		$ownerIdValue=$value;
	$xt->assign("ACRESCIMO_value",$value);
	if(!$pageObject->isAppearOnTabs("ACRESCIMO"))
		$xt->assign("ACRESCIMO_fieldblock",true);
	else
		$xt->assign("ACRESCIMO_tabfieldblock",true);
////////////////////////////////////////////
//DOCUMENTO - 
	
	$value = $pageObject->showDBValue("DOCUMENTO", $data, $keylink);
	if($mainTableOwnerID=="DOCUMENTO")
		$ownerIdValue=$value;
	$xt->assign("DOCUMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("DOCUMENTO"))
		$xt->assign("DOCUMENTO_fieldblock",true);
	else
		$xt->assign("DOCUMENTO_tabfieldblock",true);
////////////////////////////////////////////
//NOTAFISCAL - 
	
	$value = $pageObject->showDBValue("NOTAFISCAL", $data, $keylink);
	if($mainTableOwnerID=="NOTAFISCAL")
		$ownerIdValue=$value;
	$xt->assign("NOTAFISCAL_value",$value);
	if(!$pageObject->isAppearOnTabs("NOTAFISCAL"))
		$xt->assign("NOTAFISCAL_fieldblock",true);
	else
		$xt->assign("NOTAFISCAL_tabfieldblock",true);
////////////////////////////////////////////
//HISTORICO - 
	
	$value = $pageObject->showDBValue("HISTORICO", $data, $keylink);
	if($mainTableOwnerID=="HISTORICO")
		$ownerIdValue=$value;
	$xt->assign("HISTORICO_value",$value);
	if(!$pageObject->isAppearOnTabs("HISTORICO"))
		$xt->assign("HISTORICO_fieldblock",true);
	else
		$xt->assign("HISTORICO_tabfieldblock",true);
////////////////////////////////////////////
//ESPECIE - 
	
	$value = $pageObject->showDBValue("ESPECIE", $data, $keylink);
	if($mainTableOwnerID=="ESPECIE")
		$ownerIdValue=$value;
	$xt->assign("ESPECIE_value",$value);
	if(!$pageObject->isAppearOnTabs("ESPECIE"))
		$xt->assign("ESPECIE_fieldblock",true);
	else
		$xt->assign("ESPECIE_tabfieldblock",true);
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
//CODNOTA - 
	
	$value = $pageObject->showDBValue("CODNOTA", $data, $keylink);
	if($mainTableOwnerID=="CODNOTA")
		$ownerIdValue=$value;
	$xt->assign("CODNOTA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODNOTA"))
		$xt->assign("CODNOTA_fieldblock",true);
	else
		$xt->assign("CODNOTA_tabfieldblock",true);
////////////////////////////////////////////
//MOVIMENTO - 
	
	$value = $pageObject->showDBValue("MOVIMENTO", $data, $keylink);
	if($mainTableOwnerID=="MOVIMENTO")
		$ownerIdValue=$value;
	$xt->assign("MOVIMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("MOVIMENTO"))
		$xt->assign("MOVIMENTO_fieldblock",true);
	else
		$xt->assign("MOVIMENTO_tabfieldblock",true);
////////////////////////////////////////////
//CODCAIXA - 
	
	$value = $pageObject->showDBValue("CODCAIXA", $data, $keylink);
	if($mainTableOwnerID=="CODCAIXA")
		$ownerIdValue=$value;
	$xt->assign("CODCAIXA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODCAIXA"))
		$xt->assign("CODCAIXA_fieldblock",true);
	else
		$xt->assign("CODCAIXA_tabfieldblock",true);

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
		$options['masterTable'] = "C000046";
		$options['firstTime'] = 1;
		
		$strTableName = $dpParams['strTableNames'][$d];
		include_once("include/".GetTableURL($strTableName)."_settings.php");
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{
			$strTableName = "C000046";
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
	$strTableName = "C000046";
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
$xt->assign("editlink_attrs","id=\"editLink".$id."\" name=\"editLink".$id."\" onclick=\"window.location.href='C000046_edit.php?".$editlink."'\"");

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
