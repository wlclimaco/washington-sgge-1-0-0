<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-8">
			<div class="form-group">

				<label for="inputEmail3" class="col-sm-2 control-label">
					Produto
				</label>
				<div class="col-sm-10">
					<input type="" class="form-control" id="produto" />
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">

				<label for="inputEmail3" class="col-sm-2 control-label">
					Unidade
				</label>
					<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			 <div class="form-group">
				<div class="row">
					<label for="inputEmail3" class=" col-sm-2 control-label" style="width: 30%;margin-left:20px;">Grupo</label>
					<select id="mySel" name="regime" class="regime" style="width: 50%!Important;"></select>
					<button type="button" class="btn btn-default addButton3"><i class="fa fa-plus"></i></button>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<label for="inputEmail3" class=" col-sm-2 control-label" style="width: 30%;margin-left:20px;">Sub Grupo</label>
					<select id="mySel" name="regime" class="regime" style="width: 50%!Important;"></select>
					<button type="button" class="btn btn-default addButton3"><i class="fa fa-plus"></i></button>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<label for="inputEmail3" class=" col-sm-2 control-label" style="width: 30%;margin-left:20px;">Fornecedor</label>
					<select id="mySel" name="regime" class="regime" style="width: 50%!Important;"></select>
					<button type="button" class="btn btn-default addButton3"><i class="fa fa-plus"></i></button>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<label for="inputEmail3" class=" col-sm-2 control-label" style="width: 30%;margin-left:20px;">Marca</label>
					<select id="mySel" name="regime" class="regime" style="width: 50%!Important;"></select>
					<button type="button" class="btn btn-default addButton3"><i class="fa fa-plus"></i></button>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Preco
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">
						Custo
						</label>
						<div class="col-sm-10">
							<input type="" class="form-control" id="produto" />
						</div>
						<label for="inputEmail3" class="col-sm-2 control-label">
							Venda
						</label>
						<div class="col-sm-10">
							<input type="" class="form-control" id="produto" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2">
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

				</div>

			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="row">
						<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
						Aplicação
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="produto" />
						</div>
						</div>
						<div class="row">
						<label for="inputEmail3" class="col-sm-3 control-label"style="margin-left:20px;">
							NCM
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="produto" />
						</div>
						</div>
						<div class="row">
						<label for="inputEmail3" class="col-sm-3 control-label " style="margin-left:20px;">
							IPI
						</label>
						<div class="col-sm-4">
							<input type="" class="form-control" id="produto" />
						</div>
						</div>
						<div class="row">
						<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
							Validade
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="produto" />
						</div>
						</div>
						<div class="row">
							<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
								IAT
							</label>
							<div class="col-sm-8">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
						<div class="row">
							<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
								IPPT
							</label>
							<div class="col-sm-8">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
						<div class="row">
							<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
								Comissao
							</label>
							<div class="col-sm-8">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
						<div class="row">
							<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
								PIS/CONFIS
							</label>
							<div class="col-sm-8">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
						<div class="row">
							<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
								Fracao
							</label>
							<div class="col-sm-8">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
						<div class="row">
							<label for="inputEmail3" class="col-sm-3 control-label" style="margin-left:20px;">
								Incidencia
							</label>
							<div class="col-sm-8">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<div class="panel-body">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Promocao
									</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										<label for="inputEmail3" class="col-sm-3 control-label">
											Preco
										</label>
										<div class="col-sm-8">
											<input type="" class="form-control" id="produto" />
										</div>
									</div>

									<div class="row">
										<label for="inputEmail3" class="col-sm-3 control-label">
											Inicio
										</label>
										<div class="col-sm-8">
											<input type="" class="form-control" id="produto" />
										</div>
									</div>

																		<div class="row">
										<label for="inputEmail3" class="col-sm-3 control-label">
											Fim
										</label>
										<div class="col-sm-8">
											<input type="" class="form-control" id="produto" />
										</div>
									</div>
								</div>
							</div>

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Preco
									</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										<label for="inputEmail3" class="col-sm-3 control-label">
											Bruto
										</label>
										<div class="col-sm-8">
											<input type="" class="form-control" id="produto" />
										</div>
									</div>
									<div class="row">
										<label for="inputEmail3" class="col-sm-3 control-label">
											Liquido
										</label>
										<div class="col-sm-8">
											<input type="" class="form-control" id="produto" />
										</div>
									</div>
								</div>
							</div>


						</div>
					</div>
				</div>




				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<div class="panel-body">
							<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Tributacao
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="row" style="margin-left: 5px;">
								<label for="inputEmail3" class="control-label" style="width: 14%!Important;">CST</label>
								<select id="mySel" name="regime" class="regime" style="width: 20%!Important;"></select>
							<label for="inputEmail3" class="control-label">ST</label>
								<select id="mySel" name="regime" class="regime" style="width: 20%!Important;"></select>
						</div>
					</div>

					<div class="form-group">
						<div class="row" style="margin-left: 5px;">
							<label for="inputEmail3" class="control-label" style="width: 14%!Important;display: initial;">ICMS</label>
								<input type="" class="form-control" id="produto" style="width: 14%!Important;display: initial;" />
							<label for="inputEmail3" class="control-label" style="width: 14%!Important;display: initial;">MVA</label>
								<input type="" class="form-control" id="produto" style="width: 14%!Important;display: initial;" />
						</div>
					</div>
					<div class="row" style="margin-left: 5px;">
						<div class="form-group">
							<label for="inputEmail3" class="control-label">CSOSN</label>
							<select id="mySel" name="regime" class="regime" style="width: 56%!Important;"></select>
						</div>
					</div>
				</div>

			</div>

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Ultima Remarcação
									</h3>
								</div>
								<div class="panel-body">
									<div class="col-sm-8">
										<input type="" class="form-control" id="produto" />
									</div>
								</div>
							</div>

							<div class="panel panel-default">
								<div class="panel-heading">

								</div>
								<div class="panel-body">
									<div class="row">
										<label for="inputEmail3" class="col-sm-3 control-label">
											Cod Fornecedor
										</label>
										<div class="col-sm-8">
											<input type="" class="form-control" id="produto" />
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
	<div class="row">
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Ultima Compra
					</h3>
				</div>
				<div class="panel-body">
					<div class="row" style="margin-left:10px">
						<span class="label label-default">10/10/2010</span>
						<span class="label label-default">Nota Fiscal :</span><span class="label label-default"> 00001</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			 <div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Ultima Venda
					</h3>
				</div>
				<div class="panel-body">
					<div class="row" style="margin-left:10px">
						<span class="label label-default">10/10/2010</span>
						<span class="label label-default">Nota Fiscal :</span><span class="label label-default"> 00001</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">

		</div>
		<div class="col-md-3">

		</div>
	</div>
</div>
