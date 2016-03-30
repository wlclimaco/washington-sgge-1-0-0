angular.module("myApp", ["ngTable"]);

(function() {
  "use strict";

  angular.module("myApp").controller("dynamicDemoController", dynamicDemoController, 
  ['$scope', 'SysMgmtData', 'toastr', 'toastrConfig',
	function($scope, SysMgmtData, toastr, toastrConfig,dynamicDemoController) {
	}	
  ])
  dynamicDemoController.$inject = ["NgTableParams"];

  function dynamicDemoController(NgTableParams, simpleList) {
   // var self = this;

	var cvm = this;		
		var initLoad =    true; //used to ensure not calling server multiple times
		var fetch_url =   WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.fetch_url;
		var refresh_url = WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.refresh_url;
		var create_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.create_url;
		var update_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.update_url;	
		var delete_url =  WebDaptiveAppConfig.base_county_url +  WebDaptiveAppConfig.delete_url;		
		cvm.isActive =    false;
		toastrConfig.closeButton = true;		
		
		//form model data
		cvm.county = {
			id: '',
			description: ''
		};
		
		//grid column defs
		var countyColumnDefs = [
			{headerName: "County Id", field: "id", width: 270},
			{headerName: "County Description", field: "description", width: 450}
		];
		
		//grid row select function
		function rowSelectedFunc(event) {
			cvm.county.id = event.node.data.id;
			cvm.county.description = event.node.data.description;			
		};

		//grid options
		cvm.countyGridOptions = {
			columnDefs: countyColumnDefs,
			rowSelection: 'single',
			onRowSelected: rowSelectedFunc,
			rowHeight: 30,			
			headerHeight: 30,			
			enableColResize: true
		};
		
		//reusable paging datasource grid
		function createNewDatasource(resIn) {
			var countyDataSource = {
				pageSize: 20, //using default paging of 20 
				getRows: function (params) {
					if (initLoad){
						//console.log("getRows() initLoad=true: " + resIn);	
						initLoad=false;
						cvm.isActive = false;
						var dataThisPage = resIn.counties;
						cvm.gList =  dataThisPage;						 
						var lastRow = (resIn) ? resIn.resultsSetInfo.totalRowsAvailable : 0;
						params.successCallback(dataThisPage, lastRow);
	                    
					}
					else{
						//console.log('asking for ' + params.startRow + ' to ' + params.endRow);	
						SysMgmtData.processPostPageData(fetch_url, new qat.model.pagedInquiryRequest(  params.startRow/20, true), function(res){
							var dataThisPage = res.counties;
							cvm.gList =  dataThisPage;							
							var lastRow = res.resultsSetInfo.totalRowsAvailable;
							params.successCallback(dataThisPage, lastRow);	
						});
					}		
				}
			};
			cvm.countyGridOptions.api.setDatasource(countyDataSource);
		};
	
		//initial data load
		processPostData(fetch_url, new qat.model.pagedInquiryRequest( 0, true), false);
		
		//reusable data methods
		//reusable processGetData (refresh,delete)
		function processGetData(_url)
		{
			//console.log(_url);			
			cvm.countyGridOptions.api.showLoadingOverlay(true);
			SysMgmtData.processGetPageData(_url,  function(res){
				if (res){	
					initLoad = true;
					createNewDatasource(res); //send Data
				}
				else{
					cvm.countyGridOptions.api.hideOverlay();				
				}				
				
			});				
		};	

		//reusable processGetData (insert, update, pagedFetch)
		function processPostData(_url, _req, _bLoading)
		{
			//console.log(_url);	
			if (_bLoading){	
				cvm.countyGridOptions.api.showLoadingOverlay(true);
			}	
			SysMgmtData.processPostPageData(_url, _req, function(res){
				if (res){	
					initLoad = true;
					createNewDatasource(res); //send Data
				}
				else{
					cvm.countyGridOptions.api.hideOverlay();				
				}		
			});				
		};			
		
		//refresh county function
		cvm.refreshCounties = function(refreshCount) {
			cvm.isActive = !cvm.isActive;			
			//clear form data
			cvm.clearForm();		
			var send_url = refresh_url + "?refreshInt=" + refreshCount + "&retList=true&retPaged=true";
			processGetData(send_url);
		};			
		
		//form methods
		//reusable clear form logic
		cvm.clearForm = function (){
			//clear data
			cvm.county.id = "";
			cvm.county.description = "";	
			//clear grid selection	
			cvm.countyGridOptions.api.deselectAll();	
			//set form to pristine
			cvm.form_county.$setPristine();			
		};
		
		//reusable button form logic		
		cvm.processButtons = function(_btnType){	
			//console.log(_btnType);		
			if (cvm.form_county.$valid)
			{	
				switch (_btnType) {
				//Add Button							
				case 'A':
					processPostData(create_url,  new qat.model.reqCounty( new qat.model.county(cvm.county.id, cvm.county.description),true, true), true);		
					break;
				//Update Button						
				case 'U':
					processPostData(update_url,  new qat.model.reqCounty( new qat.model.county(cvm.county.id, cvm.county.description),true, true), true);	
					break;
				//Delete Button	
				case 'D':
					var send_url = delete_url + "?countyId=" + cvm.county.id + "&retList=true&retPaged=true";
					processGetData(send_url);
					break;	
				//List Button	
				case 'L':
					processPostData(fetch_url, new qat.model.pagedInquiryRequest( 0, true), true);
					break;					
				default: 
					console.log('Invalid button type: ' + _btnType);					
				};
				//clear the form
				cvm.clearForm();
			}
			else{
				if (_btnType == 'L'){
					processPostData(fetch_url, new qat.model.pagedInquiryRequest( 0, true), true);
					//clear the form
					cvm.clearForm();
				}
				else{	
					toastr.error('County form error, please correct and resubmit.', 'Error');
				}	
			}		
		};
		
	cvm.cols = [
      { field: "name", title: "Name", sortable: "name", filter: { name: "text" }, show: false  },
      { field: "age", title: "Age", sortable: "age", filter: { age: "number" }, show: true },
      { field: "money", title: "Money", sortable: "money", filter: { money: "number" }, show: true }
    ];
    cvm.tableParams = new NgTableParams({}, {
      //dataset: simpleList
      dataset: [
        {
          "name": "aa",
          "age": 55,
          "money": "R$ 33"
        },
        {
          "name": "aa",
          "age": 55,
          "money": "R$ 33"
        },
        {
          "name": "aa",
          "age": 55,
          "money": "R$ 33"
        },
        {
          "name": "aa",
          "age": 55,
          "money": "R$ 33"
        },
        {
          "name": "aa",
          "age": 55,
          "money": "R$ 33"
        },
        {
          "name": "aa",
          "age": 55,
          "money": "R$ 33"
        }
      ]
    });
    
  }
})();
(function() {
  "use strict";

  angular.module("myApp").run(configureDefaults);
  configureDefaults.$inject = ["ngTableDefaults"];

  function configureDefaults(ngTableDefaults) {
    ngTableDefaults.params.count = 5;
    ngTableDefaults.settings.counts = [];
  }
})();

