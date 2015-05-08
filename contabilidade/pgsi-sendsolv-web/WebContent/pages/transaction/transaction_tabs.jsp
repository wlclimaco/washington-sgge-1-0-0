<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>


<nav class="secondary">
	<a class="alist" href="payment/batches" text="<s:message code="commons.pages.payments" text="default text" />">
		<span><s:message code="commons.pages.payments" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="pages.transaction.page.find" text="default text" />
		</span>
	</span>
	<span id="company-name"></span>
</nav>

<h2><s:message code="pages.transaction.page.find" text="default text" /></h2>

<div id="tabs">
	<ul>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="all" data-tab="all" title='<s:message code="commons.pages.all" text="default text" />' data-title='<s:message code="commons.pages.all" text="default text" />'>
				<s:message code="commons.pages.all" text="default text" />
			</a>

		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs"id="exceptions" data-tab="exceptions" title='<s:message code="commons.pages.exceptions" text="default text" />' data-title='<s:message code="commons.pages.exceptions" text="default text" />'>
				<s:message code="commons.pages.exceptions" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="cancellations" data-tab="cancellations" title='<s:message code="pages.transaction.cancellations" text="default text" />' data-title='<s:message code="pages.transaction.cancellations" text="default text" />'>
				<s:message code="pages.transaction.cancellations" text="default text" />
			</a>
		</li>
	</ul>
	<div id="primaryContent" class="tabs">
		<jsp:include page="../transaction/transaction_main.jsp" flush="true" />
	</div>

</div>

<jsp:include page="../../scripts/pages/transaction/transaction_tabs_init.js.jsp" flush="true" />


