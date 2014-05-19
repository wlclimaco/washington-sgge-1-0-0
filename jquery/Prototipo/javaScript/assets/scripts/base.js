var $actionDialog;
var $actionConfirmCP;
var $actionLrpDialog;
var $dialog;
var checkedNum;
var cookieName = 'cpStatus';
var cookieOptions = {expires: 7, path: '/'};
var filtersActive = 0;

$(document).ready(function() {						   
	/* ===========================================
		Initialize
	 =========================================== */
		//INIT UI
		//Hide
		$('.reset-container, .sort-options, .view-options, #dialog-analyze, .missing-data, .status-viewport-loading, .spindown-child, #request-complete, .summary-container .summary, .filter-select, #processing-container, #system-messaging, #message-map, .messaging, .action-dialog, #dialog-map, .blankslate, #request-processing, .ui-state-error, .cp-title, #cp-active-container').hide();		
		$('.toggle.off').next(".collapse").hide();
		
});
