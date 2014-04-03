
var dataView;
var grid;
var data = [];
var columns = [
  {id: "#"   ,name: "", width: 40,behavior: "selectAndMove",selectable: false,resizable: false,cssClass: "cell-reorder dnd"},
  {id: "sel", name: "#", field: "num", cssClass: "cell-selection", width: 40, resizable: false, selectable: false, focusable: false },
  {id: "title", name: "Title", field: "title", width: 70, minWidth: 50, cssClass: "cell-title", sortable: true, editor: Slick.Editors.Text},
  {id: "duration", name: "Duration", field: "duration", width: 70, sortable: true, groupTotalsFormatter: sumTotalsFormatter},
  {id: "%", name: "% Complete", field: "percentComplete", width: 80, formatter: Slick.Formatters.PercentCompleteBar, sortable: true, groupTotalsFormatter: avgTotalsFormatter},
  {id: "start", name: "Start", field: "start", minWidth: 60, sortable: true},
  {id: "finish", name: "Finish", field: "finish", minWidth: 60, sortable: true},
  {id: "cost", name: "Cost", field: "cost", width: 90, sortable: true, groupTotalsFormatter: sumTotalsFormatter},
  {id: "effort-driven", name: "Effort Driven", width: 80, minWidth: 20, maxWidth: 80, cssClass: "cell-effort-driven", field: "effortDriven", formatter: Slick.Formatters.Checkmark, sortable: true}
];

var options = {
  editable: true,
  enableAddRow: true,
  enableCellNavigation: true,
  forceFitColumns: true,
  autoEdit: false
};

var sortcol = "title";
var sortdir = 1;
var percentCompleteThreshold = 0;
var prevPercentCompleteThreshold = 0;



function loadData(count) {
  var someDates = ["01/01/2009", "02/02/2009", "03/03/2009"];
  data = [];
  // prepare the data
  for (var i = 0; i < count; i++) {
    var d = (data[i] = {});

    d["id"] = "id_" + i;
    d["num"] = i;
    d["title"] = "Task " + i;
    d["duration"] = Math.round(Math.random() * 30);
    d["percentComplete"] = Math.round(Math.random() * 100);
    d["start"] = someDates[ Math.floor((Math.random()*2)) ];
    d["finish"] = someDates[ Math.floor((Math.random()*2)) ];
    d["cost"] = Math.round(Math.random() * 10000) / 100;
    d["effortDriven"] = (i % 5 == 0);
  }
  dataView.setItems(data);
}


$(".grid-header .ui-icon")
    .addClass("ui-state-default ui-corner-all")
    .mouseover(function (e) {
      $(e.target).addClass("ui-state-hover")
    })
    .mouseout(function (e) {
      $(e.target).removeClass("ui-state-hover")
    });

