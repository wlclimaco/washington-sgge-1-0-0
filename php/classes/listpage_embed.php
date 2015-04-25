<?php

class ListPage_Embed extends ListPage
{
	/**
	 * Which type of master page was called detail table
	 *
	 * @var string
	 */
	var $masterPageType = "";
	/**
	 * Which type of main master page was called detail table
	 *
	 * @var string
	 */
	var $mainMasterPageType = "";
	/**
	 * View PDF on view page or not
	 *
	 * @var integer
	 */
	var $viewPDF = 0;
	/**
	 * Constructor, set initial params
	 *
	 * @param array $params
	 */
	function ListPage_Embed(&$params)
	{
		// copy properties to object
		//RunnerApply($this, $params);
		// call parent constructor
		parent::ListPage($params);
	}
	/**
	 * Add common html code for curent mode
	 *
	 */
	function addCommonHtml()
	{
		parent::addCommonHtml();
		$this->xt->assign("footer","");
	}
	
	
	/**
	 * Add common assign for simple mode on list page
	 */	
	function commonAssign() 
	{
		parent::commonAssign();	
		if ($this->isDispGrid())
		{
			$this->xt->assign_section ("grid_block", '', '');
			if(!$this->rowsFound)
				$this->xt->displayBrickHidden("grid");
		}
	}	
	
	/**
      * Display blocks after loaded template of page
      *
      */
	function displayAfterLoadTempl() 
	{
		return $this->xt->fetch_loaded("style_block").$this->xt->fetch_loaded("iestyle_block");
	}
	
	function addWhereWithMasterTable() 
	{
		if($this->masterPageType==PAGE_ADD)
			return "1=0";
		return ListPage::addWhereWithMasterTable();
	}
	
}
?>
