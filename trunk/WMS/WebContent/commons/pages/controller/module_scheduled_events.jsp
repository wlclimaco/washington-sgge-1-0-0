<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- START - Scheduled Events -->
<div id="scheduled" class="point-detail-container">

	<h3><s:text name="commons.pages.scheduledEvents"/></h3>
	
	<div id="list">

		<!-- Blankslate -->
		<div class="blankslate hide" id="blankslate-scheduled">
			<h5><s:text name="smartpointdetail.tabs.about.addYourFirstSchedule" /></h5>
			<p><s:text name="smartpointdetail.tabs.about.noSchedulesWithThisSmartPoint" /></p>
		</div>
		
		<table id="scheduled-events" class="small-table">
            
            			<thead>
				<tr>
					<th><s:text name="systemintelligence.page.today.event"/></th>
					<th><s:text name="smartpointdetail.tabs.about.action"/></th>
					<th><s:text name="commons.pages.date"/></th>
					<th><s:text name="smartpointdetail.tabs.about.repeats"/></th>
				</tr>
			</thead>
			<tbody>

			</tbody>
       				</table>
   				</div>
</div>
<!-- END - Scheduled Events -->