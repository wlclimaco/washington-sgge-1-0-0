<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("include/C000025_variables.php");
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
$layout->blocks["top"][] = "details";$page_layouts["C000025_view"] = $layout;




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
	header("Location: C000025_list.php?a=return");
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
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODBARRA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODBARRA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "PRODUTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("PRODUTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "UNIDADE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("UNIDADE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODGRUPO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODGRUPO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODSUBGRUPO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODSUBGRUPO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODFORNECEDOR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODFORNECEDOR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODMARCA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODMARCA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DATA_CADASTRO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DATA_CADASTRO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "PRECOCUSTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("PRECOCUSTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "PRECOVENDA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("PRECOVENDA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "ESTOQUE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ESTOQUE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "ESTOQUEMINIMO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ESTOQUEMINIMO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODALIQUOTA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODALIQUOTA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "LOCALICAZAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("LOCALICAZAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "PESO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("PESO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CST";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CST");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TAMANHO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TAMANHO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CSOSN";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CSOSN");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NOTAFISCAL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NOTAFISCAL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALIDADE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALIDADE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CLASSIFICACAO_FISCAL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CLASSIFICACAO_FISCAL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NCM";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NCM");
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
//CODBARRA - 
	
	$value = $pageObject->showDBValue("CODBARRA", $data, $keylink);
	if($mainTableOwnerID=="CODBARRA")
		$ownerIdValue=$value;
	$xt->assign("CODBARRA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODBARRA"))
		$xt->assign("CODBARRA_fieldblock",true);
	else
		$xt->assign("CODBARRA_tabfieldblock",true);
////////////////////////////////////////////
//PRODUTO - 
	
	$value = $pageObject->showDBValue("PRODUTO", $data, $keylink);
	if($mainTableOwnerID=="PRODUTO")
		$ownerIdValue=$value;
	$xt->assign("PRODUTO_value",$value);
	if(!$pageObject->isAppearOnTabs("PRODUTO"))
		$xt->assign("PRODUTO_fieldblock",true);
	else
		$xt->assign("PRODUTO_tabfieldblock",true);
////////////////////////////////////////////
//UNIDADE - 
	
	$value = $pageObject->showDBValue("UNIDADE", $data, $keylink);
	if($mainTableOwnerID=="UNIDADE")
		$ownerIdValue=$value;
	$xt->assign("UNIDADE_value",$value);
	if(!$pageObject->isAppearOnTabs("UNIDADE"))
		$xt->assign("UNIDADE_fieldblock",true);
	else
		$xt->assign("UNIDADE_tabfieldblock",true);
////////////////////////////////////////////
//CODGRUPO - 
	
	$value = $pageObject->showDBValue("CODGRUPO", $data, $keylink);
	if($mainTableOwnerID=="CODGRUPO")
		$ownerIdValue=$value;
	$xt->assign("CODGRUPO_value",$value);
	if(!$pageObject->isAppearOnTabs("CODGRUPO"))
		$xt->assign("CODGRUPO_fieldblock",true);
	else
		$xt->assign("CODGRUPO_tabfieldblock",true);
////////////////////////////////////////////
//CODSUBGRUPO - 
	
	$value = $pageObject->showDBValue("CODSUBGRUPO", $data, $keylink);
	if($mainTableOwnerID=="CODSUBGRUPO")
		$ownerIdValue=$value;
	$xt->assign("CODSUBGRUPO_value",$value);
	if(!$pageObject->isAppearOnTabs("CODSUBGRUPO"))
		$xt->assign("CODSUBGRUPO_fieldblock",true);
	else
		$xt->assign("CODSUBGRUPO_tabfieldblock",true);
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
//CODMARCA - 
	
	$value = $pageObject->showDBValue("CODMARCA", $data, $keylink);
	if($mainTableOwnerID=="CODMARCA")
		$ownerIdValue=$value;
	$xt->assign("CODMARCA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODMARCA"))
		$xt->assign("CODMARCA_fieldblock",true);
	else
		$xt->assign("CODMARCA_tabfieldblock",true);
