<?php
class oLocking
{
	var $lockTableName="";
	var $TableObj;
	var $ConfirmTime=250;
	var $UnlockTime=300;
	var $ConfirmAdmin;
	var $ConfirmUser;
	var $LockAdmin;
	var $LockUser;
	var $UserID;
	function oLocking()
	{
		global $dal;
		$this->ConfirmAdmin = "O administrador %s abortou a sua sessão de edição";
		$this->ConfirmUser = "A sua sessão de edição expirou";
		$this->LockAdmin = "Registro está sendo editado por %s durante %s minutos";
		$this->LockUser = "Registro está sendo editado por outro usuário";
		$this->TableObj = &$dal->Table($this->lockTableName);
		if(isset($_SESSION["UserID"]) && !is_null($_SESSION["UserID"]))
			$this->UserID = $_SESSION["UserID"];
		else
			$this->UserID = "Guest";
	}

	function LockRecord($strtable,$keys)
	{
		$skeys = "";
		foreach($keys as $ind=>$val)
		{
			if(strlen($skeys))
				$skeys.="&";
			$skeys.=rawurlencode($val);
		}
			
		$sdate=now();
//	add a record - try to lock
		
		$this->TableObj->startdatetime=$sdate;
		$this->TableObj->confirmdatetime=$sdate;
		$this->TableObj->sessionid=session_id();
		$this->TableObj->table=$strtable;
		$this->TableObj->keys=$skeys;
		$this->TableObj->userid=$this->UserID;
		$this->TableObj->action=1;
		$this->TableObj->Add();

		$arrDelete = array();
//	check all locking records
		$rstmp=$this->TableObj->Query(AddFieldWrappers("table")."=".db_prepare_string($strtable)." and ".AddFieldWrappers("keys")."=".db_prepare_string($skeys)." and ".AddFieldWrappers("action")."=1",AddFieldWrappers("id")." asc");
		while($data = db_fetch_array($rstmp))
		{
			if(secondsPassedFrom($data["confirmdatetime"])>$this->UnlockTime)
			{
//	locking record is expired
				$arrDelete[]=$data["id"];
			}
			else
			{
//	delete expired records
				foreach($arrDelete as $ind=>$val)
				{
					$this->TableObj->id=$val;
					$this->TableObj->Delete();
				}
				
				if($data["sessionid"]==session_id())
				{
//	locking was successful
					return true;
				}
				else
				{
//	record is already locked, delete locking attempt
					$this->TableObj->sessionid=session_id();
					$this->TableObj->action=1;
					$this->TableObj->table=$strtable;
					$this->TableObj->keys=$skeys;
					$this->TableObj->Delete();
					return false;
				}
			}
		}
		return false;
	}

