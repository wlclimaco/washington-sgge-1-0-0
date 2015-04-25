<?php 
/**
 * That function  copies all elements from associative array to object, as object properties with same names
 * Usefull when you need to copy many properties
 *
 * @param link $obj
 * @param link $argsArr
 */
function RunnerApply (&$obj, &$argsArr)
{	
	foreach ($argsArr as $key=>$var)
		setObjectProperty($obj,$key,$argsArr[$key]);
}


function GetImageFromDB($gQuery, $forPDF = false, $params = array())
{
	global $conn;
	if(!$forPDF)
	{
		$table = postvalue("table");
		$strTableName = GetTableByShort($table);
		$settings = new ProjectSettings($strTableName);
		
		if (!checkTableName($table))
		{
			return '';
		}
		
		//include("include/".$table."_variables.php");
		@ini_set("display_errors","1");
		@ini_set("display_startup_errors","1");
	
			if(!isLogged() || !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search"))
		{ 
			header("Location: login.php"); 
			return;
		}
	
		$field = postvalue("field");
		if(!$settings->checkFieldPermissions($field))
			return DisplayNoImage();
	
		//	construct sql
		$keysArr = $settings->getTableKeys();
		$keys = array();
		foreach ($keysArr as $ind=>$k)
		{
			$keys[$k]=postvalue("key".($ind+1));
		}
	}
	else
	{
		$table = @$params["table"];
	
		$strTableName = GetTableByShort($table);
		
		if (!checkTableName($table))
		{
			exit(0);
		}
		$settings = new ProjectSettings($strTableName);
		$field = @$params["field"];
		//	construct sql
		$keysArr = $settings->getTableKeys();
		$keys = array();
		foreach ($keysArr as $ind=>$k)
		{
			$keys[$k]=@$params["key".($ind+1)];
		}
	}
	if(!$gQuery->HasGroupBy())
	{
		// Do not select any fields except current (image) field.
		// If query has 'group by' clause then other fields are used in it and we may not simply cut 'em off.
		// Just don't do anything in that case.
		$gQuery->RemoveAllFieldsExcept($settings->getFieldIndex($field));
	}
	
	$where=KeyWhere($keys);
	
		$secOpt = $settings->getAdvancedSecurityType();
	if ($secOpt == ADVSECURITY_VIEW_OWN)
	{
		$where=whereAdd($where,SecuritySQL("Search"));
	}
	
	$sql = $gQuery->gSQLWhere($where);
	
	$rs = db_query($sql,$conn);
	
	if($forPDF)
	{
		if($rs && ($data=db_fetch_array($rs)))
			return $data[$field];
	}
	else
	{
		if(!$rs || !($data=db_fetch_array($rs)))
			return DisplayNoImage();
			
		if(postvalue('src') == 1 && strlen($data[$field]) > 51200)
		{
			$value = myfile_get_contents('images/icons/jpg.png');
		}
		else
			$value = db_stripslashesbinary($data[$field]);
		if(!$value)
		{
			if(postvalue("alt"))
			{
				$value=db_stripslashesbinary($data[postvalue("alt")]);
				if(!$value)
					return DisplayNoImage();
			}
			else
				return DisplayNoImage();
		}
		
		$itype=SupposeImageType($value);
		
		if(!$itype)
		{
			return DisplayFile();
		}
		if(!isset($pdf))
		{
			header("Content-Type: ".$itype);
			header("Cache-Control: private");
			SendContentLength(strlen_bin($value));
		}
		echoBinary($value);
		return '';
	}
}

function getLangFileName($langName)
{
	$langArr = array();
	$langArr["Portuguese(Brazil)"] = "Portuguese";
	return $langArr[$langName];
}

function GetGlobalData($name, $defValue)
{
	global $globalSettings;
	if(!array_key_exists($name, $globalSettings))
		return $defValue;
	return $globalSettings[$name];
}

function DisplayMap($params) 
{
	global $pageObject;
	
	$pageObject->googleMapCfg['mapsData'][$params['id']]['addressField'] = $params['addressField'] ? $params['addressField'] : "";
	$pageObject->googleMapCfg['mapsData'][$params['id']]['latField'] = $params['latField'] ? $params['latField'] : '';
	$pageObject->googleMapCfg['mapsData'][$params['id']]['lngField'] = $params['lngField'] ? $params['lngField'] : '';
	$pageObject->googleMapCfg['mapsData'][$params['id']]['width'] = $params['width'] ? $params['width'] : 0;
	$pageObject->googleMapCfg['mapsData'][$params['id']]['height'] = $params['height'] ? $params['height'] : 0;
	$pageObject->googleMapCfg['mapsData'][$params['id']]['type'] = 'BIG_MAP';
	$pageObject->googleMapCfg['mapsData'][$params['id']]['showCenterLink'] = $params['showCenterLink'] ? $params['showCenterLink'] : 0;
	$pageObject->googleMapCfg['mapsData'][$params['id']]['descField'] = $params['descField'] ? $params['descField'] : $pageObject->googleMapCfg['mapsData'][$params['id']]['addressField'];
	$pageObject->googleMapCfg['mapsData'][$params['id']]['descField'] = $params['description'] ? $params['description'] : $pageObject->googleMapCfg['mapsData'][$params['id']]['addressField'];
	
	if (isset($params['zoom']))
		$pageObject->googleMapCfg['mapsData'][$params['id']]['zoom'] = $params['zoom'];
	
	//$pageObject->googleMapCfg['bigMapDefZoom'] = $pageObject->googleMapCfg['mapsData'][$params['id']]['zoom'];
	
	if ($pageObject->googleMapCfg['mapsData'][$params['id']]['showCenterLink'])
		$pageObject->googleMapCfg['mapsData'][$params['id']]['centerLinkText'] = $params['centerLinkText'] ? $params['centerLinkText'] : '';
	
	$pageObject->googleMapCfg['mainMapIds'][] = $params['id'];
	
	if (isset($params['APIkey']))
		$pageObject->googleMapCfg['APIcode'] = $params['APIkey'];
}

function DisplayCAPTCHA() 
{
	global $pageObject;
	$pageObject->xt->assign_event($pageObject->captchaId, $pageObject, 'createCaptcha', array());
}

function checkTableName($shortTName, $type=false)
{
	if (!$shortTName)
		return false;
	
	if ("C000007" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000008" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000009" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000010" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000025" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000040" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000044" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000046" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000049" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000071" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000013" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("C000054" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	if ("WEB" == $shortTName && ($type===false || ($type!==false && $type == 0)))
		return true;
	return false;
}

//Get password field on register page
function GetPasswordField($table = "")
{
	global $cPasswordField;
	return $cPasswordField;
}
//Get user name field on register page
function GetUserNameField($table = "")
{
	global $cUserNameField;
	return $cUserNameField;
}

//Get user name field on register page
function GetDisplayNameField($table = "")
{
	global $cDisplayNameField;
	return $cDisplayNameField;
}

//Get user name field on register page
function GetEmailField($table = "")
{
	global $cEmailField;
	return $cEmailField;
}

function GetTablesList($pdfMode = false)
{
	$arr = array();
		$arr[]="C000007";
		$arr[]="C000008";
		$arr[]="C000009";
		$arr[]="C000010";
		$arr[]="C000025";
		$arr[]="C000040";
		$arr[]="C000044";
		$arr[]="C000046";
		$arr[]="C000049";
		$arr[]="C000071";
		$arr[]="C000013";
		$arr[]="C000054";
		$arr[]="WEB";
	return $arr;
}

//	return the full database field original name
function GetFullFieldName($field, $table = "", $addAs = true)
{
	if($table == ""){
		global $strTableName;
		$table = $strTableName;
	}
	$pSet = new ProjectSettings($table);
	$fname = $pSet->getFullNameField($field);
	if($pSet->hasEncryptedFields() && !isEncryptionByPHPEnabled()){
		$cipherer = new RunnerCipherer($table);
		return $cipherer->GetFieldName($fname, $field)
			.($cipherer->isFieldEncrypted($field) && $addAs ? " as ".AddFieldWrappers($field) : "");
	}
	return $fname;
}

//	return the full database field original name
function GetFullFieldNameForInsert($pSet, $field)
{
	return $pSet->getFullNameField($field);
}

// returns Chart type
function GetChartType($shorttable)
{
	return "";
}

////////////////////////////////////////////////////////////////////////////////
// data output functions
////////////////////////////////////////////////////////////////////////////////

function GetShorteningForLargeText($strValue, $cNumberOfChars)
{
		$useUTF8 = true;

	$ret = "";
	if($useUTF8)
		$ret = utf8_substr($strValue, 0, $cNumberOfChars );
	else
		$ret = substr($strValue, 0, $cNumberOfChars );
	return htmlspecialchars($ret);
}

function ProcessLargeText($pSet, $strValue, $iquery = "",$table = "", $mode = MODE_LIST, $format = "", $isMobileLookup = false, 
	$isReport = false)
{
	$cNumberOfChars = $pSet->getNumberOfChars();
		
	if(substr($strValue,0,8) == "<a href="
		|| substr($strValue,0,23) == "<img src=\"images/check_")
		return $strValue;
		
	$needShortening = $format!=EDIT_FORMAT_LOOKUP_WIZARD && $cNumberOfChars > 0 && strlen($strValue) > $cNumberOfChars;
	if($needShortening && ($mode==PAGE_LIST || $mode==PAGE_REPORT)  && !$isMobileLookup)
	{
		global $strTableName;
		if(!$table)
			$table = $strTableName;
		
		$ret = GetShorteningForLargeText($strValue, $cNumberOfChars);
		$ret.=' <a href="javascript:void(0);" query="fulltext.php?pagetype='.$pSet->_viewPage.'&table='.GetTableURL($table)
			.'&'.$iquery.'">'."Mais".' ...</a>';
	}
	else if($needShortening && ($mode == PAGE_EXPORT && $isReport || $mode == PAGE_PRINT || $mode == PAGE_RPRINT || $isMobileLookup))
	{
		$ret = GetShorteningForLargeText($strValue, $cNumberOfChars)." ...";
	}
	else
		$ret = htmlspecialchars($strValue);

/*Left to future developments 
//	highlight search results
	if ($mode==MODE_LIST && $_SESSION[$strTableName."_search"]==1)
	{
		$ind = 0;
		$searchopt=$_SESSION[$strTableName."_searchoption"];
		$searchfor=$_SESSION[$strTableName."_searchfor"];
//		highlight Contains search
		if($searchopt=="Contains")
		{
			while ( ($ind = my_stripos($ret, $searchfor, $ind)) !== false )
			{
				$ret = substr($ret, 0, $ind) . "<span class=highlight>". substr($ret, $ind, strlen($searchfor)) ."</span>" . substr($ret, $ind + strlen($searchfor));
				$ind+= strlen("<span class=highlight>") + strlen($searchfor) + strlen("</span>");
			}
		}
//		highlight Starts with search
		elseif($searchopt=="Starts with ...")
		{
			if( !strncasecmp($ret, $searchfor,strlen($searchfor)) )
				$ret = "<span class=highlight>". substr($ret, 0, strlen($searchfor)) ."</span>" . substr($ret, strlen($searchfor));
		}
		elseif($searchopt=="Equals")
		{
			if( !strcasecmp($ret, $searchfor) )
				$ret = "<span class=highlight>". $ret ."</span>";
		}
		elseif($searchopt=="More than ...")
		{
			if( strtoupper($ret)>strtoupper($searchfor) )
				$ret = "<span class=highlight>". $ret ."</span>";
		}
		elseif($searchopt=="Less than ...")
		{
			if( strtoupper($ret)<strtoupper($searchfor) )
				$ret = "<span class=highlight>". $ret ."</span>";
		}
		elseif($searchopt=="Equal or more than ...")
		{
			if( strtoupper($ret)>=strtoupper($searchfor) )
				$ret = "<span class=highlight>". $ret ."</span>";
		}
		elseif($searchopt=="Equal or less than ...")
		{
			if( strtoupper($ret)<=strtoupper($searchfor) )
				$ret = "<span class=highlight>". $ret ."</span>";
		}
	}
*/
	return nl2br($ret);
}

//	construct hyperlink
function GetHyperlink($str, $field, $data, $ptype, $table = "")
{
	global $strTableName;
	if(!strlen($table))
		$table = $strTableName;
	if(!strlen($str))
		return "";
	$ret = $str;
	$title = $ret;
	$link = $ret;
	if(substr($ret,strlen($ret)-1)=='#')
	{
		$i = strpos($ret,'#');
		$title = substr($ret,0,$i);
		$link = substr($ret,$i+1,strlen($ret)-$i-2);
		if(!$title)
			$title = $link;
	}
	$target = "";
	if(strpos($link,"://")===false && substr($link,0,7)!="mailto:")
		$link=$prefix.$link;
	$ret='<a href="'.$link.'"'.$target.'>'.$title.'</a>';
	return $ret;
}

//	add prefix to the URL
function AddLinkPrefix($pSet, $field, $link,$table="")
{
	if(strpos($link,"://")===false && substr($link,0,7)!="mailto:")
		return $pSet->getLinkPrefix($field).$link;
	return $link;
}

function GetTotalsForTime($value)
{
	$time=parsenumbers($value);
	while(count($time)<3)
		$time[]=0;
	return $time;
}

//	return Totals string
function GetTotals($field, $value, $stype, $iNumberOfRows, $sFormat, $ptype)
{
	global $strTableName;
	$pSet = new ProjectSettings($strTableName, $ptype);
	$days = 0;
	if($stype == "AVERAGE")
	{
		if($iNumberOfRows)
		{	
			if($sFormat == FORMAT_TIME)
			{
				if($value)
				{
					$value = round($value/$iNumberOfRows,0);
					$s = $value % 60;
					$value -= $s;
					$value /= 60;
					$m = $value % 60;
					$value -= $m;
					$value /= 60;
					$h = $value % 24;
					$value -= $h;
					$value /= 24;
					$d = $value;
					
					$value = ($d!=0 ? $d.'d ' : ''). mysprintf("%02d:%02d:%02d",array($h,$m,$s));
				}
			}
			else $value = round($value/$iNumberOfRows,2);
		}
		else
			return "";
	}
	if($stype == "TOTAL")
	{
		if($sFormat == FORMAT_TIME)
		{
			if($value)
			{
				$s = $value % 60;
				$value -= $s;
				$value /= 60;
				$m = $value % 60;
				$value -= $m;
				$value /= 60;
				$h = $value % 24;
				$value -= $h;
				$value /= 24;
				$d = $value;
				$value = ($d!=0 ? $d.'d ' : ''). mysprintf("%02d:%02d:%02d",array($h,$m,$s));
			}
		}
	}
	$sValue = "";
	$data = array($field => $value);
	if($sFormat == FORMAT_CURRENCY)
	 	$sValue = str_format_currency($value);
	else if($sFormat == FORMAT_PERCENT)
		$sValue = str_format_number($value*100)."%"; 
	else if($sFormat == FORMAT_NUMBER)
 		$sValue = str_format_number($value, $pSet->isDecimalDigits($field));
	else if($sFormat == FORMAT_CUSTOM && $stype!="COUNT")
	{
		include_once getabspath('classes/controls/ViewControlsContainer.php');
		$viewControls = new ViewControlsContainer($pSet, $ptype);			
		$sValue = $viewControls->showDBValue($field, $data);
	}
	else 
 		$sValue = $value;
	
	if($stype == "COUNT") 
		return $value;
	if($stype == "TOTAL") 
		return $sValue;
	if($stype == "AVERAGE") 
		return $sValue;
	return "";
}

//	display Lookup Wizard value in List/View mode
/*function DisplayLookupWizard($field, $value, $data, $keylink, $mode, $ptype)
{
	global $conn, $strTableName;
	$pSet = new ProjectSettings($strTableName, $ptype);
	if(!strlen($value))
		return "";
		
	$nLookupType = $pSet->getLookupType($field);

	$lookupTable = $pSet->getLookupTable($field);
	$displayFieldName = $pSet->getDisplayField($field);
	$linkFieldName = $pSet->getLinkField($field);
	$linkAndDisplaySame = $displayFieldName == $linkFieldName;
	
	$where = "";
	$out = "";
	$lookupvalue = $value;
	$iquery = "field=".htmlspecialchars(rawurlencode($field)).$keylink; 
	
	if($nLookupType == LT_QUERY){
		$lookupPSet = new ProjectSettings($lookupTable, $ptype);
		$lookupQueryObj = $lookupPSet->getSQLQuery();
		if($pSet->getCustomDisplay($field))
			$lookupQueryObj->AddCustomExpression($displayFieldName, $lookupPSet, $strTableName, $field);
		$lookupQueryObj->ReplaceFieldsWithDummies($lookupPSet->getBinaryFieldsIndices());
		$cipherer = new RunnerCipherer($lookupTable);
		$lookupIndexes = GetLookupFieldsIndexes($pSet, $field);
		$displayFieldIndex = $lookupIndexes["displayFieldIndex"];
	}else{
		$cipherer = new RunnerCipherer($strTableName);
		$LookupSQL= "SELECT ";
		$LookupSQL.= $pSet->getLWDisplayField($field);
		$LookupSQL.= " FROM ".AddTableWrappers($pSet->getLookupTable($field))." WHERE ";
		$displayFieldIndex = 0;
	}
	$where = GetLWWhere($field, $ptype);
	if($pSet->multiSelect($field))
	{
		$arr = splitvalues($value);
		$numeric = true;
		$type = $pSet->getLWLinkFieldType($field);
		if(!$type)
		{
			foreach($arr as $val)
				if(strlen($val) && !is_numeric($val))
				{
					$numeric=false;
					break;
				}
		}
		else
			$numeric = !NeedQuotes($type);
		$in = "";
		foreach($arr as $val)
		{
			if($numeric && !strlen($val))
				continue;
			if(strlen($in))
				$in.= ",";
			if($numeric)
				$in.= ($val+0);
			else
				$in.= db_prepare_string($cipherer->EncryptField($nLookupType == LT_QUERY ? $linkFieldName : $field, $val));
		}
		if(strlen($in))
		{
			if($nLookupType == LT_QUERY){
				$inWhere = GetFullFieldName($linkFieldName, $lookupTable, false)." in (".$in.")";
				if(strlen($where))
					$inWhere.=" and (".$where.")";
				$LookupSQL = $lookupQueryObj->toSql(whereAdd($lookupQueryObj->m_where->toSql($lookupQueryObj), $inWhere));
			}else{
				$LookupSQL.= $pSet->getLWLinkField($field)." in (".$in.")";
				if(strlen($where))
					$LookupSQL.=" and (".$where.")";
			}
			LogInfo($LookupSQL);
			$rsLookup = db_query($LookupSQL,$conn);
			$found = false;
			$lookupArrTmp = array(); 
			$lookupArr = array(); 
			while($lookuprow=db_fetch_numarray($rsLookup))
			{
				$lookupArrTmp[] = $lookuprow[$displayFieldIndex];
			}
			$lookupArr = array_unique($lookupArrTmp);
			foreach($lookupArr as $lookupvalue)
			{
				if($found)
					$out.= ",";
				$found = true;
				$outVal = GetDataInt($lookupvalue, $data, $field, $pSet->getViewFormat($field), $ptype);
				$out.= $nLookupType == LT_QUERY || $linkAndDisplaySame ? 
					$cipherer->DecryptField($nLookupType == LT_QUERY ? $displayFieldName : $field, $outVal) : $outVal;
			}
			if($found)
			{
				if($pSet->NeedEncode($field) && $mode!=MODE_EXPORT)
					return ProcessLargeText($pSet, $out, $iquery, "", $mode, $pSet->getEditFormat($field));
				else
					return $out;
			}
		}
	}
	else
	{
		$found = false;
		$strdata = $cipherer->MakeDBValue($nLookupType == LT_QUERY ? $linkFieldName : $field, $value, "", "", true);
		if($nLookupType == LT_QUERY){
			$strWhere = GetFullFieldName($linkFieldName, $lookupTable, false)." = " . $strdata;
			if(strlen($where))
				$strWhere.= " and (".$where.")";
			$LookupSQL = $lookupQueryObj->toSql(whereAdd($lookupQueryObj->m_where->toSql($lookupQueryObj), $strWhere));
		}else{
			$strWhere = $pSet->getLWLinkField($field)." = " . $strdata;
			if(strlen($where))
				$strWhere.= " and (".$where.")";
			$LookupSQL.= $strWhere;
		}
		LogInfo($LookupSQL);
		$rsLookup = db_query($LookupSQL,$conn);
		if($lookuprow = db_fetch_numarray($rsLookup)){
			$lookupvalue = $lookuprow[$displayFieldIndex];
			$found = true;
		}
	}
	if(!$out){
		if($found && ($nLookupType == LT_QUERY || $linkAndDisplaySame)){
			$lookupvalue = $cipherer->DecryptField($nLookupType == LT_QUERY ? $displayFieldName : $field, $lookupvalue);
		}
		$out = GetDataInt($lookupvalue, $data, $field, $pSet->getViewFormat($field), $ptype);
	}
	
	if($pSet->NeedEncode($field) && $mode!=MODE_EXPORT)
		$value = ProcessLargeText($pSet, $out ,$iquery, "", $mode, $pSet->getEditFormat($field));
	else
		$value = $out;
	return $value;
}*/

function DisplayNoImage()
{
	$path = getabspath("images/no_image.gif");
	header("Content-Type: image/gif");
	printfile($path);
}

function DisplayFile()
{
	$path = getabspath("images/file.gif");
	header("Content-Type: image/gif");
	printfile($path);
}

////////////////////////////////////////////////////////////////////////////////
// miscellaneous functions
////////////////////////////////////////////////////////////////////////////////
//	analog of strrpos function
function my_strrpos($haystack, $needle)
{
	$index = strpos(strrev($haystack), strrev($needle));
	if($index === false)
		return false;
	$index = strlen($haystack) - strlen($needle) - $index;
	return $index;
}

//	utf-8 analog of strlen function
function strlen_utf8($str)
{
	$len=0;
	$i=0;
	$olen=strlen($str);
	while($i<$olen)
	{
		$c=ord(substr($str,$i,1));
		if($c<128)
			$i++;
		else if($i<$olen-1 && $c>=192 && $c<=223)
			$i+=2;
		else if($i<$olen-2 && $c>=224 && $c<=239)
			$i+=3;
		else if($i<$olen-3 && $c>=240)
			$i+=4;
		else
			break;
		$len++;
	}
	return $len;
}

//	utf-8 analog of substr function
function substr_utf8($str,$index,$strlen)
{
	if($strlen<=0)
		return "";
	$len=0;
	$i=0;
	$olen=strlen($str);
	$oindex=-1;
	while($i<$olen)
	{
		if($len==$index)
			$oindex=$i;
		
		$c=ord(substr($str,$i,1));
		if($c<128)
			$i++;
		else if($i<$olen-1 && $c>=192 && $c<=223)
			$i+=2;
		else if($i<$olen-2 && $c>=224 && $c<=239)
			$i+=3;
		else if($i<$olen-3 && $c>=240)
			$i+=4;
		else
			break;
		$len++;
		if($oindex>=0 && $len==$index+$strlen)
			return substr($str,$oindex,$i-$oindex);
	}
	if($oindex>0)
		return substr($str,$oindex,$olen-$oindex);
	return "";
}


//	prepare string for JavaScript. Replace ' with \' and linebreaks with \r\n
function jsreplace($str)
{
	$ret= str_replace(array("\\","'","\r","\n"),array("\\\\","\\'","\\r","\\n"),$str);
	return my_str_ireplace("</script>","</scr'+'ipt>",$ret);
}

function LogInfo($SQL)
{/*
	global $dSQL,$dDebug;
	$dSQL=$SQL;
	if($dDebug)
	{
		echo $dSQL;
		echo "<br>";
	}*/
}

//	check if file extension is image extension
function CheckImageExtension($filename)
{
	if(strlen($filename)<4)
		return false;
	$ext=strtoupper(substr($filename,strlen($filename)-4));
	if($ext==".GIF" || $ext==".JPG" || $ext=="JPEG" || $ext==".PNG" || $ext==".BMP")
		return $ext;
	return false;
} 

function RTESafe($strText)
{
//	returns safe code for preloading in the RTE
	$tmpString="";
	
	$tmpString = trim($strText);
	if(!$tmpString) return "";
	
//	convert all types of single quotes
	$tmpString = str_replace( chr(145), chr(39),$tmpString);
	$tmpString = str_replace( chr(146), chr(39),$tmpString);
	$tmpString = str_replace("'", "&#39;",$tmpString);
	
//	convert all types of double quotes
	$tmpString = str_replace(chr(147), chr(34),$tmpString);
	$tmpString = str_replace(chr(148), chr(34),$tmpString);
	
//	replace carriage returns & line feeds
	$tmpString = str_replace(chr(10), " ",$tmpString);
	$tmpString = str_replace(chr(13), " ",$tmpString);
	
	return $tmpString;
}

function html_special_decode($str)
{
	$ret=$str;
	$ret=str_replace("&gt;",">",$ret);
	$ret=str_replace("&lt;","<",$ret);
	$ret=str_replace("&quot;","\"",$ret);
	$ret=str_replace("&#039;","'",$ret);
	$ret=str_replace("&#39;","'",$ret);
	$ret=str_replace("&amp;","&",$ret);
	return $ret;
}

////////////////////////////////////////////////////////////////////////////////
// database and SQL related functions
////////////////////////////////////////////////////////////////////////////////

//	add clause to WHERE or HAVING expression
function whereAdd($where,$clause)
{
	if(!strlen($clause))
		return $where;
	if(!strlen($where))
		return $clause;
	return "(".$where.") and (".$clause.")";
}

//	add WHERE clause to SQL string
function AddWhere($sql,$where)
{
	if(!strlen($where))
		return $sql;
	$sql=str_replace(array("\r\n","\n","\t")," ",$sql);
	$tsql = strtolower($sql);
	$n = my_strrpos($tsql," where ");
	$n1 = my_strrpos($tsql," group by ");
	$n2 = my_strrpos($tsql," order by ");
	if($n1===false)
		$n1=strlen($tsql);
	if($n2===false)
		$n2=strlen($tsql);
	if ($n1>$n2)
		$n1=$n2;
	if($n===false)
		return substr($sql,0,$n1)." where ".$where.substr($sql,$n1);
	else
		return substr($sql,0,$n+strlen(" where "))."(".substr($sql,$n+strlen(" where "),$n1-$n-strlen(" where ")).") and (".$where.")".substr($sql,$n1);
}

//	construct WHERE clause with key values
function KeyWhere(&$keys, $table="")
{
	global $strTableName;
	if(!$table)
		$table = $strTableName;
	$strWhere="";
	
	$pSet = new ProjectSettings($table);
	$cipherer = new RunnerCipherer($table);
	$keyFields = $pSet->getTableKeys();
	foreach($keyFields as $kf)
	{
		if(strlen($strWhere))
			$strWhere.=" and ";
		$value = $cipherer->MakeDBValue($kf, $keys[$kf], "", "", true);
			$valueisnull = ($value==="null");
		if($valueisnull)
			$strWhere.= GetFullFieldNameForInsert($pSet, $kf)." is null";
		else
			$strWhere.= GetFullFieldName($kf, $table, false)."=".$cipherer->MakeDBValue($kf, $keys[$kf], "", "", true);
	}
	return $strWhere;
}

//	consctruct SQL WHERE clause for simple search
function StrWhereExpression($strField, $SearchFor, $strSearchOption, $SearchFor2, $p_cipherer)
{
	global $strTableName;
	$pSet = new ProjectSettings($strTableName, PAGE_SEARCH);
	$cipherer = new RunnerCipherer($strTableName);
	$type = $pSet->getFieldType($strField);
	// Filed name for encryption functions
	$encFieldName = $strField;
	
	$ismssql=false;
	
	$isdb2=false;

	$isMysql = false;

	$btexttype=IsTextType($type);

	if($strSearchOption=='Empty')
	{
		if(IsCharType($type) && (!$ismssql || !$btexttype))
			return "(".GetFullFieldNameForInsert($pSet, $strField)." is null or ".GetFullFieldNameForInsert($pSet, $strField)."='')";			
		elseif ($ismssql && $btexttype)	
			return "(".GetFullFieldNameForInsert($pSet, $strField)." is null or ".GetFullFieldNameForInsert($pSet, $strField)." LIKE '')";
		else
			return GetFullFieldNameForInsert($pSet, $strField)." is null";
	}
	$strQuote="";
	if(NeedQuotes($type))
		$strQuote = "'";
//	return none if trying to compare numeric field and string value
	$sSearchFor=$SearchFor;
	$sSearchFor2=$SearchFor2;
	if(IsBinaryType($type))
		return "";
	

	
	if(IsDateFieldType($type) && $strSearchOption!="Contains" && $strSearchOption!="Starts with" )
	{
		$time=localdatetime2db($SearchFor);
		if($time=="null")
			return "";
		$sSearchFor=db_datequotes($time);
		if($strSearchOption=="Between")
		{
			$time=localdatetime2db($SearchFor2);
			if($time=="null")
				$sSearchFor2="";
			else
				$sSearchFor2=db_datequotes($time);
		}
	}
	
	if(!$strQuote && !is_numeric($sSearchFor))
		return "";
	
	if($cipherer->isFieldPHPEncrypted($encFieldName))
		$sSearchFor = $cipherer->MakeDBValue($encFieldName, $sSearchFor);
	else{
		if(!$strQuote && $strSearchOption!="Contains" && $strSearchOption!="Starts with")
		{
			$sSearchFor = 0+$sSearchFor;
			$sSearchFor2 = 0+$sSearchFor2;
		}
		else if(!IsDateFieldType($type) && $strSearchOption!="Contains" && $strSearchOption!="Starts with")
		{
			if($btexttype)
			{
				$sSearchFor=db_prepare_string($sSearchFor);
				if($strSearchOption=="Between" && $sSearchFor2)
					$sSearchFor2=db_prepare_string($sSearchFor2);
			}
			else
			{
				$sSearchFor = $pSet->isEnableUpper(db_prepare_string($sSearchFor));
				if($strSearchOption=="Between" && $sSearchFor2)
					$sSearchFor2 = $pSet->isEnableUpper(db_prepare_string($sSearchFor2));
			}
		}
	}

	if(IsCharType($type) && !$btexttype)
	{
		if(!$cipherer->isFieldPHPEncrypted($encFieldName))
			$strField = $pSet->isEnableUpper(GetFullFieldName($strField, "", false));
		else 
			$strField=GetFullFieldName($strField);
	}
	elseif($strSearchOption=="Contains" || $strSearchOption=="Starts with")
	{
		$strField = db_field2char(GetFullFieldName($strField),$type);
	}
	elseif($pSet->getViewFormat($strField)==FORMAT_TIME)
	{
		$strField = db_field2time(GetFullFieldName($strField),$type);
	}
	else 
	{
		$strField=GetFullFieldName($strField);
	}

/*
	elseif ($ismssql && !$btexttype && ($strSearchOption=="Contains" || $strSearchOption=="Starts with"))
		$strField="convert(varchar(50),".GetFullFieldName($strField).")";
	elseif ($isdb2 && !$btexttype && ($strSearchOption=="Contains" || $strSearchOption=="Starts with"))
		$strField="char(".GetFullFieldName($strField).")";
	else 
		$strField=GetFullFieldName($strField);
*/
	$ret="";
		$like="like";
	
	if ($isMysql)
	{
		$sSearchForMysql = str_replace('\\\\', '\\\\\\\\', $sSearchFor); 
	}
	if($strSearchOption=="Contains")
	{
		if ($isMysql)
		{
			$sSearchFor = $sSearchForMysql;
		}
		if($cipherer->isFieldPHPEncrypted($encFieldName))
			return $strField."=".$sSearchFor;
		
		if(IsCharType($type) && !$btexttype)
			return $strField." ".$like." ".$pSet->isEnableUpper(db_prepare_string("%".$sSearchFor."%"));
		else
			return $strField." ".$like." ".db_prepare_string("%".$sSearchFor."%");
	}
	else if($strSearchOption=="Equals") 
		return $strField."=".$sSearchFor;
	else if($strSearchOption=="Starts with")
	{
		if ($isMysql)
		{
			$sSearchFor = $sSearchForMysql;
		}
		if(IsCharType($type) && !$btexttype)
			return $strField." ".$like." ".$pSet->isEnableUpper(db_prepare_string($sSearchFor."%"));
		else
			return $strField." ".$like." ".db_prepare_string($sSearchFor."%");
	}
	else if($strSearchOption=="More than") return $strField.">".$sSearchFor;
	else if($strSearchOption=="Less than") return $strField."<".$sSearchFor;
	else if($strSearchOption=="Between")
	{
		$ret=$strField.">=".$sSearchFor;
		if($sSearchFor2) $ret.=" and ".$strField."<=".$sSearchFor2;
			return $ret;
	}
	return "";
}

///////////////////////////////////////////////
//    This function is DEPRECATED!			 //
//    Use SQLWhere from EditControl instead  //
///////////////////////////////////////////////
//	construct SQL WHERE clause for Advanced search
function StrWhereAdv($strField, $SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest)
{
	global $strTableName;
	
	$pSet = new ProjectSettings($strTableName, PAGE_SEARCH);
	$cipherer = new RunnerCipherer($strTableName);
	
	$type = $pSet->getFieldType($strField);
	$isOracle = false;

	$ismssql=false;

	$isdb2=false;
	
	$btexttype=IsTextType($type);

	$isMysql = false;

	if(IsBinaryType($type))
		return "";
	if($strSearchOption=='Empty')
	{
		if(IsCharType($type) && (!$ismssql || !$btexttype) && !$isOracle)
		{
			return "(".GetFullFieldNameForInsert($pSet, $strField)." is null or ".GetFullFieldNameForInsert($pSet, $strField)."='')";
		}
		elseif ($ismssql && $btexttype)
		{
			return "(".GetFullFieldNameForInsert($pSet, $strField)." is null or ".GetFullFieldNameForInsert($pSet, $strField)." LIKE '')";
		}
		else
		{
			return GetFullFieldNameForInsert($pSet, $strField)." is null";
		}
	}
		$like="like";
	
	
	if($pSet->getEditFormat($strField) == EDIT_FORMAT_LOOKUP_WIZARD)
	{
		if($pSet->multiSelect($strField))
			$SearchFor=splitvalues($SearchFor);
		else
			$SearchFor=array($SearchFor);
		$ret="";
		foreach($SearchFor as $value)
		{
			if(!($value=="null" || $value=="Null" || $value==""))
			{
				if(strlen($ret))
					$ret.=" or ";
				if($strSearchOption=="Equals")
				{
					$value=make_db_value($strField,$value);
					if(!($value=="null" || $value=="Null"))
						$ret.=GetFullFieldName($strField, "", false).'='.$value;
				}
				elseif($isSuggest)
				{
					$ret.=" ".GetFullFieldName($strField, "", false)." ".$like." ".db_prepare_string('%'.$value.'%');	
				}
				else
				{
					if(strpos($value,",")!==false || strpos($value,'"')!==false)
						$value = '"'.str_replace('"','""',$value).'"';
					
					if ($isMysql)
					{
						$value = str_replace('\\\\', '\\\\\\\\', $value); 
					}
					//for search by multiply Lookup wizard field
					$ret.=GetFullFieldName($strField, "", false)." = ".db_prepare_string($value);
					$ret.=" or ".GetFullFieldName($strField, "", false)." ".$like." ".db_prepare_string("%,".$value.",%");
					$ret.=" or ".GetFullFieldName($strField, "", false)." ".$like." ".db_prepare_string("%,".$value);
					$ret.=" or ".GetFullFieldName($strField, "", false)." ".$like." ".db_prepare_string($value.",%");
				}
			}
		}
		if(strlen($ret))
			$ret="(".$ret.")";
		return $ret;
	}
	if($pSet->GetEditFormat($strField) == EDIT_FORMAT_CHECKBOX)
	{
		if($SearchFor=="none")
			return "";
			
		if(NeedQuotes($type))
		{
							$isOracle = false;
			
			if($SearchFor=="on")
			{
				$whereStr = "(".GetFullFieldName($strField)."<>'0' ";
				if (!$isOracle)
				{
					$whereStr .= " and ".GetFullFieldName($strField)."<>'' ";
				} 
				$whereStr .= " and ".GetFullFieldName($strField)." is not null)";
				return $whereStr;
			}
			elseif($SearchFor=="off")
			{
				$whereStr = "(".GetFullFieldName($strField)."='0' ";
				if (!$isOracle)
				{
					$whereStr .= " or ".GetFullFieldName($strField)."='' "; 
				}
				$whereStr .= " or ".GetFullFieldName($strField)." is null)";
			}
		}
		else
		{
			if($SearchFor=="on")
			{
				return "(".GetFullFieldName($strField)."<>0 and ".GetFullFieldName($strField)." is not null)";
			}
			elseif($SearchFor=="off")
			{
				return "(".GetFullFieldName($strField)."=0 or ".GetFullFieldName($strField)." is null)";
			}
		}
	}
	$value1 = $cipherer->MakeDBValue($strField, $SearchFor, $etype, "", true);
	$value2 = false;
	$cleanvalue2 = false;
	if($strSearchOption == "Between")
	{
		$cleanvalue2 = prepare_for_db($strField,$SearchFor2,$etype);
		$value2 = make_db_value($strField,$SearchFor2,$etype);
	}
		
	if($strSearchOption!="Contains" && $strSearchOption!="Starts with" && ($value1==="null" || $value2==="null" )
		&& !$cipherer->isFieldPHPEncrypted($strField))
		return "";
	
	if(IsCharType($type) && !$btexttype)
	{
		if(!$cipherer->isFieldPHPEncrypted($strField))
		{
			$value1 = $pSet->isEnableUpper($value1);
			$value2 = $pSet->isEnableUpper($value2);
			$gstrField = $pSet->isEnableUpper(GetFullFieldName($strField, "", false));
		}
		else
			$gstrField = GetFullFieldName($strField, "", false);
	}
	elseif($strSearchOption=="Contains" || $strSearchOption=="Starts with")
	{
		$gstrField = db_field2char(GetFullFieldName($strField, "", false),$type);
	}
	elseif($pSet->getViewFormat($strField)==FORMAT_TIME)
	{
		$gstrField = db_field2time(GetFullFieldName($strField, "", false),$type);
	}
	else 
	{
		$gstrField = GetFullFieldName($strField, "", false);
	}

	$ret="";
	
	if($strSearchOption=="Contains")
	{
		if ($isMysql)
		{
			$SearchFor = str_replace('\\\\', '\\\\\\\\', $SearchFor);
		}
		if($cipherer->isFieldPHPEncrypted($strField))
			return $gstrField."=".$cipherer->MakeDBValue($strField, $SearchFor);
		
		if(IsCharType($type) && !$btexttype)
			return $gstrField." ".$like." ".$pSet->isEnableUpper(db_prepare_string("%".$SearchFor."%"));
		else
			return $gstrField." ".$like." ".db_prepare_string("%".$SearchFor."%");
	}
	else if($strSearchOption=="Equals") 
	{
		return $gstrField."=".$value1;
	}
	else if($strSearchOption=="Starts with")
	{
		if ($isMysql)
		{
			$SearchFor = str_replace('\\\\', '\\\\\\\\', $SearchFor);
		}
		if(IsCharType($type) && !$btexttype)
			return $gstrField." ".$like." ".$pSet->isEnableUpper(db_prepare_string($SearchFor."%"));
		else
			return $gstrField." ".$like." ".db_prepare_string($SearchFor."%");
	}
	else if($strSearchOption=="More than") return $gstrField.">".$value1;
	else if($strSearchOption=="Less than") return $gstrField."<".$value1;
	else if($strSearchOption=="Equal or more than") return $gstrField.">=".$value1;
	else if($strSearchOption=="Equal or less than") return $gstrField."<=".$value1;
	else if($strSearchOption=="Between")
	{
		$ret=$gstrField.">=".$value1." and ";
		if (IsDateFieldType($type))
		{
			$timeArr = db2time($cleanvalue2);
			// for dates without time, add one day
			if ($timeArr[3]==0 && $timeArr[4]==0 && $timeArr[5]==0)
			{
				$timeArr = adddays($timeArr, 1);
				$value2 = $timeArr[0]."-".$timeArr[1]."-".$timeArr[2];
				$value2 = add_db_quotes($strField, $value2, $strTableName);
				$ret .= $gstrField."<".$value2;
			}
			else
			{
				$ret.=$gstrField."<=".$value2;
			}
		}
		else 
		{
			$ret.=$gstrField."<=".$value2;
		}
		return $ret;
	}
	return "";
}

//	get count of rows from the query
function GetRowCount($strSQL)
{
	global $conn;
	$strSQL=str_replace(array("\r\n","\n","\t")," ",$strSQL);
	$tstr = strtoupper($strSQL);
	$ind1 = strpos($tstr,"SELECT ");
	$ind2 = my_strrpos($tstr," FROM ");
	$ind3 = my_strrpos($tstr," GROUP BY ");
	if($ind3===false)
	{
		$ind3 = strpos($tstr," ORDER BY ");
		if($ind3===false)
			$ind3=strlen($strSQL);
	}
	$countstr=substr($strSQL,0,$ind1+6)." count(*) ".substr($strSQL,$ind2+1,$ind3-$ind2);
	$countrs = db_query($countstr,$conn);
	$countdata = db_fetch_numarray($countrs);
	return $countdata[0];
}

//	add MSSQL Server TOP clause
function AddTop($strSQL, $n)
{
	$tstr = strtoupper($strSQL);
	$ind1 = strpos($tstr,"SELECT");
	return substr($strSQL,0,$ind1+6)." top ".$n." ".substr($strSQL,$ind1+6);
}
//	add DB2 Server TOP clause
function AddTopDB2($strSQL, $n)
{
	
	return $strSQL." fetch first ".$n." rows only";
}
function AddTopIfx($strSQL,$n)
{
	return substr($strSQL,0,7)."limit ".$n." ".substr($strSQL,7);
}
//	add Oracle ROWNUMBER checking
function AddRowNumber($strSQL, $n)
{
	return "select * from (".$strSQL.") where rownum<".($n+1);
}

// test database type if values need to be quoted
function NeedQuotesNumeric($type)
{
    if($type == 203 || $type == 8 || $type == 129 || $type == 130 || 
		$type == 7 || $type == 133 || $type == 134 || $type == 135 ||
		$type == 201 || $type == 205 || $type == 200 || $type == 202 || $type==72 || $type==13)
		return true;
	else
		return false;
}

//	using ADO DataTypeEnum constants
//	the full list available at:
//	http://msdn.microsoft.com/library/default.asp?url=/library/en-us/ado270/htm/mdcstdatatypeenum.asp

function IsNumberType($type)
{
	if($type==20 || $type==6 || $type==14 || $type==5 || $type==10 
	|| $type==3 || $type==131 || $type==4 || $type==2 || $type==16
	|| $type==21 || $type==19 || $type==18 || $type==17 || $type==139
	|| $type==11)
		return true;
	return false;
}

function IsFloatType($type)
{
	if($type==14 || $type==5 || $type==131 || $type==6 || $type==4)
		return true;
	return false;
}


function NeedQuotes($type)
{
	return !IsNumberType($type);
}

function IsBinaryType($type)
{
	if($type==128 || $type==205 || $type==204)
		return true;
	return false;
}

function IsDateFieldType($type)
{
	if($type==7 || $type==133 || $type==135)
		return true;
	return false;
}

function IsTimeType($type)
{
	if($type==134)
		return true;
	return false;
}

function IsCharType($type)	
{
	if(IsTextType($type) || $type==8 || $type==129 || $type==200 || $type==202 || $type==130)
		return true;
	return false;
}

function IsTextType($type)
{
	if($type==201 || $type==203)
		return true;
	return false;
}

function IsGuid($type)
{
	if($type==72)
		return true;
	return false;
}


////////////////////////////////////////////////////////////////////////////////
// security functions
////////////////////////////////////////////////////////////////////////////////


//	return user permissions on the table
//	A - Add
//	D - Delete
//	E - Edit
//	S - List/View/Search
//	P - Print/Export


function IsAdmin()
{
	return false;
}

function GetUserPermissionsStatic($table="")
{
	global $strTableName;
	if(!$table)
		$table=$strTableName;

	$sUserGroup=@$_SESSION["GroupID"];
//	default permissions	
	if($table=="C000007")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000008")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000009")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000010")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000025")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000040")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000044")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000046")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000049")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000071")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000013")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="C000054")
	{
	return "ADESPI";// grant all by default
	}
