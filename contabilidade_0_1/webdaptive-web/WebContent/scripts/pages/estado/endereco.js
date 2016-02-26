var estadoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Estado.js'
    };

    //Request Estado default structure
    function ESTADO(estado) {
        try {
            var self = this;

            self.id = ko.observable( estado ? (estado.id || 0) : 0);
            self.nome = ko.observable( estado ? (estado.nome || "") : "");
           	self.abreviacao = ko.observable( estado ? (estado.abreviacao || "") : "");
        	self.createUser = ko.observable( estado ? (estado.create_user || "") : "");
        	self.createDateUTC = ko.observable( estado ? (estado.create_date || 0) : 0);
        	self.modifyUser = ko.observable( estado ? (estado.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( estado ? (estado.modify_date || 0) : 0);

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        ESTADO: ESTADO
    }

})();