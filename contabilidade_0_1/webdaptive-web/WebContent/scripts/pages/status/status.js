var statusModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Status.js'
    };

    //Request Status default structure
    function STATUS(status) {
        try {
            var self = this;

            self.id = ko.observable( status ? (status.id || 0 ) : 0);
            self.tabelaEnumValue = ko.observable( status ? (status.tabela || 0) : 0);
           	self.parentId = ko.observable( status ? (status.parentId || 0) : 0);
        	self.typeValue = ko.observable( status ? (status.type || 0) : 0);
        	self.status = ko.observable( status ? (status.status || "") : "");
            self.statusTypeEnumValue = ko.observable( status ? (status.statusType || "") : "");
        	self.createUser = ko.observable( status ? (status.create_user || "") : "");
        	self.createDateUTC = ko.observable( status ? (status.create_date || 0) : 0);
        	self.modifyUser = ko.observable( status ? (status.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( status ? (status.modify_date || "") : "");

        } catch (e) {
        	console.log(e)
        }
    }

    //Default constructor
    return {
        STATUS: STATUS
    }

})();