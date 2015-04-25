<?php
class ViewShortDateField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		return format_shortdate(db2time($data[$this->field]));
	}
}
?>