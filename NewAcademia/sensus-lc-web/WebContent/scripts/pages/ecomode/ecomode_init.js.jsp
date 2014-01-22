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


	if ($.address.parameter("messageCode") != null)
	{
		var sError = ($.address.parameter("operationSuccess") == "false") ? "error" : "confirm";
		var oArguments = ($.address.parameter("arguments") != "undefined") ? JSON.parse(unescape($.address.parameter("arguments"))) : null;
		var sMessage = $.sc.locale(unescape($.address.parameter("messageCode")), oArguments);
		$.sc.showMessage("messaging-main", sMessage, sError);

		// remove parameters from url
		$.address.parameter("messageCode",null);
		$.address.parameter("arguments",null);
		$.address.parameter("operationSuccess",null);
	}

	sensus.pages.ecomode.tags.init();

	$("#submit-ecomode").on("click", function(e)
	{

		e.preventDefault();
		var uploadFile = $('#upload-ecomode').val().length;

		if (uploadFile == 0)
		{
			$('#upload-ecomode').validationEngine('showPrompt', $.sc.locale('validation.fieldrequired'), 'red', '', true);
			return false;
		}
		else
		{
			if(!$('#upload-ecomode').val().indexOf('.csv'))
			{
				$('#upload-ecomode').validationEngine('showPrompt', $.sc.locale('validation.onlyCsv'), 'red', '', true);
				return false;
			}

			/** Submit Form */
			$(this).parents('form').submit();
		}
	});

	$(".cancel-edit").on("click", function(e)
	{
		e.preventDefault();
		/** TODO Verify is this code is working when the method fetchAllTags is ready */
		importEcoModeForm.reset();
		$(".chzn-choices .search-choice").each(function()
		{
		    $(this).remove();
		});
	});

	$('.note a').on("click", function(e)
	{
		e.preventDefault();
		$.sc.launchActionDialog("fileFormatDialog", sensus.pages.ecomode.dialogSettings.fileFormatDialog);
	});

});
</script>
</sec:authorize>