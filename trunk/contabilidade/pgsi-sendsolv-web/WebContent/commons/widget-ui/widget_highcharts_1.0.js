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
	         		pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> ' + $.pgsi.locale.get("dashboard.highchart.column.total.text") + '<br/>'
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

		var sBaseColumnColor = "#8AAC9D";
		var sBasePieColor = "#cc6666";

		switch (pgsi.settings.serviceType)
		{
			case pgsi.constants.services.water.name:
				sBaseColumnColor = "#00929F";
				break;
			case pgsi.constants.services.gas.name:
				sBaseColumnColor = "#DFA803";
				break;
			case pgsi.constants.services.electric.name:
				sBaseColumnColor = "#8AAC9D";
			default:
				break;
		}

		// Make monochrome colors and set them as default for all pies
		Highcharts.getOptions().plotOptions.column.colors = (function () {

			var colors = [],
				base = sBaseColumnColor,
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
	            base = "#B70000",
	            i;

	        for (i = 0; i < 10; i += 1) {
	            // Start out with a darkened base color (negative brighten), and end
	            // up with a much brighter color
	            colors.push(Highcharts.Color(base).brighten((i - 2) / 7).get());
	        }
	        return colors;
	    }());

		// Make monochrome colors and set them as default for all pies
		/*Highcharts.getOptions().plotOptions.pie.colors = (function () {
            var colors = [],
                //base = Highcharts.getOptions().colors[3],
                base = sBasePieColor,
                i

            for (i = 0; i < 10; i++) {
                // Start out with a darkened base color (negative brighten), and end
                // up with a much brighter color
                colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
            }
            return colors;
		}());*/

		$(this.element).highcharts(oHighcharts);
	}
 });