<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
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
$('#save').click(function() {
		var test = $('#fotos').val()
		a =  test.split('|')
		x=0;

		var fotos = [];
		while( x < a.length)
		{
		if((a[x] != ' ')&&(a[x] != ''))
			{
				var foto = {};
				foto.cdfoto= parseInt(a[x], 10);;
				fotos.push(foto);
			}
			x++;
		}
		var fnCallBack = function(data){
		alert('11');

			if (data.operationSuccess){
			//	sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
			}
		};
		var oRequest = new ExercicioRequest(1,"cc","aaa",1,fotos);
console.log(oRequest);
debugger
		$.sc.getJson('api/exercicio/insert',oRequest, false, fnCallBack);



		//{"grupomusculars":[{"cdgrmusc":0,"musculo":"55","dsgrmusc":"55","createdate":"2014-03-27-00-00-00-000","createuser":"superuser","tenantid":1,"userid":1}]}
});
		//INIT UI
		//Hide
		$('.reset-container, .sort-options, .view-options, #dialog-analyze, .missing-data, .status-viewport-loading, .spindown-child, #request-complete, .summary-container .summary, .filter-select, #processing-container, #system-messaging, #message-map, .messaging, .action-dialog, #dialog-map, .blankslate, #request-processing, .ui-state-error, .cp-title, #cp-active-container').hide();
		$('.toggle.off').next(".collapse").hide();
		$('#tabs').tabs();
		$('#system-messaging').pinFooter();
		$('.datepicker').datepicker();
		$('.datepicker-day').datepicker({ dateFormat: 'dd'});

		//INIT Map toggle icons
		$("#views").buttonset();
		$('.map').button( "option", "icons", {primary:'ui-icon-map'});
		$('.list').button( "option", "icons", {primary:'ui-icon-list'});

		$( ".arrow-button-down" ).button({
            icons: {
                primary: "ui-icon-triangle-1-s"
            }
		});

		var buttonsets = $("#switch, #switch-map, .switch").buttonset();
		// INIT href buttons
		$("a.button , input:submit").button();

		// INIT sortable
		$( ".sortable" ).sortable({
			connectWith: ".connected-sortable"
		}).disableSelection();

    	// INIT I had to rename this function to fix a conflict with auto complete.
		$('#actions-button, #saved-searches').menuPlug({
			select: function(event, ui) {
				alert(ui.item);
			},
			content: $('#actions-button').next().html(), // grab content from this page
			backLink: false,
			//flyOut: true,
			showSpeed: 400
		});

		$('#search-label').menuPlug({
			select: function(event, ui) {
				alert(ui.item);
			},
			content: $('#search-label').next().html(), // grab content from this page
			showSpeed: 400
		});


		//Check for CP cookie
		if($.cookie(cookieName)=="true"){
			$('#system-messaging').show();
			$('.cp-title').slideDown();
			}

		//INIT DIALOG Map
		var $mapDialog = $("#dialog-map").dialog({
				autoOpen: false,
				//title: 'Updating Results...',
				modal: true,
				width: 700,
				height: 400,
				dialogClass: 'map-dialog',
				resizable: true,
				open: function(){closedialog = 1;$(document).bind('click', overlayclickclose);},
				focus: function(){closedialog = 0;},
				close: function(){$(document).unbind('click');},
			});
			//close map if click outside dialog
			var closedialog;
			function overlayclickclose() {
				if (closedialog) {
					$mapDialog.dialog('close');
					}
				//set to one because click on dialog box sets to zero
				closedialog = 1;
				}

		//INIT Loading Dialog
		$dialog = $("#loading").dialog({
				autoOpen: false,
				//title: 'Updating Results...',
				modal: false,
				dialogClass: 'loading',
				resizable: false
			});

		//INIT Create CP Diolog
        $actionConfirmCP = $("#confirm-cp").dialog({
				autoOpen: false,
				title: 'Action - Confirm Critical Peak Event',
				width: 500,
				minheight: 150,
				modal: true,
				buttons: {
					'Trigger Critical Peak Event': function() {
							$(this).dialog('close');
							showMessage("messaging-main","Critical Peak Event Initiated Jul 30, 2010 06:10 pm","confirm");
							$('.row-title').addClass('icn-cp-event');
							$('.row-title').attr('title', 'Critical Peak event initiated 07/30/2010');
							$('#list-table').find(':checkbox').attr('checked', false);
							$('.cp-title').slideDown();
							$('#cp-active-container').show();
							$.cookie(cookieName, true, cookieOptions);
					},
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog',
				resizable: false
			});


		//INIT Filters Dialog
        $actionCustomFilters = $("#custom-filters").dialog({
				autoOpen: false,
				title: 'Customize Filters',
				width: 800,
				minheight: 150,
				modal: true,
				buttons: {
					'Set Filters': function() {
							$(this).dialog('close');
							showMessage("messagingMain","Filters Updated Successfully","confirm");
							$('.cp-title').slideDown();
					},
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog',
				resizable: false
			});

		//INIT Columns Dialog
        $actionCustomColumns = $("#custom-columns").dialog({
				autoOpen: false,
				title: 'Customize Columns',
				width: 800,
				minheight: 150,
				modal: true,
				buttons: {
					'Set Columns': function() {
							$(this).dialog('close');
							showMessage("messagingMain","Columns Updated Successfully","confirm");
							$('.cp-title').slideDown();
					},
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog',
				resizable: false
			});



		//INIT Create Group Diolog
        var $actionDialogCreate = $("#action-dialog-create").dialog({
				autoOpen: false,
				title: 'Action - Create Control Group',
				width: 500,
				minheight: 150,
				modal: true,
				buttons: {
					'Create Control Group': function() {
						if($("#control-group-name-create").val().length > 0 ) {
							$(this).dialog('close');
							showMessage("messaging-main","Control Group <strong>"+ $("#control-group-name-create").val() +"</strong> created successfully.","confirm");
						}
						else {
							showMessage("create-group-messaging","Please enter a value for Group name.","error");
							$("#control-group-name-create").addClass("error");
							return false;
							}

					},
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog',
				resizable: false
			});

		//INIT Create Save Diolog
        var $actionDialogSave = $("#action-dialog-save").dialog({
				autoOpen: false,
				title: 'Save Your Search',
				width: 600,
				minheight: 150,
				modal: true,
				buttons: {
					'Save': function() {
						if($("#saved-search-name").val().length > 0 ) {
							$(this).dialog('close');
							showMessage("messaging-main","Saved Search <strong>"+ $("#saved-search-name").val() +"</strong> created successfully.","confirm");
						}
						else {
							showMessage("save-form-messaging","Please enter a value for Search Name","error");
							$("#saved-search-name").addClass("error");
							return false;
							}

					},
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog',
				resizable: false
			});

		//INIT Add to Group
		$actionDialog = $("#action-dialog").dialog({
			autoOpen: false,
			title: 'Action - Add to Control Group',
			width: 500,
			minheight: 150,
			modal: true,
			buttons: {
				'Add SmartPoint to Group': function() {
					//TODO: figure out why these validation classes are not working here
					 /*$("#createGroupForm").validate({
						rules: {
							controlGroupNameCreate: "required"
							}
						});
					 */
					if($("#add-group-form .ui-autocomplete-input").val().length > 0 ) {
						$(this).dialog('close');
						$actionLrpDialog.dialog('open');
						//= $("#loading").dialog

					}
					else {
							showMessage("add-group-messaging","Please name your Control Group","error");
							$("#add-group-form .ui-autocomplete-input").addClass("error");
							return false;
						}
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog',
			resizable: false
		});


		//INIT Add to tag
		$actionTagDialog = $("#action-tag-dialog").dialog({
			autoOpen: false,
			title: 'Action - Add Tag',
			width: 500,
			minheight: 150,
			modal: true,
			buttons: {
				'Add Tag': function() {
					if($("#add-tag-form .ui-autocomplete-input").val().length > 0 ) {
						$(this).dialog('close');
					}
					else {
							showMessage("add-tag-messaging","Please enter a value for Tag","error");
							$("#addTagForm .ui-autocomplete-input").addClass("error");
							return false;
						}
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog',
			resizable: false
		});


		//INIT Add to Schedule
		$actionScheduleDialog = $("#action-schedule-dialog").dialog({
			autoOpen: false,
			title: 'Action - Add to Offset Schedule',
			width: 500,
			minheight: 150,
			modal: true,
			buttons: {
				'Apply Schedule to SmartPoint(s)': function() {
					//TODO: figure out why these validation classes are not working here
					 /*$("#createGroupForm").validate({
						rules: {
							controlGroupNameCreate: "required"
							}
						});
					 */
					if($("#add-schedule-form .ui-autocomplete-input").val().length > 0 ) {
						$(this).dialog('close');
						$actionLrpDialog.dialog('open');

						//showMessage("messagingMain","<strong>11</strong> SmartPoints successfully added to Schedule <strong>Rose Garden</strong>","confirm");
					}
					else {
							showMessage("add-schedule-messaging","Please select a valid Schedule","error");
							$("#add-schedule-form .ui-autocomplete-input").addClass("error");
							return false;
						}
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog',
			resizable: false
		});

		//INIT Add to Event Schedule
		$actionEventScheduleDialog = $("#action-event-schedule-dialog").dialog({
			autoOpen: false,
			title: 'Action - Add to Event Schedule',
			width: 500,
			minheight: 150,
			modal: true,
			buttons: {
				'Apply Schedule to SmartPoint(s)': function() {
					//TODO: figure out why these validation classes are not working here
					 /*$("#createGroupForm").validate({
						rules: {
							controlGroupNameCreate: "required"
							}
						});
					 */
					if($("#add-event-schedule-form .ui-autocomplete-input").val().length > 0 ) {
						$(this).dialog('close');
						$actionLrpDialog.dialog('open');

						//showMessage("messagingMain","<strong>11</strong> SmartPoints successfully added to Schedule <strong>Rose Garden</strong>","confirm");
					}
					else {
							showMessage("add-event-schedule-messaging","Please select a valid Schedule","error");
							$("#add-event-schedule-form .ui-autocomplete-input").addClass("error");
							return false;
						}
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog',
			resizable: false
		});

		//INIT remove to Schedule
		$actionScheduleDialogRemove = $("#action-schedule-dialog-remove").dialog({
			autoOpen: false,
			title: 'Action - Remove Schedule',
			width: 500,
			minheight: 150,
			modal: true,
			buttons: {
				'Remove Schedule': function() {
					//TODO: figure out why these validation classes are not working here
					 /*$("#createGroupForm").validate({
						rules: {
							controlGroupNameCreate: "required"
							}
						});
					 */
					if($("#remove-schedule-form .ui-autocomplete-input").val().length > 0 ) {
						$(this).dialog('close');
						showMessage("messaging-main","<strong>11</strong> SmartPoints successfully removed from Schedule <strong>Rose Garden</strong>","confirm");
					}
					else {
							showMessage("remove-schedule-messaging","Please select a valid Schedule","error");
							$("#remove-schedule-form .ui-autocomplete-input").addClass("error");
							return false;
						}
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog',
			resizable: false
		});


		//INIT Ligting Control
		$actionLrpDialog = $("#lrp-dialog-container").dialog({
			open: function(event, ui) {
				//LRP Confirm Events
				$('.descriptive-button-row .monitor').click(function() {
					$actionLrpDialog.dialog('close');
					$("#request-complete, .missing-data").hide();
					$('#system-messaging').slideDown();
					$('#request-processing').show('blind',1000,function(){$('#request-processing .count').show('blind',1000)});
					showMessage("messaging-main",$('.descriptive-button-row .monitor').attr('title'),"confirm");

					$('.status-viewport-loading').show();
					setTimeout ('$(".status-viewport-loading").hide();$("#request-processing").hide();$("#request-complete").show();$(".missing-data").show();', 12000, function(){} );
					clearSelected();
					return false;
				});
				$(".descriptive-button-row .dismiss").click(function() {
					$actionLrpDialog.dialog('close');
					showMessage("messaging-main",$('.descriptive-button-row .monitor').attr('title'),"confirm");
					clearSelected();

					return false;
				});
				$(".descriptive-button-row .cancel").click(function() {
					$actionLrpDialog.dialog('close');
					return false;
				});
			},

			autoOpen: false,
			title: 'It may take longer than 90 seconds to complete your request',
			width: 500,
			minheight: 150,
			modal: true,
			dialogClass: 'action-dialog',
			resizable: false
		});


		//INIT Ligting Control
		var $actionDialogLighting = $("#action-dialog-lights").dialog({
			autoOpen: false,
			title: 'Action - Turn Lights On/Off',
			width: 500,
			minheight: 150,
			modal: true,
			buttons: {
				'Send Lighting Command': function() {
					//if($("#lightingControlOverride").val().length > 0 ) {
						$(this).dialog('close');
						$('#system-messaging').slideDown();
						$('#request-processing').show('blind',1000,function(){$('#request-processing .count').show('blind',1000)});
						showMessage("messaging-main","We have received your request.  View 'Recent Requests' or 'Reqeusts History' for event confirmation.","confirm");
					//}
					//else {
					//		showMessage("turnOnOffLightsFormMessaging","Please enter your control code","error");
					//		$("#turnOnOffLightsForm #lightingControlOverride").addClass("error");
					//		return false;
					//	}
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog',
			resizable: false
		});

		//INIT Processing Details
		//INIT details
		var $dialogProcessingDetails = $("#processing-container").dialog({
			autoOpen: false,
			title: 'Recent Requests',
			width: 960,
			minheight: 550,
			modal: false,
			dialogClass: 'action-dialog',
			resizable: true
		});

		// action/event smartpoint summary
		var $dialogProcessingSummaryDetails = $("#processing-summary-container").dialog({
			autoOpen: false,
			title: 'Request Summary',
			width: 960,
			minheight: 550,
			modal: false,
			dialogClass: 'action-dialog',
			resizable: true
		});

		// action/event smartpoint list
		var $dialogProcessingListDetails = $("#processing-list-container").dialog({
			autoOpen: false,
			title: 'SmartPoint List',
			width: 960,
			minheight: 550,
			modal: false,
			dialogClass: 'action-dialog',
			resizable: true
		});

		// action/event smartpoint error list
		var $processingListErrorContainer = $("#processing-list-error-container").dialog({
			autoOpen: false,
			title: 'SmartPoint Errors List',
			width: 960,
			minheight: 550,
			modal: false,
			dialogClass: 'action-dialog',
			resizable: true
		});


		// read only action
		var $dialog_read_only_action = $("#action-read-only-action").dialog({
			autoOpen: false,
			title: 'System IQ Action',
			width: 790,
			minheight: 400,
			modal: true,
			dialogClass: 'action-dialog',
			resizable: true
		});

		// read only action TOU
		var $dialog_read_only_action_TOU = $("#action-read-only-action-TOU").dialog({
			autoOpen: false,
			title: 'System IQ Action',
			width: 790,
			minheight: 400,
			modal: true,
			dialogClass: 'action-dialog',
			resizable: true
		});
		// read only event
		var $dialog_read_only_event = $("#action-read-only-event").dialog({
			autoOpen: false,
			title: 'System IQ Scheduled Event',
			width: 790,
			minheight: 400,
			modal: true,
			dialogClass: 'action-dialog',
			resizable: true
		});


		//INIT combobox
		(function($) {
			$.widget("ui.combobox", {
				_create: function() {
					var self = this;
					var select = this.element.hide();
					var input = $("<input>")
						.insertAfter(select)
						.focus(function() {
							if(this.value == select.find("option:selected").text())
								this.value=null;
							})
						.autocomplete({
							source: function(request, response) {
								var matcher = new RegExp(request.term, "i");
								response(select.children("option").map(function() {
									var text = $(this).text();
									if (this.value && (!request.term || matcher.test(text)))
										return {
											id: this.value,
											label: text.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + $.ui.autocomplete.escapeRegex(request.term) + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<strong>$1</strong>"),
											value: text
										};
								}));
							},
							delay: 0,
							change: function(event, ui) {
								if (!ui.item) {
									// remove invalid value, as it didn't match anything
									$(this).val("");
									return false;
								}
								select.val(ui.item.id);
								self._trigger("selected", event, {
									item: select.find("[value='" + ui.item.id + "']")
								});

							},
							minLength: 0
						})
					.addClass("ui-widget ui-widget-content ui-corner-left");
					input.val( $(select).find("option:selected").text());

					$("<button>&nbsp;</button>")
					.attr("tabIndex", -1)
					.attr("title", "Show All Items")
					.insertAfter(input)
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					}).removeClass("ui-corner-all")
					.addClass("ui-corner-right ui-button-icon")
					.click(function() {
						// close if already visible
						if (input.autocomplete("widget").is(":visible")) {
							input.autocomplete("close");
							return;
						}
						// pass empty string as value to search for, displaying all results
						input.autocomplete("search", "");
						input.focus();
						return false;
					});
				}
			});

		})(jQuery);

		$(function() {
			$(".combobox").combobox();
		});


		//INIT combobox
		(function($) {
			$.widget("ui.listcombobox", {
				_create: function() {
					var self = this;
					var select = this.element.hide();
					//TODO TURN THIS INTO AN ATTRIBUTE OF THE WIDGET
					var linklength = 6;
					var linklist = "";
					//change first option to show remaining items
					select.children('option')[0].text = select.find('option').length - linklength + " more..."
					select.children('option').each(function(index) {
								if(index > 1 && index <= linklength)
									linklist += '<li class="checkbox"><input type="checkbox"> ' + $(this).text() + '</li>';
									//todo remove listed from select
									//if list is shorter than linklength don't show autocomplete
								  });
					var list = $("<ul/>")
						.insertBefore(select)
						.append(linklist)

					list.addClass("ui-listcombobox");


					var input = $("<input>")
						.insertAfter(select)
						.focus(function() {
							if(this.value == select.find("option:selected").text())
								this.value=null;
							})
						.autocomplete({
							source: function(request, response) {
								var matcher = new RegExp(request.term, "i");
								response(select.children("option").map(function() {
									var text = $(this).text();
									if (this.value && (!request.term || matcher.test(text)))
										return {
											id: this.value,
											label: text.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + $.ui.autocomplete.escapeRegex(request.term) + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<strong>$1</strong>"),
											value: text
										};
								}));
							},
							delay: 0,
							change: function(event, ui) {
								if (!ui.item) {
									// remove invalid value, as it didn't match anything
									$(this).val("");
									return false;
								}
								select.val(ui.item.id);
								self._trigger("selected", event, {
									item: select.find("[value='" + ui.item.id + "']")
								});

							},
							minLength: 0
						})
					.addClass("ui-widget ui-widget-content ui-corner-left");
					input.val( $(select).find("option:selected").text());

					$("<button>&nbsp;</button>")
					.attr("tabIndex", -1)
					.attr("title", "Show All Items")
					.insertAfter(input)
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					}).removeClass("ui-corner-all")
					.addClass("ui-corner-right ui-button-icon")
					.click(function() {
						// close if already visible
						if (input.autocomplete("widget").is(":visible")) {
							input.autocomplete("close");
							return;
						}
						// pass empty string as value to search for, displaying all results
						input.autocomplete("search", "");
						input.focus();
						return false;
					});
				}
			});

		})(jQuery);

		$(function() {
			$(".listcombobox").listcombobox();
		});

		//TABLE FILTER ACTIONS

		/*remove all data from table
		$("tr").hide();
		$("#list").hide();
		$(".tag-container li").hide();
		updateStatus = function() {
		if ($('#filterAlerts:checked').length == 1) {
			$("#list").show();
			$("#blankslate").hide();
			$("tr.alerts").show();
			$(".tag-container li#tag-alert").show();
		}
		else
		{
			$("#list").hide();
			$("#blankslate").show();
			$("tr.alerts").hide();
			$(".tag-container li#tag-alert").hide();
			}
		if ($('#filterWarning:checked').length ==1) {
			$("#list").show();
			$("#blankslate").hide();
			$("tr.warning").show();
			$(".tag-container li#tag-warning").show();
		}
		else
		{
			$("#list").hide();
			$("#blankslate").show();
			$("tr.warning").hide();
			$(".tag-container li#tag-warning").hide();
			}
		// state:warning
		// all
		};

		updateStatus();

		*/

		/* ===========================================
		events
	 	=========================================== */
		$(".close-info").click(function() {
			return false;
		});
		$(".add-new-group").click(function() {
			$('#add-group-form .add-group-control').toggle();
			return false;
		});

		//sort prompt
		$('th.sort, .button-action-expand').hover(
			function () {
				$('div', this).stop(true, true).delay(200).slideDown(200);
			},
			function () {
				$('div', this).stop(true, true).slideUp(200);
		});

		//Trigger LRP on click
		$(".processing-action").click(function(e) {
			e.preventDefault();
			$actionLrpDialog.dialog('open');
		});

		//Save Search Dialog
		$(".save, .save-nostyle").click(function(e) {
			e.preventDefault();
			$actionDialogSave.dialog('open');
		});


		//Oncheck change for Filters
		$('.filter-input .checkbox input').change(function() {
			$('.status-viewport-loading').show();
			setTimeout ('$(".status-viewport-loading").hide();$("#request-processing").hide();', 6000, function(){} );
			//show filter reset
			if(filtersActive == 0)
			{
				 $('.reset-container').show('blind',1000);
				 filtersActive ++;
			}
		});


		//set loading height
		//$(".status_viewport .status_viewport_loading ").css('height', $(window).height()-300 + "px");
		$(".status-viewport .status-viewport-loading ").css('height', $(".status-viewport").height() + "px");

		//set graph to same height as annotation panel
		$("#container").css('height', ($(".annotation").height() - 20 + "px"));
		//dynamically reset

		//Open Dialog
		$("#create-group").click(function() {
			$actionDialogCreate.dialog('open');
			clearFormElements("create-group-form");
			return false;
		});

		//Open processing details
		$("#request-processing, #request-complete").click(function(e) {
			e.preventDefault();
			$dialogProcessingDetails.dialog('open');
		});

		//opens action/event summary
		$(".request-summary").click(function(e) {
			e.preventDefault();
			$dialogProcessingSummaryDetails.dialog('open');
		});

		//opens action/event list
		$(".request-list").click(function(e) {
			e.preventDefault();
			$dialogProcessingListDetails.dialog('open');
		});

		//opens action/event error list
		$(".request-list-error").click(function(e) {
			e.preventDefault();
			$processingListErrorContainer.dialog('open');
		});

		//opens action/event list
		$(".request-readonly-action").click(function(e) {
			e.preventDefault();
			$dialog_read_only_action.dialog('open');
		});
		//opens action/event TOU list
		$(".request-readonly-action-TOU").click(function(e) {
			e.preventDefault();
			$dialog_read_only_action_TOU.dialog('open');
		});
		//opens read only event
		$(".request-readonly-event").click(function(e) {
			e.preventDefault();
			$dialog_read_only_event.dialog('open');
		});


		//Toggle Filters
		$(".toggle").click(function() {
			$(this).next(".collapse").toggle('blind',null,500);
			if($(this).hasClass('on')){
			$(this).switchClass('on', 'off', 500);
			}
			else{
				$(this).switchClass('off', 'on', 500);
			}
			return false;
		});

		//Toggle summary
		$(".summary-toggle").click(function() {
			$(this).next(".summary").toggle('blind',null,500);
			if($(this).text().toLowerCase() == "view summary"){
				$(this).html("close summary");
				}
			else {
				$(this).html("view summary");
				}

			return false;
		});

		//Spindown
		$('.spindown').click(function(e) {
			e.preventDefault();
			$(this).parent().next('.spindown-child').toggle('blind',null,500);
			if($('span', this).hasClass('ui-icon-triangle-1-e')){
				$('span', this).switchClass('ui-icon-triangle-1-e', 'ui-icon-triangle-1-s', 500);
			}
			else{
				$('span', this).switchClass('ui-icon-triangle-1-s','ui-icon-triangle-1-e', 500);
			}
		});


		$('.remove, .remove-pin').click(function(e) {
			e.preventDefault();
			$(this).parent().fadeOut("slow");
		});

		$('.remove-dialog').click(function(e) {
			e.preventDefault();
			var dialog_text = $(this).attr("title");
			var button_text = $(this).attr("rel");
			var dialog_buttons = {};
				dialog_buttons[button_text] = function(){
						$(this).dialog( "close" );
						$(this).parents("tr:first").remove();
						 }
				dialog_buttons['Cancel'] = function(){
					 	$(this).dialog('close');
					 }

			var $remove_dialog = $('<div class="action-dialog"></div>')
			.html(dialog_text)
			.dialog({
				autoOpen: false,
				title: 'Remove Confirm',
				resizable: false,
				modal: true,
				buttons: dialog_buttons
			});
			$remove_dialog.dialog('open');
		});

		$('.delete-dialog').click(function(e) {
			e.preventDefault();
			var dialog_text = $(this).attr("title");
			var $delete_dialog = $('<div class="action-dialog"></div>')
			.html(dialog_text)
			.dialog({
			autoOpen: false,
			title: 'Delete Confirm',
			resizable: false,
			modal: true,
			buttons: {
					"Delete": function() {
						$(this).dialog( "close" );
						$(this).parents("tr:first").remove();
					},
					Cancel: function() {
						$( this ).dialog( "close" );
					}
				}
			});

			$delete_dialog.dialog('open');
		});


		$('.cancel-cp-event').click(function(e) {
			e.preventDefault();
			cancelCPEvent();
		});

		//Select actions
		$('.select-page').click(function () {
				$(this).parents('table:eq(0)').find(':checkbox').attr('checked', this.checked);
				$('#actions .message').removeClass("ui-state-error");
				$('#actions .message').addClass("ui-state-highlight");
				$('#actions .filter-select').hide();
			});
		$('a.select-page').click(function (e) {
				e.preventDefault();
				$('#list-table').find(':checkbox').attr('checked', true);
				$('.checked-count').text('15');
				$('#actions .message').removeClass("ui-state-error");
				$('#actions .message').addClass("ui-state-highlight");
				$('#actions .filter-select').show();
			});
		$('.select-all').click(function (e) {
				e.preventDefault();
				$('#list-table').find(':checkbox').attr('checked', true);
				$('.checked-count').text('840');
				$('#actions .message').removeClass("ui-state-error");
				$('#actions .message').addClass("ui-state-highlight");
				$('#actions .filter-select').show();
			});

		$('.select-none').click(function (e) {
				e.preventDefault();
				clearSelected();
			});

		//lock toggle
		$('#lock').click(function (e) {

				$('#lock-icon').toggleClass('unlocked locked');
				//$('#lock-icon').removeClass('unlocked');
				//$('#lock-icon').addClass('locked');
		});

		//Get total of selected checkboxes
		$('#list-table input:checkbox').click(function () {
			$('.checked-count').text($("#list-table input:checkbox:checked").length);
			});


		//Show custom filter overlay
		$('#custom-filter-action').click(function(e) {
			e.preventDefault();
			$actionCustomFilters.dialog('open');
		});
		//Show custom cols overlay
		$('.custom-column-action').click(function(e) {
			e.preventDefault();
			$actionCustomColumns.dialog('open');
		});

		//Show map detail
		$('.map-col a').click(function () {
			$mapDialog.dialog('open');
			return false;
		});

		//Toggle Map/List
		$('.map').click(function() {
			$('#map').show()
			$('#list').hide()
		});
		$('.list').click(function() {
			$('#map').hide()
			$('#list').show()
		});


		/* ===========================================
		validation
	 	=========================================== */
/* ===========================================
		functions
	 =========================================== */

	//display more actions dialog call comes from jquery.menu.js
		function launchActionDialog(actionId) {
		   //Add group
		   if("add-group"==actionId) {
			  clearFormElements('add-group-form');
			  $actionDialog.dialog('open');
			  $('#list-table').find(':checkbox').attr('checked', false);
		  }
		   if("add-tag"==actionId) {
			  clearFormElements('add-tag-form');
			  $actionTagDialog.dialog('open');
			  $('#list-table').find(':checkbox').attr('checked', false);
		  }
		   if("apply-schedule"==actionId) {
			  clearFormElements('add-schedule-form');
			  $actionScheduleDialog.dialog('open');
			  $('#list-table').find(':checkbox').attr('checked', false);
		  }
		   if("apply-event-schedule"==actionId) {
			  clearFormElements('add-event-schedule-form');
			  $actionEventScheduleDialog.dialog('open');
			  $('#list-table').find(':checkbox').attr('checked', false);
		  }
		   if("remove-schedule"==actionId) {
			  clearFormElements('remove-schedule-form');
			  $actionScheduleDialogRemove.dialog('open');
			  $('#list-table').find(':checkbox').attr('checked', false);
		  }
		   if("trigger-cp"==actionId) {
			  $actionConfirmCP.dialog('open');
		  }
		   if("cancel-cp"==actionId) {
			   cancelCPEvent();
		  }
		   if("dr-schedule"==actionId) {
			   $('.action-text').text("Scheduled Demand Reset");
			   $('.demand-read-schedule-fields').show('blind',1000);
		  }

		   if("apply-lock"==actionId) {
			$actionLrpDialog.dialog('open');
			}
		}

		//CP Cancel
		function cancelCPEvent() {
				showMessage("messaging-main","Critical Peak Event Canceled Aug 2, 2010 06:10 pm","confirm");
				$('.row-title').removeClass('icn-cp-event');
				$('.row-title').attr('title', '');
				$('#list-table').find(':checkbox').attr('checked', false);
				$('.cp-title').slideUp();
				$('#cp-active-container').hide();
				$('#system-messaging').slideUp();
				$.cookie(cookieName, false, cookieOptions);
				$('.blankslate').show();
				$( '#event1-switch1' ).button({ disabled: true });

		}
		//Messaging
		function showMessage(target, message, type) {
			$("#"+target).fadeIn(200, function() {
			  $("#" + target +" .message").html(message);
			  if("confirm"==type) {
				  $("#"+target).addClass("ui-state-highlight");
			  }
			  else if ("pin"==type)
			  {
				  $("#"+target).addClass("ui-state-information");
				  $(".remove").hide();
			  }
			  else if ("static"==type)
			  {
				  $("#"+target).addClass("ui-state-error");
				  $(".remove").hide();
			  }
			  else
			  {
				  $("#"+target).addClass("ui-state-error");
				  //$(this).effect("highlight", { color: "f6c", mode:"hide" }, 10000);
			  }
			});
		}
		//TODO when validation is fixed remove this
		// Clear form
		function clearFormElements(target) {
			$("#" + target + " .messaging").hide();
			$("#" + target).find(':input').each(function() {
				$(this).removeClass("error");
				switch(this.type) {
					case 'password':
					case 'select-multiple':
					case 'select-one':
					case 'text':
					case 'textarea':
						$(this).val('');
						break;
					case 'checkbox':
					case 'radio':
						this.checked = false;
				}
			});
		}
		// disable form
		function disableFormElements(target, on) {
			$('#' + target + ' .messaging').hide();
			if (on) {
				$('#' + target + ' .fields :input').attr('disabled','disabled');
				$('#' + target + ' .fields :input').addClass('transparent-class2');
				$('#' + target + ' .fields').removeClass('transparent-class5');
				$('#' + target + ' .fields').hide('slow');
			}
			else {
				$('#' + target + ' .fields :input').removeAttr('disabled');
				$('#' + target + ' .fields :input').removeClass('transparent-class2');
				$('#' + target + ' .fields').removeClass('transparent-class5');
				$('#' + target + ' .fields').show('slow');
			}

		}
		// Highlight
		function highlight(target) {
			$("." + target).effect("highlight",{color: "#ffffcc"}, 4000);
		}


		function getUrlVars()
		{
			var vars = [], hash;
			var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			for(var i = 0; i < hashes.length; i++)
			{
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		}

		//Function to clear selected list
		function clearSelected()
		{
			$('#list-table').find(':checkbox').attr('checked', false);
			$('.checked-count').text('0');
			$('#actions .message').removeClass("ui-state-error");
			$('#actions .message').removeClass("ui-state-highlight");
			$('#actions .filter-select').hide();
		}

		$(window).resize(function() {
			$("#system-messaging").pinFooter();
			$("#container").css('height', ($(".annotation").height() - 20 + "px"));
		});



	$(".event-day-custom").buttonset();
	$(".time").calendricalTime();
	$("#repeats-monthly-custom, #custom-day-month, #upload-dates").hide();

	//Validation
	//-----------------------form submit actions
	$("#create-event-form").validate();
	/*$("#createEvent").click(function(){
		//validate
		location.href='schedules-create.html?action=true';
		return false;
	})*/

	//Repeats control
	//Trigger repeat
	 $("#repeats").change(function () {
		switch($("#repeats option:selected").val())
		{
		case "day":
		  {
				$("#repeats-every, #starts-on, #ends-on").show();
				$("#repeats-text").text("days");
				$("#day-custom-container, #repeats-monthly-custom, #upload-dates").hide();
				$("#repeats-summary").text("Daily");
			  }
		  break;
		case "weekday":
		  {
				$("#day-custom-container, #repeats-every, #repeats-monthly-custom, #upload-dates").hide();
				$("#repeats-summary").text("Weekly on weekdays");
			  }
		  break;
		case "every-other":
		  {
				$("#day-custom-container, #repeats-every, #repeats-monthly-custom, #upload-dates").hide();
				$("#repeats-summary").text("Weekly on Mon., Wed., and Fri.");
			  }
		  break;
		case "every-t":
		  {
				$("#day-custom-container, #repeats-every, #repeats-monthly-custom, #upload-dates").hide();
				$("#repeats-summary").text("Weekly on Tue.and Thur.");
			  }
		  break;
		case "weekly":
		  {
				$("#upload-dates").hide();
				$("#day-custom-container, #repeats-every, #repeats-monthly-custom, #starts-on, #ends-on").show();
				$("#repeats-text").text("weeks");
				$("#repeats-summary").text("Weekly on Monday");
			  }
		  break;
		case "monthly":
		  {
				$("#day-custom-container, #upload-dates").hide();
				$("#repeats-every, #repeats-monthly-custom, #starts-on, #ends-on").show();
				$("#repeats-text").text("months");
				$("#repeats-summary").text("Monthly on day 27");
			  }
		  break;
		case "yearly":
		  {
				$("#day-custom-container, #repeats-monthly-custom, #upload-dates").hide();
				$("#repeats-every, #starts-on, #ends-on").show();
				$("#repeats-text").text("years");
				$("#repeats-summary").text("Yearly on June 27th");
			  }
		  break;
		case "custom":
		  {
				$("#day-custom-container, #repeats-monthly-custom,#repeats-every, #starts-on, #ends-on").hide();
				$("#upload-dates").show();
				$("#repeats-text").text("Custom");
				$("#repeats-summary").text("Custom");
			  }
		  break;
		default:
		  {}
		}



	});

//ADD Dialog
	//INIT Add to Group
	$actionDialogAdd = $("#action-dialog-repeat-define").dialog({
		autoOpen: false,
		title: 'Action - Event Repeats',
		width: 600,
		minheight: 150,
		modal: true,
		buttons: {
			'Done': function() {
				//TODO: figure out why these validation classes are not working here
				 /*$("#createGroupForm").validate({
					rules: {
						controlGroupNameCreate: "required"
						}
					});
				 */
					showMessage("messaging-main","Event repeats created successfully.","confirm");

					$(this).dialog('close');
			},
			Cancel: function() {
				$(this).dialog('close');
			}
		},
		dialogClass: 'action-dialog',
		resizable: false
	});

	 //Define Tier Action


	//Trigger LRP on click
	$("#add-repeat-definition-event").click(function(e) {
		e.preventDefault();
		$actionDialogAdd.dialog('open');
	});

	//Trigger hide custom months
	$("#day-custom").click(function(e) {
		$("#custom-day-month").show();
	});

	//Trigger show custom months
	$("#day-week, #day-month").click(function(e) {
		$("#custom-day-month").hide();
	});

$.sc.stopGlobalProgressBar();
});
</script>
