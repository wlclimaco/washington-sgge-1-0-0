<?php
class EditControlsContainer
{
	public $controls = array();
	public $jsSettings = array();
	public $pSetEdit = null;
	public $pageType = "";
	public $cipherer = null;
	public $tName = "";
	
	public $pageObject = null;
	
	public $pageAddLikeInline = false;
	public $pageEditLikeInline = false;
	
	
	/**
	 * Associative array used like a container to exchange some data between controls on one page
	 * @var {array}
	 */
	public $globalVals = array();
	
	public function EditControlsContainer($pageObject, $pSetEdit = null, $pageType = "", $cipherer = "")
	{
		if($pageObject != null)
		{
			$this->pageObject = $pageObject;
			$this->pSetEdit = $pageObject->pSetEdit;
			$this->pageType = $pageObject->pageType;
			$this->pageAddLikeInline = $pageObject->pageAddLikeInline;
			$this->pageEditLikeInline = $pageObject->pageEditLikeInline;
		}
		else 
		{
			$this->tName = $pSetEdit->_table;
			$this->pSetEdit = $pSetEdit;
			$this->pageType = $pageType;
			$this->cipherer	= $cipherer;
		}
	}
	
	function addControlsJSAndCSS()
	{
		$pageTypes = array();
		switch ($this->pageType)
		{
			case PAGE_ADD:
				$pageTypeStr = "Add";
				break;
			case PAGE_EDIT:
				$pageTypeStr = "Edit";
				break;
			case PAGE_LIST:
			case PAGE_SEARCH:
				$pageTypeStr = "List";
				break;
			case PAGE_REGISTER:
				$pageTypeStr = "RegisterOrSearch";
				break;
			default:
				return;
		}
		$getEditFieldsFunc = "get".($this->pageAddLikeInline || $this->pageEditLikeInline ? "Inline" : "").$pageTypeStr."Fields";
		if($this->pageAddLikeInline || $this->pageEditLikeInline)
			$appearOnPageFunc = "appearOnInline".$pageTypeStr;
		else 
			$appearOnPageFunc = "appearOn".$pageTypeStr."Page";
		if($this->pageType == PAGE_REGISTER)
			$fields = $this->pSetEdit->getFieldsForRegister();
		else
			$fields = $this->pSetEdit->$getEditFieldsFunc();
		for($i = 0; $i < count($fields); $i++)
		{
			$appear = false;
			if($this->pageType == PAGE_REGISTER)
				$appear = true;
			else 
				$appear = $this->pSetEdit->$appearOnPageFunc($fields[$i]);
			if($appear)
			{
				$this->getControl($fields[$i])->addJSFiles();
				$this->getControl($fields[$i])->addCSSFiles();
			}
		}
	}
	
	function getControl($field, $id = "")
	{
		// if conrol does not created previously  
		if(!array_key_exists($field, $this->controls))
		{
			include_once(getabspath("classes/controls/Control.php"));
			$isUserControl = false;
			$cTypes = new ControlTypes();
			$editFormat = $this->pSetEdit->getEditFormat($field);
			if($editFormat == EDIT_FORMAT_TEXT_FIELD && IsDateFieldType($this->pSetEdit->getFieldType($field)))
				$editFormat = EDIT_FORMAT_DATE;
			if($this->pageType == PAGE_SEARCH || $this->pageType == PAGE_LIST)
			{
				// Text field may be Lookup field on some page
				if($editFormat == EDIT_FORMAT_TEXT_FIELD 
					&& $this->pSetEdit->getPageTypeByFieldEditFormat($field, EDIT_FORMAT_LOOKUP_WIZARD) != "")
				{
					$localPSet = new ProjectSettings($this->pSetEdit->_table, 
						$this->pSetEdit->getPageTypeByFieldEditFormat($field, EDIT_FORMAT_LOOKUP_WIZARD));
					if($localPSet->getLWLinkField($field) != $localPSet->getLWDisplayField($field))
						$className = "LookupTextField";
					else 
						$className = $cTypes->forSearch[$editFormat];
				}
				else
					$className = $cTypes->forSearch[$editFormat];
			}
			else
				$className = $cTypes->forEdit[$editFormat];
				
			if(!$className)
			{
				if($editFormat != ""){
					$className = "Edit".$editFormat;
					$isUserControl = true;
					include_once(getabspath("classes/controls/UserControl.php"));
					if(!is_null($this->pageObject))
						$this->pageObject->AddJSFile("include/runnerJS/controls/".$className.".js", "include/runnerJS/Control.js");
				}
				else
					$className = $cTypes->forEdit[EDIT_FORMAT_TEXT_FIELD];
			}
			else if($className == $cTypes->forEdit[EDIT_FORMAT_FILE] && $this->pSetEdit->isCompatibilityMode($field))
				$className = "FileFieldSingle";
			
			$this->controls[$field] = createControlClass($className, $field, $this->pageObject != null ? $this->pageObject : $this, $id);
			$this->controls[$field]->container = $this;
			if($isUserControl)
			{
				$this->controls[$field]->format = $className;
				$this->controls[$field]->initUserControl();
			}
		}
		if($id != "")
			$this->controls[$field]->setID($id);
		return $this->controls[$field];
	}
	
	function isSystemControl($className)
	{
		include_once(getabspath("classes/controls/Control.php"));
		$cTypes = new ControlTypes();
		if($this->pageType == PAGE_SEARCH || $this->pageType == PAGE_LIST)
			return isset($cTypes->forSearch[$className]);
		else
			return isset($cTypes->forEdit[$className]);
	}
}
?>