<?php
class OrderField
{
	/**
	 * Index of field in query
	 * @var {int}
	 */
	var $fieldIndex = null;
	/**
	 * @var {string}
	 */
	var $orderDirection = '';
	/**
	 * Indicator. True if this field selected for sorting by user
	 * @var {bool}
	 */
	var $userDefined = true;
	
	function OrderField($fieldIndex, $orderDirection = 'ASC', $userDefined = true)
	{
		$this->fieldIndex = $fieldIndex;
		$this->orderDirection = $orderDirection == "a" || $orderDirection == "ASC" ? "ASC" : "DESC";
		$this->userDefined = $userDefined;
	}
}
class OrderClause
{
	/**
	 * Array of fields engaged in sorting
	 * @var {array}
	 */
	var $fieldsList = array();
	/**
	 * Reference for instance of ListPage object
	 * @var {object}
	 */
	var $listObject = null;
	var $moveNext = true;
	
	function OrderClause($listObject)
	{
		$this->listObject = $listObject;
	}
	
	function hasFieldInList($fieldIndex)
	{
		for($i = 0; $i < count($this->fieldsList); $i++)
			if($this->fieldsList[$i]->fieldIndex == $fieldIndex)
				return $i;
		return false;
	}
	function addField($fieldIndex, $orderDirection, $userDefined = true)
	{
		$direction = $orderDirection == "a" ? "ASC" : "DESC";
		$fieldPosition = $this->hasFieldInList($fieldIndex);
		if($fieldPosition === false)
			$this->fieldsList[] = new OrderField($fieldIndex, $direction, $userDefined);
		else
		{
			if($userDefined){
				$this->fieldsList[$fieldPosition]->orderDirection = ($direction);
				$this->fieldsList[$fieldPosition]->userDefined = $userDefined;
			}
		}
	}
	function removeNonUserDefinedFields()
	{
		$newFieldsArray = array();
		for($i = 0; $i < count($this->fieldsList); $i++)
			if($this->fieldsList[$i]->userDefined)
				$newFieldsArray[] = $this->fieldsList[$i];
		$this->fieldsList = $newFieldsArray;
	}
	function init()
	{
		//Session field for sort
		if(@$_SESSION[$this->listObject->sessionPrefix."_orderFieldsList"]){
			$this->fieldsList = unserialize($_SESSION[$this->listObject->sessionPrefix."_orderFieldsList"]);
		}
	}
	/**
	 * Builde sorting order params
	 */
	function buildOrderParams() 
	{
		//orderlinkattrs for fields
		$this->listObject->orderLinksAttr();
		
		$this->init();

		if(!strlen($_SESSION[$this->listObject->sessionPrefix."_order"])) 
			$this->buildFieldsArrayForSortOrder();
		if(@$_SESSION[$this->listObject->sessionPrefix."_orderby"]) 
			$this->extractFieldsArrayForSortingFromSession();		
		
		$this->buildFieldLabels();
		//Shape sorting line for a request
		for($i = 0; $i < count($this->fieldsList); $i ++)
			$this->listObject->strOrderBy .= ($this->listObject->pSet->GetFieldByIndex($this->fieldsList[$i]->fieldIndex) 
				? ($this->listObject->strOrderBy != "" ? ", " : " ORDER BY ").$this->fieldsList[$i]->fieldIndex." "
					.$this->fieldsList[$i]->orderDirection: "");
		if($_SESSION[$this->listObject->sessionPrefix."_noNextPrev"] == 1)
			$this->listObject->strOrderBy = $this->listObject->gstrOrderBy;
			
		$_SESSION[$this->listObject->sessionPrefix."_orderFieldsList"] = serialize($this->fieldsList);
	}
	/**
	 * getKeyFieldsForSortOrder
	 * Return arrray of keyfields indexes for sort order
	 * return {array}
	 */
	function getKeyFieldsForSortOrder()
	{
		if(@$_SESSION[$this->listObject->sessionPrefix."_key"])
			$keys = $_SESSION[$this->listObject->sessionPrefix."_key"];
		else
		{
			$keys = array();
			$tKeys = $this->listObject->pSet->getTableKeys();
			for($i = 0; $i < count($tKeys); $i ++) 
			{
				if($this->listObject->pSet->getFieldIndex($tKeys[$i]))
					$keys[] = $this->listObject->pSet->getFieldIndex($tKeys[$i]);
			}
			$_SESSION[$this->listObject->sessionPrefix."_key"]= $keys;
		}
		return $keys;
	}
	/**
	 * @param {array} fields for sorting defined by user 
	 */
	function buildFieldsArrayForSortOrder()
	{
		$this->fieldsList = array();
		if(count($this->listObject->gOrderIndexes)) 
		{
			for($i = 0; $i < count($this->listObject->gOrderIndexes); $i ++) 
				$this->fieldsList[] = new OrderField($this->listObject->gOrderIndexes[$i][0]
					, $this->listObject->gOrderIndexes[$i][1], false);
		}
		elseif($this->listObject->gstrOrderBy != '')
			$_SESSION[$this->listObject->sessionPrefix."_noNextPrev"] = 1;
		$this->addKeyFieldsToSortOrder();			
	}
	/**
	 * extractFieldsArrayForSortingFromSession
	 */
	function extractFieldsArrayForSortingFromSession()
	{
		$order_field = $this->listObject->pSet->getFieldByGoodFieldName(substr($_SESSION[$this->listObject->sessionPrefix."_orderby"], 1));
		$order_dir = substr($_SESSION[$this->listObject->sessionPrefix."_orderby"], 0, 1);
		$order_ind = $this->listObject->pSet->getFieldIndex($order_field);
		if($order_ind) 
		{
			if(!@$_REQUEST["a"] && !@$_REQUEST["q"] && !@$_REQUEST["qs"] && !@$_REQUEST["goto"] && !@$_REQUEST["pagesize"]) 
			{
				if(@$_REQUEST["ctrl"]) 
				{
					$this->removeNonUserDefinedFields();
					$this->addField($order_ind, $order_dir);
					$this->addKeyFieldsToSortOrder();
				} 
				else
				{
					$this->fieldsList = array();
					if(!empty($_SESSION[$this->listObject->sessionPrefix."_orderNo"]))
						unset($_SESSION[$this->listObject->sessionPrefix."_orderNo"]);
					$_SESSION[$this->listObject->sessionPrefix."_noNextPrev"]= 0;
					$this->addField($order_ind, $order_dir);
					$this->addKeyFieldsToSortOrder();
				}
			}
		}
	}
	/**
	 * addKeyFieldsToSortOrder
	 * Add table keyfields to the array which contains fields for sort
	 */
	function addKeyFieldsToSortOrder()
	{
		$keys = $this->getKeyFieldsForSortOrder();
		if(count($keys) && $this->moveNext) 
		{
			for($i = 0; $i < count($keys); $i++)
			{ 
				$this->addField($keys[$i], 'a', false);
			}
		}
	}
	/**
	 * buildFieldLabels
	 * Draw arrows near field label if field engaged in sorting and build sorting links
	 */
	function buildFieldLabels()
	{
		for($i = 0; $i < count($this->fieldsList); $i ++) 
		{
			$order_field = $this->listObject->pSet->GetFieldByIndex($this->fieldsList[$i]->fieldIndex);
			$order_dir = $this->fieldsList[$i]->orderDirection == "ASC" ? "a" : "d";
			if($this->fieldsList[$i]->userDefined) 
				$this->listObject->xt->assign_section(GoodFieldName($order_field)."_fieldheader", ""
					, "<img ".($this->listObject->is508 ? "alt=\" \" " : "")."src=\"images/".($order_dir == "a" ? "up" : "down")
					.".gif\" border=0>");
			// default ASC for key fields	
			if(!$this->fieldsList[$i]->userDefined)
				$orderlinkattrs = $this->listObject->setLinksAttr(GoodFieldName($order_field));
			else
				$orderlinkattrs = $this->listObject->setLinksAttr(GoodFieldName($order_field), $order_dir);
			$this->listObject->xt->assign(GoodFieldName($order_field)."_orderlinkattrs", $orderlinkattrs);
		}
	}
	
}
?>