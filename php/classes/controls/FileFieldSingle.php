<?php
class FileFieldSingle extends EditControl
{
	function FileFieldSingle($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_FILE;
		
		global $conn;
		$this->conn = $conn;
	}
	
	/**
	 * addJSFiles
	 * Add control JS files to page object
	 */
	function addJSFiles()
	{
		$this->pageObject->AddJSFile("include/zoombox/zoombox.js");
	}
	
	/**
	 * addCSSFiles
	 * Add control CSS files to page object
	 */ 
	function addCSSFiles()
	{
		$this->pageObject->AddCSSFile("include/zoombox/zoombox.css");
	}
	
	function buildControl($value, $mode, $fieldNum = 0, $validate, $additionalCtrlParams, $data)
	{
		parent::buildControl($value, $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
		if($this->pageObject->pageType == PAGE_SEARCH || $this->pageObject->pageType == PAGE_LIST)
		{
			echo '<input id="'.$this->cfield.'" '.$this->inputStyle.' type="text" '
				.($mode == MODE_SEARCH ? 'autocomplete="off" ' : '')
				.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508==true ? 'alt="'.$this->strLabel.'" ' : '')
				.'name="'.$this->cfield.'" '.$this->pageObject->pSetEdit->getEditParams($this->field).' value="'
				.htmlspecialchars($value).'">';	
			$this->buildControlEnd($validate);
			return;
		}	
			
		if($mode == MODE_SEARCH)
			$this->format = "";
		$disp = "";
		$strfilename = "";
		$function = "";
		if($mode == MODE_EDIT || $mode == MODE_INLINE_EDIT)
		{
//	show current file
			$fileName = $value;
			if($this->pageObject->pSet->getViewFormat($this->field) == FORMAT_FILE 
				|| $this->pageObject->pSet->getViewFormat($this->field) == FORMAT_FILE_IMAGE)
			{
				$uploadFolder = $this->pageObject->pSet->getUploadFolder($this->field);
				if(!CheckImageExtension($value))
				{
					$disp = "<a target=\"_blank\" href=\"".htmlspecialchars($uploadFolder.$value)."\">"
						.htmlspecialchars($value)."</a>";
				}
				else 
				{
					$finalUploadFolder = $this->pageObject->pSet->getFinalUploadFolder($this->field);
					if(!myfile_exists(getabspath($finalUploadFolder.$value)))
						$value = "images/no_image.gif";
					else 
						$value = $uploadFolder.$value;					
					if($this->pageObject->pSet->showThumbnail($this->field))
					{
						$thumbprefix = $this->pageObject->pSet->getStrThumbnail($this->field);
					 	// show thumbnail
						$thumbname = $thumbprefix.$fileName;
						if(substr($uploadFolder, 0, 7) != "http://")
						{ 
							if(!myfile_exists(getabspath($finalUploadFolder.$thumbname)))
							{
								$thumbname = $value;
							}
							else 
								$thumbname = $uploadFolder.$thumbname;
						}
						
						$disp = "<a target=\"_blank\" href=\"".htmlspecialchars($value)."\" class='zoombox zgallery'>";
						$disp.="<img";
						if(isEnableSection508())
							$disp.= " alt=\"".htmlspecialchars($fileName)."\"";
						$disp.=" border=0";
						$disp.=" src=\"".htmlspecialchars($thumbname)."\"></a>";
					}
					else
					{
						if($value != "images/no_image.gif")
						{
							if(filesize($finalUploadFolder.$fileName) > 51200)
								$imageValue = "images/icons/jpg.png";
							else 
								$imageValue = $value;
						}
						else 
							$imageValue = "images/no_image.gif";
							
						if(isEnableSection508())
							$disp='<img alt=\"'.htmlspecialchars($fileName).'\" src="'.htmlspecialchars($imageValue).'" border=0>';
						else
							$disp='<img src="'.htmlspecialchars($imageValue).'" border=0>';
						if($imageValue != "images/no_image.gif")
							$disp = "<a target=\"_blank\" href=\"".htmlspecialchars($value)."\">".$disp."</a>";							
					}
				}
				$disp .= "<br />";
			}
//	filename edit
			$filename_size = 30;
			if($this->pageObject->pSet->isUseTimestamp($this->field))
				$filename_size = 50;
			$strfilename = '<input type=hidden name="filenameHidden_'.$this->cfieldname.'" value="'.htmlspecialchars($fileName).'"><br>'
				."Nome de Arquivo"
				.'&nbsp;&nbsp;<input type="text" style="background-color:gainsboro" disabled id="filename_'.$this->cfieldname
				.'" name="filename_'.$this->cfieldname.'" size="'.$filename_size.'" maxlength="100" value="'.htmlspecialchars($fileName).'">';
			$strtype = '<br><input id="'.$this->ctype.'_keep" type="Radio" name="'.$this->ctype
					.'" value="upload0" checked class="runner-uploadtype">'."Manter";
			
			if((strlen($value) || $mode==MODE_INLINE_EDIT) && !$this->pageObject->pSet->isRequired($this->field))
			{
				$strtype .= '<input id="'.$this->ctype.'_delete" type="Radio" name="'.$this->ctype
					.'" value="upload1" class="runner-uploadtype">'."Elimina Selecionados";
			}
			$strtype .= '<input id="'.$this->ctype.'_update" type="Radio" name="'.$this->ctype
				.'" value="upload2" class="runner-uploadtype">'."Atualizar";
		}
		else
		{
//	if Adding record
			$filename_size=30;
			if($this->pageObject->pSet->isUseTimestamp($this->field))
				$filename_size=50;
			$strtype='<input id="'.$this->ctype.'" type="hidden" name="'.$this->ctype.'" value="upload2">';
			$strfilename='<br>'."Nome de Arquivo"
				.'&nbsp;&nbsp;<input type="text" id="filename_'.$this->cfieldname.'" name="filename_'.$this->cfieldname.'" size="'
				.$filename_size.'" maxlength="100">';			
		}
		echo $disp.$strtype.$function;
		
		if ($mode==MODE_EDIT || $mode==MODE_INLINE_EDIT)
		{
			echo '<br>';
		}
		
		echo '<input type="File" id="'.$this->cfield.'" '.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508 == true 
			? 'alt="'.$this->strLabel.'" ' : '').' name="'.$this->cfield.'" >'.$strfilename;
		echo '<input type="Hidden" id="notempty_'.$this->cfieldname.'" value="'.(strlen($value)? 1 : 0).'">';
		$this->buildControlEnd($validate);
	}
	
	function readWebValue(&$avalues, &$blobfields, $strWhereClause, $oldValuesRead, &$filename_values = null)
	{
		$this->getPostValueAndType();
		if (FieldSubmitted($this->goodFieldName."_".$this->id))
		{
			$fileNameForPrepareFunc = securityCheckFileName(postvalue("filename_".$this->goodFieldName."_".$this->id));
			if($this->pageObject->pageType != PAGE_EDIT)
			{
				$this->webValue = prepare_upload($this->field, "upload2", $fileNameForPrepareFunc, $fileNameForPrepareFunc, ""
					, $this->id, $this->pageObject);
			}
			else
			{
				if(substr($this->webType, 0, 4) == "file")
				{
					$prepearedFile = prepare_file($this->webValue, $this->field, $this->webType, $fileNameForPrepareFunc, $this->id);
					if($prepearedFile !== false)
					{
						$this->webValue = $prepearedFile["value"];
						$filename = $prepearedFile["filename"];
					}
					else 
					$this->webValue = false;
				}
				else if(substr($this->webType, 0, 6) == "upload")
				{
					if($fileNameForPrepareFunc)
						$this->webValue = $fileNameForPrepareFunc;
					if($this->webType == "upload1")
					{
						// file deletion, read filename from the database
						if(!$oldValuesRead)
						{
							$rsold = db_query($this->pageObject->gQuery->gSQLWhere($strWhereClause), $this->conn);
							$dataold = db_fetch_array($rsold);
							$oldValuesRead = true;
						}
						$fileNameForPrepareFunc = $dataold[$this->field];
					}
					$this->webValue = prepare_upload($this->field, $this->webType, $fileNameForPrepareFunc, $this->webValue, "", $this->id, $this->pageObject);
				}
			}
		}
		else
			$this->webValue = false;
		
		if(!($this->webValue === false))
		{
			//if($this->webValue)
			{
				if($this->pageObject->pSet->getResizeOnUpload($this->field) 
					|| $this->pageObject->pSet->getCreateThumbnail($this->field))
					$contents = GetUploadedFileContents("value_".$this->goodFieldName."_".$this->id);
			
				if($this->webValue && $this->pageObject->pSet->getCreateThumbnail($this->field))
				{
					$ext = CheckImageExtension(GetUploadedFileName("value_".$this->goodFieldName."_".$this->id));
					$thumb = CreateThumbnail($contents, $this->pageObject->pSet->getThumbnailSize($this->field), $ext);
					$this->pageObject->filesToSave[] = new SaveFile($thumb, $this->pageObject->pSet->GetStrThumbnail($this->goodFieldName)
						.$this->webValue, $this->pageObject->pSet->getUploadFolder($this->field), $this->pageObject->pSet->isAbsolute($this->field));
				}
				$avalues[$this->field] = $this->webValue;
			}
		}
	}
}
?>