<script type="text/javascript">
/**
 * @namespace sensus.pages.smartpoint
 * @description The action namespace for the SmartPoint Page.
 * @fileoverview Defines the various action dialog options and common behaviors
 *               for the group-related actions.
 * @author Anke Doerfel-Parker
 *
 * Return a copy of the default configuration for the "Selected SmartPoints"
 * table for use in the action dialog.
 *
 * @param aaData
 *            the data to render in the table as two-dimensional string array
 *
 * @return the default table configuration
 */
sensus.pages.smartpoint.getDefaultConfig = function(aaData) {

	 return jQuery.extend({
		aaData      : aaData,
		bAutoWidth  : false,
		aoColumns   : [
			{sId : "Delete",  sWidth : "10%"},
			{sId : "Id",      bVisible : false},
			{sId : "RNI_Id",  sWidth : "20%"},
			{sId : "Name",    sWidth : "40%"},
			{sId : "Added",   sWidth : "20%"}
		]
	}, sensus.util.datatable.getDefaultActionConfig());

};

/**
 * Render the default "Selected SmartPoint" table. Uses the selectedRows
 * property and the column configuration from the main SmartPoint table to
 * retrieve the data to render. Table is rendered and assigned to the
 * sensus.pages.smartpoint.actionDialogTable property.
 *
 * @see sensus.util.datatable.getColumnSetup
 * @see sensus.pages.smartpoint.tableColumns
 * @see sensus.widgets.datatable.selectedRows
 * @see sensus.pages.smartpoint.actionDialogTable
 */
sensus.pages.smartpoint.renderDefaultTable = function() {

	var colSetup = sensus.util.datatable.getColumnSetup(sensus.pages.smartpoint.tableColumns);
	var aaData = [];
	var r;

	for ( var i in sensus.widgets.datatable.selectedRows) {

		r = sensus.widgets.datatable.selectedRows[i];

		aaData.push( [ "", r[colSetup.colIndexAll.Id], r[colSetup.colIndexAll.RNI_Id], r[colSetup.colIndexAll.Pole_Id], r[colSetup.colIndexAll.Type] ]);

	}

	$('.smartpoint-count').html($('.checked-count').html());
	sensus.pages.smartpoint.actionDialogTable = $('#selected-smartpoints').dataTable(sensus.pages.smartpoint.getDefaultConfig(aaData));
};

/**
 * Return the default success function for the Ajax callback performed by the
 * action dialog. This function is called when the call was successful (no HTTP
 * errors). It does not mean the intended operation performed as intended.
 *
 * @param successMessage
 *            the message to display in case of successful operation
 * @param errorMessage
 *            the message to display if the operation did not work
 * @param refresh
 *            whether to refresh the main group datatable
 * @return the function to provide to as 'success' parameter to $.ajax()
 */
sensus.pages.smartpoint.getDefaultAjaxSuccessFunction = function(successMessage, errorMessage, bRefresh) {

	return function(data) {

		if (bRefresh === true) {

			sensus.pages.smartpoint.setPageState(sensus.pages.smartpoint.buildHash());

		}

		// Reset selections;
		$("#smartpoint-table input[type=checkbox]").attr('checked', false);
		$('.checked-count').html("0");
		sensus.widgets.datatable.selectedRows = [];
		$('.smartpoint-count').html(0);

		if (data.result === sensus.constants.ajax.ok) {

			sensus.util.page.showMessage("messaging-main", successMessage + sensus.util.page.getMessageList(data.messages), "confirm");

		} else {

			sensus.util.page.showMessage("messaging-main", errorMessage + sensus.util.page.getMessageList(data.messages), "error");

		}

		sensus.util.page.stopProgressBar(0,false);
	};

};

/**
 * Return the default error function for the Ajax callback performed by the
 * action dialog. This function is called when the HTTP call failed.
 *
 * @param errorMessage
 *            the message to display if the operation did not work
 * @param refresh
 *            whether to refresh the main group datatable
 * @return the function to provide to as 'error' parameter to $.ajax()
 */
sensus.pages.smartpoint.getDefaultAjaxErrorFunction = function(errorMessage, bRefresh) {

	if (bRefresh === true) {

		sensus.pages.smartpoint.setPageState(sensus.pages.smartpoint.buildHash());

	}

	return function() {

		sensus.util.page.showMessage("messaging-main", errorMessage, "error");
		sensus.util.page.stopProgressBar(0,false);

	};

};

/**
 * Retrieve the selected smartpoint ids from the action dialog page. This may be
 * a subset of the records selected in the main table because the action dialogs
 * typically allow the removal of individual records from the "Selected
 * SmartPoint" table.
 *
 * @return a string array of the ids of the selected SmartPoints
 */
