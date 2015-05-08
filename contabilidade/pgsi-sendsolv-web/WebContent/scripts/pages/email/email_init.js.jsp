<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

$(document).ready(function()
{
	var oContactTypeEnums = $.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.cbof.model.ContactTypeEnum");

	var oEmailEnums = [];

	// 3 = EMAIL_PERSONAL, 6 = EMAIL_WORK
	for (var i = 0; i < oContactTypeEnums.length; i++) {
		if (oContactTypeEnums[i].key == 3 || oContactTypeEnums[i].key == 6) {
			oEmailEnums.push(oContactTypeEnums[i]);
		}
	}
	// Fill email dropdown
	$("#email-template").find(".email-type").fnLoadDropDownList(oEmailEnums);

	pgsi.util.page.email.form.setFieldSizes();

	// Multiple emails functionality
	$("#add-email").on("click", function() {

		// Create new node
		var sCopy = pgsi.util.page.email.form.createNewNode();

		$("#email-template").find(".container").append(sCopy);

		var $emailTemplate = $("#email-template");

		$newNode =  $emailTemplate.find(".container").find(".row-form:last-child");
		pgsi.util.page.form.fnInitSelectmenu($newNode.find("select"));

		pgsi.util.page.email.form.setFieldSizes();

		$newNode.find('span').first().focus();

		var ii = parseInt($('#wb_Form3').css('top').replace("px",""),10)+40;
		$('#wb_Form3').attr({'style':'position:absolute;left:0px;top:'+ii+'px;'})

	});

	$("#email-template").on("click", ".row-form .icon-remove", function() {

		var $row = $(this).parents(".row-form");
		$row.addClass("hide");
		$row.find('.required').removeClass('required');
		var $emailTemplate = $("#email-template");
		$row.find(".model-action").val("delete");

		if ($emailTemplate.find("input[type='radio']:visible:checked").length === 0 ) {
			$firstEmail = $emailTemplate.find(".row-form:visible:first");
			$firstEmail.find("input[type='radio']").prop("checked", true);
			$firstEmail.find(".close-button-form").addClass("hide");
		}

	});

});

</script>

</sec:authorize>