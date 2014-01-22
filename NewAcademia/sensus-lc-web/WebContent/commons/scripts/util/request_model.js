/*
 * Light Criteria
 */
function ActionCriteria(iPercentage, sBlinkStatus, sOverride, dOverridePerDate, eLifeCycleState, bIsClearOverride, sGetDataFromLightEnum)
{
	this.percentage = iPercentage;
	this.blinkStatus = sBlinkStatus;
	this.override = sOverride;
	this.overridePerDate = dOverridePerDate;
	this.lifeCycleState = eLifeCycleState;
	this.isClearOverride = bIsClearOverride;
	this.getDataFromLightEnum = sGetDataFromLightEnum;

}



function academiaRequest(cdacad,academ,lograd,numen,bairr,cidade,cep,telef,dataini,datafin,atual)
{
//debugger;
	this.academia = {};
	this.academia.cdacad = cdacad;
	this.academia.academ = academ;
	this.academia.lograd = lograd;
	this.academia.numen = parseInt(numen,10);
	this.academia.bairr = bairr;
	this.academia.cidade = cidade;
	this.academia.cep = cep;
	this.academia.telef = telef;
	dataini = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, dataini);
	datafin = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, datafin);
	this.academia.dataini =$.datepicker.formatDate('yy-mm-dd',dataini) + '-00-00-00-000';
	this.academia.datafin = $.datepicker.formatDate('yy-mm-dd',dataini) + '-00-00-00-000';
	this.academia.atual = atual;
	this.academia.createdate = new Date().getTime();
	this.academia.createuser = sensus.settings.userContext.userId;
	this.academia.tenantid = parseInt(sensus.settings.userContext.tenant.id,10);
	this.academia.userid = parseInt(sensus.settings.userContext.id,10);
	this.academia.latitude = sensus.settings.userContext.tenant.longitude;
	this.academia.longitude = sensus.settings.userContext.tenant.latitude;


//	if (groupOldName)
//	{
//		this.oldName = groupOldName;
//	}
//
//	if (!aIdsSelected || aIdsSelected.length == 0)
//	{
//		aIdsSelected = [];
//	}
//
//	this.searchLight = oSearchLight;
//	this.paginationAllSelected = isAllRows;
//	this.selectionPaginationIds = aIdsSelected;
//	this.groupList = aGroupList;
//	this.bottomLeftLon = nBottomLeftLon;
//	this.topRightLat = nTopRightLat;
//	this.topRightLon = nTopRightLon;
//	this.maxSmartpointCount = nMaxSmartpointCount;
//	this.lightRequest = oLightRequest;

}

function AddressCriteria(sAddress, sCity, sState, sCounty, nZip)
{
	this.address = sAddress;
	this.city = sCity;
	this.state = sState;
	this.county = sCounty;
	this.zip = nZip;
}

function AlertCriteria(aAlertTypeEnum, aAlertSubTypeEnum)
{
	this.alertTypeList = aAlertTypeEnum;
	this.alertSubtypeList = aAlertSubTypeEnum;
}

function ConfigurationCriteria(sHousing, sHousingColor, bDimmable, sWattageRating, sInputVoltageRange, sColorTemperature, sLowerAssemblySerial,
		sUpperAssemblySerial, dDateAdded, dDateAddedBefore, dDateAddedAfter, bulbSerialNumber, sBallastSerialNumber, sLampTypeWattageDimmable,
		sFirmwareVersion, sModelNumber)
{
	this.housing = sHousing;
	this.housingColor = sHousingColor;
	this.dimmable = bDimmable;
	this.wattageRating = sWattageRating;
	this.inputVoltageRange = sInputVoltageRange;
	this.colorTemperature = sColorTemperature;
	this.lowerAssemblySerial = sLowerAssemblySerial;
	this.upperAssemblySerial = sUpperAssemblySerial;
	this.dateAdded = dDateAdded;
	this.dateAddedBefore = dDateAddedBefore;
	this.dateAddedAfter = dDateAddedAfter;
	this.bulbSerialNumber = bulbSerialNumber;
	this.ballastSerialNumber = sBallastSerialNumber;
	this.lampTypeWattageDimmable = sLampTypeWattageDimmable;
	this.firmwareVersion = sFirmwareVersion;
	this.modelNumber = sModelNumber;
}

