<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
					</h3>
				</div>
				<div class="panel-body">
					<div class="row" style="margin-left: 30px;">
						<img alt="Bootstrap Image Preview" src="http://lorempixel.com/140/140/" />
					</div>
				</div>
				<div class="panel-footer">

				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="row">
						<div class="form-group">
							<div class="checkbox">
								<label>
									<input type="checkbox" style="position:absolute;left:50px;top:0px;" />
									<input type="checkbox" style="position:absolute;left:50px;top:20px;" />
									<label for="inputEmail3" class="col-sm-3 control-label" style="position:absolute;left:30px;top:0px;width:94px;height:27px;line-height:27px;z-index:1;">Produto</label>
									<label for="inputEmail3" class="col-sm-3 control-label" style="position:absolute;left:30px;top:20px;width:94px;height:27px;line-height:27px;z-index:1;">Perda</label>
								</label>
							</div>
							<select id="mySel" name="regime" class="regime" style="position:absolute;left:160px;top:30px;width:374px!Important;height:30px;line-height:27px;z-index:1;" /></select>
						</div>
						<label for="inputEmail3" class="col-sm-3 control-label" style="position:absolute;left:107px;top:34px;">
							Produto
						</label>
						<label for="inputEmail3" class="col-sm-3 control-label" style="position:absolute;left:569px;top:34px;">
							Rentabilidade
						</label>
						<input type="" class="form-control" id="produto" style="position:absolute;left:683px;top:30px;width:94px;height:27px;line-height:27px;z-index:1;" />
						<button type="submit" class="btn btn-default" style="position:absolute;left:792px;top:30px;">Submit</button>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-hover table-striped table-condensed">
				<thead>
					<tr>
						<th>
							#
						</th>
						<th>
							Produto
						</th>
						<th>
							Rentabilidade (%)
						</th>
						<th>

						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							1
						</td>
						<td>
							Vaca
						</td>
						<td>
							5
						</td>
						<td>
							<button type="submit" class="btn btn-default">Excluir</button>
						</td>
					</tr>
					<tr>
						<td>
							1
						</td>
						<td>
							Vaca
						</td>
						<td>
							5
						</td>
						<td>
							<button type="submit" class="btn btn-default">Excluir</button>
						</td>
					</tr>
					<tr>
						<td>
							1
						</td>
						<td>
							Vaca
						</td>
						<td>
							5
						</td>
						<td>
							<button type="submit" class="btn btn-default">Excluir</button>
						</td>
					</tr>
				</tbody>
			</table>
				</div>
				<div class="panel-footer" style="height:50px;">
					<div class="row">
						<label for="inputEmail3" class="col-sm-3 control-label" style="left:237px;top:0px;width:394px;height:27px;line-height:27px;z-index:1;">
							Total dos Percentuais
						</label>
						<input type="" class="form-control" id="produto"  style="left:770px;top:0px;width:94px;height:27px;line-height:27px;z-index:1;"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>