//	default permissions	
	if($table=="WEB")
	{
	return "ADESPI";// grant all by default
	}
}

function GetUserPermissions($table="")
{
	global $strTableName, $globalEvents;
	if(!$table)
		$table = $strTableName;
	$permissions = "";
	$permissions =  GetUserPermissionsStatic($table);
	
	if($globalEvents->exists("GetTablePermissions", $table))
	{
		$permissions = $globalEvents->GetTablePermissions($permissions, $table);
	}
	return $permissions;
}

//
function isLogged(){
	
	if (@$_SESSION["UserID"]) {
		return true;
	}
		return false;
}


function inFacebook(){
		global $conn, $cCharset, $cUserNameField, $cPasswordField;
	
	$facebook = new facebookWrapper();
	
	$signed_request = $facebook->FBgetSignedRequest();
	
	if ($signed_request) {
		$strUsername = "fb".(string)$signed_request['user_id'];
		$strSQL = "select * from ".AddTableWrappers("WEB")." where ".AddFieldWrappers(GetUserNameField())."='".$strUsername."'";
		$rs = db_query($strSQL,$conn);
		$data = db_fetch_array($rs);
		if ($data){
			AfterFBLogIn($data[$cUserNameField], $data[$cPasswordField]);
			return true;
		}
	}
	return false;
}

