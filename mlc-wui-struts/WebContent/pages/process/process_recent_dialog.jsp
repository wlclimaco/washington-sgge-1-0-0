<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- Table long running Process -->
<div id="action-dialog-panel" class="action-dialog">
	<div id="processing-container">
		<p class="description"><s:text name="action.recentRequest.description" />
			<a class="alist event-history-process" href="process/ajax.processPageAction.action"><s:text name="action.recentRequest.eventHistory" /></a>
		</p>
		<div id="group-list" class="long-running">                                                      
			<div class="yui-ge tools">
				<div id="actions" class="actions yui-u first">
					<div class="yui-pad2"> 
						<a tabindex="0" id="remove-all-lrp" href="#" class="fg-button ui-widget ui-state-default ui-corner-all"><s:text name="action.recentRequest.removeAll" /></a>                                   
						<span class="message message-recent-request rounded"><span id="checked-count-dialog">0</span> <s:text name="action.recentRequest.requestedSelected" /></span>                                   
					</div>
				</div>
			</div>
				<table id="process-table" class="list">
					<thead>                            
						<tr>
							<th class="hide"><span id="process_id"><s:text name="longRunning.table.header.id" /></span></th>
							<th><span id="lc_action_id"><s:text name="longRunning.table.header.action" /></span></th>
							<th class="hide"></th>
							<th><span id="smartpoint_count"><s:text name="longRunning.table.header.smartpoint" /></span></th>
							<th class="hide"></th>
							<th><span id="description"><s:text name="longRunning.table.header.description" /></span></th>
							<th class="hide"><span id="create_user"><s:text name="longRunning.table.header.requestBy" /></span></th>
							<th><span id="start_datetime"><s:text name="longRunning.table.header.startTime" /></span></th>
							<th><span id="is_process_complete"><s:text name="longRunning.table.header.status" /></span></th>
							<th class="hide">&nbsp;</th>
							<th class="hide"></th>
							<th class="hide"></th>
							<th class="hide"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>             
				</table>
		</div>
	</div>
</div>