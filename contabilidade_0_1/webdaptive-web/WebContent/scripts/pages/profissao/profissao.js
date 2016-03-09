var profissaoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Salario.js'
    };

    //Request Salario default structure
    function PROFISSAO(profissao) {
        try {
            var self = this;

            self.id 			= ko.observable( profissao ? (profissao.id || 0) : 0);
            self.profissao 		= ko.observable( profissao ? (profissao.profissao || "") : "");
        	self.renda 			= ko.observable( profissao ? (profissao.renda || 0) : 0);
        	self.dataAdmissao	= ko.observable( profissao ? (profissao.dataAdmissao || 0) : 0);
        	self.parentId 		= ko.observable( profissao ? (profissao.parentId || 0) : 0);
            self.createUser 	= ko.observable( profissao ? (profissao.create_user || "system") : "system");
        	self.createDateUTC 	= ko.observable( profissao ? (profissao.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( profissao ? (profissao.modify_user || "system") : "system");
        	self.modifyDateUTC 	= ko.observable( profissao ? (profissao.modify_date || 0) : 0);
			self.modelAction	= ko.observable( profissao ? (profissao.modelAction || "NONE") : "NONE");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        PROFISSAO: PROFISSAO
    }

})();