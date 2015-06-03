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
				<a href="cliente" class="alist" title='Cliente' data-title='Cliente>'>Clientes</a>

				<ul class="sub-menu">
					<li>
						<a href="cliente/add" class="alist" title='Adicionar Cliente' data-title='Adicionar Cliente'>
							<span>Adicionar Cliente</span>
						</a>
					</li>
					<li>
						<a href="clientes" class="alist" data-title='Clientes' title='Clientes'>
							<span>Clientes</span>
						</a>
					</li>
					<li>
						<a href="fornecedor/add" class="alist" title='Adicionar Fornecedor' data-title='Adicionar Fornecedor'>
							<span>Adicionar Fornecedor</span>
						</a>
					</li>
					<li>
						<a href="fornecedor" class="alist" title='Fornecedor' data-title='Fornecedor'>
							<span>Fornecedor</span>
						</a>
					</li>
					<li>
						<a href="transportador/add" class="alist" title='Adicionar Transportador' data-title='Adicionar Transportador'>
							<span>Adicionar Transportador</span>
						</a>
					</li>
					<li>
						<a href="transportador" class="alist" title='Transportador' data-title='Transportador'>
							<span>Transportador</span>
						</a>
					</li>
					<li>
						<a href="cliente/condPag" class="alist" title='Condição de Pagamento' data-title='Condição de Pagamento'>
							<span>Condição de Pagamento</span>
						</a>
					</li>
					<li>
						<a href="cliente/convenio" class="alist" title='Convenios' data-title='Convenios'>
							<span>Convenios</span>
						</a>
					</li>
					<li>
						<a href="cliente/FormPag" class="alist" title='Forma de Pagamento' data-title='Forma de Pagamento'>
							<span>Forma de Pagamento</span>
						</a>
					</li>
				</ul>
			</li>
			<li data-url="member recipient">
				<a href="Funcionario" class="alist" title='Funcionarios' data-title='Funcionarios'>
					Funcionarios
				</a>
				<ul class="sub-menu">
					<li>
						<a href="funcionario/add" class="alist" title='Adicionar Funcionario' data-title='Adicionar Funcionario'>
							<span>Adicionar Funcionario</span>
						</a>
					</li>
					<li>
						<a href="funcionario" class="alist" data-title='Funcionarios' title='Funcionarios'>
							<span>Funcionarios</span>
						</a>
					</li>
					<li>
						<a href="evento" class="alist" data-title='Eventos' title='Eventos'>
							<span>Eventos</span>
						</a>
					</li>
					<li>
						<a href="horaFunc" class="alist" data-title='Horario Funcionarios' title='Horario Funcionarios'>
							<span>Horario Funcionarios</span>
						</a>
					</li>
					<li>
						<a href="funcionario/descBenef" class="alist" data-title='Descontos e Beneficios' title='Descontos e Beneficios'>
							<span>Descontos e Beneficios</span>
						</a>
					</li>
				</ul>
			</li>

				<li data-url="transaction payment">
					<a href="produto" class="payments alist"  title='Produtos' data-title='Produtos'>
						Produtos
					</a>
					<ul class="sub-menu">
						<li>
							<a href="produto" class="alist" title='Produtos' data-title='Produtos'>
								<span>Produtos</span>
							</a>
						</li>
						<li>
							<a href="produto/add" class="alist" title='Adicionar Produtos' data-title='Adicionar Produtos'>
								<span>Adicionar Produtos</span>
							</a>
						</li>
						<li>
							<a href="produto/grupo" class="alist" title='Grupo' data-title='Grupo'>
								<span>Grupo</span>
							</a>
						</li>
						<li>
							<a href="produto/subGrupo" class="alist" title='Sub-Grupo' data-title='Sub-Grupo'>
								<span>Sub-Grupo</span>
							</a>
						</li>
						<li>
							<a href="produto/cfop" class="transaction alist" data-title='CFOP' title='CFOP'>
								<span>CFOP</span>
							</a>
						</li>
						<li>
							<a href="produto/cnae" class="alist" data-title='CNAE' title='CNAE'>
								<span>CNAE</span>
							</a>
						</li>
						<li>
							<a href="produto/unimed" class="alist" data-title='Unidade de Medida' title='Unidade de Medida'>
								<span>Unidade de Medida</span>
							</a>
						</li>
					</ul>
				</li>


			<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
			<li data-url="compliance reports sdn sar">
				<a href="javascript:;" class="compliance" title='Nota Fiscal' data-title='Nota Fiscal'>
					Nota Fiscal
				</a>
				<ul class="sub-menu">
						<li>
							<a href="notafiscal/entrada" class="alist" title='Nota Fiscal Entrada' data-title='Nota Fiscal Entrada'>
								<span>Nota Fiscal Entrada</span>
							</a>
						</li>
						<li>
							<a href="notafiscal/saida" class="transaction alist" data-title='Nota Fiscal Saida' title='Nota Fiscal Saida'>
								<span>Nota Fiscal Saida</span>
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
