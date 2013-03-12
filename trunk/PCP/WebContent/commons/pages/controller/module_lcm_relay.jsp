<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- START - LCM ONLY -->
<div id="device-lcm-relay" class="point-detail-container">
 			<h3><s:text name="smartpointdetail.tabs.about.lcmRelay"/></h3>
	<div id="list">
			<div class="blankslate hide" id="blankslate-lcm-relay">
				<p><s:text name="smartpointdetail.tabs.about.noLcmRelay" /></p>
			</div>
		<table id="lcm-relay" class="small-table">
			<thead>
				<tr>
					<th><s:text name="smartpointdetail.tabs.about.relay"/></th>
					<th><s:text name="smartpointdetail.tabs.about.amp"/></th>
					<th><s:text name="smartpointdetail.tabs.about.intendedUse"/></th>
					<th><s:text name="smartpointdetail.tabs.about.used"/></th>
				</tr>
			</thead>
			<tbody>                            

			</tbody>
		</table>
	</div>
</div>