<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Add SmartPoints to Group Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="add-group-form" name="add_group_form" method="post"><!-- Messaging -->
		<h2>
			<span class="smartpoint-count"></span>&nbsp;
			<s:text name="action.addtogroup.label.smartpoints"></s:text>
		</h2>
		
		<%-- Messaging --%>
		<div id="action-messaging" class="messaging">
			<span class="message"></span>
		</div>
			
		<%-- Additional Fields --%>
		<fieldset class="two-line">
			<ul>
				<li class="ui-widget">
					<label for="group_add_select">
						<s:text name="action.addtogroup.label.grouphint"></s:text>&nbsp;
						<span class="required">*</span>
					</label> 
					<br />
					<div id="select-group">
						<s:select name="group_add_select" list="groupList" listKey="id" id="group-add-select" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" />
						
						<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
							<span class="note"><a class="add-new-group dark-link" href=""><s:text name="action.addgroup.label.addnewgroup" /></a></span>		
						</sec:authorize> 
						
					</div>
					
				</li>
				<span id="create-group-add-smartpoint" class="hide">
	                <li class="add-group-control">
	                    <label for="controlGroupNameCreate"><s:text name="action.addgroup.label.grouplabel" /> 
	                    <span class="required">*</span></label><br/>
	                    <input type="text" id="control-group-name-create" tabindex="1" class="required"/>
	                    <span class="note"><s:text name="action.addgroup.label.grouphint" /></span>
	                </li>
	                <li class="add-group-control">
	                    <label for="controlGroupDescription"><s:text name="action.addgroup.label.descrlabel" /></label><br/>
	                    <textarea id="control-group-description"></textarea>
	                </li>
                </span>
            </ul>
        </fieldset>
	</form>
</div>