(function() {
  angular.module("myApp").directive("demoTrackedTable", demoTrackedTable);

  demoTrackedTable.$inject = [];

  function demoTrackedTable() {
    return {
      restrict: "A",
      priority: -1,
      require: "ngForm",
      controller: demoTrackedTableController
    };
  }

  demoTrackedTableController.$inject = ["$scope", "$parse", "$attrs", "$element"];

  function demoTrackedTableController($scope, $parse, $attrs, $element) {
    var self = this;
    var tableForm = $element.controller("form");
    var dirtyCellsByRow = [];
    var invalidCellsByRow = [];

    init();

    ////////

    function init() {
      var setter = $parse($attrs.demoTrackedTable).assign;
      setter($scope, self);
      $scope.$on("$destroy", function() {
        setter(null);
      });

      self.reset = reset;
      self.isCellDirty = isCellDirty;
      self.setCellDirty = setCellDirty;
      self.setCellInvalid = setCellInvalid;
      self.untrack = untrack;
    }

    function getCellsForRow(row, cellsByRow) {
      return _.find(cellsByRow, function(entry) {
        return entry.row === row;
      })
    }

    function isCellDirty(row, cell) {
      var rowCells = getCellsForRow(row, dirtyCellsByRow);
      return rowCells && rowCells.cells.indexOf(cell) !== -1;
    }

    function reset() {
      dirtyCellsByRow = [];
      invalidCellsByRow = [];
      setInvalid(false);
    }

    function setCellDirty(row, cell, isDirty) {
      setCellStatus(row, cell, isDirty, dirtyCellsByRow);
    }

    function setCellInvalid(row, cell, isInvalid) {
      setCellStatus(row, cell, isInvalid, invalidCellsByRow);
      setInvalid(invalidCellsByRow.length > 0);
    }

    function setCellStatus(row, cell, value, cellsByRow) {
      var rowCells = getCellsForRow(row, cellsByRow);
      if (!rowCells && !value) {
        return;
      }

      if (value) {
        if (!rowCells) {
          rowCells = {
            row: row,
            cells: []
          };
          cellsByRow.push(rowCells);
        }
        if (rowCells.cells.indexOf(cell) === -1) {
          rowCells.cells.push(cell);
        }
      } else {
        _.remove(rowCells.cells, function(item) {
          return cell === item;
        });
        if (rowCells.cells.length === 0) {
          _.remove(cellsByRow, function(item) {
            return rowCells === item;
          });
        }
      }
    }

    function setInvalid(isInvalid) {
      self.$invalid = isInvalid;
      self.$valid = !isInvalid;
    }

    function untrack(row) {
      _.remove(invalidCellsByRow, function(item) {
        return item.row === row;
      });
      _.remove(dirtyCellsByRow, function(item) {
        return item.row === row;
      });
      setInvalid(invalidCellsByRow.length > 0);
    }
  }
})();