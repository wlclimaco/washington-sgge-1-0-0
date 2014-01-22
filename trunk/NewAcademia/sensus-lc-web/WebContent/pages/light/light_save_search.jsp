<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<%-- Save Search Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="save-search-form" name="save_search_form" method="post">

<%-- Messaging --%>
<h2>
	<s:message code="action.savesearch.message" />
</h2>
<div id="action-messaging" class="messaging"><span class="message"></span></div>

		<ul class="two-line box-save-search">
			<li>
				<label for="savedSearchName" class="title"><s:message code="action.savesearch.form.name" /></label><br />
				<input id="saved-search-name" type="text" maxlength="40"/>
			</li>
			<li>
				<label for="savedSearchDescription" class="title"><s:message code="action.savesearch.form.description" /></label><br />
				<textarea id="saved-search-description" ></textarea>
			</li>
		</ul>

<%-- Additional Description --%>
<div class="highlight"><small><s:message code="action.savesearch.highlight" /></small></div>

</form>
</div>

