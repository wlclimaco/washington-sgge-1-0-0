(function() {
  'use strict';
    var chartJS = angular.module('wdApp.charts.chartjs.controllers', []); 

	chartJS.config(function (ChartJsProvider) {
		// Configure all charts
		ChartJsProvider.setOptions({
		  colours: ['#97BBCD', '#DCDCDC', '#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
		  responsive: true	  
		});
		// Configure all doughnut charts
		ChartJsProvider.setOptions('Doughnut', {
		  animateScale: true
		});
	 });
	
    chartJS.controller('ChartjsLineController', function ($scope) {
		var cvm = this;
		cvm.linelabels = ["January", "February", "March", "April", "May", "June", "July"];
		cvm.lineseries = ['Series L1', 'Series L2'];
		cvm.linedata = [
			[65, 59, 80, 81, 56, 55, 40],
			[28, 48, 40, 19, 86, 27, 90]
		];
    }); 
	
    chartJS.controller('ChartjsBarController', function ($scope) {
		var cvm = this;		
		cvm.barlabels = ['2011', '2012', '2013', '2014', '2015', '2016', '2017'];
		cvm.barseries = ['Series B1', 'Series B2'];

		cvm.bardata = [
			[65, 59, 80, 81, 56, 55, 40],
			[28, 48, 40, 19, 86, 27, 90]
		];
    }); 

	chartJS.controller('ChartjsPieController', function ($scope) {
		var cvm = this;				
		cvm.pielabels = ['Online Sales', 'In-Store Sales', 'Mail-Order Sales'];
		cvm.piedata = [300, 500, 100];
		cvm.pieoptions = {
		  tooltipEvents: [],
		  showTooltips: true,
		  tooltipCaretSize: 0,
		  onAnimationComplete: function () {
				this.showTooltip(this.segments, true);
		  },
		};		
		
	});
	
    chartJS.controller('ChartjsRadarController', function ($scope) {
		var cvm = this;				
	  cvm.radarlabels =["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"];
	  cvm.radarseries = ['Series R1', 'Series R2'];
	  cvm.radardata = [
		[65, 59, 90, 81, 56, 55, 40],
		[28, 48, 40, 19, 96, 27, 100]
	  ];
    }); 

    chartJS.controller('ChartjsDoughnutController', function ($scope) {
		var cvm = this;				
		cvm.doughnutlabels = ['Online Sales', 'In-Store Sales', 'Mail-Order Sales'];
		cvm.doughnutdata = [300, 500, 100];
		cvm.doughnutoptions = {
		  tooltipEvents: [],
		  showTooltips: true,
		  tooltipCaretSize: 0,
		  onAnimationComplete: function () {
				this.showTooltip(this.segments, true);
		  },
		};	
    }); 
	
    chartJS.controller('ChartjsPolarController', function ($scope) {
		var cvm = this;				
		cvm.polarlabels = ['Online Sales', 'In-Store Sales', 'Mail-Order Sales', 'Tele Sales', 'Corporate Sales'];
		cvm.polardata = [300, 500, 100, 140, 120];
    });	
	
	chartJS.controller('ChartjsReactiveController', function ($scope) {
		var cvm = this;				
		cvm.reactivelabels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
		cvm.reactivedata = [
		  [65, 59, 80, 81, 56, 55, 40],
		  [28, 48, 40, 19, 86, 27, 90]
		];
		cvm.reactivecolours = [
		  { // grey
			fillColor: 'rgba(148,159,177,0.2)',
			strokeColor: 'rgba(148,159,177,1)',
			pointColor: 'rgba(148,159,177,1)',
			pointStrokeColor: '#fff',
			pointHighlightFill: '#fff',
			pointHighlightStroke: 'rgba(148,159,177,0.8)'
		  },
		  { // dark grey
			fillColor: 'rgba(77,83,96,0.2)',
			strokeColor: 'rgba(77,83,96,1)',
			pointColor: 'rgba(77,83,96,1)',
			pointStrokeColor: '#fff',
			pointHighlightFill: '#fff',
			pointHighlightStroke: 'rgba(77,83,96,1)'
		  }
		];
		cvm.randomize = function () {
		  cvm.reactivedata = cvm.reactivedata.map(function (data) {
			return data.map(function (y) {
			  y = y + Math.random() * 10 - 5;
			  return parseInt(y < 0 ? 0 : y > 100 ? 100 : y);
			});
		  });
		};
	});

    chartJS.controller('ChartjsDynamicController', function ($scope) {
		var cvm = this;				
		cvm.dynlabels = ['Online Sales', 'In-Store Sales', 'Mail-Order Sales', 'Tele Sales', 'Corporate Sales'];
		cvm.dyndata = [300, 500, 100, 140, 120];
		cvm.type = 'PolarArea';
		cvm.dynoptions = {
		  tooltipEvents: [],
		  showTooltips: true,
		  tooltipCaretSize: 0,
		  onAnimationComplete: function () {
			    if (cvm.type === 'Pie')
			    {	   
					this.showTooltip(this.segments, true);
			   	}	
		  },
		};		

		cvm.toggle = function () {
		  cvm.type = cvm.type === 'PolarArea' ?
			'Pie' : 'PolarArea';
		};
    }); 	

})();
