<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
	<a class="alist" href="pricing/profile_list" text='<s:message code="commons.pages.pricing" text="default text" />'>
		<span><s:message code="commons.pages.pricing" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="commons.pages.pricing.findProfile" text="default text" />
		</span>
	</span>
</nav>

<h2 class="list"><s:message code="commons.pages.pricing.findProfile" text="default text" /></h2>

<a href="#" onClick="event.preventDefault();" class="add-business" title='<s:message code="commons.pages.pricing.addNewProfile" text="default text" />'>
	<span class="icon-nav icon-plus icon-small-button"></span>
	<s:message code="commons.pages.pricing.addNewProfile" text="default text" />
</a>

<div class="content list">
	<table id="data_list"></table>
</div>

<jsp:include page="../../scripts/pages/pricing/pricing_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/pricing/pricing_init.js.jsp" flush="true" />