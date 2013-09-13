<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="system-messaging-list">
		<ul>
			<li class="message-title">
				<spring:message code="commons.pages.message" />
			</li>
			<li id="rni-offline" class="system-message-label error rni-link" style="display:none;">
				<a rel="black" class="rounded bubble" value="<spring:message code="commons.pages.rniofflinemsg" />">
					<spring:message code="commons.pages.rnioffline"></spring:message> </a>
			</li>
			<li id="request-processing" class="system-message-label" style="display:none;">
				<a href="#" class="processing rounded">
					<spring:message code="commons.pages.recentRequests" /> <span id="long-running-process-size-p" class="count rounded hide"></span>
				</a>
			</li>
			<li id="request-complete" class="system-message-label" style="display:none;">
				<a href="#" class="rounded">
					<spring:message code="commons.pages.recentRequests" /> <span id="long-running-process-size-c" class="count rounded hide"></span>
				</a>
			</li>
			<li id="processing-size" class="hide"></li>
		</ul>
	</div>

</sec:authorize>