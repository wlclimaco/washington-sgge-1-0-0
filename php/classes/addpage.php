<?php
include_once(getabspath("classes/runnerpage.php"));
class AddPage extends RunnerPage
{
	
	function AddPage(&$params)
	{
		parent::RunnerPage($params);
	}
	
	/**
	 * Assign body end
	 */	
	function assignBodyEnd(&$params) 
	{
		parent::assignBodyEnd($params);
			}
}
?>