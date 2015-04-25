<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000013_variables.php");

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
$layout->blocks["top"][] = "pdf";$page_layouts["C000013_print"] = $layout;


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
			$keys["NUMERO"]=refine($_REQUEST["mdelete1"][mdeleteIndex($ind)]);
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
			$keys["NUMERO"]=urldecode($arr[0]);
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
$arr['fName'] = "NUMERO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NUMERO");
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
$arr = array();
$arr['fName'] = "TITULAR_CONTA_PADRAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TITULAR_CONTA_PADRAO");
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
			$keylink.="&key1=".htmlspecialchars(rawurlencode(@$data["NUMERO"]));

//	NUMERO - 
			$record["NUMERO_value"] = $pageObject->showDBValue("NUMERO", $data, $keylink);
			$record["NUMERO_class"] = $pageObject->fieldClass("NUMERO");
//	TITULAR_CONTA_PADRAO - 
			$record["TITULAR_CONTA_PADRAO_value"] = $pageObject->showDBValue("TITULAR_CONTA_PADRAO", $data, $keylink);
			$record["TITULAR_CONTA_PADRAO_class"] = $pageObject->fieldClass("TITULAR_CONTA_PADRAO");
//	BANCO - 
			$record["BANCO_value"] = $pageObject->showDBValue("BANCO", $data, $keylink);
			$record["BANCO_class"] = $pageObject->fieldClass("BANCO");
//	CARTAO_CREDITO - 
			$record["CARTAO_CREDITO_value"] = $pageObject->showDBValue("CARTAO_CREDITO", $data, $keylink);
			$record["CARTAO_CREDITO_class"] = $pageObject->fieldClass("CARTAO_CREDITO");
//	FINANCEIRA - 
			$record["FINANCEIRA_value"] = $pageObject->showDBValue("FINANCEIRA", $data, $keylink);
			$record["FINANCEIRA_class"] = $pageObject->fieldClass("FINANCEIRA");
//	RESSARCIMENTO - 
			$record["RESSARCIMENTO_value"] = $pageObject->showDBValue("RESSARCIMENTO", $data, $keylink);
			$record["RESSARCIMENTO_class"] = $pageObject->fieldClass("RESSARCIMENTO");
//	PRAZO - 
			$record["PRAZO_value"] = $pageObject->showDBValue("PRAZO", $data, $keylink);
			$record["PRAZO_class"] = $pageObject->fieldClass("PRAZO");
//	COMISSAO_CREDITO - Number
			$record["COMISSAO_CREDITO_value"] = $pageObject->showDBValue("COMISSAO_CREDITO", $data, $keylink);
			$record["COMISSAO_CREDITO_class"] = $pageObject->fieldClass("COMISSAO_CREDITO");
//	COMISSAO_DEBITO - Number
			$record["COMISSAO_DEBITO_value"] = $pageObject->showDBValue("COMISSAO_DEBITO", $data, $keylink);
			$record["COMISSAO_DEBITO_class"] = $pageObject->fieldClass("COMISSAO_DEBITO");
//	REC_DEBITO - 
			$record["REC_DEBITO_value"] = $pageObject->showDBValue("REC_DEBITO", $data, $keylink);
			$record["REC_DEBITO_class"] = $pageObject->fieldClass("REC_DEBITO");
//	REC_CREDITO - 
			$record["REC_CREDITO_value"] = $pageObject->showDBValue("REC_CREDITO", $data, $keylink);
			$record["REC_CREDITO_class"] = $pageObject->fieldClass("REC_CREDITO");
//	CONTA_PADRAO - 
			$record["CONTA_PADRAO_value"] = $pageObject->showDBValue("CONTA_PADRAO", $data, $keylink);
			$record["CONTA_PADRAO_class"] = $pageObject->fieldClass("CONTA_PADRAO");
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

$xt->assign("NUMERO_fieldheadercolumn",true);
$xt->assign("NUMERO_fieldheader",true);
$xt->assign("NUMERO_fieldcolumn",true);
$xt->assign("NUMERO_fieldfootercolumn",true);
$xt->assign("TITULAR_CONTA_PADRAO_fieldheadercolumn",true);
$xt->assign("TITULAR_CONTA_PADRAO_fieldheader",true);
$xt->assign("TITULAR_CONTA_PADRAO_fieldcolumn",true);
$xt->assign("TITULAR_CONTA_PADRAO_fieldfootercolumn",true);
$xt->assign("BANCO_fieldheadercolumn",true);
$xt->assign("BANCO_fieldheader",true);
$xt->assign("BANCO_fieldcolumn",true);
$xt->assign("BANCO_fieldfootercolumn",true);
$xt->assign("CARTAO_CREDITO_fieldheadercolumn",true);
$xt->assign("CARTAO_CREDITO_fieldheader",true);
$xt->assign("CARTAO_CREDITO_fieldcolumn",true);
$xt->assign("CARTAO_CREDITO_fieldfootercolumn",true);
$xt->assign("FINANCEIRA_fieldheadercolumn",true);
$xt->assign("FINANCEIRA_fieldheader",true);
$xt->assign("FINANCEIRA_fieldcolumn",true);
$xt->assign("FINANCEIRA_fieldfootercolumn",true);
$xt->assign("RESSARCIMENTO_fieldheadercolumn",true);
$xt->assign("RESSARCIMENTO_fieldheader",true);
$xt->assign("RESSARCIMENTO_fieldcolumn",true);
$xt->assign("RESSARCIMENTO_fieldfootercolumn",true);
$xt->assign("PRAZO_fieldheadercolumn",true);
$xt->assign("PRAZO_fieldheader",true);
$xt->assign("PRAZO_fieldcolumn",true);
$xt->assign("PRAZO_fieldfootercolumn",true);
$xt->assign("COMISSAO_CREDITO_fieldheadercolumn",true);
$xt->assign("COMISSAO_CREDITO_fieldheader",true);
$xt->assign("COMISSAO_CREDITO_fieldcolumn",true);
$xt->assign("COMISSAO_CREDITO_fieldfootercolumn",true);
$xt->assign("COMISSAO_DEBITO_fieldheadercolumn",true);
$xt->assign("COMISSAO_DEBITO_fieldheader",true);
$xt->assign("COMISSAO_DEBITO_fieldcolumn",true);
$xt->assign("COMISSAO_DEBITO_fieldfootercolumn",true);
$xt->assign("REC_DEBITO_fieldheadercolumn",true);
$xt->assign("REC_DEBITO_fieldheader",true);
$xt->assign("REC_DEBITO_fieldcolumn",true);
$xt->assign("REC_DEBITO_fieldfootercolumn",true);
$xt->assign("REC_CREDITO_fieldheadercolumn",true);
$xt->assign("REC_CREDITO_fieldheader",true);
$xt->assign("REC_CREDITO_fieldcolumn",true);
$xt->assign("REC_CREDITO_fieldfootercolumn",true);
$xt->assign("CONTA_PADRAO_fieldheadercolumn",true);
$xt->assign("CONTA_PADRAO_fieldheader",true);
$xt->assign("CONTA_PADRAO_fieldcolumn",true);
$xt->assign("CONTA_PADRAO_fieldfootercolumn",true);

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
