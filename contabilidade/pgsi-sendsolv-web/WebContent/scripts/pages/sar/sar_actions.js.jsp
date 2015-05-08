<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
	pgsi.pages.sar.dialogSettings = {

		dialogSARDetail : function (sTitle, iId,sType,sName,sKey) {

			return {

				title : sTitle,
				width : 780,

				close : function () {

				},

				buttons : (function () {

					var oButtons = {};

					// Close Button
					oButtons[$.pgsi.locale.get("commons.pages.cancel")] = function() {

						$("#action-dialog").dialog('close');
					};
					// Close Button
					oButtons[$.pgsi.locale.get("commons.pages.report")] = function() {

						pgsi.pages.sar.common.ajaxCallSuspiciousActivity("INSERT",null, iId,sType,sName,sKey)

					};

					return oButtons;

				})(),

				action : function (actionDialog) {

					actionDialog.load("sar/sar_dialog?id="+iId+"&type="+sType, function() {
						actionDialog.dialog('open');
						pgsi.pages.sar.dialog.form.initialForm(iId,sType,sName,sKey);
					});
				}
			}
		}

	}
</script>

</sec:authorize>