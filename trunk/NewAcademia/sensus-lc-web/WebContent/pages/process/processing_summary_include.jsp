<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!-- START Processing content -->
	<div id="processing-summary-container" class="point-detail-container">
		  <div id="detail">
				<dl class="summary">
					<dt><s:message code="action.processingsummary.id" />:</dt>
					<dd><span>${smartpointHistory.processId}</span></dd>
					<dt><s:message code="action.processingsummary.action" />:</dt>
					<dd><span>${smartpointHistory.name}</span></dd>
					<dt><s:message code="action.processingsummary.description" />:</dt>
					<dd><span>${smartpointHistory.description}</span></dd>
					<dt><s:message code="action.processingsummary.starttime" />:</dt>
					<dd><span>${smartpointHistory.time}</span></dd>
					<dt><s:message code="action.processingsummary.completedin" />:</dt>
					<dd><span>${smartpointHistory.processCompletedIn}</span></dd>
					<dt><s:message code="action.processingsummary.status" />:</dt>
					<dd><span><s:if test="%{smartpointHistory.processCompleted}"><s:message code="action.processingsummary.complete" /></s:if><s:else><s:message code="action.processingsummary.processing" /></s:else></span></dd>
					<dt><s:message code="action.processingsummary.requestedby" />:</dt>
					<dd><span><a href="mailto:<s:property value='createUser'/>">${smartpointHistory.createUser}</a></span></dd>
				</dl>
			</div>
	</div>
<!-- END Processing content -->