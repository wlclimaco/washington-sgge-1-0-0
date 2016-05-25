(function() {
'use strict';
	var wdHttpInt = angular.module('wdApp.httpint', []);

	wdHttpInt.factory('WDHttpInterceptors', ['$q', '$rootScope', '$location', function($q, $rootScope, $location){
		       return {
				/* Register error provider that shows message on failed requests or redirects to login page on
				 * unauthenticated requests */
		       	'responseError': function(rejection) {
		       		var status = rejection.status;
		       		var statusText = rejection.statusText;
		       		var config = rejection.config;
		       		var method = config.method;
		       		var url = config.url;

		       		if (status == 401){
						$rootScope.logout();
						$rootScope.callingPath = $location.path();
						$rootScope.error = method + " on " + url + " failed with status " + status + " " + statusText;
		       			$location.path( "/pages/signin" );
		       		}
					else{
						if (status == 404) {
							$location.path( "/pages/404" );
						}
						else{
							$rootScope.error = method + " on " + url + " failed with status " + status + " " + statusText;
							//$location.path( "/pages/500" );
						}
					}
		       		return $q.reject(rejection);
		       	},
				/* Registers auth token interceptor, auth token is either passed by header or by query parameter
				 * as soon as there is an authenticated user only for sysmgmt api calls*/
				'request': function(config) {
				//	debugger
					//gambiarra
					if(config.url == 'http://localhost:8080/qat-sysmgmt-controller-rest/site/api/fetchPage'){
						config.headers['X-Auth-Token'] = "taz@qat.com:1464136071197:45f873499ccf7bc677c25ea06cc6efa2";
					}
					var isRestCall = config.url.indexOf(WebDaptiveAppConfig.restAuthBase) != -1;
					if (isRestCall && angular.isDefined($rootScope.authToken)) {
						var authToken = $rootScope.authToken;
						if (WebDaptiveAppConfig.useAuthTokenHeader){
							config.headers['X-Auth-Token'] = authToken;
						}
						else{
							config.url = config.url + "?token=" + authToken;
						}
					}
				//	debugger
					return config || $q.when(config);
				}
			};
	}]);
})();