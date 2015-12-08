<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=utf-8");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" href="../styles/common_grid.css" rel="stylesheet" />
<link type="text/css" href="../styles/slick.pager.css" rel="stylesheet" />
</head>
<body>
	<div id="page-wrapper">
            <div class="row">
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
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<button type="button" id="buttonInsert" class="btn active btn-primary">
							Cadastrar Empresa
						</button>
					<li>
				</ul>
                <div class="col-lg-12">
                     <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
							<div class="btn-group">
								<button class="btn btn-default">
									Acoes
								</button>
								<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li>
										<a href="#">Deletar</a>
									</li>
									<li class="">
										<a href="#">Ativar</a>
									</li>
									<li class="divider">
									</li>
									<li>
										<a href="#">Desativar</a>
									</li>
								</ul>
							</div>
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

<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/widget/slick.pager.js"></script>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/servico/servico_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/servico/servico_mvc_bas_init.js.jsp" flush="true"/>
</body>
</html>