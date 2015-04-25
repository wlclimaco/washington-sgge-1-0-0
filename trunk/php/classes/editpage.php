<?php
include_once(getabspath("classes/runnerpage.php"));
class EditPage extends RunnerPage
{
	var $keys = array();
	
	var $data = null;
	
	var $jsKeys = array();
	
	var $keyFields = array();
	
	// Array of fields, which appear on edit page
	var $editFields = array();
	
	var $editValues = array();
	
	var $readEditValues = false;
		
	function EditPage(&$params)
	{
		parent::RunnerPage($params);
		$this->editFields = $this->getFieldsByPageType();
	}
	
	/**
	 * Assign body end
	 */	
	function assignBodyEnd(&$params) 
	{
		parent::assignBodyEnd($params);
			}
	
	/**
	 * Set keys values
	 */	
	function setKeys($keys)
	{
		$this->keys = $keys;
		$this->setKeysForJs();
	}
	
	function setKeysForJs()
	{
		$i = 0;
		foreach($this->keys as $field=>$value)
		{
			$this->keyFields[$i] = $field;
			$this->jsKeys[$i++] = $value;
		}
	}
	
	function getCurrentRecord()
	{
		$data = $this->getCurrentRecordInternal();
		foreach($data as $fName => $val)
		{
			$editFormat = $this->pSet->getEditFormat($fName);
			if($editFormat == EDIT_FORMAT_DATABASE_FILE || $editFormat==EDIT_FORMAT_DATABASE_IMAGE)
			{
				if($data[$fName])
					$data[$fName] = true;
				else
					$data[$fName] = false;
			}	
		}
		return $data;
	}
	/**
	 * Read current values from the database
	 *
	 * @return {array} array of current record data
	 */
	function getCurrentRecordInternal()
	{
		if (!is_null($this->data))
			return $this->data;
		
		global $conn;
		$query = $this->gQuery->Copy();
		
		$strWhereClause = KeyWhere($this->keys);
				if($this->pSet->getAdvancedSecurityType()!=ADVSECURITY_ALL)
			//	select only owned records
			$strWhereClause = whereAdd($strWhereClause, SecuritySQL("Edit"));
		$strSQL = $this->gQuery->gSQLWhere($strWhereClause);
		
		$strSQLbak = $strSQL;
		//	Before Query event
		if($this->eventsObject->exists("BeforeQueryEdit"))
			$this->eventsObject->BeforeQueryEdit($strSQL, $strWhereClause, $this);
		
		if($strSQLbak == $strSQL)
			$strSQL = $this->gQuery->gSQLWhere($strWhereClause);
			
		LogInfo($strSQL);
		
		$rs = db_query($strSQL, $conn);
		$this->data = $this->cipherer->DecryptFetchedArray($rs);
		
		if(!$this->data && $this->mode == EDIT_SIMPLE)
			return $this->data;
			
		foreach($this->editFields as $fName)
		{
			if(@$_POST["a"]!= "edited" && $this->pSet->isAutoUpdate($fName) && $this->pSet->getDefaultValue($fName) !== "")
				$this->data[$fName] = $this->pSet->getDefaultValue($fName);	
		}
		
		if($this->readEditValues)
		{
			foreach($this->editFields as $fName)
			{
				$editFormat = $this->pSet->getEditFormat($fName);
				if($editFormat == EDIT_FORMAT_DATABASE_FILE && $editFormat!=EDIT_FORMAT_DATABASE_IMAGE && $editFormat!=EDIT_FORMAT_FILE && !$this->pSet->isReadonly($fName))
					$this->data[$fName] = $this->editValues[$fName];
			}	
		}
		
		if($this->eventsObject->exists("ProcessValuesEdit"))
			$this->eventsObject->ProcessValuesEdit($this->data, $this);
		
		return $this->data;
	}
}
?>