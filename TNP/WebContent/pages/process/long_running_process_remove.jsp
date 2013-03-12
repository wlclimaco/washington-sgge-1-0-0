<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Long Running Process Remove dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<div class="action-dialog ui-dialog-content ui-widget-content" style="width: auto; min-height: 29px; height: auto;"><s:text name="longRunning.table.message.remove" /></div>
	<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
		<button type="button" class="button removelrp">
			<s:text name="longRunning.table.remove"/>
		</button>
		<button type="button" class="button cancellrp">
			<s:text name="longRunning.table.cancel"/>
		</button>
	</div>
</div>