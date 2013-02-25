sensus.util.globalTable = {
		
		tableBusca : function(oData) {
			var dataView;
			  var grid;
			  var data = [];
			  var datasyy = [];
			  var options = {
			    enableCellNavigation: true,
			    showHeaderRow: true,
			    headerRowHeight: 30,
				editable: true,
			    enableAddRow: true,
			    asyncEditorLoading: false,
			    autoEdit: false,
				multiColumnSort: true,
			    explicitInitialization: true
			  };
			  var columns = [];
			  var columnFilters = {};

			  function create (data,checked) {
			   var columnss = [];
			   console.log(data.length);
			   if(checked){
			       var checkboxSelector = new Slick.CheckboxSelectColumn({
			      cssClass: "slick-cell-checkboxsel"
			    });

			    columnss.push(checkboxSelector.getColumnDefinition());
			   }
					for(i=0;i< data.length;i++){
						columnss.push({
						  id: i, 
						  name: data[i].name, 
						  field: data[i].field, 
						  width: data[i].width, 
						  cssClass: data[i].cssClass,
						  behavior: data[i].behevior,
						  sortable: data[i].sortable, 
						  editor: data[i].editor
						});
					}
					console.log(columnss);
				 return columnss;
			    }
			  var dataCreateColuns = [
			    {id: "title", name: "Title", field: "title", width: 120, cssClass: "cell-title",behavior: "selectAndMove",sortable: true, editor: Slick.Editors.Text},
			    {id: "desc", name: "Description", field: "description", width: 100,sortable: true, editor: Slick.Editors.LongText},
			    {id: "duration", name: "Duration", field: "duration",sortable: true, editor: Slick.Editors.Text},
			    {id: "5%", name: "% Complete", field: "percentComplete", width: 80, resizable: false,sortable: true, formatter: Slick.Formatters.PercentCompleteBar, editor: Slick.Editors.PercentComplete},
			    {id: "start", name: "Start", field: "start", minWidth: 60,sortable: true, editor: Slick.Editors.Date},
			    {id: "finish", name: "Finish", field: "finish", minWidth: 60,sortable: true, editor: Slick.Editors.Date},
			    {id: "effort-driven", name: "Effort Driven", width: 80, minWidth: 20, maxWidth: 80,sortable: true, cssClass: "cell-effort-driven", field: "effortDriven", formatter: Slick.Formatters.Checkmark, editor: Slick.Editors.Checkbox}
			  ]; 
			  columns = create(dataCreateColuns);


			  function filter(item) {
			    for (var columnId in columnFilters) {
			      if (columnId !== undefined && columnFilters[columnId] !== "") {
			        var c = grid.getColumns()[grid.getColumnIndex(columnId)];
			        if (item[c.field] != columnFilters[columnId]) {
			          return false;
			        }
			      }
			    }
			    return true;
			  }

			  $(function () {

			 for (var i = 0; i < 2; i++) {
			      var d = (data[i] = {});
				  d["id"] = i;
			      d["title"] = "Task " + i;
			      d["description"] = "This is a sample task description.\n  It can be multiline";
			      d["duration"] = "5 days";
			      d["percentComplete"] = Math.round(Math.random() * 100);
			      d["start"] = "01/01/2009";
			      d["finish"] = "01/05/2009";
			      d["effortDriven"] = (i % 5 == 0);
			    }

			    dataView = new Slick.Data.DataView();
				 console.log(dataView);
			    grid = new Slick.Grid("#myGrid", dataView, columns, options);
				  grid.onSort.subscribe(function (e, args) {
			      var cols = args.sortCols;

			      data.sort(function (dataRow1, dataRow2) {
			        for (var i = 0, l = cols.length; i < l; i++) {
			          var field = cols[i].sortCol.field;
			          var sign = cols[i].sortAsc ? 1 : -1;
			          var value1 = dataRow1[field], value2 = dataRow2[field];
			          var result = (value1 == value2 ? 0 : (value1 > value2 ? 1 : -1)) * sign;
			          if (result != 0) {
			            return result;
			          }
			        }
			        return 0;
			      });
			      grid.invalidate();
			      grid.render();
			    });

			    grid.onAddNewRow.subscribe(function (e, args) {
			      var item = args.item;
			      grid.invalidateRow(data.length);
			      data.push(item);
			      grid.updateRowCount();
			      grid.render();
			    });

			    dataView.onRowCountChanged.subscribe(function (e, args) {
			      grid.updateRowCount();
			      grid.render();
			    });

			    dataView.onRowsChanged.subscribe(function (e, args) {
			      grid.invalidateRows(args.rows);
			      grid.render();
			    });

			    


			    $(grid.getHeaderRow()).delegate(":input", "change keyup", function (e) {
			      var columnId = $(this).data("columnId");
			      if (columnId != null) {
			        columnFilters[columnId] = $.trim($(this).val());
			        dataView.refresh();
			      }
			    });

			    grid.onHeaderRowCellRendered.subscribe(function(e, args) {
			        $(args.node).empty();
			        $("<input type='text'>")
			           .data("columnId", args.column.id)
			           .val(columnFilters[args.column.id])
			           .appendTo(args.node);
			    });

			    grid.init();

			    dataView.beginUpdate();
			    dataView.setItems(data);
				
			    dataView.setFilter(filter);
			    dataView.endUpdate();
				

				
			  });
			
		},
		
		tableSort : function(oData) {
			var grid,
			  data = [],
			  columns = [
				{ id: "title", name: "Title", field: "title", width: 240, sortable: true },
				{ id: "c1", name: "Sort 1", field: "c1", width: 240, sortable: true },
				{ id: "c2", name: "Sort 2", field: "c2", width: 240, sortable: true },
				{ id: "c3", name: "Sort 3", field: "c3", width: 240, sortable: true }
			  ],
			  options = {
				enableCellNavigation: false,
				enableColumnReorder: false
			  },
			  numberOfItems = 25000, items = [], indices, isAsc = true, currentSortCol = { id: "title" }, i;

		  // Copies and shuffles the specified array and returns a new shuffled array.
		  function randomize(items) {
			var randomItems = $.extend(true, null, items), randomIndex, temp, index;
			for (index = items.length; index-- > 0;) {
			  randomIndex = Math.round(Math.random() * items.length - 1);
			  if (randomIndex > -1) {
				temp = randomItems[randomIndex];
				randomItems[randomIndex] = randomItems[index];
				randomItems[index] = temp;
			  }
			}
			return randomItems;
		  }

		  /// Build the items and indices.
		  for (i = numberOfItems; i-- > 0;) {
			items[i] = i;
			data[i] = {
			  title: "Task ".concat(i + 1)
			};
		  }
		  indices = { title: items, c1: randomize(items), c2: randomize(items), c3: randomize(items) };

		  // Assign values to the data.
		  for (i = numberOfItems; i-- > 0;) {
			data[indices.c1[i]].c1 = "Value ".concat(i + 1);
			data[indices.c2[i]].c2 = "Value ".concat(i + 1);
			data[indices.c3[i]].c3 = "Value ".concat(i + 1);
		  }

		  // Define function used to get the data and sort it.
		  function getItem(index) {
			return isAsc ? data[indices[currentSortCol.id][index]] : data[indices[currentSortCol.id][(data.length - 1) - index]];
		  }
		  function getLength() {
			return data.length;
		  }

		  grid = new Slick.Grid("#myGrid", {getLength: getLength, getItem: getItem}, columns, options);
		  grid.onSort.subscribe(function (e, args) {
			currentSortCol = args.sortCol;
			isAsc = args.sortAsc;
			grid.invalidateAllRows();
			grid.render();
		  });


		},
		
		tableCustomizeColuns : function(oData) {
			var rows = new Array(),
			cell=[];
			console.log(oData);
			cell = oData[0].filiais[0];
			console.log(cell);
		//	for (x=0;x<= 2;x++){
				rows.push({cell: cell });
		//	}


			var data = {
			        total: 100,    
			        page:5,
			        rows: rows
			}
			var searchitems=[];
			var colModel=[];

			for(i=0 ;i< oData[0].table[0].atributos.length;i++ ){
				colModel.push({display: oData[0].table[0].atributos[i].name, name : oData[0].table[0].atributos[i].name, width : 150, sortable : true, align: 'center'});		
			}

			for(i = 0;i< colModel.length;i++){
				searchitems.push({display: colModel[i].display, name : colModel[i].name});
			}
	     	console.log(oData[0].table[0].width);
			console.log(oData[0].table[0].height);
			$('#flex1').flexigrid({
			     colModel : colModel,

			        dataType: 'json',
					//url:data,
					addData :data,
					searchitems : searchitems,		
//					sortname: "Name",
//					sortorder: "asc",
//					usepager: true,
					title: oData[0].table[0].nome,
					useRp: true,
//					rp: 15,
					showTableToggleBtn: true,
					width: oData[0].table[0].width,
					height: oData[0].table[0].height
			        });
			    

			$('#flex1').flexAddData(data);//.flexReload();	
			
			
		},
		
		tableEditable : function(oData) {
			 function requiredFieldValidator(value) {
				    if (value == null || value == undefined || !value.length) {
				      return {valid: false, msg: "This is a required field"};
				    } else {
				      return {valid: true, msg: null};
				    }
				  }

				  var grid;
				  var data = [];
				  var columns = [
				    {id: "title", name: "Title", field: "title", width: 120, cssClass: "cell-title", editor: Slick.Editors.Text, validator: requiredFieldValidator},
				    {id: "desc", name: "Description", field: "description", width: 100, editor: Slick.Editors.Text},
				    {id: "duration", name: "Duration", field: "duration", editor: Slick.Editors.Text},
				    {id: "percent", name: "% Complete", field: "percentComplete", width: 80, resizable: false, formatter: Slick.Formatters.PercentCompleteBar, editor: Slick.Editors.PercentComplete},
				    {id: "start", name: "Start", field: "start", minWidth: 60, editor: Slick.Editors.Date},
				    {id: "finish", name: "Finish", field: "finish", minWidth: 60, editor: Slick.Editors.Date},
				    {id: "effort-driven", name: "Effort Driven", width: 80, minWidth: 20, maxWidth: 80, cssClass: "cell-effort-driven", field: "effortDriven", formatter: Slick.Formatters.Checkmark, editor: Slick.Editors.Checkbox}
				  ];
				  var options = {
				    editable: true,
				    enableAddRow: true,
				    enableCellNavigation: true,
				    asyncEditorLoading: false,
				    autoEdit: false
				  };


				  function openDetails() {
				    if (grid.getEditorLock().isActive() && !grid.getEditorLock().commitCurrentEdit()) {
				      return;
				    }

				    var $modal = $("<div class='item-details-form'></div>");

				    $modal = $("#itemDetailsTemplate")
				        .tmpl({
				          context: grid.getDataItem(grid.getActiveCell().row),
				          columns: columns
				        })
				        .appendTo("body");

				    $modal.keydown(function (e) {
				      if (e.which == $.ui.keyCode.ENTER) {
				        grid.getEditController().commitCurrentEdit();
				        e.stopPropagation();
				        e.preventDefault();
				      } else if (e.which == $.ui.keyCode.ESCAPE) {
				        grid.getEditController().cancelCurrentEdit();
				        e.stopPropagation();
				        e.preventDefault();
				      }
				    });

				    $modal.find("[data-action=save]").click(function () {
				      grid.getEditController().commitCurrentEdit();
				    });

				    $modal.find("[data-action=cancel]").click(function () {
				      grid.getEditController().cancelCurrentEdit();
				    });


				    var containers = $.map(columns, function (c) {
				      return $modal.find("[data-editorid=" + c.id + "]");
				    });

				    var compositeEditor = new Slick.CompositeEditor(
				        columns,
				        containers,
				        {
				          destroy: function () {
				            $modal.remove();
				          }
				        }
				    );

				    grid.editActiveCell(compositeEditor);
				  }

				  $(function () {
				    for (var i = 0; i < 500; i++) {
				      var d = (data[i] = {});

				      d["title"] = "Task " + i;
				      d["description"] = "This is a sample task description.\n  It can be multiline";
				      d["duration"] = "5 days";
				      d["percentComplete"] = Math.round(Math.random() * 100);
				      d["start"] = "01/01/2009";
				      d["finish"] = "01/05/2009";
				      d["effortDriven"] = (i % 5 == 0);
				    }

				    grid = new Slick.Grid("#myGrid", data, columns, options);

				    grid.onAddNewRow.subscribe(function (e, args) {
				      var item = args.item;
				      var column = args.column;
				      grid.invalidateRow(data.length);
				      data.push(item);
				      grid.updateRowCount();
				      grid.render();
				    });


				    grid.onValidationError.subscribe(function (e, args) {
				      // handle validation errors originating from the CompositeEditor
				      if (args.editor && (args.editor instanceof Slick.CompositeEditor)) {
				        var err;
				        var idx = args.validationResults.errors.length;
				        while (idx--) {
				          err = args.validationResults.errors[idx];
				          $(err.container).stop(true, true).effect("highlight", {color: "red"});
				        }
				      }
				    });

				    grid.setActiveCell(0, 0);
				  });
		},
		
		tableModel : function(oData) {
		var dataView;
		var grid;
		var data = [];
		var columns = [
		  {id: "sel", name: "#", field: "num", behavior: "select", cssClass: "cell-selection", width: 40, cannotTriggerInsert: true, resizable: false, selectable: false },
		  {id: "title", name: "Title", field: "title", width: 120, minWidth: 120, cssClass: "cell-title", editor: Slick.Editors.Text, validator: requiredFieldValidator, sortable: true},
		  {id: "duration", name: "Duration", field: "duration", editor: Slick.Editors.Text, sortable: true},
		  {id: "%", defaultSortAsc: false, name: "% Complete", field: "percentComplete", width: 80, resizable: false, formatter: Slick.Formatters.PercentCompleteBar, editor: Slick.Editors.PercentComplete, sortable: true},
		  {id: "start", name: "Start", field: "start", minWidth: 60, editor: Slick.Editors.Date, sortable: true},
		  {id: "finish", name: "Finish", field: "finish", minWidth: 60, editor: Slick.Editors.Date, sortable: true},
		  {id: "effort-driven", name: "Effort Driven", width: 80, minWidth: 20, maxWidth: 80, cssClass: "cell-effort-driven", field: "effortDriven", formatter: Slick.Formatters.Checkmark, editor: Slick.Editors.Checkbox, cannotTriggerInsert: true, sortable: true}
		];

		var options = {
		  editable: true,
		  enableAddRow: true,
		  enableCellNavigation: true,
		  asyncEditorLoading: true,
		  forceFitColumns: false,
		  topPanelHeight: 25
		};

		var sortcol = "title";
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
		  if (args.searchString != "" && item["title"].indexOf(args.searchString) == -1) {
		    return false;
		  }
		  if (args.searchString != "" && item["duration"].indexOf(args.searchString) == -1) {
		    return false;
		  }

		  return true;
		}

		function percentCompleteSort(a, b) {
		  return a["percentComplete"] - b["percentComplete"];
		}

		function comparer(a, b) {
		  var x = a[sortcol], y = b[sortcol];
		  return (x == y ? 0 : (x > y ? 1 : -1));
		}

	    $("#seach").click(function(e) {
			e.preventDefault();	
			alert('aqui');
			toggleFilterRow();

		});

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

		$(function () {
		  // prepare the data
		  for (var i = 0; i < 50000; i++) {
		    var d = (data[i] = {});

		    d["id"] = "id_" + i;
		    d["num"] = i;
		    d["title"] = "Task " + i;
		    d["duration"] = "5 days";
		    d["percentComplete"] = Math.round(Math.random() * 100);
		    d["start"] = "01/01/2009";
		    d["finish"] = "01/05/2009";
		    d["effortDriven"] = (i % 5 == 0);
		  }


		  dataView = new Slick.Data.DataView({ inlineFilters: true });
		  grid = new Slick.Grid("#myGrid", dataView, columns, options);
		  grid.setSelectionModel(new Slick.RowSelectionModel());

		  var pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
		  var columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);


		  // move the filter panel defined in a hidden div into grid top panel
		  $("#inlineFilterPanel")
		      .appendTo(grid.getTopPanel())
		      .show();

		  grid.onCellChange.subscribe(function (e, args) {
		    dataView.updateItem(args.item.id, args.item);
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
		})
		},

		toggleFilterRow : function() {
			  grid.setTopPanelVisibility(!grid.getOptions().showTopPanel);
			}
}