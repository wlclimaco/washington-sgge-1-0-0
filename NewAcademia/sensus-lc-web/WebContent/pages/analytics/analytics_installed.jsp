<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

		<div class="content-inner">
            <!-- Messaging -->

        	<div class="yui-t2">

                <div id="yui-main">
                	<div class="yui-b">
                        <div class="content-header">
                            <h1><s:message code="analytics.page.installedLights" /></h1>
                            <p class="description"><s:message code="analytics.page.monitorInstalledByGroupTag" /></p>
                        </div>


	                   <input id="y-title" type="hidden" value="<s:message code="analytics.page.lights" />" />

        	     	    <div id="list">
                            <!-- blankslate -->
							<div id="blankslate" class="blankslate">
								<h5></h5>
								<p></p>
							</div>
                            <table id="analytics-table" class="list">
	                        <!-- START : summary data -->
                                <thead>
                                    <tr class="">
                                        <th class="hide"></th>
                                        <th class="analytics-col-left"><span><s:message code="analytics.table.group" /></span></th>
                                        <th><span><s:message code="analytics.table.installedLights" /></span></th>
                                        <th><span><s:message code="analytics.table.induction" /></span></th>
                                        <th><span><s:message code="analytics.table.led" /></span></th>
                                        <th><span><s:message code="analytics.table.other" /></span></th>
										<th class="hide"></th>
                                    </tr>
                                </thead>
		                        <!-- END : summary data -->
                                <tbody>
                                </tbody>
                             </table>
				</div>
              </div>
            </div>
		</div>
      </div>