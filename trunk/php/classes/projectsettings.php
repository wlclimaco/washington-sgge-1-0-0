<?php
class ProjectSettings
{
	var $_table;
	
	var $_viewPage = PAGE_VIEW;

	var $_defaultViewPage = PAGE_VIEW;
	
	var $_editPage = PAGE_EDIT;
	
	var $_defaultEditPage = PAGE_EDIT;
	
	var $_tableData = array();
	
	var $_mastersTableData = array();
	
	var $_detailsTableData = array();
		
	function ProjectSettings($table = "", $page = "")
	{
		if($table && $table != 'global')
			$this->setTable($table);
		if($page)
			$this->setPage($page);
	}
	
	function setTable($table)
	{
		$this->_table = $table;
		global $tables_data, $field_labels, $fieldToolTips, $detailsTablesData, $masterTablesData, $tableEvents, $cipherer;
		if(GetTableURL($table) != "")
			include_once(getabspath("include/".GetTableURL($table)."_settings.php"));
		
		if(isset($tables_data[$this->_table]))
			$this->_tableData = &$tables_data[$this->_table];
		
		$this->_mastersTableData = &$masterTablesData[$this->_table];
		$this->_detailsTableData = &$detailsTablesData[$this->_table];
		
		$this->_editPage = $this->getDefaultEditPageType($this->isTableType());	
		$this->_viewPage = $this->getDefaultViewPageType($this->isTableType());	
		$this->_defaultEditPage = $this->_editPage;
		$this->_defaultViewPage = $this->_viewPage;
	}

	function getDefaultViewPageType($tableType)
	{
		if($tableType == "report")
			return PAGE_REPORT;
		if($tableType == "chart")
			return PAGE_CHART;
		return PAGE_VIEW;
	}

	function getDefaultEditPageType($tableType)
	{
		if($tableType == "report" || $tableType == "chart")
			return PAGE_SEARCH;
		return PAGE_EDIT;
	}

	
	function setPage($page)
	{
		//	a deeper checking for table and page types compatibility might be added here
		if($this->isPageTypeForView($page))
		{
			if($this->isTableType() != "report" && $this->isTableType() != "chart"
				&& ($page == PAGE_CHART || $page == PAGE_REPORT))
				$this->_viewPage = PAGE_LIST;
			else 
				$this->_viewPage = $page;
			$this->_defaultViewPage = $this->getDefaultViewPageType($this->isTableType());
		}
		if($this->isPageTypeForEdit($page))
		{
			$this->_editPage = $page;
			$this->_defaultEditPage = $this->getDefaultEditPageType($this->isTableType());
		}
	}
	
	function isPageTypeForView($ptype)
	{
		global $pageTypesForView;
		return in_array(strtolower($ptype), $pageTypesForView);
	}

	function isPageTypeForEdit($ptype)
	{
		global $pageTypesForEdit;
		return in_array(strtolower($ptype), $pageTypesForEdit);	
	}

	function getTable($table, $page = "")
	{
		return new ProjectSettings($table, $page);
	}
	
	function getPageTypeByFieldEditFormat($field, $editFormat)
	{
		if(isset($this->_tableData[$field]) && isset($this->_tableData[$field][FORMAT_EDIT]))
		{
			foreach($this->_tableData[$field][FORMAT_EDIT] as $pageType => $editSettings)
			{
				if(isset($editSettings["EditFormat"]) && $editSettings["EditFormat"] == $editFormat)
					return $pageType;
			}
		}
		return "";
	}
	
	function getTableData($key)
	{
		if(!$this->isExistsTableKey($key))
			return $this->getDefaultValueByKey(substr($key,1));
		return $this->_tableData[$key];
	}
	
	function getFieldData($field, $key )
	{
		global $g_settingsType;
		
		if(!isset($this->_tableData[$field]))
			return $this->getDefaultValueByKey($key);
		
		$settingType = $g_settingsType[$key];
			
		switch($settingType)
		{
			case SETTING_TYPE_VIEW:
				if(!$this->isSeparate($field))
				{
					$viewPage = $this->_defaultViewPage;
				}
				else
				{
					$viewPage = $this->_viewPage;
				}
				if(isset($this->_tableData[$field][FORMAT_VIEW][$viewPage][$key]))
					return $this->_tableData[$field][FORMAT_VIEW][$viewPage][$key];
				break;
			case SETTING_TYPE_EDIT:
				if(!$this->isSeparate($field))
				{
					$editPage = $this->_defaultEditPage;
				}
				else
				{
					$editPage = $this->_editPage;
				}
		
				if(isset($this->_tableData[$field][FORMAT_EDIT][$editPage][$key]))
					return $this->_tableData[$field][FORMAT_EDIT][$editPage][$key];
				break;
			default:
				if (isset($this->_tableData[$field][$key]))
					return $this->_tableData[$field][$key];
				break;
		}
		return $this->getDefaultValueByKey($key);
	}
	
	/**
	 * addCustomExpressionIndex
	 * Add new index to list, for determination of custom expressions position in SQL query   
	 * @param {string} table wich contain a lookup field
	 * @param {string} name of lookup field
	 * @param {int} index
	 */
	function addCustomExpressionIndex($mainTable, $mainField, $index)
	{
		if(!$this->isExistsTableKey(".customExpressionIndexes"))
			$this->_tableData[".customExpressionIndexes"] = array();
		if(!isset($this->_tableData[".customExpressionIndexes"][$mainTable]))
			$this->_tableData[".customExpressionIndexes"][$mainTable] = array();
		$this->_tableData[".customExpressionIndexes"][$mainTable][$mainField] = $index;
	}
	
	/**
	 * getCustomExpressionIndex
	 * Get index of custom expression in SQL field
	 * @param {string} table wich contain a lookup field
	 * @param {string} name of lookup field
	 */
	function getCustomExpressionIndex($mainTable, $mainField)
	{
		if(!$this->isExistsTableKey(".customExpressionIndexes"))
			$this->_tableData[".customExpressionIndexes"] = array();
		if(isset($this->_tableData[".customExpressionIndexes"][$mainTable]) 
			&& isset($this->_tableData[".customExpressionIndexes"][$mainTable][$mainField]))
			return $this->_tableData[".customExpressionIndexes"][$mainTable][$mainField];
			
		return FALSE;
	}
	
	function isExistsTableKey($key)
	{
		if(!isset($this->_tableData[$key]))
			return false;
		return true;
	}

	function isExistsFieldKey($field, $key)
	{
		if(!$this->isExistsTableKey($this->_table, $field))
			return false;
		if(!isset($this->_tableData[$field][$key]))
			return false;
		return true;
	}
	
	function getDefaultValueByKey($key)
	{
		global $g_defaultOptionValues;
		if(isset($g_defaultOptionValues[$key]) || is_null($g_defaultOptionValues[$key]))
			return $g_defaultOptionValues[$key];
		return false;
	}
	
	/**
	 * Returns array of master tables , which are master for current detail table
	 * @param string $tName - it's data source detail table name
	 * @return array if success otherwise false
	 */
	function getMasterTablesArr($tName) 
	{
		return $this->_mastersTableData;
	}
	
