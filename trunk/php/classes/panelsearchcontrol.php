<?php


/**
 * SearchControl builder for search panel on list
 *
 */
class PanelSearchControl extends SearchControl 
{
	// attrs only for search panel
	function getCtrlParamsArr($fName, $recId, $fieldNum=0, $value, $renderHidden = false, $isCached=true) 
	{
		$control = parent::getCtrlParamsArr($fName, $recId, $fieldNum, $value, $renderHidden, $isCached) ;
		
		$control["params"]["additionalCtrlParams"]['skipDependencies'] = true;
		$control["params"]["additionalCtrlParams"]["style"] = isMobile() ? 'width: 35%;' :'width: 115px;';
		
		$ctrlsMap = array('controls'=>array());
		$ctrlsMap['controls']["skipDependencies"] = true;
		$ctrlsMap['controls']["style"] = isMobile() ? 'width: 35%;' :'width: 115px;';
		$this->pageObj->fillControlsMap($ctrlsMap,true);
		
		return $control;
	}
	
	function simpleSearchFieldCombo($fNamesArr, $selOpt) 
	{
		$options = "";
		$settings = new ProjectSettings($this->tName);
		if (sizeof($settings->getGoogleLikeFields()) != 0)
			$options = '<option value="" >'."Qualquer Campo".'</option>';
		
		foreach($fNamesArr as $fName)
		{
			$fLabel = GetFieldLabel(GoodFieldName($this->tName), GoodFieldName($fName));
			$options .= '<option value="'.$fName.'" '.($selOpt == $fName ? 'selected' : '').'>'.$fLabel.'</option>';
		}
		return $options;
	}
	
	function getCtrlSearchTypeOptions($fName, $selOpt, $not) 
	{	
		return $this->pageObj->getControl($fName)->getSearchOptions($selOpt, $not, true);
	}
}
?>