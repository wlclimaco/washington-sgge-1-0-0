<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

	<script type="text/javascript">
		/**
		 * @namespace pgsi.pages.sdn
		 * @description The main namespace for the SDN View Page.
		 * @author Anke Doerfel-Parker
		 */
		pgsi.pages.sdn.view = {


			percentMask : "'alias': 'percent', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': '', 'suffix': '%' 'placeholder': '0'",
			fnCreateTableSDNHistory : function(oHistory){
				var sReturn = "";
				var sClass = 'odd';
				if (!$.pgsi.isNullOrUndefined(oHistory)){
					for(var i=0;i<oHistory.length;i++){
						var sNote = "";
						if (!$.pgsi.isNullOrUndefined(oHistory[i].noteText)){sNote = oHistory[i].noteText}
						if(i%2 == 0){sClass = 'odd';}else{sClass = 'even';}
						sReturn = sReturn + '<tr class="'+sClass+'" role="row">'+
							  '<td class="status-sdn sorting_1" colspan="">'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",oHistory[i].sdnStatus)+'</td>'+
							  '<td class="sdn-date">'+$.pgsi.date.format(new Date(oHistory[i].createDateUTC), "mm/dd/yy", null)+'</td>'+
							  '<td class="sdn-author">'+oHistory[i].createUser+'</td>'+
							  '<td class="sdn-reason">'+sNote+'</td></tr>';
					}
					$('table#data_list tbody').append(sReturn);
				 }
			},
			fnStatusPersonBusiness : function(oResponse){
				var iStatusValue;
				if($.address.parameter("type") == "member"){
					pgsi.util.page.status.view.fnFillStatusFlags(oResponse.memberList[0]);
					$('#status-template .newline ').text('Member ID');
					$('#status-id').text($.address.parameter("id"));
					iStatusValue = oResponse.memberList[0].statusValue;
					$('.view').attr('href','#/member/view?memberId='+$.address.parameter("id"));
				}else if($.address.parameter("type") == "recipient"){
					pgsi.util.page.status.view.fnFillStatusFlags(oResponse.recipientList[0]);
					$('#status-template .newline ').text('Recipient ID');
					$('#status-id').text($.address.parameter("id"));
					iStatusValue = oResponse.recipientList[0].statusValue;
					$('.view').attr('href','#/recipient/view?recipientId='+$.address.parameter("id"));
				}else if($.address.parameter("type") == "contact"){
					pgsi.util.page.status.view.fnFillStatusFlags(oResponse.liaisonList[0]);
					$('#status-template .newline ').text('Contact ID');
					$('#status-id').text($.address.parameter("id"));
					iStatusValue = oResponse.liaisonList[0].statusValue;
				}else if($.address.parameter("type") == "organization"){
					pgsi.util.page.status.view.fnFillStatusFlags(oResponse.organizationList[0]);
					$('#status-template .newline ').text('Organization ID');
					$('#status-id').text($.address.parameter("id"));
					iStatusValue = oResponse.organizationList[0].statusValue;
					$('.view').attr('href','#/organization/view?tab=info&organizationId='+$.address.parameter("id"));
				}else if($.address.parameter("type") == "location"){
					pgsi.util.page.status.view.fnFillStatusFlags(oResponse.locationList[0]);
					$('#status-template .newline ').text('Location ID');
					$('#status-id').text($.address.parameter("id"));
					iStatusValue = oResponse.locationList[0].statusValue;
					$('.view').attr('href','#/location/view?tab=info&locationId='+$.address.parameter("id"));
				}

				//Person status
				if (iStatusValue === 1) {
					$('.active').hide();
					$('.deactivate').show();
				} else if (iStatusValue === 2) {
					$('.deactivate').hide();
					$('.active').show();
				} else if (iStatusValue === 3) {
					$('.deactivate').hide();
					$('.active').show();
				} else if (iStatusValue === 4) {
					$('.deactivate').hide();
					$('.active').show();
				}


			},
			fnCreateTableMoneyTransfer : function(oMoneyTransfer){
				if (!$.pgsi.isNullOrUndefined(oMoneyTransfer)){
					if(($.address.parameter("type") == "location")||($.address.parameter("type") == "organization")){
						oMoneyTransfer = oMoneyTransfer.moneyTransferBatchList;
						$('#batches').append(pgsi.pages.sdn.view.fnCardBatchTransaction(oMoneyTransfer,'batch'));
					}else{
						oMoneyTransfer = oMoneyTransfer.moneyTransferList;
						$('#batches').append(pgsi.pages.sdn.view.fnCardBatchTransaction(oMoneyTransfer,'transaction'));
					}
				}
			},
			fnFillFields : function() {
				$("#sdn-name").text(oEntity.nameAndId);
				// Status
				$('#status-template .newline').text(
						$.pgsi.locale.get("pages.member.view.id"));
				$('#status-template #status')
						.text(
								$.pgsi.enums
										.internationalizeByLabel(
												"com.prosperitasglobal.sendsolv.model.StatusEnum",
												oEntity.status));

				// Title
				$("#sdn-title").text(
						$.pgsi.locale.get("commons.pages.sdnreport",
								oEntity.nameAndId));
				$.pgsi.pageLoader.title($.pgsi.locale.get(
						"commons.pages.sdnreport", oEntity.nameAndId));

				$("#participant-id").text(oEntity.participantId);
				$(".sdn-status")
						.text(
								$.pgsi.enums
										.internationalizeByLabel(
												"com.prosperitasglobal.sendsolv.model.StatusEnum",
												oEntity.personStatus))
				$("#status-value").val(oEntity.personStatusValue);

				$(".pepStatus")
						.text(
								$.pgsi.enums
										.internationalizeByLabel(
												"com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum",
												oEntity.pepStatus));
				$("#personPepStatus").val(oEntity.pepStatusValue);

			},

			fnSetPersonFields : function(oEntity) {
				//mask participant id MXX-00000
				var input = document.createElement("input");
				input.type = "text";
				input.value = oEntity.participantId;
				var $input = $(input);
				$input.mask('***-99999');
				oEntity.publicId = $input.val();

				// Name fields
				oEntity.name = oEntity.firstName + " " + oEntity.lastName;
				var sSuffix = "";
				if (!$.pgsi.isNullOrUndefined(oEntity.suffix)
						&& !$.pgsi.isNullOrUndefined(oEntity.suffix.code)) {
					sSuffix = " " + oEntity.suffix.code
				}
				//Middle Name
				var sMiddleName = "";
				if (!$.pgsi.isNullOrUndefined(oEntity.middleName)) {
					sMiddleName = oEntity.middleName + " ";
				}
				//Mother's Maiden Name
				var sMotherName = "";
				if (!$.pgsi.isNullOrUndefined(oEntity.nameList[0])
						&& !$.pgsi
								.isNullOrUndefined(oEntity.nameList[0].otherName)) {
					sMotherName = " " + oEntity.nameList[0].otherName;
				}
				oEntity.fullName = oEntity.firstName + " " + sMiddleName
						+ oEntity.lastName + sMotherName + sSuffix;

				oEntity.status = oEntity.personStatus;
				oEntity.nameAndId = oEntity.name + " (" + oEntity.publicId
						+ ")";
			},

			fnSetBusinessFields : function(oEntity) {
				//TODO - add Public Id for businesses

				//mask participant id MXX-00000
				// 				var input = document.createElement("input");
				// 				input.type = "text";
				// 				input.value = oEntity.participantId;
				// 				var $input = $(input);
				// 				$input.mask('***-99999');
				//				oEntity.publicId = $input.val();
				oEntity.publicId = oEntity.id;

				// Name fields
				var sSuffix = "";

				// for location, add org name?
				oEntity.fullName = oEntity.name;

				if (!$.pgsi.isNullOrUndefined(oEntity.parentOrganizationName)) {
					oEntity.name += " (" + oEntity.parentOrganizationName + ")";
				}
				oEntity.nameAndId = oEntity.name + " (" + oEntity.publicId
						+ ")";
			},
			fnCardBatchTransaction : function(oResponse,type) {

				var sReturn = "";
				var sStatus = "PENDING_APPROVAL";

				for(var i=0;i<oResponse.length;i++){
					if(oResponse[i].statusList.length > 1){
						sStatus = oResponse[i].statusList[oResponse[i].statusList.length - 2].status
					}else{
						sStatus = "PENDING_APPROVAL";
					}
					if(type == "transaction"){

						sReturn = sReturn + 	'<div class="tx-descr">'+
							'<div class="text first">'+
								'<a href="transaction_view.html" title="View Transaction #['+oResponse[i].id+']"><b>Transaction #'+oResponse[i].id+'</b></a> to <a href="recipient_view.html" title="View '+oResponse[i].moneyTransferDetail.member.firstName+' '+oResponse[i].moneyTransferDetail.member.lastName+'(#'+oResponse[i].moneyTransferDetail.member.participantId+')"> '+oResponse[i].moneyTransferDetail.member.firstName+' '+oResponse[i].moneyTransferDetail.member.lastName+'(#'+oResponse[i].moneyTransferDetail.member.participantId+')</a>'+
							'</div>'+
							'<div class="text first">'+
								'<a href="location_view.html" title="View Fremont Location">'+oResponse[i].transferSetting.employmentInfo.locationName+'</a>, <a href="organization_view.html" title="View Nebraska Premium Meatpacking, Inc">'+oResponse[i].transferSetting.employmentInfo.organizationName+'</a>'+
							'</div>'+
							'<div class="text first">'+
								'<b>On Hold</b>'+
							'</div>'+
							'<div class="text first">'+
								'<b>USD '+pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.sdn.view.percentMask,oResponse[i].transferSetting.transferAmount)+' ('+oResponse[i].transferSetting.productPlanApplicability.currency.code+' '+pgsi.util.page.fnInsertMask(pgsi.pages.sdn.view.percentMask,oResponse[i].transferSetting.customFeeList[0].value)+')</b> on <b>'+$.pgsi.date.format(new Date(oResponse[i].transferSetting.effectiveStartDate), "mm/dd/yy", null)+'</b>'+
							'</div>'+
							'<div class="text first space-bottom"></div>'+
							'<label class="first">Decision</label><input type="radio" value="A" class=""><label>Return to <b>'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",sStatus)+'</b>'+
							'</label><input type="radio" value="C"><label>Cancel</label><label class="first">Reason</label>'+
							'<textarea class="width-long"></textarea>'+
						'</div>'
					}else{
						sReturn = sReturn + 	'<div class="tx-descr">'+
							'<div class="text first">'+
								'<div class="text first">'+
									'<a title="View Batch #'+oResponse[i].id+'" href="payment/batches_view?batchesId='+oResponse[i].id+'"><b>Batch #'+oResponse[i].id+'</b></a>'+
								'</div>'+
								'<div class="text first"><b>On Hold</b></div>'+
								'<div class="text first"><b>Amount</b> <b>USD '+pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.sdn.view.percentMask,oResponse[i].originAmount.amount)+'</b></div>'+
								'<div class="text first">Est. Transfer Date<b> '+$.pgsi.date.format(new Date(oResponse[i].transferDate), "mm/dd/yy", null)+'</b></div>'+
								'<div class="text first space-bottom"></div>'+
								'<label class="first">Decision</label>'+
								'<input class="" type="radio" value="A">'+
								'<label>Return to<b> '+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum",sStatus)+'</b></label>'+
								'<input type="radio" value="C">'+
								'<label>Cancel</label>'+
								'<label class="first">Reason</label>'+
								'<textarea class="width-long"></textarea>'+
							'</div>'+
						'</div>'

					}
				}
				return sReturn;
			}

		};
	</script>

</sec:authorize>