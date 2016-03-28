(function() {
  angular.module('wdApp.apps.procedures', []).controller('ProceduresController', 
  ['$scope', 'SysMgmtData', 'toastr', 'toastrConfig',
	function($scope, SysMgmtData, toastr, toastrConfig) {
		var pvm = this;
		var initLoad = true; //used to ensure not calling server multiple times
		var fetch_url = WebDaptiveAppConfig.base_procedure_url +  WebDaptiveAppConfig.fetch_url;
		var refresh_url =  WebDaptiveAppConfig.base_procedure_url +  WebDaptiveAppConfig.refresh_url;
		var create_url =  WebDaptiveAppConfig.base_procedure_url +  WebDaptiveAppConfig.create_url;
		var update_url =  WebDaptiveAppConfig.base_procedure_url +  WebDaptiveAppConfig.update_url;	
		var delete_url =  WebDaptiveAppConfig.base_procedure_url +  WebDaptiveAppConfig.delete_url;	
		//must be part of scope since needs to referenced in the html		
		pvm.isActive = false;
		toastrConfig.closeButton = true;	
		
		//form model data
		pvm.procedure = {
			id: '',
			code: '',
			description: '',
			price: '',
			version: ''
		};
		
		//grid column defs
		pvm.procedureColumnDefs = [
			{headerName: "Id", field: "id"},
			{headerName: "Procedure Code", field: "code"},		
			{headerName: "Procedure Description", field: "description", width: 820},
			{headerName: "Price", field: "price", cellRenderer: currencyRenderer},	
			{headerName: "Version", field: "version", width: 100}			
		];
		
		//grid row select function
		function rowSelectedFunc(event) {
			pvm.procedure.id = event.node.data.id;
			pvm.procedure.code = event.node.data.code;			
			pvm.procedure.description = event.node.data.description;		
			pvm.procedure.price = event.node.data.price;
			pvm.procedure.version = event.node.data.version;				
		};

		//grid options
		pvm.procedureGridOptions = {
			columnDefs: pvm.procedureColumnDefs,
			rowSelection: 'single',
			rowHeight: 30,			
			headerHeight: 30,	
			suppressCellSelection: true,			
			onRowSelected: rowSelectedFunc,
			enableColResize: true
		};
		
		//reusable paging datasource grid
		function createNewDatasource(resIn) {
			var procedureDataSource = {
				pageSize: 20, //using default paging of 20 
				getRows: function (params) {
					if (initLoad){
						//console.log("getRows() initLoad=true: " + resIn);	
						initLoad=false;
						pvm.isActive = false;						
						var dataThisPage = resIn.procedures;
						var lastRow = (resIn) ? resIn.resultsSetInfo.totalRowsAvailable : 0;
						params.successCallback(dataThisPage, lastRow);						
					}
					else{
						//console.log('asking for ' + params.startRow + ' to ' + params.endRow);	
						SysMgmtData.processPostPageData(fetch_url, new qat.model.pagedInquiryRequest(  params.startRow/20, true), function(res){
							var dataThisPage = res.procedures;
							var lastRow = res.resultsSetInfo.totalRowsAvailable;
							params.successCallback(dataThisPage, lastRow);	
						});
					}		
					
				}
			};
			pvm.procedureGridOptions.api.setDatasource(procedureDataSource);
		};
	
		function currencyRenderer(params) {
			if (params.value){
				return params.value.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
			} 
			else{
				return null;
			}
		};
	
	
		//initial data load
		processPostData(fetch_url, new qat.model.pagedInquiryRequest( 0, true), false);
		
		//reusable data methods
		//reusable processGetData (refresh)
		function processGetData(_url){
			//console.log(_url);			
			pvm.procedureGridOptions.api.showLoadingOverlay(true);
			SysMgmtData.processGetPageData(_url,  function(res){
				initLoad = true;
				createNewDatasource(res); //send Data 
			});				
		};	

		//reusable processGetData (insert, update, pagedFetch, delete)
		function processPostData(_url, _req, _bLoading){
			//console.log(_url);	
			if (_bLoading){	
				pvm.procedureGridOptions.api.showLoadingOverlay(true);
			}	
			SysMgmtData.processPostPageData(_url, _req, function(res){
				if (res){	
					initLoad = true;
					createNewDatasource(res); //send Data
				}
				else{
					pvm.procedureGridOptions.api.hideOverlay();		
				}
			});				
		};			
		
		//refresh procedure function
		pvm.refreshProcedures = function(refreshCount) {	
			pvm.isActive = !pvm.isActive;
			//clear form data
			pvm.clearForm();
			var send_url = refresh_url + "?refreshInt=" + refreshCount + "&retList=true&retPaged=true";
			processGetData(send_url);
		};			
		
		//form methods
		//reusable clear form logic
		pvm.clearForm = function(){
			//clear data
			pvm.procedure.id = '';
			pvm.procedure.code = '';			
			pvm.procedure.description = '';		
			pvm.procedure.price = '';
			pvm.procedure.version = '';		
			//clear grid selection	
			pvm.procedureGridOptions.api.deselectAll();	
			//set form to pristine
			pvm.form_procedure.$setPristine();				
		};

		//reusable button form logic	
		pvm.processButtons = function(_btnType){	
			if (pvm.form_procedure.$valid){	
				switch (_btnType) {
				//Add Button							
				case 'A':
					processPostData(create_url, new qat.model.reqProc( new qat.model.procedure(pvm.procedure.id, pvm.procedure.code, 
								pvm.procedure.description, pvm.procedure.price, pvm.procedure.version),true, true), true);						
					break;
				//Update Button						
				case 'U':
					processPostData(update_url, new qat.model.reqProc( new qat.model.procedure(pvm.procedure.id, pvm.procedure.code, 
								pvm.procedure.description, pvm.procedure.price, pvm.procedure.version),true, true), true);						
					break;
				//Delete Button	
				case 'D':
					processPostData(delete_url, new qat.model.reqProc( new qat.model.procedure(pvm.procedure.id, pvm.procedure.code, 
								pvm.procedure.description, pvm.procedure.price, pvm.procedure.version),true, true), true);						
					break;	
				//List Button	
				case 'L':
					processPostData(fetch_url, new qat.model.pagedInquiryRequest( 0, true), true);					
					break;						
				default: 
					console.log('Invalid button type: ' + _btnType);					
				};
			
				//clear the form
				pvm.clearForm();
			}
			else
			{
				if (_btnType == 'L'){
					processPostData(fetch_url, new qat.model.pagedInquiryRequest( 0, true), true);
					//clear the form
					pvm.clearForm();
				}
				else{					
					toastr.error('Procedure form error, please correct and resubmit.', 'Error');
				}	
			}		
		};		
		
    }
  ]);
}).call(this);



