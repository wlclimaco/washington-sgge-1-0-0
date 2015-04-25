<?php
include_once(getabspath("classes/files.php"));

/**
 * Abstract base class for all pages. Contains main functionality 
 */
class RunnerPage
{
	/**
      * Id on page.
      *
      * @var integer
      * @intellisense
      */
	var $id = 1;
	/**
      * Use timepicker or not
      *
      * @var bool
      * @intellisense
      */
	var $timepicker = false;
	/**
      * Use tool tips or not
      *
      * @var bool
      * @intellisense
      */
	var $isUseToolTips = false;
	/**
	 * If use Ajax Suggest js file or not
	 *
	 * @var bool
	 * @intellisense
	 */
	var $isUseAjaxSuggest = true;
	/**
      * Type of page
      *
      * @var string
      * @intellisense
      */
	var $pageType = "";
	/**
      * Mode of page
      *
      * @var integer
      * @intellisense
      */
	var $mode = 0;
	/**
 	  * If use display loading or not
	  *
	  * @var bool
	  * @intellisense
	  */
	var $isDisplayLoading = false;
	/**
      * Original table name
      *
      * @var string
      * @intellisense
      */
	var $strOriginalTableName = "";
	/**
	 * String caption of table
	 *
	 * @var string
	 * @intellisense
	 */
	var $strCaption = "";
	/**
      * Short table name
      *
      * @var string
      * @intellisense
      */
	var $shortTableName = '';
	/**
      * Prefix for session variable
      *
      * @var integer
      * @intellisense
      */
	var $sessionPrefix = "";
	/**
      * Name of current table
      *
      * @var string
      * @intellisense
      */	
	var $tName = "";
	/**
      * Connect to database
      *
      * @var string
      * @intellisense
      */
	var $conn = "";
	/**
      * Array of order index in table
      *
      * @var array()
      * @intellisense
      */
	var $gOrderIndexes = array();
	/**
      * String of OrderBy for query
      *
      * @var string
      * @intellisense
      */
	var $gstrOrderBy = "";
	/**
      * Page size
      *
      * @var integer
      * @intellisense
      */
	var $gPageSize = 0;
	/**
      * Instance of class Xtempl
      *
      * @var object
      * @objtype{XTempl}
      * @intellisense
      */
	var $xt = null;
	/**
	 * Instance of SearchClause class
	 *
	 * @var object
	 * @objtype{SearchClause}
	 * @intellisense
	 */
	var $searchClauseObj = null;
	/**
      * Need use search clause object or not 
      *
      * @var boolean
      * @intellisense
      */
	var $needSearchClauseObj = true;
	
	var $flyId = 1;
	/**
	 *	The list of including js files 
	 * @intellisense
	 */	  
	var $includes_js = array();
	/**
	 *	The list of including js files 
	 * @intellisense
	 */
	var $includes_jsreq = array();
	/**
	 *	The list of including css files
	 * @intellisense
	 */
	var $includes_css = array();
	/**
	 *	The list of including css files wich have IE extension
	 * @intellisense
	 */
	var $includes_cssIE = array();	
	/**
	 *	Locale tunes
	 * @intellisense
	 */
	var $locale_info = array();
	/**
	 * Id of record
	 *
	 * @var integer
	 * @intellisense
	 */
	var $recId = 0;
	/**
	 * Google maps default settings
	 *
	 * @var array
	 * @intellisense
	 */
	var $googleMapCfg = array();
	/**
	 * Array of permissions for pages
	 *
	 * @var array
	 * @intellisense
	 */
	var $permis = array();
	/**
	 * If use group scurity or not
	 *
	 * @var bool
	 * @intellisense
	 */
	var $isGroupSecurity = true;
	/**
	 * Use or not debug mode for js
	 *
	 * @var bool
	 * @intellisense
	 */
	var $debugJSMode = false;
	/**
	 * Use mode ajax for simple listPage
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $listAjax = false;
	/**
	 * Array of body begin, end code and body attributs
	 *
	 * @var array
	 * @intellisense
	 */
	var $body = array('begin' => '', 'end'=> '');
	
	/**
	 * Master table name
	 *
	 * @var string
	 * @intellisense
	 */
	var $masterTable = "";
	/**
	 * Master table record data
	 *
	 * @var object
	 * @intellisense
	 */
	var $masterRecordData = null;
	/**
	 * If use Details Preview js file or not
	 *
	 * @var bool
	 * @intellisense
	 */
	var $useDetailsPreview = false;
	/**
	 * Array of all details tables data
	 *
	 * @var array
	 * @intellisense
	 */	
	var $allDetailsTablesArr = array();
	/**
	 * Array of java script settings for page
	 *
	 * @var array
	 * @intellisense
	 */	
	var $jsSettings = array();
	/**
	 * Array of controls HTML map
	 *
	 * @var array
	 * @intellisense
	 */	
	var $controlsHTMLMap = array();
	/**
	 * Array of view controls HTML map
	 *
	 * @var array
	 * @intellisense
	 */	
	var $viewControlsHTMLMap = array();
	/**
	 * Array of controls map
	 *
	 * @var array
	 * @intellisense
	 */	
	var $controlsMap = array();
	/**
	 * Array of view controls map
	 *
	 * @var array
	 * @intellisense
	 */	
	var $viewControlsMap = array();
	/**
	 * Array of field settings for use it in javascript settings
	 *
	 * @var array
	 * @intellisense
	 */	
	var $settingsMap = array();
	/**
	 * Is add page show as inlineAdd or not
	 *
	 * @var array
	 * @intellisense
	 */
	var $pageAddLikeInline = false;
	/**
	 * Is edit page show as inlineEdit or not
	 *
	 * @var array
	 * @intellisense
	 */ 
	var $pageEditLikeInline = false;
	/**
	 * Array of tabs and sections for add page
	 *
	 * @var array
	 * @intellisense
	 */	
	var $arrAddTabs = array();
	/**
	 * Use Tabs and Setctions on add page or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $useTabsOnAdd = false;
	/**
	 * Array of tabs and sections for edit page
	 *
	 * @var array
	 * @intellisense
	 */	
	var $arrEditTabs = array();
	/**
	 * Use Tabs and Setctions on edit page or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $useTabsOnEdit = false;
	/**
	 * Array of tabs and sections for edit page
	 *
	 * @var array
	 * @intellisense
	 */	
	var $arrViewTabs = array();
	/**
	 * Use Tabs and Setctions on edit page or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $useTabsOnView = false;
	/**
	 * Array of records per page for list and report without group fields
	 *
	 * @var array
	 * @intellisense
	 */	
	var $arrRecsPerPage = array();
	/**
	 * Array of groups per page for report with group fields
	 *
	 * @var array
	 * @intellisense
	 */	
	var $arrGroupsPerPage = array();
	/**
	 * Use group fields on report page or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $reportGroupFields = false;
	/**
	 * Number of page size
	 *
	 * @var integer
	 * @intellisense
	 */
	var $pageSize = 0;
	/**
	 * Type of table - list, report or chart
	 *
	 * @var string
	 * @intellisense
	 */
	var $isTableType = "";
	/**
	 * Events object for the current table
	 *
	 * @var object
	 * @intellisense
	 */	
	var $eventsObject;
	/**
	 * Detail keys by master table
	 *
	 * @var array
	 * @intellisense
	 */
	var $detailKeysByM = array();
	/**
	 * Is captcha ok after submit or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $isCaptchaOk = true;
	/**
	 * Captcha ID
	 *
	 * @var string
	 * @intellisense
	 */
	var $captchaId = "";
	/**
	 * Locking object
	 *
	 * @var object
	 * @intellisense
	 */
	var $lockingObj = null;
	/**
	 * Is use Video player or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $isUseVideo = false;
	/**
	 * Is use Audio player or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $isUseAudio = false;
	/**
	 * Is show add page like popUp or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $showAddInPopup = false;
	/**
	 * Is show edit page like popUp or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $showEditInPopup = false;
	/**
	 * Is show view page like popUp or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $showViewInPopup = false;
	/**
	 * Is columns will be resizable or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $isResizeColumns = false;
	/**
	 * Is use CKeditor or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $isUseCK = false;
	/**
	 * Is display detail data on page or not
	 *
	 * @var boolean
	 * @intellisense
	 */
	var $isShowDetailTables = false;
	/**
	*	arrays of files to process after adding or editing a record
	* @intellisense
	*/
    var $filesToSave = array();
	var $filesToMove = array();
	var $filesToDelete = array();
	/**
	 * Master keys by detail table
	 *
	 * @var array
	 * @intellisense
	 */
	var $masterKeysByD = array();
	/**
	 * Indicator is permissions dynamic
	 *
	 * @var bool
	 * @intellisense
	 */
	var $isDynamicPerm = false;
	/**
	 * If nedd add web report or not
	 *
	 * @var bool
	 * @intellisense
	 */
	var $isAddWebRep = true;
	/**
	 * Indicator, is used section 508 
	 *
	 * @var bool
	 * @intellisense
	 */
	var $is508 = false;
	/**
	 * Indicator, is encryption enabled 
	 *
	 * @var bool
	 * @intellisense
	 */	
	var $isEncryptionEnabled = false;
	/**
	 * Indicator, is encryption via PHP enabled 
	 *
	 * @var bool
	 * @intellisense
	 */
	var $isEncryptionByPHPEnabled = false;
	/**
	 * Instance of Cypher class for encoding/decoding fields values  
	 *
	 * @var object
	 * @intellisense
	 */
	var $cipherer = null;
	/**
	 * Project settings
	 *
	 * @var object
	 * @intellisense
	 */
	var $pSet = null;
	/**
	 * Project settings for edit controls
	 *
	 * @var object
	 * @intellisense
	 */
	var $pSetEdit = null;
	/**
	 * Number of rows
	 *
	 * @var integer
	 * @intellisense
	 */
	var $numRowsFromSQL = 0;	
	/**
	 * Index of my page
	 *
	 * @var integer
	 * @intellisense
	 */
	var $myPage = 0;
	var $recordsOnPage = 0;
	/**
	 * Number of records per row list
	 *
	 * @var integer
	 * @intellisense
	 */
	var $recsPerRowList = 0;
	var $colsOnPage = 1;
	/**
	 * Number of founed rows
	 *
	 * @var bool
	 * @intellisense
	 */
	var $rowsFound = false;
	/**
	  * Constructor, set initial params
	  *
	  * @param array $params
	  * @intellisense
	  */
	var $deleteMessage = '';
	/**
	 * Number of maximum pages
	 *
	 * @var integer
	 * @intellisense
	 */
	var $maxPages = 1;
	/**
	 * Name of the templete file
	 *
	 * @var string
	 * @intellisense
	 */
	var $templatefile = "";
	/**
	 * Array of menu nodes
	 *
	 * @var array
	 * @intellisense
	 */	
	var $menuNodes = array();
	/**
	 * Refferense to sqlquery object
	 * 
	 * @var object
	 * @intellisense
	 */
	var $gQuery = null;
	/**
	 * Flag. True if fillSetCntrlMaps already called 
	 * @intellisense
	 */
	var $isControlsMapFilled = false;
	/**
	 * Instance of EditControlsContainer
	 * @var {object}
	 * @intellisense
	 */
	public $controls = null;
	/**
	 * Instance of ViewControlsContainer
	 * @var {object}
	 * @intellisense
	 */
	public $viewControls = null;	
	/**
	 * Associative array of readonly fields for add, edit and register page
	 * @var array
	 * @intellisense
	 */
	public $readOnlyFields = array();
	
