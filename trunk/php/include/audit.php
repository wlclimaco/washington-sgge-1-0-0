<?php
class AuditTrailTable
{
	var $logTableName="";
	var $TableObj;
	var $params;
	
	var $strLogin="login";
	var $strFailLogin="failed login";
	var $strLogout="logout";
	var $strChPass="change password";
	var $strAdd="add";
	var $strEdit="edit";
	var $strDelete="delete";
	var $strAccess="access";
	var $strKeysHeader="---Keys";
	var $strFieldsHeader="---Fields";
	var $columnDate="Date";
	var $columnTime="Time";
	var $columnIP="IP";
	var $columnUser="User";
	var $columnTable="Table";
	var $columnAction="Action";
	var $columnKey="Key field";
	var $columnField="Field";
	var $columnOldValue="Old value";
	var $columnNewValue="New value";
	var $attLogin=0;
	var $timeLogin=0;
	var $maxFieldLength = 300;

	function AuditTrailTable()
	{
		global $dal;
		$this->TableObj = &$dal->Table($this->logTableName);
		$userid="";
		if(@$_SESSION["UserID"])
			$userid=$_SESSION["UserID"];
		$this->params=array($_SERVER["REMOTE_ADDR"],$userid);
	}
    function LogLogin($pUsername)
    {
    }
    function LogLoginFailed($pUsername)
    {
    }
    function LogLogout()
    {
    }
    function LogChPassword()
    {
    }
    function LogAdd($str_table,$values,$keys)
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$pSet = new ProjectSettings($str_table);
		$arr=array();
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($this->strAdd, $this->params, $table, $keys, $values, $arr);
		if($retval)
		{
			$str="";
			if(count($keys)>0)
			{
				$str.=$this->strKeysHeader."\r\n";
				foreach($keys as $idx=>$val)
					$str.=$idx." : ".$val."\r\n";
			}
			$strFields="";
			if($this->logValueEnable($str_table))
			{
				foreach($values as $idx=>$val)
				{
					if($val!="" && !array_key_exists($idx,$keys))
					{
						$strFields.=$idx." [new]: ";
						if(IsBinaryType($pSet->getFieldType($idx)))
							$v="<binary value>";
						else
						{
							$v=str_replace(array("\r\n","\n","\t")," ",$val);
							if(strlen($v)>$this->maxFieldLength)
								$v=substr($val,0,$this->maxFieldLength);
						}
						$strFields.=$v."\r\n";
					}
				}
			}
			if($strFields!="")
				$str.=$this->strFieldsHeader."\r\n".$strFields;
			$this->TableObj->datetime=now();
			$this->TableObj->ip=$this->params[0];
			$this->TableObj->user=$this->params[1];
			$this->TableObj->table=$str_table;
			$this->TableObj->action=$this->strAdd;
			$this->TableObj->description=$str;
			$this->TableObj->Add();
		}
		return $retval;
    }
    function LogEdit($str_table,$newvalues,$oldvalues,$keys)
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$pSet = new ProjectSettings($str_table);
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($this->strEdit, $this->params, $table, $keys, $newvalues, $oldvalues);
		if($retval)
		{
			$str="";
			if(count($keys)>0)
			{
				$str.=$this->strKeysHeader."\r\n";
				foreach($newvalues as $idx=>$val)
				{
					if(array_key_exists($idx,$keys))
					{
						if($val!=$oldvalues[$idx])
						{
							$str.=$idx." [old]: ".$oldvalues[$idx]."\r\n";
							$str.=$idx." [new]: ".$val."\r\n";
						}
						else
							$str.=$idx." : ".$val."\r\n";
					}
				}
			}
			$strFields="";
			if($this->logValueEnable($str_table))
			{
				$v="";
				foreach($newvalues as $idx=>$val)
				{
					$type=$pSet->getFieldType($idx);
					if(IsBinaryType($type))
						continue;
					if(IsDateFieldType($type))
					{
						$newvalue=format_datetime_custom(db2time($newvalues[$idx]),"yyyy-MM-dd HH:mm:ss");
						$oldvalue=format_datetime_custom(db2time($oldvalues[$idx]),"yyyy-MM-dd HH:mm:ss");
					}
					else
					{
						$newvalue=$newvalues[$idx];
						$oldvalue=$oldvalues[$idx];
					}
					if($newvalue!=$oldvalue && !array_key_exists($idx,$keys))
					{
						$strFields.=$idx." [old]: ";
						if(IsBinaryType($type))
							$v="<binary value>";
						else
						{
							$v=str_replace(array("\r\n","\n","\t")," ",$oldvalue);
							if(strlen($v)>$this->maxFieldLength)
								$v=substr($v,0,$this->maxFieldLength);
						}
						$strFields.=$v."\r\n";
												
						$strFields.=$idx." [new]: ";
						if(IsBinaryType($type))
							$v="<binary value>";
						else
						{
							$v=str_replace(array("\r\n","\n","\t")," ",$newvalue);
							if(strlen($v)>$this->maxFieldLength)
								$v=substr($v,0,$this->maxFieldLength);
						}
						$strFields.=$v."\r\n";
					}
				}
				$v="";
			}
			if($strFields!="")
				$str.=$this->strFieldsHeader."\r\n".$strFields;
			$this->TableObj->datetime=now();
			$this->TableObj->ip=$this->params[0];
			$this->TableObj->user=$this->params[1];
			$this->TableObj->table=$str_table;
			$this->TableObj->action=$this->strEdit;
			$this->TableObj->description=$str;
			$this->TableObj->Add();
		}
		return $retval;
    }
    function LogDelete($str_table,$values,$keys)
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$pSet = new ProjectSettings($str_table);
		$arr=array();
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($this->strDelete, $this->params, $table, $keys, $values, $arr);
		if($retval)
		{
			$str="";
			if(count($keys)>0)
			{
				$str.=$this->strKeysHeader."\r\n";
				foreach($keys as $idx=>$val)
					$str.=$idx." : ".$val."\r\n";
			}
			$strFields="";
			if($this->logValueEnable($str_table))
			{
				$v="";
				foreach($values as $idx=>$val)
				{
					if($val!="" && !array_key_exists($idx,$keys))
					{
						$strFields.=$idx." [old]: ";
						if(IsBinaryType($pSet->getFieldType($idx)))
							$v="<binary value>";
						else
						{	
							$v=str_replace(array("\r\n","\n","\t")," ",$val);
							if(strlen($v)>$this->maxFieldLength)
								$v=substr($v,0,$this->maxFieldLength);
						}
						$strFields.=$v."\r\n";
					}
				}
			}
			if($strFields!="")
				$str.=$this->strFieldsHeader."\r\n".$strFields;
			$this->TableObj->datetime=now();
			$this->TableObj->ip=$this->params[0];
			$this->TableObj->user=$this->params[1];
			$this->TableObj->table=$str_table;
			$this->TableObj->action=$this->strDelete;
			$this->TableObj->description=$str;
			$this->TableObj->Add();
		}
		return $retval;
    }
    
    function LogAddEvent($message,$description="",$stable="")
    {
		global $globalEvents;
		$retval=true;
		$table=$stable;
		$arr=array();
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($message, $this->params, $table, $keys, $values, $arr);
		if($retval)
		{
			$this->TableObj->datetime=now();
			$this->TableObj->ip=$this->params[0];
			$this->TableObj->user=$this->params[1];
			$this->TableObj->table=$stable;
			$this->TableObj->action=$message;
			$this->TableObj->description=$description;
			$this->TableObj->Add();
		}
		return $retval;
    }
    function LoginSuccessful()
    {
		if($this->attLogin>0 && $this->timeLogin>0)
		{
			$this->TableObj->ip=$_SERVER["REMOTE_ADDR"];
			$this->TableObj->action=$this->strAccess;
			$this->TableObj->Delete();
		}
		
    }
    function LoginUnsuccessful($pUsername)
    {
		if($this->attLogin>0 && $this->timeLogin>0)
		{
			$this->TableObj->datetime=now();
			$this->TableObj->ip=$_SERVER["REMOTE_ADDR"];
			$this->TableObj->user=$pUsername;
			$this->TableObj->table="";
			$this->TableObj->action=$this->strAccess;
			$this->TableObj->description="";
			$this->TableObj->Add();
		}
    }
    
	function LoginAccess()
	{
		if($this->attLogin>0 && $this->timeLogin>0)
		{
			$rstmp=$this->TableObj->Query(AddFieldWrappers("ip")."='".$_SERVER["REMOTE_ADDR"]."' and ".AddFieldWrappers("action")."='access'",AddFieldWrappers("id")." asc");
			$i=0;
			while($data = db_fetch_array($rstmp))
			{
				if(secondsPassedFrom($data["datetime"])/60<=$this->timeLogin)
				{
					if($i==0)
					{
						$firstAccess=$data["datetime"];
					}
					$i+=1;
				}
			}
			if($i>=$this->attLogin)
				return ceil($this->timeLogin-secondsPassedFrom($firstAccess)/60);
			else
				return false;
		}
		else
			return false;
	}
	function logValueEnable($table)
	{
		if($table=="C000007")
		{
			return false;
		}
		if($table=="C000008")
		{
			return false;
		}
		if($table=="C000009")
		{
			return false;
		}
		if($table=="C000010")
		{
			return false;
		}
		if($table=="C000025")
		{
			return false;
		}
		if($table=="C000040")
		{
			return false;
		}
		if($table=="C000044")
		{
			return false;
		}
		if($table=="C000046")
		{
			return false;
		}
		if($table=="C000049")
		{
			return false;
		}
		if($table=="C000071")
		{
			return false;
		}
		if($table=="C000013")
		{
			return false;
		}
		if($table=="C000054")
		{
			return false;
		}
		if($table=="WEB")
		{
			return false;
		}
	}
}

