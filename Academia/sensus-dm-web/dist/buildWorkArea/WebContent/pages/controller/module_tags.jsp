<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- Start Tags -->
	<div id="tags" class="point-detail-container">

		<div class="current-monitored-event">
			<h3>
				<spring:message code="smartpointdetail.tabs.about.tags"/>
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
					<a href="" id="add-tags" class="edit right"><spring:message code="smartpointdetail.tabs.about.addTagToSmartPoint"/></a>
				</sec:authorize>
			</h3>
		</div>

		<fieldset id="div-tags" class="edit-only two-line">
			<ul>
				<li class="ui-widget">
					<label for="tagAddSelect"><spring:message code="smartpoint.page.description"/> <span class="required">*</span></label>
					<br/>
					<div id="combo-tag" >
								<select name="tag_add_select_smartpoint" class="combobox" id="tag_add_select_smartpoint">
								</select>

						<label id="textValidationTag" class="error hide tag-add-error" generated="true" for="combo-tag"></label>
						<div class="yui-u"></div>
					</div>
				</li>

				<li class="submit-row">
								<input id="tag-submit" type="submit" class="submit-short-form" value="<spring:message code="smartpointdetail.tabs.about.addTagToSmartPoint"/>" />
								<spring:message code="commons.pages.or"/>
								<a href="" class="cancel-edit cancel"><spring:message code="commons.pages.cancel"/></a>
				</li>
			</ul>

			</fieldset>

			<div class="blankslate" id="blankslate-tags">
				<p><spring:message code="smartpointdetail.tabs.about.noTagsWithThisSmartPoint"/></p>
			</div>

			<ul class="tag-container">

			</ul>
	</div>
	<!-- End Tags -->

</sec:authorize>