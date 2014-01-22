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

	/**
	 * Format date on datatable
	 */
	formatDate : function(sDate)
	{
		return $.sc.dateFormat( sDate, sensus.settings.DATE_FORMAT,null,null,true);
	},

	/**
	 * Generate request object
	 */
	fnInquiryProcessRequest : function()
	{
		var aUserIds = [];
		var dEndDate = null;
		var iBack = parseInt($.address.parameter('total_days'));
		var iTimeZoneHours = parseFloat(sensus.settings.TIME_ZONE_HOURS);
		var dStartDate = $.address.parameter('view_from') || null;
		var sLightTextSearch = $.address.parameter('query');

		if (dStartDate)
		{
			// Convert String to object date
			var aDate = dStartDate.split('|');
			dStartDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, aDate[0]);
			dStartDate.setHours(23);
			dStartDate.setMinutes(59);
			dStartDate.setSeconds(59);

			dEndDate = $.datepicker.parseDate(sensus.settings.dateFormatMask, aDate[1]);
			dEndDate.setHours(0);
			dEndDate.setMinutes(0);
			dEndDate.setSeconds(0);

			dStartDate = $.sc.dateToUTC(dStartDate);
			dEndDate   = $.sc.dateToUTC(dEndDate);
		}

		if ($.address.parameter('users'))
		{
			aUserIds = $.address.parameter('users').split('|');
			aUserIds.pop();
		}

		for (var i = 0; i < aUserIds.length; i++)
		{
			aUserIds[i] = parseInt(aUserIds[i]);
		}

		if ($.address.parameter('event_type'))
		{
			var aActionCategoryList = $.address.parameter('event_type').split('|');
			aActionCategoryList.pop();
		}

		return search = new processFilter(dEndDate, dStartDate, sensus.widgets.datatable.fnConvertEnum('LCActionCategoryEnum', aActionCategoryList), sLightTextSearch, aUserIds);
	}

};
</script>