/**
* Set session variables and permissions after login via Facebook
*
*/
function AfterFBLogIn($pUsername, $pPassword){
	global $conn, $cUserNameFieldType, $cPasswordFieldType, $cUserNameField, $cDisplayNameField, $globalEvents;
	$logged = false;
	$strUsername = (string)$pUsername;
	$sUsername = $strUsername;
		
	if(NeedQuotes($cUserNameFieldType))
		$strUsername = db_prepare_string($strUsername);
	else
		$strUsername = (0+$strUsername);
		
	$strSQL = "select * from ".AddTableWrappers("WEB")." where ".AddFieldWrappers($cUserNameField)."=".$strUsername."";
	$rs = db_query($strSQL,$conn);
 	$data = db_fetch_array($rs);
	if($data){
		$logged=true;
		$pDisplayUsername = $data[$cDisplayNameField]!='' ? $data[$cDisplayNameField] : $sUsername;
		DoLogin(false, $pUsername, $pDisplayUsername, "", ACCESS_LEVEL_USER, $pPassword);
		SetAuthSessionData($pUsername, $data, true, $pPassword);
	}
}

/**
 * SetAuthSessionData
 * Add to session auth data and permissions
 * @param {string} user identifier
 * @param {string} user display name
 * @param {object} fetched row from DB with user data
 */
