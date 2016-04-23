var eventoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Evento.js'
    };

    //Request Evento default structure
    function EVENTOPESSOA(evento) {
        try {
            var self = this;

            self.id 			= ko.observable( evento ? (evento.id || 0) : 0);
            self.data 			= ko.observable( evento ? (evento.data || 0) : 0);
        	self.idEvent 		= ko.observable( evento ? (new eventoModule.EVENTO(evento.idEvent) || {}) : {});
        	self.idFunc 		= ko.observable( evento ? (evento.idFunc || 0) : 0);
        	self.createUser 	= ko.observable( evento ? (evento.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( evento ? (evento.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( evento ? (evento.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( evento ? (evento.modify_date || 0) : 0);
			self.modelAction	= ko.observable( evento ? (evento.modelAction || "NONE") : "NONE");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    function EVENTO(evento) {
        try {
            var self = this;
            self.id 			= ko.observable( evento ? (evento.id || 0) : 0);
            self.nome			= ko.observable( evento ? (evento.nome || "") : "");
          //  self.dataList 		= ko.observableArray([new eventoModule.EVENTOMESAPP(evento.dataList)]);
        	self.descricao		= ko.observable( evento ? (evento.descricao || "") : "");
        	self.codigo			= ko.observable( evento ? (evento.codigo || "") : "");
        	self.tipo			= ko.observable( evento ? (evento.tipo || "") : "");
        	self.valor			= ko.observable( evento ? (evento.valor || 0) : 0);
        	self.porcentagem	= ko.observable( evento ? (evento.porcentagem || 0) : 0);
        	self.isMensal		= ko.observable( evento ? (evento.isMensal || false) : false);
        	self.isSistema		= ko.observable( evento ? (evento.isSistema || false) : false);
        	self.noteText		= ko.observable( evento ? (evento.noteText || "") : "");
        	self.createUser 	= ko.observable( evento ? (evento.create_user || "") : "");
        	self.createDateUTC	= ko.observable( evento ? (evento.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( evento ? (evento.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( evento ? (evento.modify_date || 0) : 0);
			self.modelAction	= ko.observable( evento ? (evento.modelAction || "NONE") : "NONE");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    function EVENTOMESAPP(evento) {
        try {
            var self = this;

            self.id 			= ko.observable( evento ? (evento.id || 0) : 0);
            self.data 			= ko.observable( evento ? (evento.data || 0) : 0);
        	self.createUser 	= ko.observable( evento ? (evento.create_user || "") : "");
        	self.createDateUTC 	= ko.observable( evento ? (evento.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( evento ? (evento.modify_user || "") : "");
        	self.modifyDateUTC 	= ko.observable( evento ? (evento.modify_date || 0) : 0);
			self.modelAction	= ko.observable( evento ? (evento.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        EVENTO: EVENTO,
        EVENTOMESAPP: EVENTOMESAPP,
        EVENTOPESSOA : EVENTOPESSOA
    }

})();