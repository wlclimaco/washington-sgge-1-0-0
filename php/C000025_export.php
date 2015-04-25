<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");
include("include/dbcommon.php");
include("classes/searchclause.php");
session_cache_limiter("none");

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

$layout = new TLayout("export2","BoldGreenTea","MobileGreenTea");
$layout->blocks["top"] = array();
$layout->containers["export"] = array();

$layout->containers["export"][] = array("name"=>"exportheader","block"=>"","substyle"=>2);


$layout->containers["export"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"range");


$layout->containers["range"] = array();

$layout->containers["range"][] = array("name"=>"exprange_header","block"=>"rangeheader_block","substyle"=>1);


$layout->containers["range"][] = array("name"=>"exprange","block"=>"range_block","substyle"=>1);


$layout->skins["range"] = "fields";

$layout->containers["export"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"fields");


$layout->containers["fields"] = array();

$layout->containers["fields"][] = array("name"=>"expoutput_header","block"=>"","substyle"=>1);


$layout->containers["fields"][] = array("name"=>"expoutput","block"=>"","substyle"=>1);


$layout->skins["fields"] = "fields";

$layout->containers["export"][] = array("name"=>"expbuttons","block"=>"","substyle"=>2);


$layout->skins["export"] = "1";
$layout->blocks["top"][] = "export";$page_layouts["C000025_export"] = $layout;


// Modify query: remove blob fields from fieldlist.
// Blob fields on an export page are shown using imager.php (for example).
// They don't need to be selected from DB in export.php itself.
//$gQuery->ReplaceFieldsWithDummies(GetBinaryFieldsIndices());

$cipherer = new RunnerCipherer($strTableName);

$strWhereClause = "";
$strHavingClause = "";
$strSearchCriteria = "and";
$selected_recs = array();
$options = "1";

header("Expires: Thu, 01 Jan 1970 00:00:01 GMT"); 
include('include/xtempl.php');
include('classes/runnerpage.php');
$xt = new Xtempl();
$id = postvalue("id") != "" ? postvalue("id") : 1;

$phpVersion = (int)substr(phpversion(), 0, 1); 
if($phpVersion > 4)
{
	include("include/export_functions.php");
	$xt->assign("groupExcel", true);
}
else
	$xt->assign("excel", true);

//array of params for classes
$params = array("pageType" => PAGE_EXPORT, "id" => $id, "tName" => $strTableName);
$params["xt"] = &$xt;
if(!$eventObj->exists("ListGetRowCount") && !$eventObj->exists("ListQuery"))
	$params["needSearchClauseObj"] = false;
$pageObject = new RunnerPage($params);

//	Before Process event
if($eventObj->exists("BeforeProcessExport"))
	$eventObj->BeforeProcessExport($conn, $pageObject);

if (@$_REQUEST["a"]!="")
{
	$options = "";
	$sWhere = "1=0";	

//	process selection
	$selected_recs = array();
	if (@$_REQUEST["mdelete"])
	{
		foreach(@$_REQUEST["mdelete"] as $ind)
		{
			$keys=array();
			$keys["CODIGO"] = refine($_REQUEST["mdelete1"][mdeleteIndex($ind)]);
			$selected_recs[] = $keys;
		}
	}
	elseif(@$_REQUEST["selection"])
	{
		foreach(@$_REQUEST["selection"] as $keyblock)
		{
			$arr=explode("&",refine($keyblock));
			if(count($arr)<1)
				continue;
			$keys = array();
			$keys["CODIGO"] = urldecode($arr[0]);
			$selected_recs[] = $keys;
		}
	}

	foreach($selected_recs as $keys)
	{
		$sWhere = $sWhere . " or ";
		$sWhere.=KeyWhere($keys);
	}


	$strSQL = $gQuery->gSQLWhere($sWhere);
	$strWhereClause=$sWhere;
	
	$_SESSION[$strTableName."_SelectedSQL"] = $strSQL;
	$_SESSION[$strTableName."_SelectedWhere"] = $sWhere;
	$_SESSION[$strTableName."_SelectedRecords"] = $selected_recs;
}

