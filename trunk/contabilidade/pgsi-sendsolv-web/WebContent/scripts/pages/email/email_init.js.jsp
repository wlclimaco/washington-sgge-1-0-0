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
	//$("#email-template").find(".email-type").fnLoadDropDownList(oEmailEnums);

	pgsi.util.page.email.form.setFieldSizes();

	// Multiple emails functionality
	$("#add-email").on("click", function() {

		var t = $(".test").length;
		var sTop = $(".test:eq("+(t-1)+")").css('top')
		if(sTop == "auto"){
			sTop ="0px"
		}
		sTop = (parseInt(sTop.replace("px",""),10)+30);

			s = '<div class="email test" style="position:absolute;left:0px;top:'+sTop+'px;width:500px;height:27px;z-index:40;">'
				+'<label for="email-type" style="margin-right: 10px;">'
					+'Email tipo'
				+'</label>'
				+'<select name="emailType" class="email-type" maxlength="254" style="position:absolute;width:94px;height:27px;line-height:27px;z-index:4;" name="cnae" value="">'
					+'<option value="1">Comercial</option>'
					+'<option value="2">Compras</option>'
					+'<option value="3">SAC</option>'
					+'<option value="4">NFE</option>'
				+'</select>'
				+'<div style="position:absolute;left:300px;top:0px;width:500px;height:27px;z-index:40;">'
					+'<label for="emailAddress">'
						+'Email</label>'
					+'<input type="text" class="email-address email " name="emailAddress" style="position:absolute;width:300px;height:27px;line-height:27px;z-index:4;" value="">'
					+'<div class="close-button-form">'
						+'<span class="icon-small-button icon-nav icon-remove" title="remove"></span>'
					+'</div>'
				+'</div>'
			+'</div>'

		$('#emails').append(s);


		var ii = parseInt($('#wb_Form3').css('top').replace("px",""),10)+40;
		$('#wb_Form3').attr({'style':'position:absolute;left:0px;top:'+ii+'px;'})
		$('#add-email').attr({'style':'position:absolute;left:195px;top:'+(ii+10)+'px;width:500px;height:16px;z-index:28;'})

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