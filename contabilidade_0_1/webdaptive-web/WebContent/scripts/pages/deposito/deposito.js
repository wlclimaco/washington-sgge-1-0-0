var depositoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Deposito.js'
    };

    //Request Deposito default structure
    function DEPOSITO(deposito) {
        try {
            var self = this;
			self.id 				= ko.observable( deposito ? (deposito.id || 0) : 0);
			self.nome 				= ko.observable( deposito ? (deposito.nome || "teste") : "teste");
			self.processId 			= ko.observable( deposito ? (deposito.processId || 0) : 0);
			self.entidadeId 		= ko.observable( deposito ? (deposito.entidadeId || 0) : 0);
			self.emprId 			= ko.observable( deposito ? (deposito.emprId || 0) : 0);
			self.entidadeEnumValue 	= ko.observable( deposito ? (deposito.entidadeEnum || 2) : 2);
			self.createUser 		= ko.observable( deposito ? (deposito.create_user || "system") : "system");
			self.createDateUTC 		= ko.observable( deposito ? (deposito.create_date || 0) : 0);
			self.modifyUser 		= ko.observable( deposito ? (deposito.modify_user || "") : "");
			self.modifyDateUTC 		= ko.observable( deposito ? (deposito.modify_date || 0) : 0);
			self.documentos 		= ko.observableArray([new documentoModule.DOCUMENTO(deposito.documentos)]);
			self.enderecos 			= ko.observableArray([new enderecoModule.ENDERECO(deposito.enderecos)]);
			self.emails 			= ko.observableArray([new emailModule.EMAIL(deposito.emails)]);
			self.telefones 			= ko.observableArray([new telefoneModule.TELEFONE(deposito.telefones)]);

        } catch (e) {

        }
    }

    //Default constructor
    return {
        DEPOSITO: DEPOSITO
    }

})();