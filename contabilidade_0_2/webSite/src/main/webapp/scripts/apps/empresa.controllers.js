(function() {
  angular.module('wdApp.apps.empresa', []).controller('EmpresaController',
   ['$scope', '$rootScope', 'SysMgmtData', 'toastr', 'toastrConfig','$location','$http', '$q',
     function($scope, $rootScope, SysMgmtData, toastr, toastrConfig,location ,$http, $q) {
		var pvm = this;
		var initLoad = true; //used to ensure not calling server multiple times
		var fetch_url =    WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.fetch_url;
		var refresh_url =  WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.refresh_url;
		var create_url =   WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.create_url;
		var update_url =   WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.update_url;
		var delete_url =   WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.delete_url;
		pvm.isActive = false;
		toastrConfig.closeButton = true;

		//form model data
		pvm.county = {
			id: '',
			description: ''
		};

        pvm.empresa = new qat.model.Empresa();

          $('#dashboard-header').hide();
          $('#header').hide();
      //    debugger
		 pvm.team = 'test';// $scope.$location.search().keyword
	    // pvm.empresa = new qat.model.Empresa();
        pvm.buscaRCep = function(){
            debugger
            var cepValue = '38082243'
            var formatedCep;
            //formatedCep = cepValue.replace(/\D/g, '');
            var formatedCep = cepValue.replace(/\D/g, '');
            var viaCepUrl = "https://viacep.com.br/ws/" + formatedCep + "/json/";
            $http.get(viaCepUrl).then(function(response) {
                var raw;
                //debugger
                raw = response.data;
            });
        }      
 /*         var config = {headers: {
            'X-Cosmos-Token': 'T9pFIi3coAXpypnWF4miGw',
            'Content-Type': 'application/json',
             "Access-Control-Allow-Origin": "*",
             "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
             'Access-Control-Allow-Credentials': 'true',
             'Accept': 'application/json;odata=verbose'
            }
        };

          var formatedCep;
            //formatedCep = cepValue.replace(/\D/g, '');
          var formatedCep = cepValue.replace(/\D/g, '');
          var viaCepUrl = 'https://cosmos.bluesoft.com.br/api/gtins/7891910000197/json/';
          $http.get(viaCepUrl,config).then(function(response) {
            var raw;
            debugger
            raw = response.data;
            if (raw.erro) {
              return deferred.reject('CEP not found');
            } else {
              return deferred.resolve(raw);
            }
          });*/

    }

  ]);
}).call(this);



