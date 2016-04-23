<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<form role="form" id="create-note-form">
				<div class="form-group">

					<label for="exampleInputEmail1">
						Note
					</label>
					 <textarea class="form-control required" id="noteText" rows="3" placeholder="*"></textarea>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="../../scripts/pages/note/note_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/note/note_init.js.jsp" flush="true" />