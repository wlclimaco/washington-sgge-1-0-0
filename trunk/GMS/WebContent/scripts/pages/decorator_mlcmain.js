/**
 * @fileoverview Initializes decorator-specific functionality. In particular, it
 *               uses the sensus.settings.menuItem property to highlight the
 *               current menu item.
 *
 * @author Anke Doerfel-Parker
 */

// highlight the current menuitem
sensus.pages.decorator = {

	/** Send Action  */
	sendAction : function() {

		var sSearchValue = $("#search-text").val().trim();
		var sSearchType = $("#search-label").find("input").val();
		var sHash = "query=" + sSearchType + "|" + sSearchValue;
		var sSmartpointUrl = "smartpoint/ajax.list.action";
		var sProperty = sensus.widgets.datatable.fnGetEnumValue('PropertyEnum', sSearchType);

		// Clean URL
		$.address.parameter("query", 0);

		if (jQuery(location).attr('href').contains(sSmartpointUrl)) {

			$.address.parameter("iDisplayStart", 0);
			sSmartpointUrl += "?" + $.address.queryString() + "&" +sHash;

		} else {

			sSmartpointUrl += "?" + sHash;

		}

		var oSearch = new searchLight(null, [{'propertyEnum':sProperty, 'value':sSearchValue}]);
		var oRequest = new inquiryLightRequest(oSearch);
		//TODO
		//oRequest.inquiryLightRequest.pageSize = 2;
		var fnCallback = function(response){

			var iTotal = response.lights.length;
			if(iTotal == 0 || iTotal > 1){

				sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
					sUrl : sSmartpointUrl,
					bFilter : true

				}));

			} else {

				var nId = response.lights[0].id;
				sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
					sUrl : "lightDetail?id="+nId,
					bFilter : true

				}));

			}

		};

		$.sc.getJson("smartpoint/search.action", oRequest, false, fnCallback);

	},

	isValid : function(sValue) {
		return sValue.match(/[a-zA-Z0-9\-]/g) != null;
	}
};

$(document).ready(function() {

	// Search Bar
	$('#search-label').menuPlug({
		actionCallback : function(e) {

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

	/** Search Button  */
	$("#search-submit").click(function(e) {
		e.preventDefault();

		if (!$("#search-form-container").validationEngine('validateField', $("#search-text"))) {

			sensus.pages.decorator.sendAction();
			$("#search-text").val("");
			return false;

		}

	});

	/** Remove session filters and columns **/
	$("#sensus-menu").on('click','.alist', function(event) {
		event.preventDefault();
		$('#messaging-main').hide();
		sensus.util.session.removeSessionCustomize('filters');
		sensus.util.session.removeSessionCustomize('columns');
	});

	/** Send Action  */
	$("#search-text").keypress(function(event) {

		if (event.keyCode == '13') {

			if (!$("#search-form-container").validationEngine('validateField', $("#search-text"))) {

				sensus.pages.decorator.sendAction();
				$("#search-text").val("");
				return false;

			}

		}

	});

	/** Append Logo  */
	$(".logo h1").append(sensus.settings.tenantDescription);

	/** Set buttons  */
	$('.button').button();

	/** Set user name  */
	$("#user-name").text(uUserName);
});