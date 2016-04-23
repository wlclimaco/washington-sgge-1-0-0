(function() {
  angular.module('wdApp.apps.stocks', []).controller('StocksController', 
  ['$scope', 'YahooData', function($scope, YahooData) {
	    var svm = this;
		var	gstocks = ['AAPL', 'AMZN', 'AXP', 'BA','FDX', 'GOOG', 'IBM', 'MSFT', 'NFLX','ORCL', 'GM'];	
		
		var stockColumnDefs = [
			{headerName: "Symbol", field: "Symbol", sort: 'asc', filter: 'text', unSortIcon: true, width: 100, suppressResize: true},
			{headerName: "Company", field: "Name", filter: 'text', unSortIcon: true, width:  300, suppressResize: true},
			{headerName: "Stock Exchange", field: "StockExchange", suppressMenu: true, unSortIcon: true, width: 125, suppressResize: true},
			{headerName: "Market Cap", field: "MarketCapitalization", suppressMenu: true, suppressSorting: true},
			{headerName: "Year Range", field: "YearRange", suppressMenu: true, suppressSorting: true},
			{headerName: "Last Trade Price", field: "LastTradePriceOnly", suppressMenu: true, suppressSorting: true},
			{headerName: "Last Trade Date", field: "LastTradeDate", suppressMenu: true, suppressSorting: true},
			{headerName: "Last Trade Time (EST)", field: "LastTradeTime", suppressMenu: true, suppressSorting: true}
		];

		svm.stockGridOptions = {
			columnDefs: stockColumnDefs,
			suppressMenuHide: true,			
			enableSorting: true,
			enableColResize: true,
			enableFilter: true,
			rowHeight: 30,			
			headerHeight: 30,	
			suppressCellSelection: true,			
			ready: function() {console.log('The grid is ready');}			
		};
		
		//initload
		YahooData.getYahooFinanceData(gstocks,function(res){
			var stockDataSource = {
				rowCount: res.length, //there is no data to page so forcing to data size
				pageSize: res.length, //there is no data to page so forcing to data size 
				getRows: function (params) {
					var dataThisPage = res;
					var lastRow = res.length;
					params.successCallback(dataThisPage, lastRow);					
				}
			};

			svm.stockGridOptions.api.setDatasource(stockDataSource);
		});
    }
  ]);
}).call(this);



