<script type="text/javascript">
sensus.pages.academia.getrequestInsertAcademia = function(errorMessage, bRefresh) {

	return new academiaRequest(
		$('#academiaId').val(),
		$('#academia').val(),
		$('#logradouro').val(),
		$('#numero').val(),
		$('#bairro').val(),
		$('#cidade').val(),
		$('#cep').val(),
		$('#telef').val(),
		$('#dataini').val(),
		$('#datafin').val(),
		$('#atual').prop('checked')
	);

};

sensus.pages.academia.Clear = function() {

		$('#academiaId').text();
		$('#academia').text();
		$('#logradouro').text();
		$('#numero').text();
		$('#bairro').text();
		$('#cidade').text();
		$('#cep').text();
		$('#telef').text();
		$('#dataini').text();
		$('#datafin').text();
		$('#atual').attr("checked", false);


};


	sensus.pages.academia.dialogSettings =
	{

		academiaDialogInsert :
		{

			/** The dialog title */
			title : $.sc.locale("action.suspend.title"),

			/** Whether this dialog requires a smartpoint list*/
			requiresSmartpoints : false,

			/** The dialog width */
			width : 700,

			/** The dialog buttons (submit and cancel) */
			// The dialog buttons (submit and cancel).
		buttons : [{
				id : "delete-group-submit",
				text : $.sc.locale("action.deletegroup.submit"),
				click : function() {

					var fnCallBack = function(data){
					alert('11');
						if (data.operationSuccess){
						//	sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
						}
					};

			//		sIdUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;

					$(this).dialog('close');
					$.sc.startProgressBar(null,true);

					oRequest = sensus.pages.academia.getrequestInsertAcademia();

					$.sc.getJson('api/academia/insert',oRequest, false, fnCallBack);
					//sensus.widgets.datatable.clearSelects();

				}
			}, {
				id: "delete-group-cancel",
				text :$.sc.locale("action.deletegroup.cancel"),
				click : function() {
					$.sc.closeActionDialog("#action-dialog");
				}
		}],

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog)
			{

				$('#action-dialog').empty().load("academia/academiacreate", function()
				{

					$("#action-dialog").removeClass("waiting");
				//	$("#suspend-auto-group").append($.sc.locale("action.suspend.message", sensus.pages.systemsettings.tag.tagName));
				sensus.pages.academia.Clear();

				});
				$("#action-dialog").dialog('open');

			}
		},
				academiaDialog :
		{

			/** The dialog title */
			title : $.sc.locale("action.suspend.title"),

			/** Whether this dialog requires a smartpoint list*/
			requiresSmartpoints : false,

			/** The dialog width */
			width : 700,

			/** The dialog buttons (submit and cancel) */
			// The dialog buttons (submit and cancel).
		buttons : [{
				id : "delete-group-submit",
				text : $.sc.locale("action.deletegroup.submit"),
				click : function() {

					var fnCallBack = function(data){
					alert('11');
						if (data.operationSuccess){
						//	sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
						}
					};

			//		sIdUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;

					$(this).dialog('close');
					$.sc.startProgressBar(null,true);

					oRequest = sensus.pages.academia.getrequestInsertAcademia();

					$.sc.getJson('api/academia/update',oRequest, false, fnCallBack);
					//sensus.widgets.datatable.clearSelects();

				}
			}, {
				id: "delete-group-cancel",
				text :$.sc.locale("action.deletegroup.cancel"),
				click : function() {
					$.sc.closeActionDialog("#action-dialog");
				}
		}],

			/**
			 * The function that will be called when the action dialog is launched.
			 *
			 * @param actionDialog
			 *            a reference to the actionDialog objext
			 */
			action : function(actionDialog)
			{

				$('#action-dialog').empty().load("academia/academiacreate", function()
				{

					$("#action-dialog").removeClass("waiting");
				//	$("#suspend-auto-group").append($.sc.locale("action.suspend.message", sensus.pages.systemsettings.tag.tagName));
				sensus.pages.academia.fnFillAcademia();

				});
				$("#action-dialog").dialog('open');

			}
		}
	};
</script>