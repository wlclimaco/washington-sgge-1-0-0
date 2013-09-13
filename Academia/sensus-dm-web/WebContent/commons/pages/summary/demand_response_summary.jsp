<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="ui-state-error ui-corner-all hide">
		<small><strong>125</strong> Devices have not returned Event Status <a href="" class="button">Get Demand Response Event Status</a></small>
	</div>

	<fieldset>
	  <legend><a href="" class="text-button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> <small>Demand Response Details</small></a></legend>
	  <div class="spindown-child point-detail-container hide">
		<dl class="sui-pad1">
		  </dl>
	</div>
	</fieldset>

	<div id="detail-header-container" class="ss-widget-table-summary-kpi">
	  <table class="summary-kpi" id="summaryDemandResponse">
		  <tbody>
			   <tr>
			   </tr>
		   </tbody>
	  </table>
	</div>

	<div class="summary demand-response-sumary">
		  <h4 class="fail"></h4>
			<div class="selected-points selected-point">
				<div class="export-select tools">
					<ul class="link-list">
						<li class="export-type last"><small>Displaying <span class="size"></span> of <span class="size"></span><strong> Export ALL</strong>: <a href="" class="csv">CSV</a></small></li>
					</ul>
				</div>

			<table class="small-table" id="tableDemandResponse">
				<thead>
				</thead>
				<tbody>
				</tbody>
			</table>
		 </div>
	</div>

</sec:authorize>