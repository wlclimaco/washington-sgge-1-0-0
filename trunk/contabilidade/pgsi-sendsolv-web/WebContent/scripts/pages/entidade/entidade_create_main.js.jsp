<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

	<script type="text/javascript">
		pgsi.pages.entidade.form = {



			/**
			 * Validate the fields required at Location Form
			 */
			validator : $("#business-form").validate({
				ignore : "",
				invalidHandler : function(form, validator) {
					$.each(validator.errorList, function(index, value) {
						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
							else {
							$(value.element).addClass("error");
						}
					});
				},
				rules: {
					sic: {
						SIC:18
					},
					organizationNaics: {
						NAICS:18
					}
				}
			}),

			/**
			 * Ajax call that will submit the request ajax
			 *
			 * @param {String} sUrlAdress
			 * 			The URL to post
			 * @param {String} sMessage
			 * 			Message that will display after the ajax call
			 */
			ajaxCall : function(sUrlAdress, mainModelAction) {

				// Remove input masks
				pgsi.util.page.business.form.maskFields.fnUnmask();
				// Validate the form
				var bValidForm = pgsi.pages.entidade.form.validator.form();

				if (!bValidForm)
				{
					pgsi.util.page.business.form.maskFields.fnMask();
					return false;
				}

				var request = pgsi.pages.entidade.form.fnFillRequestObject(mainModelAction);

				var fnCallback = function (oResponse){
					if (oResponse.operationSuccess == true) {
						$(this).dialog('close');
						if (!$.pgsi.isNullOrUndefined(oResponse.entidadeList[0]) && !$.pgsi.isNullOrUndefined(oResponse.entidadeList[0].id)){
							var nLocationId = oResponse.entidadeList[0].id;
							$.pgsi.pageLoader.load({
								url: "entidade/view?locationId=" + nLocationId,
								$content: $("#load"),
								bStartProgressBar : false
							});
						}
					}
					else {
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}

				};

				// Insert the Location
				$.pgsi.ajax.post({
					sUrl : sUrlAdress,
					oRequest : request,
					fnCallback : fnCallback
				});
			},

			fnFillEmpresa : function(data,sModelAction) {

				pgsi.util.page.form.fnInitForm();

				// fill location-specific fields
				var oEmpresa = new Empresa(
						id 			: parseInt($('#codigo').val(),10),
						nome		: $('#nome').val(),
						regine  	: {id : parseInt($('#regime').val(),10)},
						enderecos   : pgsi.pages.endereco.fnCreateRequest(sModelAction),
						documentos	: pgsi.pages.documento.fnCreateRequest(sModelAction),
						emails		: pgsi.pages.email.fnCreateRequest(sModelAction),
						Telefones	: pgsi.pages.telefone.fnCreateRequest(sModelAction),
						socios		: pgsi.pages.socio.fnCreateRequest(sModelAction),
						cnaes		: pgsi.pages.cnae.fnCreateRequest(sModelAction),
						sModelAction: sModelAction
				)
				return oEmpresa;
			},

			fnFillRequestObject : function(sModelAction){
				var request = new EmpresaMaintenanceRequest();

				// fill common location fields - method returns basic business objects (i.e things locations and orgs have in common)
				request.entidade = pgsi.pages.entidade.form.fnFillEmpresa(sModelAction);


				return request;
			}

		};
	</script>

</sec:authorize>