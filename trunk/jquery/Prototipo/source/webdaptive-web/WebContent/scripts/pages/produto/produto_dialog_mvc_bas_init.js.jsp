<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var ploader = new Slick.Data.RemoteModel();
$(document).ready(function ()
{
var  arrayList=[];
	//initializes statusbar
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });
	$( "#menu" ).menu();
	//sets up initial grid ...no data yet...but binds to the object that will fill it
	pgrid = new Slick.Grid($("#prodGridDialog"), ploader.data, columns, options);
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
	 var rowIndex;

        pgrid.onCellChange.subscribe(function (e, args) {
            console.log('onCellChange');
            rowIndex = args.row;
        });

        if (pgrid.getActiveCell()) {

        }
        //onSelectedRowsChanged event, if row number was changed call some function.

        pgrid.onSelectedRowsChanged.subscribe(function (e, args) {
            if (pgrid.getSelectedRows([0])[0] !== rowIndex) {
				var count = args.rows.length;
				arrayList = [];
				for(var i = 0;i<count;i++){
					arrayList.push(args.grid.getDataItem(args.rows[i]))
				}
				console.log(arrayList);
            }
        });

	var openDialog = function(row){
		var dom = "<div class='id'>" + row.toString() + "</div>";
		$('.ui-dialog').empty();
		$(dom).load('../produto/cadastroProdutosByRequestBAS?id=1').dialog({height: 800,
            width: 800,
            modal: true});
			$.address.value('?prodId='+row+'&type=edit');
	};

	$("#qatmvctabsProd").on("click", "#insertButon", function(e) {

		e.preventDefault();
		openDialog(0);
	});

	$(".export-type").on("click", ".csv", function(e) {

		e.preventDefault();
        if(data == '')
            return;
		ploader.exportCSVProd();
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
	$(".ui-menu-item").on("click", ".items", function(e) {

		e.preventDefault();
		var acao = $(this).attr('id');
		$actionTagDialog = $("#action-produto-dialog1").load('../supermercado/fetchSupermercadosByRequestBASUtil').dialog({
			autoOpen: false,
			title: 'Action - Add Tag to SmartPoint',
			width: 500,
			minheight: 300,
			height: 300,
			modal: true,
			buttons: {
				'Adicionar': function() {
					qat.model.supermercado.pages.gravar(arrayList,acao);
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			dialogClass: 'action-dialog buttons-left',
			resizable: false
		});
		$actionTagDialog.dialog('open');
	});
	$("#menu").on("click", "#remSup", function(e) {

		e.preventDefault();
		rest_post_call('qat-webdaptive/produto/api/produtoCSV', {}, null, null);
	});

	// load the Grid first time
	//ploader.callPagedFetchWSProd(20,0);
});
</script>
