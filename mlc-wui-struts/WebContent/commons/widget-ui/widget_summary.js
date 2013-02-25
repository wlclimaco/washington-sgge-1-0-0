// plugin definition

$summary = null;

$.fn.summary = function(sId, type,action) {
var bTable = false;
	fnTable = function(oData,type){	
	if(type == 1){	

		
	}
		if(type == 2){
			sensus.util.globalTable.tableEditable(oData);
		}
		
		if(type == 3){
			sensus.util.globalTable.tableCustomizeColuns(oData);
		}
		if(type == 4){
		var dataView;
			var grid;
			var data = [];
			var columns = [
			  {id: "sel", name: "#", field: "num", behavior: "select", cssClass: "cell-selection", width: 40, cannotTriggerInsert: true, resizable: false, selectable: false },
			  {id: "title", name: "Title", field: "title", width: 120, minWidth: 120, cssClass: "cell-title", editor: Slick.Editors.Text,  sortable: true},
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
		}
	},
	
	
	
	fnTypeInput = function(oData){	
	var sTableLines="";
		if(oData.requerid){
			sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(oData.positionY)+'px;top:'+oData.positionX+'px;width:250px;height:16px;""><strong>'+oData.label+'</strong></span>  <input type="text" id="id'+oData.name+'"style="position:absolute;left:'+oData.positionY+'px;top:'+(oData.positionX + 20)+'px;width:'+(oData.tamanho)+'px;height:16px;border-style:solid;border-color:#ff0000 #ff0000;" name="Editbox'+oData.name+'">';
		}else{
			sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(oData.positionY)+'px;top:'+oData.positionX+'px;width:250px;height:16px;""><strong>'+oData.label+'</strong></span>  <input type="text" id="id'+oData.name+'"style="position:absolute;left:'+oData.positionY+'px;top:'+(oData.positionX + 20)+'px;width:'+(oData.tamanho)+'px;height:16px;" name="Editbox'+oData.name+'">';
		}
	return sTableLines;
	},
	
	fnTypeRadio = function(oData){},
	
	fnTypeCheckbox = function(oData){
		var sTableLines="";
		sTableLines +='<input type="checkbox" id="Checkbox'+oData.id+'" name="" value="on" style="position:absolute;left:'+oData.positionY+'px;top:'+oData.positionX+'px;"><span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(oData.positionY+30)+'px;top:'+oData.positionX+'px;width:250px;height:16px;""><strong>'+oData.label+'</strong></span>';
	return sTableLines;
	},
	
	fnTypeTable = function(type){
	var sTableLines="";
	if(type == 1){
		sTableLines +='<div style="position:absolute;left:0px;top:210px;width:770px;height:140px;"><div style="width:width:100%;"><table id="flex1" style="width:100%;height:200px;"></table></div></div>';
		bTable = true;
		return sTableLines;}
	if(type == 2){
		sTableLines +='<div style="position:relative">';
		sTableLines +=' <div style="width:600px;">';
		sTableLines +='  <div class="grid-header" style="width:100%">';
		sTableLines +='     <label>SlickGrid</label>';
		sTableLines +='     <span style="float:right" href="#" id="seach" class="ui-icon ui-icon-search" title="Toggle search panel"></span>';
		sTableLines +='   </div>';
		sTableLines +='   <div id="myGrid" style="width:100%;height:500px;"></div>';
		sTableLines +='   <div id="pager" style="width:100%;height:20px;"></div>';
		sTableLines +=' </div>';

		sTableLines +=' <div class="options-panel">';
		sTableLines +='   <b>Search:</b>';
		sTableLines +='   <hr/>';
		sTableLines +='   <div style="padding:6px;">';
		sTableLines +='     <label style="width:200px;float:left">Show tasks with % at least: </label>';

		sTableLines +='     <div style="padding:2px;">';
		sTableLines +='       <div style="width:100px;display:inline-block;" id="pcSlider"></div>';
		sTableLines +='     </div>';
		sTableLines +='     <br/>';
		sTableLines +='     <label style="width:200px;float:left">And title including:</label>';
		sTableLines +='     <input type=text id="txtSearch" style="width:100px;">';
		sTableLines +='     <br/><br/>';
		sTableLines +='     <button id="btnSelectRows">Select first 10 rows</button>';

		sTableLines +='     <br/>';

		sTableLines +='     <h2>Demonstrates:</h2>';
		sTableLines +='     <ul>';
		sTableLines +='       <li>a filtered Model (DataView) as a data source instead of a simple array</li>';
		sTableLines +='       <li>grid reacting to model events (onRowCountChanged, onRowsChanged)</li>';
		sTableLines +='       <li>';
		sTableLines +='         <b>FAST</b> DataView recalculation and <b>real-time</b> grid updating in response to data changes.<br/>The grid holds <b>50000</b> rows, yet you are able to sort, filter, scroll, navigate and edit as if it had 50';
		sTableLines +='         rows.';
		sTableLines +='       </li>';
		sTableLines +='       <li>adding new rows, bidirectional sorting</li>';
		sTableLines +='       <li>column options: cannotTriggerInsert</li>';
		sTableLines +='       <li>events: onCellChange, onAddNewRow, onKeyDown, onSelectedRowsChanged, onSort</li>';
		sTableLines +='       <li><font color=red>NOTE:</font> all filters are immediately applied to new/edited rows</li>';
		sTableLines +='       <li>Handling row selection against model changes.</li>';
		sTableLines +='       <li>Paging.</li>';
		sTableLines +='       <li>inline filter panel</li>';
		sTableLines +='     </ul>';
		sTableLines +='   </div>';
		sTableLines +=' </div>';
		sTableLines +='</div>';

		sTableLines +='<div id="inlineFilterPanel" style="display:none;background:#dddddd;padding:3px;color:black;">';
		sTableLines +='  Show tasks with title including <input type="text" id="txtSearch2">';
		sTableLines +='  and % at least &nbsp;';
		sTableLines +=' <div style="width:100px;display:inline-block;" id="pcSlider2"></div>';
		sTableLines +='</div>';


		return sTableLines;
	}
	},
	
	fnTypeCombo = function(oData){
	var sTableLines="";
		sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(oData.positionY)+'px;top:'+oData.positionX+'px;width:250px;height:16px;""><strong>'+oData.label+'</strong></span><select name="Combobox'+oData.id+'" size="1" id="Combobox'+oData.id+'" style="position:absolute;left:'+oData.positionY+'px;top:'+(oData.positionX+20)+'px;width:'+oData.tamanho+'px;height:21px;">';				
			for(var i= 0;oData.dominios.length > i ;i++){
				var values = oData.dominios[i].split('|');
					sTableLines +=	'<option value="'+values[0]+'">'+values[1]+'</option>';
				}
			sTableLines +='</select>';
	return sTableLines;
	},
	
	fnTypeTextArea = function(oData){},
	
	fnTypeDataPicker = function(oData){},
	
	fnTypeDataTime = function(oData){},

	fnType = function(oData){

	var sTableLines = "";
		for(var x= 0;oData.atributos.length > x ;x++){
			position = position + 20;	
			if(oData.atributos[x].type === 'INPUT'){
				sTableLines +=	fnTypeInput(oData.atributos[x]);
			}else if(oData.atributos[x].type === 'RADIO'){
				sTableLines +=	fnTypeRadio(oData.atributos[x]);
			}else if(oData.atributos[x].type === 'CHECKBOX'){
				sTableLines +=	fnTypeCheckbox(oData.atributos[x]);
			}else if(oData.atributos[x].type === 'COMBO'){
				sTableLines += fnTypeCombo(oData.atributos[x]);
			}else if(oData.atributos[x].type === 'TEXTAREA'){
			
			}else if(oData.atributos[x].type === 'DATAPICKER'){
			
			}else if(oData.atributos[x].type === 'DATATIME'){
			
			}else if(oData.atributos[x].type === 'TABLE'){
				sTableLines += fnTypeTable(1);
			}else if(oData.atributos[x].type === 'TAB'){

				sTableLines += '<div id="jQueryTabs1" style="position:absolute;left:5px;top:410px;width:'+oData.tabs[0].width+'px;height:'+oData.tabs[0].height+'px;">';
				sTableLines += '<ul>';
				for(a = 0;oData.tabs[0].abs.length > a;a++ ){
					sTableLines += '<li><a href="#jquerytabs1-page-'+a+'"><span>'+oData.tabs[0].abs[a].title+'</span></a></li>';		
				}
				sTableLines += '</ul>';	
				for(a = 0;oData.tabs[0].abs.length > a;a++ ){
					sTableLines += '<div style="height:279px;overflow:auto" id="jquerytabs1-page-'+a+'">';	
						for(b = 0;oData.tabs[0].abs[a].atributos.length > b;b++ ){
							if(oData.tabs[0].abs[a].atributos[b].type === 'INPUT'){
								sTableLines +=	fnTypeInput(oData.tabs[0].abs[a].atributos[b]);
							}else if(oData.tabs[0].abs[a].atributos[b].type === 'RADIO'){
								sTableLines +=	fnTypeRadio(oData.tabs[0].abs[a].atributos[b]);
							}else if(oData.tabs[0].abs[a].atributos[b].type === 'CHECKBOX'){
								sTableLines +=	fnTypeCheckbox(oData.tabs[0].abs[a].atributos[b]);
							}else if(oData.tabs[0].abs[a].atributos[b].type === 'COMBO'){
								sTableLines += fnTypeCombo(oData.tabs[0].abs[a].atributos[b]);
							}else if(oData.tabs[0].abs[a].atributos[b].type === 'TEXTAREA'){
							
							}else if(oData.tabs[0].abs[a].atributos[b].type === 'DATAPICKER'){
							
							}else if(oData.tabs[0].abs[a].atributos[b].type === 'DATATIME'){
							
							}
							
							//sTableLines +='<span style="color:#000000;font-family:Arial;font-size:13px;position:absolute;left:'+(oData.tabs[0].abs[a].atributos[b].positionY)+'px;top:'+oData.tabs[0].abs[a].atributos[b].positionX+'px;width:250px;height:16px;""><strong>'+oData.tabs[0].abs[a].atributos[b].label+'</strong></span>  <input type="text" id="Editbox'+b+'"style="position:absolute;left:'+oData.tabs[0].abs[a].atributos[b].positionY+'px;top:'+(oData.tabs[0].abs[a].atributos[b].positionX + 20)+'px;width:'+(oData.tabs[0].abs[a].atributos[b].tamanho)+'px;height:16px;" name="Editbox'+b+'">';				
						}
				sTableLines += '</div>';
				}
				
				sTableLines += '</div>';

			}
		}
		return sTableLines;
	}
	// Our plugin implementation code goes here.
	var objThat = $(this),
		search = function() {

			// Empty content of the DOM
			$(objThat).empty();

			var $content = $($summary).clone();
			var bShowDialog = true;
			var sUrl = "";

			$(objThat).append($content);

			if (type == undefined) {
				
				sUrl = 'id=' + sId;
				
			} else {
				
				sUrl = 'id=' + sId + '&type=' + type;
				
			}
			
			var aList = [{'id': parseInt(sId)}];


			if (bShowDialog && type) {

				sensus.util.actiondialog.launchActionDialog("summary", sensus.widgets.datatable.dialogSettings["summary"]);

			}

			return bShowDialog;

	};
	objThat.undelegate("click").unbind("click");

	if (!$summary) {

		$.ajax({
			url    : "commons/pages/summary.jsp",
			async  : false,
			success: function(data) {
				$summary = data;
				if (!search()) {
					sensus.pages.longrunningprocess.hasError = true;
				}
			}
		});

	} else {
		
		search();
		
	}
	
	sensus.util.pages.createPage(1);
	
//	$.ajax({
//		url : "dicionario/fetchTelaById.action",
//		oRequest   : 'dicionarioRequest',
//		text       : function(data, i) {
//			return sensus.locale.get("groupdelete.warning.nosmartpointsongroup", data[i.groupName]);
//		},
//		fnRequest  : function(data, i) {
//			aIds = 1;
//			return new dicionarioRequest (aIds,null, null, null, null, null, null, null, null, null, null)
//		},
//		success: function( resp ) {
//			var oSmtBody,
//					
//					sTableLines = "";
//					oData = resp.telas[0];
//					position = 0;
//					$('.ui-dialog').css({ width:" "+resp.telas[0].width+"px"});
//					$('.ui-dialog').css({ height:" "+(resp.telas[0].height+50)+"px"});
//					
//					$('#action-dialog').css({ width:" "+resp.telas[0].width+"px"});
//					$('#action-dialog').css({ height:" "+(resp.telas[0].height-50)+"px"});
//					$('#ui-dialog-title-action-dialog').text(resp.telas[0].title);
//					$(".ui-state-highlight").empty().append(fnType(oData));
//					$("#jQueryTabs1").tabs();
//					fnTable(resp.telas,3);
////					$("#seach").click(function(e) {
////						e.preventDefault();	
////						alert('aqui');
////						sensus.util.globalTable.toggleFilterRow();
////
////					});
//		}
//	});
//$(".ui-state-highlight").empty().append('<input type="text" id="Editbox2" style="position:absolute;left:146px;top:72px;width:418px;height:19px;line-height:19px;z-index:65;" name="Editbox2">');


};
