<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="endereco">
	<div class="form-group">
		<div class="row">
			<input type="text" class="hide" id="enderecoId" name="book[0].id" />
			<label class="col-xs-1 control-label">Tipo</label>
			<div class="col-xs-2">
				<select id="mySel" name="book[0].enderecoType"  id="enderecoType" class="">
					<option value="1">Cobranca</option>
					<option value="2">Entrega</option>
					<option value="3">Particular</option>
					<option value="4">Outros</option>
				</select>
			</div>
			<label class="col-xs-1 control-label">Cep</label>
			<div class="col-xs-2">
				<select id="mySel" name="book[0].cep" id="cep" class="">
					<option value="1">38082-243</option>
					<option value="2">38082-243</option>
					<option value="3">38082-243</option>
					<option value="4">38082-243</option>
					<option value="5">38082-243</option>
				</select>
			</div>
			<label class="col-xs-1 control-label">Logradouro</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" name="book[0].logradouro"  placeholder="Endereco" />
			</div>
			<label class="col-xs-1 control-label">N</label>
			<div class="col-xs-1">
				<input type="text" class="form-control" name="book[0].numero" id="numero" placeholder="000" />
			</div>
		</div>
		<div class="row">
			<label class="col-xs-1 control-label">Estado</label>
			<div class="col-xs-4">
				<select id="mySel" name="book[0].estado" id="estado" class="">
					<option value="1">Minas Gerais</option>
					<option value="2">São Paulo</option>
					<option value="3">Maranhao</option>
				</select>
			</div>
			<label class="col-xs-1 control-label">Cidade</label>
			<div class="col-xs-4">
				<select id="mySel" name="book[0].Cidade" id="cidade" class="">
					<option  value="1">Minas Gerais</option>
					<option  value="2">São Paulo</option>
					<option  value="3">Maranhao</option>
				</select>
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
			<label class="col-xs-1 control-label">Tipo</label>
			<div class="col-xs-2">
				<select id="mySel" name="enderecoType"  id="enderecoType" class="">
					<option value="1">Cobranca</option>
					<option value="2">Entrega</option>
					<option value="3">Particular</option>
					<option value="4">Outros</option>
				</select>
			</div>
			<label class="col-xs-1 control-label">Cep</label>
			<div class="col-xs-2">
				<select id="mySel" name="cep" id="cep" class="">
					<option value="1">38082-243</option>
					<option value="2">38082-243</option>
					<option value="3">38082-243</option>
					<option value="4">38082-243</option>
					<option value="5">38082-243</option>
				</select>
			</div>
			<label class="col-xs-1 control-label">Logradouro</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" name="logradouro"  placeholder="Endereco" />
			</div>
			<label class="col-xs-1 control-label">N</label>
			<div class="col-xs-1">
				<input type="text" class="form-control" name="numero" id="numero" placeholder="000" />
			</div>
		</div>
		<div class="row">
			<label class="col-xs-1 control-label">Estado</label>
			<div class="col-xs-4">
				<select id="mySel" name="estado" id="estado" class="">
					<option value="1">Minas Gerais</option>
					<option value="2">São Paulo</option>
					<option value="3">Maranhao</option>
				</select>
			</div>
			<label class="col-xs-1 control-label">Cidade</label>
			<div class="col-xs-4">
				<select id="mySel" name="Cidade" id="cidade" class="">
					<option  value="1">Minas Gerais</option>
					<option  value="2">São Paulo</option>
					<option  value="3">Maranhao</option>
				</select>
			</div>
			<div class="col-xs-1">
				<button type="button" class="btn btn-default removeButton3"><i class="fa fa-minus"></i></button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	var bookIndex = 0;

	//endereco
	$('#bookForm').on('click', '.addButton3', function() {
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
			.find('[name="id"]').attr('name', 'book[' + bookIndex + '].id').end()
			.find('[name="enderecoType"]').attr('name', 'book[' + bookIndex + '].enderecoType').end()
			.find('[name="cep"]').attr('name', 'book[' + bookIndex + '].cep').end()
			.find('[name="logradouro"]').attr('name', 'book[' + bookIndex + '].logradouro').end()
			.find('[name="numero"]').attr('name', 'book[' + bookIndex + '].numero').end()
			.find('[name="estado"]').attr('name', 'book[' + bookIndex + '].estado').end()
			.find('[name="cidade"]').attr('name', 'book[' + bookIndex + '].cidade').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].enderecoType', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].cep', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].logradouro', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].numero', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].estado', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].cidade', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton3', function() {
		var $row  = $(this).parents('.form-group'),
			index3 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index3 + '].enderecoType"]'))
			.formValidation('removeField', $row.find('[name="book[' + index3 + '].cep"]'))
			.formValidation('removeField', $row.find('[name="book[' + index3 + '].logradouro"]'))
			.formValidation('removeField', $row.find('[name="book[' + index3 + '].numero"]'))
			.formValidation('removeField', $row.find('[name="book[' + index3 + '].estado"]'))
			.formValidation('removeField', $row.find('[name="book[' + index3 + '].cidade"]'));

		// Remove element containing the fields
		$row.remove();
	});

});
</script>

<jsp:include page="../../scripts/pages/endereco/endereco_main.js.jsp" flush="true" />