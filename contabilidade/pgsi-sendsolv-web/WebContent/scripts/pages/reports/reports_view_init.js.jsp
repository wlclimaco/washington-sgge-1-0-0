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

 $(document).ready(function()
	{

	$('#sdnHistoryId').val(oHistory[0].id);
	 //------------------------ATTENTION-----------------------------------------
	 // THIS CODE ONLY LOOKS AT THE FIRST MATCH. NEED TO CONVERT TO TABS AND LOOK AT ALL!!!!!
	 // Please review sdn_detail_main.jsp - it contains ids for selecting element - it may not be safe to render this into the same page multiple times.
	 //-----------------------------------------------------------------------------------

	 pgsi.pages.sdn.view.fnCreateTableSDNHistory(oHistory);
	if (!$.pgsi.isNullOrUndefined(oMoneyTransfer)){
		pgsi.pages.sdn.view.fnCreateTableMoneyTransfer(oMoneyTransfer);
	}
	 pgsi.pages.sdn.view.fnStatusPersonBusiness(oResponse);



	$("#field-sdn-status").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum"));
	pgsi.util.page.form.fnInitForm();


	if (!$.pgsi.isNullOrUndefined(oHistory)
			&& !$.pgsi
					.isNullOrUndefined(oMatchMap)) {
		for (var m in oMatchMap){
			oMatch = oMatchMap[m];
			oEntry = oEntryMap[m];
			break;
		}
		if (!$.pgsi.isNullOrUndefined(oMatch)) {
			if (!$.pgsi.isNullOrUndefined(oMatch.sdnMatchFieldList)) {
				aMatches = oMatch.sdnMatchFieldList;
			}
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

	} else {

		// Error - decide what to do
	}
	$("#save-button").click(function(e) {
		e.preventDefault();

		$.pgsi.ajax.post({
			sUrl 		: 'api/sdn/update',
			oRequest 	: new SdnStatusHistoryRequest({sdnStatusHistory : {id : parseInt($('#sdnHistoryId').val(),10),modelAction:"UPDATE",sdnStatusValue:parseInt($('#field-sdn-status').val(),10),noteText:$('#field-sdn-note').val()}}),
			fnCallback  : function(oResponse) {

			}
		});
	});

 });

</script>

</sec:authorize>