<?php

class ListPage_DPInline extends ListPage_Embed
{
	/**
	 * DP params
	 *
	 * @var string
	 */
	var $dpParams = "";
	/**
	 * Array of details preview master key
	 *
	 * @var integer
	 */
	var $dpMasterKey = array ();
	/**
	 * Short name of master table
	 *
	 * @var string
	 */
	var $masterShortTable = "";
	/**
	 * Master's form name
	 *
	 * @var string
	 */	
	var $masterFormName = "";
	/**
	 * Master's id use only for dpInline on list page
	 * (don't confuse with dpInline on add edit pages)
	 * @var string
	 */
	var $masterId = "";
	/**
	 * Constructor, set initial params
	 *
	 * @param array $params
	 */
	function ListPage_DPInline(&$params)
	{
		// copy properties to object
		//RunnerApply($this, $params);
		// call parent constructor
		parent::ListPage_Embed($params);
		$this->initDPInlineParams();
		$this->searchClauseObj->clearSearch();
		
		$this->jsSettings['tableSettings'][$this->tName]['mainMPageType'] = $this->mainMasterPageType;
		$this->jsSettings['tableSettings'][$this->tName]['masterPageType'] = $this->masterPageType;
		$this->jsSettings['tableSettings'][$this->tName]['masterTable'] = $this->masterTable;
		$this->jsSettings['tableSettings'][$this->tName]['firstTime'] = $this->firstTime;
		$this->jsSettings['tableSettings'][$this->tName]['strKey'] = $this->getStrMasterKey();
	}
	
	/**
      * Assigne Import Links or not
      *
	  * @return boolean
      */
	function importLinksAttrs() 
	{
		return true;
	}
	/**
      * Display master table info or not
      *
	  * @return boolean
      */
	function displayMasterTableInfo() 
	{
		return true;
	}
	/**
      * Process master key value
      * Set master key for create DPInline params
	  */
	function processMasterKeyValue() 
	{
		parent::processMasterKeyValue();
		for($i=1;$i<=count($this->masterKeysReq);$i++)
			$this->dpMasterKey[] = $this->masterKeysReq[$i];
	}
	/**
      * Initialization DPInline params
      * 
      */
	function initDPInlineParams()
	{
		$strkey="";
		for($i=0;$i<count($this->dpMasterKey);$i++)
			$strkey.="&masterkey".($i+1)."=".rawurlencode($this->dpMasterKey[$i]);
		$this->dpParams = "mode=listdetails&id=".$this->id."&mastertable=".rawurlencode($this->masterTable).$strkey.
							($this->masterId ? "&masterid=".$this->masterId : "").
							(($this->masterPageType==PAGE_EDIT || $this->masterPageType==PAGE_VIEW) ? "&masterpagetype=".$this->masterPageType : "").
							(($this->mainMasterPageType==PAGE_VIEW) ? "&mainmasterpagetype=".$this->mainMasterPageType : "");
	}
	/**
	 * Get string of master keys for dpInline on Edit page
	 */
	function getStrMasterKey()
	{
		$strkey = array();
		for($i=0;$i<count($this->dpMasterKey);$i++)
			$strkey[$i] = $this->dpMasterKey[$i];
		return $strkey;	
	}
	
	/**
	 * Set order links attribute for order on list page
	 *
	 * @param {string} $field - name field, which is ordering
	 * @param {string} $sort - how is filed ordering, "a" - asc or "d" - desc, default is "a"
	 */
	function setLinksAttr($field,$sort="")
	{
		if($this->masterPageType!=PAGE_ADD)
		{
			return parent::setLinksAttr($field,$sort);
		}
	}
		
	/**
	 * show inline add link
	 * Add inline add attributes
	 */
	function inlineAddLinksAttrs()
	{
		//inline add link and attr
		if($this->masterPageType!=PAGE_VIEW && $this->mainMasterPageType!=PAGE_VIEW)
			parent::inlineAddLinksAttrs();
	}
	
