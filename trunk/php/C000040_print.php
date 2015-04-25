<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000040_variables.php");

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
$layout->blocks["top"][] = "pdf";$page_layouts["C000040_print"] = $layout;


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
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CODCLIENTE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CODCLIENTE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TITULAR";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TITULAR");
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
$arr['fName'] = "OBS1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("OBS1");
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
//	TITULAR - 
			$record["TITULAR_value"] = $pageObject->showDBValue("TITULAR", $data, $keylink);
			$record["TITULAR_class"] = $pageObject->fieldClass("TITULAR");
//	EMISSAO - Short Date
			$record["EMISSAO_value"] = $pageObject->showDBValue("EMISSAO", $data, $keylink);
			$record["EMISSAO_class"] = $pageObject->fieldClass("EMISSAO");
//	VENCIMENTO - Short Date
			$record["VENCIMENTO_value"] = $pageObject->showDBValue("VENCIMENTO", $data, $keylink);
			$record["VENCIMENTO_class"] = $pageObject->fieldClass("VENCIMENTO");
//	DATA_DEPOSITO1 - Short Date
			$record["DATA_DEPOSITO1_value"] = $pageObject->showDBValue("DATA_DEPOSITO1", $data, $keylink);
			$record["DATA_DEPOSITO1_class"] = $pageObject->fieldClass("DATA_DEPOSITO1");
//	DATA_DEPOSITO2 - Short Date
			$record["DATA_DEPOSITO2_value"] = $pageObject->showDBValue("DATA_DEPOSITO2", $data, $keylink);
			$record["DATA_DEPOSITO2_class"] = $pageObject->fieldClass("DATA_DEPOSITO2");
//	DATA_DEVOLUCAO1 - Short Date
			$record["DATA_DEVOLUCAO1_value"] = $pageObject->showDBValue("DATA_DEVOLUCAO1", $data, $keylink);
			$record["DATA_DEVOLUCAO1_class"] = $pageObject->fieldClass("DATA_DEVOLUCAO1");
//	DATA_DEVOLUCAO2 - Short Date
			$record["DATA_DEVOLUCAO2_value"] = $pageObject->showDBValue("DATA_DEVOLUCAO2", $data, $keylink);
			$record["DATA_DEVOLUCAO2_class"] = $pageObject->fieldClass("DATA_DEVOLUCAO2");
//	SITUACAO - 
			$record["SITUACAO_value"] = $pageObject->showDBValue("SITUACAO", $data, $keylink);
			$record["SITUACAO_class"] = $pageObject->fieldClass("SITUACAO");
//	CODCLIENTE - 
			$record["CODCLIENTE_value"] = $pageObject->showDBValue("CODCLIENTE", $data, $keylink);
			$record["CODCLIENTE_class"] = $pageObject->fieldClass("CODCLIENTE");
//	CODBANCO - 
			$record["CODBANCO_value"] = $pageObject->showDBValue("CODBANCO", $data, $keylink);
			$record["CODBANCO_class"] = $pageObject->fieldClass("CODBANCO");
//	AGENCIA - 
			$record["AGENCIA_value"] = $pageObject->showDBValue("AGENCIA", $data, $keylink);
			$record["AGENCIA_class"] = $pageObject->fieldClass("AGENCIA");
//	CONTA - 
			$record["CONTA_value"] = $pageObject->showDBValue("CONTA", $data, $keylink);
			$record["CONTA_class"] = $pageObject->fieldClass("CONTA");
//	DATA_CONTA - Short Date
			$record["DATA_CONTA_value"] = $pageObject->showDBValue("DATA_CONTA", $data, $keylink);
			$record["DATA_CONTA_class"] = $pageObject->fieldClass("DATA_CONTA");
//	NUMERO - 
			$record["NUMERO_value"] = $pageObject->showDBValue("NUMERO", $data, $keylink);
			$record["NUMERO_class"] = $pageObject->fieldClass("NUMERO");
//	VALOR - Number
			$record["VALOR_value"] = $pageObject->showDBValue("VALOR", $data, $keylink);
			$record["VALOR_class"] = $pageObject->fieldClass("VALOR");
