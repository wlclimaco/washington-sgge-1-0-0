<?php
// menuItem class
include_once(getabspath("include/menuitem.php"));
include(getabspath("include/testing.php"));

/**
  * Xlinesoft Template Engine
  */
class XTempl
{
	var $xt_vars=array();
	var $xt_stack;
	var $xt_events=array();
	var $template;
	var $template_file;
	var $charsets=array();
	var $testingFlag=false;
	var $eventsObject;
	var $hiddenBricks = array();
	
	/**
	 * $cssFiles
	 * Array of css files for page styles and layouts
	 * @var {array}
	 * @intellisense
	 */
	var $cssFiles = array();
	/**
	 * $cssFiles
	 * Array of additional IE css files for page styles
	 * @var {array}
	 * @intellisense
	 */
	var $IEcssFiles = array();

	/**
	  * Returns variable by name.
      * @intellisense
      */
	function getvar($name)
	{
		return xt_getvar($this,$name);
	}

	function recTesting(&$arr)
	{
		global $testingLinks;
		foreach($arr as $k=>$v)
			if(is_array($v))
				$this->recTesting($arr[$k]);
			else
				if(isset($testingLinks[$k]))
					$arr[$k].=" func=\"".$testingLinks[$k]."\"";
	}
	
	function Testing()
	{
		if(!$this->testingFlag)
			return;
		$this->recTesting($this->xt_vars);
	}
	
	function report_error($message)
	{
		echo $message;
		exit();
	}
	
	function XTempl()
	{
		$this->xt_vars=array();
		$this->xt_stack=array();
		$this->xt_stack[]=&$this->xt_vars;
		if (!isMobile())
		{
			xtempl_include_header($this,"header","include/header.php");
			xtempl_include_header($this,"footer","include/footer.php");
		}
		else
		{
			xtempl_include_header($this,"header","include/mheader.php");
			xtempl_include_header($this,"footer","include/mfooter.php");
		}
		$this->assign_method("event",$this, "xt_doevent",array());
		$this->assign_function("label","xt_label",array());
		$this->assign_function("custom","xt_custom",array());
		$this->assign_function("caption","xt_caption",array());
		$this->assign_function("mainmenu","xt_displaymenu",array());
		$this->assign_function("TabGroup","xt_displaytabs",array());
		$this->assign_function("Section","xt_displaytabs",array());
		
		$mlang_charsets=array();
		
$mlang_charsets["Portuguese(Brazil)"]="Windows-1252";;
		$this->charsets = &$mlang_charsets;
		
		$order = $this->getReadingOrder();
		$html_attrs = '';
		if($order=='RTL')
		{
			$this->assign("RTL_block",true);
			$this->assign("rtlCSS",true);
			$html_attrs .= 'dir="RTL" ';
		}
		else
			$this->assign("LTR_block",true);
		if(mlang_getcurrentlang() == 'English')
				$html_attrs .= 'lang="en"';
		$this->assign("html_attrs",$html_attrs);	
		$this->assign("menu_block",true);	
	}
	
	function getReadingOrder()
	{
		if(@$_REQUEST["language"])
			$charset = $this->charsets[$_REQUEST["language"]];
		else if(@$_SESSION["language"])
			$charset = $this->charsets[$_SESSION["language"]];
		else
			$charset = $this->charsets['Portuguese(Brazil)'];
			
		$cp = strtolower($charset);
		if($cp == 'windows-1256' || $cp == 'windows-1255')
			return 'RTL';
		else
			return 'LTR';
	}

	/**
	  * Assign value to name.
	  * @intellisense
	  */
	function assign($name,$val)
	{
		$this->xt_vars[$name]=$val;
	}

	/**
	  * Assign value to name by reference.
	  * @intellisense
	  */
	function assignbyref($name,&$var)
	{
		$this->xt_vars[$name]=&$var;
	}

	function enable_section($name)
	{
		if(!isset($this->xt_vars[$name]))
		{
			$this->xt_vars[$name] = true;
		}
		elseif($this->xt_vars[$name] == false)
		{
			$this->xt_vars[$name] = true;
		}
	}

