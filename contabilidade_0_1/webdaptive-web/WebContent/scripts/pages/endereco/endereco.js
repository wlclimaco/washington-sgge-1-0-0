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
            self.processId = ko.observable( endereco ? (endereco.processId || 0) : 0);
            self.logradouro = ko.observable( endereco ? (endereco.logradouro || "") : "");
			self.cep = ko.observable( endereco ? (endereco.cep || "") : "");
            self.bairro = ko.observable( endereco ? (endereco.bairro || "") : "");
            self.numero = ko.observable( endereco ? (endereco.numero || "") : "");
			self.complemento = ko.observable( endereco ? (endereco.complemento || "") : "");
            self.enderecoTypeValue = ko.observable( endereco ? (endereco.enderecoTypeValue || 0) : 0);
            self.createUser = ko.observable( endereco ? (endereco.create_user || "system") : "system");
        	self.createDateUTC = ko.observable( endereco ? (endereco.create_date || 0) : 0);
        	self.modifyUser = ko.observable( endereco ? (endereco.modify_user || "system") : "system");
        	self.modifyDateUTC = ko.observable( endereco ? (endereco.modify_date || 0) : 0);
        	self.cidade = ko.observable( endereco ? (new cidadeModule(endereco.cidade) || {}) : {});
			self.modelAction		= ko.observable( endereco ? (endereco.modelAction || "INSERT") : "INSERT");
        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        ENDERECO: ENDERECO
    }

})();