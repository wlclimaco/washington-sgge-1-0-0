<?php
require_once getabspath('classes/controls/TextControl.php');
class HiddenField extends TextControl
{
	function HiddenField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_HIDDEN;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		echo '<input id="'.$this->cfield.'" type="Hidden" name="'.$this->cfield.'" value="'.htmlspecialchars($value).'">';
		$this->buildControlEnd($validate);
	}
}
?>