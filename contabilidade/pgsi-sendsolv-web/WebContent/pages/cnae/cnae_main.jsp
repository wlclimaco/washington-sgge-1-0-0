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
						<jsp:include page="../produto/produto_inf.jsp" flush="true" />
					</div>
					 <div class="col-xs-3"> <!-- required for floating -->
		          <!-- Nav tabs -->
		          <ul class="nav nav-tabs tabs-left">
		            <li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
		            <li><a href="#profile" data-toggle="tab">Calculo de preco</a></li>
		            <li><a href="#messages" data-toggle="tab">Procao</a></li>
		            <li><a href="#settings" data-toggle="tab">Rentabilidade</a></li>
		            <li><a href="#tabPreco" data-toggle="tab">Tabela Preço</a></li>
		            <li><a href="#tabEstoque" data-toggle="tab">Tabela Estoque</a></li>
		            <li><a href="#tabCfop" data-toggle="tab">Tabela CFOP</a></li>
		            <li><a href="#tabSaidas" data-toggle="tab">Saidas</a></li>
		            <li><a href="#tabEntradas" data-toggle="tab">Entradas</a></li>
		          </ul>
		        </div>

		        <div class="col-xs-9">
		          <!-- Tab panes -->
		          <div class="tab-content">
		            <div class="tab-pane active" id="home"><jsp:include page="../produto/produto_cadastro.jsp" flush="true" /></div>
		            <div class="tab-pane" id="profile"><jsp:include page="../produto/produto_preco.jsp" flush="true" /></div>
		            <div class="tab-pane" id="messages"><jsp:include page="../produto/produto_porcao.jsp" flush="true" /></div>
		            <div class="tab-pane" id="settings"><jsp:include page="../produto/produto_rentabilidade.jsp" flush="true" /></div>
		            <div class="tab-pane" id="tabPreco"><jsp:include page="../produto/produto_tabPreco.jsp" flush="true" /></div>
		            <div class="tab-pane" id="tabEstoque"><jsp:include page="../produto/produto_tabEstoque.jsp" flush="true" /></div>
		            <div class="tab-pane" id="tabCfop"><jsp:include page="../produto/produto_Cfop.jsp" flush="true" /></div>
		            <div class="tab-pane" id="tabSaidas"><jsp:include page="../produto/produto_tabSaidas.jsp" flush="true" /></div>
		            <div class="tab-pane" id="tabEntradas"><jsp:include page="../produto/produto_tabEntradas.jsp" flush="true" /></div>
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
});
</script>