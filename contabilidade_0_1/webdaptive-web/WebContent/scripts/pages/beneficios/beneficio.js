var beneficioModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Beneficio.js'
    };

    //Request Beneficio default structure
    function BENEFICIOPESSOA(beneficio) {
        try {
            var self = this;

            self.id 			= ko.observable( beneficio ? (beneficio.id || 0) : 0);
            self.benefId  	= ko.observable( beneficio ? (new beneficioModule.BENEFICIO(beneficio.benefId) || {}) : {});
        	self.createUser 	= ko.observable( beneficio ? (beneficio.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( beneficio ? (beneficio.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( beneficio ? (beneficio.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( beneficio ? (beneficio.modify_date || 0) : 0);
			self.modelAction	= ko.observable( beneficio ? (beneficio.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    function BENEFICIO(beneficio) {
        try {
            var self = this;
            self.id 			= ko.observable( beneficio ? (beneficio.id || 0) : 0);
            self.nome 			= ko.observable( beneficio ? (beneficio.create_user || "") : "");
            self.codigo 		= ko.observable( beneficio ? (beneficio.create_user || "") : "");
            self.descricao 		= ko.observable( beneficio ? (beneficio.create_user || "") : "");
            self.valor 			= ko.observable( beneficio ? (beneficio.id || 0) : 0);
            self.porcentagem 	= ko.observable( beneficio ? (beneficio.id || 0) : 0);
            self.tipo			= ko.observable( beneficio ? (beneficio.create_user || "") : "");
        	self.createUser 	= ko.observable( beneficio ? (beneficio.create_user || "") : "");
        	self.createDateUTC	= ko.observable( beneficio ? (beneficio.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( beneficio ? (beneficio.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( beneficio ? (beneficio.modify_date || 0) : 0);
			self.modelAction	= ko.observable( beneficio ? (beneficio.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        BENEFICIO: BENEFICIO,
        BENEFICIOPESSOA : BENEFICIOPESSOA
    }

})();