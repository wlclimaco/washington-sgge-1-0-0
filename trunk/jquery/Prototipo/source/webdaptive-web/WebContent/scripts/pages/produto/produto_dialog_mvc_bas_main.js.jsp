<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var data = new Array();
var pgrid;
var gridPager;
var valueGlobal;
var pagingData = new qat.model.pageData(null,null,null,null);
var viewLoadedObject;

var columns=[];
 var checkboxSelector = new Slick.CheckboxSelectColumn({
      cssClass: "slick-cell-checkboxsel"
    });
	var buttonFormat = function (row, cell, value, columnDef, dataContext) {

		return "<input type='button' value='Editar' class='btn "+value+"' />"

	}


    columns.push(checkboxSelector.getColumnDefinition());
//columns & column settings for the grid
columns[1]  = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
columns[2]  = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[3]  = {id:"codBarra", name:produto.grid.codBarra.title, field:"codBarra",  width:135, editable:true, cssClass:"pad-4-left", sortable:true},
columns[4]  = {id:"nome", name: produto.grid.nome.title, field:"nome"};
columns[5]  = {id:"unimed", name: produto.grid.unimed.title, field:"unimed"};
columns[6]  = {id:"marca", name:produto.grid.marca.title, field:"marca",  width:135, editable:true, cssClass:"pad-4-left", sortable:true},
columns[7]  = {id:"supermercadoId", name: produto.grid.supermercadoId.title, field:"supermercadoId"};
columns[8]  = {id:"preco", name: produto.grid.preco.title, field:"preco",editor:Slick.Editors.Text};
columns[9]  = {id:"promocao", name: produto.grid.promocao.title, field:"promocao",editor:Slick.Editors.Text};
columns[10] = {id:"precopro", name: produto.grid.precopro.title, field:"precopro",editor:Slick.Editors.Text};
columns[11] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[12] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};


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

		function callUpdateWSProd()
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
				var oPreco = new qat.model.preco(null, data[aRowChg[a]].id, data[aRowChg[a]].supermercadoId, null,data[aRowChg[a]].preco,data[aRowChg[a]].promocao,data[aRowChg[a]].precopro, null, null);
				var oData = new qat.model.reqProduto(null, new qat.model.produto(1,1, null,null,null,null,null,null,null,null,null,[oPreco], null,11),bList,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertProduto', oData, fill_data, process_error);

				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {produto:{tabela:11,precos:[{supermercadoid:{superId:parseInt($('#supId').val())}}],userId:'rod'}}, fill_data, process_error);

			}

		}


		function callRefreshWSProd(_i)
		{
			onProcDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {produto:{userId:'rod'}}, fill_data, process_error);
		}
		</sec:authorize>

		function callPagedFetchWSProd(_iPageSize, _iStartPage)
		{
			//tabval = ($.address.parameter('supId'));
			$('#supId').val(tabval);
		    onProcDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				//rest_post_call('qat-webdaptive/cadastro/api/fetchByRequestBAS', oData, fill_data, process_error);
			    rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {produto:{tabela:11,precos:[{supermercadoid:{superId:parseInt($('#supId').val())}}],userId:'rod'}}, fill_data, process_error);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}
/*
		function exportCSVProd(_iPageSize, _iStartPage)
		{
		    onProcDataLoading.notify({});
			var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {produto:{tabela:11,precos:[{supermercadoid:{superId:parseInt($('#supId').val())}}],userId:'rod'}}, fill_dataCSV, process_error);
		}
*/
		function openProdutosProd(value){
			dom = "<div class='id'>" + value + "</div>";
		//	openDialogCadastro({produto:{tabela:3,menu:{id:parseInt(value),userId:'rod'},userId:'rod'}});
			valueGlobal = parseInt(value);
			$('#tableId').val(3);
			debugger;
			data =  qat.model.supermercado.pages.openProdutos(value,data,process_error);
		}
		function fill_data(procResponse)
		{
			onProcDataLoading.notify({});
			data = reuse_fill_data(procResponse,data,"produtoDialog");
			onProcDataLoaded.notify({});
		}
		function fill_data2(procResponse)
		{
			onProcDataLoading.notify({});
			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			onProcDataLoaded.notify({});
		}
		function fill_dataCSV(procResponse)
		{
			onProcDataLoading.notify({});
			data = reuse_fill_data(procResponse,data,"produtoDialog");
			qat.model.supermercado.pages.JSONToCSVConvertor(data, "Vehicle Report", true);
			onProcDataLoaded.notify({});

		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onProcDataLoaded.notify({});
		}

		function isProcDataLoadedProd()
		{
			if (data == undefined || data == null)
			{
				return false;
			}
			return true;
		}


		function openDialogCadastro(oData)
		{

		    onProcDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',oData , fill_data2, process_error);

		}
		function fill_data2(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			onProcDataLoaded.notify({});
		}
		function exportCSVProd()
		{
			debugger;
		    onProcDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',{produto:{tabela:2,marca:{id:parseInt(valueGlobal),userId:'rod'},userId:'rod'}} , fill_dataCSV, process_error);
		}
		function fill_dataCSV(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			qat.model.supermercado.pages.JSONToCSVConvertor(data, "Supermercado", true);
			onProcDataLoaded.notify({});

		}

		return{
			// properties
			"data": data,

			// methods
			"isProcDataLoadedProd": isProcDataLoadedProd,
			"callPagedFetchWSProd": callPagedFetchWSProd,
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			"callUpdateWSProd": callUpdateWSProd,
			"exportCSVProd":exportCSVProd,


			"openProdutosProd":openProdutosProd,
			"callRefreshWSProd": callRefreshWSProd,
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
$('#prodGridDialog').keyup(function(e)
{

	if (e.keyCode == 13)
	{
			ploaderDiaprod.callUpdateWSProd(aRowChg);
	}
});

$('#refreshprodDialog').click(function() {
	ploaderDiaprod.callRefreshWSProd(135);
});
</sec:authorize>
$('#listprodDialog').click(function() {
	 ploaderDiaprod.callPagedFetchWSProd(20,0);
});
</script>
