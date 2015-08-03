<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.cnae.dialogSettings = {

		insert : function (iId, sName,sModelAction) {

			return {
				title : "Adicionar CNAE",
				width : 1024,
				height: 400,

				close : function () {},

				buttons : (function () {

					var oButtons = {};

					// Confirm Button
					oButtons[$.pgsi.locale.get("commons.dialog.insert")] = function () {
						debugger
					var oCnae = [];
						$.each( $('#mytable tbody').find('input'), function( i, val ) {

							  if ($(this).is(":checked")) {
								aCnae = new Cnae();
								aCnae.id = $(this).attr('id');
								aCnae.emprId = parseInt($.address.parameter("locationId"),10)
								aCnae.parentId = parseInt($.address.parameter("locationId"),10)
								aCnae.modelAction = 'INSERT'

								aCnaeRel = new CnaeRel();
								aCnaeRel.idCnae = aCnae;
								aCnaeRel.parentId = parseInt($.address.parameter("locationId"),10)
								aCnaeRel.modelAction = 'INSERT';
								oCnae.push(aCnaeRel);
							  }

						});

						$.pgsi.ajax.post({
							sUrl 		: "api/empresa/add",
							oRequest 	: {empresa:{id: parseInt($.address.parameter("locationId"),10) ,modelAction :"NONE", cnaes:oCnae}},
							fnCallback  : null
					   });

					};

					// Cancel Button
					oButtons[$.pgsi.locale.get("commons.dialog.cancel")] = function() {

						$(this).dialog('close');
					};

					return oButtons;
				})(),

				action : function (actionDialog) {

					actionDialog.load("cnae/create", function() {

						$('#selected', actionDialog).removeClass("hide").append(""+sName+"");

						actionDialog.dialog('open');
					});
				}
			}
		}
	}
</script>

</sec:authorize>