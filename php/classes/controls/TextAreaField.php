<?php
require_once getabspath('classes/controls/TextControl.php');
class TextAreaField extends TextControl
{
	function TextAreaField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_TEXT_AREA;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		$nWidth = $this->pageObject->pSetEdit->getNCols($this->field);
		$nHeight = $this->pageObject->pSetEdit->getNRows($this->field);
		if($this->pageObject->pSetEdit->isUseRTE($this->field))
		{
			$value = RTESafe($value);
						// creating src url
			$browser = "";
			if(@$_REQUEST["browser"]=="ie")
				$browser = "&browser=ie";
				
			// add JS code
			echo "<iframe frameborder=\"0\" vspace=\"0\" hspace=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" id=\""
				.$this->cfield."\" ".(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508==true ? "alt=\""
				.$this->strLabel."\" " : "")."name=\"".$this->cfield."\" title=\"Basic rich text editor\" style='";
			if (!isMobile())
				echo "width: " . ($nWidth+1) . "px;";
			echo "height: " . ($nHeight+100) . "px;'";
			echo " src=\"rte.php?ptype=".$this->pageObject->pageType."&table=".GetTableURL($this->pageObject->tName)."&"."id="
				.$this->id."&".$this->iquery.$browser."&".($mode==MODE_ADD || $mode==MODE_INLINE_ADD ? "action=add" : '')."\">";  
			echo "</iframe>";
		}
		else
		{
			echo '<textarea id="'.$this->cfield.'" '.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508==true ? 'alt="'
				.$this->strLabel.'" ' : '').'name="'.$this->cfield.'" style="';
			if (!isMobile())
				echo "width: ".($nWidth)."px;";
			echo 'height: '.$nHeight.'px;">'.htmlspecialchars($value).'</textarea>';
		}
		$this->buildControlEnd($validate);
	}
}
?>