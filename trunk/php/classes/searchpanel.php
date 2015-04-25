<?php
/**
 * Base class for all search control builders
 *
 */
class SearchPanel {
	/**
	 * strTableName of searchPanel's table
	 *
	 * @var string
	 */
	var $tName = '';
	var $pSet = null;
	var $dispNoneStyle = 'style="display: none;"';
	/**
	 * Object of page for output. Used for call xt methods for current page
	 *
	 * @var object
	 */
	var $pageObj = null;
	/**
	 * Object of searchClause class.
	 *
	 * @var object
	 */
	var $searchClauseObj = null;
	/**
	 * Object of PanelSearchControl class.
	 *
	 * @var object
	 */
	var $searchControlBuilder = null;
	/**
	 * Panel id
	 *
	 * @var int
	 */
	var $id = 1;
	/**
	 * Array of panel state parametres, such as open|close menu etc.
	 *
	 * @var array
	 */
	var $panelState = array();
	/**
	 * Fields that used for search on panel, and should be open on every page load
	 *
	 * @var array
	 */
	var $panelSearchFields = array();
	/**
	 * Array of key's fields
	 *
	 * @var array
	 */
	
	var $allSearchFields = array();
	/**
	 * Indicator use suggest or not
	 *
	 * @var bool
	 */
	var $isUseAjaxSuggest = false;
	/**
	 * Permissions for search
	 *
	 * @var bool
	 */
	var $searchPerm = false;
	/**
	 * Constructor, accepts array of parametres, which will be copied to object properties by link
	 *
	 * @param array $params
	 * @return SearchPanel
	 */
	function SearchPanel(&$params)
	{
		// copy properties to object
		RunnerApply($this, $params);
		
		$this->searchClauseObj = &$this->pageObj->searchClauseObj;	
		
		$this->id = $this->pageObj->id;
		$this->tName = $this->pageObj->tName;
		$this->pSet = new ProjectSettings($this->tName, PAGE_SEARCH);
		$this->panelState = $this->searchClauseObj->getSrchPanelAttrs();
		$this->isUseAjaxSuggest = $this->pSet->isUseAjaxSuggest();
		
		$this->searchControlBuilder = new PanelSearchControl($this->id, $this->tName, $this->searchClauseObj, $this->pageObj);	
		
		// get search permissions if not passed to constructor
		if (!isset($params['searchPerm'])){
			$this->searchPerm = $this->getSearchPerm();
		}
		// get search fields if not passed to contructor
		if (!isset($params['panelSearchFields'])){
			$this->panelSearchFields = $this->pSet->getPanelSearchFields();	
		}
		if (!isset($params['allSearchFields'])){
			$this->allSearchFields = $this->pSet->getAllSearchFields();
		}
	}	
	
	function getSearchPerm($tName = "")
	{
		global $isGroupSecurity;
		$tName = $tName ? $tName : $this->tName;
		if (!$isGroupSecurity){
			return true;
		}else{
			$strPerm = GetUserPermissions($tName);
			return (strpos($strPerm, "S") !== false);
		}
	}
	
	/**
	 * Main method, call to build search panel
	 *
	 */
	function buildSearchPanel() 
	{
		$srchPanelAttrs = $this->searchClauseObj->getSrchPanelAttrs();
		$this->searchAssign();
	}
	
	function searchAssign() 
	{
		$this->pageObj->xt->assign("asearch_link", $this->searchPerm);
		$this->pageObj->xt->assign("asearchlink_attrs", "id=\"asearch_".$this->id."\" name=\"asearch_".$this->id."\" href=\"".$this->pageObj->shortTableName."_search.php\" onclick=\"window.location.href='".$this->pageObj->shortTableName."_search.php';return false;\"");
		
		if(isEnableSection508() && $this->searchPerm)
		{
			$searchPerm=array();
			$searchPerm["begin"]="<a name=\"skipsearch\"></a>";
		}
		else
			$searchPerm=$this->searchPerm;
		
		$this->pageObj->xt->assign("searchform_block", $searchPerm);
		if (isMobile()){
			$this->pageObj->xt->assign("searchformmobile_block", $searchPerm);
		}
		$this->pageObj->xt->assign("searchformbuttons_block",$searchPerm);
		
		$this->pageObj->xt->assign("searchform_text", true);
		$this->pageObj->xt->assign("searchform_search", true);
		
		if (isMobile()){
			$this->pageObj->xt->assign("searchform_showall_mobile", true);
		}
		else {
			$this->pageObj->xt->assign("searchform_showall", true);
		}
		
		if (!$this->searchClauseObj->isUsedSrch()){
			$this->pageObj->xt->assign("showAllCont_attrs", 'style="display: none;"');
		}
		
		$srchButtTitle = "Buscar"; 
		
		$this->pageObj->xt->assign("searchbutton_attrs", "id=\"searchButtTop".$this->id."\"  title=\"".$srchButtTitle.'"');
		$this->pageObj->xt->assign("showallbutton_attrs", "id=\"showAll".$this->id."\"");
	}
}

?>