function LightCriteria(aLightIdList, alifeCycleStateList, oGeoCodeCriteria, bProtect, oLightBlink, oOverride, oLightType,
		oOverridePerDate, sIntensity, sPoleId, sFlexnetId, aEcoModeFilterEnum, aNotInlightIdList)
{
	this.lightIdList = aLightIdList;
	this.notInlightIdList = aNotInlightIdList;
	this.lifeCycleStateList = alifeCycleStateList;
	this.geoCodeCriteria = oGeoCodeCriteria;
	this.protect = bProtect;
	this.lightBlink = oLightBlink;
	this.override = oOverride;
	this.lightType = oLightType;
	this.overridePerDate = oOverridePerDate;
	this.intensity = sIntensity;
	this.poleId = sPoleId;
	this.flexnetId = sFlexnetId;
	this.ecomodeFilter = aEcoModeFilterEnum;
}

function GeoCodeCriteria(nTrunc, aLocationList, nBottomLeftLat, nBottomLeftLong, nTopRightLat, nTopRightLong, nMiddleLatMin, nMiddleLatMax, nMiddleLongMin, nMiddleLongMax)
{
	this.locationList = aLocationList;
	this.trunc = nTrunc;
	this.bottomLeftLat = nBottomLeftLat;
	this.bottomLeftLong = nBottomLeftLong;
	this.topRightLat = nTopRightLat;
	this.topRightLong = nTopRightLong;
	this.middleLatMin = nMiddleLatMin;
	this.middleLatMax = nMiddleLatMax;
	this.middleLongMin = nMiddleLongMin;
	this.middleLongMax = nMiddleLongMax;
}

function GroupCriteria(aGroupIds)
{
	this.groupIdList = aGroupIds;
}

function NotificationHistoryCriteria(iNotificationHistoryId, sNotificationType, iLightId, oProcessFilter, oAlertSubTypeEnum)
{
	this.notificationHistoryId = iNotificationHistoryId;
	this.notificationType = sNotificationType;
	this.lightId = iLightId;
	this.processFilter = oProcessFilter;
	this.alertSubType = oAlertSubTypeEnum;
}

function ProcessCriteria(nProcessId, bMonitored)
{
	this.processId = nProcessId;
	this.monitored = bMonitored;
}

function ScheduleCriteria(aLightIdList, aLightSchedule, bAllEvents, bAllOffsets)
{
	this.lightIdList = aLightIdList;
	this.lightSchedule = aLightSchedule;
	this.allEvents = bAllEvents;
	this.allOffsets = bAllOffsets;
}

function TagCriteria(aTagIds)
{
	this.tagIdList = aTagIds;
}

// Used for table request
function SearchCriteria(oLightCriteria, oActionCriteria, oGroupCriteria, oAlertCriteria, oAddressCriteria, oProcessCriteria, oScheduleCriteria,
		oConfigurationCriteria, oOperationalDataCriteria, oTagCriteria, oNotificationHistoryCriteria, oLightCustomSearchCriteria)
{
	this.lightCriteria = oLightCriteria;
	this.actionCriteria = oActionCriteria;
	this.groupCriteria = oGroupCriteria;
	this.alertCriteria = oAlertCriteria;
	this.addressCriteria = oAddressCriteria;
	this.processCriteria = oProcessCriteria;
	this.scheduleCriteria = oScheduleCriteria;
	this.configurationCriteria = oConfigurationCriteria;
	this.operationalDataCriteria = oOperationalDataCriteria;
	this.tagCriteria = oTagCriteria;
	this.notificationHistoryCriteria = oNotificationHistoryCriteria;
	this.lightCustomSearchCriteria = oLightCustomSearchCriteria;
}
function Light (bProtect, nId, sPoleId, oLocation)
{
	this.id = nId;
	this.protect = bProtect;
	this.poleId = sPoleId;
	if(oLocation)
	{
		this.radio = { location: oLocation };
	}
}

