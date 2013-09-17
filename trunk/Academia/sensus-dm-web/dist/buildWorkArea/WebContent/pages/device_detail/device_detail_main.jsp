<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<div id="content-controlle">
	<!-- Header Tabs Device Detail -->
	<div id="tabs" class="point-type-navigation header rounded-top-corners">
		<a id="back-to-list" href="" class="back url">
			<spring:message code="smartpointdetail.page.backlist" />
		</a>

		<!-- Tabs -->
		<jsp:include page="modules/module_detail_tabs.jsp" flush="true"/>

	</div>

	<!--  Content -->
	<div class="content-inner">
		<div id="messaging-smartpoint-detail" class="messaging messaging-smartpoint-detail hide">
			<span class="message"></span>
			<a href="" class="remove">
				<spring:message code="commons.pages.close" />
			</a>
		</div>

		<div class="stamp-smartpoint highlight hide">
			<spring:message code="commons.pages.dataReflects" />
			<strong><span id="receivedDate"></span></strong>
		</div>

		<!-- Summary Data -->
		<jsp:include page="modules/module_detail_summary.jsp" flush="true"/>

		<!-- Tabs Content -->
		<div id="tabs-content"></div>
	</div>
</div>

<!-- JavaScript Files -->

<!-- Modules -->
<jsp:include page="../../scripts/pages/modules_detail/module_detail_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/modules_detail/module_detail_request.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/modules_detail/module_detail_summary.js.jsp" flush="true"/>
<script src="scripts/pages/device_detail/device_detail_actions.js" ></script>

<!-- Init -->
<jsp:include page="../../scripts/pages/device_detail/device_detail_init.js.jsp" flush="true"/>

</sec:authorize>