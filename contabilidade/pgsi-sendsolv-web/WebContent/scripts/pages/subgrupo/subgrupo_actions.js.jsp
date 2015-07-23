<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.subgrupo.dialogSettings = {

		insert : function (iId, sName,sModelAction) {

			return {
				title : $.pgsi.locale.get("commons.dialog.insert.title"),
				width : 800,
				height: 600,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.insert")] = function () {

						var sUrl = "";
						if(sModelAction == "insert")
						{
							sUrl = "api/subgrupo/insert"
						}
						else
						{
							sUrl = "api/subgrupo/update"
						}
						pgsi.pages.subgrupo.form.ajaxCall(sUrl,sModelAction);

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("subgrupo/editView?userId=" + pgsi.settings.userContext.userId+"&locationId=2", function() {

						$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>