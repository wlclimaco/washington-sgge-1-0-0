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

	//$('#').attr('href',)

	$('#country').text('Country ('+$.address.parameter("countryName").split('|')[1]+')');

	/** * jQuery dataTable setup ** */
	pgsi.pages.payer.payerTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/payer/payerState',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'payerStateProvinceRegionList',
			oRequest	: PayerStateProvinceRegionInquiryRequest,
			fnRequest 	: function(){
				var sCode = "",
					sName = "",
					sLongName = "";
				if(($.address.parameter("stateCode") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("stateCode")))){
					sName = $.address.parameter("stateCode");
				}else{
					sName = null;
				}
				if(($.address.parameter("stateName") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("stateName")))){
					sLongName = $.address.parameter("stateName");
				}else{
					sLongName = null;
				}
				if(($.address.parameter("code") !== "|")&&(!$.pgsi.isNullOrUndefined($.address.parameter("code")))){
					sCode = $.address.parameter("code");
				}else{
					sCode = null;
				}

				var criteria = {
					longName       : sLongName,
					name           : sName,
					noRelationship : true,
					country        : {code : sCode}
				};

				return  new PayerStateProvinceRegionInquiryRequest({
					criteria : criteria
				});
			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("pages.payer.tables.label.code"),
			order			: "code",
			sDefaultContent : "",
			mRender         : function (val, type, full){ if (type !== "display")
				{
					return val;
				}

				return '<a  id= "edit_links" title="View/Edit ' + full.name +'" href="payer/find/cities?id=' + full.id + '&countryName='+$.address.parameter("countryName")+'&stateName='+full.id+'|'+full.longName.trim()+'" class="edit_link alist">' + full.name +'</a>';
			},
			bSortable 		: true,
			sClass          : "state-code-col"

		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "code",
			sDefaultContent : "",
			mRender         : function (val, type, full){ if (type !== "display")
				{
					return val;
				}
				return '<a  id= "edit_links" title="View/Edit ' + full.longName +'" href="payer/find/cities?id=' + full.id + '&countryName='+$.address.parameter("countryName")+'&stateName='+full.id+'|'+full.longName.trim()+'" class="edit_link alist">' + full.longName +'</a>';
			},
			bSortable 		: true,
			width           : '10px'

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
		var aFilters = ['states'];

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
	$("#clear-all").on("click", function(e)
	{
		$.address.parameter("stateName","");
		$.address.parameter("stateCode","");
		pgsi.util.page.fnReloadTable(pgsi.pages.payer.payerTable);
	});

});
</script>
</sec:authorize>