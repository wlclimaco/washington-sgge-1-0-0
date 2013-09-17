<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	/**
	 * @namespace sensus.pages.processtoday
	 * @description The main namespace for the System Intelligence Today Page.
	 * @fileoverview Defines the core functionality of the System Intelligence Today Page.
	 * @author QAT
	 */
	 //head(function() {
		sensus.pages.processtoday = {

			fnRequestAction : function () {

				var sDateToday = $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), new Date());

				var processSearch = new ProcessSearch({
					endDate		: sensus.util.page.fnFormatDateFilter(sDateToday, 'endDate'),
					startDate 	: sensus.util.page.fnFormatDateFilter(sDateToday, 'setStartDate')
				});

				return {
					isToday : true,
					processSearch : processSearch
				};
			}

		 };
		//}); head
	</script>

</sec:authorize>