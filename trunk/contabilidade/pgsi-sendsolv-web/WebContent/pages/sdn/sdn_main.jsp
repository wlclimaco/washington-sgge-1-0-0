<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="content list">
	<div class="data">
		<table id="data_list"></table>
	</div>
</div>

<jsp:include page="../../scripts/pages/sdn/sdn_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/sdn/sdn_init.js.jsp" flush="true" />