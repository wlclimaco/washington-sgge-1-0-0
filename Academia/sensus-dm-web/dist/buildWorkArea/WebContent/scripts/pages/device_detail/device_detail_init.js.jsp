<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<script type="text/javascript">
	$(document).ready(function() {

		<c:choose>
			<c:when test="${empty response}">
				var oResponse = null;
			</c:when>
			<c:otherwise>
				var oResponse = ${response};
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${empty summaries}">
				var aSummaries = null;
			</c:when>
			<c:otherwise>
				var aSummaries = ${summaries};
			</c:otherwise>
		</c:choose>

		var oTabs = $("#tabs");

		var module = sensus.pages.device.module;

		// Call modules
		module.loadModules("summaryDatas", aSummaries, oResponse);

		// Init tabs
		$.fn.pageLoader.tabs($(".tabs"), $("#tabs-content"), function ($oElement) {

			var initLoadParameter = $.fn.pageLoader.initLoadParameter;
			var bInitLoad = $.address.parameter(initLoadParameter);

			return $oElement.attr("href")
					+ "?id=" + $.address.parameter("id")
					+ "&deviceType=" + $.address.parameter("deviceType")
					+ "&typeEnum=" + $.address.parameter("typeEnum")
					+ (bInitLoad ? ("&" + initLoadParameter + "=false") : "");
		});

		$(".button").button();

		oTabs.delegate("#back-to-list", "click", function(e) {

			sensus.util.page.startGlobalProgressBar();

			var sUrl = sensus.util.page.fnFormatURLService() + "?initialLoad=false&";
			var sSession = sensus.util.session.read("SelectedFilters");
			var sDeviceType;

			e.preventDefault();

			sensus.util.session.remove(["SelectedFilters"]);

			if (sSession) {

				sUrl = sUrl + sSession;

			} else {

				sDeviceType = ($.address.parameter("device_type") || $.address.parameter("deviceType"));

				sUrl = sUrl + "device_type=" + sDeviceType + "|";

				if (sDeviceType == sensus.constants.services.electric.han.name) {

					sUrl = sUrl + "&device_subtype=" + $.address.parameter("typeEnum");
				}
			}

			$.fn.pageLoader.load(sUrl, $("#load"), null, function() {

				$.fn.pageLoader.parameter("initialLoad", null);
			});
		});

		oTabs.find(".tabs").delegate("a", "click", function(e) {

			$(".messaging").hide();
		});

		$(".messaging").delegate(".remove", "click", function(e) {

			e.preventDefault();

			$(this).parent().fadeOut("slow");
		});
	});
</script>
</sec:authorize>