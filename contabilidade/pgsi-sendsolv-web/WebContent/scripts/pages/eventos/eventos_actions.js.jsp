<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.eventos.dialogSettings = {

		insert : function (iId, sName,sModelAction) {

			return {
				title : $.pgsi.locale.get("commons.dialog.insert.title"),
				width : 800,
				height: 450,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.insert")] = function () {

						if(iId == 0 ){
							pgsi.pages.eventos.fnRequestEventos('INSERT');
						}else{
							pgsi.pages.eventos.fnRequestEventos('UPDATE');
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

					actionDialog.load("funcionario/eventos/view?userId=" + pgsi.settings.userContext.userId+"&eventosId="+iId, function() {


						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>