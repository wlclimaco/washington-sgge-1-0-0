var regimeModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Regime.js'
    };

    //Request Regime default structure
    function REGIME(regime) {
    	try {
            var self = this;
            self.id = ko.observable( regime ? (regime.id || 0) : 0);
            self.nome = ko.observable( regime ? (regime.nome || "") : "");
            self.descricao = ko.observable( regime ? (regime.descricao || "") : "");
        	self.createUser = ko.observable( regime ? (regime.create_user || "") : "");
        	self.createDateUTC = ko.observable( regime ? (regime.create_date || "") : "");
        	self.modifyUser = ko.observable( regime ? (regime.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( regime ? (regime.modify_date || "") : "");
    	} catch (e) {
        	console.log(e);
        }
    }

    //Default constructor
    return {
        REGIME: REGIME
    }

})();