<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Long Running Process dialog --%>
<div id="action-dialog-panel" class="action-dialog">
    <h2><s:text name="action.longRunningProcessDialog.title"></s:text></h2>
    <div class="descriptive-button-row">          
	    <p></p>
        <a href="" class="button monitor"><s:text name="action.longRunningProcessDialog.submit"></s:text></a>
        <a href="" class="button dismiss"><s:text name="action.longRunningProcessDialog.cancel"></s:text></a>
    </div>
	<div class="highlight"><small><input type="checkbox" id="monitor-request"/> <s:text name="action.longRunningProcessDialog.highlight"></s:text></small></div>
</div>