sensus.settings = {
		//Application Settings
		appRoot: '${applicationRoot}',
		tenantDescription: '${userContext.tenant.description}',
		tenantId: '${userContext.tenant.id}',
		maxResolution: '${maxResolution}',
		maxExtent: '[${maxExtent1},${maxExtent2},${maxExtent3},${maxExtent4}]',
		slcVersion : '${slcVersion}',
		slcBuildVersion : '${slcBuildVersion}',

		//Map Settings
		searchMapCenterLatitude: '${userContext.tenant.latitude}',
		searchMapCenterLongitude: '${userContext.tenant.longitude}',
			
		//User preferences
		baseLocale: '${userLocaleSettings.baseLocale}',
		timezone: '${userLocaleSettings.timezone}',
		monitor: '${userLocaleSettings.monitor}',
		dateFormatMask: '${userLocaleSettings.dateFormatMask}',
		convertUnit: '${userLocaleSettings.convertUnit}',
		pageSize: '${userLocaleSettings.pageSize}',
		pageSizeShowDialog: '${userLocaleSettings.pageSizeShowDialog}',
		timezoneShort : '${userLocaleSettings.timezoneShort}',
		timeZoneHours : '${userLocaleSettings.timeZoneHours}',
		datenow : '${userLocaleSettings.dateNow}',
		
		//Time preferences
		longRunningProcessTime: '${longRunningProcessTime}',
		checkRniTime : '${checkRniTime}', 
		
		//Dialog page size
		pageSizeInclude: '${base}/${pageSizeInclude}',
		
		//Long running process actions
		longRunningProcessDialog: '${base}/${longRunningProcessDialog}',
		longRunningProcessDialogRemove: '${base}/${longRunningProcessDialogRemove}',
		longRunningProcessDialogAbort: '${base}/${longRunningProcessDialogAbort}',
		longRunningProcessRemove: '${base}/${longRunningProcessRemove}',
		longRunningProcessAbort: '${base}/${longRunningProcessAbort}',
		longRunningProcessRecent: '${base}/${longRunningProcessRecent}',
		longRunningProcessRemoveAll : '${base}/${longRunningProcessRemoveAll}',
		retryUnreachable : '${base}/${longRunningProcessRetryUnreachable}',
		checkRni: '${base}/${checkRni}',
		checkLongRunningProcess : '${base}/${checkLongRunningProcess}'
};