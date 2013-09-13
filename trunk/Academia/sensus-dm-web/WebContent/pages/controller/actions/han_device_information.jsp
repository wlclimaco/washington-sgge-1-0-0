<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="action-han-detail" class="action-dialog">
		<form action="" method="post">
			<fieldset id="" class="create-action-container">
				<legend><span class="left"><strong class="field-header-device-type"></strong> <small class="field-flex-net-id"><span></span> <a href="" class="text-button launch"><s:spring:message code="smartpointdetail.dialog.deviceInformation.viewDetailPage"/></a></small></span></legend>
						<!-- START: Device details -->
						<div class="yui-gc">
							<div class="point-detail-container yui-u first">
								<dl class="read-only">
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.deviceType"/></dt>
									<dd class="field-device-type"></dd>
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.manufacture"/></dt>
									<dd class="field-manufacture"></dd>
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.modelNumber"/></dt>
									<dd class="field-model-number"></dd>
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.mac"/></dt>
									<dd class="field-mac"></dd>
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.networkStatus"/></dt>
									<dd class="field-network-status"></dd>
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.lastStatus"/></dt>
									<dd class="field-last-status"></dd>
									<dt><spring:message code="smartpointdetail.dialog.deviceInformation.dateAdded"/></dt>
									<dd class="field-date-added"></dd>
								</dl>
							</div>
						</div>
						<!-- END: Step -->
			 </fieldset>
		</form>
	</div>

</sec:authorize>