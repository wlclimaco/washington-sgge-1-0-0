<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<div class="socios">
	<div class="form-group">
		<div class="row">
			<label class="col-xs-0 control-label">Nome</label>
			<div class="col-xs-4">
				<input type="text" class="form-control" name="book[0].nome" placeholder="nome" />
			</div>

			<label class="col-xs-1 control-label">CPF</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" name="book[0].cpf" placeholder="000.000.000-00" />
			</div>

			<label class="col-xs-1 control-label">RG</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" name="book[0].rg" placeholder="00.000.000" />
			</div>
		</div>
		<div class="row">
			<label class="col-xs-0 control-label">Cotas</label>
			<div class="col-xs-4">
				<input type="text" class="form-control" name="book[0].cota" placeholder="0" />
			</div>

			<label class="col-xs-1 control-label">Porcentagem</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" name="book[0].porcentagem" placeholder="000" />
			</div>
			<div class="col-xs-1">
				<button type="button" class="btn btn-default addButton7"><i class="fa fa-plus"></i></button>
			</div>
		</div>

	</div>

	<div class="form-group hide" id="bookTemplate7">
		<div class="row">
			<label class="col-xs-0 control-label">Nome</label>
			<div class="col-xs-4">
				<input type="text" class="form-control" name="nome" placeholder="nome" />
			</div>

			<label class="col-xs-1 control-label">CPF</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" name="cpf" placeholder="000.000.000-00" />
			</div>

			<label class="col-xs-1 control-label">RG</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" name="rg" placeholder="00.000.000" />
			</div>
		</div>
		<div class="row">
			<label class="col-xs-0 control-label">Cotas</label>
			<div class="col-xs-4">
				<input type="text" class="form-control" name="cota" placeholder="0" />
			</div>

			<label class="col-xs-1 control-label">Porcentagem</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" name="porcentagem" placeholder="000" />
			</div>
			<div class="col-xs-1">
				<button type="button" class="btn btn-default removeButton7"><i class="fa fa-minus"></i></button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	var bookIndex = 0;

	$('#bookForm').on('click', '.addButton7', function() {
		bookIndex++;
		var $template = $('#bookTemplate7'),
			$clone    = $template
							.clone()
							.removeClass('hide')
							.removeAttr('id')
							.attr('data-book-index', bookIndex)
							.insertBefore($template);

		// Update the name attributes
		$clone
			.find('[name="nome"]').attr('name', 'book[' + bookIndex + '].nome').end()
			.find('[name="cpf"]').attr('name', 'book[' + bookIndex + '].cpf').end()
			.find('[name="rg"]').attr('name', 'book[' + bookIndex + '].rg').end()
			.find('[name="cota"]').attr('name', 'book[' + bookIndex + '].cota').end()
			.find('[name="porcentagem"]').attr('name', 'book[' + bookIndex + '].porcentagem').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].nome', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].cpf', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].rg', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].cota', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].porcentagem', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton7', function() {
		var $row  = $(this).parents('.form-group'),
			index2 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].nome"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].cpf"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].rg"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].cota"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].porcentagem"]'));

		// Remove element containing the fields
		$row.remove();
	});

});
</script>