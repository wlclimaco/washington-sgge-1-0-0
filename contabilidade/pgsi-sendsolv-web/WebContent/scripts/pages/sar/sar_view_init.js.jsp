<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

	<script type="text/javascript">
/**
 * @namespace pgsi.pages.sdn
 * @description The init namespace for the SDN Person View Page.
 * @author Washington Costa
 */
 <c:choose>
	<c:when test="${empty response}">
 	var oResponse = null;
 </c:when>
 <c:otherwise>
 	var oResponse = ${response};
 </c:otherwise>
</c:choose>

<c:choose>
<c:when test="${empty responseAux}">
	var oBusiness = null;
</c:when>
<c:otherwise>
	var oBusiness = ${responseAux};
</c:otherwise>
</c:choose>

 $(document).ready(function(){

	$("#field-sdn-status").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.SARStatusEnum"));
	pgsi.util.page.form.fnInitForm();
	var oObject;

	if (!$.pgsi.isNullOrUndefined(oBusiness)){
		if ($.address.parameter("type") == "MEMBER"){
			oObject = oBusiness.memberList[0];
			$('#business-id').val(oObject.id);
			$('#business-name').val(oObject.lastName +" "+oObject.firstName);
			$('.pep-overview').addClass('hide');
			$('.sdnStatus').removeClass('hide');
			$('.sdnStatus-person').addClass('hide');
		}else if ($.address.parameter("type") == "RECIPIENT"){
			oObject = oBusiness.recipientList[0];
			$('#business-id').val(oObject.id);
			$('#business-name').val(oObject.lastName +" "+oObject.firstName);
			$('.pep-overview').addClass('hide');
			$('.sdnStatus').removeClass('hide');
			$('.sdnStatus-person').addClass('hide');
		}else if ($.address.parameter("type") == "LIAISON"){
			oObject = oBusiness.liaisonList[0];
			$('#business-id').val(oObject.id);
			$('#business-name').val(oObject.lastName +" "+oObject.firstName);
			$('.pep-overview').addClass('hide');
			$('.sdnStatus').removeClass('hide');
			$('.sdnStatus-person').addClass('hide');
		}else if ($.address.parameter("type") == "LOCATION"){
			oObject = oBusiness.locationList[0];
			$('#business-id').val(oObject.id);
			$('#business-name').val(oObject.name);
			$('.pep-overview').removeClass('hide');
			$('.sdnStatus').addClass('hide');
			$('.sdnStatus-person').removeClass('hide');
		}else if ($.address.parameter("type") == "ORGANIZATION"){
			oObject = oBusiness.organizationList[0];
			$('#business-id').val(oObject.id);
			$('#business-name').val(oObject.name);
			$('.pep-overview').removeClass('hide');
			$('.sdnStatus').addClass('hide');
			$('.sdnStatus-person').removeClass('hide');
		}


	}

	if(!$.pgsi.isNullOrUndefined(oResponse)){
		$("#business-type").val(oResponse.suspiciousActivityList[0].type)
		$("#company-name").text("SAR Report #"+pgsi.util.page.fnInsertMask("***-99999",oResponse.suspiciousActivityList[0].businessKey ));
	}
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
		pagingType  : "null",

		ajax :
		{
			sObj		: 'suspiciousActivityList',
			oRequest	: SarInquiryRequest,
			fnRequest 	: function(){
				return  new SarInquiryRequest ({criteria : {businessKey : $('#key').val()}})
			}

		},

		aoColumns :
		[
		{
			headerData 		: $.pgsi.locale.get("commons.pages.ID"),
			order			: "id",
			sDefaultContent : "",
			mRender	 		: pgsi.pages.sar.table.fnCreateIDLink,
			bSortable 		: true,
			width           : '10px'

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
		},
		{
			headerData 		: $.pgsi.locale.get("commons.pages.status"),
			order			: "lastName",
			mRender	 		: pgsi.pages.sar.table.fnSARStatus,
			sDefaultContent : "",
			bSortable 		: false

		},
		{
			headerData 		: $.pgsi.locale.get("pages.sdn.view.reason"),
			mData	 		: "detail",
			sDefaultContent : "",
			bSortable 		: false

		}




		],
		aaData : oResponse,

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
				eventName 	: "sarList",
				arguments 	: ["table"]
			});
			$('.sdn-history .list_header').hide()
			$('.sdn-history .list_footer').hide()
		}
	}
	));

	pgsi.pages.sar.view.fillSARDetail(oResponse.suspiciousActivityList,oObject)

	$(".btn").button();
	$("select").selectmenu();
	$("#dateTime").datepicker();

	$("#save-button").click(function(e) {
		e.preventDefault();
		$('#business-id').val();
		$('#key').val();
		$('#business-type').val();
		$('#person-id').val();
		var oSar = $.pgsi.storage.get('SAR');


		 oSuspiciousActivity = new SuspiciousActivity({
			type 		 				: oSar.type,
			modelAction 				: "UPDATE",
			status					    : $.pgsi.enums.fetchLabelByValue("com.prosperitasglobal.sendsolv.model.SARStatusEnum",$("#field-sdn-status").val()),
			summary 		 			: oSar.summary,
			detail		     			: $('#field-sdn-note').val(),
			activityStartDateTimeUTC	: oSar.activityStartDateTimeUTC,
			activityStopDateTimeUTC		: oSar.activityStartDateTimeUTC,
			amountAssociated			: oSar.amountAssociated,
			statusDateTime				: new Date($('#dateTime').val()).getTime(),
			personList 					: oSar.personList,
			businessList 				: oSar.businessList,
			businessKey                 : oSar.businessKey
		});

		$.pgsi.ajax.post({
			 sUrl : 'api/sar/update',
			 oRequest : new SarMaintenanceRequest({suspiciousActivity : oSuspiciousActivity}),
			 fnCallback : function(oResponse){
				 $.pgsi.table.reloadTable({
					table 		: pgsi.pages.sar.sarTable
				});
				$.pgsi.progressBar.stop();
			 }
		});
	});
	$("#edit-risk").click(function(event)
	{
		event.preventDefault();

		var oRequest = new RiskMaintenanceRequest({
			risk : pgsi.pages.risk.view.fillObject()
		});

		pgsi.util.actiondialog.launchActionDialog("insUpdRisk", pgsi.pages.business.dialogSettings.insUpdRisk(parseInt($('#business-id').val(),10), $('#business-name').val(), oRequest));
	});


 });
</script>
</sec:authorize>