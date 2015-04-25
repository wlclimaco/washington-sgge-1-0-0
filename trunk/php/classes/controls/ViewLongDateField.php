<?php
class ViewLongDateField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		return format_longdate(db2time($data[$this->field]));
	}
}
?>