function SetAuthSessionData($pUsername, &$data, $fromFacebook, $password)
{
	global $globalEvents;
		$_SESSION["GroupID"] = "";


	if($globalEvents->exists("AfterSuccessfulLogin"))
	{
		$globalEvents->AfterSuccessfulLogin($pUsername != "Guest" ? $pUsername : "", $password, $data);
	}	
}

function DoLogin($autoLogin = false, $userID = "Guest", $userName = "", $groupID = "<Guest>"
	, $accessLevel = ACCESS_LEVEL_GUEST, $password = "")
{
	global $globalEvents;
	
	if($userID == "Guest" && $userName == "")
		$userName = "Visitante";
	
	$_SESSION["UserID"] = $userID;
	$_SESSION["UserName"] = $userName;
	$_SESSION["GroupID"] = $groupID;
	$_SESSION["AccessLevel"] = $accessLevel;
	$auditObj = GetAuditObject();
	if($auditObj)
	{
		$auditObj->LogLogin($userID);
		$auditObj->LoginSuccessful();
	}
	if($autoLogin && $globalEvents->exists("AfterSuccessfulLogin"))
	{
		$dummy = array();
		$globalEvents->AfterSuccessfulLogin($userID != "Guest" ? $userID : "", $password, $dummy);
	}
}

