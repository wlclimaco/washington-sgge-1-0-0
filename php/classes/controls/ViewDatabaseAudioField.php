<?php
class ViewDatabaseAudioField extends ViewControl
{
	var $noScroll = true;
	
	function addJSFiles()
	{
		$this->getContainer()->AddJSFile("include/runnerJS/ymp.js");
				$this->getJSControl();
	}
	
	public function showDBValue(&$data, $keylink)
	{
		if($this->container->forExport)
			return "Dados Binários longos demais, Não pode ser exibido";
		$value = "";
		$titleField = $this->container->pSet->getAudioTitleField($this->field);
		$title = "";
		if ($titleField){
			$title = htmlspecialchars($data[$titleField]);
		}
		if (@$data[$this->field] != NULL && $this->container->pageType != PAGE_PRINT){
			$value = '<a class="htrack" type="audio/mpeg" title="'.$title.'" href="getfile.php?table='.GetTableURL($this->container->pSet->_table).
				'&field='.rawurlencode($this->field).$keylink.'">'.$title.'</a>';
		}else{
			$value = $title;
		}
		return $value;
	}
}
?>