<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		var $csv = $("#csv");

		<c:choose>
			<c:when test="${empty response}">
				var oPreLoadResponse = null;
			</c:when>
			<c:otherwise>
				var oPreLoadResponse = ${response};
			</c:otherwise>
		</c:choose>

		sensus.util.page.initMessagingTabs();

		sensus.pages.processreport.reportTable = $("#report-table").dataTable(sensus.widgets.datatable.setTable({

			id : "report-table",
			aaData : sensus.pages.processreport.fnPreDrawCallback(oPreLoadResponse),

			aColumns : [
				{sId: "deviceId", 					 sWidth: "5%", bSortable : false},
				{sId: "flexNetId", 					 sWidth: "5%", bSortable : false},
				{sId: "dateTime", 					 sWidth: "5%", bSortable : false},
				{sId: "recentConsumption", 			 sWidth: "5%", bSortable : false},
				{sId: "recentConsumptionPercentage", sWidth: "5%", bSortable : false},
				{sId: "priorConsumption", 			 sWidth: "5%", bSortable : false},
				{sId: "priorConsumptionPercentage",  sWidth: "5%", bSortable : false},
				{sId: "dailyConsumptionAverage", 	 sWidth: "5%", bSortable : false},
				{sId: "deviceType", 				 bVisible : false}
			],

			oSettings : {
				iDisplayLength: -1
			},

			$exportCSVElement : $csv,

			fnRowCallback : function(nRow, aData, iDisplayIndex, objColumn) {

				// Format the dateTime column
				var sDateTime = aData[objColumn.dateTime];

				if (sDateTime) {
					$("td:eq(" + objColumn.dateTime + ")", nRow).html(sensus.pages.processreport.fnFormatDateTime(sDateTime));
				}

				// Insert link at device Id column to load the device detail page
				$("td:eq(" + objColumn.deviceId + ")", nRow).html("<strong><a class='alist' href='device/detail?id="
						+ aData[objColumn.flexNetId] + "&deviceType=" + aData[objColumn.deviceType] + "'>"
						+ aData[objColumn.deviceId] + "</a></strong>");
			}

		}));

		// To Generate the archive CSV
		sensus.util.exportcsv.setGenerateCSVEvent({

			url : "api/export/generateFileCsvLeakReport",

			$element : $csv,

			getGenerateRequestCSV : function() {

				return new WaterMeterRequest({});
			}
		});

		//hide pagination
		$('.numbers').html("");

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>

</sec:authorize>