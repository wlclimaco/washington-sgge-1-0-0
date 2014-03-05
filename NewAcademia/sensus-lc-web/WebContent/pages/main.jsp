<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><s:message code="flexnet.portal.title"/></title>
		<link rel="stylesheet" href="/slc/styles/preload.css" />
		<link rel="stylesheet" href="/slc/styles/reset-fonts-grids.css" />
		<link rel="stylesheet" href="/slc/thirdparty/jquery/ui/jquery.menu.css" />
		<link rel="stylesheet" href="/slc/thirdparty/jquery/ui/jquery-ui-1.8.20.custom.css" />
		<link rel="stylesheet" href="/slc/styles/validationEngine.jquery.css" />
		<link rel="stylesheet" href="/slc/thirdparty/jquery/styles/jquery.dataTables.custom.css" />
		<link rel="stylesheet" href="/slc/styles/jquery-bubble-popup-v3.css" />
		<link rel="stylesheet" href="/slc/styles/base.css" />
		<link rel="stylesheet" href="/slc/thirdparty/jquery/ui/ui.slider.extras.css" />
		<link rel="stylesheet" href="/slc/styles/chosen.css" />
		<link rel="stylesheet" href="/slc/styles/calendrical.css" />
		<link rel="stylesheet" href="/slc/thirdparty/jquery/ui/jquery.validate.password.css" />
		<link rel="stylesheet" href="/slc/styles/common_grid.css"/>
	    <link rel="stylesheet" href="/slc/styles/slick.pager.css"/>
		<link rel="stylesheet" href="/slc/thirdparty/SlickGrid/slick.grid.css" type="text/css"/>
	    <link rel="stylesheet" href="/slc/thirdparty/SlickGrid/controls/slick.pager.css" type="text/css"/>
	    <link rel="stylesheet" href="/slc/thirdparty/SlickGrid/css/smoothness/jquery-ui-1.8.16.custom.css" type="text/css"/>
	    <link rel="stylesheet" href="/slc/thirdparty/SlickGrid/examples.css" type="text/css"/>
	    <link rel="stylesheet" href="/slc/thirdparty/SlickGrid/controls/slick.columnpicker.css" type="text/css"/>

	   <link href="/slc/thirdparty/New folder/face.css" rel="stylesheet" type="text/css">
      <link href="/slc/thirdparty/New folder/face1.css" rel="stylesheet" type="text/css">
      <link href="/slc/thirdparty/New folder/face2.css" rel="stylesheet" type="text/css">




      <link href="/slc/thirdparty/New folder/face10.css" rel="stylesheet" type="text/css">
		<jsp:include page="user_info.jsp" flush="true"/>
	</head>
	<body>
	<%-- START Main Document --%>

		<div id="preload">
			<div>
				<span><s:message code="commons.pages.loading.application" /></span>
			</div>
		</div>

		<div class="fullscreen"></div>

		<div id="doc1" class="application lighting"></div>
		<div id="loading" style="display: none;">
			<h5><s:message code="widgets.progress.updatingresults" /></h5>
			<div id="progress-bar"></div>
		</div>
		<div id="action-dialog" style="display: none"></div>
		<div id="control-light-dialog" style="display: none"></div>
		<div id="monitor-dialog" style="display: none"></div>
		<div id="action-dialog-lrp" style="display: none"></div>
		<div id="remove-lrp" style="display: none"></div>
		<div id="abort-lrp" style="display: none"></div>

		<script src="thirdparty/jquery/jquery-1.7.2.min.js"></script>
		<script src="thirdparty/jquery/jquery-ui-1.8.20.custom.min.js"></script>
		<script src="scripts/util/namespace_init.js"></script>
		<script src="commons/scripts/util/ajax_load.js"></script>
		<script src="commons/scripts/util/date.prototype.js"></script>
		<script src="scripts/pages/mlc_init.js"></script>
		<script src="thirdparty/jquery/custom/jquery.i18n.properties-min.custom.js"></script>
		<script src="commons/widget-ui/sensus_commons-0.1.js"></script>
		<script src="thirdparty/jquery/jquery.address-1.5.min.js"></script>
		<script src="thirdparty/jquery/custom/jquery.menu.custom.js"></script>
		<script src="thirdparty/globalize/custom/globalize.js"></script>
		<script src="thirdparty/jquery/jquery.validationEngine.min.js"></script>
		<script src="thirdparty/openlayers/OpenLayers.js"></script>
		<script src="commons/widget-ui/sensus_commons-maps-0.1.js"></script>
		<script src="commons/widget-ui/sensus_commons-filters-0.1.js"></script>
		<script src="commons/scripts/util/wd_core_3.0.0.js"></script>

		<script src="thirdparty/jquery/jquery.dataTables.min.js"></script>
		<script src="thirdparty/jquery/custom/jquery.dataTables.custom.js"></script>
		<script src="thirdparty/jquery/jquery.datatable.fnReloadAjax.js"></script>
		<script src="thirdparty/jquery/jquery.datatable.fnStartingRedraw.js"></script>
		<script src="thirdparty/jquery/jquery-bubble-popup-v3.min.js"></script>
		<script src="thirdparty/jquery/jquery.validationEngine-functions.js"></script>
		<script src="thirdparty/jquery/jquery.ez-pinned-footer.min.js"></script>
		<script src="thirdparty/jquery/jquery.sparkline-2.1.2.min.js"></script>
		<script src="thirdparty/jquery/jquery.event.drag-2.2.js"></script>
		<script src="thirdparty/jquery/selectToUISlider.jQuery.min.js"></script>
		<script src="thirdparty/jquery/custom/chosen.jquery.custom.js"></script>
		<script src="thirdparty/jquery/custom/jquery.validate.custom.js"></script>
		<script src="thirdparty/jquery/jquery.validate.password.js"></script>
		<script src="thirdparty/jquery/custom/jquery.calendrical.js"></script>
		<script src="thirdparty/highcharts/highcharts.js"></script>
		<script src="thirdparty/jquery/jquery.cookie.js"></script>
		<script src="thirdparty/jquery/jquery.json-2.3.min.js"></script>
		<script src="thirdparty/jquery/jquery.idletimer.min.js"></script>
		<script src="thirdparty/jquery/jquery.idletimeout.min.js"></script>
		<script src="commons/widget-ui/widget_summary.js"></script>
		<script src="commons/widget-ui/widget_customize.js"></script>
		<script src="commons/widget-ui/widget_module_controller.js"></script>
		<script src="commons/widget-ui/widget_number_format.js"></script>
		<script src="commons/widget-ui/widget_light_control.js"></script>

		<script src="commons/scripts/util/request_model.js"></script>
		<script src="scripts/util/session.js"></script>
		<script src="thirdparty/jquery/custom/jquery.number_format.1.0.custom.js"></script>
		<script src="thirdparty/jquery/custom/jquery.combobox.js"></script>
		<script src="thirdparty/jquery/custom/jquery.checkbox.js"></script>

		<script src="scripts/util/actiondialog.js"></script>
		<script src="scripts/util/exportcsv.js"></script>
		<script src="scripts/util/date_filter.js"></script>
		<script src="scripts/util/combobox.js"></script>
		<script src="scripts/util/page.js"></script>

		 <script src="commons/scripts/util/namespace_init.js"></script>
		 <script src="commons/scripts/util/wd_log.js"></script>
		 <script src="scripts/config/wd_config.js"></script>

		<script src="scripts/util/process_util.js"></script>
		<script src="commons/scripts/model/request_objects.js"></script>
		<script src="commons/scripts/model/domain_objects.js"></script>

		<script src="thirdparty/SlickGrid/lib/jquery.event.drag-2.2.js"></script>

		<script src="thirdparty/SlickGrid/slick.core.js"></script>
		<script src="thirdparty/SlickGrid/slick.formatters.js"></script>
		<script src="thirdparty/SlickGrid/slick.editors.js"></script>
		<script src="thirdparty/SlickGrid/plugins/slick.rowselectionmodel.js"></script>
		<script src="thirdparty/SlickGrid/plugins/slick.cellrangedecorator.js"></script>
		<script src="thirdparty/SlickGrid/plugins/slick.cellrangeselector.js"></script>
		<script src="thirdparty/SlickGrid/plugins/slick.cellselectionmodel.js"></script>
		<script src="thirdparty/SlickGrid/slick.grid.js"></script>
		<script src="thirdparty/SlickGrid/slick.groupitemmetadataprovider.js"></script>
		<script src="thirdparty/SlickGrid/slick.dataview.js"></script>
		<script src="thirdparty/SlickGrid/controls/slick.pager.js"></script>
		<script src="thirdparty/SlickGrid/controls/slick.columnpicker.js"></script>



		<script src="commons/scripts/widget/slick.pager.js"></script>

		<script src="scripts/pages/process/long_running_process_main.js"></script>
		<script src="scripts/pages/process/long_running_process_actions.js"></script>
		<script src="scripts/pages/process/long_running_process_init.js"></script>
		<script src="scripts/util/cSlider_functions.js"></script>

		<script src="commons/scripts/model/user_context.js"></script>
		<script src="commons/scripts/model/request_objects.js"></script>
		<script src="commons/scripts/model/domain_objects.js"></script>
		<script src="commons/scripts/widget/slick.pager.js"></script>

		<script src="../lib/jquery.event.drag-2.2.js"></script>
		<script src="http://malsup.github.com/jquery.form.js"></script>


	</body>
	<%-- END Main Document --%>

</html>