if ($_SESSION[$strTableName."_SelectedSQL"]!="" && @$_REQUEST["records"]=="") 
{
	$strSQL = $_SESSION[$strTableName."_SelectedSQL"];
	$strWhereClause = @$_SESSION[$strTableName."_SelectedWhere"];
	$selected_recs = $_SESSION[$strTableName."_SelectedRecords"];
}
else
{
	$strWhereClause = @$_SESSION[$strTableName."_where"];
	$strHavingClause = @$_SESSION[$strTableName."_having"];
	$strSearchCriteria = @$_SESSION[$strTableName."_criteria"];
	$strSQL = $gQuery->gSQLWhere($strWhereClause, $strHavingClause, $strSearchCriteria);
}

$mypage = 1;
if(@$_REQUEST["type"])
{
//	order by
	$strOrderBy = $_SESSION[$strTableName."_order"];
	if(!$strOrderBy)
		$strOrderBy = $gstrOrderBy;
	$strSQL.=" ".trim($strOrderBy);

	$strSQLbak = $strSQL;
	if($eventObj->exists("BeforeQueryExport"))
		$eventObj->BeforeQueryExport($strSQL,$strWhereClause,$strOrderBy, $pageObject);
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
				$masterKeysReq[] = $_SESSION[$strTableName."_masterkey".($i + 1)];
			$rowcount = $eventObj->ListGetRowCount($pageObject->searchClauseObj,$_SESSION[$strTableName."_mastertable"],$masterKeysReq,$selected_recs, $pageObject);
		}
		if($rowcount !== false)
			$numrows = $rowcount;
		else
			$numrows = $gQuery->gSQLRowCount($strWhereClause,$strHavingClause,$strSearchCriteria);
	}
	LogInfo($strSQL);

//	 Pagination:

	$nPageSize = 0;
	if(@$_REQUEST["records"]=="page" && $numrows)
	{
		$mypage = (integer)@$_SESSION[$strTableName."_pagenumber"];
		$nPageSize = (integer)@$_SESSION[$strTableName."_pagesize"];
		
		if(!$nPageSize)
			$nPageSize = $gSettings->getInitialPageSize();
				
		if($nPageSize<0)
			$nPageSize = 0;
			
		if($nPageSize>0)
		{
			if($numrows<=($mypage-1)*$nPageSize)
				$mypage = ceil($numrows/$nPageSize);
		
			if(!$mypage)
				$mypage = 1;
			
				}
	}
	$listarray = false;
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
		$listarray = $eventObj->ListQuery($pageObject->searchClauseObj, $arrFieldForSort, $arrHowFieldSort,
			$_SESSION[$strTableName."_mastertable"], $masterKeysReq, $selected_recs, $nPageSize, $mypage, $pageObject);
	}
	if($listarray!==false)
		$rs = $listarray;
	elseif($nPageSize>0)
	{
					$rs = db_query($strSQL,$conn);
			db_pageseek($rs,$nPageSize,$mypage);
	}
	else
		$rs = db_query($strSQL,$conn);

	if(!ini_get("safe_mode"))
		set_time_limit(300);
	
	if(substr(@$_REQUEST["type"],0,5)=="excel")
	{
//	remove grouping
		$locale_info["LOCALE_SGROUPING"]="0";
		$locale_info["LOCALE_SMONGROUPING"]="0";
				if($phpVersion > 4)
			ExportToExcel($cipherer, $pageObject);
		else
			ExportToExcel_old($cipherer);
	}
	else if(@$_REQUEST["type"]=="word")
	{
		ExportToWord($cipherer);
	}
	else if(@$_REQUEST["type"]=="xml")
	{
		ExportToXML($cipherer);
	}
	else if(@$_REQUEST["type"]=="csv")
	{
		$locale_info["LOCALE_SGROUPING"]="0";
		$locale_info["LOCALE_SDECIMAL"]=".";
		$locale_info["LOCALE_SMONGROUPING"]="0";
		$locale_info["LOCALE_SMONDECIMALSEP"]=".";
		ExportToCSV($cipherer);
	}
	db_close($conn);
	return;
}

