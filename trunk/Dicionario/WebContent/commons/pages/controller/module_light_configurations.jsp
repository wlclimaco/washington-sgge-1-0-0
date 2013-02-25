<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div id="configuration" class="point-detail-container configuration">
	<h3><s:message code="smartpointdetail.configuration" /></h3>
	<dl class="read-only">
		<dt><s:message code="smartpointdetail.configuration.listeningmode" />:</dt>
			<dd id="update-protect" class="locking"></dd>
		<dt><s:message code="smartpointdetail.configuration.poleid" />:</dt>
		<span>
			<form id="update-poleid">
				<dd class="read-only"><div class="left"><span id="pole-id"></span></div>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<a id="light-details-edit" href="#" class="button small right edit"><s:message code="smartpointdetail.location.edit"/></a>
					<dd class="edit-only hide"><input type="text" name="pole-id"  id="pole-id-input" value="${lightView.poleId}" /><label class="error hide"></label>
					<a id="light-details-save" href="#" class="button small save-pole-id"><s:message code="smartpointdetail.location.save"/></a>
					<s:message code="action.addtogroup.or"/> <a id="light-details-cancel" href="#" class="small cancel-edit cancel"><s:message code="smartpointdetail.actiongroups.cancel"/></a></dd>
				</sec:authorize>
				</dd>
			</form>
		</span>
		<dt><s:message code="smartpointdetail.configuration.lighttype" />:</dt>
		<dd><div class="left"><span id="update-light-type"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.voltage" />:</dt>
		<dd><div class="left"><span id="update-voltage-range"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.temperature" />:</dt>
		<dd><div class="left"><span id="update-temperature-color"></span></div></dd>
		<dt><s:message code="smartpointdetail.page.housingcolor" />:</dt>
		<dd><div class="left"><span id="update-housing"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.manufacturer" />:</dt>
		<dd><div class="left"><span id="update-manufacturer"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.modelnumber" />:</dt>
		<dd><div class="left"><span id="update-sensus-part-number"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.bulbserial" />:</dt>
		<dd><div class="left"><span id="update-bulb-serial-number"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.lightserial" />:</dt>
		<dd><div class="left"><span id="update-light-driver-serial"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.lowerserialnumber" />:</dt>
		<dd><div class="left"><span id="update-lower-serial-number"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.upperserialnumber" />:</dt>
		<dd><div class="left"><span id="update-upper-serial-number"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.added" />:</dt>
		<dd><div class="left"><span id="update-date-added"></span></div></dd>
		<dt><s:message code="smartpointdetail.configuration.firmware" />:</dt>
		<dd><div class="left"><span id="update-firmware"></span></div></dd>
	</dl>
</div>
<script src="commons/scripts/controller/module_light_configurations.js"></script>