<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000025_variables.php");

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
$layout->blocks["top"][] = "pdf";$page_layouts["C000025_print"] = $layout;


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
$arr['fName'] = "SITUACAO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("SITUACAO");
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
//	CODBARRA - 
			$record["CODBARRA_value"] = $pageObject->showDBValue("CODBARRA", $data, $keylink);
			$record["CODBARRA_class"] = $pageObject->fieldClass("CODBARRA");
//	PRODUTO - 
			$record["PRODUTO_value"] = $pageObject->showDBValue("PRODUTO", $data, $keylink);
			$record["PRODUTO_class"] = $pageObject->fieldClass("PRODUTO");
//	SITUACAO - 
			$record["SITUACAO_value"] = $pageObject->showDBValue("SITUACAO", $data, $keylink);
			$record["SITUACAO_class"] = $pageObject->fieldClass("SITUACAO");
//	CODGRUPO - 
			$record["CODGRUPO_value"] = $pageObject->showDBValue("CODGRUPO", $data, $keylink);
			$record["CODGRUPO_class"] = $pageObject->fieldClass("CODGRUPO");
//	CODSUBGRUPO - 
			$record["CODSUBGRUPO_value"] = $pageObject->showDBValue("CODSUBGRUPO", $data, $keylink);
			$record["CODSUBGRUPO_class"] = $pageObject->fieldClass("CODSUBGRUPO");
//	CODMARCA - 
			$record["CODMARCA_value"] = $pageObject->showDBValue("CODMARCA", $data, $keylink);
			$record["CODMARCA_class"] = $pageObject->fieldClass("CODMARCA");
//	UNIDADE - 
			$record["UNIDADE_value"] = $pageObject->showDBValue("UNIDADE", $data, $keylink);
			$record["UNIDADE_class"] = $pageObject->fieldClass("UNIDADE");
//	DATA_CADASTRO - Short Date
			$record["DATA_CADASTRO_value"] = $pageObject->showDBValue("DATA_CADASTRO", $data, $keylink);
			$record["DATA_CADASTRO_class"] = $pageObject->fieldClass("DATA_CADASTRO");
//	PRECOCUSTO - Number
			$record["PRECOCUSTO_value"] = $pageObject->showDBValue("PRECOCUSTO", $data, $keylink);
			$record["PRECOCUSTO_class"] = $pageObject->fieldClass("PRECOCUSTO");
//	PRECOVENDA - Number
			$record["PRECOVENDA_value"] = $pageObject->showDBValue("PRECOVENDA", $data, $keylink);
			$record["PRECOVENDA_class"] = $pageObject->fieldClass("PRECOVENDA");
//	ESTOQUE - Number
			$record["ESTOQUE_value"] = $pageObject->showDBValue("ESTOQUE", $data, $keylink);
			$record["ESTOQUE_class"] = $pageObject->fieldClass("ESTOQUE");
//	ESTOQUEMINIMO - Number
			$record["ESTOQUEMINIMO_value"] = $pageObject->showDBValue("ESTOQUEMINIMO", $data, $keylink);
			$record["ESTOQUEMINIMO_class"] = $pageObject->fieldClass("ESTOQUEMINIMO");
//	CODALIQUOTA - 
			$record["CODALIQUOTA_value"] = $pageObject->showDBValue("CODALIQUOTA", $data, $keylink);
			$record["CODALIQUOTA_class"] = $pageObject->fieldClass("CODALIQUOTA");
//	LOCALICAZAO - 
			$record["LOCALICAZAO_value"] = $pageObject->showDBValue("LOCALICAZAO", $data, $keylink);
			$record["LOCALICAZAO_class"] = $pageObject->fieldClass("LOCALICAZAO");
//	PESO - Number
			$record["PESO_value"] = $pageObject->showDBValue("PESO", $data, $keylink);
			$record["PESO_class"] = $pageObject->fieldClass("PESO");
//	CST - 
			$record["CST_value"] = $pageObject->showDBValue("CST", $data, $keylink);
			$record["CST_class"] = $pageObject->fieldClass("CST");
