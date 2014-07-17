$.widget("custom.highchart", {

	options : {

		sType 	: null,

		specifc : {},

		commons : {
			drilldownColumn : {

				 chart		: {
					 type : "column"
	             },

	             title 		: null,
	             subtitle 	: null,

	             xAxis 		: {
	            	 type : "category"
	             },

	             yAxis 		: null,

	             legend 	: {
	            	 enabled : false
	             },

	            credits 	: {
						enabled 	: false
				},

	            tooltip: {
	         		headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	         		pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
	         	},

	        	series 		: null,
	        	drilldown 	: null
			},

			donut : {

				chart 		: {
					type 			: "pie",
					backgroundColor : "Transparent",
					spacing 		: [25, 50, 25, 50]
				},

				credits 	: {
					enabled 	: false
				},

				title : {
					text 	: "",
					y		: 10,
					verticalAlign : "bottom"
				},

				tooltip 	: {
					enabled : true,
	         		pointFormat: '<span>{series.name}: <b>{point.y}</b></span><br/>',
					backgroundColor : "rgba(255, 255, 255, 0.95)"
				},

				plotOptions	: {
					pie: {
						borderColor	: '#ffffff',
						innerSize	: '75%',
						dataLabels	: {
							enabled: false
						}
					}
				},

				series 		: null
			}
		}
	},

	_create : function() {

		var oHighcharts = $.extend({}, this.options.commons[this.options.sType], this.options.specifc);

		// Add the background image to the container
		Highcharts.wrap(Highcharts.Chart.prototype, 'getContainer', function (proceed) {
			proceed.call(this);
			this.container.style.background = 'none';
		});

		// Make monochrome colors and set them as default for all pies
		Highcharts.getOptions().plotOptions.column.colors = (function () {

			var colors = [],
				base = "#8AAC9D",
				i;

			for (i = 0; i < 10; i++) {

				// Start out with a darkened base color (negative brighten), and end
				// up with a much brighter color
				colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
			}

			return colors;
		}());

		// Make monochrome colors and set them as default for all pies
		Highcharts.getOptions().plotOptions.pie.colors = (function () {
            var colors = [],
                //base = Highcharts.getOptions().colors[3],
                base = "#cc6666",
                i

            for (i = 0; i < 10; i++) {
                // Start out with a darkened base color (negative brighten), and end
                // up with a much brighter color
                colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
            }
            return colors;
		}());

		$(this.element).highcharts(oHighcharts);
	}
 });