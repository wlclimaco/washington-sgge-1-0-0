<?php
/*
 * jQuery File Upload Plugin PHP Class 5.11.1
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

class UploadHandler
{
	public $formStamp;
	public $pageType;
	public $table;
	public $field;
	public $tkeys;
    public $options;
    
    /**
     * Project settings 
     * @var {object}
     */
    public $pSet = null;

    function UploadHandler($options=null) {
		$this->formStamp = "";
		$this->pageType = "";
		$this->table = "";
		$this->field = "";
		$this->tkeys = "";	
		
		$this->options = array();
        $this->options['script_url'] = 'mfhandler.php';
		$this->options['param_name'] =  'files';
		// Set the following option to 'POST', if your server does not support
		// DELETE requests. This is a parameter sent to the client:
		$this->options['delete_type'] =  'DELETE';
		$this->options['max_totalFile_size'] =  null;
		// The php.ini settings upload_max_filesize and post_max_size
		// take precedence over the following max_file_size setting:
		$this->options['max_file_size'] =  null;
		$this->options['min_file_size'] =  0;
		$this->options['resizeOnUpload'] =  false;
		$this->options['accept_file_types'] =  '/.+$/i';
		// The maximum number of files for the upload directory:
		$this->options['max_number_of_files'] =  null;
		// Image resolution restrictions:
		$this->options['max_width'] =  null;
		$this->options['max_height'] =  null;
		$this->options['min_width'] =  1;
		$this->options['min_height'] =  1;
		// Set to true to rotate images based on EXIF meta data, if available:
		$this->options['image_versions'] =  array();
		if ($options)
		{
			foreach($this->options as $key=>$value)
			{
				if(array_key_exists($key,$options))
				{
					$this->options[$key] = $options[$key];
				}
			}
		}
    }

    public function getFullUrl() {
        $https = !empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] !== 'off';
      	return
    		($https ? 'https://' : 'http://').
    		(!empty($_SERVER['REMOTE_USER']) ? $_SERVER['REMOTE_USER'].'@' : '').
    		(isset($_SERVER['HTTP_HOST']) ? $_SERVER['HTTP_HOST'] : ($_SERVER['SERVER_NAME'].
    		($https && $_SERVER['SERVER_PORT'] === 443 ||
    		$_SERVER['SERVER_PORT'] === 80 ? '' : ':'.$_SERVER['SERVER_PORT']))).
    		substr($_SERVER['SCRIPT_NAME'],0, strrpos($_SERVER['SCRIPT_NAME'], '/'));
    }

    public function set_file_delete_url($file) {
        $file["delete_url"] = $this->options['script_url']
            .'?file='.rawurlencode($file["name"]);
        $file["delete_type"] = $this->options['delete_type'];
        if ($file["delete_type"] !== 'DELETE') {
            $file["delete_url"] .= '&_method=DELETE';
        }
    }

    public function get_file_object($file_name) {
        $file_path = $this->pSet->getUploadFolder($this->field).$file_name;
        if (is_file($file_path) && $file_name[0] !== '.') {
            $file = array();
            $file["error"] = false;
            $file["name"] = $file_path;
            $file["usrName"] = $file_name;
            $path_parts = $this->pathinfo_local($file_name);
            $file["type"] = getContentTypeByExtension($path_parts["extension"]);
            $file["size"] = filesize($file_path);
            $file["url"] = rawurlencode($file_path);
            $file["thumbnail"] = "";
            foreach($this->options['image_versions'] as $version => $options) {
				if ($file_path)
				{
					$thumbPath = $this->pSet->getUploadFolder($this->field).$this->pSet->getStrThumbnail($this->field).$file_name;
					if(is_file(getabspath($thumbPath)))
					{
						$file["thumbnail"] = $thumbPath;
						$path_parts = $this->pathinfo_local($thumbPath);
						$file["thumbnail_type"] = getContentTypeByExtension($path_parts["extension"]);
						$file["thumbnail_size"] = filesize(getabspath($thumbPath));
					}
					else
					{
						$file["thumbnail"] = $file_path;
						$file["thumbnail_type"] = $file["type"];
						$file["thumbnail_size"] = $file["size"];
					}
				}				
            }
            $this->set_file_delete_url($file);
            return $file;
        }
        return null;
    }

    public function validate($uploaded_file, &$file, $error, $file_size, $index, $uploadDir) {
        if ($error) {
            $file["error"] = $error;
            return false;
        }
        if (!$file["name"]) {
            $file["error"] = "O nome do arquivo não foi fornecido";
            return false;
        }
        if (!preg_match($this->options['accept_file_types'], $file["name"])) {
            $file["error"] = "O tipo de arquivo não é permitido";
            return false;
        }
        if ($this->options['max_file_size'] && (
                $file_size > $this->options['max_file_size'] * 1024 ||
                $file["size"] > $this->options['max_file_size'] * 1024)
            ) {
            $file["error"] = mysprintf("O tamanho do arquivo excede o limite de %s kbytes", array($this->options['max_file_size']));
            return false;
        }
        if ($this->options['min_file_size'] &&
            $file_size < $this->options['min_file_size'] * 1024) {
            $file["error"] = mysprintf("O tamanho do arquivo não poderá ser menor que %s kbytes", array($this->options['min_file_size']));
            return false;
        }
   		if (is_int($this->options['max_totalFile_size']) && (
                $this->getUploadFilesSize() + $file["size"] > $this->options['max_totalFile_size'] * 1024)
            ) {
            $file["error"] = mysprintf("O tamanho total dos arquivos excede o limite de %s kbytes", array($this->options['max_totalFile_size']));
            return false;
        }
        if (is_int($this->options['max_number_of_files']) && (
                $this->getUploadFilesCount() >= $this->options['max_number_of_files'])
            ) {
            	if($this->options['max_number_of_files'] > 1)
            		$file["error"] = mysprintf("Não poderá ser feito o upload de mais de %s arquivos", array($this->options['max_number_of_files']));
            	else 
            		$file["error"] = "Só pode ser feito o upload de um único arquivo";
            return false;
        }
        $image_size = @getimagesize($uploaded_file);
		$img_width = $image_size[0];
		$img_height = $image_size[1];		
		if (is_int($img_width)) {
            if (($this->options['max_width'] && $img_width > $this->options['max_width'] ||
                    $this->options['max_height'] && $img_height > $this->options['max_height']) 
                    && !$this->options['resizeOnUpload']) {
                $file["error"] = 'maxResolution';
                return false;
            }
            if ($this->options['min_width'] && $img_width < $this->options['min_width'] ||
                    $this->options['min_height'] && $img_height < $this->options['min_height']) {
                $file["error"] = 'minResolution';
                return false;
            }
        }
        return true;
    }
    
    public function getUploadFilesCount(){
    	$result = 0;
    	foreach($_SESSION["mupload_".$this->formStamp] as $fileArray)
    		if(!$fileArray["deleted"] && count($fileArray)>0)
    			$result++;
    	return $result;
    }
    
	public function getUploadFilesSize(){
    	$result = 0;
    	foreach($_SESSION["mupload_".$this->formStamp] as $fileArray)
    		if(!$fileArray["deleted"])
    			$result += $fileArray["file"]["size"];
    	return $result;
    }

    public function handle_form_data($file, $index) {
        // Handle form data, e.g. $_REQUEST['description'][$index]
    }

    public function handle_file_upload($uploaded_file, $name, $size, $type, $error, $index) {
    	$fileInfo = array("name" => $name, "size" => intval($size), "type" => $type, "isThumbnail" => false);
    	$uploadDir = $this->pSet->getFinalUploadFolder($this->field, $fileInfo);
    	$uploadDirRelative = $this->pSet->getUploadFolder($this->field, $fileInfo);
        $file = array();
        $file["error"] = false;
        $file["name"] = trim_file_name($name, $type, $index, $this);
        $file["usrName"] = $file["name"];
        $file["size"] = intval($size);
        switch($type)
        {
        	case "image/png":
        	case "image/x-png":
        		$file["type"] = "image/png";
        		break;
        	case "image/jpeg":
        	case "image/pjpeg":
        		$file["type"] = "image/jpeg";
        		break;
        	default:
        		$file["type"] = $type;
        }
        $path_parts = $this->pathinfo_local($name);
        if($file["type"] == "")
        	$file["type"] = getContentTypeByExtension($path_parts["extension"]);
        $file["isImg"] = false;
        $file["thumbnail"] = "";
		if($this->pSet->isMakeDirectoryNeeded($this->field))
		{
			if(!makeSurePathExists($uploadDir))
			{
				$file["error"] = "A pasta destino do upload não existe";
				return $file;
			}
		}
		else if(!is_dir($uploadDir))
        {
        	$file["error"] = "A pasta destino do upload não existe";
        	return $file;
        }
        if ($this->validate($uploaded_file, $file, $error, $size, $index, $uploadDir)) {
        	$file["isImg"] = CheckImageExtension($uploaded_file) != false;
        	$this->handle_form_data($file, $index);
            $file["name"] = $this->tempnam_sfx($uploadDir, $path_parts["filename"], $path_parts["extension"]);
            $file_path = $uploadDir.$file["name"];
            clearstatcache();
            upload_File($uploaded_file, $file_path);
            $file_size = filesize($file_path);
            if($this->options["resizeOnUpload"]){
            	$tempOptions = array( 
            		'max_width' => $this->options["max_width"], 
            		'max_height' => $this->options["max_width"]);
            	$new_file_name = $this->tempnam_sfx($uploadDir, $path_parts["filename"],$path_parts["extension"]);
                if($this->create_scaled_image($uploadDir.$file["name"], $uploadDir, $new_file_name, $tempOptions, $file, false, $uploadDirRelative)){
                	unlink($file_path);
                	$file["name"] = $new_file_name;
                	$file_path = $uploadDir.$new_file_name;
                	$file_size = filesize($file_path);
                }
            }
            if ($file_size === $file["size"]) {
                $file["url"] = $uploadDir.rawurlencode($file["name"]);
                foreach($this->options['image_versions'] as $version => $options) {
                	$fileInfoThimbnail = array("name" => $name, "size" => intval($size), "type" => $type, "isThumbnail" => true);
                	$thumbUploadDir = $this->pSet->getFinalUploadFolder($this->field, $fileInfoThimbnail);
                	$thumbUploadDirRelative = $this->pSet->getUploadFolder($this->field, $fileInfoThimbnail);
        			if($this->pSet->isMakeDirectoryNeeded($this->field))
					{
						if(!makeSurePathExists($thumbUploadDir))
							continue;
					}
					$thumbnail_name = $this->tempnam_sfx($thumbUploadDir, 
        				$options['thumbnailPrefix'].$path_parts["filename"], $path_parts["extension"]);
					if ($this->create_scaled_image($uploadDir.$file["name"], $thumbUploadDir, $thumbnail_name, $options, $file
							, true, $thumbUploadDirRelative)) {
	                    clearstatcache();
                        $file_size = filesize($file_path);
                    }
                }
            } else {
                unlink($file_path);
                $file["error"] = 'abort';
            }
            $file["size"] = $file_size;
            $file["name"] = $uploadDirRelative.$file["name"];
            $this->set_file_delete_url($file);
        }
        return $file;
    }

    public function get() {
        $file_name = isset($_REQUEST['file']) ?
            basename(db_stripslashesbinary($_REQUEST['file']),"") : null;
        if ($file_name) {
            $info = $this->get_file_object($file_name);
        } else {
            $info = $this->get_file_objects();
        }
        header('Content-type: application/json');
        echo my_json_encode($info);
    }

    public function post() {
		$upload = uploadFiles($this->options['param_name']);
        $info = array();
        if ($upload && is_array($upload['tmp_name'])) {
            // param_name is an array identifier like "files[]",
            // $_FILES is a multi-dimensional array:
            foreach ($upload['tmp_name'] as $index => $value) {
                $info[] = $this->handle_file_upload(
                    $upload['tmp_name'][$index],
                    $upload['name'][$index],
                    $upload['size'][$index],
                    $upload['type'][$index],
                    $upload['error'][$index],
                    $index
                );
            }
        } elseif ($upload) {
            // param_name is a single object identifier like "file",
            // $_FILES is a one-dimensional array:
            $info[] = $this->handle_file_upload(
                $upload['tmp_name'],
                $upload['name'],
                $upload['size'],
				$upload['type'],
                $upload['error'],
				0
            ); 
        }
        header('Vary: Accept');
        $result = array();
        foreach ($info as $file)
        {
        	if($file["error"] === false)
        	{
        		//$file["name"] = $this->options['upload_url_nonabsolute'].$file["name"];
        		//if($file["thumbnail"] != "")
        		//	$file["thumbnail"] = $this->options['upload_url_nonabsolute'].$file["thumbnail"];
        		$_SESSION["mupload_".$this->formStamp][$file["usrName"]] = array();
	        	$_SESSION["mupload_".$this->formStamp][$file["usrName"]]["file"] = $file;
	        	$_SESSION["mupload_".$this->formStamp][$file["usrName"]]["fromDB"] = false;
				$_SESSION["mupload_".$this->formStamp][$file["usrName"]]["deleted"] = false;
        	}
        	$userFile = $this->buildUserFile($file);
	        if(!$userFile["isImg"]){
	        	$userFile["isImg"] = true;
	        	$userFile["thumbnail_url"] = $userFile["url"]."&icon=1";
	        }
        	$result[] = $userFile; 
        }
        $json = my_json_encode($result);
        if (isset($_SERVER['HTTP_ACCEPT']) &&
            (strpos($_SERVER['HTTP_ACCEPT'], 'application/json') !== false)) {
            header('Content-type: application/json');
        } else {
            header('Content-type: text/plain');
        }
        echo $json;
    }
    
    public function buildUserFile($file)
    {
	    $userFile = array();
        $userFile["name"] = $file["usrName"];
        $userFile["size"] = $file["size"];
        $userFile["type"] = $file["type"];
        $userFile["isImg"] = CheckImageExtension($file["name"]) != false;
        if($file["error"])
        	$userFile["error"] = $file["error"];
        $hasThumbnail = $file["thumbnail"] != "";
	    $userFile["url"] = "mfhandler.php?file=".rawurlencode($userFile["name"])."&table=".rawurlencode($this->table)
	    	."&field=".rawurlencode($this->field)."&pageType=".rawurlencode($this->pageType)
	    	.($this->tkeys != "" ? $this->tkeys : "&fkey=".$this->formStamp);
	    if($hasThumbnail)
        	$userFile["thumbnail_url"] = $userFile["url"]."&thumbnail=1";
        else 
        	$userFile["thumbnail_url"] = '';
        return $userFile;
    }

    public function delete() {
    	$fileName = postvalue("fileName");
    	$success = false;
    	if(isset($_SESSION["mupload_".$this->formStamp][$fileName]))
    	{
    		if(!$_SESSION["mupload_".$this->formStamp][$fileName]["fromDB"])
    		{
    			$sessionFile = $_SESSION["mupload_".$this->formStamp][$fileName]["file"];
	    		$file_path = $sessionFile["name"];
	    		if (is_file($file_path)) {
		        	$success = unlink($file_path);
	    		}
		        if ($success && $sessionFile["thumbnail"] != "") {
	                $file = $sessionFile["thumbnail"];
	                if (is_file($file)) {
	                    unlink($file);
	                }
		        }
		        unset($_SESSION["mupload_".$this->formStamp][$fileName]);
    		}
    		else
    		{
				$_SESSION["mupload_".$this->formStamp][$fileName]["deleted"] = true;
    			$success = true;
    		}
    	}
        header('Content-type: application/json');
        echo my_json_encode($success);
    }

    // runnerfix
    public function tempnam_sfx($path, $prefix, $suffix ){ 
		do{ 
			$fileName = $prefix."_".generatePassword(8).'.'.$suffix;
			$file = $path.$fileName; 
			$fp = @fopen($file, 'x'); 
		}while(!$fp); 
		
		fclose($fp); 
		return $fileName; 
	} 
	function create_scaled_image($file_path, $uploadDir, $new_file_name, $options, &$file, $isThumbnail, $uploadDirRelative) {
		
		$img_size = @getimagesize($file_path);
		$img_width = $img_size[0];
		$img_height = $img_size[1];
		
		$new_file_path = $uploadDir.$new_file_name;
		
		if (!$img_width || !$img_height) {
			unlink($new_file_path);
			return false;
		}
		
		
		$scale = min(
			$options['max_width'] / $img_width,
			$options['max_height'] / $img_height
		);
		if ($scale >= 1) {
			if ($file_path !== $new_file_path) {
				$result = copy($file_path, $new_file_path);
				if($result && $isThumbnail){
					$file["thumbnail"] = $uploadDirRelative.$new_file_name;
					$file["thumbnail_size"] = filesize($file_path);
					$file["thumbnail_type"] = $file["type"];
				}
				return $result;
			}
			return false;
		}
		$new_width = $img_width * $scale;
		$new_height = $img_height * $scale;
		$success = imageCreateThumb($new_width,$new_height,$img_width,$img_height,$file_path,$options,$new_file_path);
		if($success){
			if($isThumbnail){
				$file["thumbnail"] = $uploadDirRelative.$new_file_name;
				$file["thumbnail_size"] = filesize($new_file_path);
				$file["thumbnail_type"] = $file["type"];
			}else{
				$file["name"] = $new_file_name;
				$file["size"] = filesize($new_file_path);
			}
		}
		return $success;
	}
	function pathinfo_local($path)
	{
		$ret = pathinfo($path);
		if(!isset($ret["filename"]))
		{
			$extlen = strlen($ret['extension']);
			if($extlen)
				++$extlen;
			$ret["filename"] = substr($ret["basename"],0, strlen($ret["basename"]) - $extlen);
		}
		return $ret;
	}
}