	/**
	 * Returns array of master keys for passed detailTable
	 *
	 * @param string $dTableName - it's detail data sourse table name, 
	 * @return array if success otherwise false
	 */
	function getMasterKeysByDetailTable($dTableName, $default = array())
	{
		if(!$dTableName)
			return $default;
		foreach ($this->_detailsTableData as $dTableDataArr)
		{
			if ($dTableDataArr['dDataSourceTable'] == $dTableName)
				return $dTableDataArr['masterKeys'];
		}
		return $default;
	}

	/**
	 * Returns array of detail tables , which are detail for current master table
	 * @param string $tName - it's data source master table name
	 * @return array if success otherwise false
	 */
	function getDetailTablesArr() 
	{
		return $this->_detailsTableData;
	}
	
	/**
	 * Returns array of detail keys for passed masterTable
	 *
	 * @param string $mTableName - it's master table name, 
	 * @param string $tName - it's current (detail) table
	 * @return array if success otherwise default value
	 */
	function getDetailKeysByMasterTable($mTableName = "", $default = array())
	{
		if(!$mTableName)
			return $default;
		foreach($this->_mastersTableData as $mTableDataArr)
		{
			if ($mTableDataArr['mDataSourceTable'] == $mTableName)
				return $mTableDataArr['detailKeys'];
		}
		return $default;
	}
	
	/**
	 * Returns array of detail keys for passed detailTable
	 *
	 * @param string $dTableName - It's detail data sourse table name
	 * @param string $tName - current(master) table name
	 * @return array if success otherwise false
	 */
	function getDetailKeysByDetailTable($dTableName, $default = array())
	{
		foreach ($this->_detailsTableData as $dTableDataArr)
		{
			if ($dTableDataArr['dDataSourceTable'] == $dTableName)
				return $dTableDataArr['detailKeys'];
		}
		return $default;
	}
	
	/**
	 * Returns details preview Type 
	 *
	 * @param string $dTableName - it's detail data sourse table name, 
	 * @param string $tName - current(master) table name
	 * @return array if success otherwise false
	 */
	function getDPType($dTableName) 
	{
		if(!$dTableName)
			return false;
		foreach ($this->_detailsTableData as $dTableDataArr)
		{
			if ($dTableDataArr['dDataSourceTable'] == $dTableName)
				return $dTableDataArr['previewOnList'];
		}
		return false;
	}

	function GetFieldByIndex($index)
	{
		foreach($this->_tableData as $key => $value)
		{
			if(!is_array($value))
				continue;
			elseif(!isset($value["Index"]))
				continue;
			if($value["Index"] == $index && $this->getFieldIndex($key))
				return $key;
		}
		return null;
	}
	
	//	Is field has separate type for editing and viewing 
	function isSeparate($field)
	{
		if (isset($this->_tableData[$field]["isSeparate"]))
			return $this->_tableData[$field]["isSeparate"];
		return false;
	}
	
	// return field label
	function label($field)
	{
		$result = GetFieldLabel($this->_table,$field); 
		return $result != "" ? $result : $field;
	}

	//	return filename field if any
	//	viewFormat setting
	function getFilenameField($field)
	{
		return $this->getFieldData($field, "Filename");
	}

	//	return hyperlink prefix
	//	viewFormat setting
	function getLinkPrefix($field)
	{
		return $this->getFieldData($field, "LinkPrefix");
	}

	//	return database field type
	//	using ADO DataTypeEnum constants
	//	the full list available at:
	//	http://msdn.microsoft.com/library/default.asp?url=/library/en-us/ado270/htm/mdcstdatatypeenum.asp
	function getFieldType($field)
	{
		return $this->getFieldData($field, "FieldType");
	}

	function isAutoincField($field)
	{
		return $this->getFieldData($field, "AutoInc");
	}
	
	function isAutoUpdate($field)
	{
		return $this->getFieldData($field, "AutoUpdate");
	}
	
	function getDefaultValue($field)
	{
		$editPage = $this->_editPage;
		if(!$this->isSeparate($field))
			$editPage = $this->getDefaultEditPageType($this->isTableType());;
		return GetDefaultValue($field, $editPage, $this->_table);
	}

	//	return Edit format
	//	editFormats
	function getEditFormat($field)
	{
		return $this->getFieldData($field, "EditFormat");
	}
	
	function isReadonly($field)
	{
		if($this->getEditFormat($field) == EDIT_FORMAT_READONLY)
			return true;
		return false;
	}

	//	return View format
	//	viewFormat setting
	function getViewFormat($field)
	{
		return $this->getFieldData($field, "ViewFormat");
	}

	//	show time in datepicker or not
	function dateEditShowTime($field)
	{
		return $this->getFieldData($field, "ShowTime");
	}

	//	use FastType Lookup wizard or not
	function fastType($field)
	{
		return $this->getFieldData($field, "FastType");
	}

	function lookupControlType($field)
	{
		return $this->getFieldData($field, "LCType");
	}
	
	function isDeleteAssociatedFile($field)
	{
		return $this->getFieldData($field, "DeleteAssociatedFile");
	}

	//	is Lookup wizard dependent or not
	function useCategory($field)
	{
		return $this->getFieldData($field, "UseCategory");
	}

	//	is Lookup wizard with multiple selection
	function multiSelect($field)
	{
		return $this->getFieldData($field, "Multiselect");
	}

	// Lookup wizard select size
	function selectSize($field)
	{
		return $this->getFieldData($field, "SelectSize");
	}

	function showThumbnail($field)
	{
		return $this->getFieldData($field, "ShowThumbnail");
	}
	
	function showCustomExpr($field)
	{
		return $this->getFieldData($field, "ShowCustomExpr");
	}

	function showFileSize($field)
	{
		return $this->getFieldData($field, "ShowFileSize");
	}
	
	function showIcon($field)
	{
		return $this->getFieldData($field, "ShowIcon");
	}
	
	function getImageWidth($field)
	{
		return $this->getFieldData($field, "ImageWidth");
	}

	function getImageHeight($field)
	{
		return $this->getFieldData($field, "ImageHeight");
	}
	
	// Get nLookupType for current field
	function getLookupType($field)
	{
		return $this->getFieldData($field, "LookupType");
	}

	//Get lookup table name
	function getLookupTable($field)
	{
		return $this->getFieldData($field, "LookupTable");
	}
	
	function getLinkField($field)
	{
		return $this->getFieldData($field, "LinkField");
	}
	
	function getLWLinkField($field, $addWrap = true)
	{
		if ($addWrap)
			return AddFieldWrappers($this->getLinkField($field)); 
		else 
			return $this->getLinkField($field);	
	}
	
	function getLWLinkFieldType($field)
	{
		return $this->getFieldData($field, "LinkFieldType");
	}
	
	function getDisplayField($field)
	{
		return $this->getFieldData($field,"DisplayField");
	}
	
	function getCustomDisplay($field)
	{
		return $this->getFieldData($field, 'CustomDisplay');
	}
	
	//	viewFormats
	function NeedEncode($field)
	{
		return $this->getFieldData($field, "NeedEncode");
	}
	
	/**
	 * Get array of validation for control
	 * return array - of validations
	 */
	function getValidation($field)
	{
		return $this->getFieldData($field, "validateAs");
	}

	/** Check is appear current field on list page
	  *	return boolean - true or false	
	  */
	function appearOnListPage($field)
	{	
		return $this->getFieldData($field, "bListPage");
	}

