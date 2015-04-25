$layout = array();
$block = array();

$container = array();

$container[] = "recordcontrols_block";

##if (CreateMenu) || IsAdminTable(@TABLE)##
$container[] = "menu_block";
##endif##
##if Fields[bAdvancedSearch].len##
$container[] = "searchform_block";
$container[] = "searchformbuttons_block";
##endif##
##if Fields[bAdvancedSearch].len##
$container[] = "searchform_block";
$container[] = "searchformbuttons_block";
##endif##
##if @TABLE.nType!=titCHART##
$container[] = "details_block";
##endif##
##if @TABLE.nType!=titCHART##
$container[] = "pages_block";
##endif##
##if @TABLE.nType!=titCHART##
$container[] = "recordspp_block";
##endif##
$skins["2"] = "";
$block["2"] = $container;

$layout["left"] = $block;
$block = array();

$container = array();

$container[] = "recordcontrols_block";

$skins["5"] = "";
$block["5"] = $container;


$container = array();

$container[] = "message_block";

$skins["5"] = "";
$block["5"] = $container;


$container = array();

$container[] = "grid_block";

$skins["4"] = "";
$block["4"] = $container;


$container = array();

$container[] = "pagination_block";

$skins["6"] = "";
$block["6"] = $container;

$layout["center"] = $block;
$block = array();

$container = array();
##if @BUILDER.bCreateLoginPage##
$container[] = "security_block";
##endif##

$container[] = "toplinks_block";

$skins["2"] = "";
$block["2"] = $container;

$layout["right"] = $block;
