<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.sendsolv.error.dialogSettings = {

		errorDialog : function (sUrl,oRequest,sUrlAction,fnCallBack,sTitleButton) {

			return {
				width : 485,
				title : sTitle,

				close : function () {

				},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get(sTitleButton)] = function () {

						$.pgsi.ajax.post({
							 sUrl       : sUrl,
							 oRequest   : oRequest,
							 fnCallback : fnCallBack
						});

						$("#action-dialog").dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					if(sUrlAction.indexOf("userId") == -1){
						if(sUrlAction.indexOf("?") != -1){
							sUrlAction += "&userId=" + pgsi.settings.userContext.userId
						}else{
							sUrlAction += "?userId=" + pgsi.settings.userContext.userId
						}
					}
					actionDialog.load(sUrlAction, function() {

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>