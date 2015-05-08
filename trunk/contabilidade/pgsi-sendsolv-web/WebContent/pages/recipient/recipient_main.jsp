<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">
	<a class="alist" href="member" text="<s:message code="commons.pages.customers" text="default text" />">
		<span><s:message code="commons.pages.customers" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="commons.pages.recipientfind" text="default text" />
		</span>
	</span>
</nav>

<h2 class="list"><s:message code="commons.pages.recipientfind" text="default text" /></h2>

<div class="content list">
	<div class="filter">
		<form id="filterForm"></form>
	</div>
	<table id="data_list"></table>
</div>

<jsp:include page="../../scripts/pages/recipient/recipient_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_init.js.jsp" flush="true" />
