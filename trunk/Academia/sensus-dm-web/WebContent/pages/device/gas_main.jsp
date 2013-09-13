<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-ge point-type-navigation header rounded-top-corners">
		<div class="yui-u first">
			<ul class="tabs device-tabs">
				<li id="meters">
					<a href="#GAS_METER|" class="gradient-vert">
						<spring:message code="commons.pages.Meters" />
					</a>
				</li>
			</ul>
		</div>
	</div>

	<jsp:include page="device_main.jsp" flush="true" />

	<jsp:include page="../../scripts/util/device_util.js.jsp" flush="true" />
	<script src="scripts/pages/device/gas_main.js" ></script>
	<jsp:include page="../../scripts/pages/device/device_init.js.jsp" flush="true" />

</sec:authorize>