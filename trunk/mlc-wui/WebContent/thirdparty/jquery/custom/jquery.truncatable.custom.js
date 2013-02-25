/*
 * truncatable 1.2 - jQuery lightwieght text truncation plugin
 *
 * Copyright (c) 2009 Philip Beel (http://www.theodin.co.uk/)
 * Dual licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) 
 * and GPL (http://www.opensource.org/licenses/gpl-license.php) licenses.
 *
 * Revision: $Id: jquery.truncatable.js 2009-08-20 $
 *
 */

/**
 * Modified by Alexandre Tiveron for truncate Strings on middle words 
 */

(function($){$.fn.truncatable=function(options){
	var defaults={limit:100, more:'...'};
	var options=$.extend(defaults,options);
	return this.each(function(num){
		var stringLength=$(this).html().length;
		if(stringLength>defaults.limit){
			var splitText=$(this).html().substr(0, defaults.limit);
			$(this).html(splitText).append(defaults.more);		
		}	
	});
}})(jQuery);