<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <title>SlickGrid example 5: Collapsing</title>
  <link rel="stylesheet" href="../slick.grid.css" type="text/css"/>
  <link rel="stylesheet" href="../css/smoothness/jquery-ui-1.8.16.custom.css" type="text/css"/>
  <link rel="stylesheet" href="examples.css" type="text/css"/>
  <style>
    .cell-title {
      font-weight: bold;
    }

    .cell-effort-driven {
      text-align: center;
    }

    .toggle {
      height: 9px;
      width: 9px;
      display: inline-block;
    }

    .toggle.expand {
      background: url(../images/expand.gif) no-repeat center center;
    }

    .toggle.collapse {
      background: url(../images/collapse.gif) no-repeat center center;
    }

  </style>
</head>
<body>
<table  id='table' width="100%">
  <tr>
    <td valign="top" width="50%">
      <br/>
	  
	  <div id="actions" class="yui-u add-action first actions">
	<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
		<a tabindex="0" href="" id="ajax-button-Empresa" class="fg-button ui-widget ui-state-default ui-corner-all group-create">
			Nova Empresa
		</a>
		<a tabindex="1" href="" id="ajax-button-Filial" class="fg-button ui-widget ui-state-default ui-corner-all group-create">
			Nova Filial
		</a>
	</sec:authorize>
</div>
	  <br/>
      <div id="myGrid" style="width:100%;height:500px;"></div>
    </td>
  </tr>
</table>

