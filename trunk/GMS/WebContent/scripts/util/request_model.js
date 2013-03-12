
	function UserContext()
	{
		return sensus.settings.userContext;
	}

	function SearchParameter(sValue,sFilterEnum)
	{
		this.value = sValue;
		this.filterEnum = sFilterEnum;
	}

	function processFilter(dStartDate, dEndDate, aActionCategoryList, sLightTextSearch, aUserIds)
	{
		this.startDate = dStartDate;
		this.endDate = dEndDate;
		this.actionCategoryList = aActionCategoryList;
		if(sLightTextSearch){

			aSearch = sLightTextSearch.split('|');

			if(aSearch[0] == '12' || aSearch[0] == '36'){

				sEnum = 'PropertyEnum';

			} else {

				sEnum = 'LightPropertyForSearchEnum';

			}
			this.lightTextSearch = {};

			this.lightTextSearch.searchText = aSearch[1];
			this.lightTextSearch.lightProperty = sensus.widgets.datatable.fnGetEnumValue(sEnum, aSearch[0]);

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
		this.statusList        = aStatusList;
		this.searchParameters  = aSearchParameters;
	}

	function inquiryPaginationRequest (oSearch) {

		this.startRow         = 0;
		this.endRow           = 0;
		this.pageSize         = null;
		this.sortExpressions  = [];

		//if (oSearch != null) {

		//	this.baseSearch = oSearch;

		//}


	}

	function inquiryLightRequest (oSearch, aCurrentColumns) {

		var aLights = $.address.parameter('lights');
		if(aLights){

			aLights = aLights.split('|');
			aLights.pop();
			this.selectionPaginationIds = aLights;

		}

			var oNewSearch = oSearch;
			var oNewProcessFilter = null;

		this.listColumn		 = aCurrentColumns;
		this.lights          = [{'id':$.address.parameter("id")}];
		this.startRow        = 0;
		this.endRow          = 0;
		this.pageSize        = 25;
		this.searchLight          = oNewSearch;
		this.processFilter   = oNewProcessFilter;
		//this.userContext     = new UserContext();
		this.datePattern = sensus.settings.DATE_FORMAT;
		this.timezone = sensus.settings.timezone;
		this.datePattern = sensus.settings.DATE_FORMAT;
		this.sortExpressions = [];

	}

	function inquiryProcessRequest (oSearch) {

		this.startRow  = 0;
		this.endRow    = 0;
		this.pageSize  = null;
		this.datePattern = sensus.settings.DATE_FORMAT;
		this.timezone = sensus.settings.timezone;

		if (oSearch != null) {

			this.processFilter = oSearch;

		}

		//this.userContext = new UserContext();

	}

	function inquiryScheduleRequest (oSearch) {

		this.startRow  = 0;
		this.endRow    = 0;
		this.pageSize  = 25;
/*
		if (oSearch != null) {

			this.baseSearch = oSearch;

		}*/

		//this.userContext = new UserContext();

	}


	function inquiryUserRequest (oSearch) {

		this.startRow  = 0;
		this.endRow    = 0;
		this.pageSize  = 25;

		if (oSearch != null) {

			//this.baseSearch = oSearch;
			this.action = oSearch.action;

			this.user = oSearch.user;
		}

		//this.userContext = new UserContext();

	}

	function inquiryTagRequest (oSearch) {

		this.startRow  = 0;
		this.endRow    = 0;
		this.pageSize  = null;

		/*if (oSearch != null) {

			this.baseSearch = oSearch;

		}*/

		//this.userContext = new UserContext();

	}

	function tagRequest (iTagId, sTagName, aTags, isAllRows, aSmartpointIds, oSearchLight, bIncludeSmartPointsToGroup, bAutoGroup) {

		this.tag                        = {};
		this.tag.id                     = iTagId;
		this.tag.name                   = sTagName;
		this.tag.autoGroup              = bAutoGroup;
		this.tags                       = aTags;
		this.paginationAllSelected      = isAllRows;
		this.selectionPaginationIds     = aSmartpointIds;
		this.searchLight                = {searchParameters: []};
		//this.userContext                = new UserContext();
		this.includeSmartPointsToGroup	= bIncludeSmartPointsToGroup;

	}

	function settingsRequest (aSettings, sActualPassword, sNewPassword) {

		this.settings        = aSettings;
		this.actualPassword  = sActualPassword;
		this.newPassword     = sNewPassword;
		//this.userContext     = new UserContext();

	}

	function processRequest(aProcessList, oProcessFilter, isSorted, nId) {

		if(nId){

			this.process 	= {};
			this.process.id	= nId;

		}
		this.isSorted       			= isSorted;
		this.processList    			= aProcessList;
		//this.userContext    			= new UserContext();
		this.processFilter  			= oProcessFilter;
		//this.sortExpressions  			= [];

	}

	function userRequest (sUserId, sUserName, sFirstName, aRole, sLastName, sPassword, sEmail, bAllLightsAuth, aGroups, paginationAllSelected, selectionPaginationIds, sAction) {

		this.user = {
				id             : sUserId,
				userName       : sUserName,
				firstName      : sFirstName,
				role          : aRole,
				email          : sEmail,
				groups         : aGroups,
				lastName       : sLastName,
				password       : sPassword,
				allLightsAuth  : bAllLightsAuth
		};

		this.paginationAllSelected        = paginationAllSelected;
		this.selectionPaginationIds  	  = selectionPaginationIds;
		//this.userContext                  = new UserContext();

	}

	function analyticsRequest (statusExceptionTypeEnum, sAction) {

		//try {
			// Convert String to object date
			var dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("di"));
			dStartDate.setHours(0);
			dStartDate.setMinutes(0);
			dStartDate.setSeconds(0);

			var dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("de"));
			dEndDate.setHours(23);
			dEndDate.setMinutes(59);
			dEndDate.setSeconds(59);

			var sType = $.address.parameter('ty');

			if (sType != '5' && sType != '6') {

				//dStartDate = $.date.fnTimeToUTC(dStartDate);
				//dEndDate   = $.date.fnTimeToUTC(dEndDate);

			} else {

				dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate)+'T00:00:00';
				dEndDate   = $.datepicker.formatDate('yy-mm-dd', dEndDate)+'T23:59:59';

			}


			var oGroup = null;
