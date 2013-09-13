<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<%-- Add SmartPoints to Tag Action Dialog --%>
	<div id="action-dialog-panel" class="action-dialog">
	<form id="add_tag_form" name="add_tag_form" method="post">

	<%-- Messaging --%>
	<div id="action-messaging" class="messaging"><span
		class="message"></span></div>

	<%-- Additional Fields --%>
	<fieldset class="two-line">

	<ul>
		<li class="ui-widget"><label for="tag_add_select">

			<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
				<spring:message code="action.addtag.label.taghint" />&nbsp;
			</sec:authorize>

			<sec:authorize access="!hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
				<spring:message code="action.addtag.label.select_taghint" />&nbsp;
			</sec:authorize>

		<span class="required">*</span></label> <br />
		<select name="tag_add_select" list="tagList" listKey="id"
			id="tag_add_select" cssClass="combobox" listValue="value"
			multiple="false" size="1" required="false"></select><label id="validation"></label></li>
	</ul>

	<div class="highlight"></div>
	</fieldset>
	</form>
	</div>

</sec:authorize>