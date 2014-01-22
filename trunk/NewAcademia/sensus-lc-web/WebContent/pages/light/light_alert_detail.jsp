<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<ul class="summary">
	<li class="fail"><span class="icon-small"></span></li>
    <li>
	    <div class="selected-points">
	    	<table class="small-table">
				<thead>
	            	<tr>
	                	<th><s:message code="smartpointdetail.page.diagnosis" />    </th>
	                	<th><s:message code="smartpointdetail.status.voltage" />    </th>
	                	<th><s:message code="smartpointdetail.status.current" />    </th>
	                	<th><s:message code="smartpointdetail.status.lightonoff" /> </th>
	                	<th><s:message code="smartpointdetail.page.operatingcondition" /></th>
	                	<th><s:message code="smartpointdetail.page.alerttime" /></th>
	                </tr>
	            </thead>
				<tbody>

	            </tbody>
	        </table>
	    </div>
	</li>
</ul>