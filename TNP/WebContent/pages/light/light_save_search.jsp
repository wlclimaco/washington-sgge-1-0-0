<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Save Search Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="save-search-form" name="save_search_form" method="post">

<%-- Messaging --%>
<h2>
	<s:text name="action.savesearch.message"></s:text>
</h2>
<div id="action-messaging" class="messaging"><span class="message"></span></div>

		<ul class="two-line box-save-search">
			<li>
				<label for="savedSearchName" class="title"><s:text name="action.savesearch.form.name"></s:text></label><br />
				<input id="saved-search-name" type="text" maxlength="40"/>
			</li>
			<li>
				<label for="savedSearchDescription" class="title"><s:text name="action.savesearch.form.description"></s:text></label><br />
				<textarea id="saved-search-description" ></textarea>
			</li>
		</ul>

<%-- Additional Description --%>
<div class="highlight"><small><s:text name="action.savesearch.highlight"></s:text></small></div>

</form>
</div>