// add button events if exist
$pageObject->addButtonHandlers();

if($options)
{
	$xt->assign("rangeheader_block",true);
	$xt->assign("range_block",true);
}

$xt->assign("exportlink_attrs", 'id="saveButton'.$pageObject->id.'"');

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

$pageObject->body["end"] .= "<script>".$pageObject->PrepareJS()."</script>";
$xt->assignbyref("body",$pageObject->body);

$xt->display("C000025_export.htm");

function ExportToExcel_old($cipherer)
{
	global $cCharset;
	header("Content-Type: application/vnd.ms-excel");
	header("Content-Disposition: attachment;Filename=C000025.xls");

	echo "<html>";
	echo "<html xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:x=\"urn:schemas-microsoft-com:office:excel\" xmlns=\"http://www.w3.org/TR/REC-html40\">";
	
	echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=".$cCharset."\">";
	echo "<body>";
	echo "<table border=1>";

	WriteTableData($cipherer);

	echo "</table>";
	echo "</body>";
	echo "</html>";
}

function ExportToWord($cipherer)
{
	global $cCharset;
	header("Content-Type: application/vnd.ms-word");
	header("Content-Disposition: attachment;Filename=C000025.doc");

	echo "<html>";
	echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=".$cCharset."\">";
	echo "<body>";
	echo "<table border=1>";

	WriteTableData($cipherer);

	echo "</table>";
	echo "</body>";
	echo "</html>";
}

function ExportToXML($cipherer)
{
	global $nPageSize,$rs,$strTableName,$conn,$eventObj, $pageObject;
	header("Content-Type: text/xml");
	header("Content-Disposition: attachment;Filename=C000025.xml");
	if($eventObj->exists("ListFetchArray"))
		$row = $eventObj->ListFetchArray($rs, $pageObject);
	else
		$row = $cipherer->DecryptFetchedArray($rs);	
	//if(!$row)
	//	return;
		
	global $cCharset;
	
	echo "<?xml version=\"1.0\" encoding=\"".$cCharset."\" standalone=\"yes\"?>\r\n";
	echo "<table>\r\n";
	$i = 0;
	$pageObject->viewControls->forExport = "xml";
	while((!$nPageSize || $i<$nPageSize) && $row)
	{
		$values = array();
			$values["CODIGO"] = $pageObject->showDBValue("CODIGO", $row);
			$values["CODBARRA"] = $pageObject->showDBValue("CODBARRA", $row);
			$values["PRODUTO"] = $pageObject->showDBValue("PRODUTO", $row);
			$values["SITUACAO"] = $pageObject->showDBValue("SITUACAO", $row);
			$values["UNIDADE"] = $pageObject->showDBValue("UNIDADE", $row);
			$values["CODGRUPO"] = $pageObject->showDBValue("CODGRUPO", $row);
			$values["DATA_CADASTRO"] = $pageObject->showDBValue("DATA_CADASTRO", $row);
			$values["CODSUBGRUPO"] = $pageObject->showDBValue("CODSUBGRUPO", $row);
			$values["CODFORNECEDOR"] = $pageObject->showDBValue("CODFORNECEDOR", $row);
			$values["PRECOCUSTO"] = $pageObject->showDBValue("PRECOCUSTO", $row);
			$values["PRECOVENDA"] = $pageObject->showDBValue("PRECOVENDA", $row);
			$values["ESTOQUE"] = $pageObject->showDBValue("ESTOQUE", $row);
			$values["ESTOQUEMINIMO"] = $pageObject->showDBValue("ESTOQUEMINIMO", $row);
			$values["CODALIQUOTA"] = $pageObject->showDBValue("CODALIQUOTA", $row);
			$values["LOCALICAZAO"] = $pageObject->showDBValue("LOCALICAZAO", $row);
			$values["PESO"] = $pageObject->showDBValue("PESO", $row);
			$values["CST"] = $pageObject->showDBValue("CST", $row);
			$values["TAMANHO"] = $pageObject->showDBValue("TAMANHO", $row);
			$values["COR"] = $pageObject->showDBValue("COR", $row);
			$values["CSOSN"] = $pageObject->showDBValue("CSOSN", $row);
			$values["NOTAFISCAL"] = $pageObject->showDBValue("NOTAFISCAL", $row);
			$values["VALIDADE"] = $pageObject->showDBValue("VALIDADE", $row);
			$values["CLASSIFICACAO_FISCAL"] = $pageObject->showDBValue("CLASSIFICACAO_FISCAL", $row);
			$values["NCM"] = $pageObject->showDBValue("NCM", $row);
			$values["CODMARCA"] = $pageObject->showDBValue("CODMARCA", $row);
		
		$eventRes = true;
		if ($eventObj->exists('BeforeOut'))
			$eventRes = $eventObj->BeforeOut($row, $values, $pageObject);
		
		if ($eventRes)
		{
			$i++;
			echo "<row>\r\n";
			foreach ($values as $fName => $val)
			{
				$field = htmlspecialchars(XMLNameEncode($fName));
				echo "<".$field.">";
				echo $values[$fName];
				echo "</".$field.">\r\n";
			}
			echo "</row>\r\n";
		}
		
		
		if($eventObj->exists("ListFetchArray"))
			$row = $eventObj->ListFetchArray($rs, $pageObject);
		else
			$row = $cipherer->DecryptFetchedArray($rs);
	}
	echo "</table>\r\n";
}

