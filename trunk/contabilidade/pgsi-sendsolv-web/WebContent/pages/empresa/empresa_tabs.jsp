<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
		<a class="alist" href="organization">
			<span><s:message code="commons.pages.customers" text="Customers" /></span>
		</a>
		<span class="icon-nav icon-angle-right"></span>
		<a class="alist" href="view?organizationId=" id="parent-organization-link">

		</a>
		<span class="icon-nav icon-angle-right"></span>
		<span id="company-name"></span>
</nav>

<h2 id="company-name-field"></h2>

<div id="tabs">
	<ul>
		<li>
			<a href="#" id="infoTab" class="alist" data-tab="info" title='<s:message code="commons.organization.view.locationinfo" text="default text" />' data-title='<s:message code="commons.organization.view.locationinfo" text="default text" />'>
				Informacao
			</a>
		</li>
		</li>
		<li>
			<a href="#" id="funcionarioTab" class="alist" data-tab="funcionario" title='Funcionarios' data-title='Funcionarios'>
				Contador
			</a>
		</li>
		<li>
			<a href="#" id="xmlTab" class="alist" data-tab="xml" title='XMLs' data-title='XMLs'>
				XMLs
			</a>
		</li>
	</ul>
</div>

<jsp:include page="../../scripts/pages/empresa/empresa_tabs_init.js.jsp" flush="true" />