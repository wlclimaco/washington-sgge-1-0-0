/**
 * @param sId
 * 			string, id of device
 * @param sType
 * 			string, type of summary
 * @return date
 * 			date java script format
 */
$.fn.summary = function (iProcessId, sType, bHideTabs, ActionId, rniEventId) {

	var $oDialogContent = $(this),
		oDemandResponse,
		oCommunication,
		oImportHanDevices,
		oTamperDetect,
		oSetupDemandResponse,
		iStartTab = 0,

		// Default Fetch Summary API URL
		sFetchSummaryURL = "api/summary/fetch",

		// Default Request Object to Export
		aProcesses = [new Process({id : iProcessId, rniEventId : rniEventId})],

		oCsvRequest = new InquiryProcessRequest({processes : aProcesses}),

		fnCreateMessageColumn = sensus.util.process.fnCreateMessageColumn,

		/**
		 * ### Commons Function for all Summary Tabs ###
		 */

		/**
		 * Ajax Content
		 * Do a Ajax call to get Summary Page and set on content
		 *
		 * @param {String} sUrl, page url
		 * @param {Object} $oContent, content to set the page
		 */
		fnAjaxContent = function (sUrl, $oContent, oSummaryDeferred) {

			$.ajax({
				url : sUrl,
				async : true,
				cache : false,
				method : "GET",
				success : function (sHtml) {
					$oContent.html(sHtml);
					if (!$.sc.isNullOrUndefined(oSummaryDeferred)) {
						oSummaryDeferred.resolve();
					}
				}
			});

		},

		/**
		 * Calculate the percentage and return
		 *
		 * @param {number} sum, total
		 * @param {number} per, part of total
		 * @return {number} percentage
		 */
		fnGetPercentage = function(sum, per) {

			var avg = 0;

			if (sum == 0) {

				return 0;

			} else {
				avg = (per / sum) * 100;
				// Round a number to the nearest integer
				// example: 42.3% and 57.7% turn to 42% and 58%, total 100%
				return Math.round(avg);
			}
		},

		/**
		 * Counter
		 * Iterate process items and return the total of process with completed status
		 * and following count config
		 *
		 * @param {Array} aProcessItems, array of process items
		 * @return {Object} oCount, count config
		 */
		fnCounter = function (aProcessItems, oCount) {

		    var oProcessItem,
		    	count = 0,
		    	i = 0,
		    	max = aProcessItems.length;

		    oCount = fnCountConfigValidator(oCount);

		    for (; i < max; i++) {

		    	oProcessItem = aProcessItems[i];

		    	// Counts functionally
				if (oCount.fnCount) {

					oCount.fnCount(oProcessItem);
				}

				// Count COMPLETED
		    	if (oProcessItem.processItemStatusEnum == 'COMPLETED') {

		    		count++;
		    	}

		    }

		    oCount.COMPLETED = count;

			return oCount;
		},

		/**
		 * Count Config Validator
		 *
		 * @param {Object} oCount, count config object
		 * @return {Object} oCount, return a valid count config object
		 */
		fnCountConfigValidator = function (oCount) {

			// Make sure oCount will have COMPLETED property
			if (!oCount) {

				// If oCount NULL or UNDEFINED create default object
				oCount = {COMPLETED : 0};

			} else if (!oCount.COMPLETED) {

				// If oCount does not have COMPLETED property
				oCount['COMPLETED'] = 0;
			}

			return oCount;
		},

		/**
		 * Create Device array for Get Demand Response Request
		 * @param {Array} aDeviceList, array of device
		 *
		 * @return {Array} aDevices
		 */
		fnCreateDevices = function (aDeviceList) {

			var i = 0,
				max = aDeviceList.length,
				aDevices = [],
				oDevice,
				oRadio,
				sBaseFlexNetId;

			for (; i < max; i++) {

				oDevice = aDeviceList[i];
				oRadio = oDevice.communicationDeviceAsRadio || {customerId : "", flexNetId : "", eletricMeterFlexNetId : ""};
				sBaseFlexNetId = oRadio.eletricMeterFlexNetId || "";

				if (aDeviceList.deviceType = 'LCM') {

					aDevices.push({
						flexNetId						: "" + oRadio.flexNetId,
						baseFlexNetId					: sBaseFlexNetId,
						customerId						: oRadio.customerId,
						deviceId						: oDevice.deviceId,
						lifecycleStateEnum				: oDevice.lifecycleStateEnum,
						specificDeviceTypeEnumValue     : 2,//oDevice.lcmTypeEnum,
						deviceType						: {
							id	 : oDevice.deviceTypeValue,
							type : oDevice.deviceType
						},
						deviceType		: oDevice.deviceType
					});

				} else {

					aDevices.push({
							flexNetId			: "" + oRadio.flexNetId,
							baseFlexNetId		: sBaseFlexNetId,
							customerId			: oRadio.customerId,
							deviceId			: oDevice.deviceId,
							lifecycleStateEnum	: oDevice.lifecycleStateEnum,
							deviceType			: {
								id	 : oDevice.deviceTypeValue,
								type : oDevice.deviceType
							},
							deviceType		: oDevice.deviceType
						});
				}
			}

			return aDevices;
		},

		/**
		 * Fill process description
		 * Decode description
		 *
		 * @param {Object} oProcess, process from BE
		 */
		fnFillDescriptionProcess = function (oProcess, $oSummaryDesc) {

			var processUtil = sensus.util.process;
			var sDescription = sensus.util.process.processDescription.generate(oProcess, false);

			// Type/Action Category - Type/Action Name
			$('.action-type', $oSummaryDesc).text(
					sensus.locale.get(processUtil.fnCreateProcessName(oProcess)) + " - " +
					sensus.locale.get(processUtil.fnCreateProcessCategoryType(oProcess)));

			// Set on HTML Elements
			$('.process-id', $oSummaryDesc).text(oProcess.id);
			$('.request-by, .initiate-by', $oSummaryDesc).text(sDescription);

			if (oProcess.processStatusEnum == "COMPLETED") {

				// Completed In case
				$("#completed-in", $oSummaryDesc).removeClass("hide");
				$('.time', $oSummaryDesc).text(sensus.util.process.fnCreateCompletedInColumn(
					oProcess.processStatusEnum,
					oProcess.estimatedSecondsToComplete,
					$.date.fnGetDate(oProcess.startTime),
					$.date.fnGetDate(oProcess.endTime)
				));

			} else if (oProcess.processStatusEnum == "COMMAND_SENT") {

				// Command Sent Case
				$("#command-sent", $oSummaryDesc).removeClass("hide");
			}
		},

		/**
		 *  Create Information
		 *
		 *  @param {Object} $oInfoColumn, jQuery Object
		 *  @param {String} sTitle, title
		 *  @param {String} sValue, value
		 *  @param {String} sSubHead, sub head
		 */
		fnCreateInformation = function ($oInfoColumn, sValue, sSubHead) {

			$oInfoColumn
				.find(".value").html(sValue) // value
				.next().html(sSubHead); // sub-head
		},

		/**
		 * Create Failed Table data
		 *
		 * @param {Object} $oTable, jQuery Object, table to set processes items
		 * @param {Array} aProcessItems, processes items to iterate
		 * @return {number} count all process items with COMPLETED status
		 */
		fnCreateFailedTable = function ($oTable, aProcessItems, oCount, bRetry, $Retry) {

			var $oTableRow = [],
				$aLi = [],
				oDevice,
				oRadio,
				oLocation,
				oProcessItem,
				sProcessItemStatusEnum,
				oProcessItemStatusEnum = {},
				sConnectionStatus,
				sStatus,
				i = 0,
				iItemsLength = aProcessItems.length,
				aDisabledStatus = ["RUNNING", "COMMAND_SENT"]; // Status will not be displayed.

			// Valid oCount
			oCount = fnCountConfigValidator(oCount);

			// Iterate process items
			// Create Failed Table
			for (; i < iItemsLength; i = i + 1) {

				// Process Item
				oProcessItem = aProcessItems[i];
				sProcessItemStatusEnum = oProcessItem.processItemStatusEnum;

				// Device Validation
				oDevice = oProcessItem.device || "";
				oRadio = oDevice.communicationDeviceAsRadio || {flexNetId : oProcessItem.networkAddress || ""};
				oLocation = oRadio.location || {address : ""};

				if (sProcessItemStatusEnum) {

					if (sProcessItemStatusEnum != 'COMPLETED') {

						// To group process item status
						if (oProcessItemStatusEnum.hasOwnProperty(sProcessItemStatusEnum)) {

							oProcessItemStatusEnum[sProcessItemStatusEnum]++;

						} else {

							oProcessItemStatusEnum[sProcessItemStatusEnum] = 1;
						}

						if ($.inArray(sProcessItemStatusEnum, aDisabledStatus) == -1) {

							$oTableRow.push("<tr><td>" + (oDevice.deviceId || "") + "</td>" + // Device ID
									"<td>" + (oDevice.macAddress ? oDevice.macAddress : oRadio.flexNetId || oProcessItem.networkAddress || "") + "</td>" + // Network Address or Flex Net Id
									"<td>" + ((oDevice.configuration ? oDevice.configuration.premiseId : "") || "") + "</td>" + // Premise ID
									"<td>" + oLocation.address + "</td>" + // Location Address
									"<td>" + sensus.locale.get("summary.text.processStatus." + sProcessItemStatusEnum) + "</td>" + // State
									"<td>" + fnCreateMessageColumn(oProcessItem.message) + "</td></tr>"); // Error
						}

					} else {

						oCount.COMPLETED++;
					}
				}

				// Counts functionally
				if (oCount.fnCount) {

					oCount.fnCount(oProcessItem);
				}
			}

			// Set Failed Table on DOM
			if ($oTableRow.length > 0) {

				$oTable.find("tbody").html($oTableRow.join(''));
				$oTable.parent()
					.removeClass("hide")
					.find(".size").text($oTableRow.length);

				$("#list-fail", $oDialogContent)
					.removeClass("hide")
					.find("strong").text($oTableRow.length);
			}

			// Create Failed Information
			for (sStatus in oProcessItemStatusEnum) {

				i = oProcessItemStatusEnum[sStatus];

				if (i > 0) {

					if (sStatus == "FAILED" && bRetry) {

						$aLi.push("<li class='fail'>" +
								(sensus.locale.get("summary.text.processStatusMessage." + (i > 1 ? "plural." : "single.") + sStatus, "" + i)) +
								" <a href='#' id='retry-failed' class='button small ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' role='button' aria-disabled='false'>" +
								" <span class='ui-button-text'>" + sensus.locale.get("summary.text.retryConnect") + "</span></a></li>");
						continue;
					}

					$aLi.push(
						"<li class='fail'>"
						+ (sensus.locale.get("summary.text.processStatusMessage." + (i > 1 ? "plural." : "single.") + sStatus, "" + i))	+ "</li>");
				}
			}

			// Set Failed Information on DOM
			if ($aLi.length > 0) {
				$($Retry, $oDialogContent).html($aLi.join('')).parent().removeClass("hide");
			}

			return oCount;
		},

		/**
		 * Function to hide some tabs at dialog
		 *
		 * @param {Array} aProcessItems, with jQuery Object to hide
		 */
		fnHideTabs = function (aTabs) {

			if (!$.sc.isNullOrUndefined(aTabs)) {

				var count = aTabs.length;

				for (var i = 0; i < count; i++) {
					aTabs[i].hide();
				}
			}
		},

		/**
		 * Content Tabs Object
		 * Define Tab definitions
		 *
		 * @param {String} url, the tab page URL
		 * @param {Object} request, the Ajax Object Request
		 * @param {Function} callback, the callback for Ajax Call
		 * @param {Function} init, the start function
		 */
		oContentTabs = {

			communication : {

				url : sFetchSummaryURL,

				csvUrl : 'api/export/generateComunicationSummaryCSV',

				request : new FetchRequest({id : "" + (iProcessId || ""), type : "communicationSummary", rniEventId : rniEventId}),

				csvRequest : oCsvRequest,

				processId : null,

				callback : function (oResponse) {

					var $oInfoTable 	= $("#informationTable", $oDialogContent);
					var $failedDevices 	= $("#failed-devices-group");
					var	oProcess 		= oResponse.processes[0];
					var oProperty 		= null;
					var sBroadcast 		= "INITIATE_DEMAND_RESPONSE_EVENT_TO_GROUP";
					var	sDescDevices 	= " " + sensus.locale.get("commons.pages.smartPoints");
					var sActionType 	= oProcess.action.actionType.actionTypeEnum;
					var iProperties		= oProcess.properties.length;
					var	iTotal 			= oProcess.totalSmartpoints || 0;
					var iFailed 		= oProcess.failedSmartpoints || 0;
					var	iSuccess 		= 0;
					var i 				= 0;

					// Saving the id to use on export CSV of deliverOn dialog
					oContentTabs.communication.processId = oProcess.id || null;

					// Fills description of the process
					fnFillDescriptionProcess(oProcess, $('#summary-description', $oDialogContent));

					if (sActionType != sBroadcast) {

						// Create Table with Failed Devices on process
						if (iFailed > 0) {

							// On iterate process items count all with COMPLETED status
							iSuccess = fnCreateFailedTable(
									$("#tableCommunicationSummary", $oDialogContent), oProcess.processItems, null, null, $failedDevices).COMPLETED;

						} else {

							// Count process items with COMPLETED status
							iSuccess = fnCounter(oProcess.processItems).COMPLETED;
						}

						// Create Success Column Information
						fnCreateInformation($oInfoTable.find("td:eq(0)"), (fnGetPercentage(iTotal, iSuccess) + "%"), (iSuccess + sDescDevices));

						// Create Failed Column Information
						fnCreateInformation($oInfoTable.find("td:eq(1)"), (fnGetPercentage(iTotal, iFailed) + "%"),	(iFailed + sDescDevices));

					} else {

						// Fake data, just used in order to set percentage
						iTotal = 1;
						iSuccess = 1;
						iFail = 0;

						for (; i < iProperties; i++) {

							oProperty = oProcess.properties[i];

							if (oProperty.propertyName == "DESCRIPTION") {

								iSuccess = 0;
								iFailed = 1;

								$($failedDevices, $oDialogContent).html("<li class='fail'>"	+ oProperty.propertyValue	+ "</li>").parent().removeClass("hide");
							}
						}

						// Create Success Column Information
						fnCreateInformation($oInfoTable.find("td:eq(0)"), (fnGetPercentage(iTotal, iSuccess) + "%"), "");

						// Create Failed Column Information
						fnCreateInformation($oInfoTable.find("td:eq(1)"), (fnGetPercentage(iTotal, iFailed) + "%"),	"");
					}

					sensus.util.page.stopProgressBar();
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, true, this.callback,null,null,null,true );
				},

				init : function () {

					this.ajaxCall();

					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

						// Fill processId on csvRequest when processId is null
						if (oContentTabs.communication.processId && !oContentTabs.communication.csvRequest.processes[0].id) {

							oContentTabs.communication.csvRequest.processes[0].id = oContentTabs.communication.processId;
						}

						sensus.util.exportcsv.generateCSV(
								oContentTabs.communication.csvUrl,
								oContentTabs.communication.csvRequest);
					});
				}
			},

			demandResponse : {

				url : sFetchSummaryURL,

				csvUrl : 'api/export/generateDemandResponseCSV',

				request : new FetchRequest({id : "" + iProcessId, type : "demandResponse"}),

				csvRequest : oCsvRequest,

				getDemandResponseUrl : "api/deviceop/apply",

				getDemandResponseRequest : new Action({
					isMonitored		: true,
					onDemand		: true,
					actionTime		: $.date.fnTimeToUTC($.date.setDateServer(), true)
				}),

				createFailedColumn : function (aProcessItems) {

					var $oTable = $("#tableDemandResponse", $oDialogContent),
						$oTableRow = [],
						oDevice,
						oRadio,
						oProcessItem,
						sParticipation,

						iFullparticipation = 0,
						iPartialParticipation = 0,
						iOptOut = 0,
						iOperatorOptOut = 0,
						iStarted = 0,
						aDevices = [],

						FULLPARTICIPATION = "FullParticipation",
						PARTIALPARTICIPATION = "PartialParticipation",
						OPERATOROPTOUT = "Operator_OptOut",
						OPTOUT = "OptOut",
						PARTIALPARTICIPATIONANDOPTOUT = "PartialParticipationAndOptOut",
						COMPLETED = "COMPLETED",
						STARTED = "Started",

						i = 0,
						max = aProcessItems.length,

						sNetworkStatus = "",
						sRemoteConnectStatus,
						sRemoteConnectReason,

						oActionCancel = null;

					for (; i < max; i = i + 1) {

						oProcessItem = aProcessItems[i];
						oDevice = oProcessItem.device;
						oRadio = oDevice.communicationDeviceAsRadio || {flexNetId : ""};

						sParticipation = oProcessItem.participation;

						// Add devices from UNCOMPLETED status item process
						if (oProcessItem.processItemStatusEnum != COMPLETED) {
							aDevices.push(oDevice);
						}

						// Start row
						$oTableRow.push("<tr>");

						// Device ID
						$oTableRow.push("<td>" + (oDevice.deviceId || "") + "</td>");

						// Network Address or Flex Net Id
						$oTableRow.push("<td>" + (oDevice.macAddress ? oDevice.macAddress : oRadio.flexNetId) + "</td>");

						// Parent Id
						$oTableRow.push("<td>" + (oDevice.electricMeterFlexNetId || "")+ "</td>");

						// Network Status
						$oTableRow.push("<td>" + ((oDevice.configuration ? oDevice.configuration.premiseId : "") || "") + "</td>");

						sRemoteConnectStatus = sensus.util.page.fnGetRemoteDisconnectState(oDevice.remoteConnectStatusEnum);
						sRemoteConnectReason = sensus.util.page.fnGetRemoteDisconnectReason(oDevice.remoteConnectReasonEnum);

						if (sRemoteConnectReason == sensus.locale.get("commons.pages.doubleHyphen")) {

							sNetworkStatus = sRemoteConnectStatus;

						} else {

							sNetworkStatus = sRemoteConnectStatus + " - " + sRemoteConnectReason;
						}

						// Network Status
						$oTableRow.push("<td>" + sNetworkStatus + "</td>");

						// Count Participation
						switch (sParticipation) {

						case FULLPARTICIPATION :
							iFullparticipation = iFullparticipation + 1;
							iStarted = iStarted + 1;
							$oTableRow.push("<td>Yes</td><td>Yes</td><td>No</td><td>No</td><td>No</td>");
							break;

						case PARTIALPARTICIPATION :
							iPartialParticipation = iPartialParticipation + 1;
							iStarted = iStarted + 1;
							$oTableRow.push("<td>Yes</td><td>No</td><td>Yes</td><td>No</td><td>No</td>");
							break;

						case OPTOUT :
							iOptOut = iOptOut + 1;
							iStarted = iStarted + 1;
							$oTableRow.push("<td>Yes</td><td>No</td><td>No</td><td>Yes</td><td>No</td>");
							break;

						case PARTIALPARTICIPATIONANDOPTOUT :
							iOptOut = iOptOut + 1;
							iStarted = iStarted + 1;
							$oTableRow.push("<td>Yes</td><td>No</td><td>Yes</td><td>Yes</td><td>No</td>");
							break;

						case OPERATOROPTOUT :
							iOperatorOptOut = iOperatorOptOut + 1;
							$oTableRow.push("<td>No</td><td>No</td><td>No</td><td>No</td><td>Yes</td>");
							break;

						case STARTED :
							iStarted = iStarted + 1;
							$oTableRow.push("<td>Yes</td><td>No</td><td>No</td><td>No</td><td>No</td>");
							break;

						default :
							$oTableRow.push("<td></td><td></td><td></td><td></td><td></td><td></td>");
						}

						$oTableRow.push("</tr>");
					}

					// Set Failed Table on DOM
					if (max > 0) {

						$oTable.find("tbody").html($oTableRow.join(''));
						$oTable.find("tbody tr").find("td:last").append(oActionCancel);

						$oTable.parent()
							.removeClass("hide")
							.find(".size").text(max);

						$("#list-fail", $oDialogContent)
							.removeClass("hide")
							.find("strong").text(max);
					}

					return {
						FULL_PARTICIPATION : iFullparticipation,
						PARTIAL_PARTICIPATION : iPartialParticipation,
						OPT_OUT: iOptOut,
						OPERATOR_OPT_OUT: iOperatorOptOut,
						STARTED : iStarted,
						DEVICES : aDevices
					};
				},

				createDemandResponseDetails : function (properties, action) {

					var aDetails,
						sEnrollmentCode 	= !$.sc.isNullOrUndefined(action.enrollmentCode) ? action.enrollmentCode : "-",
						sCriticalityLevel 	= !$.sc.isNullOrUndefined(action.criticalityLevel) ? action.criticalityLevel : "-",
						sDutyCycleRate 		= !$.sc.isNullOrUndefined(action.dutyCycleRate) ? action.dutyCycleRate + "%" : "-",
						sLoadAdjustment 	= !$.sc.isNullOrUndefined(action.loadAdjustment) ? action.loadAdjustment + "%" : "-",
						sRandomizeStart		= !$.sc.isNullOrUndefined(action.randomizeStart) ? action.randomizeStart : "-",
						sRandomizeEnd		= !$.sc.isNullOrUndefined(action.randomizeEnd) ? action.randomizeEnd : "-",
						aOffset 			= [],
						sOffset 			= "-",
						sHeating			= "",
						sCooling			= "",
						sRandomize			= "-",
						sTemperatureFormat	= "&#176;";
						sTemperatureType	= sensus.settings.userSettings.temperatureType,
						sDeviceClasses 		= "-";

					if (sTemperatureType)
					{
						if (sTemperatureType == "CELSIUS") {
							sTemperatureFormat = "C" + sTemperatureFormat;
						} else {
							sTemperatureFormat = "F" + sTemperatureFormat;
						}

						if (!$.sc.isNullOrUndefined(action.heating)) {

							sHeating = action.heating;
							if (sTemperatureType.toLowerCase() == sensus.locale.get("systemsettings.page.temperature.fahrenheit").toLowerCase()) {

								sHeating = $.temperature.convertTemperature(action.heating, "sf");
							}
							aOffset.push(sensus.locale.get("systemintelligence.dialogDemandResponse.heating") + " " + sHeating + " " + sensus.locale.get("systemintelligence.dialogDemandResponse.degrees") + " " + sTemperatureFormat);
						}
						if (!$.sc.isNullOrUndefined(action.cooling)) {
							sCooling = action.cooling;
							if (sTemperatureType.toLowerCase() == sensus.locale.get("systemsettings.page.temperature.fahrenheit").toLowerCase()) {

								sCooling = $.temperature.convertTemperature(action.cooling, "sf");
							}
							aOffset.push(sensus.locale.get("systemintelligence.dialogDemandResponse.cooling") + " " + sCooling + " " + sensus.locale.get("systemintelligence.dialogDemandResponse.degrees") + " " + sTemperatureFormat);
						}
						if (aOffset.length) {
							sOffset = aOffset.join(" | ");
						}
					}
					if (action && action.deviceClasses)
					{
						var nDeviceClassesLength = action.deviceClasses.length;
						var aDeviceClasses = [];
						for (var i = 0; i < nDeviceClassesLength; i++) {
							aDeviceClasses.push(sensus.locale.get("systemintelligence.scheduledCreateEvent." + action.deviceClasses[i]));
						}
						if (aDeviceClasses.length) {
							sDeviceClasses = aDeviceClasses.join(", ");
						}
					}
					// Randomize
					if (sRandomizeStart == true) {

						sRandomize = sensus.locale.get("systemintelligence.page.event.hanStart") + " ";
					}

					if (sRandomizeEnd == true) {

						if (sRandomizeStart == true) {

							sRandomize = sRandomize + " | ";

						} else {

							sRandomize = "";
						}

						sRandomize = sRandomize + sensus.locale.get("systemintelligence.page.event.hanEnd");
					}

					// Add Data on DOM
					aDetails = [sEnrollmentCode, sOffset, sDutyCycleRate, sLoadAdjustment, sCriticalityLevel, sRandomize, sDeviceClasses];

					$("#demand-response-details dd", $oDialogContent).each(function (i, item) {
						$(item).html(aDetails[i]);
					});
				},

				searchProperty : function (properties, sProperty) {

					var iPropertiesLenght = properties.length;

					for (var i = 0; i < iPropertiesLenght; i = i + 1) {

						iPropertyName = properties[i].propertyName;
						iPropertyValue = properties[i].propertyValue;

						if (iPropertyName == sProperty) {

							return iPropertyValue;
						}
					}
				},

				callback : function (oResponse) {

					var $oInfoTable = $("#summaryDemandResponse", $oDialogContent),
						oProcess = oResponse.processes[0],
						aProcessItems = oProcess.processItems,
						oCount = oContentTabs.demandResponse.createFailedColumn(aProcessItems), // Create Failed Table
						iStarted = oCount.STARTED,
						iReceived = aProcessItems.length,
						iFullParticipation = oCount.FULL_PARTICIPATION,
						iPartialParticipation = oCount.PARTIAL_PARTICIPATION,
						iOptOut = oCount.OPT_OUT,
						iOperatorOptOut = oCount.OPERATOR_OPT_OUT,
						aDevices = oCount.DEVICES,
						iUncompleted = aDevices.length,
						aStartTime = $.date.dateFormat(oProcess.action.actionTime ,sensus.settings.userSettings.dateFormatMask.replace("yyyy", "yy") + " h:i A").split(" "),
						oDemandResponseDuration = oContentTabs.demandResponse.searchProperty(oProcess.properties, "DURATION");

					oActionCancel = $("<a class='button' href='#'>"
							+ sensus.locale.get("commons.pages.cancel") + "</a>", $oDialogContent).button().click(function(e) {

						e.preventDefault();

						var sTableId = "history-table";

						if ($.address.parameter("pg") == "device/history") {
							sTableId = "device-history-table";
						}

						sensus.util.actiondialog.launchActionDialog("cancel", sensus.util.process.actions.cancelDialog(oProcess, sTableId, true));
					});

					// Create Columns Information
					// Start
					fnCreateInformation($oInfoTable.find("td:eq(0)"), aStartTime[1] + "<small>" + aStartTime[2] + "</small>",aStartTime[0]);

					// Duration
					if (oDemandResponseDuration) {

						fnCreateInformation($oInfoTable.find("td:eq(1)"), (oDemandResponseDuration || ""), sensus.locale.get("commons.pages.Minutes"));
					}

					// Participation
					fnCreateInformation($oInfoTable.find("td:eq(2)"),
							fnGetPercentage(iReceived, iFullParticipation) + "%",
							iFullParticipation + " " + sensus.locale.get("commons.pages.of") + " " + iReceived);

					// Received
					fnCreateInformation($oInfoTable.find("td:eq(3)"), iReceived, "");

					// Started
					fnCreateInformation($oInfoTable.find("td:eq(4)"), iReceived, "");

					// Full Participation
					fnCreateInformation($oInfoTable.find("td:eq(5)"), iFullParticipation, "");

					// Partial Participation
					fnCreateInformation($oInfoTable.find("td:eq(6)"), iPartialParticipation, "");

					// Opt-out
					fnCreateInformation($oInfoTable.find("td:eq(7)"), iOptOut, "");

					// Opt-out
					fnCreateInformation($oInfoTable.find("td:eq(8)"), iOperatorOptOut, "");

					if (oProcess.processStatusEnum == "COMMAND_SENT" || oProcess.processStatusEnum == "STARTED") {

						// Cancel-event
						fnCreateInformation($oInfoTable.find("td:eq(9)"), oActionCancel, "");

					} else {

						$oInfoTable.find("td:eq(9)").remove();
					}

					// Create Demand Response Details
					oContentTabs.demandResponse.createDemandResponseDetails(oProcess.properties,oProcess.action);

					// Get Demand Response Event Status
					if (iUncompleted > 0) {

						// Prepare getDemandResponse Request Object
//						oContentTabs.demandResponse.getDemandResponseRequest.devices = fnCreateDevices(aDevices);

						$(".ui-state-error").removeClass("hide").find("strong").text(iUncompleted);
					}

					oContentTabs.demandResponse.getDemandResponseRequest.rniEventId = oProcess.rniEventId;
//					oContentTabs.demandResponse.getDemandResponseRequest.deviceType = oProcess.firstProcessItem.device.deviceType;

					sensus.util.page.stopProgressBar();
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, true, this.callback,null,null,true,true );
				},

				init : function () {

					this.ajaxCall();

					// Export CSV Click Event
					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

						sensus.util.exportcsv.generateCSV(
								oContentTabs.demandResponse.csvUrl,
								oContentTabs.demandResponse.csvRequest);
					});

					// Toggle demand response details
					$('.spindown', $oDialogContent).click(function(e) {

						e.preventDefault();

						// Change triangle icon
						$(this).find(".ui-icon").toggle();

						// Open Detail Block
						$(".spindown-child").toggle("blind", null, 500);
					});

					// Get Demand Response Event Status
					$(".button:eq(0)", $oDialogContent).button().click(function(e) {

						e.preventDefault();

						$.ajaxValidator.fnDoCall(
								oContentTabs.demandResponse.getDemandResponseUrl,
								{getDemandResponseEventStatusAction : oContentTabs.demandResponse.getDemandResponseRequest},
								true, null, sensus.locale.get("commons.pages.getDemandResponseEventStatusSucess"),null,null,true);
					});
				}
			},

			demandRead : {

				url : sFetchSummaryURL,

				csvUrl : 'api/export/generateDemandReadCSV',

				request : new FetchRequest({id : "" + iProcessId, type : "demandRead"}),

				csvRequest : oCsvRequest,

				callback : function (oResponse) {

					var $row;
					var $readTime;
					var $readValue;
					var aProcessItems;
					var oProcessItem;
					var oDevice;
					var aProperties;
					var i;
					var max;
					var iPropertiesMax;
					var indexProperties;
					var oProperty;
					var sDateFormatMask;
					var sValue;

					if (oResponse && oResponse.processes
							&& oResponse.processes.length > 0) {

						aProcessItems = oResponse.processes[0].processItems;
						max = aProcessItems.length;
						$row = [];
						sDateFormatMask = sensus.settings.userSettings.dateFormatMask.replace("yyyy", "yy");

						for (i = 0; i < max; i = i + 1) {

							oProcessItem = aProcessItems[i];
							aProperties = oProcessItem.properties;
							oDevice = oProcessItem.device;

							// Start row
							$row.push("<tr>");

							// Device ID
							$row.push("<td>");
							$row.push(oDevice.deviceId);
							$row.push("</td>");

							// Network Address or FlexNet Id
							$row.push("<td>");
							$row.push(oDevice.macAddress || oDevice.communicationDeviceAsRadio.flexNetId);
							$row.push("</td>");

							// Premise ID
							$row.push("<td>");
							$row.push(oDevice.configuration ? oDevice.configuration.premiseId : "");
							$row.push("</td>");

							if (aProperties && aProperties.length) {

								for (indexProperties = 0, iPropertiesMax = aProperties.length;
									indexProperties < iPropertiesMax; indexProperties = indexProperties + 1) {

									oProperty = aProperties[indexProperties];
									sValue = oProperty.propertyValue;

									if ($.sc.isNullOrUndefined(sValue)) {
										sValue = "";
									}

									// Read Value
									if (oProperty.propertyName == "LAST_READ_VALUE") {

										$readValue = "<td>" + sValue + "</td>";

									// Read Time
									} else if (oProperty.propertyName == "LAST_READ_TIME") {

										// The adding of three zeros it's to format in data value
										$readTime = "<td>"
											+ $.date.dateFormat(oProperty.propertyValue + "000", sDateFormatMask + " h:i A");
											+ "</td>";
									}
								}

								$row.push($readValue);
								$row.push($readTime);

							} else {

								$row.push("<td></td><td></td>");
							}

							// End row
							$row.push("</tr>");
						}

						if (max > 0) {

							$("#tableDemandRead tbody", $oDialogContent).html($row.join(''))
									.parents(".selected-points")
										.removeClass("hide")
										.find(".size").text(max);

							$("#list-fail", $oDialogContent)
								.removeClass("hide")
								.find("strong").text(max);
						}

					}
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, true, this.callback,null,null,null,true );
				},

				init : function () {

					this.ajaxCall();

					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

						sensus.util.exportcsv.generateCSV(
								oContentTabs.demandRead.csvUrl,
								oContentTabs.demandRead.csvRequest);
					});
				}
			},

			importHanDevices : {

				url : sFetchSummaryURL,

				csvUrl : 'api/export/generateImportHanSummaryCSV',

				request : new FetchRequest({id : "" + iProcessId, type : "importHan"}),

				csvRequest : oCsvRequest,

				retryConnectUrl : "api/deviceop/apply",

				retryConnectRequest : {
					isMonitored		: true,
					onDemand		: true,
					actionTime		: $.date.fnTimeToUTC($.date.setDateServer(), true)
				},

				processItems : [],

				countConfig : function (oProcessItem) {

					// TODO - Need to check if this if is necessary, because the connectionsStatus propertie doesn't exist at BE side
					if (!$.sc.isNullOrUndefined(oProcessItem) && !$.sc.isNullOrUndefined(oProcessItem.device) && !$.sc.isNullOrUndefined(oProcessItem.device.connectionStatus)) {

						var sConnectionStatus = oProcessItem.device.connectionStatus;

						if (sConnectionStatus == "CONNECTED") {

							this.CONNECTED++;

						} else if (sConnectionStatus == "DISCONNECTED" || sConnectionStatus == null) {

							this.DISCONNECTED++;
						}

					} else if (!$.sc.isNullOrUndefined(oProcessItem) && (!$.sc.isNullOrUndefined(oProcessItem.processItemStatusEnum) && oProcessItem.processItemStatusEnum == "COMPLETED")
									&& !$.sc.isNullOrUndefined(oProcessItem.device) && !$.sc.isNullOrUndefined(oProcessItem.device.lifecycleStateEnum)) {

						if (oProcessItem.device.lifecycleStateEnum == "JOINED") {

							this.CONNECTED++;

						} else {

							this.DISCONNECTED++;
						}
					}
				},

				countConfigFailedDevices : function (oProcessItem) {

					if (oProcessItem.processItemStatusEnum == "FAILED") {

						this.DEVICES.push(oProcessItem.device);
					}
				},

				callback : function (oResponse) {

					var $oInfoTable 	= $("#informationTableImportHan", $oDialogContent);
					var	sDescDevices 	= " " + sensus.locale.get("commons.pages.smartPoints");
					var	oProcess 		= oResponse.processes[0];
					var	iFailed 		= oProcess.failedSmartpoints || 0;
					var	iSum 			= 0;
					var	oCount;

					// Set RNI ID on Retry Request
					oContentTabs.importHanDevices.retryConnectRequest.rniEventId = oProcess.rniEventId;

					// Create Table with Failed Devices on process
					if (iFailed > 0) {

						// On iterate process items count all with COMPLETED status
						oCount = fnCreateFailedTable(
								$("#tableImportHanDevice", $oDialogContent),
								oProcess.processItems,
								{
									CONNECTED 		: 0,
									DISCONNECTED 	: 0,
									COMPLETED 		: 0,
									fnCount 		: oContentTabs.importHanDevices.countConfig
								}, oProcess.processStatusEnum == "COMPLETED",$("#failed-devices-group-importHan"));

						oContentTabs.importHanDevices.processItems = oProcess.processItems;

					} else {

						oCount = fnCounter(oProcess.processItems,
								{
									CONNECTED 		: 0,
									DISCONNECTED	: 0,
									COMPLETED		: 0,
									fnCount		 	: oContentTabs.importHanDevices.countConfig
								});
					}

					iSum = oCount.CONNECTED + oCount.DISCONNECTED;

					// Create CONNECTED Column Information
					fnCreateInformation(
							$oInfoTable.find("td:eq(0)"),
							(fnGetPercentage(iSum, oCount.CONNECTED) + "%"),
							(oCount.CONNECTED + sDescDevices)
						);

					// Create DISCONNECTED Column Information
					fnCreateInformation(
							$oInfoTable.find("td:eq(1)"),
							(fnGetPercentage(iSum, oCount.DISCONNECTED) + "%"),
							(oCount.DISCONNECTED + sDescDevices)
						);
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall(this.url, this.request, true, this.callback,null,null,null,true);

					$("#retry-failed", $oDialogContent).button();
				},

				init : function () {

					this.ajaxCall();

					// Retry Connect Failed
					$(".ss-widget-table-summary-kpi", $oDialogContent).delegate("#retry-failed", "click", function(e) {

						e.preventDefault();

						var importTab = oContentTabs.importHanDevices;
						var oCount = fnCounter(importTab.processItems, {
							DEVICES : [],
							fnCount : importTab.countConfigFailedDevices
						});


						$.ajaxValidator.fnDoCall("api/deviceop/apply",
								{retryImportHanDeviceAction : importTab.retryConnectRequest},
								true,
								function (oResponse) {

									importTab.ajaxCall();
								},
								sensus.locale.get("summary.text.retrySuccess"),
								null,null,null,true
						);
					});

					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

						sensus.util.exportcsv.generateCSV(
								oContentTabs.importHanDevices.csvUrl,
								oContentTabs.importHanDevices.csvRequest);
					});
				}
			},

			tamperDetect : {

				url : "api/process/fetchById",

				csvUrl : 'api/export/generateTamperDetectSummaryCSV',

				request : {processID : iProcessId},

				csvRequest : oCsvRequest,

				callback : function (oResponse) {

					var $row;
					var $i;
					var max;
					var oProcess;
					var aProcessItems;
					var aProperties;
					var oDevice;
					var oProperty;
					var sPropertyName;
					var sPropertyValue;
					var oProcessItem;
					var indexProperties;
					var iPropertiesMax;


					if (oResponse && oResponse.processes && oResponse.processes.length > 0) {
						aProcessItems = oResponse.processes[0].processItems;
						var sActionType = oResponse.processes[0].action.actionType.actionTypeEnum;
						max = aProcessItems.length;

						$row = [];

						for (i = 0; i < max; i++) {

							oProcessItem = aProcessItems[i];
							oDevice 	 = oProcessItem.device;

							if (sActionType.contains("GET")) {

								aProperties  = oResponse.processes[0].processItems[i].properties;
							} else {

								aProperties  = oResponse.processes[0].properties;
							}

							if (aProperties && aProperties.length) {

								iPropertiesMax = aProperties.length;

								for (indexProperties = 0; indexProperties < iPropertiesMax; indexProperties++) {

									oProperty 		= aProperties[indexProperties];
									sPropertyName 	= oProperty.propertyName;

									if (sPropertyName.contains("RELAY")) {

										sPropertyValue = oProperty.propertyValue || "--";

										if (sPropertyValue == "0" || sPropertyValue == "1") {

											sPropertyValue = sPropertyValue + " " + sensus.locale.get("commons.pages.minute");

										} else if (sPropertyValue != "--") {

											sPropertyValue = sPropertyValue + " " + sensus.locale.get("commons.pages.minutes");
										}

										if (sPropertyName.contains("RELAY1")) {

											sPropertyName = "1";

										} else if (sPropertyName.contains("RELAY2")) {

											sPropertyName = "2";

										} else if (sPropertyName.contains("RELAY3")) {

											sPropertyName = "3";
										}

										// Start row
										$row.push("<tr>");

										// Device ID
										$row.push("<td>");
										$row.push(oDevice.deviceId);
										$row.push("</td>");

										// Network Address or FlexNet Id
										$row.push("<td>");
										$row.push(oDevice.macAddress || oDevice.communicationDeviceAsRadio.flexNetId);
										$row.push("</td>");

										// Premise ID
										$row.push("<td>");
										$row.push(oDevice.configuration ? oDevice.configuration.premiseId : "");
										$row.push("</td>");

										// Relay
										$row.push("<td>");
										$row.push(sPropertyName);
										$row.push("</td>");

										// Tamper Timer
										$row.push("<td>");
										$row.push(sPropertyValue);
										$row.push("</td>");

										// End row
										$row.push("</tr>");
									}

									if (max > 0) {

										$("#tableTamperDetect tbody", $oDialogContent).html($row.join(''))
												.parents(".selected-points")
													.removeClass("hide")
													.find(".size").text(max);
									}
								}
							}
						}
					}
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, true, this.callback,null,null,null,true );
				},

				init : function () {

					this.ajaxCall();

					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

						sensus.util.exportcsv.generateCSV(
								oContentTabs.tamperDetect.csvUrl,
								oContentTabs.tamperDetect.csvRequest);
					});
				}
			},

			setupDemandResponse : {

				url : sFetchSummaryURL,

				csvUrl : 'api/export/generateDemandResponseSetupCSV',

				request : new FetchRequest({id : "" + iProcessId, type : "setupDemandResponse"}),

				csvRequest : oCsvRequest,

				callback : function (oResponse) {

					var $row;
					var max;
					var _get;
					var oProcessItems;
					var oDevice;
					var aLcmRelays;
					var oLcmRelay;
					var iLcmRelaysMax;
					var indexRelays;
					var sDeviceID;
					var sNetworkAddress;
					var sRelay;
					var nRelaySize;
					var sDeviceClass;
					var sEnrollmentGroup;
					var sTemperTime;
					var sStartRandz;
					var sDurationRandz;
					var aProperties;
					var nRelays;
					var iPropertiesLength;
					var oProperty;
					var sPropertyName;
					var sPropertyValue;
					var aTheRelays = [[],[],[]];
					var aRelayAux;
					var sActionType;

					if (oResponse && oResponse.processes && oResponse.processes.length > 0) {

						$.each(oResponse.processes, function (i,oProcess) {

							if (oProcess && oProcess.processItems && oProcess.processItems.length > 0 ) {

								sActionType 	= oProcess.action.actionType.actionTypeEnum;
								oProcessItems 	= oProcess.processItems;
								max 			= oProcessItems.length;
								$row 			= [];

								for (i = 0; i < max; i = i + 1) {

									oDevice 	= oProcess.processItems[i].device;

									if (sActionType.contains("GET")) {

										aProperties  = oProcess.processItems[i].properties;
									} else {

										aProperties  = oProcess.properties;
									}

									if (!aProperties || !aProperties.length) {
										continue;
									}

									iPropertiesLength = aProperties.length;

									sDeviceID 		= oDevice.deviceId || "--";
									sNetworkAddress = oDevice.macAddress || oDevice.communicationDeviceAsRadio.flexNetId || "--";
									sRelay 			= "--";
									sDeviceClass 	= "--";
									sStartRandz		= "--";
									sDurationRandz	= "--";
									sEnrollmentGroup = "--";
									sTemperTime		= "--";

									for (var indexProp = 0; indexProp < iPropertiesLength; indexProp ++) {
										oProperty 		= aProperties[indexProp];
										sPropertyName 	= oProperty.propertyName;
										sPropertyValue 	= oProperty.propertyValue;
										if (sPropertyName.indexOf("RELAY1_") >= 0) {

											aRelayAux = sPropertyName.split("RELAY1_");
											aTheRelays[0].push([aRelayAux[1],sPropertyValue?sPropertyValue:"--"]);

										} else if (sPropertyName.indexOf("RELAY2_") >= 0) {

											aRelayAux = sPropertyName.split("RELAY2_");
											aTheRelays[1].push([aRelayAux[1],sPropertyValue?sPropertyValue:"--"]);

										} else if (sPropertyName.indexOf("RELAY3_") >= 0) {

											aRelayAux = sPropertyName.split("RELAY3_");
											aTheRelays[2].push([aRelayAux[1],sPropertyValue?sPropertyValue:"--"]);
										}
									}

									for (var nRelays = 0; nRelays < 3; nRelays ++) {
										if (aTheRelays[nRelays] && aTheRelays[nRelays].length > 0) {
											for (var indexProperties = 0; indexProperties < iPropertiesLength; indexProperties ++) {

												oProperty 		= aProperties[indexProperties];
												sPropertyName 	= oProperty.propertyName;
												sPropertyValue 	= oProperty.propertyValue;
												sRelay = nRelays+1;
												nRelaySize = aTheRelays[nRelays].length;
												for (var countRelay = 0; countRelay < nRelaySize; countRelay ++)
												{
													aVerifyArray = aTheRelays[nRelays][countRelay];
													if (aVerifyArray[0] == "RANDOMIZE_START") {

														sStartRandz = aVerifyArray[1]!="--"?aVerifyArray[1] + "min":"--";
													} else if (aVerifyArray[0] ==  "RANDOMIZE_END") {

														sDurationRandz = aVerifyArray[1]!="--"?aVerifyArray[1] + "min":"--";
													} else if (aVerifyArray[0] == "ENROLLMENT_CODE") {

														sEnrollmentGroup = aVerifyArray[1];
													} else if (aVerifyArray[0] == "DEVICE_CLASS") {

														sDeviceClass = aVerifyArray[1];
													} else if (aVerifyArray[0] == "TAMPER_TIMEOUT") {

														sTemperTime = aVerifyArray[1];
													}
												}
											}

											// Start row
											$row.push("<tr>");

											// Device ID
											$row.push("<td>");
											$row.push(sDeviceID);
											$row.push("</td>");

											// Network Address or FlexNet Id
											$row.push("<td>");
											$row.push(sNetworkAddress);
											$row.push("</td>");

											// Relay
											$row.push("<td>");
											$row.push(sRelay);
											$row.push("</td>");

											// DeviceClass
											$row.push("<td>");
											$row.push($.sc.enums.internationalizeByLabel("com.sensus.dm.elec.device.model.DeviceClassEnum", sDeviceClass));
											$row.push("</td>");

											// Enrollment Group
											$row.push("<td>");
											$row.push(sEnrollmentGroup);
											$row.push("</td>");

											// Start Randomization
											$row.push("<td>");
											$row.push(sStartRandz);
											$row.push("</td>");

											// Duration Randomization
											$row.push("<td>");
											$row.push(sDurationRandz);
											$row.push("</td>");

											// Duration Randomization
											$row.push("<td>");
											$row.push(sTemperTime);
											$row.push("</td>");

											// End row
											$row.push("</tr>");

											if (max > 0) {

												$("#tableSetupDemandResponse tbody", $oDialogContent).html($row.join(''))
														.parents(".selected-points")
															.removeClass("hide")
															.find(".size").text(max);
											}
										}
									}

								}
							}
						});
					}
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, true, this.callback,null,null,null,true );
				},

				init : function () {

					this.ajaxCall();

					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

						sensus.util.exportcsv.generateCSV(
								oContentTabs.setupDemandResponse.csvUrl,
								oContentTabs.setupDemandResponse.csvRequest);
					});
				}
			},

			timeOfUsePricing : {

				url : sFetchSummaryURL,

				request : new FetchRequest({id : "" + iProcessId, type : "timeOfUsePricing"}),

				callback : function (oResponse) {

					if (oResponse && oResponse.processes && oResponse.processes.length > 0 && oResponse.processes[0].action) {

						var oTable;
						var oAction = oResponse.processes[0].action;
						var sProviderId;
						var sUnitMeasure;
						var sTrailingDigits;
						var iPriceTiersLength;
						var sCurrency;
						var sRow = "";

						if (oAction) {
							oTable = $("#time-of-use-pricing-table");
							sProviderId = oAction.priceFormat.providerId;
							sUnitMeasure = oAction.priceFormat.unitofMeasure;
							sTrailingDigits = oAction.priceFormat.trailingDigit;
							sCurrency = oAction.priceFormat.currency;
							iPriceTiersLength = oAction.priceTiers.length;

							sUnitMeasure = sensus.util.deviceoperation.setHanPricing.oMeasures[sensus.util.page.toHex(sUnitMeasure)];

							for (var i = 0; i < iPriceTiersLength; i++) {
								sRow += '<tr class="">';
								sRow += '<td>' + sProviderId + '</td>'; // Provider Id
								sRow += '<td>' + sensus.locale.get("action.setHanTimeOfUse.rate") + " " +  sensus.util.deviceoperation.setHanPricing.aTier[i] + '</td>'; // Rate
								sRow += '<td>' + sensus.locale.get("commons.pages.Tier") + " " + oAction.priceTiers[i].registerTier + '</td>'; // Tier Register
								sRow += '<td>' + oAction.priceTiers[i].rateLabel + '</td>'; // Tier Label
								sRow += '<td class="center">' + sensus.locale.get(sUnitMeasure) + '</td>'; // Unit of Measure
								sRow += '<td>' + sensus.util.deviceoperation.setHanPricing.oCurrency[sCurrency] + oAction.priceTiers[i].price + '</td>'; // Price
								sRow += '<td class="center">' + sTrailingDigits + '</td>'; // Trailing Digits
								sRow += '</tr>';
							}

							$("tbody", oTable).append($(sRow));

						}

					}

				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, true, this.callback,null,null,null,true );
				},

				init : function () {
					this.ajaxCall();
				}
			}
		};

	var oSummaryDeferred = $.Deferred();

	// Load Summary Dialog JSP
	fnAjaxContent("summary", $oDialogContent, oSummaryDeferred);

	$.when(oSummaryDeferred).then(function () {

		oCommunication 		 = $("#communication", $oDialogContent);
		oDemandResponse 	 = $("#demandResponse", $oDialogContent);
		oDemandRead			 = $("#demandRead", $oDialogContent);
		oImportHanDevices 	 = $("#importHanDevices", $oDialogContent);
		oTamperDetect 		 = $("#tamperDetect", $oDialogContent);
		oSetupDemandResponse = $("#setupDemandResponse", $oDialogContent);
		oTimeOfUsePricingResponse = $("#timeOfUsePricing", $oDialogContent);

		// Hide/Show tabs
		if (bHideTabs) {
			$.ajax({
				url : "summary/communication?" + new Date().getTime(),
				async : true,
				cache : false,
				method : "GET",
				success : function (sHtml) {
					$("#summary-container", $oDialogContent).removeClass("hide").html(sHtml);
					oContentTabs.communication.init();
				}
			});
		} else {

			if (sType == "sensus.dm.action.import.han.device") {

				fnHideTabs([oDemandResponse, oDemandRead, oTamperDetect, oSetupDemandResponse, oTimeOfUsePricingResponse]);

				iStartTab = 2; // Start Import HAN device

			} else if (sType == 'sensus.dm.action.demand.response') {

				fnHideTabs([oImportHanDevices, oDemandRead, oTamperDetect, oSetupDemandResponse, oTimeOfUsePricingResponse]);

				iStartTab = 1; // Start Demand Response

			} else if (sType == 'sensus.dm.action.get.read.data') {

				fnHideTabs([oImportHanDevices, oDemandResponse, oTamperDetect, oSetupDemandResponse, oTimeOfUsePricingResponse]);

				iStartTab = 3; // Start Demand Read

			} else if (sType == 'sensus.dm.action.set.tamper.detect.timer' || sType == 'sensus.dm.action.get.tamper.detect.timer') {

				fnHideTabs([oDemandResponse, oDemandRead, oImportHanDevices, oSetupDemandResponse, oTimeOfUsePricingResponse]);

				iStartTab = 4; // Start Tamper Detect

			} else if ((sType == 'sensus.dm.action.set.relay.configuration') || (sType == "sensus.dm.action.get.relay.configuration")) {

				fnHideTabs([oDemandResponse, oDemandRead, oImportHanDevices, oTamperDetect, oTimeOfUsePricingResponse]);

				iStartTab = 5; // Start Setup Demand Response

			} else if ((sType == 'sensus.dm.action.set.pricing')) {

				fnHideTabs([oDemandResponse, oImportHanDevices, oDemandRead, oSetupDemandResponse, oTamperDetect]);

				iStartTab = 6; // Start Time Of Use Pricing Setup

			} else {

				// Hide all tabs, show only Communication
				fnHideTabs([oDemandResponse, oImportHanDevices, oDemandRead, oTamperDetect, oSetupDemandResponse, oTimeOfUsePricingResponse]);
			}

			// Start tabs
			$.ajaxSetup({cache:false});
			$("#summary-tabs", $oDialogContent)
				.removeClass("hide")
				.tabs({
					active : iStartTab,
					// Call when load a tab
					load: function (event, ui) {

						sensus.util.page.startProgressBar();

						// Start tab content
						oContentTabs[$(ui.tab).attr("id")].init();

						$(ui.panel).removeClass($(ui.panel).attr("class")).addClass('summary communication-summary');
					}

				})
				// Refresh Button
				.delegate(".refresh", "click", function(e) {

					e.preventDefault();

					// Get Selected Tab
					var $tab = $(this).parent(),
						iActiveTab = $tab.tabs("option", "selected"),
						sTab = $tab.find("li").eq(iActiveTab).attr("id");

					// Refresh selected tab
					oContentTabs[sTab].ajaxCall();

				});
				$.ajaxSetup({cache:true});
		}
	});
};