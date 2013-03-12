<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- START - Device Type Details -->
<div id="device" class="point-detail-container">
	
	<h3><s:text name="smartpointdetail.tabs.about.smartpointInformation"/></h3>

	<table class="small-table">
		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.meterType"/>:
			</td>
			<td>
				<span id="meter-type-value"></span>
			</td>
		</tr>
		
		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.meterFirmWare"/>:
			</td>
			<td>
				<span id="meter-firmware-value"></span>
			</td>
		</tr>
		
		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.flexNetFirmWare"/>:
			</td>
			<td>
				<span id="flexnet-firmware-value"></span>
			</td>
		</tr>

		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.state"/>:
			</td>

			<td>
				<span id="meter-state"></span>
			</td>
		</tr>

		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.installedDate"/>:
			</td>

			<td id="installed-date">
				<span id="meter-installed-date"></span>
			</td>
		</tr>

		<tr>
			<td>
				<s:text name="smartpointdetail.tabs.about.encryption"/>:
			</td>
				
			<td>
				<span id="meter-encryption"></span>
			</td>
		</tr>
	</table>

</div>
<!-- End - Device Type Details -->