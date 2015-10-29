<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


<style>

</style>
<div class="container-fluid">
	<div class="panel panel-default">
		<div class="panel-heading"></div>
		<div class="panel-body" style="margin-left: 30px;">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-12">
						<jsp:include page="../pessoa/pessoa_inf.jsp" flush="true" />
					</div>
					 <div class="col-xs-3"> <!-- required for floating -->
		          <!-- Nav tabs -->
		          <ul class="nav nav-tabs tabs-left">
		            <li class="active"><a href="#home" data-toggle="tab">Inf Cadastro</a></li>

		          </ul>
		        </div>

		        <div class="col-xs-9">
		          <!-- Tab panes -->
		          <div class="tab-content">
		            <div class="tab-pane active" id="home"><jsp:include page="../pessoa/pessoa_cadastro.jsp" flush="true" /></div>
		          </div>
		        </div>

		        <div class="clearfix"></div>


				</div>
			</div>
		<div class="panel-footer">
				<div style="float: right;">
					<div class="row">
						<button type="button" class="btn btn-primary">Gravar</button>
						<button type="button" class="btn btn-primary">Cancelar</button>
						<button type="button" class="btn btn-primary">Deletar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {
	$.pgsi.progressBar.stopGlobal()

	$("select").select2({
		  placeholder: "Select a state",
		  allowClear: true
		});
});
</script>