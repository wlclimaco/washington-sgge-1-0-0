<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<!-- cnae: -->
<div class="cnae">
	<div class="form-group">
		<label class="col-xs-1 control-label">Cnae</label>
		<div class="col-xs-8">
			<select id="mySel" name="book[0].cnae" class="cnaeId" style="width: 80%;">
			</select><br>
		</div>
		<div class="col-xs-1">
			<button type="button" class="btn btn-default addButton1"><i class="fa fa-plus"></i></button>
		</div>
	</div>

	<div class="form-group hide" id="bookTemplate1">
		<label class="col-xs-1 control-label">Cnae</label>
		<div class="col-xs-8">
			<select id="mySel" name="cnae" class="cnaeId" style="width: 80%;">
			</select>
		</div>
		<div class="col-xs-1">
			<button type="button" class="btn btn-default removeButton1"><i class="fa fa-minus"></i></button>
		</div>
	</div>
</div>
<script type="text/javascript">

$(document).ready(function() {

	var bookIndex = 0;

	$('#bookForm').on('click', '.addButton1', function() {
		bookIndex++;
		var $template = $('#bookTemplate1'),
			$clone    = $template
							.clone()
							.removeClass('hide')
							.removeAttr('id')
							.attr('data-book-index', bookIndex)
							.insertBefore($template);

		// Update the name attributes
		$clone
			.find('[name="cnae"]').attr('name', 'book[' + bookIndex + '].cnae').end();

		// Add new fields
		// Note that we also pass the validator rules for new field as the third parameter
		$('#bookForm')
			.formValidation('addField', 'book[' + bookIndex + '].cnae', priceValidators);
	})

	// Remove button click handler
	.on('click', '.removeButton1', function() {
		var $row  = $(this).parents('.form-group'),
			index1 = $row.attr('data-book-index');

		// Remove fields
		$('#bookForm')
			.formValidation('removeField', $row.find('[name="book[' + index1 + '].cnae"]'));

		// Remove element containing the fields
		$row.remove();
	});
});
</script>


<jsp:include page="../../scripts/pages/cnae/cnae_main.js.jsp" flush="true" />