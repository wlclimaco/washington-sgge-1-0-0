sensus.settings = $.extend({
	menuItem: '${menuItem}',
	search: '${base}/${search}',
	maxExtent: '[${maxExtent1},${maxExtent2},${maxExtent3},${maxExtent4}]',
	searchUrlLRP : '${base}/${searchUrlLRP}',
	deleteFile : '${base}/${deleteFile}',
	generateSummaryFileCSV : '${base}/${generateSummaryFileCSV}'
}, sensus.commons.lib.ajax.settings);