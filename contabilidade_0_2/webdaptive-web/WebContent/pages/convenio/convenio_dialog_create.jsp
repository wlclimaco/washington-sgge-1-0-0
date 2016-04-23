<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %><div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label">
							Codigo
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
						<label for="inputEmail3" class="col-sm-4 control-label">
							Data de Cadastro
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<jsp:include page="../address/address_main.jsp" flush="true" />
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<jsp:include page="../phone/phone_main.jsp" flush="true" />
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<jsp:include page="../email/email_main.jsp" flush="true" />
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<jsp:include page="../document/document_main.jsp" flush="true" />
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label">
							Desconto
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
						<label for="inputEmail3" class="col-sm-4 control-label">
							Limite
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label">
							Dia Fechamento
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
						<label for="inputEmail3" class="col-sm-4 control-label">
							Dia Desconto
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>