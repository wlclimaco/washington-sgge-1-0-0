	//UserContext Object
	qat.base.model.userContext = function(_userId, _userRole)
	{

		if (_userId == null)
		{
			this.userId = wd.session.userId;
		}
		else
		{
			this.userId = _userId;
		}

		if (_userRole== null)
		{
			this.userRole = wd.session.authorities;
		}
		else
		{
			this.userRole = _userRole;
		}

		this.id = null;
		this.localeString = WDi18nLanguage;
//		this.tenant = new qat.base.model.tenant();
		this.authorities = new Array();
		this.authorities[0] = new qat.base.model.authority();
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