	/**
	 * Add common assign for current mode
	 *
	 */
	function commonAssign()
	{
		parent::commonAssign();
		
		$this->xt->assign("left_block", false);
		//select all link and attr	
		if($this->masterPageType==PAGE_ADD || $this->masterPageType==PAGE_VIEW || $this->mainMasterPageType==PAGE_VIEW)
		{
			$this->xt->assign("selectall_link",false);
			$this->xt->assign("checkbox_column",false);
			$this->xt->assign("checkbox_header",false);
			$this->xt->assign("editselected_link",false);
			$this->xt->assign("delete_link",false);
			$this->xt->assign("saveall_link",false);
			if($this->masterPageType==PAGE_VIEW || $this->mainMasterPageType==PAGE_VIEW)
				$this->xt->assign("record_controls_block",false);
		}
		else{
			//selectall link attrs
			$this->selectAllLinkAttrs();
			
			//checkbox column
			$this->checkboxColumnAttrs();
			
			//edit selected link and attr
			$this->editSelectedLinkAttrs();	
			
			//save all link, attr, span
			$this->saveAllLinkAttrs();
			
			//delete link and attr
			$this->deleteSelectedLink();
			
			if($this->masterPageType!=PAGE_EDIT)
			{
				$searchPermis = $this->permis[$this->tName]['search'];
				$this->xt->assign("record_controls_block",$this->permis[$this->tName]['edit']);
				$this->xt->assign("details_block", $searchPermis && $this->rowsFound );
				$this->xt->assign("details_attrs","id=\"detFound".$this->id."\" name=\"detFound".$this->id."\"");
				$this->xt->assign("pages_block", $searchPermis && $this->rowsFound );
			}
		}
		
		if($this->masterPageType!=PAGE_VIEW && $this->mainMasterPageType!=PAGE_VIEW)
		{
			//inline edit column
			$editPermis = $this->permis[$this->tName]['edit'];
			$this->xt->assign("inlineedit_column",$editPermis);
			
			//for list icons instead of list links
			$this->assignListIconsColumn($editPermis);
					
			//cancel all link, attr, span
			$this->cancelAllLinkAttrs();
		}
		
		for($i=0;$i<count($this->allDetailsTablesArr);$i++)
		{
			$permis = ($this->isGroupSecurity && ($this->permis[$this->allDetailsTablesArr[$i]['dDataSourceTable']]['add'] || $this->permis[$this->allDetailsTablesArr[$i]['dDataSourceTable']]['search'])) || (!$this->isGroupSecurity);	
			if($permis)
			{
				$this->xt->assign(GoodFieldName($this->tName)."_dtable_column", $permis);
				break;
			}
		}
	}
		
	/**
	 * Final build page
	  *
	  */
	function prepareForBuildPage() 
	{	
		//orderlinkattrs for fields
		$this->orderLinksAttr();
		
		//Sorting fields
		if($this->masterPageType!=PAGE_ADD)
			$this->orderClause->buildOrderParams();
		
		// delete record
		$this->deleteRecords();
		
		// build sql query
		$this->buildSQL();
		
		// build pagination block
		$this->buildPagination();
		
		// seek page must be executed after build pagination
		$this->seekPageInRecSet($this->querySQL);
		
		$this->setGoogleMapsParams($this->listFields);
		
		// fill grid data
		$this->fillGridData();
		
		// add common js code
		$this->addCommonJs();
		
		// add common html code
		$this->addCommonHtml();
		
		// Set common assign
		$this->commonAssign();
	}
	
	/**
	 * Display blocks after loaded template of page
	 *
	 */
	function displayAfterLoadTempl() 
	{
		return parent::displayAfterLoadTempl().$this->xt->fetch_loaded("body");
	}
	
