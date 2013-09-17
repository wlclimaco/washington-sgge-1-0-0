<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="yui-b dashboard-contianer">
	<ul class="dashboardLinks">
		<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC')">
			<li id="electric"><a href="electric" class="rounded box-shadow-dashboard"><spring:message code="main.page.manage_electric"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_EPM_SERVICE_GAS')">
			<li id="gas"><a href="gas" class="rounded box-shadow-dashboard"><spring:message code="main.page.manage_gas"/></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_EPM_SERVICE_WATER')">
			<li id="water"><a href="water" class="rounded box-shadow-dashboard"><spring:message code="main.page.manage_water"/></a></li>
		</sec:authorize>
	</ul>
</div>