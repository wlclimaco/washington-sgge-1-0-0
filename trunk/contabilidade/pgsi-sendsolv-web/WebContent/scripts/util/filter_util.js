pgsi.util.filter =
{
	api : "api/customization/fetchFilter",

	createRequest : function (oFilters)
	{
		var _paramRequest;
		var properties = [];
		var i;

		for (i in oFilters)
		{
			if (oFilters[i])
			{
				properties.push({
					propertyValue 	: i.toUpperCase()
				});
			}
		}

		// CustomizationCriteria Object
		var customizationCriteria = {
			properties 	: properties
		};

		// customizationRequest Object
		var customizationRequest = new CustomizationRequest({
			customizationCriteria : customizationCriteria
		});

		return customizationRequest
	},

	mergeResponse : function (oResponse, oFilters)
	{
		// Filters list from response
		oResponse = oResponse.filters;

		var i;
		for (i in oFilters)
		{
			if (oResponse[i])
			{
				if ($.pgsi.isValidArray(oResponse[i][0]) && oFilters[i].type === "search")
				{
					var iOptionsCount = 0;
					for (var j in oFilters[i].inputs)
					{
						oFilters[i].inputs[j].options = oResponse[i][iOptionsCount];
						iOptionsCount++;
					}
				}
				else
				{
					oFilters[i].dataList = oResponse[i];
				}
			}
		}

		return oFilters;
	},

	init : function (preLoad, oFilters, fnCallback)
	{
		if (preLoad == "refresh" || $.pgsi.isNullOrUndefined(preLoad))
		{
			$.pgsi.ajax.post({
				sUrl		: this.api,
				oRequest	: this.createRequest(oFilters),
				fnCallback	: function(oResponse)
				{
					oResponse = pgsi.util.filter.mergeResponse(oResponse, oFilters);

					if (fnCallback)
					{
						fnCallback(oResponse);
					}
				}
			});
		}
		else if (!$.pgsi.isNullOrUndefined(preLoad))
		{
			if (fnCallback)
			{
				fnCallback(pgsi.util.filter.mergeResponse(preLoad, oFilters));
			}
		}
	},

	filterArrayToObject : function (arr)
	{
		var i = 0;
		var length = arr ? arr.length : 0;
		var filters = this.filters;
		var filter;
		var filterId;
		var o = {};

		for (; i < length; i = i + 1)
		{
			filterId = arr[i].toLowerCase();
			filter = filters[filterId];

			o[filterId] = filter;
		}

		return length == 0 ? null : o;
	},

	/**
	 * Format URL Filters
	 */

	fnFormatURLFilter : function (sFilterValue, bIsString, sType)
	{
		var sFormatFilter = null;

		if (!$.pgsi.isNullOrUndefined(sFilterValue))
		{
			if (bIsString)
			{
				sFormatFilter = decodeURI(sFilterValue);
			}
			else
			{
				sFormatFilter = decodeURI(sFilterValue).replace(/%26/g, '&').slice(0, -1).split('|');
			}

			if (sType == "int")
			{
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

	fnFormatDateFilter : function (sDate, sTypeDate, sStartDate, oTz)
	{
		var dFormatDate = null;
		var sDateFormatMask = pgsi.settings.user.dateFormat;
		var dDate;

		if (!$.pgsi.isNullOrUndefined(sDate))
		{
			if (sDate.length && (sTypeDate == 'startDate' || sTypeDate == 'endDate' || sTypeDate == 'date_start'))
			{
				dDate = $.datepicker.parseDate(sDateFormatMask, sDate);

				if (sTypeDate == 'date_start')
				{
					if (sStartDate == 'startYear')
					{
						dDate.setMonth(0);
						dDate.setDate(1);
					}
					else if (sStartDate == 'endYear')
					{
						dDate.setMonth(11);
						dDate.setDate(31);
					}
				}

			}
			else if (!$.pgsi.isNullOrUndefined(sStartDate))
			{
				if (sStartDate.length)
				{
					dDate = $.datepicker.parseDate(sDateFormatMask, sStartDate);
				}
				else
				{
					dDate 		= $.pgsi.date.getCurrentDate();
				}

				dDate.setDate(dDate.getDate() - parseInt(sDate));

			}
			else
			{
				dDate 		= $.pgsi.date.getCurrentDate(true);
			}

			if (sTypeDate == 'startDate' || sTypeDate == 'date_start' || sTypeDate =='setStartDate')
			{
				dDate.setHours(0);
				dDate.setMinutes(0);
				dDate.setSeconds(0);
				dDate.setMilliseconds(000);
			}
			else
			{
				dDate.setHours(23);
				dDate.setMinutes(59);
				dDate.setSeconds(59);
				dDate.setMilliseconds(999);
			}

			// When it do not receive time Zone, apply application time zone
			if ($.pgsi.isNullOrUndefined(oTz))
			{
				oTz = true;
			}

			dFormatDate = $.pgsi.date.toUTC(dDate, oTz);
		}

		return dFormatDate;
	},

	options :
	{
		id :
		{
			value : "ID",
			label : "commons.pages.id"
		},
		network_address :
		{
			value : "NETWORK_ADDRESS",
			label : "summary.text.headerTable.Network_Address"
		},
		device_id :
		{
			value : "DEVICE_ID",
			label : "commons.pages.device_id"
		}
	},

	paramRequest :
	{
		parameterArrayBind : "|",

		iterator : function (arr, fn)
		{
			var i;
			var length;

			if (arr && arr.length && fn)
			{
				for (i = 0, length = arr.length; i < length; i = i + 1)
				{
					arr[i] = fn(arr[i]);
				}
			}

			return arr;
		},

		getParameter : function (sParameter)
		{
			var value = $.address.parameter(sParameter);

			if (value)
			{
				return decodeURI(value).replace(this.parameterArrayBind, "");
			}

			return null;
		},

		getEnumParameterArray : function(sParameter, sEnumName)
		{
			return $.pgsi.enums.fetchLabelsByValues(sEnumName, this.getParameterArray(sParameter));
		},

		getParameterArray : function (sParameter)
		{
			var value = $.address.parameter(sParameter);
			var arr;

			if (value)
			{
				value = decodeURI(value);
				arr = value.split(this.parameterArrayBind);

				if (!arr[arr.length - 1])
				{
					arr.pop();
				}

				return arr.length ? arr : null;
			}

			return null;
		},

		getDate : function (sDate, sTypeDate)
		{
			var sDateFormatMask = pgsi.settings.user.dateFormat;
			var dDate;

			if (sDate)
			{
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

		getIntParameterArray : function (sParameter)
		{
			return this.iterator(this.getParameterArray(sParameter), function (value) {

				return parseInt(value, 10);
			})
		},

		getIntParameter : function (sParameter)
		{
			return parseInt(this.getParameter(sParameter), 10);
		},

		description : function ()
		{
			return this.iterator(this.getParameterArray("description"), function(value) {

				return {id : parseInt(value, 10)};
			});
		}
	},

	noFiltersTags : {
		processId : {label 	: "commons.pages.process_id"}
	},

	filters : {

		action_type : {
			type : "options",
			title : "commons.pages.action_type",
			allLabel : "filter.all.event_type",
			urlParameter : "action_type",
			propertyValue : 0,
			propertyTitle : 1
		},

		employer : {
			type : "search",
			title : "pages.filter.label.employer",

			inputs : {
				organization : {
					type : "text",
					name : "organization",
					hint : "pages.filter.label.organizationName",
					maxsize : 100
				},
				location : {
					type : "text",
					name : "location",
					hint : "pages.filter.label.locationName",
					maxsize : 100
				}
			}
		},

		business : {
			type: "search",
			title: "pages.filter.label.businessInfo",

			inputs : {
				organization : {
					type : "text",
					name : "organization",
					hint : "pages.filter.label.organizationName",
					maxsize : 100
				},
				location : {
					type : "text",
					name : "location",
					hint : "pages.filter.label.locationName",
					maxsize : 100
				}
			}
		},

		member : {
			type : "search",
			title : "pages.filter.label.memberInfo",

			inputs : {
				memberId : {
					type : "text",
					name : "memberId",
					hint : "pages.filter.label.memberId",
					maxsize : 100
				},
				pinNumber : {
					type : "text",
					name : "pinNumber",
					hint : "pages.filter.label.pinNumber",
					maxsize : 100
				},
				phone : {
					type : "text",
					name : "phone",
					hint : "pages.filter.label.primaryPhone",
					maxsize : 100
				},
				last : {
					type : "text",
					name : "last",
					hint : "pages.filter.label.lastName",
					maxsize : 100,
					clazz : "short"
				},
				first : {
					type : "text",
					name : "first",
					hint : "pages.filter.label.firstName",
					maxsize : 100,
					clazz : "short"
				}
			}
		},

		recipient : {
			type : "search",
			title : "pages.filter.label.recipientInfo",

			inputs : {
				recipientId : {
					type : "text",
					name : "recipientId",
					hint : "pages.filter.label.recipientId",
					maxsize : 100
				},

				memberId : {
					type : "text",
					name : "memberId",
					hint : "pages.filter.label.memberId",
					maxsize : 100
				},

				phone : {
					type : "text",
					name : "phone",
					hint : "pages.filter.label.primaryPhone",
					maxsize : 100
				},

				last : {
					type : "text",
					name : "last",
					hint : "pages.filter.label.lastName",
					maxsize : 100,
					clazz : "short"
				},

				first : {
					type : "text",
					name : "first",
					hint : "pages.filter.label.firstName",
					maxsize : 100,
					clazz : "short"
				}
			}
		},

		transaction : {
			type : "search",
			title : "pages.filter.label.transactioInfo",

			inputs : {
				transactionId : {
					type : "text",
					name : "transactionId",
					hint : "pages.filter.label.transactionId",
					maxsize : 100
				},
				number : {
					name : "number",
					type : "text",
					hint : "pages.filter.label.confirmationNumber",
					maxsize : 100
				},
				payer : {
					type : "select",
					name : "payer",
					clazz: "standard validate[required]",
					options : []
				}
			}
		},
		coutry : {
			type : "search",
			title : "pages.payer.filter.country.label",

			inputs : {
				transactionId : {
					type : "text",
					name : "code",
					hint : "pages.payer.filter.country.code",
					maxsize : 100
				},
				number : {
					name : "name",
					type : "text",
					hint : "pages.payer.filter.country.name",
					maxsize : 100
				}
			}
		},
		city : {
			type : "search",
			title : "pages.payer.filter.city.label",

			inputs : {
				transactionId : {
					type : "text",
					name : "cityCode",
					hint : "pages.payer.filter.city.code",
					maxsize : 100
				}
			}
		},
		states : {
			type : "search",
			title : "pages.payer.filter.state.label",

			inputs : {
				transactionId : {
					type : "text",
					name : "stateCode",
					hint : "pages.payer.filter.state.code",
					maxsize : 100
				},
				number : {
					name : "stateName",
					type : "text",
					hint : "pages.payer.filter.state.name",
					maxsize : 100
				}
			}
		},



		date_filter : {
			type : "search",
			joinTags : true,
			inputs : {
				view_from : {
					name : "view_from",
					type : "text",
					label : "process.page.filter.view",
					span : true,
					clazz : "date-filter",
					style : "width:65%"
				},
				total_days : {
					name : "total_days",
					type : "text",
					label : "process.page.filter.back",
					span : true,
					clazz : "",
					value : "10",
					goButtonLabel : "process.page.filter.days",
					style : "width:2em"
				}
			},

			validate : function ($form, $filter)
			{
				var $view 				= $filter.find("input:eq(0)");
				var $back 				= $filter.find("input:eq(1)");
				var dateFormat 			= pgsi.settings.user.dateFormat;
				var sClassTypeFormat 	= 'datePt';

				if (dateFormat == 'mm/dd/yy')
				{
					sClassTypeFormat = 'dateEn';
				}

				$view.addClass("validate[required, custom["+sClassTypeFormat+"]");

				if ($view.validationEngine("validate"))
				{
					return $("#" + $view.attr("id")).validationEngine("validate");
				}
				else
				{
					$back.addClass("validate[required,custom[integer],min[0],max[365]");
					return $("#" + $back.attr("id")).validationEngine("validate");
				}

				return false;
			}
		},

		status : {
			type : "options",
			title : "pages.batches.filter.title",
			allLabel : "pages.batches.filter.all.types",
			urlParameter : "transaction_type",
			propertyValue : 0,
			propertyTitle : 1
		},

		transferstatus : {
			type : "options",
			title : "pages.batches.filter.title",
			allLabel : "pages.batches.filter.all.types",
			urlParameter : "transaction_type",
			propertyValue : 0,
			propertyTitle : 1
		},

		alerts : {
			type : "options",
			title : "commons.pages.alerts",
			allLabel : "filter.all.alerts",
			urlParameter : "alerts",
			propertyValue : 0,
			propertyTitle : 1
		},

		bulb_serial_number : {
			type : "search",
			title : "widgets.customize.filter.bulbserialnumber",

			inputs : {
				bulb_serial_number : {
					type : "text",
					name : "bulb_serial_number",
					label : "commons.pages.bulb_serial_number",
					maxsize : 100,
					goButtonLabel : "light.filter.go"
				}
			}
		},

		color_temperature : {
			type : "options",
			title : "widgets.customize.filter.colortemperature",
			allLabel : "filter.all.color_temperature",
			urlParameter : "color_temperature",
			propertyValue : 0,
			propertyTitle : 1
		},

		configuration : {
			type : "options",
			title : "widgets.customize.filter.configuration",
			allLabel : "filter.all.configuration",
			urlParameter : "configuration",
			propertyValue : 0,
			propertyTitle : 1
		},

		date_added : {
			type : "search",
			title : "widgets.customize.filter.dateadded",
			joinTags : false,
			inputs : {
				from : {
					name : "start",
					type : "text",
					label : "commons.pages.start",
					clazz : "standard date-filter"
				},
				to : {
					name : "end",
					type : "text",
					label : "commons.pages.end",
					clazz : "date-filter",
					goButtonLabel : "process.page.filter.go",
					style : "width:64%"
				}
			},

			validate : function ($form, $filter)
			{
				var $start				= $filter.find("#start");
				var $end				= $filter.find("#end");
				var dateFormat			= pgsi.settings.user.dateFormat;
				var sClassTypeFormat	= 'dateEn';
				var dStart;

				if (dateFormat == 'dd/mm/yy')
				{
					sClassTypeFormat = 'datePt';
				}

				$start.attr("class", this.inputs.from.clazz + " validate[required, custom[" + sClassTypeFormat + "]");

				if (!$start.validationEngine("validate"))
				{
					dStart = $.pgsi.date.parseDate($start.val(), dateFormat);

					$end.attr("class", this.inputs.to.clazz + " validate[required, custom["
							+ sClassTypeFormat + ", future[" + $.pgsi.date.format(dStart, "yy-mm-dd", {bUserTZ : false}) + "]");

					return $end.validationEngine("validate");
				}

				return true;
			}
		},

		ecomode : {
			type : "options",
			title : "widgets.customize.filter.ecomode",
			allLabel : "filter.all.ecomode",
			urlParameter : "ecomode",
			propertyValue : 0,
			propertyTitle : 1
		},

		event_schedule : {
			type : "options",
			title : "widgets.customize.filter.eventschedule",
			allLabel : "filter.all.event_schedule",
			urlParameter : "event_schedule",
			propertyValue : "id",
			propertyTitle : "name"
		},

		firmware_version : {
			type : "search",
			title : "widgets.customize.filter.firmwareversion",

			inputs : {
				firmware_version : {
					type : "text",
					name : "firmware_version",
					label : "widgets.customize.filter.firmwareversion",
					maxsize : 100,
					goButtonLabel : "light.filter.go"
				}
			}
		},

		groups : {
			type : "options",
			title : "widgets.customize.filter.groups",
			allLabel : "filter.all.groups",
			urlParameter : "groups",
			propertyTitle : "name",
			propertyValue : "id",
			notInFilter : {
				value : "not",
				label : "widgets.customize.filter.notIn.group_id"
			}
		},

		housing_color : {
			type : "options",
			title : "widgets.customize.filter.housingcolor",
			allLabel : "filter.all.housing_color",
			urlParameter : "housing_color",
			propertyValue : 0,
			propertyTitle : 1
		},


		address : {
			type : "search",
			title : "widgets.customize.filter.address",

			inputs : {
				street : {
					type : "text",
					name : "street",
					label : "commons.pages.street",
					maxsize : 100
				},
				city : {
					type : "text",
					name : "city",
					label : "commons.pages.city",
					maxsize : 100
				},
				zip : {
					type : "text",
					name : "zip",
					label : "commons.pages.zip",
					maxsize : 100,
					clazz : "short",
					goButtonLabel : "light.filter.go"
				}
			}
		},
		light_types : {
			type : "search",
			title : "light.filter.lamptype",

			inputs : {
				dimmable : {
					type : "select",
					name : "dimmable",
					label: "light.filter.dimmable",
					clazz: "standard validate[required]",
					options : []
				},
				housing : {
					type : "select",
					name : "housing",
					label: "light.filter.housing",
					clazz: "standard validate[required]",
					options : []
				},
				wattage : {
					type : "select",
					name : "wattage",
					label: "light.filter.wattage",
					clazz: "standard validate[required]",
					options : []
				},
				lamptype : {
					type : "select",
					name : "lamptype",
					label: "light.filter.type",
					clazz: "standard validate[required]",
					options : [],
					goButtonLabel : "light.filter.search",
				}
			}
		},

		lifecycle_state : {
			type : "options",
			title : "commons.pages.lifecycle_state",
			allLabel : "filter.all.lifecycle_state",
			urlParameter : "lifecycle_state",
			propertyValue : 0,
			propertyTitle : 1
		},

		light_driver_serial_number : {
			type : "search",
			title : "widgets.customize.filter.lightdriverserialnumber",

			inputs : {
				light_driver_serial_number : {
					type : "text",
					name : "light_driver_serial_number",
					label : "widgets.customize.filter.lightdriverserialnumber",
					maxsize : 100,
					goButtonLabel : "light.filter.go"
				}
			}
		},

		lower_assembly_serial_number : {
			type : "search",
			title : "widgets.customize.filter.lowerassemblyserialnumber",

			inputs : {
				lower_assembly_serial_number : {
					type : "text",
					name : "lower_assembly_serial_number",
					label : "widgets.customize.filter.lowerassemblyserialnumber",
					maxsize : 100,
					goButtonLabel : "light.filter.go"
				}
			}
		},

		meter_firmware : {
			type : "search",
			title : "commons.pages.meterFirmware",

			inputs : {
				meter_firmware : {
					type : "text",
					name : "meter_firmware",
					label : "commons.pages.meterFirmware",
					maxsize : 100
				}
			}
		},

		model_number : {
			type : "search",
			title : "commons.pages.model_number",

			inputs : {
				model_number : {
					type : "text",
					name : "model_number",
					label : "commons.pages.model_number",
					maxsize : 100,
					goButtonLabel : "light.filter.go"
				}
			}
		},

		offset_schedule : {
			type : "options",
			title : "widgets.customize.filter.offsetschedule",
			allLabel : "filter.all.offset_schedule",
			urlParameter : "offset_schedule",
			propertyTitle : "name",
			propertyValue : "id"
		},

		search_light : {
			type : "search",
			goButtonLabel : "light.filter.search",
			inputs : {
				query : {
					type : "text",
					name : "query",
					hintMessageKey : "light.filter.search"
				},
				query_type : {
					type : "select",
					name : "query",
					options : [
						{
							value : $.pgsi.enums.fetchValueByLabel("com.pgsi.lc.property.model.PropertyEnum","FLEXNET_ID" ),
							label : "process.page.filter.lightpropertysearch.FlexNetID"
						},
						{
							value : $.pgsi.enums.fetchValueByLabel("com.pgsi.lc.property.model.PropertyEnum","POLE_ID" ),
							label : "process.page.filter.lightpropertysearch.poleID"
						}
					]
				}
			},

			validate : function ($form, $filter){
				var $select = $filter.find("select");
				var $input = $filter.find("input");
				var type = $select.val();

				switch (type){
					case "DEVICE_ID":

						$input.attr("class", "validate[required,maxSize[25],custom[noSpecialCaractersOfSearch]]");
						break;

					case "NETWORK_ADDRESS" :

						$input.attr("class", "validate[required, custom[integer]]");
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

		search_detail_history :
		{
			type : "search",
			goButtonLabel : "light.filter.search",
			inputs :
			{
				query :
				{
					type : "text",
					clazz: "validate[required, custom[specialCharsCustomSearch]]",
					name : "query",
					hintMessageKey : "process.page.filter.lightpropertysearch.actionID"
				},
				query_type :
				{
					type : "select",
					name : "query",
					options :
					[{
						value : "ACTION_ID",
						label : "process.page.filter.lightpropertysearch.actionID"
					}]
				}
			},

			validate : function ($form, $filter)
			{
				var $select = $filter.find("select");
				var $input = $filter.find("input");
				var type = $select.val();

				switch (type)
				{
					case "ACTION_NAME":

						$input.attr("class", "validate[required,maxSize[25],custom[noSpecialCaractersOfSearch]]");
						break;

					case "ACTION_ID" :

						$input.attr("class", "validate[required, custom[integer],min[0],maxSize[25]]");
						break;

					default:
						break;
				}

				return $("#" + $input.attr("id")).validationEngine("validate");
			}
		},

		search_process : {
			type : "search",
			goButtonLabel : "light.filter.search",
			inputs : {
				query : {
					type : "text",
					clazz: "validate[required, custom[specialCharsCustomSearch]]",
					name : "query",
					hintMessageKey : "light.filter.search"
				},
				query_type : {
					type : "select",
					name : "query",
					options : [
						{
							value : "RNI_ID",
							label : "process.page.filter.lightpropertysearch.FlexNetID"
						},
						{
							value : "POLE_ID",
							label : "process.page.filter.lightpropertysearch.poleID"
						},
						{
							value : "EVENT_ID",
							label : "process.page.filter.lightpropertysearch.eventID"
						}
					]
				}
			},

			validate : function ($form, $filter){
				var $select = $filter.find("select");
				var $input = $filter.find("input");
				var type = $select.val();

				switch (type)
				{
					case "RNI_ID":

						$input.attr("class", "validate[required,maxSize[25],custom[noSpecialCaracters]]");
						break;

					case "POLE_ID" :

						$input.attr("class", "validate[required,custom[integer]]");
						break;

					case "EVENT_ID" :

						$input.attr("class", "validate[required,custom[integer],min[0],maxSize[25]]");
						break;

					default:
						break;
				}

				return $("#" + $input.attr("id")).validationEngine("validate");
			}
		},

		tags : {
			type : "options",
			title : "widgets.customize.filter.tags",
			allLabel : "filter.all.tags",
			urlParameter : "tags",
			propertyTitle : "name",
			propertyValue : "id"
		},

		upper_assembly_serial_number : {
			type : "search",
			title : "widgets.customize.filter.upperassemblyserialnumber",

			inputs : {
				upper_assembly_serial_number : {
					type : "text",
					name : "upper_assembly_serial_number",
					label : "widgets.customize.filter.upperassemblyserialnumber",
					maxsize : 100,
					goButtonLabel : "light.filter.go"
				}
			}
		},

		users : {
			type : "options",
			title : "process.page.filter.users",
			allLabel : "filter.all.users",
			urlParameter : "users",
			propertyTitle : "userName",
			propertyValue : "id"
		},

		voltage_range : {
			type : "options",
			title : "widgets.customize.filter.voltagerange",
			allLabel : "filter.all.voltage_range",
			urlParameter : "voltage_range",
			propertyValue : 0,
			propertyTitle : 1
		},

		warning_type : {
			type : "options",
			title : "commons.pages.warning_type",
			allLabel : "filter.all.warning_type",
			urlParameter : "warning_type",
			propertyValue : 0,
			propertyTitle : 1
		}
	}
}