<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div id="tabs">
	<ul>
		<li>
			<a href="#" id="infoTab" data-tab="info" title='<s:message code="commons.organization.view.locationinfo" text="default text" />' data-title='<s:message code="commons.organization.view.locationinfo" text="default text" />'>
				<s:message code="commons.organization.view.locationinfo" text="Location Info" />
			</a>
		</li>
		</li>
		<li>
			<a href="#" id="pricingTab" data-tab="pricing" title='Contatos' data-title='Contatos'>
				Contatos
			</a>
		</li>
		<li>
			<a href="#" id="pricingTab" data-tab="pricing" title='Contatos' data-title='Contatos'>
				Historico Compras
			</a>
		</li>
		<li>
			<a href="#" id="pricingTab" data-tab="pricing" title='Contatos' data-title='Contatos'>
				Titulos Pagar
			</a>
		</li>
	</ul>
</div>

<jsp:include page="../../scripts/pages/cliente/cliente_tabs_init.js.jsp" flush="true" />