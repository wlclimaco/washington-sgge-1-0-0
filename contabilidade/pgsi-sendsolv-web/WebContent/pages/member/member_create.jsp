<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
	<a class="alist" href="member" title='<s:message code="commons.pages.members" text="default text" />'>
		<span><s:message code="commons.pages.members" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business"></span>
	<span class="add-business">
			<s:message code="commons.pages.memberaddnew" text="default text" />
	</span>
</nav>

<h2><s:message code="commons.pages.memberaddnew" text="default text" /></h2>

<div class="container-member">
	<div class="content member-content person-content">
		<div class="remembered-location"><s:message code="pages.member.form.label.addingMembers" text="default text" />
			<a href="" class="view view-organization edit_link alist"></a>
			<a href="" class="view view-Location edit_link alist"></a>
			<a href="" title="Remove">&nbsp;&nbsp;<span class="alist icon-nav icon-remove remove"></span> </a>
		</div>
		<jsp:include page="member_form.jsp"></jsp:include>
	</div>
</div>