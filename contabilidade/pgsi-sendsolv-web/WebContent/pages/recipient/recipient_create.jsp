<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
	<a class="alist" href="member" title='<s:message code="commons.pages.members" text="default text" />'>
		<span><s:message code="commons.pages.members" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<span class="add-business">
		<s:message code="commons.pages.memberaddnew" text="default text" />
	</span>
</nav>

<h2><s:message code="commons.pages.recipientaddnew" text="default text" /></h2>

<div class="content member-content person-content">

	<jsp:include page="recipient_form.jsp" flush="true" />

</div>
