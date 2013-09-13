<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		var tags;
		// Fetch all tags for combobox and table
		<c:choose>
			<c:when test="${empty tags}">
				var inquiryTagResponse = null;
			</c:when>
			<c:otherwise>
				var inquiryTagResponse = ${tags};
			</c:otherwise>
		</c:choose>

		sensus.util.page.initMessaging();

		if (inquiryTagResponse) {

			tags = inquiryTagResponse.tags;

			// Build the combobox with all tags
			sensus.util.page.createOptions($('#select-tag'), tags, "chosen", true);

			if (tags && tags.length) {

				// reload set result info object for table pagination
				inquiryTagResponse.resultsSetInfo.totalRowsAvailable = tags.length;

				// slice tag array with the page size setup on settings for table
				inquiryTagResponse.tags = tags.slice(0, sensus.settings.pageSize);
			}
		}

		$(".chzn-search input:visible").keydown(function(e) {

			if (e.keyCode == '13') {

				sensus.pages.tag.createTag($(this).val().trim());
			}
		});

		// Remove error message when click at select
		$("#select_tag_chzn").click(function() {

		    if ($(".select_tag_chznformError").length > 0) {

		    	$(".formError").remove();
		    }
		});

		// jQuery dataTable setup
		sensus.pages.tag.tagTable = $('#tags-table').dataTable(sensus.widgets.datatable.setTable({

			id : "tags-table",
			sAjaxSource : "api/tag/fetchAll",
			oPreLoadResponse : inquiryTagResponse,
			bPreLoad : true,
			aColumns : [{sId: "Id", bVisible : false},
			            {sId: "Tag", sWidth : "5%", bSortable : true},
			            {sId: "SmartPoints", sWidth : "5%", bSortable : false}],
			oSettings : {
				oRequest : InquiryTagRequest,
				sortEnum : 'BaseSortEnum',
				fnRequest : function() {
					return {};
				},
				sResponseObj : 'tags',
				aColumns : ['id', 'name', 'devicesCount'],
				iDefaultCol : 1,
				oDefaultSort : {
					iCol : 1,
					sSort : 'asc'
				},
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">
					remove : {
						bRemove : true,
						param : {paramName: "tagName", paramValue: "Tag"},
						url : "api/tag/delete",
						text : function (data, i) {

							return sensus.locale.get("tag.page.delete", data[1]);
						},
						success : function(data, i) {

							var oSettings = sensus.pages.tag.tagTable.fnSettings();
							var iStart = null;
							var $select = $("#select-tag");

							if (oSettings._iRecordsDisplay > 1
									&& ((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() == 0)) {

								iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
							}

							sensus.widgets.datatable.reloadTable(sensus.pages.tag.tagTable, iStart);

							$select.find('option[value="' + data[0] + '"]').remove();

							$select.trigger("liszt:updated");

							return sensus.locale.get("tag.page.deleteSuccess", data[1]);
						}
					}
				</sec:authorize>
			},

			fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

				if (aData[oColumn.SmartPoints] > 0) {

					$("td:eq(" + oColumn.SmartPoints + ")", nRow).html(["<a class='alist' href='", sensus.util.page.fnFormatURLService(), "?tag=",
							aData[oColumn.Id], "|'>", $.convertionNumber(aData[oColumn.SmartPoints], false, 0).dvalueConverter, "</a>"].join(""));
				}
			}
		}));

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>
</sec:authorize>