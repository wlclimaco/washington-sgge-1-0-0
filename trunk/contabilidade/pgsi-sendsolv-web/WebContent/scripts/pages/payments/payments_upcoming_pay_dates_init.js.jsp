<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments_upcoming
 * @description The init namespace for the Location Page.
 */
$(document).ready(function()
{

	$.pgsi.listener.wait({
		eventName 	: "locationList",
		arguments 	: ["table"],
		fnCallback  : function(){ }
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.payments_upcoming.locationTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/payment/upcoming_pay_dates",
		bPreLoad	: true,

		ajax :
		{
			sObj		: "locationList",
			oRequest	: PagedInquiryRequest,
			fnRequest 	: function(){}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "name",
			mRender         : pgsi.pages.payments_upcoming.fnCreateLocationNameLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.payments.table.nextPPD"),
			order			: "organization_column",
			mRender 		: pgsi.pages.payments_upcoming.fnNextPPD,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.payments.table.paycycle"),
			order			: "city_column",
			mRender 		: pgsi.pages.payments_upcoming.fnPayCycle,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false

		},
		{
			headerData 		: $.pgsi.locale.get("pages.payments.table.last.payroll"),
			order			: "state_column",
			mRender 		: pgsi.pages.payments_upcoming.fnLastPayroll,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "last-payroll"
		},
		{
			headerData 		: $.pgsi.locale.get("pages.payments.table.upcoming.payrolls"),
			order			: "country_column",
			mRender	 		: pgsi.pages.payments_upcoming.fnUpcomingPayrolls,
			sDefaultContent : "",
			bSortable 		: false
		},
		],

		<c:choose>
			<c:when test="${not empty refresh}">
				aaData : "refresh",
			</c:when>
			<c:when test="${empty response}">
				aaData : null,
		    </c:when>
		    <c:otherwise>
		    	aaData : ${response},
		    </c:otherwise>
		</c:choose>

		oSettings :
		{
			sortEnum      	: "",
			iDefaultCol   	: 0
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {


		},

		fnInitComplete: function (oSettings, json)
		{

			$(".dataTables_length select").outerWidth(62).selectmenu({
				appendTo: ".content.list",
  				change: function( event, ui ) {
  					$('#data_list_length').find("select").val(ui.item.value);
  					$("#data_list_length").find("select").trigger("change");
  					$("#load").find(".dataTables_length").find("select").selectmenu("refresh" );
  				}
			});
		}
	}
	));
	$.pgsi.progressBar.stopGlobal();
});
</script>
</sec:authorize>