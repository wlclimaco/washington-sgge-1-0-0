<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="dashboard">
		<nav class="primary-dash">
			<ul class="main-menu">
				<li id="customers" class="menu-active menu" title='<s:message code="commons.pages.customers" text="default text" />'>
					<s:message code="commons.pages.customers" text="default text" />
				</li>
				<li id="members" class="menu" title='<s:message code="commons.pages.members" text="default text" />'>
					<s:message code="commons.pages.members" text="default text" />
				</li>

				<li id="empresa" class="menu" title='<s:message code="commons.pages.empresa" text="Empresa" />'>
					<s:message code="commons.pages.members" text="default text" />
				</li>

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					<li id="payments" class="menu" title='<s:message code="commons.pages.payments" text="default text" />'>
						<s:message code="commons.pages.payments" text="default text" />
					</li>
				</sec:authorize>

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					<li id="compliance" class="menu" title='<s:message code="commons.pages.compliance" text="default text" />'>
						<s:message code="commons.pages.compliance" text="default text" />
					</li>
				</sec:authorize>

				<!-- Pricing -->
				<li id="rates" class="menu" title='<s:message code="commons.pages.rates" text="default text" />'>
					<s:message code="commons.pages.rates" text="default text" />
				</li>

				<li id="operation-reports" class="menu" title='<s:message code="pages.operationReport.label.operationReport" text="default text" />'>
					<s:message code="pages.operationReport.label.operationReport" text="default text" />
				</li>

				<li id="analytics" class="menu disabled" title='<s:message code="commons.pages.analytics" text="default text" />'>
					<s:message code="commons.pages.analytics" text="default text" />
				</li>

				<li id="users" class="menu disabled" title='<s:message code="commons.pages.users" text="default text" />'>
					<s:message code="commons.pages.users" text="default text" />
				</li>

				<li id="system" class="menu disabled" title='<s:message code="commons.pages.system" text="default text" />'>
					<s:message code="commons.pages.system" text="default text" />
				</li>
			</ul>
		</nav>

		<div class="content">
			<div class="col customers">
				<a href="organization" class="alist" title="<s:message code='commons.pages.organizations' text='default text' />">
					<img class="dashboard-img" src="images/dashboard-org.png" />
				</a>
				<ul>
					<li>
						<a href="organization" class="alist" title="<s:message code='commons.pages.organizationfind' text='default text' />">
							<span class="icon-nav icon-search-find"></span>
							<s:message code="commons.pages.organizationfind" text="default text" />
						</a>
					</li>

					<li>
						<a href="organization/add" class="alist" title="<s:message code='commons.pages.organizationaddnew' text='default text' />">
							<span class="icon-nav icon-plus"></span>
							<s:message code="commons.pages.organizationaddnew" text="default text" />
						</a>
					</li>
				</ul>
			</div>
			<div class="col customers">
				<a href="empresa" class="alist" title="<s:message code='commons.pages.locations' text='default text' />">
					<img class="dashboard-img" src="images/dashboard-loc.png" />
				</a>
				<ul>
					<li>
						<a href="empresa" class="alist" title="<s:message code="commons.pages.empresa" text="default text" />">
							<span class="icon-nav icon-search-find"></span>
							<s:message code='commons.pages.locationfind' text='default text' />
						</a>
					</li>

					<li>
						<a href="location/add" class="alist" title="<s:message code='commons.pages.locationaddnew' text='default text' />">
							<span class="icon-nav icon-plus"></span>
							<s:message code="commons.pages.locationaddnew" text="default text" />
						</a>
					</li>
				</ul>
			</div>
			<div class="col members" style="display: none">
				<a href="member" class="alist" title="Members">
					<img class="dashboard-img" src="images/dashboard-member.png" />
				</a>

				<ul>
					<li>
						<a href="member" class="alist" title="<s:message code='commons.pages.memberfind' text='default text' />">
							<span class="icon-nav icon-search-find"></span>
							<s:message code="commons.pages.memberfind" text="default text" />
						</a>
					</li>
					<li>
						<a href="member/add" class="alist" title="<s:message code='commons.pages.memberaddnew' text='default text' />">
							<span class="icon-nav icon-plus"></span>
							<s:message code="commons.pages.memberaddnew" text="default text" />
						</a>
					</li>
				</ul>
			</div>
		<div class="col members" style="display: none">
			<a href="recipient" class="alist" title="Members">
				<img class="dashboard-img" src="images/dashboard-recipient.png" />
			</a>
			<ul>
				<li>
					<a href="recipient" class="alist" title="<s:message code='commons.pages.recipientSearch' text='default text' />">
						<span class="icon-nav icon-search-find"></span>
						<s:message code="commons.pages.recipientSearch" text="default text" />
					</a>
				</li>
			</ul>
		</div>

		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			<div class="col payments" style="display: none">
				<a href="payment/batches" class="alist" title="<s:message code='pages.payments.menu.batches' text='default text' />">
					<img class="dashboard-img" src="images/dashboard-batches.png" />
				</a>
				<ul>
					<li>
						<a href="payment/batches" class="alist" title="<s:message code='pages.payments.menu.batches' text='default text' />">
							<span class="icon-action icon-bank-notes"></span>
							<s:message code="pages.payments.menu.batches" text="default text" />
						</a>
					</li>
				</ul>
			</div>
		</sec:authorize>

		<div class="col payments" style="display: none">
				<a href="transaction" class="alist" title="<s:message code='pages.payments.menu.find.transaction' text='default text' />">
					<img class="dashboard-img" src="images/dashboard-transactions.png" />
				</a>

				<ul>
					<li>
						<a href="transaction" class="alist" title="<s:message code='pages.payments.menu.find.transaction' text='default text' />">
							<span class="icon-nav icon-search-find"></span>
							<s:message code="pages.payments.menu.find.transaction" text="default text" />
						</a>
					</li>
				</ul>
			</div>

		<div class="col payments" style="display: none">
			<a href="payment/upcoming_pay_dates" class="alist" title="<s:message code='pages.payments.menu.upcoming.pay.dates' text='default text' />">
				<img class="dashboard-img" src="images/dashboard-schedule.png" />
			</a>
			<ul>
				<li>
					<a href="payment/upcoming_pay_dates" class="alist" title="<s:message code='pages.payments.menu.upcoming.pay.dates' text='default text' />">
						<span class="icon-action icon-calendar-1"></span>
						<s:message code="pages.payments.menu.upcoming.pay.dates" text="default text" />
					</a>
				</li>
			</ul>
		</div>

		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			<div class="col compliance" style="display: none">
				<a href="sdn" class="alist" title="<s:message code='pages.payments.menu.batches' text='default text' />">
					<img class="dashboard-img" src="images/dashboard-compliance.png" />
				</a>
				<ul>
					<li>
						<a href="sdn" class="alist" title="<s:message code='pages.sdn.dashboard.match.SDN' text='default text' />">
							<span class="icon-security icon-shield82"></span>
							<s:message code="pages.sdn.dashboard.match.SDN" text="default text" />
						</a>
					</li>
					<li>
						<a href="sar" class="alist" title="<s:message code='pages.sdn.dashboard.suspisciousActivity' text='default text' />">
							<span class="icon-security icon-eye-1"></span>
							<s:message code="pages.sdn.dashboard.suspisciousActivity" text="default text" />
						</a>
					</li>
					<li>
						<a href="reports" class="alist" title="<s:message code='commons.pages.abnormalBehaviour' text='default text' />">
							<span class="icon-security icon-flag-1"></span>
							<s:message code="commons.pages.abnormalBehaviour" text="default text" />
						</a>
					</li>
					<li class="sar-reports">
						<a href="javascript:;" title="<s:message code='pages.sdn.dashboard.SAR.Reports' text='default text' />">
							<span class="icon-security icon-eye-1"></span>
							<s:message code="pages.sdn.dashboard.SAR.Reports" text="default text" />
						</a>
						<ul class="submenu">
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
			</div>
		</sec:authorize>

		<div class="col rates" style="display: none">
			<a href="pricing/profile_list" class="alist" title="<s:message code='pages.payments.menu.batches' text='default text' />">
				<img class="dashboard-img" src="images/dashboard-pricing.png" />
			</a>
			<ul>
				<li>
					<a href="pricing/profile_list" class="alist" title="<s:message code='commons.pages.pricing.find' text='default text' />">
						<span class="icon-nav icon-search-find"></span>
						<s:message code="commons.pages.pricing.findProfile" text="default text" />
					</a>
				</li>
				<li>
					<a href="payer/find/countries" class="alist" title="<s:message code='commons.pages.payer.findPayer' text='default text' />">
						<span class="icon-nav icon-search-find"></span>
						<s:message code="commons.pages.payer.findPayer" text="default text" />
					</a>
				</li>
			</ul>
		</div>

		<div class="col operation-reports" style="display: none">
			<a href="javascript:;" title="<s:message code='pages.payments.menu.batches' text='default text' />">
				<img class="dashboard-img" src="images/dashboard-operational-reports.png" />
			</a>
			<ul>
				<li>
					<a href="" target="_blank" class="reports-OperationalOrganizationReport" title="<s:message code='pages.operationReport.label.organization' text='default text' />">
						<span class="icon-action icon-world"></span>
						<s:message code="pages.operationReport.label.organization" text="default text" />
					</a>
				</li>

				<li>
					<a href="" target="_blank" class="reports-OperationalMemberReport" title="<s:message code='pages.operationReport.label.members' text='default text' />">
						<span class="icon-action icon-group"></span>
						<s:message code="pages.operationReport.label.members" text="default text" />
					</a>
				</li>

				<li>
					<a href="" target="_blank" class="reports-OperationalRevenueReport" title="<s:message code='pages.operationReport.label.revenueReport' text='default text' />">
						<span class="icon-action icon-newspaper"></span>
						<s:message code="pages.operationReport.label.revenueReport" text="default text" />
					</a>
				</li>
			</ul>
		</div>

	</div>
</div>

<jsp:include page="../../scripts/pages/dashboard/dashboard_init.js.jsp" flush="true" />