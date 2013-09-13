sensus.util.filter = {

		api : "api/filter/fetch",

		createRequest : function (oFilters) {

			var _paramRequest;
			var filters = [];
			var i;

			for (i in oFilters) {

				if (oFilters[i]) {

					filters.push(i);
				}
			}

			if ($.address.parameter("device_type")) {

				_paramRequest = sensus.util.filter.paramRequest;

				return {
					filter : filters,
					deviceType : _paramRequest.getParameterArray("device_type"),
					deviceSubTypes : _paramRequest.getParameterArray("device_subtype")
				};
			}

			return {filter : filters};
		},

		createResponse : function (oResponse, oFilters) {

			var i;

			for (i in oFilters) {

				if (oResponse[i]) {

					oFilters[i].dataList = oResponse[i];
				}
			}

			return oFilters;
		},

		init : function (preLoad, oFilters, fnCallback) {

			if (preLoad == "refresh" || $.sc.isNullOrUndefined(preLoad)) {

				$.ajaxValidator.fnDoCall(this.api, this.createRequest(oFilters), false, function(oResponse) {

					oResponse = sensus.util.filter.createResponse(oResponse, oFilters);

					if (fnCallback) {

						fnCallback(oResponse);
					}
				});

			} else if (!$.sc.isNullOrUndefined(preLoad)) {

				if (fnCallback) {

					fnCallback(sensus.util.filter.createResponse(preLoad, oFilters));
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

		getDeviceFiltersByType : function (sDeviceType) {

			var electric = sensus.constants.services.electric;

			if (!sDeviceType) {

				sDeviceType = $.address.parameter("device_type").replace("|", "");
			}

			switch (sDeviceType) {

			case electric.meter.name:
				return ["group","lifecycle_state","description","install_date",
				        "address","tag","meter_firmware", "remote_disconnect"];

			case electric.lcm.name:
				return ["group","lifecycle_state","device_subtype","description",
				        "install_date","address","tag","alarm"];

			case electric.han.name:
				return ["group","lifecycle_state","description",
				        "install_date","address","tag"];
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

				var sDateFormatMask = sensus.settings.dateFormatMask.replace("yyyy", "yy");
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

						$input.attr("class", "validate[required,maxSize[25],custom[noSpecialCaracters]]");
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

					return $form.validationEngine("validateField", "#" + $input.attr("id"));
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

			description : {
				type : "options",
				title : "commons.pages.description",
				allLabel : "filter.all.description",
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

			alarm : {
				type : "options",
				title : "commons.pages.alarms",
				allLabel : "filter.all.alarms",
				urlParameter : "alarm",
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

			device_subtype : {
				type : "options",
				title : "commons.pages.device_subtype",
				allLabel : "filter.all.device_subtype",
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
					city : {
						type : "text",
						name : "city",
						label : "commons.pages.city",
						maxsize : 100
					},
					street : {
						type : "text",
						name : "street",
						label : "commons.pages.street",
						maxsize : 100
					},
					zip : {
						type : "text",
						name : "zip",
						label : "commons.pages.zip",
						maxsize : 100,
						clazz : "short",
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
					var dateFormat			= sensus.settings.dateFormatMask.replace('yyyy','yy');
					var sClassTypeFormat	= 'dateEn';
					var dStart;

					if (dateFormat == 'dd/mm/yy') {

						sClassTypeFormat = 'datePt';
					}

					$start.attr("class", this.inputs.start.clazz + " validate[required, custom[" + sClassTypeFormat + "]");

					if (!$form.validationEngine('validateField', $start)) {

						dStart = $.date.parseDate($start.val(), dateFormat);

						$end.attr("class", this.inputs.end.clazz + " validate[required, custom["
								+ sClassTypeFormat + ", future[" + $.date.dateFormat(dStart, "yy-mm-dd", {bUserTZ : false}) + "]");

						return $form.validationEngine("validateField", $end);
					}

					return true;
				}
			},

			meter_firmware : {
				type : "search",
				title : "commons.pages.meter_firmware",
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

					if (!$form.validationEngine('validateField', $input)) {

						if ($input.val().search(/^[0-9\.]+$/) != 0 || $input.val().contains("..")) {

							$input.validationEngine('showPrompt', sensus.locale.get('commons.pages.onlyNumbersAndDecimal'), 'red', 'topLeft', true);
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

					return $form.validationEngine("validateField", "#query");
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
					var dateFormat 			= sensus.settings.dateFormatMask.replace('yyyy','yy');
					var sClassTypeFormat 	= 'datePt';

					if (dateFormat == 'mm/dd/yy') {
						sClassTypeFormat = 'dateEn';
					}

					$view.addClass("validate[required, custom["+sClassTypeFormat+"]");

					if ($form.validationEngine('validateField', $view)) {

						return $form.validationEngine("validateField", "#" + $view.attr("id"));

					} else {

						$back.addClass("validate[required,custom[integer],min[0],max[365]");

						return $form.validationEngine("validateField", "#" + $back.attr("id"));

					}

					return false;
				}
			}
		}
}