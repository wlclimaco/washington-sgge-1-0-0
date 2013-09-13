$(document).ready(function() {

	var _serviceType = sensus.settings.serviceType,
		_services = sensus.constants.services,
		_currentPage = $.fn.pageLoader.currentPage(),
		deviceListURL;

	// Get all devices Parameters and set to the properties
	sensus.pages.dm.getDeviceTypeParameters();

	// set device list URL
	deviceListURL = _serviceType.toLowerCase() + "list?device_type=" +
		sensus.settings.oDeviceTypeParameters.deviceTypePermissions[0].deviceType + "|";

	if (_serviceType == _services.electric.name) {

		deviceListURL = deviceListURL + "&lifecycle_state=" + "INSTALLED|";
	}

	$("#menu-smartpoint").attr("href", deviceListURL);

	sensus.util.session.remove();

	// Menu links
	$("#sensus-menu").on("click", "a", function(e) {

		var _this = $(this);

		e.preventDefault();

		$.fn.pageLoader.load(_this.attr("href"), $("#load"), _this);
	});

	// App links
	$("#doc1").on("click", ".alist", function(e) {

		e.preventDefault();

		$.fn.pageLoader.load($(this).attr("href"), $("#load"));
	});

	// Dialod links
	$("body").on("click", "> div.ui-dialog .alist", function(e) {

		e.preventDefault();

		$(this).parents("div.ui-dialog-content").dialog("close");

		$.fn.pageLoader.load($(this).attr("href"), $("#load"));
	});

	$.address.externalChange(function(event) {

		var _page = sensus.util.page;
		var _pageLoader = $.fn.pageLoader;
		var parameters = $.fn.pageLoader.queryString();
		var page = event.parameters[_pageLoader.pageParameter];
		var currentService = _pageLoader.currentService();
		var url;

		_pageLoader.parameter("initialLoad", false);

		if (!page || sensus.settings.serviceType.toLowerCase() != currentService) {

			window.location.href =  sensus.settings.appRoot + "/service";
			return;
		}

		if (parameters) {

			url = page + "?" + parameters;

		} else {

			url = page;
		}

		_pageLoader.load(url, $("#load"), null, null, false);
	});

	$("#search-label").menuPlug({
		actionCallback : function(id) {

			var $actualItem = $(".fg-menu").find("#" + id);

			$(".formError").remove();

			$("#search-label").html('<span class="ui-icon ui-icon-triangle-1-s">'
					+ '</span><input id="search-type" value="' + $actualItem.find("input").val() + '" type="hidden">'
					+ '<span>' + $actualItem.text() +'</span>');

			$actualItem.closest("ul").find("li.hide").removeClass("hide");
			$actualItem.parent().addClass("hide");

			return false;
		},
		content: $("#search-label").next().html(), // grab content from this page
		showSpeed: 400
	});

	// Show User name and System Settings link
	$("#user-name").text(uUserName);

	$(".user-settings li:first, #systemsettings, h2", "#hd-user").removeClass("hide-settings");

	$(".logo h2", "#hd-user").text(sensus.locale.get("main.page.manage_" + sensus.settings.serviceType.toLowerCase()));

	// Search field
	$("#search-submit").click(function(event) {

		event.preventDefault();

		sensus.pages.dm.fnQuickSearch();

		return false;
	});

	// Search field enter press event
	$("#search-text").keypress(function(event) {

		if (event.keyCode == "13") {

			sensus.pages.dm.fnQuickSearch();

			return false;
		}
	});

	// set timeout
	$(document).bind("idle.idleTimer", function() {

		$("#doc1").load("timeout.jsp");
	});

	$.idleTimer(sensus.settings.time.sessionTimeout);

	// Hint feature
	$("#load").on("focusin", ".input-clear", function() {

	    $(this).removeClass("input-clear").val("");

	});

	$(".ui-icon-closethick, #search-label, #search-text, #labelOptions a").live("click", function() {

		$(".formError").remove();
	});

	// Load Page
	$.fn.pageLoader.parameter($.fn.pageLoader.pageParameter, null);

	$.fn.pageLoader.load(_currentPage ? (_currentPage + "?" + $.address.queryString()) : "dashboard",
			$("#load"), $("#sensus-menu a[href^='" + (_currentPage || "dashboard") + "']"));

	// Check each 10 seconds the list long running process
	var fnAutoRefreshLRP = setInterval(function() {

		sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();

	}, sensus.settings.time.longRunningProcess);

	// Check each 5 minutes the RNI
	var fnAutoRefreshRNI = setInterval(function() {

		$.ajaxValidator.fnIsRniOn(true, sensus.pages.longrunningprocess.fnCheckRniCallback);

	}, sensus.settings.time.checkRni);
});