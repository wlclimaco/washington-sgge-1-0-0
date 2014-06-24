
	//County Object
	qat.model.county = function(_countyId, _countyDesc)
	{
		this.modelAction = null;
		this.id = _countyId;
		this.description = _countyDesc;
	};

	//Procedure Object
	qat.model.procedure = function(_version, _procId, _procCode, _procDesc, _procPrice)
	{
		this.modelAction = null;
		this.version = _version;
		this.id = _procId;
		this.code = _procCode;
		this.description = _procDesc;
		this.price = _procPrice;
	};

	qat.model.cadastro = function(_Id, _type, _nome, _descricao,_controlAcess)
	{
	    var userContext = new qat.base.model.userContext();
		this.id = _Id;
		this.type = _type;
		this.nome = _nome;
		this.descricao = _descricao;
		this.userId = userContext.userId;
		this.id = userContext.id;
		this.tenant = userContext.tenant;
		this.userRole = userContext.userRole;

	//	this.controlAcess = _controlAcess;
	};
