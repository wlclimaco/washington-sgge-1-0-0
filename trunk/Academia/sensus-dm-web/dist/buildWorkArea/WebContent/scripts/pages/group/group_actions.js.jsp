<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
	
	<script type="text/javascript">
	sensus.pages.group.dialogSettings = {

		deleteGroups : {

			title : sensus.locale.get("commons.pages.confirmDelete"),

			width : 300,

			close : function () {

				sensus.pages.group.aSelectedGroups = null;
			},

			buttons : (function () {

				var oButtons = {};

				// Confirm Button
				oButtons[sensus.locale.get("commons.pages.delete")] = function () {

					var aIds 		= sensus.pages.group.fnGetSelectedGroups("id");
					var aGroupNames = sensus.pages.group.fnGetSelectedGroups("groupName");
					var oRequest	= {groups : aIds};

					var fnCallBack = function(oResponse) {

						if (oResponse.operationSuccess) {

							sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.addsgroup.delete"), "confirm");

							// Validations for change pagination when delete one or more groups of last page.
							var iStart;
							var oSettings = sensus.pages.group.groupTable.fnSettings();

							if (aIds.length <= 0) {

								// If exist just one group at last page and this group is deleted, the pagination back to previous page.
								if (((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0)) {
									iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
								}

							} else {

								// If all groups at last page is deleted, the pagination back to previous page.
								if (((oSettings._iRecordsDisplay - (aIds.length)) % $('.dataTables_length').find('select').val() === 0)) {
									iStart = (oSettings._iRecordsDisplay - (aIds.length)) - oSettings._iDisplayLength;
								}
							}

							sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable, iStart);

						} else {

							sensus.util.page.showMessage("messaging-main", sensus.locale.get("action.longRunningProcessDialog.error"), "error");
						}
					}

					$.ajaxValidator.fnDoCall(sensus.pages.group.api.remove, oRequest, false, fnCallBack);

					$(this).dialog('close');
				};

				// Cancel Button
				oButtons[sensus.locale.get("commons.pages.cancel")] = function() {

					$(this).dialog('close');
				};

				return oButtons;
			})(),

			action : function (actionDialog) {

				actionDialog.load("group/dialogDeleteGroup", function() {

					var aIds 		= sensus.pages.group.fnGetSelectedGroups("id");
					var	aGroupNames = sensus.pages.group.fnGetSelectedGroups("groupName");
					var	aSchedules 	= sensus.pages.group.fnGetScheduleByGroupIds(aIds);
					var	sGroupName 	= aGroupNames.join(", ");
					var aScheduleGroups = [];
					var sScheduleGroups='';
					var $deleteMessage = $("#delete-message", actionDialog);

					// Check if Scheduled
					if (aSchedules.length > 0) {

						// Delete Instructions Dialog
						$(actionDialog).parent().find("button:eq(0)").remove();

						//$("#delete-message", actionDialog).show().html(sensus.locale.get("group.page.deleteInstructionsMessage", sGroupName, sGroupName));

						actionDialog.dialog("option", "title", sensus.locale.get("group.page.deleteError"));

						var sScheduleListHtml = "";

						for (var i = 0; i < aSchedules.length; i = i + 1) {

							for (var x = 0; x < aSchedules[i].dmAction.groups.length; x = x + 1) {

								aScheduleGroups.push(aSchedules[i].dmAction.groups[x].name);
							}

							sScheduleListHtml += '<li><a class="alist" href="schedule">' + aSchedules[i].name + '</a></li>';
						}

						aScheduleGroups = sensus.pages.group.fnCompareTwoVectors(aScheduleGroups,aGroupNames);
						sScheduleGroups 	= aScheduleGroups.join(", ");
						$deleteMessage.show().html(sensus.locale.get("group.page.deleteInstructionsMessage", sScheduleGroups, sScheduleGroups));

						$('#ul-schedule-list', actionDialog).removeClass("hide").append(sScheduleListHtml);

					} else {

						// Delete Confirm Dialog
						$deleteMessage.show().html(sensus.locale.get("group.page.deletePermanently", sGroupName));
					}

					actionDialog.dialog('open');
				});
			}
		}
	}
	</script>

</sec:authorize>