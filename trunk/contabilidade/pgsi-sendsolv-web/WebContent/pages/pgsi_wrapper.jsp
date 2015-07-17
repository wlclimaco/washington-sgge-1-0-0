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
             <li><a href="cliente/Cidade" class="alist" data-title='Cidade'>Cidade</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Departamento Pessoal</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="funcionario" class="alist" data-title='Funcionario'>Funcionario</a></li>
            <li><a href="funcionario/eventos">Eventos</a></li>
            <li><a href="funcionario/beneficios">Beneficios</a></li>
			<li><a href="funcionario/convenio">Convenio</a></li>
			<li><a href="funcionario/funcPonto">Ponto Funcionario</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Produtos</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="produto" class="alist" data-title='Produto'>Produto</a></li>
            <li><a href="faq.html">Unidade Medida</a></li>
            <li><a href="pricing.html">Grupo</a></li>
			<li><a href="pricing.html">Sub Grupo</a></li>
			<li><a href="pricing.html">Marca</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Financeiro</span> <b class="caret"></b></a>
		 <ul class="dropdown-menu">
            <li><a href="icons.html">Contas Pagar</a></li>
            <li><a href="faq.html">Contas Receber</a></li>
            <li><a href="pricing.html">Caixa</a></li>
			<li><a href="pricing.html">Condicao Pagamento</a></li>
			<li><a href="pricing.html">Marca</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Fiscal</span> <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="icons.html">Nota Entrada</a></li>
            <li><a href="faq.html">Nota Saida</a></li>
            <li><a href="pricing.html">CFOP</a></li>
			<li><a href="pricing.html">CNAE</a></li>
			<li><a href="pricing.html">Marca</a></li>
          </ul>
        </li>
		<li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Compras/vendas</span> <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="icons.html">Nota Entrada</a></li>
            <li><a href="faq.html">Nota Saida</a></li>
            <li><a href="pricing.html">Orçamento</a></li>
			<li><a href="pricing.html">Pedido de Compras</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-long-arrow-down"></i><span>Sistema</span> <b class="caret"></b></a>
		<ul class="dropdown-menu">
            <li><a href="icons.html">Tabelas</a></li>
            <li><a href="faq.html">Atributos</a></li>
            <li><a href="sistema/icons">Incones</a></li>
			<li><a href="sistema/reports">Relatorios</a></li>
			<li><a href="sistema/chats">Graficos</a></li>
			<li><a href="sistema/pricing">pricing</a></li>
			<li><a href="sistema/shortcodes">shortCodes</a></li>
			<li><a href="sistema/faq">Faq</a></li>
			<li><a href="sistema/guidely">Guidely</a></li>
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
                            About Free Admin Template</h4>
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
