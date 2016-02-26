var filialModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Filial.js'
    };

    //Request Filial default structure
    function FILIAL(filial) {
        try {
            var self = this;
            self.id = ko.observable( filial ? (filial.id || 0) : 0);
            self.nome = ko.observable( filial ? (filial.nome || "") : "");
            self.processId = ko.observable( filial ? (filial.processId || 0) : 0);
            self.entidadeId = ko.observable( filial ? (filial.entidadeId || 0) : 0);
            self.emprId = ko.observable( filial ? (filial.emprId || 0) : 0);
        	self.entidadeEnumValue = ko.observable( filial ? (filial.entidadeEnum || 0) : 0);
            self.createUser = ko.observable( filial ? (filial.create_user || "") : "");
        	self.createDateUTC = ko.observable( filial ? (filial.create_date || "") : "");
        	self.modifyUser = ko.observable( filial ? (filial.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( filial ? (filial.modify_date || "") : "");
        	self.regime = ko.observable( filial ? (filial.regime || {}) : {});
        	self.documentos = ko.observable( filial ? (filial.documentos || {}) : {});
        	self.enderecos = ko.observable( filial ? (filial.enderecos || {}) : {});
        	self.emails = ko.observable( filial ? (filial.emails || {}) : {});
        	self.telefones = ko.observable( filial ? (filial.telefones || {}) : {});
        	self.cnaes = ko.observable( filial ? (filial.cnaes || {}) : {});
        	self.statusList = ko.observable( filial ? (filial.statusList || {}) : {});
        	self.notes = ko.observable( filial ? (filial.notes || {}) : {});
        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        FILIAL: FILIAL
    }

})();