sensus.commons.search = {

		quickSearch : function()
		{

			if (!$("#search-form-container").validationEngine('validateField', $("#search-text")))
			{

				var sSearchValue = $("#search-text").val().trim();
				var sSearchType = $("#search-label").find("input").val();
				var sHash = "query=" + sSearchType + "|" + sSearchValue;
				var sSmartpointUrl = "light";
				var sProperty = sensus.widgets.datatable.fnGetEnumValue('PropertyEnum', sSearchType);

				/** Clean URL */
				$.address.parameter("query", null);

				if ( jQuery(location).attr('href').indexOf(sSmartpointUrl) != -1)
				{

					$.address.parameter("iDisplayStart", 0);
					sSmartpointUrl += "?" + $.address.queryString() + "&" +sHash;

				}
				else
				{

					sSmartpointUrl += "?" + sHash;

				}

				var oLightCriteria = new LightCriteria();

				if(sSearchType == 12)
				{
					oLightCriteria.poleId = sSearchValue;
				}
				else
				{
					oLightCriteria.flexnetId = sSearchValue;
				}

				if(sSearchType == 36 && !$.isNumeric(sSearchValue))
				{

						$("#search-text").validationEngine('showPrompt', $.sc.locale("search.validation.onlyLetterFlexnetId"), 'red', '', true);

				}
				else
				{
					var oRequest = new lightRequest(oLightCriteria);

					$.sc.quickSearch(oRequest, sSmartpointUrl);

				}

				$("#search-text").val("");

			}

		}

};

$(document).ready(function() {


	/**	add trim method to String class */
	if(typeof String.prototype.trim !== 'function')
	{
		String.prototype.trim = function()
		{
			return this.replace(/^\s+|\s+$/g, '');
		}
	}

	String.prototype.capitalize = function()
	{
		return this.charAt(0).toUpperCase() + this.slice(1);
	}

	$("body").on('click', '.remove', function (e)
	{
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

	 /** Init and change handlers */
	$.address.externalChange(function(event)
	{
		var sUrl = $.address.value();

		$.sc.getPage($.extend({}, oParam,
		{
			sUrl : sUrl.substr(1, sUrl.length),
			bInitialLoad : false
		}));
	});

	/**
	 * Load Simple page without active menu item
	 */

	$("body").on('click','.alist', function(event)
	{
		event.preventDefault();
		$.sc.getPage($.extend({}, oParam,
		{
			$element : $(this)
		}));
	});

	/**
	 * Load page when need filter with hash
	 */

	$('.afilter').live("click", function(event)
	{
		event.preventDefault();
		$.sc.getPage($.extend({}, oParam,
		{
			 $element : $(this),
			 bFilter : true
		 }));
	});

	/** Search Send Action  */
	$('#search-submit').bind("click", function(e)
	{

		e.preventDefault();
		sensus.commons.search.quickSearch();

	});

	$("#search-text").keypress(function(e)
	{

		if (e.keyCode == '13')
		{

			e.preventDefault();
			sensus.commons.search.quickSearch();

		}

	});

	/** Search type */
	$('#search-label').menuPlug({
		actionCallback : function(e)
		{

			var oActualItem = $(".fg-menu").find("#" + e);
			var sValActualItem = oActualItem.text();
			var sValActualType = oActualItem.find("input").val();
			$("#search-label").html('<span class="ui-icon ui-icon-triangle-1-s"></span><input id="search-type" value="' + sValActualType + '" type="hidden">' + sValActualItem);
			oActualItem.closest("ul").find("li.hide").removeClass("hide");
			oActualItem.parent().addClass("hide");
			return false;

		},
		content: $('#search-label').next().html(), // grab content from this page
		showSpeed: 400
	});

	/** Remove session filters and columns **/
	$("#sensus-menu").on('click','.alist', function(event)
	{
		event.preventDefault();
		$('#messaging-main').hide();
		sensus.util.session.removeSessionCustomize('COLUMNS');
		sensus.util.session.removeSessionCustomize('FILTERS');
	});

	var nTimeout = 600000;

	$(document).bind("idle.idleTimer", function()
	{
	  $("#load").empty().load('timeOut');
	});

	$.idleTimer(nTimeout);

	sensus.util.page.initProgressBar();

	$('.logo h1').text(sensus.settings.userContext.tenant.description);
	$('#version-number').text(sensus.settings.slcVersion);
	$('#build-number').text(sensus.settings.slcBuildVersion);
	$('#user-name').text(sensus.settings.userContext.userId);

	/**
	 *  If no page, call default page
	 */

	if (!location.hash.length)
	{
		var sDefaultUrl = "dashboard";
		$.address.value(sDefaultUrl);
	}

});