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
    <c:when test="${empty produtoResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${produtoResponse};
    </c:otherwise>
</c:choose>
console.log(viewLoadedObject);
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
columns[2]  = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[3]  = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[4]  = {id:"codBarra", name:produto.grid.codBarra, field:"codBarra",  width:135, editable:true, cssClass:"pad-4-left", sortable:true},
columns[5]  = {id:"nome", name: produto.grid.nome.title, field:"nome"};
columns[6]  = {id:"unimed", name: produto.grid.unimed.title, field:"unimed"};
columns[7]  = {id:"descricao", name: produto.grid.descricao.title, field:"descricao"};
columns[8]  = {id:"marca", name:produto.grid.marca.title, field:"marca",  width:135, editable:true, cssClass:"pad-4-left", sortable:true},
columns[9]  = {id:"menu", name: produto.grid.menu.title, field:"menu"};
columns[10] = {id:"submenu", name: produto.grid.submenu.title, field:"submenu"};
columns[11] = {id:"trimenu", name: produto.grid.trimenu.title, field:"trimenu"};
columns[12] = {id:"supermercadoId", name: produto.grid.supermercadoId.title, field:"supermercadoId"};
columns[13] = {id:"preco", name: produto.grid.preco.title, field:"preco"};
columns[14] = {id:"imagens", name: produto.grid.imagens.title, field:"imagens"};
columns[15] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[16] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};
columns[17] = {id:"id", name: " ", field:"id",  width:100,formatter: buttonFormat};




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
		function callInsertWS()
		{
			onProcDataLoading.notify({});
		   // var oData = new qat.model.reqCadastro(null, new qat.model.procedure(0,0,data[0].pcode,data[0].pdesc,0.0),true,true);qat.model.cadastro = function(_Id, _type, _nome, _descricao,_controlAcess)
			var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,4,data[0].nome,data[0].descricao,null),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertProduto', oData, fill_data, process_error);
			var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(null,3),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {cadastro:{type:4,userId:'rod'}}, fill_data, process_error);

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
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/ProdutoCadastro', oData, fill_data, process_error);
				var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,3),true,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',{cadastro:{type:4,userId:'rod'}}, fill_data, process_error);

			}
		}

		function callDeleteWS(_procId)
		{
			onProcDataLoading.notify({});
		    var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(_procId,4),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/deleteProduto', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {cadastro:{type:4,userId:'rod'}}, fill_data, process_error);
		}

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {cadastro:{type:4,userId:'rod'}}, fill_data, process_error);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onProcDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				//rest_post_call('qat-webdaptive/cadastro/api/fetchByRequestBAS', oData, fill_data, process_error);
			    rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {cadastro:{type:4,userId:'rod'}}, fill_data, process_error);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produto");
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
			"callDeleteWS": callDeleteWS,
			"callInsertWS": callInsertWS,
			"callUpdateWS": callUpdateWS,
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
$('#prodGrid').keyup(function(e)
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
				ploader.callInsertWS();
		//	}
		}
	}
});

$('#refreshprod').click(function() {
	ploader.callRefreshWS(135);
});
</sec:authorize>
$('#listprod').click(function() {
	 ploader.callPagedFetchWS(20,0);
});
</script>
