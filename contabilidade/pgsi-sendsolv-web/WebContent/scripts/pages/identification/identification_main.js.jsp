<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

pgsi.pages.identification = {
	form : {

		fnInitForm : function() {
			pgsi.util.page.form.fnInitSelectmenu($("#identification-create").find("select"));
			pgsi.pages.identification.form.setFieldSizes();
		},

		setFieldSizes : function() {
			$("#document-type-button").outerWidth(220);
			$("#identification-country-button").outerWidth(220);
			$("#identification-region-button").outerWidth(220);
		},

		fnFillFields : function(oDocument) {
			$("#identification-parentkey").val(oDocument.parentKey);
			$("#identification-parentvalue").val(oDocument.parentValue);
			$("#identification-date").datepicker();
		},

		fillObject: function (oDocument) {
			if (!$.pgsi.isNullOrUndefined($('#document-type').val())) {
				var documentType = { id: parseInt($('#document-type').val()) };
				oDocument.documentType  = documentType;
			}
			oDocument.expirationDate =  new Date($("#identification-date").val()).getTime();
			oDocument.issueCountry = new Country({ code: $("#identification-country").val() });
			if (!$.pgsi.isNullOrUndefined($('#identification-region').val())) {
				oDocument.issueStateProvinceRegion = new StateProvinceRegion({ id: $("#identification-region").val() });
			}
			oDocument.value 	= $("#identification-number").val();

			return oDocument;
		},

		fnAjaxCallInsertUpdate : function(sUrl,oRequestDocument,iId) {

			var validator = $("#identification-create").validate({
				ignore : "",

				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						$(value.element).addClass("error");

						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
					});
				}
			});

			var bValidForm = validator.form();

			if (bValidForm) {

				oRequestDocument.document = pgsi.pages.identification.form.fillObject(oRequestDocument.document);

				var fnCallBackFetchs = function(oResponseFetch) {

					if (oResponseFetch.operationSuccess == true) {
						pgsi.pages.identification.view.fill(oResponseFetch.memberList[0].documentList, oResponseFetch.memberList[0]);
						$("#action-dialog").dialog('close');
					}

					else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponseFetch,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
					$.pgsi.progressBar.stop();
				}

				var fnCallBack = function(oResponse) {

					if (oResponse.operationSuccess == true) {

						$.pgsi.ajax.post({
							sUrl 		: "api/member/fetch",
							oRequest 	: {id : iId},
							fnCallback  : fnCallBackFetchs
						});
						$("#action-dialog").dialog('close');
					}

					else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}

				$.pgsi.ajax.post({
					 sUrl 		: sUrl,
					 oRequest 	: oRequestDocument,
					 fnCallback : fnCallBack
				});
			}
		}
	},

	view  : {
		fill : function(oDocumentList, oBusiness) {

			var sDocumentList = "";
			var oDocument;
			var sDeleteButton="";

			if (!$.pgsi.isNullOrUndefined(oDocumentList)) {

				for (var i=0; i < oDocumentList.length; i++) {
					oDocument = oDocumentList[i];

					// Social security number
					// it will be displayed under personal info
					if (oDocument.documentType.id === parseInt(pgsi.settings.ssnMember, 10)) {
						continue;
					}

					if(oDocument.issueCountry !== null) {

						var issueCountry =  oDocument.issueCountry.description;
					// IssueCountry
					}

					else {
						var issueCountry = "";
					}

					if (oDocument.issueStateProvinceRegion != null) {
						var issueStateProvinceRegion = oDocument.issueStateProvinceRegion.description + "/";
					} else {
						var issueStateProvinceRegion = "";
					}

					if ($.pgsi.isNullOrUndefined(oBusiness) || !pgsi.util.page.fnIsSDNFlagged(oBusiness.sdnstatus)) {
						<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
						sDeleteButton = "<div class='small-box'><div class='links viewNote'><a href='" + oDocument.id + "' class='ui-subtitle delete' title='" + $.pgsi.locale.get('commons.pages.delete') + "'> <span class='icon-small-button icon-nav icon-trash-bin delete'></span> <span>" + $.pgsi.locale.get('commons.pages.delete') + "</span></a></div></div>";
						</sec:authorize>
					}

					sDocumentList += "<div class='outer-box'><div class='box'><input type='hidden' name='id' value='" + oDocument.id + "'>" + sDeleteButton + "<div class='value newline title'>" + oDocument.documentType.name + "</div><div class='value newline'>" + oDocument.value + "</div><div class='label newline'>" + $.pgsi.locale.get("pages.member.view.expires") + " " +  $.pgsi.date.format(new Date(oDocument.expirationDate), "mm/dd/yy", null) + "</div><div class='label newline'>" + $.pgsi.locale.get("pages.member.view.issuedby") + " " + issueStateProvinceRegion +""+ issueCountry + "</div></div></div>";
				}
			}

			$("section.identification").find(".container").empty();

			if (sDocumentList.length > 0) {
				$("section.identification").find(".container").append(sDocumentList);
			}

			else {
				$("section.identification").find('div.container').append("<p class='empty'>" + $.pgsi.locale.get("page.business.view.document.empty") + "</p>");
			}

			$("#link-add-id").unbind("click");
			pgsi.pages.identification.view.attachClickEvents($("section.identification.view"));

		},

		attachClickEvents : function ($section) {
			$section.find("a").click(function(event) {
				event.preventDefault();

				var oDocument = new Document();
				var $box = $(this).parents(".box");
				if (!$box.hasClass("box")) { $box = null; }

				oDocument = pgsi.pages.identification.view.fillObject(oDocument, $box);

				var oRequest = new DocumentMaintenanceRequest({document : oDocument });

				if ($(this).hasClass('delete')) {
					pgsi.pages.document.fnDelete(oDocument);
				}

				else {

					oDocument.modelAction = "INSERT";
					var sUrl = "api/document/insert",
					sTitle   = $.pgsi.locale.get("pages.business.dialog.document.title.add", $('#business-name').val());

					pgsi.util.actiondialog.launchActionDialog("insUpdDocuments", pgsi.pages.business.dialogSettings.insUpdDocuments(oRequest.document.parentKey,sTitle,sUrl,oRequest,null));

				}
			});
		},

		fillObject : function (oDocument, $parent) {
			oDocument.parentKey = parseInt($('#business-id').val());
			oDocument.parentKeyValue = parseInt($('#business-type').val());

			if (!$.pgsi.isNullOrUndefined($parent)) {
				oDocument.id = parseInt($parent.find("input[name='id']").val());
			}

			return oDocument;
		},
	}
}

</script>
</sec:authorize>