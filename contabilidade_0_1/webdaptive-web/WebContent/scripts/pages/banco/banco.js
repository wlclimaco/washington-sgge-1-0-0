var bancoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Banco.js'
    };

    //Request Email default structure
    function BANCOPESSOA(banco) {
        try {
            var self = this;
            self.id 			= ko.observable( banco ? (banco.id || 0 ) : 0);
            self.numCont 		= ko.observable( banco ? (banco.numCont || "") : "");
            self.saldo 			= ko.observable( banco ? (banco.saldo || 0) : 0);
            self.bancoId 		= ko.observableArray([new bancoModule.BANCO(banco.bancoId)]);
            self.agenciaId		= ko.observableArray([new agenciaModule.Agencia(banco.agenciaId)]);
           	self.parentId 		= ko.observable( banco ? (banco.parentId || 0) : 0);
        	self.createUser 	= ko.observable( banco ? (banco.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( banco ? (banco.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( banco ? (banco.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( banco ? (banco.modify_date || "") : "");
			self.modelAction	= ko.observable( banco ? (banco.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    function BANCO(banco) {
        try {
            var self = this;
            self.id				= ko.observable( banco ? (banco.id || 0 ) : 0);
            self.nome			= ko.observable( banco ? (banco.nome || "") : "");
        	self.createUser 	= ko.observable( banco ? (banco.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( banco ? (banco.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( banco ? (banco.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( banco ? (banco.modify_date || "") : "");
			self.modelAction	= ko.observable( banco ? (banco.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
    	BANCOPESSOA: BANCOPESSOA,
    	BANCO : BANCO
    }

})();