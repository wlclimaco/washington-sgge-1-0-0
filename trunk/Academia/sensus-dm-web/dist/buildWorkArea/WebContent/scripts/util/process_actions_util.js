/**
 * @namespace sensus.util.process.actions
 * @description The main namespace for Processes dialogs.
 * @fileoverview Defines processes dialogs.
 */
sensus.util.process.actions = {

	/**
	 * Create Default Process Request Object
	 *
	 * @param aData
	 * 			Array, row data from clicked datatable row
	 * @param oColumn
	 * 			Object, datatable columns with index to access aData
	 * @return Object with one Process ID
	 */
	fnRequestDefault :  function (aData, oColumn) {

		return {
			processID : aData[oColumn.Id]
		};
	},

	/**
	 * Abort Dialog Setting Object
	 * Use on processDialog function
	 */
	abortDialogSetting : {

		width : 300, // dialog width

		title : function(aData, oColumn) { // dialog title

			var sActionName = aData[oColumn.ActionName];

			if (sActionName == "sensus.dm.action.send.han.text.message"
					|| sActionName == "sensus.dm.action.demand.response") {
				return sensus.locale.get("systemintelligence.ScheduledEvent.cancel");
			}

			return sensus.locale.get("process.action.expireAction.title");

		},

		url : "process/actionDialog", // dialog JSP URL

		sConfirmButton : sensus.locale.get("commons.pages.yes"), // confirm button text

		sCancelButton :  sensus.locale.get("commons.pages.no"), // cancel button text

		sApiMethodUrl : sensus.util.process.api.abort, // API Action URL

		sDialogText : function (aData, oColumn) { // dialog text

			var sActionName = aData[oColumn.ActionName];

			if (sActionName == "sensus.dm.action.send.han.text.message"
					|| sActionName == "sensus.dm.action.demand.response") {
				return sensus.locale.get("process.action.cancelDevice.title");
			}

			return sensus.locale.get("process.action.expireDevice.title");
		},

		fnGetSuccessMessage : function(aData, oColumn) { // customize message to display after Ajax API Call

			return sensus.locale.get(sensus.util.page.fnAbortProcessMessage(aData[oColumn.ActionName]));
		}
	},

	expireDialogSettings : {

		width : 300,

		title : function() {
			return sensus.locale.get("expire.confirmDialog");
		},

		url : "process/actionDialog",

		sConfirmButton : sensus.locale.get("expire.actionlabel"),

		sCancelButton :  sensus.locale.get("commons.pages.cancel"),

		sApiMethodUrl : sensus.util.process.api.expire,

		sDialogText : function (aData, oColumn) {
			return sensus.locale.get("process.action.expireDevice.title");
		},

		fnRequest : function (aData, oColumn, oDevice) {

			var aProcessList = [new Process({id : aData[oColumn.Id]})];
			var processItem  = {processItemStatusEnum : "RUNNING"};

			if (oDevice) {
				processItem.device = {radio : {flexNetId : oDevice.radio.flexNetId}};
			}

			aProcessList[0].processItems = [processItem];

			return new ProcessRequest({processList : aProcessList});
		},

		fnGetSuccessMessage : function(aData, oColumn) {
			return sensus.locale.get("expire.successfully");
		}
	},

	/**
	 * Remove Recent Request(s) Dialog Setting Object
	 * Use on processDialog function
	 */
	removeRecentRequestDialogSetting : {

		width : 300,

		title : function() {
			return sensus.locale.get("commons.pages.removeConfirm");
		},

		url : "process/actionDialog",

		sConfirmButton : sensus.locale.get("commons.pages.remove"),

		sCancelButton :  sensus.locale.get("commons.pages.cancel"),

		sApiMethodUrl : sensus.util.process.api.updateStatus,

		sDialogText : function (aData, oColumn) {
			return sensus.locale.get("action.longRunningProcessDialog.removeText");
		},

		fnRequest : function (aData, oColumn) {

			var aProcessList = [new Process({id : aData[oColumn.Id], monitoredInstance: false})];

			return new ProcessRequest({processList : aProcessList});
		},

		close : function () {
			sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();
		}
	},

	/**
	 * Show Success/Fail Message(s)
	 *
	 * @param sMsg
	 * 			String, a custom message
	 * @param oResponse
	 * 			Object, response object from BE with a message list to display
	 */
	fnCallbackShowMessage : function (sMsg, oResponse) {

		var sMessageID = "";

		if ($("#messaging-smartpoint-detail").length) {
			sMessageID = "messaging-smartpoint-detail";
		} else {
			sMessageID = "messaging-main";
		}

		if (oResponse.operationSuccess) {
			sensus.util.page.showMessage(sMessageID, sMsg + sensus.util.page.getMessageList(oResponse.messageList), "confirm");
		} else {
			sensus.util.page.showMessage(sMessageID, sensus.util.page.getMessageList(oResponse.messages), "error");
		}
	},

	/**
	 * Create a process dialog following Dialog Setting Object
	 *
	 * @param aData
	 * 			Array, data from BE
	 * @param oColumn
	 * 			Object with all columns name and columns index
	 * 			Example: {sColumnIdName : iIndex}
	 * @param sTableId
	 * 			String, table id
	 * @param oDialogSetting
	 * 			Object:
	 * 				title 			: String, action title
	 * 				width 			: int, action width
	 * 				sConfirmButton	: String, confirm button value name
	 * 				sCancelButton	: String, cancel button value name
	 * 				url				: String, JSP Dialog URL
	 * 				sApiMethodUrl	: String, Controller Action URL
	 * 				sDialogText		: String, action description
	 * 				fnGetSuccessMessage : Function, Will call when Ajax Call finish, the return should be a String to display
	 *
	 * @return Dialog Object
	 */
	processDialog : function (aData, oColumn, sTableId, oDialogSetting, oDevice) {

		// Ajax Callback Function
		function fnCallback (oResponse) {

			// table reload
			sensus.widgets.datatable.reloadTable($("#" + sTableId).dataTable());

			// show message
			if (oDialogSetting.fnGetSuccessMessage) {

				sensus.util.process.actions.fnCallbackShowMessage(
						oDialogSetting.fnGetSuccessMessage(aData, oColumn) || "", oResponse);
			}
		}

		return {

			title : oDialogSetting.title(aData, oColumn),

			width : oDialogSetting.width,

			close : oDialogSetting.close,

			buttons : (function() {

				var oButtons = {};

				oButtons[oDialogSetting.sConfirmButton] = function() {

					var oRequest;

					// if not fnRequest call fnRequestDefault
					if (oDialogSetting.fnRequest) {

						oRequest = oDialogSetting.fnRequest(aData, oColumn, oDevice);

					} else {

						oRequest = sensus.util.process.actions.fnRequestDefault(aData, oColumn);
					}

					$.ajaxValidator.fnDoCall(oDialogSetting.sApiMethodUrl, oRequest, false, fnCallback);

					// close dialog
					$("#action-dialog").dialog('close');
				};

				oButtons[oDialogSetting.sCancelButton] = function() {

					$("#action-dialog").dialog('close');

				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load(oDialogSetting.url, function() {

					actionDialog.removeClass("waiting");

					// dialog text
					$("#selected").append(oDialogSetting.sDialogText(aData, oColumn));

				});

				actionDialog.dialog('open');
			}
		}
	},

	/**
	 * Schedule Event View Dialog
	 * @param Schedule Id
	 */
	eventView : function (iId, sType, oTimeZone) {

		// Function for to load groups and tags list, that the schedule have.
		function fnLoadList(oTable, iCount, sText, data) {

			var idataLength = data.length;

			if (idataLength != 0) {

				var sTableLines = "";
				var	x 			= "";
				var	sData 		= "";

				// Create table lines
				for (var i = 0; idataLength > i; i++) {

					sTableLines += '<tr>';
					sData = data[i];

					if (i == 0) {
						sTableLines += '<td>'+ sText + '</td>';
					} else {
						sTableLines += '<td> </td>';
					}

					sTableLines += '<td>'+ sData.name + '</td>';
					sTableLines += '<td>' + sData.devicesCount + '</td>';
					sTableLines += '</tr>';
				}

				oTable.append(sTableLines);
			}
		};

		return {

			title : sensus.locale.get("scheduledTou.page.titleDialog"),

			form : 'dialog',

			width : 790,

			open : function () {
				sensus.util.page.startProgressBar();
			},

			action : function (actionDialog) {

				actionDialog.addClass('waiting');

				actionDialog.load("schedule/openScheduleDialog", function() {

					var sEditType 		= "schedule";
					var oFetchRequest 	= new FetchRequest({id : iId, type: sType});

					var	fnCallBack = function (oGroupResponse) {

						var schedule		= oGroupResponse.schedules[0];
						var action			= schedule.dmAction;
						var actionType		= action.actionType;
						var oTableGroupTag	= $("#table-group-tag");
						var oDateScheduled 	= $("#dateScheduled");
						var oEventName 		= $("#event-name");
						var oEventDescription = $("#event-description");

						// Call the function fnCountElement for to load group list.
						fnLoadList(oTableGroupTag, 0, "groups", action.groups);

						// Call the function fnCountElement for to load tag list.
						fnLoadList(oTableGroupTag, 0, "tags", action.tags);

						// Populate schedule dialog
						if (!$.sc.isNullOrUndefined(schedule.name)) {

							oEventName.text(schedule.name);

						} else {

							oEventName.text("");
						}

						if (!$.sc.isNullOrUndefined(schedule.description)) {

							oEventDescription.text(schedule.description);

						} else {

							oEventDescription.text("");
						}

						// Date Format
						oDateScheduled.text($.date.dateFormat(schedule.startTime, "MM dd, yy h:i A", oTimeZone));

						$("#repeat-enum").text(sensus.locale.get("commons.pages."+ schedule.frequency.frequencyTypeEnum));
						$("#action-name").text(sensus.locale.get(actionType.actionCategoryEnumNameValue));
						$("#action-description").text(sensus.locale.get(actionType.actionTypeEnumNameValue));

						// If action description is equals Initiate Demand Reset, the steps are shown at dialog.
						if (actionType.actionTypeEnumNameValue == "commons.pages.initiate.demand.reset") {
							$(".advanced-search-container").show();
						}

						$("#total-devices").text(action.totalDevices || 0);

						// Function for action of edit schedule button.
						$('#event-fields-container-event').delegate('.edit-schedule', 'click', function(e) {

							var $content;
							var $tabMenu;

							e.preventDefault();

							actionDialog.dialog("close");

							// When in System Intelligence
							if ($("#menu-systemintelligence").hasClass("active")) {

								if ($.fn.pageLoader.currentPage() == "schedule") {

									sensus.util.session.create({
										key : "SelectedFilters",
										value : $.fn.pageLoader.queryString($.fn.pageLoader.parameterNames([$.fn.pageLoader.pageParameter, "iDisplayStart"]))
									});

								} else {

									sensus.util.session.remove(["SelectedFilters"]);
								}

								$content = $("#tabs-content");
								$tabMenu = $("#schedule");

							} else {

								$content = $("#load");
							}

							$.fn.pageLoader.load("schedule/edit?id=" + iId + "&type=edit&editType=" + sEditType, $content, $tabMenu, function() {

								sensus.util.page.stopProgressBar();
							});
						});

						sensus.util.page.stopProgressBar();
					}

					if (sType === "openUpdateByAction") {

						sEditType = "action";
					}

					$(".button").button();

					// Function for make fetchSchedule by id, passing schedule Id and type into fetchRequest.
					$.ajaxValidator.fnDoCall("api/schedule/fetch", oFetchRequest, false, fnCallBack);

				});

				actionDialog.dialog('open');
			}
		}
	},

	/**
	 * Device Total/Failed Description Dialog
	 * Show devices from process
	 *
	 * @param iSummaryType
	 * 			int: 1 to Total Summary Type
	 * 				 2 to Failed Summary Type
	 * 				 Check sensus.util.process.summaryType Object
	 * @param iId
	 * 			int, Process ID or Schedule ID
	 * @param bScheduled
	 * 			Boolean, if Scheduled or not
	 * @return Dialog Object
	 */
	smartpointsDialog : function (iSummaryType, iId, bScheduled) {

		var FAILED = sensus.util.process.summaryType.failed;
		var	sTitle;

		if (FAILED == iSummaryType) {

			sTitle = sensus.locale.get("commons.pages.SmartPointListError");

		} else {

			sTitle = sensus.locale.get("commons.pages.SmartPointList");
		}

		return {

			title : sTitle,
			form  : 'dialog',
			width : 960,

			open : function() {
				sensus.util.page.startProgressBar();
			},

			close : function() {
				$("#processing-list-container").hide();
			},

			action : function(actionDialog) {

				$('#action-dialog').load("process/deviceList", function() {

					var fnCreateMessageColumn = sensus.util.process.fnCreateMessageColumn;

					/**
					 * Check if is valid process
					 *
					 * @param actionTypeName
					 * 			String, action type name from process
					 * @return Boolean, always return true (invalid process)
					 * 					except if action type name is delete or import han device
					 */
					function isValidProcess(actionTypeName) {

						if (actionTypeName == "sensus.dm.action.delete.han.device"
							|| actionTypeName == "sensus.dm.action.import.han.device") {

							return false;
						}
						return true;
					};

					/**
					 * Check if is valid process item
					 *
					 * @param messageError
					 * 			String, error message from process item
					 * @return Boolean, always return true (invalid process item)
					 * 					except if message error is invalid network address or device id
					 */
					function isInvalidProcessItem(messageError) {

						if (messageError == "sensus.dm.common.process.invalid.networkaddress"
							|| messageError == "sensus.dm.common.process.invalid.deviceid") {

							return false;
						}

						return true;
					};

					// Callback Function of Ajax Call
					var sFailMessage = "sensus.dm.common.process.nc.process.";

					var	fnCallback = function (oProcessResponse) {

						var aProcessItems;
						var	oProcessItem;
						var	oDevice;
						var	oRadio;
						var	oLocation;
						var	iFlexNetId;
						var	iDeviceId;
						var	sDeviceTypeEnum;
						var	sTypeEnum;
						var	$tr = "";
						var	bValidProcess;
						var	sApiUrl;
						var	sApiExport;
						var	oRequest;
						var	iProcessItemStatus;
						var	i;

 						if (oProcessResponse.processes.length > 0) {

 							aProcessItems = oProcessResponse.processes[0].processItems;

 							// If Scheduled, all devices are valid, else, makes the verification for to confirm if the devices are valid.
 							if (bScheduled) {

 								bValidProcess = true;

 							} else if (oProcessResponse.processes[0].epmAction) {

 								bValidProcess = isValidProcess(oProcessResponse.processes[0].epmAction.actionType.actionTypeEnumNameValue);
 							}

 							for (i in aProcessItems) {

 								oProcessItem = aProcessItems[i],
 								oDevice		 = oProcessItem.device;
 								oRadio		 = oDevice.radio || {flexNetId : "", location : {address : ""}};
 								oLocation	 = oRadio.location || {address : ""};
 								iFlexNetId	 = oDevice.macAddress || oRadio.flexNetId || "";
 								iDeviceId	 = oDevice.deviceId || "";
 								sDdeviceTypeEnum = oDevice.deviceType;
 								sTypeEnum = "";

 								if (oDevice.lcmTypeEnum) {
 									sTypeEnum = oDevice.lcmTypeEnum;
 								} else if (oDevice.hanDeviceTypeEnum) {
 									sTypeEnum = oDevice.hanDeviceTypeEnum;
 								}

 								// if summary type is failed and process item is ok
 								// process item is ok when any status is complete or running
 								if (FAILED == iSummaryType
 										&& (oProcessItem.processItemStatusEnumValue == 5 || oProcessItem.processItemStatusEnumValue == 1)) {
 									continue;
 								}

 								$tr += "<tr>";

 								// check valid process item to set up link to device detail page
 								if (bValidProcess && isInvalidProcessItem(oProcessItem.message)) {

 									$tr += "<td><a class='alist' href='device/detail?id=" + iFlexNetId + "&deviceType=" + sDdeviceTypeEnum + "&typeEnum=" + sTypeEnum + "'>" + iDeviceId + "</a></td>"
										+ "<td><a class='alist' href='device/detail?id=" + iFlexNetId + "&deviceType=" + sDdeviceTypeEnum + "'>" + iFlexNetId + "</a></td>";

 								} else {

 									$tr += "<td>" +  iDeviceId + "</td>"
 											+ "<td>" + iFlexNetId + "</td>";

 								}

 								// Message Error
 								$tr += "<td>" + (oLocation.address || "") + "</td>"
 										+ "<td class='columError'>" + fnCreateMessageColumn(oProcessItem.message) + "</td>"
 										+ "</tr>";
 							}

 							$("#list-table").html($tr);
 						}

 						if (FAILED == iSummaryType) {

 							$("#header").addClass("small-table error");

 						} else {

 							$(".columError").addClass("hide");
 						}
 					};

 					// define API Method URL to fetch and export
 					if (!$.ajaxValidator.fnIsNullOrUndefined(bScheduled) && bScheduled) {

 						// By Schedule
 						sApiUrl = sensus.util.process.api.fetchScheduled;
 						sApiExport = "api/export/generateScheduleDeviceCSV";

 						// Request
						oRequest = new ScheduleRequest({schedule : new Schedule({id : iId})});

 					} else {

 						// By Process ID
 						sApiUrl = sensus.util.process.api.fetchById;
 						sApiExport = "api/export/generateSummaryCSV";

						// process item status enum
						if (FAILED == iSummaryType) {

							iProcessItemStatus = 2; // FAILED

						} else {

							iProcessItemStatus = 1; // RUNNING

						}

						// Request
						oRequest = new InquiryProcessRequest({processes : [new Process({id : iId})], processItemStatusEnum : iProcessItemStatus});
 					}

 					// Ajax Call
 					$.ajaxValidator.fnDoCall(sApiUrl, {processID : iId}, false, fnCallback);

 					$("#processing-list-container").show();

 					// To  Generate the archive CSV
 					$("#csvDialog").click(function (e) {

 						e.preventDefault();

 						sensus.util.page.startProgressBar();

 						sensus.util.exportcsv.generateCSV(sApiExport, oRequest);
 					});

					sensus.util.page.stopProgressBar();
				});

				actionDialog.addClass('waiting');
				actionDialog.dialog('open');
			}
		}
	},

	/**
	 * Send Han Text Message Dialog
	 *
	 * @param iProcessId
	 * 			[int], the process id
	 * @return Dialog Object
	 */
	sendHanTextMessage : function (iProcessId) {

		return {

			title : sensus.locale.get("action.sendHanTextMessageDialog.eventDetailsTitle"),

			width : 790,

			minheight: 400,

			open : function () {

				sensus.util.page.startProgressBar();
			},

			action : function (actionDialog) {

				var $actionDialog = $("#action-dialog");

				$actionDialog.load('process/hanMessage', function() {

					var fnCallback = function (oProcessResponse) {

						if (oProcessResponse.processes.length > 0) {

							var oFieldMessage 	= $('.message', $actionDialog);
							var oFieldDuration 	= $('.duration', $actionDialog);
							var oFieldDevices 	= $('.devices', $actionDialog);
							var oFieldInitiated = $('.initiated', $actionDialog);
							var oProcess 		= oProcessResponse.processes[0];
							var aProperties 	= oProcess.properties;
							var oProperty;
							var i;
							var length;

							oFieldDevices.text(oProcess.totalSmartpoints || "");
							oFieldInitiated.text(oProcess.createUser || "");

							if (aProperties) {

								for (i = 0, length = aProperties.length; i < length; i = i + 1) {

									oProperty = aProperties[i];

									if (oProperty.propertyName == "HAN_TEXT_MESSAGE_TEXT") {

										oFieldMessage.text(oProperty.propertyValue || "");

									} else if (oProperty.propertyName == "HAN_TEXT_MESSAGE_DURATION") {

										oFieldDuration.text(oProperty.propertyValue || "");
									}
								}
							}
						}

						sensus.util.page.stopProgressBar();
					}

					$.ajaxValidator.fnDoCall(sensus.util.process.api.fetchHanTextMessageInfo, {processID : iProcessId}, false, fnCallback);
				});

				actionDialog.dialog('open');
			}
		};
	}
};