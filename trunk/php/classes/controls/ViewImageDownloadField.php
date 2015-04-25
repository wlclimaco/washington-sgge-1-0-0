<?php
include_once getabspath("classes/controls/ViewFileField.php");
class ViewImageDownloadField extends ViewFileField
{
	var $noScroll = true;
	/**
	 * addJSFiles
	 * Add control JS files to page object
	 */
	function addJSFiles()
	{
				$this->getContainer()->AddJSFile("include/sudo/jquery.sudoSlider.js");
		$this->getContainer()->AddJSFile("include/zoombox/zoombox.js");
		$this->getJSControl();
	}
	
	/**
	 * addCSSFiles
	 * Add control CSS files to page object
	 */ 
	function addCSSFiles()
	{
		$this->getContainer()->AddCSSFile("include/sudo/style.css");
		$this->getContainer()->AddCSSFile("include/zoombox/zoombox.css");
	}
	
	public function showDBValue(&$data, $keylink)
	{
		$this->upload_handler->tkeys = $keylink;
		$filesArray = $this->getFilesArray($data[$this->field]);
		$resultValues = array();
		$zoomboxRand = rand(11111, 99999);
		foreach ($filesArray as $imageFile)
		{
			$userFile = $this->upload_handler->buildUserFile($imageFile);
			if($this->container->pageType == PAGE_EXPORT 
				|| $this->container->forExport != '')
			{
				$resultValues[] = $userFile["name"];
				continue;
			}
			if(CheckImageExtension($imageFile["name"])) 
			{
				$userFile["url"] .= "&nodisp=1";
				if($userFile["thumbnail_url"] != "")
					$userFile["thumbnail_url"] .= "&nodisp=1";
				$imageValue = '';
				$divSize = '';
				if($this->container->pSet->showThumbnail($this->field)) 
				{
					$imageValue .= "<a target=_blank";
					
					$imageValue .= " href=\"".htmlspecialchars($userFile["url"])."\" class='zoombox zgallery".$zoomboxRand."'>";
					$imageValue .= "<img";
					
					$imageValue .= " border='0'";
					if($this->is508)
						$imageValue .= " alt=\"".htmlspecialchars($userFile["name"])."\"";
					$imageValue .= " src=\"".htmlspecialchars($userFile["thumbnail_url"] != "" ? $userFile["thumbnail_url"] :
						$userFile["url"])."\" /></a>";
				} 
				else 
				{
					$imageValue .= "<img";
					
					$imgWidth = $this->container->pSet->getImageWidth($this->field);
					if($imgWidth)
					{
						$imageValue .= " width=".$imgWidth;
						$divSize = "width: ".$imgWidth."px;";
					}
					
					$imgHeight = $this->container->pSet->getImageHeight($this->field);
					if($imgHeight)
					{
						$imageValue .= ($imgHeight ? " height=".$imgHeight : "");
						$divSize .= "height: ".$imgHeight."px;";
					}
					
					if($divSize != "")
						$divSize = 'style="'.$divSize.'"';
					
					$imageValue .= " border=0";
					if($this->is508)
						$imageValue.= " alt=\"".htmlspecialchars($userFile["name"])."\"";
					$imageValue .= ' src="'.htmlspecialchars($userFile["url"]).'">';
				}
				$resultValues[] = $imageValue;
			}
			else 
				$resultValues[] = '<a href="'.htmlspecialchars($userFile["url"]).'">'.$userFile["name"].'</a>';
		}
		if(count($resultValues) > 1)
		{
			if($this->container->pageType == PAGE_EXPORT || $this->container->forExport != '')
				return implode(', ', $resultValues);

			if($this->container->pageType == PAGE_PRINT)
				return implode('<br />', $resultValues);
				
			for($i = 0; $i < count($resultValues); $i++)
			{
				if($i == 0)
					$resultValues[$i] = '<li>'.$resultValues[$i].'</li>';
				else 
					$resultValues[$i] = '<li style="display:none;">'.$resultValues[$i].'</li>';
			}
			return '<div style="position:relative;"><div class="presudoslider" '.$divSize.'><ul style="list-style: none;">'.implode("",$resultValues).'</ul></div></div>';
		}
		else if(count($resultValues) == 1)
			return $resultValues[0];
		return "";
	}
}
?>