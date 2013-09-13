<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		$(document).ready(function() {

			sensus.util.page.startProgressBar();

			var sParameterDateStart = "date_start";
			var sStart 				= $.address.parameter(sParameterDateStart);
			var	sOption 			= '';
			var oSelect 			= $('#select-year');
			var iDay    			= $.datepicker.parseDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), sensus.pages.demandreads.oDate.getDate(new Date()));

			for (var i = 0; i <= 5; i++) {
				sOption += '<option value="'+ $.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), iDay) +'">' + iDay.getFullYear() + '</option>';
				iDay.setFullYear(iDay.getFullYear() - 1);
			}

			oSelect.append(sOption);
			oSelect.chosen({no_results_text: sensus.locale.get('commons.pages.noResultsMatched')}).change(function() {

				sensus.pages.demandreads.demandReadsTable.fnDestroy();
				$('#smartpoint-table thead tr, #smartpoint-table tbody').empty();
				$.fn.pageLoader.parameter(sParameterDateStart, $('#select-year option:selected').val());
				sensus.pages.demandreads.fnLoadDemandReadsTable();

			});

			$.fn.pageLoader.parameter(sParameterDateStart, $('#select-year option:selected').val());

			// Load Demand Reads Table
			sensus.pages.demandreads.fnLoadDemandReadsTable();

			// if not has date in URL set values
			if (!sStart) {
				sStart = sensus.pages.demandreads.oDate.getDate(new Date());
				$.fn.pageLoader.parameter(sParameterDateStart, sStart);
			}

			 // To  Generate the archive
			$("#csv").click(function(e) {

				e.preventDefault();

				sensus.util.page.startProgressBar();

				var sDateStart = new Date($.datepicker.formatDate(sensus.settings.dateFormatMask.replace('yyyy', 'yy'), new Date($.address.parameter(sParameterDateStart))));
				var oRequestCSV;

				var inquiryDemandReadRequest = new InquiryDemandReadRequest({

					device		: sensus.util.module_operation.createNewDeviceForReadingsRequest(sensus.util.module_operation.fnPrepareDeviceData()),
					initialDate	: sensus.util.page.fnFormatDateFilter($.address.parameter('date_start'), 'date_start', 'startYear'),
					endDate		: sensus.util.page.fnFormatDateFilter($.address.parameter('date_start'), 'date_start', 'endYear'),
					sortExpressions : sensus.util.page.getSortExpression(sensus.pages.demandreads.demandReadsTable)

				});

				sensus.util.exportcsv.generateCSV("api/export/generateDemandReadDetailCSV", inquiryDemandReadRequest);
			});

			sensus.util.page.stopGlobalProgressBar();

		});
	</script>
</sec:authorize>