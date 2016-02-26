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
            self.typeValue = ko.observable( telefone ? (telefone.type || 0 ) : 0);
            self.parentId = ko.observable( telefone ? (telefone.parentId || 0 ) : 0);
           	self.tabelaEnumValue = ko.observable( telefone ? (telefone.tabela || 0 ) : 0);
        	self.ddd = ko.observable( telefone ? (telefone.ddd || "" ) : "");
        	self.numero = ko.observable( telefone ? (telefone.telefone || "" ) : "");
        	self.telefoneTypeEnumValue = ko.observable( telefone ? (telefone.telefoneType || 0 ) : 0);
        	self.precessId = ko.observable( telefone ? (telefone.processId || 0 ) : 0);
        	self.createUser = ko.observable( telefone ? (telefone.create_user || "" ) : "");
        	self.createDateUTC = ko.observable( telefone ? (telefone.create_date || 0 ) : 0);
        	self.modifyUser = ko.observable( telefone ? (telefone.modify_user || "" ) : "");
        	self.modifyDateUTC = ko.observable( telefone ? (telefone.modify_date || 0 ) : 0);

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        TELEFONE: TELEFONE
    }

})();