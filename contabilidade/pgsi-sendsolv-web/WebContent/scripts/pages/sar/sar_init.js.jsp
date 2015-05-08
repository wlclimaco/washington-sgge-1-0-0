<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The init namespace for the Member List Page.
 * @author Washington Costa
 */
$(document).ready(function()
{

	$.pgsi.listener.wait({
		eventName 	: "sarList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.sar.sarTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/sar/fetch',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'suspiciousActivityList',
			oRequest	: SarInquiryRequest,
			fnRequest 	: function(){

			}

		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.ID"),
			order			: "name",
			sDefaultContent : "",
			mRender	 		: pgsi.pages.sar.table.fnCreateIDLink,
			bSortable 		: true,
			width           : '10px'

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.status"),
			order			: "lastName",
			mRender	 		: pgsi.pages.sar.table.fnSARStatus,
			sDefaultContent : "",
			bSortable 		: false

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.type"),
			order			: "lastName",
			mRender	 		: pgsi.pages.sar.table.fnSARType,
			sDefaultContent : "",
			bSortable 		: false

		},
		{
			headerData 		: $.pgsi.locale.get("pages.sar.dialog.report.target"),
			mRender	 		: pgsi.pages.sar.table.fnCreateBusinessNamePersonNameLink,
			sDefaultContent : "",
			bSortable 		: false,
			width           : '50px',
			sClass          : "location-col"

		},
		{
			headerData 		: $.pgsi.locale.get("pages.sar.dialog.activity.period"),
			mRender	 		: pgsi.pages.sar.table.fnActivityPeriod,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.sar.dialog.amount.total"),
			order			: "state_column",
			mRender	 		: pgsi.pages.sar.table.fnTotalAmout,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.summary"),
			order			: "state_column",
			mData	 		: "summary",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.reported"),
			order			: "state_column",
			mRender	 		: function(val, type, full){ return $.pgsi.date.format(new Date(full.createDateUTC), "mm/dd/yy", null)},
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.reporter"),
			order			: "state_column",
			mData	 		: "createUser",
			sDefaultContent : "",
			bSortable 		: false
		}
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
			sortEnum      	: 'com.prosperitasglobal.sendsolv.model.OrganizationOrderByEnum',
			iDefaultCol   	: 0
		},

		/**
		* Callback function used by the datatables plugin called for every row
		*/
		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

		},

		fnInitComplete: function(oSettings, json)
		{

			$(".dataTables_length select").outerWidth(62).selectmenu({
				appendTo: ".content.list",
  				change: function( event, ui ) {
  					$('#data_list_length').find("select").val(ui.item.value);
  					$("#data_list_length").find("select").trigger("change");
  					$("#load").find(".dataTables_length").find("select").selectmenu("refresh" );
  				}
			});

			$.pgsi.listener.notify({
				eventName 	: "sarList",
				arguments 	: ["table"]
			});
		}
	}
	));

});
</script>
</sec:authorize>