	function RunnerPage(&$params)
	{
		global $locale_info, $cCharset, $page_layouts;
		
		// copy properties to object
		RunnerApply($this, $params);
		
		$this->pSet = new ProjectSettings($this->tName, $this->pageType);
		$this->pSetEdit = $this->pSet;
		$this->cipherer = new RunnerCipherer($this->tName, $this->pSet);
		include_once getabspath("classes/controls/EditControlsContainer.php");
		$this->controls = new EditControlsContainer($this);
		include_once getabspath("classes/controls/ViewControlsContainer.php");
		$this->viewControls = new ViewControlsContainer($this->pSet, $this->pageType, $this);
		
		$this->gQuery = $this->pSet->getSQLQuery();
		
		//set google map configuration
		$this->googleMapCfg = array('markerAsLinkToView'=>true, 'isUseMainMaps'=>false, 'isUseFieldsMaps'=> false, 'isUseGoogleMap'=>false, 'APIcode'=>GetGlobalData("apiGoogleMapsCode",""), 'mainMapIds'=>array(), 'fieldMapsIds'=>array(), 'mapsData'=>array());
		
				$this->debugJSMode = false;
		
		if($this->flyId < $this->id+1)
			$this->flyId = $this->id+1;
		
		// get permissions 
		if ($this->tName)
		{
			$this->permis[$this->tName]= $this->getPermissions();
			$this->eventsObject = &getEventObject($this->tName);
		}
		
		if(!$this->sessionPrefix)
			$this->sessionPrefix = $this->tName;
		
		$this->setSessionVariables();
		
		//	get locking object
		$this->lockingObj = GetLockingObject($this->tName);
		
		$this->is508 = isEnableSection508();
		$this->isEncryptionEnabled = isEncryptionEnabled();
		$this->isEncryptionByPHPEnabled = isEncryptionByPHPEnabled();
		
		$this->isUseVideo = $this->pSet->isUseVideo();
		$this->isUseAudio = $this->pSet->isUseAudio();
		$this->strCaption = GetTableCaption(GoodFieldName($this->tName));
		$this->isTableType = $this->pSet->isTableType();
		$this->isAddWebRep = GetGlobalData("isAddWebRep",false);
		//	get details keys by master table
		$this->detailKeysByM = $this->pSet->getDetailKeysByMasterTable($this->masterTable);
		$this->isDynamicPerm = GetGlobalData("isDynamicPerm",false);
		$this->shortTableName = $this->pSet->getShortTableName();
		$this->showAddInPopup = $this->pSet->isShowAddInPopup();
		$this->showEditInPopup = $this->pSet->isShowEditInPopup();
		$this->showViewInPopup = $this->pSet->isShowViewInPopup();
		$this->isResizeColumns = $this->pSet->isResizeColumns();
		$this->isUseAjaxSuggest = $this->pSet->isUseAjaxSuggest();
		$this->useDetailsPreview = $this->pSet->isUseDetailsPreview();
		$this->isShowDetailTables = displayDetailsOn($this->tName,$this->pageType);
		//	get all details table for current table
		$this->allDetailsTablesArr = $this->pSet->getDetailTablesArr();
		
		//	set template file
		$this->setTemplateFile();
		
		//	init jsSettings
		$this->jsSettings["tableSettings"][$this->tName] = array();
		$this->jsSettings["tableSettings"][$this->tName]["proxy"] = array("proxy" => "");
		$this->jsSettings["tableSettings"][$this->tName]['fieldSettings'] = array();
	
		$this->settingsMap["globalSettings"] = array();
		$this->settingsMap["globalSettings"]["ext"] = "php";
		$this->settingsMap["globalSettings"]["charSet"] = $cCharset;
		$this->settingsMap["globalSettings"]["debugMode"] = $this->debugJSMode;
		$this->settingsMap["globalSettings"]["googleMapsApiCode"] = $this->googleMapCfg['APIcode'];
		$this->settingsMap["globalSettings"]["shortTNames"][$this->tName] = $this->shortTableName;
		
				
		//isMobile 
		$this->settingsMap["globalSettings"]["isMobile"] = isMobile();
		
		// s508 must be in global settings
		$this->settingsMap['globalSettings']['s508'] = $this->is508;
		
		$this->settingsMap["globalSettings"]["locale"]["dateFormat"] = $locale_info["LOCALE_IDATE"];
		$this->settingsMap["globalSettings"]["locale"]["startWeekDay"] = $locale_info["LOCALE_IFIRSTDAYOFWEEK"];
		$this->settingsMap["globalSettings"]["locale"]["dateDelimiter"] = $locale_info["LOCALE_SDATE"];
		
		$this->settingsMap["tableSettings"] = array();
		$this->settingsMap['tableSettings']['hasEvents'] = array("default"=>false,"jsName"=>"hasEvents");
		$this->settingsMap["tableSettings"]["listIcons"] = array("default"=>false,"jsName"=>"listIcons");
		$this->settingsMap["tableSettings"]["strCaption"] = array("default"=>"","jsName"=>"strCaption");
		$this->settingsMap["tableSettings"]["isUseAudio"] = array("default"=>false,"jsName"=>"isUseAudio");
		$this->settingsMap["tableSettings"]["isUseVideo"] = array("default"=>false,"jsName"=>"isUseVideo");
		$this->settingsMap['tableSettings']['isVerLayout'] = array("default"=>false,"jsName"=>"isVertLayout");
		$this->settingsMap["tableSettings"]["rowHighlite"] = array("default"=>false,"jsName"=>"isUseHighlite");
		$this->settingsMap["tableSettings"]["isUseToolTips"] = array("default"=>false,"jsName"=>"isUseToolTips");
		$this->settingsMap['tableSettings']['recsPerRowList'] = array("default"=>1,"jsName"=>"recsPerRowList");
		$this->settingsMap["tableSettings"]["showAddInPopup"] = array("default"=>false, "jsName"=>"showAddInPopup");
		$this->settingsMap["tableSettings"]["showEditInPopup"] = array("default"=>false,"jsName"=>"showEditInPopup");
		$this->settingsMap["tableSettings"]["showViewInPopup"] = array("default"=>false,"jsName"=>"showViewInPopup");
		$this->settingsMap["tableSettings"]["isResizeColumns"] = array("default"=>false,"jsName"=>"isUseResize");
		$this->settingsMap["tableSettings"]["isUseAjaxSuggest"] = array("default"=>true,"jsName"=>"ajaxSuggest");
		$this->settingsMap["tableSettings"]["useDetailsPreview"] = array("default"=>false,"jsName"=>"isUseDP");
		$this->settingsMap['tableSettings']['isUsebuttonHandlers'] = array("default"=>false,"jsName"=>"isUseButtons");
		
		if ($this->pageType == PAGE_REGISTER || $this->pageType == PAGE_CHANGEPASS)
			$layout = GetPageLayout('', $this->pageType);
		else 
			$layout = GetPageLayout($this->shortTableName, $this->pageType);
		if($layout)
		{
			$rtl = $this->xt->getReadingOrder() == 'RTL' ? 'RTL' : '';
			$this->jsSettings['tableSettings'][$this->tName]['pageCSS'] = "styles/".$layout->style.'/style'.$rtl.".css";
			$this->jsSettings['tableSettings'][$this->tName]['pageLayout'] = "pagestyles/".$layout->name.$rtl.".css";
			$this->jsSettings['tableSettings'][$this->tName]['pageSkinStyle'] = $layout->style." page-".$layout->name;
		}
		
		$this->settingsMap["fieldSettings"]["UseTimestamp"] = array("default"=>false,"jsName"=>"isUseTimeStamp");
		$this->settingsMap["fieldSettings"]["strName"] = array("default"=>"","jsName"=>"strName");
		$this->settingsMap["fieldSettings"]["ShowTime"] = array("default"=>false,"jsName"=>"showTime");
		$this->settingsMap["fieldSettings"]["EditFormat"] = array("default"=>"","jsName"=>"editFormat");
		$this->settingsMap["fieldSettings"]["DateEditType"] = array("default"=>EDIT_DATE_SIMPLE,"jsName"=>"dateEditType");
		$this->settingsMap["fieldSettings"]["RTEType"] = array("default"=>"","jsName"=>"RTEType");
		$this->settingsMap["fieldSettings"]["ViewFormat"] = array("default"=>"","jsName"=>"viewFormat");
		$this->settingsMap["fieldSettings"]["validateAs"] = array("default"=>null,"jsName"=>"validation");
		$this->settingsMap["fieldSettings"]["strEditMask"] = array("default"=>null,"jsName"=>"mask");
		$this->settingsMap["fieldSettings"]["LastYearFactor"] = array("default"=>10,"jsName"=>"lastYear");
		$this->settingsMap["fieldSettings"]["InitialYearFactor"] = array("default"=>100,"jsName"=>"initialYear");
		
		$this->jsSettings["tableSettings"][$this->tName]["strCaption"] = $this->strCaption;
		$this->jsSettings["tableSettings"][$this->tName]["pageMode"] = $this->mode;
		
		if ($this->listAjax)
			$this->jsSettings['tableSettings'][$this->tName]['pageMode'] = LIST_AJAX;
		
		if($this->lockingObj)
			$this->jsSettings['tableSettings'][$this->tName]['locking'] = true;
		
		//If current table has detail tables
		if(count($this->allDetailsTablesArr))
		{
			if($this->pageType==PAGE_LIST)
				$this->jsSettings['tableSettings'][$this->tName]['detailTables'] = array();
			
			$this->jsSettings['tableSettings'][$this->tName]['isShowDetails'] = $this->isShowDetailTables;
			
			for($i = 0; $i < count($this->allDetailsTablesArr); $i ++) 
			{
				$this->settingsMap["globalSettings"]['shortTNames'][$this->allDetailsTablesArr[$i]['dDataSourceTable']] = $this->allDetailsTablesArr[$i]['dShortTable'];
				if($this->pageType==PAGE_LIST){
					$this->jsSettings['tableSettings'][$this->tName]['detailTables'][$this->allDetailsTablesArr[$i]['dDataSourceTable']] = 
						array(
							'dispChildCount' => $this->allDetailsTablesArr[$i]['dispChildCount'], 
							'hideChild' => $this->allDetailsTablesArr[$i]['hideChild'],
							'listShowType'=>$this->allDetailsTablesArr[$i]['previewOnList'],
							'addShowType'=>$this->allDetailsTablesArr[$i]['previewOnAdd'],
							'editShowType'=>$this->allDetailsTablesArr[$i]['previewOnEdit'],
							'viewShowType'=>$this->allDetailsTablesArr[$i]['previewOnView']				  
						);
					if($this->allDetailsTablesArr[$i]['previewOnList'] == DP_POPUP)
						$this->jsSettings['tableSettings'][$this->tName]['isUsePopUp'] = true;
					// field names of master keys of current table for passed details table name
					$this->masterKeysByD[$i] = $this->pSet->getMasterKeysByDetailTable($this->allDetailsTablesArr[$i]['dDataSourceTable']);		
				}
			}
			
			if(($this->pageType==PAGE_ADD || $this->pageType==PAGE_EDIT) && $this->isShowDetailTables)
				$this->controlsMap["dControlsMap"] = array();
		}
		$this->controlsMap["video"] = array();
		$this->controlsMap['toolTips'] = array();
		$this->addLookupSettings();
		$this->addMultiUploadSettings();
		
		if($this->mode!=LIST_DETAILS)
		{
			$this->controlsMap["controls"] = array();
			if(!$this->pageAddLikeInline && !$this->pageEditLikeInline)
			{
				$this->controlsMap["search"] = array();
				$this->controlsMap["search"]["searchBlocks"] = array();
				$this->controlsMap["search"]["panelSearchFields"] = $this->pSet->GetPanelSearchFields();
				$this->controlsMap["search"]["allSearchFields"] = $this->pSet->getAllSearchFields();
				$this->controlsMap["search"]["googleLikeFields"] = $this->pSet->getGoogleLikeFields();
					
				if($this->pageType!=PAGE_SEARCH)
					$this->controlsMap["search"]["submitPageType"] = $this->pageType;
				else
				{
					if(postvalue("rname")){
						$this->controlsMap["search"]["submitPageType"] = "dreport";
						$this->controlsMap["search"]["baseParams"]["rname"] = postvalue("rname");
						if($_SESSION["crossLink"])
						{
							if(substr($_SESSION["crossLink"],0,1)=="&")
								$_SESSION["crossLink"]=substr($_SESSION["crossLink"],1);
							$alink=explode("&",$_SESSION["crossLink"]);
							foreach($alink as $param)
							{
								$arrtmp=explode("=",$param);
								$this->controlsMap["search"]["baseParams"][$arrtmp[0]] = $arrtmp[1];
							}
						}
					}elseif(postvalue("cname")){
						$this->controlsMap["search"]["submitPageType"] = "dchart";
						$this->controlsMap["search"]["baseParams"]["cname"] = postvalue("cname");
					}else{
						$this->controlsMap["search"]["submitPageType"] = $this->isTableType;
					}
				}
			}
		}
		
		$this->timepicker = $this->timepicker || $this->pSet->isUseTimeForSearch();
		$this->isUseToolTips = $this->isUseToolTips || $this->pSet->isUseToolTips(); 
		
		$this->googleMapCfg["APIcode"] = "";
		if($this->xt)
			$this->xt->assign("pagetitle", $this->getPageTitle());
	}	
	
	/**
	 * addMultiUploadSettings
	 * Adding js-settings for FileField
	 * @intellisense
	 */
	function addMultiUploadSettings()
	{
		$this->settingsMap["fieldSettings"]["autoUpload"] = array("default"=>false, "jsName"=>"autoUpload");
		$this->settingsMap["fieldSettings"]["acceptFileTypes"] = array("default"=>false, "jsName"=>"acceptFileTypes");
		$this->settingsMap["fieldSettings"]["CompatibilityMode"] = array("default"=>false, "jsName"=>"compatibilityMode");
		$this->settingsMap["fieldSettings"]["maxFileSize"] = array("default"=>null, "jsName"=>"maxFileSize");
		$this->settingsMap["fieldSettings"]["maxTotalFilesSize"] = array("default"=>null, "jsName"=>"maxTotalFilesSize");
		$this->settingsMap["fieldSettings"]["maxNumberOfFiles"] = array("default"=>null, "jsName"=>"maxNumberOfFiles");
	}
	
