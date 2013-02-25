<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="no-baseline" class="ui-state-highlight ui-corner-all sui-pad1">
	<p>
		<span class="ui-icon ui-icon-info left"></span>
			<s:text name="smartpoint.detail.ecoMode.Edit" />
		</p>
</div>	
<div id="light-eco-mode">   
	<div id="eco-mode" class="eco-mode-container">
		<div class="content-header">
			<h1>Eco-Mode</h1>
			<p class="description"></p>
		</div>
			<div class="report-tools-container yui-gd">
				<div class="tools">
					<div class="export-select">
						<ul class="link-list">
							<li class="last export-type">
								<small>
									<strong><s:text name="analytics.page.export" /></strong><a href="" class="csv" id="csv"><s:text name="analytics.page.csv" /></a>
								</small>
							</li>
						</ul>
					</div>
					<div class="date_select yui-u">
						<div id="actions-options">
							<ul id="date-tag" class="link-list">
								<li class="active"><a id="datetage-30" href="#">1m</a></li>
								<li><a id="datetage-90" href="#">3m</a></li>
								<li><a id="datetage-ytd" href="#">YTD</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
			<!-- END: Report Tools -->
			<div class="report-graphic-container">
				<div id="container" style="width: 100%; height:275px; margin: 0 auto"></div>                                        
			</div>
			<div id="list">
			<div id="blankslate" class="blankslate" style="display: none;">
				<h5><s:text name="widgets.blankslate.noresults" /></h5>
				<p><s:text name="widgets.blankslate.ecoMode.detail" /></p>
			</div>
				   <table id="ecomode-table" class="list">
				<!-- START : summary data -->
					<thead>
						<tr>
							<th><span id="consumption_day"><s:text name="smartpoint.detail.ecoMode.date" /></span></th>
							<th><span id="consumption"><s:text name="smartpoint.detail.ecoMode.measured" /></span></th>
							<th><span id="ecomode_baseline"><s:text name="smartpoint.detail.ecoMode.baseline" /></span></th>
							<th><span id="consumption_ecomode"><s:text name="smartpoint.detail.ecoMode.EcoMode" /></span></th>
						</tr>
					</thead>
					<!-- END : summary data -->                        
					   <tbody>                            
					   </tbody>
					</table>                           
			</div>				
		</div>
	</div>
