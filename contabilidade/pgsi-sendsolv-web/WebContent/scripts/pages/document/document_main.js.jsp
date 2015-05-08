<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.document = {

		form :{

			/**
			 * Validate the fields required at Organization Form
			 */
			validator : $("#create-document-form").validate({
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

			fnInitForm : function() {
				// Fill the dropdownlists
				$("#document-type").fnLoadDropDownList(oPreLoadResponse);
				$('#filing-status').fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.cbof.model.FilingStatusEnum"));
				pgsi.util.page.form.fnInitForm();
				pgsi.pages.document.form.setFieldSizes();

				// Remove requirement for Description field for Document Type ==  Other
				pgsi.pages.document.form.fnInitValidationForm(0);

				$("#document-type").on("selectmenuchange", function( event, ui ) {
					pgsi.pages.document.form.fnInitValidationForm(parseInt(ui.item.value,10));
				});

				$("#document-type-button").focus();


			},

			fnInitValidationForm : function(iValue) {

				$input  = $("input#description");
				if (iValue !== 5) {
					$input.removeClass("required required-field");
					$input.removeClass("error");
					$input.prop("placeholder", "");
				}
				else {
					if (!$input.hasClass("required")) {
						$input.addClass("required required-field");
						$input.prop("placeholder", "*");
					}
				}
			},

			setFieldSizes : function() {
				$("#document-type-button").outerWidth(285);
				$("#filing-status-button").outerWidth(225);

			},

			fnFillFields : function(oRequestDocument) {
				if (!$.pgsi.isNullOrUndefined(oRequestDocument) &&
						!$.pgsi.isNullOrUndefined(oRequestDocument.document)) {

					$('#note').text(oRequestDocument.document.noteText);
					$('#description').val(oRequestDocument.document.keywordText);
					$("#isActionRequired").prop('checked',oRequestDocument.document.isActionRequired);
					if (!$.pgsi.isNullOrUndefined(oRequestDocument.document.documentType.id)) {
						$('#document-type').val(oRequestDocument.document.documentType.id).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
					}

					if (!$.pgsi.isNullOrUndefined(oRequestDocument.document.filingStatusValue)) {
						$("#filing-status").val(oRequestDocument.document.filingStatusValue).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
					}

					pgsi.pages.document.form.setFieldSizes();
				}

			},

			fillRequest : function(oRequestDocument) {

				oRequestDocument.document.documentType.id   = $('#document-type').val();
				oRequestDocument.document.noteText          = $('#note').val();
				oRequestDocument.document.keywordText       = $('#description').val();
				oRequestDocument.document.isActionRequired  = $("#isActionRequired").is(':checked');
				oRequestDocument.document.filingStatusValue = $("#filing-status").val();

				return oRequestDocument;

			},

			fnAjaxCallInsertUpdateDocument : function(sUrl,oRequestDocument,iId){

				var bValidForm = pgsi.pages.document.form.validator.form();

				var fnCallBackFetchs = function(oResponseFetch) {

					if (oResponseFetch.operationSuccess == true) {

							$("#business-view").find("section.documents").find("div.box").remove()

							pgsi.pages.document.view.fill(oResponseFetch.organizationList[0].documentList, oResponseFetch.organizationList[0]);


							$("#action-dialog").dialog('close');
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponseFetch,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
					$.pgsi.progressBar.stopGlobal();
					$.pgsi.progressBar.stop();
				}

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {
						pgsi.pages.business.dialog.fnFetchBusiness("api/organization/fetch",{id:iId},fnCallBackFetchs)
						$("#action-dialog").dialog('close');
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}
				if(bValidForm) {
					$.pgsi.ajax.post({
						 sUrl 		: sUrl,
						 oRequest 	: pgsi.pages.document.form.fillRequest(oRequestDocument),
						 fnCallback : fnCallBack
					});
				}
			}

		},
		view :{
			fill : function(oDocumentList, oBusinessParent) {
				var oDocument = null;
				var sDocumentList = "";
				var sDeleteButton = "";

				var sDescription;
				var sDescriptionLabel = "n/a";
				var iDocumentTypeId;
				var bActionRequired;
				var sNoteText;
				var sDocumentType;
				var iIdDocument;
				var sTitle;
				var sFilingStatusDescription;
				var sInsUpdLinks = "";

				if((oDocumentList != null )||(oDocumentList.length > 0)){
					for (var i=0; i < oDocumentList.length; i++) {
						oDocument = oDocumentList[i];

						if(oDocument.documentType !== null){
							sDocumentType      = oDocument.documentType.name;
							iDocumentTypeId    = oDocument.documentType.id;
						}
						sDescription       = oDocument.keywordText;
						bActionRequired    = oDocument.isActionRequired;
						sNoteText          = oDocument.noteText;
						iIdDocument        = oDocument.id;
						iIdFilingStatus    = oDocument.filingStatusValue;
						sIdFiling          = $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.FilingStatusEnum",oDocument.filingStatus);

						// Setting filing status descripton
						if (oDocument.filingStatusValue == 1) {
							sFilingStatusDescription = $.pgsi.locale.get("com.prosperitasglobal.cbof.model.filingstatusenum.filed.description");
						}

						else if (oDocument.filingStatusValue == 2) {
							sFilingStatusDescription = $.pgsi.locale.get("com.prosperitasglobal.cbof.model.filingstatusenum.notfiled.description");
						}

						else if (oDocument.filingStatusValue == 3) {
							sFilingStatusDescription = $.pgsi.locale.get("com.prosperitasglobal.cbof.model.filingstatusenum.unknown.description");
						}

						// Setting custom title and description for document type == Other
						if (sDocumentType.toUpperCase().trim().indexOf("OTHER") != -1) {
							sTitle = sDescription;
						}

						else {
							sTitle = sDocumentType;
							sDescriptionLabel = sDescription;
						}

						<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
							sDeleteButton = "<a href='"+iIdDocument+"' class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') +"'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>"+$.pgsi.locale.get('commons.pages.delete')+"</span></a>";
						</sec:authorize>

						if (!pgsi.util.page.fnIsSDNFlagged(oBusinessParent.sdnstatus)) {
							sInsUpdLinks = "<div class='small-box'><div class='links'><a href='" + iIdDocument+"' class='ui-subtitle edit' title='" + $.pgsi.locale.get('commons.pages.edit') +"'> <span class='icon-small-button icon-nav icon-pencil edit'></span> <span>" + $.pgsi.locale.get('commons.pages.edit') +"</span></a>" + sDeleteButton + "</div></div>";
						}

						if (bActionRequired === true || bActionRequired === "true") {
							sDocumentList = sDocumentList + "<div class='outer-box'><div class='box action-required'>" + sInsUpdLinks + "<p>" + sTitle + "</p><p class='documentType hide'>"+sDocumentType+"</p><p class='hide typeId'>" + iDocumentTypeId+"</p><p class='full-text hide'>" + sDescriptionLabel + "</p><p class='description hide'>" + sDescription + "</p><p class='filingStatus'>"+sFilingStatusDescription+"</p><p class='hide filingId'>"+iIdFilingStatus+"</p><p class='note full-text hide'>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" + sNoteText + "</span></div><p class='warning'><span class='icon-nav icon-exclamation'></span><span>" + $.pgsi.locale.get('pages.document.form.label.actionRequired') +"</span></p></div></div>";
						}

						else {
							sDocumentList = sDocumentList + "<div class='outer-box'><div class='box'>" + sInsUpdLinks + "<p>" + sTitle + "</p><p class='documentType hide'>"+sDocumentType+"</p><p class='hide typeId'>"+iDocumentTypeId+"</p><p class='full-text hide'>" + sDescriptionLabel + "</p><p class='description hide'>" + sDescription + "</p><p class='filingStatus'>"+sFilingStatusDescription+"</p><p class='hide filingId'>" + iIdFilingStatus + "</p><p class='note full-text hide'>" + sNoteText + "</p><p></p><div class='text_here'><span class='ellipsis_text'>" + sNoteText + "</span></div><p>n/a</p></div></div>";
						}

					}
				}

				// Removes content from the section
				$("#business-view").find("section.documents").find("div.container").empty();

				if (oDocumentList.length > 0) {
					$("#business-view").find("section.documents").find("div.container").append(sDocumentList);

					// Configuring the initial settings to use
					$("section.documents").find('.text_here').ThreeDots({max_rows:2});

				}
				else {
					$("#business-view").find("section.documents").find('div.container').append("<p class='empty'>" + $.pgsi.locale.get("page.business.view.document.empty") + "</p>");
				}

				$("#add-document").unbind("click");

				$('.documents .links a').click(function(event)
				{
						event.preventDefault();
						var sDocument  = $(this).parents('.box');

							if (sDocument.find('p').hasClass('warning')) {
								var bIsFiled = true;
							} else {
								var bIsFiled = false;
							}

							var oDocument = new Document( {parentKey         : parseInt($('#business-id').val()),
														   parentKeyValue    : parseInt($('#business-type').val()),
														   documentType      : {id : isNaN(parseInt(sDocument.find('.typeId').text())) ? null : parseInt(sDocument.find('.typeId').text()) },
														   keywordText       : sDocument.find('.description').text(),
														   noteText          : sDocument.find('.note').text(),
														   isActionRequired  : bIsFiled,
														   filingStatusValue : isNaN(parseInt(sDocument.find('.filingId').text())) ? null : parseInt(sDocument.find('.filingId').text()),
														   id                : isNaN(parseInt($(this).attr("href"))) ?  null : parseInt($(this).attr("href")),
														   name              : sDocument.find('.documentType').text()});


						if ($(this).hasClass('delete')) {

							pgsi.pages.document.fnDelete(oDocument, oBusinessParent);

						}

						else {

								if ($(this).hasClass('edit')) {
									oDocument.modelAction = "UPDATE";
									var sUrl = "api/document/edit",
									sTitle   = sTitle = $.pgsi.locale.get("pages.business.dialog.document.title.edit", $('#company-name-field').text());
								}

								else {
									oDocument.modelAction = "INSERT";
									var sUrl = "api/document/insert",
									sTitle   = $.pgsi.locale.get("pages.business.dialog.document.title.add", $('#company-name-field').text());
								}

								pgsi.util.actiondialog.launchActionDialog(
									"insUpdDocuments",
									 pgsi.pages.business.dialogSettings.insUpdDocuments(
									 	$('#business-id').val(),
									 	sTitle,
									 	sUrl,
									 	new DocumentMaintenanceRequest({document : oDocument}),
									 	null)
									 );

						}
				});
			}

		},
		fnDelete : function(oDocument, oBusinessParent) {

				var iParentKeyValue = oDocument.parentKeyValue;

				var fnCallBackFetch = function(oResponseFetch) {

					if (oResponseFetch.operationSuccess == true) {

						if (iParentKeyValue === 1) {
							pgsi.pages.document.view.fill(oResponseFetch.organizationList[0].documentList, oBusinessParent);
						}

						else if (iParentKeyValue === 3) {
							pgsi.pages.identification.view.fill(oResponseFetch.memberList[0].documentList, oBusinessParent);
						}

						$("#action-dialog").dialog('close');
					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponseFetch,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
					$.pgsi.progressBar.stop();

				}

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {

						if (iParentKeyValue === 1) {
							sPurl = "api/organization/fetch";
						}

						else if (iParentKeyValue === 3) {
							sPurl = "api/member/fetch";
						}


						$.pgsi.ajax.post({
							 sUrl       : sPurl,
							 oRequest   : {id:parseInt($('#business-id').val())},
							 fnCallback : fnCallBackFetch
						});

					}else{
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}

				oDocument.modelAction = "DELETE";
				sTitle = $.pgsi.locale.get("pages.business.dialog.document.title.delete",$('#business-name').val());
				pgsi.util.actiondialog.launchActionDialog("deleteDialog", pgsi.pages.business.dialogSettings.deleteDialog("api/document/delete", new DocumentMaintenanceRequest({document : oDocument}),sTitle,fnCallBack,$.pgsi.locale.get("commons.pages.erroView","Document")));
		},
		fnFetchDocumentType : function(iBusinessType) {

			var iId="";
			var fnCallback = function(oResponse){
				if (oResponse) {
					$.each(oResponse, function(key, value) {
						if(value.trim() === "Social Security Number"){
							iId = key;

						}
					});
				}

			};
			$.pgsi.ajax.get(
				{
					sUrl : "fetchDocumentType?businessTypeEnum=" + iBusinessType,
					fnCallback : fnCallback,
					bAsync : false
				});

				return 	iId;
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