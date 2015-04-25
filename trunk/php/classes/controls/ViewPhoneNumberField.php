<?php
class ViewPhoneNumberField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		$result = $data[$this->field];
		if(strlen($result)==7)
			$result = substr($result,0,3)."-".substr($result,3);
		else if(strlen($result) == 10)
			$result = "(".substr($result,0,3).") ".substr($result,3,3)."-".substr($result,6);
		return $this->checkForEncoding($result, $keylink);
	}
}
?>