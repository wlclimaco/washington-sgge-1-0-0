<?php
class LookupField extends EditControl
{
	/**
	 * Name of table which is lookup source 
	 * @var unknown_type
	 */
	public $lookupTable = "";
	/**
	 * Type of lookup source (db table, project table, etc.)
	 * @var int
	 */
	public $lookupType = 0;
	/**
	 * Type of lookup control (Dropdown, List page with search, etc.)
	 * @var int
	 */
	public $LCType = 0;
	/**
	 * RunnerCipherer entity for link field
	 * @var RunnerCipherer
	 */
	public $ciphererLink = null;
	/**
	 * RunnerCipherer entity for display field
	 * @var RunnerCipherer
	 */
	public $ciphererDisplay = null;
	
	public $displayFieldName = "";
	public $linkFieldName = "";
	public $linkAndDisplaySame = false;
	
	public $linkFieldIndex = 0;
	public $displayFieldIndex = 0;
	
	public $lookupSize = 1;
	public $multiple = "";
	public $postfix = "";
	public $alt = "";
	public $strLookupWhere = "";
	
	public $clookupfield = "";
	public $openlookup = "";
	public $categoryFieldId = "";
	public $bUseCategory = false;
	public $horizontalLookup = false;
	public $addnewitem = false;
	
	public $isLinkFieldEncrypted = false;
	public $isDisplayFieldEncrypted = false;
	
	public $lookupPageType = "";
	
	public $lookupPSet = null;
	
	public $multiselect = false;
	public $lwLinkField = "";
	public $lwDisplayFieldWrapped = "";
	public $customDisplay = "";
	
	function LookupField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_TEXT_FIELD;
		
		if($pageObject->pageType == PAGE_LIST)
			$this->lookupPageType = PAGE_SEARCH;
		else 
			$this->lookupPageType = $pageObject->pageType; 
			
		$this->lookupTable = $this->pageObject->pSetEdit->getLookupTable($this->field);
		$this->lookupType = $this->pageObject->pSetEdit->getLookupType($this->field);
		if($this->lookupType == LT_QUERY)
			$this->lookupPSet = new ProjectSettings($this->lookupTable);
		$this->displayFieldName = $this->pageObject->pSetEdit->getDisplayField($this->field);
		$this->linkFieldName = $this->pageObject->pSetEdit->getLinkField($this->field);
		$this->linkAndDisplaySame = $this->displayFieldName == $this->linkFieldName;
		
		$this->ciphererLink = new RunnerCipherer($this->pageObject->tName);
		if($this->lookupType == LT_QUERY)
			$this->ciphererDisplay = new RunnerCipherer($this->lookupTable);
		else 
			$this->ciphererDisplay = $this->ciphererLink;
			
		$this->LCType = $this->pageObject->pSetEdit->lookupControlType($this->field);
		
