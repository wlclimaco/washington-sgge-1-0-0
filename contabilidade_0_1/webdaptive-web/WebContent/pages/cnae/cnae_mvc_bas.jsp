<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=utf-8");
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
	<div style="width:100%;height:100%;float:left;">
		<div class="grid-header" style="width:100%">
		    <span id="listcid" style="float:left; width: 16px; height: 16px; background-image:url('../images/text_list.png')" title="List Procedures"></span>
			<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		    <span id="refreshcid" style="float:right" class="ui-icon ui-icon-refresh" title="Rebuild Procedures"></span>
			</sec:authorize>
		</div>
		<div id="cidGrid" style="width:100%; height:95%;" class="wdgrid" ></div>
		<div id="pager" style="width:100%; height:3%;"></div>
	</div>
<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/widget/slick.pager.js"></script>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/cnae/cnae_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/cnae/cnae_mvc_bas_init.js.jsp" flush="true"/>
</body>
</html>