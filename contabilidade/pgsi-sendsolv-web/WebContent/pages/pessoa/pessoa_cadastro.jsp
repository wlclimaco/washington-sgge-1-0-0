<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<div>
						<label for="inputEmail3" class="col-sm-2 control-label">
						Apelido
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="produto" />
						</div>
						<label for="inputEmail3" class="col-sm-2 control-label">
						Usuario
						</label>
						<div class="col-sm-2">
							<input type="" class="form-control" id="produto" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-9">
					<div class="row">
						<jsp:include page="../address/address_main.jsp" flush="true" />
					</div>
					<div class="row">
						<div class="page-header"></div>
						<jsp:include page="../phone/phone_main.jsp" flush="true" />
					</div>
					<div class="row">
						<div class="page-header"></div>
						<jsp:include page="../document/document_main.jsp" flush="true" />
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<div class="panel-body">
							<div class="checkbox">
								<label>
									<input type="checkbox" /> Ativo
								</label>
							</div>

							<div class="checkbox">
								<label>
									<input type="checkbox" /> Inativo
								</label>
							</div>

							<div class="checkbox">
								<label>
									<input type="checkbox" /> Aguardando
								</label>
							</div>

							<div class="checkbox">
								<label>
									<input type="checkbox" /> Bloqueado
								</label>
							</div>

							<div class="checkbox">
								<label>
									<input type="checkbox" /> SPC
								</label>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<div class="panel-body">
							<div class="row">
								<label for="inputEmail3" class="col-sm-3 control-label">
								Limite
								</label>
								<div class="col-sm-5">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
							<div class="row">
								<label for="inputEmail3" class="col-sm-3 control-label">
								Ultima Compra
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
							<div class="row">
								<label for="inputEmail3" class="col-sm-6 control-label">
								Data Nascimento
								</label>
								<div class="col-sm-5">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								Email
							</h3>
						</div>
						<div class="panel-body">
							<jsp:include page="../email/email_main.jsp" flush="true" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								Informações Profissionais
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<label for="inputEmail3" class="col-sm-2 control-label">
								Profissão
								</label>
								<div class="col-sm-5">
									<input type="" class="form-control" id="produto" />
								</div>

								<label for="inputEmail3" class="col-sm-2 control-label">
								Renda
								</label>
								<div class="col-sm-2">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
							<div class="row">
								<label for="inputEmail3" class="col-sm-2 control-label">
								Empresa
								</label>
								<div class="col-sm-7">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-9">
							<div class="row">
								<label for="inputEmail3" class="col-sm-2 control-label">
								Filiação
								</label>
								<div class="col-sm-7">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">
										Estado Civil
									</label>
									<div class="col-sm-2">
										<select id="mySel" name="regime" class="regime" style="width: 80%;">
											<option value="1"> Casado</option>
											<option value="2"> Solteiro</option>
										</select>
									</div>
								</div>
								<label for="inputEmail3" class="col-sm-2 control-label">
								Conjugue
								</label>
								<div class="col-sm-5">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">
										Sexo
									</label>
									<div class="col-sm-3">
										<select id="mySel" name="regime" class="regime" style="width: 80%;">
											<option value="1"> Masculino</option>
											<option value="2"> Feminino</option>
										</select>
									</div>
								</div>
								<label for="inputEmail3" class="col-sm-2 control-label">
								Referencias
								</label>
								<div class="col-sm-5">
									<input type="" class="form-control" id="produto" />
								</div>
							</div>
							<div class="row">
								<div class="checkbox">
									<label>
										<input type="checkbox" /> Casa Propria
									</label>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Convenio
									</h3>
								</div>
								<div class="panel-body">
									<div class="col-sm-12">
										<select id="mySel" name="regime" class="regime" style="width: 80%;">
											<option value="1"> Convenio 0001</option>
											<option value="1"> Convenio 0002</option>
											<option value="1"> Convenio 0003</option>
											<option value="1"> Convenio 0004</option>
											<option value="1"> Convenio 0005</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
