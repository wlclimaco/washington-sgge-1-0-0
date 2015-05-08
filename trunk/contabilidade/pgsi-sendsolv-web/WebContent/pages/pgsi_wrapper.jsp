<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<header>
		<ul>
			<li><s:message code="header.label.welcome" text="default text" />&nbsp;&nbsp;<span class="emphasis" id="userName"></span></li>
			<li><a class="account_link" href="javascript:;" title='<s:message code="header.label.account" text="default text" />'><span
					class="icon-user icon-nav"></span><s:message code="header.label.account" text="default text" /></a></li>
			<li><a class="help_link" href="javascript:;" title='<s:message code="header.label.help" text="default text" />'><span
					class="icon-question icon-nav"></span><s:message code="header.label.help" text="default text" /></a></li>
			<li><a class="signout_link" href="j_spring_security_logout" title='<s:message code="header.label.signout" text="default text" />'><span
					class="icon-unlock icon-nav"></span><s:message code="header.label.signout" text="default text" /></a></li>
		</ul>
	</header>

	<nav class="primary hide">
		<ul class="main-menu">
			<li data-url="dashboard" class="dashboard">
				<span><a href="dashboard" class="icon-home icon-nav alist" title='<s:message code="commons.title.dashboard" text="default text" />'></a></span>
			</li>

			<li  data-url="organization location">
				<a href="organization" class="alist" title='<s:message code="commons.title.customers" text="default text" />' data-title='<s:message code="commons.title.customers" text="default text" />'><s:message code="commons.title.customers" text="default text" /></a>

				<ul class="sub-menu">
					<li>
						<a href="organization/add" class="alist" title='<s:message code="commons.pages.organizationadd" text="default text" />' data-title='<s:message code="commons.pages.organizationadd" text="default text" />'>
							<span><s:message code="commons.pages.organizationadd" text="default text" /></span>
						</a>
					</li>
					<li>
						<a href="organization" class="alist" data-title='<s:message code="commons.pages.organizationSearch" text="default text" />' title='<s:message code="commons.pages.organizationSearch" text="default text" />'>
							<span><s:message code="commons.pages.organizationSearch" text="default text" /></span>
						</a>
					</li>
					<li>
						<a href="location/add" class="alist" title='<s:message code="commons.pages.addlocation" text="default text" />' data-title='<s:message code="commons.pages.addlocation" text="default text" />'>
							<span><s:message code="commons.pages.addlocation" text="default text" /></span>
						</a>
					</li>
					<li>
						<a href="location" class="alist" title='<s:message code="commons.pages.locationSearch" text="default text" />' data-title='<s:message code="commons.pages.locationSearch" text="default text" />'>
							<span><s:message code="commons.pages.locationSearch" text="default text" /></span>
						</a>
					</li>
				</ul>
			</li>
			<li data-url="member recipient">
				<a href="member" class="alist" title='<s:message code="commons.title.members" text="default text" />' data-title='<s:message code="commons.title.members" text="default text" />'>
					<s:message code="commons.title.members" text="default text" />
				</a>
				<ul class="sub-menu">
					<li>
						<a href="member/add" class="alist" title='<s:message code="commons.pages.memberaddnew" text="default text" />' data-title='<s:message code="commons.pages.memberaddnew" text="default text" />'>
							<span><s:message code="commons.pages.memberaddnew" text="default text" /></span>
						</a>
					</li>
					<li>
						<a href="member" class="alist" data-title='<s:message code="commons.pages.memberfind" text="default text" />' title='<s:message code="commons.pages.memberfind" text="default text" />'>
							<span><s:message code="commons.pages.memberfind" text="default text" /></span>
						</a>
					</li>
					<li>
						<a href="recipient" class="alist" data-title='<s:message code="commons.pages.recipientSearch" text="default text" />' title='<s:message code="commons.pages.recipientSearch" text="default text" />'>
							<span><s:message code="commons.pages.recipientSearch" text="default text" /></span>
						</a>
					</li>
				</ul>
			</li>

			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
				<li data-url="transaction payment">
					<a href="payment/batches" class="payments alist"  title='<s:message code="commons.title.payments" text="default text" />' data-title='<s:message code="commons.title.payments" text="default text" />'>
						<s:message code="commons.title.payments" text="default text" /></a>
					<ul class="sub-menu">
						<li>
							<a href="payment/batches" class="alist" title='<s:message code="pages.payments.menu.batches" text="default text" />' data-title='<s:message code="pages.payments.menu.batches" text="default text" />'>
								<span><s:message code="pages.payments.menu.batches" text="default text" /></span>
							</a>
						</li>
						<li>
							<a href="transaction" class="transaction alist" data-title='<s:message code="pages.payments.menu.find.transaction" text="default text" />' title='<s:message code="pages.payments.menu.find.transaction" text="default text" />'>
								<span><s:message code="pages.payments.menu.find.transaction" text="default text" /></span>
							</a>
						</li>
						<li>
							<a href="payment/upcoming_pay_dates" class="alist" data-title='<s:message code="pages.payments.menu.upcoming.pay.dates" text="default text" />' title='<s:message code="pages.payments.menu.upcoming.pay.dates" text="default text" />'>
								<span><s:message code="pages.payments.menu.upcoming.pay.dates" text="default text" /></span>
							</a>
						</li>
					</ul>
				</li>
			</sec:authorize>

			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			<li data-url="compliance reports sdn sar">
				<a href="javascript:;" class="compliance" title='<s:message code="commons.title.compliance" text="default text" />' data-title='<s:message code="commons.title.compliance" text="default text" />'>
					<s:message code="commons.title.compliance" text="default text" />
				</a>
				<ul class="sub-menu">
						<li>
							<a href="sdn" class="alist" title='<s:message code="pages.sdn.dashboard.match.SDN" text="default text" />' data-title='<s:message code="pages.sdn.dashboard.match.SDN" text="default text" />'>
								<span><s:message code="pages.sdn.dashboard.match.SDN" text="default text" /></span>
							</a>
						</li>
						<li>
							<a href="sar" class="transaction alist" data-title='<s:message code="pages.sdn.dashboard.SAR.Reports" text="default text" />' title='<s:message code="pages.sdn.dashboard.SAR.Reports" text="default text" />'>
								<span><s:message code="pages.sdn.dashboard.suspisciousActivity" text="Suspicious Activity" /></span>
							</a>
						</li>
						<li>
							<a href="reports" class="alist" data-title='<s:message code="commons.title.reports" text="default text" />' title='<s:message code="commons.title.reports" text="default text" />'>
								<span><s:message code="commons.title.reports" text="default text" /></span>
							</a>
						</li>
						<li data-url="sar-reports">
							<a href="" class="alist" title='<s:message code="pages.compliance.label.sarReports" text="default text" />' data-title='<s:message code="pages.compliance.label.sarReports" text="default text" />'>
								<span><s:message code="pages.compliance.label.sarReports" text="default text" /></span>
							</a>
							<ul class="sub-menu side" style="left: 220px; top: 0px; width: 360px;">
								<li>
									<a class="reports-SARSummaryReport" target="_blank" href="" title='<s:message code="pages.compliance.label.summary" text="default text" />' data-title='<s:message code="pages.compliance.label.summary" text="default text" />'>
										<span><s:message code="pages.compliance.label.summary" text="default text" /></span>
									</a>
								</li>
								<li>
									<a class="reports-SARSummaryByEmployeeReport" target="_blank" href="" title='<s:message code="pages.compliance.label.summaryByPGSiEmployee" text="default text" />' data-title='<s:message code="pages.compliance.label.summaryByPGSiEmployee" text="default text" />'>
										<span><s:message code="pages.compliance.label.summaryByPGSiEmployee" text="default text" /></span>
									</a>
								</li>
								<li>
									<a class="reports-SARSummaryByOrganizationReport" target="_blank" href="" title='<s:message code="pages.compliance.label.individualSARsSummarybyOrganization" text="default text" />' data-title='<s:message code="pages.compliance.label.individualSARsSummarybyOrganization" text="default text" />'>
										<span><s:message code="pages.compliance.label.individualSARsSummarybyOrganization" text="default text" /></span>
									</a>
								</li>
								<li>
									<a class="reports-SARGeneratedAgainstOrganizationsReport" target="_blank" href="" title='<s:message code="pages.compliance.label.sarsGeneratedAgainstOrganizations" text="default text" />' data-title='<s:message code="pages.compliance.label.sarsGeneratedAgainstOrganizations" text="default text" />'>
										<span><s:message code="pages.compliance.label.sarsGeneratedAgainstOrganizations" text="default text" /></span>
									</a>
								</li>
								<li>
									<a class="reports-SARGeneratedAgainstIndividuals" target="_blank" href="" title='<s:message code="pages.compliance.label.sarsGeneratedAgainstIndividuals" text="default text" />' data-title='<s:message code="pages.compliance.label.sarsGeneratedAgainstIndividuals" text="default text" />'>
										<span><s:message code="pages.compliance.label.sarsGeneratedAgainstIndividuals" text="default text" /></span>
									</a>
								</li>
							</ul>
						</li>
					</ul>
			</li>
			</sec:authorize>

			<li data-url="pricing payer">
				<a href="pricing/profile_list" class="pricing alist" title='<s:message code="ccommons.pages.pricing" text="default text" />' data-title='<s:message code="commons.pages.pricing" text="default text" />'>
					<s:message code="commons.pages.pricing" text="default text" />
				</a>

				<ul class="sub-menu" style="width: 240px;">
					<li>
						<a href="pricing/profile_list" class="alist" title='<s:message code="commons.pages.pricing.findProfile" text="default text" />' data-title='<s:message code="commons.pages.pricing.findProfile" text="default text" />'>
							<span><s:message code="commons.pages.pricing.findProfile" text="default text" /></span>
						</a>
					</li>

					<li>
						<a href="payer/find/countries" class="alist" title='<s:message code="commons.pages.payer.findPayer" text="default text" />' data-title='<s:message code="commons.pages.payer.findPayer" text="default text" />'>
							<span><s:message code="commons.pages.payer.findPayer" text="default text" /></span>
						</a>
					</li>

				</ul>
			</li>

			<li data-url="operation-reports">
				<a href="" class="alist" title='<s:message code="pages.operationReport.label.operationReport" text="default text" />' data-title='<s:message code="pages.operationReport.label.operationReport" text="default text" />'>
					<s:message code="pages.operationReport.label.operationReport" text="default text" />
				</a>

				<ul class="sub-menu" style="width: 170px;">
					<li>
						<a class="reports-OperationalOrganizationReport" href="" target="_blank" title='<s:message code="pages.operationReport.label.organization" text="default text" />' data-title='<s:message code="pages.operationReport.label.organization" text="default text" />' target="_blank">
							<span><s:message code="pages.operationReport.label.organization" text="default text" /></span>
						</a>
					</li>
					<li>
						<a class="reports-OperationalMemberReport" href="" target="_blank" title='<s:message code="pages.operationReport.label.members" text="default text" />' data-title='<s:message code="pages.operationReport.label.members" text="default text" />'>
							<span><s:message code="pages.operationReport.label.members" text="default text" /></span>
						</a>
					</li>
					<li>
						<a class="reports-OperationalRevenueReport" href="" target="_blank" title='<s:message code="pages.operationReport.label.revenueReport" text="default text" />' data-title='<s:message code="pages.operationReport.label.revenueReport" text="default text" />' target="_blank">
							<span><s:message code="pages.operationReport.label.revenueReport" text="default text" /></span>
						</a>
					</li>
				</ul>
			</li>

			<li>
				<a href="javascript:;" class="users" title='<s:message code="commons.title.users" text="default text" />' data-title='<s:message code="commons.title.users" text="default text" />'>
					<s:message code="commons.title.users" text="default text" />
				</a>
			</li>
			<li>
				<a href="javascript:;" class="system" title='<s:message code="commons.title.system" text="default text" />' data-title='<s:message code="commons.title.system" text="default text" />'>
					<s:message code="commons.title.system" text="default text" />
				</a>
			</li>
			<li class="last suspicious"><a data-title="Report Suspicious Activity" title="Report Suspicious Activity" id="link-sar" class="" href="javascript:;"><span class="icon-security icon-eye-1"></span>Report Suspicious Activity</a></li>
		</ul>
	</nav>

	<div id="load">

	</div>

	<footer>
		<div class="left">&copy; 2014 Prosperitas Global Solutions, Inc.
			<s:message code="company.org" text="default text" /></div>
		<div class="right"><s:message code="commons.pages.version" text="default text" /> 1.00.00 Build 00000</div>
	</footer>
	<jsp:include page="../scripts/pages/sar/sar_main.js.jsp" flush="true" />
	<jsp:include page="../scripts/pages/sar/sar_actions.js.jsp" flush="true" />
	<jsp:include page="../scripts/pages/pgsi_wrapper.js.jsp" flush="true" />
