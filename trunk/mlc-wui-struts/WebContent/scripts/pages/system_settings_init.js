/**
 * @namespace     sensus.pages.systemsettings
 * @description   The init namespace for the Tag System Settings.
 * @fileoverview  Initializes the systemsettings page.
 * @author        Raphael Constantino
 *
 */
$(document).ready(function() {

	sensus.util.page.initMessaging();

	// Apply JQuery UI
	$(".button").button();

	var oTabs = $("#tabs");
		
	// Init tabs

		oTabs.tabs({
			show: function(event, ui) {

				$('.formError').remove();

				var oNote = $('.note');

				if (!oNote.hasClass('inserted')) {

					oNote.remove();
					oNote.addClass('inserted');
					oNote.insertBefore($('.stamp').eq(1));

				}
			}
		});
	
	$(".blankslate").hide();
	
});