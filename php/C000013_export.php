<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");
include("include/dbcommon.php");
include("classes/searchclause.php");
session_cache_limiter("none");

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
$layout->blocks["top"][] = "export";$page_layouts["C000013_export"] = $layout;


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
			$keys["NUMERO"] = refine($_REQUEST["mdelete1"][mdeleteIndex($ind)]);
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
			$keys["NUMERO"] = urldecode($arr[0]);
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

$xt->display("C000013_export.htm");

function ExportToExcel_old($cipherer)
{
	global $cCharset;
	header("Content-Type: application/vnd.ms-excel");
	header("Content-Disposition: attachment;Filename=C000013.xls");

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
	header("Content-Disposition: attachment;Filename=C000013.doc");

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
	header("Content-Disposition: attachment;Filename=C000013.xml");
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
			$values["NUMERO"] = $pageObject->showDBValue("NUMERO", $row);
			$values["BANCO"] = $pageObject->showDBValue("BANCO", $row);
			$values["CARTAO_CREDITO"] = $pageObject->showDBValue("CARTAO_CREDITO", $row);
			$values["FINANCEIRA"] = $pageObject->showDBValue("FINANCEIRA", $row);
			$values["RESSARCIMENTO"] = $pageObject->showDBValue("RESSARCIMENTO", $row);
			$values["PRAZO"] = $pageObject->showDBValue("PRAZO", $row);
			$values["COMISSAO_CREDITO"] = $pageObject->showDBValue("COMISSAO_CREDITO", $row);
			$values["COMISSAO_DEBITO"] = $pageObject->showDBValue("COMISSAO_DEBITO", $row);
			$values["REC_DEBITO"] = $pageObject->showDBValue("REC_DEBITO", $row);
			$values["REC_CREDITO"] = $pageObject->showDBValue("REC_CREDITO", $row);
			$values["CONTA_PADRAO"] = $pageObject->showDBValue("CONTA_PADRAO", $row);
			$values["TITULAR_CONTA_PADRAO"] = $pageObject->showDBValue("TITULAR_CONTA_PADRAO", $row);
		
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
	header("Content-Disposition: attachment;Filename=C000013.csv");
	
	if($eventObj->exists("ListFetchArray"))
		$row = $eventObj->ListFetchArray($rs, $pageObject);
	else
		$row = $cipherer->DecryptFetchedArray($rs);

// write header
	$outstr = "";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"NUMERO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"BANCO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CARTAO_CREDITO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"FINANCEIRA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"RESSARCIMENTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"PRAZO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COMISSAO_CREDITO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COMISSAO_DEBITO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"REC_DEBITO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"REC_CREDITO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CONTA_PADRAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"TITULAR_CONTA_PADRAO\"";
	echo $outstr;
	echo "\r\n";

// write data rows
	$iNumberOfRows = 0;
	$pageObject->viewControls->forExport = "csv";
	while((!$nPageSize || $iNumberOfRows < $nPageSize) && $row)
	{
		$values = array();
			$values["NUMERO"] = $pageObject->getViewControl("NUMERO")->showDBValue($row, "");
			$values["BANCO"] = $pageObject->getViewControl("BANCO")->showDBValue($row, "");
			$values["CARTAO_CREDITO"] = $pageObject->getViewControl("CARTAO_CREDITO")->showDBValue($row, "");
			$values["FINANCEIRA"] = $pageObject->getViewControl("FINANCEIRA")->showDBValue($row, "");
			$values["RESSARCIMENTO"] = $pageObject->getViewControl("RESSARCIMENTO")->showDBValue($row, "");
			$values["PRAZO"] = $pageObject->getViewControl("PRAZO")->showDBValue($row, "");
			$values["COMISSAO_CREDITO"] = $row["COMISSAO_CREDITO"];
			$values["COMISSAO_DEBITO"] = $row["COMISSAO_DEBITO"];
			$values["REC_DEBITO"] = $pageObject->getViewControl("REC_DEBITO")->showDBValue($row, "");
			$values["REC_CREDITO"] = $pageObject->getViewControl("REC_CREDITO")->showDBValue($row, "");
			$values["CONTA_PADRAO"] = $pageObject->getViewControl("CONTA_PADRAO")->showDBValue($row, "");
			$values["TITULAR_CONTA_PADRAO"] = $pageObject->getViewControl("TITULAR_CONTA_PADRAO")->showDBValue($row, "");

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
			$outstr.='"'.str_replace('"', '""', $values["NUMERO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["BANCO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CARTAO_CREDITO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["FINANCEIRA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["RESSARCIMENTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["PRAZO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COMISSAO_CREDITO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COMISSAO_DEBITO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["REC_DEBITO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["REC_CREDITO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CONTA_PADRAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["TITULAR_CONTA_PADRAO"]).'"';
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
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Número").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Bando").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Cartão de Crédito").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Financeira").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Ressarcimento").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Prazo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Comissão Crédito").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Comissão Débito").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Rec. Débito").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Rec. Crédito").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Conta").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Titular").'</td>';	
	}
	else
	{
		echo "<td>"."Número"."</td>";
		echo "<td>"."Bando"."</td>";
		echo "<td>"."Cartão de Crédito"."</td>";
		echo "<td>"."Financeira"."</td>";
		echo "<td>"."Ressarcimento"."</td>";
		echo "<td>"."Prazo"."</td>";
		echo "<td>"."Comissão Crédito"."</td>";
		echo "<td>"."Comissão Débito"."</td>";
		echo "<td>"."Rec. Débito"."</td>";
		echo "<td>"."Rec. Crédito"."</td>";
		echo "<td>"."Conta"."</td>";
		echo "<td>"."Titular"."</td>";
	}
	echo "</tr>";
	
// write data rows
	$iNumberOfRows = 0;
	$pageObject->viewControls->forExport = "export";
	while((!$nPageSize || $iNumberOfRows<$nPageSize) && $row)
	{
		countTotals($totals, $totalsFields, $row);
		
		$values = array();
	
					$values["NUMERO"] = $pageObject->getViewControl("NUMERO")->showDBValue($row, "");
					$values["BANCO"] = $pageObject->getViewControl("BANCO")->showDBValue($row, "");
					$values["CARTAO_CREDITO"] = $pageObject->getViewControl("CARTAO_CREDITO")->showDBValue($row, "");
					$values["FINANCEIRA"] = $pageObject->getViewControl("FINANCEIRA")->showDBValue($row, "");
					$values["RESSARCIMENTO"] = $pageObject->getViewControl("RESSARCIMENTO")->showDBValue($row, "");
					$values["PRAZO"] = $pageObject->getViewControl("PRAZO")->showDBValue($row, "");
					$values["COMISSAO_CREDITO"] = $pageObject->getViewControl("COMISSAO_CREDITO")->showDBValue($row, "");
					$values["COMISSAO_DEBITO"] = $pageObject->getViewControl("COMISSAO_DEBITO")->showDBValue($row, "");
					$values["REC_DEBITO"] = $pageObject->getViewControl("REC_DEBITO")->showDBValue($row, "");
					$values["REC_CREDITO"] = $pageObject->getViewControl("REC_CREDITO")->showDBValue($row, "");
					$values["CONTA_PADRAO"] = $pageObject->getViewControl("CONTA_PADRAO")->showDBValue($row, "");
					$values["TITULAR_CONTA_PADRAO"] = $pageObject->getViewControl("TITULAR_CONTA_PADRAO")->showDBValue($row, "");
		
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
						echo PrepareForExcel($values["NUMERO"]);
					else
						echo $values["NUMERO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["BANCO"]);
					else
						echo $values["BANCO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["CARTAO_CREDITO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["FINANCEIRA"];
			echo '</td>';
							echo '<td>';
			
									echo $values["RESSARCIMENTO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["PRAZO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["COMISSAO_CREDITO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["COMISSAO_DEBITO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["REC_DEBITO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["REC_CREDITO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CONTA_PADRAO"]);
					else
						echo $values["CONTA_PADRAO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["TITULAR_CONTA_PADRAO"]);
					else
						echo $values["TITULAR_CONTA_PADRAO"];
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
