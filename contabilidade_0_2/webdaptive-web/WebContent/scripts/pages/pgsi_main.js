qat.pages.wd =
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
		$.extend(qat.settings, oSettingsResponse.uiProperties);
		this.sAppStorageKey += "washington 0001";//oSettingsResponse.uiProperties["wdVersion"] + "_" + oSettingsResponse.uiProperties["wdBuildVersion"];

		var oAppLocalData = $.qat.storage.get(this.sAppStorageKey);
		if(!oAppLocalData)
		{
			this.fnClearAllLocalData();
			this.fnWrapperInit(true, true);
			return;
		}

		this.fnLoadAppProperties(oAppLocalData);

		var oUserLocalData = $.qat.storage.get(this.sUserStorageKey);
		if($.qat.isNullOrUndefined(oUserLocalData) || oUserLocalData.sessionId !== oSettingsResponse.uiProperties["sessionId"])
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
		$.qat.storage.flush();
	},

	/**
	 *	Clear session data based on common keys: User data and local session data.
	 */
	fnClearSessionData: function()
	{
		$.qat.storage.delete(this.sUserStorageKey);
		$.qat.storage.delete(this.sAppSessionDataKey);
	},

	/**
	 *	Get the application main body and property data if needed.
	 *	@param bUser	- Boolean, whether to fetch user properties
	 *	@param bApp		- Boolean, whether to fetch app properties
	 */
	fnWrapperInit: function(bUser, bApp)
	{
		//qat.settings.storage.app		= false;
	//	qat.settings.storage.user	= 'rod';
var a = true;
		var sWrapperUrl = "./wrapper?app=" + a + "&user="+a;

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
		if($.qat.isNullOrUndefined(oPropertiesResponse))
		{
			return;
		}

		if(bSave)
		{
			$.qat.storage.set(this.sAppStorageKey, oPropertiesResponse);
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
		if ($.qat.isNullOrUndefined(oData))
		{
			return;
		}

		$.extend(qat.settings, oData);
	},

	fnDialogMessageError : function(sUrl,oRequest,oResponse,fnCallBackError,sMessage,bIsFetch)
	{

		//(sUrl,oRequest,sUrlAction,fnCallBack,sTitleButton) {qat.pages.wd.fnDialogMessageError(sUrl,oResponse,fnCallBackError);
		if(oResponse.messageInfoList[0] != null){
			if((oResponse.messageInfoList[0].code == "wd.base.organizationbaiimpl.ol.error")||(oResponse.messageInfoList[0].code == "wd.base.locationbaiimpl.ol.error")||(oResponse.messageInfoList[0].code == "wd.base.optimistic.locking.error")){
				var oUrl = $.address.value().split('/');

				qat.util.actiondialog.launchActionDialog("errorDialog", qat.pages.business.dialogSettings.errorDialog(sUrl,oRequest,"util/refreshDialog",fnCallBackError,"commons.pages.refresh",$.qat.locale.get("commons.dialog.error.update.title",oUrl[1])));
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
					qat.util.actiondialog.launchActionDialog("errorDialog", qat.pages.business.dialogSettings.errorDialog("",{},"util/errorDialog","null","commons.pages.confirm",$.qat.locale.get("commons.title.error.validation"),sError));
				}else{
					qat.util.actiondialog.launchActionDialog("errorDialog", qat.pages.business.dialogSettings.errorDialog("",{},"util/errorDialog","null","commons.pages.confirm",sMessage));
				}
			}
		}else{
			qat.util.actiondialog.launchActionDialog("errorDialog", qat.pages.business.dialogSettings.errorDialog("",{},"util/errorDialog","null","commons.pages.confirm",sMessage));
		}
		$.qat.progressBar.stop();
	},

	/**
	 * Load internationalization properties
	 */
	fnLoadLocaleMessages: function()
	{
		var oAppLocalData = $.qat.storage.get(this.sAppStorageKey);
		if(!$.qat.isNullOrUndefined(oAppLocalData))
		{
			$.qat.locale.init(oAppLocalData.localeMessages[qat.settings.user.language]);
		}
	}
}