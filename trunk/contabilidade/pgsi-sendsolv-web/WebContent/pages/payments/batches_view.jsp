<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<input type="hidden" name="batchId" id="batch-id" value="0" />
<input type="hidden" name="batchStatus" id="batch-status" value="" />
<input type="hidden" name="locationId" id="location-id" value="" />
<input type="hidden" name="version" id="version-number" value="" />
<nav class="secondary">
	<a class="alist" href="payment/batches" text="Payments">
		<span><s:message code="commons.pages.payments" text="default text" /></span>
	</a>
	<a class="alist" href="payment/batches" text="Payments">
		<span> > <s:message code="pages.batches.page.find.batch" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span id="icon-nav">

		</span>
	</span>
	<span id="company-name"></span>
</nav>

	<div id="title">
		<h2 class="list"></h2>
	</div>
	<div class="content list batch">
		<div class="header">
			<div class="summary">
				<div class="cont">
					<div class="batch-summary">
					</div>
					<div class="fund-summary">
					</div>
					<div class="tx-summary" style="width: 450px">
					</div>
					<div class="batch-actions">
					</div>
				</div>
			</div>
			<hr>
		</div>
		<div class="body">
			<div class="content list">
				<div class="filter">
					<form id="filterForm"></form>
				</div>
				<table id="data_list"></table>
			</div>
		</div>
	</div>
<jsp:include page="../../scripts/pages/payments/payments_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/batches_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transaction/transaction_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/batches_view_init.js.jsp" flush="true" />