<?php
class MembersPage extends ListPage_Simple 
{	
	/**
	 * Groups array from DB
	 *
	 * @var array
	 */
	var $groups = array();
	var $groupFullChecked = array();
	/**
	 * Members array from DB
	 *
	 * @var array
	 */	
	var $members = array();
	/**
	 * Users array from DB
	 *
	 * @var array
	 */	
	var $users = array();
	/**
	 * If sort by group - number of group, else false
	 *
	 * @var int
	 */
	var $sortByGroup = false;
	/** 
	* If sort by username or display name - name of field, else ""
	*
	* @var string
	*/
	var $sortField = "";
	/**
	 * Sort order for group sorting
	 *
	 * @var string
	 */
	var $sortOrder = "";
		
	var $addSaveButtons = false;
	
	/**
	 * Contructor
	 *
	 * @param array $params
	 * @return MembersPage
	 */
	function MembersPage(&$params) 
	{
		// call parent
		parent::ListPage_Simple($params);
		
		$this->listAjax = false;
	}
	/**
	 * Override, add admin_members specific assignments
	 *
	 */
	function commonAssign() 
	{	
		// call parent
		parent::commonAssign();

		if ($this->addSaveButtons)
		{
			///$this->xt->assign("recordcontrols_block",true);
			$this->xt->assign("savebuttons_block", true);
			$this->xt->assign("savebutton_attrs","id=\"saveBtn\"");
			$this->xt->assign("resetbutton_attrs", "id=\"resetBtn\"");
		}
		
		//$this->xt->assign("toplinks_block",true);
		$this->xt->assign("search_records_block",true);
		$this->xt->assign("username",htmlspecialchars($_SESSION["UserName"]));
		$this->xt->assign("displayusername",htmlspecialchars($_SESSION["UserName"]));
		$this->xt->assign("shiftstyle_block",true);
		$this->xt->assign("security_block",true);
		$this->xt->assign("logoutbutton",isSingleSign());
		$this->xt->assign("left_block",true);
				
		$this->xt->assign("message_block", true);
		$this->xt->displayBrickHidden("message");
			
		$userheaderlink_attrs = "href=\"".$this->tName."_list.php?orderby=ALOGIN\"";
		
		//for display name 
		$displayuserheaderlink_attrs = "href=\"".$this->tName."_list.php?orderby="."ANOME\"";
		
		if ($this->sortByGroup === false && $this->sortField != ""){
			if ($this->sortField == "LOGIN"){
				$userheaderlink_attrs = "href=\"".$this->tName."_list.php?orderby=";
				if($this->sortOrder == "A") {
					if($this->is508)
						$this->xt->assign("users_orderimg","<img src=\"images/up.gif\" alt=\" \" border=0>");
					else
						$this->xt->assign("users_orderimg","<img src=\"images/up.gif\" border=0>");
					$userheaderlink_attrs.="DLOGIN";
				} else {
					if($this->is508)
						$this->xt->assign("users_orderimg","<img src=\"images/down.gif\" alt=\" \" border=0>");
					else
						$this->xt->assign("users_orderimg","<img src=\"images/down.gif\" border=0>");
					$userheaderlink_attrs.="ALOGIN";
				}
				$userheaderlink_attrs.="\"";
			}
			else if ($this->sortField == "NOME"){
				$displayuserheaderlink_attrs = "href=\"".$this->tName."_list.php?orderby=";
				if($this->sortOrder == "A") {
					if($this->is508)
						$this->xt->assign("displayusers_orderimg","<img src=\"images/up.gif\" alt=\" \" border=0>");
					else
						$this->xt->assign("displayusers_orderimg","<img src=\"images/up.gif\" border=0>");
					$displayuserheaderlink_attrs.="DNOME";
				} else {
					if($this->is508)
						$this->xt->assign("displayusers_orderimg","<img src=\"images/down.gif\" alt=\" \" border=0>");
					else
						$this->xt->assign("displayusers_orderimg","<img src=\"images/down.gif\" border=0>");
					$displayuserheaderlink_attrs.="ANOME";
				}
				$displayuserheaderlink_attrs.="\"";
			}
		}
		$this->xt->assign("userheaderlink_attrs",$userheaderlink_attrs);
		$this->xt->assign("displayuserheaderlink_attrs",$displayuserheaderlink_attrs);
		$this->xt->assign("menu_block",true);
	}	
	
	
	/**
	 * save member assignments
	 *
	
	function save() 
	{
		if(postvalue("a")=="save")
		{
			$useridx=0;
			foreach(postvalue("username") as $user)
			{
				$useridx++;
				//	delete records which are not needed
				$sql="delete from ugmembers where UserName=".db_prepare_string($user);
				if($user==$_SESSION["UserID"])
					$sql.=" and GroupID<>-1";
				db_exec($sql,$this->conn);
				if(count($_POST["cb_".$useridx]))
				{
					$glist="";
					foreach(@$_POST["cb_".$useridx] as $g)
					{
						if($g<0)
						{
							if($user==$_SESSION["UserID"] && $g==-1)
								continue;
							$sql="insert into ugmembers (UserName,GroupID) values (".db_prepare_string(htmlspecialchars($user)).",".$g.")";
							db_exec($sql,$this->conn);
						}
						else
						{
							if($glist!="")
								$glist.=",";
							$glist.=$g;
						}
					}
					if($glist!="")
					{
						$sql="insert into ugmembers (UserName,GroupID) select ".db_prepare_string(htmlspecialchars($user)).",GroupID from uggroups where GroupID in (".$glist.")";
						db_exec($sql,$this->conn);
					}
				}
			}
		}
	} */
	
