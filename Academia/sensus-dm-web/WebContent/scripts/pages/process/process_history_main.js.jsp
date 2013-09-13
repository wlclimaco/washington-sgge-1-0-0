<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	/**
	 * @namespace sensus.pages.processhistory
	 * @description The main namespace for the System Intelligence History Page.
	 * @fileoverview Defines the core functionality of the System Intelligence History Page.
	 */
	sensus.pages.processhistory = {

			fnRequestAction : function() {

				var sQuery				= sensus.util.page.fnFormatURLFilter($.address.parameter("query"), true),
					sSearchText 		= null,
					sSearchType 		= null,
					sViewFrom			= $.address.parameter('view_from') || '',
					sTotalDays			= $.address.parameter('total_days') || 10,
					oActionCategories	= [];

				if (!$.ajaxValidator.fnIsNullOrUndefined(sQuery)) {
					sSearchText = sQuery.split('|')[0];
					sSearchType = sQuery.split('|')[1];
				}

				var processSearch = new ProcessSearch({
										searchText 			: sSearchText,
										searchType 			: sSearchType,
										startDate  			: sensus.util.page.fnFormatDateFilter(sTotalDays, 'setStartDate', sViewFrom),
										endDate 			: sensus.util.page.fnFormatDateFilter(sViewFrom, 'endDate'),
										users				: sensus.util.page.fnFormatURLFilter($.address.parameter("users")),
										actionTypeEnums 	: sensus.util.actionrequestutil.fnGetActionTypesByCategories(
																	sensus.settings.oDeviceTypeParameters.eventHistoryFilterActions,
																	sensus.util.page.fnFormatURLFilter($.address.parameter("all_action_categories"))),
										processCategories	: sensus.util.process.fnGetProcessTypes(sensus.util.page.fnFormatURLFilter($.address.parameter("all_action_categories")))
									});

				return {
					processSearch : processSearch
				};
			}
	};
	</script>

</sec:authorize>