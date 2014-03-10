<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace     sensus.pages.systemsettings
 * @description   The init namespace for the Tag System Settings.
 * @fileoverview  Initializes the systemsettings page.
 * @author        Raphael Constantino
 *
 */
$(document).ready(function() {

	/** Apply JQuery UI */
	$(".button").button();

	/** Validate if tenant has ecomode */
	if(sensus.settings.userContext.tenant.ecoModeDisable)
	{
		$("#settings-container #tabs ul li:eq(2)").remove();
	}

	var oTabs = $("#tabs");

	/** Init tabs */
	oTabs.tabs
	({
		ajaxOptions: {cache: false},
		show : function(event, ui)
		{

			$('.formError').remove();
			var oNote = $('.note');

			if (!oNote.hasClass('inserted'))
			{

				oNote.remove();
				oNote.addClass('inserted');
				oNote.insertBefore($('.stamp').eq(1));

			}
		}
	});

	fnCallBack1 = function(data){
console.log(data);
			if (data.operationSuccess){
				$.sc.startProgressBar(null,true);
		//		$.sc.getJson(sUrlFetch,oRequestFetch, false,fnCallBack1, null, true);
				$.sc.stopGlobalProgressBar();
			}else{
				$.sc.stopGlobalProgressBar();
			}
		};

	$('.UFICommentPhotoIcon').click(function(e)
	{
		e.preventDefault();
		console.log('dd');
		$.sc.getJson("api/comum/insertComentario", {"texto":"5555555"}, false,fnCallBack1, null, true);
	});


	/** The Save Settings on System Settings */
	$(".save-settings").click(function(e)
	{
		e.preventDefault();

		var sMessage = $.sc.locale("action.savesettings.success");

		if(sensus.settings.LANGUAGE != $("#language").val())
		{
			sMessage = $.sc.locale("action.savesettings.success_relogin");
		}

		oSettingsRequest = new settingsRequest(sensus.pages.profile.getSystemSettingsValues(false));
		oSettingsRequest.settings.push({ propertyEnum : "MONITOR_REQUEST", propertyValue : "3"});	// Force monitor default setting.
		oSettingsRequest.settings.push({ propertyEnum : "SHOW_SUBSCRIPTION_DIALOG", propertyValue : "true"});	// Force subscribe dialog default setting.

		$.sc.getJson("api/settings/upsertSystemSettings", oSettingsRequest , false, $.sc.reloadProperties, sMessage);

	});

	oTabs.tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" ).removeClass("ui-widget-content");
	$( "#tabs ul" ).tabs().removeClass("ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all ui-widget ui-widget-content ui-tabs-vertical ui-tabs-nav");
    $( "#tabs li" ).removeClass( "ui-corner-top ui-state-default");

	$("#insertDiv").append($("#getDiv").find("div"));

	$.sc.stopGlobalProgressBar();

});
</script>
</sec:authorize>