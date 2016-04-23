<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

	<script type="text/javascript">
		qat.pages.entidade.form = {



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
			ajaxCall : function(sUrlAdress,iId,sType, mainModelAction) {

				// Remove input masks
			//	qat.util.page.business.form.maskFields.fnUnmask();
				// Validate the form
				//var bValidForm = qat.pages.entidade.form.validator.form();

				//if (!bValidForm)
				//{
				//	qat.util.page.business.form.maskFields.fnMask();
				//	return false;
				//}

				var request = qat.pages.entidade.form.fnFillRequestObject(iId,sType,mainModelAction);

				var fnCallback = function (oResponse){
					if (oResponse.operationSuccess == true) {
						$(this).dialog('close');
						if (!$.qat.isNullOrUndefined(oResponse.entidadeList[0]) && !$.qat.isNullOrUndefined(oResponse.entidadeList[0].id)){
							var nLocationId = oResponse.entidadeList[0].id;
							$.qat.pageLoader.load({
								url: "entidade/view?locationId=" + nLocationId,
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
					fnCallback : qat.pages.empresa.view.fnCallbackRefleshPageEmpresa
				});
			},

			fnFillEmpresa : function(iId,sType,sModelAction) {

				//qat.util.page.form.fnInitForm();
				sModelAction = "INSERT"
				// fill location-specific fields
				if(sType == 1){
					var oEmpresa = new Empresa();
					oEmpresa.socios		= qat.pages.socio.fnCreateRequest(sModelAction);
				}else if(sType == 2){
					var oEmpresa = new Filial();

				}else{
					var oEmpresa = new Deposito();
				}
						if(sType != 1){
							oEmpresa.entidadeId = iId;
							oEmpresa.entidadeEnumValue = sType;
						}
						oEmpresa.id 		= parseInt($('#codigo').val(),10);
						oEmpresa.nome		= $('#nome').val();
						oEmpresa.regime  	= qat.pages.regime.fnCreateRequest(sModelAction),
						oEmpresa.enderecos  = qat.pages.endereco.fnCreateRequest(sModelAction);
						oEmpresa.documentos	= qat.pages.documento.fnCreateRequest(sModelAction);
						oEmpresa.emails		= qat.pages.email.fnCreateRequest(sModelAction);
						oEmpresa.telefones	= qat.pages.telefone.fnCreateRequest(sModelAction);
						oEmpresa.cnaes		= qat.pages.cnae.fnCreateRequest(sModelAction);
						oEmpresa.modelAction = sModelAction;



				return oEmpresa;
			},

			fnFillRequestObject : function(iId,sType,sModelAction){
				var request = new EmpresaMaintenanceRequest();

				// fill common location fields - method returns basic business objects (i.e things locations and orgs have in common)


				if(sType == 1){
					request.empresa = qat.pages.entidade.form.fnFillEmpresa(iId,sType,sModelAction);
				}else if(sType == 2){
					request.filial = qat.pages.entidade.form.fnFillEmpresa(iId,sType,sModelAction);
				}else{
					request.deposito = qat.pages.entidade.form.fnFillEmpresa(iId,sType,sModelAction);
				}


				return request;
			}

		};
	</script>
