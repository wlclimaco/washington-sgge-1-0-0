<?php
class EditControl
{
	/**
	 * Reference to RunnerPage (or its descendant) instance 
	 */
	public $pageObject = null;
	/**
	 * Reference to EditControlsContainer instance
	 */
	public $container = null;
		
	public $id = "";
	public $field = "";
	public $goodFieldName = "";
	public $format = "";
	/**
	 * Field name prefix
	 * @var {string}
	 */
	public $cfieldname = "";
	/**
	 * Value field name
	 * @var {string}
	 */
	public $cfield = "";
	/**
	 * Type field name
	 * @var {string}
	 */
	public $ctype = "";
	/**
	 * A flag indicating whether the support for section 508 is on
	 * @var {bool}
	 */
	public $is508 = false;
	public $strLabel = "";
	public $type = "";
	public $inputStyle = "";
	public $iquery = "";
	public $keylink = "";
	public $webValue = null;
	public $webType = null;
	/**
	 * Reference to database connection
	 */
	public $conn = null;
	
	/**
	 * Storage for control settings. It fills in the init() function. 
	 * @var {array}
	 */
	public $settings = array();
	
	//Search params
	public $isOracle = false;
	public $ismssql = false;
	public $isdb2 = false;
	public $btexttype = false;
	public $isMysql = false;
	public $like = "like";
	
	public $searchOptions = array();
	
	function EditControl($field, $pageObject, $id)
	{
		$this->field = $field;
		$this->goodFieldName = GoodFieldName($field);
		$this->setID($id);

		$this->pageObject = $pageObject;

		$this->is508 = isEnableSection508();
		
		$this->strLabel = $pageObject->pSetEdit->label($field);
		$this->type = $pageObject->pSetEdit->getFieldType($this->field);
		

		$this->searchOptions[CONTAINS] = "Contém";
		$this->searchOptions[EQUALS] = "Igual a";
		$this->searchOptions[STARTS_WITH] = "Inicia com";
		$this->searchOptions[MORE_THAN] = "Maior que";
		$this->searchOptions[LESS_THAN] = "Menor  que";
		$this->searchOptions[BETWEEN] = "Entre";
		$this->searchOptions[EMPTY_SEARCH] = "Vazio";
		$this->searchOptions[NOT_CONTAINS] = "Não contém";
		$this->searchOptions[NOT_EQUALS] = "Não é igual a";
		$this->searchOptions[NOT_STARTS_WITH] = "Não começa com";
		$this->searchOptions[NOT_MORE_THAN] = "Não é maior que";
		$this->searchOptions[NOT_LESS_THAN] = "Não é menor que";
		$this->searchOptions[NOT_BETWEEN] = "Não é entre";
		$this->searchOptions[NOT_EMPTY] = "Não é nulo";
		
		$this->init();
	}
	
	function setID($id)
	{
		$this->id = $id;		
		$this->cfieldname = $this->goodFieldName."_".$id;
		$this->cfield = "value_".$this->goodFieldName."_".$id;
		$this->ctype = "type_".$this->goodFieldName."_".$id;
	}
	 
	/**
	 * addJSFiles
	 * Add control JS files to page object
	 */
	function addJSFiles()
	{
		//example
		// $this->pageObject->AddJSFile("include/mupload.js");
	}
	
	/**
	 * addCSSFiles
	 * Add control CSS files to page object
	 */ 
	function addCSSFiles()
	{
		//example
		// $this->pageObject->AddCSSFile("include/mupload.css");
	}
	
	function getSetting($key)
	{
		return $this->pageObject->pSetEdit->getFieldData($this->field, $key);
	}
	
