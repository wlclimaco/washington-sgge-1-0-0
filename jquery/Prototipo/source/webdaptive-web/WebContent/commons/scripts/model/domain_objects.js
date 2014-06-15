
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
		this.id = _Id;
		this.type = 1;
		this.nome = _nome;
		this.descricao = _descricao;
	//	this.controlAcess = _controlAcess;
	};
