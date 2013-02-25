/**
 * Plugin responsible for uploading file
 *
 * First load the page, Converter-file.jsp.
 * According opens a new window with the input to upload.
 */

(function($) {

	$.convertionNumber = function(dValue,sEnergy,decimals) {



		var  dvalueConverter=dValue,
	    sNmConverter='',
		convertionEnergy = sensus.settings.convertUnit;
		/**
		 *
		 */
		if(sensus.settings.LANGUAGE === 'en_US'){
			thousands_sep = ",";
			dec_point = ".";
		}else{
			thousands_sep = "."
			dec_point = ",";
		}
		if(sEnergy === true || sEnergy=== "" ){
			if(convertionEnergy === 'true'){
				if((dValue*0.000001) < 1){
					sNmConverter = 'kWh';
					dvalueConverter = (dValue*0.01)*100;
				}
				if(((dValue*0.000001) > 1)&&((dValue*0.000000001) < 1)){
					sNmConverter = 'MWh';
					dvalueConverter = dValue*0.000001;
				}
				if(((dValue*0.000000001) > 1)&&((dValue*0.000000000001) < 1)){
					sNmConverter = 'GWh';
					dvalueConverter = dValue*0.000000001;
				}
				if(((dValue*0.000000000001) > 1)&&((dValue*0.000000000000001) < 1)){
					sNmConverter = 'TWh';
					dvalueConverter = dValue*0.000000000001;
				}
				if((dValue*0.000000000000001) > 1){
					sNmConverter = 'PWh';
					dvalueConverter = dValue*0.000000000000001;
				}

			}else{
				sNmConverter = 'kWh';
				dvalueConverter = (dValue*0.01)*100;
			};
		}

		var number = (dvalueConverter + '').replace(/[^0-9+\-Ee.]/g, '');
		var prec,sep,dec,s,n,decimals;
	    n = !isFinite(+number) ? 0 : +number,

		    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
	        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
	        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
	        s = '',
	        toFixedFix = function (n, prec) {
	            var k = Math.pow(10, prec);
	            return '' + Math.floor(n * k) / k;
	        };
	    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
	    s = (prec ? toFixedFix(n, prec) : '' + Math.floor(n)).split('.');
	    if (s[0].length > 3) {
	        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	    }
	    if ((s[1] || '').length < prec) {
	        s[1] = s[1] || '';
	        s[1] += new Array(prec - s[1].length + 1).join('0');
	    }

		return {
			sNmConverter : sNmConverter,
			dvalueConverter : s.join(dec)
		};

	};
})(jQuery);