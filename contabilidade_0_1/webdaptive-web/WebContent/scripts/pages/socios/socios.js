var socioModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Socio.js'
    };

    //Request Socio default structure
    function SOCIO(socio) {
        try {
            var self = this;

            self.id = ko.observable( socio ? (socio.id || 0) : 0);
            self.parentId = ko.observable( socio ? (socio.parentId || 0) : 0);
        	self.cota = ko.observable( socio ? (socio.cota || "") : "");
        	self.porcentagem = ko.observable( socio ? (socio.porcentagem || "") : "");
			self.documentos = ko.observableArray([new documentoModule.DOCUMENTO(socio.documentos)]);
        	self.createUser = ko.observable( socio ? (socio.create_user || "") : "");
        	self.createDateUTC = ko.observable( socio ? (socio.create_date || "") : "");
        	self.modifyUser = ko.observable( socio ? (socio.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( socio ? (socio.modify_date || "") : "");

        } catch (e) {

        }
    }

    //Default constructor
    return {
        SOCIO: SOCIO
    }

})();