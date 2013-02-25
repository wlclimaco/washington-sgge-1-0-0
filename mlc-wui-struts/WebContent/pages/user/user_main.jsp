<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">

<%-- START Main Content Container --%>
<div class="content-inner">
	<div id="yui-main" class="invisible"><%-- Header --%>
		<div class="content-header">
			<h1><s:text name="user.page.header" /></h1>
			<p class="description"><s:text name="user.page.intro" /></p>
		</div>

		<%-- START User List Section --%>
		<div id="list"><%-- START Tools Section --%>
			<div class="yui-ge tools-pages tools"><%-- Add User Button --%>
				<div id="actions" class="yui-u add-action first">
					
					<!-- Create User  -->
					<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
						<a tabindex="0" id="ajax-button" href="user/ajax.createUserPage.action" class="fg-button ui-widget ui-state-default ui-corner-all user-create alist">
							<s:text name="user.actions.createuser" />
						</a>
					</sec:authorize>	
					
					<a tabindex="0" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<s:text name="smartpoint.actions.actions" />
					</a> <%-- Action Droplist --%>
					
					<div id="actions-options" class="hidden">
						<ul>
							<s:iterator value="actionList">	
								<s:if test="%{value.size == 1}" >	
									<s:if test="%{key == getText('user.actions.deleteUser')}">	
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
				
					<%-- Selected Users Information --%> 
					<span class='message rounded'>&nbsp;<span id="checked-count" class="checked-count">0</span>&nbsp;<span></span>&nbsp;<s:text name="user.table.checkedcount" /> </span>
					
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
					<p class="info rounded"><s:text name="table.filter.select"/> <a id="users-select-all" class="select-all" href=""><s:text name="table.filter.all"/></a>, <a id="users-select-page" class="select-page" href=""><s:text name="table.filter.page"/></a> <s:text name="table.filter.or"/> <a id="users-select-none" class="select-none" href=""><s:text name="table.filter.none"/></a></p>
				</div>
			</div>
			<%-- END Filter Bar --%>
			
			<%-- Blank Slate --%>
			<div id="blankslate" class="blankslate" style="display: none;">
							<h5><s:text name="widgets.blankslate.user.noresults" /></h5>
							<p><s:text name="widgets.blankslate.detail" /></p>
			</div>
			
			 <%-- The User Table --%>
			<table id="user-table" class="list table-selection">
				<thead>
					<tr>
						<th class="hide"><span id="col-id"></span></th>
						<th><span id="user_name"><s:text name="user.table.header.user" /></span></th>
						<th><span id="full_name"><s:text name="user.table.header.username" /></span></th>
						<th><span id="role"><s:text name="user.table.header.role" /></span></th>
						<th><span id="email"><s:text name="user.table.header.email" /></span></th>
						<th><span id="total_lights"><s:text name="user.table.header.smartpoints" /></span></th>			
						<th><span id="date_modify"><s:text name="user.table.header.datemodified" /></span></th>	
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

</sec:authorize>	 
