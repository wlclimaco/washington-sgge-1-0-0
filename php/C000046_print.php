<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000046_variables.php");

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
$layout->blocks["top"][] = "pdf";$page_layouts["C000046_print"] = $layout;


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
$arr['fName'] = "CODFORNECEDOR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODFORNECEDOR");
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
//	CODFORNECEDOR - 
			$record["CODFORNECEDOR_value"] = $pageObject->showDBValue("CODFORNECEDOR", $data, $keylink);
			$record["CODFORNECEDOR_class"] = $pageObject->fieldClass("CODFORNECEDOR");
//	DATA_EMISSAO - Short Date
			$record["DATA_EMISSAO_value"] = $pageObject->showDBValue("DATA_EMISSAO", $data, $keylink);
			$record["DATA_EMISSAO_class"] = $pageObject->fieldClass("DATA_EMISSAO");
//	DATA_VENCIMENTO - Short Date
			$record["DATA_VENCIMENTO_value"] = $pageObject->showDBValue("DATA_VENCIMENTO", $data, $keylink);
			$record["DATA_VENCIMENTO_class"] = $pageObject->fieldClass("DATA_VENCIMENTO");
//	DATA_PAGAMENTO - Short Date
			$record["DATA_PAGAMENTO_value"] = $pageObject->showDBValue("DATA_PAGAMENTO", $data, $keylink);
			$record["DATA_PAGAMENTO_class"] = $pageObject->fieldClass("DATA_PAGAMENTO");
//	CODCONTA - 
			$record["CODCONTA_value"] = $pageObject->showDBValue("CODCONTA", $data, $keylink);
			$record["CODCONTA_class"] = $pageObject->fieldClass("CODCONTA");
//	VALOR - Number
			$record["VALOR_value"] = $pageObject->showDBValue("VALOR", $data, $keylink);
			$record["VALOR_class"] = $pageObject->fieldClass("VALOR");
//	VALORPAGO - Number
			$record["VALORPAGO_value"] = $pageObject->showDBValue("VALORPAGO", $data, $keylink);
			$record["VALORPAGO_class"] = $pageObject->fieldClass("VALORPAGO");
//	LIQUIDO - Number
			$record["LIQUIDO_value"] = $pageObject->showDBValue("LIQUIDO", $data, $keylink);
			$record["LIQUIDO_class"] = $pageObject->fieldClass("LIQUIDO");
//	DESCONTO - Number
			$record["DESCONTO_value"] = $pageObject->showDBValue("DESCONTO", $data, $keylink);
			$record["DESCONTO_class"] = $pageObject->fieldClass("DESCONTO");
//	ACRESCIMO - Number
			$record["ACRESCIMO_value"] = $pageObject->showDBValue("ACRESCIMO", $data, $keylink);
			$record["ACRESCIMO_class"] = $pageObject->fieldClass("ACRESCIMO");
//	DOCUMENTO - 
			$record["DOCUMENTO_value"] = $pageObject->showDBValue("DOCUMENTO", $data, $keylink);
			$record["DOCUMENTO_class"] = $pageObject->fieldClass("DOCUMENTO");
//	NOTAFISCAL - 
			$record["NOTAFISCAL_value"] = $pageObject->showDBValue("NOTAFISCAL", $data, $keylink);
			$record["NOTAFISCAL_class"] = $pageObject->fieldClass("NOTAFISCAL");
//	HISTORICO - 
			$record["HISTORICO_value"] = $pageObject->showDBValue("HISTORICO", $data, $keylink);
			$record["HISTORICO_class"] = $pageObject->fieldClass("HISTORICO");
//	ESPECIE - 
			$record["ESPECIE_value"] = $pageObject->showDBValue("ESPECIE", $data, $keylink);
			$record["ESPECIE_class"] = $pageObject->fieldClass("ESPECIE");
//	SITUACAO - 
			$record["SITUACAO_value"] = $pageObject->showDBValue("SITUACAO", $data, $keylink);
			$record["SITUACAO_class"] = $pageObject->fieldClass("SITUACAO");
//	CODNOTA - 
			$record["CODNOTA_value"] = $pageObject->showDBValue("CODNOTA", $data, $keylink);
			$record["CODNOTA_class"] = $pageObject->fieldClass("CODNOTA");
//	MOVIMENTO - 
			$record["MOVIMENTO_value"] = $pageObject->showDBValue("MOVIMENTO", $data, $keylink);
			$record["MOVIMENTO_class"] = $pageObject->fieldClass("MOVIMENTO");
