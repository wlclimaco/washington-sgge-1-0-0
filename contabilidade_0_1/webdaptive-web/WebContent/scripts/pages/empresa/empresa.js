var empresaModule = (function () {

			    //Setup settings
			    var settings = {
			        jsFile: 'Empresa.js'
			    };

			    //Request Empresa default structure
			    function EMPRESA(empresa) {
			    	try {
			            var self = this;
			            self.id 				= ko.observable( empresa ? (empresa.id || 0) : 0);
			            self.nome 				= ko.observable( empresa ? (empresa.nome || "teste") : "teste");
			            self.processId 			= ko.observable( empresa ? (empresa.processId || 0) : 0);
			            self.entidadeId 		= ko.observable( empresa ? (empresa.entidadeId || 0) : 0);
			            self.emprId 			= ko.observable( empresa ? (empresa.emprId || 0) : 0);
			        	self.entidadeEnumValue 	= ko.observable( empresa ? (empresa.entidadeEnum || 1) : 1);
			            self.createUser 		= ko.observable( empresa ? (empresa.create_user || "system") : "system");
			        	self.createDateUTC 		= ko.observable( empresa ? (empresa.create_date || "") : "");
			        	self.modifyUser 		= ko.observable( empresa ? (empresa.modify_user || "") : "");
			        	self.modifyDateUTC 		= ko.observable( empresa ? (empresa.modify_date || "") : "");
			            self.regime				= ko.observable( empresa ? (new regimeModule.REGIME(empresa.regime) || {}) : {});
			           	self.documentos 		= ko.observableArray([new documentoModule.DOCUMENTO(empresa.documentos)]);
			        	self.enderecos 			= ko.observableArray([new enderecoModule.ENDERECO(empresa.enderecos)]);
			        	self.emails 			= ko.observableArray([new emailModule.EMAIL(empresa.emails)]);
						self.telefones 			= ko.observableArray([new telefoneModule.TELEFONE(empresa.telefones)]);
			        	self.cnaes 				= ko.observableArray([new cnaeModule.CNAE(empresa.cnaes)]);
			        	self.statusList 		= ko.observableArray([new statusModule.STATUS(empresa.statusList)]);
			        	self.notes 				= ko.observableArray([new noteModule.NOTE(empresa.notes)]);
			        	self.socios 			= ko.observableArray([new socioModule.SOCIO(empresa.socios)]);
			        	self.planoList 			= ko.observableArray([new planoModule.PLANO(empresa.planoList)]);
			        	self.filialList 		= ko.observableArray([new filialModule.FILIAL(empresa.filialList)]);
			        	self.depositoList 		= ko.observableArray([new depositoModule.DEPOSITO(empresa.depositoList)]);
			        	self.usuarioList		= ko.observableArray([new usuarioModule.USUARIO(empresa.usuarioList)]);
				    } catch (e) {
						debugger
				    	console.log(e);
				    }
			    }

			    //Default constructor
			    return {
			        EMPRESA: EMPRESA
			    }

})();