	/**
	 * Fills grid rows and headers
	 *
	 */
	function fillGridData() 
	{
		$rowClass = false;
		//	fill $rowInfo array
		$rowInfo = array();	
		// add grid data
		$data = $this->beforeProccessRow();
		// like usual grid data fill 
		while($data && ($this->sortByGroup !== false || $this->recNo <= $this->pageSize || $this->pageSize==-1))
		{
			$row = array();
			
			$row["rowclass"] = "";
			if(!$rowClass) 
			{
				$row["rowclass"] .= "interlaced";
				$rowClass = true;
			} 
			else 
				$rowClass = false;
			
			$row["rowattrs"] = "rowid=\"".$this->recNo."\"";
			$row["grid_record"] = array();
			$row["grid_record"]["data"] = array();
		
			//	create checkboxes
			$member_indexes=array();
			foreach($this->members as $idx=>$m)
			{
				if($m[1]==$data["LOGIN"])
					$member_indexes[]=$idx;
			}
			$rowgroups = array();
			$userfullchecked = true;
			foreach($this->groups as $idx => $g)
			{
				$checked=false;
				$smarty_group=array();
				foreach($member_indexes as $i)
				{
					if($this->members[$i][0]==$g[0])
					{
						$checked = true;
						break;
					}
				}
				if($_SESSION["UserID"] != $data["LOGIN"] || $g[0] != -1)
				{
					$smarty_group["groupbox_attrs"] = "id=\"cb".$g[0]."_".count($this->users)."_\" value=\"1\"";
				
					$smarty_group["checked"] = 0;
					if(!$checked)
					{
						$userfullchecked = false;
						$groupfullchecked[$idx] = false;
					}
					else
					{
						if(!isset($groupfullchecked[$idx]))
							$groupfullchecked[$idx] = true;
						$smarty_group["groupbox_attrs"] .= " checked";
						$smarty_group["checked"] = 1;
					}
				
					$smarty_group["group"] = $g[0];
					$rowgroups[] = array("usergroup_box" => array("data" => array($smarty_group)));
				}
				else
					$rowgroups[] = array();
					//$smarty_group["groupbox_attrs"] = 'style="display:"';
				//$rowgroups[] = $smarty_group; 
			}
			$row["usergroup_boxes"] = array("data" => $rowgroups);
			$usercheckbox_attrs = "";
			if($userfullchecked)
				$usercheckbox_attrs .= " checked";
			
			//$row["begin"] = "<input type=hidden name=\"username[]\" value=\"".htmlspecialchars($data["LOGIN"])."\">";
			
			$row["username"] = htmlspecialchars($data["LOGIN"]);
			$row["displayusername"] = htmlspecialchars($data["NOME"]); //htmlspecialchars($data["LOGIN"]);
			$row["user"] = $data["LOGIN"];
			$usercheckbox_attrs.=" name=\"user_".count($this->users)
				."\" id=\"".count($this->users)."\"";
			
			$this->users[] = htmlspecialchars($data["LOGIN"]);
			
			if($this->sortByGroup === false)
				$usercheckbox_attrs.= " rowid=".$this->recNo;
			$row["usercheckbox_attrs"] = $usercheckbox_attrs;
			$row["recNo"] = $this->recNo; 
			$this->recNo++;
	
	//	assign row spacings for vertical layout
			$row["grid_rowspace"]=true;
			$row["grid_recordspace"] = array("data"=>array());
			for($i=0;$i<$this->colsOnPage*2-1;$i++)
				$row["grid_recordspace"]["data"][]=true;
			
			if($this->eventExists("BeforeMoveNextList"))
				$this->eventsObject->BeforeMoveNextList($data,$row,$record, $this);
			$rowInfo[]=$row;
			
			$data = $this->beforeProccessRow();
		}
		
		// fill headers array
		foreach($this->groups as $g)
		{
			$smartyGroups[]=array("groupname"=>htmlspecialchars($g[1]),
				"groupheaderlink_attrs"=>"href=\"".$this->tName."_list.php?orderby=a".$g[0]."\"",
				"groupheaderbox_attrs"=>"id=\"cb".$g[0]."\" value=\"".$g[0]."\""
			);
		}
		// fill group checkbox attrs
		foreach($smartyGroups as $idx=>$g)
		{
			if(isset($groupfullchecked[$idx]) && $groupfullchecked[$idx])
			{
				$smartyGroups[$idx]["groupheaderbox_attrs"].=" checked";				
			}
		}
		// add sort arrow to groups
		$this->sortGroups($smartyGroups);		
		// sort by group header
		$this->doSortByGroup($rowInfo);	
		// assign grid rows		
		$this->xt->assign_loopsection("grid_row", $rowInfo);
		// assign grid headers
		$this->xt->assign_loopsection("usergroup_header", $smartyGroups);
		
		if (count($rowInfo))
		{
			$this->addSaveButtons = true;
		}
		
	}
	/**
	 * For group array sorting
	 *
	 * @param link $rowInfo
	 */	
	function DPOrderUsers(&$rowInfo)
	{
		// deal with global vars
		global $sortgroup, $sortorder;
		$sortgroup = $this->sortByGroup;
		$sortorder = $this->sortOrder;
	
		sortMembers($rowInfo);
	}
	
