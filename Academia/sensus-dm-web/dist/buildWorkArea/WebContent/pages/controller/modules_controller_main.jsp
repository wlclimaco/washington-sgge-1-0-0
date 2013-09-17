<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- Header Tabs Device Detail -->
	<div id="tabs" class="point-type-navigation header rounded-top-corners">
		<a id="ajax-button" href="device" class="back url alist">
			<spring:message code="smartpointdetail.page.backlist" />
		</a>
		<ul class="tabs">

			<!-- About this Device Tab -->
			<c:if test="${tabs.aboutThisDevice}">
				<li class="about-this-device">
					<a id="about-deivce" class="active" href="device/tab/detail" class="url">
						<spring:message code="smartpointdetail.page.abouttab" />
					</a>
				</li>
			</c:if>

			<!-- About Load Profile Tab -->
			<c:if test="${tabs.loadProfile}">
				<li>
					<a id="loadProfile" href="device/tab/intervalReads" class="url">
						<spring:message code="smartpointdetail.page.loadprofiletab" />
					</a>
				</li>
			</c:if>

			<!-- About Demand Reads -->
			<c:if test="${tabs.demandReads}">
				<li>
					<a id="demandReads" href="device/tab/demandReads" class="url">
						<spring:message code="smartpointdetail.page.demandReads" />
					</a>
				</li>
			</c:if>

			<!-- About Read Data Tab -->
			<c:if test="${tabs.readData}">
				<li>
					<a id="readData" href="device/tab/intervalReads" class="url">
						<spring:message code="smartpointdetail.page.readDataTab" />
					</a>
				</li>
			</c:if>

			<!-- About Snap Shot Tab -->
			<c:if test="${tabs.snapshot}">
				<li>
					<a id="touReads" href="device/tab/snapshot" class="url">
						<spring:message code="smartpoint.page.snapshot" />
					</a>
				</li>
			</c:if>

			<!-- About TOU Tab -->
			<c:if test="${tabs.tou}">
				<li>
					<a id="touRead" href="device/tab/tou" class="url">
						<spring:message code="smartpoint.page.tou" />
					</a>
				</li>
			</c:if>

			<!-- About History Tab -->
			<c:if test="${tabs.history}">
				<li>
					<a href="device/tab/history" class="url">
						<spring:message code="smartpointdetail.page.historytab" />
					</a>
				</li>
			</c:if>

		</ul>
	</div>

	<div class="content-inner">
		<div id="yui-main">
			<div id="messaging-smartpoint-detail" class="messaging messaging-smartpoint-detail hide">
				<span class="message"></span>
				<a href="" class="remove">
					<spring:message code="commons.pages.close" />
				</a>
			</div>

			<div id="address" class="point-detail-container">

				<div class="stamp-smartpoint highlight hide">
					<spring:message code="commons.pages.dataReflects" />
					<strong><span id="receivedDate"></span></strong>
					<span class="messaging-warning rounded ui-state-highlight" style="display: none;">
						<spring:message code="smartpointdetail.page.timeZoneInfo" />
						<span id="meter-timeZone"></span>.
					</span>
				</div>

				<!-- Start Summary Data -->
				<div id="detail-header-container" class="ss-widget-table-summary-kpi">
					<table class="summary-kpi">
						<tr>
							<!-- Device Information -->
							<c:if test="${summaryDatas.deviceInformation}">
								<td class="first">
									<div id="detail-header-pane-1" class="detail-header">
										<h1>
											<strong id="device-id"></strong>
										</h1>
										<p class="description-address"></p>
										<p id="premise-id" class="description-id"></p>
									</div>
								</td>
							</c:if>

							<!-- Hour -->
							<c:if test="${summaryDatas.hour}">
								<td id="hour" title=""><spring:message code="commons.pages.hour" />
									<span id="smartpointdetail-page-hour"></span>
								</td>
							</c:if>

							<!-- Month -->
							<c:if test="${summaryDatas.month}">
								<td id="month" title=""><spring:message code="commons.pages.month" />
									<span id="smartpointdetail-page-month"></span>
								</td>
							</c:if>

							<!-- Peak Demand -->
							<c:if test="${summaryDatas.peakDemand}">
								<td id="peakDemand" title=""><spring:message code="commons.pages.peakDemand" />
									<span id="smartpointdetail-page-peakdemand"></span>
								</td>
							</c:if>

							<!-- Network Status -->
							<c:if test="${summaryDatas.networkStatus}">
								<td title=""><spring:message code="summary.text.headerTable.NetworkStatus" /> <span id="network-status-value"></span>
								</td>
							</c:if>

							<!-- Parent -->
							<c:if test="${summaryDatas.parent}">
								<td title=""><spring:message code="commons.pages.parent" /> <span id="parent-value">--</span>
								</td>
							</c:if>

							<!-- Connected Date -->
							<c:if test="${summaryDatas.connectedDate}">
								<td title=""><spring:message code="commons.pages.connectedDate" /> <span id="connected-date-value"></span>
								</td>
							</c:if>

							<!-- SmartPointType -->
							<c:if test="${summaryDatas.smartPointType}">
								<td id="smartPointType">
									<spring:message code="smartpointdetail.tabs.about.smartPoint" />
									<span></span>
									<small></small>
								</td>
							</c:if>

							<!-- Life Cycle -->
							<c:if test="${summaryDatas.lifecycle}">
								<td id="lifecycle">
									<span><spring:message code="smartpointdetail.tabs.about.lifecycle" /></span>
									<strong class="value"></strong>
									<small class="sub-head"></small>
								</td>
							</c:if>

							<!-- Transmit -->
							<c:if test="${summaryDatas.transmit}">
								<td id="transmit">
									<span><spring:message code="smartpointdetail.tabs.about.transmit" /></span>
									<strong class="value"></strong>
									<small class="sub-head"></small>
								</td>
							</c:if>

							<!-- Last Read -->
							<c:if test="${summaryDatas.lastRead}">
								<td id="lastRead">
									<span><spring:message code="smartpointdetail.tabs.about.lastRead" /></span>
									<strong class="value"></strong>
									<small class="sub-head"></small>
								</td>
							</c:if>

							<!-- Consumption High -->
							<c:if test="${summaryDatas.consumptionHigh}">
								<td id="consumptionHigh">
									<spring:message code="smartpointdetail.tabs.about.consumptionHigh" />
									<span></span>
									<small></small>
								</td>
							</c:if>

							<!-- Consumption Low -->
							<c:if test="${summaryDatas.consumptionLow}">
								<td id="consumptionLow">
									<spring:message code="smartpointdetail.tabs.about.consumptionLow" />
									<span></span>
									<small></small>
								</td>
							</c:if>

							<!-- SmartPoint -->
							<c:if test="${summaryDatas.smartPoint}">
								<td id="smartPoint">
									<span><spring:message code="smartpointdetail.tabs.about.smartPoint" /></span>
									<strong class="value"><span><a href="" class="alist"></a></span></strong>
								</td>
							</c:if>

							<!--  Actions -->
							<c:if test="${summaryDatas.actions}">
								<td class="last">
									<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
										<a tabindex="1" href="#actions-options"
											class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"
											id="actions-button">
											<span class="ui-icon ui-icon-triangle-1-s"></span>
											<spring:message code="commons.pages.actions" />
										</a>
										<!-- START SmartPoint Actions-->
										<div id="actions-options" class="hidden">
											<ul class="detail-actions-height"></ul>
										</div>
										<!--END SmartPoint Actions -->
									</sec:authorize>
								</td>
							</c:if>
						</tr>
					</table>
				</div>
				<!-- End Summary Data -->
			</div>
		</div>
		<!-- Start Content -->
		<div id="tabs-content"></div>
		<!-- End Content -->
	</div>

</sec:authorize>