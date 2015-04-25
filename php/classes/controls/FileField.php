<?php
class FileField extends EditControl
{
	/**
	 * Instanse of UploadHandler class
	 * @var {object}
	 */
	var $upload_handler = null;
	
	/**
	 * Field random identifier for sessions values 
	 * @var {string}
	 */
	var $formStamp = "";
	
	function FileField($field, $pageObject, $id)
	{
		parent::EditControl($field, $pageObject, $id);
		$this->format = EDIT_FORMAT_FILE;
		
		global $conn;
		$this->conn = $conn;
	}
	
	function addJSFiles()
	{
		if($this->pageObject->pageType == PAGE_ADD 
			|| $this->pageObject->pageType == PAGE_EDIT
			|| $this->pageObject->pageType == PAGE_REGISTER){
			$this->pageObject->AddJSFile("include/mupload.js");
			$this->pageObject->AddJSFile("include/zoombox/zoombox.js");
		}
	}
	
	function addCSSFiles()
	{
		if($this->pageObject->pageType == PAGE_ADD 
			|| $this->pageObject->pageType == PAGE_EDIT
			|| $this->pageObject->pageType == PAGE_REGISTER){
			$this->pageObject->AddCSSFile("include/zoombox/zoombox.css");
		}
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

		$this->formStamp = generatePassword(15);
			
		$this->initUploadHandler();
		$this->upload_handler->formStamp = $this->formStamp;
		$filesArray = my_json_decode($value);
		if(!is_array($filesArray) || count($filesArray) == 0)
		{
			if(!$value)
				$jsonValue = "[]";
			else 
			{
				$uploadedFile = $this->upload_handler->get_file_object($value);
				if(is_null($uploadedFile))
					$filesArray =  array();
				else
					$filesArray = array(my_json_decode(my_json_encode($uploadedFile)));
			}
		}
		
		if($this->pageObject->pageType == PAGE_EDIT)
		{
			if(count($this->pageObject->keys) > 0)
			{
				$i = 1;
				foreach($this->pageObject->keys as $keyName => $keyValue)
				{
					$this->upload_handler->tkeys .= "&key".$i."=".rawurlencode($keyValue);
					$i++;
				}
			}
		}
		$_SESSION["mupload_".$this->formStamp] = array();
		$userFilesArray = array();
		if(is_array($filesArray))
		{
			foreach ($filesArray as $file)
			{
				$_SESSION["mupload_".$this->formStamp][$file["usrName"]]["file"] = $file;
				$_SESSION["mupload_".$this->formStamp][$file["usrName"]]["fromDB"] = true;
				$_SESSION["mupload_".$this->formStamp][$file["usrName"]]["deleted"] = false; 
				$userFile = $this->upload_handler->buildUserFile($file);
				if(!$userFile["isImg"]){
					$userFile["isImg"] = true;
					$userFile["isIco"] = true;
					$userFile["thumbnail_url"] = $userFile["url"]."&icon=1";
				}
				$userFilesArray[] = $userFile;
			}
		}
		$jsonValue = my_json_encode($userFilesArray);
		
		echo '
 <!-- The file upload form used as target for the file upload widget -->
    <form id="fileupload_'.$this->cfieldname.'" action="mfhandler.php" method="POST" enctype="multipart/form-data">
    
    <input type="hidden" name="formStamp_'.$this->cfieldname.'" id="formStamp_'.$this->cfieldname.'" value="'.$this->formStamp.'" />
    <input type="hidden" name="_action" value="POST" />
    <input type="hidden" id="value_'.$this->cfieldname.'" name="value_'.$this->cfieldname.'" value="'.htmlspecialchars($jsonValue).'" />
    <input type="hidden" 
    
    <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="span7">
                <!-- The fileinput-button span is used to style the file input field as button -->
 				<SPAN class="btn btn-success fileinput-button">
				<SPAN class="runner-btnframe">
				<SPAN class="runner-btnleft"></SPAN>
				<SPAN class="runner-btnright"></SPAN>
					<A class="runner-button" href="#" ><input class="fileinput-button-input" type="file" name="files[]" value="'
				."Adicionar arquivos"
				.'" multiple />'
				."Adicionar arquivos"
				.'</A>
                </SPAN>
				</SPAN>'
		
		.($this->pageObject->pSetEdit->isAutoUpload($this->field) ? '' : '
                <SPAN class="btn btn-primary start">
				<SPAN class="runner-btnframe">
				<SPAN class="runner-btnleft"></SPAN>
				<SPAN class="runner-btnright"></SPAN>
				<A class="runner-button" href="#" >'
				."Upload"
				.'</A> 
				</SPAN>
				</SPAN>
				<SPAN class="btn btn-warning cancel">
				<SPAN class="runner-btnframe">
				<SPAN class="runner-btnleft"></SPAN>
				<SPAN class="runner-btnright"></SPAN>
				<A class="runner-button" href="#" >'
				."Cancela"
				.'</A> 
				</SPAN>
				</SPAN>')
		
		.'
                
            </div>
            <!-- The global progress information -->
            <div class="fileupload-progress fade">
                <!-- The global progress bar -->
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="bar" style="width:0%;"></div>
                </div>
                <!-- The extended global progress information -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The loading indicator is shown during file processing -->
        <div class="fileupload-loading"></div>
        <!-- The dummy for FireFox -->
        <input type="text" name="focusDummy" class="runner-focusDummy" />
        <br>
        <!-- The table listing the files available for upload/download -->
        <table><tbody class="files"></tbody></table>
    </form>
    ';
		if(!isset($this->container->globalVals["muploadTemplateIncluded"]))
		{
			echo '<script type="text/x-tmpl" id="template-download">{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span dir="LTR">{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">'
			.""
			.'</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else { %}
            <td class="preview">{% if (file.thumbnail_url) { %}
                <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}" 
                	{% if (!file.isIco) { %} class="zoombox zgallery" {% } %} 
                	><img src="{%=file.thumbnail_url%}&src=1"></a>
            {% } else { %}
            	{% if (file.isImg) { %}
            		<a href="{%=file.url%}&nodisp=1" title="{%=file.name%}" rel="gallery" download="{%=file.name%}" class="zoombox zgallery"><img src="{%=file.url%}&src=1"></a>
            	{% } %}
            {% } %}</td>
            <td class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&\'gallery\'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span dir="LTR">{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
        {% } %}
        <td class="delete">
        	{% if (!file.error) { %}
        	<SPAN class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}" data-name="{%=file.name%}">
				<A href="#" >'
			."Elimina Selecionados"
			.'</A>
				</SPAN>
			{% } %}
        </td>
    </tr>
{% } %}
</script>
<script type="text/x-tmpl" id="template-upload">{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">'
			.""
			.'</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" 
                	aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
        	<SPAN class="btn btn-primary">
				<A href="#" >'
			."Upload"
			.'</A>   
				</SPAN>       
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="cancel">{% if (!i) { %}
        	{% if (!file.error) { %}
        	<SPAN class="btn btn-warning">
				<A href="#" >'
			."Cancela"
			.'</A>
				</SPAN>
			{% } %}
        {% } %}</td>
    </tr>
{% } %}</script>';
			$this->container->globalVals["muploadTemplateIncluded"] = true;
		}
		$this->buildControlEnd($validate);
	}
	
	function initUploadHandler()
	{
		if(is_null($this->upload_handler))
		{
			require_once getabspath("classes/uploadhandler.php");
			$this->upload_handler = new UploadHandler(getOptionsForMultiUpload($this->pageObject->pSet, $this->field));
			$this->upload_handler->pSet = $this->pageObject->pSetEdit;
			$this->upload_handler->field = $this->field;
			$this->upload_handler->table = $this->pageObject->tName;
			$this->upload_handler->pageType = $this->pageObject->pageType;
		}
	} 
	
	function readWebValue(&$avalues, &$blobfields, $strWhereClause = "", $oldValuesRead = false, &$filename_values = null)
	{
		$this->getPostValueAndType();
		$this->formStamp = postvalue("formStamp_".$this->goodFieldName."_".$this->id);
		if (FieldSubmitted($this->goodFieldName."_".$this->id) && $this->formStamp != "")
		{
			$filesArray = my_json_decode($this->webValue);
			if(!is_array($filesArray) || count($filesArray) == 0)
				$this->webValue = "";
			else 
			{
				if(count($_SESSION["mupload_".$this->formStamp]) > 0)
				{
					foreach($_SESSION["mupload_".$this->formStamp] as $fileArray)
						$fileArray["deleted"] = true;
				}
				$result = array();
				$uploadDir = $this->pageObject->pSetEdit->getLinkPrefix($this->field);
				$searchStr = "";
				foreach ($filesArray as $file)
				{
					if(isset($_SESSION["mupload_".$this->formStamp][$file["name"]]))
					{
						$sessionFile = $_SESSION["mupload_".$this->formStamp][$file["name"]]["file"];
						$searchStr .= $file["name"].",!";
						$result[] = array("name" => $sessionFile["name"],
							"usrName" => $file["name"], "size" => $sessionFile["size"], "type" => $sessionFile["type"]
						);
						if($this->pageObject->pSetEdit->getCreateThumbnail($this->field) 
							&& $sessionFile["thumbnail"] != "")
						{
							$lastIndex = count($result) - 1; 
							$result[$lastIndex]["thumbnail"] = $sessionFile["thumbnail"];
							$result[$lastIndex]["thumbnail_type"] = $sessionFile["thumbnail_type"];
							$result[$lastIndex]["thumbnail_size"] = $sessionFile["thumbnail_size"];
						}
						$_SESSION["mupload_".$this->formStamp][$file["name"]]["deleted"] = false;
					}
				}
				if(count($result) > 0)
				{
					$result[0]["searchStr"] = $searchStr.":sStrEnd";
					$this->webValue = my_json_encode($result);
				}
				else 
					$this->webValue = "";
			}
		}
		else
			$this->webValue = false;
			
		if(!($this->webValue===false))
		{
				$avalues[$this->field] = $this->webValue;
		}
	} 
	
	public function showDBValue($value, $keyLink)
	{
		$imageValue = "";
		$this->initUploadHandler();
		$this->upload_handler->tkeys = $keyLink;
		$filesArray = my_json_decode($value);
		if(!is_array($filesArray) || count($filesArray) == 0)
		{
			if($value == "")
				$filesArray = array();
			else 
			{
				$uploadedFile = $this->upload_handler->get_file_object($value);
				if(is_null($uploadedFile))
					$filesArray =  array();
				else
					$filesArray = array($uploadedFile);
			}
		}
		foreach ($filesArray as $imageFile)
		{
			$userFile = $this->upload_handler->buildUserFile($imageFile);
			if($this->pageObject->pSet->getViewFormat($this->field) == FORMAT_FILE)
			{
				$imageValue .= ($imageValue != "" ? "</br>" : "");
				$imageValue .= '<a href="'.htmlspecialchars($userFile["url"]).'">'
					.htmlspecialchars($imageFile["usrName"] != "" ? $imageFile["usrName"] : $imageFile["name"]).'</a>';
			}
			else if(CheckImageExtension($imageFile["name"])) 
			{
				$imageValue .= ($imageValue != "" ? "</br>" : "");
				if($this->pageObject->pSet->showThumbnail($this->field)) 
				{
					$thumbname = $userFile["thumbnail_url"];
					$imageValue .= "<a target=_blank";
					
					$imageValue .= " href=\"".htmlspecialchars($userFile["url"])."\" class='zoombox'>";
					$imageValue .= "<img";
					if($thumbname == "" || $imageFile["name"] == $imageFile["thumbnail"]) {
						$imgWidth = $this->pageObject->pSet->getImageWidth($this->field);
						$imageValue .=($imgWidth ? " width=".$imgWidth : "");
						
						$imgHeight = $this->pageObject->pSet->getImageHeight($this->field);
						$imageValue .=($imgHeight ? " height=".$imgHeight : "");
					}
					
					$imageValue .= " border=0";
					if($this->is508)
						$imageValue .= " alt=\"".htmlspecialchars($userFile["name"])."\"";
					$imageValue .= " src=\"".htmlspecialchars($userFile["thumbnail_url"])."\"></a>";
				} 
				else 
				{
					$imageValue .= "<img";
					
					$imgWidth = $this->pageObject->pSet->getImageWidth($this->field);
					$imageValue.=($imgWidth ? " width=".$imgWidth : "");
					
					$imgHeight = $this->pageObject->pSet->getImageHeight($this->field);
					$imageValue .=($imgHeight ? " height=".$imgHeight : "");
					
					$imageValue .= " border=0";
					if($this->is508)
						$imageValue.= " alt=\"".htmlspecialchars($userFile["name"])."\"";
					$imageValue .= " src=\"".htmlspecialchars($userFile["url"])."\">";
				}
			}
		}
		return $imageValue;
	}
	
	function SQLWhere($SearchFor, $strSearchOption, $SearchFor2, $etype, $isSuggest)
	{
		$baseResult = $this->baseSQLWhere($strSearchOption);
		if($baseResult === false)
			return "";
		if($baseResult != "")
			return $baseResult;

		if(IsCharType($this->type))
		{
			if(!$this->pageObject->cipherer->isFieldPHPEncrypted($this->field))
				$gstrField = $this->pageObject->pSetEdit->isEnableUpper(GetFullFieldName($this->field, "", false));
			else
				$gstrField = GetFullFieldName($this->field, "", false);
		}
		elseif($strSearchOption=="Contains" || $strSearchOption=="Starts with")
		{
			$gstrField = db_field2char(GetFullFieldName($this->field, "", false), $this->type);
		}
		else
		{
			$gstrField = GetFullFieldName($this->field, "", false);
		}

		$ret="";
		
		if ($this->isMysql)
			$SearchFor = str_replace('\\\\', '\\\\\\\\', $SearchFor);
			
		if($strSearchOption=="Contains")
			$SearchFor = "%".$SearchFor."%";
		else if($strSearchOption=="Starts with") 
			$SearchFor = $SearchFor."%";

		if($strSearchOption=="Contains" || $strSearchOption=="Starts with" || $strSearchOption == "Equals") 
			return $this->buildWhere($gstrField, $SearchFor, $strSearchOption == "Equals");
		
		return "";
	}
	
	function buildWhere($gstrField, $value, $equals = false)
	{
		$likeVal = db_prepare_string('%searchStr":"'.$value.':sStrEnd"%');
		$notLikeVal = db_prepare_string($value);
		if(IsCharType($this->type))
		{
			$likeVal = $this->pageObject->pSetEdit->isEnableUpper($likeVal);
			$notLikeVal = $this->pageObject->pSetEdit->isEnableUpper($notLikeVal);
		}
				$testSymbols = "'[{%'";
		return "((".$gstrField." ".$this->like." ".$testSymbols." and ".$gstrField." ".$this->like." ".$likeVal.") or (".
			$gstrField." not ".$this->like." ".$testSymbols." and ".$gstrField." ".($equals ? "=" : $this->like)." ".$notLikeVal."))";
	}
	
	function getSearchOptions($selOpt, $not, $both)
	{
		$optionsArray = array();
		$isPHPEncripted = $this->pageObject->cipherer->isFieldPHPEncrypted($this->field);
		if(!$isPHPEncripted){
			$optionsArray[] = CONTAINS;
			$optionsArray[] = EQUALS;
		}
		$optionsArray[] = EMPTY_SEARCH;
		if($both)
		{
			if(!$isPHPEncripted){
				$optionsArray[] = NOT_CONTAINS;
				$optionsArray[] = NOT_EQUALS;
			}
			$optionsArray[] = NOT_EMPTY;
		}
		return $this->buildSearchOptions($optionsArray, $selOpt, $not, $both);
	}
	
	function suggestValue($value, $searchFor, &$response, &$row)
	{
		if(!$value)
			return;
		$filesArray = my_json_decode($value);
		if(!is_array($filesArray) || count($filesArray) == 0)
			$response[$value.""] = $value."";
		else 
			for($i = 0; $i < count($filesArray) && count($response) < 10; $i++)
			{
				if($this->pageObject->pSetEdit->getNCSearch())
				{
					if(stripos($filesArray[$i]["usrName"], $searchFor) !== false)
						$response[$filesArray[$i]["usrName"].""] = $filesArray[$i]["usrName"]."";
				}
				else 
				{
					if(strpos($filesArray[$i]["usrName"], $searchFor) !== false)
						$response[$filesArray[$i]["usrName"].""] = $filesArray[$i]["usrName"]."";
				}
			} 
	}
	
	function afterSuccessfulSave()
	{
		if(count($_SESSION["mupload_".$this->formStamp]) > 0)
		{
			foreach($_SESSION["mupload_".$this->formStamp] as $fileArray)
			{
				if($fileArray["deleted"] === true)
				{
	    			$file_path = $fileArray["file"]["name"];
	    			if (is_file($file_path)) {
			        	unlink($file_path);
	    			}
			        if ($fileArray["file"]["thumbnail"] != "") {
		                $file_path = $fileArray["file"]["thumbnail"];
		                if (is_file($file_path)) {
		                    unlink($file_path);
		                }
			        }
				}
			}
		}
		unset($_SESSION["mupload_".$this->formStamp]);
	}
}
?>