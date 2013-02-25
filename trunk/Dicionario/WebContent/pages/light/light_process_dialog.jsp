<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">

<%-- Long Running Process dialog --%>
<div id="action-dialog-panel" class="action-dialog">
    <h2><s:message code="action.longRunningProcessDialog.title"/></h2>
    <div class="descriptive-button-row">
	    <p></p>
        <a href="" class="button monitor"><s:message code="action.longRunningProcessDialog.submit"/></a>
        <a href="" class="button dismiss"><s:message code="action.longRunningProcessDialog.cancel"/></a>
    </div>
	<div class="highlight"><small><input type="checkbox" id="monitor-request"/> <s:message code="action.longRunningProcessDialog.highlight"/></small></div>
</div>

</sec:authorize>