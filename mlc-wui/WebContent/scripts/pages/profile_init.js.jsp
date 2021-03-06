<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @namespace sensus.pages.profile
 * @description The init namespace for the Profile Page.
 * @fileoverview Initializes the profile page.
 *
 * @author Raphael Constantino
 */
$(document).ready(function() {

	sensus.util.page.stopProgressBar(1);
	sensus.util.page.initMessaging();

	$(".button").button();
	$('#tabs').tabs();
	$(".blankslate").hide();

	// ## Fill screen with datas

	var fnCallback = function(oResponse){

		var aSettings = oResponse.settings;

		for (var iKey in aSettings) {

			if (aSettings.hasOwnProperty(iKey)) {

				// Current Setting
				var oSetting = aSettings[iKey];


				//Traverses the list of properties filling the fields in the screen
				switch (oSetting.propertyEnum) {

					case "LANGUAGE" :

						$("#language").val(oSetting.propertyValue);

					break;
					case "DATE_FORMAT" :

						$("#dateformat").val(oSetting.propertyValue);

					break;
					case "PAGE_SIZE" :

						$("#pagesize").val(oSetting.propertyValue);

					break;
					case "TIME_ZONE" :

						$("#timezone").val(oSetting.propertyValue);
						$(".chzn-select").chosen();

					break;
					case "CONVERT_ENERGY_UNIT" :

						$("#unit-convert").attr('checked', oSetting.propertyValue == "true");
						$("#convert").val(oSetting.propertyValue);

					break;
					case "MONITOR_REQUEST" :

						$('.monitor', $("#prompt-monitor")).find("input[value=" + oSetting.propertyValue + "]").attr("checked", true);
						$("#pageSizeDialogShow").val(oSetting.propertyValue);

					break;
				}

			}

		}

	};

	//Performs the ajax call to search the properties
	$.sc.getJson("api/settings/fetch", null, false, fnCallback, null, null, null);

	if ($.address.parameter("messageSuccess") != null || $.address.parameter("messageError") != null) {

		$("#tabs").tabs({ selected: '3' });

	}

	/**
	 * @returns array - List of System Settings
	 */
	var getSystemSettingsValues = function(bMonitorRequest) {


		if(bMonitorRequest == null || bMonitorRequest == undefined){
			bMonitorRequest = true;
		}

		var aSettingsList = [];
		var sMonitor = $("#prompt-monitor").find("input:checked").val();

		if ($.ajaxValidator.fnIsNullOrUndefined(sMonitor)) {
			sMonitor = sensus.settings.monitor;
		}

		aSettingsList.push({ propertyEnum : "LANGUAGE",               propertyValue : $("#language").val()                              });
		aSettingsList.push({ propertyEnum : "DATE_FORMAT",            propertyValue : $("#dateformat").val()                            });
		aSettingsList.push({ propertyEnum : "PAGE_SIZE",              propertyValue : $("#pagesize").val()                              });
		aSettingsList.push({ propertyEnum : "TIME_ZONE",              propertyValue : $("#timezone").val()                              });
		aSettingsList.push({ propertyEnum : "CONVERT_ENERGY_UNIT",    propertyValue : $("#unit-convert").is(':checked') + ""            });

		if(bMonitorRequest){
			aSettingsList.push({ propertyEnum : "MONITOR_REQUEST",        propertyValue : sMonitor                                          });
		}

		aSettingsList.push({ propertyEnum : "PAGE_SIZE_SHOW_DIALOG",  propertyValue : sensus.settings.PAGE_SIZE_SHOW_DIALOG             });
		aSettingsList.push({ propertyEnum : "SHOW_DIALOG_POLYGON", 	  propertyValue : sensus.settings.SHOW_DIALOG_POLYGON      		    });

		return aSettingsList;
	};

	// ## The Save Settings on System Settings
	$(".save-settings").click(function(e) {
		e.preventDefault();

		var sMessage = $.sc.locale("action.savesettings.success");

		if(sensus.settings.baseLocale != $("#language").val()){

			sMessage = $.sc.locale("action.savesettings.success_relogin");

		}


		oSettingsRequest = new settingsRequest(getSystemSettingsValues(false));
		$.sc.getJson("api/settings/upsert", oSettingsRequest , false, $.sc.reloadProperties, sMessage);

	});

	// ## The Save Settings on Profile Settings
	$(".save-profile-main").click(function(e) {
		e.preventDefault();

		var oSettingsRequest = null;

		var hasPassword = $('#user-password-current').val() || $('#user-password-new').val() || $('#user-password-confirm').val();
		var bValidNewPass = true;


		if (hasPassword) {

			// Call validation, if has PROBLEM the script stop here.
			var oPasswordUser = $("#user-form");

			if (!oPasswordUser.validationEngine('validateField', $('#user-password-current')) && !oPasswordUser.validationEngine('validateField', $('#user-password-new')) &&
				!oPasswordUser.validationEngine('validateField', $('#user-password-confirm'))) {

				oSettingsRequest = new settingsRequest(getSystemSettingsValues(), $('#user-password-current').val(), $('#user-password-new').val());

			} else {

				bValidNewPass = false;

			}

		} else {

			oSettingsRequest = new settingsRequest(getSystemSettingsValues());

		}

		if (bValidNewPass) {

			var sMessage = $.sc.locale("action.savesettings.success");

			if(sensus.settings.baseLocale != $("#language").val()){

				sMessage = $.sc.locale("action.savesettings.success_relogin");

			}

			$.sc.getJson("api/settings/upsert", oSettingsRequest , false, $.sc.reloadProperties, sMessage);

		}
	});

	//Table of Units
	var bUnitTable = $("#units-table");

	// ## Load Units

	$(".unit-kwh", bUnitTable).find("sup").text(sensus.settings.unitkWh);
	$(".unit-mwh", bUnitTable).find("sup").text(sensus.settings.unitMWh);
	$(".unit-gwh", bUnitTable).find("sup").text(sensus.settings.unitGWh);
	$(".unit-twh", bUnitTable).find("sup").text(sensus.settings.unitTWh);
	$(".unit-pwh", bUnitTable).find("sup").text(sensus.settings.unitPWh);

	//Bubbles
	$('.bubble', "#user-form").CreateBubblePopup({
		align	       : 'center',
		width          : '200px',
		position       : 'top',
		innerHtml      : $('.bubble').attr('title'),
		themeName      : 'black',
		themePath      : 'images/jquerybubblepopup-theme',
		themeMargins   : {
			total       : '5px',
			difference  : '5px'
		},
		innerHtmlStyle : {
			color        : ($(this).attr('id')!='azure' ? '#000000' : '#333333'),
			'text-align' :'center'
		}
	});
	$('.bubble').removeAttr('title');

	//Set up default select view
	$('.register').validate();

	sensus.util.page.stopProgressBar(0, true);
});

</script>
</sec:authorize>