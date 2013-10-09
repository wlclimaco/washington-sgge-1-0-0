<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title><spring:message code="header.label.home" /></title>

			<!-- Combo-handled YUI CSS files: -->
			<link rel="stylesheet" type="text/css" href="../styles/yahoo_reset_fonts_grids.css">
			<link rel="stylesheet" type="text/css" href="../styles/base.css">
			<!--[if IE]>
			<link rel="stylesheet" type="text/css" href="styles/ie.css">
			<![endif]-->

			<!-- Combo-handled YUI JS files: -->
			<script type="text/javascript"
				src="http://yui.yahooapis.com/combo?2.7.0/build/yahoo-dom-event/yahoo-dom-event.js&amp;2.7.0/build/animation/animation-min.js&amp;2.7.0/build/container/container_core-min.js&amp;2.7.0/build/menu/menu-min.js&amp;2.7.0/build/element/element-min.js&amp;2.7.0/build/button/button-min.js&amp;2.7.0/build/connection/connection-min.js">
			</script>
		</head>
		<body>
			<div id="doc2" class="yui-t application home">
				<div id="hd-user" class="yui-g">
					<div class="logo yui-g first">
						<h1><spring:message code="breadcrumbs.label.customer" /></h1>
					</div>

					<form id="search-form-container" class="rounded">
						<div class="search-input-container yui-u first">
							<div id="search-label-container">
								<a tabindex="1" href="#labelOptions" class="fg-button fg-button-icon-right ui-widget ui-state-default" id="search-label">
									<span class="ui-icon ui-icon-triangle-1-s"></span>
									<input id="search-type" value="DEVICE_ID" type="hidden">
									<span><spring:message code="commons.pages.deviceId"/></span>
								</a>
								<div id="labelOptions" class="hidden">
									<ul>
										<li class="hide">
											<a href="#" class="action-option" id="device-id">
												<input value="DEVICE_ID" type="hidden">
												<span><spring:message code="commons.pages.deviceId"/></span>
											</a>
										</li>
										<li>
											<a href="#" class="action-option" id="network-address">
												<input value="NETWORK_ADDRESS" type="hidden" />
												<span><spring:message code="commons.pages.networkAddress"/></span>
											</a>
										</li>
										<li>
											<a href="#" class="action-option" id="premise-id">
												<input value="PREMISE_ID" type="hidden" />
												<span><spring:message code="commons.pages.premiseId"/></span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						<input type="text" id="search-text" value="" />

						</div>
					</form>

					<div class="yui-g user-settings">
						<ul class="link-list">
							<li><spring:message code="header.label.welcome" />[NAME]</li>
							<li>
								<a id="logout-link"	href="<s:url value="/pages/flexnet_signin.jsp" includeParams="none"/>"
									title="<spring:message code="header.label.logout"/>"><spring:message code="header.label.logout" />
								</a>
							</li>
							<li class="last">
								<a href="<s:url value="/pages/flexnet_index.jsp" includeParams="none"/>"
									title="<spring:message code="header.label.home"/>"><spring:message code="header.label.home" />
								</a>
							</li>
						</ul>
					</div>
				</div>
				<div id="bd">
					<div class="yui-b dashboard-container">
						<ul class="dashboard-links">
							<li id="network-management"><a href="#" class="rounded box_shadow"
								onclick='return false;'
						><spring:message code="flexnet.portal.network" /></a></li>
							<li id="electric"><a href="#" class="rounded box_shadow"
								onclick='return false;'
						><spring:message code="flexnet.portal.electric" /></a></li>
							<li id="lighting"><a
								href="<s:url value="/device/list.action" includeParams="none"/>"
								class="rounded box_shadow"><spring:message code="flexnet.portal.lighting" /></a></li>
						</ul>
					</div>
				</div>
				<div id="ft" class="yui-g">
					<div class="yui-u first">
						<div class="vcard">
							<div class="fn org">
								&copy;
								<spring:message code="company.copyright-year" />
								<spring:message code="company.org" />
							</div>
							<div class="adr">
								<div class="street-address">
									<spring:message code="company.street-address" />
								</div>
								<div>
									<span class="locality"><spring:message code="company.locality" /></span>,
									<span class="region"><spring:message code="company.region" /></span>
									<span class="postal-code"><spring:message code="company.postal-code" /></span>
								</div>
								<div class="country-name">
									<spring:message code="company.country-name" />
								</div>
							</div>
						</div>
					</div>
					<div class="yui-u footer-links">
						<ul class="link-list">
							<li>
								<a href="" title="
									<spring:message code="footer.links.training" />">
									<s:text name="footer.links.training" />
								</a>
							</li>
							<li>
								<a href="" title="
									<spring:message code="footer.links.support" />">
									<s:text name="footer.links.support" />
								</a>
							</li>
							<li class="last" title="<spring:message code="footer.links.contact" />">
								<a href="">
									<spring:message code="footer.links.contact" />
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</body>
	</html>

</sec:authorize>