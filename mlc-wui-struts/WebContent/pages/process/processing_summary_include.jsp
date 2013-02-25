<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- START Processing content -->
	<div id="processing-summary-container" class="point-detail-container">
		  <div id="detail">
				<dl class="summary">
					<dt><s:text name="action.processingsummary.id" />:</dt>
					<dd><span>${smartpointHistory.processId}</span></dd>
					<dt><s:text name="action.processingsummary.action" />:</dt>
					<dd><span>${smartpointHistory.name}</span></dd>
					<dt><s:text name="action.processingsummary.description" />:</dt>
					<dd><span>${smartpointHistory.description}</span></dd>
					<dt><s:text name="action.processingsummary.starttime" />:</dt>
					<dd><span>${smartpointHistory.time}</span></dd>
					<dt><s:text name="action.processingsummary.completedin" />:</dt>
					<dd><span>${smartpointHistory.processCompletedIn}</span></dd>
					<dt><s:text name="action.processingsummary.status" />:</dt>
					<dd><span><s:if test="%{smartpointHistory.processCompleted}"><s:text name="action.processingsummary.complete" /></s:if><s:else><s:text name="action.processingsummary.processing" /></s:else></span></dd>
					<dt><s:text name="action.processingsummary.requestedby" />:</dt>
					<dd><span><a href="mailto:<s:property value='createUser'/>">${smartpointHistory.createUser}</a></span></dd>
				</dl>                       
			</div>                                    
	</div>
<!-- END Processing content -->