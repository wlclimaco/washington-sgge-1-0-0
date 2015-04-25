<?php
require_once getabspath('classes/controls/TextControl.php');
class ReadOnlyField extends TextControl
{
	function ReadOnlyField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_READONLY;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		if($mode==MODE_EDIT || $mode==MODE_ADD || $mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD)
		{
			echo '<span id="readonly_'.$this->cfield.'" '.$this->inputStyle.'>'.$this->pageObject->readOnlyFields[$this->field].'</span>';
		}
		echo '<input id="'.$this->cfield.'" type="Hidden" name="'.$this->cfield.'" value="'.htmlspecialchars($value).'">';
		$this->buildControlEnd($validate);
	}
}
?>