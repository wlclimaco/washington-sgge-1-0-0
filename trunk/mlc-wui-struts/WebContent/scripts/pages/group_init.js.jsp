<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.group
 * @description The init namespace for the Group Page.
 */

/**
 * @fileoverview Initializes the group page.
 * 
 * @author Anke Doerfel-Parker
 */
$(document).ready(function() {
	
	sensus.util.page.initMessaging();
	sensus.util.actiondialog.initActionDialog();
	sensus.util.page.showContent("#yui-main,.yui-t2");
		
	/* Initialize action buttons */
	sensus.util.page.menuPlug(sensus.pages.group, {});
	
	/** * jQuery dataTable setup ** */
	sensus.pages.group.groupTable = $('#group-table').dataTable(sensus.widgets.datatable.setTable({

		id : "group-table",
		sAjaxSource : sensus.settings.searchUrl,
		aColumns : [
			{sId: "Id",                   bVisible : false                   },
			{sId: "groupName",            sWidth : "5%",   bSortable : true  },
			{sId: "groupDescription",     sWidth : "5%",   bSortable : false },
			{sId: "groupSmartPointCount", sWidth : "5%",   bSortable : true  },
			{sId: "groupDateAdded",       sWidth : "5%",   bSortable : true  },
			{sId: "groupLatitude",        sWidth : "5%",   bVisible : false  },
			{sId: "groupLongitude",       sWidth : "5%",   bVisible : false  }
		],

		oSettings : {
			oRequest : inquiryPaginationRequest,
			fnRequest : function() {

					return new SearchMeter([]);

			}, 
			aColumns      : ['id','name','description','smartPointCount','createDate','centerLatitude','centerLongitude'],
			sResponseObj  : 'groups',		
			bCheckbox     : true,
			iDefaultCol   : 1,
			bSelection    : true,
			iMapCol       : 5
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			,remove : {
					url        : sensus.settings.deleteGroupUrl,
					oRequest   : 'groupRequest',
					text       : function(data, i) {
						return sensus.locale.get("groupdelete.warning.nosmartpointsongroup", data[i.groupName]);
					},
					fnRequest  : function(data, i) {
						aIds = [data[i.Id]];
						return new groupRequest(null, null, null, null, null, null, aIds, null);
					},					
					success   : function(data, i) {
						sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
						return sensus.locale.get("action.deletegroup.success");
					}
			},
			
			edit : {
					col : "groupName",
					url : 'group/ajax.createGroupPage.action'
			}
			</sec:authorize>
		},
		
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {
			
			//Format  date
			$("td:eq(" +col.groupDateAdded+ ")", nRow).text($.date.dateFormat(aData[col.groupDateAdded], sensus.settings.dateFormatMask));

			// Add Update Group Link
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
				$('td:eq(' + col.groupName + ')', nRow).html('<a href="group/ajax.createGroupPage.action?groupId=' + aData[col.Id] + '&groupName=' + escape(aData[col.groupName]) + '&groupDescription=' + escape(aData[col.groupDescription]) + '" class="alist"><strong class="group-name">' + aData[col.groupName] + '</strong></a>');
			</sec:authorize>
				
			$("td:eq(" + col.Id + ")", nRow).text($.convertionNumber(aData[col.Id],false,0).dvalueConverter);		
			
			// Add link to smartpoint list
			if (parseInt(aData[col.groupSmartPointCount]) > 0) {	
				 
				$('td:eq(' + col.groupSmartPointCount + ')', nRow).html("<a class='afilter' href='smartpoint/ajax.list.action?groups="+ aData[col.Id] +"|'>"+ $.convertionNumber(aData[col.groupSmartPointCount],false,0).dvalueConverter +"</a>");
			
			} else {
				 
				$('td:eq(' + col.groupSmartPointCount + ')', nRow).html('<span class="ui-state-disabled">0</span>');	
				
				var oMapCol = $("td:eq("+ col.groupSmartPointCount +")", nRow).next();
				
				oMapCol.html('<span class="ui-state-disabled">'+oMapCol.children().text()+'</span>').unbind('click');				
				
			}	
			
			$('td:eq(' + col.groupSmartPointCount + ')', nRow).addClass("num");
		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			$('#checked-count').next().text(sensus.locale.get("table.filter.of")+' '+ iTotal);

		}
	}));					
});
</script>
</sec:authorize>