<?php
class SearchClause
{
	/**
	 * Array with all session data
	 *
	 * @var array
	 */
	var $_where = array();
	
	/**
	 * Name of current table, for which instance of class was created
	 *
	 * @var string
	 */
	var $tName = "";
	/**
	 * Array of fields for basic search
	 *
	 * @var array
	 */
	var $searchFieldsArr = array();
	
	var $googleLikeFields = array();
	/**
	 * Type of search
	 *
	 * @var string
	 */
	//var $srchType = 'advanced';
	var $srchType = 'integrated';
	
	/**
	 * Session vars pref
	 *
	 * @var string
	 */
	var $sessionPrefix = "";
	/**
	 * Indicator, if used search it will be true
	 *
	 * @var bool
	 */
	var $bIsUsedSrch = false;
	/**
	 * Indicator, if used "search for" field it will be true
	 *
	 * @var bool
	 */
	var $isUsedSearchFor = false;
	/**
	 * Indicator, if used fields (added) for search it will be true
	 *
	 * @var bool
	 */
	var $isUsedFieldsForSearch = false;
	/**
	 * Indicator, if request have agregate fields it will be true
	 *
	 * @var bool
	 */
	var $haveAgregateFields = false;
	
	var $panelSearchFields = array();
	
	var $cipherer = null;
	
	var $searchOptions = array();
	
	var $fieldDelimiterLeft = ')';
	var $fieldDelimiterRight = '(';
	var $valueDelimiter = '~';
	
	/**
	 * Local instance of EditControlsContainer. Use only for compatibility with business templates
	 * @var {object}
	 */
	var $localEditControls = null;
	
