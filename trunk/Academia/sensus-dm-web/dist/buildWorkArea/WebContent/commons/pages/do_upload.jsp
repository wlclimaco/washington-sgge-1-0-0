<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<html>
	<script src="/epm-wui/thirdparty/jquery/jquery-1.6.4.min.js"></script>
	<head>
	<script>
	$(document).ready(function() {
		var ids = $("#values").val();

		window.opener.$("#upload-list").val(ids);
		window.opener.sensus.util.page.stopProgressBar();
		window.close();

	});

	</script>
	</head>

	<body>

	<input type="hidden" value="<s:property value="values" />" id="values" name="values">

	</body>
	</html>

</sec:authorize>