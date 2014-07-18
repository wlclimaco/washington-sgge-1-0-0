<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:when test="${empty precoResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${precoResponse};
    </c:otherwise>
</c:choose>
console.log(viewLoadedObject);
var columns=[];
 var checkboxSelector = new Slick.CheckboxSelectColumn({
      cssClass: "slick-cell-checkboxsel"
    });
	var buttonFormat = function (row, cell, value, columnDef, dataContext) {
		if(row > 0)
			return "<input type='button' value='Detail' class='btn ' />"
		else
			return ""
	}

	//columns & column settings for the grid
	columns.push(checkboxSelector.getColumnDefinition());
	columns[1] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
	columns[2] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
	columns[3] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
	columns[4] = {id:"supermercadoid", name:preco.grid.supermercadoid.title, field:"supermercadoid",  width:135, editable:true, cssClass:"pad-4-left", sortable:true,editor:Slick.Editors.Text},
	columns[5] = {id:"preco", name: preco.grid.preco.title, field:"preco", editor:Slick.Editors.Text};
	columns[6] = {id:"promocao", name: preco.grid.promocao.title, field:"promocao" ,editor:Slick.Editors.Text};
	columns[7] = {id:"precopromo", name: preco.grid.precopromo.title, field:"precopromo",editor:Slick.Editors.Text};
	columns[8] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
	columns[9] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};

//];

//grid options
var options =
{
	<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
	editable: true,
	</sec:authorize>
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

		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		function callInsertWS(_Id)
		{
			onProcDataLoading.notify({});

			if($('#codId').val() != null){
				var oData = callCreateObject($('#codId').val());
				var oData = new qat.model.reqProduto(null, oData, false, false);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateProduto', oData, fill_data_2, process_error);
			}else{
				var oData = callCreateObject();
				var oData = new qat.model.reqProduto(null, oData, false, false);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertProduto', oData, fill_data_2, process_error);
			}
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
				//	var oData = new qat.model.reqProc(null, new qat.model.cadastro(data[aRowChg[a]].pversion,data[aRowChg[a]].psak,data[aRowChg[a]].pcode,data[aRowChg[a]].pdesc,0.0), bList, true);
				var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(data[aRowChg[a]].id,4,data[aRowChg[a]].nome,data[aRowChg[a]].descricao),bList,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateCadastro', oData, fill_data, process_error);
				var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,3),true,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:4,userId:'rod'}}, fill_data, process_error);

			}
		}

		function callDeleteWS(_procId)
		{
			onProcDataLoading.notify({});
		    var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(_procId,4),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/deleteCadastro', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros', {cadastro:{type:4,userId:'rod'}}, fill_data, process_error);
		}

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, true);
			rest_post_call('qat-webdaptive/cadastro/api/refreshBAS', oData, fill_data, process_error);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage,iId)
		{
		    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
			if(iId > 0)
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchProdutoById', {fetchId:iId}, fill_data, process_error)
			else{
				insertProduto_fill_data(null,data);
			}

		}
		function callCreateObject(_id)
		{
			var tmpLength = data.length;
			var oDataPreco =[];
			for (var i=1; i < tmpLength; i++){
				oDataPreco.push(new qat.model.preco(data[i].id, _id, data[i].supermercadoid, null,data[i].preco, data[i].promocao,data[i].precopromo, null, null));
			}
			return new qat.model.produto(_id,
									 null,
									 $('#codbarra').val(),
									 $('#marca').val(),
									 $('#menu').val(),
									 $('#submenu').val(),
									 $('#trimenu').val(),
									 $('#unimed').val(),
									 $('#nomeProd').val(),
									 $('#descr').val(),
									 null,
									 oDataPreco,
									 null);
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"insertproduto");
			onProcDataLoaded.notify({});
		}
		function fill_data_2(procResponse)
		{
			$("#action-produto-dialog").dialog('close');
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {}, fill_data_3, process_error);
			onProcDataLoaded.notify({});
		}
		function fill_data_3(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produto");
			onProcDataLoaded.notify({});
		}
		function fill_data2(datas)
		{
			data = datas;
			onProcDataLoaded.notify({});
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
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">

			"fill_data2"  : fill_data2,
			"callDeleteWS": callDeleteWS,
			"callInsertWS": callInsertWS,
			"callUpdateWS": callUpdateWS,
	//		"callDeletePC": callDeletePC,
	//		"callInsertPC": callInsertPC,
	//		"callUpdatePC": callUpdatePC,
	//		"callPagedFetchPC": callPagedFetchPC,
			"callRefreshWS": callRefreshWS,
			</sec:authorize>
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

<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
$('#precoGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
			ploader.callUpdateWS(aRowChg);
		}
		else
		{
		//	if (validateFields(0))
		//	{
				var data3 = data;
				data.operationSuccess = true;
				ploader.fill_data2(reuse_fill_data(data,data3,"preco"));
				//console.log(data);
		//	}
		}
	}
});

$('#refreshpreco').click(function() {
	ploader.callRefreshWS(135);
});
</sec:authorize>
$('#listpreco').click(function() {
	 ploader.callPagedFetchWS(20,0);
});
</script>
