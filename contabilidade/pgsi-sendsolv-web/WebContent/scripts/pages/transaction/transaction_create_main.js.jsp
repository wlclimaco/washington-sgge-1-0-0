<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
	pgsi.pages.transaction.form = {

			fnInitForm : function ($form, oResponse) {
				pgsi.util.page.form.fnInitForm($form);

				$("#pricing").selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field").outerWidth(150);

				$('#transfer-date, #expires').datepicker();

				$("#amount").inputmask();
				$("#fee").inputmask();
				// Mask Date Fields
				$("#expires").mask("99/99/9999");
				$("#transfer-date").mask("99/99/9999");


				if ($.pgsi.isNullOrUndefined(oResponse.moneyTransferBatchList)) {
					$form.find('input[type="radio"]').first().prop("checked", true);
					return;
				}

				var sBatches = "";
				var sInput = "";
				var sLabel = "";
				var sNote = "";

				for (var i=0; i < oResponse.moneyTransferBatchList.length; i++) {
					if (oResponse.moneyTransferBatchList[i].noteList.length > 0) {
						sNote = " " + oResponse.moneyTransferBatchList[i].noteList[oResponse.moneyTransferBatchList[i].noteList.length - 1].noteText;
					}

					sInput = '<input type="radio" class="first radio newline" name="batch" value="'+ i +'">';
					sLabel = '<label>' + $.pgsi.locale.get('pages.batches.dialog.createOneOffTransaction.addToBatch') + '<b>&nbsp#' + oResponse.moneyTransferBatchList[i].key + sNote  + '</b>&nbsp(' + $.pgsi.locale.get('pages.batches.view.transfer.date') + ') ' + $.pgsi.date.format(new Date(oResponse.moneyTransferBatchList[i].transferDate), "mm/dd/yy", null) + '</label>';

					sBatches += sInput + "&nbsp" + sLabel;
				}

				$form.find('hr').first().after(sBatches);

				$form = $('#dialog-one-off-form');

				$form.find("input[name='batch']").change(function(e) {
					if (($(this).attr('id') === "new-batch") && $(this).prop('checked')) {
						$("#description").removeClass('hide').addClass('required').prev().removeClass('hide');
						$("#transfer-date").removeClass('hide').addClass('required').prev().removeClass('hide');
					}

					else {
						$("#description").addClass('hide').removeClass('required').removeClass('error').prev().addClass('hide');
						$("#transfer-date").addClass('hide').removeClass('required').removeClass('error').prev().addClass('hide');
					}

				});

				$form.find('input[type="radio"]').first().prop("checked", true).trigger('change');

				$("#fee").blur(function(e){
					if ($(this).val.length > 0) {
						$("#expires").addClass('required');
					}
					else {
						$("#expires").removeClass('required');
					}
				});
			},

			fnAjaxRequest : function (oMoneyTransferBatch) {

				// Refresh member pag e
				var fnCallback = function(oResponse) {
					if (oResponse.operationSuccess === true) {
						$.pgsi.ajax.post({
							sUrl       : "api/member/fetch",
							oRequest   : { id : $("#business-id").val() },

							fnCallback : function(oResponse) {
								pgsi.pages.member.view.fnFillFields(oResponse);
							}
						});
					}

					else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}

				var request = new MoneyTransferBatchMaintenanceRequest({
					moneyTransferBatch : oMoneyTransferBatch
				});

				$.pgsi.ajax.post({
					sUrl : "api/payment/createOneOffTransaction",
					oRequest : request,
					fnCallback : fnCallback
				});
			},

			fnInsertMoneyTransferBatch : function (oLocation) {
				$.pgsi.ajax.post({
					sUrl : "api/payment/createOneOffTransaction",
					oRequest : request,
					fnCallback : fnCallback
				});
			},

			fnFillMoneyTransferBatch : function(oMoneyTransferBatch, oMoneyTransfer) {
				oMoneyTransferBatch.moneyTransferList = [oMoneyTransfer];

				return oMoneyTransferBatch;
			},

			fnCreateMoneyTransferBatch : function(oMoneyTransfer) {
				oMoneyTransferBatch =  new MoneyTransferBatch({
					modelAction : "INSERT",
					moneyTransferList : [
						oMoneyTransfer
					]
				});

				return oMoneyTransferBatch;
			},

			fnFillTransferSetting : function(oTransferSetting) {
				oTransferSetting.modelAction = "INSERT";
				oTransferSetting.transferAmount = parseFloat($("#amount").val().substring(4).replace(/,/g,""));
				oTransferSetting.productPlanApplicability.productPlanId = $("#pricing").val();

				if ($("#fee").val().length > 0 && $('#expires').val().length > 0) {

					dDateFrequency = new Date($('#expires').val());

					oTransferSetting.customFeeList = [{
						modelAction : "INSERT",
						effectiveEndDate : dDateFrequency.getTime(),
						value : parseFloat($("#fee").val().substring(4).replace(/,/g,"")),
						status : "ACTIVE"
					}];
				}

				return oTransferSetting;
			},

			fnCreateMoneyTransfer : function(oTransferSetting) {
				var oMoneyTransfer = new MoneyTransfer({
					modelAction : "INSERT",
					transferSetting : oTransferSetting
				});

				return oMoneyTransfer;
			}
		}

</script>

</sec:authorize>