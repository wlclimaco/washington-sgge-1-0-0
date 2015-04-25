<?php
class DateTimeControl extends EditControl
{
	function getSearchOptions($selOpt, $not, $both)
	{
		$optionsArray = array(EQUALS, MORE_THAN, LESS_THAN, BETWEEN, EMPTY_SEARCH);
		if($both)
		{
			$optionsArray[] = NOT_EQUALS;
			$optionsArray[] = NOT_MORE_THAN;
			$optionsArray[] = NOT_LESS_THAN;
			$optionsArray[] = NOT_BETWEEN;
			$optionsArray[] = NOT_EMPTY;
		}
		return $this->buildSearchOptions($optionsArray, $selOpt, $not, $both);
	}
}
?>