//	TAMANHO - 
			$record["TAMANHO_value"] = $pageObject->showDBValue("TAMANHO", $data, $keylink);
			$record["TAMANHO_class"] = $pageObject->fieldClass("TAMANHO");
//	COR - 
			$record["COR_value"] = $pageObject->showDBValue("COR", $data, $keylink);
			$record["COR_class"] = $pageObject->fieldClass("COR");
//	CSOSN - 
			$record["CSOSN_value"] = $pageObject->showDBValue("CSOSN", $data, $keylink);
			$record["CSOSN_class"] = $pageObject->fieldClass("CSOSN");
//	NOTAFISCAL - 
			$record["NOTAFISCAL_value"] = $pageObject->showDBValue("NOTAFISCAL", $data, $keylink);
			$record["NOTAFISCAL_class"] = $pageObject->fieldClass("NOTAFISCAL");
//	VALIDADE - 
			$record["VALIDADE_value"] = $pageObject->showDBValue("VALIDADE", $data, $keylink);
			$record["VALIDADE_class"] = $pageObject->fieldClass("VALIDADE");
//	CLASSIFICACAO_FISCAL - 
			$record["CLASSIFICACAO_FISCAL_value"] = $pageObject->showDBValue("CLASSIFICACAO_FISCAL", $data, $keylink);
			$record["CLASSIFICACAO_FISCAL_class"] = $pageObject->fieldClass("CLASSIFICACAO_FISCAL");
//	NCM - 
			$record["NCM_value"] = $pageObject->showDBValue("NCM", $data, $keylink);
			$record["NCM_class"] = $pageObject->fieldClass("NCM");
