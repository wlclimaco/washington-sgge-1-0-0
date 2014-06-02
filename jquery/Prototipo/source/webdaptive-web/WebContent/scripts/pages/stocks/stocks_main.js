var stock_dt;
var stock_data = new Array();
var onStockDataLoading = new EventHelper();
var onStockDataLoaded = new EventHelper();

var columns_def = [
            { "sTitle": stock.dt.symbol.title,  "mDataProp": "symbol", "sClass": "left", "sWidth": "130px" },
            { "sTitle": stock.dt.coname.title, "mDataProp": "coname" , "sClass": "left", "sWidth": "135px" },
            { "sTitle": stock.dt.sexch.title, "mDataProp": "sexch", "sClass": "left", "sWidth": "135px" },
            { "sTitle": stock.dt.mcap.title, "mData": "mcap", "sClass": "left", "sWidth": "100px", "bSortable": false },
            { "sTitle": stock.dt.yrrange.title, "mDataProp": "yrrange", "sClass": "left", "sWidth": "135px", "bSortable": false },
            { "sTitle": stock.dt.ltradeprc.title, "mDataProp": "ltradeprc", "sClass": "left", "sWidth": "135px"},
            { "sTitle": stock.dt.ltradedte.title, "mDataProp": "ltradedte", "sClass": "left", "sWidth": "185px", "bSortable": false }
];

//calls yahoo to get stock info via yql
function call_get_stocks()
{
	//fires data loading blockui event
	onStockDataLoading.notify({});
	//demo purposes only
	getStock({stocks: ['AAPL', 'AMZN', 'AXP', 'FDX', 'GOOG', 'IBM', 'MSFT','ORCL']}, function(err, data) {
		if (err != null || !data || !data.quote)
		{
			process_error(err);
		}
		else
		{
			fill_data(data);
		}
	});
};

//fills the data array from the yahoo results
function fill_data(data)
{
	//loads the data from yahoo into structure datatable is expecting
	for (var i=0; i < data.quote.length; i++)
	{
		stock_data[i] =
		{
			symbol:     data.quote[i].Symbol,
			coname: 	data.quote[i].Name,
			sexch:     	data.quote[i].StockExchange,
			mcap: 		data.quote[i].MarketCapitalization,
			yrrange: 	data.quote[i].YearRange,
			ltradeprc: 	data.quote[i].LastTradePriceOnly,
			ltradedte:  data.quote[i].LastTradeDate + " @ "  +  data.quote[i].LastTradeTime + " EST"
		}
	}
	//fires data loaded event
	onStockDataLoaded.notify({});
};

function process_error(errorThrown)
{
	wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
	onProcDataLoaded.notify({});
};

