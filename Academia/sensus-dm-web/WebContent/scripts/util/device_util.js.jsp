<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.util.device = {

		setRequest : function (value, oRequest, sAttribute) {

			if (value) {

				oRequest[sAttribute] = value;
			}
		},

		setSearchRequest : function (sQuery, sQueryType, sDeviceTypeEnum, oDeviceSearch, oDevice) {

			var electric;

			if (sQuery) {

				electric = sensus.constants.services.electric;

				if (sQueryType == "DEVICE_ID") {

					oDevice.deviceId = sQuery;

				} else if (sQueryType == "PREMISE_ID") {

					if (sDeviceTypeEnum != electric.lcm.han && sDeviceTypeEnum != electric.han.name) {

						oDevice.configuration.premiseId = sQuery;

					} else {

						oDeviceSearch.electricMeterSearch = {electricMeter : {configuration : {premiseId : sQuery}}};
					}

				} else if (sQueryType == "NETWORK_ADDRESS") {

					if (sDeviceTypeEnum != electric.lcm.han || sDeviceTypeEnum != electric.han.name) {

						if ($.isNumeric(sQuery)) {

							oDevice.radio.flexNetId = parseInt(sQuery, 10);

						} else {

							oDevice.radio.flexNetId = -1;
						}

					} else {

						oDevice.macAddress = sQuery;
					}
				}
			}
		},

		getSearchRequest : function () {

			var _paramRequest = sensus.util.filter.paramRequest;
			var _fnSetRequest = this.setRequest;
			var oSearch = {};

			_fnSetRequest(_paramRequest.getIntParameterArray("tag"), oSearch, "tags");
			_fnSetRequest(_paramRequest.getIntParameterArray("group"), oSearch, "groups");
			_fnSetRequest(_paramRequest.description(), oSearch, "deviceModels");
			_fnSetRequest(_paramRequest.getIntParameter("processId"), oSearch, "processId");
			_fnSetRequest(_paramRequest.getDate(_paramRequest.getParameter("start"), "firstHour"), oSearch, "startDate");
			_fnSetRequest(_paramRequest.getDate(_paramRequest.getParameter("end"), "lastHour"), oSearch, "endDate");
			_fnSetRequest(_paramRequest.getParameterArray("device_type"), oSearch, "deviceTypes");

			return oSearch;
		},

		getLocation : function () {

			var _paramRequest = sensus.util.filter.paramRequest;
			var _fnSetRequest = this.setRequest;
			var oLocation = {};

			_fnSetRequest(_paramRequest.getParameter("street"), oLocation, "address");
			_fnSetRequest(_paramRequest.getParameter("city"), oLocation, "city");
			_fnSetRequest(_paramRequest.getParameter("zip"), oLocation, "zip");

			return oLocation;
		},

		allColumns : {

			deviceId 			: {sId : "DEVICE_ID", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.device_id"), propertyRequest : "deviceId", sOrderColumn : "device_id"},
			flexNetId 			: {sId : "NETWORK_ADDRESS", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.networkAddress"), propertyRequest : "radio.flexNetId", sOrderColumn : "flexnet_id"},
			description 		: {sId : "description", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.device_description"), propertyRequest : "deviceModel.description", sOrderColumn : "device_type_desc"},
			installDate 		: {sId : "install_date", bVisible : true, bFixed : true, bEffectSortable : true, sName : sensus.locale.get("filter.device.installdate.label"), propertyRequest : "configuration.installDate", sOrderColumn : "install_date"},
			latitude			: {sId : "latitude", bVisible : false, bFixed : true, propertyRequest : "radio.location.latitude", bSortable : false},
			longitude			: {sId : "longitude", bVisible : false, bFixed : true, propertyRequest : "radio.location.longitude", bSortable : false},
			id					: {sId : "Id", bVisible : false, bFixed : true, propertyRequest : "radio.flexNetIdAsString", bSortable : false},
			deviceType			: {sId : "DeviceType", bVisible : false, bFixed : true, propertyRequest : "deviceType", bSortable : false},
			customerId			: {sId : "CustomerId", bVisible : false, bFixed : true, propertyRequest : "radio.customerId", bSortable : false},
			baseRepId			: {sId : "BaseRepId", bVisible : false, bFixed : true, propertyRequest : "", bSortable : false},
			clientEndPoint		: {sId : "ClientEndPointId", bVisible : false, bFixed : true, propertyRequest : "deviceId",	bSortable : false},
			deviceSubType		: {sId : "DeviceSubType", bVisible : false, bFixed : true, propertyRequest : "deviceType", bSortable : false},
			deviceSubTypeId		: {sId : "DeviceSubTypeId", bVisible : false, bFixed : true, propertyRequest : "deviceTypeValue", bSortable : false},
			typeEnum			: {sId : "TypeEnum", bVisible : false, bFixed : true, bSortable : false},
			typeEnumValue		: {sId : "TypeEnumValue", bVisible : false, bFixed : true, bSortable : false},
			quarantine			: {sId : "quarantine", bVisible : false, bFixed : true, propertyRequest : "quarantine", bSortable : false},
			timeZoneInMinutes 	: {sId : "TimeZoneInMinutes", bVisible : false, bFixed : true, propertyRequest : "radio.location.timeZoneInfo.displayMinutes", bSortable : false},
			lifecycleState		: {sId : "LIFECYCLE_STATE", bVisible : true, bFixed : true, bEffectSortable : true, sName : sensus.locale.get("smartpointdetail.tabs.about.state"), propertyRequest : "lifecycleStateEnum", sOrderColumn : "lifecycle_state"},
			electricSubType		: {sId : "device_subtype", bVisible : false, bFixed : true, sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.type"), sOrderColumn : "device_type_id"},
			alarm				: {sId : "ALARM", bVisible : false, bFixed : true, bEffectSortable : true, sName : sensus.locale.get("commons.pages.alert"), propertyRequest : "firstAlarm.alarmEnum", sOrderColumn : "alarm|alarm_time|device_id"},
			alarmTime			: {sId : "ALARM_TIME", bVisible : false, bFixed : true, propertyRequest : "firstAlarm.alarmTime", bSortable : false},
			showMap				: {sId : "ShowMap", bVisible : false, bFixed : true, propertyRequest : "lifecycleStateEnum", bSortable : false},
			lastHead			: {sId : "LAST_HEARD", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.last_heard"), propertyRequest : "lastHeard", sOrderColumn : "last_heard"},
			status				: {sId : "STATUS", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.status"), propertyRequest : "status", sOrderColumn : "status"},
			parentId			: {sId : "parent_id", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.parent_id"), propertyRequest : "electricMeterFlexNetId", sOrderColumn : "PARENT_ID"},
			cityName	    	: {sId : "city_name", sWidth : "5%", bEffectSortable : true, sName : sensus.locale.get("commons.pages.city"), propertyRequest : "radio.location.city", sOrderColumn : "CITY_NAME"}
		},

		loadTable : function (oTableConfig, bPreLoad, oResponse, oColumnFilter, session) {

			sensus.pages.device.smartpointTable = $("#smartpoint-table").dataTable(sensus.widgets.datatable.setTable({

				id 					: "smartpoint-table",
				sAjaxSource 		: oTableConfig.url,
				customColumns 		: "smartpointlist",
				oPreLoadResponse 	: bPreLoad === false ? "refresh" : oResponse,
				bPreLoad			: bPreLoad,
				aColumns 			: oTableConfig.columns,
				oColumnFilter		: oColumnFilter,
				session				: session,
				oSettings : {
					cutomizeHack	: oTableConfig.cutomizeHack,
					oRequest	 	: InquiryDeviceRequest,
					sortEnum	 	: "DeviceColumnEnum",
					bDestroy		: true,
					fnRequest 		: sensus.pages.device.requestAction,
					sResponseObj 	: "devices",
					iDefaultCol 	: 6,
					sDefaulSortEnum : oTableConfig.sortExpression,
					sDefaultSort 	: "asc",
					bCheckbox 		: true,
					bSelection 		: true,
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
					edit : {
						col : "DEVICE_ID",
						url : "device/detail",
						bSaveSession : true,
						params : function (aData, objColumn) {
							return "&deviceType=" + aData[objColumn.DeviceSubType]
								+ "&typeEnum=" + escape(aData[objColumn.TypeEnum]);
						}
					},
					</sec:authorize>
					iMapCol : oTableConfig.mapColNum,
					aSelectedParameters : ["DeviceType", "CustomerId", "BaseRepId", "ClientEndPointId",
										   "DeviceSubType", "DeviceSubTypeId", "TypeEnum", "TypeEnumValue",
										   "ALARM_TIME", "LIFECYCLE_STATE"]
				},

				$exportCSVElement : $("#csv"),

				fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

					var _get = sensus.locale.get;
					var sDateFormat = sensus.settings.dateFormatMask.replace("yyyy", "yy");
					var sTimeZoneInMinutes = sensus.settings.timezoneMinutes;
					var $map;

					if (oColumn.TimeZoneInMinutes && aData[oColumn.TimeZoneInMinutes]) {

						sTimeZoneInMinutes = aData[oColumn.TimeZoneInMinutes];
					}

					if (iDisplayIndex == 0) {

						sensus.widgets.datatable.objLatLon.length = 0;
					}

					sensus.widgets.datatable.objLatLon.push({
						latitude	: aData[oColumn.latitude],
						longitude 	: aData[oColumn.longitude],
						flexnet_id	: aData[oColumn.NETWORK_ADDRESS]
					});

					if (oColumn.LIFECYCLE_STATE) {

						$("td", nRow).eq(oColumn.LIFECYCLE_STATE).addClass("status");

						if (aData[oColumn.LIFECYCLE_STATE]) {

							$("td", nRow).eq(oColumn.LIFECYCLE_STATE).text(_get("commons.pages." + aData[oColumn.LIFECYCLE_STATE]));
						}
						if (aData[oColumn.LIFECYCLE_STATE] == null) {

							$("td", nRow).eq(oColumn.LIFECYCLE_STATE).text(_get("commons.pages.lifeCycle_state.null"));
						}
					}

					if ((oColumn.ShowMap && aData[oColumn.ShowMap] != "JOINED") && (oColumn.DeviceSubTypeId && aData[oColumn.ShowMap] != "INSTALLED")) {

						$map = $("td", nRow).eq(oColumn.Map);

						$map.html("<span class='ui-state-disabled'>" + $map.children().text() + "</span>").unbind("click");
					}

					if (oColumn.ALARM && aData[oColumn.ALARM]) {

						$("td", nRow).eq(oColumn.ALARM)
								.addClass("active").html(
									_get("filter.alarm." + aData[oColumn.ALARM].toLowerCase()) + "<br /><small>" +
									(aData[oColumn.ALARM_TIME] ?
										$.date.dateFormat(aData[oColumn.ALARM_TIME], sDateFormat + ' - h:i:s A', {iTZMinutes : sTimeZoneInMinutes})
										: "--") + "</small>")
								.parents("tr").addClass("alerts");
					}

					if (oColumn.QUARANTINE) {

						if (aData[oColumn.QUARANTINE]) {

							$("td", nRow).eq(oColumn.QUARANTINE).text(sensus.locale.get("filter.quarantine.name"));

						} else {

							$("td", nRow).eq(oColumn.QUARANTINE).text("");
						}
					}

					if (oColumn.INSTALL_DATE && aData[oColumn.INSTALL_DATE]) {

						$("td", nRow).eq(oColumn.INSTALL_DATE).text($.date.dateFormat(aData[oColumn.INSTALL_DATE],
								sDateFormat, {iTZMinutes : sTimeZoneInMinutes}));
					}

					if (oColumn.STATUS && aData[oColumn.STATUS]) {

						$("td", nRow).eq(oColumn.STATUS).text(_get("filter.status_meter." + aData[oColumn.STATUS].toLowerCase()));
					}

					if (oColumn.DEVICE_SUBTYPE && aData[oColumn.DEVICE_SUBTYPE]) {

						$("td", nRow).eq(oColumn.DEVICE_SUBTYPE).text(_get("commons.pages." + aData[oColumn.DEVICE_SUBTYPE]));
					}

					if (oColumn.REMOTE_DISCONNECT && aData[oColumn.REMOTE_DISCONNECT]) {

						$("td", nRow).eq(oColumn.REMOTE_DISCONNECT).text(
								sensus.util.page.fnGetRemoteDisconnectSate(aData[oColumn.REMOTE_DISCONNECT]));
					}

					if (oColumn.LAST_HEARD && aData[oColumn.LAST_HEARD]) {

						$("td", nRow).eq(oColumn.LAST_HEARD).text(
								$.date.dateFormat(aData[oColumn.LAST_HEARD], sDateFormat + ' h:i:sA',
										{iTZMinutes : sTimeZoneInMinutes}));
					}

					if (oColumn.ENCRYPTION_SUPPORT) {

						$("td", nRow).eq(oColumn.ENCRYPTION_SUPPORT).text(
								_get(aData[oColumn.ENCRYPTION_SUPPORT] == false ?
										"smartpointdetail.page.disabled" : "smartpointdetail.page.enabled"));
					}
				},

				fnInfoCallback : function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {

					if (iTotal == 0) {

						sensus.widgets.datatable.objLatLon.length = 0;
					}
				},

				fnDrawCallback : function(setting, oColumn) {

					$(".status-viewport-loading").addClass("hide");
				}
			}));
		},

		reloadTabContent : function (aKeepFilters) {

			var $filter = $("#w-filters");
			var $labelMap = $("label.map");

			// When change the tab with mode map active, activate the list mode and hide the map
			if ($labelMap.hasClass("ui-state-active")) {

				$(".status-viewport").show();
				$("#map-list").addClass("hide-map");
				$("label.list").addClass("ui-state-active");
				$labelMap.removeClass("ui-state-active");
			}

			$filter.filters("cleanAllFilters", aKeepFilters);

			$.fn.pageLoader.parameter("iDisplayStart", null);

			sensus.pages.device.smartpointTable.fnDestroy();
			$("#smartpoint-table thead tr, #smartpoint-table tbody").empty();
			sensus.util.device.loadTable(sensus.pages.device.getTableConfig(), false);

			$filter.filters("destroy");
			sensus.util.device.loadFilter(sensus.pages.device.getFilters());

			$("#actions-options").find("ul").empty();

			sensus.util.device.fnLoadActionsList(sensus.pages.device.additionalActions);
		},

		/**
		 * Save Search
		 *
		 * @param urlAdress
		 *		[String], the URL
		 */
		saveSearch : function (urlAddress) {

			var sSearchName			= $("#saved-search-name").val();
			var sSearchDescription	= $("#saved-search-description").val();
			var oFilters			= $.wCustomize.getData("smartpointlist", "Filters").filters;
			var aColumns			= $.wCustomize.getData("smartpointlist", "Columns").listColumn;
			var sSuccessMessage 	= sensus.locale.get("searchSave.page.success", sSearchName);
			var i;
			var length;
			var oColumn;

			// Parameters of Custom Search Object
			var oCustomSearchParam = sensus.pages.device.requestAction().deviceSearch;

			// Remove the propertyRequest of the column object
			if (aColumns) {

				for (i = 0, length = aColumns.length; i < length; i = i + 1) {

					oColumn = aColumns[i];
					delete oColumn.propertyRequest;
					delete oColumn.sOrderColumn;
				}
			}

			oCustomSearchParam.name 		= sSearchName;
			oCustomSearchParam.description 	= sSearchDescription;
			oCustomSearchParam.listColumn 	= aColumns;
			oCustomSearchParam.listFilters 	= oFilters;
			oCustomSearchParam.deviceType 	= $.address.parameter("device_type").replace("|","");

			$.ajaxValidator.fnDoCall(urlAddress, new CustomSearchRequest({customSearch : new CustomSearch(oCustomSearchParam)}),
					false, null, sSuccessMessage);

			$('#action-dialog').dialog("close");
		},

		/**
		 * Get Generate Request CSV for Device
		 *
		 * @return the request
		 */
		 getGenerateRequestCSVDevice : function () {

			// Get Request populate with current filters
			var request = new InquiryDeviceRequest(sensus.pages.device.requestAction());

			// Default Columns
			var aDefaultColumns	= [];
			var oTableColumns	= $.wCustomize.getData("smartpointlist", "Columns");
			var sListColumn		= "columnEnum";
			var i ;
			var length;
			var iMapColumn;
			var oTableColumn;

			if (oTableColumns.listColumn) {

				if (!oTableColumns.listColumn[0][sListColumn]) {

					sListColumn = 'sId';
				}

				for (i = 0, length = oTableColumns.listColumn.length; i < length; i = i + 1) {

					aDefaultColumns.push(oTableColumns.listColumn[i][sListColumn]);
				}

				iMapColumn = $.inArray("MAP_IT", aDefaultColumns);

				if (iMapColumn != -1) {

					aDefaultColumns.splice(iMapColumn, 1);
				}

			} else {

				aTableColumns = sensus.pages.device.getTableConfig().columns;

				for (i = 0, length = aTableColumns.length; i < length; i = i + 1) {

					oTableColumn = aTableColumns[i];

					if (oTableColumn.bVisible !== false) {

						aDefaultColumns.push(oTableColumn.sId.toUpperCase());
					}
				}
			}

			request.listColumn = aDefaultColumns;

			// Default Request Parameters
			request.sortExpressions	= sensus.util.page.getSortExpression(sensus.pages.device.smartpointTable);

			return request;
		},

		loadFilter : function (oFilter, oColumnFilter, session) {

			var aDefaultFilterSaved = $.wCustomize.getData("smartpointlist", "Filters", oColumnFilter, session);
			var filterUtil = sensus.util.filter;
			var filter;
			var opts;

			// Search for saved filters
			if (aDefaultFilterSaved && aDefaultFilterSaved.filters && aDefaultFilterSaved.filters.length) {

				oFilter = filterUtil.filterArrayToObject(aDefaultFilterSaved.filters);
			}

			if ($.address.parameter("saved")) {

				filter = {search : filterUtil.filters.search};
				opts = filterUtil.options;

				filter.search.inputs.query_type.options = [opts.device_id, opts.network_address, opts.premise_id];
				oFilter = $.extend({}, filter, oFilter);

				// To show message in the right place
				if ($.address.parameter("name")) {

					sensus.util.page.showMessage("messaging-main",
							sensus.locale.get("search.page.fetchSaved",
									decodeURI($.address.parameter("name"))), "confirm");
				}
			}

			filterUtil.init(null, oFilter, function(oResponse) {

				$("#w-filters").filters({
					hasCustomize : true,
					title : sensus.locale.get("filter.device.label"),
					table : sensus.pages.device.smartpointTable,
					filters : oResponse});

				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
					$("#group").append("<a href='/group/create' class='alist filter-create launch'>"
							+ sensus.locale.get("smartpoint.page.createNewGroup") +"</a>");
				</sec:authorize>
			});
		},

		/**
		 * Get Default Actions
		 * Checks what the device type for create the actions menu
		 *
		 * @param aDeviceTypeParameters
		 *			{Array}, the device type parameters
		 * @param sDeviceType
		 *			{String}, the device type
		 * @return {Array} of default actions
		 */
		fnGetDefaultActions : function (aDeviceTypeParameters, sDeviceType) {

			var i 	= 0;
			var max = aDeviceTypeParameters.length;

			for (; i < max; i = i + 1) {

				if (sDeviceType === aDeviceTypeParameters[i].deviceType) {

					return aDeviceTypeParameters[i].defaultActions.actions;
				}
			}
		},

		additionalActions : {

			// Manage group and tag is not show when is customer or billing
			<sec:authorize access="!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
				group : {
					id : "headerGroup",
					description : sensus.locale.get("commons.pages.manage.groups"),
					children : [{id : "addToGroup", name: "addToGroup", description : sensus.locale.get("commons.pages.addtogroup")},
								{id : "removeFromGroup", name : "removeFromGroup", description : sensus.locale.get("smartpoint.actions.removefromgroup")}]
				},

				tag : {
					id : "headerTag",
					description : sensus.locale.get("systemsettings.page.manageTags"),
					children : [{id : "addTag", name : "addTag", description : sensus.locale.get("commons.pages.addTag")},
								{id : "removeTag", name : "removeTag", description : sensus.locale.get("sensus.epm.action.remove.tag")}]
				}
			</sec:authorize>
		},

		/**
		 * Create drop down menu of actions recursively
		 *
		 * @param oExtraActions
		 *			{Object} the extra actions to create the tree of UL an LI HTML elements
		 * @return Array of extra actions
		*/
		fnCreateOptions : function (oExtraActions) {

			var arr = [];
			var oExtraAction;

			for (var i in oExtraActions) {

				if (oExtraActions.hasOwnProperty(i)) {

					oExtraAction = oExtraActions[i];

					arr.push("<li>");

					arr.push("<a href='#' name='"
						+ (oExtraAction.name ? oExtraAction.name : oExtraAction.id)
						+ "' class='action-option' id='"
						+ oExtraAction.id + "'>"
						+ oExtraAction.description + "</a>");

					// Whether is a category, call again to create children
					if (oExtraAction.children && oExtraAction.children.length) {

						arr.push("<ul>");
						arr = arr.concat(sensus.util.device.fnCreateOptions(oExtraAction.children));

						arr.push("</ul>");
					}

					arr.push("</li>");
				}
			}

			return arr;
		},

		fnLoadActionsList : function (oAdditionalActions) {

			var device 					= sensus.util.device;
			var actionrequestutil		= sensus.util.actionrequestutil;
			var sDeviceType 			= $.address.parameter("device_type").replace("|", "");
			var aDeviceTypeParameters 	= sensus.settings.oDeviceTypeParameters.deviceTypePermissions;
			var oDefaultActions 		= device.fnGetDefaultActions(aDeviceTypeParameters, sDeviceType);
			var oActions				= [];
			var $additional;

			oActions = actionrequestutil.fnLoadDefaultActions(oDefaultActions);

			// Extend additional actions default of group and tag with especified additional actions
			if (oAdditionalActions) {

				oAdditionalActions = $.extend({}, device.additionalActions, oAdditionalActions);

			} else {

				oAdditionalActions = device.additionalActions;

			}

			$additional = device.fnCreateOptions(oAdditionalActions);

			// Initialize action buttons
			sensus.util.page.menuPlug(
					sensus.commons.modules,
					actionrequestutil.menuPlugCallBack,
					oActions,
					$additional.join(''),
					false,
					sensus.pages.device.requestAction);
		},

		changeViewEvent : function (e) {

			var $this = $(this);
			var $viewport = $(".status-viewport");

			if ($this.hasClass("list") && !$viewport.is(":visible")) {

				// List View
				$("#map-list").addClass("hide-map");
				$viewport.show();
				sensus.widgets.datatable.reloadTable(sensus.pages.device.smartpointTable, 0, false);

			} else if ($this.hasClass("map") && $("#map-list").hasClass("hide-map")) {

				sensus.util.page.startProgressBar();

				$viewport.hide();
				$("#map-list").removeClass("hide-map");

				sensus.util.mapopen.iZoom = 0;

				// Map Vew
				if ($.sc.map.mapExists()) {

					// Whether map already exists
					$('#map').empty();
				}

				// Create map
				sensus.util.device.createMap(sensus.pages.device.mapUrl);

				sensus.util.page.stopProgressBar();
			}
		},

		startView : function ($view, fnChangeViewEvent) {

			$view.buttonset();

			$view.find("input:first").button("option", "icons", {primary : "ui-icon-list"});

			$view.find("input:eq(1)").button("option", "icons", {primary : "ui-icon-map"});

			$view.on("click", "label", fnChangeViewEvent);
		},

		createMap : function (sUrl) {

			var oSearch = sensus.pages.device.smartpointTable.data().config.oSettings.fnRequest();
			var oRequest = new InquiryDeviceRequest(oSearch);
			var height = $(".filter-vert").height() - 92;

			if (height > 600) {

				height = height + "px";

			} else {

				height = null;
			}

			$.sc.map.build({

				height : height,

				ajaxCalls :  {

					fetchBounds : {
						url : "api/maps/fetch",
						request : oRequest,
					},

					fetchAll : {

						url : sUrl,
						request : function (nCurrentTrunc, aLatLonTruncs) {

							var oSearch = sensus.pages.device.smartpointTable.data().config.oSettings.fnRequest();
							var oRequest;

							oSearch.geocodeLatLongTruncs = aLatLonTruncs;
							oRequest = new InquiryDeviceRequest(oSearch);

							if ($.sc.isNullOrUndefined(nCurrentTrunc)) {

								nCurrentTrunc = 4;
							}

							oRequest.geoCodeTrunc = nCurrentTrunc;
							oRequest.pageSize = 10;
							oRequest.sortExpressions = [{field:"device_id", direction:"Ascending"}];

							return oRequest;
						},

						callback : null
					}
				}

			}, 'map', [], 1);

			$.sc.map.zoomTo(0);
		},

		dialogSettings : {

			searchSave : {

				title : sensus.locale.get("search.dialog.save.title"),

				width : 760,

				close : function (actionDialog) {
					$('.ui-dialog').find('.ui-dialog-titlebar-close').show();
				},

				buttons : (function () {

					var objButtons = {};

					objButtons[sensus.locale.get("commons.pages.save")] = function() {

						var $form = $("#save-form");

						if (!($form.validationEngine('validateField', '#saved-search-name')
								|| $form.validationEngine('validateField', '#saved-search-description'))) {

							sensus.util.device.saveSearch("api/search/insert");
						}
					};

					objButtons[sensus.locale.get("commons.pages.cancel")] = function() {

						$(this).dialog('close');
					};

					return objButtons;
				})(),

				/** The name of the form to clear when launching the dialog. */
				form : 'saveForm',

				/**
				 * The function that will be called when the action dialog is launched.
				 *
				 * @param actionDialog
				 *            a reference to the actionDialog objext
				 */
				action : function (actionDialog) {

					var sUrlPage = sensus.settings.appRoot + "/savedSearch/openDialog";
					var $actionDialog = $('#action-dialog');

					$actionDialog.load(sUrlPage, function() {
						$(".button").button();
					});

					$actionDialog.keypress(function(e) {

						if (e.keyCode == "13") {

							$('button:contains("' + sensus.locale.get("commons.pages.save") + '")').click();

							return false
						}
					});

					actionDialog.addClass('waiting');
					actionDialog.dialog('open');
				}
			},

			importHanDevice : function (sActionName, sUser) {

				return {

					title : sensus.locale.get("sensus.dm.action.import.han.device.dialog"),

					width : 790,

					height : 560,

					buttons : (function() {

						var buttons = {};

						buttons[sensus.locale.get("systemintelligence.importHan.dialog.buttonSubmit")] = function() {

							var oForm = $('#import-han-form');
							var uploadHan = $('#upload-han');
							var uploadFile = uploadHan.val().length;

							if (uploadFile == 0) {

								uploadHan.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);

							} else {

								if ($(".upload-hanformError").length == 0) {

									var fnCallback = function(bMonitored) {

										$("#b-monitored-han").val(bMonitored);

										sensus.util.page.startProgressBar();

										oForm.submit();
									}

									sensus.pages.longrunningprocess.monitorUpload(fnCallback);
								}
							}
						},

						buttons[sensus.locale.get("action.addtogroup.cancel")] = function() {

							$(this).dialog('close');
						};

						return buttons;

					})(),

					close : function() {

						$(".formError").remove();

					},

					action : function (actionDialog) {

						// Load
						actionDialog.load(sensus.settings.appRoot + "/action/dialog/importHanDevice", function () {

							$("#import-han-form").validationEngine('attach', {

								onValidationComplete: function(form, status) {

									if (!status) {

										sensus.util.page.stopProgressBar();

									} else {

										 form.validationEngine('detach');
										 form.submit();
									}
								}
							});

							$.ajaxValidator.fnDoCall("api/tag/fetchAll",
									new InquiryTagRequest ({endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]}),
									false, function(oResponse) {

										// iterator response object to create options from select element
										// and than call chosen plugin
										sensus.util.page.createOptions($("#select_tags"), oResponse.tags, "chosen", null, function (oData) {

											return oData.name + "|" + oData.id;
										});
									});

							actionDialog.removeClass('waiting');

							// Clean the upload file input when click in message error
							actionDialog.delegate('.upload-hanformError', 'click', function(e) {

								var uploadHan = $('#upload-han');

								if ($.browser.msie) {

									uploadHan.replaceWith(uploadHan.clone());
								}

								uploadHan.val('');

								$("#upload-list-han").attr("disabled", false);
							});
						});

						actionDialog.dialog("open");
					}
				}
			}
		}
	}
	</script>
</sec:authorize>