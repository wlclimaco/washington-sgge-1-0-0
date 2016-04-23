(function() {
  'use strict';
  angular.module('wdApp.tables.controllers', []).controller('TableController', [
    '$scope', '$filter', 'DemoData', function($scope, $filter, DemoData) {
	  var init;
      DemoData.getDemoData('./demodata/dyntable.json', function(data){
          $scope.stores = data;
		  $scope.searchKeywords = '';
		  $scope.filteredStores = [];
		  $scope.row = '';
		  $scope.select = function(page) {
			var end, start;
			start = (page - 1) * $scope.numPerPage;
			end = start + $scope.numPerPage;
			return $scope.currentPageStores = $scope.filteredStores.slice(start, end);
		  };
		  $scope.onFilterChange = function() {
			$scope.select(1);
			$scope.currentPage = 1;
			return $scope.row = '';
		  };
		  $scope.onNumPerPageChange = function() {
			$scope.select(1);
			return $scope.currentPage = 1;
		  };
		  $scope.onOrderChange = function() {
			$scope.select(1);
			return $scope.currentPage = 1;
		  };
		  $scope.search = function() {
			$scope.filteredStores = $filter('filter')($scope.stores, $scope.searchKeywords);
			return $scope.onFilterChange();
		  };
		  $scope.order = function(rowName) {
			if ($scope.row === rowName) {
			  return;
			}
			$scope.row = rowName;
			$scope.filteredStores = $filter('orderBy')($scope.stores, rowName);
			return $scope.onOrderChange();
		  };
		  $scope.numPerPageOpt = [5, 10, 15, 20];
		  $scope.numPerPage = $scope.numPerPageOpt[2];
		  $scope.currentPage = 1;
		  $scope.currentPageStores = [];
		  init = function() {
			$scope.search();
			return $scope.select($scope.currentPage);
		  };
		  return init();			
       });
	

    }
  ]);

}).call(this);