	/** Check is appear current field on add page
	  *	return boolean - true or false	
	  */
	function appearOnAddPage($field)
	{
		return $this->getFieldData($field,"bAddPage");
	}

	/** Check is appear current field on inline add
	  *	return boolean - true or false	
	  */
	function appearOnInlineAdd($field)
	{
		return $this->getFieldData($field,"bInlineAdd");
	}

	/** Check is appear current field on edit page
	  *	return boolean - true or false	
	  */
	function appearOnEditPage($field)
	{
		return $this->getFieldData($field, "bEditPage");
	}

	/** Check is appear current field on edit page
	  *	return boolean - true or false	
	  */
	function appearOnInlineEdit($field)
	{
		return $this->getFieldData($field, "bInlineEdit");
	}

	/** Check is appear current field on view page
	  *	return boolean - true or false	
	  */
	function appearOnViewPage($field)
	{
		return $this->getFieldData($field, "bViewPage");
	}

	/** Check is appear current field on print page
	  *	return boolean - true or false	
	  */
	function appearOnPrinterPage($field)
	{
		return $this->getFieldData($field, "bPrinterPage");
	}
	
	function isVideoUrlField($field)
	{
		return $this->getFieldData($field, "fieldIsVideoUrl");
	}
	
	function isAbsolute($field)
	{
		return $this->getFieldData($field, "Absolute");
	}
	
	function getAudioTitleField($field)
	{
		return $this->getFieldData($field, "audioTitleField");
	}
	
	function getVideoWidth($field)
	{
		return $this->getFieldData($field, "videoWidth");
	}	

	function getVideoHeight($field)
	{
		return $this->getFieldData($field, "videoHeight");
	}
	
	function isRewindEnabled($field)
	{
		return $this->getFieldData($field, "RewindEnabled");
	}

	function getCategoryFilter($field)
	{
		return $this->getFieldData($field, "CategoryFilter");
	}

	function isLookupUnique($field)
	{
		return $this->getFieldData($field, "LookupUnique");
	}

	function getLookupOrderBy($field)
	{
		return $this->getFieldData($field, "LookupOrderBy");
	}

	function isLookupDesc($field)
	{
		return $this->getFieldData($field, "LookupDesc");
	}

	function getOwnerTable($field)
	{
		return $this->getFieldData($field, "ownerTable");
	}

	function isFieldEncrypted($field)
	{
		return $this->getFieldData($field, "bIsEncrypted");
	}

	function isAllowToAdd($field)
	{
		return $this->getFieldData($field, "AllowToAdd");
	}

	function isSimpleAdd($field)
	{
		return $this->getFieldData($field, "SimpleAdd");
	}

	function getAutoCompleteFields($field)
	{
		return $this->getFieldData($field, "autoCompleteFields");
	}

	function isAutoCompleteFieldsOnEdit($field)
	{
		return $this->getFieldData($field, "autoCompleteFieldsOnEdit");
	}

	function isfreeInput($field)
	{
		return $this->getFieldData($field, "freeInput");
	}

	function getMapData($field)
	{
		return $this->getFieldData($field, "mapData");
	}

	function getFormatTimeAttrs($field)
	{
		return $this->getFieldData($field, "FormatTimeAttrs");
	}
		
	/** Check is appear current field on export page
	  *	return boolean - true or false	
	  */
	function appearOnExportPage($field)
	{
		return $this->getFieldData($field, "bExportPage");
	}
	
	/** Check is appear current field on register page
	  *	return boolean - true or false	
	  */
	function appearOnRegisterOrSearchPage($field, $pageType)
	{
		$arrFields = array();
		if($pageType == PAGE_REGISTER)
			$arrFields = $this->getFieldsForRegister();
		elseif($pageType == PAGE_SEARCH)
			$arrFields = $this->getAllSearchFields();
		else
			return 'break';
		
		for($i=0;$i<count($arrFields);$i++)
		{
			if($arrFields[$i]==$field)
			{
				return true;
			}	
		}
		return false;	
	} 

	/** Check is appear field on page by page type
	  * param $fName - field name
	  * param $pageType - type of current page
	  *	return boolean/string - true or false/'break' - if need to break the cycle	
	  */
	function AppearOnCurrentPage($fName, $pageType, $pageLikeInline = false)
	{
		if($pageType == PAGE_LIST)
		{
			if($this->appearOnListPage($fName))
				return true;
			else
				return $this->appearOnRegisterOrSearchPage($fName,PAGE_SEARCH);	
		}
		elseif($pageType == PAGE_ADD)
		{
			if($pageLikeInline)
			{
				if($this->appearOnInlineAdd($fName) && $this->appearOnListPage($fName))
					return true;
			}
			else if($this->appearOnAddPage($fName))
				return true;
		}
		elseif($pageType == PAGE_EDIT)
		{
			if($pageLikeInline)
			{
				if($this->appearOnInlineEdit($fName) && $this->appearOnListPage($fName))
					return true;
			}
			else if($this->appearOnEditPage($fName))
				return true;
		}
		elseif($pageType == PAGE_SEARCH || $pageType == PAGE_REPORT || $pageType == PAGE_CHART)
			return $this->appearOnRegisterOrSearchPage($fName, PAGE_SEARCH);
		elseif($pageType == PAGE_REGISTER)
			return $this->appearOnRegisterOrSearchPage($fName, PAGE_REGISTER);
		else
			return 'break';
		return false;
	}

	/**
	 * Return original table name for report or chart
	 */
	function getStrOriginalTableName()
	{
		return $this->getTableData(".strOriginalTableName");
	}
	
	function getFieldsForRegister()
	{
		return $this->getTableData(".fieldsForRegister");
	}
	
	function getAllSearchFields()
	{
		return $this->getTableData('.allSearchFields');
	}
	
	function isUseTimeForSearch()
	{
		return $this->GetTableData(".isUseTimeForSearch");
	}
	
	function isUseToolTips()
	{
		return $this->GetTableData(".isUseToolTips");
	}

	function isUseVideo()
	{
		return $this->GetTableData(".isUseVideo");
	}

	function isUseAudio()
	{
		return $this->GetTableData(".isUseAudio");
	}
	
	function isUseAudioOnDetails()
	{
		for($i = 0; $i < count($this->_detailsTableData); $i++)
		{
			if($this->_detailsTableData[$i]["isUseAudio"])
				return true;
		}
		return false;
	}

	function isTableType()
	{
		return $this->GetTableData(".isTableType");
	}

	function getShortTableName()
	{
		return $this->GetTableData(".shortTableName");
	}

	function isShowAddInPopup()
	{
		return $this->GetTableData(".showAddInPopup");
	}
	
	function isShowEditInPopup()
	{
		return $this->GetTableData(".showEditInPopup");
	}
	
	function isShowViewInPopup()
	{
		return $this->GetTableData(".showViewInPopup");
	}
	
	function isResizeColumns()
	{
		return $this->GetTableData(".isResizeColumns");
	}
	
	function isUseAjaxSuggest()
	{
		return $this->GetTableData(".isUseAjaxSuggest");
	}
	
	function isUseDetailsPreview()
	{
		return $this->GetTableData(".useDetailsPreview");
	}

	
	function getPanelSearchFields()
	{
		return $this->GetTableData(".panelSearchFields");
	}

