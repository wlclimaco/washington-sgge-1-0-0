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
		//	this.userContext = new qat.base.model.userContext();
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
	qat.model.reqEmpresa = function(_oUC, _oEmpresa, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
		//	this.userContext = new qat.base.model.userContext();
		}
		else
		{
		//	this.userContext = _oUC;
		}
		this.empresa = _oEmpresa;
	//	this.returnList = _bList;
	//	this.returnListPaged = _bPagedList;
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


	qat.model.EmpresaInquiryRequest = function (_oUC, _iPageSize, _iStartPage, _bCount,_bCrit)
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
		this.criteria = _bCrit;
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

	qat.model.reqCliente = function(_oUC, _oCliente, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.cliente = _oCliente;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//CountyMaintenanceRequest Object
	qat.model.reqProduto = function(_oUC, _oProduto, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.produto         = _oProduto;
		this.returnList      = _bList;
		this.returnListPaged = _bPagedList;
	};
	//CountyMaintenanceRequest Object
	qat.model.reqUniMed = function(_oUC, _oCadastro, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
		//	this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.embalagem = {unimedid :_oCadastro};
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};
	qat.model.reqEmbalagem = function(_oUC, _oEmbalagem, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.embalagem       = _oEmbalagem;
		this.returnList      = _bList;
		this.returnListPaged = _bPagedList;
	};


	qat.model.reqServico = function(_oUC, _oEmbalagem, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.servico       = _oEmbalagem;
//		this.returnList      = _bList;
	//	this.returnListPaged = _bPagedList;
	};

	qat.model.reqPlano = function(_oUC, _oEmbalagem, _bList, _bPagedList)
	{
		if (_oUC == null)
		{
			this.userContext = new qat.base.model.userContext();
		}
		else
		{
			this.userContext = _oUC;
		}
		this.plano       = _oEmbalagem;
//		this.returnList      = _bList;
	//	this.returnListPaged = _bPagedList;
	};