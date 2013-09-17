<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="dialog-processing-alert-details"  class="action-dialog alert-details">
		<ul class="summary">
			<li class="fail"><span class="icon-small"></span></li>
			<li>
			 <div class="selected-points">
				<table class="small-table">
					<thead>
						<tr>
							<th><spring:message code="smartpointdetail.dialog.alerts.diagnosis"/></th>
							<!--<th><spring:message code="smartpointdetail.dialog.alerts.consumption"/></th>
							<th><spring:message code="smartpointdetail.dialog.alerts.current"/></th>-->
							<th><spring:message code="smartpointdetail.dialog.alerts.date_and_time"/></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		   </div>
		</li>
		</ul>
	</div>

</sec:authorize>