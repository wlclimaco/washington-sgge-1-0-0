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
				<div class="col-sm-10">
					<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
				</div>
			</div>
		</div>
		<div class="col-md-1">
			<div class="checkbox">
				<label>
					<input type="checkbox" /> Inativo
				</label>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="row">
				 <div class="form-group">

					<label for="inputEmail3" class="col-sm-2 control-label">
						Grupo
					</label>
					<div class="col-sm-10">
						<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">

					<label for="inputEmail3" class="col-sm-2 control-label">
						Sub - Grupo
					</label>
					<div class="col-sm-10">
						<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">

					<label for="inputEmail3" class="col-sm-2 control-label">
						Fornecedor
					</label>
					<div class="col-sm-10">
						<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">

					<label for="inputEmail3" class="col-sm-2 control-label">
						Marca
					</label>
					<div class="col-sm-10">
						<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
					</div>
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
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Tributacao
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="row">
							<label for="inputEmail3" class="col-sm-2 control-label">
							CST
							</label>
							<div class="col-sm-3">
								<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
							</div>
							<label for="inputEmail3" class="col-sm-2 control-label">
								ST
							</label>
							<div class="col-sm-3">
								<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<label for="inputEmail3" class="col-sm-2 control-label">
								ICMS
							</label>
							<div class="col-sm-3">
								<input type="" class="form-control" id="produto" />
							</div>
							<label for="inputEmail3" class="col-sm-2 control-label">
								MVA
							</label>
							<div class="col-sm-3">
								<input type="" class="form-control" id="produto" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">
								CSOSN
							</label>
							<div class="col-sm-5">
								<select id="mySel" name="regime" class="regime" style="width: 80%;"></select>
							</div>
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
						Panel title
					</h3>
				</div>
				<div class="panel-body">
					Panel content
				</div>
				<div class="panel-footer">
					Panel footer
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								Panel title
							</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
						<div class="panel-footer">
							Panel footer
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								Panel title
							</h3>
						</div>
						<div class="panel-body">
							Panel content
						</div>
						<div class="panel-footer">
							Panel footer
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
			 <span class="label label-default">Label</span>
		</div>
		<div class="col-md-3">
			 <span class="label label-default">Label</span>
		</div>
		<div class="col-md-3">
			 <span class="label label-default">Label</span>
		</div>
		<div class="col-md-3">
			 <span class="label label-default">Label</span>
		</div>
	</div>
</div>