// 
function CheckSecurity($strValue, $strAction)
{

	global $cAdvSecurityMethod, $strTableName;
	$pSet = new ProjectSettings($strTableName);
	
	if($_SESSION["AccessLevel"]==ACCESS_LEVEL_ADMIN)
		return true;

	$strPerm = GetUserPermissions();
	if(@$_SESSION["AccessLevel"]!=ACCESS_LEVEL_ADMINGROUP && strpos($strPerm, "M")===false)
	{
	}
		return true;
}

function CheckPermissionsEvent($strTableName, $permission){
	if(strpos(GetUserPermissions($strTableName), $permission) === false){
		$_SESSION["MyURL"]=$_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
		header("Location: menu.php"); 
		exit();
	}
	return true;
}

//	add security WHERE clause to SELECT SQL command
function SecuritySQL($strAction, $table="")
{
	global $cAdvSecurityMethod,$strTableName;
	
	if (!strlen($table))	
		$table = $strTableName;
	
	$pSet = new ProjectSettings($table);
	
   	$ownerid=@$_SESSION["_".$table."_OwnerID"];
	$ret="";
	if(@$_SESSION["AccessLevel"]==ACCESS_LEVEL_ADMIN)
		return "";
		
	$ret="";
	$strPerm = GetUserPermissions($table);

	if(@$_SESSION["AccessLevel"]!=ACCESS_LEVEL_ADMINGROUP && strpos($strPerm, "M") === false)
	{
	}
	
	if($strAction=="Edit" && !(strpos($strPerm, "E")===false) ||
	   $strAction=="Delete" && !(strpos($strPerm, "D")===false) ||
	   $strAction=="Search" && !(strpos($strPerm, "S")===false) ||
	   $strAction=="Export" && !(strpos($strPerm, "P")===false) )
		return $ret;
	else
		return "1=0";
	return "";
}

////////////////////////////////////////////////////////////////////////////////
// editing functions
////////////////////////////////////////////////////////////////////////////////

function make_db_value($field,$value,$controltype="",$postfilename="",$table="")
{	
	$ret=prepare_for_db($field,$value,$controltype,$postfilename,$table);
	
	if($ret===false)
		return $ret;
	return add_db_quotes($field,$ret,$table);
}

function add_db_quotes($field, $value, $table = "", $type = null)
{
	global $strTableName;
	if($table=="")
		$table=$strTableName;
	$pSet = new ProjectSettings($table);
	
	if($type == null)
		$type = $pSet->getFieldType($field);
	if(IsBinaryType($type))
		return db_addslashesbinary($value);
	if(($value==="" || $value===FALSE || is_null($value)) && !IsCharType($type))
		return "null";
	if(NeedQuotes($type))
	{
		if(!IsDateFieldType($type))
			$value=db_prepare_string($value);
		else
			$value=db_datequotes($value);
	}
	else
	{
		$strvalue = (string)$value;
		$strvalue = str_replace(",",".",$strvalue);
		if(is_numeric($strvalue))
			$value=$strvalue;
		else
			$value=0;
	}
	return $value;
}

function prepare_for_db($field, $value, $controltype = "", $postfilename = "", $table = "")
{
	global $strTableName;
	if($table == "")
		$table = $strTableName;
	$pSet = new ProjectSettings($table);
	$filename = "";
	$type = $pSet->getFieldType($field);
	if(!$controltype || $controltype == "multiselect")
	{
		if(is_array($value))
			$value = combinevalues($value);
		if(($value === "" || $value === FALSE) && !IsCharType($type))
			return "";
		if(IsGuid($type))
		{
			if(!IsGuidString($value))
				return "";
		}
		return $value;
	}	
	else if($controltype == "time")
	{
		if(!strlen($value))
			return "";
		$time = localtime2db($value);
		if(IsDateFieldType($pSet->getFieldType($field)))
		{
			$time = "2000-01-01 ".$time;
		}
		return $time;
	}
	else if(substr($controltype, 0, 4) == "date")
	{
		$dformat = substr($controltype, 4);
		if($dformat == EDIT_DATE_SIMPLE || $dformat == EDIT_DATE_SIMPLE_DP)
		{
			$time = localdatetime2db($value);
			if($time == "null")
				return "";
			return $time;
		}
		else if($dformat == EDIT_DATE_DD || $dformat == EDIT_DATE_DD_DP)
		{
			$a = explode("-",$value);
			if(count($a) < 3)
				return "";
			else
			{
				$y = $a[0];
				$m = $a[1];
				$d = $a[2];
			}
			if($y < 100)
			{
				if($y < 70)
					$y += 2000;
				else
					$y += 1900;
			}
			return mysprintf("%04d-%02d-%02d",array($y,$m,$d));
		}
		else
			return "";
	}
	else if(substr($controltype, 0, 8) == "checkbox")
	{
		if($value == "on")
			$ret = 1;
		else if($value == "none")
			return "";
		else 
			$ret = 0;
		return $ret;
	}
	else
		return false;
}

//	delete uploaded files when deleting the record
function DeleteUploadedFiles($pSet, $deleted_values)
{
	global $conn;
	foreach($deleted_values as $field => $value)
	{
		if(($pSet->getEditFormat($field) == EDIT_FORMAT_FILE || $pSet->getPageTypeByFieldEditFormat($field, EDIT_FORMAT_FILE) != "") 
			&& $pSet->isDeleteAssociatedFile($field))
		{
			if(!strlen($value))
				return;
				
			$filesArray = my_json_decode($value);
			if(!is_array($filesArray) || count($filesArray) == 0)
			{
				$filesArray = array(array("name" => $pSet->getUploadFolder($field).$value));
				if($pSet->getCreateThumbnail($field))
					$filesArray[0]["thumbnail"] = $pSet->getUploadFolder($field).$pSet->getStrThumbnail($field).$value;
			}
		
			foreach($filesArray as $delFile)
			{
				$isAbs = $pSet->isAbsolute($field);
				$filename = $delFile["name"];
				if(!$isAbs)
					$filename = getabspath($filename);
				runner_delete_file($filename);
				if($delFile["thumbnail"] != "")
				{
					$filename = $delFile["thumbnail"];
					if(!$isAbs)
						$filename = getabspath($filename);
					runner_delete_file($filename);
				}
			}
		}
	}
}

//	combine checked values from multi-select list box
function combinevalues($arr)
{
	$ret="";
	foreach($arr as $val)
	{
		if(strlen($ret))
			$ret.=",";
		if(strpos($val,",")===false && strpos($val,'"')===false)
			$ret.=$val;
		else
		{
			$val=str_replace('"','""',$val);
			$ret.='"'.$val.'"';
		}
	}
	return $ret;
}

//	split values for multi-select list box
function splitvalues($str)
{
	$arr=array();
	$start=0;
	$i=0;
	$inquot=false;
	while($i<=strlen($str))
	{
		if($i<strlen($str) && substr($str,$i,1)=='"')
			$inquot=!$inquot;
		else if($i==strlen($str) || !$inquot && substr($str,$i,1)==',')
		{
			$val=substr($str,$start,$i-$start);
			$start=$i+1;
			if(strlen($val) && substr($val,0,1)=='"')
			{
				$val=substr($val,1,strlen($val)-2);
				$val=str_replace('""','"',$val);
			}
			$arr[]=$val;
		}
		$i++;
	}
	return $arr;
}

//	create javascript array with values for dependent dropdowns
function BuildSecondDropdownArray( $arrName, $strSQL)
{
	global $conn;

	echo $arrName . "=new Array();\r\n";
	$i = 0;
	$rs = db_query($strSQL,$conn);
	while($row = db_fetch_numarray($rs))
	{
		echo $arrName."[".($i*3)."]='".jsreplace($row[0]). "';\r\n";
		echo $arrName."[".($i*3 + 1)."]='".jsreplace($row[1]). "';\r\n";
		echo $arrName."[".($i*3 + 2)."]='".jsreplace($row[2]). "';\r\n";
		$i++;
	}
}

function GetLookupFieldsIndexes($pSet, $field)
{
	$lookupTable = $pSet->getLookupTable($field);
	$lookupType = $pSet->getLookupType($field);
	$displayFieldName = $pSet->getDisplayField($field);
	$linkFieldName = $pSet->getLinkField($field);
	$linkAndDisplaySame = $linkFieldName == $displayFieldName;
	if($lookupType == LT_QUERY)
	{
		$lookupPSet = new ProjectSettings($lookupTable);
		$linkFieldIndex = $lookupPSet->getFieldIndex($linkFieldName) - 1;
		if($linkAndDisplaySame)
			$displayFieldIndex = $linkFieldIndex;
		else
		{
			if($pSet->getCustomDisplay($field))
				$displayFieldIndex = $lookupPSet->getCustomExpressionIndex($pSet->_table, $field);
			else
				$displayFieldIndex = $lookupPSet->getFieldIndex($displayFieldName) - 1;
		}
	}
	else 
	{
		$linkFieldIndex = 0;
		$displayFieldIndex = $linkAndDisplaySame ? 0 : 1;
	}
	return array("linkFieldIndex" => $linkFieldIndex, "displayFieldIndex" => $displayFieldIndex);
}

////////////////////////////////////////////////////////////////////////////////
/**
 * Get locale, am, pm for field edit as time
 * @param integer $convention - 24 or 12 hours format for timePicker
 * @param boolean $useTimePicker -  use timePicker or not
 * @return array
 */
function getLacaleAmPmForTimePicker($convention, $useTimePicker = false)
{
	$am = '';
	$pm = '';
	global $locale_info;
	if($useTimePicker)
	{
		$locale_convention = $locale_info["LOCALE_ITIME"] ? 24 : 12;
		if($convention == $locale_convention)
		{
			$am = $locale_info["LOCALE_S1159"];
			$pm = $locale_info["LOCALE_S2359"];
			$locale = $locale_info["LOCALE_STIMEFORMAT"];
		}
		else{
				if($convention == 24)
				{
					$am = '';
					$pm = '';
					$locale = "H:mm:ss";
				}
				else{
						$am = 'am';
						$pm = 'pm';
						$locale = "h:mm:ss tt";
					}
			}
	}
	else
		$locale = $locale_info["LOCALE_STIMEFORMAT"];
		
	return array('am'=>$am,'pm'=>$pm,'locale'=>$locale);	
}

/**
 * Get value for field edit as time and get dpTime settings
 * @param integer $convention - 24 or 12 hours format for timePicker
 * @param string $type - type of field
 * @param string $value - value of field
 * @param boolean $useTimePicker -  use timePicker or not
 * @return array
 */
function getValForTimePicker($type,$value,$locale)
{
	$val = "";
	$dbtime = array();
	if(IsDateFieldType($type))
	{
		$dbtime = db2time($value);
		if(count($dbtime))
			$val = format_datetime_custom($dbtime, $locale);
	}
	else 
	{
		$arr = parsenumbers($value);
		if(count($arr))
		{
			while(count($arr)<3)
				$arr[] = 0;
			$dbtime = array(0, 0, 0, $arr[0], $arr[1], $arr[2]);
			$val = format_datetime_custom($dbtime, $locale);
		}
	}
	
	return array('val'=>$val,'dbTime'=>$dbtime);
}
////////////////////////////////////////////////////////////////////////////////

function my_stripos($str,$needle, $offest)
{
	if (strlen($needle)==0 || strlen($str)==0)
		return false;
	return strpos(strtolower($str),strtolower($needle), $offest);
} 

function my_str_ireplace($search, $replace,$str)
{
	$pos=my_stripos($str,$search,0);
	if($pos===false)
		return $str;
	return substr($str,0,$pos).$replace.substr($str,$pos+strlen($search));
} 


