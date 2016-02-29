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
            self.login = ko.observable( usuario ? (usuario.login || 0) : 0);
            self.senha = ko.observable( usuario ? (usuario.senha || 0) : 0);
            self.pergunta = ko.observable( usuario ? (usuario.pergunta || 0) : 0);
            self.role = ko.observable( usuario ? (usuario.role || 0) : 0);
            self.language = ko.observable( usuario ? (usuario.language || 0) : 0);
            self.nome = ko.observable( usuario ? (usuario.nome || 0) : 0);
            self.createUser = ko.observable( usuario ? (usuario.create_user || 0) : 0);
        	self.createDateUTC = ko.observable( usuario ? (usuario.create_date || 0) : 0);
        	self.modifyUser = ko.observable( usuario ? (usuario.modify_user || 0) : 0);
        	self.modifyDateUTC = ko.observable( usuario ? (usuario.modify_date || 0) : 0);
			self.emails = ko.observableArray(usuario ? [new emailModule.EMAIL(usuario.emails)]|| [{email : ""}] : [{email : ""}]);

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