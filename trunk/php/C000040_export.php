<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");
include("include/dbcommon.php");
include("classes/searchclause.php");
session_cache_limiter("none");

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
$layout->blocks["top"][] = "export";$page_layouts["C000040_export"] = $layout;


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

$xt->display("C000040_export.htm");

function ExportToExcel_old($cipherer)
{
	global $cCharset;
	header("Content-Type: application/vnd.ms-excel");
	header("Content-Disposition: attachment;Filename=C000040.xls");

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
	header("Content-Disposition: attachment;Filename=C000040.doc");

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
	header("Content-Disposition: attachment;Filename=C000040.xml");
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
			$values["TITULAR"] = $pageObject->showDBValue("TITULAR", $row);
			$values["EMISSAO"] = $pageObject->showDBValue("EMISSAO", $row);
			$values["VENCIMENTO"] = $pageObject->showDBValue("VENCIMENTO", $row);
			$values["DATA_DEPOSITO1"] = $pageObject->showDBValue("DATA_DEPOSITO1", $row);
			$values["DATA_DEPOSITO2"] = $pageObject->showDBValue("DATA_DEPOSITO2", $row);
			$values["DATA_DEVOLUCAO1"] = $pageObject->showDBValue("DATA_DEVOLUCAO1", $row);
			$values["DATA_DEVOLUCAO2"] = $pageObject->showDBValue("DATA_DEVOLUCAO2", $row);
			$values["SITUACAO"] = $pageObject->showDBValue("SITUACAO", $row);
			$values["CODCLIENTE"] = $pageObject->showDBValue("CODCLIENTE", $row);
			$values["CODBANCO"] = $pageObject->showDBValue("CODBANCO", $row);
			$values["AGENCIA"] = $pageObject->showDBValue("AGENCIA", $row);
			$values["CONTA"] = $pageObject->showDBValue("CONTA", $row);
			$values["DATA_CONTA"] = $pageObject->showDBValue("DATA_CONTA", $row);
			$values["NUMERO"] = $pageObject->showDBValue("NUMERO", $row);
			$values["VALOR"] = $pageObject->showDBValue("VALOR", $row);
			$values["DESCONTO"] = $pageObject->showDBValue("DESCONTO", $row);
			$values["LIQUIDO"] = $pageObject->showDBValue("LIQUIDO", $row);
			$values["CODVENDA"] = $pageObject->showDBValue("CODVENDA", $row);
			$values["CODCONTAS_PAGAR"] = $pageObject->showDBValue("CODCONTAS_PAGAR", $row);
			$values["DESTINO"] = $pageObject->showDBValue("DESTINO", $row);
			$values["DATA_BAIXA"] = $pageObject->showDBValue("DATA_BAIXA", $row);
			$values["CODCONTA_CORRENTE"] = $pageObject->showDBValue("CODCONTA_CORRENTE", $row);
			$values["CODCONTA"] = $pageObject->showDBValue("CODCONTA", $row);
			$values["OBS1"] = $pageObject->showDBValue("OBS1", $row);
		
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
	header("Content-Disposition: attachment;Filename=C000040.csv");
	
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
	$outstr.= "\"TITULAR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"EMISSAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VENCIMENTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_DEPOSITO1\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_DEPOSITO2\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_DEVOLUCAO1\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_DEVOLUCAO2\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"SITUACAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODCLIENTE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODBANCO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"AGENCIA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CONTA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_CONTA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"NUMERO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DESCONTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"LIQUIDO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODVENDA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODCONTAS_PAGAR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DESTINO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_BAIXA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODCONTA_CORRENTE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODCONTA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"OBS1\"";
	echo $outstr;
	echo "\r\n";

// write data rows
	$iNumberOfRows = 0;
	$pageObject->viewControls->forExport = "csv";
	while((!$nPageSize || $iNumberOfRows < $nPageSize) && $row)
	{
		$values = array();
			$values["CODIGO"] = $pageObject->getViewControl("CODIGO")->showDBValue($row, "");
			$values["TITULAR"] = $pageObject->getViewControl("TITULAR")->showDBValue($row, "");
			$values["EMISSAO"] = $pageObject->getViewControl("EMISSAO")->showDBValue($row, "");
			$values["VENCIMENTO"] = $pageObject->getViewControl("VENCIMENTO")->showDBValue($row, "");
			$values["DATA_DEPOSITO1"] = $pageObject->getViewControl("DATA_DEPOSITO1")->showDBValue($row, "");
			$values["DATA_DEPOSITO2"] = $pageObject->getViewControl("DATA_DEPOSITO2")->showDBValue($row, "");
			$values["DATA_DEVOLUCAO1"] = $pageObject->getViewControl("DATA_DEVOLUCAO1")->showDBValue($row, "");
			$values["DATA_DEVOLUCAO2"] = $pageObject->getViewControl("DATA_DEVOLUCAO2")->showDBValue($row, "");
			$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
			$values["CODCLIENTE"] = $pageObject->getViewControl("CODCLIENTE")->showDBValue($row, "");
			$values["CODBANCO"] = $pageObject->getViewControl("CODBANCO")->showDBValue($row, "");
			$values["AGENCIA"] = $pageObject->getViewControl("AGENCIA")->showDBValue($row, "");
			$values["CONTA"] = $pageObject->getViewControl("CONTA")->showDBValue($row, "");
			$values["DATA_CONTA"] = $pageObject->getViewControl("DATA_CONTA")->showDBValue($row, "");
			$values["NUMERO"] = $pageObject->getViewControl("NUMERO")->showDBValue($row, "");
			$values["VALOR"] = $row["VALOR"];
			$values["DESCONTO"] = $row["DESCONTO"];
			$values["LIQUIDO"] = $row["LIQUIDO"];
			$values["CODVENDA"] = $pageObject->getViewControl("CODVENDA")->showDBValue($row, "");
			$values["CODCONTAS_PAGAR"] = $pageObject->getViewControl("CODCONTAS_PAGAR")->showDBValue($row, "");
			$values["DESTINO"] = $pageObject->getViewControl("DESTINO")->showDBValue($row, "");
			$values["DATA_BAIXA"] = $pageObject->getViewControl("DATA_BAIXA")->showDBValue($row, "");
			$values["CODCONTA_CORRENTE"] = $pageObject->getViewControl("CODCONTA_CORRENTE")->showDBValue($row, "");
			$values["CODCONTA"] = $pageObject->getViewControl("CODCONTA")->showDBValue($row, "");
			$values["OBS1"] = $pageObject->getViewControl("OBS1")->showDBValue($row, "");

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
			$outstr.='"'.str_replace('"', '""', $values["TITULAR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["EMISSAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VENCIMENTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_DEPOSITO1"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_DEPOSITO2"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_DEVOLUCAO1"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_DEVOLUCAO2"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["SITUACAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODCLIENTE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODBANCO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["AGENCIA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CONTA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_CONTA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["NUMERO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DESCONTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["LIQUIDO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODVENDA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODCONTAS_PAGAR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DESTINO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_BAIXA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODCONTA_CORRENTE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODCONTA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["OBS1"]).'"';
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
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Titular").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Emissão").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Vencimento").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data Depósito nº1").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data Depósito nº2").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data Devolução nº1").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data Devolução nº2").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Situação").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Código Cliente").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Código Banco").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Agência").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Conta").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data da Conta").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Número").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Desconto").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Líquido").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Código da Venda").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Código Contas a Pagar").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Destino").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data da Baixa").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Conta Corrente").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Conta").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("OBS").'</td>';	
	}
	else
	{
		echo "<td>"."Código"."</td>";
		echo "<td>"."Titular"."</td>";
		echo "<td>"."Emissão"."</td>";
		echo "<td>"."Vencimento"."</td>";
		echo "<td>"."Data Depósito nº1"."</td>";
		echo "<td>"."Data Depósito nº2"."</td>";
		echo "<td>"."Data Devolução nº1"."</td>";
		echo "<td>"."Data Devolução nº2"."</td>";
		echo "<td>"."Situação"."</td>";
		echo "<td>"."Código Cliente"."</td>";
		echo "<td>"."Código Banco"."</td>";
		echo "<td>"."Agência"."</td>";
		echo "<td>"."Conta"."</td>";
		echo "<td>"."Data da Conta"."</td>";
		echo "<td>"."Número"."</td>";
		echo "<td>"."Valor"."</td>";
		echo "<td>"."Desconto"."</td>";
		echo "<td>"."Líquido"."</td>";
		echo "<td>"."Código da Venda"."</td>";
		echo "<td>"."Código Contas a Pagar"."</td>";
		echo "<td>"."Destino"."</td>";
		echo "<td>"."Data da Baixa"."</td>";
		echo "<td>"."Conta Corrente"."</td>";
		echo "<td>"."Conta"."</td>";
		echo "<td>"."OBS"."</td>";
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
					$values["TITULAR"] = $pageObject->getViewControl("TITULAR")->showDBValue($row, "");
					$values["EMISSAO"] = $pageObject->getViewControl("EMISSAO")->showDBValue($row, "");
					$values["VENCIMENTO"] = $pageObject->getViewControl("VENCIMENTO")->showDBValue($row, "");
					$values["DATA_DEPOSITO1"] = $pageObject->getViewControl("DATA_DEPOSITO1")->showDBValue($row, "");
					$values["DATA_DEPOSITO2"] = $pageObject->getViewControl("DATA_DEPOSITO2")->showDBValue($row, "");
					$values["DATA_DEVOLUCAO1"] = $pageObject->getViewControl("DATA_DEVOLUCAO1")->showDBValue($row, "");
					$values["DATA_DEVOLUCAO2"] = $pageObject->getViewControl("DATA_DEVOLUCAO2")->showDBValue($row, "");
					$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
					$values["CODCLIENTE"] = $pageObject->getViewControl("CODCLIENTE")->showDBValue($row, "");
					$values["CODBANCO"] = $pageObject->getViewControl("CODBANCO")->showDBValue($row, "");
					$values["AGENCIA"] = $pageObject->getViewControl("AGENCIA")->showDBValue($row, "");
					$values["CONTA"] = $pageObject->getViewControl("CONTA")->showDBValue($row, "");
					$values["DATA_CONTA"] = $pageObject->getViewControl("DATA_CONTA")->showDBValue($row, "");
					$values["NUMERO"] = $pageObject->getViewControl("NUMERO")->showDBValue($row, "");
					$values["VALOR"] = $pageObject->getViewControl("VALOR")->showDBValue($row, "");
					$values["DESCONTO"] = $pageObject->getViewControl("DESCONTO")->showDBValue($row, "");
					$values["LIQUIDO"] = $pageObject->getViewControl("LIQUIDO")->showDBValue($row, "");
					$values["CODVENDA"] = $pageObject->getViewControl("CODVENDA")->showDBValue($row, "");
					$values["CODCONTAS_PAGAR"] = $pageObject->getViewControl("CODCONTAS_PAGAR")->showDBValue($row, "");
					$values["DESTINO"] = $pageObject->getViewControl("DESTINO")->showDBValue($row, "");
					$values["DATA_BAIXA"] = $pageObject->getViewControl("DATA_BAIXA")->showDBValue($row, "");
					$values["CODCONTA_CORRENTE"] = $pageObject->getViewControl("CODCONTA_CORRENTE")->showDBValue($row, "");
					$values["CODCONTA"] = $pageObject->getViewControl("CODCONTA")->showDBValue($row, "");
					$values["OBS1"] = $pageObject->getViewControl("OBS1")->showDBValue($row, "");
		
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
						echo PrepareForExcel($values["TITULAR"]);
					else
						echo $values["TITULAR"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["EMISSAO"]);
					else
						echo $values["EMISSAO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["VENCIMENTO"]);
					else
						echo $values["VENCIMENTO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_DEPOSITO1"]);
					else
						echo $values["DATA_DEPOSITO1"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_DEPOSITO2"]);
					else
						echo $values["DATA_DEPOSITO2"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_DEVOLUCAO1"]);
					else
						echo $values["DATA_DEVOLUCAO1"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_DEVOLUCAO2"]);
					else
						echo $values["DATA_DEVOLUCAO2"];
			echo '</td>';
							echo '<td>';
			
									echo $values["SITUACAO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODCLIENTE"]);
					else
						echo $values["CODCLIENTE"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODBANCO"]);
					else
						echo $values["CODBANCO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["AGENCIA"]);
					else
						echo $values["AGENCIA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CONTA"]);
					else
						echo $values["CONTA"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_CONTA"]);
					else
						echo $values["DATA_CONTA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["NUMERO"]);
					else
						echo $values["NUMERO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR"];
			echo '</td>';
							echo '<td>';
			
									echo $values["DESCONTO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["LIQUIDO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODVENDA"]);
					else
						echo $values["CODVENDA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODCONTAS_PAGAR"]);
					else
						echo $values["CODCONTAS_PAGAR"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DESTINO"]);
					else
						echo $values["DESTINO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_BAIXA"]);
					else
						echo $values["DATA_BAIXA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODCONTA_CORRENTE"]);
					else
						echo $values["CODCONTA_CORRENTE"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODCONTA"]);
					else
						echo $values["CODCONTA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["OBS1"]);
					else
						echo $values["OBS1"];
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
