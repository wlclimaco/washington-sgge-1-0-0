/***
 * Contains basic SlickGrid formatters.
 * 
 * NOTE:  These are merely examples.  You will most likely need to implement something more
 *        robust/extensible/localizable/etc. for your use!
 * 
 * @module Formatters
 * @namespace Slick
 */

(function ($) {
  // register namespace
  $.extend(true, window, {
    "Slick": {
      "Formatters": {
        "PercentComplete": PercentCompleteFormatter,
        "PercentCompleteBar": PercentCompleteBarFormatter,
        "YesNo": YesNoFormatter,
		"HTML": HTMLFormatter,		
		"Currency": CurrencyFormatter,		
        "Checkmark": CheckmarkFormatter
      }
    }
  });

  function PercentCompleteFormatter(row, cell, value, columnDef, dataContext) {
    if (value == null || value === "") {
      return "-";
    } else if (value < 50) {
      return "<span style='color:red;font-weight:bold;'>" + value + "%</span>";
    } else {
      return "<span style='color:green'>" + value + "%</span>";
    }
  }

  function PercentCompleteBarFormatter(row, cell, value, columnDef, dataContext) {
    if (value == null || value === "") {
      return "";
    }

    var color;

    if (value < 30) {
      color = "red";
    } else if (value < 70) {
      color = "silver";
    } else {
      color = "green";
    }

    return "<span class='percent-complete-bar' style='background:" + color + ";width:" + value + "%'></span>";
  }

  function YesNoFormatter(row, cell, value, columnDef, dataContext) {
    return value ? "Yes" : "No";
  }
  
  function addCommas(input) {
	var pair = (input + '').split('.');
	var integer = pair[0];
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(integer)) {
		integer = integer.replace(rgx, '$1' + ',' + '$2');
	}
	var decimals = "00";
	 
	if (pair[1])
	{
	  decimals = pair[1];
	}
	 
	if (decimals.length == 1)
	{
	  decimals = "0" + decimals;
	}
	return integer + '.' + decimals;
  }
  
  function CurrencyFormatter(row, cell, value, columnDef, dataContext) {
	return "$" + addCommas(value);
  } 
  
  function HTMLFormatter(row, cell, value, columnDef, dataContext) {
    return value;
  } 
  
  function CheckmarkFormatter(row, cell, value, columnDef, dataContext) {
    return value ? "<img src='../images/tick.png'>" : "";
  }
})(jQuery);
