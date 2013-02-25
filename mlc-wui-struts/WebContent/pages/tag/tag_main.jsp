<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<div id="tag-main">
	<fieldset><legend><s:text name="tag.page.title" /></legend>
	<ul>
		<li>
		<p class="desc"><s:text name="tag.page.description" /></p>
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">	
			<div id="combo-tag" class="yui_ge create-action-container"><s:select
				name="tag-add-select" list="tagList" listKey="id" id="tag-add-select"
				cssClass="combobox" listValue="value" multiple="false" size="1"
				required="false" /><label for="combo-tag" generated="true" class="error hide tag-add-error"></label>
			<div class="yui-u"></div>
			</div>
		</sec:authorize>
		</li>
	</ul>	
	</fieldset>
	<!-- blankslate -->
	<div id="blankslate2" class="blankslate">
	<h5><s:text name="tag.page.notag" /></h5>
	<p><s:text name="tag.page.addFirstTag" /></p>
	</div>
	<table id="tag-table" class="list">
		<thead>
			<tr>
				<th class="hide"><span id="col-tag"><s:text name="tag.table.id" /></span></th>
				<th><span id="name"><s:text name="tag.table.tag" /></span></th>
				<th class="rounded bubble2" rel="black" title="<s:text name='tag.table.autoGroup.description' />"><s:text name="tag.table.autoGroup" /></th>
				<th><span id="smartpoint_count"><s:text name="tag.table.smartpoint" /></span></th>
				<th><span id="create_date"><s:text name="tag.table.dateadded" /></span></th>				
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