	function assign_section($name,$begin,$end)
	{
		$arr = array();
		$arr["begin"]=$begin;
		$arr["end"]=$end;
		$this->xt_vars[$name]=&$arr;
	}

	function assign_loopsection($name,&$data)
	{
		$arr = array();
		$arr["data"]=&$data;
		$this->xt_vars[$name]=&$arr;
	}


	function assign_function($name,$func,$params)
	{
		$this->xt_vars[$name]=array("func"=>$func,"params"=>$params);
	}

	function assign_method($name,&$object,$method,$params)
	{
		$this->xt_vars[$name]=array("method"=>$method,"params"=>$params);
		$this->xt_vars[$name]["object"]=&$object;
	}

	/**
	 * Remove assigned element
	 * @param string - name of assigned element
	 * @intellisense
	 */
	function unassign($name){
		unset($this->xt_vars[$name]);
	}

	function assign_event($name,&$object,$method,$params)
	{
		 $this->xt_events[$name]=array("method"=>$method,"params"=>$params);
		 $this->xt_events[$name]["object"]=&$object;
	}

	function xt_doevent($params)
	{
		if (isset($this->xt_events[@$params["custom1"]]))
		{
			$eventArr = $this->xt_events[@$params["custom1"]];
			
			if(isset($eventArr["method"]))
			{
				$params=array();
				if(isset($eventArr["params"]))
					$params=$eventArr["params"];
				$method=$eventArr["method"];
	//			if(method_exists($eventArr["object"],$method))
					$eventArr["object"]->$method($params);
				return;
			}
		}
		global $strTableName, $globalEvents;
		if($this->eventsObject)
			$eventObj = &$this->eventsObject;
		elseif(strlen($strTableName))
			$eventObj = getEventObject($strTableName);
		else
			$eventObj = &$globalEvents;
		if(!$eventObj)
			return;
		$eventName = $params["custom1"];
		if(!$eventObj->exists($eventName))
			return;
		$eventObj->$eventName($params);
	}
	
	function fetchVar($varName)
	{
		ob_start();
		$varParams = array();
		$this->processVar($this->getVar($varName), $varParams);	
		$out=ob_get_contents();
		ob_end_clean();
		return $out;
		
	}

	function fetch_loaded($filtertag="")
	{
		ob_start();
		$this->display_loaded($filtertag);
		$out=ob_get_contents();
		ob_end_clean();
		return $out;
	}

	function fetch_loaded_before($filtertag)
	{
		$pos1=strpos($this->template,"{BEGIN ".$filtertag."}");
		if($pos1===false)
			return;
		$str=substr($this->template,0,$pos1);
		ob_start();
		$this->Testing();
		xt_process_template($this,$str);
		$out=ob_get_contents();
		ob_end_clean();
		return $out;
	}

	function fetch_loaded_after($filtertag)
	{
		$pos2=strpos($this->template,"{END ".$filtertag."}");
		if($pos2===false)
			return;
		$str=substr($this->template,$pos2+strlen("{END ".$filtertag."}"));
		ob_start();
		$this->Testing();
		xt_process_template($this,$str);
		$out=ob_get_contents();
		ob_end_clean();
		return $out;
	}
	
	function call_func($var)
	{
		return xtempl_get_func_output($var);
	}

	function set_template($template)
	{
		//	read template file
		$templatesPath = "templates/";
		if (isMobile())
			$templatesPath = "mobile/";
		if(file_exists(getabspath($templatesPath.$template)))
			$this->template = myfile_get_contents(getabspath($templatesPath.$template));
		
		if (isMobile() && $this->template==''){
			$templatesPath = "templates/";
			$this->template = myfile_get_contents(getabspath($templatesPath.$template));
		}
		$this->template_file = basename($template,".htm");
	}

	function prepare_template($template)
	{
		$this->prepareContainers();
	}

	function load_template($template)
	{
		$this->set_template($template);
		$this->prepareContainers();
	}

