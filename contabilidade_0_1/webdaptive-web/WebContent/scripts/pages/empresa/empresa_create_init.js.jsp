<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

	<script type="text/javascript">

$(document).ready(function()
{
	var GiftModel = function(empresa) {
    var self = this;

    self.empresa = ko.observable(empresa);

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
			data		: $.toJSON(ko.toJSON("empresa :"+self.empresa())),
			contentType : "application/json; charset=utf-8",
			//timeout		: 5000,
			async		: true,
			success		: function(retur){console.log(retur)},
			error		: function(retur){console.log(retur)},
 	});
    };

    $.ajax({
			type		: "POST",
			dataType    : 'json',
			url			: WDHost + "qat-webdaptive/api/empresa/add",
			username	: wd.session.userId,
			password	: wd.session.userPassword,
			data		: $.toJSON({"empresa" : ko.toJSON(self.empresa())}),
			contentType : "application/json; charset=utf-8",
			//timeout		: 5000,
			async		: true,
			success		: function(retur){console.log(retur)},
			error		: function(retur){console.log(retur)},
 	});
};

var viewModel = new GiftModel(new empresaModule.EMPRESA({}));
ko.applyBindings(viewModel);

// Activate jQuery Validation
$("form").validate({ submitHandler: viewModel.save });



});
</script>
