var empresaModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Empresa.js'
    };

    //Request Empresa default structure
    function EMPRESA(empresa) {
        try {
            var self = this;

            self.id 				= ko.observable( empresa ? (empresa.id || 0) : 0);
            self.nome 				= ko.observable( empresa ? (empresa.nome || "") : "");
            self.processId 			= ko.observable( empresa ? (empresa.processId || 0) : 0);
            self.entidadeId 		= ko.observable( empresa ? (empresa.entidadeId || 0) : 0);
            self.emprId 			= ko.observable( empresa ? (empresa.emprId || 0) : 0);
        	self.entidadeEnumValue 	= ko.observable( empresa ? (empresa.entidadeEnum || 1) : 1);
            self.createUser 		= ko.observable( empresa ? (empresa.create_user || "system") : "system");
        	self.createDateUTC 		= ko.observable( empresa ? (empresa.create_date || "") : "");
        	self.modifyUser 		= ko.observable( empresa ? (empresa.modify_user || "") : "");
        	self.modifyDateUTC 		= ko.observable( empresa ? (empresa.modify_date || "") : "");
            self.regime				= ko.observable( empresa ? (new regimeModule.REGIME(empresa.regime) || {}) : {});
           	self.documentos 		= ko.observable( empresa ? (empresa.documentos || {}) : {});
        	self.enderecos 			= ko.observable( empresa ? (empresa.enderecos || {}) : {});
        	self.emails 			= ko.observable( empresa ? (empresa.emails || {}) : {});
        	self.telefones 			= ko.observable( empresa ? (empresa.telefones || {}) : {});
        	self.cnaes 				= ko.observable( empresa ? (empresa.cnaes || {}) : {});
        	self.statusList 		= ko.observable( empresa ? (empresa.statusList || {}) : {});
        	self.notes 				= ko.observable( empresa ? (empresa.notes || {}) : {});
        	self.socios 			= ko.observable( empresa ? (empresa.socios || {}) : {});
        	self.planoList 			= ko.observable( empresa ? (empresa.planoList || {}) : {});
        	self.filialList 		= ko.observable( empresa ? (empresa.filialList || {}) : {});
        	self.depositoList 		= ko.observable( empresa ? (empresa.depositoList || {}) : {});

        } catch (e) {
            JL(settings.jsFile).fatalException('Exception!', e);
        }
    }

    //Default constructor
    return {
        EMPRESA: EMPRESA
    }

})();