	function display_loaded($filtertag = "")
	{
		$str = $this->template;
		if($filtertag)
		{
			$pos1 = strpos($this->template, "{BEGIN ".$filtertag."}");
			$pos2 = strpos($this->template, "{END ".$filtertag."}");
			if($pos1 === false || $pos2 === false)
				return;
			$pos2 += strlen("{END ".$filtertag."}");
			$str = substr($this->template,$pos1,$pos2-$pos1);
		}
		$this->Testing();
		xt_process_template($this,$str);
	}
	
	function display($template)
	{
		$this->load_template($template);
		$this->Testing();
		xt_process_template($this,$this->template);
	}
	
	function processVar(&$var, &$varparams)
	{
		if(!is_array($var))
		{
		//	just display a value
			echo $var;
		}
		elseif(isset($var["func"]))
		{
		//	call a function
			$params = array();
			if(isset($var["params"]))
				$params = $var["params"];
			foreach($varparams as $key => $val)
				$params["custom".$key] = $val;
			$func = $var["func"];
			xtempl_call_func($func,$params);
		}
		elseif(isset($var["method"]))
		{
			$params = array();
			if(isset($var["params"]))
				$params = $var["params"];
			foreach($varparams as $key=>$val)
				$params["custom".$key]=$val;
			$method = $var["method"];
//			if(method_exists($var["object"],$method))
				$var["object"]->$method($params);
		}
		else
		{
			$this->report_error("Incorrect variable value");
			return;
		}
	}
	
	/**
	 * Display brick hidden
	 * @param {string} brick name
	 * @intellisense
	 */
	function displayBrickHidden($name)
	{
		$this->hiddenBricks[$name] = true;
	}

	/** 
	 * Hide All bricks on the page
	 * @param {array} of excepted bricks
	 * @intellisense
	 */
	function hideAllBricksExcept($arrExceptBricks){
		global $page_layouts;
		foreach($page_layouts[$this->template_file]->containers as $cname=>$container)
		{
			foreach($container as $brick)
			{
				if (!in_array($brick["name"],$arrExceptBricks)){
					$this->assign($brick["block"],false);
				}	
			}
		}
	}
	
	/** 
	 * Show brick on the page
	 * @param {string} name of brick
	 * @intellisense
	 */
	function showBrick($name)
	{
		global $page_layouts;
		foreach($page_layouts[$this->template_file]->containers as $cname=>$container)
		{
			foreach($container as $brick)
			{
				if ($brick["name"]==$name){
					$this->assign($brick["block"],true);
				}
			}
		}
	}
	
	/** 
	 * Check are bricks exist on page
	 * If not pass param "all" then check if any brick from array is exist
	 * @param {array} names of bricks
	 * @param {boolean} check all bricks on exist or not
	 * @return {boolean}
	 * @intellisense
	 */
	function isExistBricks($names, $all = false)
	{
		$exist = false;
		foreach($names as $name)
		{
			if($this->isExistBrick($name))
			{
				if(!$all)
				{
					return true;
				}	
				$exist = true;	
			}
			elseif($all)
			{
				$exist = false;
			}	
		}	
		return $exist;
	}
	
	/** 
	 * Check is brick exist on page
	 * @param {string} name of brick
	 * @return {boolean}
	 * @intellisense
	 */
	function isExistBrick($name)
	{
		global $page_layouts;
		if (isset($page_layouts[$this->template_file]))
		{	
			foreach($page_layouts[$this->template_file]->containers as $cname=>$container)
			{
				foreach($container as $brick)
				{
					if ($brick["name"] == $name)
					{
						return true;
					}
				}
			}
		}	
		return false;
	}
	
