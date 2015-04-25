<?php
class ViewHTMLField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		return $data[$this->field];
	}
}
?>