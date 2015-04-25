<?php
class DatabaseFileField extends EditControl
{
	function DatabaseFileField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = $pageObject->pSetEdit->getEditFormat($field);
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
		$disp = "";
		$strfilename = "";
		if($mode == MODE_EDIT || $mode == MODE_INLINE_EDIT)
		{
			$value = db_stripslashesbinary($value);
			$itype = SupposeImageType($value);
			if($itype)
			{
				if($this->pageObject->pSetEdit->showThumbnail($this->field))
				{
					$disp = "<a target=_blank";
						
					$disp.=" href=\"imager.php?table=".GetTableURL($this->pageObject->tName)."&".
						$this->iquery."&rndVal=".rand(0,32768)."\" class='zoombox'>";
					$disp.= "<img id=\"image_".GoodFieldName($this->field)."_".$this->id."\" name=\"".$this->cfield."\" border=0";
					if($this->is508)
						$disp .= " alt=\"Image from DB\"";
					$disp .= " src=\"imager.php?table=".GetTableURL($this->pageObject->tName)."&field="
						.rawurlencode($this->pageObject->pSetEdit->getStrThumbnail($this->field))
						."&alt=".rawurlencode($this->field).$this->keylink."&rndVal=".rand(0,32768)."\">";
					$disp.= "</a>";
				}
				else
				{
					$disp='<img id="image_'.GoodFieldName($this->field).'_'.$this->id.'" name="'.$this->cfield.'"';
					if($this->is508)
						$disp.= ' alt="Image from DB"';
					$disp.=' border=0 src="imager.php?table='.GetTableURL($this->pageObject->tName).'&'.$this->iquery."&src=1&rndVal="
						.rand(0,32768).'">';
				}	
			}
			else
			{
				if(strlen($value))
				{
					$disp = '<img id="image_'.GoodFieldName($this->field).'_'.$this->id.'" name="'.$this->cfield.'" border=0 ';
					if($this->is508)
						$disp .= ' alt="file"';
					$disp .= ' src="images/file.gif">';
				}
				else
				{
					$disp = '<img id="image_'.GoodFieldName($this->field).'_'.$this->id.'" name="'.$this->cfield.'" border="0"';
					if($this->is508)
						$disp.= ' alt=" "';
					$disp.=' src="images/no_image.gif">';
				}
			}
//	filename
			if($this->format == EDIT_FORMAT_DATABASE_FILE && !$itype && strlen($value))
			{
				if(!($filename = @$data[$this->pageObject->pSetEdit->getFilenameField($this->field)]))
					$filename = "file.bin";
				$disp = '<a href="getfile.php?table='.GetTableURL($this->pageObject->tName).'&filename='.htmlspecialchars($filename)
					.'&'.$this->iquery.'".>'.$disp.'</a>';
			}
//	filename edit
			if($this->format == EDIT_FORMAT_DATABASE_FILE && $this->pageObject->pSetEdit->getFilenameField($this->field))
			{
				if(!($filename = @$data[$this->pageObject->pSetEdit->getFilenameField($this->field)]))
					$filename = "";
				if($mode == MODE_INLINE_EDIT)
				{
					$strfilename = '<br><label for="filename_'.$this->cfieldname.'">'."Nome de Arquivo"
						.'</label>&nbsp;&nbsp;<input type="text" '.$this->inputStyle.' id="filename_'.$this->cfieldname
						.'" name="filename_'.$this->cfieldname.'" size="20" maxlength="50" value="'.htmlspecialchars($filename).'">';					
				}
				else
				{
					$strfilename = '<br><label for="filename_'.$this->cfieldname.'">'."Nome de Arquivo"
						.'</label>&nbsp;&nbsp;<input type="text" '.$this->inputStyle.' id="filename_'.$this->cfieldname.'" name="filename_'
						.$this->cfieldname.'" size="20" maxlength="50" value="'.htmlspecialchars($filename).'">';					
				}
			}
			$strtype = '<br><input id="'.$this->ctype.'_keep" type="Radio" name="'.$this->ctype.'" value="file0" checked>'."Manter";
			
			if(strlen($value) && !$this->pageObject->pSetEdit->isRequired($this->field))
			{
				$strtype .= '<input id="'.$this->ctype.'_delete" type="Radio" name="'.$this->ctype.'" value="file1">'."Elimina Selecionados";
			}
			$strtype .= '<input id="'.$this->ctype.'_update" type="Radio" name="'.$this->ctype.'" value="file2">'."Atualizar";
		}
		else
		{
//	if Add mode
			$strtype = '<input id="'.$this->ctype.'" type="hidden" name="'.$this->ctype.'" value="file2">';
			if($this->format == EDIT_FORMAT_DATABASE_FILE && $this->pageObject->pSetEdit->getFilenameField($this->field))
			{
				$strfilename = '<br><label for="filename_'.$this->cfieldname.'">'."Nome de Arquivo"
					.'</label>&nbsp;&nbsp;<input type="text" '.$this->inputStyle.' id="filename_'.$this->cfieldname.'" name="filename_'
					.$this->cfieldname.'" size="20" maxlength="50">';			
			}
		}
		
