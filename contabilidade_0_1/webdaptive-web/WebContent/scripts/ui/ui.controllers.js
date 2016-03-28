(function() {
  'use strict';
  angular.module('wdApp.ui.controllers', []).controller('NotifyController', [
    '$scope', 'toastr', 'toastrConfig', function($scope, toastr, toastrConfig) {
		
	  toastrConfig.positionClass = 'toast-bottom-right';
      toastrConfig.closeButton = true;

      return $scope.notify = function(type) {
        switch (type) {
          case 'info':
            return toastr.info('Heads up! This alert needs your attention, but it\'s not super important.', 'Information');
          case 'success':
            return toastr.success('Well done! You successfully read this important alert message.');
          case 'warning':
            return toastr.warning('Warning! Best check yo self, you\'re not looking too good.', 'Warning');
          case 'error':
            return toastr.error('Oh snap! Change a few things up and try submitting again.', 'Error');
        }
      };
    }
  ]).controller('AlertDemoController', [
    '$scope', function($scope) {
      $scope.alerts = [
        {
          type: 'success',
          msg: 'Well done! You successfully read this important alert message.'
        }, {
          type: 'info',
          msg: 'Heads up! This alert needs your attention, but it is not super important.'
        }, {
          type: 'warning',
          msg: "Warning! Best check yourself, you're not looking too good."
        }, {
          type: 'danger',
          msg: 'Oh snap! Change a few things up and try submitting again.'
        }
      ];
      $scope.addAlert = function() {
        var num, type;
        num = Math.ceil(Math.random() * 4);
        type = void 0;
        switch (num) {
          case 0:
            type = 'info';
            break;
          case 1:
            type = 'success';
            break;
          case 2:
            type = 'info';
            break;
          case 3:
            type = 'warning';
            break;
          case 4:
            type = 'danger';
        }
        return $scope.alerts.push({
          type: type,
          msg: "Another alert!"
        });
      };
      return $scope.closeAlert = function(index) {
        return $scope.alerts.splice(index, 1);
      };
    }
  ]).controller('ProgressDemoController', [
    '$scope', function($scope) {
      $scope.max = 200;
      $scope.random = function() {
        var type, value;
        value = Math.floor((Math.random() * 100) + 10);
        type = void 0;
        if (value < 25) {
          type = "success";
        } else if (value < 50) {
          type = "info";
        } else if (value < 75) {
          type = "warning";
        } else {
          type = "danger";
        }
        $scope.showWarning = type === "danger" || type === "warning";
        $scope.dynamic = value;
        $scope.type = type;
      };
      return $scope.random();
    }
  ]).controller('AccordionDemoController', [
    '$scope', function($scope) {
      $scope.oneAtATime = true;
      $scope.groups = [
        {
          title: "Dynamic Group Header - 1",
          content: "Dynamic Group Body - 1"
        }, {
          title: "Dynamic Group Header - 2",
          content: "Dynamic Group Body - 2"
        }, {
          title: "Dynamic Group Header - 3",
          content: "Dynamic Group Body - 3"
        }
      ];
      $scope.items = ["Item 1", "Item 2", "Item 3"];
      $scope.status = {
        isFirstOpen: true,
        isFirstOpen1: true,
        isFirstOpen2: true,
        isFirstOpen3: true,
        isFirstOpen4: true,
        isFirstOpen5: true,
        isFirstOpen6: true
      };
      $scope.addItem = function() {
        var newItemNo;
        newItemNo = $scope.items.length + 1;
        $scope.items.push("Item " + newItemNo);
      };
    }
  ]).controller('CollapseDemoController', [
    '$scope', function($scope) {
      return $scope.isCollapsed = false;
    }
  ]).controller('ModalDemoController', [
    '$scope', '$uibModal', '$log', function($scope, $uibModal, $log) {
      $scope.items = ["item1", "item2", "item3"];
      $scope.open = function() {
        var modalInstance;
        modalInstance = $uibModal.open({
          templateUrl: "myModalContent.html",
          controller: 'ModalInstanceController',
          resolve: {
            items: function() {
              return $scope.items;
            }
          }
        });
        modalInstance.result.then((function(selectedItem) {
          $scope.selected = selectedItem;
        }), function() {
          $log.info("Modal dismissed at: " + new Date());
        });
      };
    }
  ]).controller('ModalInstanceController', [
    '$scope', '$uibModalInstance', 'items', function($scope, $uibModalInstance, items) {
      $scope.items = items;
      $scope.selected = {
        item: $scope.items[0]
      };
      $scope.ok = function() {
        $uibModalInstance.close($scope.selected.item);
      };
      $scope.cancel = function() {
        $uibModalInstance.dismiss("cancel");
      };
    }
  ]).controller('PaginationDemoController', [
    '$scope', function($scope) {
      $scope.totalItems = 64;
      $scope.currentPage = 4;
      $scope.setPage = function(pageNo) {
        return $scope.currentPage = pageNo;
      };
      $scope.maxSize = 5;
      $scope.bigTotalItems = 175;
      return $scope.bigCurrentPage = 1;
    }
  ]).controller('TabsDemoController', [
    '$scope', function($scope) {
      $scope.tabs = [
        {
          title: "Dynamic Title 1",
          content: "Dynamic content 1.  Consectetur adipisicing elit. Nihil, quidem, officiis, et ex laudantium sed cupiditate voluptatum libero nobis sit illum voluptates beatae ab. Ad, repellendus non sequi et at."
        }, {
          title: "Disabled",
          content: "Dynamic content 2.  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil, quidem, officiis, et ex laudantium sed cupiditate voluptatum libero nobis sit illum voluptates beatae ab. Ad, repellendus non sequi et at.",
          disabled: true
        }
      ];
      return $scope.navType = "pills";
    }
  ]).controller('TreeDemoController', [
    '$scope','DemoData', function($scope, DemoData) {

	  DemoData.getDemoData('./demodata/dyntree.json', function(data){
			$scope.list = data;		 
	  });
      $scope.selectedItem = {};
      $scope.options = {};
      $scope.remove = function(scope) {
        scope.remove();
      };
      $scope.toggle = function(scope) {
        scope.toggle();
      };
      return $scope.newSubItem = function(scope) {
        var nodeData;
        nodeData = scope.$modelValue;
        nodeData.items.push({
          id: nodeData.id * 10 + nodeData.items.length,
          title: nodeData.title + "." + (nodeData.items.length + 1),
          items: []
        });
      };
    }
  ]).controller('MapDemoController', [
    '$scope', '$http', '$interval', function($scope, $http, $interval) {
      var i, markers;
      markers = [];
      i = 0;
      while (i < 8) {
        markers[i] = new google.maps.Marker({
          title: "Marker: " + i
        });
        i++;
      }
      $scope.GenerateMapMarkers = function() {
        var d, lat, lng, loc, numMarkers;
        d = new Date();
        $scope.date = d.toLocaleString();
        numMarkers = Math.floor(Math.random() * 4) + 4;
        i = 0;
        while (i < numMarkers) {
          lat = 40.7500000 + (Math.random() / 100);
          lng = -73.9800000 + (Math.random() / 100);
          loc = new google.maps.LatLng(lat, lng);
          markers[i].setPosition(loc);
          markers[i].setMap($scope.map);
          i++;
        }
      };
      $interval($scope.GenerateMapMarkers, 2000);
    }
  ]).controller('LoaderController', ['$scope', 'cfpLoadingBar', function($scope, cfpLoadingBar) {
    $scope.start = function() {
      return cfpLoadingBar.start();
    };
    $scope.inc = function() {
      return cfpLoadingBar.inc();
    };
    $scope.set = function() {
      return cfpLoadingBar.set(0.3);
    };
    return $scope.complete = function() {
      return cfpLoadingBar.complete();
    };
   }
 ]);

}).call(this);