	/**
	 * Get master record
	 *
	 * User function
	 * Using only in events by users
	 * @return{array}
	 * @intellisense
	 */
	function getMasterRecord()
	{
		if (!is_null($this->masterRecordData))
			return $this->masterRecordData;
		
		if(!$this->masterTable)
			return null;
		
		$where = "";
		$masterTablesInfoArr = $this->pSet->getMasterTablesArr($this->tName);
		for($i=0; $i < count($masterTablesInfoArr); $i++) 
		{
			if($this->masterTable == $masterTablesInfoArr[$i]['mDataSourceTable']) 
			{
				for($j=0; $j < count($masterTablesInfoArr[$i]['detailKeys']); $j ++)
					$masterKeys[] = @$_SESSION[$this->sessionPrefix."_masterkey".($j + 1)];
				
				$cipherer = new RunnerCipherer($this->masterTable);
				for($j=0; $j < count($masterTablesInfoArr[$i]['masterKeys']); $j++)
				{
					if($j)
						$where.= " and ";
					$mKey = $masterTablesInfoArr[$i]['masterKeys'][$j];
					$where.= GetFullFieldName($mKey, "", false)."=".$cipherer->MakeDBValue($mKey, $masterKeys[$j], "", "", true);
				}
			}
		}
		
		if(!$where)
			return null;
		
		global $conn;
		$settings = new ProjectSettings($this->masterTable, PAGE_LIST);	
		$masterQuery = $settings->getSQLQuery();
		
		$str = SecuritySQL("Search");
		if(strlen($str))
			$where.= " and ".$str;
		
		$strWhere = whereAdd($masterQuery->WhereToSql(),$where);
		if(strlen($strWhere))
			$strWhere = " where ".$strWhere." ";
		$strSQL = $masterQuery->HeadToSql().' '.$masterQuery->FromToSql().$strWhere.$masterQuery->TailToSql();
		
		LogInfo($strSQL);
		$rs = db_query($strSQL,$conn);
		$this->masterRecordData = $cipherer->DecryptFetchedArray($rs);
		return $this->masterRecordData;
	}
	
	/**
	 * Set Proxy Value 
	 * Fill array serverData for using in js OnLoad event
	 *
	 * User function
	 * Using only in events by users
	 *
	 * @param{string} name of data
	 * @param{string} value of data
	 * @intellisense
	 */
	function setProxyValue($name, $value)
	{
		if(!$name)
			return;
		$this->jsSettings["tableSettings"][$this->tName]["proxy"][$name] = $value;
	}
	
	/**
	 * Get Proxy Value 
	 *
	 * User function
	 * Using only in events by users
	 *
	 * @param{string} name of data
	 * @return{}
	 * @intellisense
	 */
	function getProxyValue($name)
	{
		if(array_key_exists($name, $this->jsSettings['tableSettings'][$this->tName]['proxy']))
			return $this->jsSettings["tableSettings"][$this->tName]["proxy"][$name];
		return null;	
	}
	
	/**
	 * Set template file if it empty
	 * @intellisense
	 */
	function setTemplateFile()
	{
		if(!$this->templatefile)
		{
			$this->templatefile	= $this->shortTableName."_".$this->pageType.".htm";
		}
		$this->xt->set_template($this->templatefile);
	}
	/**
	 * Get menu nodes if use menu on page
	 * @intellisense
	 */	
	function getMenuNodes()
	{
		if(!count($this->menuNodes))
		{
			global $menuNodesObject;
			$menuNodesObject = &$this;
			require(getabspath("include/menunodes.php"));
		}
	}
	/**
	 * Check is use menu on page
	 * @intellisense
	 */
	function isUseMenu()
	{
		$menuBricks = array('vmenu', 'vmenu_mobile', 'hmenu', 'quickjump');
		if($this->xt->isExistBricks($menuBricks))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Check is need to show menu
	 * @intellisense
	 */
	function isShowMenu()
	{
		if(!$this->isUseMenu() && $this->pageType!=PAGE_MENU && $this->pageType!=PAGE_ADD)
			return false;
			
		$this->getMenuNodes();
		$allowedMenuItems = 0;
		for($i=0;$i<count($this->menuNodes);$i++)
		{
			if($this->menuNodes[$i]["linkType"] == "Internal")
			{
				if($this->isUserHaveTablePerm($this->menuNodes[$i]["table"], $this->menuNodes[$i]["pageType"]))
					$allowedMenuItems++;
			}
			else 
				$allowedMenuItems++;
		}
		if($this->isDynamicPerm && IsAdmin() && $this->pageType==PAGE_MENU) 
			$allowedMenuItems++;
		if($this->isAddWebRep) 
			$allowedMenuItems++;
		if($allowedMenuItems > 1)
			return true;
		return false;
	}
	
	/**
	 * Check if user have permission for link
	 * @param {string} table name
	 * @param {string} page type
	 * @return {boolean}
	 * @intellisense
	 */
	function isUserHaveTablePerm($tName, $pageType)
	{
		if($pageType == "WebReports")
			return true;
		if(!strlen($tName))
			return false;
		$type = $this->getPermisType($pageType);
		$strPerm = GetUserPermissions($tName);	
		if(strpos($strPerm,$type)!==false)
			return true;
		else
			return false;
	}
	/**
	 * Get type of permission
	 * @return {string}
	 * @intellisense
	 */
	function getPermisType($pageType)
	{
		$type = '';
		if ($pageType == "List" || $pageType == "Search" || $pageType == "Report" || $pageType == "Chart")
			$type = "S";
		elseif ($pageType == "Add")
			$type = "A";
		elseif ($pageType == "Edit")
			$type = "E";
		elseif ($pageType == "Print" || $pageType == "Export")
			$type = "P";
		elseif ($pageType == "Import")
			$type = "I";
		return $type;
	}
	
	/**
	 * Get redirect location for menu page
	 * @return {string}
	 * @intellisense
	 */
	function getRedirectForMenuPage() 
	{
		if($this->isShowMenu())
			return '';
		
		$redirect = '';
		for($i=0;$i<count($this->menuNodes);$i++)
		{
			if($this->menuNodes[$i]["linkType"] == "Internal")
			{
				if($this->isUserHaveTablePerm($this->menuNodes[$i]["table"], $this->menuNodes[$i]["pageType"]))
				{	
					$type = $this->getPermisType($this->menuNodes[$i]['pageType']);
					if($type == "A")
						$redirect = "add";
					elseif($this->menuNodes[$i]["pageType"] == "List" && $type == "S")
						$redirect = "list";
					elseif($this->menuNodes[$i]["pageType"] == "Report" && $type == "S")
						$redirect = "report";
					elseif($this->menuNodes[$i]["pageType"] == "Chart" && $type == "S")
						$redirect = "chart";	
					$redirect = GetTableURL($this->menuNodes[$i]["table"]).'_'.$redirect.".php";	
				}	
			}
		}
		if($this->isDynamicPerm && IsAdmin()) 
			$redirect = "admin_rights_list.php";
			
		if($this->isAddWebRep) 
			$redirect = "webreport.php";
		
		return $redirect;
	}
	
	function getPageTitle()
	{
		switch($this->pageType)
		{
			case PAGE_MENU:
				return "";
			case PAGE_LOGIN:
				return "Login";
			case PAGE_REGISTER:
				return "Registrar";
			case PAGE_REMIND:
				return "Lembrar a minha Senha";
			case PAGE_CHANGEPASS:
				return "Mudar senha";
			case PAGE_SEARCH:
				return $this->strCaption.': '."Busca Avancada";
			case PAGE_EXPORT:
				return "Exportar";
			case PAGE_IMPORT:
				return "Importar";
			default:
				return $this->strCaption;
		}
	}
	/**
	 * Clear session kyes
	 * @intellisense
	 */
	function clearSessionKeys() 
	{
		if($this->pageType == PAGE_LIST && !count($_POST) && (!count($_GET) || count($_GET) == 1 && isset($_GET["menuItemId"]) || isset($_GET["mastertable"]) && @$_GET["mode"]!="listdetails") || @$_GET["editType"] == ADD_ONTHEFLY)
		{
			$sess_unset = array();
			foreach($_SESSION as $key => $value)
				if(substr($key, 0, strlen($this->sessionPrefix) + 1) == $this->sessionPrefix."_" && strpos(substr($key, strlen($this->sessionPrefix) + 1), "_") === false)
					$sess_unset[]= $key;
			
			foreach($sess_unset as $key)
				unset($_SESSION[$key]);
		}
	}
	/**
	 * Set session variables
	 * @intellisense
	 */	
	function setSessionVariables() 
	{
		//clear session keys
		$this->clearSessionKeys();
		
		// Process master table value
		if($this->masterTable!="")
			$_SESSION[$this->sessionPrefix."_mastertable"] = $this->masterTable;
		else
			$this->masterTable = $_SESSION[$this->sessionPrefix."_mastertable"];
			
		// SearchClause class stuff
		$allSearchFields = $this->pSet->getAllSearchFields();
		if($this->needSearchClauseObj && !$this->searchClauseObj)
		{
			if (isset($_SESSION[$this->sessionPrefix.'_advsearch']))
			{
				$this->searchClauseObj = unserialize($_SESSION[$this->sessionPrefix.'_advsearch']);
				$this->searchClauseObj->cipherer = $this->cipherer;
			}
			else{
				$params = array();
				$params['tName'] = $this->tName;
				$params['cipherer'] = $this->cipherer;
				$params['searchFieldsArr'] = $allSearchFields;
				$params['sessionPrefix'] = $this->sessionPrefix;
				$params['panelSearchFields'] = $this->pSet->getPanelSearchFields();
				$params['googleLikeFields'] = $this->pSet->getGoogleLikeFields();
				$this->searchClauseObj = new SearchClause($params);
			}
		}
		
		//set session page size
		if(@$_REQUEST["pagesize"]) 
		{
			$_SESSION[$this->sessionPrefix."_pagesize"] = @$_REQUEST["pagesize"];
			$_SESSION[$this->sessionPrefix."_pagenumber"] = 1;
		}
		//set page size
		$this->pageSize = (integer) $_SESSION[$this->sessionPrefix."_pagesize"];
	}
	/**
	 * Add lookup settings to settings map
	 * Use on list and add pages
	 * @intellisense
	 */
	function addLookupSettings()
	{
		$this->settingsMap["fieldSettings"]["CategoryControl"] = array("default"=>"","jsName"=>"categoryField");
		$this->settingsMap["fieldSettings"]["DependentLookups"] = array("default"=>array(),"jsName"=>"depLookups");
		$this->settingsMap["fieldSettings"]["LCType"] = array("default"=>LCT_DROPDOWN,"jsName"=>"lcType");
		$this->settingsMap["fieldSettings"]["LookupTable"] = array("default"=>"","jsName"=>"lookupTable");
		$this->settingsMap["fieldSettings"]["SelectSize"] = array("default"=>1,"jsName"=>"selectSize");
		$this->settingsMap["fieldSettings"]["LinkField"] = array("default"=>"","jsName"=>"linkField");
		$this->settingsMap["fieldSettings"]["DisplayField"] = array("default"=>"","jsName"=>"dispField");
		$this->settingsMap["fieldSettings"]["freeInput"] = array("default"=>false,"jsName"=>"freeInput");
		$this->settingsMap["fieldSettings"]["autoCompleteFieldsAdd"] = array("default"=>array(),"jsName"=>"autoCompleteFieldsAdd");
		$this->settingsMap["fieldSettings"]["autoCompleteFieldsEdit"] = array("default"=>array(),"jsName"=>"autoCompleteFieldsEdit");
	}
	
	/**
	 * Fill global settings
	 * @intellisense
	 */
	function fillGlobalSettings()
	{
		$this->jsSettings["global"] = array();
		foreach($this->settingsMap["globalSettings"] as $key => $val)
			$this->jsSettings["global"][$key] = $val;
		// start augment id from this value	
		$this->jsSettings["global"]['idStartFrom'] = $this->flyId;	
	}
	
	/**
	 * Fill table settings
	 * @intellisense
	 */
	function fillTableSettings() 
	{
		foreach($this->settingsMap["tableSettings"] as $key => $val)
		{
			$tData = $this->pSet->getTableData(".".$key);

			$isDefault = false;
			if(is_array($tData))
				$isDefault = !count($tData);
			else if(!is_array($val['default']))
				$isDefault = ($tData == $val['default']);
			
			if(!$isDefault)
				$this->jsSettings['tableSettings'][$this->tName][$val['jsName']] = $tData;		
		}	
	}
		
	/**
	 * Fill field settings for current table 
	 * @intellisense
	 */
	function fillFieldSettings()
	{
		$arrFields = $this->pSet->getFieldsList();
		foreach($arrFields as $fName)
		{
			if(!array_key_exists($fName, $this->jsSettings['tableSettings'][$this->tName]['fieldSettings']))
				$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName] = array();
			
			if(!array_key_exists($this->pageType, $this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName]))
				$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName][$this->pageType] = array();
						
			$matchDK = false;	
			if($this->matchWithDetailKeys($fName))
				$matchDK = true;
			
