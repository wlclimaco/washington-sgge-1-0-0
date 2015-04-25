<?php
class ViewDatetimeField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		return str_format_datetime(db2time($data[$this->field]));
	}
}
?>