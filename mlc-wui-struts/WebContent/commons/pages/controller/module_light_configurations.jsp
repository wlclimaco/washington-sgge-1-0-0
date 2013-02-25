<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<div id="configuration" class="point-detail-container configuration">
	<h3><s:text name="smartpointdetail.configuration" /></h3>
	<dl class="read-only">
		<dt><s:text name="smartpointdetail.configuration.listeningmode" />:</dt>
			<dd id="update-protect" class="locking"></dd>
		<dt><s:text name="smartpointdetail.configuration.poleid" />:</dt>
		<span>
			<form id="update-poleid">
				<dd class="read-only"><div class="left"><span id="pole-id"></span></div>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<a id="light-details-edit" href="#" class="button small right edit"><s:text name="smartpointdetail.location.edit"/></a>
					<dd class="edit-only hide"><input type="text" name="pole-id"  id="pole-id-input" value="${lightView.poleId}" /><label class="error hide"></label>
					<a id="light-details-save" href="#" class="button small save-pole-id"><s:text name="smartpointdetail.location.save"/></a>
					<s:text name="action.addtogroup.or"/> <a id="light-details-cancel" href="#" class="small cancel-edit cancel"><s:text name="smartpointdetail.actiongroups.cancel"/></a></dd>
				</sec:authorize>	
				</dd>
			</form>
		</span>						      
		<dt><s:text name="smartpointdetail.configuration.lighttype" />:</dt>
		<dd><div class="left"><span id="update-light-type"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.voltage" />:</dt>
		<dd><div class="left"><span id="update-voltage-range"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.temperature" />:</dt>
		<dd><div class="left"><span id="update-temperature-color"></span></div></dd>
		<dt><s:text name="smartpointdetail.page.housingcolor" />:</dt>
		<dd><div class="left"><span id="update-housing"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.manufacturer" />:</dt>
		<dd><div class="left"><span id="update-manufacturer"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.modelnumber" />:</dt>
		<dd><div class="left"><span id="update-sensus-part-number"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.bulbserial" />:</dt>
		<dd><div class="left"><span id="update-bulb-serial-number"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.lightserial" />:</dt>
		<dd><div class="left"><span id="update-light-driver-serial"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.lowerserialnumber" />:</dt>
		<dd><div class="left"><span id="update-lower-serial-number"></span></div></dd>		
		<dt><s:text name="smartpointdetail.configuration.upperserialnumber" />:</dt>
		<dd><div class="left"><span id="update-upper-serial-number"></span></div></dd>			
		<dt><s:text name="smartpointdetail.configuration.added" />:</dt>
		<dd><div class="left"><span id="update-date-added"></span></div></dd>
		<dt><s:text name="smartpointdetail.configuration.firmware" />:</dt>
		<dd><div class="left"><span id="update-firmware"></span></div></dd>
	</dl>                            
</div>
<script src="commons/scripts/controller/module_light_configurations.js"></script>