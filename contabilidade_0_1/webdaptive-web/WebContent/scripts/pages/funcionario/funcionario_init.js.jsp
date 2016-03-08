<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{
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
			$.ajax(WDHost + "qat-webdaptive/api/empresa/add", {
	        data: {
	            empresa: ko.toJSON(self.empresa())
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