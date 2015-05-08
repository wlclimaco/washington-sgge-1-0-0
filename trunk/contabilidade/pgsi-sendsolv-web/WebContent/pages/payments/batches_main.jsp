<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="content list">
	<a class="btn ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only hide disabled" id="link-fund" href="#" role="button" data-status="" data-message="" data-title="">
		<span class="ui-button-text"></span>
	</a>

	<table id="data_list"></table>
</div>

<jsp:include page="../../scripts/pages/payments/batches_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/payments/batches_init.js.jsp" flush="true" />
