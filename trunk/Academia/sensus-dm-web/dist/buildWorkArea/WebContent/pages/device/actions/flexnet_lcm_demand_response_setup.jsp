<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="setup_demand_response_han" class="action-dialog">
		<form id="createSetupDemandResponseForm" name="createSetupDemandResponseForm" action="" method="post">
			<fieldset class="demand-response-fields label-right">
				<legend id="flexnetLcmLegend"></legend>
				<table id="relay-table" class="small-table">
					<tbody>
						<jsp:include page="demand_response_relay.jsp" flush="true" />
						<jsp:include page="demand_response_relay.jsp" flush="true" />
						<jsp:include page="demand_response_relay.jsp" flush="true" />
					</tbody>
				</table>
		</fieldset>
		</form>
	</div>

</sec:authorize>