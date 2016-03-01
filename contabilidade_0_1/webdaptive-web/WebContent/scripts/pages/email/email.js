var emailModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Email.js'
    };

    //Request Email default structure
    function EMAIL(email) {
        try {
            var self = this;

            self.id = ko.observable( email ? (email.id || 0 ) : 0);
            self.tabelaEnumValue = ko.observable( email ? (email.tabela || 0) : 0);
           	self.parentId = ko.observable( email ? (email.parentId || 0) : 0);
        	self.typeValue = ko.observable( email ? (email.type || 0) : 0);
        	self.email = ko.observable( email ? (email.email || "") : "");
            self.emailTypeEnumValue = ko.observable( email ? (email.emailType || "") : "");
        	self.createUser = ko.observable( email ? (email.create_user || "") : "");
        	self.createDateUTC = ko.observable( email ? (email.create_date || 0) : 0);
        	self.modifyUser = ko.observable( email ? (email.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( email ? (email.modify_date || "") : "");
			self.modelAction		= ko.observable( email ? (email.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        EMAIL: EMAIL
    }

})();