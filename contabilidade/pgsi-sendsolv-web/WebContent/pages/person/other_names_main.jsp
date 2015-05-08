<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div id="otherNamesTemplate">
	<div>
		<label class="newline first" for="otherName"><s:message code="pages.contacts.form.label.otherNames" text="default text" /></label>
		<input data-id="" data-personid="" data-modelaction="" id="otherName" name="otherName" class="width-long" type="text" minlength="1" maxlength="255">
		<a href="#" class="close-button-form hide"><span class="icon-small-button icon-nav icon-remove"></span></a>

	</div>

	<a href="#" id="add-another-name" title='<s:message code="pages.member.form.label.addAnotherName" text="default text" />' class="form-link first">
		<span class="icon-nav icon-plus"></span>
		<s:message code="pages.member.form.label.addAnotherName" text="default text" />
	</a>
</div>
