<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<link rel="stylesheet" href="/slc/styles/light.css"></link>
<%-- Page Navigation: SmartPoint Tabs --%>
<div class="yui-ge point-type-navigation header rounded-top-corners">
	<div class="yui-u first">
	   <ul class="tabs tab-saved hide" id="tabs-list">
		   <li><a class="active gradient-vert" href="#"><s:message code="search.tabs.search" /><span class="save"><s:message code="table.result.save"/></span></a></li>
		   <%-- <li><a id="ajax-button" href="search/ajax.advanced.action" class="gradient-vert alist"><s:message code="search.tabs.advanced" /></a></li> --%>
	        <li><a id="ajax-button" href="search" class="gradient-vert alist"><s:message code="search.tabs.saved" /></a></li>
	    </ul>
	</div>
</div>
<!-- Messaging -->
<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove"><s:message code="message.action.close" /></a></div>

<%-- START Main Page Content --%>
<div class="content-inner">

<%-- START Content Container --%>
<div class="yui-t2"><%-- START Filter and Action Container --%>
<div class="yui-b"><%-- Filter Title --%>
<div id="w-filters"></div>

</div>
<%-- END Filter and Action Container --%> <%-- START Main Section --%>
<div id="yui-main" class="">
<div class="yui-b"><%-- START Tools Bar --%>
	<div class="yui-gc tools"><%-- START Action Container --%>
		<div id="actions" class="actions yui-u first">
		<div class="yui-pad"><%-- Action Link --%>
		<!-- Turn lights ON/OFF -->
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
			<a tabindex="0" href="#" class="fg-button ui-widget ui-state-default ui-corner-all" title="<s:message code="smartpoint.actions.switchDim"/>" id="lights-on-off">
				<s:message code="smartpoint.actions.switchDim" />
			</a>
		<a tabindex="0" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
			<span class="ui-icon ui-icon-triangle-1-s"></span><s:message code="smartpoint.actions.actions" />
		</a> <%-- Action Droplist --%>
		</sec:authorize>
		<div id="actions-options" class="hidden">
			<ul>
				<%-- Menu Manage Groups --%>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
				<li>
					<a href="#" class="action-option" id="header-tag"><s:message code="smartpoint.actions.group" /></a>
					<ul>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
						<li>
							<a href="#" id="addToGroup" title="<s:message code='smartpoint.actions.addtogroup'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.addtogroup" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<li>
							<a href="#" id="removeFromGroup" title="<s:message code='smartpoint.actions.removegroup'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.removegroup" />
							</a>
						</li>
					</sec:authorize>
					</ul>
				</li>
				</sec:authorize>
				<%-- Menu Manage Lights --%>
				<li>
					<a href="#" class="action-option" id="header-tag"><s:message code="smartpoint.actions.manage" /></a>
					<ul>
						<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Billing Manager')">
							<li>
								<a href="#" id="clearAlerts" title="<s:message code='smartpoint.actions.clearAlerts'/>" onclick="javascript:return false;">
									<s:message code="smartpoint.actions.clearAlerts" />
								</a>
							</li>
						<li>
							<a href="#" id="clearManualOverride" title="<s:message code='smartpoint.actions.clearManual'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.clearManual" />
							</a>
						</li>
						</sec:authorize>
						<li>
							<a href="#" id="resetValuesLightList" title="<s:message code='smartpoint.actions.reset'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.reset" />
							</a>
						</li>
						<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
							<a href="#" class="action-option" id="header-tag"><s:message code="smartpoint.actions.lock" /></a>
							<ul>
								<li>
									<a href="#" id="applyProtected" title="<s:message code='smartpoint.actions.lock'/>" onclick="javascript:return false;">
										<s:message code="smartpoint.actions.lock" />
									</a>
								</li>
								<li>
									<a href="#" id="applyListen" title="<s:message code='smartpoint.actions.unlock'/>" onclick="javascript:return false;">
										<s:message code="smartpoint.actions.unlock" />
									</a>
								</li>
							</ul>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
						<a href="#" class="action-option" id="header-tag"><s:message code="smartpoint.actions.getDataLight" /></a>
						<ul>
							<li>
								<a href="#" id="updateStatus" title="<s:message code='smartpoint.actions.getDataLight'/>" onclick="javascript:return false;">
									<s:message code='smartpointdetail.page.alldata' />
								</a>
							</li>
							<li>
								<a href="#" id="updateReadings" title="<s:message code='smartpointdetail.page.readingsonly'/>" onclick="javascript:return false;">
									<s:message code='smartpointdetail.page.readingsonly' />
								</a>
							</li>
						</ul>
						</sec:authorize>
					</ul>
				</li>
				<%-- Menu Manage Tags --%>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Billing Manager')">
					<li>
						<a href="#" class="action-option" id="header-tag"><s:message code="smartpoint.actions.tag" /></a>
						<ul>
							<li>
								<a href="#" id="addTag" title="<s:message code='smartpoint.actions.addtag'/>" onclick="javascript:return false;">
									<s:message code="smartpoint.actions.addtag" />
								</a>
							</li>
							<li>
								<a href="#" id="removeTag" title="<s:message code='smartpoint.actions.removetag'/>" onclick="javascript:return false;">
									<s:message code="smartpoint.actions.removetag" />
								</a>
							</li>
						</ul>
					</li>
				</sec:authorize>
				<%-- Menu Manage Schedule --%>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
				<li>
					<a href="#" class="action-option" id="header-tag"><s:message code="smartpoint.actions.schedule" /></a>
					<ul>
						<li>
							<a href="#" id="applyEventSchedule" title="<s:message code='smartpoint.actions.applyeventschedule'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.applyeventschedule" />
							</a>
						</li>
						<li>
							<a href="#" id="applyOffsetSchedule" title="<s:message code='smartpoint.actions.applyoffsetchedule'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.applyoffsetchedule" />
							</a>
						</li>
						<li>
							<a href="#" id="resetEventSchedule" title="<s:message code='smartpoint.actions.removeeventschedule'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.removeeventschedule" />
							</a>
						</li>
						<li>
							<a href="#" id="resetOffsetSchedule" title="<s:message code='smartpoint.actions.removeoffsetschedule'/>" onclick="javascript:return false;">
								<s:message code="smartpoint.actions.removeoffsetschedule" />
							</a>
						</li>
					</ul>
				</li>
				</sec:authorize>
			</ul>
		</div>

		<%-- Selected SmartPoints Information --%>
		<span class='message rounded'><span	id="checked-count" class="checked-count">0</span>&nbsp; <s:message code="table.filter.of" /> <span id="general-total">0</span> <s:message code="smartpoint.table.checkedcount" /></span>

			<div class="result-count">
				<p class="info rounded">
					<s:message code="table.filter.select"/>
					<a id="lights-select-all" class="select-all" href="#"><s:message code="table.filter.all"/></a><span id="comma">, </span>
					<a id="lights-maps-all" class="maps-all hide" href="#"><s:message code="table.filter.all"/></a>
					<a id="lights-select-page" class="select-page" href="#"><s:message code="table.filter.page"/></a>
					<s:message code="table.filter.or"/>
					<a id="lights-select-none" class="select-none" href="#"><s:message code="table.filter.none"/></a>
					<a id="lights-maps-none" class="maps-none hide" href="#"><s:message code="table.filter.none"/></a>
				</p>
			</div>

		</div>
		</div>

			<%-- END Action Container --%> <%-- Map/List Toggle --%>
			<div class="yui-u">
			<div class="icon-container">
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
					<div class="export-select">
						 <ul class="link-list">
							 <li class="last export-type"><small><strong><s:message code="smartpont.page.export" /></strong> <a href="" id="csv" class="csv"><s:message code="smartpont.page.csv" /></a></small></li>
						 </ul>
					 </div>
				</sec:authorize>
				<span id="toolbar" class="i-widget-header ui-corner-all">
					<span id="views">
						<input type="radio" id="list_toggle" name="view_toggle" class="list" value="list" />
						<label for="list_toggle"><s:message code="smartpoint.filter.list" /></label>
						<input type="radio" id="map_toggle"	name="view_toggle" class="map" value="map" />
						<label for="map_toggle"><s:message code="smartpoint.filter.map" /></label>
					</span>
				</span>
			</div>
		</div>
	</div>
