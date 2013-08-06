sensus.settings = $.extend({
	menuItem: '${menuItem}',
	//Process
	processPageAction: '${base}/${processPageAction}',
	searchProcessUrl: '${base}/${searchProcessUrl}',
	abortInclude: '${base}/${abortInclude}',
	summaryInclude: '${base}/${summaryInclude}',
	abortProcessUrl: '${base}/${abortProcessUrl}',
	retryProcessUrl: '${base}/${retryProcessUrl}',
	searchUrlLRP : '${base}/${searchUrlLRP}',
	deleteFile : '${base}/${deleteFile}',
	generateSummaryFileCSV : '${base}/${generateSummaryFileCSV}',
	generateFile : '${base}/${generateFile}',
	insertProcess : '${base}/${insertProcess}',
	saveProfileSettings: '${base}/${saveProfileSettings}'
}, sensus.commons.lib.ajax.settings);