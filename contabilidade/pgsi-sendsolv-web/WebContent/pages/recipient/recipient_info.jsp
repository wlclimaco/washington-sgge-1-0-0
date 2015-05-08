<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div id="person_info">
		<jsp:include page="../util/update_status.jsp" flush="true" />
		<hr>
		<div class="col-info">
			<h3><s:message code='pages.member.view.personalinfo' text='default text' /></h3>
			<a href="javascript:;" title="<s:message code='pages.member.view.editpersonalinfo' text='default text' />" id="link-edit-personalinfo">
				<span class="icon-small-button icon-nav icon-pencil"></span>
				<s:message code='pages.member.view.edit' text='default text' />
			</a>
			<div class="col1">
				<div class="newline all" id="info-name"></div>
				<div class="newline label" id="info-other-names">
					<ul>
					</ul>
				</div>

			</div>
			<div class="col2">

				<div class="wrapper address">
					<div class="newline all normal">
						<ul>
							<li class="newline all normal" id="street-address-line-1-field"></li>
							<li class="newline all normal" id="street-address-line-2-field"></li>
							<li class="newline all normal" id="street-address-line-3-field"></li>
							<li class="newline all normal" id="street-address-line-4-field"></li>
						</ul>
					</div>
					<div class="newline all normal"><span id="city-field"></span><span id="state-province-region-field"></span> <span id="zip-postal-code-field"></span> <span id="country-code-field"></span></div>

				</div>

				<div id="phone-container"></div>

				<div id="email-container"></div>

			</div>
		</div>

		<div class="col-risk">
			<section class="risk">
				<input type="hidden" name="risk-level-value" id="risk-level-value" value="" />
				<h3><s:message code='pages.member.view.risk' text='default text' /></h3>
				<a href="javascript:;" id="addRisk" title="<s:message code='pages.member.view.editrisk' text='default text' />">
					<span class="icon-small-button icon-nav icon-pencil"></span>
					<s:message code='commons.pages.edit' text='default text' />
				</a>
				<div class="newline all"><s:message code='pages.person.pep.status' text='default text'/> <span class="pepStatus" ></span></div>
				<div class="newline all"><p class="label-name"></p></div>
			</section>
		</div>
		<div class="col-inactive"></div>
		<hr>

		<section class="transfer view">
			<div class="col-title">
					<h3><s:message code='pages.member.view.tranfers' text='default text' /></h3>
					<!--<div class="links">
						<a id="addTransfer" class="insertTransferRecipient" href="javascript:;" title="Add New Transfers">
							<span class="icon-small-button icon-nav icon-plus"></span>
							<s:message code='pages.member.view.addnew' text='default text' />
						</a>
					</div>-->
			</div>
			<div class="container">

			</div>
		</section>


		<hr>

		<section class="notes view">
			<div class="col-title">
					<h3><s:message code='pages.view.notes' text='default text' /></h3>
					<a href="javascript:;" title="Add New Note">
						<span class="icon-small-button icon-nav icon-plus"></span>
						<s:message code='pages.member.view.addnew' text='default text' />
					</a>
			</div>
			<div class="container">
				<div class="box-container">

				</div>
			</div>
		</section>

	</div>