//	CODFORNECEDOR - 
			$record["CODFORNECEDOR_value"] = $pageObject->showDBValue("CODFORNECEDOR", $data, $keylink);
			$record["CODFORNECEDOR_class"] = $pageObject->fieldClass("CODFORNECEDOR");
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
$xt->assign("CODBARRA_fieldheadercolumn",true);
$xt->assign("CODBARRA_fieldheader",true);
$xt->assign("CODBARRA_fieldcolumn",true);
$xt->assign("CODBARRA_fieldfootercolumn",true);
$xt->assign("PRODUTO_fieldheadercolumn",true);
$xt->assign("PRODUTO_fieldheader",true);
$xt->assign("PRODUTO_fieldcolumn",true);
$xt->assign("PRODUTO_fieldfootercolumn",true);
$xt->assign("SITUACAO_fieldheadercolumn",true);
$xt->assign("SITUACAO_fieldheader",true);
$xt->assign("SITUACAO_fieldcolumn",true);
$xt->assign("SITUACAO_fieldfootercolumn",true);
$xt->assign("CODGRUPO_fieldheadercolumn",true);
$xt->assign("CODGRUPO_fieldheader",true);
$xt->assign("CODGRUPO_fieldcolumn",true);
$xt->assign("CODGRUPO_fieldfootercolumn",true);
$xt->assign("CODSUBGRUPO_fieldheadercolumn",true);
$xt->assign("CODSUBGRUPO_fieldheader",true);
$xt->assign("CODSUBGRUPO_fieldcolumn",true);
$xt->assign("CODSUBGRUPO_fieldfootercolumn",true);
$xt->assign("CODMARCA_fieldheadercolumn",true);
$xt->assign("CODMARCA_fieldheader",true);
$xt->assign("CODMARCA_fieldcolumn",true);
$xt->assign("CODMARCA_fieldfootercolumn",true);
$xt->assign("UNIDADE_fieldheadercolumn",true);
$xt->assign("UNIDADE_fieldheader",true);
$xt->assign("UNIDADE_fieldcolumn",true);
$xt->assign("UNIDADE_fieldfootercolumn",true);
$xt->assign("DATA_CADASTRO_fieldheadercolumn",true);
$xt->assign("DATA_CADASTRO_fieldheader",true);
$xt->assign("DATA_CADASTRO_fieldcolumn",true);
$xt->assign("DATA_CADASTRO_fieldfootercolumn",true);
$xt->assign("PRECOCUSTO_fieldheadercolumn",true);
$xt->assign("PRECOCUSTO_fieldheader",true);
$xt->assign("PRECOCUSTO_fieldcolumn",true);
$xt->assign("PRECOCUSTO_fieldfootercolumn",true);
$xt->assign("PRECOVENDA_fieldheadercolumn",true);
$xt->assign("PRECOVENDA_fieldheader",true);
$xt->assign("PRECOVENDA_fieldcolumn",true);
$xt->assign("PRECOVENDA_fieldfootercolumn",true);
$xt->assign("ESTOQUE_fieldheadercolumn",true);
$xt->assign("ESTOQUE_fieldheader",true);
$xt->assign("ESTOQUE_fieldcolumn",true);
$xt->assign("ESTOQUE_fieldfootercolumn",true);
$xt->assign("ESTOQUEMINIMO_fieldheadercolumn",true);
$xt->assign("ESTOQUEMINIMO_fieldheader",true);
$xt->assign("ESTOQUEMINIMO_fieldcolumn",true);
$xt->assign("ESTOQUEMINIMO_fieldfootercolumn",true);
$xt->assign("CODALIQUOTA_fieldheadercolumn",true);
$xt->assign("CODALIQUOTA_fieldheader",true);
$xt->assign("CODALIQUOTA_fieldcolumn",true);
$xt->assign("CODALIQUOTA_fieldfootercolumn",true);
$xt->assign("LOCALICAZAO_fieldheadercolumn",true);
$xt->assign("LOCALICAZAO_fieldheader",true);
$xt->assign("LOCALICAZAO_fieldcolumn",true);
$xt->assign("LOCALICAZAO_fieldfootercolumn",true);
$xt->assign("PESO_fieldheadercolumn",true);
$xt->assign("PESO_fieldheader",true);
$xt->assign("PESO_fieldcolumn",true);
$xt->assign("PESO_fieldfootercolumn",true);
$xt->assign("CST_fieldheadercolumn",true);
$xt->assign("CST_fieldheader",true);
$xt->assign("CST_fieldcolumn",true);
$xt->assign("CST_fieldfootercolumn",true);
$xt->assign("TAMANHO_fieldheadercolumn",true);
$xt->assign("TAMANHO_fieldheader",true);
$xt->assign("TAMANHO_fieldcolumn",true);
$xt->assign("TAMANHO_fieldfootercolumn",true);
$xt->assign("COR_fieldheadercolumn",true);
$xt->assign("COR_fieldheader",true);
$xt->assign("COR_fieldcolumn",true);
$xt->assign("COR_fieldfootercolumn",true);
$xt->assign("CSOSN_fieldheadercolumn",true);
$xt->assign("CSOSN_fieldheader",true);
$xt->assign("CSOSN_fieldcolumn",true);
$xt->assign("CSOSN_fieldfootercolumn",true);
$xt->assign("NOTAFISCAL_fieldheadercolumn",true);
$xt->assign("NOTAFISCAL_fieldheader",true);
$xt->assign("NOTAFISCAL_fieldcolumn",true);
$xt->assign("NOTAFISCAL_fieldfootercolumn",true);
$xt->assign("VALIDADE_fieldheadercolumn",true);
$xt->assign("VALIDADE_fieldheader",true);
$xt->assign("VALIDADE_fieldcolumn",true);
$xt->assign("VALIDADE_fieldfootercolumn",true);
$xt->assign("CLASSIFICACAO_FISCAL_fieldheadercolumn",true);
$xt->assign("CLASSIFICACAO_FISCAL_fieldheader",true);
$xt->assign("CLASSIFICACAO_FISCAL_fieldcolumn",true);
$xt->assign("CLASSIFICACAO_FISCAL_fieldfootercolumn",true);
$xt->assign("NCM_fieldheadercolumn",true);
$xt->assign("NCM_fieldheader",true);
$xt->assign("NCM_fieldcolumn",true);
$xt->assign("NCM_fieldfootercolumn",true);
$xt->assign("CODFORNECEDOR_fieldheadercolumn",true);
$xt->assign("CODFORNECEDOR_fieldheader",true);
$xt->assign("CODFORNECEDOR_fieldcolumn",true);
$xt->assign("CODFORNECEDOR_fieldfootercolumn",true);

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
