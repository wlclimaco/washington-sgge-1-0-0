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
			//self.idFunc			= ko.observable( beneficio ? (beneficio.idFunc || 0) : 0);
            self.benefId  		= ko.observable( beneficio ? (new beneficioModule.BENEFICIO(beneficio.benefId) || {}) : {});
        	self.createUser 	= ko.observable( beneficio ? (beneficio.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( beneficio ? (beneficio.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( beneficio ? (beneficio.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( beneficio ? (beneficio.modify_date || 0) : 0);
			self.modelAction	= ko.observable( beneficio ? (beneficio.modelAction || "INSERT") : "INSERT");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    function BENEFICIO(beneficio) {
        try {
            var self = this;
            self.id 			= ko.observable( beneficio ? (beneficio.id || 0) : 0);
            self.nome 			= ko.observable( beneficio ? (beneficio.nome || "") : "");
            self.codigo 		= ko.observable( beneficio ? (beneficio.codigo || "") : "");
            self.descricao 		= ko.observable( beneficio ? (beneficio.descricao || "") : "");
            self.valor 			= ko.observable( beneficio ? (beneficio.valor || 0) : 0);
            self.porcentagem 	= ko.observable( beneficio ? (beneficio.porcentagem || 0) : 0);
            self.tipo			= ko.observable( beneficio ? (beneficio.tipo || "") : "");
        	self.createUser 	= ko.observable( beneficio ? (beneficio.create_user || "") : "");
        	self.createDateUTC	= ko.observable( beneficio ? (beneficio.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( beneficio ? (beneficio.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( beneficio ? (beneficio.modify_date || 0) : 0);
			self.modelAction	= ko.observable( beneficio ? (beneficio.modelAction || "INSERT") : "INSERT");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        BENEFICIO: BENEFICIO,
        BENEFICIOPESSOA : BENEFICIOPESSOA
    }

})();