/*
 * Model for Light Request
 */
function lightTableRequest(oSearchCriteria)
{
	return new lightRequest(oSearchCriteria.lightCriteria, oSearchCriteria.actionCriteria, oSearchCriteria.groupCriteria, oSearchCriteria.alertCriteria,
			oSearchCriteria.addressCriteria, oSearchCriteria.processCriteria, oSearchCriteria.scheduleCriteria, oSearchCriteria.configurationCriteria,
			oSearchCriteria.operationalDataCriteria, oSearchCriteria.tagCriteria, oSearchCriteria.notificationHistoryCriteria, oSearchCriteria.lightCustomSearchCriteria);
}

function lightRequest(oLightCriteria, oActionCriteria, oGroupCriteria, oAlertCriteria, oAddressCriteria, oProcessCriteria, oScheduleCriteria,
		oConfigurationCriteria, oOperationalDataCriteria, oTagCriteria, oNotificationHistoryCriteria, oLightCustomSearchCriteria)
{

	this.lightCriteria = oLightCriteria;
	this.actionCriteria = oActionCriteria;
	this.groupCriteria = oGroupCriteria;
	this.alertCriteria = oAlertCriteria;
	this.addressCriteria = oAddressCriteria;
	this.processCriteria = oProcessCriteria;
	this.scheduleCriteria = oScheduleCriteria;
	this.configurationCriteria = oConfigurationCriteria;
	this.operationalDataCriteria = oOperationalDataCriteria;
	this.tagCriteria = oTagCriteria;
	this.notificationHistoryCriteria = oNotificationHistoryCriteria;
	this.lightCustomSearchCriteria = oLightCustomSearchCriteria;

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = ($.address.parameter("length")) ? $.address.parameter("length") : sensus.settings.PAGE_SIZE;
	this.sortExpressions = [];

}

function notificationHistoryRequest(oNotificationHistoryCriteria, oGroupCriteria)
{
	this.notificationHistoryCriteria = oNotificationHistoryCriteria;
	this.groupCriteria = oGroupCriteria;
}

function lightMaintenanceRequest(oLight)
{
	this.light = oLight;
}

function lightMassUpdateRequest (oLight, oLightCriteria, oActionCriteria, oGroupCriteria, oAlertCriteria, oAddressCriteria, oProcessCriteria, oScheduleCriteria,
		oConfigurationCriteria, oOperationalDataCriteria, oTagCriteria, oNotificationHistoryCriteria, oLightCustomSearchCriteria)
{
	this.light = oLight;
	this.lightCriteria = oLightCriteria;
	this.actionCriteria = oActionCriteria;
	this.groupCriteria = oGroupCriteria;
	this.alertCriteria = oAlertCriteria;
	this.addressCriteria = oAddressCriteria;
	this.processCriteria = oProcessCriteria;
	this.scheduleCriteria = oScheduleCriteria;
	this.configurationCriteria = oConfigurationCriteria;
	this.operationalDataCriteria = oOperationalDataCriteria;
	this.tagCriteria = oTagCriteria;
	this.notificationHistoryCriteria = oNotificationHistoryCriteria;
	this.lightCustomSearchCriteria = oLightCustomSearchCriteria;
}

function UserContext()
{
	return sensus.settings.userContext;
}

function SearchParameter(sValue, sFilterEnum)
{
	this.value = sValue;
	this.filterEnum = sFilterEnum;
}

function processFilter(dStartDate, dEndDate, aActionCategoryList, sLightTextSearch, aUserIds)
{
	this.startDate = dStartDate;
	this.endDate = dEndDate;
	this.actionCategoryList = aActionCategoryList;
	if (sLightTextSearch) {

		aSearch = sLightTextSearch.split('|');

		if (aSearch[0] == '12' || aSearch[0] == '36') {

			sEnum = 'PropertyEnum';

		} else {

			sEnum = 'LightPropertyForSearchEnum';

		}
		this.lightTextSearch = {};

		this.lightTextSearch.searchText = aSearch[1];
		this.lightTextSearch.lightProperty = sensus.widgets.datatable
				.fnGetEnumValue(sEnum, aSearch[0]);

	}
	this.userIds = aUserIds;
}

