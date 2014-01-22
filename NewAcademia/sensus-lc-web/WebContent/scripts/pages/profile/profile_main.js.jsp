<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.profile
 * @description The main namespace for the Profile Page.
 */

/**
 * @fileoverview Defines the core functionality of the Profile Page.
 * @author Rodolfo Alves
 */

/**
 * The main namespace for profile-related functionality.
 */
sensus.pages.profile = {

		fnCallSubscribe : function(oInput , bCallDialog)
		{
			if(bCallDialog)
			{
				oInput.prop("checked", !oInput.prop("checked"));
				$.sc.launchActionDialog(oInput.attr("id"), subscribe.dialogSettings.subscribeHelp);
			}
			else
			{
				if(oInput.is(':checked'))
				{
					oInput.next("label").text($.sc.locale("profile.page.subscribed"));
				}
				else
				{
					oInput.next("label").text($.sc.locale("profile.page.subscribe"));
				}

				if($.address.path() == "/profile")
		 		{
					var aSettingsList = [];
					var sMessage = $.sc.locale("profile.page.updateSubscribe");

					aSettingsList.push({ propertyEnum : oInput.attr("id").replace("#",""),    propertyValue : oInput.is(':checked') + ""  });
			 		oSettingsRequest = new settingsRequest(aSettingsList);
			 		oSettingsRequest.action = "no_session";
			 		$.sc.getJson("api/settings/upsert", oSettingsRequest , false, $.sc.reloadProperties, sMessage);
		 		}
				else
				{
					$.sc.stopProgressBar();
				}

			}
		},

		/**
		 * @returns array - List of System Settings
		 */
		getSystemSettingsValues : function(bMonitorRequest)
		{
			var aSettingsList = [];
			var sMonitor = $("#prompt-monitor").find("input:checked").val();

			if ($.sc.isNullOrUndefined(sMonitor))
			{
				sMonitor = sensus.settings.monitor;
			}

			aSettingsList.push({ propertyEnum : "LANGUAGE",               propertyValue : $("#language").val()                              });
			aSettingsList.push({ propertyEnum : "DATE_FORMAT",            propertyValue : $("#dateformat").val()                            });
			aSettingsList.push({ propertyEnum : "PAGE_SIZE",              propertyValue : $("#pagesize").val()                              });
			aSettingsList.push({ propertyEnum : "TIME_ZONE",              propertyValue : $("#timezone").val()                              });
			aSettingsList.push({ propertyEnum : "CONVERT_ENERGY_UNIT",    propertyValue : $("#unit-convert").is(':checked') + ""            });
			/*ALARMS*/
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_BOARD_FAILURE",    propertyValue : $("#SUBSCRIBE_ALARM_BOARD_FAILURE").is(':checked') + ""     });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_LAMP_FAILURE",    	propertyValue : $("#SUBSCRIBE_ALARM_LAMP_FAILURE").is(':checked') + ""      });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_POWER_FAILURE",    propertyValue : $("#SUBSCRIBE_ALARM_POWER_FAILURE").is(':checked') + ""     });
			aSettingsList.push({ propertyEnum : "SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE",  propertyValue : $("#SUBSCRIBE_ALARM_METROLOGY_COM_FAILURE").is(':checked') + "" });
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

			if($.sc.isNullOrUndefined(bMonitorRequest))
			{
				aSettingsList.push({ propertyEnum : "MONITOR_REQUEST",        propertyValue : sMonitor                                      });
			}

			aSettingsList.push({ propertyEnum : "PAGE_SIZE_SHOW_DIALOG",  propertyValue : sensus.settings.PAGE_SIZE_SHOW_DIALOG             });
			aSettingsList.push({ propertyEnum : "SHOW_DIALOG_POLYGON", 	  propertyValue : sensus.settings.SHOW_DIALOG_POLYGON      		    });

			return aSettingsList;
		}
};
</script>