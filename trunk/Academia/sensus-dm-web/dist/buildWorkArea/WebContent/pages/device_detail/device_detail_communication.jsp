<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="config-communications" class="point-detail-container">
		<div class="yui-gc">
			<h3><spring:message code="commons.pages.configuration"/></h3>
			<dl class="read-only font-page">
				<dt><spring:message code="commons.pages.network_address"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.transmitRate"/>:</dt>
				<dd></dd>
				<dt><spring:message code="filter.encryptionstatus.encrypted.label"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.sampleRate"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.billingCycle"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.receiveChannel"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.transmit.channelMask"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.transmit.operationalMode"/>:</dt>
				<dd></dd>
				<dt><spring:message code="communication.page.receive.operationalMode"/>:</dt>
				<dd></dd>
			</dl>
		</div>
	</div>

	<script src="scripts/pages/device_detail/device_detail_communications_main.js" ></script>
	<script src="scripts/pages/device_detail/device_detail_communications_init.js" ></script>

</sec:authorize>