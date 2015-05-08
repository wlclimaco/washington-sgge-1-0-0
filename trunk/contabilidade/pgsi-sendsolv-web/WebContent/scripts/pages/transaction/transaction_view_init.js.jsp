<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.transaction
 * @description The init namespace for the Transaction View Page.
 * @author QAT BRAZIL
 */

//Receives preloaded data
<c:choose>
	<c:when test="${empty response}">
    	var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${response};
    </c:otherwise>
</c:choose>

$(document).ready(function()
{

	if (!$.pgsi.isNullOrUndefined(oPreLoadResponse) &&
			!$.pgsi.isNullOrUndefined(oPreLoadResponse.moneyTransferBatchList[0]))
	{
		pgsi.pages.transaction.view.fnBatchInfo(oPreLoadResponse.moneyTransferBatchList[0]);

		if (!$.pgsi.isNullOrUndefined(oPreLoadResponse.moneyTransferBatchList[0].moneyTransferList))
		{
			oMoneyTransferList = oPreLoadResponse.moneyTransferBatchList[0].moneyTransferList
			for(var i = 0;i<oMoneyTransferList.length;i++){

				if(oMoneyTransferList[i].id === parseInt($.address.parameter("transactionId"),10)){



					//-- fill the sender (member) information
					pgsi.pages.transaction.view.fnMemberInfo(oMoneyTransferList[i].moneyTransferDetail.member);

					//-- fill the recipient information
					pgsi.pages.transaction.view.fnRecipientInfo(oMoneyTransferList[i].moneyTransferDetail.recipient);

					//-- fill the transfer details
					pgsi.pages.transaction.view.fnTransferInfo(oMoneyTransferList[i]);

					pgsi.pages.transaction.view.fnHistoryStatus(oMoneyTransferList[i]);

				}

			}
		}
		pgsi.pages.transaction.view.fnPageHeader(oPreLoadResponse.moneyTransferBatchList[0]);
	}

	$(".btn").button();
	$("#tabs").tabs();
	var hideAllHovers = function() {
		$(".hover-card").hide();
	}
	$("#emp-hover-ref").hover(function() {
		hideAllHovers();
		$("#employment-hover").show();
	}, function() {
	});
	$("#transfer-hover-ref").hover(function() {
		hideAllHovers();
		$("#transfer-hover").show();
	}, function() {
	});

	$("#note-hover-ref").hover(function() {
		hideAllHovers();
		$("#note-hover").show();
	}, function() {
	});
	$("#risk-hover-ref").hover(function() {
		hideAllHovers();
		$("#risk-hover").show();
	}, function() {
	});
	$("#id-hover-ref").hover(function() {
		hideAllHovers();
		$("#id-hover").show();
	}, function() {
	});
	hideAllHovers();

	$(".show-secquestion,.hide-secquestion").click(function(e) {
		e.preventDefault();
		$(".secquestion, .show-secquestion").toggle();
	});

	$(".show-pin,.hide-pin").click(function(e) {
		e.preventDefault();
		$(".pin, .show-pin").toggle();
		$(".pin-verify").hide();
	});

	$("#link-edit-memberinfo").click(function(e) {
		e.preventDefault();
		var close = function() {
			$("#dialog-edit-memberinfo").dialog('close');
		}
		$("#dialog-edit-memberinfo").dialog({
			modal : true,
			open : function(event, ui) {
				$("#dialog-edit-memberinfo select").selectmenu();
			},
			buttons : {
				"Cancel" : close,
				"Save" : close,
			},
			width : 970
		});
	});
	$("#link-edit-recipientinfo").click(function(e) {
		e.preventDefault();
		var close = function() {
			$("#dialog-edit-recipientinfo").dialog('close');
		}
		$("#dialog-edit-recipientinfo").dialog({
			modal : true,
			open : function(event, ui) {
				$("#dialog-edit-recipientinfo select").selectmenu();
			},
			buttons : {
				"Cancel" : close,
				"Save" : close
			},
			width : 970
		});
	});

	$(".link-ach-details").click(function(e) {
		e.preventDefault();
		var close = function() {
			$("#dialog-ach-details").dialog('close');
		}
		$("#dialog-ach-details").dialog({
			modal : true,
			open : function(event, ui) {
			},
			buttons : {
				"Cancel" : close,
				"Save" : close
			},
			width : 970
		});
	});

	$("#link-edit-transfer").click(function(e) {
		e.preventDefault();
		var close = function() {
			$("#dialog-edit-transfer").dialog('close');
		}
		$("#dialog-edit-transfer").dialog({
			modal : true,
			open : function(event, ui) {
				$("#dialog-edit-transfer select").selectmenu();
				var hideAll = function() {
					$(".account, #account-type2-button").hide();
				};
				$("#method").on("selectmenuchange", function(event, ui) {
					hideAll();
					switch (ui.item.value) {
					case 'D':
						$(".account, #account-type2-button").show();
						break;
					default:
					}
				});
			},
			buttons : {
				"Cancel" : close,
				"Update Transaction Only" : close,
				"Update Transaction and Transfer Settings" : close
			},
			width : 970
		});
	});
	$.pgsi.progressBar.stopGlobal();
});

</script>

</sec:authorize>