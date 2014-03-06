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
<div class="tools">
 <a tabindex="0" id="ajax-button" href="exercicio/exerciciocreate" class="fg-button ui-widget ui-state-default ui-corner-all group-create alist">Create Exercicio</a>
	<div class="export-select">
		<ul class="link-list">
			<li class="last export-type"><small><strong><s:message code="analytics.page.export" /></strong> <a href="" class="csv" id="csv"><s:message code="analytics.page.csv" /></a></small></li>
		</ul>
	</div>
</div>
    <div id="history" class="history-container">

		  <!-- START status viewport -->
          <div class="status-viewport">
				<%-- Blank Slate --%>
				<div id="blankslate" class="blankslate" style="display: none;">
				<h5><s:message code="widgets.blankslate.noresults" /></h5>
				<p><s:message code="widgets.blankslate.detail" /></p>
				</div>
				<!-- START status grid -->
				<div style="width:100%;">
				<div class="grid-header" style="width:100%">
				  <label>SlickGrid</label>
				</div>
				<div id="myGrid" style="width:100%;height:500px;"></div>
				<div id="pager" style="width:100%;height:20px;"></div>
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