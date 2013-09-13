<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-ge point-type-navigation header rounded-top-corners">
		<div class="yui-u first">
			<ul class="tabs">
				<li id="tab-search">
					<a href="#" class="alist"><spring:message code="commons.pages.search" /></a>
				</li>
				<li id="tab-saved">
					<a href="savedSearch" class="active alist"><spring:message code="search.tabs.saved" /></a>
				</li>
			</ul>
		</div>
	</div>

	<div id="tabs-content">
		<div class="yui-main">
			<div id="saved-search" class="yui-main content-inner">
				<div class="blankslate blankslate-saved hide">
					<h5><spring:message code="search.page.blankstate.title" /></h5>
					<p><spring:message code="search.page.blankstate.description" /></p>
				</div>
				<table id="saved-table" class="list">
					<thead>
						<tr class="">
							<th class="hide"><span><spring:message code="commons.pages.id" /> </span></th>
							<th><span id="name"><spring:message code="search.page.table.name" /> </span></th>
							<th><span><spring:message code="commons.pages.description" /></span></th>
							<th class=""><span><spring:message code="search.page.table.filters" /></span></th>
							<th><span id="modified_date"><spring:message code="commons.pages.date_added" /> </span></th>
							<th class="hide"><span>&nbsp;</span></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/saved_search/saved_search_main.js.jsp" flush="true"/>
	<jsp:include page="../../scripts/pages/saved_search/saved_search_init.js.jsp" flush="true"/>

</sec:authorize>