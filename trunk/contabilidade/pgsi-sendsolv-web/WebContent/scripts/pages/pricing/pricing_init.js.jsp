<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{

	$.pgsi.listener.wait({
		eventName 	: "locationList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.pricing.pricingTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/location/fetchall",
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
			headerData 		: $.pgsi.locale.get("pages.pricing.table.label.name"),
			order			: "name",
			mRender         : pgsi.pages.pricing.fnCreateLocationNameLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.pricing.table.label.description"),
			order			: "",
			mRender        	: pgsi.pages.pricing.fnCreateDescription,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.pricing.table.label.lastEdit"),
			order			: "",
			mRender         : pgsi.pages.pricing.fnFormatModifyDate,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: $.pgsi.locale.get("pages.pricing.table.label.author"),
			order			: "",
			mRender       	: pgsi.pages.pricing.fnRenderAuthor,
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
			sortEnum      	: "",
			iDefaultCol   	: 0
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

		},

		fnInitComplete: function (oSettings, json)
		{
			$.pgsi.listener.notify({
				eventName 	: "locationList",
				arguments 	: ["table"]
			});

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
});
</script>
</sec:authorize>