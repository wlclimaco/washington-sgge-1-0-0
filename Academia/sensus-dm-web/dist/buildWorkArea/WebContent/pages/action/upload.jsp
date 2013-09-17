<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title><spring:message code="flexnet.portal.title" /></title>

			<link rel="stylesheet" href="../thirdparty/jquery/styles/jquery-ui-1.9.2.custom.min.css">
			<link rel="stylesheet" href="../styles/yahoo_reset_fonts_grids.css">
			<link rel="stylesheet" href="../styles/preload.css">
		</head>
		<body>

			<div class="preload">
				<div>
					<span><spring:message code="commons.pages.updating" /></span>
				</div>
			</div>

			<input type="hidden" id="messageError" value="${actionModel.messageError}" />

			<script src="../thirdparty/head.js" ></script>
			<script src="../scripts/util/namespace_init.js" ></script>
			<script src="../thirdparty/jquery/jquery-1.8.3.min.js" ></script>
			<script src="../thirdparty/jquery/jquery.ui.components.js" ></script>
			<script src="../thirdparty/jquery/jquery-ui-1.9.2.custom.min.js" ></script>
			<script src="../thirdparty/jquery/jquery.address-1.5.min.js" ></script>
			<script src="../scripts/util/page.js" ></script>

			<script type="text/javascript">
				head(function() {
					$(document).ready(function() {

						// Message error from ActionModel Object
						var sMessageError = $("#messageError").val();

						if (sMessageError) {

							var sLocationUrl = "../service#/electric?pg=electriclist&message=" + encodeURI(sMessageError);

						} else {

							var sLocationUrl = "../service#/electric?pg=electriclist&message=IMPORT";
						}

						window.location = sLocationUrl;

					});
				});
			</script>
		</body>
	</html>

</sec:authorize>