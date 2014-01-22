<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">

<%-- START Main Content Container --%>
<div class="content-inner">
	<div id="yui-main"><%-- Header --%>
		<div class="content-header">
			<h1>
				<s:message code="user.page.header" /></h1>
			<p class="description"><s:message code="user.page.intro" /></p>
		</div>

		<%-- START User List Section --%>
		<div id="list"><%-- START Tools Section --%>
			<div class="yui-ge tools-pages tools"><%-- Add User Button --%>
				<div id="actions" class="yui-u add-action first">

					<!-- Create User  -->
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<a tabindex="0" id="ajax-button" href="user/update" class="fg-button ui-widget ui-state-default ui-corner-all user-create alist">
							<s:message code="user.actions.createuser" />
						</a>


						<a id="actions-button" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" href="#actions-options" tabindex="0">
							<span class="ui-icon ui-icon-triangle-1-s"></span>
							<s:message code="smartpoint.actions.actions" />
						</a>
						<div class="hidden" id="actions-options">
							<ul>
								<li>
									<a onclick="javascript:return false;" title="<s:message code='user.actions.deleteUser' />" href="#" id="deleteUser"><s:message code="user.actions.deleteUser" /></a>
								</li>
							</ul>
						</div>
					</sec:authorize>
					<%-- Selected Users Information --%>
					<span class='message rounded'>&nbsp;<span id="checked-count" class="checked-count">0</span>&nbsp;<span></span>&nbsp;<s:message code="user.table.checkedcount" /> </span>

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
					<p class="info rounded"><s:message code="table.filter.select"/> <a id="users-select-all" class="select-all" href=""><s:message code="table.filter.all"/></a>, <a id="users-select-page" class="select-page" href=""><s:message code="table.filter.page"/></a> <s:message code="table.filter.or"/> <a id="users-select-none" class="select-none" href=""><s:message code="table.filter.none"/></a></p>
				</div>
			</div>
			<%-- END Filter Bar --%>

			<%-- Blank Slate --%>
			<div id="blankslate" class="blankslate" style="display: none;">
							<h5><s:message code="widgets.blankslate.user.noresults" /></h5>
							<p><s:message code="widgets.blankslate.detail" /></p>
			</div>

			 <%-- The User Table --%>
			<table id="user-table" class="list table-selection">
				<thead>
					<tr>
						<th class="hide"><span id="col-id"></span></th>
						<th><span id="user_name"><s:message code="user.table.header.user" /></span></th>
						<th><span id="full_name"><s:message code="user.table.header.username" /></span></th>
						<th><span id="role"><s:message code="user.table.header.role" /></span></th>
						<th><span id="email"><s:message code="user.table.header.email" /></span></th>
						<th><span id="total_lights"><s:message code="user.table.header.smartpoints" /></span></th>
						<th><span id="date_modify"><s:message code="user.table.header.datemodified" /></span></th>
						<th class="hide">&nbsp;</th>
						<th class="hide">&nbsp;</th>
						<th class="hide">&nbsp;</th>
						<th class="hide">&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%-- END User List Section --%>
<%-- END Main Content Container --%>
<%-- END Main Page Content --%>

<%-- Placeholders for Action and Map Dialog Windows --%>
<div id="dialog-map"></div>

<%@ include file="../../scripts/pages/user/user_main.js.jsp" %>
<%@ include file="../../scripts/pages/user/user_actions.js.jsp" %>
<%@ include file="../../scripts/pages/user/user_init.js.jsp" %>

</sec:authorize>