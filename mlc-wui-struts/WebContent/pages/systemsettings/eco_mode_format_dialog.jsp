<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<div id="eco-mode-format-table" class="ss_widget_container">
	<table>
		<tr>
			<th>Field</th>
			<th>Type</th>
			<th>Description</th>
			<th>Example</th>
		</tr>
		<tr>
			<td>PoleId</td>
			<td>String (15 chars)</td>
			<td>Pole ID of the light being replaced. This field will be used as the key for calculation of energy savings.</td>
			<td>CHS-CLPWW018</td>
		</tr>
		<tr class="alt">
			<td>Wattage</td>
			<td>Number</td>
			<td>Wattage of the old lamp</td>
			<td>250</td>
		</tr>
		<tr>
			<td>Lamp Type</td>
			<td>String (15 chars)</td>
			<td>The lamp type being replaced. This string is only used for display purposes on the UI.</td>
			<td>HPS</td>
		</tr>
	</table>
	
</div>
</sec:authorize>