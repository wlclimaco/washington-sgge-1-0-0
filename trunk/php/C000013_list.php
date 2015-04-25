<?php
@ini_set("display_errors","1");
@ini_set("display_startup_errors","1");

include("include/dbcommon.php");
add_nocache_headers();

include('include/xtempl.php');
include("include/C000013_variables.php");
include('classes/runnerpage.php');
include('classes/listpage.php');
include("classes/searchpanel.php");
include("classes/searchcontrol.php");
include("classes/searchclause.php");
include("classes/panelsearchcontrol.php");

if(!isLogged())
{ 
	$_SESSION["MyURL"]=$_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
	header("Location: login.php?message=expired"); 
	return;
}

if(isLoggedAsGuest()){
   $_SESSION["MyURL"] = $_SERVER["SCRIPT_NAME"]."?".$_SERVER["QUERY_STRING"];
}

if(CheckPermissionsEvent($strTableName, 'S') && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Search") && !CheckSecurity(@$_SESSION["_".$strTableName."_OwnerID"],"Add"))
{
	if(IsAdmin())
		echo "<p>"."Você não tem permissão para acessar esta tabela"."<br><a href=\"admin_rights_list.php\">"."Siga para a Área Admin"."</a> "."para configurar permissões de usuário"."</p>";
	else
		echo "<p>"."Você não tem permissão para acessar esta tabela"." <a href=\"login.php\">"."Voltar à página de Login"."</a></p>";
	exit();
}

$layout = new TLayout("list8","BoldGreenTea","MobileGreenTea");
$layout->blocks["center"] = array();
$layout->containers["bigblock"] = array();

$layout->containers["bigblock"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"recordcontrols");


$layout->containers["recordcontrols"] = array();

$layout->containers["recordcontrols"][] = array("name"=>"recordcontrols_new","block"=>"newrecord_controls_block","substyle"=>1);


$layout->containers["recordcontrols"][] = array("name"=>"recordcontrol","block"=>"record_controls_block","substyle"=>1);


$layout->skins["recordcontrols"] = "2";

$layout->containers["bigblock"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"message");


$layout->containers["message"] = array();

$layout->containers["message"][] = array("name"=>"message","block"=>"message_block","substyle"=>1);


$layout->skins["message"] = "2";

$layout->containers["bigblock"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"grid");


$layout->containers["grid"] = array();

$layout->containers["grid"][] = array("name"=>"grid","block"=>"grid_block","substyle"=>1);


$layout->skins["grid"] = "grid";

$layout->containers["bigblock"][] = array("name"=>"wrapper","block"=>"","substyle"=>1, "container"=>"pagination");


$layout->containers["pagination"] = array();

$layout->containers["pagination"][] = array("name"=>"pagination","block"=>"pagination_block","substyle"=>1);


$layout->skins["pagination"] = "2";

$layout->skins["bigblock"] = "1";
$layout->blocks["center"][] = "bigblock";$layout->blocks["left"] = array();
$layout->containers["left"] = array();

$layout->containers["left"][] = array("name"=>"vmenu","block"=>"menu_block","substyle"=>1);


$layout->containers["left"][] = array("name"=>"vsearch1","block"=>"searchform_block","substyle"=>1);


$layout->containers["left"][] = array("name"=>"vsearch2","block"=>"searchform_block","substyle"=>1);


$layout->containers["left"][] = array("name"=>"vdetails_found","block"=>"details_block","substyle"=>2);


$layout->containers["left"][] = array("name"=>"vpage_of","block"=>"pages_block","substyle"=>1);


$layout->containers["left"][] = array("name"=>"vrecsperpage","block"=>"recordspp_block","substyle"=>1);


$layout->containers["left"][] = array("name"=>"searchpanel","block"=>"searchPanel","substyle"=>1);


$layout->skins["left"] = "menu";
$layout->blocks["left"][] = "left";$layout->blocks["top"] = array();
$layout->skins["master"] = "empty";
$layout->blocks["top"][] = "master";
$layout->containers["toplinks"] = array();

$layout->containers["toplinks"][] = array("name"=>"loggedas","block"=>"security_block","substyle"=>1);


$layout->containers["toplinks"][] = array("name"=>"toplinks_print","block"=>"prints_block","substyle"=>1);


$layout->containers["toplinks"][] = array("name"=>"toplinks_advsearch","block"=>"asearch_link","substyle"=>1);



$layout->containers["toplinks"][] = array("name"=>"toplinks_export","block"=>"export_link","substyle"=>1);




$layout->skins["toplinks"] = "2";
$layout->blocks["top"][] = "toplinks";$page_layouts["C000013_list"] = $layout;



//	Include necessary files in accordance with mode displaying page
if (postvalue("mode")=="")
{
	$mode = LIST_SIMPLE;
	include('classes/listpage_simple.php');
	include("classes/searchpanelsimple.php");
}
elseif(postvalue("mode") == "ajax")
{
	$mode = LIST_AJAX;
	include('classes/listpage_simple.php');
	include('classes/listpage_ajax.php');
	include("classes/searchpanelsimple.php");
}
elseif(postvalue("mode") == "lookup")
{	
	include('classes/listpage_embed.php');
	include('classes/listpage_lookup.php');
	include("classes/searchpanellookup.php");
	$mode=LIST_LOOKUP;
	//determine which field should be used to select values
			$params["lookupSelectField"] = "NUMERO";
				}
elseif(postvalue("mode")=="listdetails")
{
	
	include('classes/listpage_embed.php');
	include('classes/listpage_dpinline.php');
	$mode=LIST_DETAILS;
}
$xt = new Xtempl();


// Modify query: remove blob fields from fieldlist.
// Blob fields on a list page are shown using imager.php (for example).
// They don't need to be selected from DB in list.php itself.
$noBlobReplace = false;

$options = array();
//array of params for classes
$options["pageType"] = PAGE_LIST;
$options["id"] = postvalue("id") ? postvalue("id") : 1;
$options["mode"] = $mode;
$options['xt'] = &$xt;
$options['mainMasterPageType'] = postvalue("mainmasterpagetype");
$options['masterPageType'] = postvalue("masterpagetype");
$options["masterTable"] = postvalue("mastertable");
$options["masterId"] = postvalue("masterid");
$options["firstTime"] = postvalue("firsttime");

$i = 1;
while(isset($_REQUEST["masterkey".$i])) 
{
	$options["masterKeysReq"][$i] = $_REQUEST["masterkey".$i];
	$i++;
}
$pageObject = ListPage::createListPage($strTableName, $options);



if (!$noBlobReplace){
	$gQuery->ReplaceFieldsWithDummies($pageObject->pSet->getBinaryFieldsIndices());
}

if ($mode != LIST_DETAILS) { 
}

unset($_SESSION["message_add"]);
unset($_SESSION["message_edit"]);

// prepare code for build page
$pageObject->prepareForBuildPage();

$includesArr = array();

//include files if need
for($i=0;$i<count($includesArr);$i++)
	include($includesArr[$i]);

// show page depends of mode

$pageObject->showPage();

?>