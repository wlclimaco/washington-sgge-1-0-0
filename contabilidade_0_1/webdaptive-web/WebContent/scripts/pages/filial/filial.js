var filialModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Filial.js'
    };

    //Request Filial default structure
    function FILIAL(filial) {
        try {
            var self = this;
            self.id 				= ko.observable( filial ? (filial.id || 0) : 0);
			self.nome 				= ko.observable( filial ? (filial.nome || "teste") : "teste");
			self.processId 			= ko.observable( filial ? (filial.processId || 0) : 0);
			self.entidadeId 		= ko.observable( filial ? (filial.entidadeId || 0) : 0);
			self.emprId 			= ko.observable( filial ? (filial.emprId || 0) : 0);
			self.entidadeEnumValue 	= ko.observable( filial ? (filial.entidadeEnum || 2) : 2);
			self.createUser 		= ko.observable( filial ? (filial.create_user || "system") : "system");
			self.createDateUTC 		= ko.observable( filial ? (filial.create_date || 0) : 0);
			self.modifyUser 		= ko.observable( filial ? (filial.modify_user || "") : "");
			self.modifyDateUTC 		= ko.observable( filial ? (filial.modify_date || 0) : 0);
			self.regime				= ko.observable( filial ? (new regimeModule.REGIME(filial.regime) || {}) : {});
			self.documentos 		= ko.observableArray([new documentoModule.DOCUMENTO(filial.documentos)]);
			self.enderecos 			= ko.observableArray([new enderecoModule.ENDERECO(filial.enderecos)]);
			self.emails 			= ko.observableArray([new emailModule.EMAIL(filial.emails)]);
			self.telefones 			= ko.observableArray([new telefoneModule.TELEFONE(filial.telefones)]);
			self.cnaes 				= ko.observableArray([new cnaeModule.CNAEPESSOA(filial.cnaes)]);
			self.usuarioList		= ko.observableArray([new usuarioModule.USUARIO(filial.usuarioList)]);
        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        FILIAL: FILIAL
    }

})();