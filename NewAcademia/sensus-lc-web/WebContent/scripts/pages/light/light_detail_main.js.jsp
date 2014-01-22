<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.smartpointdetail
 * @description The main namespace for the SmartPointDetail Page.
 * @author Alexandre Tiveron
*/

sensus.pages.smartpoint.detail = {

	id            : 0, //The smartpoint id
	totalRows     : 0, // The Total Rows
	auto_refresh  : 0, // The Interval for auto_refresh
	processingSummaryData : "", // The Processing Summary Data

	// The Mock Parameters
	oDeviceTypeParameters : {

		summaryData : ['lightInformation', 'lightState', 'lightStatus', 'statusMessage', 'ecoMode', 'lightActions'],
	    tabs    : ['lightDetail', 'lightHistory'],
	    content   : ['lightAlerts','lightSchedule','lightReadings','lightConfigurations','lightLocation','lightGroups', 'lighttags']

	},

	fnInquiryLightRequest : function() {

		var dEndDate = null;
		var iBack = parseInt($.address.parameter('total_days'));
		var dStartDate = $.address.parameter('view_from') || null;
		var aUserIds = null;
		var sLightTextSearch = unescape($.address.parameter('query'));

		if (dStartDate) {

			// Convert String to object date
			var aDate = dStartDate.split('|');
			dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, aDate[0]);
			dStartDate.setHours(23);
			dStartDate.setMinutes(59);
			dStartDate.setSeconds(59);

			dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, aDate[1]);
			dEndDate.setHours(0);
			dEndDate.setMinutes(0);
			dEndDate.setSeconds(0);

			dStartDate = $.sc.dateToUTC(dStartDate);
			dEndDate   = $.sc.dateToUTC(dEndDate);

		}

		if ($.address.parameter('action_type')) {

			var aActionCategoryList = $.address.parameter('action_type').split('|');
			aActionCategoryList.pop();

		}

		if ($.address.parameter('users')) {

			aUserIds = $.address.parameter('users').split('|');
			aUserIds.pop();

		}

		var search = new processFilter(null, null, sensus.widgets.datatable.fnConvertEnum('LCActionCategoryEnum', aActionCategoryList), sLightTextSearch, aUserIds)

		return search;
	}
};
</script>