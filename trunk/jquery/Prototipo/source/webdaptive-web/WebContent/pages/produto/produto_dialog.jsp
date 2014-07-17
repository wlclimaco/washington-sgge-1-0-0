<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" href="../styles/common_grid.css" rel="stylesheet" />
<link type="text/css" href="../styles/slick.pager.css" rel="stylesheet" />
</head>
<body>
	<div id="actions" class="actions yui-u first">
	     <div class="yui-pad">
	         <a tabindex="1" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button"><span class="ui-icon ui-icon-triangle-1-s"></span>Actions</a>
	           <div id="actions-options" class="hidden">
	                 <ul>
	                     <li><a href="#" class="action-option" id="header-configuration">Demand Reset</a>
	                     	<ul>
	                             <!--<li><a href="#" class="action-option" id="dr-schedule">Preset Demand Reset</a>-->
	                             <li><a href="#" class="action-option" id="dr-initiate">Initiate Demand Reset</a>
	                         </ul>
	                     </li>
	                     <!--<li><a href="#" class="action-option" id="header-group">TOU Delivery Analysis</a>-->
	                      <li><a href="#" class="action-option" id="header-group">Groups</a>
	                      	<ul>
	                              <li><a href="#" class="action-option" id="add-group">Add to Group</a></li>
	                        <li><a href="" id="remove-group">Remove from Group</a></li>
	                          </ul>
	                      </li>
	                      <li><a href="#" class="action-option" id="header-tag">Tags</a>
	                      	<ul>
	                              <li><a href="" id="add-tag">Add Tag</a></li>
	                              <li><a href="" id="remove-tag">Remove Tag</a></li>
	                          </ul>
	                      </li>
	                  </ul>
	              </div>
	              <span class="message rounded"><span class="checked-count">0</span> SmartPoints Selected</span>
	       </div>
	</div>
	 <div class="export-select">
	     <ul class="link-list">
	         <li class="last export-type"><small><strong>Export</strong>:<a href="" class="csv">CSV</a></small></li>
	     </ul>
	 </div>
	<div style="width:100%;height:100%;float:left;">
		<div class="grid-header" style="width:100%">
		    <span id="listprodDialog" style="float:left; width: 16px; height: 16px; background-image:url('../images/text_list.png')" title="List Procedures"></span>
			<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		    <span id="refreshprodDialog" style="float:right" class="ui-icon ui-icon-refresh" title="Rebuild Procedures"></span>
			</sec:authorize>
		</div>
		<div id="prodGridDialog" style="width:100%; height:95%;" class="wdgrid" ></div>
		<div id="pager" style="width:100%; height:3%;"></div>
	</div>
<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/widget/slick.pager.js"></script>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/produto/produto_dialog_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/produto/produto_dialog_mvc_bas_init.js.jsp" flush="true"/>
</body>
</html>