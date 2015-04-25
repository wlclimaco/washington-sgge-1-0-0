<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("include/C000040_variables.php");
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
$layout->blocks["top"][] = "details";$page_layouts["C000040_view"] = $layout;




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
	header("Location: C000040_list.php?a=return");
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
$arr['fName'] = "TITULAR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TITULAR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "EMISSAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("EMISSAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VENCIMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VENCIMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_DEPOSITO1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_DEPOSITO1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_DEPOSITO2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_DEPOSITO2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_DEVOLUCAO1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_DEVOLUCAO1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_DEVOLUCAO2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_DEVOLUCAO2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCLIENTE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCLIENTE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODBANCO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODBANCO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "AGENCIA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("AGENCIA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CONTA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CONTA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_CONTA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_CONTA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NUMERO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NUMERO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DESCONTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DESCONTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "LIQUIDO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("LIQUIDO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODVENDA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODVENDA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCONTAS_PAGAR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCONTAS_PAGAR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DESTINO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DESTINO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_BAIXA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_BAIXA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCONTA_CORRENTE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCONTA_CORRENTE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCONTA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCONTA");
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
//TITULAR - 
	
	$value = $pageObject->showDBValue("TITULAR", $data, $keylink);
	if($mainTableOwnerID=="TITULAR")
		$ownerIdValue=$value;
	$xt->assign("TITULAR_value",$value);
	if(!$pageObject->isAppearOnTabs("TITULAR"))
		$xt->assign("TITULAR_fieldblock",true);
	else
		$xt->assign("TITULAR_tabfieldblock",true);
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
//EMISSAO - Short Date
	
	$value = $pageObject->showDBValue("EMISSAO", $data, $keylink);
	if($mainTableOwnerID=="EMISSAO")
		$ownerIdValue=$value;
	$xt->assign("EMISSAO_value",$value);
	if(!$pageObject->isAppearOnTabs("EMISSAO"))
		$xt->assign("EMISSAO_fieldblock",true);
	else
		$xt->assign("EMISSAO_tabfieldblock",true);
////////////////////////////////////////////
//VENCIMENTO - Short Date
	
	$value = $pageObject->showDBValue("VENCIMENTO", $data, $keylink);
	if($mainTableOwnerID=="VENCIMENTO")
		$ownerIdValue=$value;
	$xt->assign("VENCIMENTO_value",$value);
	if(!$pageObject->isAppearOnTabs("VENCIMENTO"))
		$xt->assign("VENCIMENTO_fieldblock",true);
	else
		$xt->assign("VENCIMENTO_tabfieldblock",true);
////////////////////////////////////////////
//DATA_DEPOSITO1 - Short Date
	
	$value = $pageObject->showDBValue("DATA_DEPOSITO1", $data, $keylink);
	if($mainTableOwnerID=="DATA_DEPOSITO1")
		$ownerIdValue=$value;
	$xt->assign("DATA_DEPOSITO1_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_DEPOSITO1"))
		$xt->assign("DATA_DEPOSITO1_fieldblock",true);
	else
		$xt->assign("DATA_DEPOSITO1_tabfieldblock",true);
////////////////////////////////////////////
//DATA_DEPOSITO2 - Short Date
	
	$value = $pageObject->showDBValue("DATA_DEPOSITO2", $data, $keylink);
	if($mainTableOwnerID=="DATA_DEPOSITO2")
		$ownerIdValue=$value;
	$xt->assign("DATA_DEPOSITO2_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_DEPOSITO2"))
		$xt->assign("DATA_DEPOSITO2_fieldblock",true);
	else
		$xt->assign("DATA_DEPOSITO2_tabfieldblock",true);
////////////////////////////////////////////
//DATA_DEVOLUCAO1 - Short Date
	
	$value = $pageObject->showDBValue("DATA_DEVOLUCAO1", $data, $keylink);
	if($mainTableOwnerID=="DATA_DEVOLUCAO1")
		$ownerIdValue=$value;
	$xt->assign("DATA_DEVOLUCAO1_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_DEVOLUCAO1"))
		$xt->assign("DATA_DEVOLUCAO1_fieldblock",true);
	else
		$xt->assign("DATA_DEVOLUCAO1_tabfieldblock",true);
////////////////////////////////////////////
//DATA_DEVOLUCAO2 - Short Date
	
	$value = $pageObject->showDBValue("DATA_DEVOLUCAO2", $data, $keylink);
	if($mainTableOwnerID=="DATA_DEVOLUCAO2")
		$ownerIdValue=$value;
	$xt->assign("DATA_DEVOLUCAO2_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_DEVOLUCAO2"))
		$xt->assign("DATA_DEVOLUCAO2_fieldblock",true);
	else
		$xt->assign("DATA_DEVOLUCAO2_tabfieldblock",true);
////////////////////////////////////////////
//CODCLIENTE - 
	
	$value = $pageObject->showDBValue("CODCLIENTE", $data, $keylink);
	if($mainTableOwnerID=="CODCLIENTE")
		$ownerIdValue=$value;
	$xt->assign("CODCLIENTE_value",$value);
	if(!$pageObject->isAppearOnTabs("CODCLIENTE"))
		$xt->assign("CODCLIENTE_fieldblock",true);
	else
		$xt->assign("CODCLIENTE_tabfieldblock",true);
