var usuarioModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Usuario.js'
    };

    //Request Usuario default structure
    function USUARIO(usuario) {
        try {
            var self = this;

            self.id = ko.observable( usuario ? (usuario.id || 0) : 0);
            self.login = ko.observable( usuario ? (usuario.login || "") : "");
            self.senha = ko.observable( usuario ? (usuario.senha || "") : "");
            self.pergunta = ko.observable( usuario ? (usuario.pergunta || "") : "");
            self.role = ko.observable( usuario ? (usuario.role || "") : "");
            self.language = ko.observable( usuario ? (usuario.language || "") : "");
            self.nome = ko.observable( usuario ? (usuario.nome || "") : "");
            self.createUser = ko.observable( usuario ? (usuario.create_user || "system") : "system");
        	self.createDateUTC = ko.observable( usuario ? (usuario.create_date || 0) : 0);
        	self.modifyUser = ko.observable( usuario ? (usuario.modify_user || "system") : "system");
        	self.modifyDateUTC = ko.observable( usuario ? (usuario.modify_date || 0) : 0);
			self.emails 	= ko.observableArray([new emailModule.EMAIL(usuario.emails)]);

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        USUARIO: USUARIO
    }

})();