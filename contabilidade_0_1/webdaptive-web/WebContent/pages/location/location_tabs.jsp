<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
		<a class="alist" href="organization">
			<span><s:message code="commons.pages.customers" text="Customers" /></span>
		</a>
		<span class="icon-nav icon-angle-right"></span>
		<a class="alist" href="view?organizationId=" id="parent-organization-link">

		</a>
		<span class="icon-nav icon-angle-right"></span>
		<span id="company-name"></span>
</nav>

<h2 id="company-name-field"></h2>

<div id="tabs">
	<ul>
		<li>
			<a href="#" id="infoTab" data-tab="info" title='<s:message code="commons.organization.view.locationinfo" text="default text" />' data-title='<s:message code="commons.organization.view.locationinfo" text="default text" />'>
				<s:message code="commons.organization.view.locationinfo" text="Location Info" />
			</a>
		</li>
		</li>
		<li>
			<a href="#" id="pricingTab" data-tab="pricing" title='<s:message code="commons.pages.pricing" text="default text" />"' data-title='<s:message code="commons.pages.pricing" text="default text" />'>
				<s:message code="commons.pages.pricing" text="default text" />
			</a>
		</li>
		<li class="add_loc_link" title="<s:message code="commons.pages.addanotherlocation" text="default text" />">
			<span class="icon-nav icon-plus icon-small-button"></span>
			<s:message code="commons.pages.addanotherlocation" text="default text" />
		</li>
	</ul>
</div>

<jsp:include page="../../scripts/pages/location/location_tabs_init.js.jsp" flush="true" />