
var grid001;
var data001 = [];
var columns001 = [
  {id: "#", name: "",width: 40,behavior: "selectAndMove",selectable: false,resizable: false,cssClass: "cell-reorder dnd"},
  {id: "name",id: "name",name: "Name",field: "name",width: 500,cssClass: "cell-title",editor: Slick.Editors.Text,validator: requiredFieldValidator},
  {id: "quant",id: "quant",name: "quant",field: "quant",width: 500,cssClass: "cell-title",editor: Slick.Editors.Text,validator: requiredFieldValidator},

];

var options = {
  editable: true,
  enableAddRow: true,
  enableCellNavigation: true,
  forceFitColumns: true,
  autoEdit: true
};



$(function () {
  data001 = [

  ];

  grid001 = new Slick.Grid("#myGrid2", data001, columns001, options);

  grid001.setSelectionModel(new Slick.RowSelectionModel());

  var moveRowsPlugin = new Slick.RowMoveManager({
    cancelEditOnDrag: true
  });

  moveRowsPlugin.onBeforeMoveRows.subscribe(function (e, data001) {
    for (var i = 0; i < data001.rows.length; i++) {
      // no point in moving before or after itself
      if (data001.rows[i] == data001.insertBefore || data001.rows[i] == data001.insertBefore - 1) {
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
    left = data001.slice(0, insertBefore);
    right = data001.slice(insertBefore, data001.length);

    rows.sort(function(a,b) { return a-b; });

    for (var i = 0; i < rows.length; i++) {
      extractedRows.push(data001[rows[i]]);
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

    data001 = left.concat(extractedRows.concat(right));

    var selectedRows = [];
    for (var i = 0; i < rows.length; i++)
      selectedRows.push(left.length + i);

    grid001.resetActiveCell();
    grid001.setData(data001);
    grid001.setSelectedRows(selectedRows);
    grid001.render();
  });

  grid001.registerPlugin(moveRowsPlugin);

  grid001.onDragInit.subscribe(function (e, dd) {
    // prevent the grid001 from cancelling drag'n'drop by default
    e.stopImmediatePropagation();
  });

  grid001.onDragStart.subscribe(function (e, dd) {
    var cell = grid001.getCellFromEvent(e);
    if (!cell) {
      return;
    }

    dd.row = cell.row;
    if (!data001[dd.row]) {
      return;
    }

    if (Slick.GlobalEditorLock.isActive()) {
      return;
    }

    e.stopImmediatePropagation();
    dd.mode = "recycle";

    var selectedRows = grid001.getSelectedRows();

    if (!selectedRows.length || $.inArray(dd.row, selectedRows) == -1) {
      selectedRows = [dd.row];
      grid001.setSelectedRows(selectedRows);
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

  grid001.onDrag.subscribe(function (e, dd) {
    if (dd.mode != "recycle") {
      return;
    }
    dd.helper.css({top: e.pageY + 5, left: e.pageX + 5});
  });

  grid001.onDragEnd.subscribe(function (e, dd) {
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
        var rowsToDelete = dd.rows.sort().reverse();
        for (var i = 0; i < rowsToDelete.length; i++) {
          data001.splice(rowsToDelete[i], 1);
        }
        grid001.invalidate();
        grid001.setSelectedRows([]);
      });


  grid001.onAddNewRow.subscribe(function (e, args) {
    var item = {name: "New task", complete: false};
    $.extend(item, args.item);
    data001.push(item);
    grid001.invalidateRows([data001.length - 1]);
    grid001.updateRowCount();
    grid001.render();
  });
})
$(document).ready(function() {

})