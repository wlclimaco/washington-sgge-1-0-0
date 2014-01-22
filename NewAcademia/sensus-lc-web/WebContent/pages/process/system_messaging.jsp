<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

	<div id="system-messaging-list">
	    <ul>
	       <li class="message-title"><s:message code="messaging.process.title"></s:message></li>
		   <li id="rni-offline" class="system-message-label error rni-link" id="link-status"><a rel="black" class="rounded bubble" title="<s:message code="messaging.process.rniofflinemsg"></s:message>"><s:message code="messaging.process.rnioffline"></s:message> </a></li>
	       <li id="request-processing" class="system-message-label"><a href="#" class="processing rounded"><s:message code="action.recentRequest.title"></s:message> <span id="long-running-process-size-p" class="count rounded hide">${processSize}</span></a></li>
	       <li id="request-complete" class="system-message-label"><a href="#" class="rounded"><s:message code="action.recentRequest.title"></s:message> <span id="long-running-process-size-c" class="count rounded hide">${processSize}</span></a></li>
	       <li id="processing-size" class="hide">${processingSize}</li>
	    </ul>
	</div>