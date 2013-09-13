<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	//head(function() {
		$(document).ready(function() {



			/*
			 * Variable: oSelect
			 * Purpose:  Store object jQuery with DOM of the select
			 * type:     object
			 * Scope:    -
			 * Notes:    -
			 */
			var oSelect = $("#repeats");

			/*
			 * Variable: oSummary
			 * Purpose:  Store object jQuery with DOM of the text
			 * type:     object
			 * Scope:    -
			 * Notes:    -
			 */
			var oSummary = $("#repeats-summary");

			/*
			 * Variable: repeatName
			 * Purpose:  To check if has values
			 * type:     string
			 * Scope:    -
			 * Notes:    -
			 */
			var repeatName = $("#frequencyEnum").val();

			if (repeatName) {
				$("select#repeats option[value='" + repeatName + "']").attr("selected", "selected");
			}

			sensus.pages.scheduleEvent.fnChangeRepeatType(oSelect);

			// Change perspective of the dialog when show up
			oSelect.change(function () {
				sensus.pages.scheduleEvent.fnChangeRepeatType(oSelect);
				sensus.pages.scheduleEvent.fnChangeSummary(oSelect);
			});

			$( "#start_date" ).datepicker({ onSelect: function() { $(".ui-datepicker a").removeAttr("href"); } });

			var formatter = sensus.settings.dateFormatMask.replace('yyyy','yy');

			$( ".datepicker" ).datepicker({minDate: '-0d', dateFormat: formatter, onSelect: function() { $(".ui-datepicker a").removeAttr("href"); }});
			$('.datepicker-day').datepicker({ dateFormat: 'dd'});
			$('.event-day-custom').buttonset();


			if ($.address.parameter("type") == "edit") {

				$('#repeats-form #ends-on input[type=text]').each(function() {

					if($(this).val().length){

						$(this).prev().prev().attr("checked", true);

					}

				});

				var sRepeatType = $('#repeat-type-select').val();

				if (sRepeatType == "MONTHLY") {

					$("#" + $('#repeat-by-monthly').val()).prop("checked", true);

				}
			}

		});
		//}); head
	</script>

</sec:authorize>