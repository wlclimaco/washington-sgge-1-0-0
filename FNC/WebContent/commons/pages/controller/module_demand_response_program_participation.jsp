<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<div id="device-demand-response-program-participation" class="point-detail-container">
    <h3><s:text name="smartpointdetail.tabs.about.demandResponseProgramParticipation"/></h3>
      <div id="list">
	 		<!-- Blankslate -->
			<div class="blankslate hide" id="blankslate-demand-response-program-participation">
				<p><s:text name="smartpointdetail.tabs.about.noDemandResponseProgramParticipation" /></p>
			</div>
            <table id="demand-response-program-participation" class="small-table">
            <thead>
                <tr>
                    <th><s:text name="smartpointdetail.tabs.about.eventTitle"/></th>
                    <th><s:text name="smartpointdetail.tabs.about.dateAndTime"/></th>
                    <th><s:text name="smartpointdetail.tabs.about.status"/></th>
                </tr>
            </thead>
            <tbody>                            
            </tbody>
           </table>
       </div>
       
</div>