<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>


    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<ul class="breadcrumb">
				<li>
					<a href="#">Home</a> <span class="divider">/</span>
				</li>
				<li>
					<a href="#">Library</a> <span class="divider">/</span>
				</li>
				<li class="active">
					Data
				</li>
			</ul>
			<div class="page-header">
				<h1>
					<small id="nome-empresa"></small>
				</h1>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span class="label organization hide">
					<s:message code="pages.business.view.dbaname" text="default text" />
					</span>

					<span class="text organization hide" id="dba-field"></span>

					<span class="label label-default">Nome/Fantasia</span>
					<span class="text" id="nome-field"></span><br>

					<span class="label label-default" id="cnpj">CNPJ</span>
					<span class="text" id="cnpj-field"></span><br>

					<span class="label label-default" id="im">Inscricao Municipal</span>
					<span class="text" id="im-field"></span><br>

					<span class="label label-default" id="ie">Inscricao Estadual</span>
					<span class="text" id="IE-field"></span><br>
				</div>
				<div class="col-md-4">

					<span class="label label-default">Pessoa :</span>
					<span class="text" id="pessoa-tipo-field"></span><br>

					<span class="label label-default">Nome Pai :</span>
					<span class="text" id="migrant-members-field"></span><br>

					<span class="label label-default">Nome da Mae :</span>
					<span class="text organization hide" id="total-locations-field"></span><br>

					<span class="label label-default">Estado Civil :</span>
					<span class="text location" id="enrolled-members"></span><br>

				</div>
				<div class="col-md-4">
					<span class="label label-default">Endereço</span> <br>
					<span class="text" id="street-address-line-1-field"></span>
					<span class="text" id="street-address-line-2-field"></span>
					<span class="text" id="street-address-line-3-field"></span>
					<span class="text" id="street-address-line-4-field"></span>
					<span class="text"><span id="city-field"></span><span id="state-province-region-field"></span>
					<span id="zip-postal-code-field"></span></span> <span class="text" id="country-field"></span>
					<span class="label label-default">Telefone</span> <br>
					<div id="phone-container"></div>
					<span class="label label-default">Email</span> <br>
					<div id="email-container"></div>
				</div>
			</div>
		<div id="selection">
			<div class="row">
				<div class="col-md-12">
					<section class="notes view">
						<div class="col-title">
							<h4 class="ui-subtitle"><s:message code="pages.view.notes" text="default text" /></h4>
							<div class="viewNote">
							<a href="javascript:;" class="ui-subtitle add-note" id="add-note" title='<s:message code="commons.pages.addnote" text="default text" />'>
								<span class="icon-small-button icon-nav icon-plus"></span>
								<span><s:message code="commons.pages.addnote" text="default text" /></span>
							</a>
							</div>
						</div>
						<div class="container">
							<p class="empty"><s:message code="page.business.view.note.empty" text="default text" /></p>
						</div>

					</section>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<section class="Convenio view">
						<div class="col-title links">
							<h4 class="ui-subtitle ">Convenio</h4>
							<a href="javascript:;" class="ui-subtitle buttonContact add" id="add-plano" title='<s:message code="commons.pages.addnew" text="default text" />'>
								<span class="icon-small-button icon-nav icon-pencil add"></span>
								<span>Convenio</span>
							</a>
						</div>

						<div class="container">
							<p class="empty"><s:message code="page.business.view.contact.empty" text="default text" /></p>
						</div>

					</section>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
<jsp:include page="../../scripts/pages/pessoa/pessoa_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/pessoa/pessoa_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/pessoa/pessoa_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/pessoa/pessoa_view_init.js.jsp" flush="true" />


