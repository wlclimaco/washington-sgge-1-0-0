<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var dataView;
var grid;
var data = [];
var columns = [
  {id: "cdexerc", name: "#", field: "cdexerc", cssClass: "cell-selection", width: 40, resizable: false, selectable: false, focusable: false },
  {id: "nmexerc", name: "nmexerc", field: "nmexerc", width: 300, minWidth: 350, cssClass: "cell-title", sortable: true, editor: Slick.Editors.Text},
  {id: "grupomuscular", name: "grupomuscular", field: "grupomuscular",width: 200, minWidth: 60, sortable: true},
  {id: "createdate", name: "createdate", field: "createdate", minWidth: 160,width: 160, sortable: true},
  {id: "tenantid", name: "tenantid", field: "tenantid", minWidth: 60, sortable: true},
  {id: "userid", name: "userid", field: "userid", minWidth: 60, sortable: true},
  {id: "ftexerc", name: "ftexerc", field: "ftexerc", minWidth: 60, sortable: true},
  {id: "acao", name: "acao", field: "",width:300, minWidth: 300, sortable: true,formatter:Slick.Formatters.Buttons}

];

var options = {
  enableCellNavigation: true,
  editable: true
};

var sortcol = "title";
var sortdir = 1;
var percentCompleteThreshold = 0;
var prevPercentCompleteThreshold = 0;

function percentCompleteSort(a, b) {
  return a["percentComplete"] - b["percentComplete"];
}

function comparer(a, b) {
  var x = a[sortcol], y = b[sortcol];
  return (x == y ? 0 : (x > y ? 1 : -1));
}

function groupByDuration() {
  dataView.setGrouping({
    getter: "grupomuscular",
    formatter: function (g) {
      return "grupomuscular:  " + g.value + "  <span style='color:green'>(" + g.count + " items)</span>";
    },
    aggregateCollapsed: false,
    lazyTotalsCalculation: true
  });
}

function loadData(count) {
  var someDates = ["01/01/2009", "02/02/2009", "03/03/2009"];
  data = [];
  // prepare the data
  /* for (var i = 0; i < count; i++) {
    var d = (data[i] = {});

    d["id"] = "id_" + i;
    d["cdexerc"] = i;
    d["nmexerc"] = "Task " + i;
    d["grupomuscular"] = Math.round(Math.random() * 30);
    d["createdate"] = someDates[ Math.floor((Math.random()*2)) ];
    d["tenantid"] = 1;
    d["userid"] = "superuser"
    d["ftexerc"] = "Foto " + i;
  } */

  //+++++++++++++++++++++++++++++++++

	var fnCallback = function(response){

		for (var i = 0; i < response.exercicios.length; i++) {
			var d = (data[i] = {});
			d["id"] = "id_" + i;
			d["cdexerc"] = response.exercicios[i].cdexerc;
			d["nmexerc"] = response.exercicios[i].nmexerc;
			d["grupomuscular"] = response.exercicios[i].grupomuscular.musculo;
			d["createdate"] = response.exercicios[i].createdate;
			d["tenantid"] = response.exercicios[i].tenantid;
			d["userid"] = response.exercicios[i].createuser;
			d["ftexerc"] = response.exercicios[i].ftexerc;
		}
	};


 //sensus.pages.grupoMuscular2.callPagedFetchWS(null,null,fnCallback);
 sensus.pages.cSlider.callPagedFetchWS("api/exercicio/fetchall",{"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"exercicios":[]},fnCallback);

 /*   for (var i = 0; i < oData.length; i++) {
     data.push(oData[i]);
   } */


  //+++++++++++++++++++++++++++++++++



  /*private Integer cdexerc;

	private String nmexerc;

	private String dsexerc;

	private Date createdate;

	private String createuser;

	private Integer tenantid;

	private Integer userid;

	private List<Foto> ftexerc;

	private List<Grupomuscular> grupomuscular;


  */
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
  //dataView.setFilter(myFilter);
  dataView.setFilterArgs({
    percentComplete: percentCompleteThreshold
  });
  loadData(50);
  groupByDuration();
  dataView.endUpdate();

  $("#gridContainer").resizable();
})
</script>