sensus.pages.smartpoint.getSmartpointIds = function() {

	var aIds = [];

	$("#smartpoint-table").find(":checked").each(function(i) {

		if ( $(this).attr("value").match('^(0|[1-9][0-9]*)$') ) {

			aIds.push($(this).attr("value"));

		}

	 });

	return aIds;
};

/**
 * Get all filters selected and put it in a array
 */

sensus.pages.smartpoint.getFilterURL = function() {

	var sFilterUrl = sensus.util.page.fnGetURLFilters();
	return sFilterUrl;

};

sensus.pages.smartpoint.getStringFiltersURL = function() {

	var sFilterUrl = sensus.util.page.fnGetURLFilters();
	return sFilterUrl;

};

/**
 * Configuration for the "applySchedule" dialog.
 */

sensus.pages.smartpoint.applySmartPointToSchedule = function(url, urlInclude, title) {

	var apply = {
		/**
		 * The dialog title.
		 */
		title : title,
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "apply-smartpoint-to-schedule-submit",
				text : $.sc.locale("action.addtoschedule.submit"),
				click : function() {

					var sUrl          = null;
					var	oSearchLight  = { searchParameters :  sensus.util.page.getSearchParameters()};
					var	nId 		  = sensus.util.combobox.getOption("#add-schedule-select");
					var	sScheduleName = $('#add-schedule-select option[value="'+nId+'"]').text();
					var sScheduleType = "";
					var aIds          = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;

					// Check if name is empty or is a valid name
					if (sScheduleName.length == 0 || sScheduleName == $.sc.locale("widgets.combobox.prompt")) {
						$("#add-schedule-form #add-schedule-select_input").validationEngine('showPrompt', $.sc.locale("validation.addtoschedule.noschedule"), 'red', '', true);
						return false;
					}

					sUrl = "api/schedule/insertlights";
					if (url == "addToEventScheduleUrl") {
						sScheduleType = 'EVENT';

					} else if (url == "addToOffsetScheduleUrl") {
						sScheduleType = 'OFFSET';

					}

					$(this).dialog('close');

					var oRequest = new scheduleRequest(nId, null, null, null, null, null, {}, sScheduleType, sensus.widgets.datatable.isAllRows, aIds, oSearchLight, sScheduleName);

					//$.sc.getJson(sUrl, oRequest, false, sensus.pages.smartpoint.fnClearTableSelects, $.sc.locale("action.longRunningProcessDialog.confirm"), false);
					$.ajaxValidator.fnMonitor(sUrl, oRequest, false, sensus.pages.smartpoint.fnClearTableSelects, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

				}

			}, {

				id: "apply-smartpoint-to-schedule-cancel",
				text :$.sc.locale("action.addtoschedule.cancel"),
				click : function() {

					$(this).dialog('close');
					sensus.pages.smartpoint.actionDialogTable = null;

				}

		}],

		/**
		 * The name of the form to clear when launching the dialog.
		 */
		form : 'add_schedule_form',

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			var sUrlIncludePage = null;
			var sScheduleType = "";

			if (urlInclude == "addToScheduleEventInclude") {

				//sUrlIncludePage = sensus.settings.addToScheduleEventInclude;
				sUrlIncludePage = "light/applyEventSchedule";
				sScheduleType = "EVENT";

			} else if (urlInclude == "addToScheduleOffsetInclude") {

				//sUrlIncludePage = sensus.settings.addToScheduleOffsetInclude;
				sUrlIncludePage = "light/applyOffsetSchedule";
				sScheduleType = "OFFSET";

			}

			$('#action-dialog').empty().load(sUrlIncludePage, function() {
				var oReq = new inquiryScheduleRequest();
				var fnCallBack = function(oResp){
					var sHtml = "<option value='0'>Select one...</option>";

					for (var i = 0; i < oResp.schedules.length; i++){
						if (oResp.schedules[i].scheduleTypeEnum == sScheduleType){
							sHtml += "<option value='"+ oResp.schedules[i].id +"'>"+ oResp.schedules[i].name +"</option>";
						}
					}

					$("#add-schedule-select").append(sHtml);

					actionDialog.removeClass('waiting');
					$('#add-schedule-select').combobox( { zIndex : 3999	});
					$("#add-schedule-select_input").attr("value", $.sc.locale("widgets.combobox.prompt.typetosearch"));
					$('.smartpoint-count').html($('.checked-count').text().split(' ')[0]);
				};
				$.sc.getJson("api/schedule/fetchAll", oReq, false, fnCallBack, null);





			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');

		},

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {

			$('.formError').remove();

		}

	};

	return apply;

};

