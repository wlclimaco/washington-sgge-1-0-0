var planoModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Empresa.js'
    };

    //Request Empresa default structure
    function PLANO(plano) {
        try {
            var self = this;
            self.id = ko.observable( plano ? (plano.id || "") : "");
        	self.dataInicio = ko.observable( plano ? (plano.dataInicio || "") : "");
        	self.dataFinal = ko.observable( plano ? (plano.dataFinal || "") : "");
            self.numeroContrato = ko.observable( plano ? (plano.numeroContrato || "") : "");
            self.descricao = ko.observable( plano ? (plano.descricao || "") : "");
            self.titulo = ko.observable( plano ? (plano.titulo || "") : "");
            self.emprId = ko.observable( plano ? (plano.emprId || "") : "");
           	self.createUser = ko.observable( plano ? (plano.create_user || "") : "");
        	self.createDateUTC = ko.observable( plano ? (plano.create_date || "") : "");
        	self.modifyUser = ko.observable( plano ? (plano.modify_user || "") : "");
        	self.modifyDateUTC = ko.observable( plano ? (plano.modify_date || "") : "");
        	//self.preco = ko.observable( plano ? (new preco.PRECO(plano.preco) || {}: {});
			//self.servicos = ko.observable( plano ? (new servicoModule.SERVICO(plano.servico) || {}: {});
			//self.imagens = ko.observable( plano ? (new imagem.IMAGEM(plano.imagem) || {}: {});
        } catch (e) {
console.log(e)
        }
    }

    //Default constructor
    return {
        PLANO: PLANO
    }

})();