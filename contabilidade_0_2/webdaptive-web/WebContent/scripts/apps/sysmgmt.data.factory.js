(function() {
'use strict';
angular.module('wdApp.apps.sysmgmt.data', [])
	.factory('SysMgmtData', ['$http', 'toastr', 'toastrConfig',  function($http, toastr, toastrConfig){

		toastrConfig.closeButton = true;
	
		//common process error logic
		function process_errors(_resp, _callback){
			toastr.error('AJAX Error calling sysmgmt rest api: ' + _resp.status + " " +  _resp.statusText, 'Error');		
			_resp = "";  
			_callback(_resp);			
		};
		
		//common process business error logic
		function process_business_errors(_resp, _callback){
				//console.log(_resp);
				var msgOut = "Data Processing Error calling sysmgmt rest api: ";
				if (_resp == null){
					//use default message
				}
				else{
					//Make sure return is an array
					if ($.isArray(_resp.messageList)){
						var tmpLength = _resp.messageList.length;
						for (var q = 0; q < (tmpLength); q++){
							msgOut = msgOut + "Severity:" + _resp.messageList[q].messageInfo.severity + ",Level:" + _resp.messageList[q].messageInfo.level +  ",Text:"  + _resp.messageList[q].text + ",traceInfo:"  +  _resp.messageList[q].messageInfo.traceInfo;
						}
					}
				}
				toastr.warning(msgOut, 'Warning');		
				_resp = "";  
				_callback(_resp);			
		};		
		
		//common process data logic		
		function process_data(_resp, _callback)	{
			//console.log(_resp);
			//Successful Return and got some type of object back
			if (_resp != null && (_resp.operationSuccess)){
				//checks for business errors
				if (_resp.messageList.length > 0){	
					process_business_errors(_resp, _callback);	
				}
				else{
					_callback(_resp);
				}
			}
			else{
				process_business_errors(_resp, _callback);								
			};			
		};		
	
		return{
				processPostPageData: function(_url, _req, _callback){
					var res = $http.post(_url, _req);
					res.then(function(response) {
						//process success data						
						process_data(response.data, _callback)						
					}).catch( // Catch
						function(responseError) {
							process_errors(responseError, _callback);;
					});
					
				},
				processGetPageData: function(_url, _callback){
					var res = $http.get(_url);
					res.then(function(response) {
						//process success data						
						process_data(response.data, _callback)						
					}).catch( // Catch
						function(responseError) {
							process_errors(responseError, _callback);;
					});				
				}				
			};
			
}]);

}).call(this);

