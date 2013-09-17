<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<%-- START Footer --%>
	<div id="ft" class="yui-g clearfix">
		<div class="yui-u first"><%-- Footer address section --%>
			<div class="vcard">
				<div class="fn org">&copy;<span>${currentYear}</span>
					<spring:message code="company.org" />
				</div>
				<div class="adr">
					<div class="street-address">

					</div>
					<div>
						<span class="locality">

						</span>
						<span class="region"></span>
						<span class="postal-code"></span>
					</div>
					<div class="country-name"></div>
					<p class="version"><span id="version-number"></span> <spring:message code="commons.pages.build" /> <span id="build-number"></span></p>
				</div>
			</div>
		</div>
		<%-- END Footer --%>
	</div>

</sec:authorize>