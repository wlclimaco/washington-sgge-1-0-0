<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<div class="email">
	<div class="form-group">
		<label class="col-xs-1 control-label">Email tipo</label>
		<div class="col-xs-3">
			<select id="mySel" name="book[0].emailTipo" class="email-type">
				<option value="1"> teste 001</option>
				<option value="2"> teste 002</option>
				<option value="3"> teste 003</option>
				<option value="4"> teste 004</option>
				<option value="5"> teste 005</option>

			</select><br>
		</div>
		<label class="col-xs-1 control-label">Email</label>
		<div class="col-xs-5">
			<input type="text" class="form-control email-address" name="book[0].email"  placeholder="exemplo@exemplo.com" />
		</div>
		<div class="col-xs-1">
			<button type="button" class="btn btn-default addButton2"><i class="fa fa-plus"></i></button>
		</div>
	</div>

	<div class="form-group hide" id="bookTemplate2">
		<label class="col-xs-1 control-label">Email tipo</label>
		<div class="col-xs-3">
			<select id="mySel" name="emailTipo" class="email-type">
				<option value="1"> teste 001</option>
				<option value="2"> teste 002</option>
				<option value="3"> teste 003</option>
				<option value="4"> teste 004</option>
				<option value="5"> teste 005</option>
			</select>
		</div>
		<label class="col-xs-1 control-label">Email</label>
		<div class="col-xs-5">
			<input type="text" class="form-control email-address" name="email"  placeholder="exemplo@exemplo.com" />
		</div>
		<div class="col-xs-1">
			<button type="button" class="btn btn-default removeButton2"><i class="fa fa-minus"></i></button>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	var bookIndex = 0;

	$('#bookForm').on('click', '.addButton2', function() {
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
			.find('[name="emailTipo"]').attr('name', 'book[' + bookIndex + '].emailTipo').end()
			.find('[name="email"]').attr('name', 'book[' + bookIndex + '].email').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].emailTipo', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].email', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton2', function() {
		var $row  = $(this).parents('.form-group'),
			index2 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].emailTipo"]'))
			.formValidation('removeField', $row.find('[name="book[' + index2 + '].email"]'));

		// Remove element containing the fields
		$row.remove();
	});

});
</script>

<jsp:include page="../../scripts/pages/email/email_main.js.jsp" flush="true" />