function SearchMeter(aSearchParameters)
{
	this.groupTypes = aSearchParameters;
}

function search(aSearchParameters)
{
	return aSearchParameters;
}

function searchLight(aStatusList, aSearchParameters)
{
	this.statusList = aStatusList;
	this.searchParameters = aSearchParameters;
	this.requestType = 'searchLight';
}

function inquiryPaginationRequest(oSearch)
{

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = null;
	this.sortExpressions = [];

}

function inquiryAcademiaRequest(oSearch)
{

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = null;
	this.sortExpressions = [];
	this.academias = [{createuser : sensus.settings.userContext.userId,tenantid : parseInt(sensus.settings.userContext.tenant.id,10),userid : parseInt(sensus.settings.userContext.id,10)}];

}

function inquiryLightRequest(oSearch, aCurrentColumns, aIds, aLatLonTrunc)
{

	var aLights = $.address.parameter('lights');
	if (aLights)
	{
		aLights = aLights.split('|');
		aLights.pop();
		this.selectionPaginationIds = aLights;
	}

	if (aIds)
	{
		this.selectionPaginationIds = aIds;
	}

	if (aLatLonTrunc)
	{
		this.geocodeLatLongTruncs = aLatLonTrunc;
	}

	var oNewSearch = null;

	if (oSearch)
	{
		if (oSearch.requestType == 'searchLight')
		{
			oNewSearch = oSearch;
			var oNewProcessFilter = null;
			delete oNewSearch.requestType;
		}
		else if (oSearch.requestType == 'processFilter')
		{
			oNewSearch = null;
			var oNewProcessFilter = oSearch;
			delete oNewProcessFilter.requestType;
		}
		else
		{
			oNewProcessFilter = oSearch;
		}

	}

	this.listColumn = aCurrentColumns;
	this.lights = [ {
		'id' : $.address.parameter("id")
	} ];
	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = 25;
	this.search = oNewSearch;
	this.processFilter = oNewProcessFilter;
	this.datePattern = sensus.settings.dateFormatMask;
	this.timezone = sensus.settings.TIME_ZONE;
	this.datePattern = sensus.settings.dateFormatMask;
	this.sortExpressions = [];

}

function ProcessCSVRequest(oInquiryProcessRequest, oCsvColumns)
{
	this.inquiryProcessRequest = oInquiryProcessRequest;
	this.csvColumns = oCsvColumns;
}

function inquiryProcessRequest(oSearch)
{

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = null;
	this.datePattern = sensus.settings.DATE_FORMAT;
	this.timezone = sensus.settings.TIME_ZONE;

	if (oSearch != null)
	{
		this.processFilter = oSearch;
	}

}

function inquiryScheduleRequest(oSearch)
{

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = 0;
	/*
	 * if (oSearch != null) {
	 *
	 * this.baseSearch = oSearch;
	 *  }
	 */

}

function inquiryUserRequest(oSearch)
{

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = 25;

	if (oSearch != null)
	{
		this.action = oSearch.action;

		this.user = oSearch.user;
	}

}

function inquiryTagRequest(oSearch)
{

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = null;

}

function tagRequest(iTagId, sTagName, aTags, isAllRows, aSmartpointIds,
		oSearchLight, bIncludeSmartPointsToGroup, bAutoGroup, oLightRequest)
{

	this.tag = {};
	this.tag.id = iTagId;
	this.tag.name = sTagName;
	this.tag.autoGroup = bAutoGroup;
	this.tags = aTags;
	this.paginationAllSelected = isAllRows;
	this.selectionPaginationIds = aSmartpointIds;
	this.searchLight = {
		searchParameters : []
	};
	this.includeSmartPointsToGroup = bIncludeSmartPointsToGroup;
	this.lightRequest = oLightRequest;

}

function settingsRequest(aSettings, sActualPassword, sNewPassword)
{
	this.settings = aSettings;
	this.actualPassword = sActualPassword;
	this.newPassword = sNewPassword;
}

