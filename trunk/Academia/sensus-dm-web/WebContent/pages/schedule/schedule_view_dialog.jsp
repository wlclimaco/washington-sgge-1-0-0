<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<form action="" method="post">

		<fieldset id="event-fields-container-event">
			<legend>
				<span class="left"><strong class="label"><spring:message code="systemintelligence.page.today.event" /></strong><span id="event-name"></span></span>
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
					<a href="#" class="button right small edit-schedule"> <span class="ui-button-text" id="button-text" ><spring:message code="systemintelligence.page.action.buttonDialogSchedule" /></span> </a>
				</sec:authorize>
			</legend>
			<p id="event-description" class="desc"></p>
		</fieldset>

		<fieldset id="event-fields-container-scheduled">
			<legend ><strong class="label"><spring:message code="table.type.scheduled" /> </strong><span id="dateScheduled"></span></legend>

			<div class="point-detail-container">
				<dl>
					<dt> <spring:message code="systemintelligence.scheduledCreateEvent.repeat" /> : </dt>
					<dd id="repeat-enum"></dd>
				</dl>
			</div>
		</fieldset>

		<fieldset id="dr-fields-container">
			<legend><strong class="label"><spring:message code="smartpointdetail.tabs.about.action"/></strong><span id="action-name"></span>, <span id="action-description"></span></legend>
			<!-- START: Step 1 -->
			<div class="advanced-search-container group yui-gc hide">
				<div class="yui-u first">
					<h5><spring:message code="systemintelligence.create.action.dialog.stepOne"/>
						<small><spring:message code="systemintelligence.create.action.dialog.stepOne.description"/></small>
					</h5>
				</div>
				<div class="yui-u"></div>
			</div>
			<!-- END: Step -->
			<!-- START: Step 2 -->
			<div class="advanced-search-container group yui-gc hide">
				<div class="yui-u first">
					<h5><spring:message code="systemintelligence.create.action.dialog.stepTwo"/>
						<small><spring:message code="systemintelligence.create.action.dialog.stepTwo.description"/></small>
					</h5>
				</div>
				<div class="yui-u"></div>
			</div>
			<!-- END: Step -->
		</fieldset>

		<fieldset id="event-fields-container-endpoints">
			<legend><strong class="label"><spring:message code="commons.pages.smartPoints" /></strong><span id="total-devices"></span></legend>
			<div class="selected-points">
				<table id="table-group-tag"></table>
			</div>
		</fieldset>
	</form>

</sec:authorize>