<?php
/**
 * Search panel builder for LIST_LOOKUP mode
 *
 */
class SearchPanelLookup extends SearchPanel {

	function SearchPanelLookup(&$params) 
	{
		parent::SearchPanel($params);
	}
	
	function searchAssign()
	{
		parent::searchAssign();
		
		$valSrchFor = "Buscar";
		$searchforAttrs = "";
		$searchforAttrs .= ' tip="'.$valSrchFor.'"';
				
		$searchGlobalParams = $this->searchClauseObj->getSearchGlobalParams();
		if($this->searchClauseObj->isUsedSrch())
			$valSrchFor = $searchGlobalParams["simpleSrch"];
		else
			$searchforAttrs .= ' class="ctlSearchTip"';
		
		$searchforAttrs .= " size=\"15\" name=\"ctlSearchFor".$this->id."\" id=\"ctlSearchFor".$this->id."\"";
		$searchforAttrs .= " value=\"".htmlspecialchars($valSrchFor)."\"";
		
		$this->pageObj->xt->assign("searchfor_attrs", $searchforAttrs);
	}
}

?>