<?php
/**
 * Class for display admin_rights_list  
 *
 */
class RightsPage extends ListPage  
{
	/**
	 * Array of non admin tables
	 *
	 * @var array
	 */
	var $nonAdminTablesArr = array();
	/**
	 * Array of non admin tables rights
	 *
	 * @var array
	 */
	var $nonAdminTablesRightsArr = array();
	/**
	 * Array with groups data from DB
	 *
	 * @var array
	 */
	var $groupsArr = array();
	/**
	 * Array of smarty groups
	 *
	 * @var array
	 */
	var $smartyGroups = array();
	/**
	 * Array with checkboxes prefixes and access masks
	 * @var array
	 */
	var $cbxNames;
	
	/**
	 * Contructor
	 *
	 * @param array $params
	 * @return RightsPage
	 */
	function RightsPage(&$params)
	{
	
		$this->cbxNames = array('add' => array('mask' => 'A', 'rightName' => 'add')
		, 'edt' => array('mask' => 'E', 'rightName' => 'edit')
		, 'del' => array('mask' => 'D', 'rightName' => 'delete')
		, 'lst' => array('mask' => 'S', 'rightName' => 'list')
		, 'exp' => array('mask' => 'P', 'rightName' => 'export')
		, 'imp' => array('mask' => 'I', 'rightName' => 'import')
		, 'adm' => array('mask' => 'M'));
		
		// copy properties to object
		//RunnerApply($this, $params);
		parent::RunnerPage($params);
		
		//fill session variables
		$this->setSessionVariables();
		
		// Set language params, if have more than one language
		$this->setLangParams();
		
		// get permissions
		$this->permis[$this->tName]= $this->getPermissions();
		
		$this->is508 = isEnableSection508();
		
		$this->DPOrderTables($this->nonAdminTablesArr);
		
		$this->fillGroupsArr();
	}
	
	function fillGroupsArr(){
		//	select groups list
		$this->groupsArr[] = array(-1, "<"."Admin".">");
		$this->groupsArr[] = array(-2, "<"."Padrão".">");
		$this->groupsArr[] = array(-3, "<"."Visitante".">");
		
		$trs = db_query("select , from uggroups order by ", $this->conn);
		
		while($tdata = db_fetch_numarray($trs))
		{
			$this->groupsArr[] = array($tdata[0],$tdata[1]);
		}
	}
	/**
	 * Fill and prepare rights array
	 * Call it only after save new data, for get fresh data
	 *
	 */
	function fillSmartyAndRights() 
	{
		foreach($this->groupsArr as $g)
		{
			$sg = array();
			$sg["group_attrs"] = "value=\"".$g[0]."\"";
			$sg["groupname"] = htmlspecialchars($g[1]);
			if($g[0] == $_SESSION["hgroup"])
				$sg["group_attrs"].=" selected";
			$this->smartyGroups[] = $sg;
			foreach($this->nonAdminTablesArr as $t)
				$this->nonAdminTablesRightsArr[$t[0]][$g[0]] = "";
		}
	}
	/**
	 * Fill rights array
	 * Call it only after save new data, for get fresh data
	 *
	 */
	function getRights() 
	{
		$trs = db_query("select ,, from ugrights order by ", $this->conn);
		while($tdata = db_fetch_numarray($trs))
		{
			if(!array_key_exists($tdata[1],$this->nonAdminTablesRightsArr))
				continue;
			if(!array_key_exists((int)$tdata[0],$this->nonAdminTablesRightsArr[$tdata[1]]))
				continue;
			$this->nonAdminTablesRightsArr[$tdata[1]][$tdata[0]] = $tdata[2];
		}
	}
	
	/**
	 * Prepare JS arrays with groups and tables data
	 *
	 */
	function addJsGroupsAndRights() 
	{
		$this->jsSettings['tableSettings'][$this->tName]['rightsTables'] = array();
		$this->jsSettings['tableSettings'][$this->tName]['rightsGroups'] = array();
		
		foreach($this->groupsArr as $grp)
			$this->jsSettings['tableSettings'][$this->tName]['rightsGroups'][] = $grp[0];
		
		foreach($this->nonAdminTablesArr as $tbl)
			$this->jsSettings['tableSettings'][$this->tName]['rightsTables'][] = htmlspecialchars($tbl[0]);
	}
	
