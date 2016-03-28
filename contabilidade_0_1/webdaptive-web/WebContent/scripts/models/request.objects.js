	//FetchAllRequest Object
	qat.model.fetchAllRequest = function()
	{

	};

	//FetchByIdRequest Object
	qat.model.fetchByIdRequest = function( _iInt)
	{
		this.fetchId = _iInt;
	};

	//RefreshRequest Object
	qat.model.refreshRequest = function( _iInt, _bList, _bPagedList)
	{
		this.refreshInt = _iInt;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//CountyMaintenanceRequest Object
	qat.model.reqCounty = function(_oCounty, _bList, _bPagedList)
	{
		this.county = _oCounty;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//ProcedureMaintenanceRequest
	qat.model.reqProc = function ( _oProc, _bList, _bPagedList)
	{
		this.procedure = _oProc;
		this.returnList = _bList;
		this.returnListPaged = _bPagedList;
	};

	//PagedInquryRequest
	qat.model.pagedInquiryRequest = function ( _iStartPage, _bCount)
	{
		this.pageSize = 20;
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