	function addJSSetting($key, $value)
	{
		$this->pageObject->jsSettings['tableSettings'][$this->pageObject->tName]['fieldSettings'][$this->field][$this->pageObject->pageType][$key] = $value;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		$this->inputStyle = 'style="'.($additionalCtrlParams['style'] ? $additionalCtrlParams['style'] : '').$this->pageObject->pSetEdit->getInputStyle($this->field).'"';
	
		if($fieldNum)
		{
			$this->cfield="value".$fieldNum."_".$this->goodFieldName."_".$this->id;
			$this->ctype="type".$fieldNum."_".$this->goodFieldName."_".$this->id;
		}
		$arr = "";
		$this->iquery = "field=".rawurlencode($this->field);
	
		$arrKeys = $this->pageObject->pSetEdit->getTableKeys();
		for ($j = 0; $j < count($arrKeys); $j++) 
		{
			$this->keylink .= "&key".($j+1)."=".rawurlencode($data[$arrKeys[$j]]);
		}
		$this->iquery .= $this->keylink;
		
		$isHidden = (isset($additionalCtrlParams['hidden']) && $additionalCtrlParams['hidden']);
		echo '<span id="edit'.$this->id.'_'.$this->goodFieldName.'_'.$fieldNum.'" class="runner-nowrap"'.($isHidden ? ' style="display:none"' : '').'>';
	}
	
	function buildControlEnd($validate)
	{
		if(count($validate['basicValidate']) && array_search('IsRequired', $validate['basicValidate'])!==false)
			echo'&nbsp;<font color="red">*</font></span>';
		else
			echo '</span>';
	}
	
	function getPostValueAndType()
	{
		$this->webValue = postvalue("value_".$this->goodFieldName."_".$this->id);
		$this->webType = postvalue("type_".$this->goodFieldName."_".$this->id);
	}
	
	function getWebValue()
	{
		return $this->webValue;
	}
	
	function readWebValue(&$avalues, &$blobfields, $strWhereClause = "", $oldValuesRead = false, &$filename_values = null)
	{
		$this->getPostValueAndType();
		if (FieldSubmitted($this->goodFieldName."_".$this->id))
			$this->webValue = prepare_for_db($this->field, $this->webValue, $this->webType);
		else
			$this->webValue = false;
			
		if($this->pageObject->pageType == PAGE_EDIT && $this->pageObject->pSetEdit->isReadonly($this->field))
		{
			if($this->pageObject->pSetEdit->isAutoUpdate($this->field) && $this->pageObject->pSetEdit->getDefaultValue($this->field))
				$this->webValue = $this->pageObject->pSetEdit->getDefaultValue($this->field);
			else
				if($this->pageObject->pSetEdit->getOwnerTable($this->field) != $this->pageObject->pSetEdit->getStrOriginalTableName())
					$this->webValue = false;
		}
		
		if(!($this->webValue===false))
		{
					$avalues[$this->field] = $this->webValue;
		}
	} 
	
	function baseSQLWhere($strSearchOption)
	{
				$this->btexttype = IsTextType($this->type);
		
		if(IsBinaryType($this->type))
			return false;
			if($strSearchOption=='Empty')
		{
			if(IsCharType($this->type) && (!$this->ismssql || !$this->btexttype) && !$this->isOracle)
			{
				return "(".GetFullFieldNameForInsert($this->pageObject->pSetEdit, $this->field).
					" is null or ".GetFullFieldNameForInsert($this->pageObject->pSetEdit, $this->field)."='')";
			}
			elseif ($this->ismssql && $this->btexttype)
			{
				return "(".GetFullFieldNameForInsert($this->pageObject->pSetEdit, $this->field).
					" is null or ".GetFullFieldNameForInsert($this->pageObject->pSetEdit, $this->field)." LIKE '')";
			}
			else
			{
				return GetFullFieldNameForInsert($this->pageObject->pSetEdit, $this->field)." is null";
			}
		}
		return "";
	}
	
