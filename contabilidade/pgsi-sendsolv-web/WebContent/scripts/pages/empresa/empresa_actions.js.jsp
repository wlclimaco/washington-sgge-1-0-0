<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.empresa.dialogSettings = {

		insert : function (iId, sName) {

			return {
				title : $.pgsi.locale.get("commons.dialog.delete.title"),
				width : 800,
				height: 600,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.remove")] = function () {

						var aIds;

						var fnCallBack = function(oResponse) {

							if (oResponse.operationSuccess == true) {

								// Validations for change pagination when delete one or more groups of last page.
								var iStart;
								var oSettings = pgsi.pages.location.locationTable.fnSettings();

									// If exist just one group at last page and this group is deleted, the pagination back to previous page.
									if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0))
									{
										iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
									}

								$.pgsi.table.reloadTable({
									table 		: pgsi.pages.location.locationTable,
									iStart 		: iStart
								});
							}else{
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title","Location"),true);
							}
						}

						var oRequest = new LocationMaintenanceRequest({location : {id : iId }});

						$.pgsi.ajax.post({
							 sUrl 		: 'api/location/delete',
							 oRequest 	: oRequest,
							 fnCallback : fnCallBack
						});

						$(this).dialog('close');
					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("empresa/editView?userId=" + pgsi.settings.userContext.userId+"&locationId=2", function() {

						$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>