		$this->multiselect = $this->pageObject->pSetEdit->multiSelect($this->field);
		$this->lwLinkField = $this->pageObject->pSetEdit->getLWLinkField($this->field);
		$this->lwDisplayFieldWrapped = $this->pageObject->pSetEdit->getLWDisplayField($this->field, true);
		$this->customDisplay = $this->pageObject->pSetEdit->getCustomDisplay($this->field);
	}
	
	/**
	 * This function need to bypass buildControl function of this class. It calls from LookupTextField class only.
	 */
	function parentBuildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		global $conn;
		$this->conn = $conn;
		
	//	read control settings
		$this->alt = "";
		if(($mode == MODE_INLINE_EDIT || $mode == MODE_INLINE_ADD) && $this->is508)
			$this->alt = ' alt="'.htmlspecialchars($this->strLabel).'" ';
		$this->cfield = "value_".GoodFieldName($this->field)."_".$this->id;
		$this->clookupfield = "display_value".($fieldNum ? $fieldNum : '')."_".GoodFieldName($this->field)."_".$this->id;
		$this->openlookup = "open_lookup_".GoodFieldName($this->field)."_".$this->id;
		$this->ctype = "type_".GoodFieldName($this->field)."_".$this->id;
		if($fieldNum)
		{
			$this->cfield = "value".$fieldNum."_".GoodFieldName($this->field)."_".$this->id;
			$this->ctype = "type".$fieldNum."_".GoodFieldName($this->field)."_".$this->id;
		}
		$this->addnewitem = false;
		$advancedadd = false;
		//$this->strCategoryControl = $this->pageObject->pSetEdit->getCategoryControl($this->field);
		$this->categoryFieldId = GoodFieldName($this->pageObject->pSetEdit->getCategoryControl($this->field));
		$this->bUseCategory = $this->pageObject->pSetEdit->useCategory($this->field);
		$dependentLookups = $this->pageObject->pSetEdit->getDependentLookups($this->field);
			
		$this->isLinkFieldEncrypted = $this->ciphererLink->isFieldPHPEncrypted($this->field);
			
		$this->horizontalLookup = $this->pageObject->pSetEdit->isHorizontalLookup($this->field);
		//$this->inputStyle = ($additionalCtrlParams['style'] ? 'style="'.$additionalCtrlParams['style'].'"' : '');
		$this->strLookupWhere = GetLWWhere($this->field, $this->lookupPageType, $this->pageObject->tName);
	
		$this->lookupSize = $this->pageObject->pSetEdit->selectSize($this->field);
		if($this->LCType == LCT_CBLIST)
			$this->lookupSize = 2; // simply > 1 for CBLIST
		
		$add_page = GetTableURL($this->lookupTable)."_add.php";
		$list_page = GetTableURL($this->lookupTable)."_list.php";
	
		$strPerm = GetUserPermissions($this->lookupTable);
	//	alter "add on the fly" settings	
		if(strpos($strPerm,"A")!==false)
		{
			$this->addnewitem = $this->pageObject->pSetEdit->isAllowToAdd($this->field);
			$advancedadd = !$this->pageObject->pSetEdit->isSimpleAdd($this->field);
			if(!$advancedadd)
				$this->addnewitem = false;
		}
	//	alter lookuptype settings
		if($this->LCType == LCT_LIST && strpos($strPerm,"S") === false)
			$this->LCType = LCT_DROPDOWN;
		if($this->LCType == LCT_LIST)
			$this->addnewitem = false;
		if($mode == MODE_SEARCH)
			$this->addnewitem = false;
	//	prepare multi-select attributes
		$this->multiple = "";
		$this->postfix = "";
		if($this->lookupSize > 1)
		{
			$avalue = splitvalues($value);
			$this->multiple = " multiple";
			$this->postfix = "[]";
		}
		else 
			$avalue = array((string)$value);
			
	//	prepare JS code
	
		$className = "DropDownLookup";
		if($this->LCType == LCT_AJAX)
			$className = "EditBoxLookup";
		elseif($this->LCType == LCT_LIST)
			$className = "ListPageLookup";
		elseif($this->LCType == LCT_CBLIST)
			$className = "CheckBoxLookup";
			
			
	
	//	build the control
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	list of values
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if($this->lookupType == LT_LISTOFVALUES)
		{
			$this->buildListOfValues($avalue, $value, $mode);
		}
		else
		{
			// build table-based lookup
		
			$this->isDisplayFieldEncrypted = ($this->lookupType == LT_QUERY || $this->linkAndDisplaySame) 
				&& $this->ciphererDisplay->isFieldPHPEncrypted($this->lookupType == LT_QUERY ? $this->displayFieldName : $this->field);	
	
		////////////////////////////////////////////////////////////////////////////////////////////
		//	table-based ajax-lookup control
		////////////////////////////////////////////////////////////////////////////////////////////
			if($this->LCType == LCT_AJAX || $this->LCType == LCT_LIST)
			{
				$this->buildAJAXLookup($value, $mode);
			}
			else
			{
				$this->buildClassicLookup($avalue, $value, $mode);
			}
		}
		$this->buildControlEnd($validate);
	}
	
	/**
	 * Get indexes of link and display fields
	 */
	public function fillLookupFieldsIndexes()
	{
		$lookupIndexes = GetLookupFieldsIndexes($this->pageObject->pSetEdit, $this->field);
		$this->linkFieldIndex = $lookupIndexes["linkFieldIndex"];
		$this->displayFieldIndex = $lookupIndexes["displayFieldIndex"];
	}
	
	public function buildListOfValues($avalue, $value, $mode)
	{
	//	read lookup values
		$arr = $this->pageObject->pSetEdit->getLookupValues($this->field);
//	print Type control to allow selecting nothing
		if($this->lookupSize > 1)
			echo "<input id=\"".$this->ctype."\" type=\"hidden\" name=\"".$this->ctype."\" value=\"multiselect\">";
//	dropdown control
		switch($this->LCType)
		{ 
			case LCT_DROPDOWN:
				echo '<select id="'.$this->cfield.'" size = "'.$this->lookupSize.'" name="'.$this->cfield.$this->postfix.'" '
					.$this->multiple.' '.$this->inputStyle.'>';
				if($this->lookupSize < 2 )
					echo '<option value="">'."Favor Selecionar".'</option>';
				else if($mode == MODE_SEARCH)
					echo '<option value=""> </option>';
					
				foreach($arr as $opt)
				{
					$res = array_search((string)$opt, $avalue);
					if(!($res===NULL || $res===FALSE))
			      		echo '<option value="'.htmlspecialchars($opt).'" selected>'.htmlspecialchars($opt).'</option>';
					else
			      		echo '<option value="'.htmlspecialchars($opt).'">'.htmlspecialchars($opt).'</option>';
				}
				echo "</select>";
				break;
			case LCT_CBLIST:
				echo '<div>';
				$spacer = '<br/>';
				if($this->horizontalLookup)
					$spacer = '&nbsp;&nbsp;';
				$i = 0;
				foreach($arr as $opt)
				{
					echo '<input id="'.$this->cfield.'_'.$i.'"  class="runner-checkbox" type="checkbox" '.$this->alt.' name="'.$this->cfield.$this->postfix.'" value="'
						.htmlspecialchars($opt).'"';
					$res = array_search((string)$opt, $avalue);
					if(!($res === NULL || $res === FALSE))
						echo ' checked="checked" ';
					echo '/>';
					echo '&nbsp;<b class="runner-checkbox-label" id="data_'.$this->cfield.'_'.$i.'">'.htmlspecialchars($opt).'</b>'.$spacer;
					$i++;
				}
				echo '</div>';
				break;
			case LCT_RADIO:
				$spacer = $this->horizontalLookup ? "&nbsp;&nbsp;" : "<br/>";
				echo '<input id="'.$this->cfield.'" type="hidden" name="'.$this->cfield.'" value="'.htmlspecialchars($value).'">';
				$i = 0;
				foreach($arr as $opt)
				{
					$checked = "";
					if($opt == $value)
						$checked = ' checked="checked" ';
					echo "<input  type=\"Radio\" class=\"runner-radio-button\" id=\"radio_".$this->cfieldname."_".$i."\" "
						.(($mode == MODE_INLINE_EDIT || $mode == MODE_INLINE_ADD) && $this->is508==true ? "alt=\"".$this->strLabel."\" " : "")
						."name=\"radio_".$this->cfieldname."\" ".$checked." value=\"".htmlspecialchars($opt)."\">"
						." <span id=\"label_radio_".$this->cfieldname."_".$i."\" class=\"runner-radio-label\">"
						.htmlspecialchars($opt)."</span >".$spacer;
					$i++;
				}
				break;
		}
	}
	
	public function buildAJAXLookup($value, $mode)
	{
////////////////////////////////////////////////////////////////////////////////////////////
//	dependent ajax-lookup control
////////////////////////////////////////////////////////////////////////////////////////////
		if($this->bUseCategory)
		{
			// ajax	dependent dropdown
			// get parent value
			$celementvalue = "var parVal = ''; var parCtrl = Runner.controls.ControlManager.getAt('".jsreplace($this->pageObject->tName)
				."', ".$this->id.", '".jsreplace($this->field)."', 0).parentCtrl; if (parCtrl){ parVal = parCtrl.getStringValue();};";
			if($this->LCType == LCT_AJAX)
				echo '<input type="text" categoryId="'.$this->categoryFieldId.'" autocomplete="off" id="'.$this->clookupfield.'" name="'
					.$this->clookupfield.'" '.$this->inputStyle.'>';
			else if($this->LCType == LCT_LIST)
			{	
				echo '<input type="text" categoryId="'.$this->categoryFieldId.'" autocomplete="off" id="'.$this->clookupfield.'" name="'
					.$this->clookupfield.'"  readonly '.$this->inputStyle.'>';				
				echo "&nbsp;<a href=# id=".$this->openlookup.">"."Selecione"."</a>";				
			}
			echo '<input type="hidden" id="'.$this->cfield.'" name="'.$this->cfield.'">';
			//	add new item link
			if($this->addnewitem)
				echo "&nbsp;<a href=# id='addnew_".$this->cfield."'>"."Adicionar Novo"."</a>";
				
			return;
		}

////////////////////////////////////////////////////////////////////////////////////////////
//	regular ajax-lookup control
////////////////////////////////////////////////////////////////////////////////////////////
		
//	get the initial value
		$lookup_value = "";
		$lookupSQL = buildLookupSQL($this->lookupPageType, $this->field, $this->pageObject->tName, "", $value, false, true, false, true);
		$this->fillLookupFieldsIndexes();
		$rs_lookup = db_query($lookupSQL, $this->conn);	
		if ( $data = db_fetch_numarray($rs_lookup) )
		{
			if($this->isDisplayFieldEncrypted) 
				$lookup_value = $this->ciphererDisplay->DecryptField($this->lookupType == LT_QUERY 
					? $this->displayFieldName : $this->field, $data[$this->displayFieldIndex]);
			else
				$lookup_value = $data[$this->displayFieldIndex];
		}
		elseif(strlen($this->strLookupWhere))
		{
		// try w/o WHERE expression
			$lookupSQL = buildLookupSQL($this->lookupPageType, $this->field, $this->pageObject->tName, "", 
				$value, false, true, false, false);
			$rs_lookup = db_query($lookupSQL, $this->conn);			
			if($data = db_fetch_numarray($rs_lookup))
			{
				if($this->isDisplayFieldEncrypted)
					$lookup_value = $this->ciphererDisplay->DecryptField($this->lookupType == LT_QUERY 
						? $this->displayFieldName : $this->field, $data[$this->displayFieldIndex]);
				else
					$lookup_value = $data[$this->displayFieldIndex];
			}
		}
//	build the control
		if($this->LCType == LCT_AJAX)
		{
			if (!strlen($lookup_value) && ($this->pageObject->pSetEdit->isfreeInput($this->field) || $this->lookupPageType == PAGE_SEARCH))
				$lookup_value = $value;
			
			echo '<input type="text" '.$this->inputStyle.' autocomplete="off" '.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) 
				&& $this->is508 == true ? 'alt="'.$this->strLabel.'" ' : '').'id="'.$this->clookupfield.'" name="'.$this->clookupfield
				.'" value="'.htmlspecialchars($lookup_value).'">';
		}
		elseif($this->LCType == LCT_LIST)
		{
			echo '<input type="text" autocomplete="off" '.$this->inputStyle.' id="'.$this->clookupfield.'" '
				.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508==true ? 'alt="'.$this->strLabel.'" ' : '')
				.'name="'.$this->clookupfield.'" value="'.htmlspecialchars($lookup_value).'" 	readonly >';			
			echo "&nbsp;<a href=# id=".$this->openlookup.">"."Selecione"."</a>";			
		}
		echo '<input type="hidden" id="'.$this->cfield.'" name="'.$this->cfield.'" value="'.htmlspecialchars($value).'">';