////////////////////////////////////////////
//DATA_CADASTRO - Short Date
	
	$value = $pageObject->showDBValue("DATA_CADASTRO", $data, $keylink);
	if($mainTableOwnerID=="DATA_CADASTRO")
		$ownerIdValue=$value;
	$xt->assign("DATA_CADASTRO_value",$value);
	if(!$pageObject->isAppearOnTabs("DATA_CADASTRO"))
		$xt->assign("DATA_CADASTRO_fieldblock",true);
	else
		$xt->assign("DATA_CADASTRO_tabfieldblock",true);
////////////////////////////////////////////
//PRECOCUSTO - Number
	
	$value = $pageObject->showDBValue("PRECOCUSTO", $data, $keylink);
	if($mainTableOwnerID=="PRECOCUSTO")
		$ownerIdValue=$value;
	$xt->assign("PRECOCUSTO_value",$value);
	if(!$pageObject->isAppearOnTabs("PRECOCUSTO"))
		$xt->assign("PRECOCUSTO_fieldblock",true);
	else
		$xt->assign("PRECOCUSTO_tabfieldblock",true);
////////////////////////////////////////////
//PRECOVENDA - Number
	
	$value = $pageObject->showDBValue("PRECOVENDA", $data, $keylink);
	if($mainTableOwnerID=="PRECOVENDA")
		$ownerIdValue=$value;
	$xt->assign("PRECOVENDA_value",$value);
	if(!$pageObject->isAppearOnTabs("PRECOVENDA"))
		$xt->assign("PRECOVENDA_fieldblock",true);
	else
		$xt->assign("PRECOVENDA_tabfieldblock",true);
////////////////////////////////////////////
//ESTOQUE - Number
	
	$value = $pageObject->showDBValue("ESTOQUE", $data, $keylink);
	if($mainTableOwnerID=="ESTOQUE")
		$ownerIdValue=$value;
	$xt->assign("ESTOQUE_value",$value);
	if(!$pageObject->isAppearOnTabs("ESTOQUE"))
		$xt->assign("ESTOQUE_fieldblock",true);
	else
		$xt->assign("ESTOQUE_tabfieldblock",true);
////////////////////////////////////////////
//ESTOQUEMINIMO - Number
	
	$value = $pageObject->showDBValue("ESTOQUEMINIMO", $data, $keylink);
	if($mainTableOwnerID=="ESTOQUEMINIMO")
		$ownerIdValue=$value;
	$xt->assign("ESTOQUEMINIMO_value",$value);
	if(!$pageObject->isAppearOnTabs("ESTOQUEMINIMO"))
		$xt->assign("ESTOQUEMINIMO_fieldblock",true);
	else
		$xt->assign("ESTOQUEMINIMO_tabfieldblock",true);
////////////////////////////////////////////
//CODALIQUOTA - 
	
	$value = $pageObject->showDBValue("CODALIQUOTA", $data, $keylink);
	if($mainTableOwnerID=="CODALIQUOTA")
		$ownerIdValue=$value;
	$xt->assign("CODALIQUOTA_value",$value);
	if(!$pageObject->isAppearOnTabs("CODALIQUOTA"))
		$xt->assign("CODALIQUOTA_fieldblock",true);
	else
		$xt->assign("CODALIQUOTA_tabfieldblock",true);
////////////////////////////////////////////
//LOCALICAZAO - 
	
	$value = $pageObject->showDBValue("LOCALICAZAO", $data, $keylink);
	if($mainTableOwnerID=="LOCALICAZAO")
		$ownerIdValue=$value;
	$xt->assign("LOCALICAZAO_value",$value);
	if(!$pageObject->isAppearOnTabs("LOCALICAZAO"))
		$xt->assign("LOCALICAZAO_fieldblock",true);
	else
		$xt->assign("LOCALICAZAO_tabfieldblock",true);
////////////////////////////////////////////
//PESO - Number
	
	$value = $pageObject->showDBValue("PESO", $data, $keylink);
	if($mainTableOwnerID=="PESO")
		$ownerIdValue=$value;
	$xt->assign("PESO_value",$value);
	if(!$pageObject->isAppearOnTabs("PESO"))
		$xt->assign("PESO_fieldblock",true);
	else
		$xt->assign("PESO_tabfieldblock",true);
