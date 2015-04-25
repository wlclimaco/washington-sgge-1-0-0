<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000049_variables.php");

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
$layout->blocks["top"][] = "pdf";$page_layouts["C000049_print"] = $layout;


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
			$selected_recs[]=$keys;
		}
	}
	elseif(@$_REQUEST["selection"])
	{
		foreach(@$_REQUEST["selection"] as $keyblock)
		{
			$arr=explode("&",refine($keyblock));
			if(count($arr)<0)
				continue;
			$keys=array();
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
$arr['fName'] = "CODVENDA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODVENDA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODVENDEDOR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODVENDEDOR");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCAIXA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCAIXA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCLIENTE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCLIENTE");
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
$arr['fName'] = "VALOR_ORIGINAL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR_ORIGINAL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR_PAGO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR_PAGO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR_JUROS";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR_JUROS");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR_ATUAL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR_ATUAL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR_DESCONTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR_DESCONTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "DOCUMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("DOCUMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TIPO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TIPO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "NUMERO_CUPOM";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NUMERO_CUPOM");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "VALOR_VENDA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("VALOR_VENDA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "HISTORICO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("HISTORICO");
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

//	CODIGO - 
			$record["CODIGO_value"] = $pageObject->showDBValue("CODIGO", $data, $keylink);
			$record["CODIGO_class"] = $pageObject->fieldClass("CODIGO");
//	CODVENDA - 
			$record["CODVENDA_value"] = $pageObject->showDBValue("CODVENDA", $data, $keylink);
			$record["CODVENDA_class"] = $pageObject->fieldClass("CODVENDA");
//	CODVENDEDOR - 
			$record["CODVENDEDOR_value"] = $pageObject->showDBValue("CODVENDEDOR", $data, $keylink);
			$record["CODVENDEDOR_class"] = $pageObject->fieldClass("CODVENDEDOR");
//	CODCAIXA - 
			$record["CODCAIXA_value"] = $pageObject->showDBValue("CODCAIXA", $data, $keylink);
			$record["CODCAIXA_class"] = $pageObject->fieldClass("CODCAIXA");
//	CODCLIENTE - 
			$record["CODCLIENTE_value"] = $pageObject->showDBValue("CODCLIENTE", $data, $keylink);
			$record["CODCLIENTE_class"] = $pageObject->fieldClass("CODCLIENTE");
//	DATA_EMISSAO - Short Date
			$record["DATA_EMISSAO_value"] = $pageObject->showDBValue("DATA_EMISSAO", $data, $keylink);
			$record["DATA_EMISSAO_class"] = $pageObject->fieldClass("DATA_EMISSAO");
//	DATA_VENCIMENTO - Short Date
			$record["DATA_VENCIMENTO_value"] = $pageObject->showDBValue("DATA_VENCIMENTO", $data, $keylink);
			$record["DATA_VENCIMENTO_class"] = $pageObject->fieldClass("DATA_VENCIMENTO");
//	DATA_PAGAMENTO - Short Date
			$record["DATA_PAGAMENTO_value"] = $pageObject->showDBValue("DATA_PAGAMENTO", $data, $keylink);
			$record["DATA_PAGAMENTO_class"] = $pageObject->fieldClass("DATA_PAGAMENTO");
//	VALOR_ORIGINAL - Number
			$record["VALOR_ORIGINAL_value"] = $pageObject->showDBValue("VALOR_ORIGINAL", $data, $keylink);
			$record["VALOR_ORIGINAL_class"] = $pageObject->fieldClass("VALOR_ORIGINAL");
//	VALOR_PAGO - Number
			$record["VALOR_PAGO_value"] = $pageObject->showDBValue("VALOR_PAGO", $data, $keylink);
			$record["VALOR_PAGO_class"] = $pageObject->fieldClass("VALOR_PAGO");
//	VALOR_JUROS - Number
			$record["VALOR_JUROS_value"] = $pageObject->showDBValue("VALOR_JUROS", $data, $keylink);
			$record["VALOR_JUROS_class"] = $pageObject->fieldClass("VALOR_JUROS");
//	VALOR_ATUAL - Number
			$record["VALOR_ATUAL_value"] = $pageObject->showDBValue("VALOR_ATUAL", $data, $keylink);
			$record["VALOR_ATUAL_class"] = $pageObject->fieldClass("VALOR_ATUAL");
//	VALOR_DESCONTO - Number
			$record["VALOR_DESCONTO_value"] = $pageObject->showDBValue("VALOR_DESCONTO", $data, $keylink);
			$record["VALOR_DESCONTO_class"] = $pageObject->fieldClass("VALOR_DESCONTO");
//	DOCUMENTO - 
			$record["DOCUMENTO_value"] = $pageObject->showDBValue("DOCUMENTO", $data, $keylink);
			$record["DOCUMENTO_class"] = $pageObject->fieldClass("DOCUMENTO");
//	TIPO - 
			$record["TIPO_value"] = $pageObject->showDBValue("TIPO", $data, $keylink);
			$record["TIPO_class"] = $pageObject->fieldClass("TIPO");
//	SITUACAO - 
			$record["SITUACAO_value"] = $pageObject->showDBValue("SITUACAO", $data, $keylink);
			$record["SITUACAO_class"] = $pageObject->fieldClass("SITUACAO");
//	NUMERO_CUPOM - 
			$record["NUMERO_CUPOM_value"] = $pageObject->showDBValue("NUMERO_CUPOM", $data, $keylink);
			$record["NUMERO_CUPOM_class"] = $pageObject->fieldClass("NUMERO_CUPOM");
//	VALOR_VENDA - Number
			$record["VALOR_VENDA_value"] = $pageObject->showDBValue("VALOR_VENDA", $data, $keylink);
			$record["VALOR_VENDA_class"] = $pageObject->fieldClass("VALOR_VENDA");
//	HISTORICO - 
			$record["HISTORICO_value"] = $pageObject->showDBValue("HISTORICO", $data, $keylink);
			$record["HISTORICO_class"] = $pageObject->fieldClass("HISTORICO");
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
$xt->assign("CODVENDA_fieldheadercolumn",true);
$xt->assign("CODVENDA_fieldheader",true);
$xt->assign("CODVENDA_fieldcolumn",true);
$xt->assign("CODVENDA_fieldfootercolumn",true);
$xt->assign("CODVENDEDOR_fieldheadercolumn",true);
$xt->assign("CODVENDEDOR_fieldheader",true);
$xt->assign("CODVENDEDOR_fieldcolumn",true);
$xt->assign("CODVENDEDOR_fieldfootercolumn",true);
$xt->assign("CODCAIXA_fieldheadercolumn",true);
$xt->assign("CODCAIXA_fieldheader",true);
$xt->assign("CODCAIXA_fieldcolumn",true);
$xt->assign("CODCAIXA_fieldfootercolumn",true);
$xt->assign("CODCLIENTE_fieldheadercolumn",true);
$xt->assign("CODCLIENTE_fieldheader",true);
$xt->assign("CODCLIENTE_fieldcolumn",true);
$xt->assign("CODCLIENTE_fieldfootercolumn",true);
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
$xt->assign("VALOR_ORIGINAL_fieldheadercolumn",true);
$xt->assign("VALOR_ORIGINAL_fieldheader",true);
$xt->assign("VALOR_ORIGINAL_fieldcolumn",true);
$xt->assign("VALOR_ORIGINAL_fieldfootercolumn",true);
$xt->assign("VALOR_PAGO_fieldheadercolumn",true);
$xt->assign("VALOR_PAGO_fieldheader",true);
$xt->assign("VALOR_PAGO_fieldcolumn",true);
$xt->assign("VALOR_PAGO_fieldfootercolumn",true);
$xt->assign("VALOR_JUROS_fieldheadercolumn",true);
$xt->assign("VALOR_JUROS_fieldheader",true);
$xt->assign("VALOR_JUROS_fieldcolumn",true);
$xt->assign("VALOR_JUROS_fieldfootercolumn",true);
$xt->assign("VALOR_ATUAL_fieldheadercolumn",true);
$xt->assign("VALOR_ATUAL_fieldheader",true);
$xt->assign("VALOR_ATUAL_fieldcolumn",true);
$xt->assign("VALOR_ATUAL_fieldfootercolumn",true);
$xt->assign("VALOR_DESCONTO_fieldheadercolumn",true);
$xt->assign("VALOR_DESCONTO_fieldheader",true);
$xt->assign("VALOR_DESCONTO_fieldcolumn",true);
$xt->assign("VALOR_DESCONTO_fieldfootercolumn",true);
$xt->assign("DOCUMENTO_fieldheadercolumn",true);
$xt->assign("DOCUMENTO_fieldheader",true);
$xt->assign("DOCUMENTO_fieldcolumn",true);
$xt->assign("DOCUMENTO_fieldfootercolumn",true);
$xt->assign("TIPO_fieldheadercolumn",true);
$xt->assign("TIPO_fieldheader",true);
$xt->assign("TIPO_fieldcolumn",true);
$xt->assign("TIPO_fieldfootercolumn",true);
$xt->assign("SITUACAO_fieldheadercolumn",true);
$xt->assign("SITUACAO_fieldheader",true);
$xt->assign("SITUACAO_fieldcolumn",true);
$xt->assign("SITUACAO_fieldfootercolumn",true);
$xt->assign("NUMERO_CUPOM_fieldheadercolumn",true);
$xt->assign("NUMERO_CUPOM_fieldheader",true);
$xt->assign("NUMERO_CUPOM_fieldcolumn",true);
$xt->assign("NUMERO_CUPOM_fieldfootercolumn",true);
$xt->assign("VALOR_VENDA_fieldheadercolumn",true);
$xt->assign("VALOR_VENDA_fieldheader",true);
$xt->assign("VALOR_VENDA_fieldcolumn",true);
$xt->assign("VALOR_VENDA_fieldfootercolumn",true);
$xt->assign("HISTORICO_fieldheadercolumn",true);
$xt->assign("HISTORICO_fieldheader",true);
$xt->assign("HISTORICO_fieldcolumn",true);
$xt->assign("HISTORICO_fieldfootercolumn",true);

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
