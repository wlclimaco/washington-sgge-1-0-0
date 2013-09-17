<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="action-dialog ui-dialog-content ui-widget-content" id="action-read-only-event"
		style="display: block; width: auto; min-height: 100.733px; height: auto;">
		<form method="post" action="">
			<fieldset id="dr-fields-container">
				<legend>
					<strong class="label">
						<spring:message code="action.sendHanTextMessageDialog.action" />
					</strong>
					<spring:message code="action.sendHanTextMessageDialog.sendHanTextMessage" />
				</legend>
				<div class="point-detail-container sui-pad">
					<dl class="read-only">
						<dt>
							<spring:message code="action.sendHanTextMessageDialog.message" />
						</dt>
						<dd class="message"></dd>
						<dt>
							<spring:message code="action.sendHanTextMessageDialog.displayDuration" />
						</dt>
						<dd class="duration"></dd>
						<dt>
							<spring:message code="action.sendHanTextMessageDialog.devices" />
						</dt>
						<dd class="devices"></dd>
						<dt>
							<spring:message code="action.sendHanTextMessageDialog.initiatedBy" />
						</dt>
						<dd class="initiated"></dd>
					</dl>
				</div>
			</fieldset>
		</form>
	</div>

</sec:authorize>