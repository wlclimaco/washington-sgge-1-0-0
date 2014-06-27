	//FetchAllRequest Object
	qat.model.fetchAllRequest = function(_oUC)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
	};

	//FetchByIdRequest Object
	qat.model.fetchByIdRequest = function(_oUC, _iInt)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.fetchId = _iInt;
	};

	//RefreshRequest Object
	qat.model.refreshRequest = function(_oUC, _iInt, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.refreshInt = _iInt;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//CountyMaintenanceRequest Object
	qat.model.reqCounty = function(_oUC, _oCounty, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.county = _oCounty;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//CountyMaintenanceRequest Object
	qat.model.reqCadastro = function(_oUC, _oCadastro, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.cadastro = _oCadastro;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	qat.model.reqCidade = function(_oUC, _oCidade, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.cidade = _oCidade;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//ProcedureMaintenanceRequest
	qat.model.reqProc = function (_oUC, _oProc, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.procedure = _oProc;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//PagedInquryRequest
	qat.model.pagedInquiryRequest = function (_oUC, _iPageSize, _iStartPage, _bCount)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.pageSize = _iPageSize;
		this.startPage = _iStartPage;
		this.sortExpressions = null;
		this.preQueryCount = _bCount;
		this.maxPreQueryCount = 0;
	};

	//PageData Object
	qat.model.pageData = function(_pageSize, _startPage, _bRowsAvailable, _totalRows)
	{
	 	this.pageSize =  _pageSize;
	 	this.startPage =  _startPage;
	 	this.moreRowsAvailable =  _bRowsAvailable;
	 	this.totalRowsAvailable = _totalRows;
	};

	qat.model.reqSupermercado = function(_oUC, _oSupermercado, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.supermercado = _oSupermercado;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};
