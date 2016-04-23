qat.model.util.filter = {

	api : "api/filter/fetch",

	aAllFilters : ["group", "lifecycle_state", "alert", "alarm", "warning", "device_subtype", "install_date", "address", "flexnet_firmware", "tag", "remote_disconnect", "description",],

	//This property is used on widgetfilter on _createOptionFilters()
	oDefaultCheckboxEvent : {},

	createRequest : function (oFilters) {

		var sDeviceType = "";
		var sDeviceSubType 	= "device_subtype";
		var aUrlParameters 	= $.address.parameterNames();
		var filters 		= [];
		var _paramRequest;
		var sValue;
		var i;
		var sTheDeviceType = "";

		if (!$.sc.isNullOrUndefined($.address.parameter("device_type"))) {
			sDeviceType = $.address.parameter("device_type");
			sTheDeviceType = "device_type";
		} else if (!$.sc.isNullOrUndefined($.address.parameter("deviceType"))) {
			sDeviceType = $.address.parameter("deviceType");
			sTheDeviceType = "deviceType";
		}

		if (!$.sc.isNullOrUndefined($.address.parameter("device_subtype"))) {
			sDeviceSubType = "device_subtype";
		} else if (!$.sc.isNullOrUndefined($.address.parameter("typeEnum"))) {
			sDeviceSubType = "typeEnum";
		}

		for (i in oFilters) {

			if (oFilters[i]) {

				filters.push(i);
			}
		}

		for (i in aUrlParameters) {

			sValue = aUrlParameters[i];

			if (sValue) {

					if (($.inArray(sValue, this.aAllFilters) != -1) && ($.inArray(sValue, filters) == -1)) {

						filters.push(sValue);
					}
			}
		}

		if (sDeviceType) {

			_paramRequest = qat.model.util.filter.paramRequest;

			return {
				filter : filters,
				deviceType : _paramRequest.getParameterArray(sTheDeviceType),
				deviceSubTypes : _paramRequest.getParameterArray(sDeviceSubType)
			};
		}

		return {filter : filters};
	},

	createResponse : function (oResponse, oFilters) {
		oResponse = oResponse.responseJSON ? oResponse.responseJSON : oResponse;
		var aFilters 			= [];
		var aUrlParameters 		= $.address.parameterNames();
		var aAdditionalFilters 	= ["firmware", "start", "end", "street", "city", "zip"];
		var sFilter;
		var sValue;
		var i;

		for (i in oFilters) {

			aFilters.push(i);

			if (oResponse[i]) {

				oFilters[i].dataList = oResponse[i];
			}
		}

		for (i in oResponse) {

			if ($.inArray(i, aFilters) == -1) {

				oFilters[i] = qat.model.util.filter.filters[i];
				oFilters[i].dataList = oResponse[i];
			}
		}

		for (i in aUrlParameters) {

			sValue = aUrlParameters[i];

		    if (sValue) {

		    	if ($.inArray(sValue, aAdditionalFilters) != -1) {

		    		if (sValue == "start" || sValue == "end") {

		    			sFilter = "install_date";

		    		} else if (sValue == "street" || sValue == "city" || sValue == "zip") {

		    			sFilter = "address";

		    		} else {

		    			sFilter = "flexnet_firmware";
		    		}

		    		if ($.sc.isNullOrUndefined(oFilters[sFilter])) {

						oFilters[sFilter] = qat.model.util.filter.filters[sFilter];
					}
		    	}
		    }
		}

		return oFilters;
	},

	init : function (preLoad, oFilters, fnCallback) {

		if (preLoad == "refresh" || $.sc.isNullOrUndefined(preLoad)) {

			$.ajaxValidator.fnDoCall(this.api, this.createRequest(oFilters), false, function(oResponse) {

				oResponse = qat.model.util.filter.createResponse(oResponse, oFilters);

				if (fnCallback) {

					fnCallback(oResponse);
				}
			}, null, null, null, true, true);

		} else if (!$.sc.isNullOrUndefined(preLoad)) {

			if (fnCallback) {

				fnCallback(qat.model.util.filter.createResponse(preLoad, oFilters));
			}
		}
	},

	filterArrayToObject : function (arr) {

		var i = 0;
		var length = arr ? arr.length : 0;
		var filters = this.filters;
		var filter;
		var filterId;
		var o = {};

		for (; i < length; i = i + 1) {

			filterId = arr[i].toLowerCase();
			filter = filters[filterId];

			o[filterId] = filter;
		}

		return length == 0 ? null : o;
	},

	/**
	 * Format URL Filters
	 */

	fnFormatURLFilter : function (sFilterValue, bIsString, sType) {

		var sFormatFilter = null;

		if (!$.ajaxValidator.fnIsNullOrUndefined(sFilterValue)) {

			if (bIsString) {
				sFormatFilter = decodeURI(sFilterValue);
			} else {
				sFormatFilter = decodeURI(sFilterValue).replace(/%26/g, '&').slice(0, -1).split('|');
			}

			if (sType == "int") {
				 var sFormatFilter = $.map(sFormatFilter, function(item, index) {
					return window.parseInt(item);
				});
			}
		}
		return sFormatFilter;
	},

	/**
	 * Format Date Filter
	 */

	fnFormatDateFilter : function (sDate, sTypeDate, sStartDate, oTz) {

		var dFormatDate = null;
		var sDateFormatMask = qat.model.settings.userSettings.dateFormatMask.replace("yyyy", "yy") ;
		var dDate;

		if (!$.ajaxValidator.fnIsNullOrUndefined(sDate)) {

			if (sDate.length && (sTypeDate == 'startDate' || sTypeDate == 'endDate' || sTypeDate == 'date_start')) {

				dDate = $.datepicker.parseDate(sDateFormatMask, sDate);

				var dCurrentDate = $.date.setDateServer(true);

				dDate.setHours(dCurrentDate.getHours());
				dDate.setMinutes(dCurrentDate.getMinutes());
				dDate.setSeconds(dCurrentDate.getSeconds());

				if (sTypeDate == 'date_start') {

					if (sStartDate == 'startYear') {
						dDate.setMonth(0);
						dDate.setDate(1);
					} else if (sStartDate == 'endYear') {
						dDate.setMonth(11);
						dDate.setDate(31);
					}
				}

			} else if (!$.ajaxValidator.fnIsNullOrUndefined(sStartDate)) {

					if (sStartDate.length) {
						dDate = $.datepicker.parseDate(sDateFormatMask, sStartDate);
					} else {
						dDate 		= $.date.setDateServer();
					}

					dDate.setDate(dDate.getDate() - parseInt(sDate));

			} else {
				dDate 		= $.date.setDateServer(true);
			}

			if (sTypeDate == 'startDate' || sTypeDate == 'date_start' || sTypeDate =='setStartDate')
			{

				if ($.sc.isNullOrUndefined(sStartDate) || sStartDate.length == 0)
				{
					dDate = $.date.fnGetDate(dDate);
				}

				dDate.setHours(0);
				dDate.setMinutes(0);
				dDate.setSeconds(1);
			} else
			{
				dDate = $.date.fnGetDate(dDate);

				dDate.setHours(23);
				dDate.setMinutes(59);
				dDate.setSeconds(59);

			}

			// When it do not receive time Zone, apply application time zone
			if ($.sc.isNullOrUndefined(oTz)) {
				oTz = true;
			}

			dFormatDate = $.date.fnTimeToUTC(dDate, oTz);
		}

		return dFormatDate;
	},


	getDeviceFiltersByType : function (sDeviceType) {

		var service = qat.model.constants.services;

		if (!sDeviceType) {

			sDeviceType = $.address.parameter("device_type").replace("|", "");
		}

		switch (sDeviceType) {

		case service.electric.meter.name:
			return ["group","lifecycle_state", "alert", "alarm", "warning", "meter_type" ,"install_date", "address", "flexnet_firmware", "tag","remote_disconnect"];

		case service.electric.lcm.name:
			return ["group","lifecycle_state", "alert", "alarm", "warning", "device_family", "install_date","address","tag","device_type"];

		case service.electric.han.name:
			return ["group","lifecycle_state","install_date","address","tag","device_type"];

		case service.gas.meter.name:
			return ["group", "alert", "alarm", "warning", "status_meter", "install_date", "address", "flexnet_firmware", "quarantine", "tag", "meter_type"];

		case service.water.meter.name:
			return ["group", "alert", "alarm", "warning", "status_meter", "install_date", "address", "flexnet_firmware", "quarantine", "tag", "meter_type"];
		}
	},

	options : {
		id : {
			value : "ID",
			label : "commons.pages.id"
		},
		network_address : {
			value : "NETWORK_ADDRESS",
			label : "summary.text.headerTable.Network_Address"
		},
		device_id : {
			value : "DEVICE_ID",
			label : "commons.pages.device_id"
		},
		premise_id : {
			value : "PREMISE_ID",
			label : "commons.pages.premise_id"
		}
	},

	paramRequest : {

		parameterArrayBind : "|",

		iterator : function (arr, fn) {

			var i;
			var length;

			if (arr && arr.length && fn) {

				for (i = 0, length = arr.length; i < length; i = i + 1) {

					arr[i] = fn(arr[i]);
				}
			}

			return arr;
		},

		getParameter : function (sParameter) {

			var value = $.address.parameter(sParameter);

			if (value) {

				return decodeURI(value).replace(this.parameterArrayBind, "");
			}

			return null;
		},

		getParameterArray : function (sParameter) {

			var value = $.address.parameter(sParameter);
			var arr;

			if (value) {

				value = decodeURI(value);
				arr = value.split(this.parameterArrayBind);

				if (!arr[arr.length - 1]) {

					arr.pop();
				}

				return arr.length ? arr : null;
			}

			return null;
		},

		getDate : function (sDate, sTypeDate) {

			var sDateFormatMask = qat.model.settings.userSettings.dateFormatMask.replace("yyyy", "yy");
			var dDate;

			if (sDate) {

				dDate = $.datepicker.parseDate(sDateFormatMask, sDate);

				switch (sTypeDate) {

				case "lastHour" :

					dDate.setHours(23);
					dDate.setMinutes(59);
					dDate.setSeconds(59);
					break;

				case "firstHour" :

					dDate.setHours(0);
					dDate.setMinutes(0);
					dDate.setSeconds(1);
					break;
				}

				return $.date.fnTimeToUTC(dDate);
			}

			return null;
		},

		getIntParameterArray : function (sParameter) {

			return this.iterator(this.getParameterArray(sParameter), function (value) {

				return parseInt(value, 10);
			})
		},

		getIntParameter : function (sParameter) {

			return parseInt(this.getParameter(sParameter), 10);
		},

		description : function () {

			return this.iterator(this.getParameterArray("description"), function(value) {

				return {id : parseInt(value, 10)};
			});
		},

		quarantine : function () {

			var quarantine = this.getParameterArray("quarantine");

			if (quarantine) {

				return quarantine.join("");
			}

			return null;
		}
	},

	filters : {
		search : {
			type : "search",
			goButtonLabel : "commons.pages.search",
			inputs : {
				query : {
					type : "text",
					name : "query"
				},
				query_type : {
					type : "select",
					name : "query",
					options : []
				}
			},

			validate : function ($form, $filter) {

				var $select = $filter.find("select");
				var $input = $filter.find("input");
				var type = $select.val();

				switch (type) {
				case "DEVICE_ID":
				case "PREMISE_ID":

					$input.attr("class", "validate[required,maxSize[25],custom[noSpecialCaractersOfSearch]]");
					break;

				case "NETWORK_ADDRESS" :

					$input.attr("class", "validate[required]");

					sValidationError = qat.model.util.search.fnValidateQuickSearchField($input.val(), "NETWORK_ADDRESS", $input);

					if (sValidationError.length) {
						$input.validationEngine('showPrompt', sValidationError, 'red', 'topLeft', true);
						return true;
					}
					break;

				case "ID" :

					$input.attr("class", "validate[required, custom[integer],min[0],maxSize[25]]");
					break;

				default:
					break;
				}

				return $("#" + $input.attr("id")).validationEngine("validate");
			}
		},

		tag : {
			type : "options",
			title : "commons.pages.tag",
			allLabel : "filter.all.tag",
			urlParameter : "tag",
			propertyTitle : "name",
			propertyValue : "id"
		},

		group : {
			type : "options",
			title : "commons.pages.group",
			allLabel : "filter.all.groups",
			urlParameter : "group",
			propertyTitle : "name",
			propertyValue : "id"
		},

		lifecycle_state : {
			type : "options",
			title : "commons.pages.lifecycle_state",
			allLabel : "filter.all.lifecycle_state",
			urlParameter : "lifecycle_state",
			propertyValue : 0,
			propertyTitle : 1
		},

		device_type : {
			type : "options",
			title : "commons.pages.device_type",
			allLabel : "filter.all.device_type",
			urlParameter : "description",
			propertyTitle : "description",
			propertyValue : "id"
		},

		meter_type : {
			type : "options",
			title : "commons.pages.meter_type",
			allLabel : "filter.all.meter_type",
			urlParameter : "description",
			propertyTitle : "description",
			propertyValue : "id"
		},

		remote_disconnect : {
			type : "options",
			title : "commons.pages.remote_disconnect",
			allLabel : "filter.all.remote_disconnect",
			urlParameter : "remote_disconnect",
			propertyValue : 0,
			propertyTitle : 1
		},

		quarantine : {
			type : "options",
			title : "commons.pages.quarantine",
			allLabel : "filter.all.quarantine",
			urlParameter : "quarantine",
			propertyValue : 0,
			propertyTitle : 1
		},

		alert : {
			type : "options",
			title : "smartpointdetail.page.alerts",
			allLabel : "filter.all.alerts",
			urlParameter : "alert",
			propertyValue : 0,
			propertyTitle : 1
		},

		alert_state : {
			type : "options",
			title : "smartpointdetail.page.alertStatus",
			allLabel : "filter.all.alertState",
			urlParameter : "alert_state",
			propertyValue : 0,
			propertyTitle : 1
		},

		alarm : {
			type : "options",
			title : "smartpointdetail.page.alarms",
			allLabel : "filter.all.alarms",
			urlParameter : "alarm",
			propertyValue : 0,
			propertyTitle : 1
		},

		warning : {
			type : "options",
			title : "smartpointdetail.page.warnings",
			allLabel : "filter.all.warnings",
			urlParameter : "warning",
			propertyValue : 0,
			propertyTitle : 1
		},

		status_meter : {
			type : "options",
			title : "commons.pages.status_meter",
			allLabel : "filter.all.status_meter",
			urlParameter : "status_meter",
			propertyValue : 0,
			propertyTitle : 1
		},

		device_family : {
			type : "options",
			title : "commons.pages.device_family",
			allLabel : "filter.all.device_family",
			urlParameter : "device_subtype",
			propertyValue : 0,
			propertyTitle : 1
		},

		group_type : {
			type : "options",
			title : "commons.pages.group_type",
			allLabel : "filter.all.group_type",
			urlParameter : "group_type",
			propertyValue : 0,
			propertyTitle : 1
		},

		action_categories : {
			type : "options",
			title : "commons.pages.action_type",
			allLabel : "filter.all.action_type",
			urlParameter : "action_categories",
			propertyValue : 0,
			propertyTitle : 1
		},

		schedule_action_categories : {
			type : "options",
			title : "commons.pages.action_type",
			allLabel : "filter.all.action_type",
			urlParameter : "schedule_action_categories",
			propertyValue : 0,
			propertyTitle : 1
		},

		all_action_categories : {
			type : "options",
			title : "commons.pages.action_type_all",
			allLabel : "filter.all.action_type_all",
			urlParameter : "all_action_categories",
			propertyValue : 0,
			propertyTitle : 1
		},

		group_device_type : {
			type : "options",
			title : "commons.pages.group_device_type",
			allLabel : "filter.all.group_device_type_all",
			urlParameter : "group_device_type",
			propertyValue : 0,
			propertyTitle : 1
		},

		users : {
			type : "options",
			title : "commons.pages.users",
			allLabel : "filter.all.users",
			urlParameter : "users",
			propertyValue : "userId",
			propertyTitle : "userId"
		},

		status_scheduled : {
			type : "options",
			title : "commons.pages.status_scheduled",
			allLabel : "filter.all.status_scheduled",
			urlParameter : "status_scheduled",
			propertyValue : 0,
			propertyTitle : 1
		},

		repeats : {
			type : "options",
			title : "commons.pages.repeats",
			allLabel : "filter.all.repeats",
			urlParameter : "repeats",
			propertyValue : 0,
			propertyTitle : 1
		},

		address : {
			type : "search",
			title : "commons.pages.address",

			inputs : {
				street : {
					type : "text",
					name : "street",
					label : "commons.pages.street",
					clazz : "short validate[required, maxSize[50]]",
					maxsize : 100
				},
				city : {
					type : "text",
					name : "city",
					label : "commons.pages.city",
					clazz : "short validate[required, maxSize[25], custom[onlyLetterSp, noSpecialCaracters]]",
					maxsize : 100
				},
				zip : {
					type : "text",
					name : "zip",
					label : "commons.pages.zip",
					maxsize : 100,
					clazz : "short validate[required, maxSize[10], custom[number, noSpecialCaracters]]",
					goButtonLabel : "widgets.filter.go"
				}
			}
		},

		install_date : {
			type : "search",
			title : "commons.pages.install_date",
			joinTags : true,
			inputs : {
				start : {
					name : "start",
					type : "text",
					label : "commons.pages.start",
					clazz : "standard date-filter"
				},
				end : {
					name : "end",
					type : "text",
					label : "commons.pages.end",
					clazz : "date-filter",
					goButtonLabel : "widgets.filter.go",
					style : "width:64%"
				}
			},

			validate : function ($form, $filter) {

				var $start				= $filter.find("#start");
				var $end				= $filter.find("#end");
				var dateFormat			= qat.model.settings.userSettings.dateFormatMask.replace('yyyy','yy');
				var sClassTypeFormat	= 'dateEn';
				var dStart;

				if (dateFormat == 'dd/mm/yy') {

					sClassTypeFormat = 'datePt';
				}

				$start.attr("class", this.inputs.start.clazz + " validate[required, custom[" + sClassTypeFormat + "]");

				if (!$start.validationEngine("validate")) {

					dStart = $.date.parseDate($start.val(), dateFormat);

					$end.attr("class", this.inputs.end.clazz + " validate[required, custom["
							+ sClassTypeFormat + ", future[" + $.date.dateFormat(dStart, "yy-mm-dd", {bUserTZ : false}) + "]");

					return $end.validationEngine("validate");
				}

				return true;
			}
		},

		flexnet_firmware : {
			type : "search",
			title : "firmware.firmwareFlexnet",
			inputs : {
				firmware : {
					name : "firmware",
					type : "text",
					clazz : "short input-clear validate[required, maxSize[17]]",
					hint : "1.1.1.1",
					goButtonLabel : "widgets.filter.go"
				}
			},

			validate : function ($form, $filter) {

				var $input = $filter.find("input");

				if (!$input.validationEngine("validate")) {

					if ($input.val().search(/^[0-9\.]+$/) != 0 || $input.val().contains("..")) {

						$input.validationEngine('showPrompt', qat.model.locale.get('commons.pages.onlyNumbersAndDecimal'), 'red', 'topLeft', true);
						return true;
					}

					return false;
				}

				return true;
			}
		},

		query_search : {
			type : "search",
			goButtonLabel : "widgets.filter.go",
			inputs : {
				query : {
					name : "query",
					type : "text",
					clazz : "input-clear validate[required, custom[noSpecialCaracters],min[0],maxSize[100]]",
					hintMessageKey : "commons.pages.search"
				}
			},

			validate : function ($form, $filter) {

				return $("#query").validationEngine("validate");
			}
		},

		date_filter : {
			type : "search",
			joinTags : true,
			inputs : {
				view_from : {
					name : "view_from",
					type : "text",
					label : "widgets.filter.View",
					span : true,
					clazz : "date-filter",
					style : "width:65%"
				},
				total_days : {
					name : "total_days",
					type : "text",
					label : "widgets.filter.back",
					span : true,
					clazz : "",
					goButtonLabel : "commons.pages.Days",
					style : "width:2em"
				}
			},

			validate : function ($form, $filter) {

				var $view 				= $filter.find("#view_from");
				var $back 				= $filter.find("#total_days");
				var dateFormat 			= qat.model.settings.userSettings.dateFormatMask.replace('yyyy','yy');
				var sClassTypeFormat 	= 'datePt';

				if (dateFormat == 'mm/dd/yy') {
					sClassTypeFormat = 'dateEn';
				}

				$view.addClass("validate[required, custom["+sClassTypeFormat+"]");

				if ($view.validationEngine("validate")) {

					return $("#" + $view.attr("id")).validationEngine("validate");

				} else {

					$back.addClass("validate[required,custom[integer],min[0],max[365]");

					return $("#" + $back.attr("id")).validationEngine("validate");

				}

				return false;
			}
		}
	}
}