<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");

if(!isLogged() || @$_SESSION["UserID"]=="<Guest>")
{ 
	$_SESSION["MyURL"]=$_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
	header("Location: login.php?message=expired"); 
	return;
}

include('include/xtempl.php');
include('classes/runnerpage.php');
require_once(getabspath("classes/cipherer.php"));

$xt = new Xtempl();
$id = postvalue("id") != "" ? postvalue("id") : 1;
$message = "";

$cipherer = new RunnerCipherer("WEB");

$layout = new TLayout("changepwd","BoldGreenTea","MobileGreenTea");
$layout->blocks["top"] = array();
$layout->containers["fields"] = array();

$layout->containers["fields"][] = array("name"=>"changeheader","block"=>"","substyle"=>2);


$layout->containers["fields"][] = array("name"=>"message","block"=>"message_block","substyle"=>1);


$layout->containers["fields"][] = array("name"=>"changefields","block"=>"","substyle"=>1);


$layout->containers["fields"][] = array("name"=>"changebuttons","block"=>"","substyle"=>2);


$layout->skins["fields"] = "fields";
$layout->blocks["top"][] = "fields";$page_layouts["changepwd"] = $layout;


//array of params for classes
$params = array("pageType" => PAGE_CHANGEPASS, "id" =>$id);
$params['xt'] = &$xt;
$params['tName'] = "global";
$params['templatefile'] = "changepwd.htm";
$params['needSearchClauseObj'] = false;
$pageObject = new RunnerPage($params);
$referer = @$_SERVER["HTTP_REFERER"] != "" 
	&& strpos($_SERVER["HTTP_REFERER"], "changepwd.php") != strlen($_SERVER["HTTP_REFERER"]) - strlen("changepwd.php")
	? $_SERVER["HTTP_REFERER"] : ""; 
if(!isset($_SESSION["changepwd_referer"]))
	$_SESSION["changepwd_referer"] = $referer != "" ? $referer : "menu.php";
else if($referer != "")
	$_SESSION["changepwd_referer"] = $referer;

$auditObj = GetAuditObject();

//	Before Process event
if($globalEvents->exists("BeforeProcessChangePwd"))
	$globalEvents->BeforeProcessChangePwd($conn, $pageObject);

if (@$_POST["btnSubmit"] == "Submit")
{	
	$xt->assign("backlink_attrs","href=\"".$_SESSION["changepwd_referer"]."\"");
	$opass = postvalue("opass");
	$newpass = postvalue("newpass");
	$newpassraw=$newpass;
	

	$value = @$_SESSION["UserID"];
	
	if($cipherer->isFieldEncrypted($cUserNameField))
		$value = $cipherer->MakeDBValue($cUserNameField,$value,"","",true);
	else
	{
		if(NeedQuotes($cUserNameFieldType))
			$value=db_prepare_string($value);
		else
			$value=(0+$value);
	}
	
	$passvalue = $newpass;
	
		if($cipherer->isFieldEncrypted($cPasswordField))
			$passvalue = $cipherer->MakeDBValue($cPasswordField,$newpass);
		else
		{
			if(NeedQuotes($cPasswordFieldType))
				$passvalue=db_prepare_string($passvalue);
			else
				$passvalue=(0+$passvalue);
		}

    	$sWhere = " where ".GetFullFieldName($cUserNameField,"WEB",false)."=".$value;
		$strSQL = "select ".GetFullFieldName($cPasswordField,"WEB",false)." as ".AddFieldWrappers($cPasswordField)." from ".AddTableWrappers($cLoginTable).$sWhere;
		$rstemp=db_query($strSQL,$conn);

		if($row=$cipherer->DecryptFetchedArray($rstemp))
		{
			if($opass == $row[$cPasswordField])
			{
				$retval=true;
				if($globalEvents->exists("BeforeChangePassword"))
					$retval=$globalEvents->BeforeChangePassword(postvalue("opass"), postvalue("newpass"), $pageObject);
				if($retval)
				{
					$strSQL= "update ".AddTableWrappers($cLoginTable)." set ".AddFieldWrappers($cPasswordField)."=".$passvalue.$sWhere;
					db_exec($strSQL,$conn);
					if($auditObj)
						$auditObj->LogChPassword();
					if($globalEvents->exists("AfterChangePassword"))
						$globalEvents->AfterChangePassword(postvalue("opass"), postvalue("newpass"), $pageObject);
					
					$layout = new TLayout("changepwd_success","BoldGreenTea","MobileGreenTea");
$layout->blocks["top"] = array();
$layout->containers["fields"] = array();

$layout->containers["fields"][] = array("name"=>"changeheader","block"=>"","substyle"=>2);


$layout->containers["fields"][] = array("name"=>"changepwd_message","block"=>"","substyle"=>1);


$layout->containers["fields"][] = array("name"=>"changesuccessbutton","block"=>"","substyle"=>2);


$layout->skins["fields"] = "fields";
$layout->blocks["top"][] = "fields";$page_layouts["changepwd_success"] = $layout;

					
					$xt->assign("body",true);
					$xt->display("changepwd_success.htm");
					return;
				}
			}
			else
				$message = "Senha inválida";
	}
}
else $xt->assign("backlink_attrs","href=\"".$_SESSION["changepwd_referer"]."\"");
	
if($message)
{
	$xt->assign("message","<div class='message'>".$message."</div>");
	$xt->assign("message_block",true);
}
$xt->assign("loginlink_attrs","onclick='if (document.forms.form1.onsubmit()) document.forms.form1.submit();return false;'");

$includes="";
$includes.="<script language=\"JavaScript\" src=\"include/loadfirst.js\"></script>\r\n";
$includes.="<script type=\"text/javascript\" src=\"include/lang/".getLangFileName(mlang_getcurrentlang()).".js\"></script>";

$pageObject->body["begin"] .= $includes."<script language=\"JavaScript\">
function validate(){
	if (document.forms.form1.cpass.value!=document.forms.form1.newpass.value){	
		alert('".jsreplace("Senha Incorreta. Favor tentar novamente").
		"');
		document.forms.form1.newpass.value='';
		document.forms.form1.cpass.value='';
		document.forms.form1.newpass.focus();
		return false;
	}
	return true;
}
</script>
<form method=\"POST\" action=\"changepwd.php\" id=form1 name=form1 onsubmit=\"return validate();\">
<input type=hidden name=btnSubmit value=\"Submit\">";
$pageObject->body["end"] .="</form>";

$pageObject->addCommonJs();
$pageObject->fillSetCntrlMaps();
$pageObject->body['end'] .= '<script>';
$pageObject->body['end'] .= "window.controlsMap = ".my_json_encode($pageObject->controlsHTMLMap).";";
$pageObject->body['end'] .= "window.viewControlsMap = ".my_json_encode($pageObject->viewControlsHTMLMap).";";
$pageObject->body['end'] .= "window.settings = ".my_json_encode($pageObject->jsSettings).";</script>";
$pageObject->body['end'] .= "<script language=\"JavaScript\" src=\"include/runnerJS/RunnerAll.js\"></script>\r\n";
$pageObject->body["end"] .= '<script>'.$pageObject->PrepareJS()."</script>";
$pageObject->addButtonHandlers();

$xt->assignbyref("body",$pageObject->body);

if($globalEvents->exists("BeforeShowChangePwd"))
	$globalEvents->BeforeShowChangePwd($xt,$pageObject->templatefile, $pageObject);

$xt->display($pageObject->templatefile);
?>