	/**
	 * Initialize the main namespaces and constants.
	 */
	var qat = {
		model 	: {},
		base 	: {
			model : {}
		}
	};


	//County Object
	qat.model.county = function(_countyId, _countyDesc)
	{
		this.id = _countyId;
		this.description = _countyDesc;
	};

	//Procedure Object
	qat.model.procedure = function(_procId, _procCode, _procDesc, _procPrice, _version)
	{
		this.id = _procId;
		this.code = _procCode;
		this.description = _procDesc;
		this.price = _procPrice;
		this.version = _version;
	};

	/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//Servico Object
qat.model.Servico = function(_oObjet)
{
     this.id = _oObjet.id;
     this.nome = _oObjet.nome;
     this.descricao = _oObjet.descricao;
     this.preco = _oObjet.preco;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//ServicoByPlano Object
qat.model.ServicoByPlano = function(_oObjet)
{
     this.id = _oObjet.id;
     this.parentId = _oObjet.parentId;
     this.servico = _oObjet.servico;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//Site Object
qat.model.Site = function(_oObjet)
{
     this.id = _oObjet.id;
     this.nome = _oObjet.nome;
     this.url = _oObjet.url;
     this.quemSomos = _oObjet.quemSomos;
     this.missao = _oObjet.missao;
     this.visao = _oObjet.visao;
     this.titulo = _oObjet.titulo;
     this.logo = _oObjet.logo;
     this.atorization = _oObjet.atorization;
     this.siteTypeEnum = _oObjet.siteTypeEnum;
     this.servicoList = _oObjet.servicoList;
     this.planoList = _oObjet.planoList;
     this.parentId       = _oObjet.parentId;
     this.empresa         = _oObjet.empresa;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//'sysrem';//'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//Contato Object
qat.model.Contato = function(_oObjet)
{
     this.id = _oObjet.id;
     this.parentId = _oObjet.parentId;
     this.dataContato = _oObjet.dataContato;
     this.nome = _oObjet.nome;
     this.motivoValue = _oObjet.motivoValue;
     this.contatoItensList = _oObjet.contatoItensList;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//ContatoItens Object
qat.model.ContatoItens = function(_oObjet)
{
     this.id = _oObjet.id;
     this.dataAlt = _oObjet.dataAlt;
     this.texto = _oObjet.texto;
     this.titulo = _oObjet.titulo;
     this.contatoStatus = _oObjet.contatoStatus;
     this.visto = _oObjet.visto;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//OrdemServico Object
qat.model.OrdemServico = function(_oObjet)
{
     this.id = _oObjet.id;
     this.emprId = _oObjet.emprId;
     this.userId = _oObjet.userId;
     this.nome = _oObjet.nome;
     this.data = _oObjet.data;
     this.assunto = _oObjet.assunto;
     this.statusValue = _oObjet.statusValue;
     this.OrdemServicoItensList = _oObjet.OrdemServicoItensList;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//OrdemServicoItens Object
qat.model.OrdemServicoItens = function(_oObjet)
{
     this.id = _oObjet.id;
     this.data = _oObjet.data;
     this.texto = _oObjet.texto;
     this.parentId = _oObjet.parentId;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}

/** create by system gera-java version 1.0.0 19/05/2016 15:9 : 2*/

//Plano Object
qat.model.Plano = function(_oObjet)
{
     this.id = _oObjet.id;
     this.dataInicio = _oObjet.dataInicio;
     this.dataFinal = _oObjet.dataFinal;
     this.numeroContrato = _oObjet.numeroContrato;
     this.descricao = _oObjet.descricao;
     this.titulo = _oObjet.titulo;
     this.precoList = _oObjet.precoList;
     this.servicoList = _oObjet.servicoList;
     this.parentId       = _oObjet.parentId;
     this.emprId         = _oObjet.emprId;
     this.processId      = _oObjet.processId;
     this.tableEnumValue = _oObjet.tableEnumValue;
     this.modelAction    = _oObjet.modelAction;
     this.createUser     = 'sysrem';//$rootScope.user;
     this.createDateUTC  = (new Date()).getTime();
     this.modifyUser     = 'sysrem';//$rootScope.user;
     this.modifyDateUTC  = (new Date()).getTime();
}





