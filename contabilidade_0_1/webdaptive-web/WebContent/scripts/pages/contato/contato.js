var contatoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Contato.js'
    };

    //Request Contato default structure
    function CONTATO(contato) {
        try {
            var self = this;

            self.id 				= ko.observable( contato ? (contato.id || 0) : 0);
            self.dataContato 		= ko.observable( contato ? (contato.id || 0) : 0);
            self.nome				= ko.observable( contato ? (contato.create_user || "") : "");
            self.email				= ko.observable( contato ? (contato.create_user || "") : "");
            self.telefone			= ko.observable( contato ? (contato.create_user || "") : "");
            self.motivoValue 		= ko.observable( contato ? (contato.create_user || "") : "");
            self.contatoItensList 	= ko.observableArray([new contatoModule.CONTATOITENS(contato.contatoItensList)]);
        	self.createUser 		= ko.observable( contato ? (contato.create_user || "") : "");
        	self.createDateUTC 		= ko.observable( contato ? (contato.create_date || 0) : 0);
        	self.modifyUser 		= ko.observable( contato ? (contato.modify_user || "") : "");
        	self.modifyDateUTC 		= ko.observable( contato ? (contato.modify_date || 0) : 0);
			self.modelAction		= ko.observable( contato ? (contato.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    function CONTATOITENS(contato) {
        try {
            var self = this;
            self.id 			= ko.observable( contato ? (contato.id || 0) : 0);
            self.dataAlt 		= ko.observable( contato ? (contato.id || 0) : 0);
        	self.texto  		= ko.observable( contato ? (contato.create_user || "") : "");
        	self.titulo  		= ko.observable( contato ? (contato.create_user || "") : "");
        	self.createUser 	= ko.observable( contato ? (contato.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( contato ? (contato.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( contato ? (contato.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( contato ? (contato.modify_date || 0) : 0);
			self.modelAction	= ko.observable( contato ? (contato.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        CONTATO: CONTATO,
        CONTATOITENS: CONTATOITENS
    }

})();