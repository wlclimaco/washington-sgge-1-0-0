(function() {
'use strict';
angular.module('wdApp.apps.stocksdata', [])
	.factory('YahooData', ['$http', 'toastr', 'toastrConfig', function($http, toastr, toastrConfig){

		toastrConfig.closeButton = true;
	
		return{
				getYahooFinanceData: function(_stocks, _callback){
					var defs = {
						desc: false,
						baseURL: 'http://query.yahooapis.com/v1/public/yql?q=',
						query: 'select {display} from yahoo.finance.quotes where symbol in ({quotes}) | sort(field="{sortBy}", descending="{desc}")',
						suffixURL: '&env=store://datatables.org/alltableswithkeys&format=json'
					};

					var opts = {
						display: ['*'],
						stocks: _stocks
					};
					var query = {
						display: opts.display.join(', '),
						quotes: opts.stocks.map(function (stock) {
							return '"' + stock + '"';
						}).join(', ')
					};

					defs.query = defs.query
						.replace('{display}', query.display)
						.replace('{quotes}', query.quotes)
						.replace('{sortBy}', defs.sortBy)
						.replace('{desc}', defs.desc);

					defs.url = defs.baseURL + defs.query + defs.suffixURL;					
					
					$http.get(defs.url).
					  then(function(_resp) {
						_callback(_resp.data.query.results.quote);
					  }).catch( // Catch
						function(_respError) {
							toastr.error('Error calling Yahoo stock api: ' + _respError.status + " " +  _respError.statusText, 'Error');						
							_respError = "";  
							_callback(_respError);
					 });
				}
			};
}]);

}).call(this);

