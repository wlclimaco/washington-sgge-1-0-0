var telefoneModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Telefone.js'
    };

    //Request Telefone default structure
    function TELEFONE(telefone) {
        try {
            var self = this;

            self.id = ko.observable( telefone ? (telefone.id || 0 ) : 0);
            self.parentId = ko.observable( telefone ? (telefone.parentId || 0 ) : 0);
        	self.ddd = ko.observable( telefone ? (telefone.ddd || "" ) : "");
        	self.numero = ko.observable( telefone ? (telefone.telefone || "" ) : "");
        	self.telefoneTypeEnumValue = ko.observable( telefone ? (telefone.telefoneTypeEnumValue || 0 ) : 0);
        	self.processId = ko.observable( telefone ? (telefone.processId || 0 ) : 0);
        	self.createUser = ko.observable( telefone ? (telefone.create_user || "system" ) : "system");
        	self.createDateUTC = ko.observable( telefone ? (telefone.create_date || 0 ) : 0);
        	self.modifyUser = ko.observable( telefone ? (telefone.modify_user || "system" ) : "system");
        	self.modifyDateUTC = ko.observable( telefone ? (telefone.modify_date || 0 ) : 0);
			self.modelAction		= ko.observable( telefone ? (telefone.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        TELEFONE: TELEFONE
    }

})();