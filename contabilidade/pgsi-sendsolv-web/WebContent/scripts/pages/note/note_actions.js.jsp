<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.note.dialogSettings = {

		insert : function (iId, sName,sModelAction) {

			return {
				title : Inserir Note,
				width : 445,
				height: 140,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.insert")] = function () {

						var sUrl = "";
						if(sModelAction == "insert")
						{
							sUrl = "api/entidade/insert"
						}
						else
						{
							sUrl = "api/entidade/update"
						}
						pgsi.pages.note.form.fnAjaxCallInsertUpdateNote(sUrl,sModelAction);

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("note/create", function() {

						$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>