<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.group
 * @description The main namespace for the Group Page.
 */

/**
 * @fileoverview Defines the core functionality of the Group page.
 * @author Anke Doerfel-Parker
 */

/**
 * The main namespace for the Group Page.
 */
sensus.pages.grupoMuscular2 = {
callInsertWS : function(sUrl, oRequest) {
/* 	onProcDataLoading.notify({});
	var oData = new qat.model.reqProc(null, new qat.model.procedure(0,0,data[0].pcode,data[0].pdesc,0.0),true,true);
	rest_post_call('qat-webdaptive/procedure/api/insertBAS', oData, fill_data, process_error); */

var fnCallBack = function(data){

	if (data.operationSuccess){
		sensus.pages.grupoMuscular2.callPagedFetchWS();
	//	renderTable();
	}
};

//		sIdUrl = sensus.widgets.datatable.isAllRows && nIds.length <= 0 ? "" : nIds;

	$(this).dialog('close');
	$.sc.startProgressBar(null,true);


	$.sc.getJson('api/academia/insert',oRequest, false, fnCallBack);
	//sUrl.render();
},

callUpdateWS : function(sUrl, oRequest) {
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
},

callDeleteWS : function(sUrl, oRequest)
{
	onProcDataLoading.notify({});
	var oData = new qat.model.reqProc(null, new qat.model.procedure(0,_procId,"","",0.0),true,true);
	rest_post_call('qat-webdaptive/procedure/api/deleteBAS', oData, fill_data, process_error);
},
callRefreshWS : function(sUrl, oRequest)
{
	onProcDataLoading.notify({});
	var oData = new qat.model.refreshRequest(null, _i, true, true);
	rest_post_call('qat-webdaptive/procedure/api/refreshBAS', oData, fill_data, process_error);
},
callPagedFetchWS : function(sUrl, oRequest,fnCallback)
{



	//$.ajaxValidator.fnDoCall("api/academia/fetchall", inquiryAcademiaRequest, false, null,null,true);
	$.sc.getJson("api/grupoMuscular/fetchall", {"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"grupomusculars":[{"createuser":"superuser","tenantid":1,"userid":1}]}, false,fnCallback, null, true);


	//onProcDataLoading.notify({});

}

};
</script>
