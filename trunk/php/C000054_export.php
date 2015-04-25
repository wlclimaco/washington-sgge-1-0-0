<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");
include("include/dbcommon.php");
include("classes/searchclause.php");
session_cache_limiter("none");

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
$layout->blocks["top"][] = "export";$page_layouts["C000054_export"] = $layout;


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

$xt->display("C000054_export.htm");

function ExportToExcel_old($cipherer)
{
	global $cCharset;
	header("Content-Type: application/vnd.ms-excel");
	header("Content-Disposition: attachment;Filename=C000054.xls");

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
	header("Content-Disposition: attachment;Filename=C000054.doc");

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
	header("Content-Disposition: attachment;Filename=C000054.xml");
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
			$values["SITUACAO"] = $pageObject->showDBValue("SITUACAO", $row);
			$values["NOME"] = $pageObject->showDBValue("NOME", $row);
			$values["ANO"] = $pageObject->showDBValue("ANO", $row);
			$values["COMBUSTIVEL"] = $pageObject->showDBValue("COMBUSTIVEL", $row);
			$values["PLACA"] = $pageObject->showDBValue("PLACA", $row);
			$values["UFPLACA"] = $pageObject->showDBValue("UFPLACA", $row);
			$values["COR"] = $pageObject->showDBValue("COR", $row);
			$values["CIDADE"] = $pageObject->showDBValue("CIDADE", $row);
			$values["SEGURO_OBRIGATORIO"] = $pageObject->showDBValue("SEGURO_OBRIGATORIO", $row);
			$values["COD_MARCA"] = $pageObject->showDBValue("COD_MARCA", $row);
			$values["COD_MODELO"] = $pageObject->showDBValue("COD_MODELO", $row);
			$values["LICENCIAMENTO"] = $pageObject->showDBValue("LICENCIAMENTO", $row);
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
	header("Content-Disposition: attachment;Filename=C000054.csv");
	
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
	$outstr.= "\"SITUACAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"NOME\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"ANO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COMBUSTIVEL\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"PLACA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"UFPLACA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CIDADE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"SEGURO_OBRIGATORIO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COD_MARCA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"COD_MODELO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"LICENCIAMENTO\"";
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
			$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
			$values["NOME"] = $pageObject->getViewControl("NOME")->showDBValue($row, "");
			$values["ANO"] = $pageObject->getViewControl("ANO")->showDBValue($row, "");
			$values["COMBUSTIVEL"] = $pageObject->getViewControl("COMBUSTIVEL")->showDBValue($row, "");
			$values["PLACA"] = $pageObject->getViewControl("PLACA")->showDBValue($row, "");
			$values["UFPLACA"] = $pageObject->getViewControl("UFPLACA")->showDBValue($row, "");
			$values["COR"] = $pageObject->getViewControl("COR")->showDBValue($row, "");
			$values["CIDADE"] = $pageObject->getViewControl("CIDADE")->showDBValue($row, "");
			$values["SEGURO_OBRIGATORIO"] = $pageObject->getViewControl("SEGURO_OBRIGATORIO")->showDBValue($row, "");
			$values["COD_MARCA"] = $pageObject->getViewControl("COD_MARCA")->showDBValue($row, "");
			$values["COD_MODELO"] = $pageObject->getViewControl("COD_MODELO")->showDBValue($row, "");
			$values["LICENCIAMENTO"] = $pageObject->getViewControl("LICENCIAMENTO")->showDBValue($row, "");
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
			$outstr.='"'.str_replace('"', '""', $values["SITUACAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["NOME"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["ANO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COMBUSTIVEL"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["PLACA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["UFPLACA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CIDADE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["SEGURO_OBRIGATORIO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COD_MARCA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["COD_MODELO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["LICENCIAMENTO"]).'"';
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
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Situação").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Nome do Veículo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Ano").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Combustível").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Placa").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("UF da Placa").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Cor").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Cidade").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Seguro Obrigatório").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Marca").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Modelo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Licenciamento").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("OBS").'</td>';	
	}
	else
	{
		echo "<td>"."Código"."</td>";
		echo "<td>"."Situação"."</td>";
		echo "<td>"."Nome do Veículo"."</td>";
		echo "<td>"."Ano"."</td>";
		echo "<td>"."Combustível"."</td>";
		echo "<td>"."Placa"."</td>";
		echo "<td>"."UF da Placa"."</td>";
		echo "<td>"."Cor"."</td>";
		echo "<td>"."Cidade"."</td>";
		echo "<td>"."Seguro Obrigatório"."</td>";
		echo "<td>"."Marca"."</td>";
		echo "<td>"."Modelo"."</td>";
		echo "<td>"."Licenciamento"."</td>";
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
					$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
					$values["NOME"] = $pageObject->getViewControl("NOME")->showDBValue($row, "");
					$values["ANO"] = $pageObject->getViewControl("ANO")->showDBValue($row, "");
					$values["COMBUSTIVEL"] = $pageObject->getViewControl("COMBUSTIVEL")->showDBValue($row, "");
					$values["PLACA"] = $pageObject->getViewControl("PLACA")->showDBValue($row, "");
					$values["UFPLACA"] = $pageObject->getViewControl("UFPLACA")->showDBValue($row, "");
					$values["COR"] = $pageObject->getViewControl("COR")->showDBValue($row, "");
					$values["CIDADE"] = $pageObject->getViewControl("CIDADE")->showDBValue($row, "");
					$values["SEGURO_OBRIGATORIO"] = $pageObject->getViewControl("SEGURO_OBRIGATORIO")->showDBValue($row, "");
					$values["COD_MARCA"] = $pageObject->getViewControl("COD_MARCA")->showDBValue($row, "");
					$values["COD_MODELO"] = $pageObject->getViewControl("COD_MODELO")->showDBValue($row, "");
					$values["LICENCIAMENTO"] = $pageObject->getViewControl("LICENCIAMENTO")->showDBValue($row, "");
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
						echo PrepareForExcel($values["SITUACAO"]);
					else
						echo $values["SITUACAO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["NOME"]);
					else
						echo $values["NOME"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["ANO"]);
					else
						echo $values["ANO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["COMBUSTIVEL"]);
					else
						echo $values["COMBUSTIVEL"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["PLACA"]);
					else
						echo $values["PLACA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["UFPLACA"]);
					else
						echo $values["UFPLACA"];
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
						echo PrepareForExcel($values["CIDADE"]);
					else
						echo $values["CIDADE"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["SEGURO_OBRIGATORIO"]);
					else
						echo $values["SEGURO_OBRIGATORIO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["COD_MARCA"]);
					else
						echo $values["COD_MARCA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["COD_MODELO"]);
					else
						echo $values["COD_MODELO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["LICENCIAMENTO"]);
					else
						echo $values["LICENCIAMENTO"];
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
