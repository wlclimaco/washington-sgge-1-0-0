<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The init namespace for the Member View Page.
 * @author Washington Costa
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
$(document).ready(function()
{
	//Attach Links to tabs
	$("#personInfo").attr('href', "member/view/info?memberId=" + oResponse.memberList[0].id);
	$('#recipientList').attr('href',"recipient/fetchRecipientByMember?memberId=" + oResponse.memberList[0].id);
	$('#transactionList').attr('href',"transaction/member?memberId=" + oResponse.memberList[0].id);

	$.pgsi.storage.delete('member-view');
	$.pgsi.storage.set('member-view',oResponse.memberList[0])

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
		beforeLoad : function(event, ui) {
			// Setting correct tab parameter to the url
			$.address.parameter("tab", ui.tab[0].childNodes[1].dataset.tab);
			sTab = $.address.parameter("tab");

			$.pgsi.progressBar.start();

			// update active tab
			iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
		},

		load: function(event, ui) {

			if (iActiveTab === 0) {

				$("#show-pin, #hide-pin").click(function(e) {
					e.preventDefault();

					if (pgsi.util.page.fnIsSDNFlagged(oResponse.memberList[0].sdnstatus)) {
						return;
					}

					$("#pin-section").find(".pin").toggleClass("hide");
				});

				// Show all the security question & answer that the user has.
				$("#show-secquestion, #hide-secquestion").click(function(e) {
					e.preventDefault();

					if (!$.pgsi.isNullOrUndefined(oResponse.memberList[0].securityAnswerList))
					{
						var questionsAndAnswers = "";

						for (i =0;i < oResponse.memberList[0].securityAnswerList.length;i++) {

							if (!$.pgsi.isNullOrUndefined(oResponse.memberList[0].securityAnswerList[i].answerText) &&
								!$.pgsi.isNullOrUndefined(oResponse.memberList[0].securityAnswerList[i].securityQuestion) &&
								!$.pgsi.isNullOrUndefined(oResponse.memberList[0].securityAnswerList[i].securityQuestion.securityQuestionKey))
							{
								questionsAndAnswers += "<div>" + $.pgsi.locale.get(oResponse.memberList[0].securityAnswerList[i].securityQuestion.securityQuestionKey) + "</div>";
								questionsAndAnswers += "<div>" + oResponse.memberList[0].securityAnswerList[i].answerText + "</div>";
							}
						}

						$("#secquestion-section").find("#person-sec-question-answer").text("");
						$("#secquestion-section").find("#person-sec-question-answer").append(questionsAndAnswers);
					}

					$("#secquestion-section").find(".secquestion").toggleClass("hide");
				});

				// Call generate new pin dialog
				$("#pin-generate").click(function(e){
					e.preventDefault();

					var oRequest;

					$.pgsi.ajax.post({
					    sUrl       : "api/member/fetch",
						oRequest   : { id : $("#business-id").val() },

						fnCallback : function(oResponse) {

							oRequest = new MemberMaintenanceRequest({
								member: oResponse.memberList[0]
							});

							oRequest.member.risk = { modelAction: "NONE" };
							oRequest.member.employmentInfoList = [];
							oRequest.member.transferSettingList = [];

							oRequest.member.pinNumber = null;
							oRequest.member.modelAction = "UPDATE";

							pgsi.util.actiondialog.launchActionDialog(
								"regeneratePin",
								pgsi.pages.business.dialogSettings.regeneratePin(
									$.pgsi.locale.get("pages.member.pin.dialog.title", $("#business-name").val()),
									oRequest
								)
							);
						}
					});

				});

			$("#edit-risk").click(function(e){

				var oMember = $.pgsi.storage.get('member-view');

				oMember.id = parseInt($('#business-id').val());
				oMember.personTypeValue = parseInt($("#personType").val());
				oMember.modelAction = "NONE";
				oMember.version = pgsi.version.versionMember;
				oMember.risk = pgsi.pages.risk.view.fillObject();

				var oRequest = new MemberMaintenanceRequest({
					member : oMember
				});

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					if ($("#personPepStatus").length > 0) {
						oRequest.member.pepStatusValue = parseInt($("#personPepStatus").val());
					}
				</sec:authorize>

				pgsi.util.actiondialog.launchActionDialog("insUpdMemberRisk", pgsi.pages.business.dialogSettings.insUpdMemberRisk($("#business-name").val(), oRequest));
			});

			$("#link-edit-personalinfo").click(function(e) {
				e.preventDefault();
				pgsi.util.actiondialog.launchActionDialog("insUpdRisk", pgsi.pages.business.dialogSettings.insUpdMember( parseInt($('#member-id').val(),10),$('#member-name').text(),"",3,""," "));
			});

			$('#status-template').on("click", "a.active, a.deactivate, a.delete", function(e) {

				e.preventDefault();

				if (pgsi.util.page.fnIsSDNFlagged(oResponse.memberList[0].sdnstatus)) {
					return;
				}

				var refreshPage = function() {
					$.pgsi.ajax.post({
						sUrl       : "api/member/fetch",
						oRequest   : { id : $("#business-id").val() },

						fnCallback : function(oResponse) {

							pgsi.pages.member.view.fnFillFields(oResponse);
						}
					});
				};

				if ($(this).hasClass('active')) {

					pgsi.util.page.fnUpdateStatus('api/member/fetch',parseInt($('#member-id').val(),10),'member',1,refreshPage,"Activate Member for "+ $('#member-name').text(), "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Member")+"<br>" +$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.activate"))+"<span>");
					pgsi.pages.member.view.fnHidePinNumber();
				}

				if($(this).hasClass('deactivate')){

					pgsi.util.page.fnUpdateStatus('api/member/fetch',parseInt($('#member-id').val(),10),'member',2,refreshPage,"Deactivate Member for "+ $('#member-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Member")+"<br>"+$.pgsi.locale.get("pages.person.dialog.status.information",$.pgsi.locale.get("pages.view.deactivate"))+"<span>");
					pgsi.pages.member.view.fnHidePinNumber();
				}

				if($(this).hasClass('delete')){
					var oRequest = new MemberMaintenanceRequest({
						member : {
							id : parseInt($('#member-id').val(),10),
							modelAction : "DELETE",
							firstName : $("#business-name").val()
						}
					});

					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess == true) {

							$.pgsi.pageLoader.load({
								url : "member",
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
							"api/member/delete",
							 oRequest,
							 $.pgsi.locale.get("pages.member.dialog.title", oRequest.member.firstName),
							 fnCallBack,
							 $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.member"))
						 ));
				}

			});

			$(".btn").button();

			//remember location name
			if  (pgsi.location.locationId !== null){

				var fnCallBack = function(oResponse) {

					if(oResponse.organizationList.length > 0){
						$('.view-organization').text(' '+ oResponse.organizationList[0].name +' ');
						$('.view-organization').attr("href", "organization/view?tab=info&organizationId="+oResponse.organizationList[0].id);
					}else{
						$('.view-organization').text('');
					}
				};

				$('.view-Location').text(' (' + pgsi.location.locationName +' ) ');
				$('.view-Location').attr("href", 'location/view?locationId='+pgsi.location.locationId);

				if((pgsi.location.organizationId != "") || (pgsi.location.organizationId != null)){
					pgsi.pages.member.create.fnFetchOrganizationById(parseInt(pgsi.location.organizationId,10),fnCallBack);
				}
				$(".remembered-location").show();
				$('#location-id').val(pgsi.location.locationId);
				$('#location').val(pgsi.location.locationName);
				$('#organizationId').val(pgsi.location.organizationId);
				$('.remembered-location').removeClass('hide');
			}

			//remove remember
			$(".remembered-location").find('.remove').click(function(e) {
				$(".remembered-location").hide();
				pgsi.location = {locationId:null, locationName:null, organizationId:null};
			});

			pgsi.pages.member.view.fnFillFields(oResponse);
			}
		}
	});

});
</script>

</sec:authorize>