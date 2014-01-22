<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Delete Group Dialog --%>
<div id="action-dialog-delete" class="action-dialog">
	<div id="action-group-new-dialog" class="action-dialog">
		<form id="add-group-new-form" name="addGroupNewForm" method="post" >
		    <!-- Messaging -->
		    <h2 id="auto-no-group-message"></h2>
		    <%-- Messaging --%>
			<div id="action-messaging" class="messaging hide"><span
			class="message"></span></div>
			<fieldset class="two-line">
                <ul>
                    <li class="add-group-control">
                        <label for="controlGroupNameCreate2"><s:message code="action.autogroup.groupName"/> <strong id="auto-no-group-name"></strong></label>
                    </li>
                    <li class="add-group-control">
                        <label for="controlGroupDescription2"><s:message code="action.autonogroup.description"/></label><br/>
                        <textarea  id="control-group-description2"></textarea>
                    </li>
                </ul>
                <div class="highlight"><small id="include-smarpoints-msg"><input class="include-smartpoints-to-group" type="checkbox"> </small></div>
			 </fieldset>
		</form>
	</div>
</div>