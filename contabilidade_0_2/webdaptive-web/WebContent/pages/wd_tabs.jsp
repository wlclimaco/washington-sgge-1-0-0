<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<ul class="tabs">
	<li><a href="empresa" class="alist"
		title="Temporary link to organization list"><s:message code="commons.pages.empresa" text="Empresas" /></a></li>
	<li><a href="organization/add" class="alist"
		title="Temporary link to add organization form"><s:message code="commons.pages.organizationadd" text="Add Organization" /></a></li>
	<li><a href="location" class="alist"
		title="Temporary link to location list"><s:message code="commons.pages.locations" text="Locations" /></a></li>
	<li><a href="location/add" class="alist"
		title="Temporary link to add location form"><s:message code="commons.pages.addlocation" text="Add Location" /></a></li>
	<li><a href="contact" class="alist"
		title="Temporary link to contact list"><s:message code="commons.pages.contacts" text="Contacts" /></a></li>
		<li><a href="contact/add" class="alist"
		title="Temporary link to add contact form"><s:message code="commons.pages.contactadd" text="Add Contact" /></a></li>
</ul>