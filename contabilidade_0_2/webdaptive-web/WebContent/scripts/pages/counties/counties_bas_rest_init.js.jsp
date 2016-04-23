<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var crloader = new Slick.Data.RemoteModel();
$(document).ready(function ()
{
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });

	crgrid = new Slick.Grid($("#countiesGridREST"), crloader.data, columns, options);
	crgrid.setSelectionModel(new Slick.RowSelectionModel());
	setTimeout('crgrid.init()', 250);

	crgrid.onSort.subscribe(function (e, args)
	{
	    sortdir = args.sortAsc ? 1 : -1;
		sortcol = args.sortCol.field;
		if (args.sortAsc == true)
		{
			data.sort(comparer);
		}
		else
		{
			data.reverse(comparer);
		}
		crgrid.invalidateAllRows();
		crgrid.render();
	});

	crloader.onCountyDataLoading.subscribe(function()
	{
		wd.core.blockUIMessage('div.ui-layout-center',true,true,wdloading.title,wdloading.msg);
	});

	crloader.onCountyDataLoaded.subscribe(function()
	{
		crgrid.invalidateAllRows();
		crgrid.updateRowCount();
		crgrid.setSelectedRows([]);
		crgrid.resetActiveCell();
		crgrid.render();
		crgrid.setSortColumn(sortcol,sortdir);
		wd.core.unblockUIMessage('div.ui-layout-center');
	});

	<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
	crgrid.onClick.subscribe(function (e, args)
	{
		$('#cid_rest').val(data[args.row].csak);
		$('#cdesc_rest').val(data[args.row].cdesc);
	});
	$("#clegend_rest").html(county.legend.label);
	$("#lcid_rest").html(county.cid.label);
	$("#lcdesc_rest").html(county.cdesc.label);
	$("#insert_rest").html(county.insert.label);
	$("#update_rest").html(county.update.label);
	$("#delete_rest").html(county.delete.label);
	$("#list_rest").html(county.list.label);
	$("#clear_rest").html(county.clear.label);
	</sec:authorize>
	// load the Grid first time
    crloader.callFetchWS();
});
</script>
