 <%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all hide">
	<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
		<li id="communicationSummary" class="ui-state-default ui-corner-top"><a href="commons/pages/summary/communication_summary.jsp">Communication Summary</a></li>
		<li id="demandResponseSummary" class="ui-state-default ui-corner-top"><a href="commons/pages/summary/demand_response_summary.jsp">Demand Response Summary</a></li>
		<li id="importHanDevices" class="ui-state-default ui-corner-top"><a href="commons/pages/summary/import_han_devices.jsp">Import Han Devices</a></li>
	</ul>
	<button class="ui-button refresh" title="Refresh Data"><span class="ui-icon ui-icon-refresh">Refresh Data</span></button>  
	<div id="messaging-summary" class="messaging messaging-smartpoint-detail hide"><span class="message"></span><a href="" class="remove"><s:text name="commons.pages.close" /></a></div>

	<div id="content">
	
	</div>
</div>

<div id="summary-container" class="hide white">
	
</div>