<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.pessoa.dialogSettings = {

		insertCliente : function (iId, sType,sModelAction) {

			return {
				title : "Adicionar Cliente",
				width : 800,
				height: 600,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.insert")] = function () {



						$(this).dialog('close');

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {


					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("cliente/edit?clienteId="+iId+"&userId=" + pgsi.settings.userContext.userId, function() {

						//$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>