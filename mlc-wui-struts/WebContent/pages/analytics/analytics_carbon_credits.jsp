<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


		<div class="content-inner">
            <!-- Messaging -->    
        	<div class="yui-t2">
                <div id="yui-main">
                	<div class="yui-b">
                        <div class="content-header">
                            <h1><s:text name="analytics.page.carbonCredits" /></h1>
                            <p class="description"><s:text name="analytics.page.trackCarbonCredits" /></p>
                        </div>
	                   
	                   <input id="y-title" type="hidden" value="<s:text name="analytics.page.carbonCredits" />" />
	                   
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
                                        <th><span><s:text name="analytics.table.creditsCreated" /></span></th>
                                        <th><span><s:text name="analytics.table.energySaved" /><sup></sup></span></th>
                                        <th><span><s:text name="analytics.table.barrelsOfOilSaved" /></span></th>
                                        <th><span><s:text name="analytics.table.metricTonsOfCO" /><sub>2</sub> <s:text name="analytics.table.saved" /></span></th>
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
