<?php
include_once(getabspath("classes/runnerpage.php"));
class ViewPage extends RunnerPage
{
	var $keys = array();
	
	var $data = null;
	
	var $all = false;
	
	var $jsKeys = array();
	
	var $keyFields = array();
	
	function ViewPage(&$params)
	{
		parent::RunnerPage($params);
	}
	
	/**
	 * Set keys values
	 *
	 * @param {array} 
	 */	
	function setKeys($keys = array())
	{
		if(count($keys))
		{
			$this->keys = $keys;
			return;
		}
		$tKeys = $this->pSet->getTableKeys();
		for($i = 0; $i < count($tKeys); $i++)
		{
			$this->keys[$tKeys[$i]] = postvalue("editid".($i+1));
		}
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
			$viewFormat = $this->pSet->getViewFormat($fName);
			if($viewFormat == FORMAT_DATABASE_FILE || $viewFormat == FORMAT_DATABASE_IMAGE || $viewFormat == FORMAT_FILE_IMAGE)
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
		
		global $gstrOrderBy, $conn;
		$strWhereClause = '';
		$strHavingClause = '';
		if(!$this->all)
		{
			//	show one record only
			$this->setKeys();
			
			$strWhereClause = KeyWhere($this->keys);
					if($this->pSet->getAdvancedSecurityType()!=ADVSECURITY_ALL)
				$strWhereClause = whereAdd($strWhereClause, SecuritySQL("Search"));
			$strSQL = $this->gQuery->gSQLWhere($strWhereClause);
		}
		else
		{
			if ($_SESSION[$this->tName."_SelectedSQL"]!="" && @$_REQUEST["records"]=="") 
			{
				$strSQL = $_SESSION[$this->tName."_SelectedSQL"];
				$strWhereClause=@$_SESSION[$this->tName."_SelectedWhere"];
			}
			else
			{
				$strWhereClause = @$_SESSION[$this->tName."_where"];
				$strHavingClause = @$_SESSION[$this->tName."_having"];
				$strSearchCriteria = @$_SESSION[$this->tName."_criteria"];
							if($this->pSet->getAdvancedSecurityType()==ADVSECURITY_VIEW_OWN && $strWhereClause=="")
					$strWhereClause = whereAdd($strWhereClause, SecuritySQL("Search"));
				$strSQL = $this->gQuery->gSQLWhere($strWhereClause, $strHavingClause, $strSearchCriteria);
			}
		//	order by
			$strOrderBy = $_SESSION[$this->tName."_order"];
			if(!$strOrderBy)
				$strOrderBy = $gstrOrderBy;
			$strSQL.=" ".trim($strOrderBy);
		}
		
		$strSQLbak = $strSQL;
		if($this->eventsObject->exists("BeforeQueryView"))
			$this->eventsObject->BeforeQueryView($strSQL, $strWhereClause, $this);
		if($strSQLbak == $strSQL)
		{
			$strSQL = $this->gQuery->gSQLWhere($strWhereClause, $strHavingClause);
			if($this->all)
			{
				$numrows = $this->gQuery->gSQLRowCount($strWhereClause, $strHavingClause, $strSearchCriteria);
				$strSQL.=" ".trim($strOrderBy);
			}
		}
		else
		{	//	changed $strSQL - old style	
			if($this->all)
				$numrows = GetRowCount($strSQL);
		}
		
		if(!$this->all)
		{
			LogInfo($strSQL);
			$rs = db_query($strSQL, $conn);
		}
		else
		{
		// Pagination:
			$nPageSize = 0;
			if(@$_REQUEST["records"]=="page" && $numrows)
			{
				$mypage = (integer)@$_SESSION[$this->tName."_pagenumber"];
				$nPageSize = (integer)@$_SESSION[$this->tName."_pagesize"];
				if($numrows <= ($mypage-1)*$nPageSize)
					$mypage = ceil($numrows/$nPageSize);
				if(!$nPageSize)
					$nPageSize = $gPageSize;
				if(!$mypage)
					$mypage = 1;
					}
					$rs = db_query($strSQL,$conn);
		}
		
		$this->data = $this->cipherer->DecryptFetchedArray($rs);
		
			if(sizeof($this->data) && $this->eventsObject->exists("ProcessValuesView"))
			$this->eventsObject->ProcessValuesView($this->data, $this);
		
		return $this->data;
	}
}
?>