<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- Start Groups -->
	<div id="groups" class="point-detail-container">
		<div class="current-monitored-event">
			<h3>
				<spring:message code="commons.pages.Groups"/> <sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
					<a href="" id="add-groups" class="edit right">
						<spring:message code="smartpointdetail.tabs.about.addSmartPointToGroup"/>
					</a>
				</sec:authorize>
			</h3>
		</div>
		<div class="blankslate" id="blankslate-group">
			<p><spring:message code="smartpointdetail.tabs.about.noGroupsWithThisSmartPoint"/></p>
		</div>
		<fieldset id="div-groups" class="edit-only two-line">
			<ul>
				<li class="ui-widget">
					<label for="groupAddSelect"><spring:message code="commons.pages.selectGrouplist"/> <span class="required">*</span></label> <label class="error hide group-add-error" generated="true" for="combo-tag" id="smartpoint-invalid-tag" style="display: inline;"></label><br/>

					<select name="combo_group" class="combobox" id="combo_group">
					</select>

					<label id="textValidationGroup" class="error hide tag-add-error" generated="true" for="combo-tag"></label>
				</li>
				<li class="submit-row">
					<input id="group-submit" type="submit" class="submit-short-form" value="<spring:message code="smartpointdetail.tabs.about.addSmartPointToGroup"/>" /> <spring:message code="commons.pages.or"/> <a href="" class="cancel-edit cancel"><spring:message code="commons.pages.cancel"/></a>
				</li>
			</ul>
		</fieldset>
		<table class="small-table">
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- end Groups -->

</sec:authorize>