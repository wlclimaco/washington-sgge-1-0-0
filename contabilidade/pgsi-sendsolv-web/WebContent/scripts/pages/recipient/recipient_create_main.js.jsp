<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.recipient.create = {

		form :{

			validator : $("#recipient-form").validate({
				ignore : "",
				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
							else {
							$(value.element).addClass("error");
						}
					});
				}
			}),

			validator :	$( "#recipient-form" ).validate(),

			fnInitMaskForm : function() {
				$("#ssn").mask("999-99-9999");
				$("#dob").mask("99/99/9999");
			},
			fillValidation : function() {

				if(parseInt($("#create-contact").find("#contact-risk").val()) === 4 ){
					$('#riskNote').removeClass("required required-field");
					$('#riskNote').removeClass("error");
					$('#riskNote').prop("placeholder", "");
				}
				else {
					$('#riskNote').addClass("required required-field");
					$('#riskNote').prop("placeholder", "*");
				}


			},
			fnInitFormDialog : function() {
				$('#section-document').hide();
				$('#section-risk').hide();
				$('#section-note').hide();
				$('#section-employment').hide();
				$('.buttons-form').hide();
				$("#residence-button").outerWidth(200);
				$("#country-button").outerWidth(255);
				$( "#prefix-button" ).outerWidth(100);
				$("#infoMember").hide();
				$(".dob-dialog").removeClass('hide');
				$(".dob-create").addClass('hide');
			},

			fnInitFormDialogTransfer : function() {
				$('#section-document').hide();
				$('#section-employment').hide();
				$('.buttons-form').hide();
				$("#residence-button").outerWidth(200);
				$("#country-button").outerWidth(255);
				$( "#prefix-button" ).outerWidth(100);
				$("#infoMember").hide();
				$(".dob-dialog").removeClass('hide');
				$(".dob-create").addClass('hide');
			},

			fnInitForm : function() {

				pgsi.util.page.form.fnInitForm();
				pgsi.util.page.person.otherNames.form.fnInit(
						$('#otherNamesTemplate').find('div'),
						!pgsi.util.page.fnCheckXSS($.address.parameter('recipientId')) ? $.address.parameter('recipientId') : null
					);
				pgsi.pages.recipient.create.form.setFieldSizes();
				pgsi.pages.address.form.makeItOptional();

				$("#prev-button").click(function() {

					if (!pgsi.util.page.fnCheckXSS($.address.parameter("member").split("|")[0])) {
						$.pgsi.pageLoader.load({
							url : "member/add?memberId=" + $.address.parameter("member").split("|")[0],
							$content : $("#load"),
							bStartProgressBar : false
						});
					}
				});

				$("#cancel").click(function(e) {
					$.pgsi.pageLoader.load({
						url : "member",
						$content : $("#load"),
						bStartProgressBar : false
					});
				});

				$("#risk").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum"));

				$("#risk").on("selectmenuchange", function( event, ui ) {
					if (parseInt(ui.item.value) !== 3) {
						if ($("#risknote").val().length === 0) {
							$("#risknote").addClass("required").addClass("error").prop("placeholder","*");
						}
					}
					else {
						$("#risknote").removeClass("required").removeClass("error").prop("placeholder","");
					}
				} );

				$('#risk-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum","UNKNOWN"));
				$("#risk").val(3).prop('selected',true);

				// fill Mask
				pgsi.pages.recipient.create.form.fnInitMaskForm();

				$("input[type='button'], input[type='submit']").button();

			},

			setFieldSizes : function() {

				pgsi.util.page.email.form.setFieldSizes();
				pgsi.pages.address.form.setFieldSizes();
				pgsi.pages.phone.form.setFieldSizes();

			},
			fillClearFilds : function() {


			},

			fillObject : function(oResponse) {

				$('#recipient-id').val(oResponse.id);
				$("#motherMaidenName").val(oResponse.mothersMaidenName);

				if (!$.pgsi.isNullOrUndefined(oResponse.suffix)) {
					$("#suffix").val(oResponse.suffix.id).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
				}

				if (!$.pgsi.isNullOrUndefined(oResponse.prefix)){
					$("#prefix").val(oResponse.prefix.id).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				}

				$("#risk").val(oResponse.risk.riskLevelValue).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
					pgsi.pages.risk.form.removeLoweRiskOptions(oResponse.risk.riskLevelValue, $("#risk"));
				</sec:authorize>

				$('#risknote').val(oResponse.risk.riskLevelNote);

				if (parseInt($("#risk").val()) === 3) {
					$('#risknote').removeClass("required").prop("placeholder", "");
				}

				else {
					$('#risknote').addClass("required").prop("placeholder", "*");
				}

				$('#firstname').val(oResponse.firstName);
				$('#middlename').val(oResponse.middleName);
				$('#lastname').val(oResponse.lastName);

				if (!$.pgsi.isNullOrUndefined(oResponse.dateOfBirth)) {
					$("#dob").val($.pgsi.date.format(new Date(oResponse.dateOfBirth), "mm/dd/yy", null));
				}

				if(oResponse.noteList !== null){
					if(oResponse.noteList.length > 0){
						$('#note').val(oResponse.noteList[0].noteText);
					}
				}

				// fill phone fields
				pgsi.pages.phone.form.fillFormFields(oResponse.contactList);
				// fill address fields
				pgsi.pages.address.form.fillFormFields(oResponse.contactList);
				// fill email fields
				pgsi.util.page.email.form.fillFormFields(oResponse.contactList);
				// fill other names
				pgsi.util.page.person.otherNames.form.fnFill(oResponse.nameList, $('#otherNamesTemplate').find('div').first());


			},
			fillForm : function() {
				$('#firstname').val("firstname");
				$('#middlename').val("middlename");
				$('#lastname').val("lastname");
				$('#suffix').val(1);
				$('#othernames').val("othernames");
				$('#dob').val("10/10/2010");
				$('#ssn').val("111111111");
				$('.phone-type').val(2);
				$('.phone-country').val("USA");
				$('.phone-number').val("1111111");
				$('.phone-extension').val("10");
				$('.email-type').val(1);
				$('.email-address').val("teste@outmail.com");
				$('#street-address-line-1').val("Av Joao Pinheiro 540");
				$('#city').val("Uberaba");
				$('#zip-postal-code').val("38082-0243");
				$('.valueOthernames').val("teste@outmail.com");
				$('#risk').val(1);
				$('#risknote').val("risknote");
				$('#note').val("note text");
			},


			fillPerson : function (sModelAction,request) {

				request.mothersMaidenName  = $("#motherMaidenName").val();
				request.firstName            = $("#firstname").val();
				request.middleName           = $("#middlename").val();
				request.lastName             = $("#lastname").val();
				request.personStatusValue    = $("#status-value").val();
				request.genderValue          = 1;
				request.personTypeValue      = 3;
				request.id                   = parseInt($("#recipient-id").val(),10);
				dDate 						 = new Date($("#dob").val());
				request.dateOfBirth			 = dDate.getTime();
				request.suffix     			 = {id : parseInt($("#suffix").val(),10)};
				request.prefix     			 = {id : parseInt($("#prefix").val(),10)};
				request.modelAction 		 = sModelAction;

				if(sModelAction != "UPDATE"){
					if (!$.pgsi.isNullOrUndefined($("#note").val())){
						request.noteList 			 = [{ noteText :$("#note").val() ,modelAction   : sModelAction}];
					}
				}
				request.risk 				 =  new Risk({
												riskLevelValue: parseInt($("#risk").val(),10),
												riskLevelNote : $("#risknote").val(),
												modelAction   : sModelAction,
												version       : 10
											})

				request.contactList = new Array();

				request.modelAction = sModelAction;

				var oAddress = pgsi.pages.address.form.fillObject(sModelAction);

				if (!$.pgsi.isNullOrUndefined(oAddress)) {
					request.contactList.push(oAddress);
				}

				request.nameList = pgsi.util.page.person.otherNames.form.fnCreateArray($('#otherNamesTemplate').find('div'));

				var aPhones = pgsi.pages.phone.form.fillObject();

				for (var i=0; i < aPhones.length; i++) {
					request.contactList.push(aPhones[i]);
				}

				var aEmails = pgsi.util.page.email.form.fillObject();

				for (var i = 0; i < aEmails.length; i++) {
					request.contactList.push(aEmails[i]);
				}

				request.version = pgsi.version.versionRecipient;

				return request;


			},
			fillRequest : function(sModelAction) {


				request = new Recipient();

				//request fill person
				request = pgsi.pages.recipient.create.form.fillPerson(sModelAction,request);


				return new RecipientMaintenanceRequest({recipient: request});

			},
			fnAjaxCallFetchAll : function(iId){
				$.pgsi.ajax.post({
					sUrl 		: "api/recipient/fetch",
					oRequest 	: {id:iId},
					fnCallback  : function(oResponse) {
						if(oResponse.operationSuccess == true){
							pgsi.pages.recipient.view.fnFillFields(oResponse);
						}else{
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
					}
				});
			},
			fnAjaxCallInsertUpdateRecipient : function(iRecipientId,fnFillCallBack,bTransfer){

				var bValidForm = pgsi.pages.recipient.create.form.validator.form();
				if (bValidForm){
					if(parseInt(iRecipientId,10) != 0){
						var sUrl = 'api/recipient/update',
						    sModelAction = "UPDATE";
					}else{
						var sUrl = 'api/recipient/insert',
						    sModelAction = "INSERT";
					}

					$.pgsi.ajax.post({
						sUrl 		: sUrl,
						oRequest 	: pgsi.pages.recipient.create.form.fillRequest(sModelAction),
						fnCallback  : function(oResponse) {

							if (oResponse.operationSuccess == true){
								 fnFillCallBack(oResponse);
								 $.pgsi.progressBar.stop();
							}else{
								fnCallBackError = function(oResponse) {

									pgsi.version.versionRecipient = oResponse.recipientList[0].version;
									pgsi.pages.recipient.create.form.fillObject(oResponse.recipientList[0]);
									$("#action-dialog-Error").dialog('close');
									 $.pgsi.progressBar.stop();
								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/recipient/fetch",{id:iRecipientId},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Recipient"),false);
							}
						}
					});

				}
			}

		},
		fnDelete : function(iId) {

			var fnCallBack = function(oResponse) {

				if (oResponse.operationSuccess == true) {

					pgsi.pages.contact.form.fnAjaxCallFetchAll(parseInt($('#recipient-id').val(),10));

				}else{
					pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
				}
			}
			sTitle = $.pgsi.locale.get("pages.business.dialog.contact.title.delete",$('#company-name-field').text());
			pgsi.util.actiondialog.launchActionDialog("deleteDialog", pgsi.pages.business.dialogSettings.deleteDialog("api/recipient/delete", new RecipientMaintenanceRequest({recipient : {id : iId,modelAction : 'DELETE' }}),sTitle,fnCallBack,$.pgsi.locale.get("commons.pages.erroView","Employment")));
		},

		fnDisableActiveDesctive : function(iId,iAcao,fnFillCallBack){

			$.pgsi.ajax.post({
				sUrl 		: "api/recipient/applyStatus",
				oRequest 	: new RecipientMaintenanceRequest({recipient : {id : iId,personStatusValue : iAcao,modelAction:"UPDATE"}}),
				fnCallback  : function(oResponse) {

					if (oResponse.operationSuccess == true){
						 fnFillCallBack(oResponse);
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}
			});

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