<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Long Running Process Remove dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<div class="action-dialog ui-dialog-content ui-widget-content" style="width: auto; min-height: 29px; height: auto;"><s:message code="longRunning.table.message.remove" /></div>
	<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
		<button type="button" class="button removelrp">
			<s:message code="longRunning.table.remove"/>
		</button>
		<button type="button" class="button cancellrp">
			<s:message code="longRunning.table.cancel"/>
		</button>
	</div>
</div>