function in_assoc_array($name, $arr)
{
foreach ($arr as $key => $value) 
	if ($key==$name)
		return true;

return false;
}

function buildLookupSQL($pageType, $field, $table, $parentVal, $childVal = "", 
	$doCategoryFilter = true, $doValueFilter = false, $addCategoryField = false, $doWhereFilter = true, 
	$oneRecordMode = false, $doValueFilterByLinkField = false)
{
	global $strTableName;
	
	if(!strlen($table))
		$table=$strTableName;
	$pSet = new ProjectSettings($table, $pageType);
//	read settings
	$nLookupType = $pSet->getLookupType($field);
	if($nLookupType != LT_LOOKUPTABLE && $nLookupType != LT_QUERY)
		return "";
	
	$lookupTable = $pSet->getLookupTable($field);
	$displayFieldName = $pSet->getDisplayField($field);
	$linkFieldName = $pSet->getLinkField($field);
	$linkAndDisplaySame = $displayFieldName == $linkFieldName;
	
	$bUnique = $pSet->isLookupUnique($field);
	$strLookupWhere = GetLWWhere($field, $pageType, $table);
	$strOrderBy = $pSet->getLookupOrderBy($field);
	if(strlen($strOrderBy))
	{
		$strOrderBy = GetFullFieldName($strOrderBy, $lookupTable);
		if($pSet->isLookupDesc($field))
			$strOrderBy .= ' DESC';
	}
	$bDesc = $pSet->isLookupDesc($field);
	$strCategoryFilter = $pSet->getCategoryFilter($field);
	
	if($nLookupType == LT_QUERY)
	{
		$lookupPSet = new ProjectSettings($lookupTable, $pageType);
		$cipherer = new RunnerCipherer($lookupTable, $lookupPSet);
		$secOpt = $lookupPSet->getAdvancedSecurityType();
		if($secOpt == ADVSECURITY_VIEW_OWN)
			$strLookupWhere = whereAdd($strLookupWhere, SecuritySQL("Search", $lookupTable));
	}
	else 
		$cipherer = new RunnerCipherer($table, $pSet);
		
	if($doCategoryFilter)
	{
		if($nLookupType == LT_QUERY)
			$parentVal = $cipherer->MakeDBValue($strCategoryFilter, $parentVal, "", $lookupTable, true);
		else
			$parentVal = make_db_value($pSet->getCategoryControl($field), $parentVal, '', '', $table);
	}
		
	if($doValueFilter)
	{
		if($pageType != PAGE_SEARCH || $doValueFilterByLinkField)
		{
			if($nLookupType == LT_QUERY)
				$childWhereField = $pSet->getLWLinkField($field, false);
			else 
				$childWhereField = $pSet->getLWLinkField($field, true);
		}
		else
		{
			if($nLookupType == LT_QUERY)
				$childWhereField = $pSet->getLWDisplayField($field, false);
			else 
				$childWhereField = $pSet->getLWDisplayField($field, true);
		} 
		if($nLookupType == LT_QUERY)
			$childVal = $cipherer->MakeDBValue($childWhereField, $childVal, "", $lookupTable, true);
		else
		{
			if($linkAndDisplaySame)
				$childVal = make_db_value($field, $childVal, '', '', $table);
			else 
				$childVal = add_db_quotes($field, $childVal, $table, 200);
		}
	}
		
	//	build Where clause
	
	$categoryWhere = "";
	$childWhere = "";
	if($pSet->useCategory($field) && $doCategoryFilter)
	{
		$condition = "=".$parentVal;
		if($parentVal === "null")
			$condition = " is null";
		
		if($nLookupType == LT_QUERY)
		{
			$categoryWhere = $cipherer->GetFieldName(AddFieldWrappers($strCategoryFilter), $strCategoryFilter).$condition;
		}
		else 
		{
			$categoryWhere = AddFieldWrappers($strCategoryFilter).$condition;
		}
	}
	if($doValueFilter)
	{
		$condition = "=".$childVal;
		if($childVal === "null")
			$condition = " is null";
		if($nLookupType == LT_QUERY)
		{
			if($pageType != PAGE_SEARCH || $pSet->lookupControlType($field) == LCT_LIST || $doValueFilterByLinkField)
			{
				$childWhere = GetFullFieldName($pSet->getLinkField($field), $lookupTable, false).$condition;
			}
			else 
			{
				if(!$pSet->getCustomDisplay($field))
					$childWhere = $cipherer->GetFieldName($lookupPSet->getFullNameField($displayFieldName), $field).$condition;
				else 
					$childWhere = $pSet->getDisplayField($field).$condition;
			}
		}
		else 
		{
			if($pageType != PAGE_SEARCH || $doValueFilterByLinkField)
				$childWhere = $pSet->getLWLinkField($field, true).$condition;
			else 
				$childWhere = $pSet->getLWDisplayField($field, true).$condition;
		}
			
	}
	$strWhere = "";
	if($doWhereFilter && strlen($strLookupWhere))
		$strWhere = "(".$strLookupWhere.")";
		
	if(strlen($categoryWhere))
	{
		if(strlen($strWhere))
			$strWhere.=" AND ";
		$strWhere.=$categoryWhere;
	}
	if(strlen($childWhere))
	{
		if(strlen($strWhere))
			$strWhere.=" AND ";
		$strWhere.=$childWhere;
	}
			
//	build SQL string	
	if($nLookupType == LT_QUERY){
		$lookupQueryObj = $lookupPSet->getSQLQuery();
		
		if($pSet->getCustomDisplay($field))
		{
			$lookupQueryObj->AddCustomExpression($displayFieldName, $lookupPSet, $table, $field);
		}
		
		$lookupQueryObj->ReplaceFieldsWithDummies($lookupPSet->getBinaryFieldsIndices());
		$strWhere = whereAdd($lookupQueryObj->m_where->toSql($lookupQueryObj), $strWhere);
		$LookupSQL = $lookupQueryObj->toSql($strWhere, strlen($strOrderBy) ? ' ORDER BY '.$strOrderBy : null, null, $oneRecordMode);
	}else{		
		$LookupSQL = "SELECT ";
				if($bUnique && !$oneRecordMode)
			$LookupSQL .= "DISTINCT ";
		$LookupSQL .= $pSet->getLWLinkField($field);
		if(!$linkAndDisplaySame){
			$LookupSQL .= ",".$pSet->getLWDisplayField($field);
		}
		if($addCategoryField && strlen($strCategoryFilter))
			$LookupSQL .= ",".AddFieldWrappers($strCategoryFilter);
		
		$LookupSQL .= " FROM ".AddTableWrappers($lookupTable);
	
		if(strlen($strWhere))
			$LookupSQL.=" WHERE ".$strWhere;
		//	order by clause
		if(strlen($strOrderBy))
		{
			$LookupSQL.= " ORDER BY ".AddTableWrappers($lookupTable).".".$strOrderBy;
		}
							}
	return $LookupSQL;
}

function loadSelectContent($pageType, $childFieldName, $parentVal, $doCategoryFilter=true, $childVal="", $initialLoad = true)
{
	global $conn, $LookupSQL, $strTableName;

	$pSet = new ProjectSettings($strTableName, $pageType);
	
	$response = array();
	
	$lookupType = $pSet->getLookupType($childFieldName);
	$isUnique = $pSet->isLookupUnique($childFieldName);
	
	if($pSet->useCategory($childFieldName) && $doCategoryFilter)
	{
		if($lookupType == LT_QUERY)
		{
			$lookupTable = $pSet->getLookupTable($childFieldName);
			$cipherer = new RunnerCipherer($lookupTable);
			$tempParentVal = $cipherer->MakeDBValue($pSet->getCategoryControl($childFieldName), $parentVal, "", $lookupTable, true);
		}
		else
			$tempParentVal = make_db_value($childFieldName, $parentVal);
		if($tempParentVal === "null")
			return $response;
	}
	
	$LookupSQL = buildLookupSQL($pageType, $childFieldName, $strTableName, $parentVal, $childVal, $doCategoryFilter
		, $pSet->fastType($childFieldName) && $initialLoad);

	$lookupIndexes = GetLookupFieldsIndexes($pSet, $childFieldName);
		
	$rs=db_query($LookupSQL,$conn);

	if(!$pSet->fastType($childFieldName))
	{
		while ($data = db_fetch_numarray($rs)) 
		{
			if($lookupType == LT_QUERY && $isUnique){
				if(!isset($uniqueArray))
					$uniqueArray = array();
				if(in_array($data[$lookupIndexes["displayFieldIndex"]], $uniqueArray))
					continue;
				$uniqueArray[] = $data[$lookupIndexes["displayFieldIndex"]];
			}
			$response[] = $data[$lookupIndexes["linkFieldIndex"]];
			$response[] = $data[$lookupIndexes["displayFieldIndex"]];
		}
	}
	else
	{
		$data=db_fetch_numarray($rs);
//	one record only
		if($data && (strlen($childVal) || !db_fetch_numarray($rs)))
		{
			$response[] = $data[$lookupIndexes["linkFieldIndex"]];
			$response[] = $data[$lookupIndexes["displayFieldIndex"]];
		}
	}
	return $response;
}


function xmlencode($str)
{

	$str = str_replace("&","&amp;",$str);
	$str = str_replace("<","&lt;",$str);
	$str = str_replace(">","&gt;",$str);
	$str = str_replace("'","&apos;",$str);
	return escapeEntities($str);

}

function print_inline_array(&$arr,$printkey=false)
{
	if(!$printkey)
	{
		foreach ( $arr as $key=>$val )
			echo str_replace(array("&","<","\\","\r","\n"),array("&amp;","&lt;","\\\\","\\r","\\n"),str_replace(array("\\","\r","\n"),array("\\\\","\\r","\\n"),$val))."\\n";
	}
	else
	{
		foreach( $arr as $key=>$val )
			echo str_replace(array("&","<","\\","\r","\n"),array("&amp;","&lt;","\\\\","\\r","\\n"),str_replace(array("\\","\r","\n"),array("\\\\","\\r","\\n"),$key))."\\n";
	}
		
}


function GetChartXML($chartname)
{
	$strTableName = GetTableByShort($chartname);	
	$settings = new ProjectSettings($strTableName);
	return $settings->getChartXml();
}


function GetSiteUrl()
{
	$url = "http://".$_SERVER["SERVER_NAME"];
	if($_SERVER["SERVER_PORT"]!=80)
	{
		if ($_SERVER["SERVER_PORT"]==443)
		   $url = "https://".$_SERVER["SERVER_NAME"];
		else
		   $url.=":".$_SERVER["SERVER_PORT"];
	}
	return $url;
}

function GetAuditObject($table="")
{
	return NULL;
	
	$linkAudit = false;
	if(!$table)
	{
		$linkAudit = true;
	}
	else
	{
		$settings = new ProjectSettings($table);
		$linkAudit = $settings->auditEnabled();
	}
	if ($linkAudit)
	{	
		require_once(getabspath("include/audit.php"));
			}
	else
	{
		return NULL;
	}
}
function GetLockingObject($table="")
{
	return NULL;

	if(!$table)
	{
		global $strTableName;
		$table = $strTableName;
	}
	$settings = new ProjectSettings($table);
	if ($settings->lockingEnabled())
	{	
		require_once(getabspath("include/locking.php"));
		return new oLocking();
	}
	else
	{
		return NULL;
	}
}

function isEnableSection508()
{
	return GetGlobalData("isSection508",false);
}

function isEncryptionEnabled(){
	return GetGlobalData("isUseEncryption", ENCRYPTION_NONE) == ENCRYPTION_PHP 
		|| GetGlobalData("isUseEncryption", ENCRYPTION_NONE) == ENCRYPTION_DB;
}

function isEncryptionByPHPEnabled(){
	return GetGlobalData("isUseEncryption", ENCRYPTION_NONE) == ENCRYPTION_PHP;
}
/**
 * Returns validation type which defined in js validation object.
 * Use this function, because runner constants has another names of validation functions
 *
 * @param string $name
 * @return string
 */
