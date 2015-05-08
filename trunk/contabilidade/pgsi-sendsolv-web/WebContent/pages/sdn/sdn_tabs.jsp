<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">

	<a class="alist" href="sdn" text="<s:message code="commons.pages.compliance" text="default text" />">
		<span><s:message code="commons.pages.compliance" text="default text"/></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="pages.sdn.dashboard.match.SDN" text="default text" />
		</span>
	</span>
</nav>

<h2> <s:message code='pages.sdn.dashboard.match.SDN' text='default text' /></h2>

<div id="tabs">
	<ul>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="individual" data-tab="INDIVIDUAL" title='<s:message code="pages.sdn.tabs.individual" text="default text" />' data-title='<s:message code="pages.sdn.tabs.individual" text="default text" />'>
				<s:message code="pages.sdn.tabs.individual" text="default text" />
			</a>
		</li>
		<li>
			<a href="#primaryContent" class="batches-tab tabs" id="entity" data-tab="ENTITY" title='<s:message code="pages.sdn.tabs.entity" text="default text" />' data-title='<s:message code="pages.sdn.tabs.entity" text="default text" />'>
				<s:message code="pages.sdn.tabs.entity" text="default text" />
			</a>
		</li>
	</ul>
	<div id="primaryContent" class="tabs">
		<jsp:include page="../sdn/sdn_main.jsp" flush="true" />
	</div>

</div>

<jsp:include page="../../scripts/pages/sdn/sdn_tabs_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/sdn/sdn_tabs_init.js.jsp" flush="true" />



