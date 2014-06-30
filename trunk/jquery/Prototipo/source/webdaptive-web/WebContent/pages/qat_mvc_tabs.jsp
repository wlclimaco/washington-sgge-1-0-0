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
		<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			<li><a href="../cidade/fetchCidadesByRequestBAS" title="qatmvctab-1"><span>Cidade</span></a></li>
	        <li><a href="../supermercado/fetchSupermercadosByRequestBAS" title="qatmvctab-2"><span>Supermercado</span></a></li>
	        <li><a href="../cliente/fetchClientesByRequestBAS" title="qatmvctab-3"><span>Cliente</span></a></li>
			<li><a href="../cadastro/fetcMenusByRequestBAS" title="qatmvctab-4"><span>Menu</span></a></li>
			<li><a href="../cadastro/fetchSubMenusByRequestBAS" title="qatmvctab-5"><span>SubMenu</span></a></li>
			<li><a href="../cadastro/fetchTriMenusByRequestBAS" title="qatmvctab-6"><span>TriMenu</span></a></li>
			<li><a href="../cadastro/fetchUniMEdByRequestBAS" title="qatmvctab-7"><span>Unidade Medida</span></a></li>
			<li><a href="../cadastro/fetchMarcasByRequestBAS" title="qatmvctab-8"><span>Marca</span></a></li>
			<li><a href="../produto/fetchProdutoByRequestBAS" title="qatmvctab-9"><span>Produto</span></a></li>
			<li><a href="../cidade/fetchCidadesByRequestBAS" title="qatmvctab-10"><span>Procedures MVC BAS</span></a></li>
			<li><a href="../cidade/fetchCidadesByRequestBAS" title="qatmvctab-11"><span>Insert Foto</span></a></li>
		</sec:authorize>
	</ul>
	<div class="tabscontent">
		<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			<div id="qatmvctab-1">	</div>
			<div id="qatmvctab-2"></div>
			<div id="qatmvctab-3"></div>
			<div id="qatmvctab-4"></div>
			<div id="qatmvctab-5"></div>
			<div id="qatmvctab-6">	</div>
			<div id="qatmvctab-7"></div>
			<div id="qatmvctab-8"></div>
			<div id="qatmvctab-9"></div>
			<div id="qatmvctab-10"></div>
			<div id="qatmvctab-11"></div>
		</sec:authorize>
	</div>
</div>
<script type="text/javascript" src="../commons/scripts/util/wd_tabs_actions.js"></script>
</body>
</html>
