<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=utf-8");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

<!---->
#estado {
	width:			400px!important;
    }
.hide{display:none;}

</style>
</head>
<body>
  <select size="1" name="estado" id="estado">
	  <option value="">Selecione</option>
	</select>

<jsp:include page="../../scripts/util/util_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/util/util_mvc_bas_init.js.jsp" flush="true"/>
</body>
</html>