<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.member.create = {

		form : {

			/**
			 * Validate the fields required at Organization Form
			 */
			validator : $("#member-form").validate({
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
					memberLocation: {
						required: true,
						locationValid:18
					}
				}
			}),

			fnInitMaskForm : function() {
				$("#ssn").mask("999-99-9999");
				$("#idexpirationdate").mask("99/99/9999");
				$("#hiredate").mask("99/99/9999");
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
				$('#section-document').hide()
				$('#section-risk').hide()
				$('#section-note').hide()
				$('#section-employment').hide()
				$('.buttons-form').hide()
				$('.location-member').hide()
			},


			fnInitForm : function() {
				pgsi.pages.address.form.makeItOptional();
				pgsi.util.page.form.fnInitForm();
				pgsi.util.page.person.otherNames.form.fnInit(
					$('#otherNamesTemplate').find('div'),
					!pgsi.util.page.fnCheckXSS($.address.parameter('memberId')) ? $.address.parameter('memberId') : null
				);
				pgsi.pages.member.create.form.setFieldSizes();

				// Load dropdown fields
				$("#risk").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum"));
				$('#risk-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum","UNKNOWN"));
				$("#risk").val(3).prop('selected',true);
				$("#gender").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.cbof.model.GenderEnum"));

				// fill Mask
				pgsi.pages.member.create.form.fnInitMaskForm();

				$(".buttons-form").find("input[type='button']").button();
				$(".buttons-form").find("input[type='submit']").button();

				// Note Required if Risk Level is other than Unknown
				$("#risk").on("selectmenuchange", function( event, ui ) {

					if (ui.item.value !== "3") {
						$("#risknote").addClass("required").prop("placeholder", "*");
					}
					else {
						$("#risknote").removeClass("required").prop("placeholder", "");
					}

				});

				// Remove first line border
				var sMemberId = $.address.parameter("memberId");

				$("input#currentpay").inputmask();

			},

			setFieldSizes : function() {

				$("#residence-button").outerWidth(200);
				$("#country-button").outerWidth(255);
				$("#birth-button").outerWidth(200);
				$("#citizenship-button").outerWidth(200);
				$( "#prefix-button" ).outerWidth(100);
				$("#location").attr("name","location");

				pgsi.pages.phone.form.setFieldSizes();
				pgsi.util.page.email.form.setFieldSizes();
				pgsi.pages.address.form.setFieldSizes();
				pgsi.pages.security.question.form.setFieldSizes();

			},

			fillObject : function(oResponse) {

				$('#motherMaidenName').val(oResponse.mothersMaidenName);
				$('#member-id').val(oResponse.id);
				if (!$.pgsi.isNullOrUndefined(oResponse.suffix)){
					$('#suffix-button .ui-selectmenu-text').text(oResponse.suffix.value);
					$("#suffix").val(oResponse.suffix.id).prop('selected',true);
					$('#suffix-button').removeClass("required-field");
				}
				// Prefix valid is null
				if (!$.pgsi.isNullOrUndefined(oResponse.prefix)){
					$('#prefix-button .ui-selectmenu-text').text(oResponse.prefix.value);
					$("#prefix").val(oResponse.prefix.id).prop('selected',true);
					$('#prefix-button').removeClass("required-field");
				}
				$('#risk-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum", oResponse.risk.riskLevel));
				$("#risk").val(oResponse.risk.riskLevelValue).prop('selected',true);
				$('#risk-button').removeClass("required-field");
				$('#risknote').val(oResponse.risk.riskLevelNote);

				$('#location').val("");
				$("#location-id").val(oResponse.locationId)
				$('#firstname').val(oResponse.firstName);
				$('#middlename').val(oResponse.middleName);
				$('#lastname').val(oResponse.lastName);

				if (!$.pgsi.isNullOrUndefined(oResponse.dateOfBirth)) {
					$("#dob").val($.pgsi.date.format(new Date(oResponse.dateOfBirth), "mm/dd/yy", null));
				}

				// SSN
				$('#ssn').val(oResponse.socialSecurityNumber);
				$('#ssn-id').val(oResponse.socialSecurityNumber);

				//Document
				if (oResponse.documentList != null) {
					if (oResponse.documentList.length > 0) {
						for (i =0;i < oResponse.documentList.length;i++) {
							document = oResponse.documentList[i];
							$('#document-id').val(document.id);
							if (!$.pgsi.isNullOrUndefined(document.documentType)){
								$('#document-type').val(document.documentType.id);
								$('#document-type-button').text(document.documentType.name);
							}

							$('#identification-country').val(document.issueCountry);
							$('#identification-region').val(document.issueStateProvinceRegion);
							$("#idexpirationdate").val($.pgsi.date.format(new Date(document.expirationDate), "mm/dd/yy", null));
							$("#idnumber").val(document.value);
						}
					}
				}

				// Language
				if(oResponse.preferredLanguage != null){
					$('#preferredLanguage-button .ui-selectmenu-text').text(oResponse.preferredLanguage.name);
					$("#preferredLanguage").val(oResponse.preferredLanguage.id).prop('selected',true);
					$('#preferredLanguage-button').removeClass("required-field");
				}
				$('#timetocall').val(oResponse.bestTimeToCall);
				$("#gender").val(oResponse.genderValue).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				if(oResponse.countryUsageList != null){
					countryUsageList = oResponse.countryUsageList;
					pgsi.version.versionCountryUsage = countryUsageList[0].version;

					for (i=0; i < countryUsageList.length; i++) {
						if(countryUsageList[i].usageValue == 3){
							$('#birth-button .ui-selectmenu-text').text(countryUsageList[i].country.description);
							$("#birth").val(countryUsageList[i].country.code).prop('selected',true);
							$('#birth-button').removeClass("required-field");
							$('#birthId').val(countryUsageList[i].id);
						} else if(countryUsageList[i].usageValue == 2){
							$('#residence-button .ui-selectmenu-text').text(countryUsageList[i].country.description);
							$("#residence").val(countryUsageList[i].country.code).prop('selected',true);
							$('#residence-button').removeClass("required-field");
							$('#residenceId').val(countryUsageList[i].id);
						}else if(countryUsageList[i].usageValue == 1){
							$('#citizenship-button .ui-selectmenu-text').text(countryUsageList[i].country.description);
							$("#citizenship").val(countryUsageList[i].country.code).prop('selected',true);
							$('#citizenship-button').removeClass("required-field");
							$('#citizenshipId').val(countryUsageList[i].id);
						}
					}
				}
				if(oResponse.noteList !== null){
					if(oResponse.noteList.length > 0) {
						$('#note').val(oResponse.noteList[0].noteText);
						$('#noteId').val(oResponse.noteList[0].id);
					}
				}

				// fill EmploymentInfo
				if(oResponse.employmentInfoList != null) {
					if(oResponse.employmentInfoList.length > 0){
						var oEmploymentInfoList = oResponse.employmentInfoList[0];
						$('#hiredate').val(oEmploymentInfoList.hireDate);
						$('#currentpay').val(oEmploymentInfoList.payPerPeriod);
						$('#location').val(oEmploymentInfoList.locationName);
						$('#location-id').val(oEmploymentInfoList.locationId);

						// Fill and display exmploymentinfo fields
						if (!$.pgsi.isNullOrUndefined(oEmploymentInfoList.id)) {
							$('#employmentInfoId').val(oEmploymentInfoList.id);
							$("#employmentInfoVersion").val(oEmploymentInfoList.version);
							$('#employeeId').val(oEmploymentInfoList.employeeId);
							$("#jobtitle").val(oEmploymentInfoList.jobTitle);
							if (!$.pgsi.isNullOrUndefined(oEmploymentInfoList.hireDate)) {
								$("#hiredate").val($.pgsi.date.format(new Date(oEmploymentInfoList.hireDate), "mm/dd/yy", null));
							}
							$("#employment-section").show();
							$("#toggle-employment").hide();
						}
					}

				}

				// fill phone fields
				pgsi.pages.phone.form.fillFormFields(oResponse.contactList);
				// fill address fields
				pgsi.pages.address.form.fillFormFields(oResponse.contactList);
				// fill email fields
				pgsi.util.page.email.form.fillFormFields(oResponse.contactList);
				// fill security question
				pgsi.pages.security.question.form.fillFormFields(oResponse.securityAnswerList);
				// fill other names
				pgsi.util.page.person.otherNames.form.fnFill(oResponse.nameList, $('#otherNamesTemplate').find('div').first());

				pgsi.version.versionMember = oResponse.version;
			},

			fillEmploymentInfo : function (sModelAction) {

 				oEmploymentInfo = new EmploymentInfo();
 				if (!$.pgsi.isNullOrUndefined($("#employmentInfoId").val())) {
 					oEmploymentInfo.id 					  = $("#employmentInfoId").val();
 				}
 				if (!$.pgsi.isNullOrUndefined($("#employmentInfoVersion").val())) {
 					oEmploymentInfo.version				  = $("#employmentInfoVersion").val();
 				}
 				oEmploymentInfo.memberId 				  = $("#member-id").val();

 				if (!$.pgsi.isNullOrUndefined($('#jobtitle').val())) {
 					oEmploymentInfo.jobTitle  				  = $("#jobtitle").val();
 				}

 				if (!$.pgsi.isNullOrUndefined($('#employeeId').val())) {
 					oEmploymentInfo.employeeId            = $('#employeeId').val();
 				}

				if (!pgsi.util.page.fnCheckXSS($.address.parameter("tab")) && $.pgsi.isNullOrUndefined($.address.parameter("tab"))
					&& !$.pgsi.isNullOrUndefined(sessionStorage.locationId))
				{
 						oEmploymentInfo.locationId = sessionStorage.locationId;
 				}

 				else {
 					oEmploymentInfo.locationId = $("#location-id").val();
 				}

				oEmploymentInfo.locationName              = $('#location').val();

				if (!$.pgsi.isNullOrUndefined($("#currentpay").val())) {
					oEmploymentInfo.payPerPeriod              = $('#currentpay').val().substring(4).replace(",", "");
				}

				dDate 						              = new Date($('#hiredate').val());

				if (!$.pgsi.isNullOrUndefined(dDate)) {
					oEmploymentInfo.hireDate = dDate.getTime();
				}

				oEmploymentInfo.employmentInfoStatusValue = 3;
				oEmploymentInfo.modelAction				  = sModelAction;

				return oEmploymentInfo;
			},

			fillPerson : function (sModelAction,request) {

				request.firstName            = $("#firstname").val();
				request.middleName           = $("#middlename").val();
				request.lastName             = $("#lastname").val();
				request.personStatusValue    = $("#status-value").val();
				request.mothersMaidenName     = $('#motherMaidenName').val();

				if ($.pgsi.isNullOrUndefined($('#gender').val().trim())) {
					request.genderValue = 3;
				}
				else {
					request.genderValue = parseInt($('#gender').val(),10);
				}

				request.personTypeValue      = 2;
				request.id                   = parseInt($("#member-id").val(),10);
				dDate 						 = new Date($("#dob").val());
				request.dateOfBirth			 = dDate.getTime();
				request.suffix     			 = {id : parseInt($("#suffix").val(),10)};
				request.prefix     			 = {id : parseInt($("#prefix").val(),10)};
				request.modelAction 		 = sModelAction;

				if(sModelAction != "UPDATE"){
					if(!$.pgsi.isNullOrUndefined($("#note").val())){
						request.noteList 			 = [{ noteText :$("#note").val() ,modelAction   : sModelAction}];
					}
				}else{
					if(!$.pgsi.isNullOrUndefined($("#note").val())){
						request.noteList 			 = [{ noteText :$("#note").val() ,modelAction   : sModelAction,id : parseInt($('#noteId').val(),10) }];
					}

					else {
						if (!$.pgsi.isNullOrUndefined($("noteId").val())) {
							request.noteList 			 = [{ noteText :$("#note").val() ,modelAction   : "DELETE",id : parseInt($('#noteId').val(),10) }];
						}
					}
				}
				request.risk  =  new Risk({
					riskLevelValue : parseInt($("#risk").val(),10),
					riskLevelNote  : $("#risknote").val(),
					modelAction    : sModelAction,
					version        : pgsi.version.versionMember
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

				request.version = pgsi.version.versionMember;

				return request;


			},
			fillDocument : function (sModelAction) {

				var document = new Document();

				document.id                       = parseInt($('#document-id').val(),10);
				document.parentKey                = parseInt($('#member-id').val(),10);
				document.parentKeyType            = 3;
				document.documentType	          = {id: parseInt($('#document-type').val(),10)};
				document.modelAction              = sModelAction;
				document.issueCountry             = {code : $('#identification-country').val()};
				document.issueStateProvinceRegion = {id : $('#identification-region').val()};
				dDate 						      = new Date($("#idexpirationdate").val());
		    	document.expirationDate		      = dDate.getTime();
				document.value                    = $("#idnumber").val();

				return document;
			},

			fillRequest : function(sModelAction) {
				request = new Member();

				//request fill person
				request = pgsi.pages.member.create.form.fillPerson(sModelAction,request);

				request.employmentInfoList  = new Array();

				// Filling Document
				request.documentList  = new Array();

				var sSsn = $('#ssn').val().replace('-','').replace('-','');

				request.socialSecurityNumber = sSsn;

				if (sModelAction != "UPDATE") {
					if ($("#id-section").is(":visible")) {
						if((!$.pgsi.isNullOrUndefined($('#document-type').val()))||(!$.pgsi.isNullOrUndefined($('#idnumber').val()))||(!$.pgsi.isNullOrUndefined($('#idexpirationdate').val()))||(!$.pgsi.isNullOrUndefined($('#identification-country').val()))){
							request.documentList.push(pgsi.pages.member.create.form.fillDocument(sModelAction));
						}
					}
				}

				request.employmentInfoList.push(pgsi.pages.member.create.form.fillEmploymentInfo(sModelAction,request));

				request.countryUsageList  = new Array();

				if (($('#birth').val() != null) || ($('#birth').val() != "")) {
					request.countryUsageList.push({
						id:parseInt($('#birthId').val(),10),
						country: {
							code : $('#birth').val(),
							description : $('#birth-button .ui-selectmenu-text').text()
						},
						usageValue : 3,
						personId : $('member-id').val(),
						modelAction : sModelAction,
						version : pgsi.version.versionCountryUsage
					});
				}

				if(($('#residence').val() != null)||($('#residence').val() != "") ){
					request.countryUsageList.push({id:parseInt($('#residenceId').val(),10),country:{code : $('#residence').val(),description : $('#residence-button .ui-selectmenu-text').text() },usageValue : 2, personId:$('member-id').val(),modelAction   : sModelAction,version : pgsi.version.versionCountryUsage})
				}
				if(($('#citizenship').val() != null)||($('#citizenship').val() != "") ){
					request.countryUsageList.push({id:parseInt($('#citizenshipId').val(),10),country:{code : $('#citizenship').val(),description : $('#citizenship-button .ui-selectmenu-text').text() },usageValue : 1, personId:$('member-id').val() ,modelAction   : sModelAction,version : pgsi.version.versionCountryUsage})
				}

				request.preferredLanguage = {
					id : $('#preferredLanguage').val()
				};

				if ($.pgsi.isNullOrUndefined(request.preferredLanguage.id)) {
					request.preferredLanguage = null;
				}

				// Security questions
				request.securityAnswerList = pgsi.pages.security.question.form.fillObject();

				request.bestTimeToCall = $('#timetocall').val();
				request.version = pgsi.version.versionMember;

				return new MemberMaintenanceRequest({member: request});

			},

			fnAjaxCallFetchAll : function(iId){
				$.pgsi.ajax.post({
					sUrl 		: "api/member/fetchall",
					oRequest 	: {parentId : iId},
					fnCallback  : function(oResponse) {
						if(oResponse.operationSuccess == true){
							pgsi.pages.member.create.view.fillMember(oResponse.liaisonList);
						}else{
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
					}
				});
			},
			fnAjaxCallInsertUpdateMember : function(iMemberId,fnFillCallBack) {

				var bValidForm = pgsi.pages.member.create.form.validator.form();

				if (bValidForm){

					if(parseInt(iMemberId,10) != 0){
						var sUrl = 'api/member/update',
						    sModelAction = "UPDATE";
					}

					else{
						var sUrl = 'api/member/insert',
						    sModelAction = "INSERT";
					}

					$.pgsi.ajax.post({
						sUrl 		: sUrl,
						oRequest 	: pgsi.pages.member.create.form.fillRequest(sModelAction),
						bHideProgressBar : true,
						fnCallback  : function(oResponse) {
							if (oResponse.operationSuccess == true){
								 fnFillCallBack(oResponse);
							}
							else{
								fnCallBackError = function(oResponse) {

									pgsi.version.versionMember = oResponse.memberList[0].version;
									pgsi.pages.member.create.form.fillObject(oResponse.memberList[0]);
									$("#action-dialog-Error").dialog('close');

								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/member/fetch",{id:iMemberId},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Member"),false);
							}
						}
					});

				}
			}

		},

		view :{
			fillEmployment : function(oResponse) {

			}

		},

		fnDelete : function(iId) {

			var fnCallBack = function(oResponse) {

				if (oResponse.operationSuccess == true) {

					pgsi.pages.contact.form.fnAjaxCallFetchAll(parseInt($('#member-id').val(),10));

				}

				else{
					pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
				}
			}

			sTitle = $.pgsi.locale.get("pages.business.dialog.contact.title.delete",$('#company-name-field').text());
			pgsi.util.actiondialog.launchActionDialog("deleteDialog", pgsi.pages.business.dialogSettings.deleteDialog("api/member/delete", new MemberMaintenanceRequest({member : {id : iId,modelAction : 'DELETE' }}),sTitle,fnCallBack,$.pgsi.locale.get("commons.pages.erroView","Employment")));
		},

		fnFetchOrganizationById : function(iId,fnFillCallBack) {

			$.pgsi.ajax.post({
				sUrl 		: "api/organization/fetch",
				oRequest 	: {id : iId},
				fnCallback  : function(oResponse) {

					if (oResponse.operationSuccess == true){
						 fnFillCallBack(oResponse);
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}

				}
			});

		},
		fnFetchLocationById : function(iId,fnFillCallBack) {

			$.pgsi.ajax.post({
				sUrl 		: "api/location/fetch",
				oRequest 	: {id : iId},
				fnCallback  : function(oResponse) {

					if (oResponse.operationSuccess == true){
						 fnFillCallBack(oResponse);
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
					$.pgsi.progressBar.stop();
				}
			});

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
		fnValidDocument : function(){

			if((!$.pgsi.isNullOrUndefined($('#document-type').val()))||(!$.pgsi.isNullOrUndefined($('#idnumber').val()))||(!$.pgsi.isNullOrUndefined($('#idexpirationdate').val()))||(!$.pgsi.isNullOrUndefined($('#identification-country').val()))){

				if($.pgsi.isNullOrUndefined($('#document-type').val())){
					$('#document-type').addClass('required').prop("placeholder", "*");
					$('#document-type-button').addClass('required-field').prop("placeholder", "*");
				}else{
					$('#document-type').addClass('required').prop("placeholder", "");
					$('#document-type-button').removeClass('required-field').prop("placeholder", "");
				}
				if($.pgsi.isNullOrUndefined($('#identification-country').val())){
					$('#identification-country-button').addClass('required-field ').prop("placeholder", "*");
					$('#identification-country').addClass('required').prop("placeholder", "*");
				}else{
					$('#identification-country').addClass('required').prop("placeholder", "");
					$('#identification-country-button').removeClass('required-field').prop("placeholder", "");

				}
				$('#idnumber').addClass('required').prop("placeholder", "*");
				$('#idexpirationdate').addClass('required').prop("placeholder", "*");

			}else{
				$('#document-type').removeClass('required').prop("placeholder", "");
				$('#document-type-button').removeClass('required-field').prop("placeholder", "");
				$('#idnumber').removeClass('required').prop("placeholder", "");
				$('#idexpirationdate').removeClass('required').prop("placeholder", "");
				$('#identification-country').removeClass('required').prop("placeholder", "");
				$('#identification-country-button').removeClass('required-field').prop("placeholder", "");

			}

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