<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.schedule
 * @description The init namespace for the Schedule Page.
 */

/**
 * @fileoverview Initializes the schedule page.
 * @author Anke Doerfel-Parker
 */
$(document).ready(function() {

	query : {
		/**
		 * Template for GET request returning to main schedule page. The
		 * schedule parameter will cause a message to be displayed.
		 */
		schedule : "{0}?schedule={1}"
	}

	/** * jQuery dataTable setup ** */
	sensus.pages.schedule.scheduleTable = $('#schedule-table').dataTable(sensus.widgets.datatable.setTable({

		id           : "schedule-table",
		sAjaxSource  : "api/schedule/fetchAll",
		bPreLoad : true,
		<c:choose>
			<c:when test="${not empty refresh}">
				oPreLoadResponse : "refresh",
			</c:when>
			<c:when test="${empty response}">
		    	oPreLoadResponse : null,
		    </c:when>
		    <c:otherwise>
		    	oPreLoadResponse : ${response},
		    </c:otherwise>
		</c:choose>
		aColumns     : [
			{sId: "Id",                       bVisible : false },
			{sId: "scheduleName",             bVisible : true, sWidth : "5%", bSortable : true},
			{sId: "scheduleDescription",      bVisible : true, sWidth : "5%", bSortable : false},
			{sId: "scheduleType",             bVisible : true, sWidth : "5%", bSortable : true},
			{sId: "scheduleLightCount",  	  bVisible : true, sWidth : "5%", bSortable : true},
			{sId: "scheduleDateAdded",        bVisible : true, sWidth : "5%", bSortable : true},
			{sId: "centerLatitude",           bVisible : false },
			{sId: "centerLongitude",          bVisible : false },
			{sId: "scheduleStatusEnum",	  	  bVisible : false }
		],

		oSettings : {
			oRequest     : inquiryScheduleRequest,
			aColumns     : ['id','name','description','scheduleTypeEnum','lightCount','createDate','centerLatitude','centerLongitude','precedence'],
			sResponseObj : 'schedules',
			fnRequest    : function() {

				return  new SearchMeter([]);

			},
			 <sec:authorize ifAllGranted="ROLE_Role.Admin">
			bCheckbox    : true,
			</sec:authorize>
			bSelection   : true,
			iMapCol      : 6,
			iDefaultCol  : 1
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			,remove        : {
				url   : "api/schedule/delete",
				text  : function(data, i) {
					return $.sc.locale("scheduledelete.warning.nosmartpointsonschedule", data[i.scheduleName]);
				},
				oRequest  : 'scheduleRequest',
				fnRequest : function(data, i) {

					var aIds = [];
					aIds.push(data[i.Id]);
					var request = {"selectionPaginationIds": aIds};
					return request;

				},
				success : function(data, i) {

					sensus.widgets.datatable.reloadTable(sensus.pages.schedule.scheduleTable);
					return $.sc.locale("action.deleteschedule.success");

				}
			}
			</sec:authorize>
		},

		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			//Format number
			$("td:eq("+ col.scheduleLightCount +")", nRow).text($.convertionNumber(aData[col.scheduleLightCount],false,0).dvalueConverter);

			if (aData[col.scheduleLightCount] == 0) {

				$("td:eq("+ col.scheduleLightCount +")", nRow).html('<span class="ui-state-disabled">0</span>');

				var oMapCol = $("td:eq("+ col.scheduleLightCount +")", nRow).next();

				oMapCol.html('<span class="ui-state-disabled">'+oMapCol.children().text()+'</span>').unbind('click');

			}

			//Format date
			$("td:eq(" +col.scheduleDateAdded+ ")", nRow).text($.sc.dateFormat(aData[col.scheduleDateAdded], sensus.settings.dateFormatMask));

			var bIsTypeOffset = aData[col.scheduleType].toLowerCase() == $.sc.locale("schedule.table.type.offset").toLowerCase();
			var bIsTypeEvent = aData[col.scheduleType].toLowerCase() == $.sc.locale("schedule.table.type.event").toLowerCase();

			var sScheduleType = aData[col.scheduleType];
			var sUrl = 'Offset';
			var sFilter = 'offset_schedule';

			if(sScheduleType == 'EVENT'){

				sUrl = 'Event';
				sFilter = 'event_schedule';

			}

				$('td:eq(' + col.scheduleType + ')', nRow).html($.sc.locale("schedule.table.type."+sScheduleType.toLowerCase()));

				<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					$('td:eq(' + col.scheduleName + ')', nRow).html('<a class="alist" href="systemintelligence/schedule/insert'+sUrl+'?id=' + aData[col.Id] + '"><strong class="schedule-name">' + aData[col.scheduleName] + '</strong></a>');
				</sec:authorize>

				if (aData[col.scheduleLightCount] > 0){

					$('td:eq(' + col.scheduleLightCount + ')', nRow).html("<a class='afilter' href='light?"+sFilter+"="+ aData[col.Id] +"|'>"+ aData[col.scheduleLightCount] +"</a>");

				}

			<sec:authorize ifAllGranted="ROLE_Role.Admin">
			</sec:authorize>
			if(aData[col.scheduleStatusEnum])
			{
				$(nRow).addClass(aData[col.scheduleStatusEnum].toLowerCase());
			}

		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			$('#checked-count').next().text($.sc.locale("table.filter.of")+' '+ iTotal);

		}
	}));
	/*
	 * Display message from URL if it came from a successful create
	 * schedule request or hide messaging area
	 */
	var aNvpairs = "";//sensus.util.page.getUrlVars();
	var sMessage = "";

	if (aNvpairs.scheduleId == "null" || aNvpairs.scheduleId == "") {

		sMessage = $.sc.locale("action.addschedule.success", [ decodeURIComponent(aNvpairs.schedule), sensus.settings.smartpointUrl ]);

	} else {

		sMessage = $.sc.locale("action.updateschedule.success", [ decodeURIComponent(aNvpairs.schedule), sensus.settings.smartpointUrl ]);

	}

	if (aNvpairs.schedule) {

		$.sc.locale.showMessage("messaging-main", sMessage, "confirm");

	}

	/* Initialize action buttons */
	$.sc.menuPlug(sensus.pages.schedule, {});

	$('.filter-results-container').remove();

	$('#schedule-table').on("click", ".delete_dialog", function(e){
		e.preventDefault();
		$('.checkbox input').attr('checked', false);
		$('#checked-count').text("0");
		sensus.pages.schedule.countSmartpoints = $(this).parent().parent().find('td:eq(5)').text();
		sensus.pages.schedule.scheduleName     = $(this).parent().parent().find('td:eq(2)').text();
		sensus.widgets.datatable.selectedRows  = [$(this).parent().siblings('.checkbox').children().val()];
		$.sc.launchActionDialog("remove", sensus.pages.schedule.dialogSettings.deleteSchedule);
	});

	$.sc.stopGlobalProgressBar();

});
</script>
</sec:authorize>