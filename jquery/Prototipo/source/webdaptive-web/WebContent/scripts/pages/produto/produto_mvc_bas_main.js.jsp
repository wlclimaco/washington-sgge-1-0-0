<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var dataProd = new Array();
var pgrid;
var gridPager;
var pagingData = new qat.model.pageData(null,null,null,null);
var id = $("div[id*='tabs']" ).attr("id");
var viewLoadedObject;

//loads object if being served via controller
<c:choose>
    <c:when test="${empty produtoResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${produtoResponse};
    </c:otherwise>
</c:choose>

var columns=[];
 var checkboxSelector = new Slick.CheckboxSelectColumn({
      cssClass: "slick-cell-checkboxsel"
    });
	var buttonFormat = function (row, cell, value, columnDef, dataContext) {

		return "<input type='button' value='Editar' class='btn "+value+"' />"

	}


columns.push(checkboxSelector.getColumnDefinition());
columns[1]  = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
columns[2]  = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[3]  = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[4]  = {id:"codBarra", name:produto.grid.codBarra.title, field:"codBarra",  width:135, editable:true, cssClass:"pad-4-left", sortable:true},
columns[5]  = {id:"nome", name: produto.grid.nome.title, field:"nome"};
columns[6]  = {id:"unimed", name: produto.grid.unimed.title, field:"unimed"};
columns[7]  = {id:"marca", name:produto.grid.marca.title, field:"marca",  width:135, editable:true, cssClass:"pad-4-left", sortable:true},
columns[8]  = {id:"menu", name: produto.grid.menu.title, field:"menu"};
columns[9] = {id:"supermercadoId", name: produto.grid.supermercadoId.title, field:"supermercadoId"};
columns[10] = {id:"preco", name: produto.grid.preco.title, field:"preco"};
columns[11] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[12] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};
columns[13] = {id:"id", name: " ", field:"id",  cssClass:"cell-center",formatter:  buttonFormat,width:72};




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

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {produto:{tabela:1,userId:'rod'}}, fill_data, process_error);
		}
		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onProcDataLoading.notify({});
			if (viewLoadedObject == null)
			{
			    rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {produto:{tabela:1,userId:'rod'}}, fill_data, process_error);
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
			if (dataProd == undefined || dataProd == null)
			{
				return false;
			}
			return true;
		}
		function callInsertWS(_Id)
		{
			debugger;
			onProcDataLoading.notify({});
			if(_Id > 0){
				var oData = callCreateObject(_Id);
				var oData = new qat.model.reqProduto(null, oData, true, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateProduto', oData, fill_data2, process_error);
			}else{
				var oData = callCreateObject();
				var oData = new qat.model.reqProduto(null, oData, true, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertProduto', oData, fill_data2, process_error);
			}
		}

		function callCreateObject(_id)
		{
			debugger;
			var tmpLength = dataProd.length;
			var oDataPreco =[];
			for (var i=1; i < tmpLength; i++){
				oDataPreco.push(new qat.model.preco(dataProd[i].id, parseInt(_id), dataProd[i].supermercadoid, null,dataProd[i].preco, dataProd[i].promocao,dataProd[i].precopromo, null, null));
			}
			return new qat.model.produto(parseInt(_id),
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
									 null,
									 1);
		}
		function fill_data(procResponse)
		{
			dataProd = reuse_fill_data(procResponse,dataProd,"produto");
			onProcDataLoaded.notify({});
		}
		function fill_data2(procResponse)
		{
			$("#action-produto-dialog").dialog('close');
			dataProd = reuse_fill_data(procResponse,dataProd,"produto");
			onProcDataLoaded.notify({});
		}
		return{
			// properties
			"data": dataProd,
			// methods
			"isProcDataLoaded": isProcDataLoaded,
			"callPagedFetchWS": callPagedFetchWS,
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			"callRefreshWS": callRefreshWS,
			"callInsertWS":callInsertWS,
			</sec:authorize>
			// events
			"onProcDataLoading": onProcDataLoading,
			"onProcDataLoaded": onProcDataLoaded
		};
	};
	$.extend(true, window, { Slick: { Data: { RemoteModel: RemoteModel }}});
})(jQuery);


$('#refreshprod').click(function() {
	ploaderPro.callRefreshWS(135);
});
$('#listprod').click(function() {
	 ploaderPro.callPagedFetchWS(20,0);
});
</script>