function processRequest(aProcessList, oProcessFilter, isSorted, nId)
{
	if (nId)
	{
		this.process = {};
		this.process.id = nId;
	}
	this.isSorted = isSorted;
	this.processList = aProcessList;
	this.processFilter = oProcessFilter;
	// this.sortExpressions = [];
}

function userRequest(sUserId, sUserName, sFirstName, aRole, sLastName,
		sPassword, sEmail, bAllLightsAuth, aGroups, paginationAllSelected,
		selectionPaginationIds, sAction)
{

	this.user = {
		id : sUserId,
		userName : sUserName,
		firstName : sFirstName,
		role : aRole,
		email : sEmail,
		groups : aGroups,
		lastName : sLastName,
		password : sPassword,
		allLightsAuth : bAllLightsAuth
	};

	this.paginationAllSelected = paginationAllSelected;
	this.selectionPaginationIds = selectionPaginationIds;

}

function analyticsRequest(statusExceptionTypeEnum, sAction)
{

	// try {
	// Convert String to object date
	var dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT,
			$.address.parameter("di"));
	dStartDate.setHours(0);
	dStartDate.setMinutes(0);
	dStartDate.setSeconds(0);

	var dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT,
			$.address.parameter("de"));
	dEndDate.setHours(23);
	dEndDate.setMinutes(59);
	dEndDate.setSeconds(59);

	var sType = $.address.parameter('ty');

	if (sType != '5' && sType != '6')
	{
		dStartDate = $.sc.dateToUTC(dStartDate);
		dEndDate = $.sc.dateToUTC(dEndDate);
	}
	else
	{
		dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate) + '-00-00-00-000';
		dEndDate = $.datepicker.formatDate('yy-mm-dd', dEndDate) + '-23-59-59-999';
	}

	var oGroup = null;

	if ($.address.parameter('g') && $.address.parameter('g') != "0")
	{
		oGroup = {
			id : $.address.parameter('g')
		};
	}

	this.action = sAction;
	this.group = oGroup;

	this.initialDate = dStartDate;
	this.endDate = dEndDate;
	this.statusExceptionTypeEnum = statusExceptionTypeEnum; // "LAMP_FAILURE";
	this.analyticsTypeEnum = sensus.widgets.datatable.fnGetEnumValue(
			'AnalyticsTypeEnum', $.address.parameter('ty'));


	if($.address.parameter('dt') != 0)
	{
		var iTypeEnum;
		switch($.address.parameter('dt'))
		{
			case '1d' :
				iTypeEnum = 0;
				break;
			case '3d' :
				iTypeEnum = 1;
				break;
			case '1w' :
				iTypeEnum = 2;
				break;
			case '1m' :
				iTypeEnum = 3;
				break;
			case '3m' :
				iTypeEnum = 4;
				break;
			case 'ytd' :
				iTypeEnum = 5;
				break;
			case '1y' :
				iTypeEnum = 6;
				break;
		}
		this.analyticsRangeDateEnum = sensus.widgets.datatable.fnGetEnumValue(
				'AnalyticsRangeDateEnum', iTypeEnum);
		this.analyticsDateTypeEnum = sensus.widgets.datatable.fnGetEnumValue(
				'AnalyticsDateTypeEnum', $.address.parameter('dt') );
	}
	else
	{
		this.analyticsDateTypeEnum = sensus.widgets.datatable.fnGetEnumValue(
				'AnalyticsDateTypeEnum', "1d");
		this.analyticsRangeDateEnum = null;
	}

}

function AnalyticsCSVRequest(oInquiryAnalyticsRequest, oCsvColumns)
{
	this.inquiryAnalyticsRequest = oInquiryAnalyticsRequest;
	this.csvColumns = oCsvColumns;
}

