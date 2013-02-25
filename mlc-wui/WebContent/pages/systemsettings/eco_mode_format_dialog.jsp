<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<div id="eco-mode-format-table" class="ss_widget_container">
	<table>
		<tr>
			<th><s:message code="systemsettings.ecomode.page.field" /></th>
			<th><s:message code="systemsettings.ecomode.page.type" /></th>
			<th><s:message code="systemsettings.ecomode.page.description" /></th>
			<th><s:message code="systemsettings.ecomode.page.example" /></th>
		</tr>
		<tr>
			<td><s:message code="systemsettings.ecomode.page.poleid" /></td>
			<td><s:message code="systemsettings.ecomode.page.string" /></td>
			<td><s:message code="systemsettings.ecomode.page.poleidreplaced" /></td>
			<td><s:message code="systemsettings.ecomode.page.chsclpww" /></td>
		</tr>
		<tr class="alt">
			<td><s:message code="systemsettings.ecomode.page.wattage" /></td>
			<td><s:message code="systemsettings.ecomode.page.number" /></td>
			<td><s:message code="systemsettings.ecomode.page.oldlampwattage" /></td>
			<td>250</td>
		</tr>
		<tr>
			<td><s:message code="systemsettings.ecomode.page.lamptype" /></td>
			<td><s:message code="systemsettings.ecomode.page.string" /></td>
			<td><s:message code="systemsettings.ecomode.page.lamptypereplaced" /></td>
			<td><s:message code="systemsettings.ecomode.page.hps" /></td>
		</tr>
	</table>
</div>
</sec:authorize>