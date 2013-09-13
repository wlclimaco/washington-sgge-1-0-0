<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="setup_demand_response_han" class="action-dialog">
		<form id="createSetupDemandResponseForm" name="createSetupDemandResponseForm" action="" method="post">
			<fieldset class="demand-response-fields">
			<legend id="get-tamper-dialog-legend"></legend>
			<br></br>
				 <div class="advanced-search-container group yui-gf spindown-child">
					<legend><spring:message code="devicedetail.dialog.setTamper.lcmAlarms"/></legend>
					<div class="yui-g">
							<div class="yui-u first">
								<ul>
									<li class="checkbox"><input type="checkbox" id="device_relay_one" /><label for="device_class_hvac"><spring:message code="devicedetail.dialog.setTamper.relay1"/></label></li>
									<li class="checkbox"><input type="checkbox" id="device_relay_two" /><label for="device_class_heater"><spring:message code="devicedetail.dialog.setTamper.relay2"/></label></li>
									<li class="checkbox"><input type="checkbox" id="device_relay_three" /><label for="device_class_water_heater"><spring:message code="devicedetail.dialog.setTamper.relay3"/></label></li>
								</ul>
							</div>
					</div>


				</div>
			 </fieldset>
		</form>
	</div>

</sec:authorize>