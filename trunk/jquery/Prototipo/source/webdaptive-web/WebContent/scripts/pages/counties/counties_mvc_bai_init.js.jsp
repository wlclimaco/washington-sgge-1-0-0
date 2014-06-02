<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var ciloader = new Slick.Data.RemoteModel();
$(document).ready(function ()
{
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });
	cigrid = new Slick.Grid($("#countiesMVCBAIGrid"), ciloader.data, columns, options);
	cigrid.setSelectionModel(new Slick.RowSelectionModel());
	setTimeout('cigrid.init()', 250);

	ciloader.onCountyDataLoading.subscribe(function()
	{
		wd.core.blockUIMessage('div.ui-layout-center',true,true,wdloading.title,wdloading.msg);
	});

	ciloader.onCountyDataLoaded.subscribe(function()
	{
		cigrid.invalidateAllRows();
		cigrid.updateRowCount();
		cigrid.setSelectedRows([]);
		cigrid.resetActiveCell();
		cigrid.render();
		wd.core.unblockUIMessage('div.ui-layout-center');
	});
	// load the Grid first time
    ciloader.callFetchWS();
});
</script>
