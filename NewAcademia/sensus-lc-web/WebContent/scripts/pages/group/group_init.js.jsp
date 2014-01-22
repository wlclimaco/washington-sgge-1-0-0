<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
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
		sAjaxSource : 'api/academia/fetchall',
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
		aColumns : [
			{sId: "Id",					bVisible : false                   },
			{sId: "academ",				sWidth : "5%",   bSortable : true  },
			{sId: "lograd",				sWidth : "5%",   bSortable : false },
			{sId: "numen", 				sWidth : "5%",   bSortable : true  },
			{sId: "bairr",				sWidth : "5%",   bSortable : true  },
			{sId: "cidade",				sWidth : "5%",   bVisible : true  },
 			{sId: "telef",				sWidth : "5%",   bVisible : true  },
 			{sId: "dataini",			bVisible : true  },
			{sId: "datafin",			bVisible : true  },
			{sId: "atual",				bVisible : true  },
		],

		oSettings :
		{
			oRequest : inquiryAcademiaRequest,
			fnRequest : function(){},
			aColumns      : ['cdacad','academ','lograd','numen','bairr','cidade','telef', 'dataini', 'datafin', 'atual'],
			sResponseObj  : 'academias',
			//aData : [academias],
			bCheckbox     : true,
			iDefaultCol   : 1,
			sDefaultSort  : "asc",
			bSelection    : true
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
			,remove        :
			{
				url   : "api/academia/delete",
				text  : function(data, i)
				{
					return $.sc.locale("groupdelete.warning.nosmartpointsongroup", data[i.academ]);
				},
				oRequest  : 'groupRequest',

				//Create the request to delete group containing the ids of groups selected
				fnRequest : function(data, i)
				{
					var aIds = [];
					aIds.push(data[i.Id]);
					var request = {"selectionPaginationIds": aIds};
					return request;
				},
				success : function(data, i)
				{
					sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
					return $.sc.locale("action.deletegroup.success");
				}
			}
			</sec:authorize>
		},

		fnRowCallback : function(nRow, aData, iDisplayIndex, col)
		{
			//TODO: Format  date
			$("td:eq(" +col.dataini+ ")", nRow).text($.sc.dateFormat(aData[col.dataini], sensus.settings.dateFormatMask));
			$("td:eq(" +col.datafin+ ")", nRow).text($.sc.dateFormat(aData[col.datafin], sensus.settings.dateFormatMask));

			// Add Update Group Link
			<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
				$('td:eq(' + col.groupName + ')', nRow).html('<a href="group/update?groupId=' + aData[col.Id] + '" class="alist"><strong class="group-name">' + aData[col.groupName] + '</strong></a>');
			</sec:authorize>

			$("td:eq(" + col.Id + ")", nRow).text($.convertionNumber(aData[col.Id],false,0).dvalueConverter);

			// Add link to smartpoint list if the amount of lights is greater than zero

		},

		fnInfoCallback  : function( oSettings, iStart, iEnd, iMax, iTotal, sPre )
		{
			//Update the amount of items displayed in list
			$('#checked-count').next().text($.sc.locale("table.filter.of")+' '+ iTotal);
		}
	}));

	$(".group-create").click(function(e) {

		e.preventDefault();
		$.sc.launchActionDialog("academiaDialogInsert", sensus.pages.academia.dialogSettings.academiaDialogInsert);

		return false;
	});

/* 	$('#buttons a').click(function(e) {

			var $this = $(this);

			e.preventDefault();
			if($.contains($this.attr('class'),'edit'))
			{
			}else{
				sensus.widgets.datatable.removeDialog(datatable);
			}
			console.log($this.attr('class'));
	}); */

	$.sc.stopGlobalProgressBar();
});
</script>
</sec:authorize>