function inquiryAnalyticsRequest(statusExceptionTypeEnum)
{

	// Convert String to object date
	var dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT,
	$.address.parameter("di"));
	dStartDate.setHours(0);
	dStartDate.setMinutes(0);
	dStartDate.setSeconds(0);

	var dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT,
	$.address.parameter("de"));
	dEndDate.setHours(23);
	dEndDate.setMinutes(59);
	dEndDate.setSeconds(59);

	var sType = $.address.parameter('ty');

	if (sType != '5' && sType != '6')
	{
		dStartDate = $.sc.dateToUTC(dStartDate);
		dEndDate = $.sc.dateToUTC(dEndDate);
	}
	else
	{
		dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate) + '-00-00-00-000';
		dEndDate = $.datepicker.formatDate('yy-mm-dd', dEndDate) + '-23-59-59-999';
	}

	this.startRow = 0;
	this.endRow = 0;
	this.pageSize = null;
	this.initialDate = dStartDate;
	this.endDate = dEndDate;
	this.analyticsTypeEnum = sensus.widgets.datatable.fnGetEnumValue(
			'AnalyticsTypeEnum', $.address.parameter('ty'));


	if($.address.parameter('dt') != 0)
	{
		var iTypeEnum;
		switch($.address.parameter('dt'))
		{
			case '1d' :
				iTypeEnum = 0;
				break;
			case '3d' :
				iTypeEnum = 1;
				break;
			case '1w' :
				iTypeEnum = 2;
				break;
			case '1m' :
				iTypeEnum = 3;
				break;
			case '3m' :
				iTypeEnum = 4;
				break;
			case 'ytd' :
				iTypeEnum = 5;
				break;
			case '1y' :
				iTypeEnum = 6;
				break;
		}
		this.analyticsRangeDateEnum = sensus.widgets.datatable.fnGetEnumValue(
				'AnalyticsRangeDateEnum', iTypeEnum);
		this.analyticsDateTypeEnum = sensus.widgets.datatable.fnGetEnumValue(
				'AnalyticsDateTypeEnum', $.address.parameter('dt') );
	}
	else
	{
		this.analyticsDateTypeEnum = sensus.widgets.datatable.fnGetEnumValue(
				'AnalyticsDateTypeEnum', "1d");
		this.analyticsRangeDateEnum = null;
	}
	if($.address.parameter("g") == $.address.parameter("og"))
	{
		this.group = {
				id : 0
		};
	}
	else
	{
		this.group = {
				id : $.address.parameter('g')
		};
		$.address.parameter("iDisplayStart",0);
	}

	if (statusExceptionTypeEnum != null)
	{
		this.statusExceptionTypeEnum = statusExceptionTypeEnum;
	}

}


function groupRequest(groupName, groupDescription, groupOldName, groupId,
		isAllRows, aIdsSelected, oSearchLight, aGroupList, nBottomLeftLat,
		nBottomLeftLon, nTopRightLat, nTopRightLon, nMaxSmartpointCount, oLightRequest)
{

	this.group = {};
	this.group.id = groupId;
	this.group.name = groupName;
	this.group.description = groupDescription;

	if (groupOldName)
	{
		this.oldName = groupOldName;
	}

	if (!aIdsSelected || aIdsSelected.length == 0)
	{
		aIdsSelected = [];
	}

	this.searchLight = oSearchLight;
	this.paginationAllSelected = isAllRows;
	this.selectionPaginationIds = aIdsSelected;
	this.groupList = aGroupList;
	this.bottomLeftLon = nBottomLeftLon;
	this.topRightLat = nTopRightLat;
	this.topRightLon = nTopRightLon;
	this.maxSmartpointCount = nMaxSmartpointCount;
	this.lightRequest = oLightRequest;

}

function OffsetSchedule()
{
	this.id = 1;
}

function customSearchRequest(nId, sSearchName, sSearchDescription, aSearchParameters,aListColumns, aListFilters)
{
	this.customSearch = {};
	this.customSearch.id = nId;
	this.customSearch.name = sSearchName;
	this.customSearch.description = sSearchDescription;
	this.customSearch.searchParameters = aSearchParameters;
	this.customSearch.listColumn = aListColumns;
	this.customSearch.listFilters = aListFilters;
}

