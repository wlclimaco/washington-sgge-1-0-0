<?php
class ViewHyperlinkField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		if($data && !$this->container->forExport)
			return GetHyperlink($data[$this->field], $this->field, $data, $this->container->pageType, "");
		return $this->checkForEncoding($data[$this->field], $keylink);
	}
}
?>