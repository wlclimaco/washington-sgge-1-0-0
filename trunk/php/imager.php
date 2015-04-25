<?php 
include_once("include/dbcommon.php");
include_once("include/".(isset($pdf) ? @$params["table"] : postvalue("table"))."_variables.php");

if(!isset($gQuery)){
	if(!isset($gSettings))
		$gSettings = new ProjectSettings(GetTableByShort(isset($pdf) ? @$params["table"] : postvalue("table")));
	$gQuery = $gSettings->getSQLQuery();
}
$file = GetImageFromDB($gQuery, isset($pdf), isset($params) ? $params : array());
?>
