<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
	<a class="alist" href="organization">
		<span><s:message code="commons.pages.customers" text="Customers" /></span>
	</a>

	<span class="icon-nav icon-angle-right"></span>
	<span id="company-name"></span>
</nav>

<h2 id="company-name-field"></h2>

<div id="tabs">
	<ul>
		<li>
			<a href="#" id="organizationInfo" data-tab="info" title='<s:message code="commons.organization.view.organizationinfo" text="default text" />' data-title='<s:message code="commons.organization.view.organizationinfo" text="default text" />'>
				<s:message code="commons.organization.view.organizationinfo" text="default text" />
			</a>
		</li>
		<li>
			<a href="#" id="organizationBylocation" data-tab="locations" title='<s:message code="commons.pages.locations" text="default text" />' data-title='<s:message code="commons.pages.locations" text="default text" />'>
				<s:message code="commons.pages.locations" text="default text" />
			</a>
		</li>
		<li class="add_loc_link" title="<s:message code='commons.pages.addlocation' text='default text' />">
			<span class="icon-nav icon-plus icon-small-button"></span>
			<s:message code="commons.pages.addlocation" text="default text" />
		</li>
	</ul>
</div>

<jsp:include page="../../scripts/pages/organization/organization_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/organization/organization_view_init.js.jsp" flush="true" />