<?php
class ViewPercentField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		$result = "";
		if($data[$this->field] != "")
			$result = ($data[$this->field] * 100)."%";
		return $result;
	}
}
?>