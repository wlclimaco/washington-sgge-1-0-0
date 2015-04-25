<?php
require_once getabspath('classes/controls/DateTimeControl.php');
class DateField extends DateTimeControl
{
	function DateField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_DATE;
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		global $locale_info;
		
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		if($fieldNum)
			$this->cfield = "value".$fieldNum."_".GoodFieldName($this->field).'_'.$this->id;
			
		echo '<input id="'.$this->ctype.'" type="hidden" name="'.$this->ctype.'" value="date'
			.$this->pageObject->pSetEdit->getDateEditType($this->field).'">';
			
		if($this->pageObject->pageType == PAGE_LIST)
			$pSet = new ProjectSettings($this->pageObject->tName, PAGE_SEARCH);
		else 
			$pSet = $this->pageObject->pSetEdit;
		
		$tvalue = $value;
			
		$time = db2time($tvalue);
		if(!count($time))
			$time = array(0, 0, 0, 0, 0, 0);
		$dp = 0;
		switch($pSet->getDateEditType($this->field))
		{
			case EDIT_DATE_SIMPLE_DP:
				$ovalue = $value;
				if($locale_info["LOCALE_IDATE"] == 1)
				{
					$fmt = "dd".$locale_info["LOCALE_SDATE"]."MM".$locale_info["LOCALE_SDATE"]."yyyy";
					$sundayfirst = "false";
				}
				else if($locale_info["LOCALE_IDATE"] == 0)
				{
					$fmt = "MM".$locale_info["LOCALE_SDATE"]."dd".$locale_info["LOCALE_SDATE"]."yyyy";
					$sundayfirst = "true";
				}
				else
				{
					$fmt = "yyyy".$locale_info["LOCALE_SDATE"]."MM".$locale_info["LOCALE_SDATE"]."dd";
					$sundayfirst = "false";
				}
				
				if($time[5])
					$fmt.= " HH:mm:ss";
				else if($time[3] || $time[4])
					$fmt.= " HH:mm";
				
				if($time[0])
					$ovalue = format_datetime_custom($time,$fmt);
				$ovalue1 = $time[2]."-".$time[1]."-".$time[0];
				$showtime = "false";
				if($pSet->dateEditShowTime($this->field))
				{
					$showtime = "true";
					$ovalue1.= " ".$time[3].":".$time[4].":".$time[5];
				}
				// need to create date control object to use it with datePicker
				$ret='<input id="'.$this->cfield.'" '.$this->inputStyle.' type="Text" name="'.$this->cfield.'" value="'.$ovalue.'">';
				$ret.='<input id="ts'.$this->cfield.'" type="Hidden" name="ts'.$this->cfield.'" value="'.$ovalue1.'">&nbsp;&nbsp;';
				$ret.='&nbsp;<a href="#" id="imgCal_'.$this->cfield.'">'.
					'<img src="images/cal.gif" width=16 height=16 border=0 alt="'."Click Here to Pick up the date".'"></a>';			
				echo $ret;
				break;
			case EDIT_DATE_DD_DP:
				$dp=1;
			case EDIT_DATE_DD:
				$controlWidth = $pSet->getControlWidth($this->field);
				if($controlWidth > 0)
				{
					$controlWidth -= 10;
					$yearWidth = floor($controlWidth * 0.3);
					$yearStyle = 'style="width: '.$yearWidth.'px; ';
					$dayWidth = floor($controlWidth * 0.2);
					$dayStyle = 'style="width: '.$dayWidth.'px; margin-right:5px;" ';
					$mothWidth = $controlWidth - $yearWidth - $dayWidth;
					$monthStyle = 'style="width: '.$mothWidth.'px; margin-right:5px;" ';
				}
				else 
				{
					$dayStyle = '';
					$monthStyle = '';
					$yearStyle = '';
				}
				$alt = ($mode == MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508 ? 'alt="'.$this->strLabel.'" ' : '';
				$retday='<select id="day'.$this->cfield.'" '.$dayStyle.$alt.'name="day'.$this->cfield.'" ></select>';
				$retmonth='<select id="month'.$this->cfield.'" '.$monthStyle.$alt.'name="month'.$this->cfield.'" ></select>';
				$retyear='<select id="year'.$this->cfield.'" '.$yearStyle.$alt.'name="year'.$this->cfield.'" ></select>';
				$sundayfirst="false";
				$space = ($controlWidth > 0 ? '' : "&nbsp;");
				if($locale_info["LOCALE_ILONGDATE"]==1)
					$ret=$retday.$space.$retmonth.$space.$retyear;
				else if($locale_info["LOCALE_ILONGDATE"]==0)
				{
					$ret=$retmonth.$space.$retday.$space.$retyear;
					$sundayfirst="true";
				}
				else
					$ret=$retyear.$space.$retmonth.$space.$retday;
					
				if($time[0] && $time[1] && $time[2])
					$ret.="<input id=\"".$this->cfield."\" type=hidden name=\"".$this->cfield."\" value=\"".$time[0]."-".$time[1]."-".$time[2]."\">";
				else
					$ret.="<input id=\"".$this->cfield."\" type=hidden name=\"".$this->cfield."\" value=\"\">";
				// calendar handling for three DD
				if($dp)
				{
					$ret.='&nbsp;<a href="#" id="imgCal_'.$this->cfield.'">'.
					'<img src="images/cal.gif" width=16 height=16 border=0 alt="Click Here to Pick up the date"></a>'.
					'<input id="ts'.$this->cfield.'" type=hidden name="ts'.$this->cfield.'" value="'.$time[2].'-'.$time[1].'-'.$time[0].'">';
				}
			
				echo $ret;
				break;
		//	case EDIT_DATE_SIMPLE:
			default:
				$ovalue=$value;
				if($time[0])
				{
					if($time[3] || $time[4] || $time[5])
						$ovalue=str_format_datetime($time);
					else
						$ovalue=format_shortdate($time);
				}
				echo '<input id="'.$this->cfield.'" type=text name="'.$this->cfield.'" '.$this->inputStyle.' value="'.htmlspecialchars($ovalue).'">';
		}			
			
		$this->buildControlEnd($validate);
	}
	
}
?>