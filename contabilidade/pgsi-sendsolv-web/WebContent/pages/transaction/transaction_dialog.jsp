<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<hr class="top">

<div class="transation-dialog-wrapper">
	<div class="wrapper">
		<div class="col-all transfer">
		<div class="col1 newline">
			<h3  style="margin-top: 0"><s:message code="pages.transactions.dialog.detail.view.general.info" text="default text" /></h3>

			<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.confirmation" text="default text" /></div>
			<div id="confirmation" class="value"></div>
			<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.request.sent" text="default text" /></div>
			<div id="requestSent" class="value"></div>
			<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.request.status" text="default text" /></div>
			<div id="requestStatus" class="value"></div>
		</div>
		<div>
			<div class="col1">
				<h3><s:message code="pages.transactions.dialog.detail.view.exception.details" text="default text" /></h3>
				<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.error.type" text="default text" /></div>
				<div id="errorType" class="value"></div>
				<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.error.details" text="default text" /></div>
				<div id="errorDetails" class="value"></div>
				<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.error.message" text="default text" /></div>
				<div id="errorMessage" class="value"></div>
			</div>
		</div>
	</div>

	<hr>
	<h3><s:message code="pages.transactions.dialog.detail.view.request.data" text="default text" /></h3>
	<div id="transaction-ach-box">
		<div class="box">
			<div class="col-all">
				<table>
					<tbody>
						<tr>
							<td>
								<div class="col1 first">
									<h4><s:message code="pages.transactions.dialog.detail.view.sender" text="default text" /></h4>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.first.name" text="default text" /></div>
									<div id="senderFirstName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.middle.name" text="default text" /></div>
									<div id="senderMiddleName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.last.name" text="default text" /></div>
									<div id="senderLastName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.mother.maiden.name" text="default text" /></div>
									<div id="senderMotherMaidenName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.address" text="default text" /></div>
									<div id="senderAddress" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.city" text="default text" /></div>
									<div id="senderCity" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.state.province" text="default text" /></div>
									<div id="senderStateProvince" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.postal.code" text="default text" /></div>
									<div id="senderPostalCode" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.country" text="default text" /></div>
									<div id="senderCountry" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.sender.phone" text="default text" /></div>
									<div id="senderPhone" class="value"></div>
								</div>
							</td>
							<td>
								<div class="col1 ">
									<h4><s:message code="pages.transactions.dialog.detail.view.recipient" text="default text" /></h4>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.first.name" text="default text" /></div>
									<div id="recipientFirstName" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.middle.name" text="default text" /></div>
									<div id="recipientMiddleName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.last.name" text="default text" /></div>
									<div id="recipientLastName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.mother.maiden.name" text="default text" /></div>
									<div id="recipientMotherMaidenName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.address" text="default text" /></div>
									<div id="recipientAddress" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.city" text="default text" /></div>
									<div id="recipientCity" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.state.province" text="default text" /></div>
									<div id="recipientStateProvince" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.postal.code" text="default text" /></div>
									<div id="recipientPostalCode" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.country" text="default text" /></div>
									<div id="recipientCountry" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.recipient.phone" text="default text" /></div>
									<div id="recipientPhone" class="value"></div>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<div class="col1" style="clear:left">
									<h4><s:message code="pages.transactions.dialog.detail.view.transfer.details" text="default text" /></h4>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.total.amount" text="default text" /></div>
									<div id="totalAmount" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.fee" text="default text" /></div>
									<div id="fee" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.transfer.amount" text="default text" /></div>
									<div id="transferAmount" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.destination.currency" text="default text" /></div>
									<div id="destinationCurrency" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.effective.exchange.rate" text="default text" /></div>
									<div id="effectiveExchangeRate" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.transfer.amount.dest.currency" text="default text" /></div>
									<div id="transferAmountCurrency" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.destination.country" text="default text" /></div>
									<div id="destinationCountry" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.payer" text="default text" /></div>
									<div id="payerName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.transaction.method" text="default text" /></div>
									<div id="transactionMethod" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.account.number" text="default text" /></div>
									<div id="accountNumber" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.sender.payment.type" text="default text" /></div>
									<div id="senderPaymentType" class="value"></div>
								</div>
							</td>
							<td>
								<div class="col1">
									<h4><s:message code="pages.transactions.dialog.detail.view.transfer.details.pgsi.internal" text="default text" /></h4>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.order.number" text="default text" /></div>
									<div id="agentOrderName" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.region" text="default text" /></div>
									<div id="agentRegion" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.branch" text="default text" /></div>
									<div id="agentBranch" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.state" text="default text" /></div>
									<div id="agentState" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.country" text="default text" /></div>
									<div id="agentCountry" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.user.name" text="default text" /></div>
									<div id="agentUserName" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.terminal" text="default text" /></div>
									<div id="agentTerminal" class="value"></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.date" text="default text" /></div>
									<div id="agentDate" class="value "></div>
									<div class="newline label"><s:message code="pages.transactions.dialog.detail.view.transfer.details.agent.time" text="default text" /></div>
									<div id="agentTime" class="value"></div>
								</div>
							</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	</div>
</div>

<hr class="last">

<jsp:include page="../../scripts/pages/transaction/transaction_dialog_init.js.jsp" flush="true" />