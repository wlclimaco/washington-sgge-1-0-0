<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="<c:if test="${serviceType != null}">${serviceType.toLowerCase()}</c:if>">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="flexnet.portal.title" /></title>

		<link rel="stylesheet" href="styles/preload.css">
	</head>

	<body>

		<div class="preload">
			<div>
				<span><spring:message code="commons.pages.loading.application" /></span>
			</div>
		</div>

		<div class="fullscreen"></div>

		<div id="doc1" class="application lighting">

			<jsp:include page="../pages/top_bar.jsp" flush="true"/>

			<div class="general-content"></div>

			<jsp:include page="../pages/footer.jsp" flush="true"/>

		</div>

		<div id="loading" style="display: none;">
			<h5></h5>
			<div id="progressbar"></div>
		</div>

		<script src="thirdparty/head.js" ></script>

		<script type="text/javascript">
			head.js("thirdparty/jquery/styles/jquery-ui-1.9.2.custom.min.css");
			head.js("styles/yahoo_reset_fonts_grids.css");
			head.js("scripts/util/namespace_init.js",
					"thirdparty/jquery/jquery-1.8.3.min.js",
					"thirdparty/jquery/jquery-ui-1.9.2.custom.min.js",
					"thirdparty/jquery/custom/jquery.i18n.properties-min.custom.js",
					"thirdparty/jquery/jquery.glob.min.js",
					"thirdparty/jquery/jquery.address-1.5.min.js",
					"scripts/util/locale.js",
					"scripts/util/page.js",
					"scripts/pages/dm_main.js",
					"scripts/pages/dm_init.js");
		</script>

	</body>
</html>
</sec:authorize>