//	add new item
		if($this->addnewitem)
			echo "&nbsp;<a href=# id='addnew_".$this->cfield."'>"."Adicionar Novo"."</a>";		
	}
	
	public function buildClassicLookup($avalue, $value, $mode)
	{
	////////////////////////////////////////////////////////////////////////////////////////////
	//	classic lookup - start
	////////////////////////////////////////////////////////////////////////////////////////////
		
	////////////////////////////////////////////////////////////////////////////////////////////
	//	dependent classic lookup
	////////////////////////////////////////////////////////////////////////////////////////////
		if($this->bUseCategory)
		{
			//	print Type control to allow selecting nothing
			if($this->lookupSize > 1)
				echo "<input id=\"".$this->ctype."\" type=hidden name=\"".$this->ctype."\" value=\"multiselect\">";
			echo '<select size = "'.$this->lookupSize.'" id="'.$this->cfield.'" name="'.$this->cfield.$this->postfix.'"'.
				$this->multiple.' '.$this->inputStyle.'>';
			echo '<option value="">'."Favor Selecionar".'</option>';
			echo "</select>";
			if($this->addnewitem)
				echo "&nbsp;<a href=# id='addnew_".$this->cfield."'>"."Adicionar Novo"."</a>";
			return;
		}		
		
		$lookupSQL = buildLookupSQL($this->lookupPageType, $this->field, $this->pageObject->tName, "", "", false, false, false);
		$rs = db_query($lookupSQL,$this->conn);
		
		$this->fillLookupFieldsIndexes();
		
			
	////////////////////////////////////////////////////////////////////////////////////////////
	//	simple classic lookup
	////////////////////////////////////////////////////////////////////////////////////////////
	//	print control header
		if($this->lookupSize > 1)
			echo "<input id=\"".$this->ctype."\" type=hidden name=\"".$this->ctype."\" value=\"multiselect\">";
		if($this->LCType == LCT_DROPDOWN)
		{
			echo '<select size = "'.$this->lookupSize.'" id="'.$this->cfield.'" '
				.(($mode == MODE_INLINE_EDIT || $mode == MODE_INLINE_ADD) && $this->is508 == true 
				? 'alt="'.$this->strLabel.'" ' : '').'name="'.$this->cfield.$this->postfix.'"'.$this->multiple.' '.$this->inputStyle.'>';
			if($this->lookupSize < 2)
				echo '<option value="">'."Favor Selecionar".'</option>';
			else if($mode == MODE_SEARCH)
				echo '<option value=""> </option>';
			$spacer = "";
		}
		else
		{
			if($this->LCType == LCT_RADIO)
				echo '<input id="'.$this->cfield.'" type="hidden" name="'.$this->cfield.'" value="'.htmlspecialchars($value).'">';
			echo '<div>';
			if($this->horizontalLookup)
				$spacer = '&nbsp;&nbsp;';
			else 
				$spacer = '<br/>';
				
		}
	//	print lookup data
	   	$found = false;
		$i = 0;
		$isLookupUnique = $this->lookupType == LT_QUERY && $this->pageObject->pSetEdit->isLookupUnique($this->field);	
		while($data = db_fetch_numarray($rs))
		{
			if($isLookupUnique){
				if(!isset($uniqueArray))
					$uniqueArray = array();
				if(in_array($data[$this->linkFieldIndex], $uniqueArray))
					continue;
				$uniqueArray[] = $data[$this->linkFieldIndex];
			}
			$this->decryptDataRow($data);
			$res = array_search((string)$data[$this->linkFieldIndex],$avalue);
			$checked = "";
			if(!($res === NULL || $res === FALSE))
			{
				$found = true;
				if($this->LCType == LCT_CBLIST || $this->LCType == LCT_RADIO)
					$checked = " checked=\"checked\"";
				else
					$checked = " selected";
			}
			$this->buildLookupRow($mode, $data, $checked, $spacer, $i);
			$i++;
		}
	
	//	try the same query w/o WHERE clause if current value not found
		if(!$found && strlen($value) && $mode==MODE_EDIT && strlen($this->strLookupWhere))
		{
			$lookupSQL = buildLookupSQL($this->lookupPageType, $this->field,$this->pageObject->tName,"",$value,false,true,false,false,true);
			$this->fillLookupFieldsIndexes();
			$rs = db_query($lookupSQL, $this->conn);
			if($data = db_fetch_numarray($rs))
			{
				$this->decryptDataRow($data);
				if($this->LCType == LCT_CBLIST || $this->LCType == LCT_RADIO)
					$checked = " checked=\"checked\"";
				else
					$checked = " selected";
				$this->buildLookupRow($mode, $data, $checked, $spacer, $i);
			}
		}
	//	print footer
		if($this->LCType == LCT_DROPDOWN)
		{
			echo "</select>";
		}
		else
			echo '</div>';
	//	add new item
		if($this->addnewitem)
			echo "&nbsp;<a href=# id='addnew_".$this->cfield."'>"."Adicionar Novo"."</a>";
	}
	
	public function decryptDataRow(&$data)
	{
		if($this->isLinkFieldEncrypted)
			$data[$this->linkFieldIndex] = $this->ciphererLink->DecryptField($this->field, $data[$this->linkFieldIndex]);
		if($this->isDisplayFieldEncrypted)
			$data[$this->displayFieldIndex] = $this->ciphererDisplay->DecryptField($this->lookupType == LT_QUERY 
				? $this->displayFieldName : $this->field, $data[$this->displayFieldIndex]);
	}
	
	public function buildLookupRow($mode, $data, $checked, $spacer, $i)
	{
		switch($this->LCType)
		{ 
			case LCT_DROPDOWN:	
				echo '<option value="'.htmlspecialchars($data[$this->linkFieldIndex]).'"'.$checked.'>'
					.htmlspecialchars($data[$this->displayFieldIndex]).'</option>';
				break;
			case LCT_CBLIST:
				echo '<input id="'.$this->cfield.'_'.$i.'" class="runner-checkbox" type="checkbox" '.$this->alt.' name="'.$this->cfield.$this->postfix
					.'" value="'.htmlspecialchars($data[$this->linkFieldIndex]).'"'.$checked.'/>';
				echo '&nbsp;<b class="runner-checkbox-label" id="data_'.$this->cfield.'_'.$i.'">'.htmlspecialchars($data[$this->displayFieldIndex]).'</b>'.$spacer;
				break;
			case LCT_RADIO:
				echo "<input type=\"Radio\" class=\"runner-radio-button\" id=\"radio_".$this->cfieldname."_".$i."\" "
					.(($mode == MODE_INLINE_EDIT || $mode == MODE_INLINE_ADD) && $this->is508 == true ? "alt=\""
					.$this->strLabel."\" " : "")."name=\"radio_".$this->cfieldname."\" ".$checked." value=\""
					.htmlspecialchars($data[$this->linkFieldIndex])."\">"
					." <span id=\"label_radio_".$this->cfieldname."_".$i."\" class=\"runner-radio-label\">".
					htmlspecialchars($data[$this->displayFieldIndex])."</span>".$spacer;
				break;
		}
	} 
	
	function SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest)
	{
		if($this->lookupType == LT_LISTOFVALUES)
			return parent::SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest);
			
		$baseResult = $this->baseSQLWhere($strSearchOption);
		if($baseResult === false)
			return "";
		if($baseResult != "")
			return $baseResult;
			
		$displayFieldType = $this->type;
		if($this->lookupType == LT_QUERY)
		{
			$displayFieldType = $this->lookupPSet->getFieldType($this->field);
						$this->btexttype = IsTextType($displayFieldType);
		}	
			
		if($this->multiselect)
			$SearchFor = splitvalues($SearchFor);
		else
			$SearchFor = array($SearchFor);
			
		$ret="";
		
		if($this->linkAndDisplaySame)
			$gstrField = GetFullFieldName($this->field, "", false);
		else
			$gstrField = GetFullFieldName($this->displayFieldName, $this->lookupTable, false);
		if($this->customDisplay)
		{
			$gstrField = $this->lwDisplayFieldWrapped;
		}
		else if(!$this->linkAndDisplaySame && $this->lookupType == LT_QUERY && IsCharType($displayFieldType) && !$this->btexttype 
			&& !$this->ciphererDisplay->isFieldPHPEncrypted($this->displayFieldName))
		{
			$gstrField = $this->lookupPSet->isEnableUpper(GetFullFieldName($this->displayFieldName, $this->lookupTable, false));
		}
		
		foreach($SearchFor as $value)
		{
			if(!($value == "null" || $value == "Null" || $value == ""))
			{
				if(strlen(trim($ret)))
					$ret.=" or ";

				if(!$this->multiselect)
				{
					if($strSearchOption=="Starts with") $value .= '%';
					if($isSuggest || $strSearchOption=="Contains") $value = '%'.$value.'%';
						
					if($isSuggest || $strSearchOption=="Contains" ||$strSearchOption=="Starts with" || $strSearchOption=="More than" 
						|| $strSearchOption=="Less than" || $strSearchOption=="Equal or more than" || $strSearchOption=="Equal or less than" 
						|| $strSearchOption=="Between"
						|| $strSearchOption == "Equals" && $this->LCType == LCT_AJAX && !$this->linkAndDisplaySame)
					{
						$value = $this->escapeSearchValForMySQL($value);
							
						if($this->lookupType == LT_QUERY && IsCharType($displayFieldType) && !$this->btexttype)
							$value = $this->lookupPSet->isEnableUpper(db_prepare_string($value));
						else
							$value = db_prepare_string($value);
					}
					else if($strSearchOption == "Equals")
					{
						$value = make_db_value($this->field, $value);
					}
				}
					
				if($strSearchOption == "Equals")
				{
					if(!($value == "null" || $value == "Null"))
					{
						if($this->LCType == LCT_AJAX && !$this->linkAndDisplaySame)
							$condition = $gstrField.'='.$value;
						else
							$condition = GetFullFieldName($this->field, "", false).'='.$value;
					}
				}
				else if($strSearchOption=="Starts with" || $strSearchOption=="Contains" && !$this->multiselect) $condition = $gstrField." ".$this->like." ".$value;
				else if($strSearchOption=="More than") $condition = $gstrField." > ".$value;
				else if($strSearchOption=="Less than") $condition = $gstrField."<".$value;
				else if($strSearchOption=="Equal or more than") $condition = $gstrField.">=".$value1;
				else if($strSearchOption=="Equal or less than") $condition = $gstrField."<=".$value1;
				else if($strSearchOption=="Between")
				{					
					if($this->lookupType == LT_QUERY && IsCharType($displayFieldType) && !$this->btexttype)
						$value2 = $this->lookupPSet->isEnableUpper(db_prepare_string($SearchFor2));
					else
						$value2 = db_prepare_string($SearchFor2);
						
					$condition = $gstrField.">=".$value." and ";
					if (IsDateFieldType($this->type))
					{
						$timeArr = db2time($SearchFor2);
						// for dates without time, add one day
						if ($timeArr[3] == 0 && $timeArr[4] == 0 && $timeArr[5] == 0)
						{
							$timeArr = adddays($timeArr, 1);
							$SearchFor2 = $timeArr[0]."-".$timeArr[1]."-".$timeArr[2];
							$SearchFor2 = add_db_quotes($this->field, $SearchFor2, $this->pageObject->tName);
							$condition .= $gstrField."<".$SearchFor2;
						}
						else
						{
							$condition .= $gstrField."<=".$value2;
						}
					}
					else 
					{
						$condition .= $gstrField."<=".$value2;
					}	
				}
				else 
				{
					if(strpos($value,",")!==false || strpos($value,'"')!==false)
						$value = '"'.str_replace('"','""',$value).'"';
					
					$value = $this->escapeSearchValForMySQL($value);
					//for search by multiply Lookup wizard field
					$ret .= GetFullFieldName($this->field, "", false)." = ".db_prepare_string($value);
					$ret .= " or ".GetFullFieldName($this->field, "", false)." ".$this->like." ".db_prepare_string("%,".$value.",%");
					$ret .= " or ".GetFullFieldName($this->field, "", false)." ".$this->like." ".db_prepare_string("%,".$value);
					$ret .= " or ".GetFullFieldName($this->field, "", false)." ".$this->like." ".db_prepare_string($value.",%");
				}
				
				if($condition != "" && ($isSuggest || $strSearchOption=="Contains" || $strSearchOption == "Equals" ||$strSearchOption=="Starts with" 
					|| $strSearchOption=="More than" || $strSearchOption=="Less than" || $strSearchOption=="Equal or more than" 
					|| $strSearchOption=="Equal or less than" || $strSearchOption=="Between"))
				{
					
					if($this->linkAndDisplaySame || $strSearchOption == "Equals" && $this->LCType != LCT_AJAX)
						$ret .= " ".$condition;
					else
					{
						if($this->lookupType == LT_QUERY)
						{
							$lookupQueryObj = $this->lookupPSet->getSQLQuery();
							$ret .= " EXISTS (".$lookupQueryObj->toSql($condition." and "
								.GetFullFieldName($this->linkFieldName, $this->lookupTable, false)
								." = ".AddTableWrappers($this->pageObject->pSetEdit->getStrOriginalTableName())
								.".".AddFieldWrappers($this->field), '', null, false).")";
						}
						else 
						{
							$ret .= " EXISTS (SELECT 1 as fld from ".AddTableWrappers($this->lookupTable)
								." where ".$condition
								." and ".$this->lwLinkField." = ".AddTableWrappers($this->pageObject->pSetEdit->getStrOriginalTableName()).".".AddFieldWrappers($this->field).")";
						}
					}
				}
			}
		}
		if(strlen(trim($ret)))
			$ret = "(".$ret.")";
		else 
			$ret = trim($ret);
		return $ret;
	}
	
	function getSearchOptions($selOpt, $not, $both)
	{
		$optionsArray = array();
		if ($this->multiselect)
			$optionsArray[] = CONTAINS;	
		else
		{
			$this->ciphererLink = $this->pageObject->cipherer;
			if($this->lookupType == LT_QUERY)
				$this->ciphererDisplay = new RunnerCipherer($this->lookupTable);
			else 
				$this->ciphererDisplay = $this->pageObject->cipherer;
				
			$this->isDisplayFieldEncrypted = false;
			if($this->lookupType != LT_LISTOFVALUES)
			{
				$this->isDisplayFieldEncrypted = ($this->lookupType == LT_QUERY || $this->linkAndDisplaySame) 
					&& $this->ciphererDisplay->isFieldPHPEncrypted($this->lookupType == LT_QUERY ? $this->displayFieldName : $this->field);	
			}
			
			if($this->LCType == LCT_AJAX && !$this->isDisplayFieldEncrypted){
				$optionsArray[] = CONTAINS;
				$optionsArray[] = STARTS_WITH;
				$optionsArray[] = MORE_THAN;
				$optionsArray[] = LESS_THAN;
				$optionsArray[] = BETWEEN;
			}			
			$optionsArray[] = EQUALS;
		}
		$optionsArray[] = EMPTY_SEARCH;	
		if($both)
		{
			if ($this->multiselect)
				$optionsArray[] = NOT_CONTAINS;
			else
			{
				if($this->LCType == LCT_AJAX && !$this->isDisplayFieldEncrypted)
				{
					$optionsArray[] = NOT_CONTAINS;
					$optionsArray[] = NOT_STARTS_WITH;
					$optionsArray[] = NOT_MORE_THAN;
					$optionsArray[] = NOT_LESS_THAN;
					$optionsArray[] = NOT_BETWEEN;
				}
				$optionsArray[] = NOT_EQUALS;
			}
			$optionsArray[] = NOT_EMPTY;
		}
		return $this->buildSearchOptions($optionsArray, $selOpt, $not, $both);
	}
	
	function suggestValue($value, $searchFor, &$response, &$row)
	{
		if(!GetGlobalData("handleSearchSuggestInLookup", true))
		{
			parent::suggestValue($value, $searchFor, $response, $row);
			return;
		}
		
		global $conn;
		
		$lookupSQL = buildLookupSQL($this->lookupPageType, $this->field, $this->pageObject->tName, "", $value, false, true, false, true
			, true, true);
		$this->fillLookupFieldsIndexes();
		$rs_lookup = db_query($lookupSQL, $conn);	
		if ( $data = db_fetch_numarray($rs_lookup) )
		{
			if($this->isDisplayFieldEncrypted) 
				$lookup_value = $this->ciphererDisplay->DecryptField($this->lookupType == LT_QUERY 
					? $this->displayFieldName : $this->field, $data[$this->displayFieldIndex]);
			else
				$lookup_value = $data[$this->displayFieldIndex];
			
			parent::suggestValue($lookup_value, $searchFor, $response, $row);
		}
	}
}
?>