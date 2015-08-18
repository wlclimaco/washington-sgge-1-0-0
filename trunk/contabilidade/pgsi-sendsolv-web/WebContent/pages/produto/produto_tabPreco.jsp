<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-8">
			<div class="preco">
				<div class="panel panel-default">
					<div class="panel-heading">
					</div>
					<div class="panel-body">
					<div class="row">
						<div class="checkbox">
							<label>
								<input id="bPreco" type="checkbox" /> Aplicar o mesmo valor de venda para todas as entidades.
							</label>
						</div>
						<div id="iPreco" class="hide">
							<label class="col-xs-1 control-label" style="width:10%!Important;">Preco</label>
							<input type="text" class="form-control email-address" name="book[0].preco"  placeholder="R$00.00" style="width:10%!Important;"/>
						</div>
					</div>
					<div id="aPreco" class="">
						<div class="row">
							<div class="form-group">
								<label class="col-xs-1 control-label">Entidade</label>
								<div class="col-xs-2">
									<select id="mySel" name="book[0].entidade" class="email-type" style="width:83%!Important;">
										<option value="1"> teste 001</option>
										<option value="2"> teste 002</option>
										<option value="3"> teste 003</option>
										<option value="4"> teste 004</option>
										<option value="5"> teste 005</option>

									</select><br>
								</div>
								<label class="col-xs-1 control-label" style="width:11%!Important;">Preco Type</label>
								<div class="col-xs-2">
									<select id="mySel" name="book[0].precoType" class="email-type" style="width:83%!Important;">
										<option value="1"> teste 001</option>
										<option value="2"> teste 002</option>
										<option value="3"> teste 003</option>
										<option value="4"> teste 004</option>
										<option value="5"> teste 005</option>

									</select><br>
								</div>
								<label class="col-xs-1 control-label" style="width:10%!Important;">Preco</label>
									<input type="text" class="form-control email-address" name="book[0].preco"  placeholder="R$00.00" style="width:10%!Important;"/>
									<button type="button" class="btn btn-default addButton2" style="left: 73%!Important;position: absolute;"><i class="fa fa-plus"></i></button>


							</div>
						</div>
						<div class="form-group hide" id="bookTemplate2">
									<div class="form-group">
										<div class="row">
											<label class="col-xs-1 control-label">Entidade</label>
									<div class="col-xs-2">
										<select id="mySel" name="book[0].entidade" class="email-type" style="width:83%!Important;">
											<option value="1"> teste 001</option>
											<option value="2"> teste 002</option>
											<option value="3"> teste 003</option>
											<option value="4"> teste 004</option>
											<option value="5"> teste 005</option>

										</select><br>
									</div>
									<label class="col-xs-1 control-label" style="width:11%!Important;">Preco Type</label>
									<div class="col-xs-2">
										<select id="mySel" name="book[0].precoType" class="email-type" style="width:83%!Important;">
											<option value="1"> teste 001</option>
											<option value="2"> teste 002</option>
											<option value="3"> teste 003</option>
											<option value="4"> teste 004</option>
											<option value="5"> teste 005</option>

										</select><br>
									</div>
									<label class="col-xs-1 control-label">Preco</label>
									<input type="text" class="form-control email-address" name="book[0].email"  placeholder="exemplo@exemplo.com" style="width:130px!Important;display:inline-block!Important;"/>
									<button type="button" class="removeButton2" ><i class="fa fa-minus"></i></button>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>

	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading"> Historico
			</div>
			<div class="panel-body" style="margin-left: 30px;">
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
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	var bookIndex = 0;

	$('.preco').on('click', '.addButton2', function() {
		bookIndex++;
		var $template = $('#bookTemplate2'),
			$clone    = $template
							.clone()
							.removeClass('hide')
							.removeAttr('id')
							.attr('data-book-index', bookIndex)
							.insertBefore($template);

		// Update the name attributes
		$clone
			.find('[name="entidade"]').attr('name', 'book[' + bookIndex + '].entidade').end()
			.find('[name="precoType"]').attr('name', 'book[' + bookIndex + '].precoType').end()
			.find('[name="preco"]').attr('name', 'book[' + bookIndex + '].preco').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].entidade', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].precoType', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].preco', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton2', function() {
		var $row  = $(this).parents('.form-group'),
			index2 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].entidade"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].precoType"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].preco"]'));

		// Remove element containing the fields
		$row.remove();
	});

	$('.preco').on('click', '#bPreco', function() {
		if($(this).prop('checked') == true){
			$('#aPreco').addClass('hide')
			$('#iPreco').removeClass('hide')
		}else{
			$('#aPreco').removeClass('hide')
			$('#iPreco').addClass('hide')
		}
	});

});
</script>