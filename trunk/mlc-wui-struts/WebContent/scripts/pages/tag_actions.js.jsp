<script type="text/javascript">
/**
 * @namespace sensus.pages.tag
 * @description The action namespace for the Tag Page.
 */ 

/**
 * @fileoverview Defines the various action dialog options and common behaviors
 *               for the tag-related actions.
 * 
 * @author Raphael Constantino
 
 */
sensus.pages.systemsettings.tag = {

	dialogSettings : {
		
		/** The Dialog for delete Tag*/
		deleteTag : {
			
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.deletetag.title"),
			
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,
			
			/**
			 * The dialog width.
			 */
			width : 300,
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {

				var buttons = {};

				buttons[sensus.locale.get("action.deletetag.submit")] = function() {

					var nTagId = sensus.pages.systemsettings.tag.tagId;

					$(this).dialog('close');

					//Send ajax action
					sensus.util.ajaxaction.actionUrlAdress = sensus.settings.deleteTagUrl;
					sensus.util.ajaxaction.data = "id=" + nTagId;
					sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.deletetag.success"), sensus.locale.get("action.deletetag.error"));

					$('#combo-tag').find("select").find("option:contains('"+ sensus.pages.systemsettings.tag.tagName +"')").remove(); 

					sensus.pages.systemsettings.tag.tagId = 0;

					sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);

				};
				buttons[sensus.locale.get("action.tag.cancel")] = function() {

					sensus.pages.systemsettings.tag.tagId = 0;
					$("#action-dialog").dialog('close');

				};
				return buttons;

			})(),

			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {

				//sensus.util.page.clearFormElements('delete_group_form');
				$('#action-dialog').empty().load(sensus.settings.deleteTagInclude, function() {
					
					$("#action-dialog").removeClass("waiting");
					$('#selected-tag').append(sensus.locale.get("action.deletetag.tagName", sensus.pages.systemsettings.tag.tagName));
					
				});
				
				$("#action-dialog").dialog('open');
			}
		
		},

		/** The Dialog for Auto group a Existing group*/
		autoExistingGroupInclude : {

			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.autogroup.title"),

			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : true,

			/**
			 * The dialog width.
			 */
			width : 700,

			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {

				var buttons = {};

				buttons[sensus.locale.get("action.autogroup.submit")] = function() {

					//Send ajax action
					var sIncludeSmartpointsToGroup = $(".include-smartpoints-to-group").is(":checked");
					sensus.util.ajaxaction.actionUrlAdress = sensus.settings.updateAutoGroup;
					var aTag = [{id: sensus.pages.systemsettings.tag.tagId , autoGroup: true }];
					var tagReq = new tagRequest(sensus.pages.systemsettings.tag.tagId, null, aTag, null, null, null, sIncludeSmartpointsToGroup, true);
					
					sensus.util.ajaxaction.data = {'tagRequest' : tagReq};
					
					sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.autogroup.success"), sensus.locale.get("action.autogroup.error"));
					
					
					$( "#action-dialog" ).remove();
					$("#bd").after("<div id='action-dialog' style='display: none'></div>")
					sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);

				};

				buttons[sensus.locale.get("action.tag.cancel")] = function() {
					$( "#action-dialog" ).remove();
					$("#bd").after("<div id='action-dialog' style='display: none'></div>")
					sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);
				};

				return buttons;
			})(),

			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('#action-dialog').empty().load(sensus.settings.autoExistingGroupInclude, function() {

					$("#action-dialog").removeClass("waiting");
					$("#auto-existing-group-message").append(sensus.locale.get("action.autoexistinggroup.message", sensus.pages.systemsettings.tag.tagName));
					$("#action-group-exists-name").append(sensus.locale.get("action.autogroup.groupNameVal", sensus.pages.systemsettings.tag.tagName));
					$("#action-group-exists-description").append(sensus.locale.get("action.autoexistinggroup.groupDescription", sensus.pages.systemsettings.tag.tagDescription));
					$("#include-smarpoints-msg").append(sensus.locale.get("action.autogroup.addSmartpoint", sensus.pages.systemsettings.tag.tagName, sensus.pages.systemsettings.tag.smartpointCount));

				});

				$("#action-dialog").dialog('open');

			},
			
			/**
			 * The function that will be called when the action dialog is closed.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			close : function() {
				
				$('.formError').remove();
				$('#tag-table input').prop('checked', false);
				
			}

		},

		/** The Dialog for Auto group a no Existing group*/
		autoNoGroupInclude : {

			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.autogroup.title"),

			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,

			/**
			 * The dialog width.
			 */
			width : 700,
			
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {

				var buttons = {};

				buttons[sensus.locale.get("action.autogroup.submit")] = function() {

					if ($("#control-group-description2").val().length > 150) {

						if (($("#control-group-description2").val() == "" || $("#control-group-description2").val().length == 0)) {

							$("#control-group-description2").validationEngine('showPrompt', sensus.locale.get("validation.addgroup.nonewgroupdescription"), 'red', '', true);

						} else {

							$("#control-group-description2").validationEngine('showPrompt', sensus.locale.get("groupcreate.form.description.maxlength"), 'red', '', true);

						}

					} else {

						$('.formError').remove();

						var sGroupDescription           = $("#control-group-description2").val();
						var sIncludeSmartpointsToGroup  = $(".include-smartpoints-to-group").is(":checked");
						// REQUEST GROUP
						var oGroup = {name: sensus.pages.systemsettings.tag.tagName, description: sGroupDescription},
							oGroupRequest = new groupRequest(sensus.pages.systemsettings.tag.tagName, sGroupDescription);

						$.ajax({
							dataType    : 'json',
							url : sensus.settings.createGroup,
							type : 'POST',
							data : $.toJSON({'groupRequest': oGroupRequest}),		
							async: false,
							contentType : "application/json; charset=utf-8",
							success: function(data) {
								
								if (!data.operationSuccess) {
									sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.autogroup.error"), "error");
									
								} else {
									sensus.util.ajaxaction.actionUrlAdress = sensus.settings.updateAutoGroup;
									var aTag = [{id: sensus.pages.systemsettings.tag.tagId , autoGroup: true }];
									var tagReq = new tagRequest(sensus.pages.systemsettings.tag.tagId, null, aTag, null, null, null, sIncludeSmartpointsToGroup, true);
									
									sensus.util.ajaxaction.data = {'tagRequest' : tagReq};
									
									sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.autogroup.success"), sensus.locale.get("action.autogroup.error"));
									sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);
								}
								
							}
						
						});
						
						$( "#action-dialog" ).remove();
					    $("#bd").after("<div id='action-dialog' style='display: none'></div>")
					    sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);
						
					}	
					
				};
				buttons[sensus.locale.get("action.tag.cancel")] = function() {
					   $( "#action-dialog" ).remove();
					   $("#bd").after("<div id='action-dialog' style='display: none'></div>")
					   sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);
					   
				};
				
				return buttons;
				
			})(),
			
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('#action-dialog').empty().load(sensus.settings.autoNoGroupInclude, function() {
					
					$("#action-dialog").removeClass("waiting");
					$("#auto-no-group-message").append(sensus.locale.get("action.autonogroup.message", sensus.pages.systemsettings.tag.tagName));
					$("#auto-no-group-name").append(sensus.locale.get("action.autogroup.groupNameVal", sensus.pages.systemsettings.tag.tagName));
					$("#include-smarpoints-msg").append(sensus.locale.get("action.autogroup.addSmartpoint", sensus.pages.systemsettings.tag.tagName, sensus.pages.systemsettings.tag.smartpointCount));
				
				});
				
				$("#action-dialog").dialog('open');
				
			},
			
			/**
			 * The function that will be called when the action dialog is closed.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			close : function() {
				
				$('.formError').remove();
				$('#tag-table input').prop('checked', false);
				
			}
		
		},
		
		
		/** The Dialog for suspend a Auto group*/
		suspendAutoGroupInclude : {
			
			/**
			 * The dialog title.
			 */
			title : sensus.locale.get("action.suspend.title"),
			
			/**
			 * Whether this dialog requires a smartpoint list.
			 */
			requiresSmartpoints : false,
			
			/**
			 * The dialog width.
			 */
			width : 300,
			
			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function() {
				var buttons = {};
				buttons[sensus.locale.get("action.suspend.submit")] = function() {
					
					//Send ajax action
					sensus.util.ajaxaction.actionUrlAdress = sensus.settings.updateAutoGroup;
					//sensus.util.ajaxaction.data = "id=" + sensus.pages.systemsettings.tag.tagId + "&autoGroup=" + false;
					var aTag = [{id: sensus.pages.systemsettings.tag.tagId , autoGroup: false }];
					var tagReq = new tagRequest(sensus.pages.systemsettings.tag.tagId, null, aTag, null, null, null, null, false);
					
					sensus.util.ajaxaction.data = {'tagRequest' : tagReq};
					
					sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.suspend.success"), sensus.locale.get("action.suspend.error"));
					$("#action-dialog").dialog('close');
					
				};
				buttons[sensus.locale.get("action.tag.cancel")] = function() {
					
					$("#action-dialog").dialog('close');
					sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);
					
				};
				
				return buttons;
				
			})(),
			
			/**
			 * The function that will be called when the action dialog is launched.
			 * 
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog) {
				
				$('#action-dialog').empty().load(sensus.settings.suspendAutoGroupInclude, function() {
					
					$("#action-dialog").removeClass("waiting");
					$("#suspend-auto-group").append(sensus.locale.get("action.suspend.message", sensus.pages.systemsettings.tag.tagName));
					
				});
				
				$("#action-dialog").dialog('open');
				
			}
		
		}
	}	
};
</script>