<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Long Running Process Abort dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<div class="action-dialog" style="width: auto; min-height: 116px; height: auto;"><s:message code="longRunning.table.message.abort"/></div>
</div>