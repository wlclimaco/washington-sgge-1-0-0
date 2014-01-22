	//UserContext Object
	qat.base.model.userContext = function(_userId, _userRole)
	{


	};

	//Tenant Object
	qat.base.model.tenant = function()
	{
		this.modelAction = null;
		this.createUser = null;
		this.modifyUser = null;
		this.createDate = null;
		this.modifyDate = null;
		this.id = null;
	};

	//Authority Object
	qat.base.model.authority = function()
	{
		this.id = null;
		this.name = null;
	};
