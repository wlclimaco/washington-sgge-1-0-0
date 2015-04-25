<?php
/**
 * Class for list page with mode simple
 *
 */
class ListPage_Simple extends ListPage 
{
	/**
	 * Constructor, set initial params
	 *
	 * @param array $params
	 */	
	function ListPage_Simple(&$params) 
	{
		// call parent constructor
		parent::ListPage ($params);	
		$this->pSetEdit = new ProjectSettings($this->tName, PAGE_SEARCH);
	}
	/**
	 * Add common assign for simple mode on list page
	 */	
	function commonAssign() 
	{
		parent::commonAssign();
		
		//search permissions
		$searchPermis = $this->permis[$this->tName]['search'];
		//export permissions
		$exportPermis = $this->permis[$this->tName]['export'];
		
		//add edit delete print export links 
		$this->xt->assign("record_controls_block", ($this->permis[$this->tName]['export'] || $this->permis[$this->tName]['add'] || $this->permis[$this->tName]['delete'] || $this->permis[$this->tName]['edit']));
		
		// adds style displat none to hiderecord controls, edit selected, delete selected, export selected and print selected if found 0 recs
		$this->xt->assign("details_block", $searchPermis && $this->rowsFound );
		
		if(($searchPermis && count($this->arrRecsPerPage)) && ($this->rowsFound || $this->listAjax==LIST_AJAX))
		{
			$this->xt->assign("recordspp_block", true);
			$this->createPerPage();
		}	
				
		$this->xt->assign("pages_block", $searchPermis && $this->rowsFound );
		$this->xt->assign("pages_attrs","id=\"pageOf".$this->id."\" name=\"pageOf".$this->id."\"");
		
		$this->xt->assign("shiftstyle_block", true);
		$this->xt->assign("left_block", true);
		//$this->xt->assign("toplinks_block", true);
		
		//export selected link and attr
		$this->xt->assign("exportselected_link", $exportPermis);
		$this->xt->assign("exportselectedlink_span", $this->buttonShowHideStyle());
		$this->xt->assign("exportselectedlink_attrs", $this->getPrintExportLinkAttrs('export'));
		
		// print links and attrs
		$this->xt->assign("prints_block", $exportPermis);
		$this->xt->assign("print_link", $exportPermis);
		$this->xt->assign("printlink_attrs", "id='print_".$this->id."' name='print_".$this->id."'");
		
		//print selected link and attr
		$this->xt->assign("printselected_link", $exportPermis);
		$this->xt->assign("printselectedlink_attrs", $this->getPrintExportLinkAttrs('print'));
		$this->xt->assign("printselectedlink_span", $this->buttonShowHideStyle());
		
		
		//print all link and attr
		$this->xt->assign("printall_link", $exportPermis);
		$this->xt->assign("printalllink_attrs","id='printAll_".$this->id."' name='printAll_".$this->id."'");
		
		//export link and attr
		$this->xt->assign("export_link", $exportPermis);
		$this->xt->assign("exportlink_attrs", 
						  "id = 'export_".$this->id."'
						   name = 'export_".$this->id."'
						   onclick=\"window.open(this.href,'wExport');return false;\"");
		
		//add link and attr
		$this->xt->assign("add_link", $this->permis[$this->tName]['add']);
		$this->xt->assign("addlink_attrs", "href='".$this->shortTableName."_add.php' id=\"addButton".$this->id."\"");
		
		//advanced search and attr
		$this->xt->assign("advsearchlink_attrs", "id=\"advButton".$this->id."\"");
		
		//select all link and attr
		$this->selectAllLinkAttrs();	
		
		//edit selected link and attr	
		$this->editSelectedLinkAttrs();		
		
		//save all link, attr, span	
		$this->saveAllLinkAttrs();
		
		//cansel all link, attr, span	
		$this->cancelAllLinkAttrs();
		
		$this->xt->assign("grid_block", true);
		
		$this->xt->assign('menu_block', $this->isShowMenu() || $this->isAdminTable());
		$this->xt->assign("languages_block",true);
		
		if (isMobile()){
			$this->xt->assign('morelinkmobile_block', true);
			$this->xt->assign('tableinfomobile_block', true);
			$this->xt->displayBrickHidden("vmsearch2");
		}
		
	}
	/**
	 * Simple assign for grid block
	 */
	function addAssignForGrid()
	{
		parent::addAssignForGrid();
		
		//edit permissions
		$editPermis = $this->permis[$this->tName]['edit'];
		//add permissions
		$addPermis = $this->permis[$this->tName]['add'];
		//search permissions
		$searchPermis = $this->permis[$this->tName]['search'];
		
		//checkbox column				
		$this->checkboxColumnAttrs();
		
		//edit column
		$this->xt->assign("edit_column", $editPermis);
		$this->xt->assign("edit_headercolumn", $editPermis);
		$this->xt->assign("edit_footercolumn", $editPermis);
		
		//inline edit column	
		$this->xt->assign("inlineedit_column", $editPermis);
		$this->xt->assign("inlineedit_headercolumn", $editPermis);
		$this->xt->assign("inlineedit_footercolumn", $editPermis);
		
		//copy link
		$this->xt->assign("copy_column", $addPermis);
				
		//view column	
		$this->xt->assign("view_column", $searchPermis);
		
		//for list icons instead of list links
		$this->assignListIconsColumn($editPermis, $addPermis, $searchPermis);
		for($i = 0; $i < count($this->allDetailsTablesArr); $i ++) 
		{
			$permis =($this->permis[$this->allDetailsTablesArr[$i]['dDataSourceTable']]['add'] || $this->permis[$this->allDetailsTablesArr[$i]['dDataSourceTable']]['search']);
			if($permis)
			{
				$this->xt->assign(GoodFieldName($this->tName)."_dtable_column", $permis);
				break;
			}
		}
			
		//delete link and attr
		$this->deleteSelectedLink();
	}	
		
	/**
	 * Add common html code for simple mode on list page
	 */	
	function addCommonHtml() 
	{
		$this->body ["begin"] .= "<script type=\"text/javascript\" src=\"include/loadfirst.js\"></script>";
				$this->body["begin"] .= "<script type=\"text/javascript\" src=\"include/lang/".getLangFileName(mlang_getcurrentlang()).".js\"></script>";
		
		if ($this->isDisplayLoading)
			$this->body["begin"] .= "<script type=\"text/javascript\">Runner.runLoading();</script>"; 
		
		//$this->AddJSFile("include/customlabels.js");
		
		//add parent common html code
		parent::addCommonHtml();
		
		// assign body end
		$this->body['end'] = array();
		$this->body['end']["method"] = "assignBodyEnd";		
		$this->body['end']["object"] = &$this;	
	}
	
	function buildSearchPanel($xtVarName) 
	{
		$this->xt->enable_section("searchPanel");
		$params = array();
		$params['pageObj'] = &$this;
		$params['panelSearchFields'] = $this->panelSearchFields;
		$panelSearchFields = array();
		$allSearchFields = $this->pSet->getAllSearchFields();
		
		for ($i=0; $i<count($allSearchFields); $i++){
			if (!$this->matchWithDetailKeys($allSearchFields[$i])){
				$panelSearchFields[] = $allSearchFields[$i];
			}
		}
				
		$params['allSearchFields'] = $panelSearchFields;
		
		$this->searchPanel = new SearchPanelSimple($params);
		$this->searchPanel->buildSearchPanel($xtVarName);
	}	
}


?>