	function commonAssign() 
	{
		$this->xt->assign_loopsection("groups", $this->smartyGroups);
		
		parent::commonAssign();
		
		// assign headcheckboxes
		$this->xt->assign("add_headcheckbox","id=\"cbadd\"");
		$this->xt->assign("edt_headcheckbox","id=\"cbedt\"");
		$this->xt->assign("del_headcheckbox","id=\"cbdel\"");
		$this->xt->assign("lst_headcheckbox","id=\"cblst\"");
		$this->xt->assign("exp_headcheckbox","id=\"cbexp\"");
		$this->xt->assign("imp_headcheckbox","id=\"cbimp\"");
		$this->xt->assign("adm_headcheckbox","id=\"cbadm\"");
		
		// assign attrs
		$this->xt->assign("addgroup_attrs", "id=\"addGroupBtn\"");
		$this->xt->assign("delgroup_attrs", "id=\"delGroupBtn\"");
		$this->xt->assign("rengroup_attrs", "id=\"renGroupBtn\"");
		$this->xt->assign("savegroup_attrs", "id=\"saveGroupBtn\"");
		$this->xt->assign("savebutton_attrs", "id=\"saveBtn\"");
		$this->xt->assign("resetbutton_attrs", "id=\"resetBtn\"");
		$this->xt->assign("cancelgroup_attrs", "id=\"cancelBtn\"");
				
		// assign blocks
		$this->xt->assign("grid_block", true);
		$this->xt->assign("menu_block", true);
		$this->xt->assign("left_block", true);
		$this->xt->assign("rights_block", true);
		$this->xt->assign("message_block", true);
		$this->xt->assign("security_block", true);
		$this->xt->assign("logoutbutton",isSingleSign());
		$this->xt->assign("shiftstyle_block", true);
		$this->xt->assign("savebuttons_block", true);
		$this->xt->assign("search_records_block", true);
		$this->xt->assign("recordcontrols_block", true);
		
		// assign user settings
		$this->xt->assign("username", htmlspecialchars($_SESSION["UserName"]));
		if ($this->createLoginPage)
			$this->xt->assign("userid", htmlspecialchars($_SESSION["UserID"]));
		
		$this->xt->displayBrickHidden("message");
	}
	/**
	 * Sort tables array
	 *
	 * @param unknown_type $tables
	 */
	function DPOrderTables(&$tables)
	{
		sortTables($tables);
	}
	
