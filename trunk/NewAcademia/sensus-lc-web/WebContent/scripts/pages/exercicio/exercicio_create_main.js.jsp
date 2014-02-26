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

callPagedFetchWS : function(sUrl, oRequest,fnCallback)
{



	//$.ajaxValidator.fnDoCall("api/academia/fetchall", inquiryAcademiaRequest, false, null,null,true);
	$.sc.getJson("api/grupoMuscular/fetchall", {"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"grupomusculars":[{"createuser":"superuser","tenantid":1,"userid":1}]}, false,fnCallback, null, true);


	//onProcDataLoading.notify({});

}

};
</script>
