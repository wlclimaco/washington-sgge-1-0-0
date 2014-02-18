sensus.pages.cSlider = {
	callInsertWS : function(sUrl,sUrlFetch, oRequest,oRequestFetch,fnCallBackFetch,fnCallBack1) {

//		$.sc.startProgressBar(null,true);

		var alarmsTypeList  = [{cdgrmusc: parseInt(1, 10)}];
		var GrupomuscularRequest    = {grupomusculars : alarmsTypeList};



		fnCallBack1 = function(data){

			if (data.operationSuccess){
				$.sc.startProgressBar(null,true);
		//		$.sc.getJson(sUrlFetch,oRequestFetch, false,fnCallBack1, null, true);
				$.sc.stopGlobalProgressBar();
			}else{
				$.sc.stopGlobalProgressBar();
			}
		};

	$.sc.getJson(sUrl,oRequest, false,fnCallBackFetch, null, true);

		$.sc.stopGlobalProgressBar();
	},

	callUpdateWS : function(sUrl, oRequest,fnCallBackFetch) {

			$.sc.getJson(sUrl,oRequest, false,fnCallBackFetch, null, true);
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
		$.sc.getJson(sUrl, oRequest, false,fnCallback, null, true);


		//onProcDataLoading.notify({});

	}
};