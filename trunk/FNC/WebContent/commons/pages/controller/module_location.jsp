<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- Start Address -->
<div id="address" class="point-detail-container">
	
	<div class="current-monitored-event">
		<h3>
			<s:text name="smartpointdetail.tabs.about.location"/>   
			<%-- <sec:authorize access="hasAnyRole('ROLE_Role.EPM_ADMIN', 'ROLE_Role.EPM_SYSTEM_OPERATOR')"> --%>
				<%-- <s:text name="smartpointdetail.tabs.about.editLocation"/> --%>
			<%-- </sec:authorize> --%>
		</h3>
	</div>

	<div id="smallMap"></div>
	<table class="small-table">
		<tr>
			<td>
				<s:text name="commons.pages.address"/>:
			</td>
			<td>
				<span id="address-val"></span>
			</td>
		</tr>
		<tr>
			<td>
				<s:text name="commons.pages.latitude"/>:
			</td>
			<td>
				<span id="lat-val"></span>&deg;
			</td>
		</tr>
		<tr>
			<td>
				<s:text name="commons.pages.longitude"/>:
			</td>
			<td>
				<span id="long-val"></span>&deg;
			</td>
		</tr>
		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.timeZone"/>:
			</td>
			<td>
				<span id="timeZone"></span>
			</td>
		</tr>
	</table>
</div>
<!-- End Address -->