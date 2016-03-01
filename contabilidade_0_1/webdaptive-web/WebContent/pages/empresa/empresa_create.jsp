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
			<ul data-bind="foreach: empresa().usuarioList()">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							Usuario.
						</h3>
					</div>
					<div class="panel-body">
					<li>
						<div>
						<div class="form-group">
							<label for="exampleInputEmail1">
								Nome Completo
							</label>
							<input type="nome" class="form-control" id="nome" data-bind='value: nome' />
						</div>
					</div>
					</li>
					<li>
					<div>
					<div class="form-group">

						<label for="exampleInputEmail1">
							Login
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" data-bind='value: login'/>
					</div>
					</div>
						</li>
						<li>
							<div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							Email
						</label>
						<input type="email" class="form-control" id="exampleInputEmail1" data-bind='value: emails().email'/>
					</div>
					</div>
						</li>
						<li>
							<div>
					<div class="form-group">

						<label for="exampleInputPassword1">
							Defina Sua Senha
						</label>
						<input type="password" class="form-control" id="exampleInputPassword1" data-bind='value: senha'/>
					</div>
					</div>
						</li>

					</div>
				</div>
			</ul>
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
						<input type="email" class="form-control" id="exampleInputEmail1" data-bind='value: empresa().nome' />
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">
							CNPJ
						</label>
						<input type="" class="form-control" id="documento" data-bind='value: empresa().documentos()[0].cpfCnpj' />
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


					<ul data-bind="foreach: empresa().enderecos()">
					<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							Endereco
						</h3>
					</div>
					<div class="panel-body">
						<li>
							<div>
								<div class="form-group">
									<label for="exampleInputEmail1">
										Id
									</label>
									<input class='required form-control' data-bind='value: id, uniqueName: true' />
								</div>
							</div>
						</li>
						<li>
							<div>
								<div class="form-group">
									<label for="exampleInputEmail1">
										Informe o CEP da sua empresa
									</label>
									<input class='required form-control' data-bind='value: cep, uniqueName: true' />
								</div>
							</div>
						</li>
						<li>
							<div>
							<div class="form-group">
								<label for="exampleInputEmail1">
									Logradouro
								</label>
								<input class='required form-control' data-bind='value: logradouro, uniqueName: true' />
							</div>
						</div>
						</li>
						<li>
							<div>
							<div class="form-group">
								<label for="exampleInputEmail1">
									Número
								</label>
								<input class='required form-control' data-bind='value: numero, uniqueName: true' />
							</div>
							</div>
						</li>
						<li>
							<div>
							<div class="form-group">
								<label for="exampleInputEmail1">
									Complemento
								</label>
								<input class='required form-control' data-bind='value: complemento, uniqueName: true' />
							</div>
							</div>
						</li>
						<li>
							<div>
							<div class="form-group">
								<label for="exampleInputEmail1">
									Bairro
								</label>
								<input class='required form-control' data-bind='value: bairro, uniqueName: true' />
							</div>
							</div>
						</li>
						<li>
							<div>
							<div class="form-group">
								<label for="exampleInputEmail1">
									Estado
								</label>
								<input class='required email form-control' data-bind='value: cidade().estado, uniqueName: true' />
								</div>
							</div>
						</li>
						<li>
							<div>
							<div class="form-group">
								<label for="exampleInputEmail1">
									Município
								</label>
								<input class='required number form-control' data-bind='value: cidade().nome, uniqueName: true' />
							</div>
							</div>
						</li>
						</div>
					</ul>
					<a href='#' data-bind='click: $root.addEndereco'>Add Endereco</a>
						</div>

						<div class="form-group">

					<table data-bind='visible: empresa().telefones().length > 0'>
						<thead>
							<tr>
								<th>Id</th>
								<th>DDD</th>
								<th>Numero</th>
								<th />
							</tr>
						</thead>
						<tbody data-bind="foreach: empresa().telefones()">
							<tr>
								<td><input class='required form-control' data-bind='value: id, uniqueName: true' /></td>
								<td><input class='required number form-control' data-bind='value: ddd, uniqueName: true' /></td>
								<td><input class='required number form-control' data-bind='value: numero, uniqueName: true' /></td>
								<td><a href='#' data-bind='click: $root.removeGift'>Delete</a></td>
							</tr>
						</tbody>
					</table>
					<a href='#' data-bind='click: $root.addGift'>Add Telefone</a>
				</div>

						<div class="form-group">
							<label for="exampleInputEmail1">
								Atividade principal da empresa
							</label>
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
							<tbody data-bind="foreach: empresa().socios()">
								<tr>
									<td><input data-bind='value: nome' /></td>
									<td><input data-bind='value: documentos()[0].numero' /></td>
									<td><input data-bind='value: cota' /></td>
									<td><a href='#' data-bind='click: $root.removeSocio'>Delete</a></td>
								</tr>
							</tbody>
						</table>
						<a href='#' data-bind='click: $root.addSocio'>Add Socio</a>
				</div>
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