//	DESCONTO - Number
			$record["DESCONTO_value"] = $pageObject->showDBValue("DESCONTO", $data, $keylink);
			$record["DESCONTO_class"] = $pageObject->fieldClass("DESCONTO");
//	LIQUIDO - Number
			$record["LIQUIDO_value"] = $pageObject->showDBValue("LIQUIDO", $data, $keylink);
			$record["LIQUIDO_class"] = $pageObject->fieldClass("LIQUIDO");
//	CODVENDA - 
			$record["CODVENDA_value"] = $pageObject->showDBValue("CODVENDA", $data, $keylink);
			$record["CODVENDA_class"] = $pageObject->fieldClass("CODVENDA");
//	CODCONTAS_PAGAR - 
			$record["CODCONTAS_PAGAR_value"] = $pageObject->showDBValue("CODCONTAS_PAGAR", $data, $keylink);
			$record["CODCONTAS_PAGAR_class"] = $pageObject->fieldClass("CODCONTAS_PAGAR");
//	DESTINO - 
			$record["DESTINO_value"] = $pageObject->showDBValue("DESTINO", $data, $keylink);
			$record["DESTINO_class"] = $pageObject->fieldClass("DESTINO");
//	DATA_BAIXA - Short Date
			$record["DATA_BAIXA_value"] = $pageObject->showDBValue("DATA_BAIXA", $data, $keylink);
			$record["DATA_BAIXA_class"] = $pageObject->fieldClass("DATA_BAIXA");
//	CODCONTA_CORRENTE - 
			$record["CODCONTA_CORRENTE_value"] = $pageObject->showDBValue("CODCONTA_CORRENTE", $data, $keylink);
			$record["CODCONTA_CORRENTE_class"] = $pageObject->fieldClass("CODCONTA_CORRENTE");
