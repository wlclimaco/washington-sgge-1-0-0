<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

	  <header class="main-header">

        <!-- Logo -->
        <a href="index2.html" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>Admin</b>LTE</span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="javascript::;" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu">
                <a href="javascript::;" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 4 messages</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- start message -->
                        <a href="javascript::;">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li><!-- end message -->
                      <li>
                        <a href="javascript::;">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            AdminLTE Design Team
                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Developers
                            <small><i class="fa fa-clock-o"></i> Today</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Sales Department
                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <div class="pull-left">
                            <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Reviewers
                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="javascript::;">See All Messages</a></li>
                </ul>
              </li>
              <!-- Notifications: style can be found in dropdown.less -->
              <li class="dropdown notifications-menu">
                <a href="javascript::;" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning">10</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 10 notifications</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li>
                        <a href="javascript::;">
                          <i class="fa fa-users text-aqua"></i> 5 new members joined today
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <i class="fa fa-users text-red"></i> 5 new members joined
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                        </a>
                      </li>
                      <li>
                        <a href="javascript::;">
                          <i class="fa fa-user text-red"></i> You changed your username
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="javascript::;">View all</a></li>
                </ul>
              </li>
              <!-- Tasks: style can be found in dropdown.less -->
              <li class="dropdown tasks-menu">
                <a href="javascript::;" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-flag-o"></i>
                  <span class="label label-danger">9</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 9 tasks</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- Task item -->
                        <a href="javascript::;">
                          <h3>
                            Design some buttons
                            <small class="pull-right">20%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">20% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="javascript::;">
                          <h3>
                            Create a nice theme
                            <small class="pull-right">40%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">40% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="javascript::;">
                          <h3>
                            Some task I need to do
                            <small class="pull-right">60%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">60% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                      <li><!-- Task item -->
                        <a href="javascript::;">
                          <h3>
                            Make beautiful transitions
                            <small class="pull-right">80%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">80% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li><!-- end task item -->
                    </ul>
                  </li>
                  <li class="footer">
                    <a href="javascript::;">View all tasks</a>
                  </li>
                </ul>
              </li>
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="javascript::;" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  <span class="hidden-xs">Alexander Pierce</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="../thirdparty/AdminLTE-2.3.0/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    <p>
                      Alexander Pierce - Web Developer
                      <small>Member since Nov. 2012</small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="javascript::;">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="javascript::;">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="javascript::;">Friends</a>
                    </div>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="javascript::;" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="javascript::;" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="javascript::;" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>

        </nav>
      </header>

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
			<li><a href="empresa/cnae" class="alist" data-title='Cidade'>CNAE</a></li>
			<li><a href="empresa/regime" class="alist" data-title='Cidade'>Regime</a></li>
			<li><a href="produto/classificacao" class="alist" data-title='Cidade'>Classificacao</a></li>
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
            <li><a href="ordemservico" class="alist" data-title='Nota Entrada'>Ordem Servico</a></li>
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
</div

 <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">Recent Activity</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
                    <p>Will be 23 on April 24th</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-user bg-yellow"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>
                    <p>New phone +1(800)555-1234</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>
                    <p>nora@example.com</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-file-code-o bg-green"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>
                    <p>Execution time 5 seconds</p>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">Tasks Progress</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Custom Template Design
                    <span class="label label-danger pull-right">70%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Update Resume
                    <span class="label label-success pull-right">95%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Laravel Integration
                    <span class="label label-warning pull-right">50%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Back End Framework
                    <span class="label label-primary pull-right">68%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

          </div><!-- /.tab-pane -->

          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>
              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Report panel usage
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Some information about this general settings option
                </p>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Allow mail redirect
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Other sets of options are available
                </p>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Expose author name in posts
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Allow the user to show his name in blog posts
                </p>
              </div><!-- /.form-group -->

              <h3 class="control-sidebar-heading">Chat Settings</h3>

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Show me as online
                  <input type="checkbox" class="pull-right" checked>
                </label>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Turn off notifications
                  <input type="checkbox" class="pull-right">
                </label>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Delete chat history
                  <a href="javascript::;" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                </label>
              </div><!-- /.form-group -->
            </form>
          </div><!-- /.tab-pane -->
        </div>
      </aside><!-- /.control-sidebar -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>

	<div id="load">

	</div>


	<jsp:include page="../scripts/pages/wd_wrapper.js.jsp" flush="true" />