//	CODCAIXA - 
			$record["CODCAIXA_value"] = $pageObject->showDBValue("CODCAIXA", $data, $keylink);
			$record["CODCAIXA_class"] = $pageObject->fieldClass("CODCAIXA");
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
$xt->assign("CODFORNECEDOR_fieldheadercolumn",true);
$xt->assign("CODFORNECEDOR_fieldheader",true);
$xt->assign("CODFORNECEDOR_fieldcolumn",true);
$xt->assign("CODFORNECEDOR_fieldfootercolumn",true);
$xt->assign("DATA_EMISSAO_fieldheadercolumn",true);
$xt->assign("DATA_EMISSAO_fieldheader",true);
$xt->assign("DATA_EMISSAO_fieldcolumn",true);
$xt->assign("DATA_EMISSAO_fieldfootercolumn",true);
$xt->assign("DATA_VENCIMENTO_fieldheadercolumn",true);
$xt->assign("DATA_VENCIMENTO_fieldheader",true);
$xt->assign("DATA_VENCIMENTO_fieldcolumn",true);
$xt->assign("DATA_VENCIMENTO_fieldfootercolumn",true);
$xt->assign("DATA_PAGAMENTO_fieldheadercolumn",true);
$xt->assign("DATA_PAGAMENTO_fieldheader",true);
$xt->assign("DATA_PAGAMENTO_fieldcolumn",true);
$xt->assign("DATA_PAGAMENTO_fieldfootercolumn",true);
$xt->assign("CODCONTA_fieldheadercolumn",true);
$xt->assign("CODCONTA_fieldheader",true);
$xt->assign("CODCONTA_fieldcolumn",true);
$xt->assign("CODCONTA_fieldfootercolumn",true);
$xt->assign("VALOR_fieldheadercolumn",true);
$xt->assign("VALOR_fieldheader",true);
$xt->assign("VALOR_fieldcolumn",true);
$xt->assign("VALOR_fieldfootercolumn",true);
$xt->assign("VALORPAGO_fieldheadercolumn",true);
$xt->assign("VALORPAGO_fieldheader",true);
$xt->assign("VALORPAGO_fieldcolumn",true);
$xt->assign("VALORPAGO_fieldfootercolumn",true);
$xt->assign("LIQUIDO_fieldheadercolumn",true);
$xt->assign("LIQUIDO_fieldheader",true);
$xt->assign("LIQUIDO_fieldcolumn",true);
$xt->assign("LIQUIDO_fieldfootercolumn",true);
$xt->assign("DESCONTO_fieldheadercolumn",true);
$xt->assign("DESCONTO_fieldheader",true);
$xt->assign("DESCONTO_fieldcolumn",true);
$xt->assign("DESCONTO_fieldfootercolumn",true);
$xt->assign("ACRESCIMO_fieldheadercolumn",true);
$xt->assign("ACRESCIMO_fieldheader",true);
$xt->assign("ACRESCIMO_fieldcolumn",true);
$xt->assign("ACRESCIMO_fieldfootercolumn",true);
$xt->assign("DOCUMENTO_fieldheadercolumn",true);
$xt->assign("DOCUMENTO_fieldheader",true);
$xt->assign("DOCUMENTO_fieldcolumn",true);
$xt->assign("DOCUMENTO_fieldfootercolumn",true);
$xt->assign("NOTAFISCAL_fieldheadercolumn",true);
$xt->assign("NOTAFISCAL_fieldheader",true);
$xt->assign("NOTAFISCAL_fieldcolumn",true);
$xt->assign("NOTAFISCAL_fieldfootercolumn",true);
$xt->assign("HISTORICO_fieldheadercolumn",true);
$xt->assign("HISTORICO_fieldheader",true);
$xt->assign("HISTORICO_fieldcolumn",true);
$xt->assign("HISTORICO_fieldfootercolumn",true);
$xt->assign("ESPECIE_fieldheadercolumn",true);
$xt->assign("ESPECIE_fieldheader",true);
$xt->assign("ESPECIE_fieldcolumn",true);
$xt->assign("ESPECIE_fieldfootercolumn",true);
$xt->assign("SITUACAO_fieldheadercolumn",true);
$xt->assign("SITUACAO_fieldheader",true);
$xt->assign("SITUACAO_fieldcolumn",true);
$xt->assign("SITUACAO_fieldfootercolumn",true);
$xt->assign("CODNOTA_fieldheadercolumn",true);
$xt->assign("CODNOTA_fieldheader",true);
$xt->assign("CODNOTA_fieldcolumn",true);
$xt->assign("CODNOTA_fieldfootercolumn",true);
$xt->assign("MOVIMENTO_fieldheadercolumn",true);
$xt->assign("MOVIMENTO_fieldheader",true);
$xt->assign("MOVIMENTO_fieldcolumn",true);
$xt->assign("MOVIMENTO_fieldfootercolumn",true);
$xt->assign("CODCAIXA_fieldheadercolumn",true);
$xt->assign("CODCAIXA_fieldheader",true);
$xt->assign("CODCAIXA_fieldcolumn",true);
$xt->assign("CODCAIXA_fieldfootercolumn",true);

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
