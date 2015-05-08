<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">

	<a class="alist" href="payer/find/countries">
		<span><s:message code="commons.title.payer" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<a class="alist" id="countryLink" href="payer/find/countries">
		<span id="country"></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<a class="alist" id="stateLink" href="javascript:;">
		<span id="state"></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<a class="alist" id="cityLink" href="javascript:;" >
		<span id="city"></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<span><s:message code="pages.payer.tables.label.branches" text="default text" /></span>
</nav>

<h2 class=""><s:message code="commons.pages.payer.findPayer" text="default text" /></h2>

<div class="content list branches">
	<div style="margin-left: 10px;">
		<div class="content list">
			<table id="data_list"></table>
		</div>
	</div>
</div>

<jsp:include page="../../scripts/pages/payer/payer_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payer/payer_branches_init.js.jsp" flush="true" />

