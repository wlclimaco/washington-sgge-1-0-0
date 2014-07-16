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

    columns.push(checkboxSelector.getColumnDefinition());
//columns & column settings for the grid
columns[1] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
columns[2] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[3] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[4] = {id:"nome", name: unimed.grid.punimed.title, field:"nome", editor:Slick.Editors.Text};
columns[5] = {id:"descricao", name: unimed.grid.pdescricao.title, field:"descricao", editor:Slick.Editors.Text};
columns[6] = {id:"produtos", name: menu.grid.pprodutos.title, field:"produtos", formatter: buttonFormat};
columns[7] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[8] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};

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

			var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,6,data[0].nome,data[0].descricao,null),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertCadastro', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros', {cadastro:{type:6,userId:'rod'}}, fill_data, process_error);

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

				var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(data[aRowChg[a]].id,6,data[aRowChg[a]].nome,data[aRowChg[a]].descricao),bList,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateCadastro', oData, fill_data, process_error);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:6,userId:'rod'}}, fill_data, process_error);

			}
		}

		function callDeleteWS(_procId)
		{

		    var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(_procId,6),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/deleteCadastro', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros', {cadastro:{type:6,userId:'rod'}}, fill_data, process_error);
			onProcDataLoading.notify({});
		}

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:6,userId:'rod'}}, fill_data, process_error);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onProcDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:6,userId:'rod'}}, fill_data, process_error);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"menu");
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
$('#unimedGrid').keyup(function(e)
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

$('#refreshunimed').click(function() {
	ploader.callRefreshWS(135);
});
</sec:authorize>
$('#listunimed').click(function() {
	 ploader.callPagedFetchWS(20,0);
});
</script>
