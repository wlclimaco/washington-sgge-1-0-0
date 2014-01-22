/**
 * @fileoverview Defines common export-csv functionality.
 * @author Cristiane Cobo
 */

/**
 * The main namespace for export-csv functionality.
 */
sensus.util.exportcsv = {

		downloadCsvFile : function(iProcessId,sFileName,bUpdateCSVDownloaded,fnCallBack){


			window.location = "export/downloadCsvFile?fileName="
				+ sFileName + "&id=" + iProcessId + "&updateCSVDownloaded=" + bUpdateCSVDownloaded;

			$.sc.stopProgressBar();

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

					oRequest.fileName = sFileName;
					oRequest.processId = iProcessId;

					var fnExportCallback = function(oResponse)
					{
						bTimeOut = false;
						if(!oResponse.operationSuccess)
						{
							bIsError = true;
						}
					}

					$.sc.getJson(sGenerateFileUrl, oRequest, false, fnExportCallback, null, true);

					var iCount = 0;
					(function fnVerify(){
						iCount += 1;
						if (iCount == 25) {

							if(!bIsError && bTimeOut) {

								bUpdateCSVDownloaded = false;

								var processes = [new Process({id : iProcessId})];

								$.sc.monitor(sensus.util.process.api.updateStatus, new ProcessRequest({processList : processes}));

							}

						} else if (!bTimeOut) {

							if (!bIsError) {

								sensus.util.exportcsv.downloadCsvFile(iProcessId,sFileName,bUpdateCSVDownloaded);

							}

						} else if (iCount < 25) {

							setTimeout(fnVerify, 1000);
						}

					})();
				}
			}

			// Insert CSV process
			$.sc.getJson("api/export/insertCsvProcess", null, false, fnCallback, $.sc.locale("commons.pages.exportmsg"), false, true);

		}

};