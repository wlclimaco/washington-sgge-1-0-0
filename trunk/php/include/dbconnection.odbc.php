<?php


function db_connect() 
{
    global $ODBCString;
	$uid="";
	$pwd="";
	$conn = odbc_connect($ODBCString,$uid,$pwd);
	if (!$conn) 
	{
	  trigger_error(db_error($conn), E_USER_ERROR);
	}
	return $conn;
}

function db_close($conn)
{
  return odbc_close($conn);
}

function db_query($qstring,$conn) 
{
	global $strLastSQL,$dDebug;
	if ($dDebug===true)
		echo $qstring."<br>";
	$strLastSQL=$qstring;
	if(!($rs=odbc_exec($conn,$qstring)))
	  trigger_error(odbc_error(), E_USER_ERROR);
	odbc_binmode($rs,ODBC_BINMODE_RETURN);
	odbc_longreadlen($rs,1024*1024);
	return $rs;
	
}

function db_exec($qstring,$conn)
{
	global $strLastSQL,$dDebug;
	if ($dDebug===true)
		echo $qstring."<br>";
	$strLastSQL=$qstring;
	return odbc_exec($conn,$qstring);
}

function db_pageseek(&$qhandle,$pagesize,$page)
{
	db_dataseek($qhandle,($page-1)*$pagesize);
}

function db_dataseek(&$qhandle,$row)
{
   $i=0;
   while($i<$row)
   {
   		odbc_fetch_row($qhandle);
		$i++;
   }
}

function db_fetch_array(&$qhandle) {
	return odbc_fetch_array($qhandle);
}

function db_fetch_numarray(&$qhandle) {
	$row=array();
	odbc_fetch_into($qhandle,$row);
	return $row;
}

function db_closequery($qhandle)
{
	@odbc_free_result($qhandle);
}

	
function db_error($conn) {
	return @odbc_errormsg();
}

function db_numfields(&$lhandle) {
	return @odbc_num_fields($lhandle);
}

function db_fieldname(&$lhandle,$fnumber) 
{
	return @odbc_field_name($lhandle,$fnumber+1);
}


?>