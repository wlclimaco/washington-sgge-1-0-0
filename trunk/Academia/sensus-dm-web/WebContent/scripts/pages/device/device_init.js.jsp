<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		var sMessage = $.address.parameter("message");

		// Data Table
		<c:choose>
			<c:when test="${not empty refresh}">
				var oResponse = "refresh";
				var bPreLoad = false;
			</c:when>
			<c:when test="${empty response}">
				var oResponse = null;
				var bPreLoad = false;
			</c:when>
			<c:otherwise>
				var oResponse = ${response};
				var bPreLoad = true;
			</c:otherwise>
		</c:choose>

		// Column Filter
		<c:choose>
			<c:when test="${empty ColumnFilter}">
				var oColumnFilter = null;
			</c:when>
			<c:otherwise>
				var oColumnFilter = ${ColumnFilter};
			</c:otherwise>
		</c:choose>

		// Session
		<c:choose>
			<c:when test="${empty session}">
				var session = null;
			</c:when>
			<c:otherwise>
				var session = ${session};
			</c:otherwise>
		</c:choose>

		var sDeviceType = $.address.parameter("device_type");
		var sDeviceSubType = $.address.parameter("device_subtype");
		var $buttonImportHan = $("#buttonImportHan");
		var $currentTab;
		var importHanButtonControl = function (sDeviceType) {

			if (sDeviceType == "HAN_DEVICE|" || sDeviceType == "LCM|") {

				$buttonImportHan.removeClass("hide");

			} else {

				$buttonImportHan.addClass("hide");
			}
		};

		sensus.util.page.initMessagingTabs();

		if (sMessage) {

			if (sMessage != "IMPORT") {

				sensus.util.page.showMessage("messaging-main", decodeURI(sMessage), "error");

			} else {

				sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.importHanDevice.success"), "confirm");
			}

			// Clear parameters
			$.fn.pageLoader.parameter("message", null);
		}

		// Set up List/Map toggle switch (set no value yet)
		sensus.util.device.startView($("#views"), sensus.pages.device.changeViewEvent || sensus.util.device.changeViewEvent);

		// Import Han
		$buttonImportHan.click(function(e) {

			e.preventDefault();

			sensus.util.actiondialog.launchActionDialog("importHanDevice",
					sensus.util.device.dialogSettings.importHanDevice("Import Devices", "System"));
		});

		$(".tabs.device-tabs").delegate("a", "click", function(e) {

			var $this = $(this);

			e.preventDefault();

			sensus.util.page.startGlobalProgressBar(function () {

				var deviceTypes = $this.attr("href").replace("#", "").split("-");

				$(".tabs a").removeClass("active");
				$this.addClass("active");

				$.address.autoUpdate(false)
		        		.parameter("device_type", deviceTypes[0])
		        		.parameter("device_subtype", deviceTypes[1] || null)
		        		.autoUpdate(true)
		        		.update();

				// hide/show importan han button
				importHanButtonControl($.address.parameter("device_type"));

				sensus.util.session.remove();

				sensus.util.device.reloadTabContent(sensus.pages.device.keepFilters);
				sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

				sensus.util.page.stopGlobalProgressBar();
			});
		});

		// Set default device type
		if (!sDeviceType) {

			sDeviceType = sensus.settings.oDeviceTypeParameters.deviceTypePermissions[0].deviceType + "|";

			$.fn.pageLoader.parameter("device_type", sDeviceType);
		}

		// Active current tab
		$currentTab = $(".tabs a[href='#" + sDeviceType + (sDeviceSubType ? "-" + sDeviceSubType : "") + "']");

		if ($currentTab.length) {

			$currentTab.addClass("active");

		} else {

			$(".tabs a[href='#" + sDeviceType + "']").addClass("active");
		}

		sensus.util.device.loadTable(sensus.pages.device.getTableConfig(), bPreLoad, oResponse, oColumnFilter, session);
		sensus.util.device.loadFilter(sensus.pages.device.getFilters(), oColumnFilter, session);

		// Initialize actions list
		sensus.util.device.fnLoadActionsList(sensus.pages.device.additionalActions);

		// Call the object that will open Dialog for save custom search
		$(".save").click(function(e) {

			e.preventDefault();

			sensus.util.actiondialog.launchActionDialog("searchSave", sensus.util.device.dialogSettings.searchSave);

			return false;
		});

		// To Generate the archive CSV
		sensus.util.exportcsv.setGenerateCSVEvent({

			url : sensus.pages.device.exportCSVUrl,

			$element : $("#csv"),

			getGenerateRequestCSV : sensus.util.device.getGenerateRequestCSVDevice
		});

		// hide/show importan han button
		importHanButtonControl(sDeviceType);

		sensus.util.session.remove();

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>

</sec:authorize>