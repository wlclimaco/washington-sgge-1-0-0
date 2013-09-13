<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="action-dialog-panel" class="action-dialog">
		<form id="connect-han-device-form" method="post" action="">
			   <fieldset class="two-line create-action-container" id="">
				<ul>
					<li>
						<label id="label-device-id" for="device_id" class="hide">
							<spring:message code="sensus.epm.enter.parent.id" /> <span class="required-small"><spring:message code="commons.pages.required" /></span>
							<label><br>
								<input type="text" class="mac" id="device_id" value=""><span class="validate-icon icon-small icn-notice-form hideImportant" id="mac_address_validate_icon"></span></label>
						</label>
						<label id="label-mac-address" for="mac_address" class="hide">
							<spring:message code="sensus.epm.enter.macAddres" /> <span class="required-small"><spring:message code="commons.pages.required" /></span>
							<label><br>
								<input type="text" value="11:11:11:11:11:" class="mac" id="mac_address"><span class="validate-icon icon-small icn-notice-form hideImportant" id="mac_address_validate_icon"></span>
							</label>
						</label>
					</li>
					<li class="floating-form-info hide" >
						<div class="point-detail-container highlight">
							<dl>
								<dt><spring:message code="smartpointdetail.dialog.deviceInformation.deviceType" /></dt>
								<dd id="device-type"></dd>
								<dt><spring:message code="commons.pages.device_id" />:</dt>
								<dd id="device-id"></dd>
								<dt><spring:message code="smartpointdetail.dialog.deviceInformation.manufacture" />:</dt>
								<dd id="manufacture"></dd>
								<dt><spring:message code="smartpointdetail.dialog.deviceInformation.modelNumber" />:</dt>
								<dd id="model-number"></dd>
								<dt class="hide"></dt>
								<dd id="customer-id" class="hide"></dd>
								<dd id="device-type-id" class="hide"></dd>
								<dd id="flexnetid" class="hide"></dd>
							</dl>
						</div>
					</li>
					<li class="add-fields hide">
						<ul>
							<li id="mac_id" class="highlight"></li>
							<li>
								   <label for=""><spring:message code="smartpointdetail.dialog.deviceInformation.deviceType" /> <span class="required-small">*</span><label><br>
								   <select id="select-device-types"></select>
							</label></label></li>
							<li>
								   <label for=""><spring:message code="commons.pages.device_id" />: <span class="required-small">*</span><label><br>
								   <input type="text" class="" id="device_name_input" value="">
							</label></label></li>
							<li>
								   <label for=""><spring:message code="smartpointdetail.dialog.deviceInformation.manufacture" />: <span class="required-small">*</span><label><br>
								   <select disabled="" id="select-manufacture"><option><spring:message code="commons.pages.select" /></option></select>
							</label></label></li>
							   <li>
								   <label for=""><spring:message code="smartpointdetail.dialog.deviceInformation.modelNumber" />: <span class="required-small">*</span><label><br>
								   <select disabled="" id="select-model-number"><option><spring:message code="commons.pages.select" /></option></select>
							   </label></label></li>
						   </ul>
					   </li>
					<li>
						<label for="install_code"><spring:message code="sensus.epm.enter.install.code" /><span class="required">*</span><label><br>
						   <input type="text" id="install_code" value="">
					   </label></label></li>
				   </ul>
				</fieldset>
		</form>
	</div>

</sec:authorize>