function ExportToCSV($cipherer)
{
	global $rs,$nPageSize,$strTableName,$conn,$eventObj, $pageObject;
	header("Content-Type: application/csv");
	header("Content-Disposition: attachment;Filename=C000025.csv");
	
	if($eventObj->exists("ListFetchArray"))
		$row = $eventObj->ListFetchArray($rs, $pageObject);
	else
		$row = $cipherer->DecryptFetchedArray($rs);

// write header
	$outstr = "";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODIGO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODBARRA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"PRODUTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"SITUACAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"UNIDADE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODGRUPO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_CADASTRO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODSUBGRUPO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODFORNECEDOR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"PRECOCUSTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"PRECOVENDA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"ESTOQUE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"ESTOQUEMINIMO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODALIQUOTA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"LOCALICAZAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"PESO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CST\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"TAMANHO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CSOSN\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"NOTAFISCAL\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALIDADE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CLASSIFICACAO_FISCAL\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"NCM\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODMARCA\"";
	echo $outstr;
	echo "\r\n";

// write data rows
	$iNumberOfRows = 0;
	$pageObject->viewControls->forExport = "csv";
	while((!$nPageSize || $iNumberOfRows < $nPageSize) && $row)
	{
		$values = array();
			$values["CODIGO"] = $pageObject->getViewControl("CODIGO")->showDBValue($row, "");
			$values["CODBARRA"] = $pageObject->getViewControl("CODBARRA")->showDBValue($row, "");
			$values["PRODUTO"] = $pageObject->getViewControl("PRODUTO")->showDBValue($row, "");
			$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
			$values["UNIDADE"] = $pageObject->getViewControl("UNIDADE")->showDBValue($row, "");
			$values["CODGRUPO"] = $pageObject->getViewControl("CODGRUPO")->showDBValue($row, "");
			$values["DATA_CADASTRO"] = $pageObject->getViewControl("DATA_CADASTRO")->showDBValue($row, "");
			$values["CODSUBGRUPO"] = $pageObject->getViewControl("CODSUBGRUPO")->showDBValue($row, "");
			$values["CODFORNECEDOR"] = $pageObject->getViewControl("CODFORNECEDOR")->showDBValue($row, "");
			$values["PRECOCUSTO"] = $row["PRECOCUSTO"];
			$values["PRECOVENDA"] = $row["PRECOVENDA"];
			$values["ESTOQUE"] = $row["ESTOQUE"];
			$values["ESTOQUEMINIMO"] = $row["ESTOQUEMINIMO"];
			$values["CODALIQUOTA"] = $pageObject->getViewControl("CODALIQUOTA")->showDBValue($row, "");
			$values["LOCALICAZAO"] = $pageObject->getViewControl("LOCALICAZAO")->showDBValue($row, "");
			$values["PESO"] = $row["PESO"];
			$values["CST"] = $pageObject->getViewControl("CST")->showDBValue($row, "");
			$values["TAMANHO"] = $pageObject->getViewControl("TAMANHO")->showDBValue($row, "");
			$values["COR"] = $pageObject->getViewControl("COR")->showDBValue($row, "");
			$values["CSOSN"] = $pageObject->getViewControl("CSOSN")->showDBValue($row, "");
			$values["NOTAFISCAL"] = $pageObject->getViewControl("NOTAFISCAL")->showDBValue($row, "");
			$values["VALIDADE"] = $pageObject->getViewControl("VALIDADE")->showDBValue($row, "");
			$values["CLASSIFICACAO_FISCAL"] = $pageObject->getViewControl("CLASSIFICACAO_FISCAL")->showDBValue($row, "");
			$values["NCM"] = $pageObject->getViewControl("NCM")->showDBValue($row, "");
			$values["CODMARCA"] = $pageObject->getViewControl("CODMARCA")->showDBValue($row, "");

		$eventRes = true;
		if ($eventObj->exists('BeforeOut'))
		{
			$eventRes = $eventObj->BeforeOut($row,$values, $pageObject);
		}
		if ($eventRes)
		{
			$outstr="";
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODIGO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODBARRA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["PRODUTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["SITUACAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["UNIDADE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODGRUPO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_CADASTRO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODSUBGRUPO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODFORNECEDOR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["PRECOCUSTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["PRECOVENDA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["ESTOQUE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["ESTOQUEMINIMO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODALIQUOTA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["LOCALICAZAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["PESO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CST"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["TAMANHO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CSOSN"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["NOTAFISCAL"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALIDADE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CLASSIFICACAO_FISCAL"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["NCM"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODMARCA"]).'"';
			echo $outstr;
		}
		
		$iNumberOfRows++;
		if($eventObj->exists("ListFetchArray"))
			$row = $eventObj->ListFetchArray($rs, $pageObject);
		else
			$row = $cipherer->DecryptFetchedArray($rs);
			
		if(((!$nPageSize || $iNumberOfRows<$nPageSize) && $row) && $eventRes)
			echo "\r\n";
	}
}

function WriteTableData($cipherer)
{
	global $rs,$nPageSize,$strTableName,$conn,$eventObj, $pageObject;
	
	if($eventObj->exists("ListFetchArray"))
		$row = $eventObj->ListFetchArray($rs, $pageObject);
	else
		$row = $cipherer->DecryptFetchedArray($rs);
//	if(!$row)
//		return;
// write header
	echo "<tr>";
	if($_REQUEST["type"]=="excel")
	{
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Código").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Código de Barra").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Produto").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Situação").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Unidade").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Grupo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data de Cadastro").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Sub Grupo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Fornecedor").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Preço de Custo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Preço de Venda").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Estoque").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Estoque Mínimo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Alíquota").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Localização").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Peso").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("CST").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Tamanho").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Cor").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("CSOSN").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Nota Fiscal").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Validade").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Class. Fiscal").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("NCM").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Marca").'</td>';	
	}
	else
	{
		echo "<td>"."Código"."</td>";
		echo "<td>"."Código de Barra"."</td>";
		echo "<td>"."Produto"."</td>";
		echo "<td>"."Situação"."</td>";
		echo "<td>"."Unidade"."</td>";
		echo "<td>"."Grupo"."</td>";
		echo "<td>"."Data de Cadastro"."</td>";
		echo "<td>"."Sub Grupo"."</td>";
		echo "<td>"."Fornecedor"."</td>";
		echo "<td>"."Preço de Custo"."</td>";
		echo "<td>"."Preço de Venda"."</td>";
		echo "<td>"."Estoque"."</td>";
		echo "<td>"."Estoque Mínimo"."</td>";
		echo "<td>"."Alíquota"."</td>";
		echo "<td>"."Localização"."</td>";
		echo "<td>"."Peso"."</td>";
		echo "<td>"."CST"."</td>";
		echo "<td>"."Tamanho"."</td>";
		echo "<td>"."Cor"."</td>";
		echo "<td>"."CSOSN"."</td>";
		echo "<td>"."Nota Fiscal"."</td>";
		echo "<td>"."Validade"."</td>";
		echo "<td>"."Class. Fiscal"."</td>";
		echo "<td>"."NCM"."</td>";
		echo "<td>"."Marca"."</td>";
	}
	echo "</tr>";
	
