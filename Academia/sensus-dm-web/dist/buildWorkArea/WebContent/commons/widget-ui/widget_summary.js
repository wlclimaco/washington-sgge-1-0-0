/**
 * @param sId
 * 			string, id of device
 * @param sType
 * 			string, type of summary
 * @return date
 * 			date java script format
 */
$.fn.summary = function (iProcessId, sType, bHideTabs, ActionId) {

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
		aProcesses = [new Process({id : iProcessId})],

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
		fnAjaxContent = function (sUrl, $oContent) {

			$.ajax({
				url : sUrl,
				async : false,
				cache : true,
				method : "GET",
				success : function (sHtml) {

					$oContent.html(sHtml);

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
				avg = (per * 100) / sum;
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
				oRadio = oDevice.radio || {customerId : "", flexNetId : "", eletricMeterFlexNetId : ""};
				sBaseFlexNetId = oRadio.eletricMeterFlexNetId || "";
				if (aDeviceList.deviceType = 'LCM')
				{

					aDevices.push({
						flexNetId						: "" + oRadio.flexNetId,
						baseFlexNetId					: sBaseFlexNetId,
						customerId						: oRadio.customerId,
						deviceId						: oDevice.deviceId,
						lifecycleStateEnum				: oDevice.lifecycleStateEnum,
						specificDeviceTypeEnumValue     : 2,//oDevice.lcmTypeEnum,
						deviceType						: {
												id			: oDevice.deviceTypeValue,
												type		: oDevice.deviceType
						},
						deviceType		: oDevice.deviceType
					});
				}else{
					aDevices.push({
							flexNetId			: "" + oRadio.flexNetId,
							baseFlexNetId		: sBaseFlexNetId,
							customerId			: oRadio.customerId,
							deviceId			: oDevice.deviceId,
							lifecycleStateEnum	: oDevice.lifecycleStateEnum,
							deviceType			: {
									id			: oDevice.deviceTypeValue,
									type		: oDevice.deviceType
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

			var sDescription = oProcess.description || "",
				processUtil  = sensus.util.process,
				oActionType,
				aDeviceIdType,
				sFlexNetId = "",
				sFlexNet = "",
				iLastPipeIndex,
				iAux;

			sDescription = sDescription
				.substring(sDescription.indexOf(']') + 1, sDescription.length)
				.replace(/[\[\]\,\.]/g, "");

			/* Remove from process description:
			 * Characters: [ ] . ,
			 * FlexNetId
			 */
			if (sDescription.contains("network address")) {

				// remove out the 'network address' string
				sFlexNetId = sDescription.substring(sDescription.indexOf("network address") + 16, sDescription.length);

				// remove out the last unnecessary characters
				sFlexNetId = sFlexNetId.replace(/[\,\.]/g, "");

				// find last pipe
				iLastPipeIndex = sFlexNetId.lastIndexOf("|");

				// get only device type, device sub type and flexNetId information
				iAux = sFlexNetId.substring(iLastPipeIndex, sFlexNetId.length).indexOf(" ");

				if (iAux != -1) {

					sFlexNetId = sFlexNetId.substring(0, iLastPipeIndex + iAux);
				}

				// split for pipe to get device type, device sub type and flexNetId
				aDeviceIdType = sFlexNetId.split("|");

				if (aDeviceIdType[1]) {

					sFlexNet = aDeviceIdType[1];

				} else {

					sFlexNet = "";

				}

				sDescription = sDescription
					.replace(sFlexNetId, sFlexNet)
					.replace("network address", sensus.locale.get("commons.pages.device"));

			}

			// Type/Action Category - Type/Action Name
			$('.action-type', $oSummaryDesc).text(
					sensus.locale.get(processUtil.fnCreateProcessName(oProcess)) + " - " +
					sensus.locale.get(processUtil.fnCreateProcessCategoryType(oProcess)));

			// Set on HTML Elements
			$('.process-id', $oSummaryDesc).text(oProcess.id);
			$('.request-by', $oSummaryDesc).text(sDescription);
			$('.initiate-by', $oSummaryDesc).text(sDescription);

			if (oProcess.processStatusEnum == "COMPLETED") {

				// Completed In case
				$("#completed-in", $oSummaryDesc).removeClass("hide");
				$('.time', $oSummaryDesc).text(sensus.util.process.fnCreateCompletedInColumn(
						sensus.util.process.fnCreateStatus(oProcess.processStatusEnum),
						oProcess.estimatedSecondsToComplete,
						oProcess.startTime,
						oProcess.endTime
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
		fnCreateFailedTable = function ($oTable, aProcessItems, oCount, bRetry,$Retry) {
			var $oTableRow = [],
				$aLi = [],
				oDevice,
				oRadio,
				oLocation,
				oProcessItem,
				sProcessItemStatusEnum,
				oProcessItemStatusEnum = {},
				sConnectionStatus,
				sState,
				sStatus,
				i = 0,
				iItemsLength = aProcessItems.length;

			// Valid oCount
			oCount = fnCountConfigValidator(oCount);

			// Iterate process items
			// Create Failed Table
			for (; i < iItemsLength; i = i + 1) {

				// Process Item
				oProcessItem = aProcessItems[i];
				sProcessItemStatusEnum = oProcessItem.processItemStatusEnum;

				// Device Validation
				oDevice = oProcessItem.device;
				oRadio = oDevice.radio || {flexNetId : ""};
				oLocation = oRadio.location || {address : ""};

				if (sProcessItemStatusEnum) {

					if (sProcessItemStatusEnum != 'COMPLETED') {

						// To group process item status
						if (oProcessItemStatusEnum.hasOwnProperty(sProcessItemStatusEnum)) {

							oProcessItemStatusEnum[sProcessItemStatusEnum]++;

						} else {

							oProcessItemStatusEnum[sProcessItemStatusEnum] = 1;
						}

						if (sProcessItemStatusEnum != 'RUNNING') {

							sState = sensus.locale.get("summary.text.processStatus." + sProcessItemStatusEnum);

							$oTableRow.push("<tr><td>" + (oDevice.deviceId || "") + "</td>" + // Device ID
									"<td>" + (oDevice.macAddress ? oDevice.macAddress : oRadio.flexNetId) + "</td>" + // Network Address or Flex Net Id
									"<td>" + ((oDevice.configuration ? oDevice.configuration.premiseId : "") || "") + "</td>" + // Premise ID
									"<td>" + oLocation.address + "</td>" + // Location Address
									"<td>" + sState + "</td>" + // State
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

						$aLi.push("<li class='fail'><strong>" + i + "</strong> " +
								(i > 1 ? sensus.locale.get("summary.text.smartPointsunreachables") : sensus.locale.get("summary.text.smartPointsunreachable")) +
								" <a href='#' id='unreachable' class='button small'>" +
								sensus.locale.get("summary.text.retryConnect") + "</a></li>");
						continue;
					}

					$aLi.push(
						"<li class='fail'>"
						+ (sensus.locale.get("summary.text.processStatusMessage."
								+ (i > 1 ? "plural." : "single.") + sStatus, "" + i))
						+ "</li>");
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

				request : new FetchRequest({id : "" + iProcessId, type : "communicationSummary"}),

				csvRequest : oCsvRequest,

				callback : function (oResponse) {

					var $oInfoTable = $("#informationTable", $oDialogContent),
						sDescDevices = " " + sensus.locale.get("commons.pages.smartPoints"),
						oProcess = oResponse.processes[0],
						iTotal = oProcess.totalSmartpoints || 0,
						iFailed = oProcess.failedSmartpoints || 0,
						iSuccess = 0;

					// Fills description of the process
					fnFillDescriptionProcess(oProcess, $('#summary-description', $oDialogContent));

					// Create Table with Failed Devices on process
					if (iFailed > 0) {

						// On iterate process items count all with COMPLETED status
						iSuccess = fnCreateFailedTable(
								$("#tableCommunicationSummary", $oDialogContent),
								oProcess.processItems,null,null,$("#failed-devices-group")).COMPLETED;

					} else {

						// Count process items with COMPLETED status
						iSuccess = fnCounter(oProcess.processItems).COMPLETED;

					}

					// Create Success Column Information
					fnCreateInformation(
							$oInfoTable.find("td:eq(0)"),
							(fnGetPercentage(iTotal, iSuccess) + "%"),
							(iSuccess + sDescDevices)
						);

					// Create Failed Column Information
					fnCreateInformation(
							$oInfoTable.find("td:eq(1)"),
							(fnGetPercentage(iTotal, iFailed) + "%"),
							(iFailed + sDescDevices)
						);

					sensus.util.page.stopProgressBar();
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, false, this.callback );

				},

				init : function () {

					this.ajaxCall();

					$(".csv", $oDialogContent).click(function (event) {

						event.preventDefault();

						sensus.util.page.startProgressBar();

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
					actionType		: {id: 8},
					actionTypeName	: "sensus.dm.action.get.demand.response.event.status",
					isMonitored		: true,
					onDemand		: true,
					actionId        : ActionId,
					devices			: []
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
						aDevices = [],

						FULLPARTICIPATION = "FullParticipation",
						PARTIALPARTICIPATION = "PartialParticipation",
						OPTOUT = "OptOut",
						PARTIALPARTICIPATIONANDOPTOUT = "PartialParticipationAndOptOut",
						COMPLETED = "COMPLETED",

						i = 0,
						max = aProcessItems.length;

					for (; i < max; i = i + 1) {

						oProcessItem = aProcessItems[i];
						oDevice = oProcessItem.device;
						oRadio = oDevice.radio || {flexNetId : ""};

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

						// Network Status
						$oTableRow.push("<td>" + (oDevice.remoteConnectStatusEnum || "") + "</td>");

						// Count Participation
						switch (sParticipation) {

						case FULLPARTICIPATION :
							iFullparticipation = iFullparticipation + 1;
							$oTableRow.push("<td>Yes</td><td>No</td><td>No</td>");
							break;

						case PARTIALPARTICIPATION :
							iPartialParticipation = iPartialParticipation + 1;
							$oTableRow.push("<td>No</td><td>Yes</td><td>No</td>");
							break;

						case OPTOUT :
							iOptOut = iOptOut + 1;
							$oTableRow.push("<td>No</td><td>No</td><td>Yes</td>");
							break;

						case PARTIALPARTICIPATIONANDOPTOUT :
							iOptOut = iOptOut + 1;
							$oTableRow.push("<td>No</td><td>Yes</td><td>Yes</td>");
							break;

						default :
							$oTableRow.push("<td></td><td></td><td></td>");

						}

						$oTableRow.push("</tr>");
					}

					// Set Failed Table on DOM
					if (max > 0) {

						$oTable.find("tbody").html($oTableRow.join(''));
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
						DEVICES : aDevices
					};
				},

				createDemandResponseDetails : function (oEpmAction) {

					var aDetails,
						iCriticalityLevel 	= oEpmAction.criticalityLevel,
						sEnrollmentCode 	= oEpmAction.enrollmentCode,
						sDutyCycleRate 		= oEpmAction.dutyCycleRate ? (oEpmAction.dutyCycleRate + " %") : "-",
						sLoadAdjustment 	= oEpmAction.loadAdjustment ? (oEpmAction.loadAdjustment + " %") : "-",
						sRandomizeStart		= oEpmAction.randomizeStart,
						sRandomizeEnd		= oEpmAction.randomizeEnd,
						sRandomize			= "-",
						sOffset 			= "-";

					// Randomize
					if (sRandomizeStart) {

						sRandomize = sensus.locale.get("systemintelligence.page.event.hanStart") + " ";
					}

					if (sRandomizeEnd) {

						if (sRandomizeStart) {

							sRandomize = sRandomize + " " + sensus.locale.get("commons.pages.and") + " ";

						} else {

							sRandomize = "";
						}

						sRandomize = sRandomize + sensus.locale.get("systemintelligence.page.event.hanEnd");
					}

					// Cooling
					if (oEpmAction.cooling) {
						sOffset = sensus.locale.get("systemintelligence.dialogDemandResponse.cooling")
									+ " " + oEpmAction.cooling + " "
									+ sensus.locale.get("systemintelligence.dialogDemandResponse.degrees");
					}

					// Add Heating
					if (oEpmAction.heating) {
						sOffset = sOffset + " " + sensus.locale.get("systemintelligence.dialogDemandResponse.heating")
									+ " " + oEpmAction.heating + " "
									+ sensus.locale.get("systemintelligence.dialogDemandResponse.degrees");
					}

					// Add Data on DOM
					aDetails = [sEnrollmentCode, sOffset, sDutyCycleRate, sLoadAdjustment, iCriticalityLevel, sRandomize];

					$("#demand-response-details dd", $oDialogContent).each(function (i, item) {
						$(item).html(aDetails[i]);
					});
				},

				seachProperty : function (processes, sProperty) {

					var aPropertiesItems = processes.properties;
					var iPropertiesMax;
					var indexProperties;

					for (indexProperties = 0, iPropertiesMax = aPropertiesItems.length;
						indexProperties < iPropertiesMax; indexProperties = indexProperties + 1) {

						oProperty = aPropertiesItems[indexProperties];

						// Read Value
						if (oProperty.propertyName == sProperty) {

							return $.date.dateFormat(oProperty.propertyValue + "000",
									sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i A");
						}
					}
				},

				callback : function (oResponse) {

					var $oInfoTable = $("#summaryDemandResponse", $oDialogContent),
						oProcess = oResponse.processes[0],
						aProcessItems = oProcess.processItems,
						oCount = oContentTabs.demandResponse.createFailedColumn(aProcessItems), // Create Failed Table
						iReceivedStart = aProcessItems.length,
						iFullParticipation = oCount.FULL_PARTICIPATION,
						iPartialParticipation = oCount.PARTIAL_PARTICIPATION,
						iOptOut = oCount.OPT_OUT,
						aDevices = oCount.DEVICES,
						iUncompleted = aDevices.length,
						aStartTime = oContentTabs.demandResponse.seachProperty(oProcess,"ACTION_DATE").split(" "),
						oDemandResponseDuration = oProcess.epmAction || oProcess.action;

						aStartTime = oContentTabs.demandResponse.seachProperty(oProcess,"ACTION_DATE").split(" ");

					// Create Columns Information
					// Start
					fnCreateInformation($oInfoTable.find("td:eq(0)"),
							aStartTime[1] + "<small>" + aStartTime[2] + "</small>",aStartTime[0]);

					// Duration
					if (oDemandResponseDuration) {
						fnCreateInformation($oInfoTable.find("td:eq(1)"),
								(oDemandResponseDuration.demandResponseDuration || ""),
								sensus.locale.get("commons.pages.Minutes"));
					}

					// Participation
					fnCreateInformation($oInfoTable.find("td:eq(2)"),
							fnGetPercentage(iReceivedStart, iFullParticipation) + "%",
							iFullParticipation + " " + sensus.locale.get("commons.pages.of") + " " + iReceivedStart);

					// Received Start
					fnCreateInformation($oInfoTable.find("td:eq(3)"),
							iReceivedStart, "");

					// Full Participation
					fnCreateInformation($oInfoTable.find("td:eq(4)"),
							iFullParticipation, "");

					// Partial Participation
					fnCreateInformation($oInfoTable.find("td:eq(5)"),
							iPartialParticipation, "");

					// Opt-out
					fnCreateInformation($oInfoTable.find("td:eq(6)"),
							iOptOut, "");

					// Create Demand Response Details
					oContentTabs.demandResponse.createDemandResponseDetails(oProcess.action);

					// Get Demand Response Event Status
					if (iUncompleted > 0) {

						// Prepare getDemandResponse Request Object
						oContentTabs.demandResponse.getDemandResponseRequest.devices = fnCreateDevices(aDevices);

						$(".ui-state-error")
							.removeClass("hide")
								.find("strong").text(iUncompleted);
					}

					sensus.util.page.stopProgressBar();
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, false, this.callback );
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
					$(".button", $oDialogContent).button().click(function(e) {

						e.preventDefault();

						$.ajaxValidator.fnDoCall(
								oContentTabs.demandResponse.getDemandResponseUrl,
								{parameters : oContentTabs.demandResponse.getDemandResponseRequest},
								false, null, sensus.locale.get("commons.pages.getDemandResponseEventStatusSucess"));
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

					if (oResponse && oResponse.processes
							&& oResponse.processes.length > 0) {

						aProcessItems = oResponse.processes[0].processItems;
						max = aProcessItems.length;
						$row = [];
						sDateFormatMask = sensus.settings.dateFormatMask.replace("yyyy", "yy");

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
							$row.push(oDevice.macAddress || oDevice.radio.flexNetId);
							$row.push("</td>");

							// Premise ID
							$row.push("<td>");
							$row.push(oDevice.configuration ? oDevice.configuration.premiseId : "");
							$row.push("</td>");

							if (aProperties && aProperties.length) {

								for (indexProperties = 0, iPropertiesMax = aProperties.length;
									indexProperties < iPropertiesMax; indexProperties = indexProperties + 1) {

									oProperty = aProperties[indexProperties];

									// Read Value
									if (oProperty.propertyName == "LAST_READ_VALUE") {

										$readValue = "<td>" + oProperty.propertyValue || "" + "</td>";

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

					$.ajaxValidator.fnDoCall( this.url, this.request, false, this.callback );
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
					processId		: null,
					actionType		: {id: 5},
					actionTypeName	: "sensus.dm.action.import.han.device",
					isMonitored		: true,
					onDemand		: true,
					devices			: []
				},

				processItems : [],

				countConfig : function (oProcessItem) {

					var sConnectionStatus = oProcessItem.device.connectionStatus;

					// TODO
					if (sConnectionStatus == "CONNECTED") {

						this.CONNECTED++;

					} else if (sConnectionStatus == "DISCONNECTED"
						|| sConnectionStatus == null) {

						this.DISCONNECTED++;
					}
				},

				countConfigFailedDevices : function (oProcessItem) {

					if (oProcessItem.processItemStatusEnum == "FAILED") {

						this.DEVICES.push(oProcessItem.device);
					}
				},

				callback : function (oResponse) {
					var $oInfoTable = $("#informationTableImportHan", $oDialogContent),
						sDescDevices = " " + sensus.locale.get("commons.pages.smartPoints"),
						oProcess = oResponse.processes[0],
						iFailed = oProcess.failedSmartpoints || 0,
						iSum = 0,
						oCount;
					// Set RNI ID on Retry Request
					oContentTabs.importHanDevices.retryConnectRequest.processId = oProcess.id;

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

					sensus.util.page.stopProgressBar();
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, false, this.callback );

					$("#unreachable", $oDialogContent).button();
				},

				init : function () {

					this.ajaxCall();

					// Retry Connect Failed
					$(".ss-widget-table-summary-kpi", $oDialogContent).delegate("#unreachable", "click", function(e) {

						var importTab = oContentTabs.importHanDevices;
						var oCount = fnCounter(importTab.processItems, {
							DEVICES : [],
							fnCount : importTab.countConfigFailedDevices
						});

						// Prepare getDemandResponse Request Object
						importTab.retryConnectRequest.devices = fnCreateDevices(oCount.DEVICES);

						importTab.retryConnectRequest.isRetry = true;

						$.ajaxValidator.fnDoCall("api/importFile/upload/action/importFile",
								{parameters : importTab.retryConnectRequest}, false,
								function (oResponse) {

							importTab.ajaxCall();
						});
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
						max = aProcessItems.length;
						$row = [];

						for (i = 0; i < max; i++) {

							oProcessItem = aProcessItems[i];
							oDevice 	 = oProcessItem.device;
							aProperties  = oResponse.processes[0].properties;

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
										$row.push(oDevice.macAddress || oDevice.radio.flexNetId);
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

					$.ajaxValidator.fnDoCall( this.url, this.request, false, this.callback );
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
					var $i;
					var max;
					var oProcessItems;
					var oDevice;
					var aLcmRelays;
					var oLcmRelay;
					var iLcmRelaysMax;
					var indexRelays;
					var sDeviceID;
					var sNetworkAddress;
					var sRelay;
					var sDeviceClass;
					var sEnrollmentGroup;
					var sRandomize;

					if (oResponse && oResponse.processItems && oResponse.processItems.length > 0) {

						oProcessItems 	= oResponse.processItems;
						max 			= oProcessItems.length;
						$row 			= [];

						for (i = 0; i < max; i++) {

							oDevice 	= oResponse.processItems[i].device;
							aLcmRelays 	= oDevice.lcmRelays;
							iLcmRelaysMax = aLcmRelays.length;

							if (aLcmRelays && iLcmRelaysMax) {

								for (indexRelays = 0; indexRelays < iLcmRelaysMax; indexRelays++) {

									oLcmRelay 		= aLcmRelays[indexRelays];
									sDeviceID 		= oDevice.deviceId || "--";
									sNetworkAddress = oDevice.macAddress || oDevice.radio.flexNetId || "--";
									sRelay 			= "--";
									sDeviceClass 	= "--";
									sEnrollmentGroup = "--";
									sRandomize 		= "--/--";

									if (oLcmRelay.startMinutes && ($.sc.isNullOrUndefined(oLcmRelay.endMinutes)
												|| oLcmRelay.endMinutes == ""))
									{
										sRandomize = oLcmRelay.startMinutes + "/-- minutes";
									} else if (oLcmRelay.endMinutes && ($.sc.isNullOrUndefined(oLcmRelay.startMinutes)
												|| oLcmRelay.startMinutes == ""))
									{
										sRandomize = "--/" + oLcmRelay.endMinutes + " minutes";
									} else if (oLcmRelay.startMinutes && oLcmRelay.endMinutes) {
										sRandomize = oLcmRelay.startMinutes + "/" + oLcmRelay.endMinutes + " minutes";
									}

									if (!$.sc.isNullOrUndefined(oLcmRelay.enrollmentCode)) {
										sEnrollmentGroup = oLcmRelay.enrollmentCode;
									}

									if (!$.sc.isNullOrUndefined(oLcmRelay.deviceClass)) {
										sDeviceClass = sensus.locale.get("smartpointdetail.tabs.about.lcmRelay."+oLcmRelay.deviceClass);
									}

									if (!$.sc.isNullOrUndefined(oLcmRelay.relay)) {
										sRelay = oLcmRelay.relay;
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
									$row.push(sDeviceClass);
									$row.push("</td>");

									// Enrollment Group
									$row.push("<td>");
									$row.push(sEnrollmentGroup);
									$row.push("</td>");

									// Randomize
									$row.push("<td>");
									$row.push(sRandomize);
									$row.push("</td>");

									// End row
									$row.push("</tr>");
								}

								if (max > 0) {

									$("#tableSetupDemandResponse tbody", $oDialogContent).html($row.join(''))
											.parents(".selected-points")
												.removeClass("hide")
												.find(".size").text(max);
								}
							}
						}
					}
				},

				ajaxCall : function () {

					$.ajaxValidator.fnDoCall( this.url, this.request, false, this.callback );
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
			}
		};

	// Load Summary Dialog JSP
	fnAjaxContent("summary", $oDialogContent);

	oCommunication 		 = $("#communication", $oDialogContent);
	oDemandResponse 	 = $("#demandResponse", $oDialogContent);
	oDemandRead			 = $("#demandRead", $oDialogContent);
	oImportHanDevices 	 = $("#importHanDevices", $oDialogContent);
	oTamperDetect 		 = $("#tamperDetect", $oDialogContent);
	oSetupDemandResponse = $("#setupDemandResponse", $oDialogContent);

	// Hide/Show tabs
	if (bHideTabs) {

		fnAjaxContent("summary/communication",
				$("#summary-container", $oDialogContent).removeClass("hide"));

		oContentTabs.communication.init();

	} else {

		if (sType == "sensus.dm.action.import.han.device") {

			fnHideTabs([oDemandResponse, oDemandRead, oTamperDetect, oSetupDemandResponse]);

			iStartTab = 2; // Start Import HAN device

		} else if (sType == 'sensus.dm.action.demand.response') {

			fnHideTabs([oImportHanDevices, oDemandRead, oTamperDetect, oSetupDemandResponse]);

			iStartTab = 1; // Start Demand Response

		} else if (sType == 'sensus.dm.action.demand.read') {

			fnHideTabs([oImportHanDevices, oDemandResponse, oTamperDetect, oSetupDemandResponse]);

			iStartTab = 3; // Start Demand Read

		} else if (sType == 'sensus.dm.action.set.tamper.detect.timer'
					|| sType == 'sensus.dm.action.get.tamper.detect.timer') {

			fnHideTabs([oDemandResponse, oDemandRead, oImportHanDevices, oSetupDemandResponse]);

			iStartTab = 4; // Start Tamper Detect

		} else if ((sType == 'sensus.dm.action.setup.demand.response')||(sType == "sensus.dm.action.get.demand.response.setup.status")) {

			fnHideTabs([oDemandResponse, oDemandRead, oImportHanDevices, oTamperDetect]);

			iStartTab = 5; // Start Setup Demand Response

		} else {

			// Hide all tabs, show only Communication
			fnHideTabs([oDemandResponse, oImportHanDevices, oDemandRead, oTamperDetect, oSetupDemandResponse]);

		}

		// Start tabs
		$("#summary-tabs", $oDialogContent)
			.removeClass("hide")
			.tabs({
				selected : iStartTab,
				// Call when load a tab
				load: function (event, ui) {

					sensus.util.page.startProgressBar();

					// Start tab content
					oContentTabs[ui.tab.offsetParent.id].init();

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
	}

};