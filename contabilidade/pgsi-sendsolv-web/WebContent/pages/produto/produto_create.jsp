<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


<style>

</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
				<jsp:include page="../produto/produto_inf.jsp" flush="true" />
			</div>
			<div class="tabbable" id="tabs-310655">
				 <ul class="nav nav-tabs tabs-left">
					<li>
						<a href="#panel-1" data-toggle="tab">Cadastro</a>
					</li>
					<li class="active">
						<a href="#panel-2" data-toggle="tab">Calculo Preço</a>
					</li>
					<li class="">
						<a href="#panel-3" data-toggle="tab">Grade</a>
					</li>
					<li class="">
						<a href="#panel-4" data-toggle="tab">Seriais</a>
					</li>
					<li class="">
						<a href="#panel-5" data-toggle="tab">Composição</a>
					</li>
					<li class="">
						<a href="#panel-6" data-toggle="tab">Inf Nutricionais</a>
					</li>
					<li class="">
						<a href="#panel-7" data-toggle="tab">Foto</a>
					</li>
					<li class="">
						<a href="#panel-8" data-toggle="tab">Rentabilidade</a>
					</li>
					<li class="">
						<a href="#panel-9" data-toggle="tab">Tabela Preço</a>
					</li>
					<li class="">
						<a href="#panel-10" data-toggle="tab">Inf Estoque</a>
					</li>
					<li class="">
						<a href="#panel-11" data-toggle="tab">CFOPs</a>
					</li>

				</ul>
				<div class="tab-content">
					<div class="tab-pane" id="panel-1">
						<div class="col-md-12">
							<jsp:include page="../produto/produto_cadastro.jsp" flush="true" />
						</div>
					</div>
					<div class="tab-pane active" id="panel-2">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-3">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-4">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-5">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-6">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-7">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-8">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-9">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-10">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
					<div class="tab-pane active" id="panel-11">
						<div class="row">
							<div class="col-md-12">

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {
	console.log('ff')
	$.pgsi.progressBar.stopGlobal()
});
</script>