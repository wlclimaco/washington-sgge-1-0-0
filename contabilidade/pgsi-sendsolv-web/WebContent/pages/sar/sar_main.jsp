<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<nav class="secondary">
		<span><a href="dashboard" title="Compliance"><s:message code="commons.pages.compliance" text="default text" /></a></span><span
			class="icon-nav icon-angle-right"></span><span><s:message code="pages.sdn.dashboard.SAR.Reports" text="default text" /></span>
	</nav>
	<div>
		<h2 class="list"><s:message code="pages.sdn.dashboard.SAR.Reports" text="default text" /></h2>
	</div>

	<div class="content list">
		<div class="data">
			<table id="data_list">
			</table>
		</div>
	</div>
<jsp:include page="../../scripts/pages/sar/sar_init.js.jsp" flush="true" />