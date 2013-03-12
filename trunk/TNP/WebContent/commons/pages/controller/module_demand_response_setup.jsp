<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<div id="device-demand-response-setup" class="point-detail-container">
    <h3><s:text name="smartpointdetail.tabs.about.demandResponseSetup"/>    <a href="" class="edit right"><s:text name="commons.pages.edit"/></a></h3>
      <div id="list">
	 		<!-- Blankslate -->
			<div class="blankslate hide" id="blankslate-demand-response-setup">
				<p><s:text name="smartpointdetail.tabs.about.noDemandResponse" /></p>
			</div>
            <table id="demand-response-setup" class="small-table">
            <thead>
                <tr>
                    <th><s:text name="smartpointdetail.tabs.about.enrollmentCode"/></th>
                    <th><s:text name="smartpointdetail.tabs.about.randomizeStart"/></th>
                    <th><s:text name="smartpointdetail.tabs.about.randomizeEnd"/></th>
                    <th><s:text name="smartpointdetail.tabs.about.last"/></th>
                </tr>
            </thead>
				<tbody>
				</tbody>
           </table>
       </div>
</div>