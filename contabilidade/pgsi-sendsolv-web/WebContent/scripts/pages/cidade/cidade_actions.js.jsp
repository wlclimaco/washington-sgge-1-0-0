<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.cidade.dialogSettings = {

		insert : function (iId, sName,sModelAction) {

			return {
				title : $.pgsi.locale.get("commons.dialog.insert.title"),
				width : 600,
				height: 450,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.insert")] = function () {


						if(iId == 0 ){
							pgsi.pages.cidade.fnRequestCidade('INSERT');
						}else{
							pgsi.pages.cidade.fnRequestCidade('UPDATE');
						}
						$(this).dialog('close');

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("cliente/view/cidade?userId=" + pgsi.settings.userContext.userId+"&cidadeId="+iId, function() {

						$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		},

		delete : function (iId, sName,sModelAction) {

			return {
				title : "Deletar Cidade",
				width : 400,
				height: 400,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons["Deletar"] = function () {

						pgsi.pages.cidade.fnDeleteCidade(iId);

						$(this).dialog('close');

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("cidade/delete", function() {

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>