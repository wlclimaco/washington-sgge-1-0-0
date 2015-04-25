<?php
class ViewUserControl extends ViewControl
{
	function initUserControl()
	{		
	}
	
	/**
	 * Control settings filling
	 */
	function init()
	{
		// We need to add this dependencies ViewControl.js - for debug.
		// For build we need to add RunnerAll.js
		//$this->getContainer()->AddJSFile("include/runnerJS/controls/".$this->viewFormat.".js", 'include/runnerJS/ViewControl.js');
		
	}
}
?>