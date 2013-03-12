<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager', 'ROLE_Role.Analytic User')">
<script type="text/javascript">
/**
* @namespace sensus.pages.dashboard
 * @description The init namespace for the Dashboard Page.
 */

/**
 * @fileoverview Initializes the dashboard page.
 *  @author Alexandre Tiveron
 */

$(document).ready(function() {

	$.address.parameter('di', $.datepicker.formatDate(sensus.settings.DATE_FORMAT, new Date(), null, true));
	$.address.parameter('de', $.datepicker.formatDate(sensus.settings.DATE_FORMAT, new Date(), null, true));

	//sensus.util.page.initMessaging();

	sensus.pages.dashboard.header.init();

	sensus.pages.dashboard.lightingControl.init();


	$('#kwh-1').append(" "+$.convertionNumber($('#kwh-1'),true).sNmConverter);
	$('#kwh-2').append(" "+$.convertionNumber($('#kwh-2'),true).sNmConverter);
	sensus.pages.dashboard.alertsByType.init();

	sensus.pages.dashboard.lightFailureTable = $('#dashboard-lightfailure-table').dataTable(sensus.widgets.datatable.setTable(sensus.pages.dashboard.tableFailures.init("dashboard-lightfailure-table", function () {return 'LAMP_FAILURE'})));

	sensus.pages.dashboard.powerFailureTable = $('#dashboard-powerfailure-table').dataTable(sensus.widgets.datatable.setTable(sensus.pages.dashboard.tableFailures.init("dashboard-powerfailure-table", function () {return 'POWER_FAILURE'})));

	$(".dataTables_wrapper .stamp, .yui-gb .table-footer").hide();

	$.sc.stopProgressBar(0,false);
});
</script>
</sec:authorize>