	/**
	 * Show page method
	 *
	 */
	function showPage($returnJson = true)
	{
		global $page_layouts;
		$layout =& $page_layouts[$this->shortTableName.'_'.$this->pageType];
		$pageSkinStyle = $layout->style." page-".$layout->name;
		
				
		$this->BeforeShowList();
		
		//set bricks, which	must be shown on details preview page
		$bricksExcept = array("grid","pagination");
		if($this->masterPageType == PAGE_LIST){	
			$bricksExcept[] = "details_found";
			$bricksExcept[] = "page_of";
			if ($this->deleteMessage != '')
				$bricksExcept[] = "message";
		}
		if($this->masterPageType==PAGE_EDIT 
			|| $this->masterPageType==PAGE_ADD 
			|| $this->masterPageType == PAGE_LIST && $this->mainMasterPageType != PAGE_VIEW)
		{
			if ($this->pSet->hasInlineEdit() || $this->pSet->hasDelete() && $this->masterPageType != PAGE_ADD) {
				if ($this->permis[$this->tName]['edit'] || $this->permis[$this->tName]['delete']){
					$bricksExcept[] = "recordcontrol";
				}
			}

			if ($this->pSet->hasInlineAdd() && $this->permis[$this->tName]['add']){
				$bricksExcept[] = "recordcontrols_new";
			}
		}
		
		// if we use details inline. We don't need show the header/footer.
		$this->xt->unassign('header');
		$this->xt->unassign('footer');
		
		$this->xt->hideAllBricksExcept($bricksExcept);
		
		$this->xt->prepare_template($this->templatefile);
		$contents = $this->displayAfterLoadTempl();	
		
		if (!$returnJson){
			if(GetGlobalData("printDetailTableName", true))
			{
				echo'<br><div id="dpShowHide'.$this->id.'" class="dpDiv">';
				if(postvalue("pdf") != 1)
					echo '<img id="dpMinus'.$this->id.'" class="dpImg" border="0" src="images/minus.gif" valign="middle" alt="*" />';
				echo '<a name="dt'.$this->id.'" class="dt">'.$this->strCaption.'</a></div>';
			}		
			echo '<div id="detailPreview'.$this->id.'" class="'.$pageSkinStyle.' runner-pagewrapper dpStyle">'.$contents.'</div>';
			return;
		}
		//add for details preview page skin and style
				
		$respArr = array();
		// add cMap, sett
		$this->addControlsJSAndCSS();
		$this->fillSetCntrlMaps();
		$respArr['controlsMap'] = $this->controlsHTMLMap;
		$respArr['viewControlsMap'] = $this->viewControlsHTMLMap;
		$respArr['settings'] = $this->jsSettings;	
		$respArr['html'] = $contents;
		$respArr['success'] = true;
		$respArr['id'] = $this->id;
		$respArr['idStartFrom'] = $this->flyId;
		$respArr['delRecs'] = $this->recordsDeleted;
		if ($this->deleteMessage != '')
			$respArr['delMess'] = true;
		
		$respArr["additionalJS"] = $this->grabAllJsFiles();
		$respArr["additionalCSS"] = $this->grabAllCSSFiles();
		
		echo '<textarea>'.htmlspecialchars(my_json_encode($respArr)).'</textarea>';
	}

	// build checkbox if it's possible
	function fillCheckAttr(&$record, $data, $keyblock)
	{
		$record["checkbox"] = $this->permis[$this->tName]['edit'] && $this->isUseInlineEdit && $this->pSet->hasInlineEdit();
		if($this->deleteRecs && $this->permis[$this->tName]['delete'] && $this->pSet->hasDelete()) 
			$record["checkbox"] = true;
		$record["checkbox_attrs"]= "name=\"selection[]\" value=\"".htmlspecialchars($keyblock)."\" id=\"check".$this->id."_".$this->recId."\"";
	}
	
	/**
	 * Assign checkbox column, header and header attrs
	 * 
	 */
	function checkboxColumnAttrs()
	{
		$this->xt->assign("checkbox_column", $this->permis[$this->tName]['delete'] && $this->pSet->hasDelete()
			|| $this->permis[$this->tName]['edit'] && $this->isUseInlineEdit && $this->pSet->hasInlineEdit());
		$this->xt->assign("checkbox_header", true);
		$this->xt->assign("checkboxheader_attrs", "id=\"chooseAll_".$this->id."\" class=\"chooseAll".$this->id."\"");
	}
}
?>
