<?php
class ViewCheckboxField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		if($this->container->forExport)
			return $this->checkForEncoding($data[$this->field], $keylink);
		
		$result = "<img src=\"images/check_";
		if($data[$this->field] && $data[$this->field] != 0)
			$result .= "yes";
		else
			$result .= "no";
		$result .= ".gif\" border=0";
		if(isEnableSection508())
			$result .= " alt=\" \"";
		$result .=  ">";
		return $result;
	}
}
?>