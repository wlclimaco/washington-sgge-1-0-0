<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<header>
		<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="index.html">Bootstrap Admin Template </a>
      <div class="nav-collapse">
        <ul class="nav pull-right">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-cog"></i> Account <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="javascript:;">Settings</a></li>
              <li><a href="javascript:;">Help</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-user"></i> EGrappler.com <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="javascript:;">Profile</a></li>
              <li><a href="javascript:;">Logout</a></li>
            </ul>
          </li>
        </ul>
        <form class="navbar-search pull-right">
          <input type="text" class="search-query" placeholder="Search">
        </form>
      </div>
      <!--/.nav-collapse -->
    </div>
    <!-- /container -->
  </div>
  <!-- /navbar-inner -->
</div>
<!-- /navbar -->
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li class="active"><a href="dashboard"><i class="icon-dashboard alist" data-title='Dashboard'></i><span>Pagina Inicial</span> </a> </li>
        <li><a href="reports.html"><i class="icon-list-alt"></i><span>Relatorio</span> </a> </li>
         <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Cadastros</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="empresa" class="alist" data-title='Empresa'>Empresa</a></li>
            <li><a href="empresa/filial" class="alist" data-title='Filial'>Filial</a></li>
            <li><a href="empresa/deposito" class="alist" data-title='Deposito'>Deposito</a></li>
			<li><a href="pricing.html">Documentos</a></li>
			<li><a href="cliente" class="alist" data-title='Empresa'>Cliente</a></li>
            <li><a href="fornecedor" class="alist" data-title='Empresa'>Fornecedor</a></li>
            <li><a href="transportador" class="alist" data-title='Empresa'>Transportador</a></li>
            <li><a href="cliente/cidade" class="alist" data-title='Cidade'>Cidade</a></li>
             <li><a href="cliente/estado" class="alist" data-title='Cidade'>Estado</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Departamento Pessoal</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="funcionario" class="alist" data-title='Funcionario'>Funcionario</a></li>
            <li><a href="funcionario/eventos" class="alist" data-title='Cidade'>Eventos</a></li>
            <li><a href="funcionario/beneficios" class="alist" data-title='Cidade'>Beneficios</a></li>
			<li><a href="funcionario/convenio" class="alist" data-title='Cidade'>Convenio</a></li>
			<li><a href="funcionario/funcPonto" class="alist" data-title='Cidade'>Ponto Funcionario</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Produtos</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="produto" class="alist" data-title='Produto'>Produto</a></li>
            <li><a href="produto/fetch/unimed" class="alist" data-title='Cidade'>Unidade Medida</a></li>
            <li><a href="produto/fetch/grupo" class="alist" data-title='Cidade'>Grupo</a></li>
			<li><a href="produto/fetch/subgrupo" class="alist" data-title='Cidade'>Sub Grupo</a></li>
			<li><a href="produto/fetch/marca" class="alist" data-title='Cidade'>Marca</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Financeiro</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="notafiscal/fetch/contaspagar" class="alist" data-title='Cidade'>Contas Pagar</a></li>
            <li><a href="notafiscal/fetch/contasreceber" class="alist" data-title='Cidade'>Contas Receber</a></li>
            <li><a href="notafiscal/fetch/caixa" class="alist" data-title='Cidade'>Caixa</a></li>
			<li><a href="notafiscal/fetch/condpg" class="alist" data-title='Cidade'>Condicao Pagamento</a></li>
			<li><a href="cliente/banco" class="alist" data-title='Cidade'>Banco</a></li>
			<li><a href="cliente/agencia" class="alist" data-title='Cidade'>Agencia</a></li>
			<li><a href="cliente/conta" class="alist" data-title='Cidade'>Conta</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Fiscal</span> <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="produto/fetch/cfop" class="alist" data-title='Cidade'>CFOP</a></li>
			<li><a href="empresa/fetch/cnae" class="alist" data-title='Cidade'>CNAE</a></li>
			<li><a href="empresa/fetch/regime" class="alist" data-title='Cidade'>Regime</a></li>
			<li><a href="produto/fetch/classificacao" class="alist" data-title='Cidade'>Classificacao</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Compras/vendas</span> <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="notafiscal/fetch/entrada" class="alist" data-title='Nota Entrada'>Nota Entrada</a></li>
            <li><a href="notafiscal/fetch/saida" class="alist" data-title='Nota Saida'>Nota Saida</a></li>
            <li><a href="notafiscal/fetch/orcamento" class="alist" data-title='Orcamento'>Orçamento</a></li>
			<li><a href="notafiscal/fetch/pdcompras" class="alist" data-title='Pedido de Compras'>Pedido de Compras</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Sistema</span> <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="tela" class="alist" data-title='Nota Entrada'>Tela</a></li>
            <li><a href="historico" class="alist" data-title='Nota Entrada'>Historico</a></li>
            <li><a href="ordemServico" class="alist" data-title='Nota Entrada'>Ordem Servico</a></li>
            <li><a href="sistema/icons" class="alist" data-title='Nota Entrada'>Incones</a></li>
			<li><a href="sistema/reports" class="alist" data-title='Nota Entrada'>Relatorios</a></li>
			<li><a href="sistema/chats" class="alist" data-title='Nota Entrada'>Graficos</a></li>
			<li><a href="sistema/pricing" class="alist" data-title='Nota Entrada'>pricing</a></li>
			<li><a href="sistema/shortcodes" class="alist" data-title='Nota Entrada'>shortCodes</a></li>
			<li><a href="sistema/faq" class="alist" data-title='Nota Entrada'>Faq</a></li>
			<li><a href="sistema/guidely" class="alist" data-title='Nota Entrada'>Guidely</a></li>
          </ul>
        </li>
		</ul>
    </div>
    <!-- /container -->
  </div>
  <!-- /subnavbar-inner -->
