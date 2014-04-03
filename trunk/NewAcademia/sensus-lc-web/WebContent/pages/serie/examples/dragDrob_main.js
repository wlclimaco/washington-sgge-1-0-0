
function avgTotalsFormatter(totals, columnDef) {

  var val = totals.avg && totals.avg[columnDef.field];
  if (val != null) {
    return "avg: " + Math.round(val) + "%";
  }
  return "";
}

function sumTotalsFormatter(totals, columnDef) {
  var val = totals.sum && totals.sum[columnDef.field];
  if (val != null) {
    return "total: " + ((Math.round(parseFloat(val)*100)/100));
  }
  return "";
}

function requiredFieldValidator(value) {
  if (value == null || value == undefined || !value.length) {
    return {valid: false, msg: "This is a required field"};
  } else {
    return {valid: true, msg: null};
  }
}

function myFilter(item, args) {
  return item["percentComplete"] >= args.percentComplete;
}

function percentCompleteSort(a, b) {
  return a["percentComplete"] - b["percentComplete"];
}

function comparer(a, b) {
  var x = a[sortcol], y = b[sortcol];
  return (x == y ? 0 : (x > y ? 1 : -1));
}

function groupByDuration() {
  dataView.setGrouping({
    getter: "duration",
    formatter: function (g) {
      return "Duration:  " + g.value + "  <span style='color:green'>(" + g.count + " items)</span>";
    },
    aggregators: [
      new Slick.Data.Aggregators.Avg("percentComplete"),
      new Slick.Data.Aggregators.Sum("cost")
    ],
    aggregateCollapsed: false,
    lazyTotalsCalculation: true
  });
}

function groupByDurationOrderByCount(aggregateCollapsed) {
  dataView.setGrouping({
    getter: "duration",
    formatter: function (g) {
      return "Duration:  " + g.value + "  <span style='color:green'>(" + g.count + " items)</span>";
    },
    comparer: function (a, b) {
      return a.count - b.count;
    },
    aggregators: [
      new Slick.Data.Aggregators.Avg("percentComplete"),
      new Slick.Data.Aggregators.Sum("cost")
    ],
    aggregateCollapsed: aggregateCollapsed,
    lazyTotalsCalculation: true
  });
}

function groupByDurationEffortDriven() {
  dataView.setGrouping([
    {
      getter: "duration",
      formatter :function (g) {
        return "Duration:  " + g.value + "  <span style='color:green'>(" + g.count + " items)</span>";
      },
      aggregators: [
        new Slick.Data.Aggregators.Sum("duration"),
        new Slick.Data.Aggregators.Sum("cost")
      ],
      aggregateCollapsed: true,
      lazyTotalsCalculation: true
    },
    {
      getter: "effortDriven",
      formatter :function (g) {
        return "Effort-Driven:  " + (g.value ? "True" : "False") + "  <span style='color:green'>(" + g.count + " items)</span>";
      },
      aggregators: [
        new Slick.Data.Aggregators.Avg("percentComplete"),
        new Slick.Data.Aggregators.Sum("cost")
      ],
      collapsed: true,
      lazyTotalsCalculation: true
    }
  ]);
}

function groupByDurationEffortDrivenPercent() {
  dataView.setGrouping([
    {
      getter: "duration",
      formatter: function (g) {
        return "Duration:  " + g.value + "  <span style='color:green'>(" + g.count + " items)</span>";
      },
      aggregators: [
        new Slick.Data.Aggregators.Sum("duration"),
        new Slick.Data.Aggregators.Sum("cost")
      ],
      aggregateCollapsed: true,
      lazyTotalsCalculation: true
    },
    {
      getter: "effortDriven",
      formatter: function (g) {
        return "Effort-Driven:  " + (g.value ? "True" : "False") + "  <span style='color:green'>(" + g.count + " items)</span>";
      },
      aggregators :[
        new Slick.Data.Aggregators.Sum("duration"),
        new Slick.Data.Aggregators.Sum("cost")
      ],
      lazyTotalsCalculation: true
    },
    {
      getter: "percentComplete",
      formatter: function (g) {
        return "% Complete:  " + g.value + "  <span style='color:green'>(" + g.count + " items)</span>";
      },
      aggregators: [
        new Slick.Data.Aggregators.Avg("percentComplete")
      ],
      aggregateCollapsed: true,
      collapsed: true,
      lazyTotalsCalculation: true
    }
  ]);
}


