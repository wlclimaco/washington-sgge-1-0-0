<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- START - Device Type Details -->
<div id="device" class="point-detail-container">
    <h3><s:text name="smartpointdetail.tabs.about.hanDevices"/>   <a href="" class="edit right han-join"><s:text name="smartpointdetail.tabs.about.connectHanDevice"/></a></h3>
    <table id="han-devices" class="small-table">
    <thead>
        <tr>
            <th><s:text name="smartpointdetail.tabs.about.type"/></th>
            <th><s:text name="smartpointdetail.tabs.about.deviceId"/></th>
            <th><s:text name="smartpointdetail.tabs.about.status"/></th>
            <th><s:text name="smartpointdetail.tabs.about.lastStatus"/></th>
            <th></th>
        </tr>
    </thead>
    <tbody>                            

	</tbody>
   </table>
</div> 