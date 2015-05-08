<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<input type="hidden" name="memberId" id="member-id" value="0" />
<input type="hidden" name="statusValue" id="status-value" value="0" />
<input type="hidden" name="personType" id="personType" />
<input type="hidden" name="personPepStatus" id="personPepStatus">

<nav class="secondary">
	<a class="alist" href="member">
		<span><s:message code="commons.pages.members" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right"></span>
	<a class="alist" href="recipient">
		<span><s:message code="commons.pages.recipients" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right"></span>
	<span id="recipient-name"></span>
</nav>

<h2 id="recipient-title"></h2>
<input type="hidden" name="business-id" id="recipient-id" value="0" />
<input type="hidden" name="business-id" id="business-id" value="" />
<input type="hidden" name="business-name" id="business-name" value="" />
<input type="hidden" name="business-type" id="business-type" value="5" />

<div id="tabs">
	<ul>
		<li>
			<a href="#" id="personInfo" data-tab="info"  title="<s:message code='pages.recipient.view.info' text='default text' />" data-title="<s:message code='pages.recipient.view.info' text='default text' />"><s:message code='pages.recipient.view.info' text='default text' /></a>
		</li>
		<li>
			<a href="#" id="memberList" data-tab="members" title="<s:message code='commons.page.members' text='default text' />" data-title="<s:message code='commons.page.members' text='default text' />"><s:message code='commons.pages.members' text='default text' /></a>
		</li>
	</ul>

</div>

<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/risk/risk_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/document/document_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/employment/employment_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/member/member_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/identification/identification_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/identification/identification_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_view_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/transfers/transferSetting_actions.js.jsp" flush="true" />
