<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

	<script type="text/javascript">

$(document).ready(function()
{
	var GiftModel = function(empresa) {
    var self = this;

    self.empresa = ko.observable(new empresaModule.EMPRESA(empresa));

	self.empresa().regime(new regimeModule.REGIME(empresa.regime));
	self.empresa().enderecos([]);
	for(var i = 0 ; i< empresa.enderecos.length;i++){
		self.empresa().enderecos.push(new enderecoModule.ENDERECO(empresa.enderecos[i]));
	}
	self.empresa().documentos([]);
	for(var i = 0 ; i< empresa.documentos.length;i++){
		self.empresa().documentos.push(new documentoModule.DOCUMENTO(empresa.documentos[i]));
	}
	self.empresa().emails([]);
	for(var i = 0 ; i< empresa.emails.length;i++){
		self.empresa().emails.push(new emailModule.EMAIL(empresa.emails[i]));
	}
	self.empresa().telefones([]);
	for(var i = 0 ; i< empresa.telefones.length;i++){
		self.empresa().telefones.push(new telefoneModule.TELEFONE(empresa.telefones[i]));
	}
	self.empresa().cnaes([]);
	for(var i = 0 ; i< empresa.cnaes.length;i++){
		self.empresa().cnaes.push(new cnaeModule.CNAEPESSOA(empresa.cnaes[i]));
	}
	self.empresa().socios([]);
	for(var i = 0 ; i< empresa.socios.length;i++){
		self.empresa().socios.push(new socioModule.SOCIO(empresa.socios[i]));
	}
	self.empresa().planoList([]);
	for(var i = 0 ; i< empresa.planoList.length;i++){
		self.empresa().planoList.push(new planoModule.PLANO(empresa.planoList[i]));
	}
	self.empresa().usuarioList([]);
	for(var i = 0 ; i< empresa.usuarioList.length;i++){
		self.empresa().usuarioList.push(new usuarioModule.USUARIO(empresa.usuarioList[i]));
	}



    self.addGift = function() {
        self.empresa().telefones.push( new telefoneModule.TELEFONE({
            id: "",
            ddd: ""
        }));
    };

	self.addEmail = function() {
        self.empresa().emails.push( new emailModule.EMAIL({
            id: "",
            emailTypeEnumValue: "",
			email: ""
        }));
    };


	self.addEndereco = function() {
        self.empresa().enderecos.push( new enderecoModule.ENDERECO({
			id : "",
            typeValue : "",
            processId : "",
            logradouro : "",
			cep : "",
            bairro : "",
            numero : "",
			complemento : "",
            enderecoTypeValue : "",
            createUser : "",
        	createDateUTC : "",
        	modifyUser : "",
        	modifyDateUTC : "",
        	cidade : ""
		}));
	};

    self.removeGift = function(gift) {
        $.ajax({
			type		: "POST",
			dataType    : 'json',
			url			: WDHost + "qat-webdaptive/api/empresa/add",
			username	: wd.session.userId,
			password	: wd.session.userPassword,
			data		: $.toJSON(self.empresa()),
			contentType : "application/json; charset=utf-8",
			//timeout		: 5000,
			async		: true,
			success		: function(retur){console.log(retur)},
			error		: function(retur){console.log(retur)},
 	});
    };
/**
	$.ajax(WDHost + "qat-webdaptive/api/empresa/add", {
            data: {
                json: ko.toJSON({
                    empresa: self.empresa()
                })
            },
            type: "POST",
            dataType: 'json',
            success: function(result) {
                alert(ko.toJSON(result))
            }
        });
	**/
	$.ajax({
            url: WDHost + "qat-webdaptive/api/empresa/add",
            dataType: "json",
            contentType: 'application/json',
            type: "POST",
            data: ko.toJSON( {empresa :  self.empresa()}),
            processdata: true,

            beforeSend: function () {
             //   $.mobile.loading('show');
            },

            error: function (xhr, textStatus, errorThrown) {
                alert('Sorry!');
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

var viewModel = new GiftModel(qat.pages.empresa.form.createTeste());
ko.applyBindings(viewModel);

// Activate jQuery Validation
$("form").validate({ submitHandler: viewModel.save });



});
</script>
