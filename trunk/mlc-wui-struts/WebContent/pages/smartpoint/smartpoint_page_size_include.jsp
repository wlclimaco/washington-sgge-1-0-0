<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Page Size Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="page-size-form" name="page_size_form" method="post">
		<p>
			<s:text name="action.pagesize.dialog.message"></s:text>&nbsp;<strong id="dialogValuePageSize"></strong>&nbsp;
			<s:text name="action.pagesize.dialog.message.two"></s:text>
		</p>
	    <br />
		<p class="highlight">
			<small>
				<input id="page-size-checkbox" type="checkbox" />
				<s:text name="action.pagesize.dialog.checkbox"></s:text>
			</small>
		</p>
	</form>           
</div>