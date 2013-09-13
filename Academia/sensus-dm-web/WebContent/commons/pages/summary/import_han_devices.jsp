<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="summary import-han-device-summary">
		<div id="detail-header-container" class="ss-widget-table-summary-kpi">
			<table id="informationTable" class="summary-kpi">
				<tbody>
					<tr>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="fail"></h4>
		<div class="selected-points selected-point">
			<div class="export-select tools">
				<ul class="link-list">
					<li class="export-type last"><small>Displaying <span class="size"></span> of <span class="size"></span><strong> Export ALL</strong>: <a href="" class="csv">CSV</a></small></li>
				</ul>
			</div>
			<table id="tableImportHanDevice" class="small-table">
				<thead>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</sec:authorize>