/**
 * Configuration for the "resetSchedule" dialog.
 */
sensus.pages.smartpoint.resetSchedule = function(sParamURL) {

	 var reset = {

		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.removefromschedule.title"),

		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id     : "remove-from-schedule-submit",
				text   : $.sc.locale("action.removefromschedule.submit"),
				click  : function() {

					var sUrl          = null;
					var	oSearchLight  = { searchParameters :  sensus.util.page.getSearchParameters()};
					var sScheduleType = "";
					var aIds          = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;

					if (sParamURL == "removeFromEventScheduleUrl") {

						sScheduleType = 'EVENT';

					} else if (sParamURL == "removeFromOffsetScheduleUrl") {

						sScheduleType = 'OFFSET';

					}

					$("#action-dialog").dialog('close');

					//var oRequest = new scheduleRequest(null, null, null, null, null, null, {}, sScheduleType, sensus.widgets.datatable.isAllRows, aIds, oSearchLight, null);
					var oRequest = new scheduleRequest(null, null, null, null, null, null, null, sScheduleType, sensus.widgets.datatable.isAllRows, aIds, oSearchLight, null);

					$.sc.getJson("api/schedule/deletelights", oRequest, false, sensus.pages.smartpoint.fnClearTableSelects, $.sc.locale("action.longRunningProcessDialog.confirm"), false);

				}

			}, {

				id     : "remove-from-schedule-cancel",
				text   :$.sc.locale("action.removefromschedule.cancel"),
				click  : function() {

					$(this).dialog('close');
					sensus.pages.smartpoint.actionDialogTable = null;

				}

		}],

		/**
		 * The name of the form to clear when launching the dialog.
		 */
		form : 'add_schedule_form',
		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			var sUrlIncludePage = "light/removeEventSchedule";

			$('#action-dialog').empty().load(sUrlIncludePage, function() {

				actionDialog.removeClass('waiting');
				$('.smartpoint-count').html($('.checked-count').text().split(' ')[0]);
			});

			actionDialog.addClass('waiting');
			actionDialog.dialog('open');

		}

	};

	return reset;
};

/**
 * The main namespace for the smartpoint action dialog configurations. Note that
 * the buttons are initialized in a function because their names must be
 * internationalized and cannot be used in the simple JSON notation because
 * property names must be literals.
 *
 * The name of each configuration matches the value behind each option from the
 * action droplist and the action button.
 */
