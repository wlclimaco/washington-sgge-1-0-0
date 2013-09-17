<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<%-- Remove Smartpoints from Group Action Dialog --%>
	<div id="action-dialog-panel" class="action-dialog">
	<form id="add-group-form" name="add_group_form" method="post">

	<%-- Messaging --%>
	<div id="action-messaging" class="messaging"><span
		class="message"></span></div>

	<%-- Additional Fields --%>
	<fieldset class="two-line">
	<ul>
		<li class="ui-widget"><label for="group_add_select"><spring:message
			code="action.removefromgroup.label.grouphint" />&nbsp;<span
			class="required">*</span></label> <br />
		<select name="group_add_select" list="groupList" listKey="id"
			id="group_add_select" cssClass="combobox" listValue="value"
			multiple="false" size="1" required="false" /></select></li>
	</ul>
	<div class="highlight"><span class="smartpoint-count"></span>&nbsp;<spring:message
		code="action.removefromgroup.label.smartpoints" /></div>
	</fieldset>
	</form>
	</div>

</sec:authorize>