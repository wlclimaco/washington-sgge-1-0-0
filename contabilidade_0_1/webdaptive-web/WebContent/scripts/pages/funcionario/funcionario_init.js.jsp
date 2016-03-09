<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{

		qat.pages.funcionario.funcionarioTable = $('#data_list').dataTable($.qat.table.setTable(
	{
		id 			: "#data_list",
		sAjaxSource : "api/funcionario/fetchall",
		bPreLoad	: true,
		sCheckbox : "id",

		ajax :
		{
			sObj		: "funcionarioList",
			oRequest	: qat.model.EmpresaInquiryRequest,
			fnRequest 	: {}
		},

		aoColumns :
		[
		{
			headerData 		: "CPF",
			order			: "name",
			mRender         : qat.pages.pessoa.fnCreateEmpresaNameLink,
			sDefaultContent : "",
			bSortable 		: false,
			sClass          : "name-col"
		},
		{
			headerData 		: "Nome funcionario",
			order			: "organization_column",
			mRender 		: qat.pages.pessoa.fnCreateNomeLink,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Endereco",
			order			: "phone_column",
			mRender 		: qat.pages.pessoa.fnEndereco,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Email",
			order			: "state_column",
			mRender 		: qat.pages.pessoa.fnEmail,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Telefone",
			order			: "country_column",
			mRender 		: qat.pages.pessoa.fnTelefone,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Profissão",
			order			: "city_column",
			mRender 		: qat.pages.pessoa.fnProfissao,
			mData	 		: "null",
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Salario",
			order			: "sdn_status_column",
			mRender 		: qat.pages.pessoa.fnSalario,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Eventos",
			order			: "phone_column",
			mRender 		: qat.pages.pessoa.fnEventos,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Beneficios",
			order			: "phone_column",
			mRender 		: qat.pages.pessoa.fnBeneficios,
			sDefaultContent : "",
			bSortable 		: false
		},
		{
			headerData 		: "Status",
			order			: "phone_column",
			mRender 		: qat.pages.pessoa.fnStatus,
			sDefaultContent : "",
			bSortable 		: false
		},
		],

		<c:choose>
			<c:when test="${not empty refresh}">
				aaData : "refresh",
			</c:when>
			<c:when test="${empty funcionarioList}">
				aaData : null,
		    </c:when>
		    <c:otherwise>
		    	aaData : ${funcionarioList},
		    </c:otherwise>
		</c:choose>

		oSettings :
		{
			sortEnum      	: "",
			iDefaultCol   	: 0
		},

		rowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

		},

		fnInitComplete: function (oSettings, json)
		{
			$('.dataTables_paginate:eq(0)').addClass('hide')
			$('.dataTables_info:eq(0)').addClass('hide')
			$('.dataTables_length:eq(0)').addClass('hide')

			$(".dataTables_length select").outerWidth(62).selectmenu({
				appendTo: ".content.list",
  				change: function( event, ui ) {
  					$('#data_list_length').find("select").val(ui.item.value);
  					$("#data_list_length").find("select").trigger("change");
  					$("#load").find(".dataTables_length").find("select").selectmenu("refresh" );
  				}
			});

			$.qat.listener.notify({
				eventName 	: "locationList",
				arguments 	: ["table"]
			});
		}
	}
	));

	var GiftModel = function(funcionario) {
	    var self = this;

	    self.funcionario = ko.observable(new funcionarioModule.FUNCIONARIO(funcionario));


		self.funcionario().enderecos([]);
		self.funcionario().salarios([]);
		for(var i = 0 ; i< funcionario.enderecos.length;i++){
			self.funcionario().enderecos.push(new enderecoModule.ENDERECO(funcionario.enderecos[i]));
		}

		for(var i = 0 ; i< funcionario.salarios.length;i++){
			self.funcionario().salarios.push(new salarioModule.SALARIO(funcionario.salarios[i]));
		}
		self.funcionario().documentos([]);
		for(var i = 0 ; i< funcionario.documentos.length;i++){
			self.funcionario().documentos.push(new documentoModule.DOCUMENTO(funcionario.documentos[i]));
		}
		self.funcionario().emails([]);
		for(var i = 0 ; i< funcionario.emails.length;i++){
			self.funcionario().emails.push(new emailModule.EMAIL(funcionario.emails[i]));
		}
		self.funcionario().telefones([]);
		for(var i = 0 ; i< funcionario.telefones.length;i++){
			self.funcionario().telefones.push(new telefoneModule.TELEFONE(funcionario.telefones[i]));
		}
		self.funcionario().bancos([]);
		for(var i = 0 ; i< funcionario.bancos.length;i++){
			self.funcionario().bancos.push(new bancoModule.BANCOPESSOA(funcionario.bancos[i]));
			self.funcionario().bancos()[i].bancoId(new bancoModule.BANCO(funcionario.bancos[i].bancoId));
			self.funcionario().bancos()[i].agenciaId(new agenciaModule.AGENCIA(funcionario.bancos[i].agenciaId));

			self.funcionario().bancos()[i].agenciaId().enderecos(qat.pages.funcionario.enderecoList(funcionario.bancos[i].agenciaId.enderecos))
		//	for(var i = 0 ; i< funcionario.bancos[i].enderecos.length;i++){
		//		self.funcionario().bancos[i].enderecos.push(new enderecoModule.ENDERECO(funcionario.bancos[i].enderecos[i]));
		//	}
		}

		self.funcionario().beneficios([]);
		for(var i = 0 ; i< funcionario.beneficios.length;i++){
			self.funcionario().beneficios.push(new beneficioModule.BENEFICIOPESSOA(funcionario.beneficios[i]));
			self.funcionario().beneficios()[i].benefId(new beneficioModule.BENEFICIO(funcionario.beneficios[i].benefId));
		}

		self.funcionario().eventosList([]);
		for(var i = 0 ; i< funcionario.eventosList.length;i++){
			self.funcionario().eventosList.push(new eventoModule.EVENTOPESSOA(funcionario.eventosList[i]));
			//self.funcionario().eventosList()[i].idEvent(new eventoModule.EVENTO(funcionario.eventosList[i].idEvent));
		}





		$.ajax({
	            url: WDHost + "qat-webdaptive/api/funcionario/add",
	            dataType: "json",
	            contentType: 'application/json',
	            type: "POST",
	            data: ko.toJSON( {funcionario :  self.funcionario()}),
	            processdata: true,

	            beforeSend: function () {
	             //   $.mobile.loading('show');
	            },

	            error: function (xhr, textStatus, errorThrown) {
	                console.log('Sorry!');
	            },

	            success: function (data) {

	               // $.mobile.loading('hide');
	                if (data.result!= '') {
	                  //  self.vendors(data.result);

	console.log(data)

	                } else {
						console.log(data)
	                //    self.vendors({something});

	                }
	            }
	        });

		/**
			$.ajax(WDHost + "qat-webdaptive/api/funcionario/add", {
	        data: {
	            funcionario: ko.toJSON(self.funcionario())
	        },
	        type: "POST",
	        dataType: 'json',
	        success: function(data) {
	           console.log(data)
	        }
	    });
	**/

	};

	var viewModel = new GiftModel(qat.pages.pessoa.createTeste());
	ko.applyBindings(viewModel);

	// Activate jQuery Validation
	$("form").validate({ submitHandler: viewModel.save });



	});
</script>