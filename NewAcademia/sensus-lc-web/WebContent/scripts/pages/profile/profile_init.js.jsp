<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Analytic User')">
<script type="text/javascript">

/**
 * @namespace sensus.pages.profile
 * @description The init namespace for the Profile Page.
 * @fileoverview Initializes the profile page.
 *
 * @author Raphael Constantino
 */
$(document).ready(function() {
	var bPreload = true;

	$.ajax({
	url    : "subscribe_include",
	async  : false,
	success: function(data) {
		$("#subscribe-html").append(data);
	}});

	$(".button").button();
	$('#tabs').tabs();
	$(".blankslate").hide();
	<c:choose>
		<c:when test="${not empty refresh}">
			bPreload = false;
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${empty user_response}">
					var oPreLoadResponseUser = null;
				</c:when>
				<c:otherwise>
					var oPreLoadResponseUser = ${user_response};
				</c:otherwise>
			</c:choose>
			<c:choose>
					<c:when test="${empty response}">
							var oPreLoadResponse = null;
					</c:when>
					<c:otherwise>
							var oPreLoadResponse = ${response};
					</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

	var userResponse = null;
	if($.address.path() == "/profile")
	{
		var fnUserCallBack = function(oResponse)
		{
			userResponse = oResponse;
			$("#user-email").val(userResponse.user.email);
		}
		if(bPreload && $.sc.isValidPreLoad(oPreLoadResponseUser, true))
		{
			fnUserCallBack(oPreLoadResponseUser);
		}
		else
		{
			var userRequest = {"id":  UserContext().id} ;
			$.sc.getJson("api/user/fetch", userRequest, false, fnUserCallBack);
		}
	}
	/** Fill screen with datas used by profile and system Setttings */
	var fnCallback = function(oResponse)
	{
		var aSettings = oResponse.settings;

		for (var iKey in aSettings)
		{
			if (aSettings.hasOwnProperty(iKey))
			{
				/** Current Setting */
				var oSetting = aSettings[iKey];

				/** Traverses the list of properties filling the fields in the screen */
				switch (oSetting.propertyEnum)
				{
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
					/* ALARMS */
					case "SUBSCRIBE_ALARM_BOARD_FAILURE" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_ALARM_BOARD_FAILURE").attr('checked',true);
							$("#SUBSCRIBE_ALARM_BOARD_FAILURE").next().text( $.sc.locale("profile.page.subscribed"));
						}
						break;

					case "SUBSCRIBE_ALARM_LAMP_FAILURE" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_ALARM_LAMP_FAILURE").attr('checked', true);
							$("#SUBSCRIBE_ALARM_LAMP_FAILURE").next().text( $.sc.locale("profile.page.subscribed"));
						}
						break;

					case "SUBSCRIBE_ALARM_POWER_FAILURE" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_ALARM_POWER_FAILURE").attr('checked', true);
							$("#SUBSCRIBE_ALARM_POWER_FAILURE").next().text( $.sc.locale("profile.page.subscribed"));
						}
						break;

					case "SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE").attr('checked',true);
							$("#SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE").next().text( $.sc.locale("profile.page.subscribed"));
						}
						break;

					case "SUBSCRIBE_ALARM_METROLOGY_ERROR" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_ALARM_METROLOGY_ERROR").attr('checked',true);
							$("#SUBSCRIBE_ALARM_METROLOGY_ERROR").next().text( $.sc.locale("profile.page.subscribed"));
						}
						break;

					/* WARNINGS */
					case "SUBSCRIBE_WARN_BROWNOUT_DETECTED" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_BROWNOUT_DETECTED").attr('checked', true);
							$("#SUBSCRIBE_WARN_BROWNOUT_DETECTED").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;

					case "SUBSCRIBE_WARN_COMMN_FAIL" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_COMMN_FAIL").attr('checked', true);
							$("#SUBSCRIBE_WARN_COMMN_FAIL").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;

					case "SUBSCRIBE_WARN_POWER_SURGE" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_POWER_SURGE").attr('checked', true);
							$("#SUBSCRIBE_WARN_POWER_SURGE").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;
					case "SUBSCRIBE_WARN_HIGH_CURRENT" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_HIGH_CURRENT").attr('checked', true);
							$("#SUBSCRIBE_WARN_HIGH_CURRENT").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;
					case "SUBSCRIBE_WARN_LOW_CURRENT" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_LOW_CURRENT").attr('checked', true);
							$("#SUBSCRIBE_WARN_LOW_CURRENT").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;
					case "SUBSCRIBE_WARN_METROLOGY_RESET" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_METROLOGY_RESET").attr('checked', true);
							$("#SUBSCRIBE_WARN_METROLOGY_RESET").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;
					case "SUBSCRIBE_WARN_REVERSE_ENERGY" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_REVERSE_ENERGY").attr('checked', true);
							$("#SUBSCRIBE_WARN_REVERSE_ENERGY").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;

					case "SUBSCRIBE_WARN_LIGHT_QUALITY" :
						if(oSetting.propertyValue == "true")
						{
							$("#SUBSCRIBE_WARN_LIGHT_QUALITY").attr('checked', true);
							$("#SUBSCRIBE_WARN_LIGHT_QUALITY").next().text( $.sc.locale("profile.page.subscribed"));
						}

						break;
				}
			}
		}
	};


	if(bPreload && $.sc.isValidPreLoad(oPreLoadResponse, true))
	{
		fnCallback(oPreLoadResponse);
	}
	else
	{
		/** Performs the ajax call to search the properties */
		$.sc.getJson("api/settings/fetch", { userId: $.address.parameter('userId') }, false, fnCallback, null, null, null);
	}

	if ($.address.parameter("messageCode") != null)
	{
		$("#tabs").tabs({ selected: '3' });
	}

	//Open subscribe dialog
	$("#subscribeTable").find(":checkbox").click(function(e)
	{
		e.stopPropagation();
		sensus.pages.profile.fnCallSubscribe($(this) , sensus.settings.SHOW_SUBSCRIPTION_DIALOG == "true");
	});

	/** The Save Settings on Profile Settings */
	$(".save-profile-main").click(function(e)
	{
		e.preventDefault();

		var oSettingsRequest = null;

		var bValidNewPass = true;

		if ($(".password-reset").is(":visible"))
		{
			/** Call validation, if has PROBLEM the script stop here. */
			var oPasswordUser = $("#user-form");

			if ( !oPasswordUser.validationEngine('validateField', $('#user-password-current')) &&
			     !oPasswordUser.validationEngine('validateField', $('#user-password-new'))     &&
				 !oPasswordUser.validationEngine('validateField', $('#user-password-confirm')))
			{
				oSettingsRequest = new settingsRequest(sensus.pages.profile.getSystemSettingsValues(), $('#user-password-current').val(), $('#user-password-new').val());
			}
			else
			{
				bValidNewPass = false;
			}

		}
		else
		{
			oSettingsRequest = new settingsRequest(sensus.pages.profile.getSystemSettingsValues());
		}

		if (bValidNewPass)
		{
			var sMessage = $.sc.locale("action.savesettings.success");

			if(sensus.settings.LANGUAGE != $("#language").val())
			{
				sMessage = $.sc.locale("action.savesettings.success_relogin");
			}

			if ($(".password-reset").is(":visible"))
			{
				$('#user-password-current').val("") ;
				$('#user-password-new').val("") 	;
				$('#user-password-confirm').val("") ;
				sMessage = $.sc.locale("profile.page.updatePassword");
			}

			$.sc.getJson("api/settings/upsert", oSettingsRequest , false, $.sc.reloadProperties, sMessage);

			userResponse.user.email = $("#user-email").val();
			userResponse.user.password = null;
			oRequest = {"user": userResponse.user};
			$.sc.getJson("api/user/update", oRequest, false, null);
		}
	});

	/*email validation*/
	$("#email-subscribe-form").validationEngine('attach', {
		validationEventTrigger : 'submit',
		onValidationComplete   : function(form, status) {
			if (status)
			{
				userResponse.user.email = $("#user-email").val();
				userResponse.user.password = null;
				oRequest = {"user": userResponse.user};
				$.sc.getJson("api/user/update", oRequest, false, null,$.sc.locale("profile.page.updateEmail"));
			}
		}
	});

	/** Table of Units */
	var bUnitTable = $("#units-table");

	/** Load Units */
	$(".unit-kwh", bUnitTable).find("sup").text(sensus.settings.units.kWh);
	$(".unit-mwh", bUnitTable).find("sup").text(sensus.settings.units.mWh);
	$(".unit-gwh", bUnitTable).find("sup").text(sensus.settings.units.gWh);
	$(".unit-twh", bUnitTable).find("sup").text(sensus.settings.units.tWh);
	$(".unit-pwh", bUnitTable).find("sup").text(sensus.settings.units.pWh);

	/** Bubbles */
	$('.bubble', "#user-form").CreateBubblePopup({
		align	       : 'center',
		width          : '200px',
		position       : 'top',
		innerHtml      : $('.bubble').attr('title'),
		themeName      : 'black',
		themePath      : 'images/jquerybubblepopup-theme',
		themeMargins   :
		{
			total       : '5px',
			difference  : '5px'
		},
		innerHtmlStyle :
		{
			color        : ($(this).attr('id')!='azure' ? '#000000' : '#333333'),
			'text-align' :'center'
		}
	});
	$('.bubble').removeAttr('title');

	/** Set up default select view */
	$('.register').validate();

	$(".button").button();
	/*Set up style for jquery ui menu*/
	$( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" ).removeClass("ui-widget-content");
	$( "#tabs ul" ).tabs().removeClass("ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all ui-widget ui-widget-content ui-tabs-vertical ui-tabs-nav");
    $( "#tabs li" ).removeClass( "ui-corner-top ui-state-default");

    $.sc.stopGlobalProgressBar();
});

</script>
</sec:authorize>