	/**
	 * Fills info in array about grid.
	 *
	 * @param array $rowInfoArr array with total info, that assignes grid
	 */
	function fillGridShowInfo(&$rowInfoArr) 
	{
		//	fill $rowInfoArr array
		global $pageRights;
		$rowInfoArr = array();
		$rowClass = false;
		$recno = 1;
		$editlink = "";
		$copylink = "";
	
		foreach($this->nonAdminTablesArr as $tkey => $tbl)
		{
			$row = array();
			//$row["begin"] = "<input type=hidden name=\"table[]\" value=\"".htmlspecialchars($tbl[0])."\">";
			if($tbl[0] == $tbl[1])
				$row["tablename"] = htmlspecialchars($tbl[0]);
			else
				$row["tablename"] = "<span dir='LTR'>".htmlspecialchars($tbl[1])."&nbsp;(".htmlspecialchars($tbl[0]).")</span>";
				
			$row["tablecheckbox_attrs"]="name=\"table_".$tkey."\" id=\"".$tkey."\"";
			
			$row["rowclass"] = "";
			if(!$rowClass) 
			{
				$row["rowclass"] .= "interlaced";
				$rowClass = true;
			} 
			else 
				$rowClass = false;
			
			$sgroups = array();
			foreach($this->groupsArr as $g)
			{
				$group = array();
				$mask = $this->nonAdminTablesRightsArr[$tbl[0]][$g[0]];
				// add display none style if group not Admin, because at page load, admin rights are shown
				$styleDispNone = $g[0] == -1 ? "" : ' style="display: none;" ';
				
				foreach ($this->cbxNames as $key => $val)
					$group[$key."_checkbox"] = $styleDispNone.mysprintf(' id="%s" %s name="%s"'
						, array("cb".$key."_".$tkey."_".$g[0]
							, ((strpos($mask, $val['mask']) !== FALSE)) ? " checked" : ""
							, "cb".$key."_".$tkey."_".$g[0]));
				
				$sgroups[] = $group;
			}
			$row["add_groupboxes"] = array("data" => $sgroups);
			foreach ($this->cbxNames as $key => $val)
			{			
				if($key != 'add')
					$row[$key."_groupboxes"] = &$row["add_groupboxes"];
				if($key != 'adm')
					$row[$key."_group"] = $pageRights[$tbl[0]][$val['rightName']];
			}
			
			$rowInfoArr[] = $row;
		}
		
	}
	/**
	 * Fill premissions grid
	 *
	 */
	function fillGridData() 
	{
		//	fill $rowinfo array
		$rowInfo = array();
		$this->fillGridShowInfo($rowInfo);
		$this->xt->assign_loopsection("grid_row", $rowInfo);
	}
	/**
	 * Fill session vars, override parent
	 *
	 */
	function setSessionVariables() 
	{
		if(@$_REQUEST["orderby"])
			$_SESSION[$strTableName."_orderby"] = @$_REQUEST["orderby"];
		
		if(@$_REQUEST["pagesize"])
		{
			$_SESSION[$strTableName."_pagesize"] = @$_REQUEST["pagesize"];
			$_SESSION[$strTableName."_pagenumber"] = 1;
		}
		
		if(@$_REQUEST["goto"])
			$_SESSION[$strTableName."_pagenumber"] = @$_REQUEST["goto"];
	}
	
	/**
	 * Main function, call to build page
	 * Do not change methods call oreder!!
	 *
	 */
	function prepareForBuildPage() 
	{
		// PRG rule, to avoid POSTDATA resend
		$this->rulePRG();
		// prepare array, only after save, for get new data
		$this->fillSmartyAndRights();
		// get rights, only after save, for fresh data
		$this->getRights();
		// fill grid data
		$this->fillGridData();
		// add common js code
		$this->addCommonJs();
		// add common html code
		$this->addCommonHtml();
		// Set common assign
		$this->commonAssign();
		// build admin block
		$this->assignAdmin();
	}
	
	/**
	 * PRG rule, to avoid POSTDATA resend
	 * call after save
	 *
	 */
	function rulePRG() 
	{
		if(no_output_done() && postvalue("a")=="save") 
		{
			$getParams = '';
			if (postvalue('group'))
				$getParams = '?group='.postvalue('group');
			
			// redirect, add a=return param for saving SESSION
			header("Location: ".$this->shortTableName."_".$this->getPageType().".php".$getParams);
			// turned on output buffering, so we need to stop script
			exit();
		}
	}
	
	/**
	 * show page at the end of its proccess, depending on mode
	 *
	 */
	function showPage() 
	{
		$this->xt->display($this->templatefile);
	}
	/**
	 * Adds HTML and JS
	 *
	 */
	function addCommonHtml() 
	{
		$this->body["begin"] .= "<script type=\"text/javascript\" src=\"include/loadfirst.js\"></script>";
				$this->body["begin"] .= "<script type=\"text/javascript\" src=\"include/lang/".getLangFileName(mlang_getcurrentlang()).".js\"></script>";
		
		if ($this->isDisplayLoading)
		{
			$this->body["begin"] .= "<script type=\"text/javascript\">Runner.runLoading();</script>"; 
			//$this->getStopLoading();
		}
		
		//$this->body['end'] = "<script>".$this->PrepareJS()."</script>";
		
		// assign body end
		$this->body['end'] = array();
		$this->body['end']["method"] = "assignBodyEnd";
		$this->body['end']["object"] = &$this;
	}
	
	function prepareForResizeColumns()
	{
		return true;
	}
	
	/**
	 * Add js files and scripts
	 *
	 */
	function addCommonJs() {
		// call parent if need RunnerJS API 
		RunnerPage::addCommonJs();
		
		$this->addJsGroupsAndRights();
	}
	
}

?>