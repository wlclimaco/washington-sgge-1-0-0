<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.entidade.dialogSettings = {

		insert : function (iId, sType,sModelAction) {

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
						if(sType == 1){sUrl = "api/empresa/add"}else if(sType == 2){sUrl = "api/empresa/filial/add"}else{sUrl = "api/empresa/deposito/add"}
						pgsi.pages.entidade.form.ajaxCall(sUrl,iId,sType,sModelAction);

						$(this).dialog('close');

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {


					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("empresa/editView?locationId="+iId+"&userId=" + pgsi.settings.userContext.userId, function() {

						//$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>