<?php
$access_dmy="mdy";

function db_connect()
{
	global $host,$user,$pwd,$dbname,$access_dmy,$cCodepage;
    //$connstr="PROVIDER=SQLOLEDB;SERVER=".$host.";UID=".$user.";PWD=".$pwd.";DATABASE=".$dbname;
	
    global $ODBCString;
    
	try {
	$conn = new COM("ADODB.Connection",NULL,$cCodepage);
	$conn->Open($ODBCString);
		$rs=$conn->Execute("select datevalue('2000-11-22') as qw");
	$str = $rs->Fields[0]->Value;
	$y=strpos($str,"2000");
	$m=strpos($str,"11");
	$d=strpos($str,"22");
	if($y<$m && $m<$d)
		$access_dmy="ymd";
	if($d<$m && $m<$y)
		$access_dmy="dmy";

	} catch(Exception $e){
		trigger_error($e->getMesage());
	}
	
	return $conn;
}
function db_close($conn)
{
	$conn->Close();
}

function db_query($qstring,$conn)
{
	global $strLastSQL,$dDebug, $cCodepage;
	if ($dDebug===true)
		echo $qstring."<br>";
		$strLastSQL=$qstring;
	try{
		$recordset = new COM("ADODB.recordset",NULL,$cCodepage);
		$recordset->Open($qstring,$conn, 2);
		
		return $recordset;
		//$rows = $recordset->GetRows();
		//$recordset->close();
			
		
		
	} catch(com_exception $e)
	{
		trigger_error($e->getMessage(),E_USER_ERROR);
	}

}

function db_exec($qstring,$conn)
{
	global $dDebug;
	if ($dDebug===true)
		echo $qstring."<br>";
	return db_query($qstring,$conn);
}


function db_pageseek($qhandle,$pagesize,$page)
{
	if($page==1)
		return;
	if($qhandle->EOF())
		return;
   $qhandle->Move($pagesize*($page-1));
}

function db_fetch_array($rs, $assoc=1)
{
	global $access_dmy;
	if( $rs->EOF() )
           return false;
	try {
	$ret=array();
	for( $i = 0; $i < db_numfields($rs); $i++ )
	{
		if((IsBinaryType($rs->Fields[$i]->Type) && $rs->Fields[$i]->Type!=128) || $rs->Fields[$i]->Type == 203)
		{
			$str="";
			if($rs->Fields[$i]->ActualSize > 0)
			{
				$val=$rs->Fields[$i]->GetChunk($rs->Fields[$i]->ActualSize);
				if(is_array($val))
				{
					$str=str_pad("",count($val));
				}
				else if (is_object($val)){
					$j=0;
					foreach($val as $byte){
						$str[$j++]=chr($byte);
					}
				}
				else
				{
					$str = $val;
				}
			}
			if($assoc)
				$ret[$rs->Fields[$i]->Name] = $str;
			else
				$ret[$i] = $str;
		}
		else
		{
			$value = $rs->Fields[$i]->Value;
			if(is_null($value))
			{
				$val=NULL;
			}
			else
			{
				if(isdatefieldtype($rs->Fields[$i]->Type))
					$value=localdatetime2db((string)$rs->Fields[$i]->Value,$access_dmy);
				if(IsNumberType($rs->Fields[$i]->Type))
					$val=floatval($value);
				else
					$val=strval($value);
			}
			if($assoc)
				$ret[$rs->Fields[$i]->Name] = $val;
			else
				$ret[$i] = $val;
		}
	}
	
	$rs->MoveNext();
	
	} catch(com_exception $e)
	{
		trigger_error($e->getMessage(),E_USER_ERROR);
	}

	return $ret;
}

function db_fetch_numarray($qhandle)
{
	return db_fetch_array($qhandle,0);
}

function db_closequery($qhandle)
{
	$qhandle->Close();
}


function db_error($conn)
{
	if($conn->Errors->Count)
		return $conn->Errors[$conn->Errors->Count-1]->Description;
	else
		return '';
}



function db_numfields($lhandle)
{
	return $lhandle->Fields->Count;
}

function db_fieldname($lhandle,$fnumber)
{
	return $lhandle->Fields($fnumber)->Name;
}

function db_insertid($qhandle)
{
$strSQL = "select @@IDENTITY as indent";
$rs = db_query($strSQL,$qhandle);
$row = db_fetch_array($rs);
return $row["indent"];
}

?>