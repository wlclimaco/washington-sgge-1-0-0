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

	/**
	 * The Dialog Settings
	 */
	dialogSettings :
	{

		/**
		 * The Dialog for Auto group an Existing group
		 */
		autoExistingGroupInclude :
		{

			/** The dialog title. */
			title : $.sc.locale("action.autogroup.title"),

			/** Whether this dialog requires a smartpoint list.*/
			requiresSmartpoints : true,

			/** The dialog width */
			width : 700,

			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function()
			{

				var buttons = {};

				buttons[$.sc.locale("action.autogroup.submit")] = function()
				{

					var sUrl = "api/tag/updateautogroup";

					/** Send ajax action */
					var sIncludeSmartpointsToGroup = $(".include-smartpoints-to-group").is(":checked");

					$.sc.getJson(sUrl, {"id": sensus.pages.systemsettings.tag.tagId,"autoGroup" : true, "includeSmartpointsToGroup" : sIncludeSmartpointsToGroup}, false, null, $.sc.locale("action.autogroup.success"), false);

					$( "#action-dialog" ).remove();
					$("#bd").after("<div id='action-dialog' style='display: none'></div>")
					sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);

				};

				buttons[$.sc.locale("action.tag.cancel")] = function()
				{
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
			action : function(actionDialog)
			{

				$('#action-dialog').empty().load("tag/includeAutoExistingGroupDialog", function()
				{

					$("#action-dialog").removeClass("waiting");
					$("#auto-existing-group-message").append($.sc.locale("action.autoexistinggroup.message", sensus.pages.systemsettings.tag.tagName));
					$("#action-group-exists-name").append($.sc.locale("action.autogroup.groupNameVal", sensus.pages.systemsettings.tag.tagName));
					$("#action-group-exists-description").append($.sc.locale("action.autoexistinggroup.groupDescription", sensus.pages.systemsettings.tag.tagDescription));
					$("#include-smarpoints-msg").append($.sc.locale("action.autogroup.addSmartpoint", sensus.pages.systemsettings.tag.tagName, sensus.pages.systemsettings.tag.smartpointCount));

				});

				$("#action-dialog").dialog('open');

			},

			/**
			 * The function that will be called when the action dialog is closed.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			close : function()
			{

				$('.formError').remove();
				$('#tag-table input').prop('checked', false);

			}

		},

		/**
		 * The Dialog for Auto group a no Existing group
		 */
		autoNoGroupInclude :
		{

			/** The dialog title */
			title : $.sc.locale("action.autogroup.title"),

			/** Whether this dialog requires a smartpoint list. */
			requiresSmartpoints : false,

			/** The dialog width */
			width : 700,

			/**
			 * The dialog buttons (submit and cancel).
			 */
			buttons : (function()
			{

				var buttons = {};

				buttons[$.sc.locale("action.autogroup.submit")] = function()
				{

					/** Make sure the input value has less than 150 characters */
					if ($("#control-group-description2").val().length > 150)
					{

						$("#control-group-description2").validationEngine('showPrompt', $.sc.locale("groupcreate.form.description.maxlength"), 'red', '', true);

					}
					else
					{

						$('.formError').remove();
						var sUrl = "api/group/insert";

						var sGroupDescription           = $("#control-group-description2").val();
						var sIncludeSmartpointsToGroup  = $(".include-smartpoints-to-group").is(":checked");

						var request = new groupRequest(sensus.pages.systemsettings.tag.tagName, sGroupDescription);

						var data = $.sc.getJson(sUrl, request, false, null, $.sc.locale("action.autogroup.success"), false);

						if (!data.operationSuccess)
						{

							$.sc.showMessage("messaging-main", $.sc.locale("action.autogroup.group.exists"), "error");

						}
						else
						{

							var sUrl = "api/tag/updateautogroup";
							$.sc.getJson(sUrl, {"id": sensus.pages.systemsettings.tag.tagId,"autoGroup" : true, "includeSmartpointsToGroup" : sIncludeSmartpointsToGroup}, false, null, $.sc.locale("action.autogroup.success"), false);
							sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);

						}

						/** remove Dialog and reload screen */
						$( "#action-dialog" ).remove();
						$("#bd").after("<div id='action-dialog' style='display: none'></div>")
						sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);
					}
				};

				buttons[$.sc.locale("action.tag.cancel")] = function()
				{
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
			action : function(actionDialog)
			{

				$('#action-dialog').empty().load("tag/includeAutoNoGroupDialog", function()
			    {

					$("#action-dialog").removeClass("waiting");
					$("#auto-no-group-message").append($.sc.locale("action.autonogroup.message", sensus.pages.systemsettings.tag.tagName));
					$("#auto-no-group-name").append($.sc.locale("action.autogroup.groupNameVal", sensus.pages.systemsettings.tag.tagName));
					$("#include-smarpoints-msg").append($.sc.locale("action.autogroup.addSmartpoint", sensus.pages.systemsettings.tag.tagName, sensus.pages.systemsettings.tag.smartpointCount));

				});

				$("#action-dialog").dialog('open');

			},

			/**
			 * The function that will be called when the action dialog is closed.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			close : function()
			{

				$('.formError').remove();
				$('#tag-table input').prop('checked', false);

			}

		},


		/**
	     * The Dialog for suspend a Auto group
	     */
		suspendAutoGroupInclude :
		{

			/** The dialog title */
			title : $.sc.locale("action.suspend.title"),

			/** Whether this dialog requires a smartpoint list*/
			requiresSmartpoints : false,

			/** The dialog width */
			width : 300,

			/** The dialog buttons (submit and cancel) */
			buttons : (function()
			{
				var buttons = {};
				buttons[$.sc.locale("action.suspend.submit")] = function()
				{

					/** Send ajax action */
					var sUrl = "api/tag/updateautogroup";

					$.sc.getJson(sUrl, {"id": sensus.pages.systemsettings.tag.tagId,"autoGroup" : false, "includeSmartpointsToGroup" : false}, false, null, $.sc.locale("action.suspend.success"), false);
					$.sc.closeActionDialog("#action-dialog");

				};
				buttons[$.sc.locale("action.tag.cancel")] = function()
				{
					$.sc.closeActionDialog("#action-dialog");
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
			action : function(actionDialog)
			{

				$('#action-dialog').empty().load("tag/suspendAutoGroupDialog", function()
				{

					$("#action-dialog").removeClass("waiting");
					$("#suspend-auto-group").append($.sc.locale("action.suspend.message", sensus.pages.systemsettings.tag.tagName));

				});

				$("#action-dialog").dialog('open');

			}
		}
	}
};
</script>