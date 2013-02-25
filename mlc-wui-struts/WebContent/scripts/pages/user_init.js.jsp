<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.user
 * @description The init namespace for the User Page.
 * @fileoverview Initializes the user page.
 * 
 * @author Vinicius Scalon Ferreira
 */
 
$(document).ready(function() {
	
	sensus.util.page.initMessaging();
	sensus.util.actiondialog.initActionDialog();

	sensus.util.page.showContent("#yui-main,.yui-t2");
		
	/* Initialize action buttons */
	sensus.util.page.menuPlug(sensus.pages.user, {});
	
	/* Initialize Chosen plugin */
	$(".chzn-select").chosen();
	
	/** * jQuery dataTable setup ** */
	sensus.pages.user.userTable = $('#user-table').dataTable(sensus.widgets.datatable.setTable({

		id           : "user-table",
		sAjaxSource  : sensus.settings.search,
		aColumns     : [
			{sId: "Id",                   bVisible : false                },
			{sId: "id",                   sWidth : "5%", bSortable : true },
			{sId: "firstName",            sWidth : "5%", bSortable : true },
			{sId: "userRole",             sWidth : "5%", bSortable : true },
			{sId: "userEmail",            sWidth : "5%", bSortable : true },
			{sId: "userSmartPointCount",  sWidth : "5%", bSortable : true  },
			{sId: "userDateAdded",        sWidth : "5%", bSortable : true  },
			{sId: "userLatitude",         sWidth : "5%", bVisible : false  },
			{sId: "userLongitude",        sWidth : "5%", bVisible : false  },
			{sId: "userGroups",           sWidth : "5%", bVisible : false  }
		],
		oSettings   : {
			oRequest      : inquiryUserRequest,
			fnRequest     : function() { },
			aColumns      : ['id','userName','fullName','role','email','totalLights','modifyDate','latitude','longitude','groups[object]'],
			sResponseObj  : 'users',
			bCheckbox     : true,
			iDefaultCol   : 1,
			bSelection    : true,
			iMapCol       : 7
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			,remove        : {
				url   : sensus.settings.deleteUser,
				text  : function(data, i) {
					return sensus.locale.get("user.validation.deleteUser", data[i.id]);
				},
				oRequest  : 'userRequest',
				fnRequest : function(data, i) {

					var aIds = [];
					aIds.push(data[i.Id]);
					var request = new userRequest(null, null, null, null, null, null, null, null, null, null, aIds);
					return request;

				},
				success : function(data, i) {

					sensus.widgets.datatable.reloadTable(sensus.pages.user.userTable);
					return sensus.locale.get("action.deleteuser.success");

				}
			}
			</sec:authorize>
		},
		
		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			// Remove checkbox and delete button preventing User to delete your self
			if($('td:eq(' + col.Id + ')', nRow).html() == sensus.settings.userContext.id) {
			
				$('td:eq(0)', nRow).html('');

				$('td:eq(12)', nRow).unbind('click').html('');

			}

			if (aData[col.userDateAdded]) {

				// Format  date
				$("td:eq(8)", nRow).text($.datepicker.formatDate(sensus.settings.dateFormatMask, new Date(aData[col.userDateAdded].replace('T',' ').replace(/-/g,'/').split('.')[0])));

			}

			// Role Internacionalization 
			$("td:eq("+ col.userRole + ")", nRow).text(sensus.locale.get('user.role.' + aData[col.userRole]));

			// Add Update User Link
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
				$('td:eq(' + col.id + ')', nRow).html('<a href="user/ajax.updateUserPage.action?userId='  + aData[col.Id] + '"class="alist"><strong class="user-name">' + aData[col.id] + '</strong></a>');
			</sec:authorize>

			if (parseInt(aData[col.userSmartPointCount]) > 0) {
				// Add Update User Link
				var aGroups = $.parseJSON(aData[col.userGroups]);
				var sGroups = '';

				for (iKey in aGroups) {

					if (aGroups.hasOwnProperty(iKey)) {
					
						if(iKey == 0) {
						
							sGroups = '?groups=';
						
						}

						sGroups += aGroups[iKey].id+'|';

					}

				}
				
				$('td:eq(' + col.userSmartPointCount + ')', nRow).css('text-align', 'right')
																 .html('<a href="smartpoint/ajax.list.action'
																	 		+ sGroups + '"class="alist"><strong class="user-name">' 
																	 		+ aData[col.userSmartPointCount] + '</strong></a>');

			} else {

				$('td:eq(' + col.userSmartPointCount + ')', nRow).css('text-align', 'right')
																 .html('<span class="ui-state-disabled">0</span>');	
					
				var oMapCol = $("td:eq("+ col.userSmartPointCount +")", nRow).next();

				oMapCol.html('<span class="ui-state-disabled">' + oMapCol.children().text() + '</span>')
					   .unbind('click');

			}

		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {
		
			$('#checked-count').next().text(sensus.locale.get("table.filter.of")+' '+ iTotal);
			sensus.widgets.datatable.allRowsCount = sensus.widgets.datatable.allRowsCount -1;

		}

	}));

});
</script>
</sec:authorize>