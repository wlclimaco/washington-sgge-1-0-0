<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
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



	sensus.pages.dashboard.oPreLoadHeaderResponse =	<c:choose>
															<c:when test="${empty dashBoardHeaderResponse}">
																null
															</c:when>
															<c:otherwise>
																${dashBoardHeaderResponse}
															</c:otherwise>
														</c:choose>;
   	sensus.pages.dashboard.oPreLoadResumeResponse =	<c:choose>
															<c:when test="${empty resumeResponse}">
																null
															</c:when>
															<c:otherwise>
																${resumeResponse}
															</c:otherwise>
												        </c:choose>;
    sensus.pages.dashboard.oPreLoadAlertsResponse =	<c:choose>
															<c:when test="${empty alertsResponse}">
																null
															</c:when>
															<c:otherwise>
																${alertsResponse}
															</c:otherwise>
												        </c:choose>;

   	sensus.pages.dashboard.oPreLoadPowerResponse  =	<c:choose>
														<c:when test="${empty powerFailureResponse}">
															null
														</c:when>
														<c:otherwise>
															${powerFailureResponse}
														</c:otherwise>
											        </c:choose>;

 	sensus.pages.dashboard.oPreLoadLampResponse   =	<c:choose>
													   <c:when test="${empty lampFailureResponse}">
													       null
												       </c:when>
													   <c:otherwise>
														   ${lampFailureResponse}
													   </c:otherwise>
										           </c:choose>;

		sensus.pages.dashboard.header.init();

		sensus.pages.dashboard.lightingControl.init();

		$('#kwh-1').append(" "+$.convertionNumber($('#kwh-1'),true).sNmConverter);
		$('#kwh-2').append(" "+$.convertionNumber($('#kwh-2'),true).sNmConverter);

		sensus.pages.dashboard.alertsByType.init();

		sensus.pages.dashboard.lightFailureTable = $('#dashboard-lightfailure-table').dataTable(sensus.widgets.datatable.setTable(sensus.pages.dashboard.tableFailures.init("dashboard-lightfailure-table", function () {return 'LAMP_FAILURE'}, sensus.pages.dashboard.oPreLoadLampResponse)));

		sensus.pages.dashboard.powerFailureTable = $('#dashboard-powerfailure-table').dataTable(sensus.widgets.datatable.setTable(sensus.pages.dashboard.tableFailures.init("dashboard-powerfailure-table", function () {return 'POWER_FAILURE'}, sensus.pages.dashboard.oPreLoadPowerResponse)));

		$(".dataTables_wrapper .stamp, .yui-gb .table-footer").remove();

		$.sc.stopGlobalProgressBar();

});
</script>
</sec:authorize>