function getJsValidatorName($name) 
{	
	switch ($name) 
	{
		case "Number":
			return "IsNumeric";
			break;
		case "Password":
			return "IsPassword";
			break;
		case "Email":
			return "IsEmail";
			break;
		case "Currency":
			return "IsMoney";
			break;
		case "US ZIP Code":
			return "IsZipCode";
			break;
		case "US Phone Number":
			return "IsPhoneNumber";
			break;
		case "US State":
			return "IsState";
			break;
		case "US SSN":
			return "IsSSN";
			break;
		case "Credit Card":
			return "IsCC";
			break;
		case "Time":
			return "IsTime";
			break;
		case "Regular expression":
			return "RegExp";
			break;						
		default:
			return $name;
			break;
	}
}

function GetInputElementId($field, $id, $ptype)
{
	global $strTableName;
	$pSet = new ProjectSettings($strTableName, $ptype);
	$format = $pSet->getEditFormat($field);
	if($format == EDIT_FORMAT_DATE)
	{
		$type = $pSet->getDateEditType($field);
		if($type==EDIT_DATE_DD || $type==EDIT_DATE_DD_DP)
			return "dayvalue_".GoodFieldName($field)."_".$id;
		else
			return "value_".GoodFieldName($field)."_".$id;
	}
	else if($format==EDIT_FORMAT_RADIO)
		return "radio_".GoodFieldName($field)."_".$id."_0";
	else if($format==EDIT_FORMAT_LOOKUP_WIZARD)	
	{
		$lookuptype=$pSet->LookupControlType($field);
		if($lookuptype==LCT_AJAX || $lookuptype==LCT_LIST)
			return "display_value_".GoodFieldName($field)."_".$id;
		else
			return "value_".GoodFieldName($field)."_".$id;
	}	
	else
		return "value_".GoodFieldName($field)."_".$id;		
}

function SetLangVars($links)
{
	global $xt;
	$xt->assign("lang_label",true);
	if(@$_REQUEST["language"])
		$_SESSION["language"]=@$_REQUEST["language"];

	$var=GoodFieldName(mlang_getcurrentlang())."_langattrs";
	$xt->assign($var,"selected");
	$is508=isEnableSection508();
	if($is508)
		$xt->assign_section("lang_label","<label for=\"lang\">","</label>");
	if($links == "login")
		$xt->assign("langselector_attrs","name=lang ".($is508==true ? "id=\"lang\" " : "")."onchange=\"javascript: document.forms[0].btnSubmit.value='';document.forms[0].action = '".$links.".php?language='+this.options[this.selectedIndex].value;document.forms[0].submit();\"");
	else
		$xt->assign("langselector_attrs","name=lang ".($is508==true ? "id=\"lang\" " : "")."onchange=\"javascript: window.location='".$links.".php?language='+this.options[this.selectedIndex].value\"");
}

function GetTableCaption($table)
{
	global $tableCaptions;
	return @$tableCaptions[mlang_getcurrentlang()][$table];
}

function GetFieldByLabel($table="", $label) 
{
	global $field_labels, $strTableName;
	if (!$table)
	{
		$table = $strTableName;
	}
	
	if(!array_key_exists($table,$field_labels))
		return "";
	$currLang = mlang_getcurrentlang();
	if(!array_key_exists($currLang,$field_labels[$table]))
		return "";	
	$lables = $field_labels[$table][mlang_getcurrentlang()];
	foreach ($lables as $key=>$val)
	{
		if ($val == $label)
		{
			return $key;
		}
	}
	return '';
}

function GetFieldLabel($table,$field)
{
	global $field_labels;
	if(!array_key_exists($table,$field_labels))
		return "";
	return @$field_labels[$table][mlang_getcurrentlang()][$field];
}

function GetFieldToolTip($table, $field)
{
	global $fieldToolTips;
	if(!array_key_exists($table, $fieldToolTips))
		return "";
	return @$fieldToolTips[$table][mlang_getcurrentlang()][$field];
}

function GetCustomLabel($custom)
{
	global $custom_labels;
	return @$custom_labels[mlang_getcurrentlang()][$custom];
}

function mlang_getcurrentlang()
{
	global $mlang_messages,$mlang_defaultlang;
	if(@$_REQUEST["language"])
		$_SESSION["language"]=@$_REQUEST["language"];
	if(@$_SESSION["language"])
		return $_SESSION["language"];
	return $mlang_defaultlang;
}

function mlang_getlanglist()
{
	global $mlang_messages,$mlang_defaultlang;
	return array_keys($mlang_messages);
}

function displayDetailsOn($table,$page)
{
	global $detailsTablesData;
	if(!isset($detailsTablesData[$table]) && !is_array($detailsTablesData[$table]))
		return false;
	if($page == PAGE_EDIT)
		$key="previewOnEdit";
	elseif($page == PAGE_ADD)
		$key="previewOnAdd";
	elseif($page == PAGE_VIEW)
		$key="previewOnView";
	else
		$key="previewOnList";
	for($i=0;$i<count($detailsTablesData[$table]);$i++)
	{
		if($detailsTablesData[$table][$i][$key])
			return true;
	}
	return false;
}

function showDetailTable($params)
{
	global $strTableName;
	$oldTableName = $strTableName;
	$strTableName = $params["table"];
	// show page
	if($params["dpObject"]->isDispGrid())
		$params["dpObject"]->showPage();	
	$strTableName = $oldTableName;
}

//	update record on Edit page

function DoUpdateRecordSQL($table,&$evalues,&$blobfields,$strWhereClause, $pageid, &$pageObject, &$cipherer)
{
	global $error_happened,$conn,$inlineedit,$usermessage,$message;
	if(!count($evalues))
		return true;
	$strSQL = "update ".AddTableWrappers($table)." set ";
	$blobs = PrepareBlobs($evalues,$blobfields);
//	construct SQL string
	foreach($evalues as $ekey=>$value)
	{
		if(in_array($ekey,$blobfields))			
			$strValues=$value;
		else
			if(is_null($cipherer))
				$strValues = add_db_quotes($ekey,$value);
			else 
				$strValues = $cipherer->AddDBQuotes($ekey,$value);
		$strSQL.=$pageObject->pSet->getTableField($ekey)."=".$strValues.", ";
	}
	if(substr($strSQL,-2)==", ")
		$strSQL=substr($strSQL,0,strlen($strSQL)-2);
	if($strWhereClause === "")
	{
		$strWhereClause = " (1=1) ";
	}
	$strSQL.=" where ".$strWhereClause;
	if(SecuritySQL("Edit"))
		$strSQL .= " and (".SecuritySQL("Edit").")";

	if(!ExecuteUpdate($pageObject,$strSQL,$blobs,false))
		return false;

//	delete & move files
	$pageObject->ProcessFiles();
	if ( $inlineedit ) 
	{
		$status="UPDATED";
		$message=""."Registro foi atualizado"."";
		$IsSaved = true;
	} 
	else 
		$message="<<< "."Registro foi atualizado"." >>>";
	if($usermessage!="")
		$message = $usermessage;
	return true;
}

//	insert record on Add & Register pages

function DoInsertRecordSQL($table,&$avalues,&$blobfields, $pageid, &$pageObject, &$cipherer)
{
	global $error_happened,$conn,$inlineadd,$usermessage,$message,$failed_inline_add,$keys,$strTableName;
//	make SQL string
	$strSQL = "insert into ".AddTableWrappers($table)." ";
	$strFields="(";
	$strValues="(";
	$blobs = PrepareBlobs($avalues,$blobfields);
	foreach($avalues as $akey=>$value)
	{
		$strFields .= $pageObject->pSet->getTableField($akey).", ";
		if(in_array($akey, $blobfields))			
			$strValues.=$value.", ";
		else
			if(is_null($cipherer))
				$strValues .= add_db_quotes($akey,$value).", ";
			else 
				$strValues .= $cipherer->AddDBQuotes($akey,$value).", ";
	}
	if(substr($strFields,-2)==", ")
		$strFields=substr($strFields,0,strlen($strFields)-2);
	if(substr($strValues,-2)==", ")
		$strValues=substr($strValues,0,strlen($strValues)-2);
	$strSQL.=$strFields.") values ".$strValues.")";
	
	if(!ExecuteUpdate($pageObject,$strSQL,$blobs,true))
		return false;
	
	if($error_happened)
		return false;
	$pageObject->ProcessFiles();
	if ( $inlineadd==ADD_INLINE ) 
	{
		$status="ADDED";
		$message=""."Registro foi adicionado"."";
		$IsSaved = true;
	} 
	else
		$message="<<< "."Registro foi adicionado"." >>>";
	if($usermessage!="")
		$message = $usermessage;
		
	$auditObj = GetAuditObject($table);
	
	if($inlineadd==ADD_SIMPLE || $inlineadd==ADD_INLINE || $inlineadd==ADD_ONTHEFLY || $inlineadd==ADD_POPUP || $inlineadd==ADD_MASTER || tableEventExists("AfterAdd",$strTableName) || $auditObj)
	{
		$failed_inline_add = false;
		$keyfields=$pageObject->pSet->getTableKeys();
		foreach($keyfields as $k)
		{
			if(array_key_exists($k,$avalues))
				$keys[$k]=$avalues[$k];
			elseif($pageObject->pSet->isAutoincField($k))
			{
							$mystrSQL = "SELECT MAX(".AddFieldWrappers($k).") FROM ".AddTableWrappers($table);
				$myrs=db_query($mystrSQL,$conn);
				if($mydata = db_fetch_numarray($myrs));
					$keys[$k] = $mydata[0];
			}
			else
				$failed_inline_add = true;
		}
	}
	return true;
}

function &getEventObject($table)
{
	global $tableEvents;
	$ret = null;
	if(!array_key_exists($table,$tableEvents))
		return $ret;
	return $tableEvents[$table];
}
function tableEventExists($event,$table)
{
	global $tableEvents;
	if(!array_key_exists($table,$tableEvents))
		return false;
	return $tableEvents[$table]->exists($event);
}

function add_nocache_headers()
{
	header("Cache-Control: no-cache, no-store, max-age=0, must-revalidate");
	header("Pragma: no-cache");
	header("Expires: Fri, 01 Jan 1990 00:00:00 GMT");
}

function IsGuidString(&$str)
{
//	{3F2504E0-4F89-11D3-9A0C-0305E82C3301} 
	if(strlen($str)==36 && substr($str,0,1)!="{" && substr($str,-1)!="}")
		$str="{".$str."}";
	elseif(strlen($str)==37 && substr($str,0,1)=="{" && substr($str,-1)!="}")
		$str=$str."}";
	elseif(strlen($str)==37 && substr($str,0,1)!="{" && substr($str,-1)=="}")
		$str="{".$str;
		
	if(strlen($str)!=38)
		return false;
	for($i=0;$i<38;$i++)
	{
		$c = substr($str,$i,1);
		if($i==0)
		{
			if($c!='{')
				return false;
		}
		elseif($i==37 )
		{
			if($c!='}')
				return false;
		}
		elseif($i==9 || $i==14 || $i==19 || $i==24)
		{
			if($c!='-')
				return false;
		}
		else
		{
			if(($c<'0' || $c>'9') && ($c<'a' || $c>'f') && ($c<'A' || $c>'F'))
				return false;
		}
	}
	return true;
}
function IsStoredProcedure($strSQL)
{
	if(strlen($strSQL)>6)
	{
		$c=strtolower(substr($strSQL,6,1));
		if(strtolower(substr($strSQL,0,6))=="select" && ($c<'0' || $c>'9') && ($c<'a' || $c>'z') && $c!='_')
			return false;
		else
			return true;
	}
	else
		return true;
}

function CreateCKeditor($cfield, $value)
{
	echo "<div id=\"disabledCKE_".$cfield."\"><textarea id=\"".$cfield."\" name=\"".$cfield."\" rows=\"8\" cols=\"60\">".htmlspecialchars($value)."</textarea>";
}
function GetDatabaseType()
{
	global $nDBType;
	return $nDBType;
}



/**
* Check mobile device or PC 
*
*/
function isMobile(){
			return false;
}

/**
 * GetPageLayout
 * Return reference to layout object by table name, page type and section (or tab) name
 * @param {string} short table name (may be empty)
 * @param {string} page type
 * @param {string} section or tab name (may be empty)
 * @return {reference} reference to layout object
 */