<%-- END Tools Bar --%> <%-- START Filter Bar --%>
<div class="filter-results-container yui-ge">

	<div class="yui-u first">
		<div class="active-filters-list">
			<ul class="filter-container">
				<li id='tag-clear'>
					<a href="#" class="clear"><s:message code="smartpoint.filter.reset" /></a>
				</li>
			</ul>
		</div>
	</div>
	<div class="results yui-u">
		<span id="total-records"></span>
		<span id="label-total-records"><s:message code="table.result.matches"/></span>
		<span id="lights-save" class="save"><s:message code="table.result.save"/></span>
	</div>

</div>



<%-- END Filter Bar --%>
<div id="box-list">
	<%-- Overview Map Container --%>
	<div id="map-list" class="map">
		<div class="drawing-tools yui-g">
			<div class="yui-u first">
				<ul id="panel" class="">
					<li><button id="draw-pan" class="ui-state-default ui-state-focus ui-corner-all"><span class="icon-small icn-hand-a"></span></button></li>
					<li><button id="draw-polygon" value="polygon" class="ui-state-default ui-corner-all action-link"><span class="icon-small icn-poly-a"></span></button></li>
					<li><button id="drag-location" class="ui-state-default ui-corner-all action-link"><span class="icon-small icn-map-edit"></span></button></li>
				</ul>
				<div class="view-port-description"><span id="map-view-records"></span> <s:message code="table.filter.of"/> <span id="map-total-records"></span> <s:message code="smartpoint.table.header.mapVisible"/></div>
			</div>
			<div class="filter-results-container yui-g"><ul id="polygon-filters" class="filter-container"></ul></div>
		</div>
		<div id="map"></div>
	</div>
	<%-- SmartPoint Table --%>
	<div id="table-list">
		<%-- Blank Slate --%>
		<div id="blankslate" class="blankslate" style="display: none;">
			<h5><s:message code="widgets.blankslate.noresults" /></h5>
			<p><s:message code="widgets.blankslate.detail" /></p>
		</div>
		<table id="smartpoint-table" class="list table-selection">
			<thead>
				<tr>

				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

	</div>
</div>

<div id="dialog-map"></div>
</div>
</div>
<%-- END Main Section --%>
</div>
<%-- END Content Container --%>
</div>
<%-- END Main Page Content --%>
<%@ include file="../../scripts/pages/light/light_main.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_actions.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_init.js.jsp" %>