/**
 * @namespace sensus.util.process
 * @description The namespace for Process Util functionality.
 * @fileoverview Defines Process Util functionality.
 */
sensus.util.process = {

	/**
	 * All Process API URL
	 */
	api : {
		fetch : "api/process/fetch",
		abort : "api/action/abort",
		updateStatus : "api/process/update",
		update : "api/action/update",
		fetchById : "api/process/fetchById",
		fetchScheduled : "api/process/fetchScheduled",
		checkRNIStatus : "api/process/checkRNIStatus",
		fetchCountMonitored : "api/process/fetchCountMonitored",
		fetchHanTextMessageInfo : "api/process/fetchHanTextMessageInfo",
		expire : "api/process/expire"
	},

	expireableActions : ["sensus.dm.action.demand.response", "sensus.dm.action.send.han.text.message",
	                      "sensus.dm.action.connect.han.device", "sensus.dm.action.disconnect.han.device"],

	/**
	 * Summary Type
	 */
	summaryType : {
		total : 1,
		failed : 2
	},

	dataConfig : [{sDateName : " day",    iSeconds : 86400}, // Day(s)
                   {sDateName : " hour",   iSeconds : 3600},  // Hour(s)
                   {sDateName : " minute", iSeconds : 60}],   // Minute(s)

	/**
	 * Convert seconds in minutes, hours or days
	 *
	 * @param iSeconds
	 * 			int, seconds
	 * @returns string converted
	 */
	fnConvertCompletedInTime : function (iSeconds) {

		var aDataConfig = sensus.util.process.dataConfig;
		var length = aDataConfig.length;
		var i = 0;
		var oDataConfig;
		var iAux;

		for (; i < length; i = i + 1) {

			oDataConfig = aDataConfig[i];

			if (iSeconds >= oDataConfig.iSeconds) {

				iAux = iSeconds / oDataConfig.iSeconds;
				return iAux.toFixed(2) + oDataConfig.sDateName + (iAux > 1 ? "s" : ""); // add 's' if greater than one
			}
		}

		return sensus.locale.get("longRunning.table.Less.one.minute");
	},

	/**
	 * Create Completed In Column following rules
	 * Convert seconds in minutes, hours or days
	 *
	 * @param sStatus
	 * 			String, Process Status value after fnCreateStatus function
	 * @param iSecToComplete
	 * 			int, Process attribute: estimatedSecondsToComplete
	 * @param iStartTime
	 * 			int, Process attribute: startTime
	 * @param iEndTime
	 * 			int, Process attribute: endTime
	 * @returns string converted to set up on Completed In Column
	 */
	fnCreateCompletedInColumn : function (sStatus, iSecToComplete, iStartTime, iEndTime) {
		// if status is completed
		if (sStatus == sensus.locale.get("commons.pages.completed")) {

			if (iSecToComplete != null) {

				return sensus.util.process.fnConvertCompletedInTime(iSecToComplete);
			}

			if (iEndTime != null) {

				return sensus.util.process.fnConvertCompletedInTime((iEndTime - iStartTime) / 1000);
			}
		}

		return "";
	},

	/**
	 * Create link for Devices Dialog
	 * @param iCountDevices
	 * 			int, count of devices greater than zero
	 * @param iSummaryType
	 * 			int, Summary Type
	 * @param iProcessId
	 * 			int, Process ID
	 * @param bScheduled
	 * 			Boolean, if true call fetch by schedule, if not call fetch by id
	 */
	fnCreateLinkSmartpointsDialog : function(iCountDevices, iSummaryType, iProcessId, bScheduled) {

		if (iCountDevices > 0) {

			return $("<a href='#'>" + iCountDevices + "</a>").click(
					function(e) {

						e.preventDefault();

						sensus.util.actiondialog.launchActionDialog("smartpointsDialog",
								sensus.util.process.actions.smartpointsDialog(iSummaryType, iProcessId, bScheduled));
					});
		}

		return 0;
	},


	/**
	 * Create the Status Column text with Process Status Enum from BE
	 * @param oProcess
	 * 			Object
	 */
	fnCreateStatus : function (oProcess) {

		if (oProcess.processStatusEnum == "ABORTED") {

			// Status Column
			if (oProcess.action && (oProcess.action.actionType.actionTypeEnumNameValue == "sensus.dm.action.send.han.text.message"
					|| oProcess.action.actionType.actionTypeEnumNameValue == "sensus.dm.action.demand.response")) {

				return sensus.locale.get("summary.text.processStatus.CANCELLED");
			}

			return sensus.locale.get("summary.text.processStatus.EXPIRED");
		}

		if (!$.sc.isNullOrUndefined(oProcess.processStatusEnum)) {

			return sensus.locale.get("commons.pages." + oProcess.processStatusEnum.toLowerCase());

		} else {

			return sensus.locale.get("commons.pages." + oProcess.toLowerCase());
		}
	},

	/**
	 * Create the Action Name Column text with necessaries HTML tags
	 * @param sActionName
	 *			String, Process Action Name
	 * @param sStatus
	 *			String, Process Status
	 * @param iProcessId
	 * 			int, Process ID
	 *
	 */
	fnCreateActionNameColumn : function (sActionName, sStatus, iProcessId) {

		sActionName = sensus.locale.get(sActionName);

		if (sActionName === sensus.locale.get("sensus.dm.action.send.han.text.message")
				&& sStatus != sensus.locale.get("filter.status.scheduled.label")) {

			return $("<a href='#' class='action-info'>" + sActionName + "</a>")
						.click(function(e) {

							e.preventDefault();

							sensus.util.actiondialog.launchActionDialog("sendHanTextMessageDialog",
									sensus.util.process.actions.sendHanTextMessage(iProcessId));
						});
		}

		return sActionName;
	},

	/**
	 * Create the Event Column text with necessaries HTML tags
	 * @param iId
	 *			int, Process ID
	 * @param sEvent
	 *			String, Process Event Description to format
	 * @param sType
	 *			String, Process Action Type
	 * @param sStatus
	 *			String, Process Status
	 * @param sDeviceSubType
	 * 			String, Device Sub Type
	 */
	fnCreateEventColumn : function (iId, sEvent, sType, sStatus, sTableId, sDeviceType, sDeviceSubType) {

		sType = sensus.locale.get(sType);

		var DOWNLOAD_TEXT = "File Downloaded"; // Do not internationalize this String - Coming from BE this hard text
		var API_PROCESS_URL = "/export/downloadCsvFile";
		var EXPORT_TYPE = "EXPORT";

		// Export
		if (sType.toUpperCase() == EXPORT_TYPE) {

			// Complete
			if (sStatus == sensus.locale.get("table.type.complete")) {

				// Check if CSV already downloaded
				if (sEvent == DOWNLOAD_TEXT) {

					return sensus.locale.get('table.type.downloaded');

				} else {

					// Download link
					return sDowloadLink = $('<a class="download-css-process" href="#" id="export-csv-file-success-lrp">'
								+ sensus.locale.get('table.type.downloadCSV') + '</a>').click(

									function(e) {

										e.preventDefault();

										window.location = sensus.settings.appRoot + API_PROCESS_URL + "?fileName=" + sEvent + "&id=" + iId + "&updateCSVDownloaded=" + true;

										sensus.widgets.datatable.reloadTable($("#" + sTableId).dataTable());
									});
				}

			// Processing
			} else if (sStatus == sensus.locale.get("commons.pages.in_process")) {

				return sensus.locale.get('table.type.download.waiting');

			// Aborted
			} else if (sStatus == sensus.locale.get("commons.pages.aborted")) {

				return sensus.locale.get("commons.pages.GENERATE_CSV_FILE");
			}

		} else {

			// Generate Link

			if (sEvent) {

				return sensus.util.generatelinkaction.generateLink(sEvent, sStatus, sType, iId, sDeviceType, sDeviceSubType);
			}

			return "";
		}
	},

	/**
	 * Create Process Category Type
	 *
	 * @param oProcess
	 * 			{Object}, the process
	 * @return {String} the process type/action name
	 */
	fnCreateProcessCategoryType : function (oProcess) {

		if (oProcess.action && oProcess.action.actionType) {

			return oProcess.action.actionType.actionCategoryEnumNameValue;
		}

		if (oProcess.processType && oProcess.processType.processCategory) {

			return oProcess.processType.processCategory.name;
		}

		return "";
	},

	fnCreateProcessDeviceType : function (oProcess) {
		return oProcess.propertyDeviceType;
	},

	/**
	 * Create Process Category Name
	 *
	 * @param oProcess
	 * 			{Object}, the process
	 * @return {String} the process type/action category
	 */
	fnCreateProcessName : function (oProcess) {

		if (oProcess.action && oProcess.action.actionType) {

			return oProcess.action.actionType.actionTypeEnumNameValue;
		}

		if (oProcess.processType) {

			return oProcess.processType.description;
		}

		return "";
	},

	/**
	 * Get Process Types
	 * Get action_type_all parameter from URL and return only the process types
	 * The Process types will be numbers, different of action categories that will be
	 * strings like 'sensus.dm.action.category.han'
	 *
	 * @param aTypes
	 * 			{Array}, array of type
	 * 			example: ['sensus.dm.action.category.han', '1', '2']
	 * @param {Array} of Process Types
	 * 			example: [{id : 1}, {id : 2}]
	 */
	fnGetProcessTypes : function (aTypes) {

		var aProcessTypes = [];
		var i;
		var iLengthTypes;
		var type;

		if (aTypes && aTypes.length) {

			for (i = 0, iLengthTypes = aTypes.length; i < iLengthTypes; i = i + 1) {

				// try parse to int to find process types filters
				type = parseInt(aTypes[i], 10);

				if (type) {

					aProcessTypes.push({id : type});
				}
			}
		}

		return aProcessTypes.length > 0 ? aProcessTypes : null;
	},

	/**
	 * Create Message Failed Column
	 *
	 * @param {String} sData, the message
	 * @return ${String} the decoded message
	 */
	fnCreateMessageColumn : function (sData) {

		var sMessage 	= "sensus.dm.common.process.";
		var sRNIMessage = "sensus.rni";
		var aDecode;

		// If sData null, undefined or []
		if (!sData || sData == "[]") {

			return "";
		}

		sData = sData.replace(/[\[\]]/g, ""); // remove "[" and "]"

		// Id NC Process
		if (sData.contains(sMessage)) {

			aDecode = sData.split(/[\*\ ]/g); // split by space or "*"

			if (aDecode.length > 1) {

				return sensus.locale.get(aDecode[0], aDecode[1]);
			}

			return sensus.locale.get(aDecode[0]);
		}

		// Message of RNI
		if (sData.contains(sRNIMessage)) {
			return sensus.locale.get(sData);
		}

		// If RepidNotFound
		if (sData.contains("RepidNotFound")) {

			return sData.replace("RepidNotFound, ");
		}

		// If all letters is upper case
		if (sData == sData.toUpperCase()) {

			return sensus.locale.get("summary.text.processStatus." + sData);
		}

		// If the string begins with "Remote"
		if (sData.substring(0,6) == ("Remote")) {

			sData = sData.toLowerCase().replace("_", " "); // put the string in lower case and remove "_"
			sData = sData.substring(0,1).toUpperCase().concat(sData.substring(1)); // put the first character to upper case
		}

		// Other cases
		return sensus.locale.get(sData).replace(/[\[\]]/g, "");
	}
}