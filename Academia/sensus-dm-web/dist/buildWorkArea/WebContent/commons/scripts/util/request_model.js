var TenantRequest = function(oParam) {
	if (oParam) {
		this.timeZone 				= sensus.settings.timezoneHours;
		this.dateFormat 			= sensus.util.page.getDateFormat(sensus.settings.dateFormatMask);
		this.isMonitored 			= oParam.isMonitored;
		this.recentRequestMonitored = oParam.recentRequestMonitored;
	}
}

var DeviceManagerInquiryRequest = function (oParam) {
	if (oParam) {

		// Super Constructor
		TenantRequest.call(this, oParam);

		this.startRow 			= oParam.startRow;
		this.endRow 			= oParam.endRow;
		this.pageSize 			= oParam.pageSize;
		this.startPage 			= oParam.startPage;
		this.sortExpressions 	= oParam.sortExpressions;
		this.preQueryCount		= oParam.preQueryCount;
	}
}

//Inherit TenantRequest
DeviceManagerInquiryRequest.prototype = new TenantRequest();

var InquiryPaginationRequest = function (oParam) {
    if (oParam) {

    	// Super Constructor
    	DeviceManagerInquiryRequest.call(this, oParam);

    	this.selectionPaginationIds = oParam.selectionPaginationIds;
        this.paginationAllSelected = oParam.paginationAllSelected;
    }
};

//Inherit DeviceManagerInquiryRequest
InquiryPaginationRequest.prototype = new DeviceManagerInquiryRequest();

var BaseSearch = function (oParam) {
	if (oParam) {
		this.groupTypes 		= oParam.groupTypes;
		this.searchText 		= oParam.searchText;
		this.users 				= oParam.users;
		this.containDevices 	= oParam.containDevices;
		this.startDate 			= oParam.startDate;
		this.endDate 			= oParam.endDate;
		this.actionTypeEnums 	= oParam.actionTypeEnums;
		this.processCategories	= oParam.processCategories;
		this.deviceTypes 		= oParam.deviceTypes;
	}
}

var SensusModel = function (oParam) {

	if (oParam) {
		this.modifyUser 		= oParam.modifyUser;
		this.modifyDate 		= oParam.modifyDate;
	}

}

/** ##########  GROUP ##############*/

var InquiryGroupRequest = function (oParam) {

	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	this.groupTypeEnum 	= oParam.groupTypeEnum;
	this.deviceSearch 	= oParam.deviceSearch;
	this.groups 		= oParam.groups;

}

var GroupRequest = function (oParam) {

	if (oParam) {
		this.groups 			= oParam.groups;
		this.oldName 			= oParam.oldName;
		this.percentage 		= oParam.percentage;
		this.retrieveHistory	= oParam.retrieveHistory;
		this.bottomLeftLat 		= oParam.bottomLeftLat;
		this.bottomLeftLon 		= oParam.bottomLeftLon;
		this.topRightLat 		= oParam.topRightLat;
		this.topRightLon 		= oParam.topRightLon;
		this.maxDeviceCount 	= oParam.maxDeviceCount;
		this.idsFile 			= oParam.idsFile;
	}

}

//Inherit InquiryPaginationRequest
InquiryGroupRequest.prototype = new InquiryPaginationRequest();

/** ##########  SCHEDULE ##############*/

var Schedule = function (oParam) {

	this.id				= oParam.id;
	this.name			= oParam.name;
	this.description	= oParam.description;
	this.statusEnum		= oParam.statusEnum;
	this.action			= oParam.action;
	this.frequency		= oParam.frequency;
	this.startTime		= oParam.startTime;
	this.endDate		= oParam.endDate;
}

var ScheduleRequest = function (oParam) {

	// Super Constructor
	TenantRequest.call(this, oParam);

	this.schedule	= oParam.schedule;
	this.idsFile	= oParam.idsFile;
	this.idFileType	= oParam.idFileType;
	this.uploadIds	= oParam.uploadIds;
	this.startDate	= oParam.startDate;
	this.endDate	= oParam.endDate;
	this.paused		= oParam.paused;
}

//Inherit TenantRequest
ScheduleRequest.prototype = new TenantRequest();

var InquiryScheduleRequest = function (oParam) {
	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	this.baseSearch 			= oParam.baseSearch;
	this.scheduleStatusEnums 	= oParam.scheduleStatusEnums;
	this.frequencies 			= oParam.frequencies;
}

