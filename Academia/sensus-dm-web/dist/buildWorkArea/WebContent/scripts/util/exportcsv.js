/**
 * @namespace The main namespace for export-csv functionality.
 * @fileoverview Defines common export-csv functionality.
 * @author Cristiane Cobo
 */
sensus.util.exportcsv = {

	/**
	 * Set Generate CSV Event
	 *
	 * Set the click event for generate CSV functionality
	 *
	 * @param oExportConfig
	 * 		Object. the export config object
	 * 		url : the url
	 * 		$element : the element to target the click event
	 * 		getGenerateRequestCSV : the function to fill a request object
	 */
	setGenerateCSVEvent : function(oExportConfig) {

		oExportConfig.$element.click( function (e) {

			e.preventDefault();

			if (!$(this).hasClass("disabled")) {

				sensus.util.page.startProgressBar();

				sensus.util.exportcsv.generateCSV(
						oExportConfig.url,
						oExportConfig.getGenerateRequestCSV());
			}
		});
	},

	/**
	 * @param sInsertProcessUrl
	 * 			string, url for insert process
	 * @param sGenerateFileUrl
	 * 			string, url for generate file
	 * @param sOptionalAjaxParam
	 * 			string, Optional Parameter to send with actions
	 */
	generateCSV : function(sGenerateFileUrl, oRequest) {

		var iProcessId = null;
		var bTimeOut = true;
		var sFileName = null;
		var bIsError = false;
		var bUpdateCSVDownloaded = true;

		var fnCallback = function(oResponse) {

			iProcessId = oResponse.processes[0].id;
			sFileName = oResponse.fileName;

			if(iProcessId != null && sFileName != null) {

				oRequest.processId = iProcessId;
				oRequest.fileName = sFileName;

				//$.ajaxValidator.fnDoCall (sensus.settings.appRoot + sGenerateFileUrl, oRequest, true, fnGenerateCallback, null, false, true);

				// Start Loading Dialog
				sensus.util.page.startProgressBar();

				// Ajax Async Call
				$.ajax({
					dataType : "json",
					type : "POST",
					contentType : "application/json; charset=utf-8",
					url : sGenerateFileUrl,
					data : $.toJSON(oRequest),
					async : true,
					success	: function (oResponse) {

						bTimeOut = false;

						// Validate oResponse
						var oValidResponse = $.ajaxValidator.fnValidResponse(oResponse);

						// Show Error Message
						if (!oValidResponse.bIsValid) {

							sensus.util.page.showMessage("messaging-main", oValidResponse.sErrorMessage, "error");

							// Stop Progress bar
							sensus.util.page.stopProgressBar(null, false);

							bIsError = true;

							return;
						}

						// Stop Progress bar
						sensus.util.page.stopProgressBar(null, false);
					},
					error : function (oError) {

						console.log(oError);
					}
				});

				var iCount = 0;
				(function fnVerify(){
					iCount += 1;
					if (iCount == 25) {

						if(!bIsError && bTimeOut) {

							bUpdateCSVDownloaded = false;

							$.ajaxValidator.fnMonitor(sensus.util.process.api.updateStatus,
									new ProcessRequest({processList : [new Process({id : iProcessId})]}), false);

						}

					} else if (!bTimeOut) {

						if (!bIsError) {

							window.location = "export/downloadCsvFile?fileName="
								+ sFileName + "&id=" + iProcessId + "&updateCSVDownloaded=" + bUpdateCSVDownloaded;

							sensus.util.page.stopProgressBar();
						}

					} else if (iCount < 25) {

						setTimeout(fnVerify, 1000);
					}

				})();
			}
		}

		// Insert CSV process
		$.ajaxValidator.fnDoCall("api/export/insertCsvProcess", null, false,
				fnCallback, sensus.locale.get("commons.pages.exportmsg"), false, true);
	}
};