function scheduleRequest(nId, nBottomLeftLat, nBottomLeftLon, nTopRightLat,
		nTopRightLon, nMaxLightCount, oSchedule, sScheduleType, isAllRows,
		aIdsSelected, oSearchLight, sScheduleName, aLightIdList)
{

	var sScheduleEnum = 'EVENT';
	if (sScheduleType == 'offsetSchedule' || sScheduleType == 'OFFSET')
	{
		sScheduleEnum = 'OFFSET';
	}
	this.scheduleTypeEnum = sScheduleEnum;
	if (nId)
	{
		this.schedule = {};
		this.schedule.id = nId;
		this.schedule.name = sScheduleName;
		this.schedule.scheduleTypeEnum = sScheduleEnum;
		this.schedule.lights = aLightIdList;
	}
	else if (oSchedule)
	{
		this[sScheduleType] = oSchedule;
		this[sScheduleType].scheduleTypeEnum = sScheduleEnum;
	}
	else
	{
		this.schedule = {};
		this.schedule.scheduleTypeEnum = sScheduleEnum;
	}
	if (!aIdsSelected)
	{
		aIdsSelected = [];
	}

	this.bottomLeftLat = nBottomLeftLat;
	this.bottomLeftLon = nBottomLeftLon;
	this.topRightLat = nTopRightLat;
	this.topRightLon = nTopRightLon;
	this.maxLightCount = nMaxLightCount;
	this.searchLight = oSearchLight;
	this.paginationAllSelected = isAllRows;
	this.selectionPaginationIds = aIdsSelected;
}

function eventSchedule(nId, sDescription, sName, sScheduleType, aEvents)
{
	this.id = nId;
	this.description = sDescription;
	this.name = sName;
	this.scheduleTypeEnum = sScheduleType;
	this.events = aEvents;
};

function offsetSchedule(nId, sDescription, sName, sScheduleType,
		bSunriseOffsetMinutes, bSunsetOffsetMinutes, bSunriseBefore,
		bSunsetBefore, iIntensity)
{

	this.id = nId;
	this.description = sDescription;
	this.name = sName;
	this.scheduleTypeEnum = sScheduleType;
	this.sunriseOffsetMinutes = bSunriseOffsetMinutes;
	this.sunsetOffsetMinutes = bSunsetOffsetMinutes;
	this.sunriseBefore = bSunriseBefore;
	this.sunsetBefore = bSunsetBefore;
	this.intensity = iIntensity;

};

function lightSelectionRequest(bMonitored)
{
	this.monitored = bMonitored;
}

function ecoModeBaseline(sType, nWattage)
{
	this.replacedType = sType;
	this.replacedWattage = nWattage;
}

function ecoModeRequest(obj)
{
	this.lights = obj.lights;
	this.tags = obj.tags;
	this.invalidEcoModeAmount = obj.invalidEcoModeAmount;
	this.internalProcessing = obj.internalProcessing;
	this.containFileHeader = obj.containFileHeader;
}
ecoModeRequest.prototype = new lightSelectionRequest();

function EcoModeCSVRequest(oInquiryEcoModeRequest, oCsvColumns)
{
	this.inquiryEcoModeRequest = oInquiryEcoModeRequest;
	this.csvColumns = oCsvColumns;
}

function inquiryEcoModeRequest(aEcoModeBaseline, aIdsSelected, light)
{

	this.datePattern = sensus.settings.DATE_FORMAT;
	this.timezone = sensus.settings.TIME_ZONE;
	this.pageSize = $("#ecomode-table_length select").val();
	this.selectionPaginationIds = [ $.address.parameter('id') ];
	var dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("di"));
	dStartDate.setHours(0);
	dStartDate.setMinutes(0);
	dStartDate.setSeconds(0);

	var dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("de"));
	dEndDate.setHours(23);
	dEndDate.setMinutes(59);
	dEndDate.setSeconds(59);

	dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate) + '-00-00-00-000';
	dEndDate = $.datepicker.formatDate('yy-mm-dd', dEndDate) + '-23-59-59-999';

	this.initialDate = dStartDate;
	this.endDate = dEndDate;
}