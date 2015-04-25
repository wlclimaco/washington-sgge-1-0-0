<?php
class ViewCustomField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		if (is_null($this->displayField)){
			return CustomExpression($data[$this->field], $data, $this->field, $this->container->pageType, "");
		}
		return CustomExpression($this->displayField, $data, $this->field, $this->container->pageType, "");
	}
}
?>