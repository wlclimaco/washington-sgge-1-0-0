<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div class="transaction-detail">

	<nav class="secondary">
		<span><a href="dashboard" class="alist" title="Payments"><s:message code="commons.pages.payments" text="default text" /></a></span><span
			class="icon-nav icon-angle-right bullet"></span><span>
			<a href="payment/batches" class="alist" title="Find Batch"><s:message code="pages.batches.page.find.batch" text="default text" /></a></span>
			<span class="icon-nav icon-angle-right bullet"></span>
			<span><a href="" id="batches" class="alist" title=""></a></span>
			<span class="icon-nav icon-angle-right bullet"></span><span id="location-transaction"></span>
	</nav>
	<h2 id="transaction"></h2>
	<div class="content">
		<div class="internal">
			<div class="newline label"><s:message code="pages.batches.transaction.table.transactionId" text="default text" /></div>
			<div id="transaction-id" class="value"></div>
			<div class="label"><s:message code="pages.batches.transaction.table.status" text="default text" /></div>
			<div id="transaction-status" class="value"></div>
			<div class="confirmation-number"></div>
			<div class="alert hide msg-rejected"
				style="clear: none; width: auto; margin-left: 24px; margin-top: -5px;">
				<span class="icon-nav icon-exclamation-triangle"></span>
				<div class=" label" style="width: auto"></div>
			</div>
			<div id="btn"></div>
		</div>
		<div id="error-detail"></div>

		<hr class="top">
		<div class="col-all batch">
			<h3><s:message code="pages.batches.batch.info" text="default text" /></h3>
			<a  id="batchId" href="javascript:;" class="parent newline alist form-link"><s:message code="commons.pages.batch" text="default text" /></a>
			<div class="location">
				<div class="newline label"><s:message code="commons.pages.location" text="default text" /></div>
				<div id="location" class="value" style="max-width: 1000px"></div>
			</div>
			<div class="location-sdn">
				<div class="newline label"><s:message code="commons.pages.location" text="default text" /></div>
				<div class="value security high" style="max-width: 1000px">
				</div>
				<div class="newline label"><s:message code="commons.pages.organization" text="default text" /></div>
				<div class="value organization"></div>
			</div>
			<div class="newline label"><s:message code="commons.pages.region" text="default text" /></div>
			<div id="region" class="value"></div>
			<div id="create-date" class="hide">
				<div class="newline label"><s:message code="pages.batches.tables.created" text="default text" /></div>
				<div id="create-value" class="value"></div>
			</div>
			<div class="newline label"><s:message code="pages.batches.dialog.transfer.date" text="default text" /></div>
			<div id="transfer-date" class="value"></div>
		</div>

		<hr>
		<div class="col-all member">

			<h3><s:message code="commons.pages.member" text="Member" /></h3>
			<a href="javascript:;" title="Edit Member Information" id="link-edit-memberinfo"class="form-link alist"><span class="icon-nav icon-pencil"></span><s:message code="commons.pages.edit" text="default text" /></a>
			<a href="javascript:;" class="parent first alist"></a>
			<div class="col1 first">
				<div class="newline label"><s:message code="pages.contacts.form.label.firstname" text="default text" /></div>
				<div class="value" id="senderFirstName"></div>
				<div class="newline label"><s:message code="pages.contacts.form.label.middlename" text="default text" /></div>
				<div class="value" id="senderMiddleName"></div>
				<div class="newline label"><s:message code="pages.contacts.form.label.lastname" text="default text" /></div>
				<div class="value" id="senderLastName"></div>
				<div class="newline label"><s:message code="pages.member.form.label.mothername" text="default text" /></div>
				<div class="value" id="senderMotherMaidenName"></div>
				<div class="newline label"><s:message code="pages.form.label.address" text="default text" /></div>
				<div class="value" id="senderAddress"></div>
				<div class="newline label"><s:message code="commons.pages.city" text="default text" /></div>
				<div class="value" id="senderCity"></div>
				<div class="newline label"><s:message code="commons.pages.state.province" text="default text" /></div>
				<div class="value" id="senderStateProvince"></div>
				<div class="newline label"><s:message code="pages.form.label.zippostalcode" text="default text" /></div>
				<div class="value" id="senderPostalCode"></div>
				<div class="newline label"><s:message code="commons.pages.country" text="default text" /></div>
				<div class="value" id="senderCountry"></div>
				<div class="newline label"><s:message code="pages.form.label.phonenumber" text="default text" /></div>
				<div class="value" id="senderPhone"></div>
			</div>
		</div>
		<!-- 		<hr> -->
		<div class="col-all recipient">
			<h3><s:message code="commons.pages.recipient" text="default text" /></h3>
			<a href="javascript:;" title="Edit Member Information" id="link-edit-recipientinfo" class="form-link alist"><span class="icon-nav icon-pencil"></span><s:message code="commons.pages.edit" text="default text" /></a>
			<a href="javascript:;" class="parent first alist"></a>
			<div class="col1 newline">
				<div class="newline label"><s:message code="pages.contacts.form.label.firstname" text="default text" /></div>
				<div class="value" id="recipientFirstName" ></div>
				<div class="newline label"><s:message code="pages.contacts.form.label.middlename" text="default text" /></div>
				<div class="value" id="recipientMiddleName" ></div>
				<div class="newline label"><s:message code="pages.contacts.form.label.lastname" text="default text" /></div>
				<div class="value" id="recipientLastName" ></div>
				<div class="newline label"><s:message code="pages.member.form.label.mothername" text="default text" /></div>
				<div class="value" id="recipientMotherMaidenName" ></div>
				<div class="newline label"><s:message code="pages.form.label.address" text="default text" /></div>
				<div class="value" id="recipientAddress" ></div>
				<div class="newline label"><s:message code="commons.pages.city" text="default text" /></div>
				<div class="value" id="recipientCity" ></div>
				<div class="newline label"><s:message code="commons.pages.state.province" text="default text" /></div>
				<div class="value" id="recipientStateProvince" ></div>
				<div class="newline label"><s:message code="pages.form.label.zippostalcode" text="default text" /></div>
				<div class="value" id="recipientPostalCode" ></div>
				<div class="newline label"><s:message code="commons.pages.country" text="default text" /></div>
				<div class="value" id="recipientCountry" ></div>
				<div class="newline label"><s:message code="pages.form.label.phonenumber" text="default text" /></div>
				<div class="value" id="recipientPhone" ></div>
			</div>
		</div>
		<!-- 		<hr> -->
		<div class="col-all transfer error-detail">
			<h3><s:message code="pages.member.view.tranfer" text="default text" /></h3>
			<a href="" title="Edit Transfer Information" id="link-edit-transfer" class="form-link alist"><span class="icon-nav icon-pencil"></span><s:message code="commons.pages.edit" text="default text" /></a>
			<a href="" class="parent first alist"></a>
			<div class="alert-transfer"></div>


			<div class="col2 newline">
				<div class="newline label"><s:message code="pages.transfer.form.label.total.deduction" text="default text" /></div>
				<div class="value" id="totalAmount" ></div>
				<div class="newline label"><s:message code="pages.batches.transaction.table.fee" text="default text" /></div>
				<div class="value" id="fee" ></div>
				<div class="newline label"><s:message code="pages.transaction.transfer.amount" text="default text" /></div>
				<div class="value" id="transferAmount" ></div>
				<div class="newline label"><s:message code="pages.transfer.form.label.destination.currency" text="default text" /></div>
				<div class="value" id="destinationCurrency" ></div>
				<div class="newline label"><s:message code="pages.transfer.form.label.effective.exchange" text="default text" /></div>
				<div class="value" id="effectiveExchangeRate" ></div>
				<div class="newline label"><s:message code="pages.transfer.form.label.transfer.amount.mxn" text="default text" /></div>
				<div class="value" id="transferAmountCurrency" ></div>
				<div class="newline label"><s:message code="pages.transfer.form.label.country" text="default text" /></div>
				<div class="value" id="destinationCountry" ></div>
				<div class="newline label"><s:message code="pages.transfer.form.label.payer" text="default text" /></div>
				<div class="value" id="payerName" ></div>
				<div class="newline label"><s:message code="pages.transfer.form.label.transaction.method" text="default text" /></div>
				<div class="value" id="transactionMethod" ></div>
				<div class="account hide">
					<div class="newline label"><s:message code="pages.transfer.form.label.account.number" text="default text" /></div>
					<div class="value" id="senderPaymentType" ></div>
				</div>
			</div>
		</div>
		<hr>
		<div class="col-all trans-hist">
			<div class="col1 newline">
				<h3><s:message code="pages.transfer.form.label.transaction.history" text="default text" /></h3>
				<div class="list-status"></div>

		    </div>
		<br> <br>
	    </div>
</div>
</div>
<jsp:include page="../../scripts/pages/transaction/transaction_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transaction/transaction_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transaction/transaction_view_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transaction/transactions_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_actions.js.jsp" flush="true" />