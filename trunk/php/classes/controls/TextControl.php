<?php
class TextControl extends EditControl
{
	function getSearchOptions($selOpt, $not, $both)
	{
		$optionsArray = array();
		$isPHPEncripted = $this->pageObject->cipherer->isFieldPHPEncrypted($this->field);
		if(!$isPHPEncripted){
			$optionsArray[] = CONTAINS;
		}
		$optionsArray[] = EQUALS;
		if(!$isPHPEncripted){
			$optionsArray[] = STARTS_WITH;
			$optionsArray[] = MORE_THAN;
			$optionsArray[] = LESS_THAN;
			$optionsArray[] = BETWEEN;
		}
		$optionsArray[] = EMPTY_SEARCH;
		if($both)
		{
			if(!$isPHPEncripted){
				$optionsArray[] = NOT_CONTAINS;
			}
			$optionsArray[] = NOT_EQUALS;
			if(!$isPHPEncripted){
				$optionsArray[] = NOT_STARTS_WITH;
				$optionsArray[] = NOT_MORE_THAN;
				$optionsArray[] = NOT_LESS_THAN;
				$optionsArray[] = NOT_BETWEEN;
			}
			$optionsArray[] = NOT_EMPTY;
		}
		return $this->buildSearchOptions($optionsArray, $selOpt, $not, $both);		
	}
}
?>