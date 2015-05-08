<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

	<script type="text/javascript">
/**
 * @namespace pgsi.pages.sdn
 * @description The init namespace for the SDN Person View Page.
 * @author Anke Doerfel-Parker
 */

//Receives preloaded data
<c:choose>
	<c:when test="${empty response}">
    	var oResponse = null;
    </c:when>
    <c:otherwise>
    	var oResponse = ${response};
    </c:otherwise>
</c:choose>

<c:choose>
<c:when test="${empty moneyTransfer}">
	var oMoneyTransfer = null;
</c:when>
<c:otherwise>
	var oMoneyTransfer = ${moneyTransfer};
</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${empty sdn_history}">
	var oHistory = null;
</c:when>
<c:otherwise>
	var oHistory = ${sdn_history};

</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${empty sdn_entry}">
	var oEntryMap = null;
</c:when>
<c:otherwise>
	var oEntryMap = ${sdn_entry};
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${empty type}">
	var entityType = null;
</c:when>
<c:otherwise>
	var entityType = "${type}";
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${empty matches}">
var oMatchMap = null;
</c:when>
<c:otherwise>
var oMatchMap = ${matches};
</c:otherwise>
</c:choose>

var oMatch = null;
var aMatches = null;
var oMatchIndex = null;
var oEntity = null;
var oEntry = null;

 $(document).ready(function(){

 	var aSDNStatuses = $.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum");
 	var aValidSDNStatuses = [];

 	for (var i=0; i < aSDNStatuses.length; i++) {
 		// User cannot manually set SDN status to Neutral or Pending Investigation
 		if (aSDNStatuses[i].key != 3 && aSDNStatuses[i].key != 4) {
 			aValidSDNStatuses.push(aSDNStatuses[i]);
 		}
 	}

	$("#field-sdn-status").fnLoadDropDownList(aValidSDNStatuses);
	pgsi.util.page.form.fnInitForm();

	if (!$.pgsi.isNullOrUndefined(oHistory)){
		$('#sdnHistoryId').val(oHistory[0].id);
		pgsi.pages.sdn.view.fnCreateTableSDNHistory(oHistory);
	}
	if (!$.pgsi.isNullOrUndefined(oMoneyTransfer)){
		pgsi.pages.sdn.view.fnCreateTableMoneyTransfer(oMoneyTransfer);
	}

	if (!$.pgsi.isNullOrUndefined(oResponse)){
		pgsi.pages.sdn.view.fnStatusPersonBusiness(oResponse);
	}

	if (!$.pgsi.isNullOrUndefined(oMatchMap)) {
		for (var m in oMatchMap){
			oMatch = oMatchMap[m];
			oEntry = oEntryMap[m];
			break;
		}
	}
	if (!$.pgsi.isNullOrUndefined(oMatch)) {
		if (!$.pgsi.isNullOrUndefined(oMatch.sdnMatchFieldList)) {
			aMatches = oMatch.sdnMatchFieldList;
		}
	}
	switch (entityType) {
		case "member":
			oEntity = oResponse.memberList[0];
			pgsi.pages.sdn.view.fnSetPersonFields(oEntity);
			break;
		case "recipient":
			oEntity = oResponse.recipientList[0];
			pgsi.pages.sdn.view.fnSetPersonFields(oEntity);
			break;
		case "liaison":
			oEntity = oResponse.liasonList[0];
			pgsi.pages.sdn.view.fnSetPersonFields(oEntity);
			break;
		case "location":
			oEntity = oResponse.locationList[0];
			pgsi.pages.sdn.view.fnSetBusinessFields(oEntity);
			break;
		case "organization":
			oEntity = oResponse.organizationList[0];
			pgsi.pages.sdn.view.fnSetBusinessFields(oEntity);
			break;
		default:
			// leave null
	}

	if (!$.pgsi.isNullOrUndefined(oEntity)) {
		pgsi.pages.sdn.view.fnFillFields();
		if (aMatches != null){
			$("#detail-section").show();
			pgsi.pages.sdn.detail.fnFillSDNDetails(pgsi.pages.sdn.detail.fnBuildMatchIndex());
		} else {
			$("#detail-section").remove();
		}

	}


	$("#save-button").click(function(e) {
		e.preventDefault();
		for(var i=0;i < $('#batches .tx-descr').length;i++){
			$('#batches .tx-descr:eq('+i+')').find('.first .status_'+i+'').each(function() {
				if ($(this).is(':checked')){
					$('.reason_'+i).addClass("required").prop("placeholder","*");
				}
			})
		}
		var bValidForm = pgsi.pages.sdn.view.form.validator.form();
		if(bValidForm){
			var sMatchType;
			if(($.address.parameter("type") == "member")||($.address.parameter("type") == "recipient")||($.address.parameter("type") == "liaison")){
				sMatchType = 'INDIVIDUAL';
			}else{
				sMatchType = 'ENTITY';
			}
			$.pgsi.ajax.post({
				sUrl 		: 'api/sdn/update',
				oRequest 	: new SdnStatusHistoryRequest({matchType: sMatchType,sdnStatusHistory : {id : parseInt($('#sdnHistoryId').val(),10),parentKey : parseInt($.address.parameter("id"),10),modelAction:"UPDATE",sdnStatusValue:parseInt($('#field-sdn-status').val(),10),noteText:$('#field-sdn-note').val()}}),
				fnCallback  : function(oResponse) {
					var oMoneyTransferStatusList = new Array();
					for(var i=0;i < $('#batches .tx-descr').length;i++){

						if($('#batches .tx-descr:eq('+i+')').find('.first .status_'+i+'').is(":checked")){
							var sStatus = "";

							$('#batches .tx-descr:eq('+i+')').find('.first .status_'+i+'').each(function() {

								if ($(this).is(':checked')){
									sStatus = $(this).val()
								}
							})
							var oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
								id : null,
								moneyTransferBatchId : parseInt($('#batches .tx-descr:eq('+i+')').find('.first .id').text().split('#')[1],10),
								actionDueDate : (new Date()).getTime(),
								status : sStatus,
								modelAction : "INSERT"
							});

							oMoneyTransferStatusList.push(oMoneyTransferBatchStatus);

							var oRequest = new MoneyTransferBatchStatusMaintenanceRequest({
								moneyTransferBatchStatusList : oMoneyTransferStatusList,
								note : $('#batches .tx-descr:eq('+i+')').find('.first .width-long').val()
							});

							$.pgsi.ajax.post({
								 sUrl 		: 'api/moneyTransferBatchStatus/insert',
								 oRequest 	: oRequest,
								 fnCallback : function(oReturn){}
							});
						}
					}

					$.pgsi.pageLoader.load({
						url : "sdn/"+$.address.parameter("type")+"/view?type="+$.address.parameter("type")+"&id="+$.address.parameter("id"),
						$content : $("#load"),
						bStartProgressBar : false,
						bStartGlobalProgressBar : true
					});
				}
			});
		}
	});

	$('#status-template').on("click", "a.active, a.deactivate, a.delete", function(e) {

		e.preventDefault();
		switch (entityType) {
			case "member":
				oEntity = oResponse.memberList[0];
				break;
			case "recipient":
				oEntity = oResponse.recipientList[0];
				break;
			case "liaison":
				oEntity = oResponse.liasonList[0];
				break;
			case "location":
				oEntity = oResponse.locationList[0];
				break;
			case "organization":
				oEntity = oResponse.organizationList[0];
				break;
			default:
				// leave null
		}

		//TODO
		var refreshPage = function() {
			$.pgsi.pageLoader.load({
				url : "sdn/"+$.address.parameter("type")+"/view?type="+$.address.parameter("type")+"&id="+$.address.parameter("id"),
				$content : $("#load"),
				bStartProgressBar : false
			});
		};

		if ($(this).hasClass('active')) {


			switch (entityType) {
				case "member":
					pgsi.util.page.fnUpdateStatus('api/member/fetch',oEntity.id,'member',1,refreshPage,"Activate Member for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Member")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
					break;
				case "recipient":

					pgsi.util.page.fnUpdateStatus('api/recipient/fetch',oEntity.id,'recipient',1,refreshPage,"Activate Recipient for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Recipient")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
					break;
				case "liaison":
					pgsi.util.page.fnUpdateStatus('api/liaison/fetch',oEntity.id,'liaison',1,refreshPage,"Activate Liaison for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Liaison")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
					break;
				case "location":
					pgsi.util.page.fnUpdateStatus('api/location/fetch',oEntity.id,'location',1,refreshPage,"Activate Location for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Location")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
					break;
				case "organization":
					pgsi.util.page.fnUpdateStatus('api/organization/fetch',oEntity.id,'organization',1,refreshPage,"Activate Organization for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Organization")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
					break;
				default:
					// leave null
			}
		}

		if($(this).hasClass('deactivate')){

			switch (entityType) {
				case "member":
					pgsi.util.page.fnUpdateStatus('api/member/fetch',oEntity.id,'member',2,refreshPage,"Deactivate Member for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Member")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
					break;
				case "recipient":

					pgsi.util.page.fnUpdateStatus('api/recipient/fetch',oEntity.id,'recipient',2,refreshPage,"Deactivate Recipient for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Recipient")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
					break;
				case "liaison":
					pgsi.util.page.fnUpdateStatus('api/liaison/fetch',oEntity.id,'liaison',2,refreshPage,"Deactivate Liaison for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Liaison")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
					break;
				case "location":
					pgsi.util.page.fnUpdateStatus('api/location/fetch',oEntity.id,'location',2,refreshPage,"Deactivate Location for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Location")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
					break;
				case "organization":
					pgsi.util.page.fnUpdateStatus('api/organization/fetch',oEntity.id,'organization',2,refreshPage,"Deactivate Organization for "+ oEntity.name, "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Organization")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
					break;
				default:
					// leave null
			}
		}

	});

 });
 $.pgsi.progressBar.stopGlobal();
</script>

</sec:authorize>