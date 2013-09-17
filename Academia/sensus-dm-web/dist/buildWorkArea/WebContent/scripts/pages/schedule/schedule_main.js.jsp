<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.pages.systemintelligence = {

		eventName : "",

		colId : "",

		oItems : [],

		sStartTime : "",

		fnRequestAction : function() {

			var scheduleStatusEnumUrl = sensus.util.page.fnFormatURLFilter($.address.parameter('status_scheduled'));

			var baseSearch = new BaseSearch({
				searchText			: sensus.util.page.fnFormatURLFilter($.address.parameter("query"), true),
				startDate 			: sensus.util.page.fnFormatDateFilter('', 'setStartDate'),
				endDate    			: sensus.util.page.fnFormatDateFilter('', ''),
				users				: sensus.util.page.fnFormatURLFilter($.address.parameter("users")),
				actionTypeEnums 	: sensus.util.actionrequestutil.fnGetActionTypesByCategories(
						sensus.settings.oDeviceTypeParameters.eventHistoryFilterActions,
						sensus.util.page.fnFormatURLFilter($.address.parameter("schedule_action_categories")))
			});

			return {
				baseSearch	 	    : baseSearch,
				scheduleStatusEnums : scheduleStatusEnumUrl,
				frequencies 		: sensus.util.page.fnFormatURLFilter($.address.parameter('repeats'))
			}
		}
	};
	</script>
</sec:authorize>