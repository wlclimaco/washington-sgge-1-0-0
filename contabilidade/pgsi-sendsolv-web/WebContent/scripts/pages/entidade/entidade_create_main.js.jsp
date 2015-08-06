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
			ajaxCall : function(sUrlAdress,iId,sType, mainModelAction) {

				// Remove input masks
			//	pgsi.util.page.business.form.maskFields.fnUnmask();
				// Validate the form
				//var bValidForm = pgsi.pages.entidade.form.validator.form();

				//if (!bValidForm)
				//{
				//	pgsi.util.page.business.form.maskFields.fnMask();
				//	return false;
				//}

				var request = pgsi.pages.entidade.form.fnFillRequestObject(iId,sType,mainModelAction);

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
					sUrl : 'api/empresa/add',
					oRequest : request,
					fnCallback : fnCallback
				});
			},

			fnFillEmpresa : function(iId,sType,sModelAction) {

				//pgsi.util.page.form.fnInitForm();
				sModelAction = "INSERT"
				// fill location-specific fields
				var oEmpresa = new Empresa();
						if(sType != 1){
							oEmpresa.entidadeId = iId;
							oEmpresa.entidadeEnumValue = sType;
						}
						oEmpresa.id 		= parseInt($('#codigo').val(),10);
						oEmpresa.nome		= $('#nome').val();
						oEmpresa.regime  	= {id : parseInt($('#regime').val(),10)};
						oEmpresa.enderecos  = [pgsi.pages.endereco.fnCreateRequest(sModelAction)];
						oEmpresa.documentos	= pgsi.pages.documento.fnCreateRequest(sModelAction);
						oEmpresa.emails		= pgsi.pages.email.fnCreateRequest(sModelAction);
						oEmpresa.telefones	= pgsi.pages.telefone.fnCreateRequest(sModelAction);
						oEmpresa.socios		= pgsi.pages.socio.fnCreateRequest(sModelAction);
						oEmpresa.cnaes		= pgsi.pages.cnae.fnCreateRequest(sModelAction);
						oEmpresa.modelAction = sModelAction;



				return oEmpresa;
			},

			fnFillRequestObject : function(iId,sType,sModelAction){
				var request = new EmpresaMaintenanceRequest();

				// fill common location fields - method returns basic business objects (i.e things locations and orgs have in common)
				request.empresa = pgsi.pages.entidade.form.fnFillEmpresa(iId,sType,sModelAction);


				return request;
			}

		};
	</script>

</sec:authorize>