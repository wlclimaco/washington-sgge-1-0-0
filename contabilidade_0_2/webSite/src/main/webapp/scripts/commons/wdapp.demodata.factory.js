(function() {
'use strict';
	var commonData = angular.module('wdApp.demodata', []);

	commonData.factory('DemoData', ['$http', function($http){
		return{
				getDemoData: function(_url,_callback){
					$http.get(_url).then(function(response) {
						_callback(response.data );						
					});					
				}
			};
	}]);
})();