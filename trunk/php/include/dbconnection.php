<?php
function includeAccessFile()
{
	global $ODBCString;
	if (stripos($ODBCString, 'Provider=') !==false)
		return "dbconnection.ado.php";
	else 
		return "dbconnection.odbc.php";
}
include(includeAccessFile());
?>
