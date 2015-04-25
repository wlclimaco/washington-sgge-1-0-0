<?php
include_once getabspath("classes/controls/ViewFileField.php");
class ViewVideoFileField extends ViewFileField
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
		$value = "";
		$fieldIsUrl = $this->container->pSet->isVideoUrlField($this->field);
		$fileName = $data[$this->field];
		if (strlen($fileName)){
			if(!$fieldIsUrl){
				$this->upload_handler->tkeys = $keylink;
				$filesArray = $this->getFilesArray($fileName);
			}
			else 
				$filesArray = array($fileName);
			
			$counter = 0;
			foreach ($filesArray as $file)
			{
				if($this->container->pageType == PAGE_EXPORT 
					|| $this->container->pageType == PAGE_PRINT
					|| $this->container->forExport != '')
				{
					if($value != "")
						$value .= ", ";
					$value .= $file["usrName"];
					continue;
				}
				if(!$fieldIsUrl)
				{
					if(!file_exists($file["name"]))
						continue;
				}
				$videoId = 'video_'.GoodFieldName(htmlspecialchars($this->field)).'_';
				$videoId .= $this->getContainer()->id."_";
				if($this->container->pageType != PAGE_ADD && $this->container->pageType != PAGE_EDIT)
					$videoId .= $this->getContainer()->recId;
				else
					$videoId .= postvalue("id");
				$videoId .= '_'.$counter++;
				if ($fieldIsUrl){
					$href = $fileName;
					if($fileName != "")
						$type = "video/flv";
				}else{
					$userFile = $this->upload_handler->buildUserFile($file);
					$href = $userFile["url"];
					if(!$this->getContainer()->pSet->isRewindEnabled($this->field))
						$href .= (strpos($href, '?') === false ? '?' : '&').'norange=1';
					if($file["type"] == "application/octet-stream")
						$type = "video/flv";
					else 
						$type = $file["type"];
				}
				if(strpos($type, 'video') !== 0)
					continue;
				if (strpos($href, 'rndVal=') === false){
					$href .= (strpos($href, '?') === false ? '?' : '&').'rndVal='.rand(0, 99999999);
				}else{
					$startPos = strpos($href, 'rndVal=') + 7;
					$endPos = strpos($href, '&', $startPos);
					$href = substr($href, 0, $startPos).rand(0, 99999999).($endPos != -1 ? substr($href, $endPos) : '');
				}
				if (isMobileIOS()){
					$value .= '<video width="'.$this->getContainer()->pSet->getVideoWidth($this->field).'" height="'.
						$this->getContainer()->pSet->getVideoHeight($this->field).'" controls="controls">
							  <source src="'.$href.'"  />
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
					$value .= '<div style="width:'.$vWidth.'px; height:'.$vHeight.'px;">
						<video class="projekktor"  width="'.$vWidth.'" height="'.$vHeight.'"  id="'.$videoId.'" type="'.$type.'" src="'.$href.'">
						</video></div>';
				}
				if($this->pageObject != null)
					$this->pageObject->controlsMap['video'][] = $videoId;
			}
		}
		return $value;
	}
}
?>