		if($mode == MODE_INLINE_EDIT && $this->format == EDIT_FORMAT_DATABASE_FILE)
			$disp = "";
		echo $disp.$strtype;
		if ($mode == MODE_EDIT || $mode==MODE_INLINE_EDIT)
		{
			echo '<br>';
		}
		echo '<input type="File" '.$this->inputStyle.' id="'.$this->cfield.'" '
			.(($mode==MODE_INLINE_EDIT || $mode==MODE_INLINE_ADD) && $this->is508 ? 'alt="'.$this->strLabel.'" ' : '').' name="'
			.$this->cfield.'" >'.$strfilename;
		echo '<input type="Hidden" id="notempty_'.$this->cfieldname.'" value="'.(strlen($value) ? 1 : 0).'">';
		$this->buildControlEnd($validate);
	}
	
	function readWebValue(&$avalues, &$blobfields, $strWhereClause, $oldValuesRead, &$filename_values)
	{
		$filename = "";
		$this->getPostValueAndType();
		if (FieldSubmitted($this->goodFieldName."_".$this->id))
		{
			$fileNameForPrepareFunc = securityCheckFileName(postvalue("filename_".$this->goodFieldName."_".$this->id));
			if($this->pageObject->pageType != PAGE_EDIT)
			{
				$prepearedFile = prepare_file($this->webValue, $this->field, "file2", $fileNameForPrepareFunc, $this->id);
				if($prepearedFile !== false)
				{
					$this->webValue = $prepearedFile["value"];
					$filename = $prepearedFile["filename"];
				}
				else 
					$this->webValue = false;
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
			if($this->webValue)
			{
				if($this->pageObject->pSetEdit->getCreateThumbnail($this->field))
				{
					$ext = CheckImageExtension(GetUploadedFileName("value_".$this->goodFieldName."_".$this->id));
					$thumb = CreateThumbnail($this->webValue, $this->pageObject->pSetEdit->getThumbnailSize($this->field), $ext);
					$blobfields[] = $this->pageObject->pSetEdit->getStrThumbnail($this->field);
					$avalues[$blobfields[count($blobfields) - 1]] = $thumb;
				}
				if($this->pageObject->pSetEdit->getResizeOnUpload($this->field))
				{
					$ext = CheckImageExtension(GetUploadedFileName("value_".$this->goodFieldName."_".$this->id));
					$this->webValue = CreateThumbnail($this->webValue, $this->pageObject->pSetEdit->getNewImageSize($this->field), $ext);
				}
			}
			else if($this->pageObject->pageType == PAGE_EDIT && $this->pageObject->pSetEdit->getCreateThumbnail($this->field))
			{
				$blobfields[] = $this->pageObject->pSetEdit->getStrThumbnail($this->field);
				$avalues[$blobfields[count($blobfields) - 1]] = "";
			}
			$blobfields[] = $this->field;		
			$avalues[$this->field] = $this->webValue;
		}
		if($filename && $this->pageObject->pSetEdit->getStrFilename($this->field))
			$filename_values[$this->pageObject->pSetEdit->getStrFilename($this->field)] = $filename;
	}
}
?>