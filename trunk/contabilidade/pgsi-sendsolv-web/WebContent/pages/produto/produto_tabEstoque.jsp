<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-8">
			<div class="endereco">
				<div class="form-group">
					<div class="row">
						<input type="text" class="hide" id="enderecoId" name="book[0].id" />
						<label class="col-xs-1 control-label">Entidade</label>
						<div class="col-xs-3">
							<select id="mySel" name="book[0].entidade"  id="enderecoType" class="">
								<option value="1">Cobranca</option>
								<option value="2">Entrega</option>
								<option value="3">Particular</option>
								<option value="4">Outros</option>
							</select>
						</div>
						<label class="col-xs-2 control-label">Estoque Minimo</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="book[0].eMinimo"  placeholder="0" />
						</div>
					</div>
					<div class="row">
						<label class="col-xs-2 control-label">Estoque Maximo</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="book[0].eMaximo"  placeholder="0" />
						</div>



						<label class="col-xs-2 control-label">Estoque Atual</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="book[0].eAtual" id="numero" placeholder="000" />
						</div>
						<div class="col-xs-1">
							<button type="button" class="btn btn-default addButton3"><i class="fa fa-plus"></i></button>
						</div>
					</div>
				</div>

				<!-- endereco templete -->
				<div class="form-group hide" id="bookTemplate3">
					<div class="row">
						<div class="page-header"></div>
						<input type="text" class="hide" id="enderecoId" name="id" />
						<label class="col-xs-1 control-label">Entidade</label>
						<div class="col-xs-3">
							<select id="mySel" name="entidade"  id="enderecoType" class="">
								<option value="1">Cobranca</option>
								<option value="2">Entrega</option>
								<option value="3">Particular</option>
								<option value="4">Outros</option>
							</select>
						</div>
						<label class="col-xs-2 control-label">Estoque Minimo</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="eMinimo"  placeholder="0" />
						</div>
					</div>
					<div class="row">
						<label class="col-xs-2 control-label">Estoque Maximo</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="eMaximo"  placeholder="0" />
						</div>



						<label class="col-xs-2 control-label">Estoque Atual</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="eAtual" id="numero" placeholder="000" />
						</div>
						<div class="col-xs-1">
							<button type="button" class="btn btn-default removeButton3"><i class="fa fa-minus"></i></button>
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
							Entidade
						</th>
						<th>
							Estoque Inicial
						</th>
						<th>
							Estoque Final
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
							10
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
							20
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
							5
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

	$('.endereco').on('click', '.addButton3', function() {
		bookIndex++;
		var $template = $('#bookTemplate3'),
			$clone    = $template
							.clone()
							.removeClass('hide')
							.removeAttr('id')
							.attr('data-book-index', bookIndex)
							.insertBefore($template);

		// Update the name attributes
		$clone
			.find('[name="entidade"]').attr('name', 'book[' + bookIndex + '].entidade').end()
			.find('[name="eMinimo"]').attr('name', 'book[' + bookIndex + '].eMinimo').end()
			.find('[name="eMaximo"]').attr('name', 'book[' + bookIndex + '].eMaximo').end()
			.find('[name="eAtual"]').attr('name', 'book[' + bookIndex + '].eAtual').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('.endereco')
			.formValidation('addField', 'book[' + bookIndex + '].entidade', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].eMinimo', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].eMaximo', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].eAtual', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton3', function() {
		var $row  = $(this).parents('.form-group'),
			index2 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].entidade"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].eMinimo"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].eMaximo"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].eAtual"]'));

		// Remove element containing the fields
		$row.remove();
	});

});
</script>