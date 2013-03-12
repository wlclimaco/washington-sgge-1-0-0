/**
 * jquery.numberFormat - Formatting Numbers in jQuery
 * Written by Leandro Ferraz (zarrefl@gmail.com)
 *
 * Licensed under the GPL (GPL-LICENSE.txt) licenses.
 *
 * Date: 08/02/2010 (mm/dd/aaaa)
 *
 * @author Leandro Ferraz
 * @version 1.0.0
 *
 *
 * This plugin can be used to format numbers as text.
 *
 * The numberFormat() function will take the text within any selector by calling,
 * getting the String, and applying the specified format to it.
 *
 * It will return the jQuery object.
 *
 * 
 **/

//indica que est� sendo criado um plugin
jQuery.fn.numberFormat = function(value, options) {

	var defaults = {
		decimalsSeparator: '', 
		thousandsSeparator: ',',
		limit: false,
		decimalsLimit: 0
	};

	var opts = $.extend(defaults, options);

	// pre defined options
	var str = new String(value);
	var is_number = /[0-9]/;
	var neg = "-";

	// load the pluggings settings
	var decimalsSeparator = opts.decimalsSeparator;
	var thousandsSeparator = opts.thousandsSeparator;
	var limit = opts.limit;
	var decimalsLimit = opts.decimalsLimit;

	// skip everything that isn't a number
	// and also skip the left zeroes
	function to_numbers (str) {
		var formatted = '';
		for (var i=0;i<(str.length);i++) {
			char = str.charAt(i);
			if (formatted.length==0 && char==0) char = false;
			if (char && char.match(is_number)) {
				if (limit) {
					if (formatted.length < limit) formatted = formatted+char;
				}else{
					formatted = formatted+char;
				}
			}
		}
		return formatted;
	}
	// format to fill with zeros to complete decimals chars
	function fill_with_zeroes (str) {
		while (str.length<(decimalsLimit+1)) str = '0'+str;
		return str;
	}
	// format as number
	function number_format (str) {
		// formatting settings
		var formatted = fill_with_zeroes(to_numbers(str));
		var thousandsFormatted = '';
		var thousandsCount = 0;
		// split integer from decimals
		var decimalsVal = formatted.substr(formatted.length-decimalsLimit,decimalsLimit);
		var integerVal = formatted.substr(0,formatted.length-decimalsLimit);
		// apply decimals pontuation
		formatted = integerVal+decimalsSeparator+decimalsVal;
		// apply thousands pontuation
		if (thousandsSeparator) {
			for (var j=integerVal.length;j>0;j--) {
				char = integerVal.substr(j-1,1);
				thousandsCount++;
				if (thousandsCount%3==0) char = thousandsSeparator+char;
				thousandsFormatted = char+thousandsFormatted;
			}
			if (thousandsFormatted.substr(0,1)==thousandsSeparator) thousandsFormatted = thousandsFormatted.substring(1,thousandsFormatted.length);
			formatted = thousandsFormatted+decimalsSeparator+decimalsVal;
		}
		return formatted;
	}

	// inster formatted number as a value of an input field
	function number_it () {
		var negative = false;
		if (str.match(neg)) negative = true;
		var number = number_format(str.replace(/\D/g, ""));
		if (negative) number = neg + number;
		if (str != number) {
			return number;
		}
		
	}
	if (str.length>0) return number_it();
};