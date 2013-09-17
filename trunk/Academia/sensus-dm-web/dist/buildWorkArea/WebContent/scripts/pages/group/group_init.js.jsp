<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

<script type="text/javascript">
$(document).ready(function() {

	var $csv = $("#csv");
	var sMessage = $.address.parameter("message");
	var sType = "confirm";

	// Initialize action buttons
	sensus.util.page.menuPlug(sensus.pages.group);

	// Initialize Messagings
	sensus.util.page.initMessaging();

	if (sMessage) {

		switch (sMessage) {

		case "INSERT" :

			sMessage = sensus.locale.get("action.addsgroup.success", unescape($.address.parameter("groupName")));

			break;

		case "UPDATE" :

			sMessage = sensus.locale.get("action.updategroup.success", unescape($.address.parameter("groupName")));

			break;

		default :

			sMessage = unescape(sMessage);
			sType = "error";
		}

		sensus.util.page.showMessage("messaging-main", sMessage, sType);

		// Clear parameters
		$.fn.pageLoader.parameters({check : null, message : null, groupName : null});
	}

	// To Generate the archive CSV
	sensus.util.exportcsv.setGenerateCSVEvent({

		url : "api/export/generateGroupCSV",

		$element : $csv,

		getGenerateRequestCSV : function() {

			// Get Request populate with current filters
			var oRequest = new InquiryGroupRequest(sensus.pages.group.fnRequestAction());

			// Default Request Parameters
			oRequest.sortExpressions = sensus.util.page.getSortExpression(sensus.pages.group.groupTable);

			return oRequest;
		}
	});

	// Group jQuery Data Table
	sensus.pages.group.groupTable = $("#group-table").dataTable(sensus.widgets.datatable.setTable({

		id : "group-table",
		sAjaxSource : "api/group/fetchAll",
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
		bPreLoad : true,
		aColumns : [
			{sId: "Id",  bVisible : false},
			{sId: "groupName", sWidth : "5%", bSortable : true},
			{sId: "groupDescription", sWidth : "5%", bSortable : false},
			{sId: "deviceType", sWidth : "5%", bSortable : true},
			{sId: "groupType", sWidth : "2%", bSortable : true},
			{sId: "groupSmartPointCount", sWidth : "5%", bSortable : true},
			{sId: "groupDateAdded", sWidth : "5%", bSortable : true},
			{sId: "groupRemoveGroup", sWidth : "5%", bSortable : false},
			{sId: "hanDeviceType", bVisible : false}
		],

		oSettings : {
			oRequest : InquiryGroupRequest,
			sortEnum : 'GroupColumnEnum',
			fnRequest : sensus.pages.group.fnRequestAction,
			sResponseObj : 'groups',
			aColumns : ['id', 'name', 'description', 'deviceType', 'groupTypeEnum', 'devicesCount', 'createDate', '', 'hanDeviceType'],
			iMapCol : 6,
			aSelectedParameters : ["groupName"],
			iDefaultCol : 1,
			bCheckbox : true,
			oDefaultSort : {
				iCol : 1,
				sSort : 'asc'
			}
			<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">,
				edit : {
					col : "groupName",
					url : "group/update",
					bSaveSession : true
				}
			</sec:authorize>
		},

		$exportCSVElement : $csv,

		fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

			var _get = sensus.locale.get;

			//	Device Type
			if (sensus.settings.serviceType == sensus.constants.services.electric.name) {

				$("td:eq(" + oColumn.deviceType + ")", nRow).html(
						_get("commons.pages." + (aData[oColumn.hanDeviceType] || aData[oColumn.deviceType])));

			} else {

				$("td:eq(" + oColumn.deviceType + ")", nRow).hide();
			}

			// Date Added Column, apply date format
			$("td:eq(" + oColumn.groupDateAdded + ")", nRow).text($.date.dateFormat(aData[oColumn.groupDateAdded]));

			// Column Type, Internacionalization
			$("td:eq(" + oColumn.groupType + ")", nRow).html(_get("group.page." + aData[oColumn.groupType]));

			// Devices Columns, apply link for Device Page
			if (aData[oColumn.groupSmartPointCount] > 0) {

				var deviceType = aData[oColumn.deviceType];
				var aLink;

				if (deviceType) {

					aLink = ["?device_type=", deviceType, "|&group=", aData[oColumn.Id], "|"];

					var deviceSubType = aData[oColumn.hanDeviceType];

					if (deviceSubType) {

						aLink.push("&device_subtype=", deviceSubType, "|");
					}

				} else {

					aLink = ["?group=", aData[oColumn.Id], "|"];
				}

				$("td:eq(" + oColumn.groupSmartPointCount + ")", nRow).html(
						["<a class='alist' href='", sensus.util.page.fnFormatURLService(), aLink.join(""), "'>",
						 $.convertionNumber(aData[oColumn.groupSmartPointCount], false, 0).dvalueConverter, "</a>"].join(""))
					.addClass("num");

			} else {

				$("td:eq(" + oColumn.groupSmartPointCount + ")", nRow).addClass("num");

				// Map Columns, if 0 devices, remove map dialog link
				var oMapCol = $("td:eq(" + oColumn.groupSmartPointCount + ")", nRow).prev();

				oMapCol.html("<span class='ui-state-disabled'>" + oMapCol.children().text() + "</span>").unbind("click");
			}

			<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">

				$("td:eq(" + oColumn.groupRemoveGroup + ")", nRow).html(
						["<a class='delete delete_dialog delete_col' href=''>", _get("table.action.delete"), "</a>"].join(""));

			</sec:authorize>
		},

		fnFooterCallback : function(nRow, aaData, iStart, iEnd, aiDisplay, iRecordsDisplay) {

			var oBlankslate, _get;

			if (aaData.length == 0) {

				oBlankslate = $(".blankslate");
				_get = sensus.locale.get;

				if ($(".filter-container span.remove").length) {

					oBlankslate.find("h5").text(_get("commons.pages.blankResult"));
					oBlankslate.find("p").text(_get("commons.pages.removefilters"));

				} else {

					oBlankslate.find("h5").text(_get("commons.pages.blankGroups"));
					oBlankslate.find("p").text(_get("commons.pages.blankGroups2"));
				}
			}

			if (sensus.settings.serviceType != sensus.constants.services.electric.name) {

				$("#device_type").parent().hide();
			}
		}

	}));

	// Column Delete Event
	<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">

		$("#group-table").delegate(".delete.delete_dialog.delete_col", "click", function (e) {

			var $oRow = $(this).parents("tr");
			var iId = $oRow.find("td:eq(1)").text();
			var sGroupName = $oRow.find("td:eq(2)").text();

			e.preventDefault();

			sensus.pages.group.aSelectedGroups = [{id : iId, groupName: sGroupName}];

			sensus.util.actiondialog.launchActionDialog("deleteGroups", sensus.pages.group.dialogSettings["deleteGroups"]);
		});

	</sec:authorize>

	$("#buttonCreateGroupPage").click(function(e) {

		e.preventDefault();

		sensus.util.session.create({
			key : "SelectedFilters",
			value : $.fn.pageLoader.queryString()
		});
	});

	<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

		<c:choose>
			<c:when test="${not empty refresh}">
				var oPreLoad = "refresh";
			</c:when>
			<c:when test="${empty filters}">
				var oPreLoad = null;
			</c:when>
			<c:otherwise>
				var oPreLoad = ${filters};
			</c:otherwise>
		</c:choose>

		sensus.pages.group.fnFillFilter(oPreLoad);

	</sec:authorize>

	sensus.util.page.stopGlobalProgressBar();

});
</script>
</sec:authorize>