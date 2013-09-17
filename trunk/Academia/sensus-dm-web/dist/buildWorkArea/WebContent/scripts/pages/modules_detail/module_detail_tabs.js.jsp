<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		sensus.pages.device.module.tabs = {

				activeMenu : function ($oElement) {

					$oElement.addClass('active');

				},

				cleanTab : function () {

					$('.tabs li').find('a.active').removeClass('active');

				},

				fnGetUrlParameters : function ($oElement) {

					return $oElement.attr("href")
							+ "?id=" + $.address.parameter("id")
							+ "&deviceType=" + $.address.parameter("deviceType")
							+ "&typeEnum=" + $.address.parameter("typeEnum");
				},

				fillTab : function ($oElement) {

					$.fn.pageLoader.load(this.fnGetUrlParameters($oElement), $("#tabs-content"), $oElement);
				},

				fnClickTabs : function (event) {

					// dependencies
					var tabs = sensus.pages.device.module.tabs,
						page = sensus.util.page,
						$this = $(this);

					event.preventDefault();

					page.startProgressBar();

					tabs.cleanTab();

					tabs.activeMenu($this);

					tabs.fillTab($this);

					page.stopProgressBar();
				},

				init : function () {

					var tabs = sensus.pages.device.module.tabs,
						oTabs = $("#tabs"),
						aPathNames = $.address.pathNames(),
						sTabName = aPathNames[aPathNames.length - 1],
						oInitTab = $("li a[href='device/tab/" + sTabName + "']", oTabs);

					if (!oInitTab.length) {

						oInitTab =  $("li:first", oTabs).find("a");
					}

					// Load First Tab
					tabs.fillTab(oInitTab);

					// Handle Click Event on Tabs
					$('li a', oTabs).click(tabs.fnClickTabs);
				}
		}
	</script>
</sec:authorize>