<%@ page  language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="yui-main">

		<div id="point-detail">

			<!-- START CONTENT -->

			<!-- START: Report Tools -->
			<div class="report-tools-contianer demand-reads">

				<div class="date-select yui-u first">

					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<div class="export-select export-select-readings">

							<ul class="link-list">
								<li class="last export-type right"><small><strong><spring:message code="commons.pages.Export"/></strong>: <a href="" id="csv" class="csv"><spring:message code="commons.pages.csv"/></a></small></li>
								<li id="menuDay" class="last export-type left"></li>
							</ul>

						</div>
					</sec:authorize>

					<div id="actions-options">

						<div class="tools-demand-reads">
							<fieldset>
								<ul>
									<li class="chzn-row">
										<select id="select-year" class="chzn-select"></select>
									</li>
								</ul>
							</fieldset>
						</div>

					</div>

				</div>

			</div>
			<!-- END: Report Tools -->

			<div id="list">

				<!-- BLANKSLATE -->
				<div class="blankslate hide">
					<h5 id="msg-blankslate-readings"></h5>
				</div>

				<!-- TABLE -->
					<table id="demand-reads-table" class="list">
						<thead>
							<tr>
								<th><span id="tier"><spring:message code="commons.pages.Tier" /></span></th>
								<th><span id="peak_demand"><spring:message code="smartpointdetail.tabs.demandreads.peakdemand" /></span></th>
								<th><span id="peak_time"><spring:message code="smartpointdetail.tabs.demandreads.peaktime" /></span></th>
								<th><span id="reading_date"><spring:message code="smartpointdetail.tabs.demandreads.read" /></span></th>
								<th><span id="reset_time"><spring:message code="smartpointdetail.tabs.demandreads.lastreset" /></span></th>
								<th><span id="demand_reset_count"><spring:message code="smartpointdetail.tabs.demandreads.counter" /></span></th>
								<th class="hide"></th>
								<th class="hide"></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				<!-- END CONTENT -->
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/device_detail/device_detail_demand_reads_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/device_detail/device_detail_demand_reads_init.js.jsp" flush="true" />

</sec:authorize>