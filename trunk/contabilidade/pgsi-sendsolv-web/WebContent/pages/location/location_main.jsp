<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">
	<a class="alist" href="organization" text="<s:message code="commons.pages.customers" text="default text" />">
		<span><s:message code="commons.pages.customers" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="commons.pages.locationfind" text="default text" />
		</span>
	</span>
	<span id="company-name"></span>
</nav>

<h2 class="list"><s:message code="commons.pages.locations" text="default text" /></h2>

<a href="location/add" class="add-business alist" title='<s:message code="commons.pages.locationadd" text="default text" />'>
	<span class="icon-nav icon-plus icon-small-button"></span>
	<s:message code="commons.pages.locationadd" text="default text" />
</a>

<div class="content list">
	<div class="filter">
		<form id="filterForm"></form>
	</div>
	<table id="data_list"></table>
</div>

<jsp:include page="../../scripts/pages/location/location_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/location/location_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/location/location_init.js.jsp" flush="true" />