//	CODCONTA - 
			$record["CODCONTA_value"] = $pageObject->showDBValue("CODCONTA", $data, $keylink);
			$record["CODCONTA_class"] = $pageObject->fieldClass("CODCONTA");
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
$xt->assign("TITULAR_fieldheadercolumn",true);
$xt->assign("TITULAR_fieldheader",true);
$xt->assign("TITULAR_fieldcolumn",true);
$xt->assign("TITULAR_fieldfootercolumn",true);
$xt->assign("EMISSAO_fieldheadercolumn",true);
$xt->assign("EMISSAO_fieldheader",true);
$xt->assign("EMISSAO_fieldcolumn",true);
$xt->assign("EMISSAO_fieldfootercolumn",true);
$xt->assign("VENCIMENTO_fieldheadercolumn",true);
$xt->assign("VENCIMENTO_fieldheader",true);
$xt->assign("VENCIMENTO_fieldcolumn",true);
$xt->assign("VENCIMENTO_fieldfootercolumn",true);
$xt->assign("DATA_DEPOSITO1_fieldheadercolumn",true);
$xt->assign("DATA_DEPOSITO1_fieldheader",true);
$xt->assign("DATA_DEPOSITO1_fieldcolumn",true);
$xt->assign("DATA_DEPOSITO1_fieldfootercolumn",true);
$xt->assign("DATA_DEPOSITO2_fieldheadercolumn",true);
$xt->assign("DATA_DEPOSITO2_fieldheader",true);
$xt->assign("DATA_DEPOSITO2_fieldcolumn",true);
$xt->assign("DATA_DEPOSITO2_fieldfootercolumn",true);
$xt->assign("DATA_DEVOLUCAO1_fieldheadercolumn",true);
$xt->assign("DATA_DEVOLUCAO1_fieldheader",true);
$xt->assign("DATA_DEVOLUCAO1_fieldcolumn",true);
$xt->assign("DATA_DEVOLUCAO1_fieldfootercolumn",true);
$xt->assign("DATA_DEVOLUCAO2_fieldheadercolumn",true);
$xt->assign("DATA_DEVOLUCAO2_fieldheader",true);
$xt->assign("DATA_DEVOLUCAO2_fieldcolumn",true);
$xt->assign("DATA_DEVOLUCAO2_fieldfootercolumn",true);
$xt->assign("SITUACAO_fieldheadercolumn",true);
$xt->assign("SITUACAO_fieldheader",true);
$xt->assign("SITUACAO_fieldcolumn",true);
$xt->assign("SITUACAO_fieldfootercolumn",true);
$xt->assign("CODCLIENTE_fieldheadercolumn",true);
$xt->assign("CODCLIENTE_fieldheader",true);
$xt->assign("CODCLIENTE_fieldcolumn",true);
$xt->assign("CODCLIENTE_fieldfootercolumn",true);
$xt->assign("CODBANCO_fieldheadercolumn",true);
$xt->assign("CODBANCO_fieldheader",true);
$xt->assign("CODBANCO_fieldcolumn",true);
$xt->assign("CODBANCO_fieldfootercolumn",true);
$xt->assign("AGENCIA_fieldheadercolumn",true);
$xt->assign("AGENCIA_fieldheader",true);
$xt->assign("AGENCIA_fieldcolumn",true);
$xt->assign("AGENCIA_fieldfootercolumn",true);
$xt->assign("CONTA_fieldheadercolumn",true);
$xt->assign("CONTA_fieldheader",true);
$xt->assign("CONTA_fieldcolumn",true);
$xt->assign("CONTA_fieldfootercolumn",true);
$xt->assign("DATA_CONTA_fieldheadercolumn",true);
$xt->assign("DATA_CONTA_fieldheader",true);
$xt->assign("DATA_CONTA_fieldcolumn",true);
$xt->assign("DATA_CONTA_fieldfootercolumn",true);
$xt->assign("NUMERO_fieldheadercolumn",true);
$xt->assign("NUMERO_fieldheader",true);
$xt->assign("NUMERO_fieldcolumn",true);
$xt->assign("NUMERO_fieldfootercolumn",true);
$xt->assign("VALOR_fieldheadercolumn",true);
$xt->assign("VALOR_fieldheader",true);
$xt->assign("VALOR_fieldcolumn",true);
$xt->assign("VALOR_fieldfootercolumn",true);
$xt->assign("DESCONTO_fieldheadercolumn",true);
$xt->assign("DESCONTO_fieldheader",true);
$xt->assign("DESCONTO_fieldcolumn",true);
$xt->assign("DESCONTO_fieldfootercolumn",true);
$xt->assign("LIQUIDO_fieldheadercolumn",true);
$xt->assign("LIQUIDO_fieldheader",true);
$xt->assign("LIQUIDO_fieldcolumn",true);
$xt->assign("LIQUIDO_fieldfootercolumn",true);
$xt->assign("CODVENDA_fieldheadercolumn",true);
$xt->assign("CODVENDA_fieldheader",true);
$xt->assign("CODVENDA_fieldcolumn",true);
$xt->assign("CODVENDA_fieldfootercolumn",true);
$xt->assign("CODCONTAS_PAGAR_fieldheadercolumn",true);
$xt->assign("CODCONTAS_PAGAR_fieldheader",true);
$xt->assign("CODCONTAS_PAGAR_fieldcolumn",true);
$xt->assign("CODCONTAS_PAGAR_fieldfootercolumn",true);
$xt->assign("DESTINO_fieldheadercolumn",true);
$xt->assign("DESTINO_fieldheader",true);
$xt->assign("DESTINO_fieldcolumn",true);
$xt->assign("DESTINO_fieldfootercolumn",true);
$xt->assign("DATA_BAIXA_fieldheadercolumn",true);
$xt->assign("DATA_BAIXA_fieldheader",true);
$xt->assign("DATA_BAIXA_fieldcolumn",true);
$xt->assign("DATA_BAIXA_fieldfootercolumn",true);
$xt->assign("CODCONTA_CORRENTE_fieldheadercolumn",true);
$xt->assign("CODCONTA_CORRENTE_fieldheader",true);
$xt->assign("CODCONTA_CORRENTE_fieldcolumn",true);
$xt->assign("CODCONTA_CORRENTE_fieldfootercolumn",true);
$xt->assign("CODCONTA_fieldheadercolumn",true);
$xt->assign("CODCONTA_fieldheader",true);
$xt->assign("CODCONTA_fieldcolumn",true);
$xt->assign("CODCONTA_fieldfootercolumn",true);
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
