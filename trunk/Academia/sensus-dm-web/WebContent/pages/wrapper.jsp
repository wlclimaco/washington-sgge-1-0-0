<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- START Header 2--%>
<div id="hd" class="yui-g">
		<div class="span6 csspossition">
			<div class="square"><a href="services.html"><img src="thirdparty/jquery/Facebuk/img/icons/branding.png" alt="" /></a></div>
			<h3><a href="services.html">Branding</a></h3>
			<p> Designing graphic guidelines with possible variation on multi-support. </p>
		</div>
	<%-- START Main application menu --%>


	<%-- END Main application menu --%>

	<%-- START Search Box--%>

	<%-- END Search Box--%>

</div>
<%-- END Header 2--%>

<link rel="shortcut icon" href="thirdparty/jquery/Facebuk/img/icons/favicon.ico">
<link rel="apple-touch-icon" href="thirdparty/jquery/Facebuk/img/icons/apple-touch-icon.png">
<!--style sheet-->


<!--[if IE 7]>
  	<link rel="stylesheet" href="css/font-awesome-ie7.css">
  <![endif]-->

<!--jquery libraries / others are at the bottom-->
<script src="thirdparty/jquery/Facebuk/js/modernizr.js" type="text/javascript"></script>
</head>
<body>

<!-- header starts
================================================== -->

<!-- header ends
================================================== -->
<!-- slider starts
================================================== -->

<!-- slider ends
================================================== -->
<!-- content starts
================================================== -->
<section class="content dark container clearfix">
  <div class="services">

  <!-- item 2 -->
    <div class="span1">

			<ul class="settings-nav views-vert"><h4>Menu</h4><div class="side-slide-toggle"><span class="hide-side-slide ui-icon ui-icon-arrowthick-1-w" style="display: block;"></span><span style="display: none;" class="show-side-slide ui-icon ui-icon-arrowthick-1-e"></span></div>
				<li>
					<a href="Serie" class="linkTab">Cadastro Academia</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Comunidades</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Cadastro Dieta</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Fotos</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Cadastro Medidas</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Cadastro Serie</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Cadastro Suplementação</a>
				</li>
				<li>
					<a href="Serie" class="linkTab">Resultados</a>
				</li>
			</ul>

    </div>
    <!-- item 1 -->
    <div class="span7">
      <div class="item">
       <div id="bd" class="content-container">
			<div id="load"><%-- Page Content --%> </div>
			<div id="load"><%-- Page Content --%> </div>
		</div>
      </div>
    </div>
    <!-- End -->




  </div>
  <!-- End Services -->
<section>
<section class="content dark container clearfix">
  <div class="purchase">
    <div class="span9">
      <h2>Contrary to popular belief simply random text.</h2>
      <p>Lorem Ipsum is simply dummy text of the printing industry.</p>
    </div>
    <div class="span3 alpha"> <a href="#" class="button medium color">Purchase Now</a> </div>
  </div>
</section>






<%--                  2 --%>


<div id="action-dialog" style="display: none"></div>
<div id="action-dialog-lrp" style="display: none"></div>
<div id="dialog-monitor-dissmiss" style="display: none"></div>

<%-- System Messaging Bar --%>
<div id="system-messaging" style="display:none;">
	<div id="system-messaging-list">
		<ul>
			<li class="message-title">
				<spring:message code="commons.pages.message" />
			</li>
			<li id="rni-offline" class="system-message-label error rni-link" style="display:none;">
				<a rel="black" class="rounded bubble" value="<spring:message code="commons.pages.rniofflinemsg" />">
					<spring:message code="commons.pages.rnioffline"></spring:message> </a>
			</li>
			<li id="request-processing" class="system-message-label" style="display:none;">
				<a href="#" class="processing rounded">
					<spring:message code="commons.pages.recentRequests" /> <span id="long-running-process-size-p" class="count rounded hide"></span>
				</a>
			</li>
			<li id="request-complete" class="system-message-label" style="display:none;">
				<a href="#" class="rounded">
					<spring:message code="commons.pages.recentRequests" /> <span id="long-running-process-size-c" class="count rounded hide"></span>
				</a>
			</li>
			<li id="processing-size" class="hide"></li>
		</ul>
	</div>
