var salarioModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Salario.js'
    };

    //Request Salario default structure
    function SALARIO(salario) {
        try {
            var self = this;

            self.id 			= ko.observable( salario ? (salario.id || 0) : 0);
            self.data 			= ko.observable( salario ? (salario.id || 0) : 0);
        	self.valor 			= ko.observable( salario ? (salario.id || 0) : 0);
        	self.parentId 		= ko.observable( salario ? (salario.parentId || 0) : 0);
            self.createUser 	= ko.observable( salario ? (salario.create_user || "system") : "system");
        	self.createDateUTC 	= ko.observable( salario ? (salario.create_date || 0) : 0);
        	self.modifyUser 	= ko.observable( salario ? (salario.modify_user || "system") : "system");
        	self.modifyDateUTC 	= ko.observable( salario ? (salario.modify_date || 0) : 0);
			self.modelAction	= ko.observable( salario ? (salario.modelAction || "INSERT") : "INSERT");

        } catch (e) {
			debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        SALARIO: SALARIO
    }

})();