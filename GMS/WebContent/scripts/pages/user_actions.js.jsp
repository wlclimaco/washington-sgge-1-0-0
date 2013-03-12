
<script type="text/javascript">
/**
 * The main namespace for the user action dialog configurations. Note that the
 * buttons are initialized in a function because their names must be
 * internationalized and cannot be used in the simple JSON notation because
 * property names must be literals.
 *
 * The name of each configuration matches the value behind each option from the
 * action droplist and the action button.
 */
sensus.pages.user.dialogSettings = {

	/**
	 * Delete User
	 */
	deleteUser : {

		// The dialog title.
		title : $.sc.locale("action.deleteuser.title"),

		// Whether this dialog requires a smartpoint list.
		requiresSmartpoints : false,

		// The dialog width.
		width : 300,

		// The dialog buttons (submit and cancel).
		buttons : [{
				id     : "delete-user-submit",
				text   : $.sc.locale("action.deleteuser.submit"),
				click  : function() {

					var nIds = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
					var sIdUrl;
					var fnCallBack = function(data){
						if (data.operationSuccess){
							sensus.widgets.datatable.reloadTable(sensus.pages.user.userTable);
						}
					};
					sIdUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;
					var oRequest = new userRequest(null, null, null, null, null, null, null, null, null, sensus.widgets.datatable.isAllRows, sIdUrl);

					$(this).dialog('close');
					sensus.util.page.startProgressBar(null,true);

					sensus.util.ajaxaction.sendActionDefault($.sc.locale("action.deleteuser.success"), $.sc.locale("action.deleteuser.error"));

					for (var i = 0; i < nIds.length; i++){
						nIds[i] = parseInt(nIds[i]);
					}

					$.sc.getJson('api/user/delete', {"selectionPaginationIds": nIds, "paginationAllSelected":  sensus.widgets.datatable.isAllRows}, false, fnCallBack, null, null, null, null);

					sensus.widgets.datatable.clearSelects();

				}

			}, {
				id     : "delete-user-cancel",
				text   : $.sc.locale("action.deleteuser.cancel"),
				click  : function() {
					$("#action-dialog").dialog('close');
				}
		}],

		/**
		 * The function that will be called when the action dialog is launched.
		 *
		 * @param actionDialog
		 *            a reference to the actionDialog objext
		 */
		action : function(actionDialog) {
			sensus.util.page.clearFormElements('delete_user_form');
			actionDialog.empty().load('user/userDeleteInclude', function() {
				actionDialog.removeClass("waiting");
				sensus.pages.user.renderDeleteMessage();
			});
			actionDialog.dialog('open');

		}

	}
};
</script>