	/** 
	 * Prepare containers for show on page
	 * @intellisense
	 */
	function prepareContainers()
	{
		global $page_layouts;
		if(!isset($page_layouts[$this->template_file]))
			return;
//	set page style
		$layout =& $page_layouts[$this->template_file];
		$pageStyle = $layout->style;
		if(isMobile())
		{
			$pageStyle = $layout->styleMobile;
		}
		else if(postvalue("pdf"))
		{
			$pageStyle = $layout->pdfStyle();
		}
		
		$styleRows = array();
		$pagestyleRows = array();
		$IEcssRows = array();
		
		$styleRows["data"][] = "styles/".$pageStyle."/style".($this->getReadingOrder() == 'RTL' ? 'RTL' : '').".css";
		if(isMobile())
		{
			$pagestyleRows["data"][] = "pagestyles/mobile/".$layout->name.($this->getReadingOrder() == 'RTL' ? 'RTL' : '').".css";
		}
		else
		{
			$pagestyleRows["data"][] = "pagestyles/".$layout->name.($this->getReadingOrder() == 'RTL' ? 'RTL' : '').".css";
		}
		$IEcssRows["data"][] = "styles/".$pageStyle."/styleIE.css";
		$IEcssRows["data"][] = "pagestyles/".$layout->name."IE.css";
		
		for($i = 0; $i < count($this->cssFiles); $i++){
			if(isset($this->cssFiles[$i]["stylepath"]) && $this->cssFiles[$i]["stylepath"] != '')
				$styleRows["data"][] = $this->cssFiles[$i]["stylepath"];
			if(isset($this->cssFiles[$i]["pagestylepath"]) && $this->cssFiles[$i]["pagestylepath"] != '')				
				$pagestyleRows["data"][] = $this->cssFiles[$i]["pagestylepath"];
		}
		for($i = 0; $i < count($this->IEcssFiles); $i++)
			$IEcssRows["data"][] = $this->IEcssFiles[$i]["stylepathIE"];
		
		$styleRows["data"] = array_unique($styleRows["data"]);
		$pagestyleRows["data"] = array_unique($pagestyleRows["data"]);
		$IEcssRows["data"] = array_unique($IEcssRows["data"]);

		foreach($styleRows["data"] as $key => $val)
			$styleRows["data"][$key] = array("stylepath" => $val);
		foreach($pagestyleRows["data"] as $key => $val)
			$pagestyleRows["data"][$key] = array("pagestylepath" => $val);
		foreach($IEcssRows["data"] as $key => $val)
			$IEcssRows["data"][$key] = array("stylepathIE" => $val);
		
		$this->assignbyref("styleCSSFiles", $styleRows);
		$this->assignbyref("pageCSSFiles", $pagestyleRows);
		$this->assignbyref("IEcssFiles", $IEcssRows);
		
		$this->assign("stylename",$pageStyle." page-".$layout->name);
		$displayed_containers = array();
		$hidden_containers = array();
		
		// run reverse loop for proper processing of nested containers  
		$containersNames = array_keys($layout->containers);
		$containersNames = array_reverse($containersNames);
		foreach($containersNames as $cname)
		{
			$container = $layout->containers[$cname];
			if(isset($this->xt_vars["container_".$cname]) && $this->xt_vars["container_".$cname] == false)
				continue;
			$firstContainerSubstyle = "";
			$lastContainerSubstyle = "";
			$show = false;
			$hide = true;
			foreach($container as $brick)
			{
				if(!strlen($brick["block"]))
				{
					$show = true;
				}
				elseif(!isset($this->xt_vars[$brick["block"]]))
				{
					continue;
				}
				elseif(!$this->xt_vars[$brick["block"]])
				{
					continue;
				}
				if(!$firstContainerSubstyle)
				{
					$firstContainerSubstyle = "runner-toprow style".$brick["substyle"];
					if($brick["name"] == "vmenu")
						$firstContainerSubstyle = "runner-toprow runner-vmenu";
				}
				$lastContainerSubstyle = "runner-bottomrow style".$brick["substyle"];
				if($brick["name"] == "vmenu")
					$lastContainerSubstyle = "runner-bottomrow runner-vmenu";
				$show = true;
				if($this->hiddenBricks[$brick["name"]] 
					|| $brick["name"] == "wrapper" 
						&& (isset($hidden_containers[$brick["container"]]) || !isset($displayed_containers[$brick["container"]]))){
					$this->assign("brickclass_".$brick["name"], "runner-hiddenbrick");
				}else{
					$hide = false;
				}
			}
			if($show)
			{
				$styleString = "";
				if(isset($layout->skins[$cname]))
				{
					$skin = @$layout->skins[$cname];
					$styleString = " class=\"runner-s-".$skin." ".$pageStyle;
					if($hide)
					{
						$styleString .= " runner-hiddencontainer";
						$hidden_containers[$cname] = true;
						$this->assign("wrapperclass_".$cname,"runner-hiddencontainer");
					}
					$styleString.= "\"";
				}
				$this->assign_section("container_".$cname,"<div ".$styleString.">","</div>");
				$this->assign("cheaderclass_".$cname,$firstContainerSubstyle);
				$this->assign("cfooterclass_".$cname,$lastContainerSubstyle);
				$displayed_containers[$cname] = true;
			}
			else 
			{
				$this->assign("wrapperclass_".$cname,"runner-hiddencontainer");
			}			
		}
		//	display blocks
		foreach($layout->blocks as $bname=>$block)
		{
			$show = false;
			$hide = true;
			foreach($block as $cname)
			{
				if($displayed_containers[$cname])
				{
					$show = true;
					if(!$hidden_containers[$cname])
					{
						$hide = false;
						break;
					}
				}
			}
			if(!$show || $hide)
			{
				$this->assign("blockclass_".$bname,"runner-hiddenblock");
			}
		}
	}
	