	function SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest)
	{
		$baseResult = $this->baseSQLWhere($strSearchOption);
		if($baseResult === false)
			return "";
		if($baseResult != "")
			return $baseResult;
			
		$value1 = $this->pageObject->cipherer->MakeDBValue($this->field, $SearchFor, $etype, "", true);
		$value2 = false;
		$cleanvalue2 = false;
		if($strSearchOption == "Between")
		{
			$cleanvalue2 = prepare_for_db($this->field, $SearchFor2, $etype);
			$value2 = make_db_value($this->field, $SearchFor2, $etype);
		}
			
		if($strSearchOption != "Contains" && $strSearchOption != "Starts with" && ($value1 === "null" || $value2 === "null" )
			&& !$this->pageObject->cipherer->isFieldPHPEncrypted($this->field))
			return "";
		
		if(IsCharType($this->type) && !$this->btexttype)
		{
			if(!$this->pageObject->cipherer->isFieldPHPEncrypted($this->field))
			{
				$value1 = $this->pageObject->pSetEdit->isEnableUpper($value1);
				$value2 = $this->pageObject->pSetEdit->isEnableUpper($value2);
				$gstrField = $this->pageObject->pSetEdit->isEnableUpper(GetFullFieldName($this->field, "", false));
			}
			else
				$gstrField = GetFullFieldName($this->field, "", false);
		}
		elseif($strSearchOption=="Contains" || $strSearchOption=="Starts with")
		{
			$gstrField = db_field2char(GetFullFieldName($this->field, "", false), $this->type);
		}
		elseif($this->pageObject->pSetEdit->getViewFormat($this->field)==FORMAT_TIME)
		{
			$gstrField = db_field2time(GetFullFieldName($this->field, "", false), $this->type);
		}
		else 
		{
			$gstrField = GetFullFieldName($this->field, "", false);
		}

		$ret="";
		
		if($strSearchOption=="Contains")
		{
			$SearchFor = $this->escapeSearchValForMySQL($SearchFor);
			if($this->pageObject->cipherer->isFieldPHPEncrypted($this->field))
				return $gstrField."=".$this->pageObject->cipherer->MakeDBValue($this->field, $SearchFor);
			
			if(IsCharType($this->type) && !$this->btexttype)
				return $gstrField." ".$this->like." ".$this->pageObject->pSetEdit->isEnableUpper(db_prepare_string("%".$SearchFor."%"));
			else
				return $gstrField." ".$this->like." ".db_prepare_string("%".$SearchFor."%");
		}
		else if($strSearchOption=="Equals") 
		{
			return $gstrField."=".$value1;
		}
		else if($strSearchOption=="Starts with")
		{
			$SearchFor = $this->escapeSearchValForMySQL($SearchFor);
			if(IsCharType($this->type) && !$this->btexttype)
				return $gstrField." ".$this->like." ".$this->pageObject->pSetEdit->isEnableUpper(db_prepare_string($SearchFor."%"));
			else
				return $gstrField." ".$this->like." ".db_prepare_string($SearchFor."%");
		}
		else if($strSearchOption=="More than") return $gstrField.">".$value1;
		else if($strSearchOption=="Less than") return $gstrField."<".$value1;
		else if($strSearchOption=="Equal or more than") return $gstrField.">=".$value1;
		else if($strSearchOption=="Equal or less than") return $gstrField."<=".$value1;
		else if($strSearchOption=="Between")
		{
			$ret = $gstrField.">=".$value1." and ";
			if (IsDateFieldType($this->type))
			{
				$timeArr = db2time($cleanvalue2);
				// for dates without time, add one day
				if ($timeArr[3] == 0 && $timeArr[4] == 0 && $timeArr[5] == 0)
				{
					$timeArr = adddays($timeArr, 1);
					$value2 = $timeArr[0]."-".$timeArr[1]."-".$timeArr[2];
					$value2 = add_db_quotes($this->field, $value2, $this->pageObject->tName);
					$ret .= $gstrField."<".$value2;
				}
				else
				{
					$ret.=$gstrField."<=".$value2;
				}
			}
			else 
			{
				$ret.=$gstrField."<=".$value2;
			}
			return $ret;
		}
		return "";
	}
	
	function escapeSearchValForMySQL($SearchFor)
	{
		if ($this->isMysql)
		{
			$SearchFor = str_replace('\\\\', '\\\\\\\\', $SearchFor);
		}
		return $SearchFor;
	}
	
	function buildSearchOptions($optionsArray, $selOpt, $not, $both)
	{
		$result = "";
		foreach ($optionsArray as $option)
		{
			$notFlag = false;
			if(isset($this->searchOptions[$option]) && (substr($option, 0, 4) != "NOT " || $both))
			{
				if(substr($option, 0, 4) == "NOT ")
				{
					$optionStr = substr($option, 4);
					$notFlag = true;
				} 
				else 
					$optionStr = $option;
				$result .= "<option value=\"".$option."\" "
					.(($selOpt == $optionStr && ($not && $notFlag || !$not && !$notFlag)) ? "selected" : "")
					.">".$this->searchOptions[$option]."</option>";
			}
		}
		return $result;
	}
	
	function getSearchOptions($selOpt, $not, $both)
	{
		return $this->buildSearchOptions(array(EQUALS, NOT_EQUALS), $selOpt, $not, $both);
	}
	
	function suggestValue($value, $searchFor, &$response, &$row)
	{
		$viewFormat = $this->pageObject->pSetEdit->getViewFormat($this->field);
		if($viewFormat == FORMAT_NUMBER)
		{
			$value = str_format_number($value, $this->pageObject->pSetEdit->isDecimalDigits($this->field));
			$dotPosition = strpos($value, '.'); 
			if($dotPosition !== FALSE)
			{
				for($i = strlen($value) - 1; $i > $dotPosition; $i--)
				{
					if(substr($value, $i, 1) != '0')
					{
						if($i < strlen($value) - 1)
							$value = substr($value, 0, $i + 1);
						break;
					}
					else if($i == $dotPosition + 1 && $dotPosition > 0)
					{
						$value = substr($value, 0, $dotPosition);
						break;
					}
				}
			}
		}
		
		$searchStringSize = GetGlobalData("suggestStringSize", 40);
		if($searchStringSize <= strlen($searchFor))
		{
			$response[$searchFor.""] = $searchFor."";
		}
		else 
		{
			$realValue = $value;
			if($searchStringSize < strlen($value))
			{
				$diff = $searchStringSize - strlen($searchFor);
				$lDiff = floor($diff / 2);
				$rDiff = $diff - $lDiff;
				if($this->pageObject->pSetEdit->getNCSearch())
					$startPos = stripos($value, $searchFor);
				else 
					$startPos = strpos($value, $searchFor);
				$searchStartPos = $startPos;
				$valueLength = strlen($value);
				if($startPos - $lDiff < 0)
				{
					$rDiff -= $startPos - $lDiff;
					$startPos = 0;
				}
				else 
					$startPos -= $lDiff;
				if($startPos > 0)
				{
					$found = false;
					for($i = $startPos - 1; 
						$i >= $startPos - 5 && $i >= 0; 
						$i--)
					{
						if($i == 0 || $this->isStopSimbol(substr($value, $i, 1)))
						{
							if($i == 0)
								$startPos = 0;
							else 
								$startPos = $i + 1;
							$found = true;
							break;
						}
					}
					if(!$found)
					{
						for($i = $startPos; 
							$i <= $startPos + 5 && $i < $searchStartPos; 
							$i++)
						{
							if($this->isStopSimbol(substr($value, $i, 1)))
							{
								$startPos = $i + 1;
								break;
							}
						}
					}
					$brakePos = strrpos($value, "\n", $searchStartPos - $valueLength);
					if($brakePos !== FALSE && $brakePos > $startPos)
						$startPos = $brakePos + 1;
					$brakePos = strrpos($value, "\r", $searchStartPos - $valueLength);
					if($brakePos !== FALSE && $brakePos > $startPos)
						$startPos = $brakePos + 1;
				}
				if($startPos + $searchStringSize > $valueLength)
				{
					$searchStringSize = $valueLength - $startPos;
				}
				if($startPos + $searchStringSize < $valueLength)
				{
					$found = false;
					$tempStartPos = $startPos + $searchStringSize;
					for($i = $tempStartPos + 1; 
						$i <= $tempStartPos + 5 && $i < $valueLength; 
						$i++)
					{
						if($i == $valueLength - 1 || $this->isStopSimbol(substr($value, $i, 1)))
						{
							if($i == $valueLength - 1)
								$searchStringSize = $i - $startPos + 1;
							else 
								$searchStringSize = $i - $startPos;
							$found = true;
							break;
						}
					}
					if(!$found)
					{
						for($i = $tempStartPos; 
							$i >= $tempStartPos - 5; 
							$i--)
						{
							if($this->isStopSimbol(substr($value, $i, 1)))
							{
								$searchStringSize = $i - $startPos;
								break;
							}
						}
					}
					$brakePos = strpos($value, "\n", $searchStartPos + strlen($searchFor));
					if($brakePos !== FALSE && $brakePos < $startPos + $searchStringSize)
						$searchStringSize = $brakePos - $startPos - 1;
					$brakePos = strpos($value, "\r", $searchStartPos + strlen($searchFor));
					if($brakePos !== FALSE && $brakePos < $startPos + $searchStringSize)
						$searchStringSize = $brakePos - $startPos - 1;					
				}
				$value = substr($value, $startPos, $searchStringSize);
				$realValue = $value;
				if($startPos > 0)
					$value = "...".$value;
				if($startPos + $substrLength != $valueLength)
					$value .= "...";
			}
			$response[$value.""] = $realValue."";
		}
	}
	
	function isStopSimbol($smb)
	{
		return strpos(" .,;:\"'?!|\\/=(){}[]*-+", $smb) !== false;
	}
	
	/**
	 * This function ivokes after successful saving of added/edited record
	 */
	function afterSuccessfulSave()
	{
	}
	
	/**
	 * Control settings filling
	 */
	function init()
	{
	}
}
class ControlTypes
{
	public $forEdit = null;
		
