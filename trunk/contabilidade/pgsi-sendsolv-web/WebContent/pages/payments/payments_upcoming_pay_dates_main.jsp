<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">
	<a class="alist" href="payment/upcoming_pay_dates" text="<s:message code="commons.pages.payments" text="default text" />">
		<span><s:message code="commons.pages.payments" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="pages.payments.menu.upcoming.pay.dates" text="default text" />
		</span>
	</span>
	<span id="company-name"></span>
</nav>

<h2 class="list"><s:message code="pages.payments.menu.upcoming.pay.dates" text="default text" /></h2>

<div class="content list">
	<table id="data_list"></table>
</div>

<jsp:include page="../../scripts/pages/payments/payments_upcoming_pay_dates_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/payments_upcoming_pay_dates_init.js.jsp" flush="true" />