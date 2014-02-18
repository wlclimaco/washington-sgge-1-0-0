<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var dataView;
var grid;
var rowChg;
var aRowChg = new Array();
var pgrid;
var data = [];
var rowValue = 0;
var onProcDataLoading = new EventHelper();
var columns = [
  {id: "cdexerc", name: "cdexerc", field: "cdexerc", behavior: "select", cssClass: "cell-selection", width: 40, cannotTriggerInsert: true, resizable: false, selectable: false },
  {id: "nmexerc", name: "nmexerc", field: "nmexerc", width: 120, minWidth: 120, cssClass: "cell-title", editor: Slick.Editors.Text, validator: requiredFieldValidator, sortable: true},
  {id: "dsexerc", name: "dsexerc", field: "dsexerc", editor: Slick.Editors.Text, sortable: true},
  {id: "grupomuscular", name: "grupomuscular", field: "grupomuscular", editor: Slick.Editors.Text, sortable: true},
  {id: "ftexerc", name: "ftexerc", field: "ftexerc", editor: Slick.Editors.Text, sortable: true}
];

var options = {
  editable: true,
  enableAddRow: true,
  enableCellNavigation: true,
  asyncEditorLoading: true,
  forceFitColumns: false,
  topPanelHeight: 25
};

var sortcol = "cdexerc";
var sortdir = 1;
var percentCompleteThreshold = 0;
var searchString = "";

function requiredFieldValidator(value) {
  if (value == null || value == undefined || !value.length) {
    return {valid: false, msg: "This is a required field"};
  }
  else {
    return {valid: true, msg: null};
  }
}

function myFilter(item, args) {
  if (item["percentComplete"] < args.percentCompleteThreshold) {
    return false;
  }

  if (args.searchString != "" && item["cdgrmusc"].indexOf(args.searchString) == -1) {
    return false;
  }

  return true;
}

function comparer(a, b) {
  var x = a[sortcol], y = b[sortcol];
  return (x == y ? 0 : (x > y ? 1 : -1));
}

function toggleFilterRow() {
  grid.setTopPanelVisibility(!grid.getOptions().showTopPanel);
}


$(".grid-header .ui-icon")
        .addClass("ui-state-default ui-corner-all")
        .mouseover(function (e) {
          $(e.target).addClass("ui-state-hover")
        })
        .mouseout(function (e) {
          $(e.target).removeClass("ui-state-hover")
        });
