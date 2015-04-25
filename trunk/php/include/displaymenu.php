<?php
	global $pageObject;
	$pageType = "";
	$pageMode = 0;
	$menuNodes = array();
	$isAdminTable = false;
	
	if(isset($pageObject))
	{
		$pageObject->getMenuNodes();
		$pageType = $pageObject->pageType;
		$pageMode = $pageObject->mode;
		$menuNodes = $pageObject->menuNodes;
		$isAdminTable = $pageObject->isAdminTable();
	}	
		
	$xt = new Xtempl();
	$quickjump = false;
	$horizontal = false;
	
	if(array_key_exists("custom1",$menuparams))
	{ 
		if($menuparams["custom1"]=="horizontal")
			$horizontal = true;
		elseif($menuparams["custom1"]=="quickjump")	
			$quickjump = true;
	}	
		
	if(!$isAdminTable){
		if(!$quickjump){
					if(!isMobile())
				$xt->assign("simpleTypeMenu",true);
			else
				$xt->assign("treeLikeTypeMenu",true);
		}
		if($pageType == PAGE_MENU && IsAdmin() && !isMobile())
				$xt->assign("adminarea_link",true);
	}else{
		//Admin Area menu items
		$xt->assign("adminAreaTypeMenu",true);
	}	
	
	// need to predefine vars
	$nullParent = NULL;
	$rootInfoArr = array("id"=>0, "href"=>"");
	// create treeMenu instance
	$menuRoot = new MenuItem($rootInfoArr, $pageObject->menuNodes, $nullParent);
	// call xtempl assign, set session params
	$menuRoot->setMenuSession();
	$menuRoot->assignMenuAttrsToTempl($xt);
	$menuRoot->setCurrMenuElem($xt);
//	$menuRoot->clearMenuSession();
	
	$xt->assign("mainmenu_block",true);
	$rOrder = $xt->getReadingOrder();
	
	$mainmenu = array();
	if(isEnableSection508()) 
		$mainmenu["begin"]="<a name=\"skipmenu\"></a>";
	$mainmenu["end"] = '';
	//$mainmenu["end"]='<script type="text/javascript" language="javascript" src="include/jquery.dropshadow.js"></script>';
		
	$countLinks = 0;
	$countGroups = 0;
	foreach($menuRoot->children as $ind=>$val)
	{
		if($val->showAsLink)
			$countLinks++;
		if ($val->showAsGroup)
			$countGroups++;
	}
	if(($pageType == PAGE_MENU) || $countLinks>1 || $countGroups>0)
	{
		$xt->assignbyref("mainmenu_block",$mainmenu);
		if($quickjump)
			$xt->display("mainmenu_quickjump.htm");
		elseif($horizontal)
			$xt->display("mainmenu_horiz.htm");
		else
			$xt->display("mainmenu.htm");
	}
?>
