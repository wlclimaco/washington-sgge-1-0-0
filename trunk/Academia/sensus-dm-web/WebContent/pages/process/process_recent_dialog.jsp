<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- Table long running Process -->
	<div id="action-dialog-panel" class="action-dialog">
		<div id="processing-container">
			<div class="messaging hide" id="messaging-recent">
				<span class="message"></span> <a class="remove" href="#">Close</a>
			</div>
			<p class="description">
				<spring:message code="action.longRunningProcessDialog.description" />
				<sec:authorize access="!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					<a class="alist event-hisotry-process" href="process/history">
						<spring:message code="commons.pages.eventHistory" />
					</a>
				</sec:authorize>
			</p>
			<div id="group-list" class="long-running">
				<div class="yui-ge tools">
					<div id="actions" class="actions yui-u first">
						<div class="yui-pad2">
							<a tabindex="0" id="remove-all-lrp" href="#" class="fg-button ui-widget ui-state-default ui-corner-all">
								<spring:message code="action.longRunningProcessDialog.removeAll" />
							</a> <span class="message message-recent-request rounded">
							<span id="checked-count-dialog">0</span> <spring:message code="commons.pages.recentRequests.selected" /></span>
						</div>
					</div>
				</div>
				<table id="process-table" class="list">
					<thead>
						<tr>
							<th rowspan="2" class="indentation hide"></th>
							<th rowspan="2" class="lrp-id-col hide"><span class="process-id"><spring:message code="commons.pages.id" /></span></th>
							<th id="device_type" rowspan="2" class="hide"><span><spring:message code="commons.pages.device_type" /></span></th>
							<th rowspan="2"><span><spring:message code="commons.pages.event" /></span></th>
							<th rowspan="2"><span><spring:message code="commons.pages.action" /></span></th>
							<th colspan="2"><span><spring:message code="commons.pages.smartPoints" /></span></th>
							<th rowspan="2"><span><spring:message code="commons.pages.startTime" /></span></th>
							<th rowspan="2"><span><spring:message code="longRunning.table.header.completedIn" /></span></th>
							<th rowspan="2" class="status-col"><span><spring:message code="commons.pages.status" /></span></th>
							<th rowspan="2" class="hide"></th>
							<th rowspan="2" class="hide"></th>
							<th rowspan="2" class="hide"></th>
							<th rowspan="2" class="hide"></th>
						</tr>
						<tr class="sub-head">
							<th id="total"><span><spring:message code="commons.pages.total" /></span></th>
							<th id="failed"><span><spring:message code="commons.pages.failed" /></span></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../../scripts/pages/process/process_recent_dialog_init.js.jsp" flush="true" />

</sec:authorize>