var depositoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Deposito.js'
    };

    //Request Deposito default structure
    function DEPOSITO(deposito) {
        try {
            var self = this;
            self.id = ko.observable( deposito ? (deposito.id || 0) : 0);
            self.nome = ko.observable( deposito ? (deposito.nome || "") : "");
            self.processId = ko.observable( deposito ? (deposito.processId || 0) : 0);
            self.entidadeId = ko.observable( deposito ? (deposito.entidadeId || 0) : 0);
            self.emprId = ko.observable( deposito ? (deposito.emprId || 0) : 0);
        	self.entidadeEnumValue = ko.observable( deposito ? (deposito.entidadeEnum || 0) : 0);
            self.createUser = ko.observable( deposito ? (deposito.create_user || "") : "");
        	self.createDateUTC = ko.observable( deposito ? (deposito.create_date || "") : "");
        	self.modifyUser = ko.observable( deposito ? (deposito.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( deposito ? (deposito.modify_date || "") : "");
        	self.regime = ko.observable( deposito ? (deposito.regime || {}) : {});
        	self.documentos = ko.observable( deposito ? (deposito.documentos || {}) : {});
        	self.enderecos = ko.observable( deposito ? (deposito.enderecos || {}) : {});
        	self.emails = ko.observable( deposito ? (deposito.emails || {}) : {});
        	self.telefones = ko.observable( deposito ? (deposito.telefones || {}) : {});
        	self.cnaes = ko.observable( deposito ? (deposito.cnaes || {}) : {});
        	self.statusList = ko.observable( deposito ? (deposito.statusList || {}) : {});
        	self.notes = ko.observable( deposito ? (deposito.notes || {}) : {});

        } catch (e) {

        }
    }

    //Default constructor
    return {
        DEPOSITO: DEPOSITO
    }

})();