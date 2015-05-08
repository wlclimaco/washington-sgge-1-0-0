<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">
	<a class="alist" href="payer/find/countries">
		<span><s:message code="commons.title.payer" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<span><s:message code="pages.payer.label.countries" text="default text" /></span>
</nav>

<h2 class=""><s:message code="commons.pages.payer.findPayer" text="default text" /></h2>

<div class="content list">
	<div style="margin-left: 10px;">
		<div class="content list">
			<div class="filter">
				<form id="filterForm"></form>
			</div>
			<table id="data_list"></table>
		</div>
	</div>
</div>

<jsp:include page="../../scripts/pages/payer/payer_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payer/payer_coutry_init.js.jsp" flush="true" />




