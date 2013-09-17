<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="yui-b" >
		   <div class="filter-vert rounded hide" id="searchFilter">
			   <h3 class="rounded-top">Search Events</h3>
			   <div id="search" class="filter-input filter-input search ui-widget">
				   <ul>
					   <li><input id="query" type="text" value="" /></li>
					   <li>
						<select id="query_type">
							<option value="e_id">Event ID</option>
							<option value="e_name">Event Name</option>
							<option value="m_id">Meter ID</option>
							<option value="fn_id">FlexNet ID</option>
						</select>
					   </li>
					   <li><button id="go-button">Search</button></li>
				   </ul>
			   </div>
		   </div>
		   <div class="filter-vert rounded" id="filters">
			   <h3 class="rounded-top">Filter Events</h3>
		   <div class="filter-input search ui-widget hide" id="dateFilter">
			   <ul>
				   <li><span>View</span> <input id="view_from" type="text" class="short"/></li>
				   <li><span>Back</span> <input id="total_days" type="text" style="width:2em" value="10" /><button id="go-button">days</button></li>
			  </ul>
		   </div>

		   <div class="filter-input status ui-widget hide" id="checkBox">
				<label class="toggle on">Status</label>
				<div class="collapse" id="checkBoxUl">
					<ul class="all-checked"><li class="checkbox"><input type="checkbox" checked="checked"> <strong>All Status</strong></li></ul>
					<ul class="ui-listcombobox"></ul>
				</div>
			</div>
			<div class="filter-input emergency ui-widget hide" id="textFilter">
				<label class="toggle off">Configuration</label>
				<ul class="collapse" id="textFilterUl">

				</ul>
			</div>

		</div>
				<!--END - filters -->
	</div>

</sec:authorize>