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
    <c:when test="${empty cadastroResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${cadastroResponse};
    </c:otherwise>
</c:choose>

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

//    columns.push(checkboxSelector.getColumnDefinition());
//columns & column settings for the grid
columns[0] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
columns[1] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[2] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[3] = {id:"nome", name: marca.grid.pmarca.title, field:"nome", editor:Slick.Editors.Text};
//columns[5] = {id:"imagens", name: menu.grid.pimagens.title, field:"imagens", formatter: buttonFormat};
columns[4] = {id:"produtos", name: menu.grid.pprodutos.title, field:"produtos",resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[5] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[6] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};

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
		var onMarcaDataLoading = new EventHelper();
		var onMarcaDataLoaded = new EventHelper();

		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		function callInsertWS()
		{
			onMarcaDataLoading.notify({});
			var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,2,data[0].nome,data[0].descricao,null),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertCadastro', oData, fill_data, process_error,true);
		}

		function callUpdateWS()
		{
			var tempLength = aRowChg.length;
			if (tempLength > 0)
			{
				onMarcaDataLoading.notify({});
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
				onMarcaDataLoading.notify({});
				var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(data[aRowChg[a]].id,2,data[aRowChg[a]].nome,data[aRowChg[a]].descricao),bList,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateCadastro', oData, fill_data, process_error,true);
			}
		}
		function openProdutos(value){
			dom = "<div class='id'>" + value + "</div>";
			$actionTagDialog = $("#action-produto-dialog").load('../produto/produtosDialogByRequestBAS').dialog({
				autoOpen: false,
				title: 'Lista Produto Supermercado',
				width: 1024,
				height: 400,
				modal: true,
				buttons: {
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog buttons-left',
				resizable: false
			});
			$actionTagDialog.empty();
			$actionTagDialog.dialog('open');
			openDialogCadastro({produto:{tabela:2,marca:{id:parseInt(value),userId:'rod'},userId:'rod'}});
			valueGlobal = parseInt(value);
		}

		function callDeleteWS(_procId)
		{
			onMarcaDataLoading.notify({});
		    var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(_procId,2),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/deleteCadastro', oData, fill_data, process_error,true);

		}

		function openDialogCadastro(oData)
		{

		    onMarcaDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',oData , fill_data2, process_error);

		}
		function fill_data2(procResponse)
		{

			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			onMarcaDataLoaded.notify({});
		}

		function exportCSVProd()
		{
			debugger;
		    onMarcaDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',{produto:{tabela:2,marca:{id:parseInt(valueGlobal),userId:'rod'},userId:'rod'}} , fill_dataCSV, process_error);
		}
		function fill_dataCSV(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			qat.model.supermercado.pages.JSONToCSVConvertor(data, "Supermercado", true);
			onMarcaDataLoaded.notify({});

		}

		function callRefreshWS(_i)
		{
			onMarcaDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:2,userId:'rod'}}, fill_data, process_error,true);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onMarcaDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:2,userId:'rod'}}, fill_data, process_error,true);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"marca");
			onMarcaDataLoaded.notify({});
		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onMarcaDataLoaded.notify({});
		}

		function isMarcaDataLoaded()
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
			"isMarcaDataLoaded": isMarcaDataLoaded,
			"callPagedFetchWS": callPagedFetchWS,
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			"callDeleteWS" : callDeleteWS,
			"callInsertWS" : callInsertWS,
			"callUpdateWS" : callUpdateWS,
			"callRefreshWS": callRefreshWS,
			"openProdutos" : openProdutos,
			"exportCSVProd":exportCSVProd,
			"openDialogCadastro" : openDialogCadastro,
			</sec:authorize>
			// events
			"onMarcaDataLoading": onMarcaDataLoading,
			"onMarcaDataLoaded": onMarcaDataLoaded
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

function validateFieldsMarca(rowValue)
{
	if (wd.core.isEmpty(data[rowValue].nome))
	{
		pgrid.gotoCell(rowValue,3,true);
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
$('#marcaGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
		if (validateFieldsMarca(rowChg))
			{
				ploaderMarca.callUpdateWS(aRowChg);
			}

		}
		else
		{
			if (validateFieldsMarca(0))
			{
				ploaderMarca.callInsertWS();
			}
		}
	}
});

$('#refreshmarca').click(function() {
	ploaderMarca.callRefreshWS(135);
});
</sec:authorize>
$('#listmarca').click(function() {
	 ploaderMarca.callPagedFetchWS(20,0);
});
</script>
