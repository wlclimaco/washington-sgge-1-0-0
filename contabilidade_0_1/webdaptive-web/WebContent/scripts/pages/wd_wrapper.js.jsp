<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">

	$(document).ready(function() {

		var oPropertiesResponse = {};
		var oUserPropertiesResponse = {};

			// Get Settings from pre-loaded data
		<c:if test="${not empty response}">
			var oServerResponse = ${response};
			if(!$.qat.isNullOrUndefined(oServerResponse.uiProperties))
			{
				oPropertiesResponse.settings = oServerResponse.uiProperties;
			}

			if(!$.qat.isNullOrUndefined(oServerResponse.userContext))
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

		//qat.pages.sendsolv.fnLoadAppProperties(oPropertiesResponse, qat.settings.storage.app);
		//qat.pages.sendsolv.fnLoadUserProperties(oUserPropertiesResponse, qat.settings.storage.user);

		/**
		 * Load Simple page without active menu item
		 */

		$("body").on('click','.alist', function(event)
		{
			event.preventDefault();

			$submenu = $(this).parent().parent();
			var $this = $(this);

			$.qat.pageLoader.load({ url: $(this).attr("href"), $content: $("#load") });

		});

		// Initialize the progress bar dialog
		$.qat.progressBar.init();

		// Load remaining js files that can depend on internationalization
head.load("scripts/util/page.js","/qat-webdaptive/thirdparty/js/angular.js",
"/qat-webdaptive/thirdparty/js/ng-table.js",
"/qat-webdaptive/thirdparty/js/ng-table-export.src.js")

		// Wait for final js files and then finish the app initialization
		head.ready("qat_commons_table_1.0.js", function() {
			var fnLoadCurrentPage = function()
			{
			debugger
				var parameters 		= $.qat.pageLoader.getQueryString();
				var page 			= $.qat.pageLoader.currentPage();
				var url;

				// Remove unwanted slash
				if((!$.qat.isNullOrUndefined(page))){
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

				$.qat.pageLoader.load({
					url: url,
					$content: $("#load"),
					bUpdateUrl: false,
					bInitialLoad : false,
					bStartProgressBar : false
				});

			};

			// Load Page first time
			if(!$.qat.pageLoader.currentPage())
			{
				$.qat.pageLoader.load({
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

	//	$("#userName").text(qat.settings.user.userName.charAt(0).toUpperCase() + qat.settings.user.userName.slice(1));



	});


</script>