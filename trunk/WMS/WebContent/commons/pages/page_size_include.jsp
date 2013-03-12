<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Page Size Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="page-size-form" name="page_size_form" method="post">
		<p>

			<s:message code="action.pagesize.dialog.message" />&nbsp;<strong id="dialogValuePageSize"></strong>&nbsp;
			<s:message code="action.pagesize.dialog.message.two" />
		</p>
	    <br />
		<p class="highlight">
			<small>
				<input id="page-size-checkbox" type="checkbox" />
				<s:message code="action.pagesize.dialog.checkbox" />
			</small>
		</p>
	</form>
</div>