	function getGoogleLikeFields()
	{
		return $this->GetTableData(".googleLikeFields");
	}

	function getInlineEditFields()
	{
		return $this->GetTableData(".inlineEditFields");
	}
	
	function getExportFields()
	{
		return $this->GetTableData(".exportFields");
	}

	function getEditFields()
	{
		return $this->GetTableData(".editFields");
	}

	function getInlineAddFields()
	{
		return $this->GetTableData(".inlineAddFields");
	}

	function getAddFields()
	{
		return $this->GetTableData(".addFields");
	}
	
	function getViewFields()
	{
		return $this->GetTableData(".viewFields");
	}
	
	function getPrinterFields()
	{
		return $this->GetTableData(".printFields");
	}

	function getListFields()
	{
		return $this->GetTableData(".listFields");
	}

	function isAddPageEvents()
	{
		return $this->GetTableData(".addPageEvents");
	}

	function isUsebuttonHandlers()
	{
		return $this->GetTableData(".isUsebuttonHandlers");
	}

	function isUseMainMaps()
	{
		return $this->GetTableData(".isUseMainMaps");
	}

	function isUseFieldsMaps()
	{
		return $this->GetTableData(".isUseFieldsMaps");
	}

	function getOrderIndexes()
	{
		return $this->GetTableData(".orderindexes");
	}

	function getStrOrderBy()
	{
		return $this->GetTableData(".strOrderBy");
	}

	function getSQLQuery()
	{
		return $this->GetTableData(".sqlquery");
	}

	//	Category Control field for dependent dropdowns
	function getCategoryControl($field)
	{
		return $this->getFieldData($field,"CategoryControl");
	}

	//	create Thumbnail or not
	function getCreateThumbnail($field)
	{
		return $this->GetFieldData($field, "CreateThumbnail");
	}

	//	return Thumbnail prefix
	function getStrThumbnail($field)
	{
		return $this->getFieldData($field, "StrThumbnail");
	}
	
//	return Thumbnail prefix
	function getThumbnailSize($field)
	{
		return $this->getFieldData($field, "ThumbnailSize");
	}

	//	resize on upload
	function getResizeOnUpload($field)
	{
		return $this->getFieldData($field, "ResizeImage");
	}
	
	// True if FileField must work in old single-file mode
	function isCompatibilityMode($field)
	{
		return $this->getFieldData($field, "CompatibilityMode");
	}
	// True if file in FileField must be uploaded immediately after choosing or dropping 
	function isAutoUpload($field)
	{
		return $this->getFieldData($field, "autoUpload");
	}
	
	//	get size to reduce image after upload
	function getNewImageSize($field)
	{
		return $this->getFieldData($field, "NewSize");
	}
	
	function getAcceptFileTypes($field)
	{
		return $this->getFieldData($field, "acceptFileTypes");
	}
	
	//	get maximum allowed size for uploaded files
	function getMaxFileSize($field)
	{
		return $this->getFieldData($field, "maxFileSize");
	}
	
	//	get maximum allowed size for all uploaded files per field
	function getMaxTotalFilesSize($field)
	{
		return $this->getFieldData($field, "maxTotalFilesSize");
	}
		
	//	get maximum allowed number of uploaded files
	function getMaxNumberOfFiles($field)
	{
		return $this->getFieldData($field, "maxNumberOfFiles");
	}
	
	//	get maximum allowed width of uploaded image
	function getMaxImageWidth($field)
	{
		return $this->getFieldData($field, "maxImageWidth");
	}
	
	//	get maximum allowed heiht of uploaded image
	function getMaxImageHeight($field)
	{
		return $this->getFieldData($field, "maxImageHeight");
	}	

	//	get size to reduce image after upload
	function getStrFilename($field)
	{
		return $this->getFieldData($field, "strFilename");
	}	
	
	//	return height of text area
	//	editFormat setting
	function getNRows($field)
	{
		return $this->getFieldData($field, "nRows");
	}

	//	return width of text area
	//	editFormat setting
	function getNCols($field)
	{
		return $this->getFieldData($field, "nCols");
	}

	//	return original table name
	function getOriginalTableName()
	{
		$result = $this->getTableData(".OriginalTable");
		return  $result != "" ? $result : $this->_table;
	}

	//	return list of key fields
	function getTableKeys()
	{
		return $this->getTableData(".Keys");
	}

	//	return number of chars to show before More... link
	function getNumberOfChars()
	{
		return $this->getTableData(".NumberOfChars");
	}
	
	function getFullNameField($field)
	{
		$result = $this->getFieldData($field, "FullName");
		return $result != "" ? $result : AddFieldWrappers($field);
	}
	
	//	return table Owner ID field
	function getTableOwnerID()
	{
		return $this->getTableData(".OwnerID");
	}

	//	is field marked as required
	//	editFormat setting 
	function isRequired($field)
	{
		return $this->getFieldData($field, "IsRequired");
	}

	//	use Rich Text Editor or not
	function isUseRTE($field)
	{
		return $this->getFieldData($field, "UseRTE");
	}

	//	use Rich Text Editor BASIC or not
	function isUseRTEBasic($field)
	{
		global $isUseRTEBasic;
		return $this->isUseRTE($field) && $isUseRTEBasic;
	}

	//	use Rich Text Editor FCK or not
	function isUseRTEFCK($field)
	{
		global $isUseRTECK;
		return $this->isUseRTE($field) && $isUseRTECK;
	}

	//	use Rich Text Editor INNOVA or not
	function isUseRTEInnova($field)
	{
		global $isUseRTEInnova;
		return $this->isUseRTE($field) && $isUseRTEInnova;
	}

	//	add timestamp to filename when uploading files or not
	//	editFormat setting
	function isUseTimestamp($field)
	{
		return $this->getFieldData($field, "UseTimestamp");
	}
	
	function getFieldIndex($field)
	{
		return $this->getFieldData($field, "Index");
	}
	
	//	return Date field edit type
	function getDateEditType($field)
	{
		return $this->getFieldData($field, "DateEditType");
	}

	// returns text edit parameters
	function getEditParams($field)
	{
		return $this->getFieldData($field, "EditParams");
	}
	
	function getInputStyle($field)
	{
		return $this->getFieldData($field, "inputStyle");
	}
	
	function getControlWidth($field)
	{
		return $this->getFieldData($field, "controlWidth");
	}
	
	//	check whether field is viewable
	function checkFieldPermissions($field)
	{
		return $this->getFieldData($field,"FieldPermissions");
	}
	
	function getTableOwnerIdField()
	{
		return $this->getTableData(".mainTableOwnerID");
	}
	
	function getDependentLookups($field)
	{
		return $this->getFieldData($field, "DependentLookups");
	}

	function isHorizontalLookup($field)
	{
		return $this->getFieldData($field, "HorizontalLookup");
	}

	function isDecimalDigits($field)
	{
		return $this->getFieldData($field, "DecimalDigits");
	}
	
	function getLookupValues($field)
	{
		return $this->getFieldData($field, "LookupValues");
	}
	
