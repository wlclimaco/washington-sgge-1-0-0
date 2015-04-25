<?php
class ViewControl
{
	public $field = "";
	public $displayField = null;
	/**
	 * Reference to ViewControlsContainer instance
	 */
	public $container = null;
	/**
	 * Reference to RunnerPage (or its descendant) instance if its exist
	 */
	public $pageObject = null;
	/**
	 * A flag indicating whether the support for section 508 is on
	 * @var {bool}
	 */
	public $is508 = false;
	public $fieldType = null;
	public $viewFormat = FORMAT_NONE;
	public $editFormat = EDIT_FORMAT_NONE;
	
	/**
	 * Storage for control settings. It fills in the init() function. 
	 * @var {array}
	 */
	public $settings = array();
	
	/**
	 * Array of view controls map
	 *
	 * @var array
	 */	
	var $viewControlsMap = array();
	
	/**
	 * If true than "Scroll table data" will be canceled for IE 
	 * @var unknown_type
	 */
	var $noScroll = false;
	
	/**
	 * addJSFiles
	 * Add control JS files to page object
	 */
	function addJSFiles()
	{
		//example
		// $this->getContainer()->AddJSFile("include/mupload.js");
	}
	
	/**
	 * addCSSFiles
	 * Add control CSS files to page object
	 */ 
	function addCSSFiles()
	{
		//example
		// $this->getContainer()->AddCSSFile("include/mupload.css");
	}
	
	function getContainer()
	{
		if(!is_null($this->pageObject))
			return $this->pageObject;
		else
			return $this->container;
	}
	
	public function ViewControl($field, $container, $pageObject = null)
	{
		$this->field = $field;
		$this->container = $container;
		$this->pageObject = $pageObject;
		$this->is508 = isEnableSection508();
		$this->fieldType = $this->container->pSet->getFieldType($this->field);
		$this->viewFormat = $this->container->pSet->getViewFormat($this->field);
		$this->editFormat = $this->container->pSet->getEditFormat($this->field);
	}
	
	public function showDBValue(&$data, $keylink = "")
	{
		$value = $data[$this->field];
		if(IsBinaryType($this->fieldType))
		{
			$value = "Dados Binários longos demais, Não pode ser exibido";
		} 
		if($value === false)
			$value = "";
		
		if($this->editFormat == EDIT_FORMAT_CHECKBOX && $this->viewFormat == FORMAT_NONE)
		{
			if($value && $value!=0)
				$value = "Sim";
			else
				$value = "Não";
		}
		return $this->checkForEncoding($value, $keylink);
	}
	
	public function checkForEncoding($value, $keylink)
	{
		if($this->container->pSet->NeedEncode($this->field) 
			&& $this->container->forExport != "excel"
			&& $this->container->forExport != "csv")
		{
			$isMobileLookup = false;
			if(!is_null($this->pageObject))
			{
				if($this->pageObject->mode == LIST_LOOKUP && isMobile())
					$isMobileLookup = true;
			}
			if($this->container->pageType == PAGE_ADD || $this->container->pageType == PAGE_EDIT)
				$pageType = PAGE_LIST;
			else 
				$pageType = $this->container->pageType;
			$value = ProcessLargeText($this->container->pSet, $value, "field=".rawurlencode($this->field).$keylink, 
				"", $pageType, "", $isMobileLookup, $this->container->pSet->isTableType() == "report");
		}
		return $value;
	}
	
	public function & getJSControl()
	{
		if(!isset($this->getContainer()->viewControlsMap["controls"]))
			$this->getContainer()->viewControlsMap["controls"] = array();
			
		for($i = 0; $i < count($this->getContainer()->viewControlsMap["controls"]); $i++)
			if($this->getContainer()->viewControlsMap["controls"][$i]["fieldName"] == $this->field)
				return $this->getContainer()->viewControlsMap["controls"][$i];
		
		$this->getContainer()->viewControlsMap["controls"][$i] = array("fieldName" => $this->field, "viewFormat" => $this->viewFormat);
		return $this->getContainer()->viewControlsMap["controls"][count($this->getContainer()->viewControlsMap["controls"]) - 1];
	}
	
	/**
	 * addJSControlSetting
	 * Add setting for JS control to controls map
	 * @param {string} setting name
	 * @param {object} setting value
	 */
	public function addJSControlSetting($name, $value)
	{
		$JScontrol =& $this->getJSControl();
		$JScontrol[$name] = $value;
	}
}

class ViewControlTypes
{
	public $viewTypes = array();
		
	function ViewControlTypes() 
	{		
		$this->viewTypes[FORMAT_NONE] = "";
		$this->viewTypes[FORMAT_DATE_SHORT] = "ViewShortDateField";
		$this->viewTypes[FORMAT_DATE_LONG] = "ViewLongDateField";
		$this->viewTypes[FORMAT_DATE_TIME] = "ViewDatetimeField";
		$this->viewTypes[FORMAT_TIME] = "ViewTimeField";
		$this->viewTypes[FORMAT_CURRENCY] = "ViewCurrencyField";
		$this->viewTypes[FORMAT_PERCENT] = "ViewPercentField";
		$this->viewTypes[FORMAT_HYPERLINK] = "ViewHyperlinkField";
		$this->viewTypes[FORMAT_EMAILHYPERLINK] = "ViewEmailHyperlinkField";
		$this->viewTypes[FORMAT_DATABASE_IMAGE] = "ViewDatabaseImageField";
		$this->viewTypes[FORMAT_DATABASE_FILE] = "ViewDatabaseFileField";
		$this->viewTypes[FORMAT_FILE] = "ViewFileDownloadField";
		$this->viewTypes[FORMAT_FILE_IMAGE] = "ViewImageDownloadField";
		$this->viewTypes[FORMAT_FILE_IMAGE_OLD] = "ViewDocumentDownloadOldField";
		$this->viewTypes[FORMAT_PHONE_NUMBER] = "ViewPhoneNumberField";
		$this->viewTypes[FORMAT_NUMBER] = "ViewNumberField";
		$this->viewTypes[FORMAT_CHECKBOX] = "ViewCheckboxField";
		$this->viewTypes[FORMAT_MAP] = "ViewMapField";
		$this->viewTypes[FORMAT_AUDIO] = "ViewAudioFileField";
		$this->viewTypes[FORMAT_DATABASE_AUDIO] = "ViewDatabaseAudioField";
		$this->viewTypes[FORMAT_VIDEO] = "ViewVideoFileField";
		$this->viewTypes[FORMAT_DATABASE_VIDEO] = "ViewDatabaseVideoField";
		$this->viewTypes[FORMAT_CUSTOM] = "ViewCustomField";
		$this->viewTypes[FORMAT_LOOKUP_WIZARD] = "ViewLookupWizardField";
		$this->viewTypes[FORMAT_HTML] = "ViewHTMLField";
	}
}
?>