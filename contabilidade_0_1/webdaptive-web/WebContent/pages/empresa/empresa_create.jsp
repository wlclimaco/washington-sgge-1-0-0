<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid" id="createEmpresa">
	<div class="row">
		<div class="col-md-12">
			<form role="form">

				<button type="submit" class="btn btn-default">
					Submit
				</button>
			</form>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Usuario.
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">

					<label for="exampleInputEmail1">
						Nome Completo
					</label>
					<input type="email" class="form-control" id="exampleInputEmail1" />
				</div>
				<div class="form-group">

					<label for="exampleInputEmail1">
						CPF
					</label>
					<input type="email" class="form-control" id="exampleInputEmail1" />
				</div>
				<div class="form-group">

					<label for="exampleInputEmail1">
						Telefone
					</label>
					<input type="email" class="form-control" id="exampleInputEmail1" />
				</div>
				<div class="form-group">

					<label for="exampleInputEmail1">
						Email
					</label>
					<input type="email" class="form-control" id="exampleInputEmail1" />
				</div>
				<div class="form-group">

					<label for="exampleInputPassword1">
						Defina Sua Senha
					</label>
					<input type="password" class="form-control" id="exampleInputPassword1" />
				</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Empresa
					</h3>
				</div>
				<div class="panel-body">
					<div class="radio-inline">
                        <label>
                            <input type="radio" name="ticket_type" value="0">Unprinted
                        </label>
                    </div>
					<div class="form-group">

						<label for="exampleInputEmail1">
							Informe a Razão social
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Natureza jurídica
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
						<label for="exampleInputEmail1">
							Sua empresa está ativa?
						</label>
						<div class="radio-inline">
							<label>
								<input type="radio" name="ticket_type" value="0">Ativo
								<input type="radio" name="ticket_type" value="0">Inativo
							</label>
						</div>
					</div>

					<div class="form-group">
						<label for="exampleInputEmail1">
							Informe o CEP da sua empresa
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Logradouro
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Número
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Complemento
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Bairro
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Estado
						</label>
						<div class="select2 input-sm" style="width: 99.9%;">
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Município
						</label>
						<div class="select2 input-sm" style="width: 99.9%;">
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Atividade principal da empresa
						</label>
						<table>
							<tbody data-bind="foreach: phones">
								<tr>
									<td><input data-bind='value: type' /></td>
									<td><input data-bind='value: number' /></td>
									<td><a href='#' data-bind='click: $root.removePhone'>Delete</a></td>
								</tr>
							</tbody>
						</table>
						<a href='#' data-bind='click: $root.addPhone'>Add number</a>
					</div>

				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Socios
					</h3>
				</div>
				<div class="panel-body">
					<table>
							<tbody data-bind="foreach: socios">
								<tr>
									<td><input data-bind='value: nome' /></td>
									<td><input data-bind='value: cpf' /></td>
									<td><input data-bind='value: cota' /></td>
									<td><a href='#' data-bind='click: $root.removeSocio'>Delete</a></td>
								</tr>
							</tbody>
						</table>
						<a href='#' data-bind='click: $root.addSocio'>Add Socio</a>
				</div
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Upload Contrato Social
					</h3>
				</div>
				<div class="form-group">

					<label for="exampleInputFile">
						Documento input
					</label>
					<input type="file" id="exampleInputFile" />
					<p class="help-block">
						Example block-level help text here.
					</p>
				</div>
			</div>

	</div>
	<div class="row">
		<div class="col-md-5">
		</div>
		<div class="col-md-2">
		</div>
		<div class="col-md-5">
			<ul class="pagination">
				<li>
					<a href="#">Proximo</a>
				</li>
				<li>
					<a href="#">Gravar</a>
				</li>
				<li>
					<a href="#">Cancelar</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<jsp:include page="../../scripts/pages/empresa/empresa_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_create_init.js.jsp" flush="true" />