////////////////////////////////////////////
//CST - 
	
	$value = $pageObject->showDBValue("CST", $data, $keylink);
	if($mainTableOwnerID=="CST")
		$ownerIdValue=$value;
	$xt->assign("CST_value",$value);
	if(!$pageObject->isAppearOnTabs("CST"))
		$xt->assign("CST_fieldblock",true);
	else
		$xt->assign("CST_tabfieldblock",true);
////////////////////////////////////////////
//TAMANHO - 
	
	$value = $pageObject->showDBValue("TAMANHO", $data, $keylink);
	if($mainTableOwnerID=="TAMANHO")
		$ownerIdValue=$value;
	$xt->assign("TAMANHO_value",$value);
	if(!$pageObject->isAppearOnTabs("TAMANHO"))
		$xt->assign("TAMANHO_fieldblock",true);
	else
		$xt->assign("TAMANHO_tabfieldblock",true);
////////////////////////////////////////////
//COR - 
	
	$value = $pageObject->showDBValue("COR", $data, $keylink);
	if($mainTableOwnerID=="COR")
		$ownerIdValue=$value;
	$xt->assign("COR_value",$value);
	if(!$pageObject->isAppearOnTabs("COR"))
		$xt->assign("COR_fieldblock",true);
	else
		$xt->assign("COR_tabfieldblock",true);
////////////////////////////////////////////
//CSOSN - 
	
	$value = $pageObject->showDBValue("CSOSN", $data, $keylink);
	if($mainTableOwnerID=="CSOSN")
		$ownerIdValue=$value;
	$xt->assign("CSOSN_value",$value);
	if(!$pageObject->isAppearOnTabs("CSOSN"))
		$xt->assign("CSOSN_fieldblock",true);
	else
		$xt->assign("CSOSN_tabfieldblock",true);
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
//VALIDADE - 
	
	$value = $pageObject->showDBValue("VALIDADE", $data, $keylink);
	if($mainTableOwnerID=="VALIDADE")
		$ownerIdValue=$value;
	$xt->assign("VALIDADE_value",$value);
	if(!$pageObject->isAppearOnTabs("VALIDADE"))
		$xt->assign("VALIDADE_fieldblock",true);
	else
		$xt->assign("VALIDADE_tabfieldblock",true);
////////////////////////////////////////////
//CLASSIFICACAO_FISCAL - 
	
	$value = $pageObject->showDBValue("CLASSIFICACAO_FISCAL", $data, $keylink);
	if($mainTableOwnerID=="CLASSIFICACAO_FISCAL")
		$ownerIdValue=$value;
	$xt->assign("CLASSIFICACAO_FISCAL_value",$value);
	if(!$pageObject->isAppearOnTabs("CLASSIFICACAO_FISCAL"))
		$xt->assign("CLASSIFICACAO_FISCAL_fieldblock",true);
	else
		$xt->assign("CLASSIFICACAO_FISCAL_tabfieldblock",true);
////////////////////////////////////////////
//NCM - 
	
	$value = $pageObject->showDBValue("NCM", $data, $keylink);
	if($mainTableOwnerID=="NCM")
		$ownerIdValue=$value;
	$xt->assign("NCM_value",$value);
	if(!$pageObject->isAppearOnTabs("NCM"))
		$xt->assign("NCM_fieldblock",true);
	else
		$xt->assign("NCM_tabfieldblock",true);

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
		$options['masterTable'] = "C000025";
		$options['firstTime'] = 1;
		
		$strTableName = $dpParams['strTableNames'][$d];
		include_once("include/".GetTableURL($strTableName)."_settings.php");
		if(!CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{
			$strTableName = "C000025";
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
	$strTableName = "C000025";
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
$xt->assign("editlink_attrs","id=\"editLink".$id."\" name=\"editLink".$id."\" onclick=\"window.location.href='C000025_edit.php?".$editlink."'\"");

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
