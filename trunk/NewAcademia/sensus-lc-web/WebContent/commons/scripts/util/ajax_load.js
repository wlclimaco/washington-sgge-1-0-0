sensus.commons.lib.ajax = {

	/* Config obj  */
	param : {
		$element : null,
		$container : $('#load'),
		sUrl : null,
		message : {
			bMessage : false,
			sMessage : null
		},
		bfilter : false,
		bInitialLoad : true,
		bDetailPage : false
	},

	/* Container to be reload */
	$container : null,

	/* Actual Element */
	that : null,

	/* The Url*/
	url : null,

	/* The menu container*/
	$menuItem : null,

	settings : (function() {
		return sensus.settings;
	}()),


	reloadSettings : function () {
		// Store Base Locale
		var oCacheSettings = sensus.settings;
		// Load New Settings
		$.ajaxSetup({async:false});
		$.getScript("settings.action");
		$.ajaxSetup({async:true});

		// Add new value to settings used as base to inheritance.
		sensus.commons.lib.ajax.settings = sensus.settings;

		sensus.commons.lib.ajax.settings.enums = oCacheSettings.enums;
		sensus.commons.lib.ajax.settings.userContext = oCacheSettings.userContext;

		// Get old Settings besides user preferences
		sensus.settings = $.extend({}, oCacheSettings, sensus.settings);

	},

	/**
	 * Method call and show new page.
	 * First set the address value to the actual url,
	 * and after ajax call clear all selected smartpoint on tables
	 */

	loadPage : function (param) {

		$('.formError').remove();

		sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

		var bTabs=sensus.commons.lib.ajax.url.match(/tabs/g);

		if(bTabs){

			var aUrl = sensus.commons.lib.ajax.url.split('.');
			var nUrlSize = aUrl.length;
			aUrl.splice(1,1);
			aUrl[(nUrlSize-3)] = "main";
			sensus.commons.lib.ajax.url = aUrl.join('.');

		} else {

			$.address.value(sensus.commons.lib.ajax.url);

		}

		$.ajax({
			url: sensus.commons.lib.ajax.url,
			method:'get',
			cache: false,
			async: false,
			success: function(html, status){

				sensus.commons.lib.ajax.$container.empty().html(html);
				if (status == "success") {

					if (param.message.bMessage){

						sensus.util.page.showMessage("messaging-main", param.message.sMessage , "confirm");

					}

				}
				/** If the url is a TABS **/
				if (bTabs) {
					sensus.commons.lib.ajax.loadTab($.extend({}, param, {
						$container_tabs : $('#tabs-content'),
						bTab : true
					}));
				}

			}

		});

		$.sc.closeActionDialog("#action-dialog");

	},

	/** load the TAB **/
	/**
	 * @param param
	 * 			object
	 */

	loadTab : function (param) {

		/** clear all checkbox selected **/
		sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

		sensus.commons.lib.ajax.url = param.sUrl;
		sensus.commons.lib.ajax.$container_tabs = param.$container_tabs;

		var sBaseUrl = $.address.baseURL().replace('list.action','');

		/** Set the URL **/
		$.address.value((sensus.commons.lib.ajax.url).replace(sBaseUrl,''));

		/** if is a TAB **/
		if (param.bTab == true) {

			var iSlashIndex = param.sUrl.indexOf('/');
			var aUrl = param.sUrl.slice(iSlashIndex + 1, param.sUrl.length);
			var aUrl = aUrl.split('?');

			$('.tabs').find('li').each(function(){

				var sHref = $(this).find('a').attr('href').split('/'),
					iHref = sHref.length;

				if(aUrl[0] == sHref[iHref-1]){

					$(this).find('a').addClass('active');

				}

			});

		}

		$.ajax({
			url: sensus.commons.lib.ajax.url,
			method:'get',
			cache: false,
			async: false,
			success: function(html, status) {
				/** fill the container **/
				sensus.commons.lib.ajax.$container_tabs.empty();
				sensus.commons.lib.ajax.$container_tabs.html(html);
				/** set messages **/
				if (status == "success") {
					if (param.message.bMessage) {
						sensus.util.page.showMessage("messaging-main", param.message.sMessage , "confirm");
					}
				} else {
					if (param.message.bMessage) {
						sensus.util.page.showMessage("messaging-main", param.message.sMessage , "error");
					}
				}
			}
		});

		if(param.fnCallBack){
			param.fnCallBack();
		}

		$.sc.closeActionDialog("#action-dialog");
		$.sc.closeActionDialog("#action-dialog-lrp");
	},

	/**
	 *
	 * @param param
	 * 				Obj with load config
	 */

	do_load : function(param) {

		var deferred = $.Deferred();
		$.sc.startProgressBar(null, true, deferred);

		$.when(deferred).then(function() {

			$("#dialog-map").remove();

			//If no url, get the url from the actual element
			if (param.sUrl == null){

				sensus.commons.lib.ajax.set_url(param.$element, param.bFilter);

			} else {

				sensus.commons.lib.ajax.url = param.sUrl;

			}
			//Set active menu item
			sensus.commons.lib.ajax.activeMenuItem();


			//Load Page
			sensus.commons.lib.ajax.loadPage(param);
		});


	},

	/**
	 * Active menu functionality.
	 * Clear all active items and then check if there is a menu to the url.
	 */

	activeMenuItem : function() {
		var $menuItem = sensus.commons.lib.ajax.$menuItem;
		$menuItem.find(".active").removeClass("active");

		if (sensus.commons.lib.ajax.url){

			$menuItem.find("a").each( function() {

	            if (sensus.commons.lib.ajax.url.indexOf($(this).attr('href').split('/')[0])) {

	            	$(this).addClass("active");
	                return false; // break
	            }

	    	});

		}

	},

	/**
	 * Set the url from an element
	 * @param that
	 * 				The element to get the url.
	 * @param bFilter
	 * 				Boolean to check if the url has a hash object to filter.
	 */

	set_url : function(that,bFilter) {

		var url = null;
		var appRoot = sensus.settings.appRoot.split("/")[1];

		if (bFilter){

			if ( $.browser.msie && $.browser.version.slice(0,3) == '7.0' ) {

				var p = $(that).attr("href").split('#',2);
				var t = p[0].substring($(that).attr('href').indexOf(sensus.settings.appRoot));
				var x = t.split(appRoot + '/',2);
				sensus.commons.lib.ajax.url = x[1]+'#'+p[1];

			} else {

				sensus.commons.lib.ajax.url = $(that).attr("href");
			}

		} else {

			url = $(that).attr("href");
			sensus.commons.lib.ajax.url = url;

		}

	}

};