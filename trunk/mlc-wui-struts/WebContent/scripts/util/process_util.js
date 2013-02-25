/**
 * @fileoverview Provides utilities and extensions for the process.
 * @author Alex Tiveron
 */

sensus.util.process = {	
		/**
		 * 
		 * @param iActionId {String}
		 * 					The action id to know what is the current action
		 * @param sStatus {String}
		 * 					The status Process
		 * @param sDescription {String}
		 * 					The description for action
		 * @param iSmartpointId {Integer}
		 * 					The Smartpoint Id
		 * @param iFlexnetId {String}
		 * 					The FlexNet Id
		 * @returns {String}
		 */
	fnFormatDescription : function(iActionId, sStatus, sDescription, iSmartpointId, iPoleId) {
	
		var sColText = "";  
		
		if (iActionId == 26 && sStatus ==  true) {
			// Do not internationalize this String - Coming from be this hard text 
			var downloadText = "File Downloaded";
			if (sDescription === downloadText) {
				sColText = sensus.locale.get('process.table.download.downloaded');
			} else {
				sColText = '<a class="download-css-process" href="#" id="export-csv-file-success-process">'+ sensus.locale.get('process.table.download') +'<br/><small>'+ sensus.locale.get('process.table.download.removed') +'<small><span class="hide">'+ sDescription +'</span></a>';	
			}	
		} else if (iActionId == 26 && sStatus == false) {
			sColText = sensus.locale.get('process.table.download.waiting');
		} 
		else if (sDescription.contains(sensus.locale.get("commons.pages.poleId"))){
			//Add Link do Smarpoint if Contains FlexNet ID
			sColText = sDescription.replace(new RegExp(sensus.locale.get("commons.pages.poleId")), sensus.locale.get("commons.pages.poleId") + '<a class="launch alist" href="smartpoint/ajax.smartpoint.detail.main.action?id='+ iSmartpointId +'">'+ iPoleId +'</a>');
		} else {
			sColText = sDescription;
		}
		return sColText;

	},
	
	fnGetParameterValue : function(aData, sParameter, sValue) {
				
		var aParameter = $.grep(aData.parameters, function(e) { return e.propertyEnum == sParameter; });
		
		if(aParameter.length) {
			return aParameter[0][sValue];
		} 
		
		return '--';
	},
	
	/**
	 * Download the file.
	 * @param fileName {String}
	 * 					The file path
	 * @param updateCSVDownloaded {Boolean}
	 * 					whether is to update the process Status or not. To summary does not use it.
	 * @param id {Integer}
	 * 					Process id to update status
	 * @returns {Boolean}
	 */
	downloadFile : function(fileName, updateCSVDownloaded, id){
		
		window.location = "process/downloadCSVFile.action?fileName=" + fileName + "&updateCSVDownloaded=" + updateCSVDownloaded + "&id=" + id;
		
		return true;
			
	}
};