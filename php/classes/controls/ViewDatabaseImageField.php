<?php
class ViewDatabaseImageField extends ViewControl
{
	/**
	 * addJSFiles
	 * Add control JS files to page object
	 */
	function addJSFiles()
	{
				$this->getContainer()->AddJSFile("include/zoombox/zoombox.js");
		$this->getJSControl();	
	}
	
	/**
	 * addCSSFiles
	 * Add control CSS files to page object
	 */ 
	function addCSSFiles()
	{
		$this->getContainer()->AddCSSFile("include/zoombox/zoombox.css");
	}
	
	public function showDBValue(&$data, $keylink)
	{
		if($this->container->forExport)
			return "Dados Binários longos demais, Não pode ser exibido";
		if(!$data[$this->field])
			return "";
		$value = "";
		$fileName = 'file.jpg';
		$fileNameF = $this->container->pSet->getFilenameField($this->field);
		if($fileNameF && $data[$fileNameF])
			$fileName = $data[$fileNameF];		
		if($this->container->pSet->showThumbnail($this->field)) 
		{
			$thumbPref = $this->container->pSet->getStrThumbnail($this->field);
			$value.= "<a target=_blank";
			$value.= " href='mfhandler.php?filename=".$fileName."&table=".rawurlencode($this->container->pSet->_table)
				."&field=".rawurlencode($this->field)
				."&nodisp=1"
				."&pageType=".$this->container->pageType.$keylink."&rndVal=".rand(0,32768)."' class='zoombox'>";
			$value.= "<img border=0";
			if($this->is508)
				$value.= " alt=\"Image from DB\"";
			$value.= " src='mfhandler.php?filename=".$fileName."&table=".rawurlencode($this->container->pSet->_table)
				."&field=".rawurlencode($thumbPref)
				."&nodisp=1"
				."&pageType=".$this->container->pageType.$keylink."&rndVal=".rand(0,32768)."'>";
			$value.= "</a>";
		} 
		else 
		{
			$value = "<img";
			if($this->is508)
				$value.= " alt=\"Image from DB\"";
			
			$imgWidth = $this->container->pSet->getImageWidth($this->field);
			$value.=($imgWidth ? " width=".$imgWidth : "");
			
			$imgHeight = $this->container->pSet->getImageHeight($this->field);
			$value.=($imgHeight ? " height=".$imgHeight : "");
			
			$value.= " border=0";
			$value.= " src='mfhandler.php?filename=".$fileName."&table=".rawurlencode($this->container->pSet->_table)
				."&field=".rawurlencode($this->field)
				."&nodisp=1"
				."&pageType=".$this->container->pageType.$keylink."&rndVal=".rand(0,32768)."'>";
		}
		return $value;
	}
}
?>