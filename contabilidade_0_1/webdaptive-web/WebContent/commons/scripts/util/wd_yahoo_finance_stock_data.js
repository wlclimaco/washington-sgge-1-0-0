(function($) {
	function getStock(opts, complete) {
	    var defs = {
	        desc: false,
	        baseURL: 'http://query.yahooapis.com/v1/public/yql?q=',
	        query: 'select {display} from yahoo.finance.quotes where symbol in ({quotes}) | sort(field="{sortBy}", descending="{desc}")',
	        suffixURL: '&env=store://datatables.org/alltableswithkeys&format=json&callback=?'
	    };

	    opts = $.extend({
	        display: ['*'],
	        stocks: []
	    }, opts || {});

	    if (!opts.stocks.length) {
	        complete('No stock defined');
	        return;
	    }

	    var query = {
	        display: opts.display.join(', '),
	        quotes: opts.stocks.map(function (stock) {
	            return '"' + stock + '"';
	        }).join(', ')
	    };

	    defs.query = defs.query
	        .replace('{display}', query.display)
	        .replace('{quotes}', query.quotes)
	        .replace('{sortBy}', defs.sortBy)
	        .replace('{desc}', defs.desc);

	    defs.url = defs.baseURL + defs.query + defs.suffixURL;
        $.ajax({
            url		: defs.url,
            dataType: 'json',
            timeout	: 5000,
            success	: function( data ) {
	            var err = null;
	            if (!data || !data.query)
                {
	               err = true;
	            }
	            complete(err, !err && data.query.results);
            },
            error	: function( jqXHR, textStatus, errorThrown ) {
                var err = textStatus + ", " + errorThrown;
                complete(err, null);
            }
         });
	}
    window.getStock = getStock;
})(jQuery);
