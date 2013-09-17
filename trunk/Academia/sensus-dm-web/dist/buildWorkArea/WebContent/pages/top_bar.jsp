<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<div id="hd-user" class="yui-g"><%-- Breadcrumbs (currently hard-coded) --%>
	<div class="logo yui-u first">
		<h1></h1>
		<h2 class="hide-settings"></h2>
	</div>
	<%-- Login Menu --%>
	<div class="yui-g user-settings">
		<ul class="link-list">
			<jsp:include page="../pages/user_info.jsp" flush="true"/>
				<li class="hide-settings">
					<strong>
						<spring:message code="header.label.welcome"/>&nbsp;
						<a id="user-name" class="alist" href="settings/profile"/></a>
					</strong>
				</li>
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
					<li class="hide-settings" id="systemsettings">
						<a class="alist" href="systemsettings" title="<spring:message code="header.label.settings"/>"><spring:message code="header.label.settings" /></a>
					</li>
				</sec:authorize>
			<li>
				<a href="service"><spring:message code="header.label.home" /></a>
			</li>
			<li class="last">
				<a id="header-logout" href="<c:url value='/j_spring_security_logout'/>"><spring:message code="header.label.logout" /></a>
			</li>
		</ul>
	</div>
</div>

</sec:authorize>