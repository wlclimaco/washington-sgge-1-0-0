<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

/**
 * @namespace     sensus.pages.user
 * @author        Vinicius Scalon Ferreira
 * @description   The main namespace for the User Page.
 * @fileoverview  Defines the core functionality of the User page.
 *
 * The main namespace for the User Page.
 *
 */
sensus.pages.createUser = {

	/*
	 *  Load Include Lights
	 */
	 bSaveSettings : true,

	loadIncludeLights : function()
	{
		$('.select-group, .select-tag, .operator, .summary, .overflow').show();
		$('.all-desc-container').hide();
		$('#select-groups-label').html('Groups:').addClass('short');
		$('#select-tags-label').html('Tags:').addClass('short');
	},

	/**
	 * Do a iterator on the groups to get the id of the lights
	 *
	 * @param aUserGroups - Array of the groups
	 *
	 * @returns void
	 *
	 */
	fnGetLightLatLongFromGroup : function(aUserGroups, iLightCount)
	{
		var nLat  = 0;
		var	nLng  = 0;
		var	iGroups  = 0;
		var	oMapItIcon  = $('.map-col .text_button');
		var oSelectedLights = $('.map-col strong');

		if ($.sc.isNullOrUndefined(iLightCount))
		{
			iLightCount = 0;
		}

		sensus.pages.user.aGroupsId = [];

		var oLightsStatus = { statusValue: 5, statusEnum: null };

		for (var iKeyG in aUserGroups)
		{
			// Check if is property of this object
			if (!aUserGroups.hasOwnProperty(iKeyG))
			{
				return;
			}

			// Set Group IDs
			sensus.pages.user.aGroupsId.push({'id' : aUserGroups[iKeyG].id });

			// Do the average and set Latitude and Longitude on namespace
			if (aUserGroups[iKeyG].latitude && aUserGroups[iKeyG].longitude)
			{
				var fCenterLatitude = aUserGroups[iKeyG].latitude;
				var fCenterLongitude = aUserGroups[iKeyG].longitude;

				if ((fCenterLatitude != 0) || (fCenterLongitude != 0))
				{
					iGroups++;

					nLat = sensus.pages.createUser.fnGetCurrentLatOrLng(nLat, fCenterLatitude, iGroups);
					nLng = sensus.pages.createUser.fnGetCurrentLatOrLng(nLng, fCenterLongitude, iGroups);
				}
			}

			if (aUserGroups[iKeyG].precedenceValue !== null && aUserGroups[iKeyG].precedenceValue < oLightsStatus.statusValue)
			{
				oLightsStatus.statusValue = aUserGroups[iKeyG].precedenceValue;
				oLightsStatus.statusEnum = aUserGroups[iKeyG].precedence;
			}

		}

		if (iLightCount < 1)
		{
			$('.map-col .text_button').addClass('ui-state-disabled');
			$('.map-col').parent('li').removeClass().addClass('summary submit-row');
		}
		else
		{
			$('.map-col .text_button').removeClass('ui-state-disabled');
			$('.map-col').parent('li').removeClass().addClass('summary submit-row ' + oLightsStatus.statusEnum.toLowerCase());
		}

		sensus.pages.user.nLat = nLat;
		sensus.pages.user.nLng = nLng;
		oSelectedLights.text(iLightCount);
 	},

 	/**
 	 * Get latitude and longitude
 	 */
	fnGetCurrentLatOrLng : function(nLatLng, fValue, iGroups)
	{
		nLatLng = (nLatLng + fValue)/iGroups;

		return nLatLng;
	},

	/**
 	 * Count Lights
 	 */
	fnCountSmartpoints : function(aGroupIds, isAllGroups)
	{
		var nLat = 0;
		var nLng = 0;
		var iGroups = 0;
		var i = 0;

		sensus.pages.user.nLat = 0;
		sensus.pages.user.nLng = 0;

		$('.chzn-select option:selected').each(function()
		{
			var aData   = $(this).attr('class').split('#');
			var	aLatLng = aData[1].split('|');
			var fLat    = parseFloat(aLatLng[0]);
			var fLng    = parseFloat(aLatLng[1]);

			if (((fLat != 0) && (fLng != 0)) && ((!isNaN(fLng)) && (!isNaN(fLng))))
			{
				iGroups++;
				sensus.pages.user.nLat = sensus.pages.user.nLat + fLat;
				sensus.pages.user.nLng = sensus.pages.user.nLng + fLng;
			}

			i++;
		});

		sensus.pages.user.nLat = sensus.pages.user.nLat/iGroups;
		sensus.pages.user.nLng = sensus.pages.user.nLng/iGroups;

		var	oRequest = new groupRequest(null, null, null, null, null, null, null, aGroupIds);

		function fnCount(data)
		{
			var nLightsSelected = 0;
			var oLightsStatus = { statusValue: 106, statusEnum: null };

			for (i = 0; i < data.groups.length; i++)
			{

				if (data.groups[i].precedenceValue != null && data.groups[i].precedenceValue < oLightsStatus.statusValue)
				{
					oLightsStatus.statusValue = data.groups[i].precedenceValue;
					oLightsStatus.statusEnum = data.groups[i].precedence;
				}

				nLightsSelected += data.groups[i].lightCount;
			}

			if (nLightsSelected < 1)
			{
				$('.map-col .text_button').addClass('ui-state-disabled');
				$('.map-col').parent('li').removeClass().addClass('summary submit-row');
			}
			else
			{
				$('.map-col .text_button').removeClass('ui-state-disabled');
				$('.map-col').parent('li').removeClass().addClass('summary submit-row ' + oLightsStatus.statusEnum.toLowerCase());
			}

			$('.map-col strong').text(nLightsSelected);
		}

		if (aGroupIds.length)
		{
			$.sc.getJson('api/group/fetchcount', oRequest, false, fnCount, null);
		}
		else
		{
			$('strong', '.map-col').text('0');
			$('.map-col .text_button').addClass('ui-state-disabled');
			$('.map-col').parent('li').removeClass().addClass('summary submit-row');
		}
	},

	/**
 	 * Do changes on the user
 	 */
	doChanges : function(oElement, oKey)
	{
		var aIds = [];

		// Get all ids from the elements selecteds
		$('.chzn-choices').find('.search-choice').each(function(iKey, oElem)
		{
			var sId = $(oElem).find('a').attr('id');

			if (oKey.deselected && oKey.deselected == sId)
			{
				return;
			}

			aIds.push({ id : sId});
		});

		sensus.pages.createUser.fnCountSmartpoints(aIds, false);
	},

	/**
 	 * Fill Groups
 	 */
	fnFillGroupsCallBack : function(data)
	{
		var aGroups   = data.groups;
		var sOptions  = '';
		var oSelectGroups = $('#select_groups');
		var nUserId = $.address.parameter('userId');
		var	oElementChosen  = $(".chzn-select");

		if (aGroups)
		{
			var nLat = 0;
			var nLng = 0;

			for (var g=0; g < aGroups.length; g++)
			{
				if(aGroups[g].latitude)
				{
					nLat = aGroups[g].latitude;
				}
				else
				{
					nLat = 0;
				}

				if(aGroups[g].longitude)
				{
					nLng = aGroups[g].longitude;
				}
				else
				{
					nLng = 0;
				}

				sOptions += '<option class="' + aGroups[g].smartPointCount + '#' + nLat + '|' + nLng + '" value="' + aGroups[g].id + '">'+aGroups[g].name+'</option>';
			}
		}

		oSelectGroups.append(sOptions);

		if (!nUserId)
		{
			// Get all ids from the elements selecteds
			oElementChosen.chosen();
			oElementChosen.change(function(oElement, oKey)
			{
				sensus.pages.createUser.doChanges(oElement, oKey);
			});
		}
	},

	/**
 	 * Fill User
 	 */
	fnFillUser : function(data)
	{
		var	oElementChosen  = $(".chzn-select");

		$('.content-header h1').html($.sc.locale('smartpointdetail.location.edit')+' "'+data.user.firstName+' '+data.user.lastName+'"');
		$('#user-first-create').val(data.user.firstName);
		$('#user-last-create').val(data.user.lastName);
		$('#user-email-create').val(data.user.email);
		$('#user-user').val(data.user.userName).attr('disabled', true);
		$('.description').html($.sc.locale("user.page.editrequired"));

		if ($('.radio').find('input:checked').val() != data.user.role)
		{
			$('#include-all-lights, #include-some-lights').removeAttr("disabled");
		}

		if (!data.user.allLightsAuth)
		{
			$('#include-all-lights').attr('checked', false);
			$('#include-some-lights').attr('checked', true);

			//enable group access control
			$('.select-group, .summary, .all-desc-container, .overflow').show();
		}

		var sRoles = data.user.role;

		if (sRoles)
		{
			$(".radio-list").find("input[value='" + sRoles + "']").attr('checked', true);
		}

		var aUserGroups = data.user.groups;

		sensus.pages.createUser.fnGetLightLatLongFromGroup(aUserGroups, data.user.totalLights);

		for (g in aUserGroups)
		{
			if (aUserGroups.hasOwnProperty(g))
			{
				$('#select_groups option[value="' + aUserGroups[g].id + '"]').prop('selected',true);
			}
		}

		// After selec a options, is call the plugin CHOSEN
		oElementChosen.chosen();
		oElementChosen.change(function(oElement, oKey)
		{
			sensus.pages.createUser.doChanges(oElement, oKey);
		});

		var fnFillSubscribe = function(oResponse)
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
		/** Performs the ajax call to search the properties */
		$.sc.getJson("api/settings/fetch", { userId: $.address.parameter('userId') }, false, fnFillSubscribe, null, null, null);
	},

	/**
 	 * Ajax Call
 	 */
	ajaxCall   : function(sUrl, sMessage)
	{
		var	i = 0;
		var sRoles = [];
		var aGroups = [];
		var sPass = $("#user-password-create").val();
		var bAllLightsAuth = '';

		sRoles = $('.radio').find('input:checked').val();

		bAllLightsAuth = $("input[name='select-lights']:checked").val();

		if (bAllLightsAuth == 'false')
		{
			$('.chzn-choices .search-choice a').each(function()
			{
				aGroups[i] = { id : parseInt($(this).attr('id')) };
				i++;
			});
		}

		if ($('#user-password-create').val() == '')
		{
			sPass = null;
		}

		var request = new userRequest(
			$.address.parameter('userId'),
			$("#user-user").val(),
			$("#user-first-create").val(),
			sRoles,
			$("#user-last-create").val(),
			sPass,
			$("#user-email-create").val(),
			bAllLightsAuth,
			aGroups, null, null
		);
		var getSystemSettingsValues = function()
		{
			var aSettingsList = [];

			/*ALARMS*/
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_BOARD_FAILURE",    propertyValue : $("#SUBSCRIBE_ALARM_BOARD_FAILURE").is(':checked') + ""     });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_LAMP_FAILURE",    	propertyValue : $("#SUBSCRIBE_ALARM_LAMP_FAILURE").is(':checked') + ""      });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_POWER_FAILURE",    propertyValue : $("#SUBSCRIBE_ALARM_POWER_FAILURE").is(':checked') + ""     });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE",    propertyValue : $("#SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE").is(':checked') + "" });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_METROLOGY_ERROR",    propertyValue : $("#SUBSCRIBE_ALARM_METROLOGY_ERROR").is(':checked') + ""       });

			/*WARNINGS*/
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_BROWNOUT_DETECTED", propertyValue : $("#SUBSCRIBE_WARN_BROWNOUT_DETECTED").is(':checked') + ""  });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_COMMN_FAIL",    	propertyValue : $("#SUBSCRIBE_WARN_COMMN_FAIL").is(':checked') + "" 		});
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_POWER_SURGE",    	propertyValue : $("#SUBSCRIBE_WARN_POWER_SURGE").is(':checked') + ""        });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_HIGH_CURRENT",    	propertyValue : $("#SUBSCRIBE_WARN_HIGH_CURRENT").is(':checked') + "" 		});
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_LOW_CURRENT",    	propertyValue : $("#SUBSCRIBE_WARN_LOW_CURRENT").is(':checked') + ""        });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_METROLOGY_RESET",   propertyValue : $("#SUBSCRIBE_WARN_METROLOGY_RESET").is(':checked') + "" 	});
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_REVERSE_ENERGY",    propertyValue : $("#SUBSCRIBE_WARN_REVERSE_ENERGY").is(':checked') + "" 	});
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_WARN_LIGHT_QUALITY",     propertyValue : $("#SUBSCRIBE_WARN_LIGHT_QUALITY").is(':checked') + ""      });

			return aSettingsList;
		};

		var fnCallBack = function(data)
		{
			var nUserId	= $.address.parameter('userId');
			var sMessage;

			if (nUserId)
			{
				sMessage = $.sc.locale("action.updateuser.success", $("#user-user").val());
			}
			else
			{
				sMessage = $.sc.locale("action.adduser.success", $("#user-user").val());
				nUserId = data.user.id;
			}
			// Save user settings
			if(sensus.pages.createUser.bSaveSettings)
			{
				oSettingsRequest.userId = nUserId;
				oSettingsRequest.action = "no_session";
				$.sc.getJson("api/settings/upsert", oSettingsRequest , false, null);
			}
			if (data && data.operationSuccess)
			{
				$.sc.getPage($.extend({}, sensus.commons.lib.ajax.param, {
					sUrl     : 'user',
					message  : {
						bMessage : true,
						sMessage : sMessage,
						status : 'success'
					}
				}));
			}
			else
			{
				$.sc.stopProgressBar(0,true);
				$.sc.showMessage("messaging-main", sensus.util.page.getMessageList(data.messageList), "error");
			}
		};
		oSettingsRequest = new settingsRequest(getSystemSettingsValues());
		$.sc.getJson(sUrl, request, false, fnCallBack);
	},

	/**
 	 * Validate Groups
 	 */
	fnGroupsValidator : function ()
	{
		if ($("#include-some-lights").attr("checked"))
		{
			if ($(".chzn-choices li.search-choice").length)
			{
				return true;
			}
			else
			{
				var element = ".chzn-choices";
				$(element).validationEngine('showPrompt', $.sc.locale("validation.fieldrequired"), 'red', '', true);

				return false;
			}
		}
		return true;
	}
 };

</script>
</sec:authorize>