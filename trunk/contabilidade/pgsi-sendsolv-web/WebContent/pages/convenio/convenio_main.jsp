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
				<a href="#"><i class="fa fa-sitemap fa-fw"></i> Empresa<span class="fa arrow"></span></a>
					<jsp:include page="../filtro/empresa_form.jsp"></jsp:include>
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
				<a href="#"><i class="fa fa-sitemap fa-fw"></i> Datas <span class="fa arrow"></span></a>
					<jsp:include page="../filtro/datas_form.jsp"></jsp:include>
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
<jsp:include page="../../scripts/pages/convenio/convenio_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/convenio/convenio_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/convenio/convenio_init.js.jsp" flush="true" />

