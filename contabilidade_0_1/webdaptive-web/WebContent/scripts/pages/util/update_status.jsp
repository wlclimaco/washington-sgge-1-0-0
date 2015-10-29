<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="status-template">
	<div class="newline label"></div>
	<div class="value" id="status-id"></div>
	<div class="label"><s:message code='pages.member.view.status' text='default text' /></div>
	<div class="value status" id="status" ></div>
	<a class="security high hide" id="status-risk" href="javascript:;">
		<span class="icon-security icon-shield84"></span>
		<span><s:message code='commons.pages.sdn' text='default text' /></span>
	</a>
	<a class="security medium hide" id="medium-risk" href="javascript:;"><span class="icon-security icon-flag"></span><s:message code='commons.pages.mediumrisk' text='default text' /></a>
	<a class="security high hide" id="high-risk" href="javascript:;"><span class="icon-security icon-flag"></span><s:message code='commons.pages.highrisk' text='default text' /></a>
	<a class="security high hide" id="status-pep" href="javascript:;"><span class="icon-security icon-torso"></span><s:message code='commons.pages.pep' text='default text' /></a>
	<span class="divider">|</span>
	<a href="javascript:;" class="active"><span class="icon-small-button active icon-nav icon-check-mark"></span><s:message code='pages.view.activate' text='default text' /></a>
	<a href="javascript:;" class="deactivate"><span class="icon-small-button deactivate icon-nav icon-minus-circle"></span><s:message code='pages.view.deactivate' text='default text' /></a>
	<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
		<a href="" class="delete"><span class="icon-small-button delete icon-nav icon-trash-bin"></span><s:message code='pages.view.delete' text='default text' /></a>
	</sec:authorize>
</div>