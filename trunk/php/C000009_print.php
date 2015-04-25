<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
include("classes/searchclause.php");

add_nocache_headers();

include("include/C000009_variables.php");

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
$layout->blocks["top"][] = "pdf";$page_layouts["C000009_print"] = $layout;


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
$arr['fName'] = "NOME";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("NOME");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "FANTASIA";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("FANTASIA");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "ENDERECO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("ENDERECO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "BAIRRO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("BAIRRO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CIDADE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CIDADE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "UF";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("UF");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CEP";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CEP");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "COMPLEMENTO";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("COMPLEMENTO");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TELEFONE1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TELEFONE1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "TELEFONE2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("TELEFONE2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "FAX";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("FAX");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CONTATO1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CONTATO1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CONTATO2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CONTATO2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CELULAR1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CELULAR1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CELULAR2";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CELULAR2");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "EMAIL";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("EMAIL");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "HOMEPAGE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("HOMEPAGE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "CNPJ";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("CNPJ");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "IE";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("IE");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "OBS1";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("OBS1");
$fieldsArr[] = $arr;
$arr = array();
$arr['fName'] = "IM";
$arr['viewFormat'] = $pageObject->pSet->getViewFormat("IM");
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
//	NOME - 
			$record["NOME_value"] = $pageObject->showDBValue("NOME", $data, $keylink);
			$record["NOME_class"] = $pageObject->fieldClass("NOME");
//	FANTASIA - 
			$record["FANTASIA_value"] = $pageObject->showDBValue("FANTASIA", $data, $keylink);
			$record["FANTASIA_class"] = $pageObject->fieldClass("FANTASIA");
//	ENDERECO - 
			$record["ENDERECO_value"] = $pageObject->showDBValue("ENDERECO", $data, $keylink);
			$record["ENDERECO_class"] = $pageObject->fieldClass("ENDERECO");
//	BAIRRO - 
			$record["BAIRRO_value"] = $pageObject->showDBValue("BAIRRO", $data, $keylink);
			$record["BAIRRO_class"] = $pageObject->fieldClass("BAIRRO");
//	CIDADE - 
			$record["CIDADE_value"] = $pageObject->showDBValue("CIDADE", $data, $keylink);
			$record["CIDADE_class"] = $pageObject->fieldClass("CIDADE");
//	UF - 
			$record["UF_value"] = $pageObject->showDBValue("UF", $data, $keylink);
			$record["UF_class"] = $pageObject->fieldClass("UF");
//	CEP - 
			$record["CEP_value"] = $pageObject->showDBValue("CEP", $data, $keylink);
			$record["CEP_class"] = $pageObject->fieldClass("CEP");
//	COMPLEMENTO - 
			$record["COMPLEMENTO_value"] = $pageObject->showDBValue("COMPLEMENTO", $data, $keylink);
			$record["COMPLEMENTO_class"] = $pageObject->fieldClass("COMPLEMENTO");
//	TELEFONE1 - 
			$record["TELEFONE1_value"] = $pageObject->showDBValue("TELEFONE1", $data, $keylink);
			$record["TELEFONE1_class"] = $pageObject->fieldClass("TELEFONE1");
//	TELEFONE2 - 
			$record["TELEFONE2_value"] = $pageObject->showDBValue("TELEFONE2", $data, $keylink);
			$record["TELEFONE2_class"] = $pageObject->fieldClass("TELEFONE2");
//	FAX - 
			$record["FAX_value"] = $pageObject->showDBValue("FAX", $data, $keylink);
			$record["FAX_class"] = $pageObject->fieldClass("FAX");
//	CONTATO1 - 
			$record["CONTATO1_value"] = $pageObject->showDBValue("CONTATO1", $data, $keylink);
			$record["CONTATO1_class"] = $pageObject->fieldClass("CONTATO1");
//	CONTATO2 - 
			$record["CONTATO2_value"] = $pageObject->showDBValue("CONTATO2", $data, $keylink);
			$record["CONTATO2_class"] = $pageObject->fieldClass("CONTATO2");
//	CELULAR1 - 
			$record["CELULAR1_value"] = $pageObject->showDBValue("CELULAR1", $data, $keylink);
			$record["CELULAR1_class"] = $pageObject->fieldClass("CELULAR1");
//	CELULAR2 - 
			$record["CELULAR2_value"] = $pageObject->showDBValue("CELULAR2", $data, $keylink);
			$record["CELULAR2_class"] = $pageObject->fieldClass("CELULAR2");
//	EMAIL - 
			$record["EMAIL_value"] = $pageObject->showDBValue("EMAIL", $data, $keylink);
			$record["EMAIL_class"] = $pageObject->fieldClass("EMAIL");
//	HOMEPAGE - 
			$record["HOMEPAGE_value"] = $pageObject->showDBValue("HOMEPAGE", $data, $keylink);
			$record["HOMEPAGE_class"] = $pageObject->fieldClass("HOMEPAGE");
//	CNPJ - 
			$record["CNPJ_value"] = $pageObject->showDBValue("CNPJ", $data, $keylink);
			$record["CNPJ_class"] = $pageObject->fieldClass("CNPJ");
