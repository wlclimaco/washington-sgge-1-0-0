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
</head>
<body>
	<div style="width:100%;height:100%;float:left;">
		<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
		<div class="grid-header" style="width:100%">
		    <span id="refreshcountiesREST" style="float:right" class="ui-icon ui-icon-refresh" title="Rebuild Counties"></span>
		</div>
		</sec:authorize>
		<div id="countiesGridREST" style="width:100%; height:67%;" class="wdgrid"></div>
		<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
		<form id="cform_rest" action="">
			<fieldset>
			<legend id="clegend_rest"></legend>
			  <p>
				<label id="lcid_rest" for="cid_rest"></label>
				<input id="cid_rest" name="cid_rest" type="text"/>
			  </p>
			  <p>
				<label id="lcdesc_rest" for="cdesc_rest"></label>
				<input id="cdesc_rest" name="cdesc_rest" type="text"/>
			  </p>
			  <p>
				<button id="insert_rest" class="button1"></button><button id="update_rest" class="button2"></button><button id="list_rest" class="cancel"></button><button id="delete_rest" class="button2"></button><button id="clear_rest" class="cancel"></button>
			  </p>
			</fieldset>
		</form>
		</sec:authorize>
	</div>
<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/counties/counties_bas_rest_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/counties/counties_bas_rest_init.js.jsp" flush="true"/>
</body>
</html>
