<?php
class ViewTimeField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		$result = "";
		if(IsDateFieldType($this->fieldType))
			$result = str_format_time(db2time($data[$this->field]));
		else
		{
			$numbers=parsenumbers($data[$this->field]);
			if(!count($numbers))
				return "";
			while(count($numbers)<3)
				$numbers[]=0;
			$result = str_format_time(array(0,0,0,$numbers[0],$numbers[1],$numbers[2]));
		}
		return $this->checkForEncoding($result, $keylink);
	}
}
?>