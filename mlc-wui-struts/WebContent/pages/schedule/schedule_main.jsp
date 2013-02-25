<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
<jsp:include page="../../scripts/pages/schedule_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/schedule_actions.js.jsp" flush="true"/>	
<%-- START Main Page Content --%>
<div class="content-inner"><%-- Messaging --%>

<%-- START Main Content Container --%>
<div id="yui-main" class="invisible"><%-- Header --%>
<div class="content-header">
<h1><s:text name="schedule.page.header" /></h1>
<p class="description"><s:text name="schedule.page.intro" /></p>
</div>

<%-- START Schedule List Section --%>
<div id="schedule-list"><%-- START Tools Section --%>
<div class="yui-ge tools-pages tools"><%-- Add Action --%>
<div id="actions" class="yui-u add-action first actions">
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		<a tabindex="0" href="schedule/ajax.createOffsetSchedule.action" id="ajax-button" class="fg-button ui-widget ui-state-default ui-corner-all group-create alist">
			<s:text name="schedule.actions.createOffset" />
		</a>
		<a tabindex="0" href="schedule/ajax.createEventSchedule.action" id="ajax-button" class="fg-button ui-widget ui-state-default ui-corner-all group-create alist">
			<s:text name="schedule.actions.createEvent" />
		</a>
	</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_Role.Admin">
		<a tabindex="0" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
			<span class="ui-icon ui-icon-triangle-1-s"></span>
			<s:text name="smartpoint.actions.actions" />
		</a>
		<%-- Action Droplist --%>
		<div id="actions-options" class="hidden">
			<ul>
				<s:iterator value="actionList">
					<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
						onclick="javascript:return false;">
							<s:property value="%{value}" />
						</s:a>
					</li>
				</s:iterator>
			</ul>
		</div>
	
	    <%-- Selected Schedule Information --%> 
	    <span class='message rounded'>&nbsp;<span id="checked-count" class="checked-count">0</span>&nbsp;<span></span>&nbsp;<s:text name="schedule.table.checkedcount" /> </span>
	</sec:authorize>
</div>
	

	
<%-- Export Button --%>
<div class="yui-u">
<div class="icon-container icon-small"></div>
</div>
</div>

<%-- START Filter Bar --%>
<div class="filter-results-container yui-gf">
	<div class="result-count yui-u first">
		<p class="info rounded"><s:text name="table.filter.select"/> <a id="schedule-select-all" class="select-all" href=""><s:text name="table.filter.all"/></a>, <a id="schedule-select-page" class="select-page" href=""><s:text name="table.filter.page"/></a> <s:text name="table.filter.or"/> <a id="schedule-select-none" class="select-none" href=""><s:text name="table.filter.none"/></a></p>
	</div>
</div>
<%-- END Filter Bar --%>

<%-- Blank Slate --%>
<div id="blankslate" class="blankslate" style="display: none;">
<h5><s:text name="widgets.blankslate.noresults" /></h5>
<p><s:text name="widgets.blankslate.detail" /></p>
</div>
<%-- END Tools Section --%> <%-- Schedule Table --%>
<table id="schedule-table" class="list table-selection">
	<thead>
		<tr>
			<th class="hide"><span><s:text name="schedule.table.header.id" /></span></th>
			<th><span id="name"><s:text name="schedule.table.header.name" /></span></th>
			<th><span><s:text name="schedule.table.header.description" /></span></th>
			<th><span id="schedule_type"><s:text name="schedule.table.header.type" /></span></th>
			<th><span id="smartpoint_count"><s:text name="schedule.table.header.smartpoints" /></span></th>
			<th><span id="create_date"><s:text name="schedule.table.header.added" /></span></th>
			<th class="hide">&nbsp;</th>
			<th class="hide">&nbsp;</th>		
			<th><span id="col-delete">&nbsp;</span></th>	
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
<%-- END Schedule List Section --%></div>
<%-- END Main Content Container --%></div>
<%-- END Main Page Content --%>

<%-- Map Dialog --%>
<div id="dialog-map"></div>
</sec:authorize>
