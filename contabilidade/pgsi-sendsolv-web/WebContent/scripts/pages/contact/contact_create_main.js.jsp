<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.contact = {

		oContact : {},

		form :{

			/**
			 * Validate the fields required at Organization Form
			 */
			validator : $("#contact-form").validate({
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

			fnInitMaskForm : function() {
				$("#contact-social-security-number").mask("999-99-9999");
				$("#contact-date-of-birth").mask("99/99/9999");
			},

			fillValidation : function() {

				if(parseInt($("#create-contact").find("#contact-risk").val(),10) === 4 ){
					$('#riskNote').removeClass("required required-field");
					$('#riskNote').removeClass("error");
					$('#riskNote').prop("placeholder", "");
				} else {
					$('#riskNote').addClass("required required-field");
					$('#riskNote').prop("placeholder", "*");
				}

			},

			fnInitForm : function(sModelAction) {

				if (!$.pgsi.isNullOrUndefined(sModelAction)
					&& sModelAction.toUpperCase() === "INSERT") {
					pgsi.pages.address.form.setDefaultValues();
				}

				$("#contact-pep").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum"));

				$("#contact-pep").val(3).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				$("#contact-risk").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum"));

				$("#contact-risk").val(3).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");

				$("#contact-type").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.LiaisonTypeEnum"));

				pgsi.pages.contact.form.setFieldSizes();

				// fill Mask
				pgsi.pages.contact.form.fnInitMaskForm();

			},

			setFieldSizes : function() {

				pgsi.util.page.form.fnInitForm();

				$("#contact-type-button, #contact-title").outerWidth(325);
				$( "#contact-prefix-button" ).outerWidth(80);
				$( "#contact-firstname" ).outerWidth(200);
				$( "#contact-middlename" ).outerWidth(200);
				$( "#contact-lastname" ).outerWidth(200);
				$( "#contact-maidenname" ).outerWidth(200);
				$( "#contact-suffix-button" ).outerWidth(85);
				$( "#contact-phone-type-1-button" ).outerWidth(155);
				$( "#contact-phone-type-2-button" ).outerWidth(155);
				$( "#contact-phone-number-1" ).outerWidth(255);
				$( "#contact-phone-number-2" ).outerWidth(255);
				$( "#contact-phone-extension" ).outerWidth(120);
				$( "#contact-email-address-1" ).outerWidth(255);
				$( "#contact-email-address-2" ).outerWidth(255);
				$( "#contact-date-of-birth" ).outerWidth(100);
				$( "#contact-pep-button" ).outerWidth(105);
				$( "#contact-risk-button" ).outerWidth(107);
				$("#contact-social-security-number").outerWidth(105);
				$("#country-button").outerWidth(255);
				$(".phone-country").outerWidth(125);

				pgsi.pages.phone.form.setFieldSizes();
				pgsi.util.page.email.form.setFieldSizes();
				pgsi.pages.address.form.setFieldSizes();

				$('#contact-type-button').addClass("required required-field");
				$('#contact').addClass("required required-field");
				$('#contact').prop("placeholder", "*");

			},

			fillClearFilds : function() {

				$('#contact-type-button .ui-selectmenu-text').text("");
				$('#contact-type-button').addClass("required");

				$('#contact-prefix-button .ui-selectmenu-text').text("");
				$('#contact-prefix-button').addClass("required");

				$('#contact-suffix-button .ui-selectmenu-text').text("");
				$('#contact-suffix-button').addClass("required");


				$('#contact-pep-button .ui-selectmenu-text').text("");
				$('#contact-pep-button').addClass("required");

				$('#contact-risk-button .ui-selectmenu-text').text("");
				$('#contact-risk-button').addClass("required");

				$("#contact-firstname").val("");
				$("#contact-middlename").val("");
				$("#contact-lastname").val("");
				//TODO
				$("#contact-maidenname").val("");
				$("#contact-id").val("");


				$("#contact-social-security-number").val("");
				$("#contact-date-of-birth").val("");

				$("#riskNote").val("");
				$("#note").val("");

			},

			fillObject : function(oResponse) {

				var oLiaison = oResponse;
				//selected
				$("#contact-title").val(oLiaison.title);

				$('#contact-type-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.LiaisonTypeEnum",oLiaison.liaisonType));
				$("#contact-type").val(oLiaison.liaisonTypeValue).prop('selected',true);
				$('#contact-type-button').removeClass("required-field");

				$input  = $("#contact-title");

				if ($("#contact-type").val() != "5") {
					$input.removeClass("required required-field");
					$input.removeClass("error");
					$input.prop("placeholder", "");
				}else {
					if (!$input.hasClass("required")) {
						$input.addClass("required required-field");
						$input.prop("placeholder", "*");
					}
				}

				if (!$.pgsi.isNullOrUndefined(oLiaison.prefix)){
					$("#contact-prefix").val(oLiaison.prefix.id).prop('selected',true);
					$("#contact-prefix").selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field").outerWidth(80);
				}

				if (!$.pgsi.isNullOrUndefined(oLiaison.suffix)){
					$("#contact-suffix").val(oLiaison.suffix.id).prop('selected',true);
					$("#contact-suffix").selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field").outerWidth(80);
				}

				$('#contact-pep-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum",oLiaison.pepStatus));
				$("#contact-pep").val(oLiaison.pepStatusValue).prop('selected',true);
				$('#contact-pep-button').removeClass("required-field");

				$('#contact-risk-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum",oLiaison.risk.riskLevel));
				$("#contact-risk").val(oLiaison.risk.riskLevelValue).prop('selected',true);
				$('#contact-risk-button').removeClass("required-field");

				$("#contact-firstname").val(oLiaison.firstName);
				$("#contact-middlename").val(oLiaison.middleName);
				$("#contact-lastname").val(oLiaison.lastName);
				//TODO
				if(oLiaison.nameList.length > 0){
					$("#contact-maidenname").val(oLiaison.nameList[0].otherName);
					$("#nameListId").val(oLiaison.nameList[0].id)
				}else{
					$("#contact-maidenname").val();
				}
				$("#contact-id").val(oLiaison.id)


				$("#contact-title").val(oLiaison.title);

				$("#contact-social-security-number").val(oLiaison.socialSecurityNumber);
				$("#ssnID").val(oLiaison.socialSecurityNumber);

				if (!$.pgsi.isNullOrUndefined(oLiaison.dateOfBirth)) {
					$("#contact-date-of-birth").val($.pgsi.date.format(new Date(oLiaison.dateOfBirth), "mm/dd/yy", null));
				}

				$("#riskNote").val(oLiaison.risk.riskLevelNote);

				if(oLiaison.noteList != null){
					if(oLiaison.noteList.length > 0){
						$("#note").val(oLiaison.noteList[0].noteText);
						$("#noteId").val(oLiaison.noteList[0].id);
					}
				}

				// fill phone fields
				pgsi.pages.phone.form.fillFormFields(oLiaison.contactList);
				// fill address fields
				pgsi.pages.address.form
						.fillFormFields(oLiaison.contactList);
				// fill email fields
				pgsi.util.page.email.form
						.fillFormFields(oLiaison.contactList);

				pgsi.version.versionContact = oLiaison.version;

				$("#state-button").outerWidth(155);
			},

			fillRequest : function(sModelAction, oContact) {

				request = new Liaison();
				request.liaisonTypeValue = parseInt($("#contact-type").val(),10);
				request.locationId = parseInt($("#business-id").val());
				request.prefix     = {id : parseInt($("#contact-prefix").val(),10)};
				request.firstName  = $("#contact-firstname").val();
				request.middleName = $("#contact-middlename").val();
				request.lastName   = $("#contact-lastname").val();
				//TODO
				request.genderValue   = 1;
				request.personTypeValue = 1;
				request.id = parseInt($("#contact-id").val(),10);

				if($("#nameListId").val()	!= "0"){
					request.nameList  = [{ otherName : $("#contact-maidenname").val(), id :  parseInt($("#nameListId").val(),10) ,modelAction   : sModelAction}];
				}else{
					request.nameList  = [{ otherName : $("#contact-maidenname").val(), id :  parseInt($("#nameListId").val(),10) ,modelAction   : "INSERT"}];
				}

				if ((!$.pgsi.isNullOrUndefined($("#contact-date-of-birth").val())) || ($("#contact-date-of-birth").val() !== "")) {
					dDate = new Date($("#contact-date-of-birth").val());
					request.dateOfBirth = dDate.getTime();
				}

				else {
					request.dateOfBirth = null;
				}

				request.suffix     = {id : parseInt($("#contact-suffix").val(),10)};

				// PEP check if PEP field is available on screen
				if ($("#contact-pep").length > 0) {
					request.pepStatusValue  = parseInt($("#contact-pep").val(),10);
				}

				else {
					// Uses previous loaded PEP status
					if (sModelAction.toUpperCase() === "UPDATE" ) {
						request.pepStatusValue = oContact.pepStatusValue;
					}
					// Default Value UNKNOWN
					else {
						request.pepStatusValue = 3;
					}

				}

				request.title   =  $("#contact-title").val();
				request.modelAction = sModelAction;

				if (parseInt($("#noteId").val()) != 0 ) {
					if ($("#note").val().length > 0) {
						request.noteList = [{ noteText :$("#note").val() ,modelAction   : sModelAction, id: parseInt($("#noteId").val())}];
					}
					else {
						request.noteList = [{ noteText :$("#note").val() ,modelAction   : "DELETE", id: parseInt($("#noteId").val())}];
					}
				}

				else {
					if ($("#note").val().length > 0) {
						request.noteList = [{ noteText :$("#note").val() ,modelAction   : "INSERT"}];
					}
				}

				request.risk =  new Risk({
					modelAction   : sModelAction,
					version : 10
				});


				// check if RISK field is available on screen
				if ($("#contact-risk").length > 0) {
					request.risk.riskLevelValue = $("#contact-risk").val();
				}

				else {
					// Uses previous loaded RISK status
					if (sModelAction.toUpperCase() === "UPDATE" ) {
						request.risk.riskLevelValue = oContact.risk.riskLevelValue;
					}

					// Default value UNKNOWN
					else {
						request.risk.riskLevelValue = 3;
					}
				}

				// check if RISK NOTE field is available on screen
				if ($("#riskNote").length > 0) {
					request.risk.riskLevelNote = $("#riskNote").val();
				}

				else {
					// Uses previous loaded RISK Note status
					if (sModelAction.toUpperCase() === "UPDATE" ) {
						request.risk.riskLevelNote = oContact.risk.riskLevelNote;
					}
				}

				// SSN
				if ($("#contact-social-security-number").val().length > 0) {
					request.socialSecurityNumber = $("#contact-social-security-number").val().replace(/-/g,"");
				}

				request.contactList = new Array();

				var oAddress = pgsi.pages.address.form.fillObject(sModelAction);

				if (!$.pgsi.isNullOrUndefined(oAddress)) {
					request.contactList.push(oAddress);
				}

				var aPhones = pgsi.pages.phone.form.fillObject();

				for (var i=0; i < aPhones.length; i++) {
					request.contactList.push(aPhones[i]);
				}

				var aEmails = pgsi.util.page.email.form.fillObject();

				for (var i = 0; i < aEmails.length; i++) {
					request.contactList.push(aEmails[i]);
				}

				request.version = pgsi.version.versionContact;

				return new LiaisonRequest({liaison: request});

			},

			fnAjaxCallFetchAll : function(oBusinessParent){
				$.pgsi.ajax.post({
					sUrl 		: "api/contact/fetchall",
					oRequest 	: {parentId:oBusinessParent.id},
					fnCallback  : function(oResponse) {
						if(oResponse.operationSuccess == true){
							pgsi.pages.contact.view.fillContact(oResponse.liaisonList, oBusinessParent);
						}else{
							pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
						$.pgsi.progressBar.stopGlobal();
						$.pgsi.progressBar.stop();
					}
				});
			},

			fnAjaxCallInsertUpdateContact : function(fnFillCallBack, oContact) {

				var bValidForm = pgsi.pages.contact.form.validator.form();

				if (bValidForm){

					if (!$.pgsi.isNullOrUndefined(oContact) && !$.pgsi.isNullOrUndefined(oContact.id)) {
						var sUrl = 'api/contact/update',
						    sModelAction = "UPDATE";
					}

					else{
						var sUrl = 'api/contact/insert',
						    sModelAction = "INSERT";
					}

					$.pgsi.ajax.post({
						sUrl 		: sUrl,
						oRequest 	: pgsi.pages.contact.form.fillRequest(sModelAction, oContact),
						fnCallback  : function(oResponse) {

							if (oResponse.operationSuccess == true) {
								 fnFillCallBack();
							}

							else {
								fnCallBackError = function(oResponse) {

									pgsi.version.versionContact = oResponse.liaisonList[0].version;
									pgsi.pages.contact.form.fillObject(oResponse.liaisonList[0]);
									$("#action-dialog-Error").dialog('close');

								}
								pgsi.pages.sendsolv.fnDialogMessageError("api/contact/fetch",{id:oContact.id},oResponse,fnCallBackError,$.pgsi.locale.get("commons.dialog.error.title","Contact"),false);
							}
						}
					});
				}
			}
		},

		view :{
			fillContact : function(oLiaison, oBusinessParent) {

				var sContact = "";
				var sPhone      = '',
						sEmail      = '',
						sTypePhone  = '',
						sAddress    = '',
						sSSN        = "",
						sExtension  = "";
						sSdnIcons   = "";

				for (var i=0;i < oLiaison.length;i++){

					sEmail = "";
					sAddress = "";
					sPhone = "";

					if (oLiaison[i].documentList !== null) {
						if(oLiaison[i].documentList.length > 0) {
							if (oLiaison[i].documentList[0].value.length > 0) {
								sSSN = "SSN ***-**-"+oLiaison[i].documentList[0].value.substring(5, 9);
							}
						}
					}

					for (var iCont = 0; iCont < oLiaison[i].contactList.length; iCont++) {
						sTypePhone = $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.ContactTypeEnum",oLiaison[i].contactList[iCont].contactType)
						if (!$.pgsi.isNullOrUndefined(oLiaison[i].contactList[iCont].extension)) {
							sExtension = " x" + oLiaison[i].contactList[iCont].extension;
						}

						if (oLiaison[i].contactList[iCont].type == "phone"){
							$("#action-dialog").append('<input class="numeric hide" type="text" value=""/>');
							$(".numeric").val(oLiaison[i].contactList[iCont].number);

							if(oLiaison[i].contactList[iCont].priorityValue == 1 ){
								sPhone = sPhone + '<p class="phone-number primary">+'+oLiaison[i].contactList[iCont].country.phoneCode + " " + $(".numeric").val() + sExtension + ' ('+sTypePhone+')</p>';
							}else{
								sPhone = sPhone + '<p class="phone-number">+'+oLiaison[i].contactList[iCont].country.phoneCode + " " + $(".numeric").val() + sExtension + ' ('+sTypePhone+')</p>';
							}
							$("#action-dialog").empty();
						}

						else if(oLiaison[i].contactList[iCont].type == "email") {

							if(oLiaison[i].contactList[iCont].priorityValue == 1 ){
								sEmail = sEmail +'<p class="email primary">'+oLiaison[i].contactList[iCont].emailAddress+'</p>'
							}else{
								sEmail = sEmail +'<p class="email">'+oLiaison[i].contactList[iCont].emailAddress+'</p>'
							}

						}

						else if(oLiaison[i].contactList[iCont].type == "address") {
							if (!$.pgsi.isNullOrUndefined(oLiaison[i].contactList[iCont].stateProvinceRegion)) {
								var sStateProvinceRegion = oLiaison[i].contactList[iCont].stateProvinceRegion.code;
							}else{
								var sStateProvinceRegion = "";
							}
							if (!$.pgsi.isNullOrUndefined(oLiaison[i].contactList[iCont].country)) {
								var sCountry = oLiaison[i].contactList[iCont].country.code;
							}else{
								var sCountry = "";
							}

							sAddress = sAddress + '<p class="address-line">'+oLiaison[i].contactList[iCont].addressLine1+'</p>';

							if (!$.pgsi.isNullOrUndefined(oLiaison[i].contactList[iCont].addressLine2)) {
								sAddress += '<p class="address-line">'+ oLiaison[i].contactList[iCont].addressLine2 + '</p>';
							}
							if (!$.pgsi.isNullOrUndefined(oLiaison[i].contactList[iCont].addressLine3)) {
								sAddress += '<p class="address-line">'+ oLiaison[i].contactList[iCont].addressLine3 + '</p>';
							}
							if (!$.pgsi.isNullOrUndefined(oLiaison[i].contactList[iCont].addressLine4)) {
								sAddress += '<p class="address-line">'+ oLiaison[i].contactList[iCont].addressLine4 + '</p>';

							sAddress += '<p class="address-line">'+oLiaison[i].contactList[iCont].cityName+' '+sStateProvinceRegion+' '+oLiaison[i].contactList[iCont].postalCode+' '+sCountry+'</p>';
							}
						}
					}

					var sComment = "";
					if (!$.pgsi.isNullOrUndefined(oLiaison[i].noteList[0])) {
						if (!$.pgsi.isNullOrUndefined(oLiaison[i].noteList[0].noteText)) {
							sComment  = '<p class="comments"><span>Comments</span> <span>' + oLiaison[i].noteList[0].noteText +'</span></p>' ;
						}
					}

					var sLinks, sEditButton, sDeleteButton, sDateOfBirth;
					sLinks = "";
					sEditButton = "";
					sDeleteButton = "";
					sDateOfBirth = "";


					sEditButton = '<a href="'+oLiaison[i].id+'" class="ui-subtitle buttonContact edit" title="' + $.pgsi.locale.get("commons.pages.edit") + '">'
					+			      '<span class="icon-small-button icon-nav icon-pencil "></span> <span>'+$.pgsi.locale.get("commons.pages.edit")+'</span>'
					+		  	  '</a>';

					<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

						sDeleteButton = '<a href="'+oLiaison[i].id+'" class="ui-subtitle buttonContact delete" title="' + $.pgsi.locale.get("commons.pages.delete") + '">'
							+			       '<span class="icon-small-button icon-nav icon-trash-bin "></span> <span>' + $.pgsi.locale.get("commons.pages.delete") + '</span>'
							+			   '</a>';

					</sec:authorize>


					if (!pgsi.util.page.fnIsSDNFlagged(oBusinessParent.sdnstatus)) {
						sLinks = '<div class="links">' + sEditButton + sDeleteButton + '</div>';
					}

					if (!$.pgsi.isNullOrUndefined(oBusinessParent) && oBusinessParent.businessType === "ORGANIZATION") {
						<sec:authorize access="!hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
							sLinks = "";
							$("#add-contact").remove();
						</sec:authorize>
					}

					if (!$.pgsi.isNullOrUndefined(oLiaison[i].dateOfBirth)) {
						sDateOfBirth = '<p>Date of Birth '+$.pgsi.date.format(new Date(oLiaison[i].dateOfBirth), "mm/dd/yy", null)+'</p>';
					}

					sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags(oLiaison[i]);

					sContact = sContact + '<div class="outer-box">'
						+'	<div class="box">'
						+'	<div class="small-box">'
						+ sLinks
						+'	</div>'
						+'	<span class="company-name">'+oLiaison[i].firstName+' '+ oLiaison[i].lastName  +' </span>' + sSdnIcons + '<span'
						+'		class="acronym"><span class="acronym"> ' +$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.LiaisonTypeEnum",oLiaison[i].liaisonType)+'</span></span>'
						+'	<p class="first">'+oLiaison[i].title+'</p>'
						+'	<div class="info">'
						+      	 sEmail
						+'		<p></p>'
						+        sPhone
						+'		<p></p>'
						+       sAddress
						+'		<p>' + sSSN + '</p>'
						+sDateOfBirth
						+		sComment
						+'	</div>'
						+'</div>'
						+'</div>'
				}

				// Removes content from the section
				$("#business-view").find("section.contact").find("div.container").empty();

				$("#business-view").find("section.contact").find("div.container").append(sContact);

				$("#business-view").find("section.contact").find("div.container").find(".outer-box").each(function(){
					$(this).css("min-height", $(this).children(".box").outerHeight() + "px");
				});

				if (oLiaison.length == 0) {
					$("#business-view").find("section.contact").find('div.container').append("<p class='empty'>" + $.pgsi.locale.get("page.business.view.contact.empty") + "</p>");
				}

				$("#add-contact").unbind("click");

				$("section.contact .links a").on( "click", function(event){

					event.preventDefault();

					if (!$(this).hasClass('delete')) {

						if ($(this).hasClass('edit')) {
							sTitle   = $.pgsi.locale.get("pages.contacts.dialog.note.title.edit", $('#company-name-field').text());
							pgsi.util.actiondialog.launchActionDialog("insUpdContact", pgsi.pages.business.dialogSettings.insUpdContact(
								parseInt($(this).attr("href")),
								sTitle,
								oBusinessParent,
								'UPDATE',
								"api/contact/update"));
						}

						else {
							sTitle   = $.pgsi.locale.get("pages.contacts.dialog.note.title.add", $('#company-name-field').text());
							pgsi.util.actiondialog.launchActionDialog("insUpdContact", pgsi.pages.business.dialogSettings.insUpdContact(
								parseInt($(this).attr("href")),
								sTitle,
								oBusinessParent,
								'INSERT',
								"api/contact/insert"));
						}
					}

					else
					{
						pgsi.pages.contact.view.fnDelete(parseInt($(this).attr("href")), oBusinessParent);
					}

				});

			},

			fnDelete : function(iId, oBusinessParent) {

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {

						pgsi.pages.contact.form.fnAjaxCallFetchAll(oBusinessParent);

					} else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}
				sTitle = $.pgsi.locale.get("pages.business.dialog.contact.title.delete",$('#company-name-field').text());

				pgsi.util.actiondialog.launchActionDialog(
					"deleteDialog",
					pgsi.pages.business.dialogSettings.deleteDialog("api/contact/delete",
						new LiaisonRequest({
								liaison : {
									id : iId,
									modelAction : 'DELETE'
								}
							}),
					sTitle,
					fnCallBack,
					$.pgsi.locale.get("commons.pages.erroView","Contact"))
				);
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
	},

	/**
	 * Validate the fields required at Contact Form
	 */
	validator :	$( "#create-contact-form" ).validate(),

	/**
	 * Ajax call that will submit the request ajax
	 *
	 * @param {String} sUrlAdress
	 * 			The URL to post
	 * @param {String} sMessage
	 * 			Message that will display after the ajax call
	 */
	ajaxCall : function (sUrlAdress, sMessage)
	{
		//Get the contactId parameter from the url
		var iContactIdFromPath = $.address.path().split('/').pop();
		var iContactId = (!isNaN(iContactIdFromPath))? iContactIdFromPath : $.address.parameter('contactId');

		//Create request of creating or update
		if (iContactId)
		{
			var request = {};
		}
		else
		{
			var request = {};
		}

		//Function that will execute after the call ajax
		var fnCallBack = function(data)
		{
			$.pgsi.pageLoader.load({ url: "contact", $content: $("#load"), oMessage : {message : sMessage, type : "confirm" }});
		};

		//Send the request for controller, from the url received
		$.pgsi.ajax.post({sUrl : sUrlAdress, oRequest : request, fnCallback : fnCallBack});
	}
}

</script>

</sec:authorize>