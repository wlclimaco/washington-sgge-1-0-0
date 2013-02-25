<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


<script type="text/javascript">

/**
 * @namespace sensus.pages.process
 * @description The main namespace for the Process Page.
 */

/**
 * @fileoverview Defines the core functionality of the Process Page.
 * @author Raphael Constantino
 */

/**
 * The main namespace for process-related functionality.
 */
sensus.pages.process = {
		
	/** Process Id */	
	processId: "",	
	
	/** Smart Point ID */
	smProcessId: "",
	
	/** Date Created of Process */
	dateProcess: "",
	
	processSize : function(oProcess){

		nSize = 0;
		if(oProcess.length){
		
			nSize = oProcess.length;
		}
		
		return nSize;
	
	},
	
	generateCsvFile : function() {
		
		sensus.pages.longrunningprocess.actionUrlAdress = "process/updateMonitorProcess.action";
		
		// Convert String to object date
		var aUserIds = null;
		var dEndDate = null;
		var iBack = parseInt($.address.parameter('total_days'));
		var iTimeZoneHours = parseFloat(sensus.settings.timeZoneHours);
		var dStartDate = $.address.parameter('view_from') || null;
		var sLightTextSearch = $.address.parameter('query');

		if (dStartDate) {

			var aDate = dStartDate.split('|');
			dStartDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, aDate[0]); 
			dStartDate.setHours(23);
			dStartDate.setMinutes(59);
			dStartDate.setSeconds(59);

			dEndDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, aDate[1]); 
			dEndDate.setHours(0);
			dEndDate.setMinutes(0);
			dEndDate.setSeconds(0);

			dStartDate = $.date.fnTimeToUTC(dStartDate);
			dEndDate   = $.date.fnTimeToUTC(dEndDate);

		}

		if ($.address.parameter('users')) {

			aUserIds = $.address.parameter('users').split('|');
			aUserIds.pop();

		}

		if ($.address.parameter('event_type')) {

			var aActionCategoryList = $.address.parameter('event_type').split('|');
			aActionCategoryList.pop();

		}
		
		if ($.address.parameter('sort')) {

			var sSort = $.address.parameter('sort').split('|');
			sSort.pop();
			var sDirection = sSort[1];
			var aSort = [{
				'field'      : sSort[0],
				'direction'  : sDirection.substr(0, 1).toUpperCase() + sDirection.substr(1)+'ending'
			}];

		}
		
		
		var oSearch = new processFilter(dEndDate, dStartDate, sensus.widgets.datatable.fnConvertEnum('LCActionCategoryEnum', aActionCategoryList), sLightTextSearch, aUserIds);
		var data = { 'inquiryProcessRequest' : new inquiryProcessRequest(oSearch) };
		if(aSort){

			data.inquiryProcessRequest.sortExpressions = aSort;

		}
		sensus.util.exportcsv.bReload = true;
		sensus.util.exportcsv.generateCSV(sensus.settings.insertProcess,sensus.settings.generateFile,data,true);

	},	
	
	/** Is logged user equals row user */
	processUserEquals: ""	
	
};
</script>