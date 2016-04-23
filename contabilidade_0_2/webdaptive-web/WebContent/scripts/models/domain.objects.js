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

	//Estado Object
	qat.model.estado = function(_id, _nome,_abreviacao,_modelAction)
	{
		a= new Date();
		this.id = _id;
		this.nome = _nome;
		this.abreviacao = _abreviacao;
		if(_modelAction == "INSERT"){
			this.createUser = 'rod';
			this.createDataUTC = a.getTime();
		}else if(_modelAction == "UPDATE"){
			this.modifyUser = 'rod'
			this.modifyDataUTC = a.getTime();
		}
		this.modelAction	= _modelAction
	};
