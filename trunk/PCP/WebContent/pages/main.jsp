<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><s:message code="flexnet.portal.title"/></title>
		<link rel="stylesheet" href="/Dicionario/styles/reset-fonts-grids.css"></link>
		<link rel="stylesheet" href="/Dicionario/thirdparty/jquery/ui/jquery.menu.css"></link>
		<link rel="stylesheet" href="/Dicionario/thirdparty/jquery/ui/jquery-ui-1.8.12.custom.css"></link>
		<link rel="stylesheet" href="/Dicionario/styles/validationEngine.jquery.css"></link>
		<link rel="stylesheet" href="/Dicionario/thirdparty/jquery/styles/jquery.dataTables.custom.css"></link>
		<link rel="stylesheet" href="/Dicionario/styles/jquery-bubble-popup-v3.css"></link>
		<link rel="stylesheet" href="/Dicionario/styles/base.css"></link>
		<link rel="stylesheet" href="/Dicionario/thirdparty/jquery/ui/ui.slider.extras.css"></link>
		<link rel="stylesheet" href="/Dicionario/styles/chosen.css"></link>
		<link rel="stylesheet" href="/Dicionario/styles/calendrical.css"></link>
		<link rel="stylesheet" href="/Dicionario/thirdparty/jquery/ui/jquery.validate.password.css"></link>
	</head>
	<body>
	<jsp:include page="user_info.jsp" flush="true"/>
	<%-- START Main Document --%>
	<!--
	<div class="fullscreen"></div>
	 -->
	<div id="doc1" class="application lighting"><%-- START Header 1 --%>
		<div id="hd-user" class="yui-g"><%-- Breadcrumbs (currently hard-coded) --%>
			<div class="logo yui-u first" style="width: 49%">
				<h1></h1>
				<h2>&raquo;&nbsp;<s:message code="breadcrumbs.label.mlc" /></h2>
			</div>
			<%-- Login Menu --%>
			<div class="yui-g user-settings">
				<ul class="link-list">
					<li><strong><s:message code="header.label.welcome" /> <a id="user-name" class="alist" href="profile"></a></strong></li>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<li><a id="systemsettings" class="alist" href="settings/"	title="<s:message code="header.label.settings"/>"><s:message code="header.label.settings" /></a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					<li><a id="users-list" class="alist" href="user" title="<s:message code="header.label.users"/>"><s:message code="header.label.users" /></a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
					<li><a id="eventhistory" class="alist" href="process"	title="<s:message code="header.label.eventhistory"/>"><s:message code="header.label.eventhistory" /></a></li>
					</sec:authorize>
					<li class="last">
						<a id="header-logout" href="j_spring_security_logout"><s:message code="header.label.logout" /></a>
					</li>
				</ul>
			</div>
		</div>
		<%-- END Header 1 --%>
		<%-- START Header 2--%>
		<div id="hd" class="yui-gc">
		<%-- START Main application menu --%>
			<div class="yui-u first">
				<div id="sensus-menu">
					<div class="bd">
						<ul class="first-of-type">
							<li><a id="menu-dashboard" class="alist" href="dashboard" title="<s:message code="menu.main.dashboard"/>"><s:message code="menu.main.dashboard" /></a></li>
							<li><a id="menu-smartpoints" class="alist" href="light" title="<s:message code="menu.main.smartpoint"/>"><s:message code="menu.main.smartpoint" /></a></li>
							<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
							<li><a id="menu-groups" class="alist" href="group"	title="<s:message code="menu.main.group"/>"><s:message code="menu.main.group" /></a></li>
							<li><a id="menu-schedules" class="alist" href="schedule" title="<s:message code="menu.main.schedule"/>"><s:message code="menu.main.schedule"/></a></li>
							</sec:authorize>
							<li><a id="menu-analytics" class="alist" href="analytics/?tb=&ty=1&g=&di=&de=&dt=&dv=1"	title="<s:message code="menu.main.analytics"/>"><s:message code="menu.main.analytics" /></a></li>
						</ul>
					</div>
				</div>
			</div>
			<%-- END Main application menu --%>
			<%-- START Search Box--%>
			<div class="yui-gc">
				<form id="search-form-container" class="rounded">
					<div class="search-input-container yui-u first">
						<div id="search-label-container">
							<a tabindex="1" href="#label-options" class="fg-button fg-button-icon-right ui-widget ui-state-default" id="search-label">
								<span class="ui-icon ui-icon-triangle-1-s"></span><input id="search-type" value="12" type="hidden"><s:message code="search.label.poleid"/>
							</a>
							<div id="label-options" class="hidden">
								<ul>
									<li class="hide">
										<a href="#" class="action-option" id="search-pole-id"><input value="12" type="hidden"><s:message code="search.label.poleid"/></a>
									</li>
									<li>
										<a href="#" class="action-option" id="flexNetID"><input value="36" type="hidden"><s:message code="search.label.flexnetid"/></a>
									</li>
								</ul>
							</div>
						</div>
						<input type="text" id="search-text" value="" class="validate[required, custom[specialCharsCustomSearch]]"/>
					</div>
					<div id="search-submit-container" class="yui-u">
						<a href="#" id="search-submit" class="button"><s:message code="search.label.submit"/></a>
						<!-- <a href="search/ajax.advanced.action" id="advanced" class="white nodeco alist"><s:message code="search.label.advanced"/></a>&nbsp;| -->
						<a href="search" id="saved" class="white nodeco alist"><s:message code="search.label.saved"/></a>
					</div>
				</form>
			</div>
			<%-- END Search Box--%>
		</div>
		<%-- END Header 2--%>
		<%-- START Page Content --%>
		<div id="bd" class="content-container">

			<div id="load"><%-- Page Content --%> <decorator:body /></div>
		</div>
		<%-- Detail Map and Action Dialog Placeholders --%>
		<%-- Progress Bar --%>
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

		<%-- System Messaging Bar --%>
		<div id="system-messaging"></div>
		<%-- END Page Content --%>

		<%-- START Footer --%>
		<div id="ft" class="yui-g">
			<div class="yui-u first"><%-- Footer address section --%>
				<div class="vcard">
					<div class="fn org">&copy;<span id="current-year"></span>
					<s:message code="company.org" /></div>
					<div class="adr">
						<p class="version">Dicionario v<span id="version-number"></span> Build <span id="build-number"></span></p>
					</div>
				</div>
			</div>
			<%-- Footer Links --%>
			<div class="yui-u footer-links">

			</div>
			<%-- END Footer --%>
		</div>


		<%-- END Main Document --%>


	</body>

		<script src="scripts/util/namespace_init.js"></script>
		<script src="thirdparty/jquery/jquery-1.7.2.min.js"></script>
		<script src="commons/lib/ajax_load.js"></script>
		<script src="thirdparty/jquery/jquery.ui.core.min.js"></script>
		<script src="thirdparty/jquery/jquery.ui.widget.min.js"></script>
		<script src="thirdparty/jquery/jquery.ui.dialog.min.js"></script>
		<script src="thirdparty/jquery/jquery.ui.position.min.js"></script>
		<script src="thirdparty/jquery/jquery.ui.progressbar.min.js"></script>
		<script src="scripts/util/block_ui_init.js"></script>
		<script src="thirdparty/jquery/jquery-ui-1.8.20.custom.min.js"></script>
		<script src="thirdparty/jquery/custom/jquery.i18n.properties-min.custom.js"></script>
		<script src="thirdparty/jquery/jquery.address-1.4.min.js"></script>
		<script src="thirdparty/jquery/custom/jquery.menu.custom.js"></script>
		<script src="thirdparty/jquery/custom/globalize.js"></script>
		<script src="thirdparty/jquery/jquery.validationEngine.min.js"></script>
		<script src="thirdparty/openlayers/OpenLayers.js"></script>
		<script src="scripts/pages/mlc_init.js"></script>
		<script src="commons/widget-ui/SensusCommons-0.1.js"></script>
		<script src="commons/widget-ui/SensusCommons-maps-0.1.js"></script>
		<script src="commons/widget-ui/SensusCommons-filters-0.1.js"></script>

		<script src="thirdparty/jquery/jquery.dataTables.min.js"></script>
		<script src="thirdparty/jquery/custom/jquery.dataTables.custom.js"></script>
		<script src="thirdparty/jquery/jquery.datatable.fnReloadAjax.js"></script>
		<script src="thirdparty/jquery/jquery.datatable.fnStartingRedraw.js"></script>

		<script src="thirdparty/jquery/jquery-bubble-popup-v3.min.js"></script>

		<script src="thirdparty/jquery/jquery.validationEngine-functions.js"></script>
		<script src="thirdparty/jquery/jquery.ez-pinned-footer.min.js"></script>
		<script src="thirdparty/jquery/jquery.sparkline.min.js"></script>
		<script src="thirdparty/jquery/selectToUISlider.jQuery.min.js"></script>
		<script src="thirdparty/jquery/custom/jquery.truncatable.custom.js"></script>
		<script src="thirdparty/jquery/custom/chosen.jquery.custom.js"></script>
		<script src="thirdparty/jquery/custom/jquery.validate.custom.js"></script>
		<script src="thirdparty/jquery/jquery.validate.password.js"></script>
		<script src="thirdparty/jquery/custom/jquery.calendrical.js"></script>
		<script src="thirdparty/jquery/highcharts.js"></script>
		<script src="thirdparty/jquery/jquery.cookie.js"></script>
		<script src="thirdparty/jquery/jquery.json-2.3.min.js"></script>
		<script src="thirdparty/jquery/jquery.idletimer.min.js"></script>
		<script src="thirdparty/jquery/jquery.idletimeout.min.js"></script>
		<script src="commons/widget-ui/widget_summary.js"></script>
		<script src="commons/widget-ui/widget_customize.js"></script>
		<script src="commons/widget-ui/widget_module_controller.js"></script>
		<script src="commons/widget-ui/widget_number_format.js"></script>
		<script src="commons/widget-ui/widget_light_control.js"></script>

		<script src="commons/widget-ui/widget_ajax_validator.js"></script>

		<script src="scripts/util/request_model.js"></script>
		<script src="scripts/util/session.js"></script>
		<script src="thirdparty/jquery/custom/jquery.number_format.1.0.custom.js"></script>
		<script src="thirdparty/jquery/custom/jquery.combobox.js"></script>
		<script src="thirdparty/jquery/custom/jquery.combofilter.js"></script>
		<script src="thirdparty/jquery/custom/jquery.checkbox.js"></script>

		<script src="scripts/util/ajax_action.js"></script>
		<script src="scripts/util/actiondialog.js"></script>
		<script src="scripts/util/exportcsv.js"></script>
		<script src="scripts/util/date_filter.js"></script>
		<script src="scripts/util/combobox.js"></script>
		<script src="scripts/util/page.js"></script>

		<script src="scripts/util/process_util.js"></script>

		<script src="scripts/pages/long_running_process_main.js"></script>
		<script src="scripts/pages/long_running_process_actions.js"></script>
		<script src="scripts/pages/long_running_process_init.js"></script>

		<script src="scripts/pages/decorator_mlcmain.js"></script>



</html>