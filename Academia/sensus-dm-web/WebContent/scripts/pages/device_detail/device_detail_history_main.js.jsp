<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
		
	<script type="text/javascript">
	/**
	  * @namespace sensus.pages.detail.history
	  * @description The create-main namespace for the Device Detail History.
	  * @fileoverview Defines the core functionality of the Device Detail History page.
	  * @author QATEmployee
	  */
	//head(function() {
		sensus.pages.devicehistory = {

			  fnRequestAction : function() {

					var sId 			= $.address.parameter("id") || 0,
						sQuery			= sensus.util.page.fnFormatURLFilter($.address.parameter("query"), true),
						sViewFrom		= $.address.parameter('view_from') || '',
						sTotalDays		= $.address.parameter('total_days') || 10,
						sSearchText,
						sSearchType,
						oProcessSearch;

					if (!$.ajaxValidator.fnIsNullOrUndefined(sQuery)) {
						sSearchText = sQuery.split('|')[0];
						sSearchType = sQuery.split('|')[1];
					}

					oProcessSearch = new ProcessSearch({
						searchText 			: sSearchText,
						searchType 			: sSearchType,
						startDate 			: sensus.util.page.fnFormatDateFilter(sTotalDays, 'setStartDate', sViewFrom),
						endDate  			: sensus.util.page.fnFormatDateFilter(sViewFrom, 'endDate'),
						users				: sensus.util.page.fnFormatURLFilter($.address.parameter("users")),
						actionTypeEnums 	: sensus.util.actionrequestutil.fnGetActionTypesByCategories(
												sensus.settings.oDeviceTypeParameters.eventHistoryFilterActions,
												sensus.util.page.fnFormatURLFilter($.address.parameter("all_action_categories"))),
						processCategories	: sensus.util.process.fnGetProcessTypes(sensus.util.page.fnFormatURLFilter($.address.parameter("all_action_categories")))
					});

					return {
						devices : [new Device({radio : new Radio({flexNetId : sId})})],
						processSearch : oProcessSearch
					};
				}
		};
	//}); head
	</script>

</sec:authorize>