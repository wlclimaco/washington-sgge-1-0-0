<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<%-- Add SmartPoints to Group Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="add-group-form" name="add_group_form" method="post"><!-- Messaging -->
		<h2>
			<span class="smartpoint-count"></span>&nbsp;
			<s:message code="action.addtogroup.label.smartpoints"/>
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
						<s:message code="action.addtogroup.label.grouphint"/>&nbsp;
						<span class="required">*</span>
					</label>
					<br />
					<div id="select-group">

						<select name="group_add_select" id="group-add-select" class="combobox"></select>

						<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
							<span class="note"><a class="add-new-group dark-link" href=""><s:message code="action.addgroup.label.addnewgroup" /></a></span>
						</sec:authorize>

					</div>

				</li>
				<span id="create-group-add-smartpoint" class="hide">
	                <li class="add-group-control">
	                    <label for="controlGroupNameCreate"><s:message code="action.addgroup.label.grouplabel" />
	                    <span class="required">*</span></label><br/>
	                    <input type="text" id="control-group-name-create" tabindex="1" class="required"/>
	                    <span class="note"><s:message code="action.addgroup.label.grouphint" /></span>
	                </li>
	                <li class="add-group-control">
	                    <label for="controlGroupDescription"><s:message code="action.addgroup.label.descrlabel" /></label><br/>
	                    <textarea id="control-group-description"></textarea>
	                </li>
                </span>
            </ul>
        </fieldset>
	</form>
</div>