//Inherit InquiryPaginationRequest
InquiryScheduleRequest.prototype = new InquiryPaginationRequest();

/** ############  PROCESS #############*/

var Process = function (oParam) {
	this.id = oParam.id;
	this.monitoredInstance = oParam.monitoredInstance;
	this.dashboardMonitored = oParam.dashboardMonitored;
	this.processStatusEnum = oParam.processStatusEnum;
}

var ProcessRequest = function (oParam) {

	this.processList = oParam.processList;
}

var ProcessSearch = function (oParam) {

	this.startDate = oParam.startDate;
	this.endDate = oParam.endDate;
}

var InquiryProcessRequest = function (oParam) {

	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	this.processSearch 			= oParam.processSearch;
	this.processes 				= oParam.processes;
	this.processItems 			= oParam.processItems;
	this.devices 				= oParam.devices;
	this.isDashboardMonitored 	= oParam.isDashboardMonitored;
	this.processItemStatusEnum	= oParam.processItemStatusEnum;
	this.isToday				= oParam.isToday;
	this.fileIdsType 			= oParam.fileIdsType;
	this.unreachablesIds 		= oParam.unreachablesIds;
}

//Inherit InquiryPaginationRequest
InquiryProcessRequest.prototype = new InquiryPaginationRequest();


/** ############   TAG   ################*/

var InquiryTagRequest = function (oParam) {

	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	this.baseSearch 	= oParam.baseSearch;
	this.tags 			= oParam.tags;
}

//Inherit InquiryPaginationRequest
InquiryTagRequest.prototype = new InquiryPaginationRequest();

/** ############  DEVICE ##############*/

var Device = function (oParam) {

	if (oParam) {
		this.deviceId 			= oParam.deviceId;
		this.radio	 			= oParam.radio;
		this.deviceType 		= oParam.deviceType;
	}
}

var Radio = function (oParam) {

	if (oParam) {
		this.flexNetId 			= oParam.flexNetId;
		this.customerId 		= oParam.customerId;
		this.location 			= oParam.location;
	}

}

var Lcm = function (oParam) {

	// Super Constructor
	Device.call(this, oParam);

	if (oParam) {
		this.lifecycleStateEnum      = oParam.lifecycleStateEnum;
		this.lcmRelays				 = oParam.lcmRelays;
		this.deviceModel			 = oParam.deviceModel;
		this.electricMeterFlexNetId  = oParam.electricMeterFlexNetId;
		this.configuration			 = oParam.configuration;
		this.lcmTypeEnum			 = oParam.lcmTypeEnum;
		this.macAddress			 	 = oParam.macAddress;
		this.alarms				     = oParam.alarms;
	}
}
Lcm.prototype = new Device();

var LCMRelay = function (oParam) {

	if (oParam) {
		this.relay 					= oParam.relay;
		this.amp 					= oParam.amp;
		this.intendedUse 			= oParam.intendedUse;
		this.used 					= oParam.used;
		this.startMinutes 			= oParam.startMinutes;
		this.endMinutes 			= oParam.endMinutes;
		this.enrollmentCode 		= oParam.enrollmentCode;
		this.deviceClass 			= oParam.deviceClass;
		this.tamperDetectTimeout 	= oParam.tamperDetectTimeout;
	}

};

var InquiryDeviceRequest = function (oParam) {

	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	if (oParam) {
		this.deviceSearch 			= oParam.deviceSearch;
		this.groups					= oParam.groups;
		this.tags					= oParam.tags;
		this.idFileTypeValue 		= oParam.idFileTypeValue;
		this.devices				= oParam.devices;
		this.listColumn				= oParam.listColumn;
		this.deviceType				= oParam.deviceType;
		this.geocodeLatLongTruncs 	= oParam.geocodeLatLongTruncs;
	}
}

//Inherit InquiryPaginationRequest
InquiryDeviceRequest.prototype = new InquiryPaginationRequest();

var DeviceSearch = function (oParam) {
	if (oParam) {
		this.electricMeterSearch		= oParam.electricMeterSearch;
		this.hanDeviceSearch			= oParam.hanDeviceSearch;
		this.lcmSearch					= oParam.lcmSearch;
		this.waterMeterSearch			= oParam.waterMeterSearch;
		this.gasMeterSearch				= oParam.gasMeterSearch;
		this.groups						= oParam.groups;
		this.tags						= oParam.tags;
		this.deviceModels				= oParam.deviceModels;
		this.processId					= oParam.processId;
	}
	// Super Constructor
	BaseSearch.call(this, oParam);
}

