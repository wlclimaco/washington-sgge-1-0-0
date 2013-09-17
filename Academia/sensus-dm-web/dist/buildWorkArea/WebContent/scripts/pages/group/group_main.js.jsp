<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	/**
	 * @namespace sensus.pages.group
	 * @description The creat-main namespace for the group page.
	 * @fileoverview Defines the core functionality of the group page.
	 * @author QATEmployee
	 */
	sensus.pages.group = {

		api : {
			remove : "api/group/delete"
		},

		aSelectedGroups : null,

		fnGetSelectedGroups : function (sProperty) {

			if (this.aSelectedGroups != null) {

				return this.fnGetArrayByProperty(this.aSelectedGroups, sProperty);
			}

			return this.fnGetArrayByProperty(sensus.widgets.datatable.selectedRows, sProperty);
		},

		fnGetArrayByProperty : function (aArray, sProperty) {

			var aReturn;

			if (!sProperty) {

				return aArray;
			}

			aReturn = [];

			for (var i = 0; i < aArray.length; i++) {

				aReturn.push(aArray[i][sProperty]);
			}

			return aReturn;
		},

		fnGetScheduleByGroupIds : function (aIds) {

			var aSchedules = [];
			var oFetchRequest = new FetchRequest({id: aIds, type: "checksSchedule"});

			$.ajaxValidator.fnDoCall("api/schedule/fetch", oFetchRequest, false, function (oResponse) {

				if (oResponse.schedules && oResponse.schedules.length > 0) {

					aSchedules = oResponse.schedules;
				}
			});

			return aSchedules;
		},

		fnCompareTwoVectors : function(aVectors, bVectors) {

			var aVectorC = [];
			var i = 0;

			for (; i < bVectors.length; i = i + 1) {

				if ($.inArray(bVectors[i], aVectors) > -1) {

					aVectorC.push(bVectors[i]);
				}
			}

			return aVectorC;
		},

		fnRequestAction : function() {

			var _paramRequest = sensus.util.filter.paramRequest;
			var groupDeviceTypes = _paramRequest.getParameterArray("group_device_type");
			var hanDeviceTypes = ["IHD", "THERMOSTAT"];
			var groupHanDeviceTypes = [];
			var deviceSearch = new DeviceSearch({
				groupTypes	: _paramRequest.getParameterArray("group_type"),
				searchText	: _paramRequest.getParameter("query")
			});

			if (groupDeviceTypes) {

				groupDeviceTypes = $.grep(groupDeviceTypes, function (n) {

					var i = $.inArray(n, hanDeviceTypes);

					if (i != -1) {

						groupHanDeviceTypes.push(n);

						return false;
					}

					return true;
				});

				if (groupDeviceTypes.length) {

					deviceSearch.deviceTypes = groupDeviceTypes;
				}

				if (groupHanDeviceTypes.length) {

					deviceSearch.hanDeviceSearch = {
							hanDeviceTypeEnumList : groupHanDeviceTypes
					};
				}
			}

			return {deviceSearch : deviceSearch, preQueryCount : true};
		},

		fnFillFilter : function (oPreLoad) {

			var filterUtil = sensus.util.filter.filters;
			var filters = {
				query_search 	: filterUtil.query_search,
				group_type 		: filterUtil.group_type
			};

			// search hint 'Search Groups'
			filterUtil.query_search.inputs.query.hint = sensus.locale.get("group.page.searchGroups");

			if (sensus.settings.serviceType == sensus.constants.services.electric.name) {

				$.extend(filters, {group_device_type : filterUtil.group_device_type});
			}

			sensus.util.filter.init(oPreLoad, filters, function(oResponse) {

				$("#w-filters").filters({
					tagsDiv : ".filter-results-containter div.first",
					title : sensus.locale.get("widgets.filter.filterEvents"),
					table : sensus.pages.group.groupTable,
					filters : oResponse
				});
			});
		}
	};
	</script>

</sec:authorize>