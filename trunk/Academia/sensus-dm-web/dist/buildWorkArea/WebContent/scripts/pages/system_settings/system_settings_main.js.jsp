<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.pages.systemsettings = {

			$content : null,
			$divTabs : null,

			initLinkTab : function ($content, $divTabs) {

				sensus.pages.systemsettings.$content = $content;
				sensus.pages.systemsettings.$divTabs = $divTabs;

				$divTabs.delegate(".linkTab", "click", sensus.pages.systemsettings.linkTabEvent);

				$('.linkTab:first', $divTabs).click();

			},

			activeLinkTab : function ($tab) {

				this.$divTabs.find("li").removeClass("ui-tabs-selected ui-state-active");
				$tab.parents("li").addClass("ui-tabs-selected ui-state-active");

			},

			linkTabEvent : function (event) {

				event.preventDefault();

				sensus.util.page.startProgressBar();

				var $this = $(this),
					sUrl = $this.attr("href");

				sensus.pages.systemsettings.activeLinkTab($this);

				sensus.pages.systemsettings.ajaxLinkTab(sensus.pages.systemsettings.$content, sUrl);
			},

			ajaxLinkTab : function ($content, sUrl) {

				$.ajax({
					url : sUrl,
					type: 'GET',
					cache : false,
					async : false,
					success : function (html) {

						$content.empty();
						$content.html(html);
					}
				});
			}
	}
	</script>

</sec:authorize>