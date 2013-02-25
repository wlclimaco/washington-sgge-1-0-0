<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div class="status-viewport">
	<div class="point-detail-container sunrise-sunset">
		<div class="alarms point-detail-container ">
			<h3><div class="left"><s:message code="smartpointdetail.status.alerts" /></div>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<a id="light-clear-all" href="#" class="button small right clear-all"><s:message code="smartpointdetail.status.clearall" /></a>
				</sec:authorize>
			</h3>
			<dl id="status-messages">
			</dl>
		</div>
	</div>
</div>
<script src="commons/scripts/controller/module_light_alerts.js"></script>