function & GetPageLayout($tableName, $pageType, $sectionName = '')
{
	global $page_layouts;
	$layoutName = ($tableName != '' ? $tableName.'_' : '').$pageType.($sectionName != '' ? '_'.$sectionName : '');
	$layout = $page_layouts[$layoutName]; 
	if($layout){
		if(isMobile())
		{
			$layout->style = $layout->styleMobile;
		}
		else if(postvalue("pdf"))
		{
			$layout->style = $layout->pdfStyle();
		}
	}
	return $layout;
}

/**
 * isMobileIOS
 * Check is page browsed in apple device and is browser Safari or iCab
 * @return {bool}
 */
function isMobileIOS()
{
			return false;
}

function extractStyle($str)
{
	$pos = my_stripos($str,'style="',0);
	$quot = '"';
	if($pos === false)
	{
		$pos = my_stripos($str,'style=\'',0);
		$quot = '\'';
	}
	if($pos === false)
		return;
	$pos1 = strpos($str,$quot, $pos+7);
	if($pos1 === false)
		return "";
	return substr($str, $pos+7, $pos1-$pos-7);
}

function injectStyle($str, $style)
{
	$pos = my_stripos($str,'style="',0);
	$quot = '"';
	if($pos === false)
	{
		$pos = my_stripos($str,'style=\'',0);
		$quot = '\'';
	}
	if($pos === false)
		return $str.' style="'.$style.'"';
	return substr($str,0, $pos+7).$style.";".substr($str, $pos+7);
}

function isSingleSign(){
	if (GetGlobalData("ADSingleSign",0) && $_SERVER["REMOTE_USER"]){
		return false;
	}
	return true;
}

function generatePassword($length)
{
	$password="";
	for($i=0;$i<$length;$i++)
	{
		$j = rand(0,35);
		if($j<26)
			$password.= chr(ord('a')+$j);
		else
			$password.= chr(ord('0')-26+$j);
	}
	return $password;
}

function securityCheckFileName($fileName)
{
	$maliciousStrings = array("../", "..\\");
	for($i = 0; $i < count($maliciousStrings); $i++)
	{
		while(strpos($fileName, $maliciousStrings[$i]) !== FALSE)
		{
			$fileName = str_replace($maliciousStrings, "", $fileName);
		}
	}
	return $fileName;
}

function getOptionsForMultiUpload($pSet, $field)
{
	if($pSet->isAbsolute($field))
		$uploadDir = $pSet->getUploadFolder($field);
	else
		$uploadDir = getabspath($pSet->getUploadFolder($field));
	$options = array(
		"max_file_size" => $pSet->getMaxFileSize($field),
		"max_totalFile_size" => $pSet->getMaxTotalFilesSize($field),
		"max_number_of_files" => $pSet->getMaxNumberOfFiles($field),
		"max_width" => $pSet->getMaxImageWidth($field),
		"max_height" => $pSet->getMaxImageHeight($field));
	if($pSet->getResizeOnUpload($field))
	{
		$options["resizeOnUpload"] = true;
		$options["max_width"] = $pSet->getNewImageSize($field);
		$options["max_height"] = $options["max_width"];
	}
	if($pSet->getCreateThumbnail($field))
	{
		$options['image_versions'] = array(
                'thumbnail' => array(
                    'max_width' => $pSet->getThumbnailSize($field),
                    'max_height' => $pSet->getThumbnailSize($field),
					'thumbnailPrefix' => $pSet->getStrThumbnail($field)
                ));
	}
    return $options;
}

function getContentTypeByExtension($ext)
{
	if($ext==".asf")
		$ctype = "video/x-ms-asf";
	elseif($ext==".avi")
		$ctype = "video/avi";
	elseif($ext==".doc")
		$ctype = "application/msword";
	elseif($ext==".zip")
		$ctype = "application/zip";
	elseif($ext==".xls")
		$ctype = "application/vnd.ms-excel";
	elseif($ext==".png")
		$ctype = "image/png";
	elseif($ext==".gif")
		$ctype = "image/gif";
	elseif($ext==".jpg" || $ext=="jpeg")
		$ctype = "image/jpeg";
	elseif($ext==".wav")
		$ctype = "audio/wav";
	elseif($ext==".mp3")
		$ctype = "audio/mpeg3";
	elseif($ext==".mpg" || $ext=="mpeg")
		$ctype = "video/mpeg";
	elseif($ext==".rtf")
		$ctype = "application/rtf";
	elseif($ext==".htm" || $ext=="html")
		$ctype = "text/html";
	elseif($ext==".asp")
		$ctype = "text/asp";
	elseif($ext == ".flv")
		$ctype = "video/flv";
	elseif($ext == ".mp4")
		$ctype = "video/mp4";
	elseif($ext == ".webm")
		$ctype = "video/webm";
	else
		$ctype = "application/octet-stream";	
		
	return $ctype;
}

function getLatLngByAddr($addr) {
	$url = 'http://maps.googleapis.com/maps/api/geocode/json?address='.rawurlencode($addr).'&sensor=false';
	$result = my_json_decode(myurl_get_contents($url));
	if($result['status'] == 'OK') {
		$location = $result['results'][0]['geometry']['location'];
		return $location;
	}
	return false;	
} 

function isTableGeoUpdatable($pSettings) {
	foreach($pSettings->getEditFields() as $field) {
       if($pSettings->isUpdateLatLng($field)) {
			return true;
		}
	}	
	return false;
}

function setUpdatedLatLng(&$values, $pSettings, $oldvalues = false) {
    if($oldvalues) { 	//get Edit page's fields
		$formFields = $pSettings->getEditFields();
	} else {	   	  //get Add page's fields
		$formFields = $pSettings->getAddFields(); 
	}
	
	foreach($formFields as $field) {
        //check if 'UpdateLatLng' is ticked for a field
		if(!$pSettings->isUpdateLatLng($field)) {
			continue;
		}
		$mapData = $pSettings->getMapData($field);
		
		 //check if the actual map's address value were added/changed 
		if(!isset($values[$mapData['address']]) || $oldvalues && $values[$mapData['address']] == $oldvalues[$mapData['address']]) {
			continue;
		}
	
		//get updated coordinates
		$location = getLatLngByAddr($values[$mapData['address']]);	
		if(!$location) {
            continue; 
		}

		//check if the actual non emty lat value is added/updated by a user
		if( !isset($values[$mapData['lat']]) || ( isset($values[$mapData['lat']]) &&  
		   ($values[$mapData['lat']] == "" || $oldvalues && $values[$mapData['lat']] == $oldvalues[$mapData['lat']]) ) ) {
			
			$values[$mapData['lat']] = $location['lat'];
		}

		//check if the actual non emty lng value is added/updated by a user
		if( !isset($values[$mapData['lng']]) || ( isset($values[$mapData['lng']]) && 
		   ($values[$mapData['lng']] == "" || $oldvalues && $values[$mapData['lng']] == $oldvalues[$mapData['lng']]) ) ) {
			
			$values[$mapData['lng']] = $location['lng'];
		}	
	}
	return;
}

function isLoggedAsGuest(){
	if($_SESSION["UserID"] == "Guest" && $_SESSION["AccessLevel"] == ACCESS_LEVEL_GUEST){
		return true;
	}
	return false;
}

function Override($page)
{
	global $globalSettings;
	if(!isset($globalSettings["override"][$page]))
		return otNone;
	return $globalSettings["override"][$page];
}

/**
 * printDispositionHeader
 * Print disposition header for mfhandler.php 
 * @param {string} file name
 */
function printDispositionHeader($fileName)
{
	if(postvalue("nodisp") != 1)
		header("Content-Disposition: attachment;Filename=\"".$fileName."\"");
}

/**
 * printHeaders
 * Print HTTP headers for mfhandler.php
 */
function printHeaders()
{
	header('Content-Disposition: inline; filename="files.json"');
	header('X-Content-Type-Options: nosniff');
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: OPTIONS, HEAD, GET, POST');
	header('Access-Control-Allow-Headers: X-File-Name, X-File-Type, X-File-Size');
}

function GetFieldType($field,$table="")
{
	global $pageObject;
	if($table != "" || !isset($pageObject))
	{
		if($table == "")
		{
			global $strTableName;
			$table = $strTableName;
		}
		$newSet = new ProjectSettings($table);
		return $newSet->getFieldType($field);
	}
	else
		return $pageObject->pSet->getFieldType($field);
}
function Label($field,$table="")
{
	global $pageObject;
	if($table != "" || !isset($pageObject))
	{
		if($table == "")
		{
			global $strTableName;
			$table = $strTableName;
		}
		$newSet = new ProjectSettings($table);
		$result = $newSet->label($field); 
	}
	else
		$result = $pageObject->pSet->label($field);
	return $result != "" ? $result : $field;
}

function getIconByFileType($fileType, $sourceFileName)
{
	switch($fileType)
	{
		case "text/html":
			$fileName = "html.png";
			break;
		case "text/asp":
			$fileName = "code.png";
			break;
		case "application/msword":
		case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
			$fileName = "doc.png";
			break;
		case "application/vnd.ms-excel":
		case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
			$fileName = "xls.png";
			break;
		case "application/rtf":
			$fileName = "rtf.png";
			break;
		case "image/png":
		case "image/x-png":
			$fileName = "png.png";
			break;
		case "image/gif":
			$fileName = "gif.png";
			break;
		case "image/jpeg":
		case "image/pjpeg":
			$fileName = "jpg.png";
			break;
		case "audio/wav":
			$fileName = "wma.png";
			break;
		case "audio/mp3":
		case "audio/mpeg3":
		case "audio/mpeg":
			$fileName = "mp2.png";
			break;
		case "video/mpeg":
			$fileName = "mpeg.png";
			break;
		case "video/flv":
			$fileName = "flv.png";
			break;
		case "video/mp4":
			$fileName = "mp4.png";
			break;
		case "video/x-ms-asf":
			$fileName = "asf.png";
			break;
		case "video/webm":
		case "video/x-webm":
		case "video/avi":
			$fileName = "mpg.png";
			break;
		case "application/zip":
		case "application/x-zip-compressed":
			$fileName = "zip.png";
			break;
		default:
			$fileName = "text.png";
			$dotPosition = strrpos($sourceFileName, '.');
			if($dotPosition !== false && $dotPosition < strlen($sourceFileName) - 1)
			{
				$ext = substr($sourceFileName, $dotPosition + 1);
				$icons = array();
	           	$icons['7z'] = '7z';
	        		$icons['asf'] = 'asf';
	        		$icons['asp'] = 'code';
	        		$icons['avi'] = 'mpg';
	        		$icons['chm'] = 'chm';
	        		$icons['doc'] = 'doc';
	        		$icons['docx'] = 'doc';
	        		$icons['flv'] = 'flv';
	        		$icons['gz'] = 'gz';
	        		$icons['html'] = 'html';
	        		$icons['mdb'] = 'mdb';
	        		$icons['mdbx'] = 'mdb';
	        		$icons['mp3'] = 'mp2';
	        		$icons['mp4'] = 'mp4';
	        		$icons['mpeg'] = 'mpeg';
	        		$icons['mpg'] = 'mpg';
	        		$icons['mov'] = 'mov';
	        		$icons['pdf'] = 'pdf';
	        		$icons['php'] = 'code';
	        		$icons['pps'] = 'pps';
	        		$icons['ppt'] = 'powerpoint';
	        		$icons['psd'] = 'psd';
	        		$icons['rar'] = 'rar';
	        		$icons['rtf'] = 'rtf';
	        		$icons['swf'] = 'swf';
	        		$icons['tif'] = 'tif';
	        		$icons['ttf'] = 'ttf';
	        		$icons['txt'] = 'txt';
	        		$icons['wav'] = 'wav';
	        		$icons['webm'] = 'mpg';
	        		$icons['wma'] = 'wma';
	        		$icons['wmv'] = 'emv';
	        		$icons['xls'] = 'xls';
	        		$icons['xlsx'] = 'xls';
	        		$icons['zip'] = 'zip';
				if(isset($icons[$ext]))
					$fileName = $icons[$ext].".png";
			}
	}
	return $fileName;
}
?>