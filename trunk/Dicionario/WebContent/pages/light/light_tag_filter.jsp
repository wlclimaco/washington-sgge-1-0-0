<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="select-filter-tag">
	<ul id='check-tag'>	</ul>
	<s:select name="combo_tag" list="filterTagList" listKey="id" cssClass="combo-filter" listValue="value" multiple="false" size="1" required="false" />
</div>