<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">


<%-- Delete Group Dialog --%>
<div id="action-dialog-delete" class="action-dialog">
	<div id="selected-schedule" ></div>
</div>
</sec:authorize>