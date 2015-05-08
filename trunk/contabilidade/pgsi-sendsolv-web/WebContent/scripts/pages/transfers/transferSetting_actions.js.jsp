<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.transfer.dialogSettings = {

		//========================= DIALOG EDIT LOCATION ============================
		insUpdTransfer : function (iIdMember, sTitle,iPersonType,iTransferId, sNameIDRecipient,iStatusRecipient, iRecipientId) {

			if (!$.pgsi.isNullOrUndefined($.address.parameter('transferId'))){
				var sTitle = $.pgsi.locale.get("pages.transfer.dialog.title.edit",$('#member-name').text())
			}else{
				var sTitle = $.pgsi.locale.get("pages.transfer.dialog.title.add",$('#member-name').text())
			}
			return {

				title : sTitle,

				width : 1264,

				buttons : (function () {

					var oButtons = {};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

						$("#action-dialog").dialog('close');
					};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.pages.save")] = {

						id : "dialog-submit",

						text: $.pgsi.locale.get("commons.pages.save"),

						click : function() {
							var fnCallbackSave = function(oResponse) {

							if (oResponse.operationSuccess == true) {

								if($('#methodTransfer').val() == '4'){
									pgsi.pages.transfer.create.form.fillAddressRecipientRequestAndAjaxCall($('#recipientParticipantId').val());
								}
								$("#action-dialog").dialog('close');
								$.pgsi.ajax.post({
									sUrl 		: "api/member/fetch",
									oRequest 	: {id : parseInt($.address.parameter('memberId'),10)},
									fnCallback  : function(oResponse) {
										if(oResponse.operationSuccess == true){

											$("#person_info").find(".col-info #email-container").text("");
											pgsi.pages.member.view.fnFillFields(oResponse);
											pgsi.version.versionMember = oResponse.memberList[0].version;


										}else{
											pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
										}
									}
								});
							}else {
								fnCallBackError = function(oResponse) {

									$("#action-dialog-Error").dialog('close');

								}

								pgsi.pages.sendsolv.fnDialogMessageError(sPurl,{id:parseInt($.address.parameter('memberId'),10)},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("commons.pages.employment")),false);
								}
							}

							pgsi.pages.transfer.create.form.fnAjaxCallInsertUpdateMemberTransfer($('#transfer-id').val(),fnCallbackSave);
						}
					}

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("member/dialogCreate?memberId="+iIdMember+"&userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');

						$('.buttons-form').hide();
						$('.insert').addClass('hide');
						$('.update').removeClass('hide');
						if (!$.pgsi.isNullOrUndefined(sNameIDRecipient)) {
							var name = sNameIDRecipient.split('(');
							$('.update').find('.recipientName').text(name[0]);
						}

						var oRecipientInfo=[];
						if(iStatusRecipient != 1){
							$('.update').find('.recipientStatus').text('No active recipient found.');
						}else{
							$('.update').find('.recipientStatus').text('');
						}

						if ($.pgsi.isNullOrUndefined(iRecipientId)) {
							iRecipientId = $("#recipientId").val();
						}

						oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : iRecipientId},"api/recipient/fetch",3);
						$("#recipientId").val(iRecipientId);

						if(!$.pgsi.isNullOrUndefined(oRecipientInfo)){
							if (oRecipientInfo.length > 0) {
								$('.parent.spacer').text(oRecipientInfo[0]);
								if (oRecipientInfo[3] != 1) {
									$('.update').find('.recipientStatus').text('No active recipient found.');
								}else{
									$('.update').find('.recipientStatus').text('');
								}

								$('.update').find('.recipientName').text(oRecipientInfo[0]);

							}
						}


						if (($.pgsi.isNullOrUndefined($.address.parameter('transferId')))&&($.pgsi.isNullOrUndefined(iTransferId))){

							if(oRecipientInfo.length == 0){
								oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : iRecipientId},"api/recipient/fetch",3);
								$("#recipientId").val(iRecipientId);
							}

							$('#dialog-insert').removeClass('hide')
							$('#dialog-edit').addClass('hide')

							<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
								$('#dialog-insert .parent').text(' '+oRecipientInfo[0]+' (#'+pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1])+')');
								$('#dialog-edit .recipientName').text(' '+oRecipientInfo[0]+' (#'+pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1])+')');
							</sec:authorize>

							<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
								$('#dialog-insert .parent').text(' '+oRecipientInfo[0]);
								$('#dialog-edit .recipientName').text(' '+oRecipientInfo[0]);
							</sec:authorize>

							$('#dialog-insert .form-link').attr("title",' view '+oRecipientInfo[0]);

							$('#dialog-insert .form-link').click(function(e){
								$("#action-dialog").dialog('close');
								$.pgsi.pageLoader.load({
									url : "recipient/view?recipientId="+oRecipientInfo[4]+"&tab=info",
									$content : $("#load"),
									bStartProgressBar : false
								});

							});

						}
						<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
							$('#dialog-edit .recipientName').text(' '+oRecipientInfo[0]+' (#'+pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1])+')');
						</sec:authorize>

						<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
							$('#dialog-edit .recipientName').text(' '+oRecipientInfo[0]);
						</sec:authorize>

						$('#recipientParticipantId').val(oRecipientInfo[1]);

						$('#recipientParticipantId').focusout(function(e){

							e.preventDefault();
							var oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({stringId : $(this).val().replace('-', '')},"api/recipient/fetch",3);
							if(!$.pgsi.isNullOrUndefined(oRecipientInfo)){
								if(oRecipientInfo.length > 0){
									$('.parent.spacer').text(oRecipientInfo[0]);
									$('#recipientParticipantId').val($(this).val());
									$('#recipientId').val($(this).val());
									$('.update').find('.recipientName').text(' '+oRecipientInfo[0]+' (#'+pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1])+')');

								}
							}else{
								$('.update').find('.recipientName').text('');
							}
						});

						$('.form-link').click(function(e){
							$("#action-dialog").dialog('close');
							$.pgsi.pageLoader.load({
								url : "recipient/view?recipientId="+oRecipientInfo[4]+"&tab=info",
								$content : $("#load"),
								bStartProgressBar : false
							});

						});

						$.each( $('#locationName .employment').find('input'), function( i, val ) {

							if (!$.pgsi.isNullOrUndefined($('#location-id').val())){
								var iLocation = $(this).val().split('|');
								if(parseInt(iLocation[0]) == parseInt($('#location-id').val())){
									$(this).prop('checked', true);
									$('#employmentInfo-id').val(iLocation[1])
									$('#location-id').val(iLocation[0]);
									$('#location-name').val(iLocation[2]);

									var oLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(iLocation[0]);
									pgsi.pages.transfer.create.payPreparationDays = oLocation[4];
									$('.frequencyBasedEventCalendarList').empty();
									$('.frequencyBasedEventCalendarList').append(oLocation[2]);
									$('.contact-info2 input:eq(0)').prop('checked', true);
								}
							}

							if ($('#locationName .employment').find('input').length == 1) {
								$(this).addClass('hide');
								$(this).prop('checked', true);
								var oEmployment = $(this).val().split('|');
								if (($.pgsi.isNullOrUndefined($.address.parameter('transferId')))||($.pgsi.isNullOrUndefined(iTransferId))){
									pgsi.pages.transfer.create.form.fnAjaxCallProductPlan(oEmployment[0]);
								}

								$('#employmentInfo-id').val(oEmployment[1])
								$('#location-id').val(oEmployment[0]);
								$('#location-name').val(oEmployment[2]);
								var oLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(oEmployment[0]);
								pgsi.pages.transfer.create.payPreparationDays = oLocation[4];
								$('.frequencyBasedEventCalendarList').empty();
								$('.frequencyBasedEventCalendarList').append(oLocation[2]);
								$('.contact-info2 input:eq(0)').prop('checked', true);
							}
						});

						$('.employment input').change(function () {

							//begin selected
							if(!$.pgsi.isNullOrUndefined($('.employment input').val())){
								var oEmployment = $(this).val().split('|');

								$('#destcountry').empty();
								$('#destcountry').append("<option></option>");
								$('#destcountry').selectmenu('refresh').nextAll(".ui-selectmenu-button");
								$('#destcountry').addClass('required').removeClass('hide').prop("placeholder","*");
								pgsi.util.page.form.fnInitSelectmenu($('#destcountry'));

								$('#currency').empty();
								$('#currency').append("<option></option>");
								$('#currency').selectmenu('refresh').nextAll(".ui-selectmenu-button");
								$('#currency').addClass('required').removeClass('hide').prop("placeholder","*");
								pgsi.util.page.form.fnInitSelectmenu($('#currency'));

								$('#methodTransfer').empty();
								$('#methodTransfer').append("<option></option>");
								$('#methodTransfer').selectmenu('refresh').nextAll(".ui-selectmenu-button");
								$('#methodTransfer').addClass('required').removeClass('hide').prop("placeholder","*");
								pgsi.util.page.form.fnInitSelectmenu($('#methodTransfer'));

								$('#payer').empty();
								$('#payer').append("<option></option>");
								$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");
								$('#payer').addClass('required').removeClass('hide').prop("placeholder","*");
								pgsi.util.page.form.fnInitSelectmenu($('#payer'));

								$('#pricing').empty();
								$('#pricing').append("<option></option>");
								$('#pricing').selectmenu('refresh').nextAll(".ui-selectmenu-button");
								$('#pricing').addClass('required').removeClass('hide').prop("placeholder","*");
								pgsi.util.page.form.fnInitSelectmenu($('#pricing'));

								$('#planCategoryId').empty();
								$('#planCategoryId').append("<option></option>");
								$('#planCategoryId').selectmenu('refresh').nextAll(".ui-selectmenu-button");
								$('#planCategoryId').addClass('required').removeClass('hide').prop("placeholder","*");
								pgsi.util.page.form.fnInitSelectmenu($('#planCategoryId'));

								pgsi.pages.transfer.create.form.fnAjaxCallProductPlan(oEmployment[0]);

								$('#employmentInfo-id').val(oEmployment[1])
								$('#location-id').val(oEmployment[0]);
								$('#location-name').val(oEmployment[2]);
								var oLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(oEmployment[0]);
								pgsi.pages.transfer.create.payPreparationDays = oLocation[4];

								$('.frequencyBasedEventCalendarList').empty();
								$('.frequencyBasedEventCalendarList').append(oLocation[2]);
								$('.contact-info2 input:eq(0)').prop('checked', true);
							}
						});

						if (!$.pgsi.isNullOrUndefined($('#effectiveStartDate').val())){

							var oPayDate = null;
							var oEffectiveStartDate = $.pgsi.date.getDateObj($('#effectiveStartDate').val(), "UTC");
							var iIndex = 0;

							// The selected pay date will be the first one higher than the effective date
							$.each($('.contact-info2').find('input'), function( i, elem ) {
								oPayDate = $.pgsi.date.getDateObj((new Date($(elem).parent().find('label').text())), "UTC");

								iIndex = i;

								if (oPayDate > oEffectiveStartDate) {
									return false;
								}

							});

							$('.contact-info2:eq(' + iIndex + ')').find('input[type="radio"]').prop('checked', true);
						}

						if ($('#methodTransfer').val() == '4') {
							pgsi.pages.transfer.create.fnFetchRecipientByAddress({stringId : $('#recipientParticipantId').val().replace('-','')});
						}

					});
				}
			}
		},

		//========================= DIALOG OPTION TRANSFER SETTINGS ============================
		optionTransfer : function (iId, sTitle,iPersonType,sNameIDRecipient,iRecipientParticipantId, iRecipientId, iStatusRecipient) {

			return {
				title : $.pgsi.locale.get("pages.transfer.dialog.option.title",$('#member-name').text()),
				width : 1074,

				buttons : (function () {

					var oButtons = {};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

						$("#action-dialog").dialog('close');
					};

					// Confirm Button
					oButtons[$.pgsi.locale.get("pages.member.view.addtransfer")] = {

						id : "dialog-submit",

						text: $.pgsi.locale.get("pages.member.view.addtransfer"),

						click : function() {
							var bValidForm = pgsi.pages.transfer.create.form.validatorInput.form();

							if (!$.pgsi.isNullOrUndefined($.address.parameter('member'))) {
								iId = parseInt($.address.parameter('member'),10);
							}

							if ($('.ui-dialog-buttonset:eq(0) .ui-button:eq(1) .ui-button-text').attr('name') === "addRecipient") {
								pgsi.util.actiondialog.launchActionDialog("insUpdRecipient", pgsi.pages.business.dialogSettings.insUpdRecipient( 0,$.pgsi.locale.get("pages.recipient.dialog.title.add","Member " + $('#member-name').text()),"",3,"",true));
							}

							else {
								if (!$.pgsi.isNullOrUndefined(iRecipientId)) {
									$('#action-dialog').dialog('close');
									pgsi.util.actiondialog.launchActionDialog(
										"insUpdTransfer",
										pgsi.pages.transfer.dialogSettings.insUpdTransfer(
											iId,
											"",
											3,
											null,
											sNameIDRecipient,
											iStatusRecipient,
											iRecipientId
										)
									);
								}
							}
						}
					}

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("member/addTransferDialog?transferId="+iId+"&userId=" + pgsi.settings.userContext.userId, function() {


						actionDialog.dialog('open');
						pgsi.pages.transfer.create.form.fnInitFormDialog();
						$('input.first').change(function () {

							if($('.first.spacer.search').is(":checked")){
								$('#recipientParticipantId').addClass('required').attr('name' ,'recipientId');
							}

							else{

								$('#recipientParticipantId').removeClass('required').attr('name' ,'');
								if ($(this).is(":checked")) {

									sNameIDRecipient = $(this).attr("data-recipient-name");
									iStatusRecipient = $(this).attr("data-recipient-statusValue");
									iRecipientId = $(this).val();

									if ($(this).attr("data-recipient-participantId") && $(this).attr("data-recipient-participantId").length > 0){
										iRecipientParticipantId = $(this).attr("data-recipient-participantId");
									}
								}
							}
						   if(parseInt($(this).val(),10) === 0){
								$('.ui-dialog-buttonset:eq(0) .ui-button:eq(1) .ui-button-text').text('Add New Recipient >>').attr('name' ,'addRecipient')
						   }else{
								$('.ui-dialog-buttonset:eq(0) .ui-button:eq(1) .ui-button-text').text($.pgsi.locale.get("pages.member.view.addtransfer")).attr('name' ,'');
						   }

						});

						$("#recipientParticipantId").focus(function(e){
							$('#recipientParticipantId').addClass('required').attr('name' ,'recipientId');
							$('.search').prop('checked', true);
							$('.ui-dialog-buttonset:eq(0) .ui-button:eq(1) .ui-button-text').text($.pgsi.locale.get("pages.member.view.addtransfer")).attr('name' ,'');
						});

						$("#recipientParticipantId").focusout(function(e){
							e.preventDefault();

							if ($(this).val().length === 0) {
								return;
							}

							var oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({stringId : $(this).val().replace('-', '')},"api/recipient/fetch",3);

							if(!$.pgsi.isNullOrUndefined(oRecipientInfo)){
								if(oRecipientInfo.length > 0){
									$('.parent.spacer').text(oRecipientInfo[0]);
									$('.search').prop('value',$(this).val());
									$('.search').prop('checked', true);
									$('#recipient-id').addClass('required').attr('name' ,'recipientId');

									iRecipientId 	 = oRecipientInfo[4];
									sNameIDRecipient = oRecipientInfo[0];
									iStatusRecipient = oRecipientInfo[3];
								}else{
									$('.newline label.parent.spacer').text('');
								}
							}
						});
						$('#recipientParticipantId').removeClass('required').attr('name' ,'');
						$('.ui-dialog-buttonset:eq(0) .ui-button:eq(1) .ui-button-text').text('Add New Recipient >>').attr('name' ,'addRecipient')
					});
				}
			}
		},
	}
</script>

</sec:authorize>