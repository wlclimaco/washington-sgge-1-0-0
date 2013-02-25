<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div id="dashboard">

			<!-- START : summary data -->
	        <div id="summaryDataTable" class="ss_widget_table_summary_kpi">
	            <table class="summary_kpi">
	                <tr class="">
	                    <td  class="first">
	                    	<s:text name="dashboard.header.totalinstalled" />
	                    	<span id="totalinstalled">
	                    		<a class="afilter" href="smartpoint/ajax.list.action?noFilter=1"> </a>
                    		</span> 
                    		<small><s:text name="dashboard.page.currenttotal" /></small>
                    	</td>
	                    <td>
	                    	<s:text name="dashboard.header.totalalarms" />
	                    	<span id="totalalarms">
	                    		<a class="afilter" href="smartpoint/ajax.list.action?status=1|"> </a>
                    		</span> 
                    		<small><s:text name="dashboard.page.currenttotal" /></small>
                    	</td>
	                    <td>
	                    	<s:text name="dashboard.header.totalwarnings" />
	                    	<span id="totalwarnings">
	                    		<a class="afilter" href="smartpoint/ajax.list.action?status=2|"> </a>
                    		</span> 
                    		<small><s:text name="dashboard.page.currenttotal" /></small>
                    	</td>
	                    <td>
	                    	<s:text name="dashboard.header.consumption" />
	                    	<span id="totalconsumption">
	                    		<a class="afilter" href="analytics/ajax.list.action?tb=&ty=4&g=&di=&de=&dt=&db=true"> </a><sup id="kwh-1"></sup>
                    		</span>

                    		<small><s:text name="dashboard.page.since" /> </small>
                    	</td>
						<c:if test="${userContext.getTenant().getEcoModeDisable() == false}">
							<td>
								<s:text name="dashboard.page.ecomode" />
								<span id="totaleco-mode">
									<a class="afilter" href="analytics/ajax.list.action?tb=&ty=5&g=&di=&de=&dt=&db=true"> </a>%
								</span>

								<small id="performing"></small>
							</td>
							
							<td class="last">
								<s:text name="dashboard.header.carboncredits" />
								<span id="totalcarboncredits">
									<a class="afilter" href="analytics/ajax.list.action?tb=&ty=6&g=&di=&de=&dt=&db=true"> </a>
									<sup> cc</sup>
								</span>

								<small><s:text name="dashboard.page.since" /> </small>
							</td>
						</c:if>
	                </tr>
	            </table>
	        </div>

	        <!-- END : summary data -->
			<!-- START : content -->
	        <div class="content-inner">
	            <!-- START : KPI -->
	            <div id="kpi_container">            	
	                <table>

						<thead>
	                        <tr class="header">
	                            <th rowspan="2" colspan="2" class="title"><h2><s:text name="dashboard.page.lightcontrolkpi" /></h2></th>
	                            <th class="today"><s:text name="dashboard.page.today" /></th>
	                            <th class="week last" colspan="4"><s:text name="dashboard.page.week" /> <small>(<s:text name="dashboard.page.rollingsevendays" />)</small></th>
	                            <th class="month last" colspan="4"><s:text name="dashboard.page.month" /> <small>(<s:text name="dashboard.page.rollingthirtydays" />)</small></th>
	                        </tr>
	                        <tr class="subheader">
	                            <th class="today"><s:text name="dashboard.page.total" /></th>
	                            <th class="week"><s:text name="dashboard.page.total" /></th>
	                            <th class="week"><s:text name="dashboard.page.avg" /></th>
	                            <th class="week"><s:text name="dashboard.page.trend" /></th>
	                            <th class="week last"><s:text name="dashboard.page.change" /></th>
	                            <th class="month"><s:text name="dashboard.page.total" /></th>
	                            <th class="month"><s:text name="dashboard.page.avg" /></th>
	                            <th class="month"><s:text name="dashboard.page.trend" /></th>
	                            <th class="month"><s:text name="dashboard.page.change" /></th>
	                        </tr>
						</thead>

	                    <tbody>
	                        <tr>
	                            <td rowspan="4" class="row-title"><h3><s:text name="dashboard.page.activity" /> <small><s:text name="dashboard.page.networkHealth" /></small></h3></td>
	                        </tr>
	                        <tr>
	                            <td id="activity-alarms" class="row-title-sub"><a id="lighting_alarm" class="afilter" href="analytics/ajax.list.action?tb=&ty=1&g=1&di=&de=&dt="></a></td>
	                            <td id="lighting_alarm_today" class="today v-alt"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=1&g=1&di=&de=&dt=1d&db=true"></a></td>
	                            <td id="lighting_alarm_week" class="week first"><a class="afilter" href="analytics/ajax.list.action?ty=1&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_alarm_week_avg" class="week v-alt"><span></span></td>
	                            <td class="week"><span id="lighting_alarm_week_trends" class="inline-spark spark"></span></td>
	                            <td id="week-1" class="week v-alt neg last"><span id="lighting_alarm_week_change"></span></td>
	                            <td id="lighting_alarm_month" class="month first"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=1&g=1&di=&de=&dt=1m&db=true"></a></td>
	                            <td id="lighting_alarm_month_avg" class="month v-alt"><span></span></td>
	                            <td class="month"><span id="lighting_alarm_month_trends" class="inline-spark spark"></span></td>
	                            <td class="neg v-alt month"><span id="lighting_alarm_month_change"></span></td>
	                        </tr>
	                        <tr class="alt">
	                            <td id="activity-warnings" class="row-title-sub"><a id="lighting_warning" class="afilter" href="analytics/ajax.list.action?tb=&ty=2&g=1&di=&de=&dt=&db=true"></a></td>
	                            <td id="lighting_warning_today" class="today v-alt"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=2&g=1&di=&de=&dt=1d&db=true"></a></td>
	                            <td id="lighting_warning_week" class="week first"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=2&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_warning_week_avg" class="week v-alt"><span></span></td>
	                            <td class="week"><span id="lighting_warning_week_trends" class="inline-spark spark"></span></td>
	                            <td id="week-2" class="week v-alt pos last"><span id="lighting_warning_week_change"></span></td>
	                            <td id="lighting_warning_month" class="month first"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=2&g=1&di=&de=&dt=1m&db=true"></a></td>
	                            <td id="lighting_warning_month_avg" class="month v-alt"><span></span></td>
	                            <td class="month"><span id="lighting_warning_month_trends" class="inline-spark spark"></span></td>
	                            <td class="month v-alt pos"><span id="lighting_warning_month_change"></span></td>
	                        </tr><tr>
	                            <td id="activity-installed" class="row-title-sub"><a id="lighting_installed" class="afilter" href="analytics/ajax.list.action?tb=&ty=3&g=1&di=&de=&dt=&db=true"></a></td>
	                            <td id="lighting_installed_today"class="today v-alt"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=3&g=1&di=&de=&dt=1d&db=true"></a></td>
	                            <td id="lighting_installed_week" class="week first"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=3&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_installed_week_avg" class="week v-alt"><span></span></td>
	                            <td class="week"><span id="lighting_installed_week_trends" class="inline-spark spark"></span></td>
	                            <td id="week-3" class="week v-alt pos last"><span id="lighting_installed_week_change"></span></td>
	                            <td id="lighting_installed_month" class="month first"><a class="afilter" href="analytics/ajax.list.action?tb=&ty=3&g=1&di=&de=&dt=1m&db=true"></a></td>
	                            <td id="lighting_installed_month_avg" class="mont v-alt"><span></span></td>
	                            <td class="month"><span id="lighting_installed_month_trends" class="inline-spark spark"></span></td>
	                            <td class="month v-alt pos"><span id="lighting_installed_month_change"></span></td>
	                        </tr>
	                    </tbody>
	                </table>            
	            </div>
	            <!-- END : KPI -->
	            <!-- START : 3 column -->
	            <div class="yui-gb">
	            	<div class="yui-u first ss_widget_container">
	                	<h2><s:text name="dashboard.page.last" /> <strong> 5 <s:text name="dashboard.page.lampFailure" /></strong> <s:text name="dashboard.page.alarms" /></h2>
	                    <!-- blankslate -->
						<div id="blankslate" class="blankslate hide">
							<h5><s:text name="dashboard.blankslate.lightfailure"/></h5>
						</div>
						<table id="dashboard-lightfailure-table" class="align-left">
	                    	<thead>
		                    	<tr>
		                        	<th><s:text name="dashboard.page.poleId" /></th>
		                        	<th><s:text name="dashboard.page.flexnetID" /></th>
		                        	<th class="hide">&nbsp;</th>
		                        	<th><s:text name="dashboard.page.date" /></th>
		                        	<th class="hide"></th>
		                        	<th class="hide"></th>
		                        	<th class="hide"></th>
		                        </tr>
	                      	</thead>
							<tbody>
							</tbody>  
	                    </table>
	                </div>
	            	<div class="yui-u ss_widget_container">
	                	<h2><s:text name="dashboard.page.last" /> <strong>5 <s:text name="dashboard.page.powerFailure" /></strong> <s:text name="dashboard.page.alarms" /></h2>
	                	<!-- blankslate -->
						<div id="blankslate" class="blankslate hide">
							<h5><s:text name="dashboard.blankslate.powerfailure"/></h5>
						</div>
						<table id="dashboard-powerfailure-table" class="align-left">
	                    	<thead>
		                    	<tr>
		                        	<th><s:text name="dashboard.page.poleId" /></th>
		                        	<th><s:text name="dashboard.page.flexnetID" /></th>
									<th class="hide">&nbsp;</th>
		                        	<th><s:text name="dashboard.page.date" /></th>
		                        	<th class="hide"></th>
		                        	<th class="hide"></th>
		                        	<th class="hide"></th>
		                        </tr>
	                      	</thead>
							<tbody>
							</tbody>
	                    </table>						
	                </div>
	            	<div class="yui-u ss_widget_container">
	                	<h2><s:text name="dashboard.page.alertsByType" /> <small>(<s:text name="dashboard.page.rollingthirtydays" />)</small></h2>
						<div id="container" style="width: 100%; height: 250px; margin: 0 auto">
							<!-- blankslate -->
							<div id="blankslate" class="blankslate hide">
								<h5><s:text name="dashboard.page.noalerts" /></h5>
							</div>
						</div>                
	                </div>
	            </div>
	             <div class="stamp"><em></em></div>    
	            <!-- END : 3 column -->
	        </div>
	        <!-- END : Content -->
	    </div>