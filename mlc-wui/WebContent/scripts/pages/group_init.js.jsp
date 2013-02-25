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


	/* Initialize action buttons */
	$.sc.menuPlug(sensus.pages.group, {});

	/** * jQuery dataTable setup ** */
	sensus.pages.group.groupTable = $('#group-table').dataTable(sensus.widgets.datatable.setTable({

		id : "group-table",
		sAjaxSource : 'api/group/fetchall',
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
					//return new SearchMeter([]);
			},
			aColumns      : ['id','name','description','smartPointCount','createDate','centerLatitude','centerLongitude'],
			sResponseObj  : 'groups',
			bCheckbox     : true,
			iDefaultCol   : 1,
			bSelection    : true,
			iMapCol       : 5
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			,remove        : {
				url   : "api/group/delete",
				text  : function(data, i) {
					return $.sc.locale("groupdelete.warning.nosmartpointsongroup", data[i.groupName]);
				},
				oRequest  : 'groupRequest',

				//Create the request to delete group containing the ids of groups selected
				fnRequest : function(data, i) {
					var aIds = [];
					aIds.push(data[i.Id]);
					var request = {"selectionPaginationIds": aIds};
					return request;
				},
				success : function(data, i) {
					sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
					return $.sc.locale("action.deletegroup.success");
				}
			}
			</sec:authorize>
		},

		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			//TODO: Format  date
			//$("td:eq(" +col.groupDateAdded+ ")", nRow).text($.date.dateFormat(aData[col.groupDateAdded], sensus.settings.dateFormatMask));

			// Add Update Group Link
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
				$('td:eq(' + col.groupName + ')', nRow).html('<a href="group/update?groupId=' + aData[col.Id] + '" class="alist"><strong class="group-name">' + aData[col.groupName] + '</strong></a>');
			</sec:authorize>

			$("td:eq(" + col.Id + ")", nRow).text($.convertionNumber(aData[col.Id],false,0).dvalueConverter);

			// Add link to smartpoint list if the amount of lights is greater than zero
			if (parseInt(aData[col.groupSmartPointCount]) > 0) {

				$('td:eq(' + col.groupSmartPointCount + ')', nRow).html("<a class='afilter' href='smartpoint/ajax.list.action?groups="+ aData[col.Id] +"|'>"+ $.convertionNumber(aData[col.groupSmartPointCount],false,0).dvalueConverter +"</a>");

			} else {

				var oMapCol = $("td:eq("+ col.groupSmartPointCount +")", nRow).next();

				//Remove the links in the groups that the amount of lights is equals than zero
				$('td:eq(' + col.groupSmartPointCount + ')', nRow).html('<span class="ui-state-disabled">0</span>');

				//Remove the links of maps that the amount of lights in the group is equals than zero
				oMapCol.html('<span class="ui-state-disabled">'+oMapCol.children().text()+'</span>').unbind('click');
			}

			//Format the number of lights
			$('td:eq(' + col.groupSmartPointCount + ')', nRow).addClass("num");
		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

			//Update the amount of items displayed in list
			$('#checked-count').next().text($.sc.locale("table.filter.of")+' '+ iTotal);

		}
	}));
});
</script>
</sec:authorize>