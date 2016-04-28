(function() {
  'use strict';
  angular.module('wdApp.charts.morris.controllers', []).controller('MorrisChartController', [
    '$scope', function($scope) {
      var barColor, barData, comboColor, comboData, donutColor, donutData, mainColor, mainData, simpleColor, simpleData;
      mainData = [
        {
          month: '2016-01',
          xbox: 294000,
          will: 136000,
          playstation: 244000
        }, {
          month: '2016-02',
          xbox: 228000,
          will: 335000,
          playstation: 127000
        }, {
          month: '2016-03',
          xbox: 199000,
          will: 159000,
          playstation: 130000
        }, {
          month: '2016-04',
          xbox: 174000,
          will: 160000,
          playstation: 82000
        }, {
          month: '2016-05',
          xbox: 255000,
          will: 318000,
          playstation: 82000
        }, {
          month: '2016-06',
          xbox: 298400,
          will: 401800,
          playstation: 98600
        }, {
          month: '2016-07',
          xbox: 370000,
          will: 225000,
          playstation: 159000
        }, {
          month: '2016-08',
          xbox: 376700,
          will: 303600,
          playstation: 130000
        }, {
          month: '2016-09',
          xbox: 527800,
          will: 301000,
          playstation: 119400
        }
      ];
      mainColor = [$scope.color.infoAlt, $scope.color.danger, $scope.color.success];
      $scope.main = {
        data: mainData,
        type: 'area',
        options: {
          xkey: "month",
          ykeys: ["xbox", "will", "playstation"],
          labels: ["xbox", "will", "playstation"],
          lineColors: mainColor,
          lineWidth: 0,
          behaveLikeLine: true,
          pointSize: 0
        }
      };
      simpleData = [
        {
          year: '2011',
          value: 20
        }, {
          year: '2012',
          value: 10
        }, {
          year: '2013',
          value: 5
        }, {
          year: '2014',
          value: 5
        }, {
          year: '2015',
          value: 20
        }, {
          year: '2016',
          value: 19
        }
      ];
      simpleColor = [$scope.color.primary];
      $scope.simple1 = {
        data: simpleData,
        type: "line",
        options: {
          xkey: "year",
          ykeys: ["value"],
          labels: ["Value"],
          lineWidth: "2",
          lineColors: simpleColor
        }
      };
      $scope.simple2 = {
        data: simpleData,
        type: "area",
        options: {
          xkey: "year",
          ykeys: ["value"],
          labels: ["Value"],
          lineWidth: "2",
          lineColors: simpleColor
        }
      };
      comboData = [
        {
          month: '1',
          a: 20,
          b: 30
        }, {
          month: '2',
          a: 30,
          b: 20
        }, {
          month: '3',
          a: 20,
          b: 10
        }, {
          month: '4',
          a: 10,
          b: 20
        }, {
          month: '5',
          a: 20,
          b: 30
        }, {
          month: '6',
          a: 30,
          b: 20
        }, {
          month: '7',
          a: 20,
          b: 10
        }, {
          month: '8',
          a: 10,
          b: 20
        }, {
          month: '9',
          a: 20,
          b: 30
        }, {
          month: '10',
          a: 30,
          b: 20
        }, {
          month: '11',
          a: 20,
          b: 10
        }, {
          month: '12',
          a: 10,
          b: 20
        }
      ];
      comboColor = [$scope.color.success, $scope.color.danger, $scope.color.infoAlt];
      $scope.combo1 = {
        data: comboData,
        type: "line",
        options: {
          xkey: "month",
          ykeys: ["a", "b"],
          labels: ["Value A", "Value B"],
          lineWidth: "2",
          lineColors: comboColor
        }
      };
      $scope.combo2 = {
        data: comboData,
        type: "area",
        options: {
          xkey: "month",
          ykeys: ["a", "b"],
          labels: ["Value A", "Value B"],
          lineWidth: "2",
          lineColors: comboColor
        }
      };
      barData = [
        {
          year: '2011',
          a: 20,
          b: 16,
          c: 12
        }, {
          year: '2012',
          a: 10,
          b: 22,
          c: 30
        }, {
          year: '2013',
          a: 5,
          b: 14,
          c: 20
        }, {
          year: '2014',
          a: 5,
          b: 12,
          c: 19
        }, {
          year: '2015',
          a: 20,
          b: 19,
          c: 13
        }, {
          year: '2016',
          a: 28,
          b: 22,
          c: 20
        }
      ];
      barColor = [$scope.color.infoAlt, $scope.color.success, $scope.color.warning];
      $scope.bar1 = {
        data: barData,
        type: "bar",
        options: {
          xkey: "year",
          ykeys: ["a", "b", "c"],
          labels: ["Value A", "Value B", "Value C"],
          barColors: barColor
        }
      };
      $scope.bar2 = {
        data: barData,
        type: "bar",
        options: {
          xkey: "year",
          ykeys: ["a", "b", "c"],
          labels: ["Value A", "Value B", "Value C"],
          barColors: barColor,
          stacked: true
        }
      };
      donutColor = [$scope.color.success, $scope.color.info, $scope.color.warning, $scope.color.danger];
      donutData = [
        {
          label: "Download Sales",
          value: 12
        }, {
          label: "In-Store Sales",
          value: 30
        }, {
          label: "Mail-Order Sales",
          value: 20
        }, {
          label: "Online Sales",
          value: 19
        }
      ];
      $scope.donut1 = {
        data: donutData,
        type: 'donut',
        options: {
          xkey: "year"
        }
      };
      $scope.donut2 = {
        data: donutData,
        type: 'donut',
        options: {
          xkey: "year",
          colors: donutColor
        }
      };
      return $scope.donut3 = {
        data: donutData,
        type: 'donut',
        options: {
          xkey: "year",
          formatter: "return '$' + y;"
        }
      };
    }
  ]);

}).call(this);
