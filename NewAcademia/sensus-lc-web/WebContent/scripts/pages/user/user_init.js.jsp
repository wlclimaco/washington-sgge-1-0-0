<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
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

	/* Initialize action buttons */
	$.sc.menuPlug(sensus.pages.user, {});

	/* Initialize Chosen plugin */
	$(".chzn-select").chosen();

	/** * jQuery dataTable setup ** */
	sensus.pages.user.userTable = $('#user-table').dataTable(sensus.widgets.datatable.setTable({

		id           : "user-table",
		sAjaxSource  : "api/user/fetchall",
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
			{sId: "Id",                   bVisible : false                 },
			{sId: "id",                   sWidth : "5%", bSortable : true  },
			{sId: "firstName",            sWidth : "5%", bSortable : true  },
			{sId: "userRole",             sWidth : "5%", bSortable : true  },
			{sId: "userEmail",            sWidth : "5%", bSortable : true  },
			{sId: "userLightCount",  	  sWidth : "5%", bSortable : true  },
			{sId: "userDateAdded",        sWidth : "5%", bSortable : true  },
			{sId: "userLatitude",         sWidth : "5%", bVisible : false  },
			{sId: "userLongitude",        sWidth : "5%", bVisible : false  },
			{sId: "userGroups",           sWidth : "5%", bVisible : false  },
			{sId: "userGroupStatusEnum",  bVisible : false  }
		],
		oSettings   : {
			oRequest      : inquiryUserRequest,
			fnRequest     : function() { },
			aColumns      : ['id','userName','fullName','role','email','totalLights','modifyDate','latitude','longitude','groups[object]','precedence'],
			sResponseObj  : 'users',
			bCheckbox     : true,
			iDefaultCol   : 1,
			bSelection    : true,
			iMapCol       : 7
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			,remove        : {
				url   : "api/user/delete",
				text  : function(data, i) {
					return $.sc.locale("user.validation.deleteUser", data[i.id]);
				},
				oRequest  : 'userRequest',
				fnRequest : function(data, i) {

					var aIds = [];
					aIds.push(data[i.Id]);
					var request = {"selectionPaginationIds": aIds};
					return request;

				},
				success : function(data, i) {

					sensus.widgets.datatable.reloadTable(sensus.pages.user.userTable);
					return $.sc.locale("action.deleteuser.success");

				}
			}
			</sec:authorize>
		},

		fnRowCallback : function(nRow, aData, iDisplayIndex, col) {

			// Remove checkbox and delete button preventing User to delete your self
			if($('td:eq(' + col.Id + ')', nRow).html() == sensus.settings.userContext.id)
			{
				$('td:eq(0)', nRow).html('');
				$('td:eq(12)', nRow).unbind('click').html('');
			}

			if (aData[col.userDateAdded])
			{
				// Format  date
				$("td:eq(8)", nRow).text($.sc.dateFormat(aData[col.userDateAdded], sensus.settings.dateFormatMask));
			}

			// Role Internacionalization
			$("td:eq("+ col.userRole + ")", nRow).text($.sc.locale('user.role.' + aData[col.userRole]));

			// Add Update User Link
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
				$('td:eq(' + col.id + ')', nRow).html('<a href="user/update?userId=' + aData[col.Id] + '" class="alist"><strong class="user-name">' + aData[col.id] + '</strong></a>');
			</sec:authorize>

			if (parseInt(aData[col.userLightCount]) > 0)
			{
				// Add Update User Link
				var aGroups = $.parseJSON(aData[col.userGroups]);
				var sGroups = '';

				if (aGroups.length == 0)
				{
					sGroups = '?total=true';
				}
				else
				{
					for (iKey in aGroups)
					{
						if (aGroups.hasOwnProperty(iKey))
						{
							if(iKey == 0)
							{
								sGroups = '?groups=';
							}

							sGroups += aGroups[iKey].id + '|';
						}
					}
				}

				$('td:eq(' + col.userLightCount + ')', nRow).css('text-align', 'right')
																 .html('<a href="light'
																	 		+ sGroups + '"class="alist"><strong class="user-name">'
																	 		+ aData[col.userLightCount] + '</strong></a>');
			}
			else
			{
				$('td:eq(' + col.userLightCount + ')', nRow).css('text-align', 'right')
																 .html('<span class="ui-state-disabled">0</span>');

				var oMapCol = $("td:eq("+ col.userLightCount +")", nRow).next();

				oMapCol.html('<span class="ui-state-disabled">' + oMapCol.children().text() + '</span>')
					   .unbind('click');
			}

			if(aData[col.userGroupStatusEnum])
			{
				$(nRow).addClass(aData[col.userGroupStatusEnum].toLowerCase());
			}
		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre )
		{
			$('#checked-count').next().text($.sc.locale("table.filter.of")+' '+ iTotal);
		}

	}));

	$.sc.stopGlobalProgressBar();
});
</script>
</sec:authorize>