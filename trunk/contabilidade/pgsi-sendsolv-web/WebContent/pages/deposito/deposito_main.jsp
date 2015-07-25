<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<div id="wrapper">
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">

			<li>
				<a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Id</a>
				<jsp:include page="../filtro/id_form.jsp"></jsp:include>
			</li>
			<li>
				 <jsp:include page="../filtro/status_form.jsp"></jsp:include>
			</li>
			<li>
				<a href="forms.html"><i class="fa fa-edit fa-fw"></i> Nome</a>
			</li>
			<li>
				<jsp:include page="../filtro/nome_form.jsp"></jsp:include>
				<!-- /.nav-second-level -->
			</li>
			<li>
				<a href="#"><i class="fa fa-sitemap fa-fw"></i> CNPJ-CPF<span class="fa arrow"></span></a>
					<jsp:include page="../filtro/cnpj_cpf_form.jsp"></jsp:include>
			</li>
			<li>
				<a href="#"><i class="fa fa-files-o fa-fw"></i>Cnae<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<jsp:include page="../filtro/cnae_form.jsp"></jsp:include>

					</li>
				</ul>
				<!-- /.nav-second-level -->
			</li>
			<li>
				<a href="#"><i class="fa fa-files-o fa-fw"></i>Regime<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<jsp:include page="../filtro/regime_form.jsp"></jsp:include>

					</li>
				</ul>
				<!-- /.nav-second-level -->

			</li>
			<li>
				<a href="#"><i class="fa fa-files-o fa-fw"></i>Estado<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<jsp:include page="../filtro/estado_form.jsp"></jsp:include>

					</li>
				</ul>
			</li>
			<li id="resetFilter"><label><button class="btn btn-default" type="button"><i class="fa fa-search"></i>  Limpar Filtros</label></li>
		</ul>
	</div>


	<!-- /.sidebar-collapse -->
	</div>

<div id="page-wrapper">
            <div class="row">

                <div class="col-lg-12">
                     <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="data_list">

                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>

                </div>

            </div>
            <!-- /.row -->
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
<jsp:include page="../../scripts/pages/empresa/empresa_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_init.js.jsp" flush="true" />

