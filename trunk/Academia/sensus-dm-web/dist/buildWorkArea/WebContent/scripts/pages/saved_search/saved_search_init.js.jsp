<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	$(document).ready(function() {

		var devicelistUrl = sensus.util.page.fnFormatURLService();

		sensus.util.page.initMessagingTabs();

		$("#tab-search a").attr("href", $("#menu-smartpoint").attr("href"));

		// jQuery Data Table for Saved Search
		sensus.pages.search.savedTable = $('#saved-table').dataTable(sensus.widgets.datatable.setTable({

			id : "saved-table",

			sAjaxSource : "api/search/fetch",

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

			aColumns : [ {sId : "Id", bVisible : false},
			             {sId : "name",        sWidth : "5%", bSortable : true},
			             {sId : "description", sWidth : "5%", bSortable : false},
			             {sId : "filters",     sWidth : "5%", bSortable : false},
			             {sId : "date",        sWidth : "5%", bSortable : true},
			             {sId : "column",      sWidth : "5%", bSortable : false, bVisible : false}
			],

			oSettings : {
				oRequest 		: InquiryPaginationRequest,
				sortEnum 		: 'BaseSortEnum',
				fnRequest 		: function() {},
				sResponseObj 	: "customSearches",
				aColumns	 	: ["id", "name", "description", "searchParameters",
				        	 	   "modifyDate[fn($.date.dateFormat)]", "modifyDate"],
				iDefaultCol 	: 1,
				sDefaultSort 	: "asc"
				<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')">,
					remove : {
						bRemove : true,
						url : "api/search/delete",
						text : function (data, i) {

							return sensus.locale.get("commons.pages.deletePermanently", data[i.name]);
						},
						success : function (data, i) {

							var oSettings = sensus.pages.search.savedTable.fnSettings(),
								iStart = null;

							if ((oSettings._iRecordsDisplay - 1) % $('.dataTables_length').find('select').val() === 0) {

								iStart = (oSettings._iRecordsDisplay - 1) - oSettings._iDisplayLength;
							}

							sensus.widgets.datatable.reloadTable(sensus.pages.search.savedTable, iStart);
							return sensus.locale.get("searchSave.page.deleteSuccess", data[i.name], "1");
						}
					}
				</sec:authorize>
			},

			fnRowCallback : function(nRow, aData, iDisplayIndex, oColumn) {

				var oFilledFilters = sensus.pages.search.fillFilters(aData[oColumn.filters]);

				$("td:eq(" + oColumn.name + ")", nRow).html(
						$("<a class='alist' href='" + devicelistUrl + "?saved=true&name="
								+ escape(aData[oColumn.name]) + "&"
								+ decodeURI(oFilledFilters.url) + "'>"
								+ aData[oColumn.name] + "</a>"));

				// Creating the lines of filters column
				if (aData[oColumn.filters]) {

					$("td:eq(" + oColumn.filters + ")", nRow).html(oFilledFilters.filters);
				}
			},

			fnDrawCallback : function(setting, oColumn) {

				// Hide the footer tables
				$("#saved-table_wrapper").addClass("content-inner");
			}
		}));

		sensus.util.page.stopGlobalProgressBar();
	});
	</script>
</sec:authorize>