////////////////////////////////////////////
//CODBANCO - 
	
	$value = $pageObject->showDBValue("CODBANCO", $data, $keylink);
	if($mainTableOwnerID=="CODBANCO")
		$ownerIdValue=$value;
	$xt->assign("CODBANCO_value",$value);
	if(!$pageObject->isAppearOnTabs("CODBANCO"))
		$xt->assign("CODBANCO_fieldblock",true);
	else
		$xt->assign("CODBANCO_tabfieldblock",true);
////////////////////////////////////////////
//AGENCIA - 
	
	$value = $pageObject->showDBValue("AGENCIA", $data, $keylink);
	if($mainTableOwnerID=="AGENCIA")
		$ownerIdValue=$value;
	$xt->assign("AGENCIA_value",$value);
	if(!$pageObject->isAppearOnTabs("AGENCIA"))
		$xt->assign("AGENCIA_fieldblock",true);
	else
		$xt->assign("AGENCIA_tabfieldblock",true);
////////////////////////////////////////////
//CONTA - 
	
	$value = $pageObject->showDBValue("CONTA", $data, $keylink);
	if($mainTableOwnerID=="CONTA")
		$ownerIdValue=$value;
	$xt->assign("CONTA_value",$value);
	if(!$pageObject->isAppearOnTabs("CONTA"))
		$xt->assign("CONTA_fieldblock",true);
	else
		$xt->assign("CONTA_tabfieldblock",true);
////////////////////////////////////////////
//DATA_CONTA - Short Date
	
	$value = $pageObject->showDBValue("DATA_CONTA", $data, $keylink);
	if($mainTableOwnerID=="DATA_CONTA")
		$ownerIdValue=$value;
	$xt->assign("DATA_CONTA_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_CONTA"))
		$xt->assign("DATA_CONTA_fieldblock",true);
	else
		$xt->assign("DATA_CONTA_tabfieldblock",true);
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
//CODVENDA - 
	
	$value = $pageObject->showDBValue("CODVENDA", $data, $keylink);
	if($mainTableOwnerID=="CODVENDA")
		$ownerIdValue=$value;
	$xt->assign("CODVENDA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODVENDA"))
		$xt->assign("CODVENDA_fieldblock",true);
	else
		$xt->assign("CODVENDA_tabfieldblock",true);
////////////////////////////////////////////
//CODCONTAS_PAGAR - 
	
	$value = $pageObject->showDBValue("CODCONTAS_PAGAR", $data, $keylink);
	if($mainTableOwnerID=="CODCONTAS_PAGAR")
		$ownerIdValue=$value;
	$xt->assign("CODCONTAS_PAGAR_value",$value);
	if(!$pageObject->isAppearOnTabs("CODCONTAS_PAGAR"))
		$xt->assign("CODCONTAS_PAGAR_fieldblock",true);
	else
		$xt->assign("CODCONTAS_PAGAR_tabfieldblock",true);
////////////////////////////////////////////
//DESTINO - 
	
	$value = $pageObject->showDBValue("DESTINO", $data, $keylink);
	if($mainTableOwnerID=="DESTINO")
		$ownerIdValue=$value;
	$xt->assign("DESTINO_value",$value);
	if(!$pageObject->isAppearOnTabs("DESTINO"))
		$xt->assign("DESTINO_fieldblock",true);
	else
		$xt->assign("DESTINO_tabfieldblock",true);
////////////////////////////////////////////
//DATA_BAIXA - Short Date
	
	$value = $pageObject->showDBValue("DATA_BAIXA", $data, $keylink);
	if($mainTableOwnerID=="DATA_BAIXA")
		$ownerIdValue=$value;
	$xt->assign("DATA_BAIXA_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_BAIXA"))
		$xt->assign("DATA_BAIXA_fieldblock",true);
	else
		$xt->assign("DATA_BAIXA_tabfieldblock",true);
////////////////////////////////////////////
//CODCONTA_CORRENTE - 
	
	$value = $pageObject->showDBValue("CODCONTA_CORRENTE", $data, $keylink);
	if($mainTableOwnerID=="CODCONTA_CORRENTE")
		$ownerIdValue=$value;
	$xt->assign("CODCONTA_CORRENTE_value",$value);
	if(!$pageObject->isAppearOnTabs("CODCONTA_CORRENTE"))
		$xt->assign("CODCONTA_CORRENTE_fieldblock",true);
	else
		$xt->assign("CODCONTA_CORRENTE_tabfieldblock",true);
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
		$options['masterTable'] = "C000040";
		$options['firstTime'] = 1;
		
		$strTableName = $dpParams['strTableNames'][$d];
		include_once("include/".GetTableURL($strTableName)."_settings.php");
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{
			$strTableName = "C000040";
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
	$strTableName = "C000040";
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
$xt->assign("editlink_attrs","id=\"editLink".$id."\" name=\"editLink".$id."\" onclick=\"window.location.href='C000040_edit.php?".$editlink."'\"");

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
