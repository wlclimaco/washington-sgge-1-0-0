var enderecoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Deposito.js'
    };

    //Request Deposito default structure
    function ENDERECO(endereco) {
        try {
            var self = this;

            self.id = ko.observable( endereco ? (endereco.id || 0) : 0);
            self.typeValue = ko.observable( endereco ? (endereco.type || 0) : 0);
            self.processId = ko.observable( endereco ? (endereco.processId || 0) : 0);
            self.logradouro = ko.observable( endereco ? (endereco.logradouro || 0) : 0);
			self.cep = ko.observable( endereco ? (endereco.cep || 0) : 0);
            self.bairro = ko.observable( endereco ? (endereco.bairro || 0) : 0);
            self.numero = ko.observable( endereco ? (endereco.numero || 0) : 0);
			self.complemento = ko.observable( endereco ? (endereco.complemento || 0) : 0);
            self.enderecoTypeValue = ko.observable( endereco ? (endereco.enderecoType || 0) : 0);
            self.createUser = ko.observable( endereco ? (endereco.create_user || 0) : 0);
        	self.createDateUTC = ko.observable( endereco ? (endereco.create_date || 0) : 0);
        	self.modifyUser = ko.observable( endereco ? (endereco.modify_user || 0) : 0);
        	self.modifyDateUTC = ko.observable( endereco ? (endereco.modify_date || 0) : 0);
        	self.cidade = ko.observable( endereco ? (new cidadeModule(endereco.cidade) || {}) : {});
        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        ENDERECO: ENDERECO
    }

})();