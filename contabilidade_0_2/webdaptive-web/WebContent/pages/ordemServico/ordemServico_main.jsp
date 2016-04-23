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

</head>
<body>
	<div id="page-wrapper">
            <div class="row">

				<ul class="nav navbar-nav navbar-right" style="margim-top:20px">
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

<jsp:include page="../../scripts/pages/ordemServico/ordemServico_mvc_bas_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/ordemServico/ordemServico_mvc_bas_init.js.jsp" flush="true" />

