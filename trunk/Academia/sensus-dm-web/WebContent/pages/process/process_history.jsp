<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="content-inner expandable">
		<div class="yui-t2">
			<div id="w-filters"></div>
			<div id="yui-main">
				<div class="yui-b" style="margin-left: 164px;">
					<div class="yui-u">
						<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR',
							'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
							<div class="yui-ge tools shim">
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
							</div>
						</sec:authorize>
					</div>

					<!-- START filter bar -->
					<div class="filter-results-containter">
						<div class="yui-ge">
							<div class="yui-u first hide">
								<ul class="filter-container">
									<li>
										<a href="" class="clear">
											<spring:message code="commons.pages.resetfilters" />
										</a>
									</li>
								</ul>
							</div>
							<div class="results yui-u">
								<span id="results"></span>&nbsp;<spring:message code="commons.pages.matches" />
							</div>
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

							<table id="history-table" class="list side-slide">
								<thead>
									<tr>
										<th class="hide" rowspan="2"></th>
										<th class="hide" rowspan="2"></th>
										<th rowspan="2" class="hide"></th>
										<th rowspan="2"><span><spring:message code="commons.pages.actionType" /></span></th>
										<th rowspan="2"><span><spring:message code="commons.pages.actionName" /></span></th>
										<th rowspan="2"><span><spring:message code="commons.pages.event" /></span></th>
										<th colspan="2"><span><spring:message code="commons.pages.smartPoints" /></span></th>
										<th rowspan="2"><span id="modified_user"><spring:message code="systemintelligence.page.today.requestedBy" /></span></th>
										<th rowspan="2"><span id="start_time"><spring:message code="commons.pages.startTime" /></span></th>
										<th rowspan="2"><span id="status"><spring:message code="commons.pages.status" /></span></th>
										<th rowspan="2" class="hide"></th>
									</tr>
									<tr class="sub-head">
										<th><span id="smartpoint_count"><spring:message code="commons.pages.total" /></span></th>
										<th><span id="failed"><spring:message code="commons.pages.failed" /></span></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/process/process_history_main.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/process/process_history_init.js.jsp" flush="true"/>

</sec:authorize>