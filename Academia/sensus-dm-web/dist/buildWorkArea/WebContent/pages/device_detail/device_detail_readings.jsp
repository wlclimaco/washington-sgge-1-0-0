<%@ page  language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="yui-main">

		<div id="point-detail">

			<!-- START CONTENT -->

			<!-- START: Report Tools -->
			<div class="report-tools-contianer">

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

						<div class="right">
							<input type="text" id="datepicker1" class="date rounded datepicker" value=""/>
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
				<table id="reading-table" class="list">
					<thead>
						<tr>
							<th id="first-column" class="hide"><span></span></th>
							<th id="channel1" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel2" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel3" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel4" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel5" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel6" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel7" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel8" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel9" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel10" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel11" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel12" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel13" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel14" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel15" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th id="channel16" class="hide"><span class="readings"></span></th>
							<th class="hide"></th>
							<th class="hide"><span><spring:message code="commons.pages.date"/></span></th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
				<!-- END CONTENT -->
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/device_detail/device_detail_readings_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/device_detail/device_detail_readings_init.js.jsp" flush="true" />

</sec:authorize>