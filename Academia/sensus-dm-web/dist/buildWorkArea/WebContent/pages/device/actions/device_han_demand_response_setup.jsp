<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="setup_demand_response_han" class="action-dialog">
		<form id="createSetupDemandResponseForm" name="createSetupDemandResponseForm" action="" method="post">
		<fieldset class="demand-response-fields label-right">
			<legend><spring:message code="smartpointdetail.tabs.about.demandResponseSetup"/></legend>
			<ul>
				<li>
					<label for="enrollment-code"><spring:message code="systemintelligence.page.event.hanEnrollment"/></label>
					<input type="text" id="enrollmentCode" class="validate[custom[integer],min[0],max[255],maxSize[120]]" />
				</li>
				<li>
					<label for="randomize-start"><spring:message code="smartpointdetail.tabs.about.randomizeStart"/> :</label>
					<input type="text" id="randomizeStart" class="validate[custom[integer], min[1],max[60]] date-short" /> <spring:message code="commons.pages.minutes"/>
				</li>
				<li>
					<label for="randomize-end"><spring:message code="smartpointdetail.tabs.about.randomizeEnd"/> :</label>
					<input type="text" id="randomizeEnd" class="validate[custom[integer], min[1],max[60]] date-short" /> <spring:message code="commons.pages.minutes"/>
				</li>
			</ul>
		</fieldset>
		</form>
	</div>

</sec:authorize>