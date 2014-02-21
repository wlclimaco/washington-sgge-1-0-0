<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
 <style>
    .cell-effort-driven {
      text-align: center;
    }

    .slick-group-title[level='0'] {
      font-weight: bold;
    }

    .slick-group-title[level='1'] {
      text-decoration: underline;
    }

    .slick-group-title[level='2'] {
      font-style: italic;
    }
  </style>
<div id="process-main">
	<div class="content-inner">
	<div class="content-header">

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
						<a href="#" class="clear"><s:message code="smartpoint.filter.reset" /></a>
					</li>
				</ul>
			</div>
			<div class="yui-u results">
				<span id="total-records"></span>
				<span id="label-total-records"><s:message code="table.result.matches"/></span>
			</div>

		</div>
	</div>

	<%-- END Filter Bar --%>
    <div id="history" class="history-container">

		  <!-- START status viewport -->
          <div class="status-viewport">
				<%-- Blank Slate --%>
				<div id="blankslate" class="blankslate" style="display: none;">
				<h5><s:message code="widgets.blankslate.noresults" /></h5>
				<p><s:message code="widgets.blankslate.detail" /></p>
				</div>
				<!-- START status grid -->
				<div style="width:600px;">
				<div class="grid-header" style="width:100%">
				  <label>SlickGrid</label>
				</div>
				<div id="myGrid" style="width:100%;height:500px;"></div>
				<div id="pager" style="width:100%;height:20px;"></div>
			  </div>
            </div>
       </div>
     </div>
     </div>
 </div>
</div>
</div>

<%@ include file="../../scripts/pages/process/process_main.js.jsp" %>
<%@ include file="../../scripts/pages/process/process_actions.js.jsp" %>
<%@ include file="../../scripts/pages/process/process_init.js.jsp" %>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/exercicio/exercicio_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/exercicio/exercicio_init.js.jsp" flush="true"/>