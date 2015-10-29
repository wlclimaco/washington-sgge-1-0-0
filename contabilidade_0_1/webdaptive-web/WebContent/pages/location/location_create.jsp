<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
		<a class="alist" href="organization" title="<s:message code="commons.pages.customers" text="default text" />">
			<span><s:message code="commons.pages.customers" text="default text" /></span>
		</a>
		<span class="icon-nav icon-angle-right add-business">
			<span class="add-business">
				<s:message code="commons.pages.addlocation" text="default text" />
			</span>
		</span>
		<span id="company-name"></span>
</nav>


<h2 id="title-create-location" class="add-business">
	<s:message code="commons.pages.locationaddfor" text="default text" />
</h2>

<jsp:include page="../business/business_create.jsp" flush="true" />

<jsp:include page="../../scripts/pages/location/location_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/location/location_create_init.js.jsp" flush="true" />

