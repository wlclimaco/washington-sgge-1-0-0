<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

	<div class="group_inputs" id="security-question-template">
		<div class="container">
			<div class="row-form">
				<input type="hidden" class="answer-id">
				<input type="hidden" class="model-action">
				<input type="hidden" class="version">
				<label class="first"><s:message code="pages.member.form.label.securityQuestion" text="default text" /></label>
				<select	name="securityQuestion" class="security-question required question">
					<option></option>
				</select>
					<!--  -->
				<label class="first"><s:message code="pages.member.form.label.securityAnswer" text="default text" /></label>
				<input type="text" name="securityAnswer" class="security-answer required question" placeholder="*">

				<div class="close-button-form hide">
					<span class="icon-small-button icon-nav icon-remove" title='<s:message code="commons.pages.delete" text="default text" />'></span>
				</div>
			</div>
		</div>

		<a href="javascript:;" id="add-question" title='<s:message code="pages.form.label.addsecurityquestion" text="default text" />'>
			<span class="icon-small-button icon-nav icon-plus"></span>
			<s:message code="pages.form.label.addsecurityquestion" text="default text" />
		</a>
	</div>

<jsp:include page="../../scripts/pages/security_question/security_question_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/security_question/security_question_init.js.jsp" flush="true" />