	function hasEditPage()
	{
		return $this->getTableData(".edit");
	}
	function hasInlineEdit()
	{
		return $this->getTableData(".inlineEdit");
	}
	function hasCopyPage()
	{
		return $this->getTableData(".copy");
	}
	function hasViewPage()
	{
		return $this->getTableData(".view");
	}
	function hasExportPage()
	{
		return $this->getTableData(".exportTo");
	}
	function hasPrintPage()
	{
		return $this->getTableData(".printFriendly");
	}
	function hasDelete()
	{
		return $this->getTableData(".delete");
	}
	function getTotalsFields()
	{
		return $this->getTableData(".totalsFields");
	}
	function getAdvancedSecurityType()
	{
		return $this->getTableData(".nSecOptions");
	}
	function displayLoading()
	{
		return $this->getTableData(".isDisplayLoading");
	}
	function getRecordsPerPageArray()
	{
		return $this->getTableData(".arrRecsPerPage");
	}
	function getGroupsPerPageArray()
	{
		return $this->getTableData(".arrGroupsPerPage");
	}
	function isReportWithGroups()
	{
		return $this->getTableData(".reportGroupFields");
	}
	function noRecordsOnFirstPage()
	{
		return $this->getTableData(".noRecordsFirstPage");
	}
	function getInitialPageSize()
	{
		return $this->getTableData(".pageSize");
	}
	function getRecordsPerRowList()
	{
		return $this->getTableData(".recsPerRowList");
	}
	function useMoveNext()
	{
		return $this->getTableData(".moveNext");
	}
	function highlightRows()
	{
		return $this->getTableData(".rowHighlite");
	}
	function hasInlineAdd()
	{
		return $this->getTableData(".inlineAdd");
	}
	function isVerticalLayoutList()
	{
		return $this->getTableData(".isVerLayout");
	}
	function tableSupportsSubqueries()
	{
		return $this->getTableData(".subQueriesSupAccess");
	}
	function iconsOnList()
	{
		return $this->getTableData(".listIcons");
	}
	function ajaxBasedListPage()
	{
		return $this->getTableData(".listAjax");
	}
	/**
	 * Returns array of tabs and sections, which use on add page
	 * @param string $table - current data source table name
	 * @return array
	 */
	function getAddTabs()
	{	
		return $this->getTableData(".arrAddTabs");
	}
	/**
	 * Check use tabs and sections on add page or not
	 * @param string $table - current data source table name
	 * @return boolean result - true or false
	 */
	function useTabsOnAdd()
	{
		if(count($this->getAddTabs()))
			return true;
		return false;
	}
	
	/**
	 * Returns array of tabs and sections, which use on edit page
	 * @param string $table - current data source table name
	 * @return array
	 */
	function getEditTabs()
	{	
		return $this->getTableData(".arrEditTabs");
	}
	
	/**
	 * Check use tabs and sections on edit page or not
	 * @param string $table - current data source table name
	 * @return boolean result - true or false
	 */
	function useTabsOnEdit()
	{
		if(count($this->getEditTabs()))
			return true;
		return false;
	}
	
	/**
	 * Returns array of tabs and sections, which use on view page
	 * @param string $table - current data source table name
	 * @return array
	 */
	function getViewTabs()
	{	
		return $this->getTableData(".arrViewTabs");
	}

	/**
	 * Check use tabs and sections on view page or not
	 * @param string $table - current data source table name
	 * @return boolean result - true or false
	 */
	function useTabsOnView()
	{
		if(count($this->getViewTabs()))
			return true;
		return false;
	}
	
	function getFieldsList()
	{
		if(is_null($this->_tableData))
			return array();
		$t = array_keys($this->_tableData);
		$arr = array();
		foreach($t as $f)
			if(substr($f,0,1)!=".")
				$arr[] = $f;
		return $arr;
	}

	function getBinaryFieldsIndices()
	{
		$fields = $this->getFieldsList();
		$out = array();
		foreach($fields as $idx => $f)
		{
			if(IsBinaryType($this->getFieldType($f)))
				$out[] = $idx + 1;
		}
		return $out;
	}
	
	function getNBFieldsList()
	{
		$t = $this->getFieldsList();
		$arr = array();
		foreach($t as $f)
			if(!IsBinaryType($this->getFieldType($f)))
				$arr[] = $f;
		return $arr;
	}

	function getLWDisplayField($field, $addWrap = true)
	{
		$result = $this->getDisplayField($field);
		if ($addWrap && !$this->getCustomDisplay($field))
			return AddFieldWrappers($result); 
		else 
			return $result;	
	}

	//	return field name
	function getFieldByGoodFieldName($field)
	{
		foreach($this->_tableData as $key => $value)
		{
			if(count($value) > 1)
			{
				if($value["GoodName"] == $field)
					return $key;
			}
		}
		return "";
	}
	
	/**
	 * getUploadFolder
	 * Return inputed value or calculated path for upload folder 
	 * @param {string} field name 
	 * @param {array} file info (name, type, size)
	 */
	function getUploadFolder($field, $fileData = array())
	{
		if($this->isUploadCodeExpression($field))
			$path = GetUploadFolderExpression($field, $fileData);
		else
			$path = $this->getFieldData($field, "UploadFolder");
				if(strlen($path) && substr($path,strlen($path)-1) != "/")
			$path.="/";
		return $path;
	}
	
	function isMakeDirectoryNeeded($field)
	{
		return $this->isUploadCodeExpression($field) || !$this->isAbsolute($field);
	}
	
	function getFinalUploadFolder($field, $fileData = array())
	{
		if($this->isAbsolute($field))
			$path = $this->getUploadFolder($field, $fileData);
		else
			$path = getabspath($this->getUploadFolder($field, $fileData));
				if(strlen($path) && substr($path,strlen($path)-1) != "/")
			$path.="/";
		return $path;
	}
	
	function isUploadCodeExpression($field)
	{
		return $this->getFieldData($field, "UploadCodeExpression");
	}

	function &getQueryObject()
	{
		$queryObj = $this->getSQLQuery();
		return $queryObj;
	}

	function getListOfFieldsByExprType($needaggregate)
	{
		$query = &$this->getSQLQuery();
		$fields = $this->getFieldsList();
		$out = array();
		foreach($fields as $idx=>$f)
		{
			$aggr = $query->IsAggrFuncField($idx);
			if($needaggregate && $aggr || !$needaggregate && !$aggr)
				$out[] = $f;
		}
		return $out;
	}
	
	function getNCSearch()
	{
		return $this->getTableData(".NCSearch");
	}
	
	function isEnableUpper($val)
	{
		if($this->getNCSearch())
			return db_upper($val);
		else
			return $val;
	}
	
	function getChartRefreshTime()
	{
		return $this->getTableData(".ChartRefreshTime");
	}
	
	function getChartXml()
	{
		return $this->getTableData(".chartXml");
	}
	
	function auditEnabled()
	{
		return $this->getTableData(".audit");
	}
	
	function lockingEnabled()
	{
		return $this->getTableData(".locking");
	}
	
	function hasEncryptedFields()
	{
		return $this->getTableData(".hasEncryptedFields");
	}
	
	function showSearchPanel()
	{
		return $this->getTableData(".showSearchPanel");
	}
	
