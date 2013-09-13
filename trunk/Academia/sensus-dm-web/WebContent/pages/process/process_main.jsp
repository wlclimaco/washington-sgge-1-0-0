<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-ge point-type-navigation header rounded-top-corners">
		<div class="yui-u first">
			<ul class="tabs">
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					<li><a id="today" href="process/today"><spring:message code="commons.pages.today" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					<li><a id="report" href="process/report"><spring:message code="commons.pages.leakReport" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					<li><a id="schedule" href="schedule"><spring:message code="commons.pages.scheduledEvents" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					<li><a id="history" href="process/history"><spring:message code="commons.pages.eventHistory" /></a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
	<div class="content-inner">
		<!-- Messaging -->
		<div id="messaging-main" class="messaging hide">
			<span class="message"></span><a href="" class="remove"><spring:message code="commons.pages.close" /></a>
		</div>
		<div id="tabs-content"></div>
	</div>

	<jsp:include page="../../scripts/pages/process/process_init.js.jsp" flush="true" />

</sec:authorize>