	function hideField($fieldName)
	{
		$this->assign("fielddispclass_".GoodFieldName($fieldName), "runner-hiddenfield");
	}
	
	function showField($fieldName)
	{
		$this->assign("fielddispclass_".GoodFieldName($fieldName), "");
	}
}

$menuparams = array();
function xt_displaymenu($params)
{
	global $strTableName, $pageName, $menuparams;	
	$menuparams = $params;
	include(getabspath("include/displaymenu.php"));
}

$tabparams = array();
// display tabs in group or simple section
function xt_displaytabs($params)
{
	global $strTableName, $xt, $tabparams;	
	$tabparams = $params;
	$savedTemplate = $xt->template;
	include(getabspath("include/displaytabs.php"));
	$xt->template = $savedTemplate;
}

//	BuildEditControl wrapper
function xt_buildeditcontrol(&$params)
{
	global $gSettings, $data;
	$pageObj = $params["pageObj"];
	
	$field = $params["field"];
	if($params["mode"] == "edit")
		$mode = MODE_EDIT;
	else if($params["mode"] == "add")
	{
		$mode = MODE_ADD;
	}
	else if($params["mode"]=="inline_edit")
		$mode = MODE_INLINE_EDIT;
	else if($params["mode"]=="inline_add")
		$mode = MODE_INLINE_ADD;
	else
		$mode = MODE_SEARCH;
	
	$fieldNum = 0;
	if(@$params["fieldNum"])
		$fieldNum = $params["fieldNum"];
	
	$id = "";
	if(@$params["id"] !== "")
		$id = $params["id"];
	
	$validate = array();
	if(count(@$params["validate"]))
		$validate = @$params["validate"];
	
	$additionalCtrlParams = array();
	if(count(@$params["additionalCtrlParams"]))
		$additionalCtrlParams = @$params["additionalCtrlParams"];
	
	$pageObj->getControl($field, $id)->buildControl(@$params["value"], $mode, $fieldNum, $validate, $additionalCtrlParams, $data);
}

