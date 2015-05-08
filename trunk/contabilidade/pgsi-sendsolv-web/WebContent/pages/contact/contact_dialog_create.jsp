<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="content form" id="create-contact">
	<form id="contact-form" method="post" action="#" >
    	<jsp:include page="contact_create.jsp" flush="true" />
	</form>
</div>