// write data rows
	$iNumberOfRows = 0;
	$pageObject->viewControls->forExport = "export";
	while((!$nPageSize || $iNumberOfRows<$nPageSize) && $row)
	{
		countTotals($totals, $totalsFields, $row);
		
		$values = array();
	
					$values["CODIGO"] = $pageObject->getViewControl("CODIGO")->showDBValue($row, "");
					$values["CODBARRA"] = $pageObject->getViewControl("CODBARRA")->showDBValue($row, "");
					$values["PRODUTO"] = $pageObject->getViewControl("PRODUTO")->showDBValue($row, "");
					$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
					$values["UNIDADE"] = $pageObject->getViewControl("UNIDADE")->showDBValue($row, "");
					$values["CODGRUPO"] = $pageObject->getViewControl("CODGRUPO")->showDBValue($row, "");
					$values["DATA_CADASTRO"] = $pageObject->getViewControl("DATA_CADASTRO")->showDBValue($row, "");
					$values["CODSUBGRUPO"] = $pageObject->getViewControl("CODSUBGRUPO")->showDBValue($row, "");
					$values["CODFORNECEDOR"] = $pageObject->getViewControl("CODFORNECEDOR")->showDBValue($row, "");
					$values["PRECOCUSTO"] = $pageObject->getViewControl("PRECOCUSTO")->showDBValue($row, "");
					$values["PRECOVENDA"] = $pageObject->getViewControl("PRECOVENDA")->showDBValue($row, "");
					$values["ESTOQUE"] = $pageObject->getViewControl("ESTOQUE")->showDBValue($row, "");
					$values["ESTOQUEMINIMO"] = $pageObject->getViewControl("ESTOQUEMINIMO")->showDBValue($row, "");
					$values["CODALIQUOTA"] = $pageObject->getViewControl("CODALIQUOTA")->showDBValue($row, "");
					$values["LOCALICAZAO"] = $pageObject->getViewControl("LOCALICAZAO")->showDBValue($row, "");
					$values["PESO"] = $pageObject->getViewControl("PESO")->showDBValue($row, "");
					$values["CST"] = $pageObject->getViewControl("CST")->showDBValue($row, "");
					$values["TAMANHO"] = $pageObject->getViewControl("TAMANHO")->showDBValue($row, "");
					$values["COR"] = $pageObject->getViewControl("COR")->showDBValue($row, "");
					$values["CSOSN"] = $pageObject->getViewControl("CSOSN")->showDBValue($row, "");
					$values["NOTAFISCAL"] = $pageObject->getViewControl("NOTAFISCAL")->showDBValue($row, "");
					$values["VALIDADE"] = $pageObject->getViewControl("VALIDADE")->showDBValue($row, "");
					$values["CLASSIFICACAO_FISCAL"] = $pageObject->getViewControl("CLASSIFICACAO_FISCAL")->showDBValue($row, "");
					$values["NCM"] = $pageObject->getViewControl("NCM")->showDBValue($row, "");
					$values["CODMARCA"] = $pageObject->getViewControl("CODMARCA")->showDBValue($row, "");
		
		$eventRes = true;
		if ($eventObj->exists('BeforeOut'))
		{
			$eventRes = $eventObj->BeforeOut($row, $values, $pageObject);
		}
		if ($eventRes)
		{
			$iNumberOfRows++;
			echo "<tr>";
		
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODIGO"]);
					else
						echo $values["CODIGO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODBARRA"]);
					else
						echo $values["CODBARRA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["PRODUTO"]);
					else
						echo $values["PRODUTO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["SITUACAO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["UNIDADE"]);
					else
						echo $values["UNIDADE"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODGRUPO"]);
					else
						echo $values["CODGRUPO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_CADASTRO"]);
					else
						echo $values["DATA_CADASTRO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODSUBGRUPO"]);
					else
						echo $values["CODSUBGRUPO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODFORNECEDOR"]);
					else
						echo $values["CODFORNECEDOR"];
			echo '</td>';
							echo '<td>';
			
									echo $values["PRECOCUSTO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["PRECOVENDA"];
			echo '</td>';
							echo '<td>';
			
									echo $values["ESTOQUE"];
			echo '</td>';
							echo '<td>';
			
									echo $values["ESTOQUEMINIMO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODALIQUOTA"]);
					else
						echo $values["CODALIQUOTA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["LOCALICAZAO"]);
					else
						echo $values["LOCALICAZAO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["PESO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CST"]);
					else
						echo $values["CST"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["TAMANHO"]);
					else
						echo $values["TAMANHO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["COR"]);
					else
						echo $values["COR"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CSOSN"]);
					else
						echo $values["CSOSN"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["NOTAFISCAL"]);
					else
						echo $values["NOTAFISCAL"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["VALIDADE"]);
					else
						echo $values["VALIDADE"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CLASSIFICACAO_FISCAL"]);
					else
						echo $values["CLASSIFICACAO_FISCAL"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["NCM"]);
					else
						echo $values["NCM"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODMARCA"]);
					else
						echo $values["CODMARCA"];
			echo '</td>';
			echo "</tr>";
		}
		
		
		if($eventObj->exists("ListFetchArray"))
			$row = $eventObj->ListFetchArray($rs, $pageObject);
		else
			$row = $cipherer->DecryptFetchedArray($rs);
	}
	
}

function XMLNameEncode($strValue)
{
	$search = array(" ","#","'","/","\\","(",")",",","[");
	$ret = str_replace($search,"",$strValue);
	$search = array("]","+","\"","-","_","|","}","{","=");
	$ret = str_replace($search,"",$ret);
	return $ret;
}

function PrepareForExcel($ret)
{
	//$ret = htmlspecialchars($str); commented for bug #6823
	if (substr($ret,0,1)== "=") 
		$ret = "&#61;".substr($ret,1);
	return $ret;

}

function countTotals(&$totals, $totalsFields, $data)
{
	for($i = 0; $i < count($totalsFields); $i ++) 
	{
		if($totalsFields[$i]['totalsType'] == 'COUNT') 
			$totals[$totalsFields[$i]['fName']]["value"] += ($data[$totalsFields[$i]['fName']]!= "");
		else if($totalsFields[$i]['viewFormat'] == "Time") 
		{
			$time = GetTotalsForTime($data[$totalsFields[$i]['fName']]);
			$totals[$totalsFields[$i]['fName']]["value"] += $time[2]+$time[1]*60 + $time[0]*3600;
		} 
		else 
			$totals[$totalsFields[$i]['fName']]["value"] += ($data[$totalsFields[$i]['fName']]+ 0);
		
		if($totalsFields[$i]['totalsType'] == 'AVERAGE')
		{
			if(!is_null($data[$totalsFields[$i]['fName']]) && $data[$totalsFields[$i]['fName']]!=="")
				$totals[$totalsFields[$i]['fName']]['numRows']++;
		}
	}
}
?>
