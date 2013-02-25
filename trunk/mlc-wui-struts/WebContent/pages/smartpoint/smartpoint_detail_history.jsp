<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="light-history-general">           
	<div id="history" class="history-container">
    	<div class="yui-t2">
       		<div class="yui-b">
				<div id="w-filters"></div>
			</div>
			<div id="yui-main" class="clearfix">
				<div id="history-box" class="yui-b">
      		<div class="tools">
            	<div class="export-select">
                	<ul class="link-list">
                    	<li class="last export-type"><small><strong><s:text name="analytics.page.export" /></strong> <a href="" class="csv" id="csv"><s:text name="analytics.page.csv" /></a></small></li>
                    </ul>
                </div>							                          					
    	    </div>				
				
				<%-- START Filter Bar --%>
					<div class="filter-results-container">
						<div class="yui-ge">
							<div class="yui-u first">
								<ul class="filter-container">
									<li id='tag-clear'>
										<a href="#" class="clear reset-history"><s:text name="smartpoint.filter.reset" /></a>
									</li>
								</ul>               
							</div>				
						</div>
					</div>
										
				<%-- END Filter Bar --%>					
				
					<div id="light-history">		
						<div class="status-viewport">
							<%-- Blank Slate --%>
							<div id="blankslate" class="blankslate" style="display: none;">
								<h5><s:text name="widgets.blankslate.noresults" /></h5>
								<p><s:text name="widgets.blankslate.detail" /></p>
							</div>
								<table id="light-history-table" class="list">
									<thead>
										<tr>
											<th class="hide"><span id="id"><s:text name="longRunning.table.header.id" /></span></th>
											<th><span id="lc_action_description"><s:text name="longRunning.table.header.action" /></span></th>
											<th><s:text name="longRunning.table.header.description" /></th>
											<th><span id="create_user"><s:text name="longRunning.table.header.requestBy" /></span></th>
											<th><span id="start_datetime"><s:text name="longRunning.table.header.startTime" /></span></th>
											<th><span id="is_process_complete"><s:text name="longRunning.table.header.status" /></span></th>
											<th class="hide"><span id="status_message_id"></span></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>        
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>