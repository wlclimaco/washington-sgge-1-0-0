<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<ul class="ui-state-highlight summary">

	<li class="hide"><span id="id-process"> </span></li>
	<li id="request">
		<em>

		</em>
	</li>
	<li class="success">
		<span class="icon-small"></span>
		<span id="count-success"></span>
		(<span id="percent-success"></span>%)
		<s:message code="process.page.succeeded" />
		<small>
			<a class="launch export export-success" href="#"> <s:message code="process.page.export"/> </a>
		</small>
	</li>
	<li class="fail">
		<span class="icon-small"></span>
		<span id="count-failure"></span>
		(<span id="percent-failure"></span>%)
		<s:message code="process.page.failed" />
		<small><a class="launch export export-fail" href="#"><s:message code="process.page.export"/></a></small>
		<ul id="summary-container" class="summary-container"></ul>
	</li>

	<li>

		<div class="selected-points">
			<div class="export-select tools">
				<ul class="link-list">
					<li class="export-type last">
						<small>
							<span id="displaying"> Displaying 50 of 504 </span> -
							<strong>Export</strong>: <a class="launch export export-fail" href="#">CSV</a>
						</small>
					</li>
				</ul>
			</div>
			<table id="summary-meters-table">
				<tbody>

				</tbody>
			</table>
		</div>

	</li>

</ul>

</sec:authorize>