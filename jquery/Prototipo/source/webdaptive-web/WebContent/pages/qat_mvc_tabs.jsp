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
</head>
<body>
<div id="qatmvctabs">
	<ul>
		<li><a href="../county/fetchCounties?view=full" title="qatmvctab-1"><span>Counties MVC</span></a></li>
        <sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		<li><a href="../county/fetchAllCountiesBAI" title="qatmvctab-2"><span>Counties MVC BAI</span></a></li>
		<li><a href="../county/fetchAllCountiesBAS" title="qatmvctab-3"><span>Counties MVC BAS</span></a></li>
		<li><a href="../procedure/fetchProceduresByRequestBAS" title="qatmvctab-4"><span>Procedures MVC BAS</span></a></li>
		<li><a href="../procedure/fetchFotoByRequestBAS" title="qatmvctab-5"><span>Insert Foto</span></a></li>
		</sec:authorize>
	</ul>
	<div class="tabscontent">
		<div id="qatmvctab-1">	</div>
		<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		<div id="qatmvctab-2"></div>
		<div id="qatmvctab-3"></div>
		<div id="qatmvctab-4"></div>
		<div id="qatmvctab-5"></div>
		</sec:authorize>
	</div>
</div>
<script type="text/javascript" src="../commons/scripts/util/wd_tabs_actions.js"></script>
</body>
</html>
