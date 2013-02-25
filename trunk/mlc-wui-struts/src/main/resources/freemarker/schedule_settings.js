sensus.settings = $.extend({
		searchUrl : '${base}/${searchUrl}',
		scheduleUrl : '${base}/${scheduleUrl}',
		createUrl: '${base}/${createUrl}',
		updateScheduleUrl : '${base}/${updateScheduleUrl}',
		maxExtent: '[${maxExtent1},${maxExtent2},${maxExtent3},${maxExtent4}]',
		menuItem: '${menuItem}',
		smartpointUrl : '${base}/${smartpointUrl}',
		sunriseOffsetMin: '${sunriseOffsetMin}',
		sunriseOffsetMax: '${sunriseOffsetMax}',
		deleteScheduleInclude : '${base}/${deleteScheduleInclude}',
		deleteScheduleUrl: '${base}/${deleteScheduleUrl}',
		initiateDeleteSchedule: '${base}/${initiateDeleteSchedule}',
		searchUrlLRP : '${base}/${searchUrlLRP}',
		deleteFile : '${base}/${deleteFile}',
		saveProfileSettings: '${base}/${saveProfileSettings}',
		generateSummaryFileCSV : '${base}/${generateSummaryFileCSV}'
}, sensus.commons.lib.ajax.settings);