<script>
oOdata=[];
$.ajax({
	url : "dicionario/search.action",
	success: function( resp ) {
		  var indent = 0;
  var parents = [];

  // initialize the grid

  grid = new Slick.Grid("#myGrid", createData(resp), createColumns(resp), options);

  grid.onCellChange.subscribe(function (e, args) {
    dataView.updateItem(args.item.id, args.item);
  });
	$(".tableEmpresa").click(function(e) {
			e.preventDefault();
			var sLinkDateUrl = $(this).attr('id');
			sensus.util.page.startProgressBar();
						
							// Call Widget Summary
							$("#action-dialog").summary(sLinkDateUrl, 3,"update");

							sensus.util.page.stopProgressBar();
	});

  grid.onAddNewRow.subscribe(function (e, args) {
    var item = {
      "id": "new_" + (Math.round(Math.random() * 10000)),
      "indent": 0,
      "title": "New task",
      "duration": "1 day",
      "percentComplete": 0,
      "start": "01/01/2009",
      "finish": "01/01/2009",
      "effortDriven": false};
    $.extend(item, args.item);
    dataView.addItem(item);
  });

  grid.onClick.subscribe(function (e, args) {
    if ($(e.target).hasClass("toggle")) {
      var item = dataView.getItem(args.row);
      if (item) {
        if (!item._collapsed) {
          item._collapsed = true;
        } else {
          item._collapsed = false;
        }

        dataView.updateItem(item.id, item);
      }
      e.stopImmediatePropagation();
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
  $("#pcSlider").slider({
    "range": "min",
    "slide": function (event, ui) {
      Slick.GlobalEditorLock.cancelCurrentEdit();

      if (percentCompleteThreshold != ui.value) {
        window.clearTimeout(h_runfilters);
        h_runfilters = window.setTimeout(dataView.refresh, 10);
        percentCompleteThreshold = ui.value;
      }
	  $(".tableEmpresa").click(function(e) {
			e.preventDefault();
			var sLinkDateUrl = $(this).attr('id');
			console.log(sLinkDateUrl);
	});
    }
	
  });


  // wire up the search textbox to apply the filter to the model
  $("#txtSearch").keyup(function (e) {
    Slick.GlobalEditorLock.cancelCurrentEdit();

    // clear on Esc
    if (e.which == 27) {
      this.value = "";
    }

    searchString = this.value;
    dataView.refresh();
  })
	}
});

function requiredFieldValidator(value) {
  if (value == null || value == undefined || !value.length) {
    return {valid: false, msg: "This is a required field"};
  } else {
    return {valid: true, msg: null};
  }
}
function createColumns(value) {
var columns = [];
var columns = [
  {id: "Codemp", name: "Codemp", field: "Codemp", width: 220, cssClass: "cell-title", formatter: TaskNameFormatter, editor: Slick.Editors.Text, validator: requiredFieldValidator},
  {id: "Codfilial", name: "Codfilial", field: "Codfilial", editor: Slick.Editors.Text},
  {id: "Razemp", name: "Razemp", field: "Razemp", editor: Slick.Editors.Text},
  {id: "Nomeemp", name: "Nomeemp", field: "Nomeemp", editor: Slick.Editors.Text},
  {id: "Mzfilial", name: "Mzfilial", field: "Mzfilial", editor: Slick.Editors.Text},
  {id: "Cnpjemp", name: "Cnpjemp", field: "Cnpjemp", editor: Slick.Editors.Text},
  {id: "Inscemp", name: "Inscemp", field: "Inscemp", editor: Slick.Editors.Text}
];
return columns;
}
var TaskNameFormatter = function (row, cell, value, columnDef, dataContext) {
  value = value.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");
  var spacer = "<span style='display:inline-block;height:1px;width:" + (15 * dataContext["indent"]) + "px'></span>";
  var idx = dataView.getIdxById(dataContext.id);
  if (data[idx + 1] && data[idx + 1].indent > data[idx].indent) {
    if (dataContext._collapsed) {
      return spacer + " <span class='toggle expand'></span>&nbsp;" + "<a class='tableEmpresa' id='"+ data[idx].Cnpjemp + "' >"+ value +"</a>" ;
    } else {
      return spacer + " <span class='toggle collapse'></span>&nbsp;" + "<a class='tableEmpresa' id='"+ data[idx].Cnpjemp + "' >"+ value +"</a>";
    }
  } else {
    return spacer + " <span class='toggle'></span>&nbsp;" + value ;
  }
};

var dataView;
var grid;
var data = [];
var columns = [
  {id: "Codemp", name: "Codemp", field: "Codemp", width: 220, cssClass: "cell-title", formatter: TaskNameFormatter, editor: Slick.Editors.Text, validator: requiredFieldValidator},
  {id: "Codfilial", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Razemp", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Nomeemp", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Mzfilial", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Cnpjemp", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Inscemp", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "End", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Num", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Comple", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Bair", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Cep", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Cid", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Uf", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Ddd", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Fone", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Fax", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Email", name: "Duration", field: "duration", editor: Slick.Editors.Text},
  {id: "Www", name: "Duration", field: "duration", editor: Slick.Editors.Text}
];

var options = {
  editable: false,
  enableAddRow: true,
  enableCellNavigation: true,
  asyncEditorLoading: false
};

var percentCompleteThreshold = 0;
var searchString = "";

function myFilter(item) {
  if (item["percentComplete"] < percentCompleteThreshold) {
    return false;
  }



  if (item.parent != null) {
    var parent = data[item.parent];

    while (parent) {
      if (parent._collapsed || (parent["percentComplete"] < percentCompleteThreshold) || (searchString != "" && parent["title"].indexOf(searchString) == -1)) {
        return false;
      }

      parent = data[parent.parent];
    }
  }

  return true;
}

function percentCompleteSort(a, b) {
  return a["percentComplete"] - b["percentComplete"];
}

function createData(oData){
  var indent = 0;
  var parents = [];

var count = -1,
parent=0;
  // prepare the data oData.empresa.length
  for (var i = 0; i < oData.empresa.length; i++) {
console.log(count); 
count++;  
   var d = (data[count] = {});

	
		d["id"] = "id_" + count;
		d["indent"] = 0;
		d["parent"] = null;
		d["Codemp"] = ' '+oData.empresa[i].codemp;
		d["Codfilial"] = '0';
		d["Razemp"] = ' '+oData.empresa[i].razemp;
		d["Nomeemp"] = ' '+oData.empresa[i].nomeemp;
		d["Mzfilial"] = ' '+oData.empresa[i].mzfilial;
		d["Cnpjemp"] = ' '+oData.empresa[i].cnpjemp;
		d["Inscemp"] = ' '+oData.empresa[i].inscemp;
if(oData.empresa[i].listFilial != null)	{		
	 if(oData.empresa[i].listFilial.length > 0){
		for(x=0;x< oData.empresa[i].listFilial.length;x++){
			count++;
			 var d = (data[count] = {});
			d["id"] = "id_" +count;
			d["indent"] = 1;
			d["parent"] = parent;
			d["Codemp"] = ' '+oData.empresa[i].listFilial[x].codemp;
			d["Codfilial"] = oData.empresa[i].listFilial[x].codfilial;
			d["Razemp"] = oData.empresa[i].listFilial[x].razfilial;
			d["Nomeemp"] = oData.empresa[i].listFilial[x].nomefilial;
			d["Mzfilial"] = oData.empresa[i].listFilial[x].mzfilial;
			d["Cnpjemp"] = oData.empresa[i].listFilial[x].cnpjfilial;
			d["Inscemp"] = ' '+oData.empresa[i].listFilial[x].inscfilial;
		}
	 }
}
   parent = parent + count + 1; 
  }

  // initialize the model
  dataView = new Slick.Data.DataView({ inlineFilters: true });
  dataView.beginUpdate();

  dataView.setItems(data);
  dataView.setFilter(myFilter);
  dataView.endUpdate();

return dataView;
}

$(function () {
$(".tableEmpresa").click(function(e) {
			e.preventDefault();
			var sLinkDateUrl = $(this).attr('id');
	});

})


	$(".tableEmpresa").click(function(e) {
			e.preventDefault();
			var sLinkDateUrl = $(this).attr('id');
	});
    
	$("#ajax-button-Empresa").click(function(e) {
		e.preventDefault();
		sensus.util.pages.createPage(2,"formEmpresa","dicionario/insertEmpresa.action");
	});
	$("#ajax-button-Filial").click(function(e) {
			e.preventDefault();
			sensus.util.pages.createPage(3,"formFilial","dicionario/insertFilial.action");
	});
	
	$('.ui-button:eq(2)').click(function(e) {
		e.preventDefault();
		alert('sss');
	});
	
</script>
</body>
</html>
