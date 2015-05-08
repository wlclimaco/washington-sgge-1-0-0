<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.payer
 * @description The init namespace for the Member List Page.
 * @author Washington Costa
 */
$(document).ready(function()
{
	<c:choose>
		<c:when test="${not empty refresh}">
			var oFilterPreLoad = "refresh";
		</c:when>
		<c:when test="${empty filters}">
			var	oFilterPreLoad = null;
		</c:when>
		<c:otherwise>
			var	oFilterPreLoad = ${filters};
		</c:otherwise>
	</c:choose>

	$.pgsi.listener.wait({
		eventName 	: "payerList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	/** * jQuery dataTable setup ** */
	pgsi.pages.payer.payerTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/payer/payerCoutry',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'payerCountryList',
			oRequest	: PayerStateProvinceRegionInquiryRequest,
			fnRequest 	: function(){
				var sCode = "",
					sName = "";
				if(($.address.parameter("code") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("code")))){
					sCode = $.address.parameter("code");
				}else{
					sCode = null;
				}
				if(($.address.parameter("name") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("name")))){
					sName = $.address.parameter("name");
				}else{
					sName = null;
				}

				var criteria = {
					countryCode : sCode,
					name        : sName,
					noRelationship : true
				};

				return  new PayerStateProvinceRegionInquiryRequest({
					criteria : criteria
				});
			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.id"),
			order			: "code",
			sDefaultContent : "",
			mRender         : pgsi.pages.payer.fnCreateCodeLink,
			bSortable 		: true,
			sClass          : "coutry-code-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "name",
			mRender         : pgsi.pages.payer.fnCreateCountryLink,
			sDefaultContent : "",
			bSortable 		: true,
			width           : '50px'


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

			$.pgsi.listener.notify({
				eventName 	: "payerList",
				arguments 	: ["table"]
			});
		}
	}
	));

	if (!$.pgsi.isNullOrUndefined(oFilterPreLoad)) {
		// Filters
		var aFilters = ['coutry'];

		var filters = pgsi.util.filter.filterArrayToObject(aFilters);
		pgsi.util.filter.init(oFilterPreLoad, filters, function(oResponse)
		{
			$.pgsi.filter.create(
			{
				element			: ".filter",
				tagsDiv			: ".filter-results-container div.first",
				title			: $.pgsi.locale.get("commons.pages.filterTitle"),
				table 			:  pgsi.pages.payer.payerTable,
				filters 		: oResponse
			});
		});
	}
	//clear all Filter TODO
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("code","");
		$.address.parameter("name","");
		pgsi.util.page.fnReloadTable(pgsi.pages.payer.payerTable);
	});

});
</script>
</sec:authorize>