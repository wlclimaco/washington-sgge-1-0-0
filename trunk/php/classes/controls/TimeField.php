<?php
require_once getabspath('classes/controls/DateTimeControl.php');
class TimeField extends DateTimeControl
{
	function TimeField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_TIME;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		if($this->container->pageType == PAGE_LIST || $this->container->pageType == PAGE_SEARCH)
			$value = prepare_for_db($this->field, $value, "time");
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		echo '<input id="'.$this->ctype.'" '.$this->inputStyle.' type="hidden" name="'.$this->ctype.'" value="time">';
		$arr_number=parsenumbers((string)$value);
		if(count($arr_number) == 6)
		{
			$value = mysprintf("%d:%02d:%02d",array($arr_number[3],$arr_number[4],$arr_number[5]));
		}
		$timeAttrs = $this->pageObject->pSetEdit->getFormatTimeAttrs($this->field);	
		if(count($timeAttrs))
		{
			$input = '<input type="text" '.$this->inputStyle.' name="'.$this->cfield.'" '
					.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508 == true ? 'alt="'.$this->strLabel.'" ' : '')
					.'id="'.$this->cfield.'" '.$this->pageObject->pSetEdit->getEditParams($this->field);
			if($timeAttrs["useTimePicker"])
			{
				$convention = $timeAttrs["hours"];
				$loc = getLacaleAmPmForTimePicker($convention, true);
				$tpVal = getValForTimePicker($this->type, $value, $loc['locale']);
				echo $input.' value="'.htmlspecialchars($tpVal['val']).'">';
				echo '&nbsp;';
				echo '<img class="runner-imgclock" src="images/clock.gif" alt="Time" border="0" style="margin:4px 0 0 6px; visibility: hidden;" id="trigger-test-'.$this->cfield.'" />';
			}	
			else
				echo $input.' value="'.htmlspecialchars($value).'">';
		}
		$this->buildControlEnd($validate);
	}
	
	function SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest)
	{
		$hasDigits = false;
		for($i = 0; $i < strlen($SearchFor); $i++)
		{
			if(is_numeric($SearchFor[$i]))
			{
				$hasDigits = true;
				break;
			}
		}
		if(!$hasDigits)
		{
			for($i = 0; $i < strlen($SearchFor2); $i++)
			{
				if(is_numeric($SearchFor2[$i]))
				{
					$hasDigits = true;
					break;
				}
			}
		}
		if(!$hasDigits)
			return "";
		$SearchFor = prepare_for_db($this->field, $SearchFor, "time");
		$SearchFor2 = prepare_for_db($this->field, $SearchFor2, "time");
		return parent::SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest);
	}
}
?>