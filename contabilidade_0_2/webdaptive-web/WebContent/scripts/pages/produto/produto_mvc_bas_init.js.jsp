<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var ploaderPro = new Slick.Data.RemoteModel();
$(document).ready(function ()
{
	//initializes statusbar
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });
	$( "#menu" ).menu();
	//sets up initial grid ...no data yet...but binds to the object that will fill it
	pgrid1 = new Slick.Grid($("#prodGrid"), ploaderPro.dataProd, columnss, options);
	pgrid1.setSelectionModel(new Slick.RowSelectionModel({selectActiveRow: false}));
    pgrid1.registerPlugin(checkboxSelector);
	pgrid1.onClick.subscribe(function (e, args) {
        if ($(e.target).hasClass("btn")) {
			var classs = $(e.target).attr('class');
			var a = classs.split(' ');
            openDialog(a[1]);
        }
        e.stopImmediatePropagation();
    });

	var openDialog = function(row){
		var dom = "<div class='id'>" + row.toString() + "</div>";
		$actionTagDialog = $("#action-produto-dialog").load('../produto/cadastroProdutosByRequestBAS').dialog({
			autoOpen: false,
			title: 'Incluir Produto',
			width: 800,
			minheight: 500,
			height: 800,
			modal: true,
			buttons: {
				'Gravar': function() {
					ploaderPre.callInsertWSPrec(row);
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog buttons-left',
			resizable: false
		});
		$.address.value('?prodId='+row+'&type=edit');
		$actionTagDialog.empty();
		$actionTagDialog.dialog('open');
	};

	$("#qatmvctabsProd").on("click", "#insertButon", function(e) {

		e.preventDefault();
		openDialog(0);
	});

	$("#qatmvctabsProd").on("click", ".csv", function(e) {

		e.preventDefault();
		ploaderPro.exportCSVProd();

	});

    var columnpicker = new Slick.Controls.ColumnPicker(columnss, pgrid1, options);
	gridPager = new Slick.Controls.Pager(ploaderPro, $("#pager"));
	gridPager.init();
	setTimeout('pgrid1.init()', 250);

	//this events fires to blockui while the data is retrieved
	ploaderPro.onProDataLoading.subscribe(function()
	{
		wd.core.blockUIMessage('div.ui-layout-center',true,true,wdloading.title,wdloading.msg);
	});

	//this events fires to unblockui when the data is retruned and fills the grid
	ploaderPro.onProDataLoaded.subscribe(function()
	{
		aRowChg.length=0;
		pgrid1.invalidateAllRows();
		pgrid1.updateRowCount();
		pgrid1.setSelectedRows([]);
		pgrid1.resetActiveCell();
		pgrid1.render();
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		pgrid1.gotoCell(0,3,true);
		</sec:authorize>
		//load grid pager control
		gridPager.setPagingOptions({totalRows: pagingData.totalRowsAvailable, pageSize: pagingData.pageSize, pageNum: pagingData.startPage});
		gridPager.updatePager(gridPager.getPagingInfo());
		$.address.value('wdtree?id=' + id + '&tab=' + $('#'+ id).tabs( "option", "active" ));
		wd.core.unblockUIMessage('div.ui-layout-center');
	});
	<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
	//checks & stores any rows numbers that change
    pgrid1.onCellChange.subscribe(function (e, args)
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
//	ploaderPro.fnFillFilter();

	// load the Grid first time
	ploaderPro.callPagedFetchWS(20,0);
});
</script>