	function showSimpleSearchOptions()
	{
		return $this->getTableData(".showSimpleSearchOptions");
	}
	function isCaseInsensitiveUsername()
	{
		global $caseInsensitiveUsername;
		return $caseInsensitiveUsername;
	}
	function getCaseSensitiveUsername($value)
	{
		if (!$this->isCaseInsensitiveUsername())
			return $value;
		return strtoupper($value);
	}
	function getTableField($field)
	{
		$result = $this->getFieldData($field, "strField");
		if($result != "")
			return AddFieldWrappers($result);
		else 
			return $this->getFullNameField($field);
	}
	function getScrollGridBody()
	{
		return $this->getTableData(".scrollGridBody");
	}
	
	//	Is 'UpdateLatLng' ticked for the field
	function isUpdateLatLng($field)
	{
		return $this->getFieldData($field, "UpdateLatLng");
	}
}

$pageTypesForView = array();
$pageTypesForView[] = "list";
$pageTypesForView[] = "view";
$pageTypesForView[] = "export";
$pageTypesForView[] = "print";
$pageTypesForView[] = "report";
$pageTypesForView[] = "rprint";
$pageTypesForView[] = "chart";

$pageTypesForEdit = array();
$pageTypesForEdit[] = "add";
$pageTypesForEdit[] = "edit";
$pageTypesForEdit[] = "search";
$pageTypesForEdit[] = "register";

//	return table short name
function GetTableURL($table = "")
{
	global $strTableName, $projectTables;
	if(!$table)
		$table=$strTableName;
	if("C000007" == $table) 
		return "C000007";
	if("C000008" == $table) 
		return "C000008";
	if("C000009" == $table) 
		return "C000009";
	if("C000010" == $table) 
		return "C000010";
	if("C000025" == $table) 
		return "C000025";
	if("C000040" == $table) 
		return "C000040";
	if("C000044" == $table) 
		return "C000044";
	if("C000046" == $table) 
		return "C000046";
	if("C000049" == $table) 
		return "C000049";
	if("C000071" == $table) 
		return "C000071";
	if("C000013" == $table) 
		return "C000013";
	if("C000054" == $table) 
		return "C000054";
	if("WEB" == $table) 
		return "WEB";
}

//	return strTableName by short table name
function GetTableByShort($shortTName = "")
{	
	global $projectTables;
	if(!$shortTName)
		return false;
	if("C000007" == $shortTName) 
		return "C000007";
	if("C000008" == $shortTName) 
		return "C000008";
	if("C000009" == $shortTName) 
		return "C000009";
	if("C000010" == $shortTName) 
		return "C000010";
	if("C000025" == $shortTName) 
		return "C000025";
	if("C000040" == $shortTName) 
		return "C000040";
	if("C000044" == $shortTName) 
		return "C000044";
	if("C000046" == $shortTName) 
		return "C000046";
	if("C000049" == $shortTName) 
		return "C000049";
	if("C000071" == $shortTName) 
		return "C000071";
	if("C000013" == $shortTName) 
		return "C000013";
	if("C000054" == $shortTName) 
		return "C000054";
	if("WEB" == $shortTName) 
		return "WEB";
}

//	A
	$g_defaultOptionValues["Absolute"] = false;
	$g_defaultOptionValues["acceptFileTypes"] = ".+$";
	$g_defaultOptionValues["addFields"] = array();
	$g_defaultOptionValues["addPageEvents"] = false;
	$g_defaultOptionValues["advSearchFields"] = array();
	$g_defaultOptionValues["AllowToAdd"] = false;
	$g_defaultOptionValues["allSearchFields"] = array();
	$g_defaultOptionValues["arrGroupsPerPage"] = array();
	$g_defaultOptionValues["arrKeyFields"] = array();
	$g_defaultOptionValues["arrAddTabs"] = array();
	$g_defaultOptionValues["arrEditTabs"] = array();
	$g_defaultOptionValues["arrRecsPerPage"] = array();
	$g_defaultOptionValues["arrViewTabs"] = array();
	$g_defaultOptionValues["audioTitleField"] = "";
	$g_defaultOptionValues["audit"] = false;
	$g_defaultOptionValues["autoCompleteFields"] = array();
	$g_defaultOptionValues["autoCompleteFieldsOnEdit"] = false;
	$g_defaultOptionValues["AutoInc"] = false;
	$g_defaultOptionValues["AutoUpdate"] = false;
	$g_defaultOptionValues["autoUpload"] = false;
//	B
	$g_defaultOptionValues["bAddPage"] = false;
	$g_defaultOptionValues["bAdvancedSearch"] = false;
	$g_defaultOptionValues["bEditPage"] = false;
	$g_defaultOptionValues["bExportPage"] = false;
	$g_defaultOptionValues["bInlineAdd"] = false;
	$g_defaultOptionValues["bInlineEdit"] = false;
	$g_defaultOptionValues["bIsEncrypted"] = false;
	$g_defaultOptionValues["bListPage"] = false;
	$g_defaultOptionValues["bPrinterPage"] = false;
	$g_defaultOptionValues["bViewPage"] = false;
//	C
	$g_defaultOptionValues["CompatibilityMode"] = false;
	$g_defaultOptionValues["CategoryControl"] = "";
	$g_defaultOptionValues["CategoryFilter"] = "";
	$g_defaultOptionValues["ChartRefreshTime"] = 10;
	$g_defaultOptionValues["chartXml"] = "";
	$g_defaultOptionValues["controlWidth"] = 0;
	$g_defaultOptionValues["copy"] = false;
	$g_defaultOptionValues["CreateThumbnail"] = false;
	$g_defaultOptionValues["CustomDisplay"] = false;
//	D
	$g_defaultOptionValues["DateEditType"] = 0;
	$g_defaultOptionValues["DecimalDigits"] = "";
	$g_defaultOptionValues["DefaultValue"] = "";
	$g_defaultOptionValues["delete"] = false;
	$g_defaultOptionValues["DeleteAssociatedFile"] = false;
	$g_defaultOptionValues["DependentLookups"] = array();
	$g_defaultOptionValues["DisplayField"] = "";
//	E
	$g_defaultOptionValues["edit"] = false;
	$g_defaultOptionValues["editFields"] = array();
	$g_defaultOptionValues["EditFormat"] = "";
	$g_defaultOptionValues["EditParams"] = "";
	$g_defaultOptionValues["exportTo"] = false;
//	F
	$g_defaultOptionValues["FastType"] = false;
	$g_defaultOptionValues["fieldIsVideoUrl"] = false;
	$g_defaultOptionValues["FieldType"] = "";
	$g_defaultOptionValues["FieldPermissions"] = false;
	$g_defaultOptionValues["fieldsForRegister"] = array();
	$g_defaultOptionValues["Filename"] = "";
	$g_defaultOptionValues["FormatTimeAttrs"] = array();
	$g_defaultOptionValues["freeInput"] = false;
	$g_defaultOptionValues["FullName"] = "";
//	G
	$g_defaultOptionValues["googleLikeFields"] = array();
	$g_defaultOptionValues["GoodName"] = "";
//	H
	$g_defaultOptionValues["hasEncryptedFields"] = false;
	$g_defaultOptionValues["HorizontalLookup"] = false;
