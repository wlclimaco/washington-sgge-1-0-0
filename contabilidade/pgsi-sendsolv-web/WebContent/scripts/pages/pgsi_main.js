pgsi.pages.sendsolv =
{

	sAppStorageKey		: "AppStaticData_",
	sAppLangStorageKey	: "AppLanguageStaticData_",
	sAppSessionDataKey	: "AppSessionData",
	sUserStorageKey		: "UserSessionData",

	/**
	 * Check for local storage data and fetch if necessary.
	 * @param oSettingsResponse	- Project name, version and build
	 */
	init: function(oSettingsResponse)
	{
		// Save initial properties into settings
		$.extend(pgsi.settings, oSettingsResponse.uiProperties);
		this.sAppStorageKey += oSettingsResponse.uiProperties["sendsolvVersion"] + "_" + oSettingsResponse.uiProperties["sendsolvBuildVersion"];

		var oAppLocalData = $.pgsi.storage.get(this.sAppStorageKey);
		if(!oAppLocalData)
		{
			this.fnClearAllLocalData();
			this.fnWrapperInit(true, true);
			return;
		}

		this.fnLoadAppProperties(oAppLocalData);

		var oUserLocalData = $.pgsi.storage.get(this.sUserStorageKey);
		if($.pgsi.isNullOrUndefined(oUserLocalData) || oUserLocalData.sessionId !== oSettingsResponse.uiProperties["sessionId"])
		{
			this.fnClearSessionData();
			this.fnWrapperInit(true);
			return;
		}

		this.fnLoadUserProperties(oUserLocalData);
		this.fnWrapperInit();
	},

	/**
	 *	Clear all local storage data.
	 */
	fnClearAllLocalData: function()
	{
		$.pgsi.storage.flush();
	},

	/**
	 *	Clear session data based on common keys: User data and local session data.
	 */
	fnClearSessionData: function()
	{
		$.pgsi.storage.delete(this.sUserStorageKey);
		$.pgsi.storage.delete(this.sAppSessionDataKey);
	},

	/**
	 *	Get the application main body and property data if needed.
	 *	@param bUser	- Boolean, whether to fetch user properties
	 *	@param bApp		- Boolean, whether to fetch app properties
	 */
	fnWrapperInit: function(bUser, bApp)
	{
		pgsi.settings.storage.app		= bApp || false;
		pgsi.settings.storage.user	= bUser || false;

		var sWrapperUrl = "./wrapper?app=" + pgsi.settings.storage.app + "&user=" + pgsi.settings.storage.user;

		$.ajax({
		 	url: sWrapperUrl
		}).done(function(html) {
		 	$('body').append(html);
		});

	},

	/**
	 * Iterate over app settings object loading each one of them.
	 * @param oPropertiesResponse	- Properties object
	 * @param bSave					- Whether to save the data into local storage
	 */
	fnLoadAppProperties: function(oPropertiesResponse, bSave)
	{
		if($.pgsi.isNullOrUndefined(oPropertiesResponse))
		{
			return;
		}

		if(bSave)
		{
			$.pgsi.storage.set(this.sAppStorageKey, oPropertiesResponse);
		}

		for(property in oPropertiesResponse)
		{
			if($.isFunction(this["fnLoad" + property]))
			{
				this["fnLoad" + property](oPropertiesResponse[property]);
			}
		}
	},

	fnLoadsettings: function(oData)
	{
		if ($.pgsi.isNullOrUndefined(oData))
		{
			return;
		}

		$.extend(pgsi.settings, oData);
	},

	fnDialogMessageError : function(sUrl,oRequest,oResponse,fnCallBackError,sMessage,bIsFetch)
	{

		//(sUrl,oRequest,sUrlAction,fnCallBack,sTitleButton) {pgsi.pages.sendsolv.fnDialogMessageError(sUrl,oResponse,fnCallBackError);
		if(oResponse.messageInfoList[0] != null){
			if((oResponse.messageInfoList[0].code == "sendsolv.base.organizationbaiimpl.ol.error")||(oResponse.messageInfoList[0].code == "sendsolv.base.locationbaiimpl.ol.error")||(oResponse.messageInfoList[0].code == "sendsolv.base.optimistic.locking.error")){
				var oUrl = $.address.value().split('/');

				pgsi.util.actiondialog.launchActionDialog("errorDialog", pgsi.pages.business.dialogSettings.errorDialog(sUrl,oRequest,"util/refreshDialog",fnCallBackError,"commons.pages.refresh",$.pgsi.locale.get("commons.dialog.error.update.title",oUrl[1])));
			}

			else{
				var sError="",
				    sTypeError = false;
				for (i=0;i < oResponse.messageList.length;i++){
					if(oResponse.messageList[i].messageInfo.severity == "Error" || oResponse.messageList[i].messageInfo.severity == "Fatal"){
						sTypeError = true;
						sError = sError +  "<p>" + oResponse.messageList[i].text + "</p><br>";
					}
				}
				if(sTypeError == true){
					pgsi.util.actiondialog.launchActionDialog("errorDialog", pgsi.pages.business.dialogSettings.errorDialog("",{},"util/errorDialog","null","commons.pages.confirm",$.pgsi.locale.get("commons.title.error.validation"),sError));
				}else{
					pgsi.util.actiondialog.launchActionDialog("errorDialog", pgsi.pages.business.dialogSettings.errorDialog("",{},"util/errorDialog","null","commons.pages.confirm",sMessage));
				}
			}
		}else{
			pgsi.util.actiondialog.launchActionDialog("errorDialog", pgsi.pages.business.dialogSettings.errorDialog("",{},"util/errorDialog","null","commons.pages.confirm",sMessage));
		}
		$.pgsi.progressBar.stop();
	},

	/**
	 * Load time zone properties
	 * @param oData	- Olson table properties
	 */
	fnLoadtimeZones: function(oData)
	{
		// Set UP Time Zone Configurations
		var _tz = timezoneJS.timezone;
		_tz.loadingScheme = _tz.loadingSchemes.MANUAL_LOAD;
		_tz.loadZoneDataFromObject(oData);
	},

	/**
	 * Iterate over app settings object loading each one of them.
	 * @param oPropertiesResponse	- Properties object
	 */
	fnReloadUserSettings: function(oPropertiesResponse)
	{
		var oCurrentUserSettings = $.pgsi.storage.get(this.sUserStorageKey);

		oCurrentUserSettings.userSettings = $.extend(oCurrentUserSettings.userSettings, oPropertiesResponse.userSettings);
		$.pgsi.storage.set(this.sUserStorageKey, oCurrentUserSettings);
		pgsi.settings.user = oCurrentUserSettings.userSettings;
	},

	/**
	 * Load user properties.
	 * @param oPropertiesResponse	- Properties object
	 * @param bSave					- Whether to save the data into local storage
	 */
	fnLoadUserProperties: function(oPropertiesResponse, bSave)
	{
		if($.pgsi.isNullOrUndefined(oPropertiesResponse) || !Object.keys(oPropertiesResponse).length)
		{
			return;
		}

		if(bSave)
		{
			oPropertiesResponse.sessionId = pgsi.settings.sessionId;
			$.pgsi.storage.set(this.sUserStorageKey, oPropertiesResponse);
		}

		pgsi.settings.userContext = oPropertiesResponse.userContext;
		pgsi.settings.user = oPropertiesResponse.userSettings;
		this.fnLoadLocaleMessages();
	},

	/**
	 * Load internationalization properties
	 */
	fnLoadLocaleMessages: function()
	{
		var oAppLocalData = $.pgsi.storage.get(this.sAppStorageKey);
		if(!$.pgsi.isNullOrUndefined(oAppLocalData))
		{
			$.pgsi.locale.init(oAppLocalData.localeMessages[pgsi.settings.user.language]);
		}
	}
}