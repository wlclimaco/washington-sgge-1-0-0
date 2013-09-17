<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<!-- Save Search  -->
	<div id="action-dialog-save" class="action-dialog">
		<form id="save-form" name="saveForm" method="post" action="#" >
		<!-- Messaging -->
		<h2><spring:message code="search.dialog.save.headder" /></h2>
			<fieldset class="two-line">
					<ul>
						<li>
							<label for="saved-search-name" class="title"><spring:message code="commons.pages.name" /></label><br />
							<input name="savedSearchName" class="validate[required,maxSize[100] custom[noSpecialCaracters]]"id="saved-search-name" type="text" value=""/>
						</li>
						<li>
							<label for="saved-search-description" class="title"><spring:message code="commons.pages.description" /></label><br />
							<textarea name="savedSearchDescription" class="validate[maxSize[150]]" id="saved-search-description"></textarea>
						</li>
					</ul>
			 </fieldset>
		<div class="highlight"><small><spring:message code="search.dialog.save.message" /></small></div>
		  </form>
	</div>
	<!-- DIALOG END - Save Search -->

</sec:authorize>