<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator','ROLE_Role.Customer Support')">
<!-- Table long running Process -->
<div id="action-dialog-panel" class="action-dialog">
	<div id="processing-container">
		<p class="description"><s:message code="action.recentRequest.description" />
			<a class="alist event-history-process" href="systemintelligence/process"><s:message code="action.recentRequest.eventHistory" /></a>
		</p>
		<div id="group-list" class="long-running">
			<div class="yui-ge tools">
				<div id="actions" class="actions yui-u first">
					<div class="yui-pad2">
						<a tabindex="0" id="remove-all-lrp" href="#" class="fg-button ui-widget ui-state-default ui-corner-all"><s:message code="action.recentRequest.removeAll" /></a>
						<span class="message message-recent-request rounded"><span id="checked-count-dialog">0</span> <s:message code="action.recentRequest.requestedSelected" /></span>
					</div>
				</div>
			</div>
				<table id="process-table" class="list">
					<thead>
						<tr>
							<th class="hide" rowspan="2"><span id="process_id"><s:message code="longRunning.table.header.id" /></span></th>
							<th rowspan="2"><span id="lc_action_id"><s:message code="longRunning.table.header.action" /></span></th>
							<th rowspan="2" class="hide"></th>
							<th colspan="2"><span id="smartpoint_count"><s:message code="longRunning.table.header.smartpoint" /></span></th>
							<th rowspan="2" class="hide"></th>
							<th rowspan="2"><span id="description"><s:message code="longRunning.table.header.description" /></span></th>
							<th rowspan="2" class="hide"><span id="create_user"><s:message code="longRunning.table.header.requestBy" /></span></th>
							<th rowspan="2"><span id="start_datetime"><s:message code="longRunning.table.header.startTime" /></span></th>
							<th rowspan="2"><span id="is_process_complete"><s:message code="longRunning.table.header.status" /></span></th>
							<th rowspan="2" class="hide">&nbsp;</th>
							<th rowspan="2" class="hide"></th>
							<th rowspan="2" class="hide"></th>
							<th rowspan="2" class="hide"></th>
						</tr>
						<tr class="sub-head">
							<th class="sub-head"><span id="light_count"><s:message code="longRunning.table.header.total" /></span></th>
							<th class="sub-head"><span id="light_failed_count"><s:message code="longRunning.table.header.failed" /></span></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		</div>
	</div>
</div>
</sec:authorize>