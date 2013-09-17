<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="setup_demand_response_han" class="action-dialog">
		<form id="createSetupDemandResponseForm" name="createSetupDemandResponseForm" action="" method="post">
			<fieldset class="demand-response-fields">
			<legend id="get-participation-dialog-legend"></legend>
			<br></br>
				 <div class="advanced-search-container group yui-gf spindown-child">
					<div class="yui-g">
						<ul>
							<li class="checkbox"><input type="checkbox" id="relay_one_enrollment_group" /><label for="relay_one_enrollment_group"><spring:message code="smartpointdetail.tabs.about.lcmRelay.enrollment"/></label></li>
							<li class="checkbox"><input type="checkbox" id="relay_one_start_rondomization" /><label for="relay_one_start_rondomization"><spring:message code="smartpointdetail.tabs.about.lcmRelay.start_randomization"/></label></li>
							<li class="checkbox"><input type="checkbox" id="relay_one_duration_randomization" /><label for="relay_one_duration_randomization"><spring:message code="smartpointdetail.tabs.about.lcmRelay.duration_randomization"/></label></li>
						</ul>
					</div>
				</div>
			 </fieldset>
		</form>
	</div>

</sec:authorize>