<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var data = new Array();
var pgrid;
var gridPager;
var pagingData = new qat.model.pageData(null,null,null,null);
var id = $("div[id*='tabs']" ).attr("id");
var viewLoadedObject;

//loads object if being served via controller
<c:choose>
    <c:when test="${empty academiaResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${academiaResponse};
    </c:otherwise>
</c:choose>

//columns & column settings for the grid
var columns = [
	{id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30},
	{id:"action", name: "company.locality", field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML},
    {id:"psak", name: "company.locality", field:"psak", resizable:false, cssClass:"cell-center", width:75},
    {id:"pcode", name: "company.locality", field:"pcode", editor:Slick.Editors.Text, validator:requiredFieldValidator},
	{id:"pdesc", name: "company.locality", field:"pdesc", width:250, editor:Slick.Editors.Text, validator:requiredFieldValidator},
	{id:"pprice", name: "company.locality", field:"pprice", cssClass:"cell-center", resizable:false, width:150},
	{id:"pversion", name: "company.locality", field:"pversion", resizable:false, cssClass:"cell-center", width:135}
];

//grid options
var options =
{

	editable: true,

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
		var onProcDataLoading = new EventHelper();
		var onProcDataLoaded = new EventHelper();

		function callInsertWS()
		{
			onProcDataLoading.notify({});
		    var oData = new qat.model.reqProc(null, new qat.model.procedure(0,0,data[0].pcode,data[0].pdesc,0.0),true,true);
			rest_post_call('qat-webdaptive/procedure/api/insertBAS', oData, fill_data, process_error);
		}

		function callUpdateWS()
		{
			var tempLength = aRowChg.length;
			if (tempLength > 0)
			{
				onProcDataLoading.notify({});
			}

			var bList = true;
			for(a=0; a < (tempLength); a++)
			{
				if (a==(tempLength-1))
				{
					bList = true;
				}
				else
				{
					bList = false;
				}

				var oData = new qat.model.reqProc(null, new qat.model.procedure(data[aRowChg[a]].pversion,data[aRowChg[a]].psak,data[aRowChg[a]].pcode,data[aRowChg[a]].pdesc,0.0), bList, true);
				rest_post_call('qat-webdaptive/procedure/api/updateBAS', oData, fill_data, process_error);
			}
		}

		function callDeleteWS(_procId)
		{
			onProcDataLoading.notify({});
		    var oData = new qat.model.reqProc(null, new qat.model.procedure(0,_procId,"","",0.0),true,true);
			rest_post_call('qat-webdaptive/procedure/api/deleteBAS', oData, fill_data, process_error);
		}

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, true);
			rest_post_call('qat-webdaptive/procedure/api/refreshBAS', oData, fill_data, process_error);
		}


		function callPagedFetchWS(_iPageSize, _iStartPage)
		{

		//function fill_data(procResponse)
		var fnCallBack = function(procResponse)
		{
		debugger;
			data = reuse_fill_data(procResponse,data,"procedure");
			console.log(data);
			onProcDataLoaded.notify({});
		}

		    onProcDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				rest_post_call('api/academia/fetchall', {startRow:0,endRow:0,pageSize:25,sortExpressions:[{field:"NAME",direction:"Ascending"}],academias:[{createuser:"superuser",tenantid:1,userid:1}]}, fnCallBack, process_error);
				//$.sc.getJson('api/academia/fetch',{}, false, null);

				//$.sc.getJson('api/academia/fetchall',{startRow:0,endRow:0,pageSize:25,sortExpressions:[{field:"NAME",direction:"Ascending"}],academias:[{createuser:"superuser",tenantid:1,userid:1}]}, false, fill_data);
			//console.log(fill_data);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}



		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onProcDataLoaded.notify({});
		}

		function isProcDataLoaded()
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
			"isProcDataLoaded": isProcDataLoaded,
			"callPagedFetchWS": callPagedFetchWS,
			"callDeleteWS": callDeleteWS,
			"callInsertWS": callInsertWS,
			"callUpdateWS": callUpdateWS,
			"callRefreshWS": callRefreshWS,

			// events
			"onProcDataLoading": onProcDataLoading,
			"onProcDataLoaded": onProcDataLoaded
		};
	};
	$.extend(true, window, { Slick: { Data: { RemoteModel: RemoteModel }}});
})(jQuery);

function requiredFieldValidator(value)
{
	if (wd.core.isEmpty(value))
	{
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
		return {valid:false, msg:null};
	}
	else
	{
		return {valid:true, msg:null};
	}
};

function validateFields(rowValue)
{
	if (wd.core.isEmpty(data[rowValue].pcode))
	{
		pgrid.gotoCell(rowValue,3,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if (wd.core.isEmpty(data[rowValue].pdesc))
	{
		pgrid.gotoCell(rowValue,4,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else
	{
		return true;
	}
	return false;
};

$('#procGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
			ploader.callUpdateWS(aRowChg);
		}
		else
		{
			if (validateFields(0))
			{
				ploader.callInsertWS();
			}
		}
	}
});

$('#refreshproc').click(function() {
	ploader.callRefreshWS(135);
});

$('#listproc').click(function() {
	 ploader.callPagedFetchWS(20,0);
});
</script>
