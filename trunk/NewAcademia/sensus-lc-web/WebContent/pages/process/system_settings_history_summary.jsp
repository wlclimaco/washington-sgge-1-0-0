<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Delete Group Dialog --%>
<div id="action-dialog-delete" class="action-dialog">
	<div id="processing-summary-container">
    <ul class="ui-state-highlight summary">
        <li><em><s:message code="action.summary.completed"/> ${completedTime}</em></li>
        <li class="success"><span class="icon-small"></span>${successProcess} (${successProcessPercent}%) <s:message code="action.summary.succeeded"/>
        <s:if test="%{successProcess > 0}">
        	<small><a href="" class="export-success launch export-summary"><s:message code="widgets.export.label"/>(.csv)</a></small>
        </s:if>
        </li>
        <s:if test="%{failProcess > 0}">
        	<li class="fail"><span class="icon-small"></span>${failProcess} (${failProcessPercent}%) <s:message code="action.summary.failed"/>  <small> <a href=""class="export-summary export-fail launch"><s:message code="widgets.exportall.label"/> (.csv)</a></small>
	            <ul>
	                <!-- <li>20 were unreachable <small><a href="" class="launch"><s:message code="widgets.export.label"/>(.csv)</a></small></li>
	                <li>28 were already members of &ldquo;PDX Airport&rdquo; <small><a href="" class="launch"><s:message code="widgets.export.label"/>(.csv)</a></small></li>-->
	                <s:iterator value="%{listFailProcess}">
	                	<s:if test="%{key == 'smpProtected'}">
	                		<li><s:property value="value"/><small><a href="" class="export-summary export-fail export-protected launch "><s:message code="widgets.export.label"/>(.csv)</a></small></li>
	                	</s:if>
	                	<s:elseif test="%{key == 'sync'}">
	                		<li><s:property value="value"/><small><a href="" class="export-summary export-fail export-sync launch "><s:message code="widgets.export.label"/>(.csv)</a></small></li>
	                	</s:elseif>
	                	<s:elseif test="%{key == 'deactivated'}">
	                		<li><s:property value="value"/><small><a href="" class="export-summary export-fail export-deactivated launch "><s:message code="widgets.export.label"/>(.csv)</a></small></li>
	                	</s:elseif>
	                	<s:elseif test="%{key == 'maintenance'}">
	                		<li><s:property value="value"/><small><a href="" class="export-summary export-fail export-maintenance launch "><s:message code="widgets.export.label"/>(.csv)</a></small></li>
	                	</s:elseif>
	                	<s:if test="%{key == 'async'}">
		                	<s:if test="%{isEqualUserLogged == false}">
		                		<sec:authorize ifAllGranted="ROLE_Role.Admin">
		                			<li><s:property value="value"/> <span id="retry-process" class="button"><s:message code="action.summary.retry"/></span><small><a href="" class="export-summary export-fail export-async launch"><s:message code="widgets.export.label"/>(.csv)</a></small></li>
		                		</sec:authorize>
		                	</s:if>
		                	<s:else>
		                		<li><s:property value="value"/> <span id="retry-process" class="button"><s:message code="action.summary.retry"/></span><small><a href="" class="export-summary export-fail export-async launch"><s:message code="widgets.export.label"/>(.csv)</a></small></li>
		                	</s:else>
	                	</s:if>
	                	<s:elseif test="%{key == 'aborted'}">
	                		<li><s:property value="value"/><small><a href="" class="export-summary export-fail export-aborted launch"><s:message code="widgets.export.label"/>(.csv)</a></small></li>
	                	</s:elseif>
	                </s:iterator>
	            </ul>
        	</li>
        </s:if>
    </ul>
</div>
</div>