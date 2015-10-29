<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<div class="telefone">
	<div class="form-group">
		<label class="col-xs-0 control-label">Telefone Tipo</label>
		<div class="col-xs-2">
			<select id="mySel" name="book[0].telefoneType" class="telefoneType">
				<option value="1"> teste 001</option>
				<option value="2"> teste 002</option>
				<option value="3"> teste 003</option>
				<option value="4"> teste 004</option>
				<option value="5"> teste 005</option>

			</select><br>
		</div>

		<label class="col-xs-1 control-label">DDD</label>
		<div class="col-xs-2">
			<input type="text" class="form-control ddd" name="book[0].ddd" placeholder="000" />
		</div>

		<label class="col-xs-1 control-label">Tel :</label>
		<div class="col-xs-3">
			<input type="text" class="form-control telefone" name="book[0].telefone" placeholder="00000000" />
		</div>
		<div class="col-xs-1">
			<button type="button" class="btn btn-default addButton5"><i class="fa fa-plus"></i></button>
		</div>
	</div>

	<div class="form-group hide" id="bookTemplate5">
	<label class="col-xs-0 control-label">Telefone Tipo</label>
		<div class="col-xs-2">
			<select id="mySel" name="telefoneType" class="telefoneType">
				<option value="1"> teste 001</option>
				<option value="2"> teste 002</option>
				<option value="3"> teste 003</option>
				<option value="4"> teste 004</option>
				<option value="5"> teste 005</option>

			</select><br>
		</div>

		<label class="col-xs-1 control-label">DDD</label>
		<div class="col-xs-2">
			<input type="text" class="form-control ddd" name="ddd" placeholder="000" />
		</div>

		<label class="col-xs-1 control-label">Tel :</label>
		<div class="col-xs-3">
			<input type="text" class="form-control telefone" name="telefone" placeholder="00000000" />
		</div>
		<div class="col-xs-1">
			<button type="button" class="btn btn-default removeButton5"><i class="fa fa-minus"></i></button>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	var bookIndex = 0;

	$('#bookForm').on('click', '.addButton5', function() {
		bookIndex++;
		var $template = $('#bookTemplate5'),
			$clone    = $template
							.clone()
							.removeClass('hide')
							.removeAttr('id')
							.attr('data-book-index', bookIndex)
							.insertBefore($template);

		// Update the name attributes
		$clone
			.find('[name="telefoneType"]').attr('name', 'book[' + bookIndex + '].telefoneType').end()
			.find('[name="ddd"]').attr('name', 'book[' + bookIndex + '].ddd').end()
			.find('[name="telefone"]').attr('name', 'book[' + bookIndex + '].telefone').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].telefoneType', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].ddd', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].telefone', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton5', function() {
		var $row  = $(this).parents('.form-group'),
			index2 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].telefoneType"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].ddd"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].telefone"]'));

		// Remove element containing the fields
		$row.remove();
	});

});
</script>

<jsp:include page="../../scripts/pages/telefone/telefone_main.js.jsp" flush="true" />