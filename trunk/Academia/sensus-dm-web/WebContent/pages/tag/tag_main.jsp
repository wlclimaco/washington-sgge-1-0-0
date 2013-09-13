<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="tag-main">

		<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
			<div class="tools tools-tag">
				<fieldset>
					<ul>
						<li class="chzn-row select-group">
							<select id="select-tag" class="chzn-select" data-placeholder="<spring:message code="tag.page.addTag" />"></select>
						</li>
					</ul>
				</fieldset>
			</div>
		</sec:authorize>

		<!-- Blankslate -->
		<div id="blankslate2" class="blankslate hide">
			<h5>
				<spring:message code="tag.page.notag" text="default text" />
			</h5>

			<p><spring:message code="tag.page.addFirstTag" text="default text" /></p>
		</div>

		<table id="tags-table" class="small-table">
			<thead>
				<tr>
					<th class="hide"><span><spring:message code="commons.pages.id" /></span></th>
					<th><span id="name"><strong><spring:message code="commons.pages.tag" /></strong></span></th>
					<th><span><strong><spring:message code="commons.pages.smartPoints" /></strong></span></th>
				</tr>
			 </thead>
			 <tbody>

			 </tbody>
		</table>
	</div>

	<script src="scripts/pages/tag/tag_main.js"></script>
	<jsp:include page="../../scripts/pages/tag/tag_init.js.jsp" flush="true" />
		
</sec:authorize>