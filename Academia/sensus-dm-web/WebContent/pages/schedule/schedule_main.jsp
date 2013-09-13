<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="content-inner expandable">
		<div class="yui-t2">
	   		<div id="w-filters"></div>
			<div id="yui-main">
				<div class="yui-b" style="margin-left: 164px;">
					<div class="yui-ge tools shim">
						<div id="actions" class="actions yui-u first">
							<div class="yui-pad4">
								<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
									<a tabindex="0" id="ajax-button" href="schedule/create"
										class="fg-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="systemintelligence.scheduledCreateEvent"/>
									</a>
								</sec:authorize>
							</div>
						</div>
						<div class="yui-u">
							<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR',
					  						'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
								<div class="export-select-right">
									<ul class="link-list">
										<li class="last export-type">
											<small>
												<strong><spring:message code="commons.pages.Export"/></strong>:
												<a href="" id="csv" class="csv">
													<spring:message code="commons.pages.csv"/>
												</a>
											</small>
										</li>
									</ul>
								</div>
							</sec:authorize>
						</div>
					</div>

					<!-- START filter bar -->
					<div class="filter-results-containter">
						<div class="yui-ge">
							<div class="yui-u first hide">
								<ul class="filter-container">
									<li>
										<a href="" class="clear">
											<spring:message code="commons.pages.resetfilters"/>
										</a>
									</li>
								</ul>
							</div>
							<div class="results yui-u"></div>
						</div>
					</div>
					<!-- END -->

					<!-- START status viewport -->
					<div class="status-viewport">
						<div id="list">

							<!-- Blankslate -->
							<div class="blankslate hide side-slide">
								<h5><spring:message code="commons.pages.blankResult" /></h5>
								<p><spring:message code="commons.pages.removefilters" /></p>
							</div>

							<table id="listTable" class="list side-slide">
								<thead>
									<tr>
								   		<th class="hide"><span id="schedule_id"><spring:message code="commons.pages.updating"/></span></th>
										<th><span id="event_name"><spring:message code="commons.pages.eventName"/></span></th>
										<th><span id="action_name"><spring:message code="commons.pages.actionName"/></span></th>
										<th><span id="action_type"><spring:message code="commons.pages.actionType"/></span></th>
										<th><span id="scheduled_by"><spring:message code="systemintelligence.page.event.scheduledby"/></span></th>
										<th><span id="deliver_on"><spring:message code="commons.pages.deliverOn"/></span></th>
										<th><span id="action_date"><spring:message code="systemintelligence.page.event.eventdate"/></span></th>
										<th><span id="frequency_name"><spring:message code="commons.pages.repeats"/></span></th>
										<th><span id="total_smartpoints"><spring:message code="commons.pages.smartPoints"/></span></th>
										<th><span id="status"><spring:message code="commons.pages.status"/></span></th>
										<th class="hide"><span id="procId"><spring:message code="commons.pages.updating"/></span></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							 </table>
						</div>
					</div>
					<!-- END loading container -->
				</div>
			</div>
		</div>
	</div>

   	<jsp:include page="../../scripts/pages/schedule/schedule_main.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/schedule/schedule_actions.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/schedule/schedule_init.js.jsp" flush="true"/>

</sec:authorize>