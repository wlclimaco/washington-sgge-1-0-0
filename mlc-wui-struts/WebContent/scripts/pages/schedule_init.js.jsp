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
	
	/* Init common page functionality */
	sensus.util.page.initMessaging();
	sensus.util.actiondialog.initActionDialog();
	
	query : {
		/**
		 * Template for GET request returning to main schedule page. The
		 * schedule parameter will cause a message to be displayed.
		 */
		schedule : "{0}?schedule={1}"
	}
		
	/** * jQuery dataTable setup ** */
	sensus.util.page.showContent("#yui-main");
	sensus.pages.schedule.scheduleTable = $('#schedule-table').dataTable(sensus.widgets.datatable.setTable({

		id           : "schedule-table",
		sAjaxSource  : sensus.settings.searchUrl,
		aColumns     : [
			{sId: "Id",                       bVisible : false},
			{sId: "scheduleName",             sWidth : "5%", bSortable : true},
			{sId: "scheduleDescription",      sWidth : "5%", bSortable : false},
			{sId: "scheduleType",             sWidth : "5%", bSortable : true},
			{sId: "scheduleSmartPointCount",  sWidth : "5%", bSortable : true},
			{sId: "scheduleDateAdded",        sWidth : "5%", bSortable : true},
			{sId: "centerLatitude",           bVisible : false},
			{sId: "centerLongitude",          bVisible : false},
			{sId: "scheduleDelete",           sWidth : "5%", bSortable : false}
		],

		oSettings : {
			oRequest     : inquiryScheduleRequest,
			aColumns     : ['id','name','description','scheduleTypeEnum','smartPointCount','createDate','centerLatitude','centerLongitude',''],
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
		},
			
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {
		
			//Format number
			$("td:eq("+ col.scheduleSmartPointCount +")", nRow).text($.convertionNumber(aData[col.scheduleSmartPointCount],false,0).dvalueConverter);
			
			if (aData[col.scheduleSmartPointCount] == 0) {
			
				$("td:eq("+ col.scheduleSmartPointCount +")", nRow).html('<span class="ui-state-disabled">0</span>');
			
				var oMapCol = $("td:eq("+ col.scheduleSmartPointCount +")", nRow).next();
			
				oMapCol.html('<span class="ui-state-disabled">'+oMapCol.children().text()+'</span>').unbind('click');				
			
			}
			
			//Format date				
			$("td:eq(" +col.scheduleDateAdded+ ")", nRow).text($.date.dateFormat(aData[col.scheduleDateAdded], sensus.settings.dateFormatMask));

			var bIsTypeOffset = aData[col.scheduleType].toLowerCase() == sensus.locale.get("schedule.table.type.offset").toLowerCase();
			var bIsTypeEvent = aData[col.scheduleType].toLowerCase() == sensus.locale.get("schedule.table.type.event").toLowerCase();
			
			var sScheduleType = aData[col.scheduleType];
			var sUrl = 'OffsetSchedule';
			var sFilter = 'offset_schedule';

			if(sScheduleType == 'EVENT'){

				sUrl = 'EventSchedule';
				sFilter = 'event_schedule';
			
			}
			
				$('td:eq(' + col.scheduleType + ')', nRow).html(sensus.locale.get("schedule.table.type."+sScheduleType.toLowerCase()));
				
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					$('td:eq(' + col.scheduleName + ')', nRow).html('<a class="alist" href="schedule/ajax.create'+sUrl+'.action?id=' + aData[col.Id] + '"><strong class="schedule-name">' + aData[col.scheduleName] + '</strong></a>');
				</sec:authorize>
				
				if (aData[col.scheduleSmartPointCount] > 0){
					
					$('td:eq(' + col.scheduleSmartPointCount + ')', nRow).html("<a class='afilter' href='smartpoint/ajax.list.action?"+sFilter+"="+ aData[col.Id] +"|'>"+ aData[col.scheduleSmartPointCount] +"</a>");
					
				}
			
			<sec:authorize ifAllGranted="ROLE_Role.Admin">
			$("td:eq("+ col.scheduleDelete +")", nRow).html('<a href="#" class="delete delete_dialog delete_col '+sScheduleType+'">'+sensus.locale.get("table.action.delete")+'</a>');
			</sec:authorize>			
		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
		
			$('#checked-count').next().text(sensus.locale.get("table.filter.of")+' '+ iTotal);

		}
	}));
	/*
	 * Display message from URL if it came from a successful create
	 * schedule request or hide messaging area
	 */
	var aNvpairs = sensus.util.page.getUrlVars();
	var sMessage = "";
	
	if (aNvpairs.scheduleId == "null" || aNvpairs.scheduleId == "") {
		
		sMessage = sensus.locale.get("action.addschedule.success", [ decodeURIComponent(aNvpairs.schedule), sensus.settings.smartpointUrl ]);
		
	} else {
		
		sMessage = sensus.locale.get("action.updateschedule.success", [ decodeURIComponent(aNvpairs.schedule), sensus.settings.smartpointUrl ]);
		
	}
	
	if (aNvpairs.schedule) {
		
		sensus.util.page.showMessage("messaging-main", sMessage, "confirm");
	
	}
	
	/* Initialize action buttons */
	sensus.util.page.menuPlug(sensus.pages.schedule, {});
	
	$('.filter-results-container').remove();
	
	$('#schedule-table').on("click", ".delete_dialog", function(e){
		e.preventDefault();
		$('.checkbox input').attr('checked', false);
		sensus.pages.schedule.countSmartpoints = $(this).parent().parent().find('td:eq(5)').text();
		sensus.pages.schedule.scheduleName     = $(this).parent().parent().find('td:eq(2)').text();
		sensus.widgets.datatable.selectedRows  = [$(this).parent().siblings('.checkbox').children().val()];
		sensus.util.actiondialog.launchActionDialog("remove", sensus.pages.schedule.dialogSettings.deleteSchedule);
	});
	
});
</script>
</sec:authorize>