sensus.pages.smartpoint.dialogSettings = {
	/**
	 * Configuration for the "addToGroup" dialog.
	 */

	addToGroup : {
		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.addtogroup.title"),
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,


		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "add-to-group-submit",
				text : $.sc.locale("action.addtogroup.submit"),
				click : function() {

					var bValidGroupName = true;
					var sChars = "!@$%^&*()+=[]\\\;,./{}|\":<>?";
					var sGroupName = $("#add-group-form .ui-autocomplete-input").val();
					var sAction = 'INSERT';
					var sNewGroupName = '';
					var sGroupDescription = '';
					var sGroup = '';
					var sIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && sIds.length <= 0 ? "" : sIds;
					var filterUrl = sensus.pages.smartpoint.getFilterURL();
					var sStringFiltersURL = sensus.pages.smartpoint.getStringFiltersURL();

					//If action is create new group and after add lights to group
					if (sGroupName == $.sc.locale("action.addgroup.label.addnewgroup")) {

						sNewGroupName = $("#control-group-name-create").val();

						$('#group-add-select').find('option').each(function() {

							if ($(this).text() == $('#control-group-name-create').val()) {

								$("#add-group-form #control-group-name-create").validationEngine('showPrompt', $.sc.locale("validation.addtogroup.uniquegroupname"), 'red', '', true);
								bValidGroupName = false;

							}
						});

						for (var i = 0; i < sNewGroupName.length ; i++) {

							if (sChars.indexOf(sNewGroupName.charAt(i)) != -1) {

								$("#add-group-form #control-group-name-create").validationEngine('showPrompt', $.sc.locale("validation.specialCharsCustom"), 'red', '', true);
								bValidGroupName = false;

							}
						}

						if (sNewGroupName.length == 0 || sNewGroupName.length > 100) {

							if (sNewGroupName.length == 0) {

								$("#add-group-form #control-group-name-create").validationEngine('showPrompt', $.sc.locale("validation.addtogroup.nonewgroupname"), 'red', '', true);

							} else {

								$("#add-group-form #control-group-name-create").validationEngine('showPrompt', $.sc.locale("groupcreate.form.name.maxlength"), 'red', '', true);

							}

						} else if (bValidGroupName) {

							sGroupDescription = $("#control-group-description").val();

							if (sGroupDescription.length <= 150) {

								// After Validation show Block Ui
								sensus.util.page.startProgressBar(null,true);

								// Submit Ajax Action to Create a new Group
								sensus.util.ajaxaction.actionUrlAdress = 'group/insertGroup.action';
								sensus.util.ajaxaction.data = { 'groupRequest': new groupRequest(sNewGroupName, sGroupDescription, null, null, sAction, null, sIdUrl) };
								sensus.util.ajaxaction.sendActionDefault($.sc.locale("action.addsgroup.success", sNewGroupName), $.sc.locale("action.addgroup.error"));

								// FAIL
								if (sensus.util.ajaxaction.actionResult == false) {

									$(this).dialog('close');
									sensus.util.page.stopProgressBar(1,true);
									return false;

								}

								sensus.util.ajaxaction.actionResult = false;

								// limit the size name
								var sLabel = sNewGroupName;

								if (sLabel.length > 15) {

									sLabel = sLabel.substr(0,15) + '...';

								}

								// Add group created in filter of light list
								var sNewGroupId = sensus.util.ajaxaction.oResponse.groups[0].id;
								var oListGroups = $('.checkBoxUl .ui-listcombobox', "#GROUPS");
								var oNewGroupLi = '<li title="' + sNewGroupName + '" class="checkbox"><input type="checkbox" value="' + sNewGroupId + '"> ' + sLabel + '</li>';
								var newOptionInTagSelect = '<option value="' + sNewGroupId + '">' + sLabel + '</option>';
								var oNewSelect = $('<select class="listcombobox" id="combobox"></select>');

								if (oListGroups.find('li').length < 6) {

									oListGroups.append(oNewGroupLi);

								} else {

									if ($("#combobox", "#GROUPS").length) {

										var nComboLength = $("#combobox option", "#GROUPS").length;

										if (nComboLength) {

											$(newOptionInTagSelect).appendTo("#GROUPS .listcombobox");
											$('#combobox_input', '#GROUPS').val((parseInt(nComboLength)+1)+' more...');

										} else {

											$('#combobox', "#GROUPS").remove();
											$(oNewSelect).appendTo("#GROUPS .checkBoxUl");
											$(newOptionInTagSelect).appendTo(oNewSelect);
											$('#combobox', "#GROUPS").combobox();

										}

									} else {

										$('#combobox', "#GROUPS").remove();
										$(oNewSelect).appendTo("#GROUPS .checkBoxUl");
										$(newOptionInTagSelect).appendTo(oNewSelect);
										$('#combobox', "#GROUPS").combobox();

									}

								}

								sGroup = sensus.util.ajaxaction.oResponse.groups[0].id;

							} else {

								$("#control-group-description").validationEngine('showPrompt', $.sc.locale("groupcreate.form.description.maxlength"), 'red', '', true);

							}

						}

					} else if (sGroupName != $.sc.locale("widgets.combobox.prompt") && sGroupName.length != 0 && sensus.util.combobox.getOption("#group-add-select") != undefined) {

						sNewGroupName = $("#add-group-form .ui-autocomplete-input").val();
						sGroup = sensus.util.combobox.getOption("#group-add-select");

					} else {

						if (sensus.util.combobox.getOption("#group-add-select") == undefined) {

							$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', $.sc.locale("smartpoint.actions.addToGroup.not.exists", $('#group-add-select_input').val()), 'red', '', true);

						} else {

							$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', $.sc.locale("validation.addtogroup.nonewgroupname"), 'red', '', true);

						}
					}

					if (sGroup) {

						sAction = 'UPDATE';
						sGroupDescription = null;

						$(this).dialog('close');

						var oSearchLight = { searchParameters :  sensus.util.page.getSearchParameters()};

						// Submit Ajax Action
						sensus.util.ajaxaction.actionUrlAdress = sensus.settings.addToGroupUrl;
						sensus.util.ajaxaction.data = { 'groupRequest': new groupRequest(sNewGroupName, null, null, sGroup, sAction, sensus.widgets.datatable.isAllRows, sIdUrl, oSearchLight) };
						sensus.util.ajaxaction.sendActionDefault($.sc.locale("smartpoint.actions.addgroup.success", sNewGroupName), $.sc.locale("action.addgroup.error"));

						if (sensus.util.ajaxaction.actionResult) {

							sensus.util.ajaxaction.actionResult = false;

						}

						sensus.util.page.stopProgressBar(1,true);
						sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.smartpoint.smartpointTable);
						sensus.pages.smartpoint.fnReloadTable();

					}

				}

			}, {

				id: "add-to-group-cancel",
				text :$.sc.locale("action.addtogroup.cancel"),
				click : function() {

					$(this).dialog('close');
					sensus.pages.smartpoint.actionDialogTable = null;

				}

		}],

		/**
		 * The name of the form to clear when launching the dialog.
		 */
		form : 'add_group_form',
		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			$('#action-dialog').empty().load(sensus.settings.addToGroupInclude, function() {

				$(".add-new-group").click(function(e) {

					e.preventDefault();
					$('.formError').remove();
					$("#create-group-add-smartpoint").toggle(true).unbind("click");

				});

				$('.ui-corner-all', actionDialog).live('click', function() {

					if ($('#group-add-select_input').val() != $.sc.locale('widgets.combobox.prompt')
							&& $('#group_add_select_input').val() != $.sc.locale('action.addgroup.label.addnewgroup')) {

						$('#add-group-form .add-group-control').removeClass('s').addClass('h');
						$('#add-group-form .add-group-control').hide();

					}

				});

				$('#action-dialog a').click(function() {

					if ($('#add-group-form .add-group-control').attr('class') == 'add-group-control s') {

						$('#add-group-form .add-group-control').removeClass('s').addClass('h');
						$('#add-group-form .add-group-control').hide();
						$("#controlGroupNameCreate2").val('');
						$("#control-group-description2").val('');
						$("#controlGroupNameCreate2").removeClass("error");
						$('#group-add-select_input').val($.sc.locale('widgets.combobox.prompt'));

					} else {

						$('#add-group-form .add-group-control').removeClass('h').addClass('s');
						$('#add-group-form .add-group-control').show();
						$('#group-add-select_input').val($.sc.locale('action.addgroup.label.addnewgroup'));
						$('#group-add-select option[value="0"]').text($.sc.locale('action.addgroup.label.addnewgroup'));

					}

					return false;
				});

				actionDialog.removeClass('waiting');

				$('#group-add-select').combobox( {
					zIndex : 3999
				});

				$("#group-add-select_input").attr("value", $.sc.locale("widgets.combobox.prompt2"));
				$('.smartpoint-count').html($('.checked-count').text().split(' ')[0]);
			});

			actionDialog.addClass('waiting');
			actionDialog.dialog('open');
			$('.ui-corner-all').die();
		},

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {

			$('.formError').remove();

		}

	},
	/**
	 * Configuration for the "removeFromGroup" dialog.
	 */
	removeFromGroup : {
		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.removefromgroup.title"),
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,


		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "remove-from-group-submit",
				text : $.sc.locale("action.removefromgroup.submit"),
				click : function() {

					var sGroupName = $("#add-group-form .ui-autocomplete-input").val();
					var sIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl = sensus.widgets.datatable.isAllRows && sIds.length <= 0 ? "" : sIds;
					var sFilterUrl = sensus.pages.smartpoint.getFilterURL();
					var sStringFiltersURL = sensus.pages.smartpoint.getStringFiltersURL();
					var sNoGroup = sGroupName.length == 0 || sGroupName == $.sc.locale("widgets.combobox.prompt");
					if (!sNoGroup && (sensus.widgets.datatable.isAllRows || sIds.length > 0)) {

						sensus.util.page.startProgressBar(null,true);

						var sGroup = sensus.util.combobox.getOption("#group-add-select");
						var sAction = 'UPDATE';
						var GroupName = $("#add-group-form .ui-autocomplete-input").val();
						$("#action-dialog").dialog('close');

						// Submit Ajax Action
						sensus.util.ajaxaction.actionUrlAdress = sensus.settings.removeFromGroupUrl;
						sensus.util.ajaxaction.data = { 'groupRequest': new groupRequest(sGroupName, null, null, sGroup, sAction, sensus.widgets.datatable.isAllRows, sIdUrl) };
						sensus.util.ajaxaction.sendActionDefault($.sc.locale("smartpoint.actions.removegroup.success", GroupName), $.sc.locale("action.addgroup.error"));

						if (sensus.util.ajaxaction.actionResult) {

							sensus.pages.smartpoint.fnReloadTable();
							sensus.util.ajaxaction.actionResult = false;

						} else {

							sensus.util.page.stopProgressBar(0,true);

						}

					} else {

						if (sIds.length == 0) {

							sensus.util.page.showMessage("action-messaging", $.sc.locale("validation.removefromgroup.nosmartpoint"), "error");

						} else if (sNoGroup) {

							$("#add-group-form .ui-autocomplete-input").validationEngine('showPrompt', $.sc.locale("validation.removefromgroup.nogroup"), 'red', '', true);

						}

						return false;
					};

					$.address.parameter("iDisplayStart", 0);
					sensus.widgets.datatable.reloadTable(sensus.pages.smartpoint.smartpointTable, $.address.parameter("iDisplayStart"));

				}

			}, {

				id: "remove-from-group-cancel",
				text :$.sc.locale("action.removefromgroup.cancel"),
				click : function() {

					$(this).dialog('close');

				}

		}],

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {

			$('#action-dialog').empty().load(sensus.settings.removeFromGroupInclude, function() {

				actionDialog.removeClass('waiting');
				$('#group-add-select').combobox( {
					zIndex : 3999
				});
				$("#group-add-select_input").attr("value", $.sc.locale("widgets.combobox.prompt2"));
				$('.smartpoint-count').html($('.checked-count').text().split(' ')[0]);
			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');

		},

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {

			$('.formError').remove();

		}
	},

	/**
	 * Configuration for the "addTag" dialog.
	 */
	addTag : {

		title                : $.sc.locale("action.addtag.title"),
		requiresSmartpoints  : true,
		buttons              : [{
				id     : "add-tag-submit",
				text   : $.sc.locale("action.addtag.submit"),
				click  : function() {

					var aIdTags  = [];

					if ( ($('.search-field input').val() == $.sc.locale('widgets.combobox.prompt.selecttags')
							|| $('.search-field input').val().length == 0) && $(".chzn-choices li.search-choice").length == 0) {

						$('.chzn-choices').validationEngine('showPrompt', $.sc.locale("validation.addtag.notag"), 'red', '', true);
						return;

					}

					$(".chzn-choices li.search-choice, .search-field input").each(function() {

						var sLiText = $(this).text() || $(this).val();
						var oOption = null;

						$('select#select_tags option').each(function() {

							if($(this).text().trim() === sLiText) {

								oOption = $(this);
								return;

							}

						});

						if (!oOption && sLiText
								 && sLiText.replace(/\s/g, '').length
								 && sensus.settings.userContext.userRole == 'ROLE_Role.Admin') {

							sensus.util.ajaxaction.actionUrlAdress = sensus.settings.insertTag;
							sensus.util.ajaxaction.data = {
									'tagRequest': new tagRequest(null, sLiText, null, null, null, null)
							};
							sensus.util.ajaxaction.sendActionDefault($.sc.locale("action.inserttag.success", [sLiText, sensus.settings.smartpointUrl]));

							// If inserted with success
							if (sensus.util.ajaxaction.actionResult) {

								aIdTags.push({ id : sensus.util.ajaxaction.oResponse.tags[0].id });

								// limit the size of tag name
								var sLabel = sLiText;

								if (sLabel.length > 15) {
									sLabel = sLabel.substr(0, 15) + '...';
								}

								// Add tag created in filter of light list
								var sNewTagId = sensus.util.ajaxaction.oResponse.tags[0].id;
								var oListTags = $('.checkBoxUl .ui-listcombobox', "#TAGS");
								var oNewTagLi = '<li title="' + sLiText + '" class="checkbox"><input type="checkbox" value="' + sNewTagId + '"> ' + sLabel + '</li>';
								var newOptionInTagSelect = '<option value="' + sNewTagId + '">' + sLabel + '</option>';
								var oNewSelect = $('<select class="listcombobox" id="combobox"></select>');

								if (oListTags.find('li').length < 6) {

									oListTags.append(oNewTagLi);

								} else {

									if ($("#combobox", "#TAGS").length) {

										var nComboLength = $("#combobox option", "#TAGS").length;

										if (nComboLength) {

											$(newOptionInTagSelect).appendTo("#TAGS .listcombobox");

											$('#combobox_input', '#TAGS').val((parseInt(nComboLength)+1)+' more...');

										} else {

											$('#combobox', "#TAGS").remove();

											$(oNewSelect).appendTo("#TAGS .checkBoxUl");

											$(newOptionInTagSelect).appendTo(oNewSelect);

											$('#combobox', "#TAGS").combobox();

										}

									} else {

										$('#combobox', "#TAGS").remove();

										$(oNewSelect).appendTo("#TAGS .checkBoxUl");

										$(newOptionInTagSelect).appendTo(oNewSelect);

										$('#combobox', "#TAGS").combobox();

									}

								}

							}

						} else if ($(this).find(".search-choice-close").attr("id") != undefined) {

							aIdTags.push({ id : parseInt( ($(this).find(".search-choice-close").attr("id")) ) });

						} else {

							$('.chzn-choices').validationEngine('showPrompt', $.sc.locale("validation.addtag.notag"), 'red', '', true);

						}

					});


					$('.search-field input').val('');

					if (aIdTags.length == 0) {
						$('.chzn-choices').validationEngine('showPrompt', $.sc.locale("validation.addtag.notag"), 'red', '', true);
						return;
					}

					var isAllRows = sensus.widgets.datatable.isAllRows;
					var aIds = sensus.widgets.datatable.selectedRows;

					if (isAllRows) {
						aIds = sensus.widgets.datatable.deselectedRows;
					}

					if (aIds.length) {

						for (var iKey in aIds) {

							if (aIds.hasOwnProperty(iKey)) {
								aIds[iKey] = parseInt(aIds[iKey]);
							}

						}

					}

					sensus.util.page.startProgressBar(null,true);

					$("#action-dialog").dialog('close');

					sensus.util.ajaxaction.actionUrlAdress = sensus.settings.addTag;
					sensus.util.ajaxaction.data = {
						'tagRequest': new tagRequest(null, null, aIdTags, isAllRows, aIds, { 'searchParameters' :  sensus.util.page.getSearchParameters()})
					};
					sensus.util.ajaxaction.sendActionDefault($.sc.locale("smartpoint.actions.addtag.success"), $.sc.locale("action.longRunningProcessDialog.error"));

					if (sensus.util.ajaxaction.actionResult) {

						//TO-DO Implements reload filter
						//sensus.pages.smartpoint.filter.reload("#select-filter-tag", "smartpoint/include.filterTagContainer.action");

						sensus.pages.smartpoint.fnReloadTable();
						sensus.util.ajaxaction.actionResult = false;

					} else {
						sensus.util.page.stopProgressBar(1,true);
					}

					sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.smartpoint.smartpointTable);

				}
			}, {
				id: "add-tag-cancel",
				text :$.sc.locale("action.addtag.cancel"),
				click : function() {
					$(this).dialog('close');
				}
		}],

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {
			$('#action-dialog').empty().load(sensus.settings.addTagInclude, function() {
				actionDialog.removeClass('waiting');
				$(".chzn-select").chosen();
				$('.smartpoint-count').html('<strong>' + $('.checked-count').text().split(' ')[0] + '</strong>');
			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');
		},

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {
			$('.formError').remove();
		}
	},

	/**
	 * Configuration for the "removeTag" dialog.
	 */
	removeTag : {
		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.removetag.title"),
		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : true,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "remove-tag-submit",
				text : $.sc.locale("action.removetag.submit"),
				click : function() {

					var aIdTags = new Array();
					var i = 0;

					$("#select_tags_chzn").find(".chzn-choices li.search-choice").each(function() {

						if ($(this).find(".search-choice-close").attr("id") != undefined) {

							aIdTags[i] = { id : parseInt( ($(this).find(".search-choice-close").attr("id")) ) };
							i++;
						}

					});

					if (aIdTags.length == 0) {
						$('.chzn-choices').validationEngine('showPrompt', $.sc.locale("validation.removetag.notag"), 'red', '', true);
						return;
					}

					var isAllRows = sensus.widgets.datatable.isAllRows;
					var aIds = sensus.widgets.datatable.selectedRows;
					var aiIds = [];
					var sIdUrl = "";

					if (isAllRows) {
						aIds = sensus.widgets.datatable.deselectedRows;
					}

					if (aIds.length) {

						for (var iKey in aIds) {

							if (aIds.hasOwnProperty(iKey)) {
								aIds[iKey] = parseInt(aIds[iKey]);
							}

						}

					}

					sensus.util.page.startProgressBar(null,true);

					$("#action-dialog").dialog('close');

					sensus.util.ajaxaction.actionUrlAdress = sensus.settings.removeTag;
					sensus.util.ajaxaction.data = {
						'tagRequest': new tagRequest(null, null, aIdTags, isAllRows, aIds, { 'searchParameters' :  sensus.util.page.getSearchParameters()})
					};
					sensus.util.ajaxaction.sendActionDefault($.sc.locale("smartpoint.actions.removetag.success"), $.sc.locale("action.longRunningProcessDialog.error"));

					if (sensus.util.ajaxaction.actionResult) {

						sensus.pages.smartpoint.fnReloadTable();
						sensus.util.ajaxaction.actionResult = false;

					} else {
						sensus.util.page.stopProgressBar(1,true);
					}

					sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.smartpoint.smartpointTable);

				}

			}, {
				id: "remove-tag-cancel",
				text :$.sc.locale("action.removetag.cancel"),
				click : function() {

					$(this).dialog('close');

				}
		}],

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {
			$('#action-dialog').empty().load(sensus.settings.removeTagInclude, function() {
				actionDialog.removeClass('waiting');
				$(".chzn-select").chosen();
				$('.smartpoint-count').html('<strong>' + $('.checked-count').text().split(' ')[0] + '</strong>');
			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');
		},

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {

			$('.formError').remove();

		}

	},

	/**
	 * Configuration for the "applyEventSchedule" dialog.
	 */
	applyEventSchedule : sensus.pages.smartpoint.applySmartPointToSchedule("addToEventScheduleUrl", 'addToScheduleEventInclude', $.sc.locale("action.applyeventschedule.title")),

	/**
	 * Configuration for the "applyOffsetSchedule" dialog.
	 */
	applyOffsetSchedule : sensus.pages.smartpoint.applySmartPointToSchedule("addToOffsetScheduleUrl", 'addToScheduleOffsetInclude', $.sc.locale("action.applyoffsetschedule.title")),

	/**
	 * Configuration for the "resetEventSchedule" dialog.
	 */
	resetEventSchedule : sensus.pages.smartpoint.resetSchedule("removeFromEventScheduleUrl"),

	/**
	 * Configuration for the "resetOffsetSchedule" dialog.
	 */

	resetOffsetSchedule : sensus.pages.smartpoint.resetSchedule("removeFromOffsetScheduleUrl"),

	/**
	 * Configuration for the Save Search dialog.
	 */
	saveSearch : {
		/**
		 * The dialog title.
		 */
		title : $.sc.locale("action.savesearch.title"),

		width: 600,

		/**
		 * Whether this dialog requires a smartpoint list.
		 */
		requiresSmartpoints : false,

		/**
		 * The dialog buttons (submit and cancel).
		 */
		buttons : [{
				id : "save-search-submit",
				text : $.sc.locale("action.savesearch.submit"),
				click : function() {

					var	oSearchName = $("#saved-search-name");

					if (!oSearchName.val().replace(/\s/,"").length) {

						$("#save-search-form #saved-search-name").validationEngine('showPrompt', $.sc.locale("validation.savesearch.noname"), 'red', '', true);
						sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.smartpoint.smartpointTable);
						return false;

					}

					var oSearchDescription = $("#saved-search-description");
					var sSearchName = oSearchName.val();
					var sSearchDescription = oSearchDescription.val();
					var aSearchParameters = sensus.util.page.getSearchParameters();

					if (sSearchDescription.length > 200) {

						$("#save-search-form #saved-search-description").validationEngine('showPrompt', $.sc.locale("validation.savesearchdescription.maxlength"), 'red', '', true);
						return false;

					}

					$("#action-dialog").dialog('close');

					// Gets values from URL
					var aStatus = $.address.parameter('status');

					if (aStatus) {

						aStatus = aStatus.split("|");
						// Remove last position of array because is a white space
						aStatus.pop();

					}

					// Object Model with necessary informations to save
					var oCustomSearchRequest = {
					 		userContext  : new UserContext(),
							customSearch : {
								name              : sSearchName,
								description       : sSearchDescription,
								statusList        : sensus.widgets.datatable.fnConvertEnum('LightStatusEnum', aStatus),
								searchParameters  : aSearchParameters,
								listColumn        : $.wCustomize.getData('smartpointlist', 'Columns').listColumn,
								listFilters       : $.wCustomize.getData('smartpointlist', 'Filters').filters,
								sortExpression    : null
							}
					};

					// Send informations to save
					$.ajax({
						dataType    : 'json',
						type        : "POST",
						url         : sensus.settings.saveSearch,
						data        : $.toJSON({'customSearchRequest' : oCustomSearchRequest}),
						async       : false,
						contentType : "application/json; charset=utf-8",
						success     : function(oResponse) {

							if (oResponse && oResponse.operationSuccess) {

								sensus.util.page.showMessage("messaging-main", $.sc.locale("action.savesearch.success", sSearchName), "confirm");
								return true;

							}

							if (oResponse.messageList[0].text) {

								sensus.util.page.showMessage("messaging-main", oResponse.messageList[0].text, "error");

							} else {

								sensus.util.page.showMessage("messaging-main", $.sc.locale("action.savesearch.error"), "error");

							}
						}
					});

					sensus.widgets.datatable.clearSelectsFunction.call(sensus.pages.smartpoint.smartpointTable);

				}

			}, {
				id: "save-search-cancel",
				text : $.sc.locale("action.savesearch.cancel"),
				click : function() {

					$(this).dialog('close');

				}

		}],

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {
			$('#action-dialog').empty().load('', function() {
				actionDialog.removeClass('waiting');
				$('#save-search-form').submit(function(e){

					e.preventDefault();

				});
			});
			actionDialog.addClass('waiting');
			actionDialog.dialog('open');

		},

		/**
		 * The function that will be called when the action dialog is closed.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		close : function() {

			$('.formError').remove();

		}
	}
};
</script>