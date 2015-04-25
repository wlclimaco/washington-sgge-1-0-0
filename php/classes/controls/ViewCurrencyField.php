<?php
class ViewCurrencyField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		return $this->checkForEncoding(str_format_currency($data[$this->field]), $keylink);
	}
}
?>