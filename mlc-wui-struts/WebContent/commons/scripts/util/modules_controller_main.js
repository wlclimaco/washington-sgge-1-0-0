sensus.commons.modules = {

		oPropertiesContent  : null,
		bForceReload        : false,
		ajaxCache           : [],

		/**
		 * Load Modules Functionality
		 */
		loadModules : function () {
			// Parameters for type
			var oParameters = $.grep(sensus.settings.oDeviceTypeParameters, function(n) {
			    var sDeviceType = $.address.parameter("deviceType");
				return n.deviceType.type == sDeviceType;
			});

			$.moduleController.init(oParameters[0], "#about-deivce");

			// TODO - Use global message code
			// Close message
			$("#messaging-smartpoint-detail").delegate(".remove", "click", function(e) {
				e.preventDefault();
				$(this).parent().fadeOut("slow");
			});
		},
		
		reloadData : function(){

			sensus.commons.modules.ajaxCache.splice(0, sensus.commons.modules.ajaxCache.length);
			sensus.commons.modules.bForceReload = true;
			
			var aSummary = sensus.settings.oDeviceTypeParameters.summaryData;
			var aContent = sensus.settings.oDeviceTypeParameters.content;
			
			for (var i = 0; i < aSummary.length; i++) {

				sensus.commons.modules.summaryData[aSummary[i]].init();

			}
			
			for (var k = 0; k < aContent.length; k++) {

				try {

					sensus.commons.modules.content[aContent[k]].init();

				} catch(e){
				
				}

			}
		
			sensus.commons.modules.bForceReload = false;
		
		},

		util : {

			ajaxData : {
				//TODO: find a way to do this URL dynamic
				fetchDeviceById : function () {
					var	obj = {
						url : "smartpoint/ajax.fetchLightById.action",
						data : {'lightRequest': new lightRequest($.address.parameter('id'))}
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

				if (aCache.length && !bForceReload) {
					return aCache[0].oResponse;
				}

			    return $.ajax({
					url          : obj.url,
					dataType     : 'json',
					contentType  : "application/json; charset=utf-8",
					type         : "POST",
					async        : false,
					data         : $.toJSON(obj.data),
			        success		 : function( resp ) {
			        	if (!bNotCache) {
			        		obj.oResponse = resp;
			        		ajaxCache.push(obj);
			        	}
			        }
			    });
			},

			fnFillData : function(oDom, aData) {
				
				var sText = '';

				for (var i = 0; i < aData.length; i++) {

					sText += ' '+aData[i];

				}

				oDom.html(sText);

			},
			
			fnFillLastDate : function(aData, sParameter) {
				
				var aParam = sensus.commons.modules.util.fnGetParameterValue(aData, sParameter, 'modifyDate');
								
				if (!$.ajaxValidator.fnIsNullOrUndefined(aParam)) {
					
					return aParam;
				
				} else {
				
					return sensus.commons.modules.util.fnGetParameterValue(aData, sParameter, 'createDate');
				
				}				
			},

			fnGetParameterValue : function(aData, sParameter, sValue){
				
				var aParameter = $.grep(aData.parameters, function(e) { return e.propertyEnum == sParameter; });
				
				if(aParameter.length){
					
					return aParameter[0][sValue];

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

				if (oResponse && oResponse.devices && oResponse.devices.length) {

					return oResponse.devices[0];

				} else if(oResponse || oResponse.firstLight && oResponse.firstLight.length) {

					return oResponse.firstLight;

				}

				return null;

			},
			
			fnGetHistoryFromLight : function() {
				
				fnGetResponse(sensus.settings.searchSmartpointHistory, {'inquiryLightRequest' : new inquiryLightRequest()}, false);
				
			},

			fnValidateField : function(sField){
				if(sField == null || sField == undefined ){
					return "";
				} else {
					return sField;
				}
			}

		},

		reloadPage : function () {

		},

		executeTab : function (aMockTabs) {
			// Call Tabs functionality
			if (aMockTabs.length) {
				sensus.commons.modules.tabs.init();
				
			}
		},

		executeSummaryData : function (aMockSummaryData) {
			if (aMockSummaryData.length) {
				for (var i = 0; i < aMockSummaryData.length; i++) {
					sensus.commons.modules.summaryData[aMockSummaryData[i]].init();
				}
			}
		},

		executeContent : function (aMockContent) {
			// Call modules matching with parameters
			if (aMockContent.length) {

				for (var i = 0; i < aMockContent.length; i++) {

					if(sensus.commons.modules.content[aMockContent]){

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

			fnGetUrlParameters : function (oElement) {

				var sProperties = "";

				if (sensus.commons.modules.oPropertiesContent && !oElement.parent().prev().length ) {

					sProperties = "&" + $.param(sensus.commons.modules.oPropertiesContent);

				}

				var sUrl =  $(oElement).attr("href") + "?id=" + $.address.parameter("id") + "&deviceType=" + $.address.parameter("deviceType") + sProperties;
				return sUrl;

			},

			fillTab : function(oElement) {
				sensus.commons.lib.ajax.loadTab($.extend({}, sensus.commons.lib.ajax.param,
						{
							sUrl : sensus.commons.modules.tabs.fnGetUrlParameters($(oElement)),
							$container : $('#load'),
							$container_tabs : $('#tabs-content'),
							$element : $(oElement)
						}
				));
			},

			fnExecuteContent : function(element) {

				if ($(element).data("parameter")) {
					sensus.commons.modules.executeContent($(element).data("parameter"));
				}

			},

			fnClickTabs : function(event) {
				event.preventDefault();
			
				sensus.commons.modules.ajaxCache.splice(0, sensus.commons.modules.ajaxCache.length); 
				
				sensus.util.page.startProgressBar();

				sensus.commons.modules.tabs.cleanTab();

				sensus.commons.modules.tabs.activeMenu(this);

				if ($(this).parent().prev().length) {

					sensus.commons.modules.tabs.fillTab(this);

				} else {

					$.address.value(sensus.commons.modules.tabs.fnGetUrlParameters($(this)));
					sensus.commons.lib.ajax.loadFirstTab(sensus.commons.modules.tabs.fnGetUrlParameters($(this)), true);

				}

				sensus.commons.modules.tabs.fnExecuteContent($(this));

				sensus.util.page.stopProgressBar(0);

			},

			 init : function() {

				    var oTabs = $("#tabs");
				    var oInitTab =  $("li:first", oTabs).find("a");

				    /**
				     * Load First Tab
				     */
				    sensus.commons.lib.ajax.loadFirstTab(sensus.commons.modules.tabs.fnGetUrlParameters(oInitTab));

				    var sUrl = ($.address.value()).split('?')[0];

				    sensus.commons.modules.tabs.cleanTab();

				    /** check if the url is a tab */
				    if (sUrl.indexOf('tabs') == -1) {

				     oInitTab.addClass("active");

				    }

				    $('li a', oTabs).click(sensus.commons.modules.tabs.fnClickTabs);

			 }
		},

		summaryData : {

			messageReceived : {

				fillMessageReceived : function (oResponse) {

					if (oResponse && oResponse.loadProfile && oResponse.loadProfile.createDate) {

						$("#receivedDate").text($.date.dateFormat(oResponse.loadProfile.createDate, "MM d, yy at hh:i A"));

					} else {

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
				
					if(aData.lightStateEnum == "BLINK"){

						if(aData.lightBlinkEnumValue == 1){

							sMsg = 'BLINK FAST';

						} else if(aData.lightBlinkEnumValue == 2){

							sMsg = 'BLINK SLOW';

						}
					
					} else if(aData.lightStateEnum == "ON"){

						if(aData.lightIntensityEnumValue != 0 && aData.lightIntensityEnumValue != 6){

							sMsg = 'DIM '+aData.lightIntensityPercentageValue+'%';

						} else {

							sMsg = 'ON';

						}

					} else {

						sMsg = 'OFF';

					}	

					return sMsg;
				
				},

				fnSetOverride : function(aData){

					if(parseInt(aData.overrideEnumValue) != 0){

						var sMsgDate = '';

						if(parseInt(aData.overrideEnumValue) == 3){

							sMsgDate = aData.overridePerDate;

						}

						var oCommunication = $('#comunication-messaging .message h3');
						oCommunication.html(sensus.locale.get('smartpointdetail.page.lightoverride'));
						var sMsg = sensus.commons.modules.summaryData.lightInformation.fnSetLightState(aData);

						if(oCommunication.next().length){

							oCommunication.next().remove();

						}

						$(sensus.locale.get('smartpointdetail.page.lightoverridetext.'+aData.overrideEnumValue, sMsg, $.date.dateFormat(sMsgDate, sensus.settings.dateFormatMask + " at h:i a"))).insertAfter(oCommunication);
						$('#comunication-messaging').show();

					}

				},

				fnUpdateLightInformation : function(){

					sensus.commons.modules.bForceReload = true;
					sensus.commons.modules.summaryData.lightState.init();
					var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
					
					if (oResponse.firstLight.overrideEnum == 'NONE' && oResponse.operationSuccess == true) {

						$('#comunication-messaging').hide();

					}

					var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
					sensus.commons.modules.summaryData.lightInformation.fnSetOverride(oFirstMeter);
					sensus.commons.modules.bForceReload = false;

				},

				init: function(){

					try{

						var sMessagingMain  = $('#messaging-main');

						$('#messaging-main').remove();
						$('#yui-main').prepend(sMessagingMain);
						$('.stamp-smartpoint').remove();
						var oResponse = sensus.commons.modules.summaryData.deviceInformation.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						sensus.commons.modules.util.fnFillData($('#pole-id'), [oFirstMeter.poleId]);
						sensus.commons.modules.util.fnFillData($('.description-id'), [oFirstMeter.rniId]);

						var aAddress = [

							oFirstMeter.streetName,
							oFirstMeter.cityName,
							oFirstMeter.stateName,
							oFirstMeter.zipCode

						],
							bValidAddress = true;

						for (u in aAddress) {
							
							if (aAddress[u] == null) {
								
								bValidAddress = false;
								
							}
							
						}

						if (!bValidAddress) {
							
							aAddress = [sensus.locale.get('smartpointdetail.page.noaddress')];
							
						}

						sensus.commons.modules.util.fnFillData($('.description-address'), aAddress);

						sensus.commons.modules.util.fnFillData($('#light-dim'), [oFirstMeter.dimmable]);

						//light-levels
						var aLightLevels = [];

						if(oFirstMeter.lightIntensitylevels.length){

							for (var i = 0; i < oFirstMeter.lightIntensitylevels.length; i++) {

								aLightLevels.push(oFirstMeter.lightIntensitylevels[i].percentage);

							}

						} else {

							aLightLevels = [25,50,75,90];

						}
						sensus.commons.modules.util.fnFillData($('#light-levels'), [aLightLevels]);

						sensus.commons.modules.summaryData.lightInformation.fnSetOverride(oFirstMeter);

					}catch(e){}

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

					try{
						var oResponse = sensus.commons.modules.summaryData.lightState.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						var sMsg = sensus.commons.modules.summaryData.lightInformation.fnSetLightState(oFirstMeter); 

						sensus.commons.modules.util.fnFillData($('#light-state'), [sMsg]);

					}catch(e){}

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

					try{

						var oResponse = sensus.commons.modules.summaryData.lightStatus.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						sensus.commons.modules.util.fnFillData($('#light-status'), [oFirstMeter.currentStatusMessage.lightStatusEnum.toLowerCase()]);

					} catch(e){}

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

					try{

						var oResponse = sensus.commons.modules.summaryData.statusMessage.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						// Format message Date
						var sDate = oFirstMeter.currentStatusMessage.date;
						var sHour = "";
						var sFormatDate = "";

						sFormatDate = $.date.dateFormat( sDate, 'MM d, yy',null,true);
						sHour       = $.date.dateFormat( sDate, 'h:i:s:fff A',null,true);

						sensus.commons.modules.util.fnFillData($('#date-message'), [sFormatDate]);
						sensus.commons.modules.util.fnFillData($('#hour-message'), [sHour]);

					}catch(e){}

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

					try{

						var oResponse = sensus.commons.modules.summaryData.ecoMode.fnGetResponse();
						var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
						var nPercent = '--';

						if(oFirstMeter.ecoMode){
						
							var nValue = Math.round(oFirstMeter.ecoMode);
							nPercent = nValue + '%';
							
							var sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Economy');
							
							if(nValue >= 15 && nValue <= 50){
							
								sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Value');
							
							} else if(nValue < 15){
							
								sEcoModeLabel = sensus.locale.get('smartpoint.detail.ecoMode.Security');
							
							}
							sensus.commons.modules.util.fnFillData($('#ecomode-percent').next(), [sEcoModeLabel]);
						
						}

						sensus.commons.modules.util.fnFillData($('#ecomode-percent'), [nPercent]);

					}catch(e){}

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

					$.ajaxValidator.fnMonitor(sensus.settings.forceLightStatus, { 'lightRequest': new lightRequest($.address.parameter('id')) }, false, sensus.commons.modules.reloadData, sensus.locale.get("action.longRunningProcessDialog.confirm"), true);

				},
				
				resetValues : function(){

					// Submit Ajax Action
					var oRequest = { 'lightRequest': new lightRequest($.address.parameter('id')) };
					$.ajaxValidator.fnDoCall(sensus.settings.resetValues, oRequest, false, sensus.commons.modules.content.lightReadings.fnUpdateReadings, sensus.locale.get("smartpointdetail.action.resetvalues.success"));

				},				

				init: function() {

					//Remove the 'Edit Light Status' action for the DEACTIVATED Lights
					var oResponse = sensus.commons.modules.summaryData.statusMessage.fnGetResponse();

					if (oResponse.firstLight.currentStatusMessage.lightStatusEnum == "DEACTIVATED") {
					
						$('#editLightStatusDetail').parent().remove();
						$("#lightsOnOff").addClass('ui-state-disabled').click(function(e){
						
							e.preventDefault();
						
						});
						
					} else {
					
						$("#lightsOnOff").wLightControl(false);
					
					}

					$('#comunication-messaging').on('click', '#clear-override', function(e){
						e.preventDefault();
						sensus.pages.smartpoint.clearManualOverride();
					});

					var oMenuPlug = {

							'clearManualOverride' : sensus.pages.smartpoint.clearManualOverride,
							'getDataFromLight' : sensus.commons.modules.summaryData.lightActions.getDataFromLight,
							'resetValue' : sensus.commons.modules.summaryData.lightActions.resetValues

					};
					
					sensus.util.page.menuPlug(sensus.commons.modules, oMenuPlug);

				}

			},

			/**
			 * Device Information.
			 * Includes Meter Id, Address.
			 */

			deviceInformation : {

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

				/**
				 * Fill the id field.
				 * @param oFirstDevice    - [Object] Device Object
				 * @param oInformation - [Object] Dom Object with the container where "device-id" is found.
				 */
				fillId : function(oFirstDevice, oInformation) {
					var oDeviceId;

					if (oFirstDevice) {

						oDeviceId = $("#device-id", oInformation);

						if (oFirstDevice.deviceTypeEnum == "METER") {

							oDeviceId.text(oFirstDevice.meterId);

						} else if (oFirstDevice.deviceTypeEnum == "HAN_DEVICE") {

							oDeviceId.text(oFirstDevice.clientEndPointId);

						} else {

							oDeviceId.text('#'+oFirstDevice.poleId);

						}

					}

				},

				/**
				 * Fill the Premise Id field.
				 * @param oFirstDevice - [Object] Device Object
				 * @param oInformation - [Object] Dom Object with the container where "premiseId" is found.
				 */
				fillPremiseId : function(oFirstDevice, oInformation) {
					var oPremiseId = $("#premise-id", oInformation);

					if (oFirstDevice && oFirstDevice.premiseId) {
						oPremiseId.text(oFirstDevice.premiseId);
					} else {
						oPremiseId.text("--");
					}

				},

				/**
				 * Fill the address field.
				 * @param oFirstMeter - [Object] Meter Object
				 * @param oInformation - [Object] Dom Object with the container where "meter-id" is found.
				 */
				fillAddress : function(oFirstMeter, oInformation) {
					var oAddress;
					if (oFirstMeter) {
						oAddress = $(".description-address", oInformation);

						if ((oFirstMeter.address === null) && (oFirstMeter.city === null) && (oFirstMeter.state === null) && (oFirstMeter.zip === null)) {
							oAddress.text("");
						} else {
							var sAddress = oFirstMeter.address,
								sCity = oFirstMeter.city,
								sState = oFirstMeter.state,
								sZip = oFirstMeter.zip;

							if (sAddress === null) {
								sAddress = "";
							}

							if (sCity === null) {
								sCity = "";
							}

							if (sState === null) {
								sState = "";
							}

							if (sZip === null) {
								sZip = "";
							}
							oAddress.text(sAddress + " " + sCity + ", " + sState + " " + sZip);
						}

					}
				},

				init: function(){
					var oInformation = $("#detail-header-pane-1");
					var oDeviceType = $('h1', oInformation);
					var oResponse = sensus.commons.modules.summaryData.deviceInformation.fnGetResponse();
					var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
					oDeviceType.prepend("<strong>" + $.address.parameter("deviceType") + ' ' + "</strong>");

					sensus.commons.modules.summaryData.deviceInformation.fillId(oFirstMeter, oInformation);
					sensus.commons.modules.summaryData.deviceInformation.fillPremiseId(oFirstMeter, oInformation);
					sensus.commons.modules.summaryData.deviceInformation.fillAddress(oFirstMeter, oInformation);

				}


			},

			/**
			 * On Glass Read.
			 */
			onGlassRead : {

				/**
				 * Get the Current Reading Value configured to show at screen.
				 * @param sCurrentReadingValue - [String] - A String with the value.
				 * @returns [Array]  - The Array with all the current reading values
				 */
				getReadingValue : function (sCurrentReadingValue) {
					var aCurrentData;

					// Check if currentReading is a value number
					if (!sCurrentReadingValue || typeof sCurrentReading != "number") {
						sReadingValue = 0;
					}

					sCurrentReadingValue = ("00000000000" + sCurrentReadingValue).slice(-11);
					aCurrentData = sCurrentReadingValue.split("");

					return aCurrentData;
				},

				/**
				 * Create the dom object as a String.
				 * @param aCurrentData - [Array]  The array with all reads value
				 * @param sUnit - [String] The Unit value
				 * @returns [String] The Dom object as a String
				 */
				fnCreateReadingContainer : function (aCurrentData, sUnit) {
					var aCurrentDataLength = aCurrentData.length;
					var sHtml = "";

					if (aCurrentDataLength) {
						for(var i = 0; i < aCurrentDataLength; i++) {
						    sHtml += "<span>" + aCurrentData[i] + "</span>";
						}
					}

					if(sUnit) {

						sHtml += "<div id='unit-measure'><strong> " + sUnit + " </strong></div>";

					} else {

						sHtml += "<div id='unit-measure'><strong>  </strong></div>";

					}

					return sHtml;
				},

				/**
				 * Fill The On Glass Read Information
				 * @param oResponse - [Object] The Response Object
				 */
				fillOnGlassRead : function(oResponse) {

					if (oResponse && oResponse.loadProfile) {

						var sCurrentReading = oResponse.loadProfile.currentReading;
						var aValues;
						var sCurrentReadingValue;
						var sUnit;
						var aCurrentData;

						if (sCurrentReading) {
							aValues = sCurrentReading.split(" ");
							sCurrentReadingValue = aValues[0];
							sUnit = aValues[1];

							aCurrentData = this.getReadingValue(sCurrentReadingValue);

							$("#meter-plate").html(this.fnCreateReadingContainer(aCurrentData, sUnit));
						}

					}
				},

				init : function () {

					var obj = function (){
						return {
							url : sensus.settings.fetchUpdatedMeterLoadProfile,
							data : 'id='+$.address.parameter("id")
						};
					};

					$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
						sensus.commons.modules.summaryData.onGlassRead.fillOnGlassRead(oResponse);
					});
				}

			},

			hour : {

				/**
				 * Fill The Hours Information
				 * @param oResponse - [Object] The Response Object
				 */
				fillHour : function (oResponse) {
					var oHourContainer = $("#smartpointdetail-page-hour");

					if (oResponse && oResponse.loadProfile &&oResponse.loadProfile.currentHourconsumption){

						oHourContainer.text(oResponse.loadProfile.currentHourconsumption);

					} else {

						oHourContainer.text("--");

					}
				},

				fillHourTitle : function (oResponse) {
					var oHour = $('#hour');
					var sStart = "Start Date ";
					var sEnd = "End Date ";

					if (oResponse && oResponse.loadProfile) {

						if (oResponse.loadProfile.hourlyConsumptionStart) {
							sStart += $.date.dateFormat(oResponse.loadProfile.hourlyConsumptionStart, "m/dd/yy h:i A");
						} else {
							sStart += "--";
						}

						if (oResponse.loadProfile.hourlyConsumptionEnd) {
							sEnd += $.date.dateFormat(oResponse.loadProfile.hourlyConsumptionEnd, "m/dd/yy h:i A");
						} else {
							sEnd += "--";
						}

						oHour.attr({
							'title' : sStart + '\n\r ' + sEnd
						});

					}

				},

				init : function () {
					var obj = function (){
						return {
							url : sensus.settings.fetchUpdatedMeterLoadProfile,
							data : 'id='+$.address.parameter("id")
						};
					};

					$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
						sensus.commons.modules.summaryData.hour.fillHour(oResponse);
					});
				}
			},

			month : {

				/**
				 * Fill The Month Information
				 * @param oResponse - [Object] The Response Object
				 */
				fillMonth : function (oResponse) {
					var oMonthContainer = $("#smartpointdetail-page-month");

					if (oResponse && oResponse.loadProfile && oResponse.loadProfile.currentMonthconsumption){

						oMonthContainer.text(oResponse.loadProfile.currentMonthconsumption);

					} else {

						oMonthContainer.text("--");

					}
				},

				init : function () {
					var obj = function () {
						return {
							url : sensus.settings.fetchUpdatedMeterLoadProfile,
							data : 'id='+$.address.parameter("id")
						};
					};

					$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
						sensus.commons.modules.summaryData.month.fillMonth(oResponse);
					});
				}
			},

			peakDemand : {

				fillPeakDemand : function(oResponse) {

					var oPeakDemand = $("#smartpointdetail-page-peakdemand");
					if(oResponse && oResponse.loadProfile && oResponse.loadProfile.consumptionPeakDemand){

						oPeakDemand.text(oResponse.loadProfile.consumptionPeakDemand);

					}else{

						oPeakDemand.text('--');

					}

				},

				init : function() {
					var obj = function () {
						return {
							url : sensus.settings.fetchUpdatedMeterLoadProfile,
							data : 'id='+$.address.parameter("id")
						};
					};

					$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
						sensus.commons.modules.summaryData.peakDemand.fillPeakDemand(oResponse);
					});
				}

			},

			networkStatus : {

				fillNetworkStatus : function (oResponse) {
					oHanDevice = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

					if (oHanDevice) {
						$("#network-status-value").text(sensus.locale.get("commons.pages."+oHanDevice.hanDeviceStatusEnum));
					}

				},

				init : function () {

					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById)).done(function(oResponse) {
						sensus.commons.modules.summaryData.networkStatus.fillNetworkStatus(oResponse);
					});

				}
			},

			parent : {

				fillParent : function (oResponse) {
					oHanDevice = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

					if (oHanDevice && oHanDevice.baseFlexNetId) {
						$("#parent-value").html("<a href='smartpointdetail/ajax.smartpoint.detail.main.action?id="+ oHanDevice.baseFlexNetId +"&deviceType=Meter' class='alist'>" + oHanDevice.baseFlexNetId + "</a>");
					} else {
						$("#parent-value").text("--");
					}
				},

				init : function () {
					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById)).done(function(oResponse) {
						sensus.commons.modules.summaryData.parent.fillParent(oResponse);
					});
				}
			},

			connectedDate : {

				fillConnectedDate : function (oResponse) {
					oHanDevice = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

					if (oHanDevice) {
						var sInstallDate = "";

						try {
							sInstallDate = $.date.dateFormat(oHanDevice.installDate);
						} catch (e) {
							sInstallDate = "--";
						}

						$("#connected-date-value").text(sInstallDate);
					}
				},

				init : function () {
					$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById)).done(function(oResponse) {
						sensus.commons.modules.summaryData.connectedDate.fillConnectedDate(oResponse);
					});
				}
			},

			actions : {

				connectFunctions : {
					fnApplyHanDevice : function (oData) {
						var sUrl = 'smartpoint/ajax.applyActions.action';
						var sId;

						if (oData.sId) {
							sId = "&id=" + oData.sId;
						} else {
							sId = "";
						}

						var sData =  "actionName=" + (oData.sActionName || null) + "&installCode=" + (oData.sInstallCode || null) + "&macAddress=" + (oData.sMacAddress || null) + "&manufacture=" + (oData.sManufacture|| null) + "&model=" + (oData.sModelNumber|| null) + "&deviceId=" + (oData.sDeviceId|| null) + "&clientEndPointId=" + (oData.sClientEndPointId|| null) + sId;
						var fnCallback = function () {
							// Call modules controller component
							sensus.commons.modules.loadModules();
						};

						sensus.pages.longrunningprocess.monitor(sUrl, sData, sensus.locale.get("action.connectHanDevice.confirm"), fnCallback);
					},

					/**
					 * Validate the connect Dialog
					 * @param {Object} oData - The data with the field values.
					 * @returns Return true if there is some field that does not match with validation
					 */
					fnValidateInsertHanDevice : function (oData) {

						var sSelect = sensus.locale.get('commons.pages.select'),
							oDeviceType = $('#select-device-types'),
							oDeviceId = $("#device_name_input"),
							oManufacture = $("#select-manufacture"),
							oModelNumber = $("#select-model-number");

						//Validations selects and input
						if ((oData.sDeviceType === sSelect) || (oData.sDeviceId === "") || (oData.sManufacture === sSelect) || (oData.sModelNumber === sSelect))  {

							if (oData.sDeviceType === sSelect) {

								oDeviceType.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);

							}

							if (oData.sDeviceId === "") {

								oDeviceId.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
								oDeviceId.val("");
							}

							if (oData.sManufacture === sSelect) {

								oManufacture.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);

							}

							if (oData.sModelNumber === sSelect) {

								oModelNumber.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);

							}

							return true;
						}
						return false;

					},

					/**
					 * Validate the connect Dialog
					 * @param {Object} oData - The data with the field values.
					 * @returns Return true if there is some field that does not match with validation
					 */

					fnValidateConnectToHanFields : function (oData) {
						var regEx = /^\s+$/;

						//Validation inputs
						if ((oData.sInstallCode === "") || (oData.sInstallCode.search(regEx) === 0)
								|| (oData.sClientEndPointId === "") || (oData.sClientEndPointId.search(regEx) === 0)) {

							if ((oData.sInstallCode === "") || (oData.sInstallCode.search(regEx) === 0)) {

								var oInstallCode = $("#install_code");

								oInstallCode.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
								oInstallCode.val("");
							}

							if ((oData.sClientEndPointId === "") || (oData.sClientEndPointId.search(regEx) === 0)) {

								var oDeviceId = $("#device_id");

								oDeviceId.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
								oDeviceId.val("");

							}

							return true;
						}

						return false;
					},

					/**
					 * Validate the connect Dialog
					 * @param {Object} oData - The data with the field values.
					 * @returns Return true if there is some field that does not match with validation
					 */
					fnValidateConnectHanFields : function (oData) {
						//Regular Expression for validate the fields
						var regExMac = /^([a-zA-Z0-9][a-zA-Z0-9]:){7}[a-zA-Z0-9][a-zA-Z0-9]$/,
							regExInstall = /^\s+$/;

						if ((oData.sInstallCode === "") || (oData.sInstallCode.search(regExInstall) === 0) || (oData.sMacAddress.search(regExMac) === -1)) {

							//Validation field Install Code
							if ((oData.sInstallCode === "") || (oData.sInstallCode.search(regExInstall) === 0)) {

								var oInstallCode = $("#install_code");

								oInstallCode.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
								oInstallCode.val("");

							}

							//Validation field Mac Address
							if (oData.sMacAddress.search(regExMac) === -1) {

								var oMacAddress = $("#mac_address");
								oMacAddress.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);

							}

							return true;
						}

						return false;
					},

					/**
					 * Events to clear validation error message.
					 * @param {Object} actionDialog - The Dialog DOM Object.
					 */

					fnClearValidationEvents : function (actionDialog) {

						//Remove div of validation error
						$("#install_code", actionDialog).focus(function() {

						    $(".install_codeformError", actionDialog).remove();

						});

						//Remove div of validation error
						$("#mac_address", actionDialog).focus(function() {

						    $(".mac_addressformError", actionDialog).remove();

						});

						//Remove div of validation error
						$("#select-device-types", actionDialog).click(function() {

							$(".select-device-typesformError", actionDialog).remove();

						});

						//Remove div of validation error
						$("#select-manufacture", actionDialog).click(function() {

							$(".select-manufactureformError", actionDialog).remove();

						});

						//Remove div of validation error
						$("#select-model-number", actionDialog).click(function() {

							$(".select-model-numberformError", actionDialog).remove();

						});

						//Remove div of validation error
						$("#device_name_input", actionDialog).focus(function() {

						    $(".device_name_inputformError", actionDialog).remove();

						});

					}
				},

				connectHANDevice : {

					fnFillExistingDevice : function (device, oDom) {

						//Get Device Type
						var sDeviceType = "";
						if (device.deviceType && device.deviceType.type) {
							sDeviceType = device.deviceType.type;
						}

						// Show Device Information Container.
						oDom.oFormInfo.removeClass("hide");
						oDom.oAddFields.addClass("hide");

						// Change Icon
						oDom.oMacAddressValidate.removeClass("icn-sending").addClass("icn-valid-form");

						// Set Values
						oDom.oDeviceType.text(sDeviceType);
						oDom.oDeviceId.text(device.clientEndPointId);
						oDom.oManufacture.text(device.manufacture);
						oDom.oModelNumber.text(device.model);
					},

					fnFillManufactureByType : function (typeId, oDom) {
						oDom.oSelectManufacture.empty();
						oDom.oSelectModelNumber.empty();
						oDom.oSelectModelNumber.attr("disabled", "true");

						if (oDom.oSelectDeviceTypes.val() != sensus.locale.get("commons.pages.select")) {
							oDom.oSelectManufacture.removeAttr("disabled");
							$.ajax({
								url : 'smartpointdetail/include.fetchAllManufactureByHanDeviceType.action',
								data : 'typeId=' + typeId,
								success : function (data) {
									if (data && data.hanDevices && data.hanDevices.length) {
										var sOptions = "<option name='0'>Select</option>";
										var iHanDevicesLength = data.hanDevices.length;

										for (var i = 0; i < iHanDevicesLength; i++) {
											sOptions += "<option name="+ data.hanDevices[i].manufacture +">" + data.hanDevices[i].manufacture + "</option>";
										}

										oDom.oSelectManufacture.append(sOptions);
									}
								}
							});
						} else {
							oDom.oSelectManufacture.attr("disabled", "true");
						}

					},

					fnFillModelNumberByManufacture : function (typeId, sManufacture, oDom ) {
						oDom.oSelectModelNumber.empty();
						if (oDom.oSelectManufacture.val() != sensus.locale.get("commons.pages.select")) {
							oDom.oSelectModelNumber.removeAttr("disabled");
							$.ajax({
								url : 'smartpointdetail/include.fetchAllModelByHanDeviceType.action',
								data : 'typeId=' + typeId + '&manufacture=' + sManufacture,
								success : function (data) {
									if (data && data.hanDevices && data.hanDevices.length) {
										var sOptions = "<option name='0'>Select</option>";
										var iHanDevicesLength = data.hanDevices.length;

										for (var i = 0; i < iHanDevicesLength; i++) {
											sOptions += "<option name="+ data.hanDevices[i].model +">" + data.hanDevices[i].model + "</option>";
										}

										oDom.oSelectModelNumber.append(sOptions);
									}
								}
							});
						} else {
							oDom.oSelectModelNumber.attr("disabled", "true");
						}

					},

					fnFillNonExistingDevice : function (oDom) {

						var oSelectDeviceTypes = $("#select-device-types");

						oSelectDeviceTypes.empty();

						$.ajax({
							url : 'smartpointdetail/include.getAllDeviceTypes.action',
							success : function (data) {
								var iDataLength = data.length;
								if (data && iDataLength) {
									var sOptions = "";

									for (var i = 0; i < iDataLength; i++) {
										sOptions += "<option name='"+ data[i].id +"'>" + data[i].value + "</option>";
									}

									oSelectDeviceTypes.append(sOptions);
								}
							}
						});

					},

					fnGetHanDeviceByDeviceResponse : function (id) {
						return $.ajax({
							url : "smartpointdetail/include.fetchDeviceById.action",
							data: 'macAddress=' + id + "&deviceType=HAN_DEVICE"
						});
					}

				},

				menuPlugCallBack : function (item) {
					switch (item) {
						case "sensus.epm.action.connect.to.han" :
							sensus.util.actiondialog.launchActionDialog('connectToHan', sensus.commons.modules.dialogSettings['connectToHan']);
							break;
						case "sensus.epm.action.demand.response" :
							sensus.util.actiondialog.launchActionDialog('DemandResponse', sensus.commons.modules.dialogSettings['DemandResponse']);
							break;
						case "sensus.epm.action.connect.han.device"	:
							sensus.util.actiondialog.launchActionDialog('connectHanDevice', sensus.commons.modules.dialogSettings['connectHanDevice']);
							break;
						case "sensus.epm.action.send.han.text.message"	:
							var bConnected = true;
							// Get Device Status
							$.when(sensus.commons.modules.util.getData(sensus.commons.modules.util.ajaxData.fetchDeviceById)).done(function(oResponse) {
								var oDevice = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
								if (oDevice && (oDevice.deviceTypeEnum != "METER" && oDevice.hanDeviceStatusEnum != "CONNECTED")) {
									bConnected = false;
								}
							});
							if (bConnected) {
								sensus.util.actiondialog.launchActionDialog('HANTextMessage', sensus.commons.modules.dialogSettings['HANTextMessage']);
							} else {
								sensus.util.actiondialog.launchActionDialog('SendHANTextMessageDisconnected', sensus.commons.modules.dialogSettings['SendHANTextMessageDisconnected']);
							}
							break;
						case "sensus.epm.action.setup.demand.response"	:
							sensus.util.actiondialog.launchActionDialog('SetupDemandResponse', sensus.commons.modules.dialogSettings['SetupDemandResponse']);
							break;
						case "sensus.epm.action.initiate.demand.reset"	:
							//sensus.util.actiondialog.launchActionDialog('applyDemandReset', sensus.commons.modules.dialogSettings['applyDemandReset']);
							sensus.util.globalActions.applyScheduledEvent.apply();
							break;
						case "sensus.epm.action.disconnect.han.device"	:
							sensus.util.actiondialog.launchActionDialog('removeFromHAN', sensus.commons.modules.dialogSettings['removeFromHAN']);
							break;
						case "sensus.epm.action.get.connection.status"	:
							sensus.util.globalActions.applyScheduledEvent.getConectStatus(true);
							break;
						case "sensus.epm.action.delete.han.device"	:
							sensus.util.actiondialog.launchActionDialog('deleteHanDevices', sensus.commons.modules.dialogSettings['deleteHanDevices']);
							break;
					}

				},

				init : function (){
					sensus.util.page.menuPlug(sensus.commons.modules, this.menuPlugCallBack);
				}
			}

		},

		content : {


			group : {

				dialogDeleteGroup:{

					/**
					 * The dialog title.
					 */
					title : sensus.locale.get("commons.pages.removeConfirm"),


					/**
					 * The dialog width.
					 */
					width : 300,

					/**
					 * The dialog buttons (submit and cancel).
					 */
					buttons : (function() {
						var buttons = {};
						//SmartPointCount;

						buttons[sensus.locale.get("commons.pages.remove")] = function() {

							var actionDialogId = $("#action-dialog"),
								sGroupId = sensus.commons.modules.content.group.idGroup,
								sDeviceId = sensus.commons.modules.content.group.idDevice,
								oResponse = sensus.commons.modules.content.group.fnRemoveGroup(sGroupId, sDeviceId);

							if (oResponse) {

								if (oResponse.operationSuccess) {

									var sMessagingSmartpointDetail = 'messaging-smartpoint-detail';

									sensus.commons.modules.content.group.fnLoadGroups(sensus.commons.modules.content.group.idDevice);
									sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("smartpointdetail.tabs.about.deleteGroup", sensus.commons.modules.content.group.sNameGroup), "confirm");

								} else {

									/** Show message Error */
									sensus.util.page.showMessage(sMessagingSmartpointDetail, oResponse.messageList[0].text, "error");

								}

							}

							$(this).dialog('close');

							actionDialogId.dialog('close');
						};

						buttons[sensus.locale.get("commons.pages.cancel")] = function() {
							var actionDialogId = $("#action-dialog");
							actionDialogId.dialog('close');
						};
						return buttons;
					})(),

					/**
					 * The function that will be called when the action dialog is launched.
					 *
					 * @param actionDialog
					 *            a reference to the actionDialog objext
					 */
					action : function(actionDialog) {
						var actionDialogId = $("#action-dialog");

						$('#action-dialog').text(sensus.locale.get("smartpointdetail.tabs.about.deleteGroupPermanently",$('#meter-id').text(),sensus.commons.modules.content.group.sNameGroup));

						actionDialogId.dialog('open');
					}

				},

				/**
				 * Insert Group by Device ID
				 * @param  - [String] sGroupName Name of Group
				 * 			 [String] sDeviceId ID of Device
				 *           [String] sGroupId ID of group
				 * @return - [Object] oResponse response of the insert
				 */
				fnAddGroup : function(sGroupName, sDeviceId, sGroupId) {

					var oResponse = null;

					if (!sGroupId) {
						sGroupId = "";
					}

					$.ajax({
						url : sensus.settings.addToGroup,
						data : "groupId=" + sGroupId + "&meterId=" +  sDeviceId + "&groupName="+ sGroupName,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Remove Group
				 * @param  - [String] sGroupId ID of Group
				 * 			 [String] sDeviceId ID of Device
				 * @return - [Object] oResponse response of the remove
				 */
				fnRemoveGroup : function(sGroupId, sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.removeMeterFromGroup,
						data : "groupId=" + sGroupId
								+ "&meterId=" +  sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Ajax call to get the Response with groups by meter
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchGroupByMeter : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchListGroupMeter,
						data: 'id=' + sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Get list groups from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of groups
				 */
				fnGetListGroups: function(oResponse){

					if (oResponse && oResponse.groups && oResponse.groups.length) {
						return oResponse.groups;
					}

					return null;
				},

				/**
				 * Ajax call to get the Response with all groups
				 *
				 * @return [Object] oResponse Object
				 *
				 */
				fnFetchAllGroup : function() {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchListGroup,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Get list groups from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of groups
				 */
				fnGetListAllGroups: function(oResponse){

					if (oResponse && oResponse.groups && oResponse.groups.length) {
						return oResponse.groups;
					}

					return null;
				},

				/**
				 * Load Groups and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadGroups : function(sId){
					var i = 0,
						sTableGroupHtml = '',
						sOptionGroupHtml = '',
						oGroups = $('#groups'),
						oTbodyGroup = $(' .small-table tbody', oGroups);
						oOptionGroup = $('#combo_group'),
						oResponseByMeter = sensus.commons.modules.content.group.fnFetchGroupByMeter(sId),
						aGroupsByMeter = sensus.commons.modules.content.group.fnGetListGroups(oResponseByMeter),
						oResponseAllGroup = sensus.commons.modules.content.group.fnFetchAllGroup(),
						aAllGroups = sensus.commons.modules.content.group.fnGetListAllGroups(oResponseAllGroup),
						iGroupByMeterSize = 0,
						iAllGroupsSize = 0,
						oBlankslateGroup = $('#blankslate-group'),
						oTableGroup = $(' .small-table',oGroups);

						if(aGroupsByMeter){
							iGroupByMeterSize = aGroupsByMeter.length;
						}

						if(aAllGroups){
							iAllGroupsSize = aAllGroups.length;
						}

						for( ; i < iAllGroupsSize; i = i + 1){

							var sGroupName = aAllGroups[i].name,
								sGroupId = aAllGroups[i].id;

							sOptionGroupHtml +='<option value="'+sGroupId+'">';
							sOptionGroupHtml += sGroupName;
							sOptionGroupHtml +='</coption>';
						}

						i = 0;
						oOptionGroup.empty().append(sOptionGroupHtml);

						for( ; i < iGroupByMeterSize; i = i + 1){

							var sGroupName = aGroupsByMeter[i].name,
								sGroupId = aGroupsByMeter[i].id,
								iDevicesSize = aGroupsByMeter[i].devicesCount;

							sTableGroupHtml +='<tr>';
							sTableGroupHtml +='<td class="remove-group" id="removeGroup"><a href="#"  id="'+ sGroupId +'">'+ sensus.locale.get("commons.pages.remove") +'</a></td>';
							sTableGroupHtml +='<td class="groupName" >' + sGroupName + '</td>';
							sTableGroupHtml +='<td class="value">' + $.convertionNumber(iDevicesSize,false,0).dvalueConverter + '</td>';
							sTableGroupHtml +='</tr>';

							sOptionGroupHtml +='<option value="'+sGroupId+'">';
							sOptionGroupHtml += sGroupName;
							sOptionGroupHtml +='</coption>';
						}

						oTbodyGroup.empty().append(sTableGroupHtml);

						/** checks if there are groups */
						if(iGroupByMeterSize == 0){
							oBlankslateGroup.show();
							oTableGroup.hide();
						} else {
							oBlankslateGroup.hide();
							oTableGroup.show();
						}
				},

				/**
				 * Validate Group
				 * @param  - [String] sGroupName Name of group
				 * @return - [Boolean] return True or False
				 */
				fnValidateGroup: function(sGroupName){

					var bHasGroup = false,
						oComboGroupInput = $('#combo_group_input'),
						oComboGroupOption = $('#combo_group option'),
						sShowPrompt = 'showPrompt';

					/**
					* Checks if the group exists
					*/
					oComboGroupOption.each(function() {

						if ($(this).text() == sGroupName) {
							sensus.commons.modules.content.group.idGroup = $(this).val();
							bHasGroup = true;
							return false;

						} else {
							sensus.commons.modules.content.group.idGroup = '';
							bHasGroup = false;

						}

					});

					if (!bHasGroup) {

						/** Show error in case of group not exists */
						oComboGroupInput.validationEngine(sShowPrompt, sensus.locale.get('commons.pages.selectGroup'), 'red', '', true);

					} else if ( sGroupName != '' &&  sGroupName != sensus.locale.get('smartpointdetail.tabs.about.selectGroup')) {

						if ((sGroupName.length <= 100) && (sGroupName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) != null)) {

							/** Return success */
							return true;

						} else if (sGroupName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) {

							/** Show error in case of invalid character */
							oComboGroupInput.validationEngine(sShowPrompt, sensus.locale.get('commons.pages.characterInvalid'), 'red', '', true);

							return false;

						} else {

							/** Show error in case of limit character */
							oComboGroupInput.validationEngine(sShowPrompt, sensus.locale.get('commons.pages.max.characterJs','100'), 'red', '', true);

							return false;
						}

					} else {

						/** Show error in case of not select a group */
						oComboGroupInput.validationEngine(sShowPrompt, sensus.locale.get('groupcreate.form.name.required'), 'red', '', true);

						return false;

					}
				},
				init : function() {

					/** Starts Buttons */
					var oSubmitShortForm = $('.submit-short-form');
					oSubmitShortForm.button();

					/** Starts Combobox */
					var oOcombobox = $('.combobox');
					oOcombobox.combobox();

					/** Hide Add device to group */
					var oDivGroups = $('#div-groups');
					oDivGroups.hide();

					var sDeviceId = $.address.parameter('id'),
						oAddGroups = $('#add-groups'),
						oBlankslateGroup = $('#blankslate-group'),
						oComboGroupInput = $("#combo_group_input"),
						oGroups = $('#groups'),
						oGroupSubmit = $('#group-submit'),
						//oComboGroup = $('#combo_group'),
						sMessagingSmartpointDetail = 'messaging-smartpoint-detail',
						oCancelEdit = $('.cancel-edit', oGroups),
						oRemoveGroup = '#removeGroup a';

					if(sDeviceId){
						sensus.commons.modules.content.group.fnLoadGroups(sDeviceId);
					};

					/**
					* Show Input Add device to group
					*/
					oAddGroups.click(function(e) {
						e.preventDefault();
						oBlankslateGroup.hide();
						oComboGroupInput.val(sensus.locale.get('smartpointdetail.tabs.about.selectGroup'));
						oDivGroups.show();
					});

					/**
					* Hide Input Add device to group
					*/
					oCancelEdit.click(function() {

						var sContainer = $(this).closest("div").attr("id");

						$("#" + sContainer + " .read-only").show();
						$("#" + sContainer + " .edit-only").hide();

						oDivGroups.hide();

						if (!$('.small-table tr', oGroups).length) {
							$(oBlankslateGroup, oGroups).show();
						}

						return false;
					});

					/**
					* Insert Group from Meter
					*/
					oGroupSubmit.click(function(e) {

						e.preventDefault();

						var sGroupName = oComboGroupInput.val();

						/**
						* Validate Group
						*/
						if(sensus.commons.modules.content.group.fnValidateGroup(sGroupName)){

							var sGroupId = sensus.commons.modules.content.group.idGroup;

							oResponse = sensus.commons.modules.content.group.fnAddGroup(sGroupName, sDeviceId, sGroupId);

							if (oResponse) {

								if(oResponse.operationSuccess){
									/** Show message Sucess */
									sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("smartpointdetail.tabs.about.sucessAddToGroup", sGroupName), "confirm");
									oDivGroups.hide();
									sensus.commons.modules.content.group.fnLoadGroups(sDeviceId);


								} else {

									/** Show message Error */
									sensus.util.page.showMessage(sMessagingSmartpointDetail, oResponse.messageList[0].text, "error");

								}
							}
						}
					});


					/**  Remove Group from Meter */
					oGroups.delegate(oRemoveGroup, "click", function(e) {

						e.preventDefault();

						var oRemove = $(this);
						sensus.commons.modules.content.group.idGroup = oRemove.attr("id");
						sensus.commons.modules.content.group.sNameGroup = oRemove.parent().next().text();
						sensus.commons.modules.content.group.idDevice = sDeviceId;

						sensus.util.actiondialog.launchActionDialog('deleteGroup', sensus.commons.modules.content.group.dialogDeleteGroup);
					});


				}
			},

			tag : {
				dialogDeleteTag:{

					/**
					 * The dialog title.
					 */
					title : sensus.locale.get("commons.pages.removeConfirm"),

					/**
					 * Whether this dialog requires a smartpoint list.
					 */

					/**
					 * The dialog width.
					 */
					width : 300,
					/**
					 * The dialog buttons (submit and cancel).
					 */
					buttons : (function() {
						var buttons = {};
						//SmartPointCount;
						buttons[sensus.locale.get("commons.pages.remove")] = function() {

							var actionDialogId = $("#action-dialog"),
								sTagId = sensus.commons.modules.content.tag.idTag,
								sDeviceId = sensus.commons.modules.content.tag.idDevice,
								oResponse = sensus.commons.modules.content.tag.fnRemoveTag(sTagId, sDeviceId);

							if(oResponse){

								if(oResponse.operationSuccess){
									sensus.commons.modules.content.tag.fnLoadTags(sDeviceId);
								}

							}

							$(this).dialog('close');

							actionDialogId.dialog('close');
						};
						buttons[sensus.locale.get("commons.pages.cancel")] = function() {
							var actionDialogId = $("#action-dialog");
							actionDialogId.dialog('close');
						};
						return buttons;
					})(),
					/**
					 * The function that will be called when the action dialog is launched.
					 *
					 * @param actionDialog
					 *            a reference to the actionDialog objext
					 */
					action : function(actionDialog) {
						//var aIds = "";
						//var sIdUrl = "";
						var actionDialogId = $("#action-dialog");

						$('#action-dialog').text(sensus.locale.get("smartpointdetail.tabs.about.deleteTagPermanently",$('#meter-id').text(),sensus.commons.modules.content.tag.sNameTag));

						actionDialogId.dialog('open');
					}

				},

				/**
				 * Insert Tag by Device ID
				 * @param  - [String] sTagName Name of Tag
				 * 			 [String] sDeviceId ID of Device
				 *           [String] sTagId ID of group
				 * @return - [Object] oResponse response of the insert
				 */
				fnAddTag : function(sTagName, sDeviceId, sTagId) {

					var oResponse = null;

					if (!sTagId) {
						sTagId = "";
					}

					$.ajax({
						url : sensus.settings.addToTag,
						data : "tagId=" + sTagId + "&id=" +  sDeviceId + "&tagName="+ sTagName,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Remove Tag
				 * @param  - [String] sTagId ID of Group
				 * 			 [String] sDeviceId ID of Device
				 * @return - [Object] oResponse response of the remove
				 */
				fnRemoveTag : function(sTagId, sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.removeMeterFromTag,
						data : "id=" + sTagId +
						"&meterId=" + sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Ajax call to get the Response with tags by meter
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchTagByMeter : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchListTagMeter,
						data: 'id=' + sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Get list tags from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of tags
				 */
				fnGetListTags: function(oResponse){

					if (oResponse && oResponse.tags.length) {
						return oResponse.tags;
					}

					return null;
				},

				/**
				 * Ajax call to get the Response with all tags
				 *
				 * @return [Object] oResponse Object
				 *
				 */
				fnFetchAllTags : function() {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchListTag,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Get list tags from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of all tags
				 */
				fnGetListAllTags: function(oResponse){

					if (oResponse && oResponse.tags.length) {
						return oResponse.tags;
					}

					return null;
				},

				/**
				 * Load Tags and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadTags : function(sId){

					var i = 0,
						sLiTagHtml = '',
						sOptionTagHtml = '',
						oTags = $('#tags'),
						oUlTag = $('.tag-container', oTags);
						oOptionTag = $('#tag_add_select_smartpoint'),
						oResponseByMeter = sensus.commons.modules.content.tag.fnFetchTagByMeter(sId),
						aTagsByMeter = sensus.commons.modules.content.tag.fnGetListTags(oResponseByMeter),
						oResponseAllTags = sensus.commons.modules.content.tag.fnFetchAllTags(),
						aAllTags = sensus.commons.modules.content.tag.fnGetListAllTags(oResponseAllTags),
						iTagByMeterSize = 0,
						iAllTagsSize = 0,
						oBlankslateTag = $('#blankslate-tags'),
						oTableTag = $(' .small-table',oTags);

						if(aTagsByMeter){
							iTagByMeterSize = aTagsByMeter.length;
						}

						if(aAllTags){
							iAllTagsSize = aAllTags.length;
						}


						for( ; i < iAllTagsSize; i = i + 1){

							var sTagName = aAllTags[i].name,
								sTagId = aAllTags[i].id;

							sOptionTagHtml +='<option value="'+sTagId+'">';
							sOptionTagHtml += sTagName;
							sOptionTagHtml +='</coption>';
						}

						i = 0;
						oOptionTag.empty().append(sOptionTagHtml);

						for( ; i < iTagByMeterSize; i = i + 1){

							var sTagName = aTagsByMeter[i].name,
								sTagId = aTagsByMeter[i].id,
								iDevicesSize = aTagsByMeter[i].devicesCount;

							sLiTagHtml +='<li id="tag-id">';
							sLiTagHtml +='<a href="#" id="' + sTagId + '" class="remove-tag icon-small"></a>';
							sLiTagHtml +='<span class="title">' + sTagName + '</span>';
							sLiTagHtml +='<span class="count">' + iDevicesSize + '</span>';
							sLiTagHtml +='</li>';

							sOptionTagHtml +='<option value="'+sTagId+'">';
							sOptionTagHtml += sTagName;
							sOptionTagHtml +='</coption>';
						}

						oUlTag.empty().append(sLiTagHtml);

						/** checks if there are groups */
						if(iTagByMeterSize == 0){
							oBlankslateTag.show();
							oTableTag.hide();
						} else {
							oBlankslateTag.hide();
							oTableTag.show();
						}
				},

				/**
				 * Validate Tag
				 * @param  - [String] sTagName Name of tag
				 * @return - [Boolean] return True or False
				 */
				fnValidateTag: function(sTagName){

					//var bHasTag = false,
					var	oComboTagInput = $('#tag_add_select_smartpoint_input');
					//	sShowPrompt = 'showPrompt';

					/** sensus.commons.modules.content.tag.idTag */
					var checkTag = function(sValue){

						var oComboTag = $('#tag_add_select_smartpoint');

						oComboTag.children("option").each(function() {

							if (sValue  === $(this).text()) {
								sensus.commons.modules.content.tag.idTag = $(this).val();
								return false;
							} else {
								status = true;
								sensus.commons.modules.content.tag.idTag = '';

							}
						});
						return true;
					};

					if ( sTagName != '') {

						if ((sTagName.length <= 100) && (sTagName != sensus.locale.get('smartpointdetail.tabs.about.selectTag')) && ((sTagName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) != null) && (sTagName.replace(/\s/g,'').length != 0))) {

							if (checkTag(sTagName)) {
								return true;
							}


						} else if (sTagName === sensus.locale.get('smartpointdetail.tabs.about.selectTag')) {

							oComboTagInput.validationEngine('showPrompt', sensus.locale.get('tag.page.error.invalidTagName'), 'red', '', true);
							return false;

						} else if ((sTagName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) || (sTagName.replace(/\s/g,'').length == 0)) {

							/** Show error in case of invalid character */
							oComboTagInput.validationEngine('showPrompt', sensus.locale.get('commons.pages.characterInvalid'), 'red', '', true);
							return false;

						} else {

							/** Show error in case of limit character */
							oComboTagInput.validationEngine('showPrompt', sensus.locale.get('commons.pages.max.characterJs','100'), 'red', '', true);
							return false;

						}

					} else {

						/** Show error in case of not enter a tag */
						oComboTagInput.validationEngine('showPrompt', sensus.locale.get('tag.page.error.required'), 'red', '', true);
						return false;

					}

				},

				init : function() {

					/** Starts Buttons */
					var oSubmitShortForm = $('.submit-short-form');
					oSubmitShortForm.button();

					/** Starts Combobox */
					var oOcombobox = $('.combobox');
					oOcombobox.combobox();

					/** Hide Add device to tag */
					var oDivTags = $('#div-tags');
					oDivTags.hide();

					var sDeviceId = $.address.parameter('id'),
						oAddTags = $('#add-tags'),
						oBlankslateTag = $('#blankslate-tags'),
						oComboTagInput = $("#tag_add_select_smartpoint_input"),
						oTags = $('#tags'),
						oCancelEdit = $('.cancel-edit', oTags),
						sMessagingSmartpointDetail = 'messaging-smartpoint-detail',
						oTagSubmit = $('#tag-submit'),
						sRemoveTag = '.remove-tag';

					if(sDeviceId){
						sensus.commons.modules.content.tag.fnLoadTags(sDeviceId);
					};

					/**
					* Show Input Add device to tag
					*/
					oAddTags.click(function(e) {
						e.preventDefault();
						oBlankslateTag.hide();
						oComboTagInput.val(sensus.locale.get('smartpointdetail.tabs.about.selectTag'));
						oDivTags.show();
					});

					/**
					* Hide Input Add device to tag
					*/
					oCancelEdit.click(function() {

						var sContainer = $(this).closest("div").attr("id");

						$("#" + sContainer + " .read-only").show();
						$("#" + sContainer + " .edit-only").hide();

						oDivTags.hide();

						if (!$('.tag-container li', oTags).length) {
							$(oBlankslateTag, oTags).show();
						}

						return false;
					});


					/**
					* Insert Tag from Meter
					*/
					oTagSubmit.click(function(e) {

						e.preventDefault();

						var sTagName = oComboTagInput.val();

						/**
						* Validate tag
						*/
						if(sensus.commons.modules.content.tag.fnValidateTag(sTagName)){

							var sTagId = sensus.commons.modules.content.tag.idTag;

							oResponse = sensus.commons.modules.content.tag.fnAddTag(sTagName, sDeviceId, sTagId);


							if (oResponse) {

								$('#tag_add_select_smartpoint_input').validationEngine('hide');

								if(oResponse.operationSuccess){
									/** Show message Sucess */
									sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("smartpointdetail.tabs.about.sucessAddToTag", sTagName), "confirm");
									var oDivTags = $('#div-tags');
									oDivTags.hide();
									sensus.commons.modules.content.tag.fnLoadTags(sDeviceId);

								} else {

									if($.isArray(oResponse.messageList)){
										/** Show message Error */
										sensus.util.page.showMessage(sMessagingSmartpointDetail, oResponse.messageList[0].text, "error");
									} else {
										sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("commons.pages.error"), "error");
									}

								}
							}
						}
					});

					/**  Remove tag from Meter */
					oTags.delegate(sRemoveTag, "click", function(e) {

						e.preventDefault();

						var oRemove = $(this);
						sensus.commons.modules.content.tag.idTag = oRemove.attr("id");
						sensus.commons.modules.content.tag.sNameTag = oRemove.parent().find('.title').text();
						sensus.commons.modules.content.tag.idDevice = sDeviceId;

						sensus.util.actiondialog.launchActionDialog('deleteTag', sensus.commons.modules.content.tag.dialogDeleteTag);
					});


				}
			},

			postNote : {
				/**
				 * Insert Post Note by Device ID
				 * @param  - [String] sPostNoteName Name of Post Note
				 * 			 [String] sDeviceId ID of Device
				 * @return - [Object] oResponse response of the insert
				 */
				fnAddPostNote : function(sPostNoteName, sDeviceId) {

					var oResponse = null;


					if (sPostNoteName) {
						sNotes = escape(sPostNoteName);
					}

					$.ajax({
						url : sensus.settings.postNote,
						data : "note=" + sNotes + "&id=" + sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Remove Post Note
				 * @param  - [String] sPostNoteId ID of Post None
				 * @return - [Object] oResponse response of the remove
				 */
				fnRemovePostNote : function(sPostNoteId,sFlexNetId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.postNoteDelete,
						data : "noteId=" + sPostNoteId+"&flexnetidPost="+sFlexNetId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Ajax call to get the Response with Posts Note by meter
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchPostNote : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.commons.modules.util.ajaxData.fetchDeviceById().url,
						data: sensus.commons.modules.util.ajaxData.fetchDeviceById().data,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Load Post Note and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadPostNote : function(sId){

					var i = 0,
						sPostNoteHtml = '',
						oPostNote = $('#notes'),
						oDlPostNote = $('dl', oPostNote);
						oResponse = sensus.commons.modules.content.postNote.fnFetchPostNote(sId),
						oMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse),
						aPostNote = [],
						iPostNote = 0,
						oBlankslatePostNote = $('#blankslate-note');

						if(oMeter && oMeter.notes){
							aPostNote = oMeter.notes;
							iPostNote = aPostNote.length;
						}

						for( ; i < iPostNote; i = i + 1) {


							var sCreateUser = aPostNote[i].createUser,
								sIdPostNote = aPostNote[i].id,
								sCreateDate = aPostNote[i].createDate,
								sText = aPostNote[i].text;

							if (sText) {
								sText = unescape(sText);
							}

							sPostNoteHtml += '<div>';
							sPostNoteHtml += '<dt class="note">';
							sPostNoteHtml += '<span class="icon">N</span>';
							sPostNoteHtml += '<span class="what start rounded">'+sensus.locale.get('smartpointdetail.tabs.about.note')+'</span>';
							sPostNoteHtml += '<span class="who">'+sCreateUser+'</span> &raquo; ';
							sPostNoteHtml += '<span class="when">'+$.date.dateFormat(sCreateDate, " h:i A "+sensus.settings.dateFormatMask)+'</span>';
							sPostNoteHtml += '<small> | <a href="#" id="'+sIdPostNote+'" class="text-button delete-dialog" title="Delete Note for Meter">'+sensus.locale.get('commons.pages.delete')+'</a></small>';
							sPostNoteHtml += '</dt>';
							sPostNoteHtml += '<dd class="description">';
							sPostNoteHtml += '<p>'+sText+'</p>';
							sPostNoteHtml += '</dd>';
							sPostNoteHtml += '<div>';
						}

						oDlPostNote.empty().append(sPostNoteHtml);

						/** checks if there are Pos */
						if(iPostNote == 0){
							oBlankslatePostNote.show();
							oDlPostNote.hide();
						} else {
							oBlankslatePostNote.hide();
							oDlPostNote.show();
						}
				},

				/**
				 * Validate Post Note
				 * @param  - [String] sPostNoteName Name of Post Note
				 * @return - [Boolean] return True or False
				 */
				fnValidatePostNote: function(sPostNoteName){

					var sMessagingSmartpoint = 'messaging-smartpoint-detail';



					if (sPostNoteName.length > 0) {

					    if (sPostNoteName.length < 1001 || sPostNoteName.length > 0) {

					    	return true;

						} else {
							/** Show error in case of limit character */
							sensus.util.page.showMessage(sMessagingSmartpoint, sensus.locale.get('commons.pages.max.characterJs','1000'), "error");
							return false;
						}

					} else {

						/** Show error in case of field empty */
						sensus.util.page.showMessage(sMessagingSmartpoint, sensus.locale.get('smartpointdetail.tabs.about.noteEmpty'), "error");
						return false;

					}
				},

				init : function() {

					/** Starts Buttons */
					var oNoteSubmit = $('#note-submit');
					oNoteSubmit.button();

					var sDeviceId                   = $.address.parameter('id'),
						oNotes                      = $('#notes'),
						oNewNote                    = $('#new-note'),
						sMessagingSmartpointDetail  = 'messaging-smartpoint-detail',
						sDeleteNotes                = '.delete-dialog';


					if(sDeviceId){
						sensus.commons.modules.content.postNote.fnLoadPostNote(sDeviceId);
					}

					/**
					 * Insert Note from Meter
					 */
					oNoteSubmit.click(function(e) {
						e.preventDefault();


						var sPostNoteName = oNewNote.attr("value");

						if (sensus.commons.modules.content.postNote.fnValidatePostNote(sPostNoteName)) {

							if (sPostNoteName.length < 1001) {
								oResponse = sensus.commons.modules.content.postNote.fnAddPostNote(sPostNoteName, sDeviceId);

								if (oResponse) {

									if(oResponse.operationSuccess){
										/** Show message Sucess */
										sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("smartpointdetail.tabs.about.sucessAddToNote", sPostNoteName), "confirm");
										oNewNote.val('');
										sensus.commons.modules.content.postNote.fnLoadPostNote(sDeviceId);

									} else {

										/** Show message Error */
										sensus.util.page.showMessage(sMessagingSmartpointDetail, oResponse.messageList[0].text, "error");

									}
								}
							} else {

								/** Show message Error of max length */
								sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("smartpointdetail.tabs.about.postNote.maxLength"), "error");

							}

						}


					});


					/**  Remove Post Note from Meter */
					oNotes.delegate(sDeleteNotes, "click", function(e) {

						e.preventDefault();

						sensus.util.page.startProgressBar();

						var sNoteId = $(this).attr('id'),
						    sFlexNetId = $.address.parameter('id'),
							oResponse = sensus.commons.modules.content.postNote.fnRemovePostNote(sNoteId,sFlexNetId);

						if(oResponse){


							if(oResponse.operationSuccess){
								/** Show message Sucess */
								sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("smartpointdetail.tabs.about.sucessDeletedNote"), "confirm");
								sensus.commons.modules.content.postNote.fnLoadPostNote(sDeviceId);
								sensus.util.page.stopProgressBar(0);
							} else {
								sensus.util.page.showMessage(sMessagingSmartpointDetail, sensus.locale.get("commons.pages.error"), "error");
								sensus.util.page.stopProgressBar(0);
							}
						}


					});


				}

			},

			location : {

				/**
				 * Ajax call to get the Response with Device Information
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchDeviceInformation : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.commons.modules.util.ajaxData.fetchDeviceById().url,
						data : sensus.commons.modules.util.ajaxData.fetchDeviceById().data,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Load location displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadlocation : function(){

					var	oAddressVal = $('#address-val'),
						oLatVal = $('#lat-val'),
						oLongitude = $('#long-val'),
						oTimeZone = $('#timeZone'),
						oResponse = sensus.commons.modules.content.location.fnFetchDeviceInformation(),
						oMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse),
						sAddress = "",
						sCity = "",
						sState = "",
						sZipCode = "",
						sLatitude = "",
						sLongitude = "",
						sTimeZone = "";

					if (oMeter) {

						if (oMeter.address === null) {
							sAddress = "";
						} else {
							sAddress = oMeter.address;
						}

						if (oMeter.city === null) {
							sCity = "";
						} else {
							sCity = oMeter.city;
						}

						if (oMeter.state === null) {
							sState = "";
						} else {
							sState = oMeter.state;
						}

						if (oMeter.zip === null) {
							sZipCode = "";
						} else {
							sZipCode = oMeter.zip;
						}

						sLatitude = oMeter.latitude;
						sLongitude = oMeter.longitude;
						sTimeZone = oMeter.timeZoneInfo ? oMeter.timeZoneInfo.displayName : "";

						if ((oMeter.address === null) && (oMeter.city === null) && (oMeter.state === null) && (oMeter.zip === null)) {
							oAddressVal.text("");
						} else {
							oAddressVal.text(sAddress+' '+sCity+' '+sState+' '+sZipCode);
						}

						oLatVal.text(sLatitude);
						oLongitude.text(sLongitude);
						oTimeZone.text(sTimeZone);

						var objLatLon = [{
							latitude:sLatitude,
							longitude: sLongitude
						}];

						sensus.util.mapopen.mapIt('smallMap', objLatLon, 17, '440', '140');
					}

				},

				init : function() {

					sensus.commons.modules.content.location.fnLoadlocation();

				}

			},

			deviceInformation : {

				/**
				 * Ajax call to get the Response with Device Information
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchDeviceInformation : function(sDeviceId) {
					var oResponse = null;

					$.ajax({
						url : sensus.commons.modules.util.ajaxData.fetchDeviceById().url,
						data : sensus.commons.modules.util.ajaxData.fetchDeviceById().data,
						cache : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},

				/**
				 * Load location displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadDeviceInformation : function(){

					var	sTableHtml = '',
						oTable = $('#device-information table tbody'),
						oResponse = sensus.commons.modules.content.location.fnFetchDeviceInformation(),
						aMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
					if(aMeter){
						var sMeterType = '',
							sDeviceType = '',
							sManufacture = sensus.commons.modules.util.fnValidateField(aMeter.manufacture),
							sMeterFirmware = sensus.commons.modules.util.fnValidateField(aMeter.firmwareFlexnet),
							sModelNumber = sensus.commons.modules.util.fnValidateField(aMeter.model),
							sNetworkAddress = '',
							sPremiseId = sensus.commons.modules.util.fnValidateField(aMeter.premiseId),
							sInstallCode = sensus.commons.modules.util.fnValidateField(aMeter.installCode),
							sIpAddress = '',
							sMeterState = sensus.commons.modules.util.fnValidateField(aMeter.state),
							sInstalledDate = sensus.commons.modules.util.fnValidateField($.date.dateFormat(aMeter.installDate, sensus.settings.dateFormatMask)),
							sTimeZone = sensus.commons.modules.util.fnValidateField(aMeter.timeZoneInfo.displayName),
							sEncryption = sensus.commons.modules.util.fnValidateField(aMeter.encrypted),
							sConnectStatus = sensus.commons.modules.util.fnValidateField(aMeter.connectionStatus);

						if (aMeter.deviceTypeEnum == "METER") {
							sNetworkAddress = aMeter.flexNetId;
						} else if (aMeter.deviceTypeEnum == "HAN_DEVICE") {
							sNetworkAddress = aMeter.macAddress;
						}

						if (aMeter.deviceType){
							sMeterType = sensus.commons.modules.util.fnValidateField(aMeter.deviceType.description);
							sDeviceType = sensus.locale.get("commons.pages."+aMeter.deviceType.type.toUpperCase());
						}

						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.meterType")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sMeterType;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}

						if(sDeviceType == sensus.locale.get("commons.pages.LCM") || sDeviceType == sensus.locale.get("commons.pages.THERMOSTAT") || sDeviceType == sensus.locale.get("commons.pages.IHD")){


							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.deviceType")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sDeviceType;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';

							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.dialog.deviceInformation.manufacture");
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sManufacture;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';

							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.dialog.deviceInformation.modelNumber");
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sModelNumber;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';

						}

						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.meterFirmWare")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sMeterFirmware;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}

						sTableHtml += '<tr>';
						sTableHtml += '<td>';
						sTableHtml += sensus.locale.get("commons.pages.networkAddress")+':';
						sTableHtml += '</td>';
						sTableHtml += '<td>';
						sTableHtml += sNetworkAddress;
						sTableHtml += '</td>';
						sTableHtml += '</tr>';

						sTableHtml += '<tr>';
						sTableHtml += '<td>';
						sTableHtml += sensus.locale.get("commons.pages.premiseId")+':';
						sTableHtml += '</td>';
						sTableHtml += '<td>';
						sTableHtml += sPremiseId;
						sTableHtml += '</td>';
						sTableHtml += '</tr>';


						if(sDeviceType == sensus.locale.get("commons.pages.LCM") || sDeviceType == sensus.locale.get("commons.pages.THERMOSTAT") || sDeviceType == sensus.locale.get("commons.pages.IHD")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.dialog.deviceInformation.installCode");
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sInstallCode;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}

						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("commons.pages.ipAddress")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sIpAddress;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}


						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.state")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sMeterState;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}


						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.installedDate")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sInstalledDate;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}


						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.timeZone")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sTimeZone;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}


						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("smartpointdetail.tabs.about.encryption")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sEncryption;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}

						if(sDeviceType == sensus.locale.get("commons.pages.METER")){
							sTableHtml += '<tr>';
							sTableHtml += '<td>';
							sTableHtml += sensus.locale.get("commons.pages.connectStatus")+':';
							sTableHtml += '</td>';
							sTableHtml += '<td>';
							sTableHtml += sConnectStatus;
							sTableHtml += '</td>';
							sTableHtml += '</tr>';
						}

						oTable.empty().append(sTableHtml);
					}

				},

				init : function() {
					var sDeviceId = $.address.parameter('id');

					if(sDeviceId){
						sensus.commons.modules.content.deviceInformation.fnLoadDeviceInformation(sDeviceId);
					}

				}

			},

			scheduledEvents : {

				/**
				 * Ajax call to get the Response with Schedules
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchScheduleByDevice : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.getScheduleByDevice,
						data: 'id=' + sDeviceId,
						async : false,
						cache : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;

				},
				/**
				 * Get list schedules from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of tags
				 */
				fnGetListSchedules: function(oResponse){

					if (oResponse && oResponse.schedules && oResponse.schedules.length) {
						return oResponse.schedules;
					}

					return null;
				},

				/**
				 * Load Schedules displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadSchedules : function(sId){

					var	i = 0,
						oResponse = sensus.commons.modules.content.scheduledEvents.fnFetchScheduleByDevice(sId),
						aSchedules = sensus.commons.modules.content.scheduledEvents.fnGetListSchedules(oResponse),
						iSchedulesSize = 0,
						oTable = $('#scheduled-events'),
						oTbody = oTable.find('tbody'),
						sTableHtml = '',
						oBlankslate = $('#blankslate-scheduled'),
						fnFormatText = function(sText){
							if(sText.length > 45) {
								var sFormat = sText.substring(0,45);
								return sFormat + ' ...';
							} else {

								return sText;
							}
						};

						if(aSchedules){
							iSchedulesSize = aSchedules.length;
						}

						if(iSchedulesSize == 0){
							oTable.hide();
							oBlankslate.show();

						}else{

							for( ; i < iSchedulesSize; i = i + 1){

								var sId                 = aSchedules[i].id,
									sScheduleName       = aSchedules[i].name,
									sActionName         = aSchedules[i].actionView.actionType.description,
									sStartTime          = aSchedules[i].startTime,
									sFrequencyTypeEnum  = sensus.locale.get("commons.pages."+aSchedules[i].frequency.frequencyTypeEnum);

								sTableHtml += '<tr class="">';
								sTableHtml += '<td class="scheduled-id" ><a class="request-readonly-event"><strong>' + fnFormatText(sScheduleName) + '<span class="hide">' + sId + '</span>' + '</strong></a></td>';
								sTableHtml += '<td>'+sensus.locale.get(sActionName)+'</td>';
								sTableHtml += '<td class="date-scheduled" >'+sStartTime+'</td>';
								sTableHtml += '<td>'+sFrequencyTypeEnum+'</td> ';
								sTableHtml += '</tr>';
							}

							oTbody.empty().append(sTableHtml);
						}
				},

				init : function() {

					var sDeviceId  = $.address.parameter('id'),
						oSchedule  = $('#scheduled'),
						sHref      = '.request-readonly-event';

					if(sDeviceId){
						sensus.commons.modules.content.scheduledEvents.fnLoadSchedules(sDeviceId);
					}

					/**  Open Dialog Schedule*/
					oSchedule.delegate(sHref, "click", function(e) {
						e.preventDefault();
				        var sId = $(this).find("span").text();
				        sensus.pages.smartpointdetail.scheduleId = sId;
				        sensus.util.actiondialog.launchActionDialog("openEventScheduled", sensus.pages.smartpointdetail.dialogSettings["openEventScheduled"]);
					});

				}

			},

			lcmRelay : {


				/**
				 * Get list LCM from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of groups
				 */
				fnGetListLcm: function(oResponse){
					if (oResponse && oResponse.firstHanDevice && oResponse.firstHanDevice.lcmRelays) {
						return oResponse.firstHanDevice.lcmRelays;
					}

					return null;
				},

				/**
				 * Ajax call to get the Response with LCM Realy by Device
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchLcmRelay : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchLcmRelay,
						data: 'id=' + sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});
					return oResponse;
				},

				/**
				 * Load Lcm Relay and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadLcmRelay : function(sId){

					var i = 0,
						oResponse = sensus.commons.modules.content.lcmRelay.fnFetchLcmRelay(sId),
						aLcm = sensus.commons.modules.content.lcmRelay.fnGetListLcm(oResponse),
						oTable = $('#lcm-relay'),
						oTbody = $('tbody', oTable),
						oBlankslate = $('#blankslate-lcm-relay'),
						iLcmSize = 0,
						sTbodyHtml = '';

					if(aLcm){

						iLcmSize = aLcm.length;
					}

					if(iLcmSize == 0){
						oTable.hide();
						oBlankslate.show();

					} else {

						for( ; i < iLcmSize; i = i + 1){


							var iRelay = aLcm[i].relay,
								sAmp = aLcm[i].AMP,
								intendedUse = aLcm[i].intendedUse,
								bUsed = aLcm[i].used,
								sUsed = sensus.locale.get("commons.pages.yes");


							if(!bUsed){
								sUsed = sensus.locale.get("commons.pages.no");
							}

							sTbodyHtml += '<tr class="">';
							sTbodyHtml += '<td>'+iRelay+'</td>';
							sTbodyHtml += '<td>'+sAmp+' <small>'+sensus.locale.get("smartpointdetail.tabs.about.amp")+'</small></td>';
							sTbodyHtml += '<td>'+intendedUse+'</td>';
							sTbodyHtml += '<td>'+sUsed+'</td> ';
							sTbodyHtml += '</tr>';
						}

						oTbody.empty().append(sTbodyHtml);

					}

				},

				init : function() {

					var sDeviceId = $.address.parameter('id');

					sensus.commons.modules.content.lcmRelay.fnLoadLcmRelay(sDeviceId);

				}
			},

			demandResponseProgramParticipation : {


				/**
				 * Ajax call to get the Response with Demand Response Program Participation by Device
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchDemandResponseProgramParticipation : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchDemandResponseProgramParticipation,
						data: 'id=' + sDeviceId,
						cache : false,
						success : function(response) {
							oResponse = response;
						}
					});
					return oResponse;
				},

				/**
				 * Get list Demand Response Program Participation from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of Demand Response Setup
				 */
				fnGetListDemandResponseProgramParticipation: function(oResponse){
					if (oResponse && oResponse.actions && oResponse.actions.length) {
						return oResponse.actions;
					}

					return null;
				},

				/**
				 * Load Demand Response Program Participation and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadDemandResponseProgramParticipation : function(sId){

					var i = 0,
						oResponse = sensus.commons.modules.content.demandResponseProgramParticipation.fnFetchDemandResponseProgramParticipation(sId);
						aDemandResponseProgramParticipation = sensus.commons.modules.content.demandResponseProgramParticipation.fnGetListDemandResponseProgramParticipation(oResponse),
						oTable = $('#demand-response-program-participation'),
						oTbody = $('tbody', oTable),
						oBlankslate = $('#blankslate-demand-response-program-participation'),
						iDemandResponseProgramParticipation = 0,
						sTbodyHtml = '';

						if(aDemandResponseProgramParticipation){
							iDemandResponseProgramParticipation = aDemandResponseProgramParticipation.length;
						}

						if(iDemandResponseProgramParticipation == 0){
							oTable.hide();
							oBlankslate.show();

						} else {

							for( ; i < iDemandResponseProgramParticipation; i = i + 1){

								var sEventTitle = aDemandResponseProgramParticipation[i].eventTitle,
									sDateTime = aDemandResponseProgramParticipation[i].date,
									sStatus = sensus.locale.get("commons.pages."+aDemandResponseProgramParticipation[i].executionStatusEnum);

								sTbodyHtml += '<tr class="">';
								sTbodyHtml += '<td>'+sEventTitle+'</td>';
								sTbodyHtml += '<td>'+sDateTime+'</td>';
								sTbodyHtml += '<td>'+sStatus+'</td>';
								sTbodyHtml += '</tr>';
							}

							oTbody.empty().append(sTbodyHtml);

						}

				},

				init : function() {

					var sDeviceId = $.address.parameter('id'),
						oTable = $('#device-demand-response-program-participation'),
						sRequestSummary = '.request-summary-expanded';

					if(sDeviceId){
						sensus.commons.modules.content.demandResponseProgramParticipation.fnLoadDemandResponseProgramParticipation(sDeviceId);
					}

					/**  Request Summary */
					oTable.delegate(sRequestSummary, "click", function(e) {

						e.preventDefault();
					});

				}
			},

			demandResponseSetup: {


				/**
				 * Ajax call to get the Response with Demand Response Setup by Device
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchDemandResponseSetup : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchDemandResponseSetup,
						data: 'id=' + sDeviceId,
						cache : false,
						success : function(response) {
							oResponse = response;
						}
					});
					return oResponse;
				},

				/**
				 * Get list Demand Response Setup from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of Demand Response Setup
				 */
				fnGetListDemandResponseSetup: function(oResponse){
					if (oResponse && oResponse.actions && oResponse.actions.length) {
						return oResponse.actions;
					}

					return null;
				},

				/**
				 * Load Demand Response Setup and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadDemandResponseSetup : function(sId){

					var i = 0,
						oResponse = sensus.commons.modules.content.demandResponseSetup.fnFetchDemandResponseSetup(sId);
						aDemandResponseSetup = sensus.commons.modules.content.demandResponseSetup.fnGetListDemandResponseSetup(oResponse),
						oTable = $('#demand-response-setup'),
						oTbody = $('tbody', oTable),
						oBlankslate = $('#blankslate-demand-response-setup'),
						iDemandResponseSetupSize = 0,
						sTbodyHtml = '';

						if(aDemandResponseSetup){
							iDemandResponseSetupSize = aDemandResponseSetup.length;
						}

						if(iDemandResponseSetupSize == 0){
							oTable.hide();
							oBlankslate.show();

						} else {

							for( ; i < iDemandResponseSetupSize; i = i + 1){

								var sEnrollmentCode = aDemandResponseSetup[i].enrollmentCode,
									sRandomizeStart = aDemandResponseSetup[i].startMinutes,
									sRandomizeEnd = aDemandResponseSetup[i].endMinutes,
									sLast = aDemandResponseSetup[i].lastDate;

								sTbodyHtml += '<tr class="">';
								sTbodyHtml += '<td>'+sEnrollmentCode+'</td>';
								sTbodyHtml += '<td>'+sRandomizeStart+'</td>';
								sTbodyHtml += '<td>'+sRandomizeEnd+'</td>';
								sTbodyHtml += '<td>'+sLast+'</td>';
								sTbodyHtml += '</tr>';
							}

							oTbody.empty().append(sTbodyHtml);

						}

				},

				init : function(){

					var sDeviceId = $.address.parameter('id'),
						oDemandResponseSetup = $('#device-demand-response-setup'),
						sEdit = 'edit';

					sensus.commons.modules.content.demandResponseSetup.fnLoadDemandResponseSetup(sDeviceId);

					/**  Demand Response Setup  */
					oDemandResponseSetup.delegate(sEdit, "click", function(e) {


						e.preventDefault();
					});

				}
			},

			hanDevices : {

				hanDeviceInformationDialog:{

					/**
					 * The dialog title.
					 */
					title : sensus.locale.get("smartpointdetail.dialog.deviceInformation.hanDeviceDetails"),


					/**
					 * The dialog width.
					 */
					width : 790,


					/**
					 * The function that will be called when the action dialog is launched.
					 *
					 * @param actionDialog
					 *            a reference to the actionDialog objext
					 */
					action : function(actionDialog) {
						var actionDialogId = $("#action-dialog");

						$('#action-dialog').load('commons/pages/controller/actions/han_device_information.jsp', function(){

							var idHanDevice = sensus.commons.modules.content.hanDevices.idHanDevice;

							$.ajax({
								url : 'smartpointdetail/include.fetchDeviceById.action',
								data : 'macAddress='+idHanDevice + "&deviceType=HAN_DEVICE",
								dataType : 'json',
								success : function (data) {

									var oHanDevice = sensus.commons.modules.util.fnGetFirstDeviceResponse(data);

										oDialogHanDevice = $('#action-han-detail'),
										oHeaderDeviceType = oDialogHanDevice.find('.field-header-device-type'),
										oReadOnly = oDialogHanDevice.find('.read-only'),
										oSmallFlexNetId = $('.field-flex-net-id span', oDialogHanDevice),
										oDeviceType = $('.field-device-type', oReadOnly),
										oManufacture = $('.field-manufacture', oReadOnly),
										oModelNumber = $('.field-model-number', oReadOnly),
										oMac = $('.field-mac', oReadOnly),
										oNetworkStatus = $('.field-network-status', oReadOnly),
										oLastStatus = $('.field-last-status', oReadOnly),
										oDateAdded = $('.field-date-added', oReadOnly),
										oViewDetail = $('a.text-button', oDialogHanDevice),
										sDeviceType = "",
										sManufacture = oHanDevice.manufacture,
										sModel = oHanDevice.model,
										sFlexNetId = oHanDevice.flexNetId,
										sMac = oHanDevice.macAddress,
										sStatus = sensus.locale.get("commons.pages."+oHanDevice.hanDeviceStatusEnum),
										sLastDateStatus = oHanDevice.lastDateStatus,
										sDateAdded = oHanDevice.lastDateStatus;


									if (oHanDevice.deviceType && oHanDevice.deviceType.type) {
										sDeviceType = sensus.locale.get("commons.pages." + oHanDevice.deviceType.type);
									}

									oViewDetail.attr('href', 'smartpointdetail/ajax.smartpoint.detail.main.action?id='+sFlexNetId + '&deviceType=' + oHanDevice.deviceType.type).addClass('alist').click(function(){
										actionDialogId.dialog('close');
									});

									oSmallFlexNetId.text('('+sMac+')');
									oHeaderDeviceType.text(sDeviceType);
									oDeviceType.text(sDeviceType);
									oManufacture.text(sManufacture);
									oModelNumber.text(sModel);
									oMac.text(sMac);
									oNetworkStatus.text(sStatus);
									oLastStatus.text($.date.dateFormat(sLastDateStatus, sensus.settings.dateFormatMask+" h:i A"));
									oDateAdded.text($.date.dateFormat(sDateAdded, sensus.settings.dateFormatMask));



								}
							});
						});

						actionDialogId.dialog('open');
					}

				},

				dialogDeleteHanDevices : {

					/**
					 * The dialog title.
					 */
					title : sensus.locale.get("commons.pages.removeConfirm"),

					/**
					 * The dialog width.
					 */
					width : 300,

					/**
					 * The dialog buttons (submit and cancel).
					 */
					buttons : (function() {
						var buttons = {};
						buttons[sensus.locale.get("commons.pages.remove")] = function() {

							sensus.util.page.startProgressBar();

							var actionDialogId = $("#action-dialog"),
								sMacAddress = sensus.commons.modules.content.hanDevices.idHanDevice,
								oData = "macAddress=" + sMacAddress;

							var fnCallback = function () {
								// Call modules controller component
								sensus.commons.modules.loadModules();
							};
							sensus.pages.longrunningprocess.monitor(sensus.settings.removeMeterFromHanDevice, oData, sensus.locale.get("smartpointdetail.tabs.about.sucessRemoveHan"), fnCallback);


							$(this).dialog('close');

							actionDialogId.dialog('close');
						};
						buttons[sensus.locale.get("commons.pages.cancel")] = function() {
							var actionDialogId = $("#action-dialog");
							actionDialogId.dialog('close');
						};
						return buttons;
					})(),
					/**
					 * The function that will be called when the action dialog is launched.
					 *
					 * @param actionDialog
					 *            a reference to the actionDialog objext
					 */
					action : function(actionDialog) {

						var actionDialogId = $("#action-dialog");

						actionDialogId.text(sensus.locale.get("smartpointdetail.tabs.about.removeHanDevice",sensus.commons.modules.content.hanDevices.sNameHanDevice, sensus.commons.modules.content.hanDevices.idDevice));

						actionDialogId.dialog('open');

					}

				},

				/**
				 * Ajax call to get the Response with HAN Devices by Device
				 *
				 * @return [Object] oResponse Object
				 *
				 * @param - [String] sDeviceId Id of Device
				 */
				fnFetchHanDevices : function(sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.searchAllHanDevicesByMeter,
						data: 'id=' + sDeviceId,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				/**
				 * Get list Han Devices from the Response Object
				 * @param  - [Object] oResponse object response
				 * @return - [Array] List of Han Devices
				 */
				fnGetListHanDevices: function(oResponse){
					if (oResponse && oResponse.hanDevices && oResponse.hanDevices.length) {
						return oResponse.hanDevices;
					}

					return null;
				},


				/**
				 * Load Han Devices and displays on screen
				 * @param  - [String] sId Id of Device
				 * @return - [void]
				 */
				fnLoadHanDevices : function(sId){

					var i = 0,
						oResponse = sensus.commons.modules.content.hanDevices.fnFetchHanDevices(sId),
						aHanDevice = sensus.commons.modules.content.hanDevices.fnGetListHanDevices(oResponse),
						oTable = $('#han-devices'),
						oTbody = $('tbody', oTable),
						oBlankslate = $('#blankslate-han-devices'),
						iHanDeviceSize = 0,
						sTbodyHtml = '';

						if(aHanDevice){
							iHanDeviceSize = aHanDevice.length;
						}

						if(iHanDeviceSize == 0){
							oTable.hide();
							oBlankslate.show();

						} else {

							for( ; i < iHanDeviceSize; i = i + 1){

								var sType = sensus.locale.get("commons.pages."+aHanDevice[i].hanDeviceTypeEnum),
									sFlexNetId = aHanDevice[i].clientEndPointId,
									sStatus = '',
									SLastDateStatus = aHanDevice[i].lastDateStatus,
									oRemoveHAN = '<td><a href="'+sFlexNetId+'" class="icn-delete icon-small remove-dialog" rel="Remove" title="'+sensus.locale.get("smartpointdetail.tabs.about.removeHanDevice", sType, sId)+'"></a></td>';

								if(aHanDevice[i].hanDeviceStatusEnum){
									sStatus = sensus.locale.get("commons.pages."+aHanDevice[i].hanDeviceStatusEnum);
								}

								if ((sStatus === "Disconnected") || (sStatus === "Unknown")) {
									oRemoveHAN = '<td></td>';
								}

								sTbodyHtml += '<tr class="">';
								sTbodyHtml += '<td class="hide macAddressId">'+aHanDevice[i].macAddress+'</td>';
								sTbodyHtml += '<td><a href="'+sFlexNetId+'" class="request-han-detail">'+sType+'</a></td>';
								sTbodyHtml += '<td>'+sFlexNetId+'</td> ';
								sTbodyHtml += '<td>'+sStatus+'</td>';
								sTbodyHtml += '<td>'+$.date.dateFormat(SLastDateStatus, sensus.settings.dateFormatMask)+'</td>';
								sTbodyHtml += oRemoveHAN;
								sTbodyHtml += '</tr>';
							}

							oTbody.empty().append(sTbodyHtml);

						}

				},

				/**
				 * Remove Han Device
				 * @param  - [String] sHanDeviceId ID of Group
				 * 			 [String] sDeviceId ID of Device
				 * @return - [Object] oResponse response of the remove
				 */
				fnRemovehanDevice : function(sMacAddress, sDeviceId) {

					var oResponse = null;

					$.ajax({
						url : sensus.settings.removeMeterFromHanDevice,
						data : "macAddress=" + sMacAddress,
						cache : false,
						async : false,
						success : function(response) {
							oResponse = response;
						}
					});

					return oResponse;
				},

				init : function(){

					var sDeviceId = $.address.parameter('id'),
						oDevice = $("#device-han-container"),
						oTable = $('#han-devices', oDevice),
						sHanDevice = '.han-join',
						sDeviceType = '.request-han-detail',
						sRemoveDialog = '.remove-dialog';


					if(sDeviceId){
						sensus.commons.modules.content.hanDevices.fnLoadHanDevices(sDeviceId);
					}

					/** Connect Device */
					oDevice.delegate(sHanDevice, "click", function(e) {
						e.preventDefault();
						sensus.util.actiondialog.launchActionDialog("connectHanDevice", sensus.commons.modules.dialogSettings["connectHanDevice"]);
					});

					/**  Device Detail  */
					oTable.delegate(sDeviceType, "click", function(e) {
						sensus.commons.modules.content.hanDevices.idHanDevice = $(this).parent().siblings(".macAddressId").text();
						sensus.util.actiondialog.launchActionDialog('deleteGroup', sensus.commons.modules.content.hanDevices.hanDeviceInformationDialog);
						e.preventDefault();
					});

					/**  Remove Device  */
					oTable.delegate(sRemoveDialog, "click", function(e) {
						e.preventDefault();

						sensus.commons.modules.content.hanDevices.idHanDevice = $(this).parent().siblings(".macAddressId").text();
						sensus.commons.modules.content.hanDevices.sNameHanDevice = $(this).parent().find('a').attr('href');
						sensus.commons.modules.content.hanDevices.idDevice = sDeviceId;

						sensus.util.actiondialog.launchActionDialog('deleteHanDevice', sensus.commons.modules.content.hanDevices.dialogDeleteHanDevices);
					});

				}
			}
		}
};