function renderTable(){
	var oData = [];
	var fnCallback = function(response){

		for (var i = 0; i < response.exercicios.length; i++) {
			var d = (oData[i] = {});
			d["id"] = response.exercicios[i].cdgrmusc;
			d["cdexerc"] = response.exercicios[i].cdexerc;
			d["nmexerc"] = response.exercicios[i].nmexerc;
			d["dsexerc"] = response.exercicios[i].dsexerc;
			d["grupomuscular"] = response.exercicios[i].grupomuscular;
			d["ftexerc"] = response.exercicios[i].ftexerc;
		}


		//console.log(oData);
	};

  // prepare the data
  var d = (data[0] = {});
	  d["id"] = 0;
	  d["cdexerc"] = 0;
	  d["nmexerc"] = "";
	  d["dsexerc"] = "";
	  d["grupomuscular"] = "";
	  d["ftexerc"] = "";

 //sensus.pages.grupoMuscular2.callPagedFetchWS(null,null,fnCallback);
 sensus.pages.cSlider.callPagedFetchWS("api/exercicio/fetchall",{"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"exercicio":[{"createuser":"superuser","tenantid":1,"userid":1}]},fnCallback);

   for (var i = 0; i < oData.length; i++) {
     data.push(oData[i]);
   }

  dataView = new Slick.Data.DataView({ inlineFilters: true });
  grid = new Slick.Grid("#myGrid", dataView, columns, options);
  grid.setSelectionModel(new Slick.RowSelectionModel());

  var pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
  var columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);

console.log(grid);
  // move the filter panel defined in a hidden div into grid top panel
  $("#inlineFilterPanel")
      .appendTo(grid.getTopPanel())
      .show();

  grid.onCellChange.subscribe(function (e, args) {
    dataView.updateItem(args.item.id, args.item);

	rowValue = args.row;
  });

  grid.onAddNewRow.subscribe(function (e, args) {
    var item = {"num": data.length, "id": "new_" + (Math.round(Math.random() * 10000)), "title": "New task", "duration": "1 day", "percentComplete": 0, "start": "01/01/2009", "finish": "01/01/2009", "effortDriven": false};
    $.extend(item, args.item);
    dataView.addItem(item);
  });

  grid.onKeyDown.subscribe(function (e) {
    // select all rows on ctrl-a
    if (e.which != 65 || !e.ctrlKey) {
      return false;
    }

    var rows = [];
    for (var i = 0; i < dataView.getLength(); i++) {
      rows.push(i);
    }

    grid.setSelectedRows(rows);
    e.preventDefault();
  });

  grid.onSort.subscribe(function (e, args) {
    sortdir = args.sortAsc ? 1 : -1;
    sortcol = args.sortCol.field;

    if ($.browser.msie && $.browser.version <= 8) {
      // using temporary Object.prototype.toString override
      // more limited and does lexicographic sort only by default, but can be much faster

      var percentCompleteValueFn = function () {
        var val = this["percentComplete"];
        if (val < 10) {
          return "00" + val;
        } else if (val < 100) {
          return "0" + val;
        } else {
          return val;
        }
      };

      // use numeric sort of % and lexicographic for everything else
      dataView.fastSort((sortcol == "percentComplete") ? percentCompleteValueFn : sortcol, args.sortAsc);
    } else {
      // using native sort with comparer
      // preferred method but can be very slow in IE with huge datasets
      dataView.sort(comparer, args.sortAsc);
    }
  });

  // wire up model events to drive the grid
  dataView.onRowCountChanged.subscribe(function (e, args) {
    grid.updateRowCount();
    grid.render();
  });

  dataView.onRowsChanged.subscribe(function (e, args) {
    grid.invalidateRows(args.rows);
    grid.render();
  });

  dataView.onPagingInfoChanged.subscribe(function (e, pagingInfo) {
    var isLastPage = pagingInfo.pageNum == pagingInfo.totalPages - 1;
    var enableAddRow = isLastPage || pagingInfo.pageSize == 0;
    var options = grid.getOptions();

    if (options.enableAddRow != enableAddRow) {
      grid.setOptions({enableAddRow: enableAddRow});
    }
  });


  var h_runfilters = null;

  // wire up the slider to apply the filter to the model
  $("#pcSlider,#pcSlider2").slider({
    "range": "min",
    "slide": function (event, ui) {
      Slick.GlobalEditorLock.cancelCurrentEdit();

      if (percentCompleteThreshold != ui.value) {
        window.clearTimeout(h_runfilters);
        h_runfilters = window.setTimeout(updateFilter, 10);
        percentCompleteThreshold = ui.value;
      }
    }
  });


  // wire up the search textbox to apply the filter to the model
  $("#txtSearch,#txtSearch2").keyup(function (e) {
    Slick.GlobalEditorLock.cancelCurrentEdit();

    // clear on Esc
    if (e.which == 27) {
      this.value = "";
    }

    searchString = this.value;
    updateFilter();
  });

  function updateFilter() {
    dataView.setFilterArgs({
      percentCompleteThreshold: percentCompleteThreshold,
      searchString: searchString
    });
    dataView.refresh();
  }

  $("#btnSelectRows").click(function () {
    if (!Slick.GlobalEditorLock.commitCurrentEdit()) {
      return;
    }

    var rows = [];
    for (var i = 0; i < 10 && i < dataView.getLength(); i++) {
      rows.push(i);
    }

    grid.setSelectedRows(rows);
  });


  // initialize the model after all the events have been hooked up
  dataView.beginUpdate();
  dataView.setItems(data);
  dataView.setFilterArgs({
    percentCompleteThreshold: percentCompleteThreshold,
    searchString: searchString
  });
  dataView.setFilter(myFilter);
  dataView.endUpdate();

  // if you don't want the items that are not visible (due to being filtered out
  // or being on a different page) to stay selected, pass 'false' to the second arg
  dataView.syncGridSelection(grid, true);

  $("#gridContainer").resizable();

  function validateFields(rowValue)
{
	if (wd.core.isEmpty(data[rowValue].pcode))
	{
		/* grid.gotoCell(rowValue,2,true);
		$(grid.getActiveCellNode()).addClass("invalid");
		$(grid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000); */
	}
	else if (wd.core.isEmpty(data[rowValue].pdesc))
	{
		/* grid.gotoCell(rowValue,1,true);
		$(grid.getActiveCellNode()).addClass("invalid");
		$(grid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000); */
	}
	else
	{
		return true;
	}
	return false;
};


  $('#myGrid').keyup(function(e)
  {

	if (e.keyCode == 13)
	{
console.log(rowValue);
		if (rowValue >= 1 )
		{
			sensus.pages.cSlider.callUpdateWS("api/grupoMuscular/update",new GrupomuscularRequest(data[rowValue].cdgrmusc,data[rowValue].musculo,data[rowValue].dsgrmusc));
		}
		else
		{
/* 			if (validateFields(0))
			{ */

					fnCallBack1 = function(datas){


						if (datas.operationSuccess){
							$.sc.startProgressBar(null,true);
						    var  dd = dataView.getItems().length + 1;
							var Object = {id: dd,cdgrmusc:data[0].cdgrmusc, musculo:data[0].musculo,dsgrmusc:data[0].dsgrmusc};
							dataView.insertItem(1, Object);
							grid.render();
							$.sc.stopGlobalProgressBar();
						}else{
							$.sc.stopGlobalProgressBar();
						}
					};


				sensus.pages.cSlider.callInsertWS("api/grupoMuscular/insert","api/grupoMuscular/fetchall",new GrupomuscularRequest(data[0].cdgrmusc,data[0].musculo,data[0].dsgrmusc),{"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"grupomusculars":[{"createuser":"superuser","tenantid":1,"userid":1}]},fnCallBack1);


		}
		onProcDataLoading.notify({});
	}
});


  $.sc.stopGlobalProgressBar();

}

$(function () {
renderTable();
})
</script>
