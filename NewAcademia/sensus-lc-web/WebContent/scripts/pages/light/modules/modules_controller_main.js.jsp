<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<sec:authorize
	access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
	<script type="text/javascript">

sensus.commons.modules = {

		oPropertiesContent  : null,
		bForceReload        : false,
		ajaxCache           : [],

		/**
		 * Load Modules Functionality
		 */
		loadModules : function () {
			// Parameters for type
			var oParameters = $.grep(sensus.pages.smartpoint.detail.oDeviceTypeParameters, function(n) {
			    var sDeviceType = $.address.parameter("deviceType");
				return n.deviceType.type == sDeviceType;
			});

			$.moduleController.init(oParameters[0], "#about-device");

			$("#messaging-smartpoint-detail").delegate(".remove", "click", function(e) {
				e.preventDefault();
				$(this).parent().fadeOut("slow");
			});
		},

		reloadData : function(){

			sensus.commons.modules.ajaxCache.splice(0, sensus.commons.modules.ajaxCache.length);
			sensus.commons.modules.bForceReload = true;

			var aSummary = sensus.pages.smartpoint.detail.oDeviceTypeParameters.summaryData;
			var aContent = sensus.pages.smartpoint.detail.oDeviceTypeParameters.content;

			for (var i = 0; i < aSummary.length; i++)
			{
				if(i == 1)
				{
					sensus.commons.modules.bForceReload = false;
				}

				sensus.commons.modules.summaryData[aSummary[i]].init();
			}

			for (var k = 0; k < aContent.length; k++)
			{
				try
				{
					sensus.commons.modules.content[aContent[k]].init();
				}
				catch(e)
				{

				}
			}

		},

		util : {

			ajaxData : {
				//TODO: find a way to do this URL dynamic
				fetchDeviceById : function () {
					var	obj = {
						url : "api/light/fetchById/" + $.address.parameter('id')
					};

					return obj;
				}

			},

			/**
			 * Ajax call to get response object.
			 * This method can stores the calls in order to avoid duplicated ajax calls.
			 *
			 * @param {Object} obj
			 * 			The Object containing url and data fields.
			 * @param {Boolean} bNotCache
			 * 			Whether will store ajax call or not.
			 * @returns The ajax Object
			 */
			getData : function ( obj, bNotCache, bForceReload ) {

				obj = obj();

				var ajaxCache = sensus.commons.modules.ajaxCache;
				var aCache = $.grep(ajaxCache, function(o) {return o.url == obj.url;});

				if (aCache.length && !bForceReload)
				{
					return aCache[0].oResponse;
				}

			    return $.ajax({
					url          : obj.url,
					dataType     : 'json',
					contentType  : "application/json; charset=utf-8",
					type         : "POST",
					async        : false,
					data         : $.toJSON(obj.data),
			        success		 : function(resp) {
			        	if (!bNotCache)
			        	{
			        		obj.oResponse = resp;
			        		ajaxCache.push(obj);
			        	}
			        }
			    });
			},

			fnFillData : function(oDom, aData) {

				var sText = '';
				if(aData[0] == null)
				{
					sText = '--';
				}
				else
				{
					for (var i = 0; i < aData.length; i++)
					{
						sText += ' '+aData[i];
					}
				}

				oDom.html(sText);
			},

			fnFillLastDate : function(aData, sParameter) {

				var aParam = sensus.commons.modules.util.fnGetParameterValue(aData, sParameter, 'modifyDate');

				if (!$.sc.isNullOrUndefined(aParam))
				{
					return aParam;
				}
				else
				{
					return sensus.commons.modules.util.fnGetParameterValue(aData, sParameter, 'createDate');
				}
			},

			fnGetParameterValue : function(aData, sParameter, sValue)
			{
				if (aData[sParameter])
				{
					return aData[sParameter];
				}
				return '--';
			},

			/**
			 * Get the first Device from the Response Object
			 * @param  oResponse - [Object] The Response Object
			 * @returns [Object] The First Device came from Response
			 * 					  or null if it does not exists
			 */
			fnGetFirstDeviceResponse : function(oResponse) {

				if (oResponse && oResponse.devices && oResponse.devices.length)
				{
					return oResponse.devices[0];
				}
				else if(oResponse || oResponse.light && oResponse.light.length)
				{
					return oResponse.light;
				}

				return null;
			},

			fnGetHistoryFromLight : function() {

				fnGetResponse(sensus.settings.searchSmartpointHistory, {'inquiryLightRequest' : new inquiryLightRequest()}, false);

			},

			fnValidateField : function(sField)
			{
				if(sField == null || sField == undefined )
				{
					return "";
				}
				else
				{
					return sField;
				}
			}

		},

		reloadPage : function () {

		},

		executeTab : function (aMockTabs) {
			// Call Tabs functionality
			if (aMockTabs.length)
			{
				sensus.commons.modules.tabs.init();
			}
		},

		executeSummaryData : function (aMockSummaryData) {
			if (aMockSummaryData.length)
			{
				for (var i = 0; i < aMockSummaryData.length; i++)
				{
					sensus.commons.modules.summaryData[aMockSummaryData[i]].init();
				}
			}
		},

		executeContent : function (aMockContent) {
			// Call modules matching with parameters
			if (aMockContent.length)
			{
				for (var i = 0; i < aMockContent.length; i++)
				{
					if(sensus.commons.modules.content[aMockContent])
					{
						sensus.commons.modules.content[aMockContent[i]].init();
					}
				}
			}
		},

		tabs : {

			activeMenu : function(oElement) {
				$(oElement).addClass('active');
			},

			cleanTab : function () {
				$('.tabs li').find('a.active').removeClass('active');
			},

			fnGetUrlParameters : function (oElement)
			{
				var sProperties = "";

				if (sensus.commons.modules.oPropertiesContent && !oElement.parent().prev().length )
				{
					sProperties = "&" + $.param(sensus.commons.modules.oPropertiesContent);
				}

				var sUrl =  $(oElement).attr("href") + "?id=" + $.address.parameter("id") + "&deviceType=" + $.address.parameter("deviceType") + sProperties;
				return sUrl;
			},

			fillTab : function(oElement)
		    {
				$.sc.loadTab($.extend({}, sensus.commons.lib.ajax.param,
						{
							sUrl : sensus.commons.modules.tabs.fnGetUrlParameters($(oElement)),
							$container : $('#load'),
							$container_tabs : $('#tabs-content'),
							$element : $(oElement),
							bTab : true,
							bDetailPage: true
						}
				));
			},

			fnExecuteContent : function(element) {

				if ($(element).data("parameter"))
				{
					sensus.commons.modules.executeContent($(element).data("parameter"));
				}
			},

			fnClickTabs : function(event)
			{
				event.preventDefault();

				sensus.commons.modules.ajaxCache.splice(0, sensus.commons.modules.ajaxCache.length);

				$.sc.startProgressBar();

				sensus.commons.modules.tabs.cleanTab();
				sensus.commons.modules.tabs.activeMenu(this);

				if ($(this).parent().prev().length)
				{
					sensus.commons.modules.tabs.fillTab(this);
				}
				else
				{
					$.address.value(sensus.commons.modules.tabs.fnGetUrlParameters($(this)));
					$.sc.loadFirstTab(sensus.commons.modules.tabs.fnGetUrlParameters($(this)), true);
				}

				sensus.commons.modules.tabs.fnExecuteContent($(this));

				$.sc.stopProgressBar(0);
			},

			init : function()
			{
				var oTabs = $("#tabs");
				var oInitTab =  $("li:first", oTabs).find("a");
				var	sPath ;
				var sUrl;
				var sProperties = "";


				sPath = $.address.path();
				sPath = sPath.split("/").pop();

				if (sPath == 'history')
				{
					oInitTab =  $("#history", oTabs).find("a");
				}
				else if (sPath == 'ecoMode')
				{
					oInitTab =  $("#ecoMode", oTabs).find("a");
				}

				if (sensus.commons.modules.oPropertiesContent && !oInitTab.parent().prev().length )
				{
					sProperties = "&" + $.param(sensus.commons.modules.oPropertiesContent);
				}

				sPath = $(oInitTab).attr("href").split("/")[1];

				 /** Load Tab */
				sUrl = sPath + "?id=" + $.address.parameter("id") + "&deviceType=" + $.address.parameter("deviceType") + sProperties;

				$.sc.loadFirstTab(sUrl);

				sUrl = ($.address.value()).split('?')[0];

				sensus.commons.modules.tabs.cleanTab();

				/** check if the url is a tab */
				if (sUrl.indexOf('tabs') == -1)
				{
					oInitTab.addClass("active");
				}

				$('li a', oTabs).click(sensus.commons.modules.tabs.fnClickTabs);

			 }
		},

		summaryData : {

			messageReceived : {

				fillMessageReceived : function (oResponse) {

					if (oResponse && oResponse.loadProfile && oResponse.loadProfile.createDate)
					{
						$("#receivedDate").text($.sc.dateFormat(oResponse.loadProfile.createDate, "MM d, yy at hh:i A"));
					}
					else
					{
						$(".stamp-smartpoint").hide();
					}
				},

				init : function () {
					var obj = function() {
						return {
								url : sensus.settings.fetchUpdatedMeterLoadProfile,
								data : 'id='+$.address.parameter("id")
							};
					};

					$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
						sensus.commons.modules.summaryData.messageReceived.fillMessageReceived(oResponse);
					});
				}
			},

			/**
			 * Light Information.
			 * Includes Pole Id, Address and FlexNetID.
			 */

			lightInformation : {

				/**
				 * Ajax call to get the Response with device information
				 *
				 * @returns [Object] Response Object
				 */
				fnGetResponse : function() {
					var oResponse = null;
					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById,null,sensus.commons.modules.bForceReload)).done(function(oNewResponse) {

						oResponse = oNewResponse;

					});

					return oResponse;

				},

				fnSetLightState : function(aData){
					var sMsg;
					if(aData.deviceLifeCycleState == "BLINK")
					{
						if(aData.blinkStatusValue == 1)
						{
							sMsg =  $.sc.locale("smartpointdetail.control.blinkfast");
						}
						else if(aData.blinkStatusValue == 2)
						{
							sMsg =  $.sc.locale("smartpointdetail.control.blinkslow");
						}
						else
						{
							sMsg = $.sc.locale("smartpoint.actions.controlLights.blink");
						}
					}
					else if(aData.deviceLifeCycleState == "ON" || aData.deviceLifeCycleState == "DIM")
					{
						if(aData.intensityValue != 0 && aData.intensityValue != 6)
						{
							sMsg = 'DIM '+aData.intensityPercentage+'%';
						}
						else
						{
							sMsg = $.sc.locale("smartpointdetail.control.lighton");
						}
					}
					else
					{
						sMsg = $.sc.locale('smartpointdetail.control.lightoff');
					}

					return sMsg;
				},

				fnSetOverride : function(aData){
					if(aData.overrideTypeValue && parseInt(aData.overrideTypeValue) != 0)
					{
						var sMsgDate = '';

						if(parseInt(aData.overrideTypeValue) == 3)
						{
							sMsgDate = aData.overridePerDate;
						}

						var oCommunication = $('#comunication-messaging .message h3');
						oCommunication.html($.sc.locale('smartpointdetail.page.lightoverride'));
						var sMsg = sensus.commons.modules.summaryData.lightInformation.fnSetLightState(aData);

						if(oCommunication.next().length)
						{
							oCommunication.next().remove();
						}

						$($.sc.locale('smartpointdetail.page.lightoverridetext.'+ aData.overrideTypeValue, sMsg,
							$.sc.dateFormat(sMsgDate, sensus.settings.dateFormatMask + " at h:i a", null, false, aData.radio.location.timeZoneInfo.offsetInHours))
						 ).insertAfter(oCommunication);
						$('#comunication-messaging').show();

					}

				},

				fnUpdateLightInformation : function(){

					sensus.commons.modules.bForceReload = true;
					sensus.commons.modules.summaryData.lightState.init();
					var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();

					if (oResponse.light.overrideEnum == 'NONE' && oResponse.operationSuccess == true)
					{
						$('#comunication-messaging').hide();
					}

					var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
					sensus.commons.modules.summaryData.lightInformation.fnSetOverride(oFirstMeter);
					sensus.commons.modules.bForceReload = false;
				},

				init: function(){
					try
					{
						var sMessagingMain  = $('#messaging-main');

						$('#messaging-main').remove();
						$('#yui-main').prepend(sMessagingMain);
						$('.stamp-smartpoint').remove();
						var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						sensus.commons.modules.util.fnFillData($('#pole-id'), [oFirstMeter.poleId]);
						sensus.commons.modules.util.fnFillData($('.description-id'), [oFirstMeter.radio.flexNetIdAsString]);

						var aAddress = [

							oFirstMeter.radio.location.address,
							oFirstMeter.radio.location.city,
							oFirstMeter.radio.location.state,
							oFirstMeter.radio.location.zip

						],
							bValidAddress = true;

						for (u in aAddress)
						{
							if (aAddress[u] == null)
							{
								bValidAddress = false;
							}
						}

						if (!bValidAddress)
						{
							aAddress = [$.sc.locale('smartpointdetail.page.noaddress')];
						}

						sensus.commons.modules.util.fnFillData($('.description-address'), aAddress);

						sensus.commons.modules.util.fnFillData($('#light-dim'), [oFirstMeter.configuration.dimmable]);

						//light-levels
						var aLightLevels = [];

						if(oFirstMeter.configuration.partNumberConfigurations && oFirstMeter.configuration.partNumberConfigurations.length){

							for (var i = 0; i < oFirstMeter.configuration.partNumberConfigurations.length; i++)
							{
								aLightLevels.push(oFirstMeter.configuration.partNumberConfigurations[i].percentage);
							}
						}
						else
						{
							aLightLevels = [25,50,75,90];
						}
						sensus.commons.modules.util.fnFillData($('#light-levels'), [aLightLevels]);

						sensus.commons.modules.summaryData.lightInformation.fnSetOverride(oFirstMeter);
					}
					catch(e)
					{
						console.log(e);
					}
				}
			},

			/**
			 * Light State.
			 */

			lightState : {

				/**
				 * Ajax call to get the Response with device information
				 *
				 * @returns [Object] Response Object
				 */
				fnGetResponse : function() {

					var oResponse = null;

					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById,null,sensus.commons.modules.bForceReload)).done(function(oNewResponse) {

						oResponse = oNewResponse;

					});

					return oResponse;

				},

				init: function(){
					try
					{
						var oResponse = sensus.commons.modules.summaryData.lightState.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						var sMsg = sensus.commons.modules.summaryData.lightInformation.fnSetLightState(oFirstMeter);

						sensus.commons.modules.util.fnFillData($('#light-state'), [sMsg]);
						sensus.commons.modules.util.fnFillData($('#light-lifecycle-state'), [$.sc.locale("smartpoint.status." + oFirstMeter.lifeCycleState)]);
					}
					catch(e)
					{

					}
				}

			},

			/**
			 * Light Status.
			 */

			lightStatus : {

				/**
				 * Ajax call to get the Response with device information
				 *
				 * @returns [Object] Response Object
				 */
				fnGetResponse : function() {
					var oResponse = null;
					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById,null,sensus.commons.modules.bForceReload)).done(function(oNewResponse) {

						oResponse = oNewResponse;

					});

					return oResponse;
				},

				init: function(){
					try
					{
						var oResponse = sensus.commons.modules.summaryData.lightStatus.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
					}
					catch(e)
					{

					}
				}
			},

			/**
			 * Light Status Message.
			 */

			statusMessage : {

				/**
				 * Ajax call to get the Response with device information
				 *
				 * @returns [Object] Response Object
				 */
				fnGetResponse : function() {
					var oResponse = null;
					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById)).done(function(oNewResponse) {

						oResponse = oNewResponse;

					});

					return oResponse;
				},

				init: function(){
					try
					{
						var oResponse = sensus.commons.modules.summaryData.statusMessage.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						// Format message Date
						var sDate = oFirstMeter.lastNotificationHistory.lastMessageDate;
						var iLightTimeZone = oFirstMeter.radio.location.timeZoneInfo.offsetInHours;

						var oDate 	= $.sc.dateFormat( sDate, 'dateObj',null,false,iLightTimeZone);
						var sFormatDate = Globalize.format(new Date(oDate.getFullYear(), oDate.getMonth(), oDate.getDate()),'D');
						var sHour       = $.sc.dateFormat( oDate, 'h:i:s:fff A',null,false);

						sensus.commons.modules.util.fnFillData($('#date-message'), [sFormatDate]);
						sensus.commons.modules.util.fnFillData($('#hour-message'), [sHour]);
					}
					catch(e)
					{

					}
				}
			},

			ecoMode : {

				/**
				 * Ajax call to get the Response with device information
				 *
				 * @returns [Object] Response Object
				 */
				fnGetResponse : function() {
					var oResponse = null;
					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById)).done(function(oNewResponse) {

						oResponse = oNewResponse;

					});

					return oResponse;

				},

				init: function(){
					try
					{
						var oResponse = sensus.commons.modules.summaryData.ecoMode.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						var nPercent = '--';

						if(oFirstMeter.ecoMode)
						{
							var nValue = Math.round(oFirstMeter.ecoMode);
							nPercent = nValue + '%';

							var sEcoModeLabel = $.sc.locale('smartpoint.detail.ecoMode.Economy');

							if(nValue >= 15 && nValue <= 50)
							{
								sEcoModeLabel = $.sc.locale('smartpoint.detail.ecoMode.Value');
							}
							else if(nValue < 15)
							{
								sEcoModeLabel = $.sc.locale('smartpoint.detail.ecoMode.Security');
							}
							sensus.commons.modules.util.fnFillData($('#ecomode-percent').next(), [sEcoModeLabel]);
						}

						sensus.commons.modules.util.fnFillData($('#ecomode-percent'), [nPercent]);
					}
					catch(e)
					{

					}
				}
			},

			/**
			 * Light Actions.
			 */

			lightActions : {

				/**
				 * Update Light Status
				 */
				getDataFromLight : function() {

					var aIds = [$.address.parameter('id')];
					var oLightCriteria = new LightCriteria(aIds, null, null, null, 'NONE');
					var oActionCriteria = new ActionCriteria(null, null, 'NONE', null, null, null, 'ALL');
					var oRequest = new lightRequest(oLightCriteria, oActionCriteria);

					$.sc.monitor("api/lighttop/fetchstatus", oRequest , false, sensus.commons.modules.reloadData, $.sc.locale("action.longRunningProcessDialog.confirm"), false);
				},

				/**
				 * Update Light Readings
				 */
				 getReadings : function()
				 {
					var aIds = [$.address.parameter('id')];
					var oLightCriteria = new LightCriteria(aIds, null, null, null, 'NONE');
					var oActionCriteria = new ActionCriteria(null, null, 'NONE', null, null, null, 'READING');
					var oRequest = new lightRequest(oLightCriteria, oActionCriteria);

					$.sc.monitor("api/lighttop/fetchstatus", oRequest , false, sensus.commons.modules.reloadData, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

				},

				editLightStatusDetail : function() {

					$.sc.launchActionDialog("editLightStatusDetail", sensus.commons.modules.dialogSettings.editLightStatusDetail);

				},


				resetValues : function(){

					// Submit Ajax Action
					var oLightCriteria = new LightCriteria([parseInt($.address.parameter('id'))]);
					var oRequest = new lightRequest(oLightCriteria);
					$.sc.getJson("api/lighttop/updatereset", oRequest, false, sensus.commons.modules.content.lightReadings.fnUpdateReadings, $.sc.locale("smartpointdetail.action.resetvalues.success"));

				},

				init: function() {

					//Remove the 'Edit Light Status' action for the DEACTIVATED Lights
					var oResponse = sensus.commons.modules.summaryData.statusMessage.fnGetResponse();

					if (oResponse.light.lifeCycleState == "DEACTIVATED")
					{
						$('#editLightStatusDetail').parent().remove();
						$("#lightsOnOff").addClass('ui-state-disabled').click(function(e){
							e.preventDefault();
						});
					}
					else
					{
						$("#lightsOnOff").wLightControl(false);
					}

					$('#comunication-messaging').on('click', '#clear-override', function(e){
						e.preventDefault();
						sensus.pages.smartpoint.clearManualOverride();
					});

					var oMenuPlug = {

							'clearManualOverride' : sensus.pages.smartpoint.clearManualOverride,
							'getDataFromLight' : sensus.commons.modules.summaryData.lightActions.getDataFromLight,
							'updateReadings' : sensus.commons.modules.summaryData.lightActions.getReadings,
							'editLightStatusDetail' : sensus.commons.modules.summaryData.lightActions.editLightStatusDetail,
							'resetValue' : sensus.commons.modules.summaryData.lightActions.resetValues

					};

					$.sc.menuPlug(sensus.commons.modules, oMenuPlug);
				}
			}
		},

		content : {}
};

</script>
</sec:authorize>