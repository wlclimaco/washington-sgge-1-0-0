<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var ploader = new Slick.Data.RemoteModel();
$(document).ready(function ()
{
	//initializes statusbar
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });

	//sets up initial grid ...no data yet...but binds to the object that will fill it
	pgrid = new Slick.Grid($("#prodGrid"), ploader.data, columns, options);
	pgrid.setSelectionModel(new Slick.RowSelectionModel({selectActiveRow: false}));
    pgrid.registerPlugin(checkboxSelector);
	pgrid.onClick.subscribe(function (e, args) {
        if ($(e.target).hasClass("btn")) {
			var classs = $(e.target).attr('class');
			var a = classs.split(' ');
            openDialog(a[1]);
        }
        e.stopImmediatePropagation();
    });

	var openDialog = function(row){
		var dom = "<div class='id'>" + row.toString() + "</div>";

		$(dom).load('../produto/cadastroProdutosByRequestBAS?id=1').dialog({height: 800,
            width: 800,
            modal: true});
			$.address.value('?prodId='+row+'&type=edit');
	};

	insertButon
	$("#qatmvctabsProd").on("click", "#insertButon", function(e) {

		e.preventDefault();
		openDialog(0);
	});

    var columnpicker = new Slick.Controls.ColumnPicker(columns, pgrid, options);
	gridPager = new Slick.Controls.Pager(ploader, $("#pager"));
	gridPager.init();
	setTimeout('pgrid.init()', 250);

	//this events fires to blockui while the data is retrieved
	ploader.onProcDataLoading.subscribe(function()
	{
		wd.core.blockUIMessage('div.ui-layout-center',true,true,wdloading.title,wdloading.msg);
	});

	//this events fires to unblockui when the data is retruned and fills the grid
	ploader.onProcDataLoaded.subscribe(function()
	{
		aRowChg.length=0;
		pgrid.invalidateAllRows();
		pgrid.updateRowCount();
		pgrid.setSelectedRows([]);
		pgrid.resetActiveCell();
		pgrid.render();
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		pgrid.gotoCell(0,3,true);
		</sec:authorize>
		//load grid pager control
		gridPager.setPagingOptions({totalRows: pagingData.totalRowsAvailable, pageSize: pagingData.pageSize, pageNum: pagingData.startPage});
		gridPager.updatePager(gridPager.getPagingInfo());
		$.address.value('wdtree?id=' + id + '&tab=' + $('#'+ id).tabs( "option", "active" ));
		wd.core.unblockUIMessage('div.ui-layout-center');
	});
	<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
	//checks & stores any rows numbers that change
    pgrid.onCellChange.subscribe(function (e, args)
	{
		rowChg = args.row;
		if (args.row != 0)
		{
			var arrLength = aRowChg.length;
			if (!wd.core.containsValue(aRowChg, args.row))
			{
				aRowChg[arrLength] = [args.row];
			}
		}
    });
	</sec:authorize>

	// load the Grid first time
	ploader.callPagedFetchWS(20,0);
});
</script>