</div>
	</header>

	<div id="load">

	</div>

	<footer>
		    <div class="extra">
        <div class="extra-inner">
                <div class="row">
                    <div class="span3">
                        <h4>
                            Cosme & Damiao Contabilidade</h4>
                        <ul>
                            <li><a href="javascript:;">EGrappler.com</a></li>
                            <li><a href="javascript:;">Web Development Resources</a></li>
                            <li><a href="javascript:;">Responsive HTML5 Portfolio Templates</a></li>
                            <li><a href="javascript:;">Free Resources and Scripts</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                    <div class="span3">
                        <h4>
                            Support</h4>
                        <ul>
                            <li><a href="javascript:;">Frequently Asked Questions</a></li>
                            <li><a href="javascript:;">Ask a Question</a></li>
                            <li><a href="javascript:;">Video Tutorial</a></li>
                            <li><a href="javascript:;">Feedback</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                    <div class="span3">
                        <h4>
                            Something Legal</h4>
                        <ul>
                            <li><a href="javascript:;">Read License</a></li>
                            <li><a href="javascript:;">Terms of Use</a></li>
                            <li><a href="javascript:;">Privacy Policy</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                    <div class="span3">
                        <h4>
                            Open Source jQuery Plugins</h4>
                        <ul>
                            <li><a href="http://www.egrappler.com">Open Source jQuery Plugins</a></li>
                            <li><a href="http://www.egrappler.com;">HTML5 Responsive Tempaltes</a></li>
                            <li><a href="http://www.egrappler.com;">Free Contact Form Plugin</a></li>
                            <li><a href="http://www.egrappler.com;">Flat UI PSD</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                </div>
                <!-- /row -->
            <!-- /container -->
        </div>
        <!-- /extra-inner -->
    </div>
	</footer>
	<jsp:include page="../scripts/pages/sar/sar_main.js.jsp" flush="true" />
	<jsp:include page="../scripts/pages/sar/sar_actions.js.jsp" flush="true" />
	<jsp:include page="../scripts/pages/pgsi_wrapper.js.jsp" flush="true" />
