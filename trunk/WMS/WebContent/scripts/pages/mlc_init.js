$(document).ready(function() {

	//sensus.util.page.initMessaging();



	//Initiate Detail parameters
	//sensus.pages.mlc.getDeviceTypeParameters();

	$("body").on('click', '.remove', function (e) {
		e.preventDefault();
		$(this).parent().fadeOut("slow");
		return false;
	});


	var dToday = new Date();
	$("#current-year").html(dToday.getFullYear());

	var sDefaultUrl  = 'dashboard';
	var sContainer   = $('#load');
	var oParam       = sensus.commons.lib.ajax.param;

	sensus.commons.lib.ajax.$container = sContainer;
	sensus.commons.lib.ajax.$menuItem = $("#sensus-menu");

	/* Initialize common page elements */
	//sensus.util.page.initProgressBar();

	/**
	 *  If no page, call default page
	 */

	if (!location.hash.length) {

		$.address.value(sDefaultUrl);

	}

	/**
	 * Load Simple page without active menu item
	 */

	$("body").on('click','.alist', function(event) {

		event.preventDefault();
		$.sc.getPage($.extend({}, oParam, {
			$element : $(this)
		}));
	});

	/**
	 * Load page when need filter with hash
	 */

	$('.afilter').live("click", function(event) {
		 event.preventDefault();

		 sensus.commons.lib.ajax.do_load($.extend({}, oParam, {
			 $element : $(this),
			 bFilter : true
		 }));

	});

	 // Init and change handlers
	$.address.externalChange(function(event) {
		var sUrl = $.address.value();

		$.sc.getPage($.extend({}, oParam, {
			sUrl : sUrl.substr(1, sUrl.length)
		}));
	});

	var nTimeout = 600000;

	  $(document).bind("idle.idleTimer", function(){
		  $("#load").empty().load('include.sessionExpired.action');
	  });

	  $(document).bind("active.idleTimer", function(){

	  });

	  $.idleTimer(nTimeout);

	  $('#version-number').text(sensus.settings.DicionarioVersion);
	  $('#build-number').text(sensus.settings.DicionarioBuildVersion);

});