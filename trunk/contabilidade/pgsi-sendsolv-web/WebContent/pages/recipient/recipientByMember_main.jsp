<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="filter">
	<form id="filterForm"></form>
</div>

<table id="data_list"></table>

<jsp:include page="../../scripts/pages/recipient/recipient_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_init.js.jsp" flush="true" />