	function UnlockRecord($strtable,$keys,$sid)
	{
		if($sid=="")
			$sid=session_id();
		$skeys="";
		foreach($keys as $ind=>$val)
		{
			if(strlen($skeys))
				$skeys.="&";
			$skeys.=rawurlencode($val);
		}
		$this->TableObj->table=$strtable;
		$this->TableObj->keys=$skeys;
		$this->TableObj->sessionid=$sid;
		$this->TableObj->action=1;
		$this->TableObj->Delete();
	}
	function ConfirmLock($strtable,$keys,&$message)
	{
		$skeys="";
		foreach($keys as $ind=>$val)
		{
			if(strlen($skeys))
				$skeys.="&";
			$skeys.=rawurlencode($val);
		}

//	add locking attempt
		$sdate=now();
		$this->TableObj->startdatetime=$sdate;
		$this->TableObj->confirmdatetime=$sdate;
		$this->TableObj->sessionid=session_id();
		$this->TableObj->table=$strtable;
		$this->TableObj->keys=$skeys;
		$this->TableObj->userid=$this->UserID;
		$this->TableObj->action=1;
		$this->TableObj->Add();
		
		$rstmp=$this->TableObj->Query(AddFieldWrappers("table")."=".db_prepare_string($strtable)." and ".AddFieldWrappers("keys")."=".db_prepare_string($skeys)." and ".AddFieldWrappers("action")."=1",AddFieldWrappers("id")." asc");
		
		$myfound=0;	// total our records found
		$newid=0;	//	last our record - added 5 lines ago
		$oldid=0;	//	next to last our record
		$newdate="";	//	last our confirm time
		$olddate="";	//	next to last our confirm time
		
		$otherfound=0;	// other's records found between out last and next to last
		$tempfound=0;	
		
/*
		...
		$oldid
		...(others's records - $otherfound total)
		$newid
*/		
		
//	check all locking records, count records
		while($data = db_fetch_array($rstmp))
		{
			if($data["sessionid"]==session_id())
			{
				$oldid=$newid;
				$newid=$data["id"];
				$newdate=$data["confirmdatetime"];
				$olddate=$newdate;
				$myfound++;
				$otherfound=$tempfound;
				$tempfound=0;
				continue;
			}
			$tempfound++;
		}
		if($myfound>1 && !$otherfound)
		{
//	no other's records, locking is confirmed
			$this->TableObj->id=$oldid;
			$this->TableObj->confirmdatetime=now();
			$this->TableObj->Update();
			
			$this->TableObj->id=$newid;
			$this->TableObj->Delete();
			return true;
		}
		elseif($myfound>1 && $otherfound)
		{
//	found some other's records
//	check if previous record is not expired
			if(secondsPassedFrom($olddate)>$this->UnlockTime-5)
			{
//	expired - delete locking record, confirm was not successful
				$this->UnlockRecord($strtable,$keys,session_id());
				$message=$this->ConfirmUser;
				return false;
			}
			else
			{
//	not expired, locking is confirmed
				$this->TableObj->id=$oldid;
				$this->TableObj->confirmdatetime=now();
				$this->TableObj->Update();
				
				$this->TableObj->id=$newid;
				$this->TableObj->Delete();
				return true;
			}
		}
		else
		{
//	locking was lost
			$this->UnlockRecord($strtable,$keys,session_id());
//	check if locking was removed by admin or not
			$rstmp=$this->TableObj->Query(AddFieldWrappers("table")."=".db_prepare_string($strtable)." and ".AddFieldWrappers("keys")."=".db_prepare_string($skeys)." and ".AddFieldWrappers("sessionid")."<>'".session_id()."' and ".AddFieldWrappers("action")."=2",AddFieldWrappers("id")." asc");
			if($data = db_fetch_array($rstmp))
				$message=mysprintf($this->ConfirmAdmin,array($data["userid"]));
			else
				$message=$this->ConfirmUser;
			$this->TableObj->table=$strtable;
			$this->TableObj->keys=$skeys;
			$this->TableObj->action=2;
			$this->TableObj->Delete();
			return false;
		}
	}
	function GetLockInfo($strtable,$keys,$links, $pageid)
	{
		$page=GetTableURL($strtable)."_edit.php";
		$skeys="";
		foreach($keys as $ind=>$val){
			if(strlen($skeys))
				$skeys.="&";
			$skeys.=rawurlencode($val);
		}
		$rstmp=$this->TableObj->Query(AddFieldWrappers("table")."=".db_prepare_string($strtable)." and ".AddFieldWrappers("keys")."=".db_prepare_string($skeys)." and ".AddFieldWrappers("sessionid")."<>'".session_id()."' and ".AddFieldWrappers("action")."=1",AddFieldWrappers("id")." asc");
		if($data = db_fetch_array($rstmp))
		{
			$sdate=now();
			$arrDateTime=db2time($data["startdatetime"]);
			
			$str=mysprintf($this->LockAdmin,array($data["userid"],round(secondsPassedFrom($data["startdatetime"])/60,2)));
			if($links){
				$str.='<a class="unlock" href="#" onclick="Runner.pages.PageManager.getAt(\''.htmlspecialchars(jsreplace($strtable)).'\', '.$pageid.').locking.UnlockAdmin(\''.htmlspecialchars(jsreplace($skeys)).'\',\''.$data["sessionid"].'\',\'no\');return false;">'."Desbloquear registro".'</a>';
				$str.='<a class="edit" href="#" onclick="Runner.pages.PageManager.getAt(\''.htmlspecialchars(jsreplace($strtable)).'\', '.$pageid.').locking.UnlockAdmin(\''.htmlspecialchars(jsreplace($skeys)).'\',\''.$data["sessionid"].'\',\'yes\');return false;">'."Editar registro".'</a>';
			}
			return $str;
		}else
			return "";
	}
	function UnlockAdmin($strtable,$keys,$startEdit)
	{
		$skeys="";
		foreach($keys as $ind=>$val)
		{
			if(strlen($skeys))
				$skeys.="&";
			$skeys.=rawurlencode($val);
		}
		$sdate=now();
		if($startEdit)
		{
//	add a record - lock
			$this->TableObj->startdatetime=$sdate;
			$this->TableObj->confirmdatetime=$sdate;
			$this->TableObj->sessionid=session_id();
			$this->TableObj->table=$strtable;
			$this->TableObj->keys=$skeys;
			$this->TableObj->userid=$this->UserID;
			$this->TableObj->action=1;
			$this->TableObj->Add();
		}
//	delete all other locking records 
		$rstmp=CustomQuery("delete from ".AddTableWrappers($this->lockTableName)." where ".AddFieldWrappers("table")."=".db_prepare_string($strtable)." and ".AddFieldWrappers("keys")."=".db_prepare_string($skeys)." and ".AddFieldWrappers("action")."=1 and ".AddFieldWrappers("sessionid")."<>'".session_id()."' ");

//	inform other users that their locking were removed by locking
		$rstmp=CustomQuery("delete from ".AddTableWrappers($this->lockTableName)." where ".AddFieldWrappers("startdatetime")."<'".format_datetime_custom(adddays(db2time(now()),-2),"yyyy-MM-dd HH:mm:ss")."' and ".AddFieldWrappers("action")."=2");
		
		$this->TableObj->startdatetime=$sdate;
		$this->TableObj->confirmdatetime=$sdate;
		$this->TableObj->sessionid=session_id();
		$this->TableObj->table=$strtable;
		$this->TableObj->keys=$skeys;
		$this->TableObj->userid=$this->UserID;
		$this->TableObj->action=2;
		$this->TableObj->Add();
		
	}
}
?>