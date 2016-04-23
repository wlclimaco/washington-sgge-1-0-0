var horarioModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Horario.js'
    };

    //Request Horario default structure
    function HORARIO(horario) {
        try {
            var self = this;

            self.id = ko.observable( horario ? (horario.id || 0) : 0);
            self.data = ko.observable( horario ? (horario.id || 0) : 0);
        	self.horarioEntr  = ko.observable( horario ? (horario.id || 0) : 0);
        	self.horarioSair = ko.observable( horario ? (horario.id || 0) : 0);
        	self.tipo = ko.observable( horario ? (horario.login || "") : "");
            self.createUser = ko.observable( horario ? (horario.create_user || "system") : "system");
        	self.createDateUTC = ko.observable( horario ? (horario.create_date || 0) : 0);
        	self.modifyUser = ko.observable( horario ? (horario.modify_user || "system") : "system");
        	self.modifyDateUTC = ko.observable( horario ? (horario.modify_date || 0) : 0);
			self.modelAction		= ko.observable( horario ? (horario.modelAction || "NONE") : "NONE");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        HORARIO: HORARIO
    }

})();