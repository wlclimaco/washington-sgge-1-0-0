var cnaeModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Cnae.js'
    };

    //Request Cnae default structure
    function CNAE(cnae) {
        try {
            var self = this;

            self.id = ko.observable( cnae ? (cnae.id || 0 ) : 0);
            self.codigo = ko.observable( cnae ? (cnae.codigo || "" ) : "");
           	self.cnae = ko.observable( cnae ? (cnae.cnae || "" ) : "");
        	self.descricao = ko.observable( cnae ? (cnae.descricao || "" ) : "");
        	self.abreviado = ko.observable( cnae ? (cnae.abreviado || "" ) : "");
        	self.createUser = ko.observable( cnae ? (cnae.create_user || "system" ) : "system");
        	self.createDateUTC = ko.observable( cnae ? (cnae.create_date || 0 ) : 0);
        	self.modifyUser = ko.observable( cnae ? (cnae.modify_user || "system" ) : "system");
        	self.modifyDateUTC = ko.observable( cnae ? (cnae.modify_date || 0 ) : 0);

        } catch (e) {
        	console.log(e);
        }
    }

	function CNAEPESSOA(cnaePessoa) {
        try {
            var self = this;

            self.id 	= ko.observable( cnae ? (cnaePessoa.id || 0 ) : 0);
			self.idCnae	= ko.observable( empresa ? (new cnaeModule.CNAE(cnaePessoa.idCnae) || {}) : {});
        	self.createUser = ko.observable( cnae ? (cnae.create_user || "system" ) : "system");
        	self.createDateUTC = ko.observable( cnae ? (cnae.create_date || 0 ) : 0);
        	self.modifyUser = ko.observable( cnae ? (cnae.modify_user || "system" ) : "system");
        	self.modifyDateUTC = ko.observable( cnae ? (cnae.modify_date || 0 ) : 0);

        } catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        CNAE: CNAE,
		CNAEPESSOA : CNAEPESSOA
    }

})();


