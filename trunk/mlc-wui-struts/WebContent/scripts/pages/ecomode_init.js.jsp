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
	
	sensus.pages.ecomode.tags.init();
	
	$("#submit-ecomode").click(function(){
	
		var uploadFile = $('#upload-ecomode').val().length;
				
		if (uploadFile == 0) {

			$('#upload-ecomode').validationEngine('showPrompt', sensus.locale.get('validation.fieldrequired'), 'red', '', true);
			return false;		
			
		} else {
		
			if(!$('#upload-ecomode').val().contains('.csv')) {
		
				$('#upload-ecomode').validationEngine('showPrompt', sensus.locale.get('validation.onlyCsv'), 'red', '', true);
				return false;		
			
			}
			
			sensus.pages.ecomode.tags.fnLoadTagChosen();
		}
		
	});
	
	$(".cancel-edit").click(function() {
		importEcoModeForm.reset();
		$(".chzn-choices .search-choice").each(function(){
		    $(this).remove();
		});
		return false;
	});
	
	$(".note a").click(function(){
		
		sensus.util.actiondialog.launchActionDialog("fileFormatDialog", sensus.pages.ecomode.dialogSettings.fileFormatDialog);
		return false;
		
	});
	
});
</script>
</sec:authorize>