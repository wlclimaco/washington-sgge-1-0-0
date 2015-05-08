<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
uri='http://www.springframework.org/security/tags'%>

<nav class="secondary">
	<a class="alist" href="pricing/profile_list" text='<s:message code="commons.pages.pricing" text="default text" />'>
	<span><s:message code="commons.pages.pricing" text="default text" /></span>
</a>
<span class="icon-nav icon-angle-right"></span>
<span id="location-name"></span>
</nav>

<h2 id="location-name-title"></h2>

<div class="content" style="padding: 1%; width: 98%;">
	<h4><s:message code="pages.pricing.view.label.pricingPolicies" text="PRICING POLICIES" /></h4>
	<div id="pricings">

		<div class="pricing hide" id="template">
			<div class="pricing-header">
				<div class="pricing-title"></div>

			</div>

			<div class="pricing-content">
				<div class="fees">
					<table>
						<thead>
							<tr>
								<th class="none">&nbsp;</th>
								<th class="heading"></th>
							</tr></thead>
							<tbody>
								<tr>
									<td class="heading"><s:message code="pages.pricing.view.label.callAllowance" text="default text" /></td>
									<td class="cell"></td>
								</tr>
								<tr>
									<td class="heading"><s:message code="pages.pricing.view.label.transferAmount" text="default text" /></td>
									<td class="heading" colspan="3"><s:message code="pages.pricing.view.label.flatFee" text="default text" /></td>
								</tr>
								<tr class="fee-row" data-min="" data-max="">
									<td></td>
									<td class="cell"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="payers">
						<div class="header"><s:message code="pages.pricing.view.label.applyTo" text="default text" /></div>
						<div class="payer"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/pricing/pricing_view_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/pricing/pricing_view_init.js.jsp" flush="true" />

