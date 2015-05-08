<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.recipient
 * @description The init namespace for the Recipient View Page.
 * @author Flavio Tosta
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

//Attach Links to tabs
$("#personInfo").attr('href', "recipient/view/info?recipientId=" + oResponse.recipientList[0].id);
$('#memberList').attr('href',"member/fetchMemberByRecipient?recipientId=" + oResponse.recipientList[0].id);

var sTab = $.address.parameter("tab");
var iActiveTab;

if (!$.pgsi.isNullOrUndefined(sTab) && sTab.length > 0) {
	iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
}

else {
	iActiveTab = 0;
}

$("#tabs").tabs({

	active : iActiveTab,

	beforeLoad : function(event, ui){
		// Update tab parameter in the url
		$.address.parameter("tab", ui.tab[0].childNodes[1].dataset.tab);
		sTab = $.address.parameter("tab");
		// Update current active Tab
		iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
		$.pgsi.progressBar.start();
	},

	load: function(event, ui) {
		if (iActiveTab === 0) {
			$(".btn").button();

			$("#link-edit-personalinfo").click(function(e) {
				e.preventDefault();
				pgsi.util.actiondialog.launchActionDialog("insUpdRecipient", pgsi.pages.business.dialogSettings.insUpdRecipient( parseInt($('#recipient-id').val(),10), $.pgsi.locale.get("pages.member.dialog.title.edit",$('#recipient-name').text()),"",3,""," "));
			});

			$("#addRisk").click(function(e) {
				var oRequest = new RecipientMaintenanceRequest({
					recipient : {
						id : parseInt($('#business-id').val()),
						personTypeValue : parseInt($("#personType").val()),
						pepStatusValue : parseInt($("#personPepStatus").val()),
						personStatusValue : parseInt($("#status-value").val()),
						modelAction : "NONE",
						version : pgsi.version.versionRecipient,
						risk : pgsi.pages.risk.view.fillObject(parseInt(oResponse.recipientList[0].risk.version))
					}
				});
				pgsi.util.actiondialog.launchActionDialog("insUpdRecipientRisk", pgsi.pages.business.dialogSettings.insUpdRecipientRisk($("#business-name").val(), oRequest));
			});

			$('#status-template').on("click", "a.active, a.deactivate, a.delete", function(e) {
				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(oResponse.recipientList[0].sdnstatus)) {
					return;
				}

				var refreshPage = function() {
					$.pgsi.ajax.post({
						sUrl       : "api/recipient/fetch",
						oRequest   : { id : $("#business-id").val() },

						fnCallback : function(oResponse) {
							pgsi.pages.recipient.view.fnFillFields(oResponse);
						}
					});
				};

				if($(this).hasClass('active')){

					pgsi.util.page.fnUpdateStatus('api/recipient/fetch',parseInt($('#recipient-id').val(),10),'recipient',1,refreshPage,"Activate Recipient for "+ $('#recipient-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Recipient")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.recipient.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
				}

				if($(this).hasClass('deactivate')){

					pgsi.util.page.fnUpdateStatus('api/recipient/fetch',parseInt($('#recipient-id').val(),10),'recipient',2,refreshPage,"Deactivate Recipient for "+ $('#recipient-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Recipient")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.recipient.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
				}

				if ($(this).hasClass('delete')) {
					var oRequest = new RecipientMaintenanceRequest({recipient : {firstName: $("#business-name").val(), id : parseInt($('#recipient-id').val(),10), modelAction : "DELETE"}});

					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess == true) {
								$.pgsi.pageLoader.load({
								url : "recipient",
								$content : $("#load"),
								bStartProgressBar : false
							});
						}

						else {
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
					}

					pgsi.util.actiondialog.launchActionDialog(
						"deleteDialog",
						pgsi.pages.business.dialogSettings.deleteDialog(
							"api/recipient/delete",
							oRequest,
							$.pgsi.locale.get("pages.recipient.dialog.title", oRequest.recipient.firstName),
							fnCallBack,
							$.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.recipient"))
							));
				}
			});


			pgsi.pages.recipient.view.fnFillFields(oResponse);
		}
    }
});

</script>

</sec:authorize>