<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" href="../styles/common_grid.css" rel="stylesheet" />
</head>
<body>
	<div class="grid-header" style="width:100%">
	</div>
	<table id="county_mvc" class="ui-widget" align="center" width="100%" style="border-collapse: collapse;">
		<input type="hidden" id="be_status" value="${countyResponse.isOperationSuccess()}" />
		<thead class="ui-widget-header">
		 	<tr>
			  <th id="county_id_mvc_hdr"></th>
			  <th id="county_desc_mvc_hdr"></th>
		 	</tr>
		 </thead>
		 <tbody class="ui-widget-content">
		 	<c:forEach items="${countyResponse.getCounties()}" var="county">
		  	<tr>
			   <td class="ui-widget-content"><span><c:out value="${county.id}" /></span></td>
			   <td class="ui-widget-content"><span><c:out value="${county.description}" /></span></td>
		  	</tr>
		 	</c:forEach>
		 </tbody>
	</table>
<jsp:include page="../../scripts/pages/counties/counties_mvc_init.js.jsp" flush="true"/>
</body>
</html>
