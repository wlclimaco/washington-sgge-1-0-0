<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>


	<script type="text/javascript">
		qat.pages.empresa.form = {



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
				qat.util.page.business.form.maskFields.fnUnmask();
				// Validate the form
				var bValidForm = qat.pages.empresa.form.validator.form();

				if (!bValidForm)
				{
					qat.util.page.business.form.maskFields.fnMask();
					return false;
				}

				var request = qat.pages.empresa.form.fnFillRequestObject(mainModelAction);

				var fnCallback = function (oResponse){
					if (oResponse.operationSuccess == true) {
						$(this).dialog('close');
						if (!$.qat.isNullOrUndefined(oResponse.empresaList[0]) && !$.qat.isNullOrUndefined(oResponse.empresaList[0].id)){
							var nLocationId = oResponse.empresaList[0].id;
							$.qat.pageLoader.load({
								url: "empresa/view?locationId=" + nLocationId,
								$content: $("#load"),
								bStartProgressBar : false
							});
						}
					}
					else {
						qat.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.qat.locale.get("commons.dialog.error.title"),true);
					}

				};

				// Insert the Location
				$.qat.ajax.post({
					sUrl : sUrlAdress,
					oRequest : request,
					fnCallback : fnCallback
				});
			},

			fnFillEmpresa : function(data,sModelAction) {

				qat.util.page.form.fnInitForm();

				// fill location-specific fields
				var oEmpresa = new Empresa(
						id 			: parseInt($('#codigo').val(),10),
						nome		: $('#nome').val(),
						regine  	: qat.pages.regime.fnCreateRequest(sModelAction),
						enderecos   : qat.pages.endereco.fnCreateRequest(sModelAction),
						documentos	: qat.pages.documento.fnCreateRequest(sModelAction),
						emails		: qat.pages.email.fnCreateRequest(sModelAction),
						Telefones	: qat.pages.telefone.fnCreateRequest(sModelAction),
						socios		: qat.pages.socio.fnCreateRequest(sModelAction),
						cnaes		: qat.pages.cnae.fnCreateRequest(sModelAction),
						sModelAction: sModelAction
				)
				return oEmpresa;
			},

			fnFillRequestObject : function(sModelAction){
				var request = new EmpresaMaintenanceRequest();

				// fill common location fields - method returns basic business objects (i.e things locations and orgs have in common)
				request.empresa = qat.pages.empresa.form.fnFillEmpresa(sModelAction);


				return request;
			}

		};
	</script>
