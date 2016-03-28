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
