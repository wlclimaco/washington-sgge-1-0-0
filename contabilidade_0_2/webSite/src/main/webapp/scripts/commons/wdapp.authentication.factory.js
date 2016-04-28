(function() {
'use strict';
	var commonAuth = angular.module('wdApp.authentication', []);

	commonAuth.factory('WDAuthentication', ['$http', function($http){
		return{
				processLogin: function(_url, _req, _callback){
					var res = $http.post(_url, _req, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
					res.then(function(response) {
						_callback(response.data );						
					})
				}
			};
	}]);
})();