	function SearchClause(&$params)
	{
		global $strTableName;
		$this->searchOptions["contains"] = array("option" => "Contains", "not" => false);
		$this->searchOptions["equals"] = array("option" => "Equals", "not" => false);
		$this->searchOptions["startswith"] = array("option" => "Starts with", "not" => false);
		$this->searchOptions["morethan"] = array("option" => "More than", "not" => false);
		$this->searchOptions["lessthan"] = array("option" => "Less than", "not" => false);
		$this->searchOptions["between"] = array("option" => "Between", "not" => false);
		$this->searchOptions["empty"] = array("option" => "Empty", "not" => false);
		$this->searchOptions["notcontain"] = array("option" => "Contains", "not" => true);
		$this->searchOptions["notequal"] = array("option" => "Equals", "not" => true);
		$this->searchOptions["notstartwith"] = array("option" => "Starts with", "not" => true);
		$this->searchOptions["notmorethan"] = array("option" => "More than", "not" => true);
		$this->searchOptions["notlessthan"] = array("option" => "Less than", "not" => true); 
		$this->searchOptions["notbetween"] = array("option" => "Between", "not" => true);
		$this->searchOptions["notempty"] = array("option" => "Empty", "not" => true);

		$this->tName = ($params['tName'] ? $params['tName'] : $strTableName);
		$this->sessionPrefix = ($params['sessionPrefix'] ? $params['sessionPrefix'] : $this->tName);
		$this->searchFieldsArr = $params['searchFieldsArr'];
		$this->cipherer = $params['cipherer'];
		$settings = new ProjectSettings($this->tName, PAGE_SEARCH);
		$this->panelSearchFields = ($params['panelSearchFields'] ? $params['panelSearchFields'] : $settings->getPanelSearchFields());
		$this->googleLikeFields = ($params['googleLikeFields'] ? $params['googleLikeFields'] : $settings->getGoogleLikeFields());		
	}
	/**
	 * Build where for advanced search. 
	 * Need for compability with old projects
	 *
	 * @protected
	 * @return string
	 */
	function buildAdvancedWhere() 
	{
		$sWhere="";
		if(isset($this->_where[$this->sessionPrefix."_asearchfor"]))
		{
			foreach($this->_where[$this->sessionPrefix."_asearchfor"] as $f => $sfor)
			{
				$strSearchFor = trim($sfor);
				$strSearchFor2 = "";
				$type=@$this->_where[$this->sessionPrefix."_asearchfortype"][$f];
				if(array_key_exists($f,@$this->_where[$this->sessionPrefix."_asearchfor2"]))
					$strSearchFor2=trim(@$this->_where[$this->sessionPrefix."_asearchfor2"][$f]);
				if($strSearchFor!="" || true)
				{
					if (!$sWhere) 
					{
						if($this->_where[$this->sessionPrefix."_asearchtype"]=="and")
							$sWhere="1=1";
						else
							$sWhere="1=0";
					}
					$strSearchOption=trim($this->_where[$this->sessionPrefix."_asearchopt"][$f]);
					if($where=StrWhereAdv($f, $strSearchFor, $strSearchOption, $strSearchFor2,$type, false))
					{
						if($this->_where[$this->sessionPrefix."_asearchnot"][$f])
							$where="not (".$where.")";
						if($this->_where[$this->sessionPrefix."_asearchtype"]=="and")
							$sWhere .= " and ".$where;
						else
							$sWhere .= " or ".$where;
					}
				}
			}
		}
			
		return $sWhere;
	}
	/**
	 * Build where for simple search. 
	 * Need for compability with old projects
	 * 
	 * @protected
	 * @return string
	 */
	function buildSimpleWhere() 
	{
		$sWhere = '';
		
		$strSearchFor = trim($this->_where[$this->sessionPrefix."_searchfor"]);
		$strSearchOption = trim($this->_where[$this->sessionPrefix."_searchoption"]);
		if(@$this->_where[$this->sessionPrefix."_searchfield"]) 
		{
			$strSearchField = $this->_where[$this->sessionPrefix."_searchfield"];
			if($where = StrWhereExpression($strSearchField, $strSearchFor, $strSearchOption, "", $this->cipherer))
				$sWhere = whereAdd($sWhere, $where);
			else
				$sWhere = whereAdd($sWhere, "1=0");
		} else {
			$strWhere = "1=0";
			for($i = 0; $i < count($this->searchFieldsArr); $i ++) {
				if($where = StrWhereExpression($this->searchFieldsArr[$i], $strSearchFor, $strSearchOption, "", $this->cipherer))
					$strWhere.= " or ".$where;
			}
			$sWhere = whereAdd($sWhere, $strWhere);
		}
		
		
		
		return $sWhere;
	}
	/**
	 * Build where for united search
	 * Params are common for advanced search and search panel on list
	 * Use in new projects
	 * 
	 * @protected
	 * @return string
	 *
	 */
	function buildItegratedWhere($fieldsArr, $editControls = null) 
	{
		if (!count($fieldsArr))
			return '';
		
		if(is_null($editControls))
		{
			if(is_null($this->localEditControls))
			{
				include_once getabspath("classes/controls/EditControlsContainer.php");
				$this->localEditControls = new EditControlsContainer(null, new ProjectSettings($this->tName, PAGE_SEARCH), PAGE_SEARCH,
					$this->cipherer);
			}
			$editControls = $this->localEditControls;
		}
			
		// get global options
		$srchType = $this->_where[$this->sessionPrefix."_srchType"];
		$srchFields = &$this->_where[$this->sessionPrefix."_srchFields"];
		$sWhere = '';
		if(!($this->haveAgregateFields && $this->isUsedFieldsForSearch))
		{
			$simpleSrch = $this->_where[$this->sessionPrefix."_simpleSrch"];
			if (trim($simpleSrch) === '%')
			{
				$simpleSrch = '['.$simpleSrch.']';
			}
			// build where for any field contains search
			if (strlen($simpleSrch) || $this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] == "Empty")
			{			
				if (strlen($this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"]))
				{
					$where = $editControls->getControl($this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"])->SQLWhere($simpleSrch
						, $this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"], "", "", true);
					if($where && $this->_where[$this->sessionPrefix."simpleSrchTypeComboNot"])
					{
						$where ="not (".$where.")";
					}
					$sWhere = $where;
				}
				else 
				{
					for($i = 0; $i < count($this->searchFieldsArr); $i++) {
						if (in_array($this->searchFieldsArr[$i], $fieldsArr) && in_array($this->searchFieldsArr[$i]
							, $this->googleLikeFields))
						{
							$where = $editControls->getControl($this->searchFieldsArr[$i], "")->SQLWhere($simpleSrch
								, $this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"], "", "", true);
							// add not 
							if(trim($where) != "" && $this->_where[$this->sessionPrefix."simpleSrchTypeComboNot"])
							{
								$where ="not (".$where.")";
							}
							if(trim($where) != "")
							{
								if($sWhere)
									$sWhere.= " or ";
								$sWhere.= $where;
							}
						}
					}
				}
			}
		}
		
		$resWhere = whereAdd('', $sWhere);
		// if there are fields for build advanced where
		$sWhere = '';
		if (count($srchFields)){
			// prepare vars
			$sWhere = $srchType=="and" ? "(1=1" : "(1=0";
			$prevSrchFieldName = '';
			
			// build where
			foreach ($srchFields as $srchF)
			{	
				if (in_array($srchF['fName'], $fieldsArr))
				{
					$where = $editControls->getControl($srchF['fName'], "")->SQLWhere($srchF['value1'], $srchF['opt'], $srchF['value2'], 
						$srchF['eType'], false);
					if($where)
					{
						// add not 
						if($srchF['not'])
						{
							$where="not (".$where.")";
						}
						// and|or depends on search type
						if($srchType=="and")
						{
							// add ( if we add new clause block for same field name
								$sWhere .= ($prevSrchFieldName != $srchF['fName'] ? ") and (" : " and ").$where;
						}
							else
							{
								$sWhere .= " or ".$where;
							}
					}
					$prevSrchFieldName = $srchF['fName'];
				}
			}
			// add ) to final field block clause
			$sWhere .= ')';
		}
		$resWhere = whereAdd($resWhere, $sWhere);
		
		return $resWhere;
	}
	/**
	 * Public, return where clause
	 *
	 * @public
	 * @return string
	 */	
	function getWhere($fieldsArr, $editControls = null)
	{
		$sWhere = '';
		switch ($this->srchType){
			case 'showall' : 
				$sWhere = '';
				break;
			case 'advanced' :
				$sWhere = $this->buildAdvancedWhere();
				break;
			case 'simple' :
				$sWhere = $this->buildSimpleWhere();
				break;
			case 'integrated' :
				$sWhere = $this->buildItegratedWhere($fieldsArr, $editControls);
				break;
			default:
				$sWhere = '';
		}
		return $sWhere;
	}
	
	function getSuggestWhere($editControl, $suggestAllContent, $searchVal) 
	{
		$sWhere = '';
		$searchOpt = $suggestAllContent ? "Contains" : "Starts with";
		$where = $editControl->SQLWhere($searchVal, $searchOpt, "", "", true);
		return $where;
	}
	
	/**
	 * Parse form with advanced search REQUEST	 
	 * Need for compability with old projects
	 * 
	 * @protected
	 * @return string
	 */
	function parseAdvancedRequest() 
	{
		$additionalControlId = 1;
		
		$this->_where[$this->sessionPrefix."_asearchnot"] = array();
		$this->_where[$this->sessionPrefix."_asearchopt"] = array();
		$this->_where[$this->sessionPrefix."_asearchfor"] = array();
		$this->_where[$this->sessionPrefix."_asearchfor2"] = array();
		$tosearch = 0;
		$asearchfield = postvalue("asearchfield");
		$this->_where[$this->sessionPrefix."_asearchtype"] = postvalue("type");
		if(!$this->_where[$this->sessionPrefix."_asearchtype"])
			$this->_where[$this->sessionPrefix."_asearchtype"] = "and";
		if(isset($asearchfield) && is_array($asearchfield))
		{
			foreach($asearchfield as $field)
			{
				$gfield = GoodFieldName($field);
				
				$not = postvalue("not_".$gfield);
				$type = postvalue("type_".$gfield);
				$asopt = postvalue("asearchopt_".$gfield);
				$value1 = postvalue("value_".$gfield);
				$value2 = postvalue("value1_".$gfield);
				
				if($value1 || $asopt=='Empty')
				{
					$tosearch = 1;
					$this->_where[$this->sessionPrefix."_asearchopt"][$field] = $asopt;
					if(!is_array($value1))
						$this->_where[$this->sessionPrefix."_asearchfor"][$field] = $value1;
					else
						$this->_where[$this->sessionPrefix."_asearchfor"][$field] = ($value1);
					$this->_where[$this->sessionPrefix."_asearchfortype"][$field] = $type;
					if($value2)
						$this->_where[$this->sessionPrefix."_asearchfor2"][$field] = $value2;
					$this->_where[$this->sessionPrefix."_asearchnot"][$field] = ($not=="on");
				}
			}
		}
		if($tosearch)
			$this->_where[$this->sessionPrefix."_search"] = 2;
		else
			$this->_where[$this->sessionPrefix."_search"] = 0;
		$this->_where[$this->sessionPrefix."_pagenumber"] = 1;
	}
	/**
	 * Parse form with simple search REQUEST
	 * Need for compability with old projects
	 * 
	 * @protected
	 * @return string
	 */
	function parseSimpleRequest() 
	{
		if(postvalue("SearchField") == "" || in_array(postvalue("SearchField"), $this->searchFieldsArr) === true) 
		{
			$this->_where[$this->sessionPrefix."_searchfield"] = postvalue("SearchField");
			$this->_where[$this->sessionPrefix."_searchoption"] = postvalue("SearchOption");
			$this->_where[$this->sessionPrefix."_searchfor"] = postvalue("SearchFor");
			if(postvalue("SearchFor") != "" || postvalue("SearchOption") == 'Empty')
				$this->_where[$this->sessionPrefix."_search"] = 1;
			else
				$this->_where[$this->sessionPrefix."_search"] = 0;
			$this->_where[$this->sessionPrefix."_pagenumber"] = 1;
		}
		else
			$this->_where[$this->sessionPrefix."_search"] = 0;
	}
	/**
	 * Parse form with union search REQUEST (for new versions: 6.2 and newest)
	 * Params are common for advanced search and search panel on list
	 * Use in new projects
	 * 
	 * @protected
	 * @return string
	 */
	function parseItegratedRequest() 
	{
		if(postvalue('qs') == "" && postvalue('q') == "")
			return $this->parseItegratedRequestOld();
		
		global $suggestAllContent;
		// parse global options
		
		$this->_where[$this->sessionPrefix."_simpleSrch"] = '';	
		$this->isUsedSearchFor = false;
		$this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] = $suggestAllContent ? "Contains" : "Starts with";
		$this->_where[$this->sessionPrefix."simpleSrchTypeComboNot"] = false;
		$this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"] = '';
		
		if(postvalue('qs') != "")
		{
			$tempArr = $this->parseStringToArray(postvalue('qs'));
			$simpleQueryArr = $tempArr[0];
			$this->_where[$this->sessionPrefix."_simpleSrch"] = $this->searchUnEscape($simpleQueryArr[0]);	
			$this->isUsedSearchFor = $simpleQueryArr[0] != '';
			if(isset($this->searchOptions[$this->getArrayValueByIndex($simpleQueryArr, 2, true)]))
			{
				$simpleSrchTypeComboNot = $this->searchOptions[$simpleQueryArr[2]]["not"];
				$this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] = $this->searchOptions[$simpleQueryArr[2]]["option"];
				if (!strlen($this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"]))
				{
					$this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] = $suggestAllContent ? "Contains" : "Starts with";
				}
			}
			$this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"] = trim($this->getArrayValueByIndex($simpleQueryArr, 1, true));
		}
		
		
		$srchType = postvalue("criteria");
		if(!$srchType)
			$srchType="and";
		$_SESSION[$this->sessionPrefix."_criteria"] = $srchType;
		$this->_where[$this->sessionPrefix."_srchType"] = $srchType;
		// prepare vars
		$this->_where[$this->sessionPrefix."_srchFields"] = array();
		// scan all srch fields
		$this->isUsedFieldsForSearch = false;
		$pSet = new ProjectSettings($this->tName, PAGE_SEARCH);
		if(postvalue('q') != "")
		{
			$j = 1;
			$searchFieldsArr = $this->parseStringToArray(postvalue('q'), true);
			foreach ($searchFieldsArr as $searchItemArr) 
			{
				if(count($searchItemArr) > 1)
				{
					$fName = $this->searchUnEscape($searchItemArr[0]);
					// check if field in request exist in searchFieldsArray, for prevent SQL injection
					if (in_array($fName, $this->searchFieldsArr))
					{
						$this->isUsedFieldsForSearch = true;
						$srchF = array();
						$srchF['fName'] = $fName;
						$srchF['eType'] = $this->getArrayValueByIndex($searchItemArr, 3);
						$srchF['value1'] = $this->getArrayValueByIndex($searchItemArr, 2, true);
						$opt = $this->getArrayValueByIndex($searchItemArr, 1);
						$srchF['not'] = false;
						if(isset($this->searchOptions[$opt]))
						{
							$srchF['not'] = $this->searchOptions[$opt]["not"]; 
							$srchF['opt'] =  $this->searchOptions[$opt]["option"];
						}
						else 
						{
							$srchF['opt'] = $this->getDefaultSearchTypeOption($fName, $pSet);
						}
						$srchF['value2'] = $this->getArrayValueByIndex($searchItemArr, 4, true);	
						$this->_where[$this->sessionPrefix."_srchFields"][] = $srchF;
					}
					$j++;
				}
			}
		}	
		
		// process srch panel attrs, better then use coockies. 
		$this->_where[$this->sessionPrefix."_srchOptShowStatus"]= postvalue('srchOptShowStatus')==='1';// || count($this->_where[$this->sessionPrefix."_srchFields"])>0;
		$this->_where[$this->sessionPrefix."_ctrlTypeComboStatus"]= postvalue('ctrlTypeComboStatus')==='1';
		$this->_where[$this->sessionPrefix."srchWinShowStatus"]= postvalue('srchWinShowStatus')==='1';
		
	}
	function searchUnEscape($inputString)
	{
		return str_replace("\\\\", "\\", 
			str_replace("\\".$this->valueDelimiter, $this->valueDelimiter, 
				str_replace("\\".$this->fieldDelimiterLeft.$this->fieldDelimiterRight,
					$this->fieldDelimiterLeft.$this->fieldDelimiterRight, $inputString)));
	}
	function parseStringToArray($inputString, $advanced = false)
	{
		$result = array();
		$valuesArray = array();
		$startPos = 0;
		if($advanced)
			$inputString = substr($inputString, 1, strlen($inputString) - 2);
		$strLength = strlen($inputString);
		for($i = 0; $i < $strLength; $i++)
		{
			if($inputString[$i] == $this->valueDelimiter)
				if($this->isDelimiter($inputString, $startPos, $i))
				{
					$valuesArray[] = substr($inputString, $startPos, $i - $startPos);
					$startPos = $i + 1;
				}
			if($i == $strLength - 1 || $inputString[$i] == $this->fieldDelimiterLeft)
				if($i == $strLength - 1 || $this->isDelimiter($inputString, $startPos, $i, true))
				{
					$valuesArray[] = substr($inputString, $startPos, $i - $startPos + ($i == $strLength - 1 ? 1 : 0));
					$result[] = $valuesArray;
					$valuesArray = array();
					$startPos = $i + 2;
					$i++;
				}
		}
		return $result;
	}
	function isDelimiter(&$inputString, $startPos, $currentPos, $isFieldDelimiter = false)
	{
		$backSlahesCount = 0;
		for($i = $currentPos - 1; $i >= $startPos; $i--)
		{
			if($inputString[$i] != '\\')
				break;
			$backSlahesCount++;
		}
		$result = $backSlahesCount == 0 || $backSlahesCount % 2 == 0;
		if($result && $isFieldDelimiter && strlen($inputString) > $currentPos + 1)
		{
			return $inputString[$currentPos + 1] == $this->fieldDelimiterRight;
		}
		return $result; 
	}
	function getArrayValueByIndex(&$arr, $index, $isEncoded = false)
	{
		$result = "";
		if(isset($arr[$index]))
		{
			$result = $arr[$index];
			if($isEncoded)
				$result = $this->searchUnEscape($result);
		}
		return trim($result);
	}
	function getDefaultSearchTypeOption($fName, $pSet) 
	{
		$fType = $pSet->GetEditFormat($fName);
		$option = "Equals";
		if($fType == EDIT_FORMAT_LOOKUP_WIZARD)
		{
			if ($pSet->multiSelect($fName))
				$option = "Contains";	
		}
		elseif ($fType == EDIT_FORMAT_TEXT_FIELD || $fType == EDIT_FORMAT_TEXT_AREA || $fType == EDIT_FORMAT_PASSWORD 
					|| $fType == EDIT_FORMAT_HIDDEN || $fType == EDIT_FORMAT_READONLY)
		{
			if(!$this->cipherer->isFieldPHPEncrypted($fName))
				$option = "Contains";
		}
		
		return $option;
	}
	/**
	 * Parse form with union search REQUEST (for old versions: 6.1 and older)
	 * Params are common for advanced search and search panel on list
	 * Use in new projects
	 * 
	 * @protected
	 * @return string
	 */
	function parseItegratedRequestOld() 
	{
		global $suggestAllContent;
		// parse global options
		$this->_where[$this->sessionPrefix."_simpleSrch"] = trim(postvalue("ctlSearchFor"));	
		$this->isUsedSearchFor = $this->_where[$this->sessionPrefix."_simpleSrch"] != '';
		$this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] = trim(postvalue("simpleSrchTypeComboOpt"));
		if (!strlen($this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"]))
		{
			$this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] = $suggestAllContent ? "Contains" : "Starts with";
		}
		$this->_where[$this->sessionPrefix."simpleSrchTypeComboNot"] = trim(postvalue("simpleSrchTypeComboNot")) != '';
		$this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"] = trim(postvalue("simpleSrchFieldsComboOpt"));
		
		
		$srchType = postvalue("criteria");
		if(!$srchType)
			$srchType="and";
		$_SESSION[$this->sessionPrefix."_criteria"] = $srchType;
		$this->_where[$this->sessionPrefix."_srchType"] = $srchType;
		// prepare vars
		$this->_where[$this->sessionPrefix."_srchFields"] = array();
		$j = 1;
		// scan all srch fields
		$this->isUsedFieldsForSearch = false;
		while ($fName = postvalue('field'.$j)) 
		{
			// check if field in request exist in searchFieldsArray, for prevent SQL injection
			if (in_array($fName, $this->searchFieldsArr))
			{
				$this->isUsedFieldsForSearch = true;
				$srchF = array();
				$srchF['fName'] = trim($fName);
				$srchF['eType'] = trim(postvalue('type'.$j));
				$srchF['value1'] = trim(postvalue('value'.$j.'1'));
				$srchF['opt'] = (postvalue('option'.$j) ? postvalue('option'.$j) : 'Contains');
				$srchF['value2'] = trim(postvalue('value'.$j.'2'));	
				$srchF['not'] = postvalue('not'.$j) == 'on';
				$this->_where[$this->sessionPrefix."_srchFields"][] = $srchF;
			}
			$j++;
		}	
		
		// process srch panel attrs, better then use coockies. 
		$this->_where[$this->sessionPrefix."_srchOptShowStatus"]= postvalue('srchOptShowStatus')==='1';// || count($this->_where[$this->sessionPrefix."_srchFields"])>0;
		$this->_where[$this->sessionPrefix."_ctrlTypeComboStatus"]= postvalue('ctrlTypeComboStatus')==='1';
		$this->_where[$this->sessionPrefix."srchWinShowStatus"]= postvalue('srchWinShowStatus')==='1';
	}
	
	/**
	 * Parse REQUEST
	 * 
	 * @public
	 * @return string
	 */
	function parseRequest()
	{
		//set session if show all records
		if(@$_REQUEST["a"] == "showall"){
			$this->_where[$this->sessionPrefix."_search"]= 0;
			$this->srchType = 'showall';
			$this->bIsUsedSrch = false;
			$this->clearSearch();
			$_SESSION[$this->sessionPrefix."_pagenumber"] = 1;
			$this->isUsedSearchFor = false;
		}//set session if simple search	
		else if(@$_REQUEST["a"] == "search") {
			$this->srchType = 'simple';
			$this->bIsUsedSrch = true;
			$this->parseSimpleRequest();
		} //set session if advanced search
		else if(@$_REQUEST["a"] == "advsearch") {
			$this->srchType = 'advanced';
			$this->bIsUsedSrch = true;
			$this->parseAdvancedRequest();
		}
		else if(isset($_REQUEST["q"]) || isset($_REQUEST["qs"]) || @$_REQUEST["a"] == "integrated"){
			$this->srchType = 'integrated';
			$this->bIsUsedSrch = true;
			$this->parseItegratedRequest();
			$_SESSION[$this->sessionPrefix."_pagenumber"] = 1;
		}
	}
	/**
	 * Clears search params
	 *
	 */
	function clearSearch()
	{
		$this->_where[$this->sessionPrefix."_simpleSrch"] = '';
		$this->_where[$this->sessionPrefix."_srchType"] = "and";
		$this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"] = "Contains";
		$this->_where[$this->sessionPrefix."simpleSrchTypeComboNot"] = false;
		$this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"] = '';
		// prepare vars
		$this->_where[$this->sessionPrefix."_srchFields"] = array();
		// process srch panel attrs, better then use coockies. 
		$this->_where[$this->sessionPrefix."_srchOptShowStatus"]= false;
		$this->_where[$this->sessionPrefix."_ctrlTypeComboStatus"]= false;
		$this->_where[$this->sessionPrefix."srchWinShowStatus"]= false;
		
	}
	
	function applyWhere(&$sql, $simpleFieldsArr, $aggFieldsArr, $editControls)
	{
		if (!count($simpleFieldsArr) && !count($aggFieldsArr)){
			return $sql;
		}
		
		$searchWhereClause = $this->getWhere($simpleFieldsArr, $editControls);
		$searchHavingClause = $this->getWhere($aggFieldsArr, $editControls);
		
		if($searchWhereClause)
		{
			if($sql[2])
			{
				$sql[2] = '('.$sql[2].') AND ';
			}
			
			$sql[2] .= '('.$searchWhereClause.') ';
		}
		
		if($searchHavingClause)
		{
			if($sql[4])
			{
				$sql[4] = '('.$sql[4].') AND ';
			}
			
			$sql[4] .= '('.$searchHavingClause.') ';
		}
		
		return $sql;
	}
	/**
	 * deprecated
	 *
	 * @return unknown
	 */
	function getTable()
	{
		return $this->_where;
	}
	
	function getSearchCtrlParams($fName)
	{
		$resArr = array();
		if ($this->_where[$this->sessionPrefix."_srchFields"])
			foreach ($this->_where[$this->sessionPrefix."_srchFields"] as $srchField){
				if (strtolower($srchField['fName']) == strtolower($fName)){
					$tField = $srchField;
					$tField["value1"] = prepare_for_db($tField["fName"], $tField["value1"], $tField["eType"], "", $this->tName);
					$tField["value2"] = prepare_for_db($tField["fName"], $tField["value2"], $tField["eType"], "", $this->tName);
					$resArr[] = $tField;					
				}
			}
		return $resArr;
	}
	
	function getUsedCtrlsCount() {
		if ($this->_where[$this->sessionPrefix."_srchFields"]){
			return count($this->_where[$this->sessionPrefix."_srchFields"]);
		}else{
			return 0;
		}
	}
	/**
	 * Global search params: use and|or, srchType panel|adv and simple search value
	 *
	 * @return array
	 */	
	function getSearchGlobalParams() {
		return array('simpleSrch'=>$this->_where[$this->sessionPrefix."_simpleSrch"], 
					 'srchTypeRadio'=>$this->_where[$this->sessionPrefix."_srchType"],
					 'srchType'=>$this->srchType,
					 'simpleSrchTypeComboOpt' => $this->_where[$this->sessionPrefix."simpleSrchTypeComboOpt"],
					 'simpleSrchTypeComboNot' => $this->_where[$this->sessionPrefix."simpleSrchTypeComboNot"],
					 'simpleSrchFieldsComboOpt' => $this->_where[$this->sessionPrefix."simpleSrchFieldsComboOpt"]
		);
	}
	/**
	 * Search panel status indicators array. Open|closed etc
	 *
	 * @return array
	 */
	function getSrchPanelAttrs(){
		return array('srchOptShowStatus' => ($this->_where[$this->sessionPrefix."_srchOptShowStatus"] || count($this->panelSearchFields)),
					 'ctrlTypeComboStatus' => $this->_where[$this->sessionPrefix."_ctrlTypeComboStatus"],
					 'srchWinShowStatus' => $this->_where[$this->sessionPrefix."srchWinShowStatus"]
		);
	}
	/**
	 * Returns indicator is search was init
	 *
	 * @return unknown
	 */
	function isUsedSrch() {
		return $this->bIsUsedSrch;
	}
}
?>