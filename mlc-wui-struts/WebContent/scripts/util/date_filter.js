/**
 * @param table
 * 			string
 * @param fnGraph
 * 			function
 */

$.fn.wDateFilters = function(table,fnGraph) {
	
	$('#actions-options').append('<div class="right">' 
			+ '<form id="analytics-filter-date">'
				+ '<input type="text" id="datepicker1" name="datepicker1" class="date rounded datepicker validate[required]" value="" data-prompt-position="topLeft"> - '
				+ '<input type="text" id="datepicker2" name="datepicker2" class="date rounded datepicker validate[required]" value="" data-prompt-position="bottomLeft : -90,60">' 
				+ '<input id="submit-date" class="submit" type="submit" value="&raquo;" />'
			+ '</form>' 
			+ '</div><ul id="date-tag" class="link-list">' 
			+ '<li id="1d"><a class="0" href="#">1' +  sensus.locale.get("analytics.page.day") + '</a></li>' 
			+ '<li id="3d"><a class="2" href="#">3' + sensus.locale.get("analytics.page.day") + '</a></li>' 
			+ '<li id="1w"><a class="6" href="#">1' + sensus.locale.get("analytics.page.week") + '</a></li>' 
            + '<li id="1m"><a class="29" href="#">1' + sensus.locale.get("analytics.page.month") + '</a></li>' 
            + '<li id="3m"><a class="90" href="#">3' + sensus.locale.get("analytics.page.month") + '</a></li>' 
            + '<li id="ytd"><a class="ytd" href="#">YTD</a></li>' 
            + '<li id="1y"><a class="365" href="#">1' + sensus.locale.get("analytics.page.year")+ '</a></li>');

};