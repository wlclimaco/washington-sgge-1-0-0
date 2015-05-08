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

	$.pgsi.listener.wait({
		eventName 	: "payerList",
		arguments 	: ["table"],
		fnCallback 	: $.pgsi.progressBar.stopGlobal
	});

	$('#country').text('Country ('+$.address.parameter("countryName").split('|')[1]+')');
	$('#state').text('State ('+decodeURI($.address.parameter("stateName").split('|')[1])+')');
	$('#stateLink').attr('href','payer/find/state?code='+decodeURI($.address.parameter("countryName").split('|')[0])+'&countryName='+$.address.parameter("countryName"));
	/** * jQuery dataTable setup ** */
	pgsi.pages.payer.payerTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/payer/payerCity',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'payerCityList',
			oRequest	: PayerStateProvinceRegionInquiryRequest,
			fnRequest 	: function(){

				var criteria = {
						id          : parseInt($.address.parameter("id"),10)
					};

					return  new PayerStateProvinceRegionInquiryRequest({
						criteria : criteria
					});
			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.name"),
			order			: "name",
			mRender         : function (val, type, full){

				if (type !== "display")
				{
					return val;
				}
				return '<a  id= "edit_links" title="View/Edit ' + full.name +'" href="payer/find/branches?id=' + full.id + '&countryName='+$.address.parameter("countryName")+'&stateName='+$.address.parameter("stateName")+'&cityName='+full.id+'|'+full.name+'" class="edit_link alist">' + full.name +'</a>';
			},
			sDefaultContent : "",
			bSortable 		: true,
			width           : '50px',
			sClass          : "name-col"

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


});
</script>
</sec:authorize>