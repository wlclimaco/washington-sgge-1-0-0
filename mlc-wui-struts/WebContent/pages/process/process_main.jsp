 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<jsp:include page="../../scripts/pages/process_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/process_actions.js.jsp" flush="true"/>

<div id="process-main">
	<div class="content-inner">
	<div class="content-header">
   	<h1><s:text name="process.page.header.title" /></h1>                                          
   </div>
       <div class="yui-t2">
       	<div class="yui-b">
			<div id="w-filters"></div>
		</div>
	<div id="yui-main">
	


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
						<a href="#" class="clear"><s:text name="smartpoint.filter.reset" /></a>
					</li>
				</ul>               
			</div>
			<div class="yui-u results">
				<span id="total-records"></span>
				<span id="label-total-records"><s:text name="table.result.matches"/></span>
			</div>

		</div>
	</div>
	

	
	<%-- END Filter Bar --%>	  
          <div id="history" class="history-container">

		  <!-- START status viewport -->
              <div class="status-viewport">
				<%-- Blank Slate --%>
				<div id="blankslate" class="blankslate" style="display: none;">
				<h5><s:text name="widgets.blankslate.noresults" /></h5>
				<p><s:text name="widgets.blankslate.detail" /></p>
				</div>
				<table id="event-history-table" class="list">
					<thead>
						<tr>
							<th class="hide"><span id="process.process_id"><s:text name="longRunning.table.header.id" /></span></th>
							<th><span id="lc_action_description"><s:text name="longRunning.table.header.action" /></span></th>
							<th class="hide"></th>
							<th><span id="smartpoint_count"><s:text name="longRunning.table.header.smartpoint" /></span></th>
							<th class="hide"></th>
							<th><s:text name="longRunning.table.header.description" /></th>
							<th><span id="create_user"><s:text name="longRunning.table.header.requestBy" /></span></th>
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
     </div>
 </div>
</div>
</div>