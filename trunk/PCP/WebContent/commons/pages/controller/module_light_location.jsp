<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!-- Start Address -->
<div id="detail-map-container"></div>
<div id="address" class="point-detail-container">
	<h3><div class="left"><s:message code="smartpointdetail.location" /></div>
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
		<a id="edit-location" href="#" class="edit right"><s:message code="smartpointdetail.location.editlocation"/></a>
	</sec:authorize>
	</h3>
	<dl class="read-only">
		<dt class="top"><s:message code="smartpointdetail.location.address" />:</dt>
		<dd class="top"><span id="location-address"></span></dd>
		<dt><s:message code="smartpointdetail.location.latitude" />:</dt>
		<dd><span id="location-latitude"></span>&deg;</dd>
		<dt class="bottom"><s:message code="smartpointdetail.location.longitude" />:</dt>
		<dd class="bottom"><span id="location-longitude"></span>&deg;</dd>
	</dl>

	<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
		<fieldset class="edit-only hide">
			<ul>
				<form id="update-coordinates-form">
					<li><label><s:message code="smartpointdetail.location.latitude" />:</label><input class="validate[required,custom[number],min[-90],max[90]]" name="latitude" id="latitude" type="text" class="short" value="${lightView.latitude}" /></li>
					<li><label><s:message code="smartpointdetail.location.longitude" />:</label><input class="validate[required,custom[number],min[-180],max[180]]" name="longitude" id="longitude" type="text" class="short" value="${lightView.longitude}" /></li>
					<li class="submit-row-pad">
						<input id="submit-edit" type="submit" value="<s:message code='smartpointdetail.location.editlocation'/>" /> <s:message code="action.addtogroup.or"/> <a id="cancel-edit" href="#" class="cancel-edit cancel"><s:message code="smartpointdetail.actiongroups.cancel"/></a>
					</li>
				</form>
			</ul>
		</fieldset>
	</sec:authorize>
</div>
<script src="commons/scripts/controller/module_light_location.js"></script>
<!-- End Address -->