$(function () {
  var groupItemMetadataProvider = new Slick.Data.GroupItemMetadataProvider();
  dataView = new Slick.Data.DataView({
    groupItemMetadataProvider: groupItemMetadataProvider,
    inlineFilters: true
  });
  grid = new Slick.Grid("#myGrid", dataView, columns, options);

  // register the group item metadata provider to add expand/collapse group handlers
  grid.registerPlugin(groupItemMetadataProvider);
  grid.setSelectionModel(new Slick.CellSelectionModel());

  var pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
  var columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);


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
    }
    else {
      // using native sort with comparer
      // preferred method but can be very slow in IE with huge datasets
      dataView.sort(comparer, args.sortAsc);
    }
  });
  
   var moveRowsPlugin = new Slick.RowMoveManager({
    cancelEditOnDrag: true
  });

  moveRowsPlugin.onBeforeMoveRows.subscribe(function (e, data) {
    for (var i = 0; i < data.rows.length; i++) {
      // no point in moving before or after itself
      if (data.rows[i] == data.insertBefore || data.rows[i] == data.insertBefore - 1) {
        e.stopPropagation();
        return false;
      }
    }
    return true;
  });

  moveRowsPlugin.onMoveRows.subscribe(function (e, args) {
    var extractedRows = [], left, right;
    var rows = args.rows;
    var insertBefore = args.insertBefore;
    left = data.slice(0, insertBefore);
    right = data.slice(insertBefore, data.length);

    rows.sort(function(a,b) { return a-b; });

    for (var i = 0; i < rows.length; i++) {
      extractedRows.push(data[rows[i]]);
    }

    rows.reverse();

    for (var i = 0; i < rows.length; i++) {
      var row = rows[i];
      if (row < insertBefore) {
        left.splice(row, 1);
      } else {
        right.splice(row - insertBefore, 1);
      }
    }

    data = left.concat(extractedRows.concat(right));

    var selectedRows = [];
    for (var i = 0; i < rows.length; i++)
      selectedRows.push(left.length + i);

    grid.resetActiveCell();
    grid.setData(data);
    grid.setSelectedRows(selectedRows);
    grid.render();
  });

  grid.registerPlugin(moveRowsPlugin);

  grid.onDragInit.subscribe(function (e, dd) {
    // prevent the grid from cancelling drag'n'drop by default
    e.stopImmediatePropagation();
  });
  
    grid.onDragStart.subscribe(function (e, dd) {
    var cell = grid.getCellFromEvent(e);
    if (!cell) {
      return;
    }

    dd.row = cell.row;
    if (!data[dd.row]) {
      return;
    }

    if (Slick.GlobalEditorLock.isActive()) {
      return;
    }

    e.stopImmediatePropagation();
    dd.mode = "recycle";

    var selectedRows = grid.getSelectedRows();

    if (!selectedRows.length || $.inArray(dd.row, selectedRows) == -1) {
      selectedRows = [dd.row];
      grid.setSelectedRows(selectedRows);
    }

    dd.rows = selectedRows;
    dd.count = selectedRows.length;

    var proxy = $("<span></span>")
        .css({
          position: "absolute",
          display: "inline-block",
          padding: "4px 10px",
          background: "#e0e0e0",
          border: "1px solid gray",
          "z-index": 99999,
          "-moz-border-radius": "8px",
          "-moz-box-shadow": "2px 2px 6px silver"
        })
        .text("Drag to Recycle Bin to delete " + dd.count + " selected row(s)")
        .appendTo("body");

    dd.helper = proxy;

    $(dd.available).css("background", "pink");

    return proxy;
  });

  grid.onDrag.subscribe(function (e, dd) {
    if (dd.mode != "recycle") {
      return;
    }
    dd.helper.css({top: e.pageY + 5, left: e.pageX + 5});
  });

  grid.onDragEnd.subscribe(function (e, dd) {
    if (dd.mode != "recycle") {
      return;
    }
    dd.helper.remove();
    $(dd.available).css("background", "beige");
  });

  $.drop({mode: "mouse"});
  $(".dropzone")
      .bind("dropstart", function (e, dd) {
        if (dd.mode != "recycle") {
          return;
        }
        $(this).css("background", "yellow");
      })
      .bind("dropend", function (e, dd) {
        if (dd.mode != "recycle") {
          return;
        }
        $(dd.available).css("background", "pink");
      })
      .bind("drop", function (e, dd) {
        if (dd.mode != "recycle") {
          return;
        }
		dataItem = grid.getDataItem(dd.row);
		console.log(dd.drop[0].id)
		console.log(dataItem);
		var item = {name: dataItem.title, quant: dataItem.num};
	//	$.extend(item, args.item);
		if (dd.drop[0].id == "myGrid2"){
			data001.push(item);
			grid001.updateCell();
			grid001 = new Slick.Grid("#myGrid2", data001, columns001, options);
			grid001.setSelectionModel(new Slick.RowSelectionModel());
			grid001.render();
			grid001.updateRowCount(data001.length);
			grid001.render();
		}
		if (dd.drop[0].id == "myGrid3"){
			data002.push(item);
			
			console.log(data002.length);
			console.log(grid002);
			grid002.updateCell();
			grid002 = new Slick.Grid("#myGrid3", data002, columns002, options);

			grid002.setSelectionModel(new Slick.RowSelectionModel());
			grid002.render();
			grid002.updateRowCount(data001.length);
			grid002.render();
		}
		if (dd.drop[0].id == "myGrid4"){
			data003.push(item);
			
			console.log(data003.length);
			console.log(grid003);
			grid003.updateCell();
			grid003 = new Slick.Grid("#myGrid4", data003, columns003, options);

			grid003.setSelectionModel(new Slick.RowSelectionModel());
			grid003.render();
			grid003.updateRowCount(data001.length);
			grid003.render();
		}
		if (dd.drop[0].id == "myGrid5"){
			data004.push(item);
			
			console.log(data004.length);
			console.log(grid004);
			grid004.updateCell();
			grid004 = new Slick.Grid("#myGrid5", data004, columns004, options);

			grid004.setSelectionModel(new Slick.RowSelectionModel());
			grid004.render();
			grid004.updateRowCount(data001.length);
			grid004.render();
		}
		if (dd.drop[0].id == "myGrid6"){
			data005.push(item);
			
			console.log(data005.length);
			console.log(grid005);
			grid005.updateCell();
			grid005 = new Slick.Grid("#myGrid6", data005, columns005, options);

			grid005.setSelectionModel(new Slick.RowSelectionModel());
			grid005.render();
			grid005.updateRowCount(data001.length);
			grid005.render();
		}
		if (dd.drop[0].id == "myGrid7"){
			data006.push(item);
			
			console.log(data006.length);
			console.log(grid006);
			grid006.updateCell();
			grid006 = new Slick.Grid("#myGrid7", data006, columns006, options);

			grid006.setSelectionModel(new Slick.RowSelectionModel());
			grid006.render();
			grid006.updateRowCount(data001.length);
			grid006.render();
		}
        var rowsToDelete = dd.rows.sort().reverse();
        for (var i = 0; i < rowsToDelete.length; i++) {
          data.splice(rowsToDelete[i], 1);
        }
        grid.invalidate();
        grid.setSelectedRows([]);
      });


  grid.onAddNewRow.subscribe(function (e, args) {
    var item = {name: "New task", complete: false};
    $.extend(item, args.item);
    data.push(item);
    grid.invalidateRows([data.length - 1]);
    grid.updateRowCount();
    grid.render();
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


  var h_runfilters = null;

  // wire up the slider to apply the filter to the model
  $("#pcSlider,#pcSlider2").slider({
    "range": "min",
    "slide": function (event, ui) {
      Slick.GlobalEditorLock.cancelCurrentEdit();

      if (percentCompleteThreshold != ui.value) {
        window.clearTimeout(h_runfilters);
        h_runfilters = window.setTimeout(filterAndUpdate, 10);
        percentCompleteThreshold = ui.value;
      }
    }
  });


  function filterAndUpdate() {
    var isNarrowing = percentCompleteThreshold > prevPercentCompleteThreshold;
    var isExpanding = percentCompleteThreshold < prevPercentCompleteThreshold;
    var renderedRange = grid.getRenderedRange();

    dataView.setFilterArgs({
      percentComplete: percentCompleteThreshold
    });
    dataView.setRefreshHints({
      ignoreDiffsBefore: renderedRange.top,
      ignoreDiffsAfter: renderedRange.bottom + 1,
      isFilterNarrowing: isNarrowing,
      isFilterExpanding: isExpanding
    });
    dataView.refresh();

    prevPercentCompleteThreshold = percentCompleteThreshold;
  }

  // initialize the model after all the events have been hooked up
  dataView.beginUpdate();
  dataView.setFilter(myFilter);
  dataView.setFilterArgs({
    percentComplete: percentCompleteThreshold
  });
  loadData(50);
  groupByDuration();
  dataView.endUpdate();

  $("#gridContainer").resizable();
})