	/**
	 * Fill members array from DB, call after save
	 *
	 */
	function fillMembers() 
	{
		//	select members list		
		$trs = db_query("select , from ugmembers order by ,",$this->conn);
		while($tdata = db_fetch_numarray($trs))
		{
			$this->members[] = array($tdata[1],$tdata[0]);
		}
	}
	
	/**
	 * Fill groups array from DB, call after save
	 *
	 */
	function fillGroups() 
	{
		$this->groups[] = array(-1,"<"."Admin".">");
		$this->groupFullChecked[] = true;
		
		$trs = db_query("select , from uggroups order by ",$this->conn);
		while($tdata = db_fetch_numarray($trs))
		{
			$this->groups[] = array($tdata[0],$tdata[1]);
			$this->groupFullChecked[] = true;
		}
	}
	/**
	 * Sort rows headers by group
	 *
	 * @param link $smartyGroups
	 */
	function sortGroups(&$smartyGroups) 
	{
		//		assign sort links
		foreach($this->groups as $i=>$g)
		{
			if($this->sortByGroup==$g[0] && $this->sortOrder=="a")
			{
				$smartyGroups[$i]["groupheaderlink_attrs"]="href=\"".$this->tName."_list.php?orderby=d".$g[0]."\"";
				if($this->is508)
					$smartyGroups[$i]["groupheader_img"] = "<img src=\"images/up.gif\" alt=\" \" border=0>";
				else
					$smartyGroups[$i]["groupheader_img"] = "<img src=\"images/up.gif\" border=0>";
			}
			elseif($this->sortByGroup==$g[0] && $this->sortOrder=="d")
			{				
				if($this->is508)
					$smartyGroups[$i]["groupheader_img"] = "<img src=\"images/down.gif\" alt=\" \" border=0>";
				else
					$smartyGroups[$i]["groupheader_img"] = "<img src=\"images/down.gif\" border=0>";
			}
				
		}
	}
		
	function prepareForResizeColumns()
	{
		return true;
	}
		
