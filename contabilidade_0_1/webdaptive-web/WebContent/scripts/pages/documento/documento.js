var documentoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Deposito.js'
    };

    //Request Deposito default structure
    function DOCUMENTO(documento) {
        try {
            var self = this;
            self.documentoid = ko.observable( documento ? (documento.documentoid || 0) : 0);
            self.id = ko.observable( documento ? (documento.id || 0) : 0);
            self.rgInc = ko.observable( documento ? (documento.rginscmuni || "") : "");
            self.cpfCnpj = ko.observable( documento ? (documento.cpfcnpj || "") : "");
            self.razao = ko.observable( documento ? (documento.razao || "") : "");
            self.tableTypeValue = ko.observable( documento ? (documento.tabela || 0) : 0);
        } catch (e) {

        }
    }

    //Default constructor
    return {
        DOCUMENTO: DOCUMENTO
    }

})();