<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">

/**
 * @namespace sensus.pages.search
 * @description The init namespace for the Search Page.
 */

/**
 * @fileoverview Initializes the search page.
 *
 * @author Cristiane Cobo
 */
$(document).ready(function() {

	$(".tabs").find("li").find("a:contains('Saved')").click(function(e)
	{
		e.preventDefault();
	});

	function removeDuplicates(inputArray)
	{
		var i;
		var len = inputArray.length;
		var outputArray = [];
		var temp = {};

		for (i = 0; i < len; i++)
		{
			temp[inputArray[i]] = 0;
		}
		for (i in temp)
		{
			outputArray.push(i);
		}
		return outputArray;
	}

	sensus.pages.saved.savedTable = $('#saved-table').dataTable(sensus.widgets.datatable.setTable({
		id : "saved-table",
		sAjaxSource : "api/search/fetch",
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
		aColumns :
		[
			{sId: "Id",           bVisible : false                    },
			{sId: "Name",         sWidth : "12%",   bSortable : true  },
			{sId: "Description",  sWidth : "30%",   bSortable : false },
			{sId: "Filter",       sWidth : "40%",   bSortable : false },
			{sId: "Added",        sWidth : "11%",   bSortable : true  },
			{sId: "Delete",       bVisible : false                    },
			{sId: "Url",          bVisible : false                    }
		],
		oSettings :
		{
			oRequest      : inquiryPaginationRequest,
			fnRequest     : function()
			{

					return new SearchMeter([]);

			},
			aColumns      : ['id', 'name', 'description', 'searchParameters', 'createDate','',''],
			sResponseObj  : 'customSearchList',
			iDefaultCol   : 1,
			remove        :
			{
				bRemove  : true,
				url      : "api/search/delete",
				text     : function(data, i)
				{
					return $.sc.locale("searchdelete.warning.genericmessage", data[i.Name]);
				},
				oRequest : 'customSearchRequest',
				fnRequest : function(data, i)
				{

					var request = new customSearchRequest(data[i.Id], null, null, null, null, null);
					return request;

				},
				success  : function(data, i)
				{
					sensus.widgets.datatable.reloadTable(sensus.pages.saved.savedTable);
					return $.sc.locale("action.deletesearch.success");
				}
			}
		},
		fnRowCallback : function(nRow, aData, iDisplayIndex, col)
		{

			/** Format  date */
			$("td:eq(" +col.Added+ ")", nRow).text($.sc.dateFormat(aData[col.Added], sensus.settings.dateFormatMask));

			/* Add Description Parameters */
			$('td:eq(' + col.Description + ')', nRow).html(aData[col.Description]);

			var oParameters      = aData[col.Filter],
				sHtmlParameters  = '<ul class="filter-container">',
				sValue           = "";
				aParameters 	 = [];

			for (var iKey in oParameters)
			{

				if (oParameters.hasOwnProperty(iKey)
						&& oParameters[iKey].propertyEnum.toLowerCase() != 'smartpoint_column'
						&& oParameters[iKey].propertyEnum.toLowerCase() != 'smartpoint_filter'
						&& oParameters[iKey].name != null)
				{

					sValue = decodeURIComponent(escape(oParameters[iKey].name));

					aParameters.push(oParameters[iKey].propertyEnum);


					var currentValue = oParameters[iKey].propertyEnum.toLowerCase();
					var titleValue = $.sc.locale('commons.pages.' + currentValue);

					if (currentValue == "date_added_before" || currentValue == "date_added_after")
					{

						 sValue  = $.sc.dateFormat( oParameters[iKey].value, sensus.settings.dateFormatMask, null , false);

					}

					/** Get information of the field value of the object */
					if (currentValue == 'pole_id')
					{
						sValue = oParameters[iKey].value;
					}
					else if(currentValue == 'sort')
					{
						sValue = oParameters[iKey].value.split(' ')[1].replace('asc', ' <img src="images/icn-sort-up.gif">')
														.replace('desc', ' <img src="images/icn-sort-down.gif">');

					}
					else if (currentValue == 'dimmable')
					{

						if(oParameters[iKey].name == 'true')
						{

							sValue = $.sc.locale('smartpoint.filter.lamptype.dimmable.yes');

						}
						else if(oParameters[iKey].name == 'false')
						{

							sValue = $.sc.locale('smartpoint.filter.lamptype.dimmable.no');

						}
						else
						{

							sValue = $.sc.locale(oParameters[iKey].name);

						}

					}
					else if (currentValue == 'all_warnings' || currentValue == 'all_alarms')
					{

						sValue = $.sc.locale(oParameters[iKey].name);

					}
					else if (currentValue == 'protected')
					{

						sValue = 'Unlocked';

						if (oParameters[iKey].value == 'true')
						{

							sValue = 'Locked';

						}

					}
					else if (currentValue == 'lifecycle_state' )
					{

						switch(sValue)
						{
							case '3'  : sValue = 'Maintenance'; break;
							case '4'  : sValue = 'Deactivated'; break;
							case '5'  : sValue = 'Active';      break;
							case '6'  : sValue = 'Unknown';     break;
						}

					}
					else if (currentValue  == 'alerts')
					{
						switch(sValue)
						{
							case '2'  : sValue = 'Warning';  break;
							case '1'  : sValue = 'Alarm';    break;
							case '4'  : sValue = 'Override'; break;
						}
					}
					else if (currentValue == 'eco_mode')
					{

						switch(sValue)
						{
							case '50_100' : sValue = $.sc.locale('smartpoint.filter.ecomodeEconomy'); break;
							case '15_50' : sValue = $.sc.locale('smartpoint.filter.ecomodeValue'); break;
							case '0_15' : sValue = $.sc.locale('smartpoint.filter.ecomodeSecurity'); break;
						}

					}

					if (sValue != undefined && oParameters[iKey].labelKey != "sensus.mlc.property.sort")
					{

						sHtmlParameters += '<li id="filter-active" class="read_only"><span class="title">' + titleValue
											+ '</span><span class="type-status">' + sValue + '</span></li>';
					}
				}
			}

			aParameters = removeDuplicates(aParameters);
			var sUrl = '';
			for (var i in aParameters)
			{


				if (aParameters.hasOwnProperty(i))
				{

					var sFilter = sensus.pages.saved.config[aParameters[i]];
					sFilter = sFilter.replace("date_added_before","end");
					sFilter = sFilter.replace("date_added_after","start");
					sUrl += '&'+sFilter;

					var aFilters = ($.grep(oParameters, function(e) { return e.propertyEnum == aParameters[i]; }));

					for (var k in aFilters)
					{

						if (aFilters.hasOwnProperty(k))
						{

							if ((aFilters[k].propertyEnum === 'DATE_ADDED_AFTER') || (aFilters[k].propertyEnum === 'DATE_ADDED_BEFORE'))
							{

								sUrl.replace("DATE_ADDED_BEFORE","end");
								sUrl.replace("DATE_ADDED_AFTER","start");

								sUrl += $.sc.dateFormat(aFilters[k].value, null , null , false);

							}
							else
							{

								if(sFilter == 'sort=')
								{

									var aSort = aFilters[k].value.split(' ');
									sUrl += aSort[0]+'|'+aSort[1];

								}
								else
								{

									sUrl += escape(aFilters[k].value);

								}
							}

							if(sFilter != 'street' && sFilter != 'city' && sFilter != 'zip' && sFilter != 'query=12|' && sFilter != 'query=36|')
							{

								sUrl += '|';

							}
						}
					}
				}
			}

			/** Add Saved Link */
			$('td:eq(' + col.Name + ')', nRow).wrapInner('<strong><a class="afilter" href="light?sd=true&initialLoad=false'+sUrl+'"class="edit"></a></strong>');


			$('td:eq(' + col.Filter + ')', nRow).html(sHtmlParameters + '</ul>');
		}
	}));

	$.sc.stopGlobalProgressBar();
});
</script>