			foreach($this->settingsMap["fieldSettings"] as $key => $val)
			{
				$fData = $this->pSet->getFieldData($fName, $key);
				
				if($key != "validateAs")
				{
					if($key == "EditFormat")
					{
						if($matchDK)
							$fData = EDIT_FORMAT_READONLY;
					}
					elseif($key == "RTEType")
					{
						if($this->pSet->isUseRTEBasic($fName))
							$fData = "RTE";
						elseif($this->pSet->isUseRTEFCK($fName)){
							$fData = "RTECK";
							if(!$this->isUseCK)
								$this->isUseCK = true;
							$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName][$this->pageType]['nWidth'] = $this->pSet->getNCols($fName);
							$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName][$this->pageType]['nHeight'] = $this->pSet->getNRows($fName);
						}	
						elseif($this->pSet->isUseRTEInnova($fName))
							$fData = "RTEINNOVA";
					}
					elseif($key == "autoCompleteFieldsAdd")
					{
						$fData = $this->pSet->getAutoCompleteFields($fName);
					}
					elseif($key == "autoCompleteFieldsEdit")
					{
						if($this->pSet->isAutoCompleteFieldsOnEdit($fName))
							$fData = $this->pSet->getAutoCompleteFields($fName);
						else
							$fData = array();
					}
					
					$isDefault = false;
					if(is_array($fData))
						$isDefault = !count($fData);
					else if(!is_array($val['default']))
						$isDefault = ($fData === $val['default']);
					
