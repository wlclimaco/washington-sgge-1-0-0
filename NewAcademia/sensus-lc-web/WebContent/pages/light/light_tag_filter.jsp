<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<div id="select-filter-tag">
	<ul id='check-tag'>	</ul>
	<!--<s:select name="combo_tag" list="filterTagList" listKey="id" cssClass="combo-filter" listValue="value" multiple="false" size="1" required="false" />-->
	<select name="combo_tag"></select>
</div>