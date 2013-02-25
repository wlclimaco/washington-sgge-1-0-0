<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">

<div id="content-controller"></div>

<%@ include file="../../scripts/pages/light_detail_main.js.jsp" %>
<%@ include file="../../scripts/pages/light_detail_actions.js.jsp" %>
<%@ include file="../../scripts/pages/light_detail_init.js.jsp" %>

</sec:authorize>