//Inherit InquiryPaginationRequest
DeviceSearch.prototype = new BaseSearch();

/** ########## Customize ########## **/

var ColumnFilterRequest = function (oParam) {

	this.listColumn 		= oParam.listColumn;
	this.additionalColumns 	= oParam.additionalColumns;
	this.filters 			= oParam.filters;
	this.additionalFilters	= oParam.additionalFilters;
	this.listTypeEnum 		= oParam.listTypeEnum;

}

/** #############  GENERAL OBJECTS ########### **/

var InsertRequest = function (oParam) {
	if (oParam) {
		this.name = oParam.name;
	}
}

var RemoveRequest = function (oParam) {
	if (oParam) {
		this.id = oParam.id;
	}
}

var FetchRequest = function (oParam) {
	this.id 	= oParam.id;
	this.type 	= oParam.type;
}

// TODO remove MeterRequest
var MeterRequest = function (oParam) {
	this.devices = oParam.devices;
}

var DeviceRequest = function (oParam) {

	if (oParam) {
		this.devices = oParam.devices;
	}

	// Super Constructor
	TenantRequest.call(this, oParam);
}

//Inherit DeviceRequest
DeviceRequest.prototype = new TenantRequest();

var InsertNoteRequest = function (oParam) {

	this.id  		= oParam.id;
	this.text 		= oParam.text;

	// Super Constructor
	InsertRequest.call(this, oParam);
}

//Inherit InsertNoteRequest
InsertNoteRequest.prototype = new InsertRequest();

var DeleteNoteRequest = function (oParam) {

	this.noteId = oParam.noteId;

	// Super Constructor
	RemoveRequest.call(this, oParam);
}

//Inherit DeleteNoteRequest
DeleteNoteRequest.prototype = new RemoveRequest();

/** #############  Action ########### **/

var DMAction = function (oParam) {

	if (oParam) {
		this.id 		= oParam.id;
		this.devices 	= oParam.devices;
	}
}

var ActionRequest = function (oParam) {

	if (oParam) {
		this.action 				= oParam.action;
		this.parentRetry			= oParam.parentRetry;
		this.presetDate				= oParam.presetDate;
		this.hanDevicesFile			= oParam.hanDevicesFile;
		this.tags					= oParam.tags;
		this.idsFile				= oParam.idsFile;
		this.actionTypeDescriptions = oParam.actionTypeDescriptions;
	}
}

var ActionType = function (oParam) {

	// Super Constructor
	SensusModel.call(this, oParam);

	if (oParam) {
		this.protect 						= oParam.protect;
		this.actionTypeEnum					= oParam.actionTypeEnum;
		this.actionTypeEnumValue			= oParam.actionTypeEnumValue;
		this.actionTypeEnumDescription		= oParam.actionTypeEnumDescription;
		this.modifyUser						= oParam.modifyUser;
		this.modifyDate						= oParam.modifyDate;
	}
}

//Inherit ActionType
ActionType.prototype = new SensusModel();


/** ########### Search ########### **/

var ProcessSearch = function (oParam) {

	// Super Constructor
	BaseSearch.call(this, oParam);

	this.processStatusEnums				= oParam.processStatusEnums;
	this.processItemHistoryStatusEnums	= oParam.processItemHistoryStatusEnums;
	this.searchType						= oParam.searchType;

}

//Inherit ProcessSearch
ProcessSearch.prototype = new BaseSearch();

var CustomSearchRequest = function (oParam) {

	// Super Constructor
	TenantRequest.call(this, oParam);

	this.customSearch 		= oParam.customSearch;
}

var CustomSearch = function (oParam) {

	// Super Constructor
	DeviceSearch.call(this, oParam);

	this.id 				= oParam.id;
	this.name	 			= oParam.name;
	this.description 		= oParam.description;
	this.listColumn 		= oParam.listColumn;
	this.listFilters 		= oParam.listFilters;
	this.listTypeEnum 		= oParam.listTypeEnum;
	this.sortExpression 	= oParam.sortExpression;
	this.searchParameters 	= oParam.searchParameters;
	this.deviceType 		= oParam.deviceType;
}

