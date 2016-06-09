(function() {
  angular.module('wdApp.apps.site', []).controller('SiteController',
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
          $('#dashboard-header').hide();
          $('#header').hide();
		 pvm.team = 'test'// $scope.$location.search().keyword
	//	 cvm.team =  $scope.$location.url(); new qat.model.county


        //  var commonControllers =  angular.module('wdApp.controllers', ['LoginController']);
         // console.log(commonControllers.login());


          SysMgmtData.processPostPageData(fetch_url, new qat.model.siteInquiryRequest( 100/20, true, "http://localhost:8080/webSite/"), function(res){
               console.log(res)
               pvm.site = new qat.model.Site(res.sites[0]);
          });

       //  SysMgmtData.processPostPageData("fetch_url", {cep : '38082243'}, function(res){
        //       console.log(res)
        //       pvm.site = new qat.model.Site(res.sites[0]);
       //  });
     /*  cepValue = '38082243'
       var formatedCep;
            //formatedCep = cepValue.replace(/\D/g, '');
          var formatedCep = cepValue.replace(/\D/g, '');
          var viaCepUrl = "https://viacep.com.br/ws/" + formatedCep + "/json/";
          $http.get(viaCepUrl).then(function(response) {
            var raw;
            //debugger
            raw = response.data;
          });

            $http.get('https://cosmos.bluesoft.com.br/api/gtins/7891910000197.json', {
                headers: {
                    "Authorization": 'X-Cosmos-Token="T9pFIi3coAXpypnWF4miGw"'
                }
              }).success(function(response){
                console.log(response)
              });

          var config = {headers: {
            'X-Cosmos-Token': 'T9pFIi3coAXpypnWF4miGw',
            'Content-Type': 'application/json',
             "Access-Control-Allow-Origin": "*",
             "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
             'Access-Control-Allow-Credentials': 'true',
             'Accept': 'application/json;odata=verbose'
            }
        };

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
          });
*/
    }

  ]);
}).call(this);



