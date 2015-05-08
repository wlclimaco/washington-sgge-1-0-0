<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The main namespace for the Member View Page.
 * @author Flavio Tosta
 */
pgsi.pages.member.view = {

	fnInitPinSection : function(oMember) {

		if (oMember.personStatusValue === 3) {
			$("#pin-number").show().text(oMember.pinNumber).prev().show();
			$("#pin-verify").hide().prev().hide();
		}

		else {
			pgsi.pages.member.view.fnHidePinNumber();
		}


		$("#pin-verify").keyup(function(e) {
			var val = $(this).val();

			// Check typed pin with Member Pin
			if (val.length == 4) {
				var $this = $(this);

				if (isNaN(val)) {
					$this.next(".pin-verify").removeClass("icon-check-mark").addClass("icon-minus-circle").show();
				}

				else {
					var fnCallback = function(oResponse) {

						if (oResponse.memberList[0].pinNumber === val) {
							$this.next(".pin-verify").addClass("icon-check-mark").removeClass("icon-minus-circle").show();
						}

						else {
							$this.next(".pin-verify").removeClass("icon-check-mark").addClass("icon-minus-circle").show();
						}
						$.pgsi.progressBar.stop();
					};

					$.pgsi.ajax.post({
						sUrl       : "api/member/fetch",
						oRequest   : { id : $("#business-id").val() },
						fnCallback : fnCallback
					});
				}
			}
		});
	},

	fnHidePinNumber : function() {
		$("#pin-verify").show().prev().show();
		$("#pin-number").hide().text("").prev().hide();

	},

	fnFillFields : function(oResponse) {

		var oMember = oResponse.memberList[0];

		// SDN status specific formatting / styling
		if (pgsi.util.page.fnIsSDNFlagged(oMember.sdnstatus)) {
			$("#link-edit-personalinfo, #edit-risk, #link-add-id, #link-add-empl, #addTransfer").remove();
			$("#tabs").find(".ui-tabs-panel").first().addClass('sdnFlagged');
		}

		if ($.pgsi.isNullOrUndefined(oMember)) {
			return false;
		}

		// member id
		$('#member-id').val(oMember.id);
		$("#personType").val(oMember.personTypeValue);
		$("#personSdnStatus").val(oMember.sdnstatusValue);

		// Access Number
		var oPhoneList = pgsi.pages.phone.filterPhones(oMember.contactList);
		var oPrimaryPhone = $.grep(oPhoneList, function(o){ return o.priority === "PRIMARY"; });

		if (!$.pgsi.isNullOrUndefined(oPrimaryPhone[0])) {
			$("#pin-acessnumber").text(oPrimaryPhone[0].number);
		}

		// Status
		$('#status-template .newline').text($.pgsi.locale.get("pages.member.view.id"));
		$('#status-template #status').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oMember.personStatus));
		//mask participant id MXX-00000
		var input  = document.createElement("input");
		input.type = "text";
		input.value  = oMember.participantId;
		var $input = $(input);
		$input.mask('***-99999');
		$('#status-template #status-id').text($input.val());

		// Breadcrumb
		$("#member-name").text(oMember.firstName + " " + oMember.lastName);
		// Title
		$("#member-title").text(oMember.firstName + " " + oMember.lastName);

		$("#business-id").val(oMember.id);
		$("#business-name").val(oMember.firstName + " " + oMember.lastName);

		// Set the page title
		$.pgsi.pageLoader.title(oMember.firstName + " " + oMember.lastName + " (" + $.pgsi.locale.get("commons.pages.member") + ")", true);


		// Personal Info
		var sPrefix, sFirstName, sMiddleName, sLastName, sMothersMaidenName;
		sPrefix = $.pgsi.isNullOrUndefined(oMember.prefix) ? "" : oMember.prefix.code;
		sMiddle = $.pgsi.isNullOrUndefined(oMember.middleName) ? "" : oMember.middleName;
		sMothersMaidenName = $.pgsi.isNullOrUndefined(oMember.mothersMaidenName) ? "" : oMember.mothersMaidenName;
		$("#info-name").text(sPrefix +" "+ oMember.firstName + " " + sMiddle + " " + oMember.lastName + " " + sMothersMaidenName);

		// Other Names
		if(oMember.nameList !== null){
			if(oMember.nameList > 0){
				var sOtherNames = "";
				for (var i=0; i < oMember.nameList.length; i++){
					sOtherNames += "<li>" + oMember.nameList[i].otherName + "</li>";
				}
				$("#info-other-names").find("ul").append(sOtherNames);
			}
		}
		// Date of Birth
		$("#info-date").text($.pgsi.date.format(new Date(oMember.dateOfBirth), "mm/dd/yy", null));

		// Gender
		$("#info-gender").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.GenderEnum",oMember.gender));

		//Person status
		if(oMember.personStatusValue === 1){
			$('.active').hide();
			$('.deactivate').show();
		}else if(oMember.personStatusValue === 2){
			$('.deactivate').hide();
			$('.active').show();
		}else if(oMember.personStatusValue === 3){
			$('.deactivate').hide();
			$('.active').show();
		}else if(oMember.personStatusValue === 4){
			$('.deactivate').hide();
			$('.active').show();
		}

		$("#participant-id").text(oMember.participantId);
		$(".member-status").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oMember.personStatus))
		$("#status-value").val(oMember.personStatusValue);

		pgsi.util.page.status.view.fnFillStatusFlags(oMember);

		pgsi.version.versionMember = oMember.version;


		// Iterate through the country types
		var oCountryUsage;


		for (var i=0; i < oMember.countryUsageList.length; i++) {

			oCountryUsage = oMember.countryUsageList[i];

			if (oCountryUsage.usage === "CITIZENSHIP") {
				$("#info-citizenship").text(oCountryUsage.country.description);
			}

			else if (oCountryUsage.usage === "RESIDENCE") {
				$("#info-residence").text(oCountryUsage.country.description);
			}

			else if (oCountryUsage.usage === "BIRTH") {
				$("#info-birth").text(oCountryUsage.country.description);
			}
		}

		// SSN
		var $ssn = $("#info-ssn");

		if (!$.pgsi.isNullOrUndefined(oMember.socialSecurityNumber)) {
			$ssn.text("***-**-" + oMember.socialSecurityNumber.substring(5));
		}

		if ($ssn.text().length === 0) {
			$ssn.prev().hide();
		}
		else {
			$ssn.prev().show();
		}

		$(".pepStatus").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum",oMember.pepStatus));
		$("#personPepStatus").val(oMember.pepStatusValue);

		// Address Lines
		pgsi.pages.address.view.fillFields(oMember.contactList);

		// Phones
		pgsi.pages.phone.view.fillFields(oMember.contactList);

		// Emails
		pgsi.util.page.email.view.fillFields(oMember.contactList);

		// Best Time to Call
		if (!$.pgsi.isNullOrUndefined(oMember.bestTimeToCall)) {
			$("#info-best-time-to-call").text(oMember.bestTimeToCall).show().prev().show();
		}
		else {
			$("#info-best-time-to-call").hide().prev().hide();
		}

		// Preferred Language
		if (!$.pgsi.isNullOrUndefined(oMember.preferredLanguage) && !$.pgsi.isNullOrUndefined(oMember.preferredLanguage.name)) {
			$("#info-preferred-language").text(oMember.preferredLanguage.name).show().prev().show();
		}

		else {
			$("#info-preferred-language").hide().prev().hide();
		}

		// Pin Number
		pgsi.pages.member.view.fnInitPinSection(oResponse.memberList[0]);

		// Risk
		pgsi.pages.risk.view.fillFields(oResponse.memberList[0]);

		// Employmentinfo
		pgsi.pages.employment.view.fill(oResponse.memberList[0]);

		// Identifications
		pgsi.pages.identification.view.fill(oResponse.memberList[0].documentList, oResponse.memberList[0]);

		// Notes
		pgsi.pages.note.view.fill(oResponse.memberList[0].noteList, oMember);

		// Other Names Section
		pgsi.util.page.person.otherNames.view.fnFill(oResponse.memberList[0].nameList, $("#info-other-names").find('ul'));


		// Transfers Settings
		pgsi.pages.transfer.create.view.fillTransferSettings(oResponse.memberList[0].transferSettingList, oMember, "api/recipient/fetch");


	}
};

</script>

</sec:authorize>