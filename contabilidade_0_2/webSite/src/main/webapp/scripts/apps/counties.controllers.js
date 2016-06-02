(function() {
  angular.module('wdApp.apps.counties', []).controller('CountiesController', 
  ['$scope', 'SysMgmtData', 'toastr', 'toastrConfig',
	function($scope, SysMgmtData, toastr, toastrConfig) {
		var cvm = this;		
		var initLoad = true; //used to ensure not calling server multiple times
		var fetch_url = WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.fetch_url;
		var refresh_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.refresh_url;
		var create_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.create_url;
		var update_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.update_url;	
		var delete_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.delete_url;		
		cvm.isActive = false;
		toastrConfig.closeButton = true;		
		
		//form model data
		
		
    }
  ]);
}).call(this);

