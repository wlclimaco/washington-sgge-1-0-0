<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var csloader = new Slick.Data.RemoteModel();
$(document).ready(function ()
{
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });
	csgrid = new Slick.Grid($("#countiesMVCBASGrid"), csloader.data, columns, options);
	csgrid.setSelectionModel(new Slick.RowSelectionModel());
	setTimeout('csgrid.init()', 250);

	csloader.onCountyDataLoading.subscribe(function()
	{
		wd.core.blockUIMessage('div.ui-layout-center',true,true,wdloading.title,wdloading.msg);
	});

	csloader.onCountyDataLoaded.subscribe(function()
	{
		csgrid.invalidateAllRows();
		csgrid.updateRowCount();
		csgrid.setSelectedRows([]);
		csgrid.resetActiveCell();
		csgrid.render();
		wd.core.unblockUIMessage('div.ui-layout-center');
	});

	// load the Grid first time
    csloader.callFetchWS();
});
</script>