class AuditTrailFile
{
	var $logfile="audit.log";
	var $strLogin="login";
	var $strFailLogin="failed login";
	var $strLogout="logout";
	var $strChPass="change password";
	var $strAdd="add";
	var $strEdit="edit";
	var $strDelete="delete";
	var $strAccess="access";
	var $strKeysHeader="---Keys";
	var $strFieldsHeader="---Fields";
	var $columnDate="Date";
	var $columnTime="Time";
	var $columnIP="IP";
	var $columnUser="User";
	var $columnTable="Table";
	var $columnAction="Action";
	var $columnKey="Key field";
	var $columnField="Field";
	var $columnOldValue="Old value";
	var $columnNewValue="New value";
	var $params;
	var $maxFieldLength = 300;
	
	function AuditTrailFile()
	{
		$userid="";
		if(@$_SESSION["UserID"])
			$userid=$_SESSION["UserID"];
		$this->params=array($_SERVER["REMOTE_ADDR"],$userid);
	}
    function LogLogin($pUsername)
    {
		    }
    function LogLoginFailed($pUsername)
    {
		    }
    function LogLogout()
    {
		    }
    function LogChPassword()
    {
		    }
    function LogAdd($str_table,$values,$keys)
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$pSet = new ProjectSettings($str_table);
		$arr=array();
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($this->strAdd, $this->params, $table, $keys, $values, $arr);
		if($retval)
		{
			if(count($keys)>0)
			{
				$key="";
				foreach($keys as $idx=>$val)
				{
					if($key!="")
						$key.=",";
					$key.=$val;
				}
			}

			$fp=$this->CreateLogFile();
			$str=format_datetime_custom(db2time(now()),"MMM dd,yyyy").chr(9).format_datetime_custom(db2time(now()),"HH:mm:ss").chr(9).$this->params[0].chr(9).$this->params[1].chr(9).$table.chr(9).$this->strAdd.chr(9).$key;
			$str_add="";
			if($this->logValueEnable($str_table))
			{
				foreach($values as $idx=>$val)
				{
					if($val!="" && !array_key_exists($idx,$keys))
					{
						$v="";
						if(IsBinaryType($pSet->getFieldType($idx)))
							$v="<binary value>"."\r\n";
						else
						{
							$v=str_replace(array("\r\n","\n","\t")," ",$val);
							if(strlen($v)>$this->maxFieldLength)
								$v=substr($v,0,$this->maxFieldLength);
						}
						$str_add.=$str.chr(9).$idx.chr(9).chr(9).$v."\r\n";
					}
				}
			}
			else
				$str_add.=$str."\r\n";
			
			if($fp)
			{
				fputs($fp,$str_add);
				fclose($fp);
			}
		}
		return $retval;
    }
    function LogEdit($str_table,$newvalues,$oldvalues,$keys)
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$pSet = new ProjectSettings($str_table);
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($this->strEdit, $this->params, $table, $keys, $newvalues, $oldvalues);
		if($retval)
		{
			if(count($keys)>0)
			{
				$key="";
				foreach($keys as $idx=>$val)
				{
					if($key!="")
						$key.=",";
					$key.=$val;
				}
			}

			$fp=$this->CreateLogFile();
			$str=format_datetime_custom(db2time(now()),"MMM dd,yyyy").chr(9).format_datetime_custom(db2time(now()),"HH:mm:ss").chr(9).$this->params[0].chr(9).$this->params[1].chr(9).$table.chr(9).$this->strEdit.chr(9).$key;
			$putsValue=true;
			$str_add="";
			if($this->logValueEnable($str_table))
			{
				foreach($newvalues as $idx=>$val)
				{
					$type=$pSet->getFieldType($idx);
					if(IsBinaryType($type))
						continue;
					if(IsDateFieldType($type))
					{
						$newvalue=format_datetime_custom(db2time($newvalues[$idx]),"yyyy-MM-dd HH:mm:ss");
						$oldvalue=format_datetime_custom(db2time($oldvalues[$idx]),"yyyy-MM-dd HH:mm:ss");
					}
					else
					{
						$newvalue=$newvalues[$idx];
						$oldvalue=$oldvalues[$idx];
					}
					if($newvalue!=$oldvalue)
					{
						$v1="";
						if(IsBinaryType($type))
							$v1="<binary value>";
						else
						{
							$v1=str_replace(array("\r\n","\n","\t")," ",$oldvalue);
							if(strlen($v1)>$this->maxFieldLength)
								$v1=substr($v1,0,$this->maxFieldLength);
						}
						
						$v2="";
						if(IsBinaryType($type))
							$v2="<binary value>";
						else
						{
							$v2=str_replace(array("\r\n","\n","\t")," ",$newvalue);
							if(strlen($v2)>$this->maxFieldLength)
								$v2=substr($v2,0,$this->maxFieldLength);
						}
						$str_add.=$str.chr(9).$idx.chr(9).$v1.chr(9).$v2."\r\n";
					}
				}
			}
			else
				$str_add.=$str."\r\n";
			if($fp)
			{
				fputs($fp,$str_add);
				fclose($fp);
			}
		}
		return $retval;
    }
    function LogDelete($str_table,$values,$keys)
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$pSet = new ProjectSettings($str_table);
		$arr=array();
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($this->strDelete, $this->params, $table, $keys, $values, $arr);
		if($retval)
		{
			if(count($keys)>0)
			{
				$key="";
				foreach($keys as $idx=>$val)
				{
					if($key!="")
						$key.=",";
					$key.=$val;
				}
			}
			$fp=$this->CreateLogFile();
			$str=format_datetime_custom(db2time(now()),"MMM dd,yyyy").chr(9).format_datetime_custom(db2time(now()),"HH:mm:ss").chr(9).$this->params[0].chr(9).$this->params[1].chr(9).$table.chr(9).$this->strDelete.chr(9).$key;
			$str_add="";
			if($this->logValueEnable($str_table))
			{
				foreach($values as $idx=>$val)
				{
					$v="";
					if(IsBinaryType($pSet->getFieldType($idx)))
						$v="<binary value>";
					else
					{
						$v=str_replace(array("\r\n","\n","\t")," ",$val);
						if(strlen($v)>$this->maxFieldLength)
							$v=substr($v,0,$this->maxFieldLength);
					}
					if($fp)
						$str_add.=$str.chr(9).$idx.chr(9).$v."\r\n";
				}
			}
			else
				$str_add=$str."\r\n";
				
			if($fp)
			{
				fputs($fp,$str_add);
				fclose($fp);
			}
		}
		return $retval;
    }
	function CreateLogFile()
	{
		$p=strrpos($this->logfile,".");
		$logfileName=substr($this->logfile,0,$p);
		$logfileExt=substr($this->logfile,$p+1);
		$tn=$logfileName."_".format_datetime_custom(db2time(now()),"yyyyMMdd").".".$logfileExt;
		
		$fulname = getabspath($tn);
		$fsize = 0;
		if (file_exists($fulname)){
			$fsize = filesize($fulname);
		}
		
		$fp=@fopen($tn,"a");
		if($fp)
		{
			if(!filesize($tn))
			{
				$str=$this->columnDate.chr(9).$this->columnTime.chr(9).$this->columnIP.chr(9).$this->columnUser.chr(9).$this->columnTable.chr(9).$this->columnAction.chr(9).$this->columnKey.chr(9).$this->columnField.chr(9).$this->columnOldValue.chr(9).$this->columnNewValue."\r\n";
				if($fp)
					fputs($fp,$str);
			}			
		}
		return $fp;
	}
	function LogAddEvent($message,$description="",$str_table="")
    {
		global $globalEvents;
		$retval=true;
		$table=$str_table;
		$arr=array();
		if($globalEvents->exists("OnAuditLog"))
			$retval=$globalEvents->OnAuditLog($message, $this->params, $table, $arr, $arr, $arr);
		if($retval)
		{
			$fp=$this->CreateLogFile();
			$str=format_datetime_custom(db2time(now()),"MMM dd,yyyy").chr(9).format_datetime_custom(db2time(now()),"HH:mm:ss").chr(9).$params[0].chr(9).$params[1].chr(9).$table.chr(9).$message.chr(9).$description."\r\n";
			if($fp)
			{
				fputs($fp,$str);
				fclose($fp);
			}
		}
		return $retval;
    }
    
    function LoginAccess()
	{
		return false;
	}
	function LoginSuccessful()
    {
		return true;
    }
    function LoginUnsuccessful($pUsername)
    {	
		return true;
	}
	function logValueEnable($table)
	{
		if($table=="C000007")
		{
			return false;
		}
		if($table=="C000008")
		{
			return false;
		}
		if($table=="C000009")
		{
			return false;
		}
		if($table=="C000010")
		{
			return false;
		}
		if($table=="C000025")
		{
			return false;
		}
		if($table=="C000040")
		{
			return false;
		}
		if($table=="C000044")
		{
			return false;
		}
		if($table=="C000046")
		{
			return false;
		}
		if($table=="C000049")
		{
			return false;
		}
		if($table=="C000071")
		{
			return false;
		}
		if($table=="C000013")
		{
			return false;
		}
		if($table=="C000054")
		{
			return false;
		}
		if($table=="WEB")
		{
			return false;
		}
	}
}
?>