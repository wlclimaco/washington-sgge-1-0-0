/**
 * @namespace sensus.pages.group
 * @description The init namespace for the Group Create Page.
 */

/**
 * @fileoverview Initializes the group page.
 * 
 * @author Cristiane Cobo
 */
$(document).ready(function($) {
	/*
	 * Set up most page components.
	 */
	sensus.util.page.stopProgressBar(1);
	sensus.util.page.initMessaging();
	
	/*
	 * Set up create group button.
	 */
	$("a.button").button();
	
    /*
	 * Validate the fields required at Create Group Page
	*/
		
	$("#create-group-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status) {
			
			if (status) {
				
				sensus.pages.group.ajaxCall(sensus.settings.createGroupUrl);
				
			}
		}
	});
	
	$("#update-group-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status) {
			
			if (status) {
				
				sensus.pages.group.ajaxCall(sensus.settings.updateGroupUrl);
				
			}
		}
	});
	
	sensus.util.page.stopProgressBar(0,true);
});