/*
			if ( $.address.parameter('g') && $.address.parameter('g') != "0") {
				oGroup = { id : $.address.parameter('g') };
			}
*/
			this.action = sAction;
			this.group = oGroup;

			this.initialDate            = dStartDate;
			this.endDate                = dEndDate;
			this.statusExceptionTypeEnum = statusExceptionTypeEnum; //"LAMP_FAILURE";
			this.analyticsTypeEnum      = sensus.widgets.datatable.fnGetEnumValue('AnalyticsTypeEnum', $.address.parameter('ty'));
			this.analyticsDateTypeEnum  = sensus.widgets.datatable.fnGetEnumValue('AnalyticsDateTypeEnum', $.address.parameter('dt'));
/*
		} catch(err) {

		}*/

		//this.startRow               = 0;
		//this.endRow                 = 0;
		//this.pageSize               = 25;
		//this.userContext            = new UserContext();
		//this.fileName = "asa"
		/*if (statusExceptionTypeEnum != null) {

			this.statusExceptionTypeEnum = statusExceptionTypeEnum;

		}*/

	}

	function inquiryAnalyticsRequest (statusExceptionTypeEnum) {

		// Convert String to object date

		var dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("di"));
		dStartDate.setHours(0);
		dStartDate.setMinutes(0);
		dStartDate.setSeconds(0);

		var dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("de"));
		dEndDate.setHours(23);
		dEndDate.setMinutes(59);
		dEndDate.setSeconds(59);

		//dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate)+'T00:00:00';
		//dEndDate   = $.datepicker.formatDate('yy-mm-dd', dEndDate)+'T23:59:59';

		var sType = $.address.parameter('ty');

		if (sType != '5' && sType != '6') {

			dStartDate = $.sc.dateToUTC(dStartDate);
			dEndDate   = $.sc.dateToUTC(dEndDate);

		} else {

			dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate)+'T00:00:00';
			dEndDate   = $.datepicker.formatDate('yy-mm-dd', dEndDate)+'T23:59:59';

		}

		this.group                  = { id : $.address.parameter('g') };
		this.startRow               = 0;
		this.endRow                 = 0;
		this.pageSize               = null;
		this.initialDate            = dStartDate;
		this.endDate                = dEndDate;
		this.analyticsTypeEnum      = sensus.widgets.datatable.fnGetEnumValue('AnalyticsTypeEnum', $.address.parameter('ty'));
		this.analyticsDateTypeEnum  = sensus.widgets.datatable.fnGetEnumValue('AnalyticsDateTypeEnum', $.address.parameter('dt'));

		if (statusExceptionTypeEnum != null) {

			this.statusExceptionTypeEnum = statusExceptionTypeEnum;

		}

	}

	function lightRequest (iId, aSelectionPaginationIds, bProtect, bPaginationAllSelected, iLightStatusEnum, bIsMonitored, oSearchLight, iIntensity, lightBlinkEnum, overrideEnum, dExpireDate, bClearOverride, oStatusMessages, aParameters) {

		if (iId != null) {
			this.lights       = [{ 'id' : iId }];
		}
		if(oSearchLight){

			var oStatusList = $.grep(oSearchLight.searchParameters, function(e) { return e.propertyEnum == "ALL_STATUS" });

			if (oStatusList.length) {

				oSearchLight.statusList = [sensus.widgets.datatable.fnGetEnumValue('LightStatusEnum', oStatusList[0].value)];

			}

		}
		this.paginationAllSelected  = bPaginationAllSelected;
		this.selectionPaginationIds = aSelectionPaginationIds;
		this.protect                = bProtect;
		this.lightStatusEnum        = iLightStatusEnum;
		//this.userContext            = new UserContext();
		this.isMonitored            = bIsMonitored;
		this.searchLight            = oSearchLight;
		this.percentage             = iIntensity;
		this.lightBlinkEnum         = lightBlinkEnum;
		this.overrideEnum		    = overrideEnum;
		this.overridePerDate        = dExpireDate;
		this.isClearOverride		= bClearOverride;
		if(oStatusMessages) {

			this.statusMessages = oStatusMessages;

		}
		if(aParameters){

			this.lights[0].parameters = aParameters;

		}

	}

	function groupRequest (groupName, groupDescription, groupOldName, groupId, isAllRows, aIdsSelected, oSearchLight, aGroupList, nBottomLeftLat, nBottomLeftLon, nTopRightLat, nTopRightLon, nMaxSmartpointCount) {

		this.group              = {};
		this.group.id           = groupId;
		this.group.name         = groupName;
		this.group.description  = groupDescription;


		if (groupOldName) {

			this.oldName = groupOldName;

		}

		if(!aIdsSelected || aIdsSelected.length == 0) {

				aIdsSelected = [];

		}

		this.searchLight             = oSearchLight;
		this.paginationAllSelected   = isAllRows;
		this.selectionPaginationIds  = aIdsSelected;
		this.groupList 		         = aGroupList;
		//this.bottomLeftLat			 = nBottomLeftLat;
		this.bottomLeftLon			 = nBottomLeftLon;
		this.topRightLat			 = nTopRightLat;
		this.topRightLon			 = nTopRightLon;
		this.maxSmartpointCount	 	 = nMaxSmartpointCount;

	}

	function OffsetSchedule() {

		this.id = 1;

	}

	function offsetSchedule(scheduleType,sName,sDescription,sIntensity) {

		this.offsetSchedule                   = {};
		this.offsetSchedule.name              = sName;
		this.offsetSchedule.description       = sDescription;
		this.offsetSchedule.scheduleTypeEnum  = 'OFFSET';
		this.offsetSchedule.intensity         = sIntensity;
		this.userContext                      = new UserContext();

	}

	function customSearchRequest (nId, sSearchName, sSearchDescription, sStatusList, aSearchParameters, aListFilters) {

		this.customSearch 					  = {};
		this.customSearch.id 				  = nId;
		this.customSearch.name 				  = sSearchName;
		this.customSearch.description 		  = sSearchDescription;
		this.customSearch.statusList 		  = sStatusList;
		this.customSearch.searchParameters 	  = aSearchParameters;
		this.customSearch.listFilters 		  = aListFilters;
		this.userContext                      = new UserContext();

	}

	function scheduleRequest (nId, nBottomLeftLat, nBottomLeftLon, nTopRightLat, nTopRightLon, nMaxSmartpointCount, oSchedule, sScheduleType, isAllRows, aIdsSelected, oSearchLight, sScheduleName, aLightIdList) {

		var sScheduleEnum = 'EVENT';
		if(sScheduleType == 'offsetSchedule' || sScheduleType == 'OFFSET'){

			sScheduleEnum = 'OFFSET';

		}
		this.scheduleTypeEnum		 = sScheduleEnum;
		if(nId){

			this.schedule             	 	= {};
			this.schedule.id           	 	= nId;
			this.schedule.name         		= sScheduleName;
			this.schedule.scheduleTypeEnum	= sScheduleEnum;

		} else if(oSchedule){

			this[sScheduleType] = oSchedule;
			this[sScheduleType].scheduleTypeEnum = sScheduleEnum;

		} else {
			this.schedule = {};
			this.schedule.scheduleTypeEnum = sScheduleEnum;

		}
		if(!aIdsSelected){

			aIdsSelected = [];

		}

		//this.userContext             = new UserContext();
		this.bottomLeftLat			 = nBottomLeftLat;
		this.bottomLeftLon			 = nBottomLeftLon;
		this.topRightLat			 = nTopRightLat;
		this.topRightLon			 = nTopRightLon;
		this.maxSmartpointCount	 	 = nMaxSmartpointCount;
		this.searchLight             = oSearchLight;
		this.paginationAllSelected   = isAllRows;
		this.selectionPaginationIds  = aIdsSelected;
		this.lightIdList 			 = aLightIdList;


	}

	function eventSchedule(nId, sDescription, sName, sScheduleType, aEvents) {

		this.id = nId;
		this.description = sDescription;
		this.name = sName;
		this.scheduleTypeEnum = sScheduleType;
		this.events = aEvents;
	};

	function offsetSchedule(nId, sDescription, sName, sScheduleType, bSunriseOffsetMinutes, bSunsetOffsetMinutes, bSunriseBefore, bSunsetBefore, iIntensity) {

		this.id 		  		  = nId;
		this.description 		  = sDescription;
		this.name 		  		  = sName;
		this.scheduleTypeEnum	  = sScheduleType;
		this.sunriseOffsetMinutes = bSunriseOffsetMinutes;
		this.sunsetOffsetMinutes  = bSunsetOffsetMinutes;
		this.sunriseBefore        = bSunriseBefore;
		this.sunsetBefore 		  = bSunsetBefore;
		this.intensity 			  = iIntensity;

	};

	function lightSelectionRequest (bMonitored){

		this.monitored = bMonitored;

	}

	function ecoModeBaseline (oLight, sType, nWattage){

		this.light = oLight;
		this.replacedType = sType;
		this.replacedWattage = nWattage;

	}

	function inquiryEcoModeRequest(aEcoModeBaseline, aIdsSelected){

		this.datePattern = sensus.settings.DATE_FORMAT;
		this.timezone = sensus.settings.timezone;
		this.ecoModeBaselineList = aEcoModeBaseline;
		this.pageSize = $("#ecomode-table_length select").val();
		this.selectionPaginationIds  = [$.address.parameter('id')];
		var dStartDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("di"));
		dStartDate.setHours(0);
		dStartDate.setMinutes(0);
		dStartDate.setSeconds(0);

		var dEndDate = $.datepicker.parseDate(sensus.settings.DATE_FORMAT, $.address.parameter("de"));
		dEndDate.setHours(23);
		dEndDate.setMinutes(59);
		dEndDate.setSeconds(59);

		dStartDate = $.datepicker.formatDate('yy-mm-dd', dStartDate)+'T00:00:00';
		dEndDate   = $.datepicker.formatDate('yy-mm-dd', dEndDate)+'T23:59:59';

		this.initialDate = dStartDate;
		this.endDate = dEndDate;
		//this.userContext = new UserContext();

	}

	function dicionarioRequest (groupName, groupId, isAllRows, aIdsSelected, oSearchLight) {

		this.tela              = {};
		this.tela.codTela      = groupId;
		this.tela.nmTela       = groupName;


		if(!aIdsSelected || aIdsSelected.length == 0) {

				aIdsSelected = [];

		}

		this.searchLight             = oSearchLight;
		this.paginationAllSelected   = isAllRows;
		this.selectionPaginationIds  = aIdsSelected;

	}

	function inquiryTelaRequest (oSearch, codTela) {

		this.tela          = [{'codTela': codTela}];


	}