//	I
	$g_defaultOptionValues["Index"] = 0;
	$g_defaultOptionValues["InitialYearFactor"] = "";
	$g_defaultOptionValues["inputStyle"] = "";
	$g_defaultOptionValues["ImageHeight"] = 0;
	$g_defaultOptionValues["ImageWidth"] = 0;
	$g_defaultOptionValues["inlineAdd"] = false;
	$g_defaultOptionValues["inlineAddFields"] = array();
	$g_defaultOptionValues["inlineEdit"] = false;
	$g_defaultOptionValues["inlineEditFields"] = array();
	$g_defaultOptionValues["isDisplayLoading"] = false;
	$g_defaultOptionValues["isTableType"] = "";
	$g_defaultOptionValues["IsRequired"] = false;
	$g_defaultOptionValues["isResizeColumns"] = false;
	$g_defaultOptionValues["isSeparate"] = false;
	$g_defaultOptionValues["isUpdateLatLng"] = false;
	$g_defaultOptionValues["isUseAjaxSuggest"] = false;
	$g_defaultOptionValues["isUseAudio"] = false;
	$g_defaultOptionValues["isUsebuttonHandlers"] = false;
	$g_defaultOptionValues["isUseFieldsMaps"] = false;
	$g_defaultOptionValues["isUseMainMaps"] = false;
	$g_defaultOptionValues["isUseTimeForSearch"] = false;
	$g_defaultOptionValues["isUseToolTips"] = false;
	$g_defaultOptionValues["isUseVideo"] = false;
	$g_defaultOptionValues["isVerLayout"] = false;
//	J
//	K
	$g_defaultOptionValues["Keys"] = array();
//	L
	$g_defaultOptionValues["Label"] = "";
	$g_defaultOptionValues["LastYearFactor"] = "";
	$g_defaultOptionValues["LCType"] = LCT_DROPDOWN;
	$g_defaultOptionValues["LinkField"] = "";
	$g_defaultOptionValues["LinkFieldType"] = 0;
	$g_defaultOptionValues["LinkPrefix"] = "";
	$g_defaultOptionValues["listAjax"] = false;
	$g_defaultOptionValues["listFields"] = array();
	$g_defaultOptionValues["listIcons"] = false;
	$g_defaultOptionValues["locking"] = false;
	$g_defaultOptionValues["LookupDesc"] = false;
	$g_defaultOptionValues["LookupOrderBy"] = "";
	$g_defaultOptionValues["LookupTable"] = "";
	$g_defaultOptionValues["LookupType"] = 0;
	$g_defaultOptionValues["LookupUnique"] = false;
	$g_defaultOptionValues["LookupValues"] = array();
	$g_defaultOptionValues["LookupWhere"] = "";
//	M
	$g_defaultOptionValues["mainTableOwnerID"] = "";
	$g_defaultOptionValues["mapData"] = array();
	$g_defaultOptionValues["maxFileSize"] = null;
	$g_defaultOptionValues["maxImageHeight"] = null;
	$g_defaultOptionValues["maxImageWidth"] = null;
	$g_defaultOptionValues["maxNumberOfFiles"] = null;
	$g_defaultOptionValues["maxTotalFilesSize"] = null;
	$g_defaultOptionValues["moveNext"] = "";
	$g_defaultOptionValues["Multiselect"] = false;
//	N
	$g_defaultOptionValues["NCSearch"] = false;
	$g_defaultOptionValues["NeedEncode"] = false;
	$g_defaultOptionValues["NewSize"] = 0;
	$g_defaultOptionValues["noRecordsFirstPage"] = false;
	$g_defaultOptionValues["nSecOptions"] = ADVSECURITY_NONE;
	$g_defaultOptionValues["NumberOfChars"] = 0;
//	O
	$g_defaultOptionValues["OriginalTable"] = "";
	$g_defaultOptionValues["orderindexes"] = array();
	$g_defaultOptionValues["OwnerID"] = 0;
	$g_defaultOptionValues["ownerTable"] = "";
//	P
	$g_defaultOptionValues["pageSize"] = 0;
	$g_defaultOptionValues["panelSearchFields"] = array();
	$g_defaultOptionValues["printFriendly"] = false;
	$g_defaultOptionValues["printFields"] = array();
//	Q
//	R
	$g_defaultOptionValues["recsPerRowList"] = "";
	$g_defaultOptionValues["reportGroupFields"] = false;
	$g_defaultOptionValues["ResizeImage"] = false;
	$g_defaultOptionValues["RewindEnabled"] = false;
	$g_defaultOptionValues["rowHighlite"] = false;
//	S
	$g_defaultOptionValues["SelectSize"] = 1;
	$g_defaultOptionValues["ShortName"] = "";
	$g_defaultOptionValues["shortTableName"] = "";
	$g_defaultOptionValues["showAddInPopup"] = false;
	$g_defaultOptionValues["ShowCustomExpr"] = false;
	$g_defaultOptionValues["showEditInPopup"] = false;
	$g_defaultOptionValues["ShowFileSize"] = false;
	$g_defaultOptionValues["ShowIcon"] = false;
	$g_defaultOptionValues["showSearchPanel"] = false;
	$g_defaultOptionValues["showSimpleSearchOptions"] = false;
	$g_defaultOptionValues["ShowThumbnail"] = false;
	$g_defaultOptionValues["ShowTime"] = false;
	$g_defaultOptionValues["showViewInPopup"] = false;
	$g_defaultOptionValues["SimpleAdd"] = false;
	$g_defaultOptionValues["sqlFrom"] = "";
	$g_defaultOptionValues["sqlHead"] = "";
	$g_defaultOptionValues["sqlTail"] = "";
	$g_defaultOptionValues["sqlWhereExpr"] = "";
	$g_defaultOptionValues["sqlquery"] = null;
	$g_defaultOptionValues["strField"] = "";
	$g_defaultOptionValues["strFilename"] = "";
	$g_defaultOptionValues["strName"] = "";
	$g_defaultOptionValues["strOrderBy"] = "";
	$g_defaultOptionValues["StrThumbnail"] = "";
	$g_defaultOptionValues["ThumbnailSize"] = 0;
	$g_defaultOptionValues["subQueriesSupAccess"] = false;
	$g_defaultOptionValues["scrollGridBody"] = false;
//	T
	$g_defaultOptionValues["totalsFields"] = array();
//	U
	$g_defaultOptionValues["UploadFolder"] = "";
	$g_defaultOptionValues["UploadCodeExpression"] = false;
	$g_defaultOptionValues["UseCategory"] = false;
	$g_defaultOptionValues["useDetailsPreview"] = false;
	$g_defaultOptionValues["UseRTE"] = false;
	$g_defaultOptionValues["UseTimestamp"] = false;
//	V
	$g_defaultOptionValues["validateAs"] = array();
	$g_defaultOptionValues["videoHeight"] = 200;
	$g_defaultOptionValues["videoWidth"] = 300;
	$g_defaultOptionValues["videoTitleField"] = "";
	$g_defaultOptionValues["view"] = false;
	$g_defaultOptionValues["ViewFormat"] = "";
	$g_defaultOptionValues["viewFields"] = array();
