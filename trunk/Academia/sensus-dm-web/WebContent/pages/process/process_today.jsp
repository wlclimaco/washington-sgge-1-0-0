<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="bd" class="content-container">
		<div class="content-inner expandable">
			<!-- Messaging -->
			<div id="messaging-main" class="messaging">
 				<span class="message"></span><a href="" class="remove"><spring:message code="message.action.close" /></a>
			</div>
			<div class="yui-t2">
				<div id="w-filters"></div>
				<div id="yui-main">
					<div class="yui-b" style="margin-left: 163px;">
						<div class="yui-ge tools shim">
							<div id="actions" class="actions yui-u first">
								<div class="yui-pad4">
									<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
										<a tabindex="0" id="buttonCreateGroupPage" href="group/create"
											class="fg-button ui-widget ui-state-default ui-corner-all alist">
											<spring:message code="commons.pages.create" />
										</a>
										<a tabindex="2" href="#actions-options" id="actions-button"
											class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all">
											<span class="ui-icon ui-icon-triangle-1-s"></span>
											<spring:message code="commons.pages.actions" />
										</a>
										<div id="actions-options" class="hidden">
											<ul>
												<li>
													<a href="" class="action-option" id="deleteGroups" name="deleteGroups">
														<spring:message code="commons.pages.deleteGroups" />
													</a>
												</li>
											</ul>
										</div>
									</sec:authorize>
									<span class="message rounded">
										<strong>
											<span id="checked-count">0</span>
										</strong>
										<spring:message code="group.page.groupSelected" />
									</span>
								</div>
							</div>
							<div class="yui-u">
								<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR',
								'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
									<div class="export-select-right">
										<ul class="link-list">
											<li class="last export-type">
												<small>
													<strong><spring:message code="commons.pages.Export" /></strong>:
													<a href="" id="csv" class="csv">
														<spring:message code="commons.pages.csv" />
													</a>
												</small>
											</li>
										</ul>
									</div>
								</sec:authorize>
							</div>
						</div>

						<!-- START filter bar -->
						<div class="filter-results-containter">
							<div class="yui-ge">
								<div class="yui-u first hide">
									<ul class="filter-container">
										<li>
											<a href="" class="clear">
												<spring:message code="commons.pages.resetfilters" />
											</a>
										</li>
									</ul>
								</div>
								<div class="results yui-u"></div>
							</div>
						</div>
						<!-- END -->

						<!-- START status viewport -->
						<div class="status-viewport">
							<div id="list">

								<!-- Blankslate -->
								<div class="blankslate hide side-slide">
									<h5><spring:message code="commons.pages.blankGroups" /></h5>
									<p><spring:message code="commons.pages.blankGroups2" /></p>
								</div>

							<div style="position:relative">
							  <div style="width:100%;">
								<div class="grid-header" style="width:100%">
								  <label>SlickGrid</label>
								  <span style="float:right" class="ui-icon ui-icon-search" title="Toggle search panel"></span>
								</div>
								<div id="myGrid" style="width:100%;height:500px;"></div>
								<div id="pager" style="width:100%;height:20px;"></div>
							  </div>
							</div>

							<div id="inlineFilterPanel" style="display:none;background:#dddddd;padding:3px;color:black;">
							  Show tasks with title including <input type="text" id="txtSearch2">
							  and % at least &nbsp;
							  <div style="width:100px;display:inline-block;" id="pcSlider2"></div>
							</div>
							</div>
						</div>
						<!-- END update container -->
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../../scripts/pages/group/group_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/group/group_actions.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/process/process_today_init.js.jsp" flush="true" />

</sec:authorize>