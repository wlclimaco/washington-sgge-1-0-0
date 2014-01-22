<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%-- Remove SmartPoints to Tag Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="removeTagForm" name="createTagForm" method="post" >
    <%-- Messaging --%>
	<div id="action-messaging" class="messaging"><span class="message"></span></div>
    <fieldset class="two-line chzn-container">
            <ul>
                    <li class="chzn-row select-tag">
                        <label for="tagAddSelect"><small><s:message code="smartpoint.tags.example"/></small><span class="required">*</span></label> <br/>
						<select data-placeholder="Select Tags"  multiple class="chzn-select" id="select_tags" name="select_tags"></select>
                    </li>
                    <li>
						<p class="highlight sui-pad dialog-instructions"><s:message code="smartpoint.tags.description1"/> <strong><s:message code="smartpoint.tags.removed"/></strong> <s:message code="smartpoint.tags.description4"/> <span class="smartpoint-count">0</span> <s:message code="smartpoint.tags.description3"/></p>
                    </li>
                </ul>
	 </fieldset>
</form>
</div>