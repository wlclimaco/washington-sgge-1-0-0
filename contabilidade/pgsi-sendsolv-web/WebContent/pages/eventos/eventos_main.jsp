<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<style>
.filterable {
    margin-top: 15px;
}
.filterable .panel-heading .pull-right {
    margin-top: -20px;
}
.filterable .filters input[disabled] {
    background-color: transparent;
    border: none;
    cursor: auto;
    box-shadow: none;
    padding: 0;
    height: auto;
}
.filterable .filters input[disabled]::-webkit-input-placeholder {
    color: #333;
}
.filterable .filters input[disabled]::-moz-placeholder {
    color: #333;
}
.filterable .filters input[disabled]:-ms-input-placeholder {
    color: #333;
}

#rowTable {
    width: 97%;
    margin-left: 10px;
    float: left;
}
.panel-primary>.panel-heading{
	background :#F0F0F0!Important;
}
.navbar1 {
    color: #000!important;
    font-size: medium;
}

.navbar .nav>li>a {
    color: #000 !important;
	font-size: medium;
}
</style>
<div class="row">
	<div class="col-lg-12">
		 <div class="panel panel-default">
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<nav class="navbar navbar-default" role="navigation">
								<div class="navbar-header">

									<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
										 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
									</button>
								</div>

								<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav">
										<li class="active">
											<div class="btn-group">
												<button class="btn btn-default">
													Acoes
												</button>
												<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
													<span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li>
														<a href="javascript:;">Deletar</a>
													</li>
													<li class="">
														<a href="javascript:;">Ativar</a>
													</li>
													<li class="divider">
													</li>
													<li>
														<a href="javascript:;">Desativar</a>
													</li>
												</ul>
											</div>
										</li>

									</ul>
									<ul class="breadcrumb">
										<li>
											<a href="#">Home</a> <span class="divider">/</span>
										</li>
										<li>
											<a href="#">Library</a> <span class="divider">/</span>
										</li>
										<li class="active">
											Data
										</li>
										<li class="rigth">
											Data
										</li>
									</ul>
									<ul class="nav navbar-nav navbar-right">
										<li>
											<a href="javascript:;" class="navbar1" id="atualizar">Atualizar</a>
										</li>
										<li class="dropdown">
											<a href="javascript:;" class="navbar1" id="importar">Importar</a>
										</li>
										<li class="dropdown">
											<a href="javascript:;" class="navbar1" id="exportar">Exportar</a>
										</li>
										<li class="dropdown">
											<a href="javascript:;" class="navbar1" id="add" class="add">Adicionar Cidade</a>
										</li>
									</ul>
								</div>

							</nav>
						</div>
					</div>
				</div>
					<div class="row" id="rowTable">
						<div class="panel panel-primary filterable">
							<div class="panel-heading">
							 <h3 class="panel-title">Cadastro Cidade</h3>
								<div class="pull-right">
									<button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filtro</button>
								</div>
							</div>
							<table class="table" id="mytable">
								<thead>
									<tr class="filters">
										<th><input type="checkbox" id="checkall" /></th>
										<th><input type="text" class="form-control" placeholder="#" disabled></th>
										<th><input type="text" class="form-control" placeholder="Cidade" disabled></th>
										<th><input type="text" class="form-control" placeholder="Estado" disabled></th>
										<th><input type="text" class="form-control" placeholder="UF" disabled></th>
										<th><input type="text" class="form-control" placeholder="Codigo" disabled></th>
										<th><input type="text" class="form-control" placeholder="Codigo IBGE" disabled></th>
										<th><input type="text" class="form-control" placeholder="Codigo Municipal" disabled></th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>

	</div>

</div>

<jsp:include page="../../scripts/pages/eventos/eventos_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/eventos/eventos_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/eventos/eventos_init.js.jsp" flush="true" />