//Inherit CustomSearch
CustomSearch.prototype = new DeviceSearch();

var DeviceReadingRequest = function (oParam) {

	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	if (oParam) {
		this.device			 = oParam.device;
		this.initialDate	 = oParam.initialDate;
		this.endDate		 = oParam.endDate;
	}
}
//Inherit InquiryIntervalReadRequest
DeviceReadingRequest.prototype = new InquiryPaginationRequest();

var InquiryDemandReadRequest = function (oParam) {

	// Super Constructor
	InquiryPaginationRequest.call(this, oParam);

	this.device			 = oParam.device;
	this.initialDate	 = oParam.initialDate;
	this.endDate		 = oParam.endDate;
}

//Inherit InquiryDemandReadRequest
InquiryDemandReadRequest.prototype = new InquiryPaginationRequest();

/** ########### Settings ########### **/

var PropertyRequest = function (oParam) {

	this.propertyProviderType = oParam.propertyProviderType;
	this.properties = oParam.properties;
	this.alwaysInsert = oParam.alwaysInsert;
}

var Property = function (oParam) {

	this.providerId = oParam.providerId;
	this.propertyName = oParam.propertyName;
	this.propertyValue = oParam.propertyValue;
}

/** ########## Actions ########## **/
var Action = function (oParam) {
	if (oParam) {
		this.actionType				= oParam.actionType;
		this.actionTypeName			= oParam.actionTypeName;
		this.actionTime				= oParam.actionTime;
		this.actionId				= oParam.actionId;
		this.onDemand				= oParam.onDemand;
		this.isMonitored			= oParam.isMonitored;
		this.devices				= oParam.devices;
	}
};

Action.prototype = new Action();

var SendHanTextMessageAction = function (oParam) {
	if (oParam) {
		this.textMessage 			= oParam.textMessage;
		this.durationHANTextMessage = oParam.durationHANTextMessage;
		this.dateTimeString			= oParam.dateTimeString;

		// Super Constructor
		Action.call(this, oParam);
	}
};

SendHanTextMessageAction.prototype = new Action();

var DemandResponse = function (oParam) {
	if (oParam) {
		this.demandResponseDuration = oParam.demandResponseDuration;
		this.enrollmentCode         = oParam.enrollmentCode;
		this.heating				= oParam.heating;
		this.cooling				= oParam.cooling;
		this.dutyCycleRate			= oParam.dutyCycleRate;
		this.loadAdjustment			= oParam.loadAdjustment;
		this.criticalityLevel		= oParam.criticalityLevel;
		this.randomizeStart			= oParam.randomizeStart;
		this.randomizeEnd			= oParam.randomizeEnd;
		this.dateTimeString			= oParam.dateTimeString;
		this.deviceClasses			= oParam.deviceClasses;

		// Super Constructor
		Action.call(this, oParam);
	}
};

DemandResponse.prototype = new Action();

var SetupDemandResponse  = function (oParam) {
	if (oParam) {
	// Super Constructor
		Action.call(this, oParam);

		this.enrollmentCode = oParam.enrollmentCode;
		this.startMinutes 	= oParam.startMinutes;
		this.endMinutes		= oParam.endMinutes;
		this.deviceClasses	= oParam.deviceClasses;
	}
};

SetupDemandResponse.prototype = new Action();

var ConnectHanDevice  = function (oParam) {
	if (oParam) {
		this.installCode      = oParam.installCode;
		this.macAddress       = oParam.macAddress;
		this.manufacture      = oParam.manufacture;
		this.model            = oParam.model;
		this.customerId		  = oParam.customerId;
		this.subDeviceType    = oParam.subDeviceType;
		this.deviceId         = oParam.deviceId;
		this.clientEndPointId = oParam.clientEndPointId;
		this.flexNetId        = oParam.flexNetId;

		// Super Constructor
		Action.call(this, oParam);
	}
};

ConnectHanDevice.prototype = new Action();

/** ########### WATER ########### **/
var WaterMeterRequest = function (oParam) {

	if (oParam) {
		this.waterMeters 	= oParam.waterMeters;
		this.waterLeakList 	= oParam.waterLeakList;
	}

	// Super Constructor
	DeviceRequest.call(this, oParam);
}
WaterMeterRequest.prototype = new DeviceRequest();