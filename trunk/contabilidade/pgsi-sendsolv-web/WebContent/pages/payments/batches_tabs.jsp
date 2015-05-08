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
			<s:message code="pages.batches.page.find.batch" text="default text" />
		</span>
	</span>
	<span id="company-name"></span>
</nav>

<h2><s:message code="pages.batches.page.find.batch" text="default text" /></h2>

<div id="tabs">
	<ul>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="progress" data-tab="ALL" title='<s:message code="pages.batches.tabs.progress" text="default text" />' data-title='<s:message code="pages.batches.tabs.progress" text="default text" />'>
				<s:message code="pages.batches.tabs.progress" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="approval" data-tab="PENDING_APPROVAL" title='<s:message code="pages.batches.tabs.approval" text="default text" />' data-title='<s:message code="pages.batches.tabs.approval" text="default text" />'>
				<s:message code="pages.batches.tabs.approval" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="spreads" data-tab="SPREAD_REVIEW" title='<s:message code="pages.batches.tabs.spreads" text="default text" />' data-title='<s:message code="pages.batches.tabs.spreads" text="default text" />'>
				<s:message code="pages.batches.tabs.spreads" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="release" data-tab="READY_FOR_RELEASE" title='<s:message code="pages.batches.tabs.release" text="default text" />' data-title='<s:message code="pages.batches.tabs.release" text="default text" />'>
				<s:message code="pages.batches.tabs.release" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="ach_processing" data-tab="ACH_EXCEPTIONS|ACH_PROCESSING" title='<s:message code="pages.batches.tabs.ach.processing" text="default text" />' data-title='<s:message code="pages.batches.tabs.ach.processing" text="default text" />'>
				<s:message code="pages.batches.tabs.ach.processing" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="expired" data-tab="EXPIRED" title='<s:message code="pages.batches.tabs.expired" text="default text" />' data-title='<s:message code="pages.batches.tabs.expired" text="default text" />'>
				<s:message code="pages.batches.tabs.expired" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="completed" data-tab="BATCH_PROCESSED|CANCELLED" title='<s:message code="pages.batches.tabs.completed" text="default text" />' data-title='<s:message code="pages.batches.tabs.completed" text="default text" />'>
				<s:message code="pages.batches.tabs.completed" text="default text" />
			</a>
		</li>

	</ul>
	<div id="primaryContent" class="tabs">
		<jsp:include page="../payments/batches_main.jsp" flush="true" />
	</div>

</div>

<jsp:include page="../../scripts/pages/payments/batches_tabs_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/batches_tabs_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/payments_actions.js.jsp" flush="true" />


