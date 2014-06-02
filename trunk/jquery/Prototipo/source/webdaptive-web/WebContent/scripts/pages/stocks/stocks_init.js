$(document).ready(function ()
{
	//this events fires to blockui while the data is retrieved
	onStockDataLoading.subscribe(function()
	{
		wd.core.blockUIMessage('div.ui-layout-center',true,true,wdloading.title,wdloading.msg);
	});

	//this event fill data in table after call to yahoo
	onStockDataLoaded.subscribe(function()
	{
		stock_dt.fnClearTable(false);
		stock_dt.fnAddData(stock_data);
		wd.core.unblockUIMessage('div.ui-layout-center');
	});

	//paints initial table with empty data
	stock_dt = 	$('#dt_container').dataTable( {
						 "bJQueryUI" : true,
						 "bPaginate" : false,
						 "bFilter"   : false,
						 "aaData"	 : stock_data,
						"aoColumns"	 : columns_def
	} );

	//gets the stocks (calls yahoo using yql)
	call_get_stocks();
});
