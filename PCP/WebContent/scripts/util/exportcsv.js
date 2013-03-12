/**
 * @fileoverview Defines common export-csv functionality.
 * @author Cristiane Cobo
 */

/**
 * The main namespace for export-csv functionality.
 */
sensus.util.exportcsv = {

	iProcessId : null,
	bTimeOut : true,
	sFileName : null,
	bIsError : false,
	bUpdateCSVDownloaded : true,
	sGenerateFileUrl : null,
	oData : null,
	bReload : false,

	generateFileCSV : function(oResponse){

		var bIsError = false;
		var bTimeOut = false;
		var bUpdateCSVDownloaded = true;
		var iCount = 0;
		if(sensus.util.exportcsv.bReload){

			setTimeout(function(){

				sensus.widgets.datatable.reloadTable(sensus.pages.process.eventHistoryTable, 0);

			}, 2000);

		}

		(function fnVerify(){
			iCount += 1;
			if (iCount == 25) {

				if(!bIsError && bTimeOut) {

					bUpdateCSVDownloaded = false;

					var aProcessList = [{ 'id': sensus.util.exportcsv.iProcessId}];
					var oRequest = new processRequest(aProcessList, null, null, null);
					oRequest.action = "updateMonitorProcess";
					$.sc.getJson("api/process/update", oRequest, false, null, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

				}

			} else if (!bTimeOut) {

				if (!bIsError) {

					//window.location = "export/download"
						//+ sensus.util.exportcsv.sFileName + "/" + bUpdateCSVDownloaded + "/" + sensus.util.exportcsv.iProcessId ;

						var uri =sensus.util.exportcsv.sFileName;

						window.location = "export/download/"+ encodeURIComponent(uri) +"/"+ bUpdateCSVDownloaded +"/"+sensus.util.exportcsv.iProcessId;
						//+ sensus.util.exportcsv.sFileName + "/" + bUpdateCSVDownloaded + "/" + sensus.util.exportcsv.iProcessId ;
					sensus.util.page.stopProgressBar();
				}
			} else if (iCount < 25) {

				setTimeout(fnVerify, 1000);

			}

		})();

		sensus.util.exportcsv.bReload = false;

	},

	generateFileData : function(oResponse){

		if(oResponse.firstProcess.id != null && oResponse.fileName != null) {

			sensus.util.exportcsv.iProcessId = oResponse.firstProcess.id;
			sensus.util.exportcsv.sFileName = oResponse.fileName;

			for(var key in sensus.util.exportcsv.oData) {

				sRequest = key;

			}

			sensus.util.exportcsv.oData.fileName = oResponse.fileName;
			sensus.util.exportcsv.oData.processId = oResponse.firstProcess.id;


		$.sc.getJson(sensus.util.exportcsv.sGenerateFileUrl, sensus.util.exportcsv.oData, false, sensus.util.exportcsv.generateFileCSV, null);

		}
	},



	insertCSVProcess : function(sInsertProcessUrl){

		var oRequest = new lightSelectionRequest(null);
		oRequest.action = "eventHistory";
		$.sc.getJson(sInsertProcessUrl, oRequest, false, sensus.util.exportcsv.generateFileData, null);

	},

	/**
	 * @param sInsertProcessUrl
	 * 			string, url for insert process
	 * @param sGenerateFileUrl
	 * 			string, url for generete file
	 * @param sOptionalAjaxParam
	 * 			string, Optional Parameter to send with actions
	 */
	generateCSV : function(sInsertProcessUrl, sGenerateFileUrl, oData, bReload) {

		sensus.util.exportcsv.oData = oData;
		sensus.util.exportcsv.sGenerateFileUrl = sGenerateFileUrl;
		sensus.util.exportcsv.insertCSVProcess(sInsertProcessUrl);

	}
};