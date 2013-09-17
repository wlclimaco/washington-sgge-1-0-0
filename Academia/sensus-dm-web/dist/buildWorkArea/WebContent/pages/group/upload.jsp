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

			<input type="hidden" id="groupId" value="${groupModel.id}" />
			<input type="hidden" id="meterType" value="${groupModel.deviceType}" />
			<input type="hidden" id="uploadFile" value="${groupModel.fileName}" />
			<input type="hidden" id="groupName" value="${groupModel.name}"/>
			<input type="hidden" id="messageError" value="${groupModel.messageError}"/>
			<input type="hidden" id="messageSuccess" value="${groupModel.messageSuccess}"/>
			<input type="hidden" id="processId" value="${groupModel.processId}" />

			<script src="../thirdparty/head.js" ></script>
			<script src="../scripts/util/namespace_init.js" ></script>
			<script src="../thirdparty/jquery/jquery-1.8.3.min.js" ></script>
			<script src="../thirdparty/jquery/jquery.ui.components.js" ></script>
			<script src="../thirdparty/jquery/jquery-ui-1.9.2.custom.min.js" ></script>
			<script src="../thirdparty/jquery/custom/jquery.i18n.properties-min.custom.js" ></script>
			<script src="../thirdparty/jquery/jquery.json-2.3.min.js" ></script>
			<script src="../thirdparty/jquery/jquery.address-1.5.min.js" ></script>
			<script src="../commons/widget-ui/widget_ajax_validator.js" ></script>
			<script src="../scripts/util/page.js" ></script>
			<script src="../scripts/util/locale.js" ></script>

			<script type="text/javascript">
				head(function() {
					$(document).ready(function() {

						var uploadFile = $("#uploadFile").val();
						var messagError = $("#messageError").val();
						var messageSuccess = $("#messageSuccess").val();
						var message = "";
						var param = "";

						if (uploadFile) {

							sensus.util.page.executeUploadAction("../api/importFile/upload/insertDeviceFileGroup",
									$("#groupId").val(), uploadFile, $("#processId").val(), $("#meterType").val());
						}

						$.ajaxValidator.fnDoCall("../fillSettings", null, false, function (oResponse) {

							sensus.settings = oResponse;
						});

						if (messagError) {

							message = escape(messagError);

						} else if (messageSuccess) {

							message = escape(messageSuccess);
							param = "&groupName=" + escape($("#groupName").val());
						}

						window.location = "../service#/" + sensus.settings.serviceType.toLowerCase()
								+ "?pg=group&check=" + $.address.parameter("check")
								+ "&message=" + message + param + "";
					});
				});
			</script>
		</body>
	</html>
</sec:authorize>