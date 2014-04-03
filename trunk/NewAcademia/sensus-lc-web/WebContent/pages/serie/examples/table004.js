
var grid003;
var data003 = [];
var columns003 = [
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
  data003 = [
    { name: "Make a list", quant:"10"},
    { name: "Check it twice",quant: "10"},
    { name: "Find out who's naughty",quant:"10"},
    { name: "Find out who's nice",quant: "10"}
  ];

  grid003 = new Slick.Grid("#myGrid4", data003, columns003, options);

  grid003.setSelectionModel(new Slick.RowSelectionModel());

  var moveRowsPlugin = new Slick.RowMoveManager({
    cancelEditOnDrag: true
  });

  moveRowsPlugin.onBeforeMoveRows.subscribe(function (e, data003) {
    for (var i = 0; i < data003.rows.length; i++) {
      // no point in moving before or after itself
      if (data003.rows[i] == data003.insertBefore || data003.rows[i] == data003.insertBefore - 1) {
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
    left = data003.slice(0, insertBefore);
    right = data003.slice(insertBefore, data003.length);

    rows.sort(function(a,b) { return a-b; });

    for (var i = 0; i < rows.length; i++) {
      extractedRows.push(data003[rows[i]]);
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

    data003 = left.concat(extractedRows.concat(right));

    var selectedRows = [];
    for (var i = 0; i < rows.length; i++)
      selectedRows.push(left.length + i);

    grid003.resetActiveCell();
    grid003.setData(data003);
    grid003.setSelectedRows(selectedRows);
    grid003.render();
  });

  grid003.registerPlugin(moveRowsPlugin);

  grid003.onDragInit.subscribe(function (e, dd) {
    // prevent the grid003 from cancelling drag'n'drop by default
    e.stopImmediatePropagation();
  });

  grid003.onDragStart.subscribe(function (e, dd) {
    var cell = grid003.getCellFromEvent(e);
    if (!cell) {
      return;
    }

    dd.row = cell.row;
    if (!data003[dd.row]) {
      return;
    }

    if (Slick.GlobalEditorLock.isActive()) {
      return;
    }

    e.stopImmediatePropagation();
    dd.mode = "recycle";

    var selectedRows = grid003.getSelectedRows();

    if (!selectedRows.length || $.inArray(dd.row, selectedRows) == -1) {
      selectedRows = [dd.row];
      grid003.setSelectedRows(selectedRows);
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

  grid003.onDrag.subscribe(function (e, dd) {
    if (dd.mode != "recycle") {
      return;
    }
    dd.helper.css({top: e.pageY + 5, left: e.pageX + 5});
  });

  grid003.onDragEnd.subscribe(function (e, dd) {
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
          data003.splice(rowsToDelete[i], 1);
        }
        grid003.invalidate();
        grid003.setSelectedRows([]);
      });


  grid003.onAddNewRow.subscribe(function (e, args) {
    var item = {name: "New task", complete: false};
    $.extend(item, args.item);
    data003.push(item);
    grid003.invalidateRows([data003.length - 1]);
    grid003.updateRowCount();
    grid003.render();
  });
})
$(document).ready(function() {

})