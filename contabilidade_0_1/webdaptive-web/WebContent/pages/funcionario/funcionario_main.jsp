<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


<div id="wrapper">
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

<jsp:include page="../../scripts/pages/pessoa/pessoa_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/funcionario/funcionario_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/funcionario/funcionario_init.js.jsp" flush="true" />

