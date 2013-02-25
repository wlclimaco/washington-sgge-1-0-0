<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

		<div class="content-inner">
            <!-- Messaging -->
            <div id="messaging-main" class="messaging hide"><span class="message"></span><a href="" class="remove"><s:text name="message.action.close" /></a></div>        
        	<div class="yui-t2">
                <div id="analytics-bar" class="yui-b filter-vert rounded">
                	<h3 class="rounded-top"><s:text name="analytics.page.title" /></h3>
                    <div class="filter-input activity ui-widget">
                        <label><s:text name="analytics.page.lightingActivity" /></label>                    
                    	<ul class="menu">
                        	<li><a id="bar1" href="#"><s:text name="analytics.page.alarms" /></a></li>
                        	<li><a id="bar2" href="#"><s:text name="analytics.page.warnings" /></a></li>
                        	<li><a id="bar3" href="#"><s:text name="analytics.page.installed" /></a></li>
                        </ul>
                    </div>  
                    <div class="filter-input conservation ui-widget">
                        <label><s:text name="analytics.page.conservation" /></label>                    
                    	<ul class="menu">
                        	<li><a id="bar4" href="#"><s:text name="analytics.page.consumption" /></a></li>
							<c:if test="${userContext.getTenant().getEcoModeDisable() == false}">
								<li><a id="bar5" href="#"><s:text name="analytics.page.energySavings" /></a></li>
								<li><a id="bar6" href="#"><s:text name="analytics.page.carbonCredits" /></a></li>
							</c:if>
                        </ul>
                    </div>  
                </div>
                <div id="yui-main">
                	<div class="yui-b">
                        <div class="content-header">
                        </div>
                        <div id="box-list">
	                    	<!-- START: Report Tools -->
							<div class="report-tools-container yui-gd">
                        	 	<div class="group_select param yui-u first">
                                   	<label><s:text name="analytics.page.group" /></label>
									<select name="group_select" id="group_select" cssClass="combobox" multiple="false" size="1" required="false" />
	                            </div>
		                        <div class="date-select yui-u">
									<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
										<div class="export-select">
											<ul class="link-list">
												<!--<li><a href="">Email</a></li>-->
												<li class="last export-type"><small><strong><s:text name="analytics.page.export" /></strong> <a href="" class="csv" id="csv"><s:text name="analytics.page.csv" /></a></small></li>
											</ul>
										</div>		
									</sec:authorize> 
	                               <div id="actions-options">
		                           		
	                               </div> 
	                           	</div>
	                        </div>
	    	                <!-- END: Report Tools -->
							<div class="report-graphic-container">
	                        	<div id="container" style="width: 100%; height:275px; margin: 0 auto"></div>                                        
	                        </div>
    	                	<!-- END: Report Graphic -->
	        	     	    <div id="list">
							<div id="blankslate" class="blankslate" style="display: none;">
								<h5><s:text name="widgets.blankslate.analytics.noresults" /></h5>
								<p><s:text name="widgets.blankslate.analytics.detail" /></p>
							</div>
	                            <table id="analytics-table" class="list">
		                        <!-- START : summary data -->
	                                <thead>
		                                <tr>	
		                                	
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
      </div>
