<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<input type="hidden" name="business-id" id="business-id" />
<input type="hidden" name="business-name" id="business-name" />
<input type="hidden" name="business-type" id="business-type" />

<nav class="secondary">
		<a class="alist" href="dashboard">
			<span><s:message code="commons.pages.compliance" text="Compliance" /> > </span>
		</a>
		<a class="alist" href="sar">
			<span><s:message code="pages.sdn.dashboard.SAR.Reports" text='default text' /> </span>
		</a>
		<span class="icon-nav icon-angle-right"></span>
		<span id="company-name"></span>
</nav>

<h2 id="header-id"></h2>
<input type="hidden" name="key" id="key" value="0" />

<div class="content sar-view">
	<div class="internal">
		<div class="newline label"><s:message code="pages.sar.view.page.report.id" text='default text' /></div>
		<div class="value" id="sarKey" style="margin-right: 0"></div>
		<div class="label" id="sarReported" style="margin-right: 24px;"></div>
		<div class="label" id="sarFile"></div>
	</div>
	<hr>
	<div class="col-info">
		<h3 style="margin-bottom: 8px;"><s:message code="pages.sar.dialog.report.target" text='default text' /></h3>
		<div class="value first" style="width: auto">
			<a href="javascript:;"><div id="memberId"></div></a>
		</div>
		<div class="label first sdnStatus hide" style="width: auto"></div>
		<div class="label first sdnStatus-person hide" style="width: auto">
			<a class="security high"  nohref=""> <span class="icon-security icon-torso"></span> <span class="" id="totalIndividuals"></span></a>
			<a class="security medium sdnStatus-business " nohref=""></a>
		</div>


		<div class="label first spacer narrow"><s:message code="commons.pages.type" text='default text' /></div>
		<div class="value spacer "   id='type'><s:message code="commons.pages.member" text='default text' /></div>
		<div class="label first  narrow"><s:message code="commons.pages.status" text='default text' /></div>
		<div class="value  "   id='status'></div>
		<div class="label first  narrow"><s:message code="pages.sar.view.page.enrollment" text='default text' /></div>
		<div class="value " id='enrollment'></div>
		<div class="label first  narrow"><s:message code="pages.sar.view.page.enrollment.since" text='default text' /></div>
		<div class="value " id='date'></div>
		<div class="label first narrow"><s:message code="pages.sar.view.page.employed.by" text='default text' /></div>
		<div class="value normal" id='location'>

		</div>

	</div>
		<div class="col-info">
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
			<a href="javascript:;" id="edit-risk" title="<s:message code='pages.member.view.editrisk' text='default text' />">
				<span class="icon-small-button icon-nav icon-pencil"></span>
				<s:message code='commons.pages.edit' text='default text' />
			</a>
			<div class="newline all"><s:message code='pages.person.pep.status' text='default text'/> <span class="pepStatus" ></span></div>
			<div class="newline all"><p class="label-name"></p></div>
		</section>
	</div>
	<div class="col-risk pep-overview hide">
		<h3 class="newline">PEP Overview</h3>
		<div class="list-person"></div>
	</div>
	<hr>
	<div class="col-info newline" style="max-width: 380px;">
		<h3><s:message code="pages.sar.view.page.suspicious.activity" text='default text' /></h3>
		<div class="label first narrow"><s:message code="commons.pages.summary" text='default text' /></div>
		<div id="summary" class="value normal"><s:message code="pages.sar.view.page.suspected.structuring" text='default text' /></div>
		<div class="label first narrow"><s:message code="commons.pages.location" text='default text' /></div>
		<div id="location-sar" class="value normal ">

		</div>
		<div class="label newline narrow"><s:message code="pages.sar.view.page.period" text='default text' /></div>
		<div id="period" class="value"></div>
		<div class="label newline narrow"><s:message code="pages.sar.dialog.total.amount" text='default text' /></div>
		<div id= "amount"class="value"></div>
	</div>
	<div class="col-info" style="max-width: 450px;">
		<div class="label first" id="detail" style="width: auto; margin-top: 24px;">
		</div>
	</div>
	<hr>
	<h3><s:message code="pages.sar.view.page.SAR.history" text='default text' /></h3>
	<div class="col-all sdn-history">
		<table id="data_list"></table>
	</div>
	<hr>
	<h3><s:message code="pages.sar.view.page.SAR.update.status" text='default text' /></h3>
	<form>

		<div class="col-all first">
			<label class="first"><s:message code="pages.sar.view.page.SAR.status" text='default text' /></label>
			<select id="field-sdn-status"class="width-medium">
				<option></option>
			</select>
				<label class="first"><s:message code="pages.sar.view.page.filing.date" text='default text' /></label>
				<input id="dateTime" type="text" class="date short"><label class="first"><s:message code="pages.sdn.view.reason" text='default text' /></label>
			<textarea class="width-longest" id="field-sdn-note"></textarea>

			<input type="reset" id="reset-button" value="Reset"
				class="btn first spacer"><input class="btn spacer"
				id="save-button" value="<s:message code="pages.sar.view.page.SAR.update.status" text='default text' />" type="submit">
		</div>
	</form>

</div>
<jsp:include page="../../scripts/pages/risk/risk_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/sar/sar_view_init.js.jsp" flush="true" />

