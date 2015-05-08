<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
	pgsi.pages.transaction.dialogSettings = {

		dialogTransactionDetail : function (sTitle, iId) {

			return {

				title : sTitle,
				width : 970,

				close : function () {

				},

				buttons : (function () {

					var oButtons = {};

					// Close Button
					oButtons[$.pgsi.locale.get("commons.pages.close")] = function() {

						$("#action-dialog").dialog('close');
					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("transaction/transaction_detail_dialog?transactionId="+iId, function() {
						actionDialog.dialog('open');
					});
				}
			}
		},

		changeMoneyTransferStatus : function (sTitle, sMessage, oMoneyTransferStatus, fnCallback) {
			return {

				title : sTitle,
				width : 650,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.confirmation")] = function () {

						var validator = $("#dialogStatusBatchForm").validate();
						if (validator.form()) {

							var sNoteText = $("#dialogStatusBatchForm").find("textarea").val();

							var oMoneyTransferStatusList = [
								oMoneyTransferStatus
							];

							var oRequest = new MoneyTransferStatusMaintenanceRequest({
								moneyTransferStatusList : oMoneyTransferStatusList,
								note : sNoteText
							});

							$.pgsi.ajax.post({
								 sUrl 		: 'api/moneyTransferStatus/insert',
								 oRequest 	: oRequest,
								 fnCallback : fnCallback
							});

							$("#action-dialog").dialog('close');
						}

					};

					// No Button
					oButtons[$.pgsi.locale.get("pages.batches.dialog.cancel")] = function() {

						$("#action-dialog").dialog('close');
					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("batch/dialogInsertStatus?userId=" + pgsi.settings.userContext.userId, function() {

						actionDialog.dialog('open');
						// Set message text

						if (!$.pgsi.isNullOrUndefined(sMessage)) {
							$("#dialog-status-batch").find(".alert").removeClass('hide')
							$("#dialog-status-batch").find(".alert").find(".label").text(sMessage);
						}else{
							$("#dialog-status-batch").find(".alert").hide();
						}
					});
				}
			}
		}
	}
</script>

</sec:authorize>