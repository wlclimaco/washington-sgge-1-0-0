<?php 
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");
include("include/dbcommon.php");
include("classes/searchclause.php");
session_cache_limiter("none");

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
$layout->blocks["top"][] = "export";$page_layouts["C000049_export"] = $layout;


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
			$selected_recs[] = $keys;
		}
	}
	elseif(@$_REQUEST["selection"])
	{
		foreach(@$_REQUEST["selection"] as $keyblock)
		{
			$arr=explode("&",refine($keyblock));
			if(count($arr)<0)
				continue;
			$keys = array();
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

$xt->display("C000049_export.htm");

function ExportToExcel_old($cipherer)
{
	global $cCharset;
	header("Content-Type: application/vnd.ms-excel");
	header("Content-Disposition: attachment;Filename=C000049.xls");

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
	header("Content-Disposition: attachment;Filename=C000049.doc");

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
	header("Content-Disposition: attachment;Filename=C000049.xml");
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
			$values["CODVENDA"] = $pageObject->showDBValue("CODVENDA", $row);
			$values["CODVENDEDOR"] = $pageObject->showDBValue("CODVENDEDOR", $row);
			$values["CODCAIXA"] = $pageObject->showDBValue("CODCAIXA", $row);
			$values["CODCLIENTE"] = $pageObject->showDBValue("CODCLIENTE", $row);
			$values["DATA_EMISSAO"] = $pageObject->showDBValue("DATA_EMISSAO", $row);
			$values["DATA_VENCIMENTO"] = $pageObject->showDBValue("DATA_VENCIMENTO", $row);
			$values["DATA_PAGAMENTO"] = $pageObject->showDBValue("DATA_PAGAMENTO", $row);
			$values["VALOR_ORIGINAL"] = $pageObject->showDBValue("VALOR_ORIGINAL", $row);
			$values["VALOR_PAGO"] = $pageObject->showDBValue("VALOR_PAGO", $row);
			$values["VALOR_JUROS"] = $pageObject->showDBValue("VALOR_JUROS", $row);
			$values["VALOR_ATUAL"] = $pageObject->showDBValue("VALOR_ATUAL", $row);
			$values["VALOR_DESCONTO"] = $pageObject->showDBValue("VALOR_DESCONTO", $row);
			$values["DOCUMENTO"] = $pageObject->showDBValue("DOCUMENTO", $row);
			$values["TIPO"] = $pageObject->showDBValue("TIPO", $row);
			$values["SITUACAO"] = $pageObject->showDBValue("SITUACAO", $row);
			$values["NUMERO_CUPOM"] = $pageObject->showDBValue("NUMERO_CUPOM", $row);
			$values["VALOR_VENDA"] = $pageObject->showDBValue("VALOR_VENDA", $row);
			$values["HISTORICO"] = $pageObject->showDBValue("HISTORICO", $row);
		
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
	header("Content-Disposition: attachment;Filename=C000049.csv");
	
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
	$outstr.= "\"CODVENDA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODVENDEDOR\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODCAIXA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"CODCLIENTE\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_EMISSAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_VENCIMENTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DATA_PAGAMENTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR_ORIGINAL\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR_PAGO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR_JUROS\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR_ATUAL\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR_DESCONTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"DOCUMENTO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"TIPO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"SITUACAO\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"NUMERO_CUPOM\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"VALOR_VENDA\"";
	if($outstr!="")
		$outstr.=",";
	$outstr.= "\"HISTORICO\"";
	echo $outstr;
	echo "\r\n";

// write data rows
	$iNumberOfRows = 0;
	$pageObject->viewControls->forExport = "csv";
	while((!$nPageSize || $iNumberOfRows < $nPageSize) && $row)
	{
		$values = array();
			$values["CODIGO"] = $pageObject->getViewControl("CODIGO")->showDBValue($row, "");
			$values["CODVENDA"] = $pageObject->getViewControl("CODVENDA")->showDBValue($row, "");
			$values["CODVENDEDOR"] = $pageObject->getViewControl("CODVENDEDOR")->showDBValue($row, "");
			$values["CODCAIXA"] = $pageObject->getViewControl("CODCAIXA")->showDBValue($row, "");
			$values["CODCLIENTE"] = $pageObject->getViewControl("CODCLIENTE")->showDBValue($row, "");
			$values["DATA_EMISSAO"] = $pageObject->getViewControl("DATA_EMISSAO")->showDBValue($row, "");
			$values["DATA_VENCIMENTO"] = $pageObject->getViewControl("DATA_VENCIMENTO")->showDBValue($row, "");
			$values["DATA_PAGAMENTO"] = $pageObject->getViewControl("DATA_PAGAMENTO")->showDBValue($row, "");
			$values["VALOR_ORIGINAL"] = $row["VALOR_ORIGINAL"];
			$values["VALOR_PAGO"] = $row["VALOR_PAGO"];
			$values["VALOR_JUROS"] = $row["VALOR_JUROS"];
			$values["VALOR_ATUAL"] = $row["VALOR_ATUAL"];
			$values["VALOR_DESCONTO"] = $row["VALOR_DESCONTO"];
			$values["DOCUMENTO"] = $pageObject->getViewControl("DOCUMENTO")->showDBValue($row, "");
			$values["TIPO"] = $pageObject->getViewControl("TIPO")->showDBValue($row, "");
			$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
			$values["NUMERO_CUPOM"] = $pageObject->getViewControl("NUMERO_CUPOM")->showDBValue($row, "");
			$values["VALOR_VENDA"] = $row["VALOR_VENDA"];
			$values["HISTORICO"] = $pageObject->getViewControl("HISTORICO")->showDBValue($row, "");

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
			$outstr.='"'.str_replace('"', '""', $values["CODVENDA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODVENDEDOR"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODCAIXA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["CODCLIENTE"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_EMISSAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_VENCIMENTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DATA_PAGAMENTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR_ORIGINAL"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR_PAGO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR_JUROS"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR_ATUAL"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR_DESCONTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["DOCUMENTO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["TIPO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["SITUACAO"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["NUMERO_CUPOM"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["VALOR_VENDA"]).'"';
			if($outstr!="")
				$outstr.=",";
			$outstr.='"'.str_replace('"', '""', $values["HISTORICO"]).'"';
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
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Venda").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Vendedor").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Caixa").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Cliente").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data de Emissão").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data de Vencimento").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Data de Pagamento").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor Original").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor Pago").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor com Juros").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor Atual").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor com Desconto").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Documento").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Tipo").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Situação").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Número Cupom").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Valor da Venda").'</td>';	
		echo '<td style="width: 100" x:str>'.PrepareForExcel("Histórico").'</td>';	
	}
	else
	{
		echo "<td>"."Código"."</td>";
		echo "<td>"."Venda"."</td>";
		echo "<td>"."Vendedor"."</td>";
		echo "<td>"."Caixa"."</td>";
		echo "<td>"."Cliente"."</td>";
		echo "<td>"."Data de Emissão"."</td>";
		echo "<td>"."Data de Vencimento"."</td>";
		echo "<td>"."Data de Pagamento"."</td>";
		echo "<td>"."Valor Original"."</td>";
		echo "<td>"."Valor Pago"."</td>";
		echo "<td>"."Valor com Juros"."</td>";
		echo "<td>"."Valor Atual"."</td>";
		echo "<td>"."Valor com Desconto"."</td>";
		echo "<td>"."Documento"."</td>";
		echo "<td>"."Tipo"."</td>";
		echo "<td>"."Situação"."</td>";
		echo "<td>"."Número Cupom"."</td>";
		echo "<td>"."Valor da Venda"."</td>";
		echo "<td>"."Histórico"."</td>";
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
					$values["CODVENDA"] = $pageObject->getViewControl("CODVENDA")->showDBValue($row, "");
					$values["CODVENDEDOR"] = $pageObject->getViewControl("CODVENDEDOR")->showDBValue($row, "");
					$values["CODCAIXA"] = $pageObject->getViewControl("CODCAIXA")->showDBValue($row, "");
					$values["CODCLIENTE"] = $pageObject->getViewControl("CODCLIENTE")->showDBValue($row, "");
					$values["DATA_EMISSAO"] = $pageObject->getViewControl("DATA_EMISSAO")->showDBValue($row, "");
					$values["DATA_VENCIMENTO"] = $pageObject->getViewControl("DATA_VENCIMENTO")->showDBValue($row, "");
					$values["DATA_PAGAMENTO"] = $pageObject->getViewControl("DATA_PAGAMENTO")->showDBValue($row, "");
					$values["VALOR_ORIGINAL"] = $pageObject->getViewControl("VALOR_ORIGINAL")->showDBValue($row, "");
					$values["VALOR_PAGO"] = $pageObject->getViewControl("VALOR_PAGO")->showDBValue($row, "");
					$values["VALOR_JUROS"] = $pageObject->getViewControl("VALOR_JUROS")->showDBValue($row, "");
					$values["VALOR_ATUAL"] = $pageObject->getViewControl("VALOR_ATUAL")->showDBValue($row, "");
					$values["VALOR_DESCONTO"] = $pageObject->getViewControl("VALOR_DESCONTO")->showDBValue($row, "");
					$values["DOCUMENTO"] = $pageObject->getViewControl("DOCUMENTO")->showDBValue($row, "");
					$values["TIPO"] = $pageObject->getViewControl("TIPO")->showDBValue($row, "");
					$values["SITUACAO"] = $pageObject->getViewControl("SITUACAO")->showDBValue($row, "");
					$values["NUMERO_CUPOM"] = $pageObject->getViewControl("NUMERO_CUPOM")->showDBValue($row, "");
					$values["VALOR_VENDA"] = $pageObject->getViewControl("VALOR_VENDA")->showDBValue($row, "");
					$values["HISTORICO"] = $pageObject->getViewControl("HISTORICO")->showDBValue($row, "");
		
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
						echo PrepareForExcel($values["CODVENDA"]);
					else
						echo $values["CODVENDA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODVENDEDOR"]);
					else
						echo $values["CODVENDEDOR"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["CODCAIXA"]);
					else
						echo $values["CODCAIXA"];
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
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_EMISSAO"]);
					else
						echo $values["DATA_EMISSAO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_VENCIMENTO"]);
					else
						echo $values["DATA_VENCIMENTO"];
			echo '</td>';
							echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DATA_PAGAMENTO"]);
					else
						echo $values["DATA_PAGAMENTO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR_ORIGINAL"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR_PAGO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR_JUROS"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR_ATUAL"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR_DESCONTO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["DOCUMENTO"]);
					else
						echo $values["DOCUMENTO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["TIPO"]);
					else
						echo $values["TIPO"];
			echo '</td>';
							echo '<td>';
			
									echo $values["SITUACAO"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["NUMERO_CUPOM"]);
					else
						echo $values["NUMERO_CUPOM"];
			echo '</td>';
							echo '<td>';
			
									echo $values["VALOR_VENDA"];
			echo '</td>';
							if($_REQUEST["type"]=="excel")
					echo '<td x:str>';
				else
					echo '<td>';
			
									if($_REQUEST["type"]=="excel")
						echo PrepareForExcel($values["HISTORICO"]);
					else
						echo $values["HISTORICO"];
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