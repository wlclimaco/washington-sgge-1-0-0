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
	$('#state').text('State ('+$.address.parameter("stateName").split('|')[1].replace('%20',' ')+')');
	$('#stateLink').attr('href','payer/find/state?code='+decodeURI($.address.parameter("countryName").split('|')[0])+'&countryName='+$.address.parameter("countryName"));
	$('#city').text('City ('+decodeURI($.address.parameter("cityName").split('|')[1])+')');
	$('#cityLink').attr('href','payer/find/cities?id='+decodeURI($.address.parameter("stateName").split('|')[0])+'&countryName='+$.address.parameter("countryName")+'&stateName='+$.address.parameter("stateName"));

	/** * jQuery dataTable setup ** */
	pgsi.pages.payer.payerTable = $('#data_list').dataTable($.pgsi.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : 'api/payer/payerBranches',
		bPreLoad	: true,
		pagingType  : "full",

		ajax :
		{
			sObj		: 'payerAddressList',
			oRequest	: PayerStateProvinceRegionInquiryRequest,
			fnRequest 	: function(){

			}
		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("pages.payer.tables.label.address"),
			order			: "name",
			mData           : "address",
			sDefaultContent : "",
			bSortable 		: true,
			sClass          : "branches-code-col"

		},
		{
			headerData 		: $.pgsi.locale.get("pages.payer.tables.label.payer"),
			order			: "name",
			mData           : "payer.name",
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "payer-name-col"

		},
		{
			headerData 		: $.pgsi.locale.get("pages.payer.tables.label.branches"),
			order			: "name",
			mRender         : function (val, type, full){

				if (type !== "display")
				{
					return val;
				}
				sBranches = "";
				if(full.payerBranchList.length > 0){
					for(i=0;i < full.payerBranchList.length;i++){
						if(i == 0){
							sBranches = sBranches + full.payerBranchList[i].name;
						}else{
						//	debugger
							if(full.payerBranchList[i].name != ""){
								sBranches = sBranches + ', <br>' +full.payerBranchList[i].name;
							}
						}
					}
				}
				return sBranches
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
			$('.branches #data_list_info').hide();
			$('.branches #data_list_length').hide();
			$('.branches .dataTables_paginate').hide();
			$('.branches .list_footer').hide();
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