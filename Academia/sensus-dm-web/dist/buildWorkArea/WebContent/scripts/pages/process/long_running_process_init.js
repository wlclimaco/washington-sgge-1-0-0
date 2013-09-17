/**
 * @fileoverview Initializes Long Running Processes Bar.
 */
$(document).ready(function() {

	// Bubble
	$('.bubble').CreateBubblePopup()
		.mouseover(function() {

			//remove title
			$this = $(this);
			$.data(this, 'value', $this.attr('value'));
			$this.removeAttr('value');

			//show the bubble pop-up with new options
			$(this).ShowBubblePopup({
				innerHtml: $.data(this, 'value'),
				width: '200px',
				themeMargins: {
					total: '5px',
					difference: '5px'
				},
				innerHtmlStyle: {
					color: ($(this).attr('id') != 'azure' ? '#000000' : '#333333'),
					'text-align':'center'
				},
				themeName: 	$(this).attr('rel'),
				themePath: 	'images/jquerybubblepopup-theme/'
			});

		}).mouseout(function() {

			// add title back
			$this = $(this);
			$this.attr('value', $.data(this, 'value'));
		});

	$.ajaxValidator.fnIsRniOn(true, sensus.pages.longrunningprocess.fnCheckRniCallback);
	sensus.pages.longrunningprocess.longRunningProcessSystemMessaging();

	// Open processing details
	$("#system-messaging").delegate("#request-processing, #request-complete", "click", function(e) {

		var $lrpDialog = $("#action-dialog-lrp");

		e.preventDefault();

		if ($lrpDialog.parent().hasClass("action-dialog")) {

			$lrpDialog.dialog("open");

		} else {

			sensus.util.page.startProgressBar();

			$.ajax({
				url: 'process/recentsRequest',
				type: 'GET',
				async: false,
				success: function (html) {

					$lrpDialog.html(html).dialog({

						title: sensus.locale.get("commons.pages.recentRequests"),

						width: 1024,

						minheight: 600,

						height: 600,

						modal : true,

						dialogClass : 'action-dialog',

						beforeClose : function() {

							sensus.widgets.datatable.clearSelectsFunction.call(
									sensus.pages.longrunningprocess.lrpTable);
						}
					});

					$lrpDialog.dialog("option", "open", function() {

						sensus.widgets.datatable.reloadTable(sensus.pages.longrunningprocess.lrpTable);
					});

					sensus.util.page.stopProgressBar();
				}
			});
		}
	});

	// TODO move to dialog action JS
	// Close Dialog when click on links
	$('body').delegate('#process-table a.alist', 'click', function() {
		$('#action-dialog-lrp').dialog('close');
	});
});