<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- START - Notes -->
<div id="notes" class="point-detail-container">

	<sec:authorize access="hasAnyRole('ROLE_Role.EPM_ADMIN', 'ROLE_Role.EPM_SYSTEM_OPERATOR', 'ROLE_Role.EPM_CUSTOMER_SUPPORT', 'ROLE_Role.EPM_BILLING_MANAGER')">

		<h3><strong><s:text name="smartpointdetail.tabs.about.postNote"/></strong></h3>   

		<textarea id="new-note"></textarea><a href="" id="note-submit" class="button"><s:text name="smartpointdetail.tabs.about.postNote"/></a>	

	</sec:authorize>

	<div class="blankslate" id="blankslate-note">

		<h5><s:text name="smartpointdetail.tabs.about.addYourFirstNote"/></h5>

		<p><s:text name="smartpointdetail.tabs.about.noNotesWithThisSmartPoint"/></p>

	</div> 

	<div class="annotation point-detail-container">
		<dl>


     			</dl>
 			</div>
</div>
<!-- END - Notes -->