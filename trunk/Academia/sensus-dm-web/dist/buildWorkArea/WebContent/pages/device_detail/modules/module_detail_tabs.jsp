<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<ul class="tabs">

	<!-- About this Device Tab -->
	<c:if test="${tabs.aboutThisDevice}">
		<li class="about-this-device">
			<a id="about-deivce" class="active" href="device/about" class="url">
				<spring:message code="smartpointdetail.page.abouttab" />
			</a>
		</li>
	</c:if>

	<!-- About Load Profile Tab -->
	<c:if test="${tabs.loadProfile}">
		<li>
			<a id="loadProfile" href="device/intervalReads" class="url">
				<spring:message code="smartpointdetail.page.loadprofiletab" />
			</a>
		</li>
	</c:if>

	<!-- About Demand Reads -->
	<c:if test="${tabs.demandReads}">
		<li>
			<a id="demandReads" href="device/demandReads" class="url">
				<spring:message code="smartpointdetail.page.demandReads" />
			</a>
		</li>
	</c:if>

	<!-- About Read Data Tab -->
	<c:if test="${tabs.readData}">
		<li>
			<a id="readData" href="device/readData" class="url">
				<spring:message code="smartpointdetail.page.readDataTab" />
			</a>
		</li>
	</c:if>

	<!-- Communications Tab -->
	<c:if test="${tabs.communications}">
		<li>
			<a href="device/communications" class="url">
				<spring:message code="smartpointdetail.page.communications" />
			</a>
		</li>
	</c:if>

	<!-- About Snap Shot Tab -->
	<c:if test="${tabs.snapshot}">
		<li>
			<a id="touReads" href="device/snapshot" class="url">
				<spring:message code="smartpoint.page.snapshot" />
			</a>
		</li>
	</c:if>

	<!-- About TOU Tab -->
	<c:if test="${tabs.tou}">
		<li>
			<a id="touRead" href="device/tou" class="url">
				<spring:message code="smartpoint.page.tou" />
			</a>
		</li>
	</c:if>

	<!-- About History Tab -->
	<c:if test="${tabs.history}">
		<li>
			<a href="device/history" class="url">
				<spring:message code="smartpointdetail.page.historytab" />
			</a>
		</li>
	</c:if>

</ul>

</sec:authorize>