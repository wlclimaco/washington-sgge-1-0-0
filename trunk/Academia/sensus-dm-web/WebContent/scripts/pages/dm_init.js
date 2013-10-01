$(document).ready(function() {

	// The actual service on URL
	var currentService = $.address.value().split("?")[0].replace("/", "").toUpperCase();

	// Set server settings and bring wrapper app
	var _loadApp = function (service) {

		sensus.settings.serviceType = service;

		head.js("thirdparty/jquery/styles/jquery.menu.css");
		head.js("thirdparty/jquery/styles/validationEngine.jquery.css");
		head.js("thirdparty/jquery/styles/calendrical.css");
		head.js("styles/global_styles.css");
		head.js("thirdparty/jquery/styles/chosen.css");
		head.js("thirdparty/jquery/styles/jquery.bubblepopup.v2.3.1.css");
		head.js("thirdparty/jquery/SlickGrid/slick.grid.css");
	//	head.js("thirdparty/jquery/SlickGrid/css/smoothness/jquery-ui-1.8.16.custom.css");
		head.js("thirdparty/jquery/SlickGrid/examples/examples.css");
		head.js("thirdparty/jquery/SlickGrid/controls/slick.pager.css");
		head.js("thirdparty/jquery/SlickGrid/controls/slick.columnpicker.css");
		head.js("styles/" + service.toLowerCase() + "_styles.css");

		$.ajax({
			url : 'setService?service=' + service,
			cache : false,
			method : 'GET',
			success : function (html) {

				$("#doc1 .general-content").html(html);
			}
		});
	};

	sensus.util.page.initProgressBar();

	// Get Settings
	sensus.pages.dm.getSettings(true);

	// When has not service selected
	if (!sensus.settings.serviceType || !currentService) {

		// Load services options
		$.ajax({
			type	: 'GET',
			url 	: './servicesOptions',
			async 	: false,
			success : function (html) {

				$("#doc1 .general-content").html(html);
			}
		});

		// Service links
		$("#doc1").delegate(".dashboardLinks li a", "click", function(e) {

			var service = $(this).attr("href");

			e.preventDefault();

			// set deep-link URL
			$.address.value(service);

			// Set service on head feature
			sensus.pages.dm.setHeadService(service);

			sensus.util.page.startGlobalProgressBar();

			_loadApp(service.toUpperCase());
		});

		sensus.util.page.stopGlobalProgressBar();

		if (sensus.settings.serviceType) {

			sensus.pages.dm.currentHeadService = sensus.settings.serviceType.toLowerCase();
		}

	} else {

		_loadApp(currentService);
	}

	$.address.externalChange(function(event) {

		if ($.address.value() != "/") {

			$.address.history(false);
			$.address.parameter("initialLoad", false);
			$.address.history(true);
		}

	});

});