					if(!$isDefault && !$matchDK)
						$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName][$this->pageType][$val['jsName']] = $fData;
					else if($matchDK && ($key == "EditFormat" || $key == "strName"))
						$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName][$this->pageType][$val['jsName']] = $fData;
				}
				elseif(!$matchDK)
					$this->fillValidation($fData,$val,$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$fName][$this->pageType]);
			}
			
			$this->jsSettings['tableSettings'][$this->tName]['isUseCK'] = $this->isUseCK;
			
			if (!empty($this->googleMapCfg) and $this->googleMapCfg['isUseGoogleMap']){
				$this->jsSettings['tableSettings'][$this->tName]['isUseGoogleMap'] = true;
				$this->jsSettings['tableSettings'][$this->tName]['googleMapCfg']=$this->googleMapCfg;	
			}
			
			if($this->pSet->getLookupTable($fName))
				$this->jsSettings['global']['shortTNames'][$this->pSet->getLookupTable($fName)] = GetTableURL($this->pSet->getLookupTable($fName));
				
			if($this->pSet->getEditFormat($fName)=='Time')
				$this->fillTimePickSettings($fName);
		}
		
		if($this->pageType==PAGE_REGISTER)
			$this->addConfirmToFieldSettings();
		
			}
	/**
	 * Match field with details keys
	 * return boolean true or false
	 *
	 * @param string $fName - field name
	 * @return boolean
	 * @intellisense
	 */
	function matchWithDetailKeys($fName)
	{
		$match = false;
		if($this->detailKeysByM)
		{
			for($j=0;$j<count($this->detailKeysByM);$j++)
			{
				if($this->detailKeysByM[$j]==$fName)
				{
					$match = true;
					break;
				}
			}
		}
		return $match;
	}		
	/**
	 * Fill preload array for js settings
	 * Use on Add, Edit, Register pages and for search fields only
	 *
	 * @param string $fName - field name
	 * @param string $fval - value of field
	 * @return false or array of preload data
	 * @intellisense
	 */
	function fillPreload($fName,$value)
	{
		$preload = false;
		if(!$this->matchWithDetailKeys($fName) && $this->pSet->useCategory($fName))
		{
			if($this->pageType == PAGE_ADD || $this->pageType == PAGE_EDIT || $this->pageType == PAGE_REGISTER)
				$preload = $this->getPreloadArr($fName, $value);
			else
				$preload = $this->getSearchPreloadArr($fName, $value);
		}
		return $preload;
	}
	/**
	 * Check is current field depend on
	 *
	 * @param string $fName - current field name
	 * @return string - category control field
	 * @intellisense
	 */
	function isDependOnField($fName)
	{
		if(($this->pSet->getEditFormat($fName) != EDIT_FORMAT_LOOKUP_WIZARD || $this->pSet->getEditFormat($fName) != EDIT_FORMAT_RADIO) && !$this->pSet->useCategory($fName))
			return false;
		
		return $this->pSet->getCategoryControl($fName);	
	}
	/**
	 * Return JS for preload dependent ctrl 
	 *
	 * @param string $fName - field name
	 * @param string $fval - value of field
	 * @return array
	 * @intellisense
	 */
	function getPreloadArr($fName,$value)
	{
		// category control field
		$strCategoryControl = $this->isDependOnField($fName);
		if($strCategoryControl === false)
			return false;
		
		// Is field appear or not
		$fieldAppear=true;
		if($this->pageType == PAGE_ADD)
		{
			if(!$this->pSet->AppearOnInlineAdd($fName))
				$fieldAppear=($this->mode!=ADD_INLINE);
			elseif(!$this->pSet->AppearOnAddPage($fName))
				$fieldAppear=($this->mode==ADD_INLINE);
			
			// Is category control appear or not
			$categoryFieldAppear = $this->mode==ADD_INLINE ? $this->pSet->AppearOnInlineAdd($strCategoryControl) 
				: $this->pSet->AppearOnAddPage($strCategoryControl);
		}	
		elseif($this->pageType == PAGE_EDIT)
		{
			if(!$this->pSet->AppearOnInlineEdit($fName))
				$fieldAppear=($this->mode!=EDIT_INLINE);
			elseif(!$this->pSet->AppearOnEditPage($fName))
				$fieldAppear=($this->mode==EDIT_INLINE);
			$categoryFieldAppear=true;	
		}
		else{
				if($strCategoryControl)
					$categoryFieldAppear = true;
				else
					$categoryFieldAppear = false;
			}
		
		if(!$fieldAppear)
			return false;
			
		if ($this->pSet->isFreeInput($fName)){
			$output = array(0=>@$value[$fName], 1=>@$value[$fName]);
		}else{
			$output = loadSelectContent($this->pageType, $fName, @$value[$strCategoryControl], $categoryFieldAppear, @$value[$fName]);
		}
			
		
		$valF = "";
		if(count($value))
			$valF = $value[$fName];
		if($this->pageType==PAGE_EDIT)
		{	
			if($this->pSet->SelectSize($fName) == 1 && $this->pSet->lookupControlType($fName) != LCT_CBLIST)
				$fVal = $valF;
			else
				$fVal = splitvalues($valF);
		}
		else
			$fVal = $valF;
		
		return array('vals'=> $output, "fVal"=>$fVal);
	}	
	
	/**
	 * Common assign for diferent mode on list page
	 * Branch classes add to this method its individualy code
	 * @intellisense
	 */
	function commonAssign() 
	{
		$this->xt->displayBrickHidden("searchpanel");
		if (isMobile())
		{
			$this->xt->displayBrickHidden("vmenu");
			$this->xt->displayBrickHidden("backbutton");
			$this->xt->displayBrickHidden("fulltext_mobile");
			
			//$this->xt->displayBrickHidden("vsearch2");
		}
	}
	
	/**
	 * Return name of parent field
	 *
	 * @param string $fName
	 * @return string
	 * @intellisense
	 */
	function getParentCtrlName($fName) {
		return $this->pSet->getCategoryControl($fName); 
	}
	
	/**
	 * Return parent value for dependent ctrl
	 * Used value of first parent field
	 *
	 * @param string $fName
	 * @return string
	 * @intellisense
	 */
	function getParentVal($fName)
	{		
		$categoryFieldParams = $this->searchClauseObj->getSearchCtrlParams($this->getParentCtrlName($fName));
		if (count($categoryFieldParams))
			return $categoryFieldParams[0]['value1'];
		else
			return false;	
	}
	
	/**
	 * Return JS for preload dependent ctrl for search fields
	 *
	 * @param string $fName - field name
	 * @param string $fval - value of field
	 * @return array
	 * @intellisense
	 */
	function getSearchPreloadArr($fName, $fval)
	{
		// if no parent in project settings
		if ($this->pSet->getEditFormat($fName) != EDIT_FORMAT_LOOKUP_WIZARD && !$this->pSet->useCategory($fName))
			return false;
		
		// if parent exist in settings
		$parentFieldName = $this->pSet->getCategoryControl($fName);
		$parentVal = $this->getParentVal($fName);
		$doFilter = true;
		// if no filter f parent doesn't exist or it's value is empty
		if ($parentVal===false || $parentVal==='')
			$doFilter = false;
			
		$output = loadSelectContent($this->pageType != PAGE_LIST ? $this->pageType : PAGE_SEARCH, $fName, 
			$parentVal, $doFilter, $fval[$fName]);

		return array('vals'=> $output, "fVal"=>$fval[$fName]);	
	}
	
	/**
	 * Add confirm field to field settings
	 * Use for register page only
	 * @intellisense
	 */	
	function addConfirmToFieldSettings()
	{
		$arrSetVals = array();
		$arrSetVals['strName'] = 'confirm';
		$arrSetVals['EditFormat'] = 'Password';
		$arrSetVals['validation']['validationArr'][] = 'IsRequired';
		$this->jsSettings['tableSettings'][$this->tName]['fieldSettings']['confirm'][$this->pageType] = $arrSetVals;
	}
	
	/**
	* Add fields to  field settings
	* Use for admin members page with Active Directory
	* @intellisense
	*/
	function addFieldsToFieldSettingsforAD()
	{
		$arrSetVals = array();
		$arrSetVals['strName'] = 'displayname';
		$arrSetVals['EditFormat'] = 'Text Field';
		$arrSetVals['validation']['validationArr'][] = 'IsRequired';
		$this->jsSettings['tableSettings'][$this->tName]['fieldSettings']['displayname'][$this->pageType] = $arrSetVals;
		
		$arrSetVals = array();
		$arrSetVals['strName'] = 'name';
		$arrSetVals['EditFormat'] = 'Text Field';
		$arrSetVals['validation']['validationArr'][] = 'IsRequired';
		$this->jsSettings['tableSettings'][$this->tName]['fieldSettings']['name'][$this->pageType] = $arrSetVals;
		
		$arrSetVals = array();
		$arrSetVals['strName'] = 'category';
		$arrSetVals['EditFormat'] = 'Text Field';
		$arrSetVals['validation']['validationArr'][] = 'IsRequired';
		$this->jsSettings['tableSettings'][$this->tName]['fieldSettings']['category'][$this->pageType] = $arrSetVals;
	}
	
	
	/**
	 * Add captcha field to field settings
	 * Use for register page only
	 * @intellisense
	 */	
	function addCaptchaToFieldSettings()
	{
		$arrSetVals = array();
		$arrSetVals['strName'] = 'captcha';
		$arrSetVals['EditFormat'] = 'Text Field';
		$arrSetVals['validation']['validationArr'][] = 'IsRequired';
		$this->jsSettings['tableSettings'][$this->tName]['fieldSettings']['captcha'][$this->pageType] = $arrSetVals;
	}

	/**
	 * Fill validation for current field
	 * @intellisense
	 */
	function fillValidation($fData,$val,&$arrSetVals)
	{
		if(count($fData))
		{
			$arrSetVals[$val['jsName']]["validationArr"] = $fData['basicValidate'];
			if(array_key_exists("regExp",$fData))
				$arrSetVals[$val['jsName']]["regExp"] = $fData["regExp"];
		}
	}
	
	/**
	 * Get array of fields by current page type
	 * return array - of fields
	 * @intellisense
	 */
	function getFieldsByPageType()
	{
		$useInline = false;
		if($this->pageType == PAGE_EDIT)
		{
			if($this->pageEditLikeInline)
			{
				$useInline = true;
				$allfields = $this->pSet->getInlineEditFields();
			}else	
				$allfields = $this->pSet->getEditFields();
		}
		else if($this->pageType == PAGE_ADD)
		{
			if($this->pageAddLikeInline)
			{	
				$useInline = true;
				$allfields = $this->pSet->getInlineAddFields();
			}else
				$allfields = $this->pSet->getAddFields();
		}
		else if($this->pageType == PAGE_LIST)
		{
			$allfields = $this->pSet->getListFields();
		}
		else
			$allfields = $this->pSet->getFieldsList();
		$arrFields = array();
		foreach($allfields as $fName)
		{
			if($useInline)
				$app = $this->pSet->AppearOnCurrentPage($fName, $this->pageType, true);
			else
				$app = $this->pSet->AppearOnCurrentPage($fName, $this->pageType);
			if($app === 'break')
				break;
			elseif(!$app)
				continue;
			$arrFields[] = $fName;
		}	
		return $arrFields;	
	}
	
	/**
	 * Fill all settings for current table 
	 * @intellisense
	 */
	function fillSettings()
	{
		$this->fillGlobalSettings();
		$this->fillTableSettings();
		$this->fillFieldSettings();	
	}
	
	/**
	 * Fill tool tips for current table fields
	 * @param $fName - filed name
	 * @intellisense
	 */
	function fillFieldToolTips($fName){
		$toolTipText = GetFieldToolTip($this->tName, $fName);
		if (strlen($toolTipText)){ 
			$this->controlsMap['toolTips'][$fName] = $toolTipText;
		}
	}
	
	/**
	 * Fill controls map 
	 * For add, edit, search pages - controls
	 * 
	 * @param $arr - array of settings for one control
	 * @param $addSet - add additional settings to control
	 * @intellisense
	 */		
	function fillControlsMap($arr,$addSet = false,$fName="")
	{
		if(!$addSet)
		{
			foreach($arr as $key=>$val)
				$this->controlsMap[$key][] = $val;
		}
		else{
				foreach($arr as $key=>$val)
				{
					foreach($val as $vkey=>$vval)
					{
						if(!$fName)
							$this->controlsMap[$key][count($this->controlsMap[$key])-1][$vkey] = $vval;
						else{
								for($i=0;$i<count($this->controlsMap[$key]);$i++)
								{
									if($this->controlsMap[$key][$i]['fieldName']==$fName)
									{
										$this->controlsMap[$key][$i][$vkey] = $vval;
										break;
									}	
								}		
							}		
					}	
				}	
			}		
	}
	
	/**
	 * Fill field settings for current table 
	 * @intellisense
	 */	
	function fillControlsHtmlMap()
	{
		$this->controlsHTMLMap[$this->tName] = array();
		$this->controlsHTMLMap[$this->tName][$this->pageType] = array();
		$this->controlsHTMLMap[$this->tName][$this->pageType][$this->id] = array();
		
		$this->controlsMap['gMaps'] = $this->googleMapCfg;
		if($this->searchClauseObj)
			$this->controlsMap["search"]["usedSrch"] = $this->searchClauseObj->isUsedSrch();
			
		foreach($this->controlsMap as $key=>$val)
			$this->controlsHTMLMap[$this->tName][$this->pageType][$this->id][$key] = $val;
			
		$this->viewControlsHTMLMap[$this->tName] = array();
		$this->viewControlsHTMLMap[$this->tName][$this->pageType] = array();
		$this->viewControlsHTMLMap[$this->tName][$this->pageType][$this->id] = array();
		
		foreach($this->viewControlsMap as $key => $val)
			$this->viewControlsHTMLMap[$this->tName][$this->pageType][$this->id][$key] = $val;
	}
	
	/**
	 * Fill jsSettings and controlsHTMLMap arrays for current table 
	 * @intellisense
	 */	
	function fillSetCntrlMaps()
	{
		if($this->isControlsMapFilled)
			return;
		$this->fillSettings();
		$this->fillControlsHTMLMap();
		$this->isControlsMapFilled = true;
	}
	
	
	/**
	 * Fill arrays of names tab group and section to controlsHTMLMap for current table
	 * @intellisense
	 */		
	function fillCntrlTabGroups()
	{
		$arrTabs = $this->getArrTabs();
		$this->controlsMap['tabs'] = array();
		$this->controlsMap['sections'] = array();
		
		if(!$arrTabs)
			return false;
		
		$beginGroup = false;
		$tabGroupName = "";
		
		for($i=0;$i<count($arrTabs);$i++)
		{
			$tabC = $arrTabs[$i];//current tab
			$tabN = (($i+1)<count($arrTabs) ? $arrTabs[$i+1] : false);//next tab
			if(!$tabC['nType'])
			{
				if(!$beginGroup)
				{
					$beginGroup = true;
					$tabGroupName = $tabC['tabId'];
				}
				
				if($beginGroup)
				{
					if(!$tabN || $tabN['nType'] || $tabN['tabGroup']!=$tabC['tabGroup'])
					{
						//fill array of tabs with name of tab groups
						$tabsAndFields = array();
						for($j=0;$j<count($arrTabs);$j++)
						{
							if($arrTabs[$j]['tabGroup'] == $tabC['tabGroup'])
							{
								$tabsAndFields[$arrTabs[$j]['tabId']] = array();
								for($f=0;$f<count($arrTabs[$j]['arrFields']);$f++)
									$tabsAndFields[$arrTabs[$j]['tabId']][] = $arrTabs[$j]['arrFields'][$f];
							}
						}
						$this->controlsMap['tabs']['tabGroup_'.$tabGroupName] = $tabsAndFields;
						$beginGroup = false;
					}	
				}
			}
			else{
					//fill array of sections with name sections
					$this->controlsMap['sections']['section_'.$tabC['tabId']] = array();
					for($f=0;$f<count($arrTabs[$i]['arrFields']);$f++)
						$this->controlsMap['sections']['section_'.$tabC['tabId']][] = $arrTabs[$i]['arrFields'][$f];
				}
		}
	}
	
	/**
	 * Check are fields appaer in tabs for current page or not
	 * return boolean
	 * @intellisense
	 */	
	function isAppearOnTabs($fName)
	{
		$match = false;
		$arrTabs = $this->getArrTabs();
		if(!$arrTabs)
			return $match;
		foreach($arrTabs as $tab=>$val){
			if(in_array($fName, $val['arrFields'])){
				$match = true;
				break;
			}
		}
		return $match;
	}
	/**
	 * Get array of tabs in accordance with page type
	 * return boolean
	 * @intellisense
	 */	
	function getArrTabs()
	{
		if($this->pageType == PAGE_EDIT)
			return $this->arrEditTabs;
		elseif($this->pageType == PAGE_ADD)
			return $this->arrAddTabs;
		elseif($this->pageType == PAGE_VIEW)
			return $this->arrViewTabs;
		else
			return false;
	}
	
	/**
	 * Fill timepicker settings for current field
	 * @intellisense
	 */		
	function fillTimePickSettings($field,$value="")
	{
		$timeAttrs = $this->pSet->getFormatTimeAttrs($field);
		if(count($timeAttrs) && $timeAttrs["useTimePicker"])
		{
			$convention = $timeAttrs["hours"];
			$locAmPm = getLacaleAmPmForTimePicker($convention, true);
			$tpVal = getValForTimePicker($this->pSet->getFieldType($field),$value,$locAmPm['locale']);
			
			$range = array();
			if($convention==24)
			{
				for($h = 0;$h < $convention;$h ++)
					$range[]= $h;
			}
			else
			{
				for($h = 1;$h <= $convention;$h ++)
					$range[] = $h;
			}
			
			$minutes = array();
			for($m = 0; $m < 60; $m += $timeAttrs["minutes"])
				$minutes[] = $m;
			
			//settings
			$timePickSet = array('convention'=>$convention,
								 'range'=>$range,
								 'apm'=>array($locAmPm['am'],$locAmPm['pm']),
								 'rangeMin'=>$minutes,
								 'locale'=>$locAmPm['locale'],
								 'showSec'=>$timeAttrs["showSeconds"]);
			
			if(count($tpVal['dbtime'])>0)
				$timePickSet['hover'] = array('0'=>$tpVal['dbtime'][3],'1'=>$tpVal['dbtime'][4],'2'=>$tpVal['dbtime'][5]);
			
			if(!array_key_exists($field,$this->jsSettings['tableSettings'][$this->tName]['fieldSettings']))	
			{
				$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$field] = array();
				$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$field][$this->pageType] = array();
				$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$field][$this->pageType]['timePick'] = $timePickSet;
			}
			elseif(!array_key_exists("timePick",$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$field][$this->pageType]))
				$this->jsSettings['tableSettings'][$this->tName]['fieldSettings'][$field][$this->pageType]['timePick'] = $timePickSet;
			
			$this->fillControlsMap(array('controls'=>array('open'=>($tpVal['val'] ? true : false))),true,$field);
		}
	}
		
	/**
	 * Assign body end
	 * @intellisense
	 */	
	function assignBodyEnd(&$params) 
	{
	
		$this->fillSetCntrlMaps();
		echo "<script>
			window.controlsMap = ".my_json_encode($this->controlsHTMLMap).";
			window.viewControlsMap = ".my_json_encode($this->viewControlsHTMLMap).";
			window.settings = ".my_json_encode($this->jsSettings)."; 
			</script>\r\n";
				echo "<script language=\"JavaScript\" src=\"include/runnerJS/RunnerAll.js\"></script>\r\n";
		echo "<script>".$this->PrepareJS()."</script>";
	}
		
	/**
	 * Generates new id, same as flyId on front-end
	 *
	 * @return int
	 * @intellisense
	 */
	function genId()
	{
		$this->flyId++;
		$this->recId = $this->flyId;
		return $this->flyId;
	}
	
	/**
	  * Get page type
	  * @intellisense
	  */
	function getPageType()
	{
		return $this->pageType;
	}
	
	/**
	  * Add js files for page
	  * @intellisense
	  */
	function AddJSFileNoExt($file)
	{
		$this->includes_js[] = $file;
	}
	
	function AddJSFile($file, $req1 = "", $req2 = "", $req3 = "")
	{
		$this->includes_js[] = $file;
		if($req1!="")
			$this->includes_jsreq[$file] = array($req1);
		if($req2!="")
			$this->includes_jsreq[$file][] = $req2;
		if($req3!="")
			$this->includes_jsreq[$file][] = $req3;
	}
	
	/**
	  * Grab all js files
	  * @intellisense
	  */
	function grabAllJsFiles()
	{
		$jsFiles = array();
		foreach($this->includes_js as $file)
		{
			$jsFiles[$file] = array();
			if(array_key_exists($file, $this->includes_jsreq))
				$jsFiles[$file] = $this->includes_jsreq[$file];
		}
		$this->includes_js = array();
		$this->includes_jsreq = array();
		return $jsFiles;
	}
	
	/**
	  * Grab all css files
	  * @intellisense
	  */
	function copyAllJsFiles($jsFiles)
	{
		foreach($jsFiles as $file=>$reqFiles)
		{
			$this->includes_js[] = $file;
			
			if(array_key_exists($file,$this->includes_jsreq)){
				foreach($reqFiles as $rFile){
					if(array_key_exists($rFile,$this->includes_jsreq[$file]))
						continue;
					$this->includes_jsreq[$file][] = $rFile;
				}
			}else
				$this->includes_jsreq[$file] = $reqFiles;
		}
	}
	
	/**
	  * Add css files for page
	  * @intellisense
	  */
	function AddCSSFile($file)
	{
		$this->includes_css[] = $file;
	}
	
	/**
	  * Add css files (which have an IE extension) for page
	  * 
	  * @param {string} path to css file
	  * @intellisense
	  */
	function AddCSSIEFile($file)
	{
		$this->includes_cssIE[] = $file;
	}
	
	/**
	  * Grab all css files
	  * @intellisense
	  */
	function grabAllCssFiles()
	{
		$cssFiles = $this->includes_css;
		$this->includes_css = array();
		return $cssFiles;
	}
	/**
	  * Copy all css files
	  * @intellisense
	  */
	function copyAllCssFiles($cssFiles)
	{
		foreach($cssFiles as $file)
			$this->AddCSSFile($file);
	}
	
	/**
	  * Load js and css files
	  * @intellisense
	  */
	function LoadJS_CSS()
	{
		
		$this->includes_js = array_unique($this->includes_js);
		$this->includes_css = array_unique($this->includes_css);
		$this->includes_cssIE = array_unique($this->includes_cssIE);
		$out = "";
		if (count($this->includes_css)){
			$out .= "\r\nRunner.util.ScriptLoader.loadCSS([";
			foreach($this->includes_css as $file)
			{
				$out.="'".$file."', ";
			}
			$out = substr($out, 0, strlen($out)-2);
			$out .= "]);\r\n";
		}
		if (count($this->includes_cssIE)){
			$out .= "Runner.util.ScriptLoader.loadCSS([";
			foreach($this->includes_cssIE as $file)
			{
				$out.="'".$file."', ";
			}
			$out = substr($out, 0, strlen($out)-2);
			$out .= "], true);\r\n";
		}
		
		foreach($this->includes_js as $file)
		{
			$out .= "Runner.util.ScriptLoader.addJS(['".$file."']";
			if(array_key_exists($file,$this->includes_jsreq))
			{
				foreach($this->includes_jsreq[$file] as $req)
					$out.=",'".$req."'";
			}
			$out.=");\r\n";
		}
		$out.= " Runner.util.ScriptLoader.load();";
		return $out;
	}
	
	/**
	  * Set languge params for page
	  * @intellisense
	  */
	function setLangParams()
	{
	}
	
	/**
	  * Add general js or css files for pages
	  * @intellisense
	  */
	function addCommonJs() 
	{
		if ($this->debugJSMode === true)
		{
			$this->AddJSFile("include/customlabels.js");
			$this->AddJSFile("include/runnerJS/ControlConstants.js");
			$this->AddJSFile("include/runnerJS/RunnerEvent.js");
			$this->AddJSFile("include/runnerJS/Validate.js","include/runnerJS/RunnerEvent.js");
			$this->AddJSFile("include/runnerJS/ControlManager.js","include/runnerJS/Validate.js");
			$this->AddJSFile("include/runnerJS/button.js", "include/runnerJS/ControlManager.js");	
			$this->AddJSFile("include/runnerJS/Control.js", "include/runnerJS/ControlManager.js");
			$this->AddJSFile("include/runnerJS/ViewControl.js", "include/runnerJS/ControlManager.js");
			$this->AddJSFile("include/runnerJS/ReadOnly.js", "include/runnerJS/Control.js");				
			$this->AddJSFile("include/runnerJS/TextAreaControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/TextFieldControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/TimeFieldControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/RteControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/FileControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/MultiUploadControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/DateFieldControl.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/LookupWizard.js", "include/runnerJS/Control.js");
			$this->AddJSFile("include/runnerJS/RadioControl.js", "include/runnerJS/LookupWizard.js");
			$this->AddJSFile("include/runnerJS/DropDown.js", "include/runnerJS/LookupWizard.js");
			$this->AddJSFile("include/runnerJS/CheckBox.js", "include/runnerJS/LookupWizard.js");
			$this->AddJSFile("include/runnerJS/TextFieldLookup.js", "include/runnerJS/LookupWizard.js");
			$this->AddJSFile("include/runnerJS/EditBoxLookup.js", "include/runnerJS/TextFieldLookup.js");
			$this->AddJSFile("include/runnerJS/ListPageLookup.js", "include/runnerJS/TextFieldLookup.js");
			$this->AddJSFile("include/runnerJS/InlineEdit.js");
			
			$this->AddJSFile("include/runnerJS/pages/PageConstants.js", "include/runnerJS/ListPageLookup.js");	
			$this->AddJSFile("include/runnerJS/pages/RunnerDefaults.js", "include/runnerJS/pages/PageConstants.js");	
			$this->AddJSFile("include/runnerJS/pages/PageManager.js", "include/runnerJS/pages/RunnerDefaults.js");
			$this->AddJSFile("include/runnerJS/pages/PageSettings.js", "include/runnerJS/pages/PageManager.js");
			$this->AddJSFile("include/runnerJS/DetPreview.js", "include/runnerJS/pages/PageSettings.js");			
			$this->AddJSFile("include/runnerJS/pages/RunnerPage.js", "include/runnerJS/pages/PageSettings.js");
			$this->AddJSFile("include/runnerJS/pages/SearchPage.js", "include/runnerJS/pages/RunnerPage.js");
			$this->AddJSFile("include/runnerJS/pages/ViewPage.js", "include/runnerJS/pages/RunnerPage.js");
			$this->AddJSFile("include/runnerJS/pages/EditorPage.js", "include/runnerJS/pages/RunnerPage.js");
			$this->AddJSFile("include/runnerJS/pages/AddPageFly.js", "include/runnerJS/pages/AddPage.js");
			$this->AddJSFile("include/runnerJS/pages/AddPage.js", "include/runnerJS/pages/EditorPage.js");
			$this->AddJSFile("include/runnerJS/pages/EditPage.js", "include/runnerJS/pages/EditorPage.js");
			
			$this->AddJSFile("include/runnerJS/pages/DataPageWithSearch.js", "include/runnerJS/pages/RunnerPage.js");
			$this->AddJSFile("include/runnerJS/pages/ListPageCommon.js", "include/runnerJS/pages/DataPageWithSearch.js");
			$this->AddJSFile("include/runnerJS/pages/ListPageFly.js", "include/runnerJS/pages/ListPageCommon.js");
			$this->AddJSFile("include/runnerJS/pages/ListPage.js", "include/runnerJS/pages/ListPageCommon.js", "include/runnerJS/DetPreview.js");
			if (isMobile()) {
				$this->AddJSFile("include/runnerJS/pages/ListPageMobile.js", "include/runnerJS/pages/ListPage.js");
				$this->AddJSFile("include/runnerJS/pages/ReportPageMobile.js", "include/runnerJS/pages/ListPageMobile.js");
				$this->AddJSFile("include/runnerJS/pages/ChartPageMobile.js", "include/runnerJS/pages/ListPageMobile.js");
			}
			else {
				$this->AddJSFile("include/runnerJS/pages/ReportPage.js", "include/runnerJS/pages/DataPageWithSearch.js");
				$this->AddJSFile("include/runnerJS/pages/ChartPage.js", "include/runnerJS/pages/DataPageWithSearch.js");
			}
			$this->AddJSFile("include/runnerJS/pages/ListPageAjax.js", "include/runnerJS/pages/ListPage.js");
			$this->AddJSFile("include/runnerJS/pages/ListPageDP.js", "include/runnerJS/pages/ListPage.js");
			
			$this->AddJSFile("include/runnerJS/pages/CheckboxesPage.js", "include/runnerJS/pages/ListPage.js");
			$this->AddJSFile("include/runnerJS/pages/MembersPage.js", "include/runnerJS/pages/CheckboxesPage.js");
			$this->AddJSFile("include/runnerJS/pages/RightsPage.js", "include/runnerJS/pages/CheckboxesPage.js");
						$this->AddJSFile("include/runnerJS/pages/ExportPage.js", "include/runnerJS/pages/RunnerPage.js");
			$this->AddJSFile("include/runnerJS/pages/ImportPage.js", "include/runnerJS/pages/RunnerPage.js");
			$this->AddJSFile("include/runnerJS/pages/RegisterPage.js", "include/runnerJS/pages/RunnerPage.js");
			
			$this->AddJSFile("include/runnerJS/SearchForm.js");
			$this->AddJSFile("include/runnerJS/SearchField.js");
			$this->AddJSFile("include/runnerJS/SearchFormWithUI.js", "include/runnerJS/SearchForm.js");
			$this->AddJSFile("include/runnerJS/SearchController.js", "include/runnerJS/SearchFormWithUI.js");
			$this->AddJSFile("include/runnerJS/RunnerForm.js");
			
			$this->AddJSFile("include/runnerJS/RunnerBricks.js");
			$this->AddJSFile("include/runnerJS/RunnerMenu.js");
			if($this->lockingObj)
				$this->AddJSFile("include/runnerJS/RunnerLocking.js");
			if($this->is508)
				$this->AddJSFile("include/runnerJS/RunnerSection508.js");	
			
			if ($this->pSet->isAddPageEvents())
			{
					$this->AddJSFile("include/runnerJS/events/pageevents_".$this->shortTableName.".js", "include/runnerJS/pages/PageSettings.js", 
						"include/runnerJS/button.js");
			}
		
				}
		else
		{
			if ($this->pSet->isAddPageEvents())
			{
			 	if( $this->mode != LIST_DETAILS)
			 	{
					$this->AddJSFile("include/runnerJS/events/pageevents_".$this->shortTableName.".js", "include/runnerJS/RunnerControls.js", 
						"include/runnerJS/RunnerPages.js");
			 	}
			 	else
			 		$this->AddJSFile("include/runnerJS/events/pageevents_".$this->shortTableName.".js");
			}
				}
		$this->AddJSFile("include/yui/yui-min.js");
		
		if ($this->isUseAjaxSuggest)
		{
			$this->AddJSFile("include/ajaxsuggest.js");
		}
		elseif(count($this->allDetailsTablesArr))
		{
			for($i = 0; $i < count($this->allDetailsTablesArr); $i ++) 
			{
				if($this->allDetailsTablesArr[$i]['previewOnList'] == DP_POPUP)
					$this->AddJSFile("include/ajaxsuggest.js");
					break;
			}
		}
		
		if($this->timepicker)
		{
			$this->AddJSFile("include/ui.js");
			$this->AddJSFile("include/jquery.utils.js","include/ui.js");
			$this->AddJSFile("include/ui.dropslide.js","include/jquery.utils.js");
			$this->AddJSFile("include/jquery.timepickr.js","include/ui.dropslide.js");
		}
		
		if ($this->isUseToolTips){
			if (isMobile())
				$this->AddJSFile("include/dimensions.mobile.js");
			else 
				$this->AddJSFile("include/dimensions.js");
		}
		
		if($this->isUseCK)
			$this->addJSFile("plugins/ckeditor/ckeditor.js");
			
		// commented for feature 6869
		if(($this->isUseAudio || $this->pSet->isUseAudioOnDetails()) && $this->pageType!=PAGE_ADD && $this->pageType!=PAGE_EDIT)
			$this->AddJSFile("include/runnerJS/ymp.js");
		
		$this->addControlsJSAndCSS();
		if($this->pageType == PAGE_LIST)
			$this->setNoScroll();
	}
	
	function addControlsJSAndCSS()
	{
		$this->controls->addControlsJSAndCSS();
		$this->viewControls->addControlsJSAndCSS();
	}
	
	function setNoScroll()
	{
		$hasNoScroll = $this->viewControls->hasNoScrollControls();
		if(!$hasNoScroll)
		{
			for($i = 0; $i < count($this->pSet->_detailsTableData); $i++)
			{
				if($this->pSet->_detailsTableData[$i]['previewOnList'] == 1)
				{
					$detailVCC = new ViewControlsContainer(
						new ProjectSettings($this->pSet->_detailsTableData[$i]['dDataSourceTable'], PAGE_LIST),
						PAGE_LIST);
					if($detailVCC->hasNoScrollControls())
					{
						$hasNoScroll = true;
						break;
					}
				}
			}
		}
		if($hasNoScroll)
			$this->jsSettings['tableSettings'][$this->tName]['noScroll'] = true;
	}
	
	/**
	  * Prepare js code
	  * @intellisense
	  */
	function PrepareJs()
	{
		return $js = $this->LoadJS_CSS();
	}
	
	function addButtonHandlers()
	{	
		if (!$this->pSet->isUsebuttonHandlers())
		{
			return false;
		}
		if ($this->debugJSMode === true)
		{
			$this->AddJSFile("include/runnerJS/events/pageevents_".$this->shortTableName.".js", "include/runnerJS/pages/PageSettings.js");
		}
		else 
		{
			$this->AddJSFile("include/runnerJS/events/pageevents_".$this->shortTableName.".js", "include/runnerJS/RunnerControls.js", "include/runnerJS/RunnerPages.js");
		}
		return true;
	}
	
	function setGoogleMapsParams($fieldsArr) 
	{
		$this->googleMapCfg['isUseMainMaps'] = $this->pSet->isUseMainMaps();
		$this->googleMapCfg['isUseFieldsMaps'] = $this->pSet->isUseFieldsMaps();
		if ($this->googleMapCfg['isUseFieldsMaps'])
		foreach($fieldsArr as $f)
		{
			if ($f['viewFormat'] == FORMAT_MAP)
			{
				$this->googleMapCfg['fieldsAsMap'][$f['fName']] = array();
				$fieldMap = $this->pSet->getMapData($f['fName']);
				
				$this->googleMapCfg['fieldsAsMap'][$f['fName']]['width'] = $fieldMap['width'] ? $fieldMap['width'] : 0;
				$this->googleMapCfg['fieldsAsMap'][$f['fName']]['height'] = $fieldMap['height'] ? $fieldMap['height'] : 0;
				$this->googleMapCfg['fieldsAsMap'][$f['fName']]['addressField'] = $fieldMap['address'];
				$this->googleMapCfg['fieldsAsMap'][$f['fName']]['latField'] = $fieldMap['lat'];
				$this->googleMapCfg['fieldsAsMap'][$f['fName']]['lngField'] = $fieldMap['lng'];
				$this->googleMapCfg['fieldsAsMap'][$f['fName']]['descField'] = $fieldMap['desc'];
				if (isset($fieldMap['zoom'])){
					$this->googleMapCfg['fieldsAsMap'][$f['fName']]['zoom'] = $fieldMap['zoom'];
				}
			}
		}
		$this->googleMapCfg['isUseGoogleMap'] = $this->googleMapCfg['isUseMainMaps'] || $this->googleMapCfg['isUseFieldsMaps'] || $this->mapsExists();
	}
	
	function addBigGoogleMapMarkers(&$data, $keys, $viewLink = '') 
	{
		foreach ($this->googleMapCfg['mainMapIds'] as $mapId)
		{
			$latF = $this->googleMapCfg['mapsData'][$mapId]['latField'];
			$lngF = $this->googleMapCfg['mapsData'][$mapId]['lngField'];
			$addressF = $this->googleMapCfg['mapsData'][$mapId]['addressField'];
			$descF = $this->googleMapCfg['mapsData'][$mapId]['descField'];
			
			$markerArr = array();
			$markerArr['lat'] = str_replace(",", ".", ($data[$latF] ? $data[$latF] : ''));
			$markerArr['lng'] = str_replace(",", ".", ($data[$lngF] ? $data[$lngF] : ''));
			$markerArr['address'] = $data[$addressF] ? $data[$addressF] : '';
			$markerArr['desc'] = $data[$descF] ? $data[$descF] : $markerArr['address'];
			$markerArr['link'] = $viewLink;
			$markerArr['recId'] = $this->recId;
			$markerArr['keys'] = $keys; 
			
			$this->googleMapCfg['mapsData'][$mapId]['markers'][] = $markerArr;
		}
	}
	// call addGoogleMapData before call  proccessRecordValue!!!
	function addGoogleMapData($fName, &$data, $keys = '', $viewLink = '')  
	{
		$fieldMap = $this->pSet->getMapData($fName);
		
		$mapData['mapFieldValue'] = $data[$fName];
		$address = $data[$fieldMap['address']] ? $data[$fieldMap['address']] : "";
		$lat =  str_replace(",", ".", ($data[$fieldMap['lat']] ? $data[$fieldMap['lat']] : ''));
		$lng =  str_replace(",", ".", ($data[$fieldMap['lng']] ? $data[$fieldMap['lng']] : ''));
		$desc = $data[$fieldMap['desc']] ? $data[$fieldMap['desc']] : $address;
		if (isset($fieldMap['zoom'])){
			$zoom = $fieldMap['zoom'];
		}else{
			$zoom = '';
		}
		  
		$mapData['fName'] = $fName;
		$mapData['zoom'] = $zoom;
		$mapData['type'] = 'FIELD_MAP';
		$mapData['markers'][] = array('address'=> $address, 'lat'=>$lat, 'lng'=>$lng, 'link'=>$viewLink, 'desc'=>$desc, 'recId'=>$this->recId, 'keys'=>$keys); 
		
		$this->googleMapCfg['mapsData']['littleMap_'.GoodFieldName($fName).'_'.$this->recId] = $mapData;
		$this->googleMapCfg['fieldMapsIds'][] = 'littleMap_'.GoodFieldName($fName).'_'.$this->recId;
		
		return $this->googleMapCfg['mapsData']['littleMap_'.GoodFieldName($fName).'_'.$this->recId];
	}
	
	function initGmaps()
	{
		if ($this->googleMapCfg['isUseGoogleMap'])
		{	
			foreach ($this->googleMapCfg['mainMapIds'] as $mapId)
			{
				if ($this->googleMapCfg['mapsData'][$mapId]['showCenterLink'] === 1)
				{
					$this->googleMapCfg['centerLinkText'] = $this->googleMapCfg['mapsData'][$mapId]['centerLinkText'];
					break;
				}
			}
			$this->googleMapCfg['id'] = $this->id;
			//$this->AddJSFile("include/json.js");
			$this->AddJSFile("include/runnerJS/gmap.js");
			if (!$this->googleMapCfg['APIcode'])
			{
				$this->googleMapCfg['APIcode'] = '';	
			}
			$this->controlsMap['gMaps'] = &$this->googleMapCfg;	
			//$this->AddJSFileNoExt('http://maps.google.com/maps?file=api&v=2&sensor=true&key='.$this->googleMapCfg['APIcode']);
		}
	}
	
	function addCenterLink(&$value, $fName)
	{
		if ($this->googleMapCfg['isUseMainMaps'])
		{
			foreach ($this->googleMapCfg['mainMapIds'] as $mapId)
			{
				// if no center link than continue;
				if ($this->googleMapCfg['mapsData'][$mapId]['addressField'] != $fName || !$this->googleMapCfg['mapsData'][$mapId]['showCenterLink'])
					continue;
				
				// if use user defined link if prop = 1 or use value if prop = 2				
				if($this->googleMapCfg['mapsData'][$mapId]['showCenterLink'] === 1)
					$value = $this->googleMapCfg['mapsData'][$mapId]['centerLinkText'];					
				
				return '<a href="#" type="centerOnMarker'.$this->id.'" recId="'.$this->recId.'">'.$value.'</a>';				
			}
		}
		return $value;
	}
	
	/**
	 * Get permissions for pages
	 * @intellisense
	 */
	function getPermissions($tName = "") 
	{
		$resArr = array();
		
		if(!$tName)
			$tName = $this->tName;
		$strPerm = GetUserPermissions($tName);
		
		$resArr["add"]=(strpos($strPerm, "A") !== false);
		$resArr["delete"]=(strpos($strPerm, "D") !== false);
		$resArr["edit"]=(strpos($strPerm, "E") !== false);
		$resArr["search"]=(strpos($strPerm, "S") !== false);
		$resArr["export"]=(strpos($strPerm, "P") !== false);
		$resArr["import"]=(strpos($strPerm, "I") !== false);
		
		return $resArr;
	}
		
	/**
	 * Check is event exists on current page
	 * @param {string} - event name
	 * @intellisense
	 */
	function eventExists($name)
	{
		if(!$this->eventsObject)
			return false;
		return $this->eventsObject->exists($name);
	}
	
	/**
	 * Check is captcha exists on current page
	 *
	 * @intellisense
	 */	
	function captchaExists()
	{
		if(!$this->eventsObject)
			return false;
		return $this->eventsObject->existsCAPTCHA($this->pageType);
	}
	
	/**
	 * Check is googlemaps exists on current page
	 *
	 * @intellisense
	 */	
	function mapsExists()
	{
		if(!$this->eventsObject)
			return false;
		return $this->eventsObject->existsMap($this->pageType);
	}
	
