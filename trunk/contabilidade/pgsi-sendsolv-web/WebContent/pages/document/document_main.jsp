<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="form-group">

	<label class="col-xs-0 control-label">CNPJ</label>
	<div class="col-xs-3">
		<input type="text" class="form-control" id="cnpj" name="book[0].cnpj" placeholder="00.000.000/0000-00" />
	</div>
	<label class="col-xs-1 control-label">IM</label>
	<div class="col-xs-3">
		<input type="text" class="form-control" id="im" name="book[0].im" placeholder="000.000.000.000" />
	</div>
	<label class="col-xs-1 control-label">IE</label>
	<div class="col-xs-3">
		<input type="text" class="form-control" id="ie" name="book[0].ie" placeholder="000.000.000-0" />
	</div>

</div>

<div class="form-group hide" id="bookTemplate6">

	<label class="col-xs-0 control-label">CNPJ</label>
	<div class="col-xs-3">
		<input type="text" class="form-control" name="book[0].email" placeholder="ISBN" />
	</div>
	<label class="col-xs-1 control-label">IM</label>
	<div class="col-xs-3">
		<input type="text" class="form-control" name="book[0].email" placeholder="ISBN" />
	</div>
	<label class="col-xs-1 control-label">IE</label>
	<div class="col-xs-3">
		<input type="text" class="form-control" name="book[0].email" placeholder="ISBN" />
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	var bookIndex = 0;

	$('#bookForm').on('click', '.addButton6', function() {
		bookIndex++;
		var $template = $('#bookTemplate6'),
			$clone    = $template
							.clone()
							.removeClass('hide')
							.removeAttr('id')
							.attr('data-book-index', bookIndex)
							.insertBefore($template);

		// Update the name attributes
		$clone
			.find('[name="email"]').attr('name', 'book[' + bookIndex + '].emailTipo').end()
			.find('[name="email"]').attr('name', 'book[' + bookIndex + '].email').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].emailTipo', priceValidators)
			.formValidation('addField', 'book[' + bookIndex + '].email', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton6', function() {
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

<jsp:include page="../../scripts/pages/document/document_main.js.jsp" flush="true" />