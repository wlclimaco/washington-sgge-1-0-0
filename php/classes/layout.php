<?php

class TLayout 
{
	var $containers = array();
	var $blocks = array();
	var $name = ""; 
	var $style = "";
	var $styleMobile = "";
	var $skins = array();
	
	function TLayout($name, $style, $styleMobile)
	{
		$this->name = $name;
		$this->style = $style;
		$this->styleMobile = $styleMobile;
	}
	
	function pdfStyle()
	{
		return "Pdf".substr($this->styleMobile,6);
	}
};


?>