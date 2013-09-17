<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- START Header 2--%>
<div id="hd" class="yui-g">
	<%-- START Main application menu --%>

	<div class="yui-u first">
		<div id="sensus-menu">
			<div class="bd">
				<ul class="first-of-type">
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-dashboard" href="dashboard"
								title="<spring:message code="commons.pages.dashboard"/>"><spring:message code="commons.pages.dashboard" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-smartpoint" href="#"
								title="<spring:message code="commons.pages.smartPoints"/>"><spring:message code="commons.pages.smartPoints" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-group" href="group"
								title="<spring:message code="commons.pages.Groups"/>"><spring:message code="commons.pages.Groups" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')	and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-systemintelligence" href="process/today"
								title="<spring:message code="commons.pages.systemintelligence"/>"><spring:message code="commons.pages.systemintelligence" />
							</a>
						</li>
					</sec:authorize>
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
					<a tabindex="1" href="#labelOptions" class="fg-button fg-button-icon-right ui-widget ui-state-default" id="search-label">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<input id="search-type" value="DEVICE_ID" type="hidden">
						<span><spring:message code="commons.pages.deviceId"/></span>
					</a>
					<div id="labelOptions" class="hidden">
						<ul>
							<li class="hide">
								<a href="#" class="action-option" id="device-id">
									<input value="DEVICE_ID" type="hidden">
									<span><spring:message code="commons.pages.deviceId"/></span>
								</a>
							</li>
							<li>
								<a href="#" class="action-option" id="network-address">
									<input value="NETWORK_ADDRESS" type="hidden" />
									<span><spring:message code="commons.pages.networkAddress"/></span>
								</a>
							</li>
							<li>
								<a href="#" class="action-option" id="premise-id">
									<input value="PREMISE_ID" type="hidden" />
									<span><spring:message code="commons.pages.premiseId"/></span>
								</a>
							</li>
						</ul>
					</div>
				</div>
			<input type="text" id="search-text" value="" />
			</div>
			<div id="search-submit-container" class="yui-u">
				<a href="#" id="search-submit" class="alist button">
					<spring:message code="commons.pages.search"/>
				</a>
				<a href="savedSearch" id="saved" class="white nodeco alist">
					<spring:message code="commons.pages.saved"/>
				</a>
			</div>
		</form>
	</div>
	<%-- END Search Box--%>

</div>
<%-- END Header 2--%>

<%-- START Page Content --%>
<div id="bd" class="content-container">
	<div id="load"><%-- Page Content --%> </div>
</div>

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
	"scripts/pages/wrapper_init.js");
</script>