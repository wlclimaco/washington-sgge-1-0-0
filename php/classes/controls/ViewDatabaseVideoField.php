<?php
class ViewDatabaseVideoField extends ViewControl
{
	var $noScroll = true;
	/**
	 * addJSFiles
	 * Add control JS files to page object
	 */
	function addJSFiles()
	{
				$this->getContainer()->AddJSFile("include/video/projekktor-1.0.24r90.min.js");
		$this->getJSControl();
	}

	/**
	 * addCSSFiles
	 * Add control CSS files to page object
	 */ 
	function addCSSFiles()
	{
		$this->getContainer()->AddCSSFile("include/video/theme/style.css");
	}
	
	public function showDBValue(&$data, $keylink)
	{
		if($this->container->forExport)
			return "Dados Binários longos demais, Não pode ser exibido";
		$value = "";
		if (@$data[$this->field] != NULL && $this->container->pageType != PAGE_PRINT ){
			$videoId = 'video_'.GoodFieldName(htmlspecialchars($this->field)).'_';
			$videoId .= $this->getContainer()->id."_";
			if($this->getContainer()->pageType != PAGE_ADD)
				$videoId .= $this->getContainer()->recId;
			else
				$videoId .= postvalue("id");	
			$type = 'video/flv';
			$fileName = 'file.flv';
			$fileNameF = $this->getContainer()->pSet->getFilenameField($this->field);
			if($fileNameF) {
				$fileName = $data[$fileNameF];
				if(!$fileName)
					$fileName = 'file.flv';
				else
					$type = getContentTypeByExtension(substr($fileName, strrpos($fileName, '.')));
			}
			$href = "mfhandler.php?filename=".$fileName."&table=".rawurlencode($this->getContainer()->pSet->_table)
				."&field=".rawurlencode($this->field)
				."&pageType=".$this->getContainer()->pageType.$keylink;
			if (isMobileIOS()){
				$value = '<video width="'.$this->getContainer()->pSet->getVideoWidth($this->field).'" height="'.
					$this->getContainer()->pSet->getVideoHeight($this->field).'" controls="controls">
						  <source src="'.$href.'" type="'.$type.'" />
						  Your browser does not support the video tag.
						</video>';
			}
			else {
				$vWidth = $this->getContainer()->pSet->getVideoWidth($this->field);
				$vHeight = $this->getContainer()->pSet->getVideoHeight($this->field);
				if($vWidth == 0)
					$vWidth = 300;
				if($vHeight == 0)
					$vHeight = 200;
				$value .= '
					<div style="width:'.$vWidth.'px; height:'.$vHeight.'px;">
					<video class="projekktor" width="'.$vWidth.'" height="'.$vHeight.'" id="'.$videoId.'" type="'.$type.'" src="'.$href.'" >
					</video></div>';
			}
			if($this->pageObject != null)
				$this->pageObject->controlsMap['video'][] = $videoId; 
		}
		else 
		{
			$fileNameF = $this->getContainer()->pSet->getFilenameField($this->field);
			if($fileNameF) {
				$fileName = $data[$fileNameF];
				if(!$fileName)
					$value = $fileName;
			}
		}
		return $value;
	}
}
?>