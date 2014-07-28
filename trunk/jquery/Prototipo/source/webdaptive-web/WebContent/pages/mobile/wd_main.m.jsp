<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title></title>
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
</head>
<body>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<!-- Home -->
<div data-role="page" id="wd_main.m">
    <div data-theme="b" data-role="header">
        <h3>Tia Chica Mobile</h3>
		<a href="<c:url value='/j_spring_security_logout'/>" rel="external" class="ui-btn-right">Logout</a>
    </div>
	<div data-role="content" class="ui-content" role="main">
		<ul data-role="listview" data-inset="true" class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-first-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#menu" class="ui-link-inherit"><i class="icon-doc"></i>Menu</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#submenu" class="ui-link-inherit"><i class="icon-location"></i> Sub Menu</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#trimenu" class="ui-link-inherit"><i class="icon-link"></i>Tri Menu</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#marca" class="ui-link-inherit"><i class="icon-link"></i>Marca</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#embalagem" class="ui-link-inherit"><i class="icon-link"></i>Embalagem</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#unimed" class="ui-link-inherit"><i class="icon-link"></i>Unidade de Medida</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#produto" class="ui-link-inherit"><i class="icon-link"></i>Produtos</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#supermercado" class="ui-link-inherit"><i class="icon-link"></i>Supermercado</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#cliente" class="ui-link-inherit"><i class="icon-link"></i>Clientes</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>
 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
		  <li data-corners="false" data-shadow="false" data-iconshadow="true" data-wrapperels="div" data-icon="arrow-r" data-iconpos="right" data-theme="c" class="ui-btn ui-btn-icon-right ui-li-has-arrow ui-li ui-last-child ui-btn-up-c"><div class="ui-btn-inner ui-li"><div class="ui-btn-text"><a href="#relatorio" class="ui-link-inherit"><i class="icon-link"></i>Relatorios</a></div><span class="ui-icon ui-icon-arrow-r ui-icon-shadow">&nbsp;</span></div></li>
 		</sec:authorize>




		</ul>
	</div>

    <div data-theme="b" data-role="footer">
        <h4>-TIA PECHINCHA All rights reserved.</h4>
    </div>
</div>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="menu" data-url="menu">
  <div data-theme="b" data-role="header">
    <h1>Menu</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchMenus?view=mobile" rel="external">Menu</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="submenu" data-url="submenu">
  <div data-theme="b" data-role="header">
    <h1>Sub Menu</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchSubMenus?view=mobile" rel="external">Sub Menu</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="trimenu" data-url="trimenu">
  <div data-theme="b" data-role="header">
    <h1>Tri Menu</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchTriMenus?view=mobile" rel="external">Tri Menu</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="marca" data-url="marca">
  <div data-theme="b" data-role="header">
    <h1>Marca</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchMarcas?view=mobile" rel="external">Marca</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="embalagem" data-url="embalagem">
  <div data-theme="b" data-role="header">
    <h1>Embalagem</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchEmbalagems?view=mobile" rel="external">Embalagem</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="unimed" data-url="unimed">
  <div data-theme="b" data-role="header">
    <h1>Unidade de Medida</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchUnimeds?view=mobile" rel="external">Uni Med</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="produto" data-url="produto">
  <div data-theme="b" data-role="header">
    <h1>Produto</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cadastro/fetchProdutos?view=mobile" rel="external">Produto</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="supermercado" data-url="supermercado">
  <div data-theme="b" data-role="header">
    <h1>Supermercado</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../supermercado/fetchSupermercados?view=mobile" rel="external">Supermercado</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="cliente" data-url="cliente">
  <div data-theme="b" data-role="header">
    <h1>Cliente</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../cliente/fetchClientes?view=mobile" rel="external">Clientes</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMINS')">
<!-- Samples -->
<div data-role="page" id="relatorio" data-url="relatorio">
  <div data-theme="b" data-role="header">
    <h1>Relatorios</h1>
    <a href="./wd_main.m.jsp" rel="external" data-icon="back" data-iconpos="left" data-direction="reverse" class="ui-btn-right">Voltar</a> </div>
  <div data-role="content">
    <ul data-role="listview" data-inset="true">
      <li><a href="../../controle/fetchTotaiss?view=mobile" rel="external">Totais Cadastros</a></li>
    </ul>
  </div>
</div>
</sec:authorize>

  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
</body>
</html>