</div>
<%-- END Page Content --%>

<div id="customize-filter" class="hide"></div>
<%-- END Main Document --%>

<script type="text/javascript">
head.js("commons/scripts/util/prototype.js",
	"commons/scripts/util/request_model.js",
	"commons/scripts/util/modules_device_actions.js",
	"thirdparty/jquery/custom/jquery.i18n.properties-min.custom.js",
	"thirdparty/jquery/jquery.json-2.3.min.js",
	"thirdparty/jquery/jquery.maskedinput-1.3.min.js",
	"commons/widget-ui/widget_ajax_validator.js",
	"scripts/util/action_request_util.js",
	"thirdparty/openlayers/OpenLayers.js",
	"commons/scripts/util/sensus_commons_1.0.js",
	"scripts/util/module_operation_util.js",
	"commons/widget-ui/widget_customize.js",
	"commons/widget-ui/widget_filter_1.0.js",
	"commons/widget-ui/widget_summary.js",
	"commons/widget-ui/widget_number_format.js",
	"commons/widget-ui/widget_date_format.js",
	"commons/widget-ui/widget_temperature.js",
	"thirdparty/jquery/jquery.bubblepopup.v2.3.1.min.js",
	"thirdparty/jquery/custom/jquery.menu.custom.js",
	"thirdparty/jquery/jquery.validationEngine.js",
	"thirdparty/jquery/jquery.ez-pinned-footer.js",
	"thirdparty/jquery/jquery.idletimer.js",
	"thirdparty/jquery/jquery.idletimeout.js",
	"thirdparty/jquery/custom/jquery.combobox.js",
	"scripts/util/page_loader.js",
	"scripts/util/exportcsv.js",
	"scripts/util/generate_link_action.js",
	"scripts/util/filter_util.js",
	"scripts/util/process_util.js",
	"thirdparty/jquery/jquery.calendrical.js",
	"thirdparty/jquery/jquery.threedots.js",
	"thirdparty/jquery/custom/chosen.jquery.custom.js",
	"scripts/util/global_actions.js",
	"scripts/util/session.js",
	"scripts/util/actiondialog.js",
	"thirdparty/jquery/custom/jquery.number_format.1.0.custom.js",
	"thirdparty/jquery/jquery.dataTables.min.js",
	"thirdparty/jquery/jquery.datatable.fnStartingRedraw.js",
	"commons/scripts/util/sensus_commons_maps_1.0.js",
	"thirdparty/jquery/custom/jquery.dataTables.custom.js",
	"thirdparty/jquery/custom/jquery.validationEngine.custom.js",
	"scripts/pages/process/long_running_process_main.js",
	"scripts/pages/process/long_running_process_actions.js",
	"scripts/pages/process/long_running_process_init.js",
	"scripts/util/process_actions_util.js",
	"thirdparty/jquery/SlickGrid/lib/firebugx.js",
	"thirdparty/jquery/SlickGrid/lib/jquery.event.drag-2.2.js",
	"thirdparty/jquery/SlickGrid/slick.core.js",
	"thirdparty/jquery/SlickGrid/plugins/slick.cellrangeselector.js",
	"thirdparty/jquery/SlickGrid/plugins/slick.cellselectionmodel.js",
	"thirdparty/jquery/SlickGrid/slick.formatters.js",
	"thirdparty/jquery/SlickGrid/slick.editors.js",
	"thirdparty/jquery/SlickGrid/slick.grid.js",
	"thirdparty/jquery/SlickGrid/examples/slick.compositeeditor.js",
	"thirdparty/jquery/SlickGrid/plugins/slick.rowselectionmodel.js",
	"thirdparty/jquery/SlickGrid/slick.grid.js",
	"thirdparty/jquery/SlickGrid/slick.dataview.js",
	"thirdparty/jquery/SlickGrid/controls/slick.pager.js",
	"thirdparty/jquery/SlickGrid/controls/slick.columnpicker.js",
	"thirdparty/jquery/SlickGrid/plugins/slick.checkboxselectcolumn.js",
	"scripts/pages/wrapper_init.js");
</script>