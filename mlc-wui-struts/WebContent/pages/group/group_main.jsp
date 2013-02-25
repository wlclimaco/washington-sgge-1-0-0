<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
<%-- START Main Page Content --%>
<jsp:include page="../../scripts/pages/group_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/group_actions.js.jsp" flush="true"/>	
<%-- START Main Content Container --%>
<div class="content-inner">
<div id="yui-main" class="invisible"><%-- Header --%>
<div class="content-header">
<h1><s:text name="group.page.header" /></h1>
<p class="description"><s:text name="group.page.intro" /></p>
</div>

<%-- START Group List Section --%>
<div id="list"><%-- START Tools Section --%>
<div class="yui-ge tools-pages tools"><%-- Add Group Button --%>
<div id="actions" class="yui-u add-action first">
	
	<!-- Create Group  -->
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		<a tabindex="0" id="ajax-button" href="group/ajax.createGroupPage.action" class="fg-button ui-widget ui-state-default ui-corner-all group-create alist">
			<s:text name="group.actions.creategroup" />
		</a>
	</sec:authorize>	
	
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		<a tabindex="0" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
			<span class="ui-icon ui-icon-triangle-1-s"></span>
			<s:text name="smartpoint.actions.actions" />
		</a>
	</sec:authorize>	
	
	 <%-- Action Droplist --%>
	
	<div id="actions-options" class="hidden">
<ul>
	<s:iterator value="actionList">	
		<s:if test="%{value.size == 1}" >	
			<s:if test="%{key == getText('group.actions.deleteGroup')}">	
				<sec:authorize ifAllGranted="ROLE_Role.Admin">
					<li>
						<s:iterator value="%{value}">
							<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
							onclick="javascript:return false;">
							<s:property value="%{value}" />
							</s:a>
						</s:iterator>
					</li>
				</sec:authorize>	
			</s:if>
			<s:elseif test="%{key == getText('group.actions.lightsOnOff')}">
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<li>
						<s:iterator value="%{value}">
							<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
							onclick="javascript:return false;">
							<s:property value="%{value}" />
							</s:a>
						</s:iterator>
					</li>
				</sec:authorize>	
			</s:elseif>	
		</s:if>
		<s:else>
			<li><a href="#" class="action-option" id="header-tag"> ${key} </a>
			<ul>	
				<s:iterator value="%{value}">
					<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
					onclick="javascript:return false;">
					<s:property value="%{value}" />
					</s:a></li>
				</s:iterator>
			</ul>	
			</li>
		</s:else>		
	</s:iterator>	
</ul>
</div>

	<%-- Selected Groups Information --%> 
	<span class='message rounded'>&nbsp;<span id="checked-count" class="checked-count">0</span>&nbsp;<span></span>&nbsp;<s:text name="group.table.checkedcount" /> </span>
	
</div>

<%-- Export Button --%>
<div class="yui-u">
<div class="icon-container icon-small"></div>
</div>
</div>
<%-- END Tools Section --%>

<%-- START Filter Bar --%>
<div class="filter-results-container yui-gf">
	<div class="result-count yui-u first">
		<p class="info rounded"><s:text name="table.filter.select"/> <a id="groups-select-all" class="select-all" href=""><s:text name="table.filter.all"/></a>, <a id="groups-select-page" class="select-page" href=""><s:text name="table.filter.page"/></a> <s:text name="table.filter.or"/> <a id="groups-select-none" class="select-none" href=""><s:text name="table.filter.none"/></a></p>
	</div>
</div>
<%-- END Filter Bar --%>

<%-- Blank Slate --%>
<div id="blankslate" class="blankslate" style="display: none;">
				<h5><s:text name="widgets.blankslate.group.noresults" /></h5>
				<p><s:text name="widgets.blankslate.group.detail" /></p>
</div>
 <%-- The Group Table --%>
<table id="group-table" class="list table-selection">
	<thead>
		<tr>
			<th class="hide"><span><s:text name="group.table.header.id" /></span></th>
			<th><span id="name"><s:text name="group.table.header.name" /></span></th>
			<th><span><s:text name="group.table.header.description" /></span></th>
			<th><span id="smartpoint_count"><s:text name="group.table.header.smartpoints" /></span></th>			
			<th><span id="create_date"><s:text name="group.table.header.added" /></span></th>
			<th class="hide">&nbsp;</th>
			<th class="hide">&nbsp;</th>				
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
</div>
<%-- END Group List Section --%></div>
<%-- END Main Content Container --%>
<%-- END Main Page Content --%>

<%-- Placeholders for Action and Map Dialog Windows --%>
<div id="dialog-map"></div>

</sec:authorize>	 
