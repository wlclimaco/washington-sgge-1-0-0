<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Page Navigation: SmartPoint Tabs --%>
<div class="yui-ge point-type-navigation header rounded-top-corners">
	<div class="yui-u first">
	   <ul class="tabs tab-saved hide" id="tabs-list"> 
		    <li><a class="active gradient-vert" href="#"><s:text name="search.tabs.search" /><span class="save"><s:text name="table.result.save"/></span></a></li>
		   <%-- <li><a id="ajax-button" href="search/ajax.advanced.action" class="gradient-vert alist"><s:text name="search.tabs.advanced" /></a></li> --%>
	        <li><a id="ajax-button" href="search/ajax.list.action" class="gradient-vert alist"><s:text name="search.tabs.saved" /></a></li>
	    </ul>
	</div>
</div>
<!-- Messaging -->
<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove"><s:text name="message.action.close" /></a></div>

<%-- START Main Page Content --%>
<div class="content-inner">

<%-- START Content Container --%>
<div class="yui-t2"><%-- START Filter and Action Container --%>
<div class="yui-b"><%-- Filter Title --%>
<div id="w-filters"></div>

</div>
<%-- END Filter and Action Container --%> <%-- START Main Section --%>
<div id="yui-main" class="">
<div class="yui-b"><%-- START Tools Bar --%>
	<div class="yui-gc tools"><%-- START Action Container --%>
		<div id="actions" class="actions yui-u first">
		<div class="yui-pad"><%-- Action Link --%> 
		<!-- Turn lights ON/OFF -->
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
			<a tabindex="0" href="#" class="fg-button ui-widget ui-state-default ui-corner-all" title="<s:text name="smartpoint.actions.switchDim"/>" id="lights-on-off">
				<s:text name="smartpoint.actions.switchDim" />
			</a>
		<a tabindex="0" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
			<span class="ui-icon ui-icon-triangle-1-s"></span><s:text name="smartpoint.actions.actions" />
		</a> <%-- Action Droplist --%>
		</sec:authorize> 
		<div id="actions-options" class="hidden">
		
		<ul>
			<s:iterator value="actionList">	
		 			<s:if test="%{id == getText('smartpoint.actions.configuration') || id == getText('smartpoint.actions.schedule')}">	 
		 				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">	 
		 					<li><a href="#" class="action-option" id="header-tag"> ${id} </a> 
		 					<ul>	 
		 						<s:iterator value="%{idValuePairs}"> 
		 							<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" 
		 							onclick="javascript:return false;"> 
		 							<s:property value="%{value}" /> 
		 							</s:a></li> 
		 						</s:iterator> 
		 					</ul>	 
		 					</li> 
		 				</sec:authorize>	 
		 			</s:if>	 
					<s:else>
						<s:if test="%{id == getText('smartpoint.actions.group') || id == getText('smartpoint.actions.tag')}">	
							<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">	
								<li><a href="#" class="action-option" id="header-tag"> ${id} </a>
								<ul>	
									<s:iterator value="%{idValuePairs}">
										<s:if test="%{value == getText('smartpoint.actions.addtogroup') || value == getText('smartpoint.actions.addtag')}">
											<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">	
												<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
												onclick="javascript:return false;">
												<s:property value="%{value}" />
												</s:a></li>
											</sec:authorize>	
										</s:if>
										<s:elseif test="%{value == getText('smartpoint.actions.removegroup')}">
											<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">	
												<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
												onclick="javascript:return false;">
												<s:property value="%{value}" />
												</s:a></li>
											</sec:authorize>	
										</s:elseif>		
										<s:elseif test="%{value == getText('smartpoint.actions.removetag')}">
											<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">	
												<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
												onclick="javascript:return false;">
												<s:property value="%{value}" />
												</s:a></li>
											</sec:authorize>	
										</s:elseif>			
										<s:else>
											<li><s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}"
											onclick="javascript:return false;">
											<s:property value="%{value}" />
											</s:a></li>
										</s:else>				
									</s:iterator>
								</ul>	
								</li>
							</sec:authorize>	
						</s:if>	
						<s:else>
							<li><a href="#" class="action-option" id="header-tag"> ${id} </a>
								<ul>	
									<s:iterator value="%{idValuePairs}">
										<s:if test="%{idValuePairs.size > 0}">
											<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
												<li><a href="#" class="action-option" id="header-tag"> ${id} </a>
													<ul>
														<s:iterator value="%{idValuePairs}">
															<li>	
																<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;">
																	<s:property value="%{value}" />
																</s:a>
															</li>	
														</s:iterator>	
													</ul>	
												</li>
											</sec:authorize>
										</s:if>
										<s:else>
											<s:if test="%{value == getText('smartpoint.actions.clearAlerts') || value == getText('smartpoint.actions.clearManual') }">
												<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
													<li>
														<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;">
															<s:property value="%{value}" />
														</s:a>
													</li>
												</sec:authorize>
											</s:if>	
											<s:elseif test="%{value == getText('smartpoint.actions.getDataLight')}">
												<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
													<li>
														<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;">
															<s:property value="%{value}" />
														</s:a>	
													</li>
												</sec:authorize>
											</s:elseif>			
											<s:elseif test="%{value == getText('smartpoint.actions.reset') || value == getText('smartpoint.actions.editLight')}">
												<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
													<li>
														<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;">
															<s:property value="%{value}" />
														</s:a>	
													</li>
												</sec:authorize>
											</s:elseif>		
										</s:else>	
									</s:iterator>
								</ul>
							</li>						
						</s:else>
					</s:else>			
			</s:iterator>	
		</ul>
		</div>
		
		<%-- Selected SmartPoints Information --%> 
		<span class='message rounded'><span	id="checked-count" class="checked-count">0</span>&nbsp; <s:text name="table.filter.of" /> <span id="general-total">0</span> <s:text name="smartpoint.table.checkedcount" /></span>

			<div class="result-count">
				<p class="info rounded">
					<s:text name="table.filter.select"/> 
					<a id="lights-select-all" class="select-all" href="#"><s:text name="table.filter.all"/></a>, 
					<a id="lights-select-page" class="select-page" href="#"><s:text name="table.filter.page"/></a> 
					<s:text name="table.filter.or"/> 
					<a id="lights-select-none" class="select-none" href="#"><s:text name="table.filter.none"/></a>
				</p>
			</div>

		</div>
		</div>
		
			<%-- END Action Container --%> <%-- Map/List Toggle --%>
			<div class="yui-u">
			<div class="icon-container">
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
					<div class="export-select">
						 <ul class="link-list">
							 <li class="last export-type"><small><strong><s:text name="smartpont.page.export" /></strong> <a href="" id="csv" class="csv"><s:text name="smartpont.page.csv" /></a></small></li>
						 </ul>
					 </div>	
				</sec:authorize> 
				<span id="toolbar"
					class="i-widget-header ui-corner-all"><span id="views"> <input
					type="radio" id="list_toggle" name="view_toggle" class="list"
					value="list" /><label for="list_toggle"><s:text
					name="smartpoint.filter.list" /></label> <input type="radio" id="map_toggle"
					name="view_toggle" class="map" value="map" /><label for="map_toggle"
					class="map"><s:text name="smartpoint.filter.map" /></label> </span> </span>
			</div>
		</div>
	</div>
