<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="dashboard-content" class="content-inner">

		<c:if test="${contentModules.header}">
			<c:import url = "modules/module_dashboard_header.jsp"/>
		</c:if>

		<div class="yui-gc">

			<c:if test="${contentModules.eventsToday}">
				<c:import url = "modules/module_dashboard_events_today.jsp"/>
			</c:if>

			<c:if test="${contentModules.savedSearches}">
				<c:import url = "modules/module_dashboard_saved_searches.jsp"/>
			</c:if>
		</div>

		<c:if test="${contentModules.savedSearches}">
			<jsp:include page="../../scripts/pages/saved_search/saved_search_main.js.jsp" flush="true"/>
		</c:if>
	</div>

	<jsp:include page="../../scripts/pages/modules_dashboard/module_dashboard_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/dashboard/dashboard_init.js.jsp" flush="true" />

</sec:authorize>