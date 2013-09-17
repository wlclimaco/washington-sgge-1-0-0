<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="action_read_only_action" class="action-dialog-import">
		<form id="import-han-form" name="importHanForm" method="post" action="action/upload" enctype="multipart/form-data">

			<fieldset id="dr_fields_container" class="two-line create-action-container">
				<legend>
					<spring:message code="systemintelligence.importHan.dialog.title" />
				</legend>
				<p class="sui-pad">
					<spring:message code="systemintelligence.importHan.dialog.paragraph" />
				</p>
				<ul>
					<li class="highlight">
						<label for="upload-han"> <spring:message code="commons.pages.selectFile" /> <span class="required-small"><spring:message code="commons.pages.required" /></span></label>
						<br>
						<input id="upload-han" name="upload" class="validate[custom[onlyCsv]]" type="file"></li>
					<li>
						<input type="hidden" name="monitored" id="b-monitored-han"> </li>
					<li class="chzn-row select-tag">
						<label class="upload-tag">
							<spring:message code="systemintelligence.importHan.dialog.labelTag" /> <span class="small">
							<spring:message code="commons.pages.optional" /></span>
						</label>
						<br>
						<select id="select_tags" name="tagIds" multiple="" class="chzn-select"
							data-placeholder="<spring:message code="systemintelligence.importHan.dialog.labelSelectTag"/>">
						</select>
					</li>
				</ul>
			</fieldset>

		</form>
	</div>

</sec:authorize>