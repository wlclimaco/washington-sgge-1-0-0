<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var data = new Array();
var pgrid;
var gridPager;
var availableTags = [];
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

columns[0] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
columns[1] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[2] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[3] = {id:"nome", name:submenu.grid.pmenu.title, field:"nome",  width:135, editable:true, cssClass:"pad-4-left", sortable:true, editor:Slick.Editors.completeSubMenu},
columns[4] = {id:"descricao", name: submenu.grid.psubmenu.title, field:"descricao", editor:Slick.Editors.Text};
//columns[6] = {id:"imagens", name: menu.grid.pimagens.title, field:"imagens", formatter: buttonFormat};
columns[5] = {id:"produtos", name: menu.grid.pprodutos.title, field:"produtos",resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[6] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[7] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};

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
			var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,4,data[0].nome,data[0].descricao,null),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertCadastro', oData, fill_data, process_error);
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
				var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(data[aRowChg[a]].id,4,data[aRowChg[a]].nome,data[aRowChg[a]].descricao),bList,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateCadastro', oData, fill_data, process_error);

			}
		}

		function callDeleteWS(_procId)
		{
			onProcDataLoading.notify({});
		    var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(_procId,4),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/deleteCadastro', oData, fill_data, process_error);
		}

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:4,userId:'rod'}}, fill_data, process_error,true);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onProcDataLoading.notify({});
			if (viewLoadedObject == null)
			{
			    rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:4,userId:'rod'}}, fill_data, process_error,true);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"submenu");
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
		function openProdutos(value)
		{
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
			if($.address.parameter('id') != null){
				tabval = ($.address.parameter('id'));
			}
			if(tabval == "qatmvctabsMarca"){
				var oData = {produto:{tabela:2,marca:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsMenu"){
				var oData = {produto:{tabela:3,menu:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsSub"){
				var oData = {produto:{tabela:4,menu:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsTri"){
				var oData = {produto:{tabela:5,menu:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsUnimed"){
				var oData = {produto:{tabela:6,unimed:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}
			openDialogCadastro(oData);
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
		function exportCSV()
		{
			if(tabval = "qatmvctabsMarca"){
				var oData = {produto:{tabela:3,marca:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsMenu"){
				var oData = {produto:{tabela:4,menu:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsSub"){
				var oData = {produto:{tabela:5,marca:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsTri"){
				var oData = {produto:{tabela:6,marca:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}else if(tabval == "qatmvctabsUnimed"){
				var oData = {produto:{tabela:7,unimed:{id:parseInt(value),userId:'rod'},userId:'rod'}}
			}
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',oData , fill_dataCSV, process_error);
		}
		function fill_dataCSV(procResponse)
		{
			qat.model.supermercado.pages.data = reuse_fill_data(procResponse,data,"produtoDialog2");
			qat.model.supermercado.pages.JSONToCSVConvertor(qat.model.supermercado.pages.data, "Supermercado", true);

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
			"openProdutos":openProdutos,
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


function validateFieldsSub(rowValue)
{

	if (wd.core.isEmpty(data[rowValue].nome))
	{
		pgrid.gotoCell(rowValue,3,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if (wd.core.isEmpty(data[rowValue].descricao))
	{
		pgrid.gotoCell(rowValue,4,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	} else if(availableTags.length > 0){
		if ( $.inArray(data[rowValue].nome,availableTags) == -1) {
			pgrid.gotoCell(rowValue,3,true);
			$(pgrid.getActiveCellNode()).addClass("invalid");
			$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
			wd.core.displayNotificationMessage('#StatusBar',"MENU NÃO CADASTRADO FAVOR ENTRAR NO MENU > CADASTRO MENU E CADASTRAR!", false, 'error', 5000);
		}else{
			return true;
		}
	}
	else
	{
		return true;
	}
	return false;
};

<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
$('#submenuGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
			if (validateFieldsSub(rowChg))
			{
				ploaderSub.callUpdateWS(aRowChg);
			}
		}
		else
		{
			if (validateFieldsSub(0))
			{
				ploaderSub.callInsertWS();
			}
		}
	}
});

$('#refreshmenu').click(function() {
	ploaderSub.callRefreshWS(135);
});
</sec:authorize>
$('#listmenu').click(function() {
	 ploaderSub.callPagedFetchWS(20,0);
});
</script>