//	move this functions to viewpage or editpage class when they will be created
	function getNextPrevRecordKeys(&$data,$securityMode,&$next,&$prev)
	{
		global $conn;
		$next=array();
		$prev=array();
	
		if(@$_SESSION[$this->sessionPrefix."_noNextPrev"])
			return;
		$prevExpr = "";
		$nextExpr = "";
		$where_next="";
		$where_prev="";
		$order_next="";
		$order_prev="";

		require_once(getabspath('classes/orderclause.php'));
		$orderClause = new OrderClause($this);
		$orderClause->init();

		$query = $this->pSet->GetQueryObject();
		$where = $_SESSION[$this->sessionPrefix."_where"];
		if(!strlen($where))
			$where = SecuritySQL($securityMode);
		$having = $_SESSION[$this->sessionPrefix."_having"];
		
		$tKeys = $this->pSet->getTableKeys();

		if(!count($orderClause->fieldsList))
		{
			$_SESSION[$this->sessionPrefix."_noNextPrev"] = 1;
			return;
		}
		//	make  next & prev ORDER BY strings
		for($i = 0; $i < count($orderClause->fieldsList); $i++)
		{
			$field = $orderClause->fieldsList[$i];
			if(!$this->pSet->GetFieldByIndex($field->fieldIndex))
				continue;
			if($order_next == "")
			{
				$order_next = " ORDER BY ";
				$order_prev = " ORDER BY ";
			}
			else
			{
				$order_next .= ",";
				$order_prev .= ",";
			}
			$order_next .= $field->fieldIndex." ".$field->orderDirection;
			$order_prev .= $field->fieldIndex." ".($field->orderDirection == "DESC" ? "ASC" : "DESC");
		}

		// make next & prev where expressions
		
		$tail="";
		for($i = 0; $i < count($orderClause->fieldsList); $i++)
		{
			$field = $orderClause->fieldsList[$i];
			$fieldName = $this->pSet->GetFieldByIndex($field->fieldIndex);
			if(!$fieldName)
				continue;
			if(!$query->HasGroupBy())
				$fullName = GetFullFieldName($fieldName, $this->tName, false);
			else
				$fullName = AddFieldWrappers($fieldName);
			$asc = ($field->orderDirection == "ASC");
			if(!is_null($data[$fieldName]))
			{
			//	current field value is not null
				$value = $this->cipherer->MakeDBValue($fieldName, $data[$fieldName], "", "", true);
				$nextop = ($asc ? ">" : "<");
				$prevop = ($asc ? "<" : ">");
				$nextExpr = $fullName.$nextop.$value;
				$prevExpr = $fullName.$prevop.$value;
				if($nextop=="<")
					$nextExpr .= " or ".$fullName." IS NULL";
				else
					$prevExpr .= " or ".$fullName." IS NULL";
				if($i < count($orderClause->fieldsList) - 1)
				{
					$nextExpr .= " or ".$fullName."=".$value;
					$prevExpr .= " or ".$fullName."=".$value;
				}
			}
			else
			{
				$nextExpr = "";
				$prevExpr = "";				
			//	current field value is null
				if($asc)
					$nextExpr = $fullName." IS NOT NULL";
				else
					$prevExpr = $fullName." IS NOT NULL";
				
				if($i < count($orderClause->fieldsList) - 1)
				{
					if($nextExpr != "")
						$nextExpr.=" or ";
					$nextExpr .= $fullName." IS NULL";
					if($prevExpr != "")
						$prevExpr.=" or ";
					$prevExpr .= $fullName." IS NULL";
				}
			}
			if($nextExpr == "")
				$nextExpr = " 1=0 ";
			if($prevExpr == "")
				$prevExpr = " 1=0 ";
			
			// append expression to where clause
			if($i>0)
			{
				$where_next .= " AND ";
				$where_prev .= " AND ";
			}
			$where_next .= "(".$nextExpr;
			$where_prev .= "(".$prevExpr;
			
			$tail .=")";
		}
		$where_next = $where_next.$tail;
		$where_prev = $where_prev.$tail;

		if($where_next=="" or $order_next=="" or $where_prev=="" or $order_prev=="")
		{
			$_SESSION[$this->sessionPrefix."_noNextPrev"] = 1;
			return;
		}
//		make the resulting query
		if($query === null)
			return;
		
		if(!$query->HasGroupBy())
		{
			$oWhere = $query->Where();
			$where = whereAdd($where,$oWhere->toSql($query));
			$where_next = whereAdd($where_next,$where);
			$where_prev = whereAdd($where_prev,$where);
			$query->ReplaceFieldsWithDummies($this->pSet->getBinaryFieldsIndices());
			$sql_next = $query->toSql($where_next, $order_next);
			$sql_prev = $query->toSql($where_prev, $order_prev);
		}
		else
		{
			$oWhere = $query->Where();
			$oHaving = $query->Having();
			$where = whereAdd($where,$oWhere->toSql($query));
			$having = whereAdd($having,$oHaving->toSql($query));
			$query->ReplaceFieldsWithDummies($this->pSet->getBinaryFieldsIndices());
			$sql = "select * from (".$query->toSql($where, "", $having).") prevnextquery";
			$sql_next = $sql." WHERE ".$where_next.$order_next;
			$sql_prev = $sql." WHERE ".$where_prev.$order_prev;
		}
		if(GetGlobalData("returnToActualListPage", false))
		{
			if($prevExpr == " 1=0 ")
				$_SESSION[$this->sessionPrefix."_pagenumber"] = 1;
			else{
				$pageSQL = "select count(*) from (".$sql_prev.") tcount";
				$pageRes = db_query($pageSQL, $conn);
				$pageRow = db_fetch_numarray($pageRes);
				$currentRow = $pageRow[0];
				if($this->pageSize > 0)
					$pageSize = $this->pageSize;
				else
					$pageSize = $this->pSet->getInitialPageSize();
				$this->myPage = floor($currentRow / $pageSize) + 1;
				$_SESSION[$this->sessionPrefix."_pagenumber"] = $this->myPage;
			}
		}

		//	add record count options
		
				$res_next = db_query($sql_next,$conn);
		if($res_next)
		{
			if($row_next = $this->cipherer->DecryptFetchedArray($res_next))
			{
				foreach($tKeys as $i=>$k)
				{
					$next[$i] = $row_next[$k];
				}
			}
			db_closequery($res_next);
		}
		$res_prev = db_query($sql_prev,$conn);
		if($row_prev = $this->cipherer->DecryptFetchedArray($res_prev))
		{
			foreach($tKeys as $i=>$k)
			{
				$prev[$i] = $row_prev[$k];
			}
		}
		db_closequery($res_prev);
	}

	function doCaptchaCode()
	{
		global $globalEvents;
		
		if((!isset($_SESSION[$this->tName."_count_captcha"])) or ($_SESSION[$this->tName."_count_captcha"]>4)) 
			$_SESSION[$this->tName."_count_captcha"]=0;
				
		if(($this->pageType==PAGE_EDIT && @$_POST["a"]=="edited") || ($this->pageType==PAGE_ADD && @$_POST["a"]=="added") || ($this->pageType==PAGE_REGISTER && @$_POST["btnSubmit"] == "Register"))
			$sbmPage = true;
		else
			$sbmPage = false;
			
		if(($_SESSION[$this->tName."_count_captcha"]==0) or ($_SESSION[$this->tName."_count_captcha"]>4))
		{
			if($sbmPage)
			{	
				if(@strtolower(postvalue("value_captcha_".$this->id))!=strtolower(@$_SESSION["captcha"]))
				{
					if($this->pageType == PAGE_REGISTER)
					{
						$this->captchaId = $globalEvents->captchas[$this->pageType]['strName'];
						$globalEvents->callCAPTCHA($this);
					}
					else{
							$this->captchaId = $this->eventsObject->captchas[$this->pageType]['strName'];
							$this->eventsObject->callCAPTCHA($this);
						}
					$this->isCaptchaOk = false;
				}
				else
					$this->isCaptchaOk = true;
			}
			else{
					if($this->pageType == PAGE_REGISTER)
					{
						$this->captchaId = $globalEvents->captchas[$this->pageType]['strName'];
						$globalEvents->callCAPTCHA($this);
					}
					else{
							$this->captchaId = $this->eventsObject->captchas[$this->pageType]['strName'];
							$this->eventsObject->callCAPTCHA($this);
						}	
				}
			
			//create control and settings for captcha field, if it show on page 
			$controls = array('controls'=>array());
			$controls['controls']['ctrlInd'] = 0;
			$controls['controls']['id'] = $this->id;
			$controls['controls']['fieldName'] = 'captcha';
			$controls['controls']['mode'] = $this->pageType;
			$this->fillControlsMap($controls);
			$this->addCaptchaToFieldSettings();
		}
		elseif($sbmPage) 
			$this->isCaptchaOk = true;	
	}
	
	function createCaptcha($params)
	{	
		$captchaHTML = '<div class="captcha_block">
			<object width="210" height="65" data="securitycode.swf?ext=php" type="application/x-shockwave-flash">
				<param value="securitycode.swf?ext=php" name="movie"/>
				<param value="opaque" name="wmode"/>
				<a href="http://www.macromedia.com/go/getflashplayer"><img alt="Download Flash" src=""/></a>
			</object>
				<div style="white-space: nowrap;">'."Digite o código acima".':</div>
			<span id="edit'.$this->id.'_captcha_0">
				<input type="text" value="" name="value_captcha_'.$this->id.'" style="" id="value_captcha_'.$this->id.'"/>
				<font color="red">*</font>
			</span>';
		
		if(isset($this->isCaptchaOk) && !$this->isCaptchaOk)
			$captchaHTML .= '<div class="error">'."Código de segurança inválido".'</div>';
			
		echo $captchaHTML.'</div>';
	}
	
	function createPerPage()
	{
		$rpp = "";
		if($this->isTableType == "report" && $this->reportGroupFields)
		{
			$rpp.= "<select onChange=\"javascript: document.location='".htmlspecialchars(rawurlencode($this->shortTableName))."_report.php?pagesize='+this.options[this.selectedIndex].value;\">";
			for($i=0;$i<count($this->arrGroupsPerPage);$i++)
			{			
				if($this->arrGroupsPerPage[$i]!=-1)
					$rpp.= "<option value=\"".$this->arrGroupsPerPage[$i]."\" ".($this->pageSize == $this->arrGroupsPerPage[$i] ? "selected" : "").">".$this->arrGroupsPerPage[$i]."</option>";
				else	
					$rpp.= "<option value=\"-1\" ".($this->pageSize == $this->arrGroupsPerPage[$i] ? "selected" : "").">"."Mostrar Todos"."</option>";
			}	
		}	
		else{
				if($this->isTableType == "report")
					$rpp.= "<select onChange=\"javascript: document.location='".htmlspecialchars(rawurlencode($this->shortTableName))."_report.php?pagesize='+this.options[this.selectedIndex].value;\">";
				else
					$rpp.= "<select id=\"recordspp".$this->id."\">";
				
				for($i=0;$i<count($this->arrRecsPerPage);$i++)
				{
					if($this->arrRecsPerPage[$i]!=-1)
						$rpp.= "<option value=\"".$this->arrRecsPerPage[$i]."\" ".($this->pageSize == $this->arrRecsPerPage[$i] ? "selected" : "").">".$this->arrRecsPerPage[$i]."</option>";
					else
						$rpp.= "<option value=\"-1\" ".($this->pageSize == $this->arrRecsPerPage[$i] ? "selected" : "").">"."Mostrar Todos"."</option>";
				}
			}
		$rpp.="</select>";
		
		if($this->isTableType == "report" && $this->reportGroupFields)
			$this->xt->assign("grpsPerPage",$rpp);
		else
			$this->xt->assign("recsPerPage",$rpp);
	}
	
	function ProcessFiles()
	{
		foreach($this->filesToDelete as $f)
		{
			$f->Delete();
		}
		foreach($this->filesToMove as $f)
		{
			$f->Move();
		}
		foreach($this->filesToSave as $f)
		{
			$f->Save();
		}
	}
	
	/**
	 * Use for count details recs number, if subQueryes not supported, or keys have different types
	 *
	 * @param integer $i
	 * @param array $detailid
	 * @intellisense
	 */
	function countDetailsRecsNoSubQ($dInd, &$detailid) 
	{
		global $tables_data;
        global $masterTablesData;
        global $detailsTablesData;
		global $allDetailsTablesArr;
		$dDataSourceTable = $this->allDetailsTablesArr[$dInd]['dDataSourceTable'];

		$masterPSet = $this->pSet->getTable($dDataSourceTable);

		$detailsQuery = $masterPSet->getSQLQuery();
		$dSqlWhere = $detailsQuery->WhereToSql();
			
		$detailKeys = $masterPSet->getDetailKeysByMasterTable($this->tName);
		
		$securityClause = SecuritySQL("Search", $dDataSourceTable);
		
		// add where 
		if(strlen($securityClause))
			$dSqlWhere = whereAdd($dSqlWhere, $securityClause);
			
		$masterwhere = "";
		foreach($this->masterKeysByD[$dInd] as $idx => $val) 
		{
			if($masterwhere)
			{
				$masterwhere.= " and ";
			}
			$mastervalue = $this->cipherer->MakeDBValue($detailKeys[$idx], $detailid[$idx], "", $dDataSourceTable, true);
			
			if($mastervalue == "null")
				$masterwhere .= GetFullFieldNameForInsert($masterPSet, $detailKeys[$idx])." is NULL ";
			else
				$masterwhere .= GetFullFieldName($detailKeys[$idx], $dDataSourceTable, false)."=".$mastervalue;
		}
		return SQLQuery::gSQLRowCount_int($detailsQuery->HeadToSql(), $detailsQuery->FromToSql(), $dSqlWhere, $detailsQuery->GroupByToSql()
			, $detailsQuery->Having()->toSql($detailsQuery), $masterwhere,"");	
		
	}
	
	/**
	 * Calcs pagination info
	 *
	 * @intellisense
	 */
	function buildPagination() 
	{
		//	hide colunm headers if needed
		$this->recordsOnPage = $this->numRowsFromSQL -($this->myPage - 1) * $this->pageSize;
		if($this->recordsOnPage > $this->pageSize && $this->pageSize!=-1)
			$this->recordsOnPage = $this->pageSize;
		
		$this->colsOnPage = $this->recsPerRowList;
		if($this->colsOnPage > $this->recordsOnPage)
			$this->colsOnPage = $this->recordsOnPage;
		if($this->colsOnPage < 1)
			$this->colsOnPage = 1;
			
		//	 Pagination:
		if((! $this->numRowsFromSQL) && ($this->deleteMessage == ''))
		{
			$this->rowsFound = false;
			$message = ($this->is508 == true ? "<a name=\"skipdata\"></a>" : "")."Nenhum Registro Encontrado";
			$message= "<span name=\"notfound_message".$this->id."\">".$message."</span>";
			$this->xt->assign("message",$message);
			$this->xt->assign("message_block",true);
			
			if($this->listAjax || $this->mode == LIST_LOOKUP){
				$this->xt->assign("pagination_block", true);
				$this->xt->displayBrickHidden("pagination");
			}
		} 
		else{
				$this->rowsFound = true;
				$maxRecords = $this->numRowsFromSQL;
				
				$this->xt->assign("message_block",false);
				if($this->listAjax || $this->mode == LIST_LOOKUP){
					$this->xt->assign("message_block",true);
					$this->xt->displayBrickHidden("message");
				}
				else if ($this->deleteMessage != ''){
					$this->xt->assign("message_block",true);
				}
				
				$this->xt->assign("records_found", $this->numRowsFromSQL);
				if($this->pageSize && $this->pageSize!=-1)
					$this->maxPages = ceil($maxRecords / $this->pageSize);
				if($this->myPage > $this->maxPages)
					$this->myPage = $this->maxPages;
				if($this->myPage < 1)
					$this->myPage = 1;
				$this->jsSettings["tableSettings"][$this->tName]['maxPages'] = $this->maxPages;
				$this->maxRecs = $this->pageSize;
				$this->xt->assign("page", $this->myPage);
				$this->xt->assign("maxpages", $this->maxPages);
				
				$this->xt->assign("pagination_block", false);
				
				//	write pagination
				if($this->maxPages > 1) 
				{
					$this->xt->assign("pagination_block", true);
					$pagination = "<table rows='1' cols='1' align='center' width='auto' border='0' name='paginationTable".$this->id."'>";
					$pagination.= "<tr valign='center'><td align='center'>";
					$counterstart = $this->myPage - 9;
					if($this->myPage % 10)
						$counterstart = $this->myPage -($this->myPage % 10) + 1;
					$counterend = $counterstart + 9;
					if($counterend > $this->maxPages)
						$counterend = $this->maxPages;
					if($counterstart != 1) 
					{
						$pagination.= $this->getPaginationLink(1,"Primeiro")."&nbsp;:&nbsp;";
						$pagination.= $this->getPaginationLink($counterstart - 1,"Anterior")."&nbsp;";
					}
					$pagination.= "<b>[</b>";
					for($counter = $counterstart; $counter <= $counterend; $counter ++) 
					{
						if($counter != $this->myPage)
							$pagination.= "&nbsp;".$this->getPaginationLink($counter,$counter,true);
						else
							$pagination.= "&nbsp;<b>".$counter."</b>";
					}
					$pagination.= "&nbsp;<b>]</b>";
					if($counterend != $this->maxPages) 
					{
						$pagination.= "&nbsp;".$this->getPaginationLink($counterend + 1,"Próximo")."&nbsp;:&nbsp;";
						$pagination.= $this->getPaginationLink($this->maxPages,"Último");
					}
					$pagination.= "</td></tr></table>";
					$this->xt->assign("pagination", $pagination);
				}
				else
				{
									if($this->listAjax || $this->mode == LIST_LOOKUP){
						$this->xt->assign("pagination_block", true);
						$this->xt->displayBrickHidden("pagination");
					}
				}
			}
	}
	/**
	 * Get pagination link for build pagination block
	 *
	 * @return string
	 * @intellisense
	 */
	function getPaginationLink($pageNum,$linkText,$cls=false)
	{
		return '<a href="#" pageNum="'.$pageNum.'" '.($cls ? 'class="pag_n"' : '').' style="TEXT-DECORATION: none;">'.$linkText.'</a>';
	}
	
	
	/**
	 * Check is current table is admin table
	 *
	 * @return bool
	 * @intellisense
	 */
	function isAdminTable()
	{
		if($this->tName)
			return $this->tName === 'admin_rights' || $this->tName === 'admin_members' || $this->tName === 'admin_users';
		else
			return false;
	}
	
	/**
	 * isSimpleFormNeeded
	 * Check is simple html form needed on add and edit page
	 * @return {bool}
	 * @intellisense
	 */
	function isHTMLFormNeeded()
	{
				return false;
	}
	
	//function fieldAlignClass($f) was renamed
	function fieldClass($f)
	{
		if($this->pSet->getEditFormat($f) == FORMAT_LOOKUP_WIZARD)
			return '';
		$format = $this->pSet->getViewFormat($f);
		
		if($format==FORMAT_FILE) 
			return ' runner-field-file';
		if($format == FORMAT_AUDIO)
			return ' runner-field-audio';
		if($format == FORMAT_CHECKBOX)
			return ' runner-field-checkbox';
		if($format == FORMAT_NUMBER || IsNumberType($this->pSet->getFieldType($f)))
			return ' runner-field-number';
		return "";
	}
	
		
	/**
	 * buildDetailGridLinks
	 * Build master-details links href-attribute on list grid
	 * @param {array} master key values
	 * @return {array} array of links hrefs and ids
	 * @intellisense
	 */
	function buildDetailGridLinks(&$data)
	{
		$hrefs = array();
		for($i = 0; $i < count($this->allDetailsTablesArr); $i ++) 
		{
			$dShortTable = $this->allDetailsTablesArr[$i]['dShortTable'];
			$masterquery = "mastertable=".rawurlencode($this->tName);
			
			for($idx = 1; $idx <= count($this->allDetailsTablesArr[$i]["masterKeys"]); $idx ++){
				$masterquery.= "&masterkey".($idx)."=".rawurlencode($data[$this->allDetailsTablesArr[$i]['dDataSourceTable']]["masterkey".$idx]);
			}
			
			$hrefs[] = array("id" => $this->pSet->getDPType($this->allDetailsTablesArr[$i]['dDataSourceTable']) == DP_INLINE 
					? $dShortTable."_preview" : "master_".$dShortTable."_"
				, "href" => $dShortTable."_list.php?".$masterquery);
		}
		return $hrefs;
	}
	
	/**
	 * Create new control (if needed) for edit field, and return it
	 * @param {string} field name
	 * @param {string} field id
	 * @param {string} edit format for forced creation of field
	 * @intellisense
	 */
	function getControl($field, $id = "")
	{
		return $this->controls->getControl($field, $id);
	}
	/**
	 * Create new control (if needed) for view field, and return it
	 * @param {string} field name
	 * @param {string} predefined view format 
	 * @intellisense
	 */
	function getViewControl($field, $format = null)
	{
		return $this->viewControls->getControl($field, $format);
	}
	
	function setForExportVar($forExport)
	{
		$this->viewControls->setForExportVar($forExport);
	}
	
	/**
	 * showDBValue
	 * Wrapper for ViewControl creation and showDBValue call on it
	 * @param {string} field name
	 * @param {array} associative array with record data
	 * @param {string} string with record keys and values
	 * @intellisense
	 */
	function showDBValue($field, &$data, $keylink = "")
	{
		return $this->getViewControl($field)->showDBValue($data, $keylink);
	}
	
	/**
	 * Create pasword or confirm field control (if needed)
	 * @param {string} field name
	 * @intellisense
	 */
	function addConfirmFieldControl($field, $id, $isConfirm = false)
	{
		if(!array_key_exists($field, $this->controls))
		{
			include_once(getabspath("classes/controls/Control.php"));
			include_once(getabspath("classes/controls/PasswordField.php"));
			$this->controls->controls[$field] = new PasswordField($field, $this, $id);
			if($isConfirm)
				$this->controls->controls[$field]->field = GetPasswordField();
		}
	}
	function hideField($fieldName)
	{
		if(!is_null($this->xt))
			$this->xt->hideField($fieldName);
	}

	function showField($fieldName)
	{
		if(!is_null($this->xt))
			$this->xt->showField($fieldName);
	}	
}

$menuNodesObject = null;
?>
