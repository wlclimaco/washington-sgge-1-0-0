<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div id="tags" class="point-detail-container">
	<h3><div class="left"><s:message code="smartpointdetail.actiontags.tags"/></div></h3>
	<div class="blankslate hide">
		<h5><s:message code="smartpointdetail.actiongroups.addfirsttag"/></h5>
		<p><s:message code="smartpointdetail.actiongroups.notagsthissmartpoint"/></p>
	</div>
	<ul>
		<li class="chzn-row">
			<select  multiple class="chzn-select" id="select-tags" name="select_tags" style="width:100%;"></select>
		</li>
	</ul>
</div>
<script src="commons/scripts/controller/module_light_tags.js"></script>