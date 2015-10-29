<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var data = new Array();
var csgrid;
var viewLoadedObject;

<c:choose>
    <c:when test="${empty countyResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${countyResponse};
    </c:otherwise>
</c:choose>
var columns = [
	{id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30},
    {id:"csak", name: county.grid.cid.title, field:"csak", resizable:false, cssClass:"cell-center", width:75},
	{id:"cdesc", name: county.grid.cdescription.title, field:"cdesc"}
];

var options =
{
	editable: false,
	enableAddRow: false,
	forceFitColumns: true,
	enableCellNavigation: true,
	explicitInitialization: true
};

//Custom RemoteModel Extension for SlickGrid
(function($)
{
	function RemoteModel()
	{
		var onCountyDataLoading = new EventHelper();
		var onCountyDataLoaded = new EventHelper();

		<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
		function callRefreshWS(_i)
		{
			onCountyDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, false);
			rest_post_call('qat-webdaptive/county/api/refreshBAS', oData, fill_data, process_error);
		}
		</sec:authorize>
		function callFetchWS()
		{
		    onCountyDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
		    	var oData = new qat.model.fetchAllRequest(null);
				rest_post_call('qat-webdaptive/county/api/fetchAllBAS', oData, fill_data, process_error);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(countyResponse)
		{
			data = reuse_fill_data(countyResponse,data,"county");
			onCountyDataLoaded.notify({});
		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onCountyDataLoaded.notify({});
		}

		function isCountyDataLoaded()
		{
			if (data == undefined || data == null)
			{
				return false;
			}
			return true;
		}

		return{
			// properties
			"data": data,

			// methods
			"isCountyDataLoaded": isCountyDataLoaded,
			"callFetchWS": callFetchWS,
			<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
			"callRefreshWS": callRefreshWS,
			</sec:authorize>
			// events
			"onCountyDataLoading": onCountyDataLoading,
			"onCountyDataLoaded": onCountyDataLoaded
		};
	};
	$.extend(true, window, { Slick: { Data: { RemoteModel: RemoteModel }}});
})(jQuery);

<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
$('#refreshcountiesBAS').click(function() {
	csloader.callRefreshWS(18);
});
</sec:authorize>
$('#listcountiesBAS').click(function() {
	csloader.callFetchWS();
});
</script>
