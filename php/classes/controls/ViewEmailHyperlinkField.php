<?php
class ViewEmailHyperlinkField extends ViewControl
{
	public function showDBValue(&$data, $keylink)
	{
		if($this->container->forExport)
			return $this->checkForEncoding($data[$this->field], $keylink);
		$result = $data[$this->field];
		$link = $result;
		$title = $result;
		if(substr($result,0,7) == "mailto:")
			$title=substr($result,8);
		else
			$link="mailto:".$link;
		return '<a href="'.$link.'">'.$title.'</a>';
	}
}
?>