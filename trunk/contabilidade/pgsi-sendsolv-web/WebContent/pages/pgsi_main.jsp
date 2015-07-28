<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

	<link href="styles/bootstrap.css" rel="stylesheet">

	<link href="styles/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="styles//metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="styles/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="styles/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="styles/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="styles/font-awesome.min.css" rel="stylesheet" type="text/css">

	<link rel="stylesheet" href="styles/preload.css" />

	<link href="styles/style.css" rel="stylesheet">

	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"rel="stylesheet">

<!--<link rel="stylesheet" href="styles/preload.css" />-->
<!-- <link rel="stylesheet" href="styles/pgsi-common.css" /> -->
<!--<link href="styles/bootstrap.min.css" rel="stylesheet">-->
<!--<link href="styles/bootstrap-responsive.min.css" rel="stylesheet">-->
<!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"rel="stylesheet">-->
<!--<link href="styles/font-awesome.css" rel="stylesheet">-->
<!--<link href="styles/style.css" rel="stylesheet">-->
<!--<link href="styles/dataTables.bootstrap.css" rel="stylesheet">-->
<!--<link href="styles/dataTables.responsive.css" rel="stylesheet">-->
<!--<link rel="stylesheet" href="styles/jquery-ui.min.css" />-->
<!-- <link rel="stylesheet" href="thirdparty/jquery/styles/jquery.dataTables.custom.css"> -->

<!--	<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css" />
	<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css.map" />
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />

	<link rel="stylesheet" href="styles/preload.css" />
	<link rel="stylesheet" href="styles/actions.css" />
	<link rel="stylesheet" href="styles/fonts.css" />
	<link rel="stylesheet" href="styles/jquery-ui.min.css" />
	<link rel="stylesheet" href="styles/navigation.css" />
	<link rel="stylesheet" href="styles/pgsi-common.css" />
	<link rel="stylesheet" href="thirdparty/jquery/styles/jquery.dataTables.custom.css"> -->
</head>
<body>
<div id="preload">
	<div>
		<span></span>
	</div>
</div>

<div id="idletimeout" style="display:none;">
	<a href="#"></a>.
</div>


<div class="fullscreen"></div>
<div id="loading" style="display: none;">
	<h5></h5>
	<div id="progress-bar"></div>
</div>
<div id="action-dialog" style="display: none"></div>
<div id="action-dialog-Error" style="display: none"></div>
<script type="text/javascript" src="thirdparty/head.min.js"></script>
<script>
head.load("thirdparty/jquery/jquery-1.11.1.min.js",
					"thirdparty/jquery/jquery-ui-1.11.0.min.js",
					"scripts/util/namespace_init.js",
					"thirdparty/jquery/custom/jquery.i18n.properties.custom.js",
					"thirdparty/jquery/custom/jquery.ui.datepicker-locale.js",
					"thirdparty/jquery/jquery.address-1.6.min.js",
					"thirdparty/jquery/jquery.json-2.5.1.min.js",
					"thirdparty/jquery/jquery.validate.min.js",
					"thirdparty/jquery/jquery.idletimeout.min.js",
					"thirdparty/jquery/jquery.idletimer.min.js",
					"thirdparty/jquery/jquery.inputmask.js",
					"thirdparty/jquery/jquery.inputmask.date.extensions.js",
					"thirdparty/jquery/jquery.inputmask.extensions.js",
					"thirdparty/jquery/jquery.inputmask.numeric.extensions.js",
					"thirdparty/jquery/jquery.inputmask.regex.extensions.js",
					"thirdparty/jquery/jstorage.min.js",
					"commons/scripts/pgsi_commons_1.0.js",
					"thirdparty/timezone-js/date.custom.js",
					"thirdparty/jquery/custom/jquery.validate.custom.js",
					"thirdparty/jquery/jquery.maskedinput-1.3.1.min.js",
					"thirdparty/jquery/jquery.ThreeDots.min.js",
					"commons/scripts/model/domain_object.js",
					"commons/scripts/model/request_model.js",
					"scripts/util/actiondialog.js",
					"scripts/util/business_main.js",
					"scripts/util/business_actions.js",
				    "thirdparty/jquery/custom/jquery.dataTables.custom.js",
				    "thirdparty/jquery/jquery.datatable.fnReloadAjax.js",
				    "thirdparty/jquery/jquery.datatable.fnStartingRedraw.js",
				    "commons/scripts/pgsi_commons_filter_1.0.js",
				    "commons/widget-ui/widget_customize.js",
					"commons/widget-ui/widget_filter_1.0.js",
					"commons/widget-ui/widget_highcharts_1.0.js",
					"commons/widget-ui/widget_summary.js",
					"scripts/util/page.js",
				    "thirdparty/jquery/custom/jquery.combobox.js",
				    "bootstrap/js/bootstrap.js",
				    "thirdparty/knockout/knockout-3.3.0.js",
				    "scripts/util/checkbox.js",
				    "scripts/util/combobox.js",
				    "scripts/util/filter_util.js",
					"scripts/excanvas.min.js",
					"scripts/chart.min.js",
					"scripts/base.js",
				    "scripts/pages/pgsi_main.js");
</script>

<jsp:include page="../scripts/pages/pgsi_init.js.jsp" flush="true" />

</body>
</html>