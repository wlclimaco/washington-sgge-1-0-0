<?php
class CheckboxField extends EditControl
{
	function CheckboxField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_CHECKBOX;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		if($mode == MODE_ADD || $mode == MODE_INLINE_ADD || $mode == MODE_EDIT || $mode == MODE_INLINE_EDIT) 
		{
			$checked = "";
			if($value && $value != 0)
				$checked=" checked";
			echo '<input id="'.$this->ctype.'" type="hidden" name="'.$this->ctype.'" value="checkbox">';
			echo '<input id="'.$this->cfield.'" type="Checkbox" '
				.(($mode == MODE_INLINE_EDIT || $mode == MODE_INLINE_ADD) && $this->is508==true ? 'alt="'.$this->strLabel.'" ' : '')
				.'name="'.$this->cfield.'" '.$checked.'>';
		}
		else
		{
			echo '<input id="'.$this->ctype.'" type="hidden" name="'.$this->ctype.'" value="checkbox">';
			echo '<select id="'.$this->cfield.'" '.(($mode == MODE_INLINE_EDIT || $mode == MODE_INLINE_ADD) && $this->is508==true ? 'alt="'
				.$this->strLabel.'" ' : '').'name="'.$this->cfield.'">';
			$val = array("", "on", "off");
			$show = array("", "True", "False");
			foreach($val as $i => $v)
			{
				$sel="";
				if($value === $v)
					$sel = " selected";
				echo '<option value="'.$v.'"'.$sel.'>'.$show[$i].'</option>';
			}
			echo "</select>";
			
		}
		$this->buildControlEnd($validate);
	}
	
	function SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest)
	{
		$baseResult = $this->baseSQLWhere($strSearchOption);
		if($baseResult === false)
			return "";
		if($baseResult != "")
			return $baseResult;
		
		if($SearchFor == "none")
			return "";
			
		if(NeedQuotes($this->type))
		{
			$fullFieldName = GetFullFieldName($this->field);
			if($SearchFor == "on")
			{
				$whereStr = "(".$fullFieldName."<>'0' ";
				if (!$this->isOracle)
				{
					$whereStr .= " and ".$fullFieldName."<>'' ";
				} 
				$whereStr .= " and ".$fullFieldName." is not null)";
																			return $whereStr;
			}
			elseif($SearchFor == "off")
			{
				$whereStr = "(".GetFullFieldName($this->field)."='0' ";
				if (!$this->isOracle)
				{
					$whereStr .= " or ".GetFullFieldName($this->field)."='' "; 
				}
				$whereStr .= " or ".GetFullFieldName($this->field)." is null)";
																			return $whereStr;
			}
		}
		else
		{
			if($SearchFor == "on")
			{
				return "(".GetFullFieldName($this->field)."<>0 and ".GetFullFieldName($this->field)." is not null)";
			}
			elseif($SearchFor == "off")
			{
				return "(".GetFullFieldName($this->field)."=0 or ".GetFullFieldName($this->field)." is null)";
			}
		}
		return "";
	}
}
?>