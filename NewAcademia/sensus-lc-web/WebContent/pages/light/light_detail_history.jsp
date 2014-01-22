<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


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
                    	<li class="last export-type"><small><strong><s:message code="analytics.page.export" /></strong> <a href="" class="csv" id="csv"><s:message code="analytics.page.csv" /></a></small></li>
                    </ul>
                </div>
    	    </div>

				<%-- START Filter Bar --%>
					<div class="filter-results-container">
						<div class="yui-ge">
							<div class="yui-u first">
								<ul class="filter-container">
									<li id='tag-clear'>
										<a href="#" class="clear reset-history"><s:message code="smartpoint.filter.reset" /></a>
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
								<h5><s:message code="widgets.blankslate.noresults" /></h5>
								<p><s:message code="widgets.blankslate.detail" /></p>
							</div>
								<table id="light-history-table" class="list">
									<thead>
										<tr>
											<th class="hide"><span id="id"><s:message code="longRunning.table.header.id" /></span></th>
											<th><span id="lc_action_description"><s:message code="longRunning.table.header.action" /></span></th>
											<th><s:message code="longRunning.table.header.description" /></th>
											<th><span id="create_user"><s:message code="longRunning.table.header.requestBy" /></span></th>
											<th><span id="start_datetime"><s:message code="longRunning.table.header.startTime" /></span></th>
											<th><span id="is_process_complete"><s:message code="longRunning.table.header.status" /></span></th>
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

<%@ include file="../../scripts/pages/light/light_detail_history_main.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_detail_actions.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_detail_history_init.js.jsp" %>