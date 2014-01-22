<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

	<div id="dashboard">

			<!-- START : summary data -->
	        <div id="summaryDataTable" class="ss_widget_table_summary_kpi">
	            <table class="summary_kpi">
	                <tr class="">
	                    <td  class="first">
	                    	<s:message code="dashboard.header.totalinstalled" />
	                    	<span id="totalinstalled">
	                    		<a class="afilter" href="light?total=true"> </a>
                    		</span>
                    		<small><s:message code="dashboard.page.currenttotal" /></small>
                    	</td>
	                    <td>
	                    	<s:message code="dashboard.header.totalalarms" />
	                    	<span id="totalalarms">
	                    		<a class="afilter" href="light?alerts=1|"> </a>
                    		</span>
                    		<small><s:message code="dashboard.page.currenttotal" /></small>
                    	</td>
	                    <td>
	                    	<s:message code="dashboard.header.totalwarnings" />
	                    	<span id="totalwarnings">
	                    		<a class="afilter" href="light?alerts=2|"> </a>
                    		</span>
                    		<small><s:message code="dashboard.page.currenttotal" /></small>
                    	</td>
	                    <td>
	                    	<s:message code="dashboard.header.consumption" />
	                    	<span id="totalconsumption">
	                    		<a class="afilter" href="analytics/?tb=&ty=4&g=&di=&de=&dt=0&db=true"> </a><sup id="kwh-1"></sup>
                    		</span>

                    		<small><s:message code="dashboard.page.since" /> </small>
                    	</td>
						<c:if test="${userContext.getTenant().getEcoModeDisable() == false}">
							<td>
								<s:message code="dashboard.page.ecomode" />
								<span id="totaleco-mode">
									<a class="afilter" href="analytics/?tb=&ty=5&g=&di=&de=&dt=1m&db=true"> </a>%
								</span>

								<small id="performing"></small>
							</td>

							<td class="last">
								<s:message code="dashboard.header.carboncredits" />
								<span id="totalcarboncredits">
									<a class="afilter" href="analytics/?tb=&ty=6&g=&di=&de=&dt=0&db=true"> </a>
									<sup> cc</sup>
								</span>

								<small><s:message code="dashboard.page.since" /> </small>
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
	                            <th rowspan="2" colspan="2" class="title"><h2><s:message code="dashboard.page.lightcontrolkpi" /></h2></th>
	                            <th class="today"><s:message code="dashboard.page.today" /></th>
	                            <th class="week last" colspan="4"><s:message code="dashboard.page.week" /> <small>(<s:message code="dashboard.page.rollingsevendays" />)</small></th>
	                            <th class="month last" colspan="4"><s:message code="dashboard.page.month" /> <small>(<s:message code="dashboard.page.rollingthirtydays" />)</small></th>
	                        </tr>
	                        <tr class="subheader">
	                            <th class="today"><s:message code="dashboard.page.total" /></th>
	                            <th class="week"><s:message code="dashboard.page.total" /></th>
	                            <th class="week"><s:message code="dashboard.page.avg" /></th>
	                            <th class="week"><s:message code="dashboard.page.trend" /></th>
	                            <th class="week last"><s:message code="dashboard.page.change" /></th>
	                            <th class="month"><s:message code="dashboard.page.total" /></th>
	                            <th class="month"><s:message code="dashboard.page.avg" /></th>
	                            <th class="month"><s:message code="dashboard.page.trend" /></th>
	                            <th class="month"><s:message code="dashboard.page.change" /></th>
	                        </tr>
						</thead>

	                    <tbody>
	                        <tr>
	                            <td rowspan="4" class="row-title"><h3><s:message code="dashboard.page.activity" /> <small><s:message code="dashboard.page.networkHealth" /></small></h3></td>
	                        </tr>
	                        <tr>
	                            <td id="activity-alarms" class="row-title-sub"><a id="lighting_alarm" class="afilter" href="analytics/?tb=&ty=1&g=1&di=&de=&dt=1w"></a></td>
	                            <td id="lighting_alarm_today" class="today v-alt"><a class="afilter" href="analytics/?tb=&ty=1&g=1&di=&de=&dt=1d&db=true"></a></td>
	                            <td id="lighting_alarm_week" class="week first"><a class="afilter" href="analytics/?ty=1&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_alarm_week_avg" class="week v-alt"><span></span></td>
	                            <td class="week"><span id="lighting_alarm_week_trends" class="inline-spark spark"></span></td>
	                            <td id="week-1" class="week v-alt neg last"><span id="lighting_alarm_week_change"></span></td>
	                            <td id="lighting_alarm_month" class="month first"><a class="afilter" href="analytics/?tb=&ty=1&g=1&di=&de=&dt=1m&db=true"></a></td>
	                            <td id="lighting_alarm_month_avg" class="month v-alt"><span></span></td>
	                            <td class="month"><span id="lighting_alarm_month_trends" class="inline-spark spark"></span></td>
	                            <td class="neg v-alt month"><span id="lighting_alarm_month_change"></span></td>
	                        </tr>
	                        <tr class="alt">
	                            <td id="activity-warnings" class="row-title-sub"><a id="lighting_warning" class="afilter" href="analytics/?tb=&ty=2&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_warning_today" class="today v-alt"><a class="afilter" href="analytics/?tb=&ty=2&g=1&di=&de=&dt=1d&db=true"></a></td>
	                            <td id="lighting_warning_week" class="week first"><a class="afilter" href="analytics/?tb=&ty=2&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_warning_week_avg" class="week v-alt"><span></span></td>
	                            <td class="week"><span id="lighting_warning_week_trends" class="inline-spark spark"></span></td>
	                            <td id="week-2" class="week v-alt pos last"><span id="lighting_warning_week_change"></span></td>
	                            <td id="lighting_warning_month" class="month first"><a class="afilter" href="analytics/?tb=&ty=2&g=1&di=&de=&dt=1m&db=true"></a></td>
	                            <td id="lighting_warning_month_avg" class="month v-alt"><span></span></td>
	                            <td class="month"><span id="lighting_warning_month_trends" class="inline-spark spark"></span></td>
	                            <td class="month v-alt pos"><span id="lighting_warning_month_change"></span></td>
	                        </tr><tr>
	                            <td id="activity-installed" class="row-title-sub"><a id="lighting_installed" class="afilter" href="analytics/?tb=&ty=3&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_installed_today"class="today v-alt"><a class="afilter" href="analytics/?tb=&ty=3&g=1&di=&de=&dt=1d&db=true"></a></td>
	                            <td id="lighting_installed_week" class="week first"><a class="afilter" href="analytics/?tb=&ty=3&g=1&di=&de=&dt=1w&db=true"></a></td>
	                            <td id="lighting_installed_week_avg" class="week v-alt"><span></span></td>
	                            <td class="week"><span id="lighting_installed_week_trends" class="inline-spark spark"></span></td>
	                            <td id="week-3" class="week v-alt pos last"><span id="lighting_installed_week_change"></span></td>
	                            <td id="lighting_installed_month" class="month first"><a class="afilter" href="analytics/?tb=&ty=3&g=1&di=&de=&dt=1m&db=true"></a></td>
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
	                	<h2><s:message code="dashboard.page.last" /> <strong> 5 <s:message code="dashboard.page.lampFailure" /></strong> <s:message code="dashboard.page.alarms" /></h2>
	                    <!-- blankslate -->
						<div id="blankslate" class="blankslate hide">
							<h5><s:message code="dashboard.blankslate.lightfailure"/></h5>
						</div>
						<table id="dashboard-lightfailure-table" class="align-left">
	                    	<thead>
		                    	<tr>
		                        	<th><s:message code="dashboard.page.poleId" /></th>
		                        	<th><s:message code="dashboard.page.flexnetID" /></th>
		                        	<th class="hide">&nbsp;</th>
		                        	<th><s:message code="dashboard.page.date" /></th>
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
	                	<h2><s:message code="dashboard.page.last" /> <strong>5 <s:message code="dashboard.page.powerFailure" /></strong> <s:message code="dashboard.page.alarms" /></h2>
	                	<!-- blankslate -->
						<div id="blankslate" class="blankslate hide">
							<h5><s:message code="dashboard.blankslate.powerfailure"/></h5>
						</div>
						<table id="dashboard-powerfailure-table" class="align-left">
	                    	<thead>
		                    	<tr>
		                        	<th><s:message code="dashboard.page.poleId" /></th>
		                        	<th><s:message code="dashboard.page.flexnetID" /></th>
									<th class="hide">&nbsp;</th>
		                        	<th><s:message code="dashboard.page.date" /></th>
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
	                	<h2><s:message code="dashboard.page.alertsByType" /> <small>(<s:message code="dashboard.page.rollingthirtydays" />)</small></h2>
						<div id="container" style="width: 100%; height: 250px; margin: 0 auto">
							<!-- blankslate -->
							<div id="blankslate" class="blankslate hide">
								<h5><s:message code="dashboard.page.noalerts" /></h5>
							</div>
						</div>
	                </div>
	            </div>
	             <div class="stamp"><em></em></div>
	            <!-- END : 3 column -->
	        </div>
	        <!-- END : Content -->
	    </div>
		<%@ include file="../../scripts/pages/dashboard/dashboard_main.js.jsp" %>
		<%@ include file="../../scripts/pages/dashboard/dashboard_init.js.jsp" %>

