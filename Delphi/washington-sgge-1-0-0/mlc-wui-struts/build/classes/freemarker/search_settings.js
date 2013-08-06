sensus.settings = $.extend({
		searchUrl : '${base}/${searchUrl}',
		deleteSearchInclude: '${base}/${deleteSearchInclude}',
		deleteSearchUrl: '${base}/${deleteSearchUrl}',
		maxExtent: '[${maxExtent1},${maxExtent2},${maxExtent3},${maxExtent4}]',
		menuItem: '${menuItem}',
		searchUrlLRP : '${base}/${searchUrlLRP}',
		checkRni: '${base}/${checkRni}',
		deleteFile : '${base}/${deleteFile}',
		generateSummaryFileCSV : '${base}/${generateSummaryFileCSV}',
		saveProfileSettings: '${base}/${saveProfileSettings}', 		
		saveSearchInclude : '${base}/${saveSearchInclude}',
		saveSearch : '${base}/${saveSearch}'
}, sensus.commons.lib.ajax.settings);