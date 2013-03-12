<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">

/**
 * @fileoverview Defines the core functionality of the Smartpoint Detail History.
 * @author Alex Tiveron
 */


/**
 * The main namespace for smartpoint detail history - related functionality.
 */
sensus.pages.smartpointdetailhistory = {


	generateCsvFile : function() {

		sensus.pages.longrunningprocess.actionUrlAdress = "process/updateMonitorProcess.action";

		// Convert String to object date
		var aUserIds = null;
		var dEndDate = null;
		var iTimeZoneHours = parseFloat(sensus.settings.TIME_ZONE_HOURS);
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

		if ($.address.parameter('action_type')) {

			var aActionCategoryList = $.address.parameter('action_type').split('|');
			aActionCategoryList.pop();

		}

		if ($.address.parameter('users')) {

			aUserIds = $.address.parameter('users').split('|');
			aUserIds.pop();

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

		//var sLightTextSearch = null;
		var oSearch   = new processFilter(dEndDate, dStartDate,sensus.widgets.datatable.fnConvertEnum('LCActionCategoryEnum', aActionCategoryList), sLightTextSearch, aUserIds)
		var data = new inquiryLightRequest(oSearch);
		if(aSort){

			data.inquiryLightRequest.sortExpressions = aSort;

		}
		sensus.util.exportcsv.generateCSV(sensus.settings.insertProcess,sensus.settings.generateFileHistory,data,false);

	},

};
</script>