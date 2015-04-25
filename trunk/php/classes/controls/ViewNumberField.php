<?php
class ViewNumberField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		return str_format_number($data[$this->field], $this->container->pSet->isDecimalDigits($this->field));
	}
}