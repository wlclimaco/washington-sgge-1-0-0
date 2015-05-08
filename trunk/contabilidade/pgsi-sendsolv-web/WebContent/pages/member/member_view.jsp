<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<input type="hidden" name="memberId" id="member-id"/>
<input type="hidden" name="statusValue" id="status-value" />
<input type="hidden" name="personType" id="personType" />
<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
	<input type="hidden" name="personPepStatus" id="personPepStatus">
</sec:authorize>
<input type="hidden" name="personSdnStatus" id="personSdnStatus">

<nav class="secondary">
	<a class="alist" href="member">
		<span><s:message code="commons.pages.members" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right"></span>
	<span id="member-name"></span>
</nav>

<h2 id="member-title"></h2>

<input type="hidden" name="business-id" id="business-id" />
<input type="hidden" name="business-name" id="business-name" />
<input type="hidden" name="business-type" id="business-type" value="3" />

<div id="tabs">
	<ul>
		<li>
			<a href="#" id="personInfo" data-tab="info" title="<s:message code='pages.recipient.view.info' text='default text' />" data-title="<s:message code='pages.member.view.info' text='default text' />"><s:message code='pages.member.view.info' text='default text' /></a>
		</li>
		<li>
			<a href="#" id="recipientList" data-tab="recipients" title="<s:message code='pages.member.view.recipients' text='default text' />" data-title="<s:message code='pages.member.view.recipients' text='default text' />"><s:message code='pages.member.view.recipients' text='default text' /></a>
		</li>
		<li>
			<a href="#" id="transactionList" data-tab="transactions" title="<s:message code='pages.member.recent.transactions' text='default text' />" data-title="<s:message code='pages.member.recent.transactions' text='default text' />"><s:message code='pages.member.recent.transactions' text='default text' /></a>
		</li>
		<li class="add_loc_link">
			<span class="icon-small-button icon-nav icon-plus"></span>
			<s:message code='pages.member.view.addtransfer' text='default text' />
		</li>
	</ul>
</div>
<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/risk/risk_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/document/document_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transaction/transaction_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/member/member_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/identification/identification_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/employment/employment_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/member/member_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/member/member_view_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/payments_actions.js.jsp" flush="true" />

