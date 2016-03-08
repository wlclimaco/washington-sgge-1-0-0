var funcionarioModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Funcionario.js'
    };

    //Request Funcionario default structure
    function FUNCIONARIO(funcionario) {
        try {
            var self = this;
            self.id  					= ko.observable( funcionario ? (funcionario.id || 0 ) : 0);
			self.nome 					= ko.observable( funcionario ? (funcionario.nome || "" ) : "");
            self.emprId 				= ko.observable( funcionario ? (funcionario.emprId || 0 ) : 0);
            self.nomePai 				= ko.observable( funcionario ? (funcionario.nomePai || "" ) : "");
            self.nomeMae 				= ko.observable( funcionario ? (funcionario.nomeMae || "" ) : "");
            self.nomeConjugue 			= ko.observable( funcionario ? (funcionario.nomeConjugue || "" ) : "");
            self.estadoCivil 			= ko.observable( funcionario ? (funcionario.estadoCivil || 0 ) : 0);
            self.tipoPessoa 			= ko.observable( funcionario ? (funcionario.tipoPessoa || "" ) : "");
            self.datanasc 				= ko.observable( funcionario ? (funcionario.datanasc || 0 ) : 0);
            self.foto 					= ko.observable( funcionario ? (funcionario.foto || "" ) : "");
            self.matricula 				= ko.observable( funcionario ? (funcionario.matricula || "" ) : "");
            self.dataAdm 				= ko.observable( funcionario ? (funcionario.dataAdm || 0 ) : 0);
            self.pessoaTypeEnumValue 	= ko.observable( funcionario ? (funcionario.pessoaTypeEnumValue || 0 ) : 0);
            self.sexo 					= ko.observable( funcionario ? (funcionario.sexo || 0 ) : 0);
        	self.enderecos 				= ko.observableArray([new enderecoModule.ENDERECO(funcionario.enderecos)]);
        	self.documentos 			= ko.observableArray([new documentoModule.DOCUMENTO(funcionario.documentos)]);
        	self.emails 				= ko.observableArray([new emailModule.EMAIL(funcionario.emails)]);
			self.telefones 				= ko.observableArray([new telefoneModule.TELEFONE(funcionario.telefones)]);
        	self.bancos 				= ko.observableArray([new bancoModule.BANCOPESSOA(funcionario.bancos)]);
        	self.formaPagamentoList 	= ko.observableArray([new formaPgModule.FORMAPGPESSOA(funcionario.formaPagamentoList)]);
        	self.condPagList 			= ko.observableArray([new condPgModule.CONDPGPESSOA(funcionario.condPagList)]);
        	self.contatoList  			= ko.observableArray([new contatoModule.CONTATO(funcionario.contatoList)]);
        	self.salarios 				= ko.observableArray([new salarioModule.SALARIO(funcionario.salarios)]);
        	self.horarios 				= ko.observableArray([new horarioModule.HORARIO(funcionario.horarios)]);
        	self.beneficios 			= ko.observableArray([new beneficioModule.BENEFICIOPESSOA(funcionario.beneficios)]);
        	self.eventosList 			= ko.observableArray([new eventoModule.EVENTOPESSOA(funcionario.eventosList)]);
        	self.createUser 			= ko.observable( funcionario ? (funcionario.create_user || "") : "");
        	self.createDateUTC 			= ko.observable( funcionario ? (funcionario.create_date || 0) : 0);
        	self.modifyUser 			= ko.observable( funcionario ? (funcionario.modify_user || "") : "");
        	self.modifyDateUTC 			= ko.observable( funcionario ? (funcionario.modify_date || "") : "");
			self.modelAction			= ko.observable( funcionario ? (funcionario.modelAction || "INSERT") : "INSERT");

        } catch (e) {
        	debugger
        	console.log(e);
        }
    }

    //Default constructor
    return {
        FUNCIONARIO: FUNCIONARIO
    }

})();