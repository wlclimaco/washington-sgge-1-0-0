<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="small-dialog-container" id="create-note">
	<form id="create-note-form" name="createNoteForm" method="post" action="#" >
    	<input type="hidden" name="contactId" id="contact-id" tabindex="0" value="" />

		<div class="row-form">
			<div class="col-note">
				<label for="contact-pep"><s:message code="pages.document.form.label.note" text="PEP" /></label>
				<textarea id="note" class="note required" minlength="5" maxlength="255" placeholder="*"></textarea>
			</div>
		</div>
	</form>
</div>
<jsp:include page="../../scripts/pages/note/note_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_init.js.jsp" flush="true" />