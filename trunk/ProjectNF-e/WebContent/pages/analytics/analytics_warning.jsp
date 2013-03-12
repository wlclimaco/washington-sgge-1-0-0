<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
	<div class="content-inner">
            <!-- Messaging -->
        	<div class="yui-t2">
				<div id="yui-main">
                	<div class="yui-b">
                        <div class="content-header">
                            <h1><s:text name="analytics.page.warnings" /></h1>
                            <p class="description"><s:text name="analytics.page.monitorWarningByGroupTag" /></p>
                        </div>
                        
                        <input id="y-title" type="hidden" value="<s:text name="analytics.page.warnings" />" />
	                    
    	                <!-- END: Report Graphic -->
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
                                        <th class="analytics-col-left"><span><s:text name="analytics.table.group" /></span></th>
                                        <th><span><s:text name="analytics.table.totalWarnings" /></span></th>
                                        <th><span><s:text name="analytics.table.powerSurge" /></span></th>
                                        <th><span><s:text name="analytics.table.brownoutDetected" /></span></th>
                                        <th><span><s:text name="sensus.mlc.status_subtype.communicationfail" /></span></th>
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