<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.recipient
 * @description The init namespace for the Recipient View Page.
 * @author Flavio Tosta
 */

pgsi.pages.recipient.view = {
	fnFillFields : function(oResponse) {

		var oRecipient = oResponse.recipientList[0];

		// SDN status specific formatting / styling
		if (pgsi.util.page.fnIsSDNFlagged(oRecipient.sdnstatus)) {
			$("#link-edit-personalinfo, #addRisk, #link-add-id, #link-add-empl, #addTransfer").remove();
			$("#tabs").find(".ui-tabs-panel").first().addClass('sdnFlagged');
		}

		$('#info-other-names').find('ul').find('li').text("")
		$('.col2').find('#email-container').text("");

		if ($.pgsi.isNullOrUndefined(oRecipient)) {
			return false;
		}

		// recipient id
		$('#recipient-id').val(oRecipient.id);
		$("#personType").val(oRecipient.personTypeValue);
		$("#personPepStatus").val(oRecipient.pepStatusValue);

		// Status
		$('#status-template .newline').text($.pgsi.locale.get("pages.recipient.view.recipientId"));
		$('#status-template #status').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oRecipient.personStatus));

		//mask participant id MXX-00000
		var input  = document.createElement("input");
		input.type = "text";
		input.value  = oRecipient.participantId;
		var $input = $(input);
		$input.mask('***-99999');
		$('#status-template #status-id').text($input.val());

		// Breadcrumb
		$("#recipient-name").text(oRecipient.firstName + " " + oRecipient.lastName);
		// Title
		$("#recipient-title").text(oRecipient.firstName + " " + oRecipient.lastName);

		$("#business-id").val(oRecipient.id);
		$("#business-name").val(oRecipient.firstName + " " + oRecipient.lastName);

		$.pgsi.pageLoader.title(oRecipient.firstName + " " + oRecipient.lastName + " (" + $.pgsi.locale.get("commons.pages.recipient") + ")", true);

		// Personal Info
		var sPrefix, sFirstName, sMiddleName, sLastName, sMothersMaidenName;
		sPrefix = $.pgsi.isNullOrUndefined(oRecipient.prefix) ? "" : oRecipient.prefix.code;
		sMiddle = $.pgsi.isNullOrUndefined(oRecipient.middleName) ? "" : oRecipient.middleName;
		sMothersMaidenName = $.pgsi.isNullOrUndefined(oRecipient.mothersMaidenName) ? "" : oRecipient.mothersMaidenName;
		$("#info-name").text(sPrefix +" "+ oRecipient.firstName + " " + sMiddle + " " + oRecipient.lastName + " " + sMothersMaidenName);


		// Other Names
		var sOtherNames = "";
		for (var i=0; i < oRecipient.nameList.length; i++){
			sOtherNames += "<li>" + oRecipient.nameList[i].otherName + "</li>";
		}
		$("#info-other-names").find("ul").append(sOtherNames);

		//Person status
		if(oRecipient.personStatusValue === 1){
			$('.active').hide();
			$('.deactivate').show();
		}else if(oRecipient.personStatusValue === 2){
			$('.deactivate').hide();
			$('.active').show();
		}else if(oRecipient.personStatusValue === 3){
			$('.deactivate').hide();
			$('.active').show();
		}else if(oRecipient.personStatusValue === 4){
			$('.deactivate').hide();
			$('.active').show();
		}
		$("#recipient-status").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oRecipient.personStatus))
		$("#status-value").val(oRecipient.personStatusValue);

		$(".pepStatus").text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum",oRecipient.pepStatus));

		pgsi.util.page.status.view.fnFillStatusFlags(oRecipient);

		pgsi.version.versionRecipient = oRecipient.version;

		// Address Lines
		pgsi.pages.address.view.fillFields(oRecipient.contactList);

		// Phones
		pgsi.pages.phone.view.fillFields(oRecipient.contactList);

		// Emails
		pgsi.util.page.email.view.fillFields(oRecipient.contactList);

		// Risk
		pgsi.pages.risk.view.fillFields(oRecipient);

		// Notes
		if(oRecipient.noteList != null){
			pgsi.pages.note.view.fill(oRecipient.noteList, oRecipient);
		}

		// Transfers Settings
		pgsi.pages.transfer.create.view.fillTransferSettings(oRecipient.transferSettingList,oRecipient,"api/member/fetch");

	}

};

</script>

</sec:authorize>