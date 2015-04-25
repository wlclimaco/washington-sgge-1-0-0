<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");


include("include/dbcommon.php");

if(!isLogged())
{
	header("Location: login.php");
	return;
}

if(isLoggedAsGuest()){
	$_SESSION["MyURL"]=$_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
}


$layout = new TLayout("menu","BoldGreenTea","MobileGreenTea");
$layout->blocks["top"] = array();
$layout->containers["menu"] = array();

$layout->containers["menu"][] = array("name"=>"menulogas","block"=>"loggedas_block","substyle"=>1);


$layout->containers["menu"][] = array("name"=>"menulogout","block"=>"logout_link","substyle"=>1);


$layout->containers["menu"][] = array("name"=>"menuchangepwd","block"=>"changepwd_link","substyle"=>1);


$layout->containers["menu"][] = array("name"=>"vmenu","block"=>"menu_block","substyle"=>1);


$layout->skins["menu"] = "menu";
$layout->blocks["top"][] = "menu";$page_layouts["menu"] = $layout;


include('include/xtempl.php');
require_once(getabspath("classes/cipherer.php"));
include('classes/runnerpage.php');


$xt = new Xtempl();

$id = postvalue("id")!=="" ? postvalue("id") : 1;

//array of params for classes
$params["id"] = $id; 
$params["xt"] = &$xt;
$params["tName"] = "global";
$params["pageType"] = PAGE_MENU;
$params["templatefile"] = "menu.htm";
$params["isGroupSecurity"] = $isGroupSecurity;
$params["needSearchClauseObj"] = false;
$pageObject = new RunnerPage($params);

// button handlers file names

//	Before Process event
if($globalEvents->exists("BeforeProcessMenu"))
	$globalEvents->BeforeProcessMenu($conn, $pageObject);

$pageObject->body["begin"] .= "<script type=\"text/javascript\" src=\"include/loadfirst.js\"></script>";
$pageObject->body["begin"] .= "<script type=\"text/javascript\" src=\"include/lang/".getLangFileName(mlang_getcurrentlang()).".js\"></script>";				

$pageObject->addCommonJs();

//fill jsSettings and ControlsHTMLMap
$pageObject->fillSetCntrlMaps();
$pageObject->body['end'] .= '<script>';
$pageObject->body['end'] .= "window.controlsMap = ".my_json_encode($pageObject->controlsHTMLMap).";";
$pageObject->body['end'] .= "window.viewControlsMap = ".my_json_encode($pageObject->viewControlsHTMLMap).";";
$pageObject->body['end'] .= "window.settings = ".my_json_encode($pageObject->jsSettings).";</script>";
$pageObject->body["end"] .= "<script type=\"text/javascript\" src=\"include/runnerJS/RunnerAll.js\"></script>";
$pageObject->body["end"] .= '<script>'.$pageObject->PrepareJS()."</script>";
$xt->assignbyref("body",$pageObject->body);

$xt->assign("username",$_SESSION["UserName"]);
$xt->assign("changepwd_link",$_SESSION["AccessLevel"] != ACCESS_LEVEL_GUEST);
$xt->assign("changepwdlink_attrs","onclick=\"window.location.href='changepwd.php';return false;\"");

$xt->assign("logoutlink_attrs","onclick=\"window.location.href='login.php?a=logout';return false;\"");

$xt->assign("guestloginlink_attrs","onclick=\"window.location.href='login.php';return false;\"");

$xt->assign("loggedas_block", !isLoggedAsGuest());
$xt->assign("logout_link", true);
$xt->assign("guestloginbutton", isLoggedAsGuest());
$xt->assign("logoutbutton", isSingleSign() && !isLoggedAsGuest());

// get redirect location for menu page
$redirect = $pageObject->getRedirectForMenuPage();
if($redirect)
{
	header("Location: ".$redirect); 
	exit();
}

$xt->assign("menu_block",true);
if($globalEvents->exists("BeforeShowMenu"))
	$globalEvents->BeforeShowMenu($xt, $pageObject->templatefile, $pageObject);

$xt->display($pageObject->templatefile);
?>