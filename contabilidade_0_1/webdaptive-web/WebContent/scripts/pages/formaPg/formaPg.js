var formaPgModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'FormaPg.js'
    };

    //Request FormaPg default structure
    function FORMAPGPESSOA(formaPg) {
        try {
            var self = this;

            self.id = ko.observable( formaPg ? (formaPg.id || 0) : 0);
            self.formaPgId  = ko.observable( formaPg ? (new formaPgModule.FORMAPG(formaPg.formaPgId) || {}) : {});
        	self.createUser = ko.observable( formaPg ? (formaPg.create_user || "") : "");
        	self.createDateUTC = ko.observable( formaPg ? (formaPg.create_date || 0) : 0);
        	self.modifyUser = ko.observable( formaPg ? (formaPg.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( formaPg ? (formaPg.modify_date || 0) : 0);
			self.modelAction		= ko.observable( formaPg ? (formaPg.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    function FORMAPG(formaPg) {
        try {
            var self = this;
            self.id 			= ko.observable( formaPg ? (formaPg.id || 0) : 0);
            self.descricao 		= ko.observable( formaPg ? (formaPg.descricao || "") : "");
            self.diasPg 		= ko.observable( formaPg ? (formaPg.diasPg || 0) : 0);
            self.entrada 		= ko.observable( formaPg ? (formaPg.entrada || 0) : 0);
        	self.createUser 	= ko.observable( formaPg ? (formaPg.create_user || "") : "");
        	self.createDateUTC	= ko.observable( formaPg ? (formaPg.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( formaPg ? (formaPg.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( formaPg ? (formaPg.modify_date || 0) : 0);
			self.modelAction	= ko.observable( formaPg ? (formaPg.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        FORMAPG: FORMAPG,
        FORMAPGPESSOA : FORMAPGPESSOA
    }

})();