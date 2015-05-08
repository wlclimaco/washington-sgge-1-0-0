<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div id="person_info">
		<div class="remembered-location hide"><s:message code="pages.member.form.label.addingMembers" text="default text" />
			<a href="" class="view view-organization edit_link alist"></a>
			<a href="" class="view view-Location edit_link alist"></a>
			<a href="" title="Remove">&nbsp;&nbsp;<span class="alist icon-nav icon-remove remove"></span> </a>
		</div>
			<jsp:include page="../util/update_status.jsp" flush="true" />
		<hr>

		<div class="row">
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
				<div class="newline label spacer"><s:message code='pages.member.view.dateofbirth' text='default text' /></div>
				<div class="value spacer" id="info-date"></div>
				<div class="newline label"><s:message code='pages.member.view.gender' text='default text' /></div>
				<div class="value" id="info-gender"></div>
				<div class="newline label" id=""><s:message code='pages.member.view.countryofbirth' text='default text' /></div>
				<div class="value" id="info-birth"></div>
				<div class="newline label"><s:message code='pages.member.view.countryofcitizenship' text='default text' /></div>
				<div class="value" id="info-citizenship"></div>
				<div class="newline label"><s:message code='pages.member.view.countryofresidence' text='default text' /></div>
				<div class="value" id="info-residence"></div>
				<div class="newline label"><s:message code='common.pages.ssn' text='default text' /></div>
				<div class="value" id="info-ssn"></div>
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

				<div class="newline label spacer">
					<s:message code='pages.member.view.bestimetocall' text='default text' />
				</div>
				<div class="label spacer" id="info-best-time-to-call"></div>
				<div class="newline label"><s:message code='pages.member.view.preferredlanguage' text='default text' /></div>
				<div class="label" id="info-preferred-language"></div>
			</div>
		</div>

		<div class="col-risk">
			<section class="risk">
				<input type="hidden" name="risk-level-value" id="risk-level-value" value="" />
				<h3><s:message code='pages.member.view.risk' text='default text' /></h3>
				<a href="javascript:;" id="edit-risk" title="<s:message code='pages.member.view.editrisk' text='default text' />">
					<span class="icon-small-button icon-nav icon-pencil"></span>
					<s:message code='commons.pages.edit' text='default text' />
				</a>
				<div class="newline all"><s:message code='pages.person.pep.status' text='default text'/> <span class="pepStatus" ></span></div>
				<div class="newline all"><p class="label-name"></p></div>
			</section>
		</div>

		<div class="col-secquestion">

			<section id="secquestion-section" class="newline">
				<h3><s:message code='pages.member.view.securityquestion' text='default text' /></h3>
				<!-- 					<a href="javascript:;" title="Edit Risk"><span -->
				<!-- 						class="icon-nav icon-pencil"></span>Edit</a> -->
				<a href="javascript:;" id="show-secquestion" class="secquestion" title="<s:message code='pages.member.view.show' text='default text' />">
					<span class="icon-small-button icon-nav icon-plus"></span>
					<s:message code='pages.member.view.show' text='default text' />
				</a>
				<!--  -->
				<a href="javascript:;" id="hide-secquestion" class="secquestion hide" title="<s:message code='pages.member.view.hide' text='default text' />">
					<span class="icon-small-button icon-nav icon-minus"></span>
					<s:message code='pages.member.view.hide' text='default text' />
				</a>
				<div id="person-sec-question-answer" class="newline all normal secquestion hide">

				</div>
			</section>

			<section id="pin-section" class="newline">
				<h3 class="newline double-spacer">
					<s:message code='pages.member.view.systemacccess' text='default text' />
				</h3>
				<a href="javascript:;" id="show-pin" class="pin double-spacer" title="<s:message code='pages.member.view.show' text='default text' />" class="newline">
					<span class="icon-small-button icon-nav icon-plus"></span>
					<s:message code='pages.member.view.show' text='default text' />
				</a>
				<!--  -->
				<a href="javascript:;" id="hide-pin" class="pin double-spacer hide" title="<s:message code='pages.member.view.hide' text='default text' />">
					<span class="icon-small-button icon-nav icon-minus"></span>
					<s:message code='pages.member.view.hide' text='default text' />
				</a>
				<div class="newline label pin hide">
					<s:message code='pages.member.view.pin.acessnumber' text='Access Number' />
				</div>
				<div class="value pin hide" id="pin-acessnumber"></div>
				<div class="newline label pin hide">
					<s:message code='pages.member.view.pin' text='default text' />
				</div>
				<div class="value pin hide" id="pin-number"></div>

				<div class="newline pin spacer label hide">
					<s:message code='pages.member.view.pin.verify' text='Verify Pin' />
				</div>

				<input type="password" class="width-shortest pin hide" id="pin-verify">

				<span class="pin-verify icon-nav pin hide"></span>

				<a id="pin-generate" href="javascript:;" class="pin btn spacer newline hide" title="<s:message code='pages.member.view.edit' text='default text' />">
					<s:message code='pages.member.view.generatenewpin' text='default text' />
				</a>
			</section>

		</div>
		<div class="col-inactive"></div>
		</div>

		<div class="row">
			<section class="identification view">
				<div class="col-title">
					<h3><s:message code='pages.member.view.identification' text='default text' /></h3>
					<a href="javascript:;" id="link-add-id" title="Add New Registered ID Document">
						<span class="icon-small-button icon-nav icon-plus"></span>
						<s:message code='pages.member.view.addnew' text='default text' />
					</a>
				</div>

				<div class="container">

				</div>
			</section>
		</div>

		<div class="row">
			<section class="employment view">
				<div class="col-title">
					<h3><s:message code='commons.pages.employment' text='default text' /></h3>
					<a href="javascript:;" title="<s:message code='pages.member.view.addnewemployment' text='default text' />" id="link-add-empl">
						<span class="icon-small-button icon-nav icon-plus"></span>
						<s:message code='pages.member.view.addnew' text='default text' />
					</a>
				</div>

				<div class="container">

				</div>

			</section>
		</div>

		<div class="row">
			<section class="transfer view">
			<div class="col-title">
					<h3><s:message code='pages.member.view.tranfers' text='default text' /></h3>
					<div class="links">
						<a id="addTransfer" class="insertTransfer" href="javascript:;" title="Add New Transfers">
							<span class="icon-small-button icon-nav icon-plus"></span>
							<s:message code='pages.member.view.addnew' text='default text' />
						</a>
					</div>
			</div>
			<div class="container">

			</div>
			</section>

		</div>

		<div class="row" style="border-bottom: none;">
			<section class="notes view">
			<div class="col-title">
					<h3><s:message code='pages.view.notes' text='default text' /></h3>
					<a href="javascript:;" title="Add New Note">
						<span class="icon-small-button icon-nav icon-plus"></span>
						<s:message code='pages.member.view.addnew' text='default text' />
					</a>
			</div>
			<div class="container">
			</div>
		</section>
		</div>

	</div>
	<jsp:include page="../../scripts/pages/transfers/transferSetting_actions.js.jsp" flush="true" />