function xt_showchart($params)
{
	$width=700;
	$height=530;
	if(isset($params["custom1"]))
		$width=$params["custom1"];
	if(isset($params["custom2"]))
		$height=$params["custom2"];
	$settings = new ProjectSettings(GetTableByShort($params["chartname"]));
	$refresh = $settings->getChartRefreshTime()*60000;
	if ($_SERVER["SERVER_PORT"]==443)
		$http = "https";
	else
		$http="http";
	
?>
<div id='<?php echo $params["chartname"] ?>' style="width:<?php echo $width; ?>px;height:<?php echo $height; ?>px">
<noscript>
	<object id="'.htmlspecialchars(postvalue('cname')).'" 
		name="'.htmlspecialchars(postvalue('cname')).'" 
		classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" 
		width="100%" 
		height="100%" 
		codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
		<param name="movie" value="libs/swf/Preloader.swf" />
		<param name="bgcolor" value="#FFFFFF" />

		<param name="allowScriptAccess" value="always" />
		<param name="flashvars" value="swfFile=dchartdata.php%3Fcname%3D'.htmlspecialchars(postvalue('cname')).'%26ctype%3D'.$chrt_array['chart_type']['type'].'" />
		
		<embed type="application/x-shockwave-flash" 
			   pluginspage="http://www.adobe.com/go/getflashplayer" 
			   src="libs/swf/Preloader.swf" 
			   width="100%" 
			   height="100%" 
			   id="'.htmlspecialchars(postvalue('cname')).'" 
			   name="'.htmlspecialchars(postvalue('cname')).'" 
			   bgColor="#FFFFFF" 
			   allowScriptAccess="always" 
			   flashvars="swfFile=dchartdata.php%3Fcname%3D'.htmlspecialchars(postvalue('cname')).'%26ctype%3D'.$chrt_array['chart_type']['type'].'" />
	</object>				
</noscript>
<script type="text/javascript" language="javascript" src="libs/js/AnyChart.js"></script>
<script type="text/javascript" language="javascript" src="libs/js/AnyChartHTML5.js"></script>
<script type="text/javascript">
AnyChart.renderingType = anychart.RenderingType.FLASH_PREFERRED;
var svgSupported = window.SVGAngle != undefined;
if (!svgSupported)
{
 	//<![CDATA[
	document.write('<center>');
	document.write("You need to have Adobe Flash Player 9 (or above) to view the chart.<br /><br />");
	document.write("<a href=\"<?php echo $http?>://www.adobe.com/go/getflashplayer\"><img border=\"0\" src=\"<?php echo $http?>://www.adobe.com/images/shared/download_buttons/get_flash_player.gif\" /></a><br />");
	document.write('</center>');
	//]]>
}	
</script>

<script type="text/javascript" language="javascript">
	//<![CDATA[
	var chart = new AnyChart('libs/swf/AnyChart.swf','libs/swf/Preloader.swf');
	chart.width = '<?php echo $width; ?>';
	chart.height = '<?php echo $height; ?>';
	chart.wMode='opaque';
	chart.id = 'chart_' + '<?php echo $params["chartname"];?>';
	var xmlFile = 'dchartdata.php?chartname=<?php echo jsreplace($params["chartname"]);?>';
	xmlFile += '&ctype=<?php echo $params["ctype"];?>';
	chart.setXMLFile(xmlFile);
	chart.write('<?php echo $params["chartname"];?>');
	if("<?php echo $refresh?>"!="0" && "<?php echo isMobile()?>"=="false")
		setInterval('refreshChart()',<?php echo $refresh?>);
	function refreshChart()
	{
		page='dchartdata.php?chartname=<?php echo jsreplace($params["chartname"]);?>';
		params={
				action:'refresh',
				rndval:Math.random()
				};
		$.get(page,params,function(xml)
			{
				var arr = new Array();
				arr=xml.split("\n");
				for(i=0; i<arr.length;i+=2)
				{
					chart.removeSeries(arr[i]);
					chart.addSeries(arr[i+1]);
					chart.updatePointData(arr[i]+"_gauge",arr[i]+"_point",{value: arr[i+1]});
				}
				chart.refresh();
			});

	}
	//]]>
</script>
</div>
<?php
}

function xt_include($params)
{
	if(file_exists(getabspath($params["file"])))
		include(getabspath($params["file"]));
}



function xt_label($params)
{
	echo htmlspecialchars(GetFieldLabel($params["custom1"],$params["custom2"]));
}

function xt_custom($params)
{
	echo GetCustomLabel($params["custom1"]);
}

function xt_caption($params)
{
	echo GetTableCaption($params["custom1"]);
}

function xtempl_get_func_output(&$var)
{
	if(!strlen(@$var["func"]))
		return "";
	ob_start();	
	$params=$var["params"];
	$func=$var["func"];
	xtempl_call_func($func,$params);
	$out=ob_get_contents();
	ob_end_clean();
	return $out;
}
?>
