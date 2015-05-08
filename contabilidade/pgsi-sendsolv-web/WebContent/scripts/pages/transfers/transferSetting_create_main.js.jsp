<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
pgsi.pages.transfer.create = {

		payPreparationDays : {},

		form :{

	   		getLatestPPDBeforePayDate : function(aPreparationDays, oPayDate) {
				var oLatestPPD = $.pgsi.date.getDateObj(aPreparationDays[0].eventDate, "UTC");

				$.each(aPreparationDays, function(i, e) {
					if (oLatestPPD.getTime() < oPayDate.getTime() ){

						if ($.pgsi.date.getDateObj(e.eventDate).getTime() >= oPayDate.getTime()) {
							return false;
						}

						oLatestPPD = $.pgsi.date.getDateObj(e.eventDate);
					}
				});
				var sDatestring = oLatestPPD.getFullYear() + "-" + (oLatestPPD.getMonth() + 1) + "-" +  oLatestPPD.getDate();

				return $.pgsi.date.getEpochTime(sDatestring, "UTC");

			},
			getLatestPDBeforePayDate : function(aPreparationDays, oPayDate) {
				oPayDate = $.pgsi.date.getDateObj(oPayDate, "UTC")
				var oLatestPPD = $.pgsi.date.getDateObj(aPreparationDays[0], "UTC");
				if ((!$.pgsi.isNullOrUndefined(aPreparationDays))&&(aPreparationDays.length > 0)){
					$.each(aPreparationDays, function(i, e) {
						if (oLatestPPD.getTime() < oPayDate.getTime() ){

							if ($.pgsi.date.getDateObj(e).getTime() >= oPayDate.getTime()) {
								oLatestPPD = $.pgsi.date.getDateObj(e);
								return false;
							}

							oLatestPPD = $.pgsi.date.getDateObj(e);
						}
					});
				}else{
					oLatestPPD = oPayDate;
				}
				return  oLatestPPD.getTime();
			},

			/**
			 * Validate the fields required at Organization Form
			 */
			validator : $("#transfer-form").validate({
				ignore : "",

				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						$(value.element).addClass("error");

						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
					});
				},
				rules: {
					recipientId: {
						required: true,
						recipientValid:18
					},
					datePayDay:{
						datePAY_DAYValid:0
					}
				}
			}),

			validatorInput : $("#transfer-input").validate({
				ignore : "",
				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						$(value.element).addClass("error");

						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
					});
				},
				rules: {
					recipientId: {
						recipientValid:18
					}
				}
			}),

			fnInitMaskForm : function() {
				$("input#amount").inputmask();
				$("input#fee").inputmask();
				$("input#recipientParticipantId").inputmask();
				$("input#recipientId").inputmask();
				$("#account").inputmask("Regex");
			},
			fillValidation : function() {

			},

			fnInitFormDialog : function() {
				$('.buttons-form').hide();
				$('.insert').hide();
				$('.update').show();

			},

			fnInitForm : function() {
				pgsi.util.page.form.fnInitForm();
				pgsi.pages.transfer.create.form.setFieldSizes();


				// Load dropdown Enums fields
				$("#schedule").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.TransferTypeEnum"));

				$("#account-type").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.AccountTypeEnum"));

				// fill Mask
				pgsi.pages.transfer.create.form.fnInitMaskForm();

				$(".buttons-form").find("input[type='button']").button();
				$(".buttons-form").find("input[type='submit']").button();

				$("#cancel").click(function(e) {
					$.pgsi.pageLoader.load({
						url : "member",
						$content : $("#load"),
						bStartProgressBar : false
					});
				});

			},

			setFieldSizes : function() {

				$("#residence-button").outerWidth(200);
				$("#country-button").outerWidth(255);
				$("#birth-button").outerWidth(200);
				$("#citizenship-button").outerWidth(200);
				$( "#prefix-button" ).outerWidth(100);
				$("#location").attr("name","location");
				pgsi.pages.address.form.setFieldSizes();

			},

			fillObjectDialog : function(oResponse) {

				var oRecipientInfo = "",
				 oRecipientList = [],
				 oTransfers = oResponse.transferSettingList,
				 sRadioRecipient = "",
				 sRecipientParticipantId = "";

				for (i=0; i< oTransfers.length; i++) {
					if ($.inArray(oTransfers[i].recipientId, oRecipientList) === -1) {
						oRecipientList.push(oTransfers[i].recipientId);
					}
				}

				for (i=0; i<oRecipientList.length; i++) {

					oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : oRecipientList[i]}, "api/recipient/fetch", 3);

					<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
						sRecipientParticipantId = '(#' + oRecipientInfo[1].substring(0,3) + '-' + oRecipientInfo[1].substring(3) + ')';
					</sec:authorize>

					sRadioRecipient = sRadioRecipient
									  + '<div class="newline">'
									  + 	'<input type="radio" data-recipient-name="' + oRecipientInfo[0] + '" data-recipient-statusValue="' + oRecipientInfo[4] + '" data-recipient-participantId="' + sRecipientParticipantId + '" class="first" name="recipient" value="' + oRecipientList[i] + '" >'
									  + 	'<label class="">' + oRecipientInfo[0] + ' ' + sRecipientParticipantId + '</label>'
									  + '</div>';
				}

				$('#recipientListRadio').append(sRadioRecipient);
			},


			fillTransferSettingsCreate : function(oTransferSettings) {

				if (!$.pgsi.isNullOrUndefined(oTransferSettings.planCategory)){
					$('#busdinessPlanId').val(oTransferSettings.planCategory.productPlanId);
				}

				$('#amount').val(oTransferSettings.transferAmount);

				$('#location-id').val(oTransferSettings.employmentInfo.locationId);

				if (!$.pgsi.isNullOrUndefined(oTransferSettings.customFeeList)){
					if (oTransferSettings.customFeeList.length > 0){
						$('#fee').val(oTransferSettings.customFeeList[0].value)
						$('#customFeeId').val(oTransferSettings.customFeeList[0].id);
						$('#customVersion').val(oTransferSettings.customFeeList[0].version);
						$('#fee-exp').val($.pgsi.date.format(new Date(oTransferSettings.customFeeList[0].effectiveEndDate), "mm/dd/yy", null));
					}
				}

				if (!$.pgsi.isNullOrUndefined(oTransferSettings.productPlanApplicability)){

					pgsi.pages.transfer.create.form.fnAjaxCallProductPlan(oTransferSettings.employmentInfo.locationId,oTransferSettings,oTransferSettings.productPlanApplicability.productPlanId,true,oTransferSettings);

					var oProductPlanApplicability = oTransferSettings.productPlanApplicability;
						$('#productPlanApplicability').val(oProductPlanApplicability.id)

					if (!$.pgsi.isNullOrUndefined(oProductPlanApplicability.payer)){
						var oPayer = oProductPlanApplicability.payer;

						if (!$.pgsi.isNullOrUndefined(oTransferSettings.productPlanApplicability.productPlanId)){

							$("#pricing").val(oTransferSettings.productPlanApplicability.productPlanId).prop('selected',true);
							$('#pricing-button .ui-selectmenu-text').text($('#pricing option:selected').text().trim());
							$('#pricing-button').removeClass("required-field");

						}

						if (!$.pgsi.isNullOrUndefined(oTransferSettings.planCategory)){

							$('#planCategoryId-button .ui-selectmenu-text').text(oTransferSettings.planCategory.name);
							$("#planCategoryId").val(oTransferSettings.planCategory.id).prop('selected',true);
							$('#planCategoryId-button .ui-selectmenu-text').text($('#planCategoryId option:selected').text().trim());
							$('#planCategoryId-button').removeClass("required-field");
						}


						if (!$.pgsi.isNullOrUndefined(oPayer.country)){

							$('#destcountry-button .ui-selectmenu-text').text(oPayer.country.description);
							$("#destcountry").val(oPayer.country.code).prop('selected',true);
							$('#destcountry-button').removeClass("required-field");

							if (!$.pgsi.isNullOrUndefined(oProductPlanApplicability.currency.code)){

								$('#currency-button .ui-selectmenu-text').text(oProductPlanApplicability.currency.name);
								$("#currency").val(oProductPlanApplicability.currency.code).prop('selected',true);
								$('#currency-button').removeClass("required-field");

							}
						}

						if (!$.pgsi.isNullOrUndefined(oPayer.id)){

							$('#payer-button .ui-selectmenu-text').text(oPayer.name);
							$("#payer").val(oPayer.id).prop('selected',true);
							$('#payer-button').removeClass("required-field");

						}


					}
					if (!$.pgsi.isNullOrUndefined(oProductPlanApplicability.paymentType)){
						$('#methodTransfer-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum",oProductPlanApplicability.paymentType))
						$("#methodTransfer").val(oProductPlanApplicability.paymentTypeValue).prop('selected',true);
						$('#methodTransfer-button').removeClass("required-field");

						if(oProductPlanApplicability.paymentTypeValue == 4){
							$(".address").removeClass('hide');
							pgsi.pages.transfer.create.fnFetchRecipientByAddress({id:oTransferSettings.recipientId});


						}else if(oProductPlanApplicability.paymentTypeValue == 2){
							$(".deposit").removeClass('hide');
								if (!$.pgsi.isNullOrUndefined(oTransferSettings.account)){

									$("#account").val(oTransferSettings.account.number);

									$('#account-type-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.AccountTypeEnum",oTransferSettings.account.type))
									$("#account-type").val(oProductPlanApplicability.paymentTypeValue).prop('selected',true);
									$('#account-type-button').removeClass("required-field");
								}
						}
					}
				}

				if (!$.pgsi.isNullOrUndefined(oTransferSettings.transferType)){

					$('#schedule-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.TransferTypeEnum",oTransferSettings.transferType))
					$("#schedule").val(oTransferSettings.transferTypeValue).prop('selected',true);
					$('#schedule-button').removeClass("required-field");

					if(oTransferSettings.transferTypeValue == "1"){
						$('#skips').addClass('required').removeClass('hide').prop("placeholder","*");
						$('.skips-label').removeClass('hide');
					}else{
						$('#skips').removeClass('required').addClass('hide').prop("placeholder","");
						$('.skips-label').addClass('hide');
					}

				}

				if (!$.pgsi.isNullOrUndefined(oTransferSettings.cyclesToSkip)){

					$("#skips").val(oTransferSettings.cyclesToSkip);

				}

				$('#effectiveStartDate').val(oTransferSettings.effectiveStartDate);


				var oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : oTransferSettings.recipientId},"api/recipient/fetch",3);

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					$('#recipientParticipantId').val(oRecipientInfo[1].replace('-',''));
				</sec:authorize>

				$("#recipientId").val(oRecipientInfo[4]);

				pgsi.version.versionTransfer = oTransferSettings.version;

			},

			fillAddressRecipientRequestAndAjaxCall : function(iRecipientId,sModelAction) {

				var oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({stringId : iRecipientId.replace('-','')},"api/recipient/fetch",3);


				oRequest = new RecipientMaintenanceRequest({recipient : {id : oRecipientInfo[4],
					personTypeValue   : 7,
					pepStatusValue    : 2,
					personStatusValue : 1,
					contactList       : [{}],
					modelAction 	  : "NONE",
					risk			  : { modelAction: "NONE" },
					version 		  : pgsi.version.versionMember }
				});

				oRequest.recipient.contactList = new Array();

				var oAddress = pgsi.pages.address.form.fillObject('UPDATE');

				if (!$.pgsi.isNullOrUndefined(oAddress)) {
					oRequest.recipient.contactList.push(oAddress);
				}

				$.pgsi.ajax.post({
						sUrl 		: "api/recipient/update",
						oRequest 	: oRequest,
						fnCallback  : function(oResponse) {

							if (oResponse.operationSuccess == true){

							}else{
								fnCallBackError = function(oResponse) {

									pgsi.version.versionMember = oResponse.recipientList[0].version;
									pgsi.pages.recipient.create.form.fillObject(oResponse.recipientList[0]);
									$("#action-dialog-Error").dialog('close');

								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/recipient/fetch",{id:iRecipientId},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Recipient"),false);
							}
						}
					});
			},

			fillObject : function(oResponse) {

				if (!$.pgsi.isNullOrUndefined(oResponse)){
					if (!$.pgsi.isNullOrUndefined($.address.parameter('transferId'))){
						pgsi.pages.transfer.create.form.fillObjectDialog(oResponse);
						for(var iTransfer = 0; iTransfer < oResponse.transferSettingList.length;iTransfer++){
							if(oResponse.transferSettingList[iTransfer].id === parseInt($.address.parameter('transferId'))){
								pgsi.pages.transfer.create.form.fillTransferSettingsCreate(oResponse.transferSettingList[iTransfer]);
								// note
								if(!$.pgsi.isNullOrUndefined(oResponse.transferSettingList[iTransfer].noteList)){
									if(oResponse.transferSettingList[iTransfer].noteList.length > 0){
										$('#note').val(oResponse.transferSettingList[iTransfer].noteList[0].noteText);
										$('#noteId').val(oResponse.transferSettingList[iTransfer].noteList[0].id);
									}
								}
							}
						}
					}
					var sLocationDiv = "";
					if(!$.pgsi.isNullOrUndefined(oResponse.employmentInfoList)){
						if(oResponse.employmentInfoList.length > 0){
							for(var iCount=0;iCount < oResponse.employmentInfoList.length;iCount++){

								aLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(oResponse.employmentInfoList[iCount].locationId);

								if(iCount === 0){
									sLocationDiv = sLocationDiv + '<div class="employment" ><input type="radio" name="location" value="'+oResponse.employmentInfoList[iCount].locationId+'|'+oResponse.employmentInfoList[iCount].id+'|'+oResponse.employmentInfoList[iCount].locationName+'"></div> <label class="parent">'+oResponse.employmentInfoList[iCount].locationName+' ('+aLocation[1]+') </label>';
								}else{
									sLocationDiv = sLocationDiv + '<label class="first">&nbsp;</label><div class="employment" ><input type="radio" name="location" value="'+oResponse.employmentInfoList[iCount].locationId+'|'+oResponse.employmentInfoList[iCount].id+'|'+oResponse.employmentInfoList[iCount].locationName+'"></div> <label class="parent">'+oResponse.employmentInfoList[iCount].locationName+' ('+aLocation[1]+') </label>';
								}
							}

							$('#locationName').append(sLocationDiv);

							$('.frequencyBasedEventCalendarList').append(aLocation[2]);
						}
					}
				} else {

					if (!$.pgsi.isNullOrUndefined($.address.parameter('location'))) {

						aLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(parseInt($.address.parameter('location').split('|')[1],10));

						$('.frequencyBasedEventCalendarList').append(aLocation[2]);

					}
				}
			},

			fillEmploymentInfo : function (sModelAction) {

				oEmploymentInfo = new EmploymentInfo();

				oEmploymentInfo.id                        = parseInt($('#employmentInfo-id').val(),10);
				oEmploymentInfo.locationId                = parseInt($('#location-id').val(),10);
				oEmploymentInfo.locationName              = $('#location-name').val();


				return oEmploymentInfo;
			},

			fillRequest : function(sModelAction) {

				request = new TransferSetting();

				request.account = {};


				request.employmentInfo = pgsi.pages.transfer.create.form.fillEmploymentInfo(sModelAction);

				dDate = new Date($("#fee-exp").val());

				if(sModelAction != "UPDATE"){
					if(!$.pgsi.isNullOrUndefined($("#fee").val().substring(4))){
						request.customFeeList= [{value : parseFloat($('#fee').val().substring(4).replace(',','')) , effectiveEndDate :dDate.getTime(),modelAction : sModelAction, statusValue : 1 }];
					}
				}else{
					if(parseInt($("#customFeeId").val(),10) !== 0){
						if(!$.pgsi.isNullOrUndefined($("#fee").val())){
							request.customFeeList= [{value : parseFloat($('#fee').val().substring(4).replace(',','')) ,version : parseInt($("#customVersion").val(),10), effectiveEndDate :dDate.getTime(),modelAction : sModelAction, statusValue : 1, id : parseInt($("#customFeeId").val(),10),transferSettingId : parseInt($.address.parameter('transferId'),10)}];
						}else{
							request.customFeeList= [{modelAction : 'DELETE', statusValue : 1, version : parseInt($("#customVersion").val(),10),id : parseInt($("#customFeeId").val(),10),transferSettingId : parseInt($.address.parameter('transferId'),10)}];
						}
					}else{
						if(!$.pgsi.isNullOrUndefined($("#fee").val())){
							request.customFeeList= [{value : parseFloat($("#fee").val().substring(4).replace(',','')) ,version : parseInt($("#customVersion").val(),10), effectiveEndDate :dDate.getTime(),modelAction : 'INSERT', statusValue : 1,transferSettingId : parseInt($.address.parameter('transferId'),10) }];
						}
					}
				}

				request.planCategory = {id : parseInt($('#planCategoryId').val(),10) };

				request.productPlanApplicability = {
					id            : parseInt($('#productPlanApplicability').val()),
					productPlanId : parseInt($('#busdinessPlanId').val()),
					currency      : $('#currency').val(),
					payer         : {country:{code :$('#destcountry').val(),currencyList:[{code:$('#currency').val()}]},
					id            : parseInt($('#payer').val(),10)},
					paymentType   : $.pgsi.enums.fetchLabelsByValues("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum", [$('#methodTransfer').val()])[0]},
				request.id							= parseInt($('#transfer-id').val(),10);

				$.each( $('.contact-info2').find('input'), function( i, val ) {
					if ($(this).is(":checked")) {
						// Ex.: 2015-12-30
						var aDateArray = $(this).parent().find('label').text().split('/');
						// Date obj in UTC timezone
						var sDatestring = aDateArray[2] + "-" + aDateArray[0]  + "-" + aDateArray[1];
						var iPayDate = $.pgsi.date.getDateObj(sDatestring, "UTC");
						// The ideal "effectiveStartDate" should be equal to the latest "pay preparation date" before the "pay date"
						request.effectiveStartDate	= pgsi.pages.transfer.create.form.getLatestPPDBeforePayDate(pgsi.pages.transfer.create.payPreparationDays, iPayDate);
					}
				});

				request.memberId  					= parseInt($.address.parameter('memberId'),10) || parseInt($('#member-id').val(),10);
				request.numberOfCyclesSkipped       = parseInt($("#skips").val(),10);
				request.cyclesToSkip      			= parseInt($("#skips").val(),10);

				<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					request.recipientId					= pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({stringId : $('#recipientId').val().replace('-', '')},"api/recipient/fetch",3)[4];
				</sec:authorize>

				<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					request.recipientId = $("#recipientId").val();
				</sec:authorize>

				var iParticipantId = $("#recipientId").val().indexOf("RAA");

				if(iParticipantId != -1){
					request.recipientId = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({stringId : $('#recipientId').val().replace('-', '')},"api/recipient/fetch",3)[4];
				}else{
					request.recipientId = $("#recipientId").val();
				}

				request.statusValue                 = 1;
				request.transferAmount              = parseFloat($("#amount").val().substring(4).replace(',',''));
				request.transferTypeValue			= parseInt($("#schedule").val(),10);
				request.modelAction					= sModelAction;
				request.version                     = pgsi.version.versionTransfer;

				if($("#methodTransfer").val() == 2){
					if(!$.pgsi.isNullOrUndefined($("#account").val())){
						request.account = { number     : $("#account").val(),
											typeValue  : $("#account-type").val()}
					}
				}

				if(sModelAction != "UPDATE"){
					if(!$.pgsi.isNullOrUndefined($("#note").val())){
						var oNote 			 = [{ noteText :$("#note").val() ,modelAction   : sModelAction}];
					}
				}else{
					if(!$.pgsi.isNullOrUndefined($("#note").val())){
						if(!$.pgsi.isNullOrUndefined($("#noteId").val())){
							var oNote 			 = [{ noteText :$("#note").val() ,modelAction   : sModelAction,id : parseInt($('#noteId').val(),10) }];
						}else{
							var oNote 			 = [{ noteText :$("#note").val() ,modelAction   : "INSERT" }];
						}
					}else{
						var oNote 			 = [{ noteText :$("#note").val() ,modelAction   : "DELETE",id : parseInt($('#noteId').val(),10) }];
					}
				}

				if (!$.pgsi.isNullOrUndefined($.address.parameter('transferId'))){
					request.id = parseInt($.address.parameter('transferId'),10);
				}
				request.version 	= pgsi.version.versionTransfer;
				request.noteList    = oNote;

				// Build the request
				return new MemberMaintenanceRequest({
					member : {
						id                : parseInt($.address.parameter('memberId'),10) || parseInt($('#member-id').val(),10),
						personTypeValue   : 7,
						pepStatusValue    : 2,
						personStatusValue : 1,
						modelAction 	  : "NONE",
						risk : { modelAction: "NONE" },
						version 		  : pgsi.version.versionMember,
						transferSettingList : [
							request
						]
					}
				});

			},

			fnAjaxCallFetchAll : function(iId){
				$.pgsi.ajax.post({
					sUrl 		: "api/transfer/fetchall",
					oRequest 	: {parentId : iId},
					fnCallback  : function(oResponse) {
						if(oResponse.operationSuccess == true){
							pgsi.pages.transfer.create.view.fillMember(oResponse.liaisonList);
						}else{
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
					}
				});
			},
			fnAjaxCallRecipientByID : function(oRequest,sUrl,iPersonType) {

				var aReturn = [];
				var sAddress = " ";
				$.pgsi.ajax.post({
						sUrl 		: sUrl,
						oRequest 	: oRequest,
						bAsync		: false,
						bHideProgressBar : true,
						fnCallback  : function(oResponse) {

							if (oResponse.operationSuccess == true ){
								if(iPersonType === "MEMBER"){
									oList = oResponse.memberList[0];
								}else{
									oList = oResponse.recipientList[0];
								}
								if(!$.pgsi.isNullOrUndefined(oList)){
									 aReturn.push(oList.firstName + " " + oList.lastName);
									 aReturn.push(oList.participantId);
									 var oContact = oList.contactList;

									 for(var i=0;i< oContact.length;i++){
										if(oContact[i].type == "address"){
											sAddress = oContact[i].cityName +" , "+oContact[i].stateProvinceRegion.description.trim()+"<br> "+oContact[i].country.description ;
										}

									 }
									aReturn.push(sAddress);
									if($.pgsi.isNullOrUndefined(aReturn[2])){
										aReturn[2] = "";
									}
									aReturn.push(oList.personStatusValue);

									aReturn.push(oList.id);


									$('#recipient').val(oList.id);

									aReturn.push(oList.transferSettingList);

									// SDN Status
									aReturn.push(oList.sdnstatus);

									// Risk Level
									aReturn.push(oList.risk.riskLevel);

									// PEP Statuus
									aReturn.push(oList.pepStatus);
								}
							}
							else{
								fnCallBackError = function(oResponse) {

									pgsi.version.versionMember = oList.version;

								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/member/fetch",{id:iMemberId},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Member"),false);
							}
						}
					});

				return aReturn;
			},
			fnAjaxCallLocation : function(iId) {

				var sReturn;
				$.pgsi.ajax.post({
						sUrl 		: "api/location/fetch",
						oRequest 	: {id:iId},
						bHideProgressBar : true,
						bAsync		: false,
						fnCallback  : function(oResponse) {
							if (oResponse.operationSuccess == true){
								sReturn = oResponse.locationList[0];
							}
							$.pgsi.progressBar.stop();
						}
				});
				return sReturn;
			},
			fnAjaxCallLocationByID : function(iId) {

				var aReturn = [];
				var sFrequencyEnum="";
				$.pgsi.ajax.post({
						sUrl 		: "api/location/fetch",
						oRequest 	: {id:iId},
						bAsync		: false,
						bHideProgressBar : true,
						fnCallback  : function(oResponse) {
							var sInputRadio = "";
							var aPayDays = [];
							var aPayPreparationDays = [];
							if (oResponse.operationSuccess == true){
								var oLocation = oResponse.locationList[0];
								 if(!$.pgsi.isNullOrUndefined(oLocation)){
									aReturn.push(oLocation.parentOrganizationId);
									aReturn.push(oLocation.parentOrganizationName);
									if(!$.pgsi.isNullOrUndefined(oLocation.frequencyBasedEventList)){
										if(oLocation.frequencyBasedEventList.length > 0){
											var oFrequencyBase = oLocation.frequencyBasedEventList;
											var oPayDay;
											for (i=0; i < oFrequencyBase.length; i++) {
												if(!$.pgsi.isNullOrUndefined(oFrequencyBase[i].frequencyBasedEventCalendarList)){
													if(oFrequencyBase[i].frequencyBasedEventCalendarList.length > 0){
														var eventCalendar = oFrequencyBase[i].frequencyBasedEventCalendarList;

														if (oFrequencyBase[i].type == "PAY_DAY") {
															oPayDay = oFrequencyBase[i];
															sFrequencyEnum = oFrequencyBase[i].frequencyCode;
															if(eventCalendar.length > 0){
																for(y=0;y<eventCalendar.length;y++){

																	if(new Date().getTime() < eventCalendar[y].eventDate){
																		dDate = $.pgsi.date.format(new Date(eventCalendar[y].eventDate), 'mm/dd/yy', null);
																		sInputRadio = sInputRadio + '<div class="contact-info2"><input id="'+eventCalendar[y].id+'" type="radio" name="paydate"><label class="paydate">'+$.pgsi.date.format(new Date(eventCalendar[y].eventDate), 'mm/dd/yy', null)+'</label></div>';
																		aPayDays.push(eventCalendar[y].eventDate);
																	}

																}
															}
														}

														else if (oFrequencyBase[i].type == "PAY_PREPARATION") {

															aPayPreparationDays = eventCalendar;

														}
													}
												}
											}

											aReturn.push(sInputRadio);
											aReturn.push(sFrequencyEnum);
											aReturn.push(aPayPreparationDays);
											aReturn.push(aPayDays);
											aReturn.push(oPayDay);
											aReturn.push(iId);

										}
									}
								}

							}else{
								fnCallBackError = function(oResponse) {

									pgsi.version.versionMember = oResponse.recipientList[0].version;

								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/member/fetch",{id:iMemberId},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Member"),false);
							}
							$.pgsi.listener.notify({
								eventName 	: "locationFetch",
								arguments 	: $.pgsi.storage.get('arguments', arguments)
							});
							$.pgsi.progressBar.stop();

						}
					});
				return aReturn;
			},
			fnAjaxCallProductPlan : function(iId,fnCallbackProductPlan,productPlanId,bEdit,oTransferSettings) {

				var fnCallbackProductPlan = function(oResponse){

					if(oResponse.businessProductPlanList.length > 0){

						if(bEdit != true){
							if($.pgsi.isNullOrUndefined($('#pricing').val())){
								pgsi.pages.transfer.create.fnInitList(oResponse.businessProductPlanList,$('#pricing'));
							}
						}else{
								pgsi.pages.transfer.create.fnInitBusinessPlanList(oResponse.businessProductPlanList,productPlanId,oTransferSettings);
						}
					}

					$("#pricing").on("selectmenuchange", function(event, ui) {

						$("#busdinessPlanId").val(ui.item.element.attr('id'));
						$("#productPlanApplicability").val(ui.item.element.attr('class'));

						pgsi.pages.transfer.create.fnPricingList(oResponse.businessProductPlanList,$('#pricing'),ui.item.element.attr('class'),ui.item.label.trim(),ui.item.value);

					});

					$("#planCategoryId").on("selectmenuchange", function(event, ui) {

						$("#busdinessPlanId").val(ui.item.element.attr('id'));
						$("#productPlanApplicability").val(ui.item.element.attr('class'));

						pgsi.pages.transfer.create.fnPlanCategoryList(oResponse.businessProductPlanList,$('#planCategoryId'),ui.item.element.attr('id'),ui.item.label.trim(),ui.item.value);

					});

					$("#currency").on("selectmenuchange", function(event, ui) {

						$("#busdinessPlanId").val(ui.item.element.attr('id'));
						$("#productPlanApplicability").val(ui.item.element.attr('class'));
						pgsi.pages.transfer.create.fnCurrencyList(oResponse.businessProductPlanList,$('#currency'),ui.item.element.attr('class'),ui.item.label.trim(),ui.item.value);

					});

					$("#destcountry").on("selectmenuchange", function(event, ui) {

						$("#busdinessPlanId").val(ui.item.element.attr('id'));
						$("#productPlanApplicability").val(ui.item.element.attr('class'));

						pgsi.pages.transfer.create.fnCountryList(oResponse.businessProductPlanList,$('#destcountry'),ui.item.element.attr('class'),ui.item.label.trim(),ui.item.value);


					});

					$("#payer").on("selectmenuchange", function(event, ui) {

						$("#busdinessPlanId").val(ui.item.element.attr('id'));
						$("#productPlanApplicability").val(ui.item.element.attr('class'));
						pgsi.pages.transfer.create.fnPayerList(oResponse.businessProductPlanList,$('#payer'),ui.item.element.attr('class'),ui.item.label.trim(),ui.item.value);



					});

					$("#methodTransfer").on("selectmenuchange", function(event, ui) {

						$("#busdinessPlanId").val(ui.item.element.attr('id'));
						$("#productPlanApplicability").val(ui.item.element.attr('class'));

						if(!$.pgsi.isNullOrUndefined(ui.item.value)){
							var transfer = $.pgsi.enums.fetchLabelByValue("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum",ui.item.value ).trim()
						}else{
							var transfer = "";
						}
						pgsi.pages.transfer.create.fnMethodTransferList(oResponse.businessProductPlanList,$('#methodTransfer'),ui.item.element.attr('id'),transfer,ui.item.value);


					});

				};

				$.pgsi.ajax.get({
					sUrl : "fetchProductPlan?businessId=" + iId,
					bAsync : false,
					fnCallback : fnCallbackProductPlan
				});
			},
			fnAjaxCallInsertUpdateMemberTransfer : function(iTransferId,fnFillCallBack) {

				if (!$.pgsi.isNullOrUndefined($.address.parameter('transferId'))){
						sModelAction = "UPDATE";
				}else{
						sModelAction = "INSERT";
						$('#recipientParticipantId').removeClass('recipientId')
						$('#recipientParticipantId').attr('name',"")
				}

				var bValidForm = pgsi.pages.transfer.create.form.validator.form();

				if (bValidForm){
					bValidForm = pgsi.util.page.fnValidadeDatePayDay();
					if(!bValidForm){
						var oResponse = {messageInfoList :[{text:"The start at pay date is required."}],messageList:[{messageInfo:{severity:'Error'},text:"The start at pay date is required."}]};
						pgsi.pages.sendsolv.fnDialogMessageError("","",oResponse,"",$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("commons.pages.employment")),false);
					}
				}


				if (bValidForm){
					$.pgsi.ajax.post({
						sUrl 		: 'api/member/update',
						oRequest 	: pgsi.pages.transfer.create.form.fillRequest(sModelAction),
						fnCallback  : function(oResponse) {
							if (oResponse.operationSuccess == true){
								 fnFillCallBack(oResponse);
							}
							else{
								fnCallBackError = function(oResponse) {

									pgsi.version.versionMember = oResponse.memberList[0].version;
									pgsi.pages.transfer.create.form.fillObject(oResponse.memberList[0]);
									$("#action-dialog-Error").dialog('close');

								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/member/fetch",{id:parseInt($.address.parameter('memberId'),10)},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Member"),false);
							}
						}
					});

				}
			}

		},
		fnAjaxCallTransferId : function(iTransferId) {
			var oResponseId
			$.pgsi.ajax.post({
				sUrl 		: "api/member/fetch",
				oRequest 	: {id : parseInt($.address.parameter('memberId'),10)},
				bAsync      : true,
				fnCallback  : function(oResponse) {
					if(oResponse.operationSuccess == true){
						for(var iCountTransfer = 0;iCountTransfer < oResponse.memberList[0].transferSettingList.length;iCountTransfer++){
							if(oResponse.memberList[0].transferSettingList[iCountTransfer].id === iTransferId ){
								oResponseId = oResponse.memberList[0].transferSettingList[iCountTransfer];
							}
						}
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}
			});

			return oResponseId;
		},

		view :{
			fillTransferSettings : function(oTransferSettings, oPerson ,sUrl) {

				var sTransferSettings="";
				var sRecipientParentID ="";
				var oRecipientInfo="";
				var sButtonDelete="";
				var sInsUpdLinks="";
				var sSdnIcons = "";
				var oLocation = "";
				var arguments = [];
				var iCount = 0;
				var locations = [];
				var recipients = [];
				$.pgsi.storage.delete('recipients');
				$.pgsi.storage.delete('recipient');
				$.pgsi.storage.delete('locations');
				$.pgsi.storage.delete('location');

				if(oTransferSettings.length > 0){
					for (var i=0; i < oTransferSettings.length; i++) {

						if($.inArray('fetch_'+oTransferSettings[i].employmentInfo.locationId, arguments) === -1){
							arguments.push('fetch_'+oTransferSettings[i].employmentInfo.locationId);
							iCount++
						}
					}
				}else{
					arguments.push('fetch_0');
				}

				$.pgsi.storage.set('arguments', arguments);
				$.pgsi.listener.wait({
					eventName 	: "locationFetch",
					arguments 	: arguments,
					fnCallback 	: $.pgsi.progressBar.stopGlobal
				});
				if(oTransferSettings.length < 1){
					$.pgsi.progressBar.stopGlobal();
				}
				for (var i=0; i < oTransferSettings.length; i++) {

					if (oPerson.personType ===  "MEMBER") {

						oRecipientInfo="";
						var oRecipients = $.pgsi.storage.get('recipients');
						if(!$.pgsi.isNullOrUndefined(oRecipients)){
							for(var iCont = 0;iCont<oRecipients.length;iCont++){
								if(oRecipients[iCont][4] == oTransferSettings[i].recipientId){
									oRecipientInfo = oRecipients[iCont];
									break;
								}
							}
						}
						if($.pgsi.isNullOrUndefined(oRecipientInfo)){

							oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : oTransferSettings[i].recipientId}, sUrl, "RECIPIENT");
							$.pgsi.storage.set('recipient', oRecipientInfo);
							recipients.push($.pgsi.storage.get('recipient'));
							$.pgsi.storage.set('recipients', recipients);

						}

						if(!$.pgsi.isNullOrUndefined(oRecipientInfo[0])){
							var sRecipientParticipantId="";
							var sRecipientName = oRecipientInfo[0];

							<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
								sRecipientParticipantId = '(#'+pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1])+')';
							</sec:authorize>

							var sRecipientNameLink = '<div class="value newline title"><a class="alist" href="member/view?tab=info&memberId='+oTransferSettings[i].memberId+'" title="'+sRecipientName+ ' ' + sRecipientParticipantId +'">To ' + sRecipientName + '</a>';

							sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags({
								sdnstatus : oRecipientInfo[6],
								risk : {
									riskLevel : oRecipientInfo[7]
								},
								pepStatus : oRecipientInfo[8]
							});

							sRecipientNameLink += "</div>" + sSdnIcons;
						}

						else{
							var sRecipientName = "";
							var	sRecipientNameLink = "";

						}
					}

					else {

						oRecipientInfo="";
						var oRecipients = $.pgsi.storage.get('recipients');
						if(!$.pgsi.isNullOrUndefined(oRecipients)){
							for(var iCont = 0;iCont<oRecipients.length;iCont++){
								if(oRecipients[iCont][4] == oTransferSettings[i].memberId){
									oRecipientInfo = oRecipients[iCont];
									break;
								}
							}
						}
						if($.pgsi.isNullOrUndefined(oRecipientInfo)){

							oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : oTransferSettings[i].memberId}, sUrl, "MEMBER");
							$.pgsi.storage.set('recipient', oRecipientInfo);
							recipients.push($.pgsi.storage.get('recipient'));
							$.pgsi.storage.set('recipients', recipients);

						}
						if(!$.pgsi.isNullOrUndefined(oRecipientInfo[0])){
							var sRecipientParticipantId="";
							var sRecipientName = oRecipientInfo[0];

							<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
								sRecipientParticipantId = '(#'+pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1])+')';
							</sec:authorize>

							var sRecipientNameLink = '<div class="value newline title"><a class="alist" href="recipient/view?tab=info&recipientId='+oTransferSettings[i].recipientId+'" title="'+sRecipientName+ ' ' + sRecipientParticipantId +'">From '+sRecipientName+'</a>';

							sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags({
								sdnstatus : oRecipientInfo[6],
								risk : {
									riskLevel : oRecipientInfo[7]
								},
								pepStatus : oRecipientInfo[8]
							});

							sRecipientNameLink += "</div>" + sSdnIcons;

						} else{
							var sRecipientName = "";
							var	sRecipientNameLink = "";
						}
					}

					<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
						sRecipientParentID = pgsi.util.page.fnInsertMask('***-99999',oRecipientInfo[1]);
						sRecipientParentID = '<div class="value newline internal-id">#'+sRecipientParentID+'</div>';
					</sec:authorize>

					if(!$.pgsi.isNullOrUndefined(oRecipientInfo[2])){
						var sAddress = oRecipientInfo[2]
					}else{
						var sAddress = "";
					}
					var sCustomFeeList = "";

					if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].customFeeList)){
						if(oTransferSettings[i].customFeeList.length > 0){
							sCustomFeeList = '<div class="label newline">Discount active until '+$.pgsi.date.format(new Date(oTransferSettings[i].customFeeList[0].effectiveEndDate), "mm/dd/yy", null)+'</div>'
						}
					}
					if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].planCategory)){

						var sPlanCategory = '<div class="label newline">'+oTransferSettings[i].planCategory.name+' Plan</div>'
						if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].customFeeList[0])){
							if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].customFeeList[0].value)){
								var sPlanCategory = '<div class="label newline">'+oTransferSettings[i].planCategory.name+' Plan (Fee '+pgsi.util.page.fnInsertMaskNumeric("'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'",oTransferSettings[i].customFeeList[0].value)+')</div>'
							}
						}

					}

					var sRecipientNameLink;

					var sPayer    = "";
					var sCurrency = "";
					var sTransferAmount = "";

					if (!$.pgsi.isNullOrUndefined(oTransferSettings[i].transferAmount)) {

						var input = document.createElement("input");
						input.type = "text";
						input.dataset.inputmask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'";
						input.value = oTransferSettings[i].transferAmount;
						var $input = $(input);
						$input.inputmask();
						sTransferAmount = $input.val();
						oLocation = "";
						var oLocations = $.pgsi.storage.get('locations');
						if(!$.pgsi.isNullOrUndefined(oLocations)){
							for(var iCont = 0;iCont<oLocations.length;iCont++){
								if(oLocations[iCont][7] == oTransferSettings[i].employmentInfo.locationId){
									oLocation = oLocations[iCont];
									break;
								}
							}
						}
						if($.pgsi.isNullOrUndefined(oLocation)){
							oLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(oTransferSettings[i].employmentInfo.locationId);
							$.pgsi.storage.set('location', oLocation);
							locations.push($.pgsi.storage.get('location'));
							$.pgsi.storage.set('locations', locations);

						}
						pgsi.pages.transfer.create.payPreparationDays = oLocation[5];

						var oLatestPPD = $.pgsi.date.getDateObj(oTransferSettings[i].effectiveStartDate, "UTC");
						var sDatestring = oLatestPPD.getFullYear() + "-" + (oLatestPPD.getMonth() + 1) + "-" +  oLatestPPD.getDate();
						var iPayDate = $.pgsi.date.getDateObj(sDatestring, "UTC");
						var dAffectiveStartDate	= pgsi.pages.transfer.create.form.getLatestPDBeforePayDate(pgsi.pages.transfer.create.payPreparationDays, iPayDate);

						if (!$.pgsi.isNullOrUndefined(oTransferSettings[i].employmentInfo)) {

							if(oTransferSettings[i].transferTypeValue == 2){

								sTransferAmount = '<div class="value spacer newline"> '+ sTransferAmount + ' on '+$.pgsi.date.format(new Date(oTransferSettings[i].effectiveStartDate), "mm/dd/yy", null)+'</div>';
								var sEffective = "";
							}

							else if((oTransferSettings[i].transferTypeValue == 1)&&(oTransferSettings[i].cyclesToSkip == 0)){

								if($.pgsi.isNullOrUndefined(oLocation)){
									oLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocation(oTransferSettings[i].employmentInfo.locationId);
								}
								var sFrequencyCode = "";

								var oPayDay = oLocation[6];

									switch (oPayDay.frequencyCode) {
										case "WEEKLY":
											sFrequencyCode = "every week";
										break;

										case "BI_WEEKLY":
											sFrequencyCode = "every other week";
										break;

										case "SEMI_MONTHLY":
											sFrequencyCode = "twice a month";
										break;

										case "MONTHLY":
											sFrequencyCode = "once a month";
										break;
								}

								if(!$.pgsi.isNullOrUndefined(sFrequencyCode)){

									sTransferAmount = '<div class="value spacer newline"> '+ sTransferAmount + ' '+ sFrequencyCode +'</div>';
								}else{
									sTransferAmount = '<div class="value spacer newline"> '+ sTransferAmount +'</div>';
								}

								var sEffective = "";
								sEffective = '<div class="label spacer newline">Effective since '+$.pgsi.date.format(new Date(dAffectiveStartDate), "mm/dd/yy", null)+'</div>'


							}else{
								sTransferAmount = '<div class="value spacer newline"> '+ sTransferAmount + ' every '+(oTransferSettings[i].cyclesToSkip + 1)+' pay cycles</div>';
								var sEffective = "";
								if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].effectiveStartDate)){
									sEffective = '<div class="label spacer newline">Effective since '+$.pgsi.date.format(new Date(dAffectiveStartDate), "mm/dd/yy", null)+'</div>'
								}

							}
						}else{
							sTransferAmount = '<div class="value spacer newline"> '+ sTransferAmount + '</div>';
						}
					}

					if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].productPlanApplicability)){

						if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].productPlanApplicability.payer)){
							sPayer = '<div class="label newline">Payer '+oTransferSettings[i].productPlanApplicability.payer.name+' '+oTransferSettings[i].productPlanApplicability.paymentType+'</div>'
						}
						if(!$.pgsi.isNullOrUndefined(oTransferSettings[i].productPlanApplicability.currency)){
							sCurrency = '<div class="label newline">Payable in '+oTransferSettings[i].productPlanApplicability.currency.name.trim()+'</div>'
						}
					}

					sBottonActiveInactive = "";

					if(oTransferSettings[i].statusValue === 2 ){
						sBottonActiveInactive = "<a href='"+oTransferSettings[i].id+"' id='"+oTransferSettings[i].version+"|"+oTransferSettings[i].memberId+"'  class='ui-subtitle buttonContact active subtitle' title='" + $.pgsi.locale.get("pages.view.activate") + "'> <span class='icon-small-button icon-nav icon-check-mark '></span> <span>"+$.pgsi.locale.get("pages.view.activate")+"</span></a>"
					}else{
						sBottonActiveInactive = "<a href='"+oTransferSettings[i].id+"' id='"+oTransferSettings[i].version+"|"+oTransferSettings[i].memberId+"' class='ui-subtitle buttonContact disable subtitle' title='" + $.pgsi.locale.get("pages.view.disable") + "'> <span class='icon-small-button icon-nav icon-minus-circle '></span> <span>"+$.pgsi.locale.get("pages.view.disable")+"</span></a>"
					}

					<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
						sButtonDelete = '<a href="'+ oTransferSettings[i].id + '" id="'+oTransferSettings[i].version+"|"+oTransferSettings[i].memberId+'" class="ui-subtitle buttonContact delete" title="' + $.pgsi.locale.get("commons.pages.delete") + '"> <span class="icon-small-button icon-nav icon-trash-bin "></span> <span>'+$.pgsi.locale.get("commons.pages.delete") + '</span></a>';
					</sec:authorize>

					if (!pgsi.util.page.fnIsSDNFlagged(oRecipientInfo[6]) && !pgsi.util.page.fnIsSDNFlagged(oPerson.sdnstatus)) {
						sInsUpdLinks += '	<div class="small-box">'
						+'		<div class="links">'
						+'			<a href="'+oTransferSettings[i].id+'" id="'+oTransferSettings[i].memberId+'" class="ui-subtitle buttonContact edit" title="' + $.pgsi.locale.get("commons.pages.edit") + '"> <span class="icon-small-button icon-nav icon-pencil "></span> <span>'+$.pgsi.locale.get("commons.pages.edit")+'</span>'
						+sBottonActiveInactive
						+'			</a> '
						+sButtonDelete
						+'		</div>'
						+'	</div>';
					}

					var sCreateOneOffTransaction = "";

					if (oPerson.personType === "MEMBER") {
						sCreateOneOffTransaction =  '<a href="' + i + '" class="link-one-off spacer">'
						+	'<span class="icon-small-button icon-nav icon-chevron-right"></span>' + $.pgsi.locale.get('pages.transfer.view.createoneofftransaction')
						+'</a>';
					}

					var fTransferAmountInDestCurrency;

					if (oTransferSettings[i].productPlanApplicability.currency.code === "USD") {
						fTransferAmountInDestCurrency = "";
					}

					else {
						try {
							fTransferAmountInDestCurrency = oTransferSettings[i].transferAmount * oTransferSettings[i].productPlanApplicability.payer.dailyCurrencyRateList[0].dailyCurrencyRateDetailList[0].exchangeRate;
						}

						catch (e) {
							fTransferAmountInDestCurrency = null;
						}

						var sTransferAmountInDestCurrency = "";

						if (!$.pgsi.isNullOrUndefined(fTransferAmountInDestCurrency)) {
							input.value = fTransferAmountInDestCurrency;
							input.dataset.inputmask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': '" + oTransferSettings[i].productPlanApplicability.currency.code + " ', 'placeholder': '0'";

							$input = $(input);
							$input.inputmask();

							sTransferAmountInDestCurrency = $.pgsi.locale.get('pages.transfer.view.label.approx') + ' ' + $input.val();
						}

						else {
							sTransferAmountInDestCurrency =  $.pgsi.locale.get('pages.transfer.view.label.nocurrency');
						}

						sTransferAmountInDestCurrency = '<div class="value newline">(' + sTransferAmountInDestCurrency + ')</div>';
					}

					sTransferSettings = sTransferSettings + '<div class="outer-box">'
					+'<div class="box">'
					+sInsUpdLinks
					+'<div class="hover-card-content">'
					+sRecipientNameLink
					+sRecipientParentID
					+'<div class="label newline">'+sAddress+'</div>'
					+sTransferAmount
					+sTransferAmountInDestCurrency
					+sPlanCategory
					+sCustomFeeList
					+sCurrency
					+sPayer
					+sEffective
					+'</br>'
					+ sCreateOneOffTransaction
					+'</div>'
					+'</div>'
					+'</div>';
				}


				// Removes content from the section
				$("section.transfer.view").find("div.container").empty();

				$("section.transfer.view").find("div.container").append(sTransferSettings);

				var iHighestHeight;
				var iHeight;
				$("section.transfer.view").find("div.container").find(".outer-box").find('.box').each(function(i){
					iHeight = $(this).outerHeight();
					if (i === 0) {
						iHighestHeight = iHeight;
					}
					else if(iHighestHeight < iHeight) {
						iHighestHeight = iHeight;
					}
				});

				$("section.transfer.view").find("div.container").find(".outer-box").height(iHighestHeight);

				if (oTransferSettings.length == 0) {
					$("section.transfer.view").find('div.container').append("<p class='empty'>" + $.pgsi.locale.get("page.business.view.transfer.empty") + "</p>");
				}

				$("#addTransfer").unbind("click");

				$('.transfer.view').find('a').on("click", function(event) {

					event.preventDefault();

					if ($(this).hasClass('edit')) {
						$.address.parameter('memberId',$(this).attr("id"));
						$.address.parameter("transferId", $(this).attr("href"));
						pgsi.util.actiondialog.launchActionDialog("insUpdTransfer", pgsi.pages.transfer.dialogSettings.insUpdTransfer(
							parseInt($.address.parameter('memberId'),10),
							"",
							3,
							"",
							""
						));
					}

					else if($(this).hasClass('insertTransfer')) {
							$.address.parameter("transferId", "");
							sTitle   = $.pgsi.locale.get("pages.contacts.dialog.note.title.add", $('#company-name-field').text());
							pgsi.util.actiondialog.launchActionDialog("optionTransfer", pgsi.pages.transfer.dialogSettings.optionTransfer(parseInt($('#member-id').val(),10),"",parseInt($("#personType").val(),10)));
					}

					else if($(this).hasClass('insertTransferRecipient')) {
						pgsi.util.actiondialog.launchActionDialog(
							"insUpdTransfer",
							pgsi.pages.transfer.dialogSettings.insUpdTransfer(
								iId,
								"",
								3,
								parseInt($.address.parameter('recipientId'),10),
								$('#recipient-name').val(),
								parseInt($('#status-value').val())
							)
						);
					}

					else if ($(this).hasClass('active')) {
						var ohref = $(this).attr("id").split('|')
						pgsi.pages.transfer.create.fnDisabledActiveved(parseInt($(this).attr("href")), 1 ,parseInt(ohref[1]),parseInt(ohref[0]))
					}

					else if ($(this).hasClass('disable')) {
						var ohref = $(this).attr("id").split('|')
						pgsi.pages.transfer.create.fnDisabledActiveved(parseInt($(this).attr("href")), 2 ,parseInt(ohref[1]),parseInt(ohref[0]))
					}

					else if ($(this).hasClass('delete'))
					{
						var ohref = $(this).attr("id").split('|');
						pgsi.pages.transfer.create.fnDelete(parseInt($(this).attr("href")),parseInt(ohref[1]),parseInt(ohref[0]));
					}

					else if ($(this).hasClass('link-one-off')) {
						var oTransferSetting = oTransferSettings[parseInt($(this).attr('href'))];
						var oMember = oPerson;

						sTitle   = $.pgsi.locale.get("pages.contacts.dialog.note.title.add", $('#company-name-field').text());

						pgsi.util.actiondialog.launchActionDialog(
							"createOneOffTransaction",
							pgsi.pages.batches.dialogSettings.createOneOffTransaction(
								oTransferSetting,
								oMember
							)
						);
					}

				});

				$("#tabs").find(".add_loc_link").unbind("click");
				$("#tabs").find(".add_loc_link").on("click", function(event){
					$.address.parameter("transferId", "");
								sTitle   = $.pgsi.locale.get("pages.contacts.dialog.note.title.add", $('#company-name-field').text());
								pgsi.util.actiondialog.launchActionDialog("optionTransfer", pgsi.pages.transfer.dialogSettings.optionTransfer(parseInt($('#member-id').val(),10),"",parseInt($("#personType").val(),10)));
				});

			}
		},

		fnDelete : function(iId,iMemberId,iTransferVersion) {

			var sPurl = "";

			var fnCallbackSave = function(oResponse) {

				if (oResponse.operationSuccess == true) {
					$("#action-dialog").dialog('close');

					if ($.pgsi.isNullOrUndefined($.address.parameter('recipientId'))){
						$.pgsi.ajax.post({
							sUrl 		: "api/member/fetch",
							oRequest 	: {id : iMemberId},
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
					}else{
						$.pgsi.ajax.post({
							sUrl 		: "api/recipient/fetch",
							oRequest 	: {id : parseInt($.address.parameter('recipientId'),10)},
							fnCallback  : function(oResponse) {
								if(oResponse.operationSuccess == true){
									pgsi.pages.recipient.view.fnFillFields(oResponse);
									pgsi.version.versionMember = oResponse.recipientList[0].version;
								}else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
							}
						});
					}
				}else {
					fnCallBackError = function(oResponse) {
						pgsi.version.versionMember = oResponse.memberList[0].version;

						$("#action-dialog-Error").dialog('close');

					}

					pgsi.pages.sendsolv.fnDialogMessageError(sPurl,{id:oRequest.member.id},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("pages.transfer.commons.transfer")),false);
				}
			}
			 oRequest =  new MemberMaintenanceRequest({member : {id : iMemberId,transferSettingList:[{id:iId,modelAction:'DELETE',version : iTransferVersion}],
						personTypeValue   : 7,
						pepStatusValue    : 2,
						personStatusValue : 1,
						modelAction 	  : "NONE",
						risk : { modelAction: "NONE" },
						version 		  : pgsi.version.versionMember }});
			sTitle = $.pgsi.locale.get("pages.dialog.transfer.title.delete",$('#member-name').text());
			pgsi.util.actiondialog.launchActionDialog("deleteDialog", pgsi.pages.business.dialogSettings.deleteDialog("api/member/update",oRequest,sTitle,fnCallbackSave,$.pgsi.locale.get("commons.pages.erroView","Transfer Setting")));
		},
		fnFetchRecipientByAddress : function(oRequest) {

			$.pgsi.ajax.post({
				sUrl 		: "api/recipient/fetch",
				oRequest 	: oRequest,
				bAsync		: true,
				fnCallback  : function(oResponse) {
					if (oResponse.operationSuccess == true){
						if(!$.pgsi.isNullOrUndefined(oResponse.recipientList[0])){
							// fill address fields
							pgsi.pages.address.form.fillFormFields(oResponse.recipientList[0].contactList);
						}
					}
					else{
						fnCallBackError = function(oResponse) {

							pgsi.version.versionMember = oResponse.recipientList[0].version;

						}
						pgsi.pages.sendsolv.fnDialogMessageError("api/member/fetch",{id:iMemberId},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Member"),false);
					}
				}
			});

		},


		fnDisabledActiveved : function(iTransferId,iStatusValue,iMemberId,iTransferVersion){

			var sPurl = "";
			var fnCallbackSave = function(oResponse) {

				if (oResponse.operationSuccess == true) {
					$("#action-dialog").dialog('close');

					if ($.pgsi.isNullOrUndefined($.address.parameter('recipientId'))){
						$.pgsi.ajax.post({
							sUrl 		: "api/member/fetch",
							oRequest 	: {id : iMemberId},
							fnCallback  : function(oResponse) {
								if(oResponse.operationSuccess == true){
									pgsi.pages.member.view.fnFillFields(oResponse);
									pgsi.version.versionMember = oResponse.memberList[0].version;

								}else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
							}
						});
					}else{
						$.pgsi.ajax.post({
							sUrl 		: "api/recipient/fetch",
							oRequest 	: {id : parseInt($.address.parameter('recipientId'),10)},
							fnCallback  : function(oResponse) {
								if(oResponse.operationSuccess == true){
									pgsi.pages.recipient.view.fnFillFields(oResponse);
									pgsi.version.versionMember = oResponse.recipientList[0].version;

								}else{
									pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
								}
							}
						});
					}
				}else {
					fnCallBackError = function(oResponse) {
						pgsi.version.versionMember = oResponse.memberList[0].version;

						$("#action-dialog-Error").dialog('close');

					}

					pgsi.pages.sendsolv.fnDialogMessageError(sPurl,{id:oRequest.member.id},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title",$.pgsi.locale.get("commons.pages.employment")),false);
				}
			}

			var oRequest = new MemberMaintenanceRequest({member : {
				id                  : iMemberId,
				transferSettingList : [{modelAction  : "NONE",statusValue : iStatusValue ,id:iTransferId,version:iTransferVersion}],
				personTypeValue     : 7,
				pepStatusValue      : 2,
				personStatusValue   : 1,
				modelAction 	    : "NONE",
				risk : { modelAction: "NONE" },
				version 		    : pgsi.version.versionMember
			}});

			if(iStatusValue === 2){
				pgsi.util.actiondialog.launchActionDialog("activeDesactiveDialog", pgsi.pages.business.dialogSettings.activeDesactiveDialog(
				"api/member/update",
				 oRequest,
				 $.pgsi.locale.get("pages.member.view.transfer.disable"),
				 fnCallbackSave,
				 "<span>" + $.pgsi.locale.get("pages.person.dialog.status.question", $.pgsi.locale.get("pages.view.disable"), $.pgsi.locale.get("pages.transfer.commons.transfer")) + "</span>",
				"pages.view.disable"));
			}else{
			pgsi.util.actiondialog.launchActionDialog("activeDesactiveDialog", pgsi.pages.business.dialogSettings.activeDesactiveDialog(
				"api/member/update",
				 oRequest,
				 $.pgsi.locale.get("pages.member.view.transfer.activate"),
				 fnCallbackSave,
				 "<span>" + $.pgsi.locale.get("pages.person.dialog.status.question", $.pgsi.locale.get("pages.view.activate"), $.pgsi.locale.get("pages.transfer.commons.transfer")) + "</span>",
				"pages.view.activate"));}

		},
		fnValidadeLocation : function(sName){

			var bReturn = false;
			//TODO
			$.pgsi.ajax.post({
				sUrl 		: "api/location/fetchall",
				oRequest 	: {"sortExpressions":[{"field":"name","direction":"Descending"}],"preQueryCount":true,"startPage":0,"pageSize":999999},
				bAsync      : false,
				fnCallback  : function(oResponse) {

					if (oResponse.operationSuccess == true){
						 for(i=0;oResponse.locationList.length > i;i++){
							if(oResponse.locationList[i].name === sName){
								bReturn =  true;
							}
						 }
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
					$.pgsi.progressBar.stop();
				}
			});

			return bReturn;

		},
		fnPlanCategoryID : function(){
			$("#currencyId").val();
			$("#countryId").val();
			$("#payerId").val();
			$("#payerId").val();

		},

		fnInitBusinessPlanList : function(businessProductPlanList,iPlanId,oTransferSettings){

			sPayer          = $('#payer').val();
			sdestcountry	= $('#destcountry').val();
			sMethodTransfer	= $('#methodTransfer').val();
			var sPayers  			= "<option></option>";
			var sMethodTransfers    = "<option></option>";
			var sProductPlan		= "<option></option>";
			var sProductPlanCategory= "<option></option>";
			var sCurrencys 			= "<option></option>";
			var sCountry 			= "<option></option>";
			var sBusinessPlan		= "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){
				var oProductPlanApplicability = oTransferSettings.productPlanApplicability;
				var oPayer = oProductPlanApplicability.payer;
				for(var y = 0 ;y < businessProductPlanList.length ;y++){


						var productPlanApplicability = businessProductPlanList[y].productPlanApplicabilityList;

						if(!$.pgsi.isNullOrUndefined(productPlanApplicability)){

							for(var i = 0 ;i < productPlanApplicability.length ;i++){

								if($.inArray(businessProductPlanList[y].name.trim(), aCurrencys) === -1){
									sProductPlan = sProductPlan + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + businessProductPlanList[y].id + "'class='"+businessProductPlanList[y].id+"'>" + businessProductPlanList[y].name+ "</option>";
									aCurrencys.push(businessProductPlanList[y].name.trim());
								}

								var oPlanCategory = businessProductPlanList[y].planCategoryList;

								if(!$.pgsi.isNullOrUndefined(oPlanCategory[0])){
									for(var iCount = 0 ;iCount < oPlanCategory.length ;iCount++){
										if(oTransferSettings.productPlanApplicability.productPlanId == oPlanCategory[iCount].productPlanId){
											if($.inArray(oPlanCategory[iCount].name.trim(), aCurrencys) === -1){
												sBusinessPlan = sBusinessPlan + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + oPlanCategory[iCount].id + "'class='"+productPlanApplicability[i].id+"'>" + oPlanCategory[iCount].name + "</option>";
												aCurrencys.push(oPlanCategory[iCount].name.trim());
											}
										}
									}
								}

								if($.inArray(productPlanApplicability[i].payer.name.trim(), aCurrencys) === -1){
									if((oTransferSettings.productPlanApplicability.productPlanId == productPlanApplicability[i].productPlanId)&&(productPlanApplicability[i].paymentType.trim() == oProductPlanApplicability.paymentType)){
										sPayers = sPayers + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.id + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.name+ "</option>";
										aCurrencys.push(productPlanApplicability[i].payer.name.trim());
									}
								}

								if(oTransferSettings.productPlanApplicability.productPlanId == productPlanApplicability[i].productPlanId){
									if(oPayer.country.code == productPlanApplicability[i].payer.country.code){
										for(var iCount = 0; iCount < productPlanApplicability[i].payer.country.currencyList.length; iCount++){
											if($.inArray(productPlanApplicability[i].payer.country.currencyList[iCount].name.trim(), aCurrencys) === -1){
												sCurrencys = sCurrencys + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.country.currencyList[iCount].code + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.country.currencyList[iCount].name+ "</option>";
												aCurrencys.push(productPlanApplicability[i].payer.country.currencyList[iCount].name.trim());
											}
										}
									}
								}



								if($.inArray(productPlanApplicability[i].paymentType.trim(), aCurrencys) === -1){
									if(oTransferSettings.productPlanApplicability.productPlanId == productPlanApplicability[i].productPlanId){
										sMethodTransfers = sMethodTransfers + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].paymentTypeValue + "'class='"+productPlanApplicability[i].id+"'>" +$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum", productPlanApplicability[i].paymentType) + "</option>";
										aCurrencys.push(productPlanApplicability[i].paymentType.trim());
									}
								}



								if($.inArray(productPlanApplicability[i].payer.country.description.trim(), aCurrencys) === -1){
									if(oTransferSettings.productPlanApplicability.productPlanId == productPlanApplicability[i].productPlanId){
										sCountry = sCountry + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.country.code + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.country.description+ "</option>";
										aCurrencys.push(productPlanApplicability[i].payer.country.description);
									}
								}
							}
						}

				}

			}

			if(!$.pgsi.isNullOrUndefined(sProductPlan)){

				$('#pricing').empty();
				$('#pricing').append(sProductPlan);
				pgsi.util.page.form.fnInitSelectmenu($('#pricing'));
				$('#pricing').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}

			if(!$.pgsi.isNullOrUndefined(sCountry)){
				$('#destcountry').empty();
				$('#destcountry').append(sCountry);
				pgsi.util.page.form.fnInitSelectmenu($('#destcountry'));
				$('#destcountry').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}

			if(!$.pgsi.isNullOrUndefined(sCurrencys)){

				$('#currency').empty();
				$('#currency').append(sCurrencys);
				pgsi.util.page.form.fnInitSelectmenu($('#currency'));
				$('#currency').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}

			if(!$.pgsi.isNullOrUndefined(sMethodTransfers)){

				$('#methodTransfer').empty();
				$('#methodTransfer').append(sMethodTransfers);
				pgsi.util.page.form.fnInitSelectmenu($('#methodTransfer'));
				$('#methodTransfer').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}

			if(!$.pgsi.isNullOrUndefined(sPayers)){

				$('#payer').empty();
				$('#payer').append(sPayers);
				pgsi.util.page.form.fnInitSelectmenu($('#payer'));
				$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}

			if(!$.pgsi.isNullOrUndefined(sBusinessPlan)){

				$('#planCategoryId').empty();
				$('#planCategoryId').append(sBusinessPlan);
				pgsi.util.page.form.fnInitSelectmenu($('#planCategoryId'));

				$('#planCategoryId').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}

		},

		fnInitList : function(businessProductPlanList,$planCategory,iPlanId){
			sPayer          = $('#payer').val();
			sdestcountry	= $('#destcountry').val();
			sMethodTransfer	= $('#methodTransfer').val();
			var sPayers  			= "<option></option>";
			var sMethodTransfers    = "<option></option>";
			var sProductPlan		= "<option></option>";
			var sProductPlanCategory= "<option></option>";
			var sCurrencys 			= "<option></option>";
			var sCountry 			= "<option></option>";
			var sBusinessPlan		= "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){

				for(var y = 0 ;y < businessProductPlanList.length ;y++){

					var productPlanApplicability = businessProductPlanList[y].productPlanApplicabilityList;

					if(!$.pgsi.isNullOrUndefined(productPlanApplicability[0])){

						for(var i = 0 ;i < productPlanApplicability.length ;i++){

							if($.inArray(businessProductPlanList[y].name.trim(), aCurrencys) === -1){
								sProductPlan = sProductPlan + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + businessProductPlanList[y].id + "'class='"+businessProductPlanList[y].id+"'>" + businessProductPlanList[y].name+ "</option>";
								aCurrencys.push(businessProductPlanList[y].name.trim());
							}

							var oPlanCategory = businessProductPlanList[y].planCategoryList;

							if(!$.pgsi.isNullOrUndefined(oPlanCategory[0])){
								for(var iCount = 0 ;iCount < oPlanCategory.length ;iCount++){
									if($.inArray(oPlanCategory[iCount].name.trim(), aCurrencys) === -1){
										sBusinessPlan = sBusinessPlan + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].productPlanId + "'class='"+productPlanApplicability[i].id+"'>" + oPlanCategory[iCount].name + "</option>";
										aCurrencys.push(oPlanCategory[iCount].name.trim());
									}
								}
							}

							if($.inArray(productPlanApplicability[i].payer.name.trim(), aCurrencys) === -1){
								sPayers = sPayers + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.id + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.name+ "</option>";
								aCurrencys.push(productPlanApplicability[i].payer.name.trim());
							}

							if($.inArray(productPlanApplicability[i].currency.name.trim(), aCurrencys) === -1){
								sCurrencys = sCurrencys + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].currency.code + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].currency.name+ "</option>";
								aCurrencys.push(productPlanApplicability[i].currency.name.trim());
							}


							if($.inArray(productPlanApplicability[i].paymentType.trim(), aCurrencys) === -1){
								sMethodTransfers = sMethodTransfers + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].paymentTypeValue + "'class='"+productPlanApplicability[i].id+"'>" +$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum", productPlanApplicability[i].paymentType) + "</option>";
								aCurrencys.push(productPlanApplicability[i].paymentType.trim());
							}



							if($.inArray(productPlanApplicability[i].payer.country.description.trim(), aCurrencys) === -1){
								sCountry = sCountry + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.country.code + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.country.description+ "</option>";
								aCurrencys.push(productPlanApplicability[i].payer.country.description);
							}
						}
					}
				}

			}


			if(!$.pgsi.isNullOrUndefined(sProductPlan)){

				$('#pricing').empty();
				$('#pricing').append(sProductPlan);
				pgsi.util.page.form.fnInitSelectmenu($('#pricing'));
				$('#pricing').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			}

			$('#destcountry').empty();
			$('#destcountry').append("<option></option>");
			$('#destcountry').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#destcountry'));
			$('#destcountry').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#currency').empty();
			$('#currency').append("<option></option>");
			$('#currency').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#currency'));
			$('#currency').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#methodTransfer').empty();
			$('#methodTransfer').append("<option></option>");
			$('#methodTransfer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#methodTransfer'));
			$('#methodTransfer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#payer').empty();
			$('#payer').append("<option></option>");
			$('#payer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#payer'));
			$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

		},

		fnPricingList : function(businessProductPlanList,$planCategory,iPlanId){

			var sBusinessPlan		= "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){

				for(var y = 0 ;y < businessProductPlanList.length ;y++){

					var productPlanApplicability = businessProductPlanList[y];

					if(!$.pgsi.isNullOrUndefined(productPlanApplicability)){
						if(productPlanApplicability.id == iPlanId){

							var oPlanCategory = productPlanApplicability.planCategoryList;

							if(!$.pgsi.isNullOrUndefined(oPlanCategory[0])){
								for(var iCount = 0 ;iCount < oPlanCategory.length ;iCount++){
									if($.inArray(oPlanCategory[iCount].name.trim(), aCurrencys) === -1){

										sBusinessPlan = sBusinessPlan + "<option id='"+iPlanId+"' value='" + oPlanCategory[iCount].id + "'class='"+iPlanId+"'>" + oPlanCategory[iCount].name + "</option>";
										aCurrencys.push(oPlanCategory[iCount].name.trim());
									}
								}
							}

						}
					}
				}

			}

			if(!$.pgsi.isNullOrUndefined(sBusinessPlan)){

				$('#planCategoryId').empty();
				$('#planCategoryId').append(sBusinessPlan);
				pgsi.util.page.form.fnInitSelectmenu($('#planCategoryId'));

				$('#planCategoryId').selectmenu('refresh').nextAll(".ui-selectmenu-button");

				if(!$.pgsi.isNullOrUndefined(sMethodTransfer)){
					$('#planCategoryId').val(sMethodTransfer);
					$('#planCategoryId-button .ui-selectmenu-text').text($('#methodTransfer option:selected').text());
				}

			}

			$('#destcountry').empty();
			$('#destcountry').append("<option></option>");
			$('#destcountry').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#destcountry'));
			$('#destcountry').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#currency').empty();
			$('#currency').append("<option></option>");
			$('#currency').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#currency'));
			$('#currency').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#methodTransfer').empty();
			$('#methodTransfer').append("<option></option>");
			$('#methodTransfer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#methodTransfer'));
			$('#methodTransfer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#payer').empty();
			$('#payer').append("<option></option>");
			$('#payer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#payer'));
			$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

		},

		fnPlanCategoryList : function(businessProductPlanList,$planCategory,iPlanApplication,sLabel,sValue){

			var sCountry 			= "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){

				for(var y = 0 ;y < businessProductPlanList.length ;y++){
					var productPlanApplicability = businessProductPlanList[y].productPlanApplicabilityList;
					if(!$.pgsi.isNullOrUndefined(productPlanApplicability[0])){

						for(var i = 0 ;i < productPlanApplicability.length ;i++){

							if(!$.pgsi.isNullOrUndefined(sValue)){

								if(productPlanApplicability[i].productPlanId == iPlanApplication){

									if($.inArray(productPlanApplicability[i].payer.country.description.trim(), aCurrencys) === -1){
										sCountry = sCountry + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.country.code + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.country.description+ "</option>";
										aCurrencys.push(productPlanApplicability[i].payer.country.description);
									}

								}

							}
						}
					}
				}
			}

			if(!$.pgsi.isNullOrUndefined(sCountry)){

				$('#destcountry').empty();
				$('#destcountry').append(sCountry);
				pgsi.util.page.form.fnInitSelectmenu($('#destcountry'));
				$('#destcountry').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			}

			$('#currency').empty();
			$('#currency').append("<option></option>");
			pgsi.util.page.form.fnInitSelectmenu($('#currency'));
			$('#currency').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			$('#currency').addClass('required').removeClass('hide').prop("placeholder","*");

			$('#methodTransfer').empty();
			$('#methodTransfer').append("<option></option>");
			$('#methodTransfer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#methodTransfer'));
			$('#methodTransfer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			$('#payer').empty();
			$('#payer').append("<option></option>");
			$('#payer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#payer'));
			$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

		},

		fnCurrencyList : function(businessProductPlanList,$planCategory,iPlanApplication,sLabel,sValue){

			var sMethodTransfers    = "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){
				for(var y = 0 ;y < businessProductPlanList.length ;y++){
					var productPlanApplicability = businessProductPlanList[y].productPlanApplicabilityList;

					if(!$.pgsi.isNullOrUndefined(productPlanApplicability[0])){
						for(var i = 0 ;i < productPlanApplicability.length ;i++){

							if(!$.pgsi.isNullOrUndefined(sValue)){
								if(productPlanApplicability[i].currency.name.trim() == sLabel.trim()){

									if($.inArray(productPlanApplicability[i].paymentType.trim(), aCurrencys) === -1){
										sMethodTransfers = sMethodTransfers + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].paymentTypeValue + "'class='"+productPlanApplicability[i].id+"'>" +$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum", productPlanApplicability[i].paymentType) + "</option>";
										aCurrencys.push(productPlanApplicability[i].paymentType.trim());
									}

								}

							}
						}
					}
				}
			}

			if(!$.pgsi.isNullOrUndefined(sMethodTransfers)){

				$('#methodTransfer').empty();
				$('#methodTransfer').append(sMethodTransfers);
				pgsi.util.page.form.fnInitSelectmenu($('#methodTransfer'));
				$('#methodTransfer').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			}

			$('#payer').empty();
			$('#payer').append("<option></option>");
			$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			$('#payer').addClass('required').removeClass('hide').prop("placeholder","*");
			pgsi.util.page.form.fnInitSelectmenu($('#payer'));

		},

		fnPayerList : function(businessProductPlanList,$planCategory,iPlanApplication,sLabel,sValue){



		},
		fnMethodTransferList : function(businessProductPlanList,$planCategory,iPlanApplication,sLabel,sValue){

			var sPayers  	= "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){
				for(var y = 0 ;y < businessProductPlanList.length ;y++){
					var productPlanApplicability = businessProductPlanList[y].productPlanApplicabilityList;

					if(!$.pgsi.isNullOrUndefined(productPlanApplicability[0])){
						for(var i = 0 ;i < productPlanApplicability.length ;i++){

							if(!$.pgsi.isNullOrUndefined(sValue)){

								if((productPlanApplicability[i].paymentType.trim() == sLabel)&&(iPlanApplication == businessProductPlanList[y].id)){

									if($.inArray(productPlanApplicability[i].payer.name.trim(), aCurrencys) === -1){
										sPayers = sPayers + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.id + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.name+ "</option>";
										aCurrencys.push(productPlanApplicability[i].payer.name.trim());
									}

								}
							}
						}
					}
				}
			}
			if(!$.pgsi.isNullOrUndefined(sPayers)){

				$('#payer').empty();
				$('#payer').append(sPayers);
				pgsi.util.page.form.fnInitSelectmenu($('#payer'));
				$('#payer').selectmenu('refresh').nextAll(".ui-selectmenu-button");
			}



		},
		fnCountryList : function(businessProductPlanList,$planCategory,iPlanApplication,sLabel,sValue){

			var sCurrencys 			= "<option></option>";
			var aCurrencys  = [];

			if(!$.pgsi.isNullOrUndefined(businessProductPlanList[0])){
				for(var y = 0 ;y < businessProductPlanList.length ;y++){

					var productPlanApplicability = businessProductPlanList[y].productPlanApplicabilityList;


					if(!$.pgsi.isNullOrUndefined(productPlanApplicability[0])){
						for(var i = 0 ;i < productPlanApplicability.length ;i++){

							if(!$.pgsi.isNullOrUndefined(sValue)){

								if(productPlanApplicability[i].payer.country.description.trim() == sLabel){
									for(var iCount = 0; iCount < productPlanApplicability[i].payer.country.currencyList.length; iCount++){
										if($.inArray(productPlanApplicability[i].payer.country.currencyList[iCount].name.trim(), aCurrencys) === -1){
											sCurrencys = sCurrencys + "<option id='"+productPlanApplicability[i].productPlanId+"' value='" + productPlanApplicability[i].payer.country.currencyList[iCount].code + "'class='"+productPlanApplicability[i].id+"'>" + productPlanApplicability[i].payer.country.currencyList[iCount].name+ "</option>";
											aCurrencys.push(productPlanApplicability[i].payer.country.currencyList[iCount].name.trim());
										}
									}
								}
							}
						}
					}
				}
			}

			if(!$.pgsi.isNullOrUndefined(sCurrencys)){
				var iCurency = $('#currency').val();
				$('#currency').empty();
				$('#currency').append(sCurrencys);
				pgsi.util.page.form.fnInitSelectmenu($('#currency'));
				$('#currency').selectmenu('refresh').nextAll(".ui-selectmenu-button");

			}
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

		},

		//Set up Required Selects
	initRequiredSelects : function() {
		$( "span.ui-selectmenu-text" ).each(
			function(index) {
				if (!$(this).text().trim())
				{
					$(this).parent( "span.ui-selectmenu-button" ).addClass( "required" );
				}
				else
				{
					$(this).parent( "span.ui-selectmenu-button" ).removeClass( "required" );
				}
			});
	}
}

</script>

</sec:authorize>