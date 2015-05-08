<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.organization.dialogSettings = {

		deleteOrganizations : function (iId,sName) {

			return {
				title : $.pgsi.locale.get("pages.organization.dialog.title"),
				width : 300,

				close : function () {

					//sensus.pages.group.aSelectedGroups = null;
				},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.pages.delete")] = function () {
						var aIds;

						var fnCallBack = function(oResponse) {

							if (oResponse.operationSuccess == true) {

								// Validations for change pagination when delete one or more groups of last page.
								var iStart;
								var oSettings = pgsi.pages.organization.organizationTagle.fnSettings();

									// If exist just one group at last page and this group is deleted, the pagination back to previous page.
									if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
										iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
									}

								$.pgsi.table.reloadTable({
									table 		: pgsi.pages.organization.organizationTagle,
									iStart 		: iStart
								});
							}else{
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
							}
						}

						var oRequest = new OrganizationMaintenanceRequest({organization : {id : iId }});

						$.pgsi.ajax.post({
							 sUrl : 'api/organization/delete',
							 oRequest : oRequest,
							 fnCallback : fnCallBack
						});

						$(this).dialog('close');
					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("pages.dialog.cancel")] = function() {
						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("organization/dialogDeleteOrganization?userId=" + pgsi.settings.userContext.userId, function() {

						$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>