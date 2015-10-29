	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
	<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


<div id="notes-template">
	<div class="group-inputs hide" id="add-notes-template">
		<h4 class="ui-subtitle">NOTES</h4>
		<textarea></textarea>
	</div>

<div class="group-inputs hide" id="edit-notes-template">
		<h4 class="ui-subtitle">NOTES</h4>
	    <textarea></textarea>
</div>

</div>

<jsp:include page="../../scripts/pages/note/note_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_init.js.jsp" flush="true" />
