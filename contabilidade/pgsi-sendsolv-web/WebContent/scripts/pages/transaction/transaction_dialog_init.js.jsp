<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.transaction
 * @description The init namespace for the Transaction Dialog.
 * @author Gustavo Peres
 */
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

	//-- call the functions that are responsbible to fill the informations on the transaction detail dialog screen
	if (!$.pgsi.isNullOrUndefined(oPreLoadResponse) &&
		!$.pgsi.isNullOrUndefined(oPreLoadResponse.moneyTransferList[0]))
	{
		if (!$.pgsi.isNullOrUndefined(oPreLoadResponse.moneyTransferList[0].statusList))
		{
			if (!$.pgsi.isNullOrUndefined(oPreLoadResponse.moneyTransferList[0].statusList[parseInt($.address.parameter("statusId"),10)].moneyTransferTransaction)){
				//-- fill the sender (member) information
				pgsi.pages.transaction.fnFillSenderDialog(oPreLoadResponse.moneyTransferList[0].statusList[parseInt($.address.parameter("statusId"),10)].moneyTransferTransaction.sender,'transation-dialog-wrapper');

				//-- fill the recipient information
				pgsi.pages.transaction.fnFillRecipientDialog(oPreLoadResponse.moneyTransferList[0].statusList[parseInt($.address.parameter("statusId"),10)].moneyTransferTransaction.recipient,'transation-dialog-wrapper');

				//-- fill the transfer details
				pgsi.pages.transaction.fnFillTransferDetailsDialog(oPreLoadResponse.moneyTransferList[0].statusList[parseInt($.address.parameter("statusId"),10)].moneyTransferTransaction,'transation-dialog-wrapper',oPreLoadResponse.moneyTransferList[0]);
			}
		}


	}
	$.pgsi.progressBar.stop();
});

</script>

</sec:authorize>