	/**
	 * PRG rule, to avoid POSTDATA resend
	 * call after save
	 *
	 */
	function rulePRG() 
	{		
		if(no_output_done() && (postvalue("a")=="save"/* || count($this->selectedRecs)*/)) 
		{
			// redirect, add a=return param for saving SESSION
			header("Location: ".$this->shortTableName."_".$this->getPageType().".php?a=return");
			// turned on output buffering, so we need to stop script
			exit();
		}
	}
	/**
	 * Parse session
	 *
	 */
	function setSessionVariables() 
	{
		// call parent
		parent::setSessionVariables();
		// order vars are simple
		
		if (substr($_SESSION[$this->sessionPrefix."_orderby"],1) == "LOGIN" || substr($_SESSION[$this->sessionPrefix."_orderby"],1) =="NOME"){
			$this->sortByGroup = false;
			$this->sortField = substr($_SESSION[$this->sessionPrefix."_orderby"],1);
		}
		else {
			$this->sortByGroup = substr($_SESSION[$this->sessionPrefix."_orderby"],1);
		}
		$this->sortOrder = substr($_SESSION[$this->sessionPrefix."_orderby"], 0, 1);
		
	}
	
	/**
	 * Sort rows by groups
	 *
	 * @param link $rowInfo
	 */
	function doSortByGroup(&$rowInfo) 
	{		
		if ($this->sortByGroup!==false)
		{
			$this->DPOrderUsers($rowInfo);
			
		// apply pagination
			$firstindex=$this->pageSize*($this->myPage-1);
			for($i=0;$i<$firstindex;$i++)
				array_shift($rowInfo);
			if(count($rowInfo)>$this->pageSize)
				array_splice($rowInfo,$this->pageSize,0);
			$this->recNo=1;
			for($i=0;$i<count($rowInfo);$i++)
			{
				$rowInfo[$i]["usercheckbox_attrs"].= " rowid=".$this->recNo;
				for($j=0;$j<count($rowInfo[$i]["usergroup_boxes"]["data"]);$j++)
				{
					$rowInfo[$i]["usergroup_boxes"]["data"][$j]["groupbox_attrs"]="name=\"cb_"
						.$this->recNo."[]\" value=\""
						.$rowInfo[$i]["usergroup_boxes"]["data"][$j]["group"]."\"";
					if($rowInfo[$i]["usergroup_boxes"]["data"][$j]["checked"])
						$rowInfo[$i]["usergroup_boxes"]["data"][$j]["groupbox_attrs"].=" checked";
				}
				$this->recNo++;
			}
		}
	}
	/**
	 * Build order query only for users header
	 *
	 */
	function buildOrderParams() 
	{
		if ($this->sortByGroup === false && $this->sortField != ""){
			$this->strOrderBy = "Order by ".$this->sortField;
			if ($this->sortOrder == "A")
				$this->strOrderBy .= " asc";
			else 
				$this->strOrderBy .= " desc";
		}
	}	
		
	/**
	 * Main function, call to build page
	 * Do not change methods call oreder!!
	 *
	 */
	function prepareForBuildPage() 
	{
		// save recs
		//$this->save();
		// PRG rule, to avoid POSTDATA resend
		$this->rulePRG();	
		//Sorting fields
		$this->buildOrderParams();
		// fill data
		$this->fillMembers();
		$this->fillGroups();
		// build sql query
		$this->buildSQL();
		// build pagination block
		$this->buildPagination();
		// seek page must be executed after build pagination
		if ($this->sortByGroup===false)
			$this->seekPageInRecSet($this->querySQL);					
		else
			$this->recSet = db_query($this->querySQL, $this->conn);
		// fill grid data
		$this->fillGridData();
		// build search panel
		$this->buildSearchPanel("adv_search_panel");
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
	 * show page at the end of its proccess, depending on mode
	 *
	 */
	function showPage() 
	{
		$this->xt->display($this->templatefile);
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
	
	/**
	 * Prepare JS arrays with groups and tables data
	 *
	 */
	function addJsGroupsAndRights() 
	{
		$this->jsSettings['tableSettings'][$this->tName]['usersList'] = $this->users;
		$this->jsSettings['tableSettings'][$this->tName]['rightsGroups'] = array();
		
		foreach ($this->groups as $grArr)
			$this->jsSettings['tableSettings'][$this->tName]['rightsGroups'][] = $grArr[0];
	}
}

?>