<%-- END Tools Bar --%> <%-- START Filter Bar --%>
<div class="filter-results-container yui-ge">

	<div class="yui-u first">
		<div class="active-filters-list">
			<ul class="filter-container">
				<li id='tag-clear'>
					<a href="#" class="clear"><s:text name="smartpoint.filter.reset" /></a>
				</li>
			</ul>               
		</div>
	</div>
	<div class="results yui-u">
		<span id="total-records"></span>
		<span id="label-total-records"><s:text name="table.result.matches"/></span>
		<span id="lights-save" class="save"><s:text name="table.result.save"/></span>
	</div>
	
</div>



<%-- END Filter Bar --%>
<div id="box-list">
	<%-- Overview Map Container --%>
	<div id="map-list" class="map">
		<div class="drawing-tools">
			<ul id="panel" class="">
				<li><button id="draw-pan" class="ui-state-default ui-state-focus ui-corner-all"><span class="icon-small icn-hand-a"></span></button></li>
                <li><button value="polygon" id="draw-polygon" class="ui-state-default ui-corner-all action-link"><span class="icon-small icn-poly-a"></span></button></li>
			</ul>
			<div class="view-port-description"><span id="map-view-records"></span> <s:text name="table.filter.of"/> <span id="map-total-records"></span> <s:text name="smartpoint.table.header.mapVisible"/></div>
        </div>
		<div id="map"></div>
	</div>
	<%-- SmartPoint Table --%>
	<div id="table-list">
		<%-- Blank Slate --%>
		<div id="blankslate" class="blankslate" style="display: none;">
			<h5><s:text name="widgets.blankslate.noresults" /></h5>
			<p><s:text name="widgets.blankslate.detail" /></p>
		</div>
		<table id="smartpoint-table" class="list table-selection">
			<thead>
				<tr>

				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

	</div>
</div>	

<div id="dialog-map"></div>
</div>
</div>
<%-- END Main Section --%>
</div>
<%-- END Content Container --%>
</div>
<%-- END Main Page Content --%>
