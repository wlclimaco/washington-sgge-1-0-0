<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000054_variables.php");

if(!isLogged())
{ 
	$_SESSION["MyURL"]=$_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
	header("Location: login.php?message=expired"); 
	return;
}
if(CheckPermissionsEvent($strTableName, 'P') && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Export"))
{
	echo "<p>"."Você não tem permissão para acessar esta tabela"."<a href=\"login.php\">"."Voltar à página de Login"."</a></p>";
	return;
}

$layout = new TLayout("print","BoldGreenTea","MobileGreenTea");
$layout->blocks["center"] = array();
$layout->containers["grid"] = array();

$layout->containers["grid"][] = array("name"=>"printgrid","block"=>"grid_block","substyle"=>1);


$layout->skins["grid"] = "empty";
$layout->blocks["center"][] = "grid";$layout->blocks["top"] = array();
$layout->skins["master"] = "empty";
$layout->blocks["top"][] = "master";
$layout->skins["pdf"] = "empty";
$layout->blocks["top"][] = "pdf";$page_layouts["C000054_print"] = $layout;


include('include/xtempl.php');
include('classes/runnerpage.php');

$cipherer = new RunnerCipherer($strTableName);

$xt = new Xtempl();
$id = postvalue("id") != "" ? postvalue("id") : 1;
$all = postvalue("all");
$pageName = "print.php";

//array of params for classes
$params = array("id" => $id,
				"tName" => $strTableName,
				"pageType" => PAGE_PRINT);
$params["xt"] = &$xt;
			
$pageObject = new RunnerPage($params);

// add button events if exist
$pageObject->addButtonHandlers();

// Modify query: remove blob fields from fieldlist.
// Blob fields on a print page are shown using imager.php (for example).
// They don't need to be selected from DB in print.php itself.
$noBlobReplace = false;
if(!postvalue("pdf") && !$noBlobReplace)
	$gQuery->ReplaceFieldsWithDummies($pageObject->pSet->getBinaryFieldsIndices());

//	Before Process event
if($eventObj->exists("BeforeProcessPrint"))
	$eventObj->BeforeProcessPrint($conn, $pageObject);

$strWhereClause="";
$strHavingClause="";
$strSearchCriteria="and";

$selected_recs=array();
if (@$_REQUEST["a"]!="") 
{
	$sWhere = "1=0";	
	
//	process selection
	if (@$_REQUEST["mdelete"])
	{
		foreach(@$_REQUEST["mdelete"] as $ind)
		{
			$keys=array();
			$keys["CODIGO"]=refine($_REQUEST["mdelete1"][mdeleteIndex($ind)]);
			$selected_recs[]=$keys;
		}
	}
	elseif(@$_REQUEST["selection"])
	{
		foreach(@$_REQUEST["selection"] as $keyblock)
		{
			$arr=explode("&",refine($keyblock));
			if(count($arr)<1)
				continue;
			$keys=array();
			$keys["CODIGO"]=urldecode($arr[0]);
			$selected_recs[]=$keys;
		}
	}

	foreach($selected_recs as $keys)
	{
		$sWhere = $sWhere . " or ";
		$sWhere.=KeyWhere($keys);
	}
	$strSQL = $gQuery->gSQLWhere($sWhere);
	$strWhereClause=$sWhere;
}
else
{
	$strWhereClause=@$_SESSION[$strTableName."_where"];
	$strHavingClause=@$_SESSION[$strTableName."_having"];
	$strSearchCriteria=@$_SESSION[$strTableName."_criteria"];
	$strSQL = $gQuery->gSQLWhere($strWhereClause, $strHavingClause, $strSearchCriteria);
}
if(postvalue("pdf"))
	$strWhereClause = @$_SESSION[$strTableName."_pdfwhere"];

$_SESSION[$strTableName."_pdfwhere"] = $strWhereClause;


$strOrderBy = $_SESSION[$strTableName."_order"];
if(!$strOrderBy)
	$strOrderBy=$gstrOrderBy;
$strSQL.=" ".trim($strOrderBy);

$strSQLbak = $strSQL;
if($eventObj->exists("BeforeQueryPrint"))
	$eventObj->BeforeQueryPrint($strSQL,$strWhereClause,$strOrderBy, $pageObject);

//	Rebuild SQL if needed

if($strSQL!=$strSQLbak)
{
//	changed $strSQL - old style	
	$numrows=GetRowCount($strSQL);
}
else
{
	$strSQL = $gQuery->gSQLWhere($strWhereClause, $strHavingClause, $strSearchCriteria);
	$strSQL.=" ".trim($strOrderBy);
	
	$rowcount=false;
	if($eventObj->exists("ListGetRowCount"))
	{
		$masterKeysReq=array();
		for($i = 0; $i < count($pageObject->detailKeysByM); $i ++)
			$masterKeysReq[]=$_SESSION[$strTableName."_masterkey".($i + 1)];
			$rowcount=$eventObj->ListGetRowCount($pageObject->searchClauseObj,$_SESSION[$strTableName."_mastertable"],$masterKeysReq,$selected_recs, $pageObject);
	}
	if($rowcount!==false)
		$numrows=$rowcount;
	else
	{
		$numrows = $gQuery->gSQLRowCount($strWhereClause, $strHavingClause, $strSearchCriteria);
	}
}

LogInfo($strSQL);

$mypage=(integer)$_SESSION[$strTableName."_pagenumber"];
if(!$mypage)
	$mypage=1;

//	page size
$PageSize=(integer)$_SESSION[$strTableName."_pagesize"];
if(!$PageSize)
	$PageSize = $pageObject->pSet->getInitialPageSize();

if($PageSize<0)
	$all = 1;	
	
$recno = 1;
$records = 0;	
$maxpages = 1;
$pageindex = 1;
$pageno=1;

// build arrays for sort (to support old code in user-defined events)
if($eventObj->exists("ListQuery"))
{
	$arrFieldForSort = array();
	$arrHowFieldSort = array();
	require_once getabspath('classes/orderclause.php');
	$fieldList = unserialize($_SESSION[$strTableName."_orderFieldsList"]);
	for($i = 0; $i < count($fieldList); $i++)
	{
		$arrFieldForSort[] = $fieldList[$i]->fieldIndex; 
		$arrHowFieldSort[] = $fieldList[$i]->orderDirection; 
	}
}

if(!$all)
{	
	if($numrows)
	{
		$maxRecords = $numrows;
		$maxpages = ceil($maxRecords/$PageSize);
					
		if($mypage > $maxpages)
			$mypage = $maxpages;
		
		if($mypage < 1) 
			$mypage = 1;
		
		$maxrecs = $PageSize;
	}
	$listarray = false;
	if($eventObj->exists("ListQuery"))
		$listarray = $eventObj->ListQuery($pageObject->searchClauseObj, $arrFieldForSort, $arrHowFieldSort, 
			$_SESSION[$strTableName."_mastertable"], $masterKeysReq, $selected_recs, $PageSize, $mypage, $pageObject);
	if($listarray!==false)
		$rs = $listarray;
	else
	{
			$rs = db_query($strSQL,$conn);
		db_pageseek($rs,$PageSize,$mypage);
	}
	
	//	hide colunm headers if needed
	$recordsonpage = $numrows-($mypage-1)*$PageSize;
	if($recordsonpage>$PageSize)
		$recordsonpage = $PageSize;
		
	$xt->assign("page_number",true);
	$xt->assign("maxpages",$maxpages);
	$xt->assign("pageno",$mypage);
}
else
{
	$listarray = false;
	if($eventObj->exists("ListQuery"))
		$listarray=$eventObj->ListQuery($pageObject->searchClauseObj, $arrFieldForSort, $arrHowFieldSort,
			$_SESSION[$strTableName."_mastertable"], $masterKeysReq, $selected_recs, $PageSize, $mypage, $pageObject);
	if($listarray!==false)
		$rs = $listarray;
	else
		$rs = db_query($strSQL,$conn);
	$recordsonpage = $numrows;
	$maxpages = ceil($recordsonpage/30);
	$xt->assign("page_number",true);
	$xt->assign("maxpages",$maxpages);
}


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
$arr['fName'] = "ANO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ANO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COMBUSTIVEL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COMBUSTIVEL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "PLACA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("PLACA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "UFPLACA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("UFPLACA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "OBS1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("OBS1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CIDADE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CIDADE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SEGURO_OBRIGATORIO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SEGURO_OBRIGATORIO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COD_MARCA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COD_MARCA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COD_MODELO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COD_MODELO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "LICENCIAMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("LICENCIAMENTO");
$fieldsArr[] = $arr;
$pageObject->setGoogleMapsParams($fieldsArr);

$colsonpage=1;
if($colsonpage>$recordsonpage)
	$colsonpage=$recordsonpage;
if($colsonpage<1)
	$colsonpage=1;


//	fill $rowinfo array
	$pages = array();
	$rowinfo = array();
	$rowinfo["data"] = array();
	if($eventObj->exists("ListFetchArray"))
		$data = $eventObj->ListFetchArray($rs, $pageObject);
	else
		$data = $cipherer->DecryptFetchedArray($rs);

	while($data)
	{
		if($eventObj->exists("BeforeProcessRowPrint"))
		{
			if(!$eventObj->BeforeProcessRowPrint($data, $pageObject))
			{
				if($eventObj->exists("ListFetchArray"))
					$data = $eventObj->ListFetchArray($rs, $pageObject);
				else
					$data = $cipherer->DecryptFetchedArray($rs);
				continue;
			}
		}
		break;
	}
	
	while($data && ($all || $recno<=$PageSize))
	{
		$row = array();
		$row["grid_record"] = array();
		$row["grid_record"]["data"] = array();
		for($col=1;$data && ($all || $recno<=$PageSize) && $col<=1;$col++)
		{
			$record = array();
			$recno++;
			$records++;
			$keylink="";
			$keylink.="&key1=".htmlspecialchars(rawurlencode(@$data["CODIGO"]));

//	CODIGO - 
			$record["CODIGO_value"] = $pageObject->showDBValue("CODIGO", $data, $keylink);
			$record["CODIGO_class"] = $pageObject->fieldClass("CODIGO");
//	SITUACAO - 
			$record["SITUACAO_value"] = $pageObject->showDBValue("SITUACAO", $data, $keylink);
			$record["SITUACAO_class"] = $pageObject->fieldClass("SITUACAO");
//	NOME - 
			$record["NOME_value"] = $pageObject->showDBValue("NOME", $data, $keylink);
			$record["NOME_class"] = $pageObject->fieldClass("NOME");
//	ANO - 
			$record["ANO_value"] = $pageObject->showDBValue("ANO", $data, $keylink);
			$record["ANO_class"] = $pageObject->fieldClass("ANO");
//	COMBUSTIVEL - 
			$record["COMBUSTIVEL_value"] = $pageObject->showDBValue("COMBUSTIVEL", $data, $keylink);
			$record["COMBUSTIVEL_class"] = $pageObject->fieldClass("COMBUSTIVEL");
//	PLACA - 
			$record["PLACA_value"] = $pageObject->showDBValue("PLACA", $data, $keylink);
			$record["PLACA_class"] = $pageObject->fieldClass("PLACA");
//	UFPLACA - 
			$record["UFPLACA_value"] = $pageObject->showDBValue("UFPLACA", $data, $keylink);
			$record["UFPLACA_class"] = $pageObject->fieldClass("UFPLACA");
//	COR - 
			$record["COR_value"] = $pageObject->showDBValue("COR", $data, $keylink);
			$record["COR_class"] = $pageObject->fieldClass("COR");
//	CIDADE - 
			$record["CIDADE_value"] = $pageObject->showDBValue("CIDADE", $data, $keylink);
			$record["CIDADE_class"] = $pageObject->fieldClass("CIDADE");
//	SEGURO_OBRIGATORIO - Short Date
			$record["SEGURO_OBRIGATORIO_value"] = $pageObject->showDBValue("SEGURO_OBRIGATORIO", $data, $keylink);
			$record["SEGURO_OBRIGATORIO_class"] = $pageObject->fieldClass("SEGURO_OBRIGATORIO");
//	COD_MARCA - 
			$record["COD_MARCA_value"] = $pageObject->showDBValue("COD_MARCA", $data, $keylink);
			$record["COD_MARCA_class"] = $pageObject->fieldClass("COD_MARCA");
//	COD_MODELO - 
			$record["COD_MODELO_value"] = $pageObject->showDBValue("COD_MODELO", $data, $keylink);
			$record["COD_MODELO_class"] = $pageObject->fieldClass("COD_MODELO");
//	LICENCIAMENTO - Short Date
			$record["LICENCIAMENTO_value"] = $pageObject->showDBValue("LICENCIAMENTO", $data, $keylink);
			$record["LICENCIAMENTO_class"] = $pageObject->fieldClass("LICENCIAMENTO");
//	OBS1 - 
			$record["OBS1_value"] = $pageObject->showDBValue("OBS1", $data, $keylink);
			$record["OBS1_class"] = $pageObject->fieldClass("OBS1");
			if($col<$colsonpage)
				$record["endrecord_block"] = true;
			$record["grid_recordheader"] = true;
			$record["grid_vrecord"] = true;
			
			if($eventObj->exists("BeforeMoveNextPrint"))
				$eventObj->BeforeMoveNextPrint($data,$row,$record, $pageObject);
				
			$row["grid_record"]["data"][] = $record;
			
			if($eventObj->exists("ListFetchArray"))
				$data = $eventObj->ListFetchArray($rs, $pageObject);
			else
				$data = $cipherer->DecryptFetchedArray($rs);
				
			while($data)
			{
				if($eventObj->exists("BeforeProcessRowPrint"))
				{
					if(!$eventObj->BeforeProcessRowPrint($data, $pageObject))
					{
						if($eventObj->exists("ListFetchArray"))
							$data = $eventObj->ListFetchArray($rs, $pageObject);
						else
							$data = $cipherer->DecryptFetchedArray($rs);
						continue;
					}
				}
				break;
			}
		}
		if($col <= $colsonpage)
		{
			$row["grid_record"]["data"][count($row["grid_record"]["data"])-1]["endrecord_block"] = false;
		}
		$row["grid_rowspace"]=true;
		$row["grid_recordspace"] = array("data"=>array());
		for($i=0;$i<$colsonpage*2-1;$i++)
			$row["grid_recordspace"]["data"][]=true;
		
		$rowinfo["data"][]=$row;
		
		if($all && $records>=30)
		{
			$page=array("grid_row" =>$rowinfo);
			$page["pageno"]=$pageindex;
			$pageindex++;
			$pages[] = $page;
			$records=0;
			$rowinfo=array();
		}
		
	}
	if(count($rowinfo))
	{
		$page=array("grid_row" =>$rowinfo);
		if($all)
			$page["pageno"]=$pageindex;
		$pages[] = $page;
	}
	
	for($i=0;$i<count($pages);$i++)
	{
	 	if($i<count($pages)-1)
			$pages[$i]["begin"]="<div name=page class=printpage>";
		else
		    $pages[$i]["begin"]="<div name=page>";
			
		$pages[$i]["end"]="</div>";
	}

	$page = array();
	$page["data"] = &$pages;
	$xt->assignbyref("page",$page);

	

$strSQL = $_SESSION[$strTableName."_sql"];

$isPdfView = false;
$hasEvents = false;
if ($pageObject->pSet->isUsebuttonHandlers() || $isPdfView || $hasEvents)
{
	$pageObject->body["begin"] .="<script type=\"text/javascript\" src=\"include/loadfirst.js\"></script>\r\n";
		$pageObject->body["begin"] .= "<script type=\"text/javascript\" src=\"include/lang/".getLangFileName(mlang_getcurrentlang()).".js\"></script>";
	
	$pageObject->fillSetCntrlMaps();
	$pageObject->body['end'] .= '<script>';
	$pageObject->body['end'] .= "window.controlsMap = ".my_json_encode($pageObject->controlsHTMLMap).";";
	$pageObject->body['end'] .= "window.viewControlsMap = ".my_json_encode($pageObject->viewControlsHTMLMap).";";
	$pageObject->body['end'] .= "window.settings = ".my_json_encode($pageObject->jsSettings).";";
	$pageObject->body['end'] .= '</script>';
		$pageObject->body["end"] .= "<script language=\"JavaScript\" src=\"include/runnerJS/RunnerAll.js\"></script>\r\n";
	$pageObject->addCommonJs();
}


if ($pageObject->pSet->isUsebuttonHandlers() || $isPdfView || $hasEvents)
	$pageObject->body["end"] .= "<script>".$pageObject->PrepareJS()."</script>";

$xt->assignbyref("body",$pageObject->body);
$xt->assign("grid_block",true);

$xt->assign("CODIGO_fieldheadercolumn",true);
$xt->assign("CODIGO_fieldheader",true);
$xt->assign("CODIGO_fieldcolumn",true);
$xt->assign("CODIGO_fieldfootercolumn",true);
$xt->assign("SITUACAO_fieldheadercolumn",true);
$xt->assign("SITUACAO_fieldheader",true);
$xt->assign("SITUACAO_fieldcolumn",true);
$xt->assign("SITUACAO_fieldfootercolumn",true);
$xt->assign("NOME_fieldheadercolumn",true);
$xt->assign("NOME_fieldheader",true);
$xt->assign("NOME_fieldcolumn",true);
$xt->assign("NOME_fieldfootercolumn",true);
$xt->assign("ANO_fieldheadercolumn",true);
$xt->assign("ANO_fieldheader",true);
$xt->assign("ANO_fieldcolumn",true);
$xt->assign("ANO_fieldfootercolumn",true);
$xt->assign("COMBUSTIVEL_fieldheadercolumn",true);
$xt->assign("COMBUSTIVEL_fieldheader",true);
$xt->assign("COMBUSTIVEL_fieldcolumn",true);
$xt->assign("COMBUSTIVEL_fieldfootercolumn",true);
$xt->assign("PLACA_fieldheadercolumn",true);
$xt->assign("PLACA_fieldheader",true);
$xt->assign("PLACA_fieldcolumn",true);
$xt->assign("PLACA_fieldfootercolumn",true);
$xt->assign("UFPLACA_fieldheadercolumn",true);
$xt->assign("UFPLACA_fieldheader",true);
$xt->assign("UFPLACA_fieldcolumn",true);
$xt->assign("UFPLACA_fieldfootercolumn",true);
$xt->assign("COR_fieldheadercolumn",true);
$xt->assign("COR_fieldheader",true);
$xt->assign("COR_fieldcolumn",true);
$xt->assign("COR_fieldfootercolumn",true);
$xt->assign("CIDADE_fieldheadercolumn",true);
$xt->assign("CIDADE_fieldheader",true);
$xt->assign("CIDADE_fieldcolumn",true);
$xt->assign("CIDADE_fieldfootercolumn",true);
$xt->assign("SEGURO_OBRIGATORIO_fieldheadercolumn",true);
$xt->assign("SEGURO_OBRIGATORIO_fieldheader",true);
$xt->assign("SEGURO_OBRIGATORIO_fieldcolumn",true);
$xt->assign("SEGURO_OBRIGATORIO_fieldfootercolumn",true);
$xt->assign("COD_MARCA_fieldheadercolumn",true);
$xt->assign("COD_MARCA_fieldheader",true);
$xt->assign("COD_MARCA_fieldcolumn",true);
$xt->assign("COD_MARCA_fieldfootercolumn",true);
$xt->assign("COD_MODELO_fieldheadercolumn",true);
$xt->assign("COD_MODELO_fieldheader",true);
$xt->assign("COD_MODELO_fieldcolumn",true);
$xt->assign("COD_MODELO_fieldfootercolumn",true);
$xt->assign("LICENCIAMENTO_fieldheadercolumn",true);
$xt->assign("LICENCIAMENTO_fieldheader",true);
$xt->assign("LICENCIAMENTO_fieldcolumn",true);
$xt->assign("LICENCIAMENTO_fieldfootercolumn",true);
$xt->assign("OBS1_fieldheadercolumn",true);
$xt->assign("OBS1_fieldheader",true);
$xt->assign("OBS1_fieldcolumn",true);
$xt->assign("OBS1_fieldfootercolumn",true);

	$record_header=array("data"=>array());
	$record_footer=array("data"=>array());
	for($i=0;$i<$colsonpage;$i++)
	{
		$rheader=array();
		$rfooter=array();
		if($i<$colsonpage-1)
		{
			$rheader["endrecordheader_block"]=true;
			$rfooter["endrecordheader_block"]=true;
		}
		$record_header["data"][]=$rheader;
		$record_footer["data"][]=$rfooter;
	}
	$xt->assignbyref("record_header",$record_header);
	$xt->assignbyref("record_footer",$record_footer);
	$xt->assign("grid_header",true);
	$xt->assign("grid_footer",true);

if($eventObj->exists("BeforeShowPrint"))
	$eventObj->BeforeShowPrint($xt,$pageObject->templatefile, $pageObject);

if(!postvalue("pdf"))
	$xt->display($pageObject->templatefile);
else
{
	$xt->load_template($pageObject->templatefile);
	$page = $xt->fetch_loaded();
	$pagewidth=postvalue("width")*1.05;
	$pageheight=postvalue("height")*1.05;
	$landscape=false;
		if($pagewidth>$pageheight)
		{
			$landscape=true;
			if($pagewidth/$pageheight<297/210)
				$pagewidth = 297/210*$pageheight;
		}
		else
		{
			if($pagewidth/$pageheight<210/297)
				$pagewidth = 210/297*$pageheight;
		}
}
?>
