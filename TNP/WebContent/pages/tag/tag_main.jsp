<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<div id="tag-main">
	<fieldset><legend><s:message code="tag.page.title" /></legend>
	<ul>
		<li>
		<p class="desc"><s:message code="tag.page.description" /></p>
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			<div id="combo-tag" class="yui_ge create-action-container">
				<select name="tag-add-select" list="tagList" listKey="id" id="tag-add-select" cssClass="combobox" listValue="value" multiple="false" size="1" required="false" />
				<label for="combo-tag" generated="true" class="error hide tag-add-error"></label>
			<div class="yui-u"></div>
			</div>
		</sec:authorize>
		</li>
	</ul>
	</fieldset>
	<!-- blankslate -->
	<div id="blankslate2" class="blankslate">
	<h5><s:message code="tag.page.notag" /></h5>
	<p><s:message code="tag.page.addFirstTag" /></p>
	</div>
	<table id="tag-table" class="list">
		<thead>
			<tr>
				<th class="hide"><span id="col-tag"></span></th>
				<th><span id="name"><s:message code="tag.table.tag" /></span></th>
				<th class="rounded bubble2" rel="black" title="<s:message code='tag.table.autoGroup.description' />"><s:message code="tag.table.autoGroup" /></th>
				<th><span id="smartpoint_count"><s:message code="tag.table.smartpoint" /></span></th>
				<th><span id="create_date"><s:message code="tag.table.dateadded" /></span></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>


<%@ include file="../../scripts/pages/tag_main.js.jsp" %>
<%@ include file="../../scripts/pages/tag_actions.js.jsp" %>
<%@ include file="../../scripts/pages/tag_init.js.jsp" %>

</sec:authorize>