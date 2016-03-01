var documentoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Deposito.js'
    };

    //Request Deposito default structure
    function DOCUMENTO(documento) {
        try {
            var self = this;

			self.id = ko.observable( documento ? (documento.id || 0) : 0);
			self.documentoTypeEnumValue  = ko.observable( documento ? (documento.documentoTypeEnumValue || 0) : 0);
			self.numero  = ko.observable( documento ? (documento.numero || "" ) : "");
			self.data  = ko.observable( documento ? (documento.data || 0) : 0);
			self.estado = ko.observable( documento ? (new estadoModule.ESTADO(documento.estado) || {}) : {});

        } catch (e) {
debugger
        }
    }

    //Default constructor
    return {
        DOCUMENTO: DOCUMENTO
    }

})();