//	W
//	X
//	Y
//	Z

	
//	A
	$g_settingsType["Absolute"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["acceptFileTypes"] = SETTING_TYPE_EDIT;
	$g_settingsType["AllowToAdd"] = SETTING_TYPE_EDIT;
	$g_settingsType["autoCompleteFields"] = SETTING_TYPE_EDIT;
	$g_settingsType["autoCompleteFieldsOnEdit"] = SETTING_TYPE_EDIT;
	$g_settingsType["AutoInc"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["audioTitleField"] = SETTING_TYPE_VIEW;
	$g_settingsType["AutoUpdate"] = SETTING_TYPE_EDIT;
	$g_settingsType["autoUpload"] = SETTING_TYPE_EDIT;
//	B
	$g_settingsType["bAddPage"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bAdvancedSearch"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bEditPage"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bExportPage"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bInlineAdd"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bInlineEdit"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bIsEncrypted"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bListPage"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bPrinterPage"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["bViewPage"] = SETTING_TYPE_GLOBAL;
//	C
	$g_settingsType["CompatibilityMode"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["CategoryControl"] = SETTING_TYPE_EDIT;
	$g_settingsType["CategoryFilter"] = SETTING_TYPE_EDIT;
	$g_settingsType["controlWidth"] = SETTING_TYPE_EDIT;
	$g_settingsType["CreateThumbnail"] = SETTING_TYPE_EDIT;
	$g_settingsType["CustomDisplay"] = SETTING_TYPE_EDIT;
//	D
	$g_settingsType["DateEditType"] = SETTING_TYPE_EDIT;
	$g_settingsType["DecimalDigits"] = SETTING_TYPE_VIEW;
	$g_settingsType["DeleteAssociatedFile"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["DependentLookups"] = SETTING_TYPE_EDIT;
	$g_settingsType["DisplayField"] = SETTING_TYPE_EDIT;
//	E
	$g_settingsType["EditFormat"] = SETTING_TYPE_EDIT;
	$g_settingsType["EditParams"] = SETTING_TYPE_EDIT;
//	F
	$g_settingsType["FastType"] = SETTING_TYPE_EDIT;
	$g_settingsType["fieldIsVideoUrl"] = SETTING_TYPE_VIEW;
	$g_settingsType["FieldType"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["FieldPermissions"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["Filename"] = SETTING_TYPE_VIEW;
	$g_settingsType["FormatTimeAttrs"] = SETTING_TYPE_EDIT;
	$g_settingsType["freeInput"] = SETTING_TYPE_EDIT;
	$g_settingsType["FullName"] = SETTING_TYPE_GLOBAL;
//	G
	$g_settingsType["GoodName"] = SETTING_TYPE_GLOBAL;
//	H
	$g_settingsType["HorizontalLookup"] = SETTING_TYPE_EDIT;
//	I
	$g_settingsType["Index"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["InitialYearFactor"] = SETTING_TYPE_EDIT;
	$g_settingsType["inputStyle"] = SETTING_TYPE_EDIT;
	$g_settingsType["ImageHeight"] = SETTING_TYPE_VIEW;
	$g_settingsType["ImageWidth"] = SETTING_TYPE_VIEW;
	$g_settingsType["IsRequired"] = SETTING_TYPE_EDIT;
	$g_settingsType["isSeparate"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["isUpdateLatLng"] = SETTING_TYPE_EDIT;
//	J
//	K
//	L
	$g_settingsType["Label"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["LastYearFactor"] = SETTING_TYPE_EDIT;
	$g_settingsType["LCType"] = SETTING_TYPE_EDIT;
	$g_settingsType["LinkField"] = SETTING_TYPE_EDIT;
	$g_settingsType["LinkFieldType"] = SETTING_TYPE_EDIT;
	$g_settingsType["LinkPrefix"] = SETTING_TYPE_VIEW;
	$g_settingsType["LookupDesc"] = SETTING_TYPE_EDIT;
	$g_settingsType["LookupOrderBy"] = SETTING_TYPE_EDIT;
	$g_settingsType["LookupTable"] = SETTING_TYPE_EDIT;
	$g_settingsType["LookupType"] = SETTING_TYPE_EDIT;
	$g_settingsType["LookupUnique"] = SETTING_TYPE_EDIT;
	$g_settingsType["LookupValues"] = SETTING_TYPE_EDIT;
	$g_settingsType["LookupWhere"] = SETTING_TYPE_EDIT;
//	M
	$g_settingsType["mapData"] = SETTING_TYPE_VIEW;
	$g_settingsType["maxFileSize"] = SETTING_TYPE_EDIT;
	$g_settingsType["maxImageHeight"] = SETTING_TYPE_EDIT;
	$g_settingsType["maxImageWidth"] = SETTING_TYPE_EDIT;
	$g_settingsType["maxNumberOfFiles"] = SETTING_TYPE_EDIT;
	$g_settingsType["maxTotalFilesSize"] = SETTING_TYPE_EDIT;
	$g_settingsType["Multiselect"] = SETTING_TYPE_EDIT;
//	N
	$g_settingsType["nCols"] = SETTING_TYPE_EDIT;
	$g_settingsType["NeedEncode"] = SETTING_TYPE_VIEW;
	$g_settingsType["NewSize"] = SETTING_TYPE_EDIT;
	$g_settingsType["nRows"] = SETTING_TYPE_EDIT;
	$g_settingsType["nSecOptions"] = ADVSECURITY_NONE;
//	O
	$g_settingsType["ownerTable"] = SETTING_TYPE_GLOBAL;
//	P
//	Q
//	R
	$g_settingsType["ResizeImage"] = SETTING_TYPE_EDIT;
	$g_settingsType["RewindEnabled"] = SETTING_TYPE_VIEW;
//	S
	$g_settingsType["SelectSize"] = SETTING_TYPE_EDIT;
	$g_settingsType["ShowCustomExpr"] = SETTING_TYPE_VIEW;
	$g_settingsType["ShowFileSize"] = SETTING_TYPE_VIEW;
	$g_settingsType["ShowIcon"] = SETTING_TYPE_VIEW;
	$g_settingsType["ShowThumbnail"] = SETTING_TYPE_VIEW;
	$g_settingsType["ShowTime"] = SETTING_TYPE_EDIT;
	$g_settingsType["SimpleAdd"] = SETTING_TYPE_EDIT;
	$g_settingsType["strField"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["strFilename"] = SETTING_TYPE_VIEW;
	$g_settingsType["strEditMask"] = SETTING_TYPE_EDIT;
	$g_settingsType["strName"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["StrThumbnail"] = SETTING_TYPE_EDIT;
	$g_settingsType["ThumbnailSize"] = SETTING_TYPE_EDIT;
//	T
//	U
	$g_settingsType["UploadFolder"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["UploadCodeExpression"] = SETTING_TYPE_GLOBAL;
	$g_settingsType["UseCategory"] = SETTING_TYPE_EDIT;
	$g_settingsType["UseRTE"] = SETTING_TYPE_EDIT;
	$g_settingsType["UseTimestamp"] = SETTING_TYPE_EDIT;
//	V
	$g_settingsType["validateAs"] = SETTING_TYPE_EDIT;
	$g_settingsType["videoHeight"] = SETTING_TYPE_VIEW;
	$g_settingsType["videoWidth"] = SETTING_TYPE_VIEW;
	$g_settingsType["videoTitleField"] = SETTING_TYPE_VIEW;
	$g_settingsType["ViewFormat"] = SETTING_TYPE_VIEW;
//	W
//	X
//	Y
//	Z

?>