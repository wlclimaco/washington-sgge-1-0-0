<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="device-alarm" class="point-detail-container hide">
		<div id="device-alarm-content" class="alarms">
			<div class="yui-gd row">
				<div class="yui-u first electric-alert">
				</div>
				<div class="yui-u">
					<div class="alarms-summary">
						<a id="0" href="" class="alert-detail-action text-button-mute  quiet-sample"></a>
					</div>
				</div>
			</div>

		</div>
	</div>

</sec:authorize>