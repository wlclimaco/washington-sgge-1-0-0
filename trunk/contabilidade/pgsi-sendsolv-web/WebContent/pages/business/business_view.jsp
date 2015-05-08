<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>


<div id="business-view">

	<div class="row location hide">
		<section class="parent-organization" >
			<h4 class="ui-subtitle">
				<s:message code="pages.business.view.parentorganization" text="default text" />
			</h4>
			<h2 id="parent-organization-name-field"></h2>
			<a href="" class="ui-subtitle alist" id="view-parent-organization" title='<s:message code="commons.pages.view" text="default text" />'>
				<span class="icon-small-button icon-nav icon-pencil"></span>
				<span><s:message code="commons.pages.view" text="default text" /></span>
			</a>
		</section>
	</div>

	<input type="hidden" name="business-id" id="business-id" value="" />
	<input type="hidden" name="business-name" id="business-name" value="" />
	<input type="hidden" name="business-type" id="business-type" value="" />

		<jsp:include page="../util/update_status.jsp" flush="true" />
		<hr>
	<div class="row">
		<section class="summary">
			<div class="col-title">
				<h4 class="ui-subtitle">
					<s:message code="pages.business.businesssummary" text="default text" />
				</h4>
				<a href="javascript:;" class="ui-subtitle" id="edit-business" title='<s:message code="commons.pages.edit" text="default text" />'>
					<span class="icon-small-button icon-nav icon-pencil"></span>
					<span><s:message code="commons.pages.edit" text="default text" /></span>
				</a>
			</div>

			<div class="column first">
				<span class="label organization hide">
					<s:message code="pages.business.view.dbaname" text="default text" />
				</span>
				<span class="text organization hide" id="dba-field"></span>

				<span class="label">Nome/Fantasia</span>
				<span class="text" id="nome-field"></span>

				<span class="label">CNPJ</span>
				<span class="text" id="cnpj-field"></span>

				<span class="label">Inscricao Municipal</span>
				<span class="text" id="im-field"></span>

				<span class="label">Inscricao Estadual</span>
				<span class="text" id="IE-field"></span>
			</div>

			<div class="column second">
				<span class="label">Regime</span>
				<span class="text" id="regime-field"></span>

				<span class="label"></span>
				<span class="text" id="migrant-members-field"></span>

				<span class="label organization hide"></span>
				<span class="text organization hide" id="total-locations-field"></span>

				<span class="text organization hide" id="enrolled-locations"></span>

				<span class="label location"></span>
				<span class="text location" id="enrolled-members"></span>

				<span class="label organization hide"></span>
				<span class="text organization hide" id="payroll-field"></span>

			</div>

			<div class="column third">
				<span class="text" id="street-address-line-1-field"></span>
				<span class="text" id="street-address-line-2-field"></span>
				<span class="text" id="street-address-line-3-field"></span>
				<span class="text" id="street-address-line-4-field"></span>
				<span class="text"><span id="city-field"></span><span id="state-province-region-field"></span>
				<span id="zip-postal-code-field"></span></span> <span class="text" id="country-field"></span>
				<div id="phone-container"></div>
				<div id="email-container"></div>
			</div>

		</section>
	</div>

	<div class="row">
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

	<div class="row">

		<section class="contact view">
			<div class="col-title links">
				<h4 class="ui-subtitle ">Contador</h4>
				<a href="0" class="ui-subtitle buttonContact add" id="add-contact" title='<s:message code="commons.pages.addnew" text="default text" />'>
					<span class="icon-small-button icon-nav icon-pencil add"></span>
					<span>Add Contador</span>
				</a>
			</div>

			<div class="container">
				<p class="empty"><s:message code="page.business.view.contact.empty" text="default text" /></p>
			</div>

		</section>
	</div>

	<div class="row">

		<section class="contact view">
			<div class="col-title links">
				<h4 class="ui-subtitle ">Produtos</h4>
				<a href="0" class="ui-subtitle buttonContact add" id="add-contact" title='<s:message code="commons.pages.addnew" text="default text" />'>
					<span class="icon-small-button icon-nav icon-pencil add"></span>
					<span>Add Produtos</span>
				</a>
			</div>

			<div class="container">
				<p class="empty"><s:message code="page.business.view.contact.empty" text="default text" /></p>
			</div>

		</section>
	</div>

	<div class="row">

		<section class="cnae view">
			<div class="col-title links">
				<h4 class="ui-subtitle ">Cnae</h4>
				<a href="0" class="ui-subtitle buttonContact add" id="add-contact" title='<s:message code="commons.pages.addnew" text="default text" />'>
					<span class="icon-small-button icon-nav icon-pencil add"></span>
					<span>Add Cnae</span>
				</a>
			</div>

			<div class="container">
				<p class="empty"><s:message code="page.business.view.contact.empty" text="default text" /></p>
			</div>

		</section>
	</div>


	<div class="row organization hide last">
		<section class="documents view">
			<div class="col-title">
				<h4 class="ui-subtitle"><s:message code="pages.business.view.documents" text="default text" /></h4>
				<div class="links">
					<a href="javascript:;" class="ui-subtitle" id="add-document" title='<s:message code="commons.pages.addnew" text="default text" />'>
						<span class="icon-small-button icon-nav icon-plus"></span>
						<span><s:message code="commons.pages.adddocument" text="Add Document" /></span>
					</a>
				</div>
			</div>
			<div class="container"></div>
		</section>
	</div>

</div>
<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_view_init.js.jsp" flush="true" />


