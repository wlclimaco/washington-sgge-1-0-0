
var grid006;
var data006 = [];
var columns006 = [
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
  data006 = [
    { name: "Make a list", quant:"10"},
    { name: "Check it twice",quant: "10"},
    { name: "Find out who's naughty",quant:"10"},
    { name: "Find out who's nice",quant: "10"}
  ];

  grid006 = new Slick.Grid("#myGrid6", data006, columns006, options);

  grid006.setSelectionModel(new Slick.RowSelectionModel());

  var moveRowsPlugin = new Slick.RowMoveManager({
    cancelEditOnDrag: true
  });

  moveRowsPlugin.onBeforeMoveRows.subscribe(function (e, data006) {
    for (var i = 0; i < data006.rows.length; i++) {
      // no point in moving before or after itself
      if (data006.rows[i] == data006.insertBefore || data006.rows[i] == data006.insertBefore - 1) {
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
    left = data006.slice(0, insertBefore);
    right = data006.slice(insertBefore, data006.length);

    rows.sort(function(a,b) { return a-b; });

    for (var i = 0; i < rows.length; i++) {
      extractedRows.push(data006[rows[i]]);
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

    data006 = left.concat(extractedRows.concat(right));

    var selectedRows = [];
    for (var i = 0; i < rows.length; i++)
      selectedRows.push(left.length + i);

    grid006.resetActiveCell();
    grid006.setData(data006);
    grid006.setSelectedRows(selectedRows);
    grid006.render();
  });

  grid006.registerPlugin(moveRowsPlugin);

  grid006.onDragInit.subscribe(function (e, dd) {
    // prevent the grid006 from cancelling drag'n'drop by default
    e.stopImmediatePropagation();
  });

  grid006.onDragStart.subscribe(function (e, dd) {
    var cell = grid006.getCellFromEvent(e);
    if (!cell) {
      return;
    }

    dd.row = cell.row;
    if (!data006[dd.row]) {
      return;
    }

    if (Slick.GlobalEditorLock.isActive()) {
      return;
    }

    e.stopImmediatePropagation();
    dd.mode = "recycle";

    var selectedRows = grid006.getSelectedRows();

    if (!selectedRows.length || $.inArray(dd.row, selectedRows) == -1) {
      selectedRows = [dd.row];
      grid006.setSelectedRows(selectedRows);
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

  grid006.onDrag.subscribe(function (e, dd) {
    if (dd.mode != "recycle") {
      return;
    }
    dd.helper.css({top: e.pageY + 5, left: e.pageX + 5});
  });

  grid006.onDragEnd.subscribe(function (e, dd) {
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
          data006.splice(rowsToDelete[i], 1);
        }
        grid006.invalidate();
        grid006.setSelectedRows([]);
      });


  grid006.onAddNewRow.subscribe(function (e, args) {
    var item = {name: "New task", complete: false};
    $.extend(item, args.item);
    data006.push(item);
    grid006.invalidateRows([data006.length - 1]);
    grid006.updateRowCount();
    grid006.render();
  });
})
$(document).ready(function() {

})