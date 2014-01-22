<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<div id="no-baseline" class="ui-state-highlight ui-corner-all sui-pad1">
	<p>
		<span class="ui-icon ui-icon-info left"></span>
			<s:message code="smartpoint.detail.ecoMode.Edit" />
		</p>
</div>
<div id="light-eco-mode">
	<div id="eco-mode" class="eco-mode-container">
		<div class="content-header">
			<h1>Eco-Mode</h1>
			<p class="description"></p>
		</div>
			<div class="report-tools-container yui-gd">
				<div class="tools">
					<div class="export-select">
						<ul class="link-list">
							<li class="last export-type">
								<small>
									<strong><s:message code="analytics.page.export" /></strong><a href="" class="csv" id="csv"><s:message code="analytics.page.csv" /></a>
								</small>
							</li>
						</ul>
					</div>
					<div class="date_select yui-u">
						<div id="actions-options">
							<ul id="date-tag" class="link-list">
								<li class="active"><a id="datetage-1" href="#">1m</a></li>
								<li><a id="datetage-3" href="#">3m</a></li>
								<li><a id="datetage-ytd" href="#">YTD</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
			<!-- END: Report Tools -->
			<div class="report-graphic-container">
				<div id="container" style="width: 100%; height:275px; margin: 0 auto"></div>
			</div>
			<div id="list">
			<div id="blankslate" class="blankslate" style="display: none;">
				<h5><s:message code="widgets.blankslate.noresults" /></h5>
				<p><s:message code="widgets.blankslate.ecoMode.detail" /></p>
			</div>
				   <table id="ecomode-table" class="list">
				<!-- START : summary data -->
					<thead>
						<tr>
							<th><span id="consumption_day"><s:message code="smartpoint.detail.ecoMode.date" /></span></th>
							<th><span id="consumption"><s:message code="smartpoint.detail.ecoMode.measured" /></span></th>
							<th><span id="ecomode_baseline"><s:message code="smartpoint.detail.ecoMode.baseline" /></span></th>
							<th><span id="ecomode"><s:message code="smartpoint.detail.ecoMode.EcoMode" /></span></th>
						</tr>
					</thead>
					<!-- END : summary data -->
					   <tbody>
					   </tbody>
					</table>
			</div>
		</div>
	</div>


<%@ include file="../../scripts/pages/light/light_main.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_actions.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_detail_main.js.jsp" %>

<%@ include file="../../scripts/pages/light/light_detail_eco_mode_main.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_detail_actions.js.jsp" %>
<%@ include file="../../scripts/pages/light/light_detail_eco_mode_init.js.jsp" %>
