var agenciaModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Agencia.js'
    };

    //Request Agencia default structure
    function AGENCIA(agencia) {
        try {
            var self = this;

            self.id 				= ko.observable( agencia ? (agencia.id || 0) : 0);
            self.nome 				= ko.observable( agencia ? (agencia.nome || "system") : "system");
            self.enderecos 			= ko.observableArray( agencia ? [(new enderecoModule.ENDERECO(agencia.enderecos)) || {}] : [{}]);
	//		self.emails 			= ko.observableArray( agencia ? [(new emailModule.EMAIL(agencia.emails)) || {}] : [{}]);
	//		self.telefones 			= ko.observableArray( agencia ? [(new telefoneModule.TELEFONE(agencia.telefones)) || {}] : [{}]);
			self.gerente			= ko.observable( agencia ? (agencia.gerente || "") : "");
			self.responsavelConta	= ko.observable( agencia ? (agencia.responsavelConta || "") : "");
			self.numeroConta		= ko.observable( agencia ? (agencia.numeroConta || "") : "");
            self.createUser 		= ko.observable( agencia ? (agencia.create_user || "system") : "system");
        	self.createDateUTC 		= ko.observable( agencia ? (agencia.create_date || 0) : 0);
        	self.modifyUser 		= ko.observable( agencia ? (agencia.modify_user || "system") : "system");
        	self.modifyDateUTC 		= ko.observable( agencia ? (agencia.modify_date || 0) : 0);
			self.modelAction		= ko.observable( agencia ? (agencia.modelAction || "NONE") : "NONE");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        AGENCIA: AGENCIA
    }

})();