	public $forSearch = null;
		
	function ControlTypes() 
	{
		$this->forEdit = array();
		$this->forEdit[EDIT_FORMAT_TEXT_FIELD] = "TextField";
		$this->forEdit[EDIT_FORMAT_TIME] = "TimeField";
		$this->forEdit[EDIT_FORMAT_TEXT_AREA] = "TextAreaField";
		$this->forEdit[EDIT_FORMAT_PASSWORD] = "PasswordField";
		$this->forEdit[EDIT_FORMAT_DATE] = "DateField";
		$this->forEdit[EDIT_FORMAT_CHECKBOX] = "CheckboxField";
		$this->forEdit[EDIT_FORMAT_DATABASE_IMAGE] = "DatabaseFileField";
		$this->forEdit[EDIT_FORMAT_DATABASE_FILE] = "DatabaseFileField";
		$this->forEdit[EDIT_FORMAT_HIDDEN] = "HiddenField";
		$this->forEdit[EDIT_FORMAT_READONLY] = "ReadOnlyField";
		$this->forEdit[EDIT_FORMAT_FILE] = "FileField";
		$this->forEdit[EDIT_FORMAT_LOOKUP_WIZARD] = "LookupField";
			
		$this->forSearch = array();
		$this->forSearch[EDIT_FORMAT_TEXT_FIELD] = "TextField";
		$this->forSearch[EDIT_FORMAT_TIME] = "TimeField";
		$this->forSearch[EDIT_FORMAT_TEXT_AREA] = "TextField";
		$this->forSearch[EDIT_FORMAT_PASSWORD] = "TextField";
		$this->forSearch[EDIT_FORMAT_DATE] = "DateField";
		$this->forSearch[EDIT_FORMAT_CHECKBOX] = "CheckboxField";
		$this->forSearch[EDIT_FORMAT_DATABASE_IMAGE] = "TextField";
		$this->forSearch[EDIT_FORMAT_DATABASE_FILE] = "TextField";
		$this->forSearch[EDIT_FORMAT_HIDDEN] = "TextField";
		$this->forSearch[EDIT_FORMAT_READONLY] = "TextField";
		$this->forSearch[EDIT_FORMAT_FILE] = "FileField";
		$this->forSearch[EDIT_FORMAT_LOOKUP_WIZARD] = "LookupField";
	}
}
?>