<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.conservation
 * @description The init namespace for the Conservation Page.
 */

/**
 * @fileoverview Initializes the conservation page.
 *
 * @author Raphael Constantino
 */
$(document).ready(function() {

	sensus.util.page.initMessaging();

	if ($.address.parameter("messageError") != null) {

		sensus.util.page.showMessage("messaging-main", unescape($.address.parameter("messageError")), "error");

	} else if ($.address.parameter("messageSuccess") != null) {

		sensus.util.page.showMessage("messaging-main", unescape($.address.parameter("messageSuccess")), "confirm");

	}

	$.address.parameter("messageSuccess",0);
	$.address.parameter("messageError",0);

	// TODO Uncomment this code when the method fetchAllTags is ready
	//sensus.pages.ecomode.tags.init();

	$("#submit-ecomode").on("click", function(e){

		e.preventDefault();
		var uploadFile = $('#upload-ecomode').val().length;

		if (uploadFile == 0) {

			$('#upload-ecomode').validationEngine('showPrompt', $.sc.locale('validation.fieldrequired'), 'red', '', true);
			return false;

		} else {

			if(!$('#upload-ecomode').val().contains('.csv')) {

				$('#upload-ecomode').validationEngine('showPrompt', $.sc.locale('validation.onlyCsv'), 'red', '', true);
				return false;

			}

			sensus.pages.ecomode.tags.fnLoadTagChosen();
		}

	});

	$(".cancel-edit").on("click", function(e){
		e.preventDefault();
		// TODO Verify is this code is working when the method fetchAllTags is ready
		importEcoModeForm.reset();
		$(".chzn-choices .search-choice").each(function(){
		    $(this).remove();
		});
	});

	$('.note a').on("click", function(e){
		e.preventDefault();
		$.sc.launchActionDialog("fileFormatDialog", sensus.pages.ecomode.dialogSettings.fileFormatDialog);
	});

});
</script>
</sec:authorize>