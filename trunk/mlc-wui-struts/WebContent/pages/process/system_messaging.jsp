<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

	<div id="system-messaging-list">
	    <ul>
	       <li class="message-title"><s:text name="messaging.process.title"></s:text></li>
		   <li id="rni-offline" class="system-message-label error rni-link" id="link-status"><a rel="black" class="rounded bubble" title="<s:text name="messaging.process.rniofflinemsg"></s:text>"><s:text name="messaging.process.rnioffline"></s:text> </a></li>
	       <li id="request-processing" class="system-message-label"><a href="#" class="processing rounded"><s:text name="action.recentRequest.title"></s:text> <span id="long-running-process-size-p" class="count rounded hide">${processSize}</span></a></li>
	       <li id="request-complete" class="system-message-label"><a href="#" class="rounded"><s:text name="action.recentRequest.title"></s:text> <span id="long-running-process-size-c" class="count rounded hide">${processSize}</span></a></li>
	       <li id="processing-size" class="hide">${processingSize}</li>   
	    </ul>
	</div> 