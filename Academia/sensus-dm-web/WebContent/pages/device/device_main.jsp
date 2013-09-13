<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="bd" class="content-inner expandable">
		<div class="yui-t2">
			<div id="w-filters"></div>
			<div id="yui-main">
				<div class="yui-b" style="margin-left: 163px;">
					<div class="yui-ge tools">
						<div id="actions" class="actions yui-u first">
							<div class="yui-pad3">
								<!-- The Admin and Operator users see its actions -->
								<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
									<a tabindex="0" href="#actions-options"
										class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"
										id="actions-button">
										<span class="ui-icon ui-icon-triangle-1-s"></span>
										<spring:message code="commons.pages.actions" />
									</a>
									<div id="actions-options" class="hidden">
										<ul>
										</ul>
									</div>
								</sec:authorize>
								<!-- The Customer and Billing users see its actions, just if service is not Electric -->
								<sec:authorize access="hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
									<c:if test="${serviceType != 'ELECTRIC'}">
										<a tabindex="0" href="#actions-options"
											class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"
											id="actions-button">
											<span class="ui-icon ui-icon-triangle-1-s"></span>
											<spring:message code="commons.pages.actions" />
										</a>
										<div id="actions-options" class="hidden">
											<ul>
											</ul>
										</div>
									</c:if>
								</sec:authorize>
								<span class="message rounded">
									<strong>
										<span id="checked-count"></span>
									</strong>
									<spring:message code="smartPoint.page.smartpointSelected" />
								</span>
							</div>
						</div>
						<div class="yui-u" id="buttonsRight">
							<div class="export-select">
								<ul class="link-list">
									<li class="last export-type">
										<small><strong><spring:message code="commons.pages.Export" />
										</strong>:<a href="" class="csv" id="csv">
										<spring:message code="commons.pages.csv" /></a></small>
									</li>
								</ul>
							</div>
							<div class="icon-contianer">
								<span id="toolbar">
									<span id="views">

										<label class="list" for="repeat0">
											<spring:message code="smartPoint.page.list" />
										</label>
										<input type="radio" id="repeat0" name="repeat" class="list" checked="checked" />

										<label for="repeat1" class="map">
											<spring:message code="smartPoint.page.map" />
										</label>
										<input type="radio" id="repeat1" name="repeat" class="map" />

									</span>
									<span><a id="buttonImportHan" class="fg-button fg-button-icon-left ui-widget ui-state-default ui-corner-all action-dialog-add-test-points request-readonly-action-han-import hide" href="" tabindex="2"><span class="ui-icon ui-icon-circle-plus"></span><spring:message code="commons.pages.import" /></a></span>
								</span>
							</div>
						</div>
					</div>

					<!-- START filter bar -->
					<div class="filter-results-containter yui-ge">
						<div class="yui-u first">
							<div class="result-count">
								<p class="info">
									<spring:message code="smartPoint.page.select" /> <a href="" class="select-all">
									<spring:message code="commons.pages.all" /></a>, <a href="" class="select-page">
									<spring:message code="smartPoint.page.page" /></a> <spring:message code="commons.pages.or" /> <a href="" class="select-none">
									<spring:message code="smartPoint.page.none" /></a>
								</p>
							</div>
						</div>
						<div class="results yui-u"></div>
					</div>
					<div class="active-filters-list yui-ge">
						<div class="yui-u first hide">
							<ul class="filter-container">
								<li>
									<a href="" class="clear"><spring:message code="commons.pages.resetfilters" /></a>
								</li>
							</ul>
						</div>
						<div class="yui-u">
							<span class="save right"><spring:message code="smartPoint.page.saveSearch" /></span>
						</div>
					</div>
					<!-- END -->

					<!-- Map Container -->
					<div id="map-list" class="map hide-map">
						<div class="drawing-tools yui-g">
							<div class="yui-u first">
								<ul id="panel" class="">
									<li><button id="draw-pan" class="ui-state-default ui-corner-all action-link"><span class="icon-small icn-hand-a"></span></button></li>
									<li><button value="polygon" id="draw-polygon" class="ui-state-default ui-corner-all action-link"><span class="icon-small icn-poly-a"></span></button></li>
								</ul>
								<div class="view-port-description"><span id="map-view-records"></span> <spring:message code="table.filter.of"/> <span id="map-total-records"></span> <spring:message code="smartpoint.table.header.mapVisible"/></div>
							</div>
							<div class="filter-results-container yui-g">
								<ul id="polygon-filters" class="filter-container"></ul>
							</div>
						</div>
						<div id="map"></div>
					</div>

					<!-- START status viewpoint -->
					<div class="status-viewport">
						<div id="list">

							<!-- Blankslate -->
							<div class="blankslate hide side-slide">
								<h5><spring:message code="commons.pages.blankResult" /></h5>
								<p><spring:message code="commons.pages.removefilters" /></p>
							</div>
							<!-- Overview Map Container -->
							<div id="smartpoint-map" class="map">
								<div id="smartpoint-map-container"></div>
							</div>
							<table id="smartpoint-table" class="list table_selection side-slide">
								<thead>
									<tr>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="action-dialog-search-new"></div>

</sec:authorize>