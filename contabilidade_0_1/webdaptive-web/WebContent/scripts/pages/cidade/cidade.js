var cidadeModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Cidade.js'
    };

    //Request Cidade default structure
    function CIDADE(cidade) {
        try {
            var self = this;

            self.id = ko.observable( cidade ? (cidade.id || 0) : 0);
            self.codigo = ko.observable( cidade ? (cidade.CODIGO || "") : "");
            self.nome = ko.observable( cidade ? (cidade.cidade || "") : "");
           	self.cdIBGE = ko.observable( cidade ? (cidade.IBGE || "") : "");
           	self.cep = ko.observable( cidade ? (cidade.CEP || "") : "");
           	self.municipio = ko.observable( cidade ? (cidade.MUNICIPIO || "") : "");
        	self.createUser = ko.observable( cidade ? (cidade.create_user || "") : "");
        	self.createDateUTC = ko.observable( cidade ? (cidade.create_date || 0) : 0);
        	self.modifyUser = ko.observable( cidade ? (cidade.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( cidade ? (cidade.modify_date || 0) : 0);
        	self.estado = ko.observable( cidade ? (new estadoModule(cidade.estado || {})) : {});

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        CIDADE: CIDADE
    }

})();