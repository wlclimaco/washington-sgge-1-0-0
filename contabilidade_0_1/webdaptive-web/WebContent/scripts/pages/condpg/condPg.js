var condPgModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'CondPg.js'
    };

    //Request FormaPg default structure
    function CONDPGPESSOA(condPg) {
        try {
            var self = this;

            self.id 			= ko.observable( condPg ? (condPg.id || 0) : 0);
            self.condPgId  		= ko.observable( condPg ? (new condPgModule.FORMAPG(condPg.condPgId) || {}) : {});
        	self.createUser 	= ko.observable( condPg ? (condPg.create_user || "") : "");
        	self.createDateUTC	= ko.observable( condPg ? (condPg.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( condPg ? (condPg.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( condPg ? (condPg.modify_date || 0) : 0);
			self.modelAction	= ko.observable( condPg ? (condPg.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    function CONDPG(condPg) {
        try {
            var self = this;
            self.id 			= ko.observable( condPg ? (condPg.id || 0) : 0);
            self.nome 			= ko.observable( condPg ? (condPg.descricao || "") : "");
            self.valorIni 		= ko.observable( condPg ? (condPg.id || 0) : 0);
            self.valorFin 		= ko.observable( condPg ? (condPg.id || 0) : 0);
            self.parcelas		= ko.observable( condPg ? (condPg.id || 0) : 0);
            self.listTipoPag  	= ko.observableArray([new condPgModule.TIPOPG(condPg.listTipoPag)]);
        	self.createUser 	= ko.observable( condPg ? (condPg.create_user || "") : "");
        	self.createDateUTC	= ko.observable( condPg ? (condPg.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( condPg ? (condPg.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( condPg ? (condPg.modify_date || 0) : 0);
			self.modelAction	= ko.observable( condPg ? (condPg.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }
    function TIPOPG(condPg) {
        try {
            var self = this;

            self.id 			= ko.observable( condPg ? (condPg.id || 0) : 0);
            self.descricao 		= ko.observable( condPg ? (condPg.descricao || "") : "");
        	self.createUser 	= ko.observable( condPg ? (condPg.create_user || "") : "");
        	self.createDateUTC	= ko.observable( condPg ? (condPg.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( condPg ? (condPg.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( condPg ? (condPg.modify_date || 0) : 0);
			self.modelAction	= ko.observable( condPg ? (condPg.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
    	CONDPG: CONDPG,
        TIPOPG: TIPOPG,
        CONDPGPESSOA : CONDPGPESSOA
    }

})();