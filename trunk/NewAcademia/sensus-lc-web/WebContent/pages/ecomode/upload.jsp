<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
	<body>
		<script type="text/javascript">

			var sMessageCode 		= "${ecoModeModel.messageCode}";
			var oArguments 			= JSON.stringify(${ecoModeModel.arguments});
			var bOperationSuccess 	= "${ecoModeModel.operationSuccess}";

			window.location = "../main#/settings/?messageCode=" + escape(sMessageCode) + "&operationSuccess=" + escape(bOperationSuccess) + "&arguments=" + escape(oArguments);

		</script>
	</body>
</html>