//	IE - 
			$record["IE_value"] = $pageObject->showDBValue("IE", $data, $keylink);
			$record["IE_class"] = $pageObject->fieldClass("IE");
//	IM - 
			$record["IM_value"] = $pageObject->showDBValue("IM", $data, $keylink);
			$record["IM_class"] = $pageObject->fieldClass("IM");
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
$xt->assign("NOME_fieldheadercolumn",true);
$xt->assign("NOME_fieldheader",true);
$xt->assign("NOME_fieldcolumn",true);
$xt->assign("NOME_fieldfootercolumn",true);
$xt->assign("FANTASIA_fieldheadercolumn",true);
$xt->assign("FANTASIA_fieldheader",true);
$xt->assign("FANTASIA_fieldcolumn",true);
$xt->assign("FANTASIA_fieldfootercolumn",true);
$xt->assign("ENDERECO_fieldheadercolumn",true);
$xt->assign("ENDERECO_fieldheader",true);
$xt->assign("ENDERECO_fieldcolumn",true);
$xt->assign("ENDERECO_fieldfootercolumn",true);
$xt->assign("BAIRRO_fieldheadercolumn",true);
$xt->assign("BAIRRO_fieldheader",true);
$xt->assign("BAIRRO_fieldcolumn",true);
$xt->assign("BAIRRO_fieldfootercolumn",true);
$xt->assign("CIDADE_fieldheadercolumn",true);
$xt->assign("CIDADE_fieldheader",true);
$xt->assign("CIDADE_fieldcolumn",true);
$xt->assign("CIDADE_fieldfootercolumn",true);
$xt->assign("UF_fieldheadercolumn",true);
$xt->assign("UF_fieldheader",true);
$xt->assign("UF_fieldcolumn",true);
$xt->assign("UF_fieldfootercolumn",true);
$xt->assign("CEP_fieldheadercolumn",true);
$xt->assign("CEP_fieldheader",true);
$xt->assign("CEP_fieldcolumn",true);
$xt->assign("CEP_fieldfootercolumn",true);
$xt->assign("COMPLEMENTO_fieldheadercolumn",true);
$xt->assign("COMPLEMENTO_fieldheader",true);
$xt->assign("COMPLEMENTO_fieldcolumn",true);
$xt->assign("COMPLEMENTO_fieldfootercolumn",true);
$xt->assign("TELEFONE1_fieldheadercolumn",true);
$xt->assign("TELEFONE1_fieldheader",true);
$xt->assign("TELEFONE1_fieldcolumn",true);
$xt->assign("TELEFONE1_fieldfootercolumn",true);
$xt->assign("TELEFONE2_fieldheadercolumn",true);
$xt->assign("TELEFONE2_fieldheader",true);
$xt->assign("TELEFONE2_fieldcolumn",true);
$xt->assign("TELEFONE2_fieldfootercolumn",true);
$xt->assign("FAX_fieldheadercolumn",true);
$xt->assign("FAX_fieldheader",true);
$xt->assign("FAX_fieldcolumn",true);
$xt->assign("FAX_fieldfootercolumn",true);
$xt->assign("CONTATO1_fieldheadercolumn",true);
$xt->assign("CONTATO1_fieldheader",true);
$xt->assign("CONTATO1_fieldcolumn",true);
$xt->assign("CONTATO1_fieldfootercolumn",true);
$xt->assign("CONTATO2_fieldheadercolumn",true);
$xt->assign("CONTATO2_fieldheader",true);
$xt->assign("CONTATO2_fieldcolumn",true);
$xt->assign("CONTATO2_fieldfootercolumn",true);
$xt->assign("CELULAR1_fieldheadercolumn",true);
$xt->assign("CELULAR1_fieldheader",true);
$xt->assign("CELULAR1_fieldcolumn",true);
$xt->assign("CELULAR1_fieldfootercolumn",true);
$xt->assign("CELULAR2_fieldheadercolumn",true);
$xt->assign("CELULAR2_fieldheader",true);
$xt->assign("CELULAR2_fieldcolumn",true);
$xt->assign("CELULAR2_fieldfootercolumn",true);
$xt->assign("EMAIL_fieldheadercolumn",true);
$xt->assign("EMAIL_fieldheader",true);
$xt->assign("EMAIL_fieldcolumn",true);
$xt->assign("EMAIL_fieldfootercolumn",true);
$xt->assign("HOMEPAGE_fieldheadercolumn",true);
$xt->assign("HOMEPAGE_fieldheader",true);
$xt->assign("HOMEPAGE_fieldcolumn",true);
$xt->assign("HOMEPAGE_fieldfootercolumn",true);
$xt->assign("CNPJ_fieldheadercolumn",true);
$xt->assign("CNPJ_fieldheader",true);
$xt->assign("CNPJ_fieldcolumn",true);
$xt->assign("CNPJ_fieldfootercolumn",true);
$xt->assign("IE_fieldheadercolumn",true);
$xt->assign("IE_fieldheader",true);
$xt->assign("IE_fieldcolumn",true);
$xt->assign("IE_fieldfootercolumn",true);
$xt->assign("IM_fieldheadercolumn",true);
$xt->assign("IM_fieldheader",true);
$xt->assign("IM_fieldcolumn",true);
$xt->assign("IM_fieldfootercolumn",true);
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
