<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<script type="text/javascript">
$(document).ready(function() {

	var $csv = $("#csv");
	var sMessage = $.address.parameter("message");
	var sType = "confirm";

	// Initialize action buttons
	sensus.util.page.menuPlug(sensus.pages.group);

	// Initialize Messagings
	sensus.util.page.initMessaging();

	if (sMessage) {

		switch (sMessage) {

		case "INSERT" :

			sMessage = sensus.locale.get("action.addsgroup.success", unescape($.address.parameter("groupName")));

			break;

		case "UPDATE" :

			sMessage = sensus.locale.get("action.updategroup.success", unescape($.address.parameter("groupName")));

			break;

		default :

			sMessage = unescape(sMessage);
			sType = "error";
		}

		sensus.util.page.showMessage("messaging-main", sMessage, sType);

		// Clear parameters
		$.fn.pageLoader.parameters({check : null, message : null, groupName : null});
	}

	// To Generate the archive CSV
	sensus.util.exportcsv.setGenerateCSVEvent({

		url : "api/export/generateGroupCSV",

		$element : $csv,

		getGenerateRequestCSV : function() {

			// Get Request populate with current filters
			var oRequest = new InquiryGroupRequest(sensus.pages.group.fnRequestAction());

			// Default Request Parameters
			oRequest.sortExpressions = sensus.util.page.getSortExpression(sensus.pages.group.groupTable);

			return oRequest;
		}
	});

	// Group jQuery Data Table
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
    {id: "effort-driven", name: "Effort Driven", width: 80, minWidth: 20, maxWidth: 80, cssClass: "cell-effort-driven", field: "effortDriven", formatter: Slick.Formatters.Checkmark, editor: Slick.Editors.Checkbox},
	{id: "finishi", name: "Finishi", field: "finishi", minWidth: 60, formatter: Slick.Formatters.Button},
	{id: "pesquisa", name: "Pesquisa", field: "pesquisa", minWidth: 300, formatter: Slick.Formatters.Pesquisa}
  ];
  var options = {
    editable: true,
    enableAddRow: true,
    enableCellNavigation: true,
    asyncEditorLoading: false,
    autoEdit: false
  };

  function Deletes(id) {

     data.splice(id, 1);

	grid.invalidate();
	console.log(grid.rows);

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
	  d["effortDriven"] = i;
	  d["effortDriven"] = i;
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

  	$('.button').click(function(e) {
		e.preventDefault();
		Deletes($(this).attr("id"));
	});


  })

	// Column Delete Event
	<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">

		$("#group-table").delegate(".delete.delete_dialog.delete_col", "click", function (e) {

			var $oRow = $(this).parents("tr");
			var iId = $oRow.find("td:eq(1)").text();
			var sGroupName = $oRow.find("td:eq(2)").text();

			e.preventDefault();

			sensus.pages.group.aSelectedGroups = [{id : iId, groupName: sGroupName}];

			sensus.util.actiondialog.launchActionDialog("deleteGroups", sensus.pages.group.dialogSettings["deleteGroups"]);
		});

	</sec:authorize>

	$("#buttonCreateGroupPage").click(function(e) {

		e.preventDefault();

		sensus.util.session.create({
			key : "SelectedFilters",
			value : $.fn.pageLoader.queryString()
		});
	});

	<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

		<c:choose>
			<c:when test="${not empty refresh}">
				var oPreLoad = "refresh";
			</c:when>
			<c:when test="${empty filters}">
				var oPreLoad = null;
			</c:when>
			<c:otherwise>
				var oPreLoad = ${filters};
			</c:otherwise>
		</c:choose>

		sensus.pages.group.fnFillFilter(oPreLoad);

	</sec:authorize>

	sensus.util.page.stopGlobalProgressBar();

});
</script>
</sec:authorize>