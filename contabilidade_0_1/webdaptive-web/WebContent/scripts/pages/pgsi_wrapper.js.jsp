<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">

	$(document).ready(function() {

		var oPropertiesResponse = {};
		var oUserPropertiesResponse = {};

			// Get Settings from pre-loaded data
		<c:if test="${not empty response}">
			var oServerResponse = ${response};
			if(!$.pgsi.isNullOrUndefined(oServerResponse.uiProperties))
			{
				oPropertiesResponse.settings = oServerResponse.uiProperties;
			}

			if(!$.pgsi.isNullOrUndefined(oServerResponse.userContext))
			{
				oUserPropertiesResponse.userSettings = oServerResponse.userSettings;
				oUserPropertiesResponse.userContext = oServerResponse.userContext;
			}
		</c:if>

		// Get TimeZone rules from pre-loaded data
		<c:if test="${not empty timeZones}">
			oPropertiesResponse.timeZones = ${timeZones};
		</c:if>

		// Get localization messages from pre-loaded data
		<c:if test="${not empty localeMessages}">
			oPropertiesResponse.localeMessages = ${localeMessages};
		</c:if>

		pgsi.pages.sendsolv.fnLoadAppProperties(oPropertiesResponse, pgsi.settings.storage.app);
		pgsi.pages.sendsolv.fnLoadUserProperties(oUserPropertiesResponse, pgsi.settings.storage.user);

		/**
		 * Load Simple page without active menu item
		 */

		$("body").on('click','.alist', function(event)
		{
			event.preventDefault();

			$submenu = $(this).parent().parent();
			var $this = $(this);

			$.pgsi.pageLoader.load({ url: $(this).attr("href"), $content: $("#load") });

		});

		// Initialize the progress bar dialog
		$.pgsi.progressBar.init();

		// Load remaining js files that can depend on internationalization
		head.load("scripts/util/page.js",
				  "thirdparty/jquery/custom/jquery.dataTables.custom.js",
				  "thirdparty/jquery/jquery.datatable.fnReloadAjax.js",
				  "thirdparty/jquery/jquery.datatable.fnStartingRedraw.js",
				  "commons/scripts/pgsi_commons_table_1.0.js");

		// Wait for final js files and then finish the app initialization
		head.ready("pgsi_commons_table_1.0.js", function() {
			var fnLoadCurrentPage = function()
			{
				var parameters 		= $.pgsi.pageLoader.getQueryString();
				var page 			= $.pgsi.pageLoader.currentPage();
				var url;

				// Remove unwanted slash
				if((!$.pgsi.isNullOrUndefined(page))){
					if(page.indexOf("/") != -1)
					{
						page = page.substring(1);
					}
				}
				if (parameters)
				{
					url = page + "?" + parameters;
				}

				else
				{
					url = page;
				}

				$.pgsi.pageLoader.load({
					url: url,
					$content: $("#load"),
					bUpdateUrl: false,
					bInitialLoad : false,
					bStartProgressBar : false
				});

			};

			// Load Page first time
			if(!$.pgsi.pageLoader.currentPage())
			{
				$.pgsi.pageLoader.load({
					url: "dashboard",
					$content: $("#load"),
					$link: $("nav.primary a[href^='dashboard']"),
					bStartProgressBar : false
				});
			}
			else
			{
				fnLoadCurrentPage();
			}

			// Init external changes event
			$.address.externalChange(function(event)
			{
				fnLoadCurrentPage();
			});

		});

		$("input[type=submit], input[type=reset]").button();


		// start the idle timer plugin
		$.idleTimeout('#idletimeout', '#idletimeout a', {
			idleAfter: 1200,
			pollingInterval: 0,
			serverResponseEquals: 'OK',
			failedRequests: 0,
			onTimeout: function(){
				window.location.href = "j_spring_security_logout";
			},

			onIdle: function(){
				$.idleTimeout.options.onTimeout.call(this);
			}
		});

		$("#userName").text(pgsi.settings.user.userName.charAt(0).toUpperCase() + pgsi.settings.user.userName.slice(1));

		$(".suspicious").click(function(e) {
			e.preventDefault();
			pgsi.util.actiondialog.launchActionDialog(
					"dialogSARDetail",
					 pgsi.pages.sar.dialogSettings.dialogSARDetail(
						 $.pgsi.locale.get("commons.title.table.SAR"),
						 0,
						 ""
					 ));
		});

		// Set Correct links for reporting
		pgsi.util.page.reports.sar.fnInit($("nav.primary>ul.main-menu>li[data-url~=compliance]>ul.sub-menu>li[data-url~=sar-reports]>ul"));
		pgsi.util.page.reports.operation.fnInit($("nav.primary>ul.main-menu>li[data-url~=operation-reports]>ul"));

	});


</script>